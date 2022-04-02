package net.sf.l2j.gameserver.handler.itemhandlers;

import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;

public class ItemNobles implements IItemHandler
{
	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse)
	{
		if (!(playable instanceof Player))
			return;

		Player activeChar = (Player) playable;

		if(activeChar.isInOlympiadMode())
		{
			activeChar.sendMessage("This item cannot be used on Olympiad Games.");
		}

		if(activeChar.isNoble())
		{
			activeChar.sendMessage("You are already a noblesse.");
		}
		else
		{
        if(activeChar.isNoble())
		{
			activeChar.sendMessage("You are already a noblesse.");
           return;
		}

			activeChar.broadcastPacket(new MagicSkillUse(activeChar, 5103, 1, 1000, 0));
			activeChar.sendPacket(new ExShowScreenMessage("Congratulations! You are a Noble!", 8000)); 
			activeChar.setNoble(true, true);
			activeChar.getInventory().addItem("Tiara", 7694, 1, activeChar, null);
			activeChar.sendMessage("You are now a Noble, Check your Skills!");
			playable.destroyItem("Consume", item.getObjectId(), 1, null, false);
           activeChar.broadcastUserInfo();
		}
		activeChar = null;
	}
}