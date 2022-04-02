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
package net.sf.l2j.gameserver.handler.voicedcommandhandlers;

import java.util.logging.Logger;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.data.NpcTable;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.instancemanager.GrandBossManager;
import net.sf.l2j.gameserver.instancemanager.RaidBossSpawnManager;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.gameserver.templates.StatsSet;

/**
 * @author Baggos
 */
public class GrandBossStatus implements IVoicedCommandHandler
{
	private static Logger _log = Logger.getLogger(GrandBossStatus.class.getName());
	private static final String[] _voicedCommands =
	{
		"raidinfo"
	};
	
	@Override
	public boolean useVoicedCommand(String command, Player activeChar, String params)
	{
		if (command.equals("raidinfo") && Config.EPIC_INFO)
		{
			StringBuilder tb = new StringBuilder();
			tb.append("<html><title>Raid Info</title><body><center>");
			tb.append("<font color=\"a2a0a2\">Raid Boss respawn time</font><br>");
			tb.append("<img src=\"L2UI.SquareGray\" width=300 height=1><br>");
			
			for (int boss : Config.GRAND_BOSS_LIST)
			{
				String name = "";
				NpcTemplate template = null;
				if ((template = NpcTable.getInstance().getTemplate(boss)) != null)
				{
					name = template.getName();
				}
				else
				{
					_log.warning("Raid Info: Raid Boss with ID " + boss + " is not defined into NpcXml");
					continue;
				}
				
				StatsSet actual_boss_stat = null;
				
				GrandBossManager.getInstance().getStatsSet(boss);
				long delay = 0;
				
				if (NpcTable.getInstance().getTemplate(boss).isType("RaidBoss"))
				{
					delay = RaidBossSpawnManager.getInstance().getRespawntime(boss);
					
				}
				else if (NpcTable.getInstance().getTemplate(boss).isType("GrandBoss"))
				{
					actual_boss_stat = GrandBossManager.getInstance().getStatsSet(boss);
					if (actual_boss_stat != null)
						delay = actual_boss_stat.getLong("respawn_time");
				}
				else
					continue;
				
				if (delay <= System.currentTimeMillis())
				{
					tb.append("&nbsp;<font color=\"FFFFFF\">" + name + "</font>: " + "<font color=\"4d94ff\">Is Alive</font>" + "<br1>");
					tb.append("<img src=\"L2UI.SquareGray\" width=300 height=1><br>");
				}
				else
				{
					int hours = (int) ((delay - System.currentTimeMillis()) / 1000 / 60 / 60);
					int mins = (int) (((delay - (hours * 60 * 60 * 1000)) - System.currentTimeMillis()) / 1000 / 60);
					int seconts = (int) (((delay - ((hours * 60 * 60 * 1000) + (mins * 60 * 1000))) - System.currentTimeMillis()) / 1000);
					tb.append("&nbsp;<font color=\"4d94ff\">" + name + "</font>" + "<font color=\"FFFFFF\">" + " " + "Respawn in :</font>" + " " + " <font color=\"FF9900\">" + hours + " : " + mins + " : " + seconts + "</font><br1>");
					tb.append("<img src=\"L2UI.SquareGray\" width=300 height=1><br>");
				}
			}
			
			tb.append("</center></body></html>");
			
			NpcHtmlMessage msg = new NpcHtmlMessage(1);
			msg.setHtml(tb.toString());
			
			activeChar.sendPacket(msg);
		}
		return true;
	}

	@Override
	public String[] getVoicedCommandList()
	{
		return _voicedCommands;
	}
}
