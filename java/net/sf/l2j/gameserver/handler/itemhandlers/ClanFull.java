package net.sf.l2j.gameserver.handler.itemhandlers;

import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;

public class ClanFull implements IItemHandler
{
   private final int reputation = 150000;
   private final byte level = 8;
   
   // id skills
   private final int[] clanSkills =
   {
       370,
       371,
       372,
       373,
       374,
       375,
       376,
       377,
       378,
       379,
       380,
       381,
       382,
       383,
       384,
       385,
       386,
       387,
       388,
       389,
       390,
       391
   };
  
	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse)
	{
		if (!(playable instanceof Player))
			return;

		Player activeChar = (Player) playable;

        if (activeChar.isClanLeader())
        {
            if (activeChar.getClan().getLevel() == 8)
            {
                activeChar.sendMessage("Your clan is already maximum level!");
                return;
            }
            activeChar.getClan().changeLevel(level);
            activeChar.getClan().addReputationScore(reputation);
			
            activeChar.getClan().changeLevel(8);
            activeChar.getClan().addReputationScore(150000);
               
            for (int s : clanSkills)
		    for (int i = 370; i <= 391; i++)
            {
                 L2Skill clanSkill = SkillTable.getInstance().getInfo(s, SkillTable.getInstance().getMaxLevel(s));
                 activeChar.getClan().addNewSkill(clanSkill);
            }
            int i = 0;
			activeChar.getClan().addNewSkill(SkillTable.getInstance().getInfo(i, SkillTable.getInstance().getMaxLevel(i)));
		 
            activeChar.sendSkillList();
            activeChar.sendPacket(new ExShowScreenMessage("Congratulations! Your Clan is now level 8!", 8000)); 
            activeChar.getClan().updateClanInDB();            
            activeChar.sendMessage("Clan Full Successfully added!");      
           playable.destroyItem("Consume", item.getObjectId(), 1, null, false);
		  	playable.destroyItem("Consume", item.getObjectId(), 1, null, true);	
            activeChar.broadcastUserInfo();
        }
        else
            activeChar.sendMessage("Acess Denied! You are not the Clan Leader.");  

       return;
	}
}