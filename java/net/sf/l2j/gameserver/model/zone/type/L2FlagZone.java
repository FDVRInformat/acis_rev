package net.sf.l2j.gameserver.model.zone.type;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.zone.L2SpawnZone;
import net.sf.l2j.gameserver.model.zone.ZoneId;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.taskmanager.PvpFlagTaskManager;

/**
 * An arena
 * @author Sarada
 */
public class L2FlagZone extends L2SpawnZone
{
	public L2FlagZone(int id)
	{
		super(id);
	}
	
	@Override
	protected void onEnter(Creature character)
	{
		 if (character instanceof Player)
			{
				final Player player = (Player) character;
				
				character.setInsideZone(ZoneId.FLAG, true);
				character.setInsideZone(ZoneId.NO_SUMMON_FRIEND, true);
				player.updatePvPFlag(1);
				player.sendPacket(SystemMessageId.ENTERED_COMBAT_ZONE);
	            return;
			}
	     }
	@Override
	protected void onExit(Creature character)
	{
		if (character instanceof Player)
	     {
	         final Player activeChar = (Player) character;
			 character.setInsideZone(ZoneId.FLAG, false);
			 character.setInsideZone(ZoneId.NO_SUMMON_FRIEND, false);
			 activeChar.updatePvPFlag(0);
			 PvpFlagTaskManager.getInstance().add(activeChar, Config.PVP_NORMAL_TIME);
			 activeChar.sendPacket(SystemMessageId.LEFT_COMBAT_ZONE);
	         return;
	     }
	   }
	
	@Override
	public void onDieInside(Creature character)
	{
	}
	
	@Override
	public void onReviveInside(Creature character)
	{
	}
}