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

import java.util.StringTokenizer;

import net.sf.l2j.commons.lang.StringUtil;

import net.sf.l2j.gameserver.data.ItemTable;
import net.sf.l2j.gameserver.data.NpcTable;
import net.sf.l2j.gameserver.datatables.IconTable;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
import net.sf.l2j.gameserver.model.item.DropCategory;
import net.sf.l2j.gameserver.model.item.DropData;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;


public class Shiff_Mod implements IVoicedCommandHandler
{
 private final static int PAGE_LIMIT = 15;
       private static final String[] _voicedCommands = 
       {
 "shifffmodddrop",
 };

       @Override
       public boolean useVoicedCommand(String command, Player activeChar, String target)
       {
        
        if (activeChar.isDead() || activeChar.isFakeDeath() || activeChar.getKarma() > 0 || activeChar.getPvpFlag() > 0 || activeChar.isAlikeDead() || activeChar.isFestivalParticipant() || activeChar.isInJail() 
      || activeChar.isInOlympiadMode() || activeChar.inObserverMode() ||  activeChar.isFlying() || activeChar.isCastingNow() || activeChar.isTeleporting() || activeChar.isParalyzed() 
      || activeChar.isSleeping() || activeChar.isInDuel () || activeChar.isBetrayed() || activeChar.isMounted() || activeChar.isRooted())
       {
       activeChar.sendMessage("You Cannot Use This Command Right Now");
       activeChar.sendPacket(ActionFailed.STATIC_PACKET);
       return false;
       }
        
 if (command.startsWith("shifffmodddrop"))
 {
 final StringTokenizer st = new StringTokenizer(command, " ");
 st.nextToken();
 int npcId = Integer.parseInt(st.nextToken());
 int page = (st.hasMoreTokens()) ? Integer.parseInt(st.nextToken()) : 1;
 
 ShiffNpcDropList(activeChar, npcId, page);
 }
        return true;
       }
       
      @Override
      public String[] getVoicedCommandList()
      {
              return _voicedCommands;
      }
 
 private static void ShiffNpcDropList(Player activeChar, int npcId, int page)
 {
 final NpcTemplate npcData = NpcTable.getInstance().getTemplate(npcId);
 if (npcData == null)
 {
 activeChar.sendMessage("Npc template is unknown for id: " + npcId + ".");
 return;
 }
 
 final StringBuilder sb = new StringBuilder(2000);
 
 if (!npcData.getDropData().isEmpty())
 {
 StringUtil.append(sb, "<html><title> ", npcData.getName(), " Drop List  ", page, "</title><body>");
 StringUtil.append(sb, "<img src=\"L2UI.SquareWhite\" width=265 height=1>");
 StringUtil.append(sb, "<table border=0 bgcolor=\"000000\" width=\"265\"><tr>");
 StringUtil.append(sb, "<td width=2 align=\"center\"><font color=\"505050\">|</font></a></td>");
 StringUtil.append(sb, "<td width=\"65\" align=\"center\"><font color=\"LEVEL\">Name Item</font></td>");
 StringUtil.append(sb, "<td width=2 align=\"center\"><font color=\"505050\">|</font></a></td>");
 StringUtil.append(sb, "<td width=\"65\" align=\"center\"><font color=\"FF6600\">Quantity Drop</font></td>");
 StringUtil.append(sb, "<td width=2 align=\"center\"><font color=\"505050\">|</font></a></td>");
 StringUtil.append(sb, "<td width=\"65\" align=\"center\">Chance Drop</td>");
 StringUtil.append(sb, "<td width=2 align=\"center\"><font color=\"505050\">|</font></a></td></tr><tr><td></td><td></td><td></td><td></td></tr></table>");
 StringUtil.append(sb, " <img src=\"L2UI.SquareWhite\" width=265height=1>");
 StringUtil.append(sb, "<table border=\"0\" bgcolor=\"000000\" width=\"265\"><tr>");
 StringUtil.append(sb, "<td width=2 align=\"center\"><font color=\"505050\">||</font></a></td>");
 StringUtil.append(sb, "<td width=\"135\" align=\"center\"><font color=\"3BB9FF\">Type Drop</font></td>");
 StringUtil.append(sb, "<td width=2 align=\"center\"><font color=\"505050\">||</font></a></td>");
 StringUtil.append(sb, "<td width=\"135\" align=\"center\"><font color=\"00FF00\">Type Sweep</font></td>");
 StringUtil.append(sb, "<td width=2 align=\"center\"><font color=\"505050\">||</font></a></td>");
 StringUtil.append(sb, "</tr><tr><td></td><td></td><td></td><td></td></tr></table>");
 StringUtil.append(sb, "<img src=\"L2UI.SquareWhite\" width=265 height=1>");
 int myPage = 1;
 int i = 0;
 int shown = 0;
 boolean hasMore = false;
 
 for (DropCategory cat : npcData.getDropData())
 {
 if (shown == PAGE_LIMIT)
 {
 hasMore = true;
 break;
 }
 
 for (DropData drop : cat.getAllDrops())
 {
 if (myPage != page)
 {
 i++;
 if (i == PAGE_LIMIT)
 {
 myPage++;
 i = 0;
 }
 continue;
 }
 
 if (shown == PAGE_LIMIT)
 {
 hasMore = true;
 break;
 }
 
 StringUtil.append(sb, "<table width=\"100%\" bgcolor=000000><tr>");
 StringUtil.append(sb, "<td><img src=\"" + IconTable.getIcon(drop.getItemId()) + "\" width=32 height=32></td><td>");
 StringUtil.append(sb, "<table><tr><td><font color=\"LEVEL\">", ItemTable.getInstance().getTemplate(drop.getItemId()).getName(), "</font><font color=\"FF6600\"> --", drop.getMinDrop(), "</font></td></tr>");
 StringUtil.append(sb, "<tr><td>Drop Rate: " + (double) drop.getChance() / 10000 + "%" + (drop.isQuestDrop() ? "Quest" : (cat.isSweep() ? "<font color=\"00FF00\">Sweep</font><img src=\"L2UI.SquareWhite\" width=260 height=1>" : "<font color=\"3BB9FF\">Drop</font><img src=\"L2UI.SquareWhite\" width=260 height=1>")) + "</td></tr></table></td></tr>");
 shown++;
 }
 }
 
 StringUtil.append(sb, "</table><table width=\"100%\" bgcolor=000000><tr>");
 
 if (page > 1)
 {
 StringUtil.append(sb, "<td width=120><a action=\"bypass -h voiced_shifffmodddrop ", npcId, " ", page - 1, "\">Prev Page</a></td>");
 if (!hasMore)
 StringUtil.append(sb, "<td width=100>Page ", page, "</td><td width=70></td></tr>");
 }
 
 if (hasMore)
 {
 if (page <= 1)
 StringUtil.append(sb, "<td width=120></td>");
 
 StringUtil.append(sb, "<td width=100>Page ", page, "</td><td width=70><a action=\"bypass -h voiced_shifffmodddrop ", npcId, " ", page + 1, "\">Next Page</a></td></tr>");
 }
 StringUtil.append(sb, "</table>");
 }
 else
 StringUtil.append(sb, "This NPC has no drops.");
 
 StringUtil.append(sb, "</body></html>");
 
 final NpcHtmlMessage html = new NpcHtmlMessage(0);
 html.setHtml(sb.toString());
 activeChar.sendPacket(html);
 }
 
 public void addRadar(Player activeChar, int x, int y, int z)
 {
 activeChar.getRadar().addMarker(x, y, z);
 }

 }