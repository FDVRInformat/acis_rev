package net.sf.l2j.gameserver.handler.usercommandhandlers;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.events.teamvsteam.TvTEvent;
import net.sf.l2j.gameserver.handler.IUserCommandHandler;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.zone.ZoneId;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.PlaySound;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;

public class Escape implements IUserCommandHandler
{
	private static final int[] COMMAND_IDS =
	{
		52
	};

	@Override
	public boolean useUserCommand(int id, Player activeChar)
	{
		if (!TvTEvent.onEscapeUse(activeChar.getObjectId()) || activeChar.isCastingNow() || activeChar.isSitting() || activeChar.isMovementDisabled() || activeChar.isOutOfControl() || activeChar.isInOlympiadMode() || activeChar.inObserverMode() || activeChar.isFestivalParticipant() || activeChar.isInJail() || activeChar.isAio() || activeChar.isInsideZone(ZoneId.PVP))
		{
			activeChar.sendPacket(SystemMessageId.NO_UNSTUCK_PLEASE_SEND_PETITION);
			return false;
		}
		if (activeChar.isArenaProtection() && activeChar.isInsideZone(ZoneId.TOURNAMENT))
		{
			activeChar.sendMessage("You cannot use this skill in Tournament Event/Zone.");
			return false;
		}
		activeChar.stopMove(null);

		// Official timer 5 minutes, for GM 1 second
		if (activeChar.isGM())
			activeChar.doCast(SkillTable.getInstance().getInfo(2100, 1));
		else
		{
			activeChar.sendPacket(new PlaySound("systemmsg_e.809"));
			int unstuckTimer = Config.UNSTUCK_TIME * 1000;

			L2Skill skill = SkillTable.getInstance().getInfo(2099, 1);
			skill.setHitTime(unstuckTimer);
			activeChar.doCast(skill);
			
			if (unstuckTimer < 60000)
				activeChar.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_S2).addString("You will unstuck in " + unstuckTimer / 1000 + " seconds."));
			else
				activeChar.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_S2).addString("You will unstuck i " + unstuckTimer / 60000 + " minutes."));
			
		}

		return true;
	}

	@Override
	public int[] getUserCommandList()
	{
		return COMMAND_IDS;
	}
}