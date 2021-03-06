package net.sf.l2j.gameserver;

import com.rouxy.phantom.FakePlayerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.sf.l2j.commons.concurrent.ThreadPool;
import net.sf.l2j.commons.lang.StringUtil;
import net.sf.l2j.commons.mmocore.SelectorConfig;
import net.sf.l2j.commons.mmocore.SelectorThread;
import net.sf.l2j.commons.util.SysUtil;

import net.sf.l2j.Config;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.cache.CrestCache;
import net.sf.l2j.gameserver.cache.HtmCache;
import net.sf.l2j.gameserver.colorsystem.ColorSystem;
import net.sf.l2j.gameserver.communitybbs.Manager.ForumsBBSManager;
import net.sf.l2j.gameserver.data.BufferTable;
import net.sf.l2j.gameserver.data.CharTemplateTable;
import net.sf.l2j.gameserver.data.ItemTable;
import net.sf.l2j.gameserver.data.MapRegionTable;
import net.sf.l2j.gameserver.data.NpcTable;
import net.sf.l2j.gameserver.data.PlayerNameTable;
import net.sf.l2j.gameserver.data.RecipeTable;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.data.SkillTreeTable;
import net.sf.l2j.gameserver.data.SpawnTable;
import net.sf.l2j.gameserver.data.manager.BuyListManager;
import net.sf.l2j.gameserver.data.manager.PartyZoneReward;
import net.sf.l2j.gameserver.data.manager.RaidBossInfoManager;
import net.sf.l2j.gameserver.data.sql.BookmarkTable;
import net.sf.l2j.gameserver.data.sql.ClanTable;
import net.sf.l2j.gameserver.data.sql.ServerMemoTable;
import net.sf.l2j.gameserver.data.xml.AdminData;
import net.sf.l2j.gameserver.data.xml.AnnouncementData;
import net.sf.l2j.gameserver.data.xml.ArmorSetData;
import net.sf.l2j.gameserver.data.xml.AugmentationData;
import net.sf.l2j.gameserver.data.xml.FakePcsData;
import net.sf.l2j.gameserver.data.xml.FishData;
import net.sf.l2j.gameserver.data.xml.HennaData;
import net.sf.l2j.gameserver.data.xml.HerbDropData;
import net.sf.l2j.gameserver.data.xml.MultisellData;
import net.sf.l2j.gameserver.data.xml.NewbieBuffData;
import net.sf.l2j.gameserver.data.xml.SkipDropData;
import net.sf.l2j.gameserver.data.xml.SoulCrystalData;
import net.sf.l2j.gameserver.data.xml.SpellbookData;
import net.sf.l2j.gameserver.data.xml.StaticObjectData;
import net.sf.l2j.gameserver.data.xml.SummonItemData;
import net.sf.l2j.gameserver.data.xml.TeleportLocationData;
import net.sf.l2j.gameserver.data.xml.WalkerRouteData;
import net.sf.l2j.gameserver.datatables.DoorTable;
import net.sf.l2j.gameserver.datatables.IconTable;
import net.sf.l2j.gameserver.datatables.OfflineStoresData;
import net.sf.l2j.gameserver.events.ArenaEvent;
import net.sf.l2j.gameserver.events.ArenaTask;
import net.sf.l2j.gameserver.events.teamvsteam.TvTConfig;
import net.sf.l2j.gameserver.events.teamvsteam.TvTManager;
import net.sf.l2j.gameserver.events.tournaments.Arena2x2;
import net.sf.l2j.gameserver.events.tournaments.Arena4x4;
import net.sf.l2j.gameserver.events.tournaments.Arena9x9;
import net.sf.l2j.gameserver.geoengine.GeoEngine;
import net.sf.l2j.gameserver.handler.AdminCommandHandler;
import net.sf.l2j.gameserver.handler.ChatHandler;
import net.sf.l2j.gameserver.handler.ItemHandler;
import net.sf.l2j.gameserver.handler.SkillHandler;
import net.sf.l2j.gameserver.idfactory.IdFactory;
import net.sf.l2j.gameserver.instancemanager.AuctionManager;
import net.sf.l2j.gameserver.instancemanager.AutoSpawnManager;
import net.sf.l2j.gameserver.instancemanager.BoatManager;
import net.sf.l2j.gameserver.instancemanager.CastleManager;
import net.sf.l2j.gameserver.instancemanager.CastleManorManager;
import net.sf.l2j.gameserver.instancemanager.CharacterKillingManager;
import net.sf.l2j.gameserver.instancemanager.ClanHallManager;
import net.sf.l2j.gameserver.instancemanager.ClassDamageManager;
import net.sf.l2j.gameserver.instancemanager.CoupleManager;
import net.sf.l2j.gameserver.instancemanager.CursedWeaponsManager;
import net.sf.l2j.gameserver.instancemanager.DayNightSpawnManager;
import net.sf.l2j.gameserver.instancemanager.DimensionalRiftManager;
import net.sf.l2j.gameserver.instancemanager.FishingChampionshipManager;
import net.sf.l2j.gameserver.instancemanager.FourSepulchersManager;
import net.sf.l2j.gameserver.instancemanager.GrandBossManager;
import net.sf.l2j.gameserver.instancemanager.IPManager;
import net.sf.l2j.gameserver.instancemanager.MovieMakerManager;
import net.sf.l2j.gameserver.instancemanager.NewbiesSystemManager;
import net.sf.l2j.gameserver.instancemanager.OlympiadDamageManager;
import net.sf.l2j.gameserver.instancemanager.PetitionManager;
import net.sf.l2j.gameserver.instancemanager.RaidBossPointsManager;
import net.sf.l2j.gameserver.instancemanager.RaidBossSpawnManager;
import net.sf.l2j.gameserver.instancemanager.SevenSigns;
import net.sf.l2j.gameserver.instancemanager.SevenSignsFestival;
import net.sf.l2j.gameserver.instancemanager.ZoneManager;
import net.sf.l2j.gameserver.instancemanager.games.MonsterRace;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.entity.Hero;
import net.sf.l2j.gameserver.model.olympiad.Olympiad;
import net.sf.l2j.gameserver.model.olympiad.OlympiadGameManager;
import net.sf.l2j.gameserver.model.partymatching.PartyMatchRoomList;
import net.sf.l2j.gameserver.model.partymatching.PartyMatchWaitingList;
import net.sf.l2j.gameserver.model.vehicles.BoatGiranTalking;
import net.sf.l2j.gameserver.model.vehicles.BoatGludinRune;
import net.sf.l2j.gameserver.model.vehicles.BoatInnadrilTour;
import net.sf.l2j.gameserver.model.vehicles.BoatRunePrimeval;
import net.sf.l2j.gameserver.model.vehicles.BoatTalkingGludin;
import net.sf.l2j.gameserver.network.L2GameClient;
import net.sf.l2j.gameserver.network.L2GamePacketHandler;
import net.sf.l2j.gameserver.scripting.ScriptManager;
import net.sf.l2j.gameserver.taskmanager.AfkTaskManager;
import net.sf.l2j.gameserver.taskmanager.AttackStanceTaskManager;
import net.sf.l2j.gameserver.taskmanager.DecayTaskManager;
import net.sf.l2j.gameserver.taskmanager.GameTimeTaskManager;
import net.sf.l2j.gameserver.taskmanager.ItemsOnGroundTaskManager;
import net.sf.l2j.gameserver.taskmanager.MovementTaskManager;
import net.sf.l2j.gameserver.taskmanager.PvpFlagTaskManager;
import net.sf.l2j.gameserver.taskmanager.RandomAnimationTaskManager;
import net.sf.l2j.gameserver.taskmanager.ShadowItemTaskManager;
import net.sf.l2j.gameserver.taskmanager.WaterTaskManager;
import net.sf.l2j.gameserver.votesystem.AutoVoteMain;
import net.sf.l2j.gameserver.votesystem.VoteReminder;
import net.sf.l2j.gameserver.xmlfactory.XMLDocumentFactory;
import net.sf.l2j.util.DeadLockDetector;
import net.sf.l2j.util.IPv4Filter;

