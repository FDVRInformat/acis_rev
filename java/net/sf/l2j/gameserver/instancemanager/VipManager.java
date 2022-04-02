package net.sf.l2j.gameserver.instancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import net.sf.l2j.commons.concurrent.ThreadPool;

import net.sf.l2j.Config;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;

public class VipManager
{
	private static final Logger _log = Logger.getLogger(VipManager.class.getName());
	private final Map<Integer, Long> _vips;
	protected final Map<Integer, Long> _vipsTask;
	private ScheduledFuture<?> _scheduler;
	
	public static VipManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected VipManager()
	{
		_vips = new ConcurrentHashMap<>();
		_vipsTask = new ConcurrentHashMap<>();
		_scheduler = ThreadPool.scheduleAtFixedRate(new VipTask(), 1000L, 1000L);
		load();
	}
	
	public void reload()
	{
		_vips.clear();
		_vipsTask.clear();
		if (_scheduler != null)
		{
			_scheduler.cancel(true);
		}
		_scheduler = ThreadPool.scheduleAtFixedRate(new VipTask(), 1000L, 1000L);
		load();
	}
	
	public void load()
	{
		try
		{
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			Throwable localThrowable3 = null;
			try
			{
				PreparedStatement statement = con.prepareStatement("SELECT objectId, duration FROM character_vip ORDER BY objectId");
				ResultSet rs = statement.executeQuery();
				while (rs.next())
				{
					_vips.put(Integer.valueOf(rs.getInt("objectId")), Long.valueOf(rs.getLong("duration")));
				}
				rs.close();
				statement.close();
			}
			catch (Throwable localThrowable1)
			{
				localThrowable3 = localThrowable1;
				throw localThrowable1;
			}
			finally
			{
				if (con != null)
				{
					if (localThrowable3 != null)
					{
						try
						{
							con.close();
						}
						catch (Throwable localThrowable2)
						{
							localThrowable3.addSuppressed(localThrowable2);
						}
					}
					else
					{
						con.close();
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.warning("Exception: VipManager load: " + e.getMessage());
		}
		_log.info("VipManager: Loaded " + _vips.size() + " characters with vip privileges.");
	}
	
	public void addVip(int objectId, long duration)
	{
		_vips.put(Integer.valueOf(objectId), Long.valueOf(duration));
		_vipsTask.put(Integer.valueOf(objectId), Long.valueOf(duration));
		addVipPrivileges(objectId, true);
		
		try
		{
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			Throwable localThrowable3 = null;
			try
			{
				PreparedStatement statement = con.prepareStatement("INSERT INTO character_vip (objectId, duration) VALUES (?, ?)");
				statement.setInt(1, objectId);
				statement.setLong(2, duration);
				statement.execute();
				statement.close();
			}
			catch (Throwable localThrowable1)
			{
				localThrowable3 = localThrowable1;
				throw localThrowable1;
			}
			finally
			{
				if (con != null)
				{
					if (localThrowable3 != null)
					{
						try
						{
							con.close();
						}
						catch (Throwable localThrowable2)
						{
							localThrowable3.addSuppressed(localThrowable2);
						}
					}
					else
					{
						con.close();
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.warning("Exception: VipManager addVip: " + e.getMessage());
		}
	}
	
	public void updateVip(int objectId, long duration)
	{
		_vips.put(Integer.valueOf(objectId), Long.valueOf(duration));
		_vipsTask.put(Integer.valueOf(objectId), Long.valueOf(duration));
		try
		{
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			Throwable localThrowable3 = null;
			try
			{
				PreparedStatement statement = con.prepareStatement("UPDATE character_vip SET duration = ? WHERE objectId = ?");
				statement.setLong(1, duration);
				statement.setInt(2, objectId);
				statement.execute();
				statement.close();
			}
			catch (Throwable localThrowable1)
			{
				localThrowable3 = localThrowable1;
				throw localThrowable1;
			}
			finally
			{
				if (con != null)
				{
					if (localThrowable3 != null)
					{
						try
						{
							con.close();
						}
						catch (Throwable localThrowable2)
						{
							localThrowable3.addSuppressed(localThrowable2);
						}
					}
					else
					{
						con.close();
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.warning("Exception: VipManager updateVip: " + e.getMessage());
		}
	}
	
	public void removeVip(int objectId)
	{
		_vips.remove(Integer.valueOf(objectId));
		_vipsTask.remove(Integer.valueOf(objectId));
		removeVipPrivileges(objectId, false);
		try
		{
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			Throwable localThrowable3 = null;
			try
			{
				PreparedStatement statement = con.prepareStatement("DELETE FROM character_vip WHERE objectId = ?");
				statement.setInt(1, objectId);
				statement.execute();
				statement.close();
			}
			catch (Throwable localThrowable1)
			{
				localThrowable3 = localThrowable1;
				throw localThrowable1;
			}
			finally
			{
				if (con != null)
				{
					if (localThrowable3 != null)
					{
						try
						{
							con.close();
						}
						catch (Throwable localThrowable2)
						{
							localThrowable3.addSuppressed(localThrowable2);
						}
					}
					else
					{
						con.close();
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.warning("Exception: VipManager removeVip: " + e.getMessage());
		}
	}
	
	public boolean hasVipPrivileges(int objectId)
	{
		return _vips.containsKey(Integer.valueOf(objectId));
	}
	
	public long getVipDuration(int objectId)
	{
		return _vips.get(Integer.valueOf(objectId)).longValue();
	}
	
	public void addVipTask(int objectId, long duration)
	{
		_vipsTask.put(Integer.valueOf(objectId), Long.valueOf(duration));
	}
	
	public void removeVipTask(int objectId)
	{
		_vipsTask.remove(Integer.valueOf(objectId));
	}
	
	public void addVipPrivileges(int objectId, boolean apply)
	{
		Player player = World.getInstance().getPlayer(objectId);
		player.setVip(true);
		if(Config.ALLOW_VIP_NCOLOR)
			player.getAppearance().setNameColor(Config.VIP_NCOLOR);
		
		if(Config.ALLOW_VIP_TCOLOR)
			player.getAppearance().setTitleColor(Config.VIP_TCOLOR);
		player.broadcastUserInfo();
	}
	
	public void removeVipPrivileges(int objectId, boolean apply)
	{
		Player player = World.getInstance().getPlayer(objectId);
		player.setVip(false);
		player.getAppearance().setNameColor(0xFFFF77);
		player.getAppearance().setTitleColor(0xFFFF77);
		player.broadcastUserInfo();
	}
	
	public class VipTask implements Runnable
	{
		public VipTask()
		{
		}
		
		@Override
		public final void run()
		{
			if (_vipsTask.isEmpty())
			{
				return;
			}
			for (Map.Entry<Integer, Long> entry : _vipsTask.entrySet())
			{
				long duration = entry.getValue().longValue();
				if (System.currentTimeMillis() > duration)
				{
					int objectId = entry.getKey().intValue();
					removeVip(objectId);
					
					Player player = World.getInstance().getPlayer(objectId);
					player.sendPacket(new ExShowScreenMessage("Your Vip privileges were removed.", 10000));
				}
			}
		}
	}
	
	private static class SingletonHolder
	{
		protected static final VipManager _instance = new VipManager();
	}
}
