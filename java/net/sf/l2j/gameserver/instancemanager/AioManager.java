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

public class AioManager
{
	private static final Logger _log = Logger.getLogger(AioManager.class.getName());
	private final Map<Integer, Long> _aios;
	protected final Map<Integer, Long> _aiosTask;
	private ScheduledFuture<?> _scheduler;
	
	public static AioManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected AioManager()
	{
		_aios = new ConcurrentHashMap<>();
		_aiosTask = new ConcurrentHashMap<>();
		_scheduler = ThreadPool.scheduleAtFixedRate(new AioTask(), 1000L, 1000L);
		load();
	}
	
	public void reload()
	{
		_aios.clear();
		_aiosTask.clear();
		if (_scheduler != null)
		{
			_scheduler.cancel(true);
		}
		_scheduler = ThreadPool.scheduleAtFixedRate(new AioTask(), 1000L, 1000L);
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
				PreparedStatement statement = con.prepareStatement("SELECT objectId, duration FROM character_aio ORDER BY objectId");
				ResultSet rs = statement.executeQuery();
				while (rs.next())
				{
					_aios.put(Integer.valueOf(rs.getInt("objectId")), Long.valueOf(rs.getLong("duration")));
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
			_log.warning("Exception: AioManager load: " + e.getMessage());
		}
		_log.info("AioManager: Loaded " + _aios.size() + " characters with aio privileges.");
	}
	
	public void addAio(int objectId, long duration)
	{
		_aios.put(Integer.valueOf(objectId), Long.valueOf(duration));
		_aiosTask.put(Integer.valueOf(objectId), Long.valueOf(duration));
		addAioPrivileges(objectId, true);
		
		try
		{
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			Throwable localThrowable3 = null;
			try
			{
				PreparedStatement statement = con.prepareStatement("INSERT INTO character_aio (objectId, duration) VALUES (?, ?)");
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
			_log.warning("Exception: AioManager addAio: " + e.getMessage());
		}
	}
	
	public void updateAio(int objectId, long duration)
	{
		_aios.put(Integer.valueOf(objectId), Long.valueOf(duration));
		_aiosTask.put(Integer.valueOf(objectId), Long.valueOf(duration));
		try
		{
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			Throwable localThrowable3 = null;
			try
			{
				PreparedStatement statement = con.prepareStatement("UPDATE character_aio SET duration = ? WHERE objectId = ?");
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
			_log.warning("Exception: AioManager updateAio: " + e.getMessage());
		}
	}
	
	public void removeAio(int objectId)
	{
		_aios.remove(Integer.valueOf(objectId));
		_aiosTask.remove(Integer.valueOf(objectId));
		removeAioPrivileges(objectId, false);
		try
		{
			Connection con = L2DatabaseFactory.getInstance().getConnection();
			Throwable localThrowable3 = null;
			try
			{
				PreparedStatement statement = con.prepareStatement("DELETE FROM character_aio WHERE objectId = ?");
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
			_log.warning("Exception: AioManager removeAio: " + e.getMessage());
		}
	}
	
	public boolean hasAioPrivileges(int objectId)
	{
		return _aios.containsKey(Integer.valueOf(objectId));
	}
	
	public long getAioDuration(int objectId)
	{
		return _aios.get(Integer.valueOf(objectId)).longValue();
	}
	
	public void addAioTask(int objectId, long duration)
	{
		_aiosTask.put(Integer.valueOf(objectId), Long.valueOf(duration));
	}
	
	public void removeAioTask(int objectId)
	{
		_aiosTask.remove(Integer.valueOf(objectId));
	}
	
	public void addAioPrivileges(int objectId, boolean apply)
	{
		Player player = World.getInstance().getPlayer(objectId);
		player.setAio(true);
		player.getStat().addExp(player.getStat().getExpForLevel(81));
		if(Config.ALLOW_AIO_NCOLOR)
			player.getAppearance().setNameColor(Config.AIO_NCOLOR);
		
		if(Config.ALLOW_AIO_TCOLOR)
			player.getAppearance().setTitleColor(Config.AIO_TCOLOR);
		player.addAioSkills();
		player.getInventory().addItem("", Config.AIO_ITEM_ID, 1, player, null);
		player.getInventory().equipItem(player.getInventory().getItemByItemId(Config.AIO_ITEM_ID));
		player.sendSkillList();
		player.broadcastUserInfo();
	}
	
	public void removeAioPrivileges(int objectId, boolean apply)
	{
		Player player = World.getInstance().getPlayer(objectId);
		player.setAio(false);
		player.getAppearance().setNameColor(0xFFFF77);
		player.getAppearance().setTitleColor(0xFFFF77);
		player.lostAioSkills();
		
		player.getInventory().destroyItemByItemId("", Config.AIO_ITEM_ID, 1, player, null);
		player.getWarehouse().destroyItemByItemId("", Config.AIO_ITEM_ID, 1, player, null);
		player.sendSkillList();
		player.broadcastUserInfo();
	}
	
	public class AioTask implements Runnable
	{
		public AioTask()
		{
		}
		
		@Override
		public final void run()
		{
			if (_aiosTask.isEmpty())
			{
				return;
			}
			for (Map.Entry<Integer, Long> entry : _aiosTask.entrySet())
			{
				long duration = entry.getValue().longValue();
				if (System.currentTimeMillis() > duration)
				{
					int objectId = entry.getKey().intValue();
					removeAio(objectId);
					
					Player player = World.getInstance().getPlayer(objectId);
					player.sendPacket(new ExShowScreenMessage("Your Aio privileges were removed.", 10000));
				}
			}
		}
	}
	
	private static class SingletonHolder
	{
		protected static final AioManager _instance = new AioManager();
	}
}
