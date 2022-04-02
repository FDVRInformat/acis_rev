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
package net.sf.l2j.gameserver.handler;

import java.util.HashMap;
import java.util.Map;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.AioMenu;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.BankingCommand;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.CastleManagers;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Donate;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Event;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.GrandBossStatus;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Info;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Inventory;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Menu;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Online;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Ranking;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Repair;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Shiff_Mod;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Skills;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Status;

/**
 * @author Baggos
 */
public class VoicedCommandHandler
{
	private final Map<Integer, IVoicedCommandHandler> _datatable = new HashMap<>();

	public static VoicedCommandHandler getInstance()
	{
		return SingletonHolder._instance;
	}

	protected VoicedCommandHandler()
	{
		registerHandler(new Donate());
		registerHandler(new Status());
		registerHandler(new Inventory());
		registerHandler(new Skills());
		registerHandler(new Ranking());
		registerHandler(new CastleManagers());
		registerHandler(new Info());
		registerHandler(new Event());
		registerHandler(new Online());
		registerHandler(new Menu());
		registerHandler(new GrandBossStatus());
		registerHandler(new Repair());
		registerHandler(new AioMenu());
		registerHandler(new Shiff_Mod());
		if (Config.ALLOW_BAKING_SYSTEM)
		{
		registerHandler(new BankingCommand());
		}
	}
	public void registerHandler(IVoicedCommandHandler handler)
	{
		String[] ids = handler.getVoicedCommandList();

		for (String id : ids)
			_datatable.put(id.hashCode(), handler);
	}

	public IVoicedCommandHandler getHandler(String voicedCommand)
	{
		String command = voicedCommand;

		if (voicedCommand.indexOf(" ") != -1)
			command = voicedCommand.substring(0, voicedCommand.indexOf(" "));

		return _datatable.get(command.hashCode());
	}

	public int size()
	{
		return _datatable.size();
	}

	private static class SingletonHolder
	{
		protected static final VoicedCommandHandler _instance = new VoicedCommandHandler();
	}
}