import Dev.Events.PartyFarm.InitialPartyFarm;
import Dev.Events.PartyFarm.PartyFarm;
import phantom.FakePlayerNameManager;
import phantom.PhantomPlayers;
import phantom.PhantomStore;

public class GameServer
{
	private static final Logger _log = Logger.getLogger(GameServer.class.getName());

	private final SelectorThread<L2GameClient> _selectorThread;

	private static GameServer _gameServer;

	public static void main(String[] args) throws Exception
	{
		_gameServer = new GameServer();
	}

	public GameServer() throws Exception
	{
		
		// Create log folder
		new File("./log").mkdir();
		new File("./log/chat").mkdir();
		new File("./log/console").mkdir();
		new File("./log/error").mkdir();
		new File("./log/gmaudit").mkdir();
		new File("./log/item").mkdir();
		new File("./data/crests").mkdirs();

		// Create input stream for log file -- or store file data into memory
		try (InputStream is = new FileInputStream(new File("config/logging.properties")))
		{
			LogManager.getLogManager().readConfiguration(is);
		}

		StringUtil.printSection("acis");

		// Initialize config
		Config.loadGameServer();
		
		// Factories
		L2DatabaseFactory.getInstance();
		StringUtil.printSection("ThreadPool");
		ThreadPool.init();
		StringUtil.printSection("IdFactory");
		IdFactory.getInstance();

		StringUtil.printSection("World");
		World.getInstance();
		MapRegionTable.getInstance();
		AnnouncementData.getInstance();
		ServerMemoTable.getInstance();

		StringUtil.printSection("Skills");
		SkillTable.getInstance();
		SkillTreeTable.getInstance();

		
		StringUtil.printSection("Items");
		ItemTable.getInstance();
		SummonItemData.getInstance();
		HennaData.getInstance();
		BuyListManager.getInstance();
		MultisellData.getInstance();
		RecipeTable.getInstance();
		ArmorSetData.getInstance();
		FishData.getInstance();
		SpellbookData.getInstance();
		SoulCrystalData.getInstance();
		AugmentationData.getInstance();
		CursedWeaponsManager.getInstance();
		XMLDocumentFactory.getInstance();
		IconTable.getInstance();
		PartyZoneReward.getInstance();
		class SpawnMonsters implements Runnable
		{
			public SpawnMonsters()
			{
			}
			
			@Override
			public void run()
			{
				PartyFarm._aborted = false;
				PartyFarm._started = true;
				
				PartyFarm.spawnMonsters();
			}	
			
		}
		
		StringUtil.printSection("Admins");
		AdminData.getInstance();
		BookmarkTable.getInstance();
		MovieMakerManager.getInstance();
		PetitionManager.getInstance();

		StringUtil.printSection("Characters");
		CharTemplateTable.getInstance();
		PlayerNameTable.getInstance();
		NewbieBuffData.getInstance();
		TeleportLocationData.getInstance();
		HtmCache.getInstance();
		PartyMatchWaitingList.getInstance();
		PartyMatchRoomList.getInstance();
		RaidBossPointsManager.getInstance();

		StringUtil.printSection("Community server");
		if (Config.ENABLE_COMMUNITY_BOARD) // Forums has to be loaded before clan data
			ForumsBBSManager.getInstance().initRoot();
		else
			_log.config("Community server is disabled.");

		StringUtil.printSection("Clans");
		CrestCache.getInstance();
		ClanTable.getInstance();
		AuctionManager.getInstance();
		ClanHallManager.getInstance();

		StringUtil.printSection("Geodata & Pathfinding");
		GeoEngine.getInstance();

		StringUtil.printSection("Zones");
		ZoneManager.getInstance();

		StringUtil.printSection("Task Managers");
		AttackStanceTaskManager.getInstance();
		AfkTaskManager.getInstance();
		DecayTaskManager.getInstance();
		GameTimeTaskManager.getInstance();
		ItemsOnGroundTaskManager.getInstance();
		MovementTaskManager.getInstance();
		PvpFlagTaskManager.getInstance();
		RandomAnimationTaskManager.getInstance();
		ShadowItemTaskManager.getInstance();
		WaterTaskManager.getInstance();
		RaidBossInfoManager.getInstance();

		StringUtil.printSection("Castles");
		CastleManager.getInstance();

		StringUtil.printSection("Seven Signs");
		SevenSigns.getInstance().spawnSevenSignsNPC();
		SevenSignsFestival.getInstance();

		StringUtil.printSection("Manor Manager");
		CastleManorManager.getInstance();
		FakePlayerManager.initialise();
		_log.config("PhantomPlayers Initialized.");
		StringUtil.printSection("NPCs");
		BufferTable.getInstance();
		HerbDropData.getInstance();
		NpcTable.getInstance();
		WalkerRouteData.getInstance();
		
		DoorTable.getInstance().spawn();
		StaticObjectData.getInstance();
		SpawnTable.getInstance();
		RaidBossSpawnManager.getInstance();
		GrandBossManager.getInstance();
		DayNightSpawnManager.getInstance().notifyChangeMode();
		DimensionalRiftManager.getInstance();

		StringUtil.printSection("Four Sepulchers");
		FourSepulchersManager.getInstance().init();

		StringUtil.printSection("Quests & Scripts");
		ScriptManager.getInstance();

		if (Config.ALLOW_BOAT)
		{
			BoatManager.getInstance();
			BoatGiranTalking.load();
			BoatGludinRune.load();
			BoatInnadrilTour.load();
			BoatRunePrimeval.load();
			BoatTalkingGludin.load();
		}

		StringUtil.printSection("Monster Derby Track");
		MonsterRace.getInstance();

		if (Config.ALLOW_WEDDING)
			CoupleManager.getInstance();

		if (Config.ALT_FISH_CHAMPIONSHIP_ENABLED)
			FishingChampionshipManager.getInstance();

		StringUtil.printSection("Handlers");
		_log.config("AutoSpawnHandler: Loaded " + AutoSpawnManager.getInstance().size() + " handlers.");
		_log.config("AdminCommandHandler: Loaded " + AdminCommandHandler.getInstance().size() + " handlers.");
		_log.config("ChatHandler: Loaded " + ChatHandler.getInstance().size() + " handlers.");
		_log.config("ItemHandler: Loaded " + ItemHandler.getInstance().size() + " handlers.");
		_log.config("SkillHandler: Loaded " + SkillHandler.getInstance().size() + " handlers.");
		

		if ((Config.OFFLINE_TRADE_ENABLE || Config.OFFLINE_CRAFT_ENABLE) && Config.RESTORE_OFFLINERS)
			OfflineStoresData.getInstance().restoreOfflineTraders();

		StringUtil.printSection("Olympiads & Heroes");
		OlympiadGameManager.getInstance();
		Olympiad.getInstance();
		Hero.getInstance();

		StringUtil.printSection("l2j Mods");
		if (Config.VOTE_SYSTEM_ENABLED)
			AutoVoteMain.getInstance();
		if (Config.ALLOW_VOTE_REMINDER)
		{
			VoteReminder.getInstance();
			System.out.println("Vote Reminder: Loaded");
		}
		ColorSystem.getInstance();
		System.out.println("Color System: Loaded");
		SkipDropData.getInstance();
		FakePcsData.getInstance();
		System.out.println("Fake Players NPCs: Loaded");
		IPManager.getInstance();
		if (Config.ENABLE_STARTUP)
		{
			NewbiesSystemManager.getInstance();
			_log.info("Newbie System Actived");
		}
		else
		{
			_log.info("Newbie System Desatived");
		}
		StringUtil.printSection("Party Farm Events");
		if ((Config.PARTY_FARM_BY_TIME_OF_DAY) && (!Config.START_PARTY))
		{
			InitialPartyFarm.getInstance().StartCalculationOfNextEventTime();
			_log.info("[Party Farm Time]: Enabled");
		}
		else if ((Config.START_PARTY) && (!Config.PARTY_FARM_BY_TIME_OF_DAY))
		{
			_log.info("[Start Spawn Party Farm]: Enabled");
			ThreadPool.schedule(new SpawnMonsters(), 1000L);
		}
		StringUtil.printSection("l2j Events");
		
		TvTConfig.init();
		TvTManager.getInstance();
		
		ThreadPool.schedule(Arena2x2.getInstance(), 5000L);
		ThreadPool.schedule(Arena9x9.getInstance(), 5000L);
		ThreadPool.schedule(Arena4x4.getInstance(), 5000L);
		if (Config.TOURNAMENT_EVENT_TIME)
		{
			_log.info("Tournament Event is enabled.");
			ArenaEvent.getInstance().StartCalculationOfNextEventTime();
		}
		else if (Config.TOURNAMENT_EVENT_START)
		{
			_log.info("Tournament Event is enabled.");
			ArenaTask.spawnNpc1();
		}
		else
		{
			_log.info("Tournament Event is disabled");
		}
		
		if (Config.CKM_ENABLED)
			CharacterKillingManager.getInstance().init();
		
		StringUtil.printSection("Phantom Players");
		FakePlayerNameManager.INSTANCE.initialise();
		if (Config.ALLOW_PHANTOM_STORE)
		{
			PhantomStore.init();
			_log.info("Phantom Store Enabled");
		}
		else
		{
			_log.info("Phantom Store Desatived");
		}
		if (Config.ALLOW_PHANTOM_PLAYERS)
		{
			PhantomPlayers.init();
			_log.info("Phantom Players Enabled");
		}
		else
		{
			_log.info("Phantom Players Desatived");
		}
		
		StringUtil.printSection("Classes Damages");
		if (Config.ENABLE_CLASS_DAMAGES)
			ClassDamageManager.loadConfig();
		
		if (Config.ENABLE_CLASS_DAMAGES_IN_OLY)
			OlympiadDamageManager.loadConfig();
		else
			_log.config("Game Server Info is disabled.");
		
		 if(Config.PCB_ENABLE)
		 {
		 System.out.println("############PCB_ENABLE################");
		 ThreadPool.scheduleAtFixedRate(PcBang.getInstance(), Config.PCB_INTERVAL * 1000, Config.PCB_INTERVAL * 1000);
		}

		StringUtil.printSection("System");
		Runtime.getRuntime().addShutdownHook(Shutdown.getInstance());
		ForumsBBSManager.getInstance();
		_log.config("IdFactory: Free ObjectIDs remaining: " + IdFactory.getInstance().size());

		if (Config.DEADLOCK_DETECTOR)
		{
			_log.info("Deadlock detector is enabled. Timer: " + Config.DEADLOCK_CHECK_INTERVAL + "s.");

			final DeadLockDetector deadDetectThread = new DeadLockDetector();
			deadDetectThread.setDaemon(true);
			deadDetectThread.start();
		}
		else
			_log.info("Deadlock detector is disabled.");

		System.gc();

		_log.info("Gameserver have started, used memory: " + SysUtil.getUsedMemory() + " / " + SysUtil.getMaxMemory() + " Mo.");
		_log.info("Maximum allowed players: " + Config.MAXIMUM_ONLINE_USERS);
		
		StringUtil.printSection("Login");
		LoginServerThread.getInstance().start();

		final SelectorConfig sc = new SelectorConfig();
		sc.MAX_READ_PER_PASS = Config.MMO_MAX_READ_PER_PASS;
		sc.MAX_SEND_PER_PASS = Config.MMO_MAX_SEND_PER_PASS;
		sc.SLEEP_TIME = Config.MMO_SELECTOR_SLEEP_TIME;
		sc.HELPER_BUFFER_COUNT = Config.MMO_HELPER_BUFFER_COUNT;

		final L2GamePacketHandler handler = new L2GamePacketHandler();
		_selectorThread = new SelectorThread<>(sc, handler, handler, handler, new IPv4Filter());

		InetAddress bindAddress = null;
		if (!Config.GAMESERVER_HOSTNAME.equals("*"))
		{
			try
			{
				bindAddress = InetAddress.getByName(Config.GAMESERVER_HOSTNAME);
			}
			catch (UnknownHostException e1)
			{
				_log.log(Level.SEVERE, "WARNING: The GameServer bind address is invalid, using all available IPs. Reason: " + e1.getMessage(), e1);
			}
		}

		try
		{
			_selectorThread.openServerSocket(bindAddress, Config.PORT_GAME);
		}
		catch (IOException e)
		{
			_log.log(Level.SEVERE, "FATAL: Failed to open server socket. Reason: " + e.getMessage(), e);
			System.exit(1);
		}
		_selectorThread.start();
	}

	public static GameServer getInstance()
	{
		return _gameServer;
	}

	public SelectorThread<L2GameClient> getSelectorThread()
	{
		return _selectorThread;
	}

}

