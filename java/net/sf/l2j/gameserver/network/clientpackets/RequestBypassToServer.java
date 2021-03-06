package net.sf.l2j.gameserver.network.clientpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.l2j.commons.concurrent.ThreadPool;

import net.sf.l2j.Config;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.cache.HtmCache;
import net.sf.l2j.gameserver.communitybbs.CommunityBoard;
import net.sf.l2j.gameserver.data.xml.AdminData;
import net.sf.l2j.gameserver.handler.AdminCommandHandler;
import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.handler.VoicedCommandHandler;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.AioMenu;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Repair;
import net.sf.l2j.gameserver.instancemanager.BotsPreventionData;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Npc;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.instance.MultiNpc;
import net.sf.l2j.gameserver.model.actor.instance.OlympiadManagerNpc;
import net.sf.l2j.gameserver.model.entity.Hero;
import net.sf.l2j.gameserver.model.olympiad.OlympiadManager;
import net.sf.l2j.gameserver.network.FloodProtectors;
import net.sf.l2j.gameserver.network.FloodProtectors.Action;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public final class RequestBypassToServer extends L2GameClientPacket
{
	private static final Logger GMAUDIT_LOG = Logger.getLogger("gmaudit");

	public static String _command;
	@Override
	protected void readImpl()
	{
		_command = readS();
	}

	@Override
	protected void runImpl()
	{
		if (!FloodProtectors.performAction(getClient(), Action.SERVER_BYPASS))
			return;

		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null)
			return;

		if (_command.isEmpty())
		{
			_log.info(activeChar.getName() + " sent an empty requestBypass packet.");
			activeChar.logout();
			return;
		}

		try
		{
			if (_command.startsWith("admin_"))
			{
				String command = _command.split(" ")[0];

				IAdminCommandHandler ach = AdminCommandHandler.getInstance().getAdminCommandHandler(command);
				if (ach == null)
				{
					if (activeChar.isGM())
						activeChar.sendMessage("The command " + command.substring(6) + " doesn't exist.");

					_log.warning("No handler registered for admin command '" + command + "'");
					return;
				}

				if (!AdminData.getInstance().hasAccess(command, activeChar.getAccessLevel()))
				{
					activeChar.sendMessage("You don't have the access rights to use this command.");
					_log.warning(activeChar.getName() + " tried to use admin command " + command + " without proper Access Level.");
					return;
				}

				if (Config.GMAUDIT)
					GMAUDIT_LOG.info(activeChar.getName() + " [" + activeChar.getObjectId() + "] used '" + _command + "' command on: " + ((activeChar.getTarget() != null) ? activeChar.getTarget().getName() : "none"));

				ach.useAdminCommand(_command, activeChar);
			}
			else if (_command.startsWith("voiced_"))
			{
				String command = _command.split(" ")[0];
				
				IVoicedCommandHandler ach = VoicedCommandHandler.getInstance().getHandler(_command.substring(7));
				
				if (ach == null)
				{
					activeChar.sendMessage("The command " + command.substring(7) + " does not exist!");
					_log.warning("No handler registered for command '" + _command + "'");
					return;
				}
				
				ach.useVoicedCommand(_command.substring(7), activeChar, null);
			}
			else if (_command.startsWith("aiopanel"))
			{
				String value = _command.substring(8);
				StringTokenizer st = new StringTokenizer(value);
				String command = st.nextToken();

				AioMenu.bypass(activeChar, command, st);
			}
			else if (_command.startsWith("player_help "))
			{
				playerHelp(activeChar, _command.substring(12));
			}
			
			
			else if (_command.startsWith("npc_"))
			{
				if (!activeChar.validateBypass(_command))
					return;

				int endOfId = _command.indexOf('_', 5);
				String id;
				if (endOfId > 0)
					id = _command.substring(4, endOfId);
				else
					id = _command.substring(4);

				try
				{
					final WorldObject object = World.getInstance().getObject(Integer.parseInt(id));

					if (object != null && object instanceof Npc && endOfId > 0 && ((Npc) object).canInteract(activeChar))
						((Npc) object).onBypassFeedback(activeChar, _command.substring(endOfId + 1));

					activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				}
				catch (NumberFormatException nfe)
				{
				}
			}
			// Navigate throught Manor windows
			else if (_command.startsWith("manor_menu_select?"))
			{
				WorldObject object = activeChar.getTarget();
				if (object instanceof Npc)
					((Npc) object).onBypassFeedback(activeChar, _command);
			}
			else if (_command.startsWith("bbs_") || _command.startsWith("_bbs") || _command.startsWith("_friend") || _command.startsWith("_mail") || _command.startsWith("_block"))
			{
				CommunityBoard.getInstance().handleCommands(getClient(), _command);
			}
			else if (_command.startsWith("Quest "))
			{
				if (!activeChar.validateBypass(_command))
					return;

				String[] str = _command.substring(6).trim().split(" ", 2);
				if (str.length == 1)
					activeChar.processQuestEvent(str[0], "");
				else
					activeChar.processQuestEvent(str[0], str[1]);
			}
			else if (_command.startsWith("repairchar "))
			{
				String value = _command.substring(11);
				StringTokenizer st = new StringTokenizer(value);
				String repairChar = null;

				try
				{
					if (st.hasMoreTokens())
						repairChar = st.nextToken();
				}
				catch (Exception e)
				{
					activeChar.sendMessage("You can't put empty box.");
					return;
				}

				if (repairChar == null || repairChar.equals(""))
					return;

				if (Repair.checkAcc(activeChar, repairChar))
				{
					if (Repair.checkChar(activeChar, repairChar))
					{
						String htmContent = HtmCache.getInstance().getHtm("data/html/mods/repair/repair-self.htm");
						NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(5);
						npcHtmlMessage.setHtml(htmContent);
						activeChar.sendPacket(npcHtmlMessage);
						return;
					}
					else if (Repair.checkPunish(activeChar, repairChar))
					{
						String htmContent = HtmCache.getInstance().getHtm("data/html/mods/repair/repair-jail.htm");
						NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(5);
						npcHtmlMessage.setHtml(htmContent);
						activeChar.sendPacket(npcHtmlMessage);
						return;
					}
					else if (Repair.checkKarma(activeChar, repairChar))
					{
						activeChar.sendMessage("Selected Char has Karma,Cannot be repaired!");
						return;
					}
					else
					{
						Repair.repairBadCharacter(repairChar);
						String htmContent = HtmCache.getInstance().getHtm("data/html/mods/repair/repair-done.htm");
						NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(5);
						npcHtmlMessage.setHtml(htmContent);
						activeChar.sendPacket(npcHtmlMessage);
						return;
					}
				}

				String htmContent = HtmCache.getInstance().getHtm("data/html/mods/repair/repair-error.htm");
				NpcHtmlMessage npcHtmlMessage = new NpcHtmlMessage(5);
				npcHtmlMessage.setHtml(htmContent);
				npcHtmlMessage.replace("%acc_chars%", Repair.getCharList(activeChar));
				activeChar.sendPacket(npcHtmlMessage);
			}

			else if (_command.startsWith("_match"))
			{
				String params = _command.substring(_command.indexOf("?") + 1);
				StringTokenizer st = new StringTokenizer(params, "&");
				int heroclass = Integer.parseInt(st.nextToken().split("=")[1]);
				int heropage = Integer.parseInt(st.nextToken().split("=")[1]);
				int heroid = Hero.getInstance().getHeroByClass(heroclass);
				if (heroid > 0)
					Hero.getInstance().showHeroFights(activeChar, heroclass, heroid, heropage);
			}
			else if (_command.startsWith("_diary"))
			{
				String params = _command.substring(_command.indexOf("?") + 1);
				StringTokenizer st = new StringTokenizer(params, "&");
				int heroclass = Integer.parseInt(st.nextToken().split("=")[1]);
				int heropage = Integer.parseInt(st.nextToken().split("=")[1]);
				int heroid = Hero.getInstance().getHeroByClass(heroclass);
				if (heroid > 0)
					Hero.getInstance().showHeroDiary(activeChar, heroclass, heroid, heropage);
			}
			else if (_command.startsWith("submitpin"))
			{
				try
				{
					String value = _command.substring(9);
					StringTokenizer s = new StringTokenizer(value, " ");
					int _pin = activeChar.getPin();

					try
					{
						if (activeChar.getPincheck())
						{
							_pin = Integer.parseInt(s.nextToken());
							if (Integer.toString(_pin).length() < 5)
							{
								activeChar.sendMessage("You should type more than 5 numbers. ");
								return;
							}

							try (Connection con = L2DatabaseFactory.getInstance().getConnection(); PreparedStatement statement = con.prepareStatement("UPDATE characters SET pin=? WHERE obj_id=?");)
							{
								statement.setInt(1, _pin);
								statement.setInt(2, activeChar.getObjectId());
								statement.execute();
								statement.close();
								activeChar.setPincheck(false);
								activeChar.updatePincheck();
								activeChar.sendMessage("You successfully secure your character. Your code is: " + _pin);
							}
							catch (Exception e)
							{
								e.printStackTrace();
								_log.warning("could not set char first login:" + e);
							}
						}
					}
					catch (Exception e)
					{
						activeChar.sendMessage("Your code must be more than 5 numbers.");
					}
				}
				catch (Exception e)
				{
					activeChar.sendMessage("Your code must be more than 5 numbers.");
				}

			}
			else if (_command.startsWith("removepin"))
			{
				try
				{
					String value = _command.substring(9);
					StringTokenizer s = new StringTokenizer(value, " ");
					int dapin = 0;
					int pin = 0;
					dapin = Integer.parseInt(s.nextToken());

					PreparedStatement statement = null;

					try (Connection con = L2DatabaseFactory.getInstance().getConnection())
					{
						statement = con.prepareStatement("SELECT pin FROM characters WHERE obj_Id=?");
						statement.setInt(1, activeChar.getObjectId());
						ResultSet rset = statement.executeQuery();

						while (rset.next())
						{
							pin = rset.getInt("pin");
						}

						if (pin == dapin)
						{
							activeChar.setPincheck(true);
							activeChar.setPin(0);
							activeChar.updatePincheck();
							activeChar.sendMessage("You successfully remove your pin.");
						}
						else
							activeChar.sendMessage("Code is wrong..");
					}
					catch (Exception e)
					{

					}
				}
				catch (Exception e)
				{
					// e.printStackTrace();
					activeChar.sendMessage("Your code must be more than 5 numbers.");
				}
			}
			else if (_command.startsWith("enterpin"))
			{
				try
				{
					String value = _command.substring(8);
					StringTokenizer s = new StringTokenizer(value, " ");
					int dapin = 0;
					int pin = 0;
					dapin = Integer.parseInt(s.nextToken());

					PreparedStatement statement = null;

					try (Connection con = L2DatabaseFactory.getInstance().getConnection())
					{
						statement = con.prepareStatement("SELECT pin FROM characters WHERE obj_Id=?");
						statement.setInt(1, activeChar.getObjectId());
						ResultSet rset = statement.executeQuery();

						while (rset.next())
						{
							pin = rset.getInt("pin");
						}

						if (pin == dapin)
						{
							activeChar.sendMessage("Code Authenticated!");
							activeChar.setIsImmobilized(false);
							activeChar.setIsSubmitingPin(false);
						}
						else
						{
							activeChar.sendMessage("Code is wrong.. You will now get disconnected!");
							ThreadPool.schedule(() -> activeChar.logout(false), 3000);
						}
					}
					catch (Exception e)
					{

					}
				}
				catch (Exception e)
				{
					// e.printStackTrace();
					activeChar.sendMessage("Your code must be more than 5 numbers.");
				}
				
			}
			else if (_command.startsWith("base"))
				MultiNpc.Classes(_command, activeChar);
			else if (_command.startsWith("arenachange")) // change
			{
				final boolean isManager = activeChar.getCurrentFolkNPC() instanceof OlympiadManagerNpc;
				if (!isManager)
				{
					// Without npc, command can be used only in observer mode on arena
					if (!activeChar.inObserverMode() || activeChar.isInOlympiadMode() || activeChar.getOlympiadGameId() < 0)
						return;
				}
				
				 
					

				if (OlympiadManager.getInstance().isRegisteredInComp(activeChar))
				{
					activeChar.sendPacket(SystemMessageId.WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME);
					return;
				}
				
				final int arenaId = Integer.parseInt(_command.substring(12).trim());
				activeChar.enterOlympiadObserverMode(arenaId);
			}
			else if (_command.startsWith("tournament_observe"))
			{
				
				StringTokenizer st = new StringTokenizer(_command);
				st.nextToken();
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				
				activeChar.setArenaObserv(true);
				activeChar.enterTvTObserverMode(x, y, z);
			}
			else if (_command.startsWith("report"))
			{
				BotsPreventionData.getInstance().AnalyseBypass(_command,activeChar);
			}
			else if (_command.startsWith("one"))
			{
				BotsPreventionData.getInstance().AnalyseOneBypass(_command,activeChar);
			}
			else if (_command.startsWith("final"))
			{
				BotsPreventionData.getInstance().AnalyseFinalBypass(_command,activeChar);
			}
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Bad RequestBypassToServer: " + e, e);
		}
	}

	private static void playerHelp(Player activeChar, String path)
	{
		if (path.indexOf("..") != -1)
			return;

		final StringTokenizer st = new StringTokenizer(path);
		final String[] cmd = st.nextToken().split("#");

		final NpcHtmlMessage html = new NpcHtmlMessage(0);
		html.setFile("data/html/help/" + cmd[0]);
		if (cmd.length > 1)
			html.setItemId(Integer.parseInt(cmd[1]));
		html.disableValidation();
		activeChar.sendPacket(html);
	}
}