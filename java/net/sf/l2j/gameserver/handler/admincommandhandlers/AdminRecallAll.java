package net.sf.l2j.gameserver.handler.admincommandhandlers;

import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;

public class AdminRecallAll implements IAdminCommandHandler

{
	private static final String[] ADMIN_COMMANDS = { "admin_recallall" };
	
	private static void teleportTo(Player activeChar, int x, int y, int z)
	{
		if (activeChar.isAio() || activeChar.getClient().isDetached() || activeChar.isInStoreMode() || activeChar.isInOlympiadMode())
			return;
		activeChar.teleToLocation(x, y, z, 0);
	}
	
	@Override
	public boolean useAdminCommand(String command, Player activeChar)
	{

		if (command.startsWith("admin_recallall"))	
		{
			for(Player players :World.getInstance().getPlayers())
			{
				/*if (!activeChar.isAio())
					continue;*/
			
					teleportTo(players, activeChar.getX(), activeChar.getY(), activeChar.getZ());
			}
		}
		
		return false;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}