/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver.scripting.scripts.custom;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.actor.Npc;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.SocialAction;
import net.sf.l2j.gameserver.scripting.Quest;

public class BarakielNobless extends Quest
{
	public BarakielNobless()
	{
		super(-1, "custom");

		addKillId(Config.BOSS_CUSTOM_NOBLES_ID);
	}

	@Override
	public String onKill(Npc npc, Player player, boolean isPet)
	{
		if (player.getParty() != null)
		{
			for (Player members : player.getParty().getMembers())
			{
				
				if (!members.isInsideRadius(npc, Config.DISTANCE_FOR_RENEWAL_NOBLES_PARTY, false, false))
				{
					members.sendMessage("You were too far away from Event Boss. You've missed the chance of becoming Noblesse!");
					continue;
				}
				if(members.isDead())
				{
					members.sendMessage("You can't is dead to receive noble status.");
					continue;
				}
				if(members.isNoble())
				{
					members.sendMessage("You are already Noblesse!");
					continue;
				}
				if(Config.ENABLE_RAIDBOSS_NOBLES)
					if (!members.isNoble())
					{
						members.setNoble(true, true);
						members.getInventory().addItem("Noblesse Tiara", 7694, 1, members, null);
						members.broadcastPacket(new SocialAction(members, 16));
						members.sendMessage("Congratulations! All party members have obtained Noblesse Status");
					}
			}
		}
		
		return null;
	}

	public static void main(String args[])
	{
		new BarakielNobless();
	}
}