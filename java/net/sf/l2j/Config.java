package net.sf.l2j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import net.sf.l2j.commons.config.ExProperties;
import net.sf.l2j.commons.math.MathUtil;

import net.sf.l2j.gameserver.instancemanager.RewardHolder;
import net.sf.l2j.gameserver.model.holder.BuffSkillHolder;
import net.sf.l2j.gameserver.model.holder.IntIntHolder;

/**
 * This class contains global server configuration.<br>
 * It has static final fields initialized from configuration files.<br>
 * @author mkizub
 */
public final class Config
{
	private static final Logger _log = Logger.getLogger(Config.class.getName());
	
	public static final String CLANS_FILE = "./config/clans.properties";
	public static final String EVENTS_FILE = "./config/events.properties";
	public static final String GEOENGINE_FILE = "./config/geoengine.properties";
	public static final String HEXID_FILE = "./config/hexid.txt";
	public static final String LOGIN_CONFIGURATION_FILE = "./config/loginserver.properties";
	public static final String NPCS_FILE = "./config/npcs.properties";
	public static final String PLAYERS_FILE = "./config/players.properties";
	public static final String SERVER_FILE = "./config/server.properties";
	public static final String SIEGE_FILE = "./config/siege.properties";
	public static final String NEWBIE_FILE = "./config/customs/NewbiesSystem.properties";
	public static final String MANAGER_FILE = "./config/customs/NpcsManager.properties";
	public static final String CUSTOMS_FILE = "./config/customs/SpecialMods.properties";
	public static final String DONATOR_FILE = "./config/customs/Donate.properties";
	public static final String COMMAND_LIST = "./config/customs/Command.properties";
	public static final String RAID_FILE = "./config/customs/BossesSettings.properties";
	public static final String PHANTOM_FILE = "./config/customs/phantom/phantom.properties";
	public static final String TOUR_FILE = "./config/customs/Events/Tournament.properties";
	public static final String ENCHANT_FILE = "./config/customs/EnchantSystem.properties";
	public static final String BALANCE_FILE = "./config/customs/Balancing/Balance.properties";
	public static final String CKM_FILE = "./config/customs/Events/CharacterKillingMonuments.properties";
	public static final String FAKE_PLAYER_FILE = "./config/fakeplayer.properties";
	public static final String MULTISHOP_FILE = "./config/customs/MultiShopNpc.properties";
	public static final String PCBANGPOINT_FILE = "./config/customs/Events/PcBanG.properties";
	public static final String PARTYFARMEVENT = "./config/customs/Events/PartyFarmEvent.properties";
	
	//PCBANG POINT
	public static boolean PCB_ENABLE;
	public static int PCB_COIN_ID;
	public static int PCB_MIN_LEVEL;
	public static int PCB_POINT_MIN;
	public static int PCB_POINT_MAX;
	public static int PCB_CHANCE_DUAL_POINT;
	public static int PCB_INTERVAL;
	//============================================================
	
	/** Threads & Packets size */
	public static int THREAD_P_EFFECTS = 6; // default 6
	public static int THREAD_P_GENERAL = 15; // default 15
	public static int GENERAL_PACKET_THREAD_CORE_SIZE = 4; // default 4
	public static int IO_PACKET_THREAD_CORE_SIZE = 2; // default 2
	public static int GENERAL_THREAD_CORE_SIZE = 4; // default 4
	public static int AI_MAX_THREAD = 10; // default 10
	/** FAKE PLAYERS By Rouxy **/
	public static boolean FAKE_PLAYER_RETURNS_TO_FARM_ZONE;
	public static int[] SPAWN_FAKE_LOC2;
	public static int[] SPAWN_FAKE_LOC1;
	public static boolean FAKE_PLAYER_CAN_TARGET_REAL_PLAYER;
	public static boolean CONTROLL_FAKE_ON;
	public static String FAKE_PLAYER_TITLE;
	public static boolean AUTO_LEARN_DIVINE_INSPIRATION;
	public static boolean ENABLE_GUI;
	public static List<Integer> ALLOW_CUSTOM_CANCEL;
	public static int CUSTOM_CANCEL_SECONDS;
	public static String PROTECTED_SKILLS;
	
	
	/** nobles kill barakiel */
	public static int BOSS_ID;
	public static boolean ALLOW_AUTO_NOBLESS_FROM_BOSS;
	public static int RADIUS_TO_RAID;
	
	/** Disable attack Npcs */
	public static boolean DISABLE_ATTACK_NPC_TYPE;
	public static String ALLOWED_NPC_TYPES;
	public static ArrayList<String> LIST_ALLOWED_NPC_TYPES = new ArrayList<>();
	
	/** Character Killing Monument */
	public static boolean CKM_ENABLED;
	public static long CKM_CYCLE_LENGTH;
	public static String CKM_PVP_NPC_TITLE;
	public static int CKM_PVP_NPC_TITLE_COLOR;
	public static int CKM_PVP_NPC_NAME_COLOR;
	public static String CKM_PK_NPC_TITLE;
	public static int CKM_PK_NPC_TITLE_COLOR;
	public static int CKM_PK_NPC_NAME_COLOR;
	public static int[][] CKM_PLAYER_REWARDS;
	
	/** disable chat com letra grande */
	public static boolean DISABLE_CAPSLOCK;
	
	/** leave buff on die */
	public static boolean LEAVE_BUFFS_ON_DIE;
	
	/** balance */
	public static boolean ENABLE_CUSTOM_CRIT;
	public static int MAX_PCRIT_RATE;
	public static int PCRIT_RATE_ArcherHuman;
	public static int PCRIT_RATE_ArcherElfo;
	public static int PCRIT_RATE_ArcherDarkElfo;
	public static int MCRIT_RATE_Archmage;
	public static int MCRIT_RATE_Soultaker;
	public static int MCRIT_RATE_Mystic_Muse;
	public static int MCRIT_RATE_Storm_Screamer;
	public static int MCRIT_RATE_Dominator;
	public static int MAX_MCRIT_RATE;
	
	/** oly balance */
	public static boolean OLY_ENABLE_CUSTOM_CRIT;
	public static int OLY_MAX_PCRIT_RATE;
	public static int OLY_PCRIT_RATE_ArcherHuman;
	public static int OLY_PCRIT_RATE_ArcherElfo;
	public static int OLY_PCRIT_RATE_ArcherDarkElfo;
	public static int OLY_MCRIT_RATE_Archmage;
	public static int OLY_MCRIT_RATE_Soultaker;
	public static int OLY_MCRIT_RATE_Mystic_Muse;
	public static int OLY_MCRIT_RATE_Storm_Screamer;
	public static int OLY_MCRIT_RATE_Dominator;
	public static int OLY_MAX_MCRIT_RATE;
	public static int MAX_MATK_SPEED;
	public static int MAX_PATK_SPEED;
	public static int MAX_PATK_SPEED_GHOST;
	public static int MAX_PATK_SPEED_MOONL;
	public static int BLOW_ATTACK_FRONT;
	public static int BLOW_ATTACK_SIDE;
	public static int BLOW_ATTACK_BEHIND;
	public static int BACKSTAB_ATTACK_BEHIND;
	public static int BACKSTAB_ATTACK_FRONT;
	public static int BACKSTAB_ATTACK_SIDE;
	public static float MAGIC_CRITICAL_POWER;
	
	public static int ANTI_SS_BUG_1;
	public static int ANTI_SS_BUG_2;
	public static int ANTI_SS_BUG_3;
	public static int EVENT_BEST_FARM_TIME;
	public static String[] EVENT_BEST_FARM_INTERVAL_BY_TIME_OF_DAY;
	public static int PARTY_FARM_MONSTER_DALAY;
	public static String PARTY_FARM_MESSAGE_TEXT;
	public static int PARTY_FARM_MESSAGE_TIME;
	public static int monsterId;
	public static int MONSTER_LOCS_COUNT;
	public static int[][] MONSTER_LOCS;
	public static boolean PARTY_MESSAGE_ENABLED;
	public static boolean ENABLE_DUALBOX_PARTYFARM;
	public static boolean PARTY_FARM_BY_TIME_OF_DAY;
	public static boolean START_PARTY;
	public static String PART_ZONE_MONSTERS_EVENT;
	public static List<Integer> PART_ZONE_MONSTERS_EVENT_ID;
	public static List<RewardHolder> PARTY_ZONE_REWARDS = new ArrayList<>();
	/** Disable bow for classes */
	public static boolean ALT_DISABLE_BOW_CLASSES;
	public static String DISABLE_BOW_CLASSES_STRING;
	public static ArrayList<Integer> DISABLE_BOW_CLASSES = new ArrayList<>();
	
	/** light no use heavy */
	public static boolean ALLOW_LIGHT_USE_HEAVY;
	public static String NOTALLOWCLASS;
	public static List<Integer> NOTALLOWEDUSEHEAVY;
	public static boolean ALLOW_HEAVY_USE_LIGHT;
	public static String NOTALLOWCLASSE;
	public static List<Integer> NOTALLOWEDUSELIGHT;
	
	/** newbie system */
	public static int NEWBIE_DIST;
	public static int NEWBIE_LADO;
	public static int NEWBIE_ALTURA;
	public static boolean ENABLE_STARTUP;
	public static int[] TELE_TO_LOCATION = new int[3];
	public static int NEWBIE_ITEMS_ENCHANT;
	
	/** vip e donate system */
	public static boolean DONATESYSTEM;
	public static boolean SHOW_HTML;
	public static int DONATE_COIN_ID;
	public static int VIP_30_DAYS_PRICE;
	public static int VIP_60_DAYS_PRICE;
	public static int VIP_90_DAYS_PRICE;
	public static int VIP_ETERNAL_PRICE;
	public static int AIO_30_DAYS_PRICE;
	public static int AIO_60_DAYS_PRICE;
	public static int AIO_90_DAYS_PRICE;
	public static int AIO_ETERNAL_PRICE;
	public static int HERO_30_DAYS_PRICE;
	public static int HERO_60_DAYS_PRICE;
	public static int HERO_90_DAYS_PRICE;
	public static int HERO_ETERNAL_PRICE;
	public static int DONATE_CLASS_PRICE;
	public static int DONATE_NAME_PRICE;
	public static int DONATE_SEX_PRICE;
	
	// status / skills/ inventory CMD
	public static boolean STATUS_CMD;
	
	/** class damage */
	public static float ALT_FIGHTER_TO_SUMMON;
	public static float ALT_MAGE_TO_SUMMON;
	public static boolean ENABLE_CLASS_DAMAGES;
	public static boolean ENABLE_CLASS_DAMAGES_IN_OLY;
	public static boolean ENABLE_CLASS_DAMAGES_LOGGER;
	
	/** skill duration */
	public static boolean ENABLE_MODIFY_SKILL_DURATION;
	public static HashMap<Integer, Integer> SKILL_DURATION_LIST;
	
	/** Enchant item custom */
	public static HashMap<Integer, Integer> NORMAL_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> BLESS_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> CRYSTAL_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> DONATOR_WEAPON_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> NORMAL_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> BLESS_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> CRYSTAL_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> DONATOR_ARMOR_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> NORMAL_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> BLESS_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> CRYSTAL_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static HashMap<Integer, Integer> DONATOR_JEWELRY_ENCHANT_LEVEL = new HashMap<>();
	public static boolean ENCHANT_HERO_WEAPON;
	public static boolean SCROLL_STACKABLE;
	public static int GOLD_WEAPON;
	public static int GOLD_ARMOR;
	public static int ENCHANT_SAFE_MAX;
	public static int ENCHANT_SAFE_MAX_FULL;
	public static int ENCHANT_WEAPON_MAX;
	public static int ENCHANT_ARMOR_MAX;
	public static int ENCHANT_JEWELRY_MAX;
	public static int BLESSED_ENCHANT_WEAPON_MAX;
	public static int BLESSED_ENCHANT_ARMOR_MAX;
	public static int BLESSED_ENCHANT_JEWELRY_MAX;
	public static int BREAK_ENCHANT;
	public static int CRYSTAL_ENCHANT_MIN;
	public static int CRYSTAL_ENCHANT_WEAPON_MAX;
	public static int CRYSTAL_ENCHANT_ARMOR_MAX;
	public static int CRYSTAL_ENCHANT_JEWELRY_MAX;
	public static int DONATOR_ENCHANT_MIN;
	public static int DONATOR_ENCHANT_WEAPON_MAX;
	public static int DONATOR_ENCHANT_ARMOR_MAX;
	public static int DONATOR_ENCHANT_JEWELRY_MAX;
	public static boolean DONATOR_DECREASE_ENCHANT;
	public static boolean ENABLE_ENCHANT_ANNOUNCE;
	public static String ENCHANT_ANNOUNCE_LEVEL;
	public static ArrayList<Integer> LIST_ENCHANT_ANNOUNCE_LEVEL = new ArrayList<>();
	
	/** raid info */
	public static String GRAND_BOSS;
	public static ArrayList<Integer> GRAND_BOSS_LIST = new ArrayList<>();
	
	/** protetion pvpzone */
	public static boolean BS_PVPZONE;
	public static boolean WYVERN_PVPZONE;
	
	/** multibox */
	public static boolean MULTIBOX_PROTECTION_ENABLED;
	public static int MULTIBOX_PROTECTION_CLIENTS_PER_PC;
	public static int MULTIBOX_PROTECTION_PUNISH;
	public static int TIME_MULTIBOX;
	
	/** Arena Event */
	public static boolean TOURNAMENT_EVENT_START;
	public static boolean TOURNAMENT_EVENT_TIME;
	public static boolean TOURNAMENT_EVENT_SUMMON;
	public static boolean TOURNAMENT_EVENT_ANNOUNCE;
	public static int TOURNAMENT_TIME;
	public static String[] TOURNAMENT_EVENT_INTERVAL_BY_TIME_OF_DAY;
	public static String TITLE_COLOR_TEAM1;
	public static String TITLE_COLOR_TEAM2;
	public static String MSG_TEAM1;
	public static String MSG_TEAM2;
	public static boolean Allow_Same_HWID_On_Tournament;
	public static int ARENA_NPC;
	public static int NPC_locx;
	public static int NPC_locy;
	public static int NPC_locz;
	public static int NPC_Heading;
	public static int Tournament_locx;
	public static int Tournament_locy;
	public static int Tournament_locz;
	public static boolean ALLOW_2X2_REGISTER;
	public static boolean ALLOW_4X4_REGISTER;
	public static boolean ALLOW_9X9_REGISTER;
	public static boolean ALLOW_4X4_LOSTBUFF;
	public static boolean ARENA_MESSAGE_ENABLED;
	public static String ARENA_MESSAGE_TEXT;
	public static int ARENA_MESSAGE_TIME;
	public static int ARENA_EVENT_COUNT;
	public static int[][] ARENA_EVENT_LOCS;
	public static int ARENA_EVENT_COUNT_4X4;
	public static int[][] ARENA_EVENT_LOCS_4X4;
	public static int ARENA_EVENT_COUNT_9X9;
	public static int[][] ARENA_EVENT_LOCS_9X9;
	public static int duelist_COUNT_4X4;
	public static int dreadnought_COUNT_4X4;
	public static int tanker_COUNT_4X4;
	public static int dagger_COUNT_4X4;
	public static int archer_COUNT_4X4;
	public static int bs_COUNT_4X4;
	public static int archmage_COUNT_4X4;
	public static int soultaker_COUNT_4X4;
	public static int mysticMuse_COUNT_4X4;
	public static int stormScreamer_COUNT_4X4;
	public static int titan_COUNT_4X4;
	public static int dominator_COUNT_4X4;
	public static int doomcryer_COUNT_4X4;
	public static int duelist_COUNT_9X9;
	public static int dreadnought_COUNT_9X9;
	public static int tanker_COUNT_9X9;
	public static int dagger_COUNT_9X9;
	public static int archer_COUNT_9X9;
	public static int bs_COUNT_9X9;
	public static int archmage_COUNT_9X9;
	public static int soultaker_COUNT_9X9;
	public static int mysticMuse_COUNT_9X9;
	public static int stormScreamer_COUNT_9X9;
	public static int titan_COUNT_9X9;
	public static int grandKhauatari_COUNT_9X9;
	public static int dominator_COUNT_9X9;
	public static int doomcryer_COUNT_9X9;
	public static int ARENA_PVP_AMOUNT;
	public static int ARENA_REWARD_ID;
	public static int ARENA_WIN_REWARD_COUNT;
	public static int ARENA_LOST_REWARD_COUNT;
	public static int ARENA_WIN_REWARD_COUNT_4X4;
	public static int ARENA_LOST_REWARD_COUNT_4X4;
	public static int ARENA_WIN_REWARD_COUNT_9X9;
	public static int ARENA_LOST_REWARD_COUNT_9X9;
	public static int ARENA_CHECK_INTERVAL;
	public static int ARENA_CALL_INTERVAL;
	public static int ARENA_WAIT_INTERVAL_4X4;
	public static int ARENA_WAIT_INTERVAL_9X9;
	public static int ARENA_WAIT_INTERVAL;
	public static String TOURNAMENT_ID_RESTRICT;
	public static List<Integer> TOURNAMENT_LISTID_RESTRICT;
	public static boolean ARENA_SKILL_PROTECT;
	public static List<Integer> ARENA_SKILL_LIST = new ArrayList<>();
	public static List<Integer> ARENA_DISABLE_SKILL_LIST = new ArrayList<>();
	public static List<Integer> ARENA_STOP_SKILL_LIST = new ArrayList<>();
	public static List<Integer> ARENA_DISABLE_SKILL_LIST_PERM = new ArrayList<>();
	
	/** Advanced announce pvp end pk */
	public static boolean ANNOUNCE_PK_PVP;
	public static boolean ANNOUNCE_PK_PVP_NORMAL_MESSAGE;
	public static String ANNOUNCE_PK_MSG;
	public static String ANNOUNCE_PVP_MSG;
	
	/** phantom town */
	// private store fake
	public static boolean PHANTOM_PRIVATE_STORE;
	public static String PHANTOM_PRIVATE_BUY_TITLE_MSG;
	public static List<String> PHANTOM_PRIVATE_BUY_TITLE = new ArrayList<>();
	public static int PHANTOM_PRIVATE_BUY_CHANCE;
	public static String PHANTOM_PRIVATE_BUY;
	public static List<Integer> LIST_PRIVATE_BUY;
	public static String PHANTOM_PRIVATE_SELL_TITLE_MSG;
	public static List<String> PHANTOM_PRIVATE_SELL_TITLE = new ArrayList<>();
	public static int PHANTOM_PRIVATE_SELL_CHANCE;
	public static String PHANTOM_PRIVATE_SELL;
	public static List<Integer> LIST_PRIVATE_SELL;
	public static boolean PHANTOM_PLAYERS_SOULSHOT_ANIM;
	public static int PHANTOM_CHANCE_SIT;
	public static int PHANTOM_PLAYERS_WALK;
	public static boolean ALLOW_PHANTOM_PLAYERS = false;
	public static boolean ALLOW_PHANTOM_PVP = false;
	public static boolean ALLOW_PHANTOM_STORE = false;
	public static int PHANTOM_CHANCE_MALARIA;
	public static int PHANTOM_CHANCE_TITLE;
	public static String PHANTOM_PLAYERS_ACC_1;
	public static String PHANTOM_PLAYERS_ACC_2;
	public static int PHANTOM_DELAY_SPAWN_FIRST;
	public static long DISCONNETC_DELAY;
	public static boolean PHANTOM_PLAYERS_ARGUMENT_ANIM;
	public static int PHANTOM_PLAYERS_ENCHANT_MIN;
	public static int PHANTOM_PLAYERS_ENCHANT_MAX;
	public static String PHANTOM_PLAYERS_TITLE_MSG;
	public static List<String> PHANTOM_PLAYERS_TITLE = new ArrayList<>();
	public static boolean PHANTOM_TITLE_PHANTOM;
	public static boolean PHANTOM_TITLE_CONFIG;
	public static String PHANTOM_TITLE_MSG;
	public static List<String> PHANTOM_TITLE = new ArrayList<>();
	public static boolean ALLOW_PHANTOM_FACE;
	public static String PHANTOM_FACE;
	public static List<Integer> LIST_PHANTOM_FACE;
	public static boolean ALLOW_PHANTOM_HAIR;
	public static String PHANTOM_HAIR;
	public static List<Integer> LIST_PHANTOM_HAIR;
	
	/** PVP_LIMITS */
	public static boolean PVP_LIMITS_OLY_ENABLE;
	public static int PVP_LIMITS;
	
	/** CHAT LIMITS */
	public static boolean ALLOW_PVP_CHAT;
	public static int PVPS_TO_TALK_ON_SHOUT;
	public static int PVPS_TO_TALK_ON_TRADE;
	public static boolean EMOTION_CHAT_SYSTEM;
	public static Map<String, Integer> EMOTION_CHAT_LIST;
	public static boolean CHAT_RESTRICTION_WORDS;
	public static List<String> CHAT_RESTRICTION_WORDS_LIST;
	
	/** PVP_LIMITS */
	public static boolean KARMA_PLAYER_CAN_USE_BUFFER;
	
	/** PENALTY_LIMITS */
	public static boolean EXPERTISE_PENALTY;
	
	/** NEW CHAR TITLE */
	public static String NEW_CHAR_TITLE;
	
	/** SUB-CLASS LEVEL */
	public static int SUBCLASS_LEVEL;
	
	/** PvP Color System */
	public static String PVPS_COLORS;
	public static HashMap<Integer, Integer> PVPS_COLORS_LIST;
	public static String PKS_COLORS;
	public static HashMap<Integer, Integer> PKS_COLORS_LIST;
	
	/** Show HP PvP */
	public static boolean SHOW_HP_CP_PVP;
	
	/** Starting Level System */
	public static int START_LEVEL;
	
	/** Auto Vote */
	public static boolean VOTE_SYSTEM_ENABLED;
	public static int VOTES_FOR_REWARD_TOP;
	public static int VOTES_FOR_REWARD_NET;
	public static int VOTES_FOR_REWARD_HOP;
	public static int VOTES_SYSTEM_INITIAL_DELAY_TOP;
	public static int VOTES_SYSTEM_INITIAL_DELAY_HOP;
	public static int VOTES_SYSTEM_INITIAL_DELAY_NET;
	public static Map<Integer, Integer> VOTES_REWARDS_LIST_AUTOVOTE = new HashMap<>();
	public static int VOTE_BOXES_ALLOWED;
	public static String VOTES_SITE_TOPZONE_LINK_TOP;
	public static String VOTES_SITE_HOPZONE_LINK_HOP;
	public static String VOTES_SITE_L2NETWORK_LINK_NET;
	public static String SERVER_WEB_SITE;
	
	/** VoteManager Settings */
	public static boolean VOTE_MANAGER_ENABLED;
	public static String VOTES_SITE_TOPZONE_URL;
	public static String VOTES_SITE_HOPZONE_URL;
	public static String VOTES_SITE_L2NETWORK_URL;
	public static int VOTE_REWARD_ID;
	public static int VOTE_REWARD_ID_COUNT;
	public static int TIME_TO_VOTE;
	public static boolean ALLOW_VOTE_REMINDER;
	public static int VOTE_REMINDER_MINUTES;
	
	/** Starting Spawn System */
	public static boolean CUSTOM_SPAWN_CHAR;
	public static int RANDOM_SPAWN_CHAR;
	public static int[] CUSTOM_SPAWN1;
	public static int[] CUSTOM_SPAWN2;
	public static int[] CUSTOM_SPAWN3;
	
	/** SubClass Everywhere */
	public static boolean ALT_GAME_SUBCLASS_EVERYWHERE;
	
	public static boolean PVP_REWARD_SYSTEM;
	public static Map<Integer, Integer> PVP_REWARD = new HashMap<>();
	
	public static boolean PVP_COUNT_SYSTEM;
	
	public static Map<Integer, String> QUAKE_VALUES = new HashMap<>();
	
	public static boolean STARTING_ITEMS_SYSTEM;
	public static List<int[]> STARTING_ITEMS = new ArrayList<>();
	
	/** Clan Manager */
	public static int CLAN_ITEM_ID;
	public static int CLAN_COUNT;
	
	/** Bot Protection */
	public static boolean BOTS_PREVENTION;
	public static int KILLS_COUNTER;
	public static int KILLS_COUNTER_RANDOMIZATION;
	public static int VALIDATION_TIME;
	public static int PUNISHMENT;
	public static int PUNISHMENT_TIME;
	
	/** Hero Item */
	public static int HERO_DAYS;
	
	/** Infinity SS and Arrows */
	public static boolean INFINITY_SS;
	public static boolean INFINITY_ARROWS;
	
	/** Commands List */
	public static boolean ENABLE_ONLINE;
	public static int PLAYERS_ONLINE_TRICK;
	public static boolean EPIC_INFO;
	public static boolean MENU_PANEL;
	public static boolean SECURE_CMD;
	public static boolean TVT_CMD;
	
	/** Skipped Drop */
	public static boolean ENABLE_SKIPPING;
	
	/** Offline Shop */
	public static boolean OFFLINE_TRADE_ENABLE;
	public static boolean OFFLINE_CRAFT_ENABLE;
	public static boolean OFFLINE_MODE_IN_PEACE_ZONE;
	public static boolean OFFLINE_MODE_NO_DAMAGE;
	public static boolean RESTORE_OFFLINERS;
	public static int OFFLINE_MAX_DAYS;
	public static boolean OFFLINE_DISCONNECT_FINISHED;
	public static boolean OFFLINE_SET_NAME_COLOR;
	public static int OFFLINE_NAME_COLOR;
	public static boolean OFFLINE_SET_SLEEP;
	
	/** VIP/AIO Settings */
	public static Map<Integer, Integer> AIO_SKILLS;
	public static int AIO_ITEM_ID;
	public static int VIP_RATE_DROP_ITEMS_BY_RAID;
	public static int RATE_DROP_VIP;
	
	/** Raidbosses settings */
	public static boolean FLAG_RB;
	public static boolean ALLOW_DIRECT_TP_TO_BOSS_ROOM;
	public static boolean PLAYERS_CAN_HEAL_RB;
	/** Raid info*/
	public static int RAID_BOSS_INFO_PAGE_LIMIT;
	public static int RAID_BOSS_DROP_PAGE_LIMIT;
	public static String RAID_BOSS_DATE_FORMAT;
	public static String RAID_BOSS_IDS;
	public static List<Integer> LIST_RAID_BOSS_IDS;
	/** Custom Olympiad Settings */
	public static boolean OLYMPIAD_END_ANNOUNE;
	
	/** onEnter Messages/Effect */
	public static boolean WELCOME_EFFECT;
	public static boolean ANNOUNCE_CASTLE_LORDS;
	public static boolean ANNOUNCE_HEROES;
	
	/** PVP - PK TITLE COUNT */
	public static boolean PVP_PK_TITLE;
	public static String PVP_TITLE_PREFIX;
	public static String PK_TITLE_PREFIX;
	
	/** Baking System */
	public static int BANKING_SYSTEM_GOLDBARS;
	public static int BANKING_SYSTEM_ADENA;
	
	/** Disarm Weapons onEnter */
	public static boolean DISARM_WEP;
	
	/** Unstuck Time */
	public static int UNSTUCK_TIME;
	
	// --------------------------------------------------
	// Clans settings
	// --------------------------------------------------
	
	/** Clans */
	public static int ALT_CLAN_JOIN_DAYS;
	public static int ALT_CLAN_CREATE_DAYS;
	public static int ALT_CLAN_DISSOLVE_DAYS;
	public static int ALT_ALLY_JOIN_DAYS_WHEN_LEAVED;
	public static int ALT_ALLY_JOIN_DAYS_WHEN_DISMISSED;
	public static int ALT_ACCEPT_CLAN_DAYS_WHEN_DISMISSED;
	public static int ALT_CREATE_ALLY_DAYS_WHEN_DISSOLVED;
	public static int ALT_MAX_NUM_OF_CLANS_IN_ALLY;
	public static int ALT_CLAN_MEMBERS_FOR_WAR;
	public static int ALT_CLAN_WAR_PENALTY_WHEN_ENDED;
	public static boolean ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH;
	public static boolean REMOVE_CASTLE_CIRCLETS;
	
	/** Manor */
	public static int ALT_MANOR_REFRESH_TIME;
	public static int ALT_MANOR_REFRESH_MIN;
	public static int ALT_MANOR_APPROVE_TIME;
	public static int ALT_MANOR_APPROVE_MIN;
	public static int ALT_MANOR_MAINTENANCE_MIN;
	public static int ALT_MANOR_SAVE_PERIOD_RATE;
	
	/** Clan Hall function */
	public static long CH_TELE_FEE_RATIO;
	public static int CH_TELE1_FEE;
	public static int CH_TELE2_FEE;
	public static long CH_ITEM_FEE_RATIO;
	public static int CH_ITEM1_FEE;
	public static int CH_ITEM2_FEE;
	public static int CH_ITEM3_FEE;
	public static long CH_MPREG_FEE_RATIO;
	public static int CH_MPREG1_FEE;
	public static int CH_MPREG2_FEE;
	public static int CH_MPREG3_FEE;
	public static int CH_MPREG4_FEE;
	public static int CH_MPREG5_FEE;
	public static long CH_HPREG_FEE_RATIO;
	public static int CH_HPREG1_FEE;
	public static int CH_HPREG2_FEE;
	public static int CH_HPREG3_FEE;
	public static int CH_HPREG4_FEE;
	public static int CH_HPREG5_FEE;
	public static int CH_HPREG6_FEE;
	public static int CH_HPREG7_FEE;
	public static int CH_HPREG8_FEE;
	public static int CH_HPREG9_FEE;
	public static int CH_HPREG10_FEE;
	public static int CH_HPREG11_FEE;
	public static int CH_HPREG12_FEE;
	public static int CH_HPREG13_FEE;
	public static long CH_EXPREG_FEE_RATIO;
	public static int CH_EXPREG1_FEE;
	public static int CH_EXPREG2_FEE;
	public static int CH_EXPREG3_FEE;
	public static int CH_EXPREG4_FEE;
	public static int CH_EXPREG5_FEE;
	public static int CH_EXPREG6_FEE;
	public static int CH_EXPREG7_FEE;
	public static long CH_SUPPORT_FEE_RATIO;
	public static int CH_SUPPORT1_FEE;
	public static int CH_SUPPORT2_FEE;
	public static int CH_SUPPORT3_FEE;
	public static int CH_SUPPORT4_FEE;
	public static int CH_SUPPORT5_FEE;
	public static int CH_SUPPORT6_FEE;
	public static int CH_SUPPORT7_FEE;
	public static int CH_SUPPORT8_FEE;
	public static long CH_CURTAIN_FEE_RATIO;
	public static int CH_CURTAIN1_FEE;
	public static int CH_CURTAIN2_FEE;
	public static long CH_FRONT_FEE_RATIO;
	public static int CH_FRONT1_FEE;
	public static int CH_FRONT2_FEE;
	public static int CORE_RESP_MINION;
	// --------------------------------------------------
	// Events settings
	// --------------------------------------------------
	
	/** Olympiad */
	public static int ALT_OLY_START_TIME;
	public static int ALT_OLY_MIN;
	public static long ALT_OLY_CPERIOD;
	public static long ALT_OLY_BATTLE;
	public static long ALT_OLY_WPERIOD;
	public static long ALT_OLY_VPERIOD;
	public static int ALT_OLY_WAIT_TIME;
	public static int ALT_OLY_WAIT_BATTLE;
	public static int ALT_OLY_WAIT_END;
	public static int ALT_OLY_START_POINTS;
	public static int ALT_OLY_WEEKLY_POINTS;
	public static int ALT_OLY_MIN_MATCHES;
	public static int ALT_OLY_CLASSED;
	public static int ALT_OLY_NONCLASSED;
	public static int[][] ALT_OLY_CLASSED_REWARD;
	public static int[][] ALT_OLY_NONCLASSED_REWARD;
	public static int ALT_OLY_GP_PER_POINT;
	public static int ALT_OLY_HERO_POINTS;
	public static int ALT_OLY_RANK1_POINTS;
	public static int ALT_OLY_RANK2_POINTS;
	public static int ALT_OLY_RANK3_POINTS;
	public static int ALT_OLY_RANK4_POINTS;
	public static int ALT_OLY_RANK5_POINTS;
	public static int ALT_OLY_MAX_POINTS;
	public static int ALT_OLY_DIVIDER_CLASSED;
	public static int ALT_OLY_DIVIDER_NON_CLASSED;
	public static int ALT_OLY_RESTORE_IN_SECOND_COUNTDOWN;
	public static boolean ALT_OLY_ANNOUNCE_GAMES;
	public static boolean ALT_USE_CUSTOM_PERIOD;
	public static int[] ALT_CYSTOM_PERIOD;
	public static boolean ALT_OLY_RECHARGE_SKILLS;
	public static boolean ALT_OLY_SHOW_MONTHLY_WINNERS;
	public static boolean ALT_OLY_ALLOW_APPLY_AUGMENT_ON_CHAR;
	public static List<Integer> ALT_OLY_SKILLS_ALLOW_APPLY_AUGMENT;
	public static int ZAKEN_ENABLE_DOOR;
	public static List<Integer> LIST_OLY_RESTRICTED_ITEMS = new ArrayList<>();
	public static boolean ALT_OLY_GRADE_RESTRICTION_ITEMS;
	public static List<Integer> ALT_OLY_LIST_OF_GRADE_RESTRICTION_ITEMS;
	public static boolean ALT_OLY_ALLOW_DUALBOX_OLY;
	
	/** SevenSigns Festival */
	public static boolean ALT_GAME_CASTLE_DAWN;
	public static boolean ALT_GAME_CASTLE_DUSK;
	public static int ALT_FESTIVAL_MIN_PLAYER;
	public static int ALT_MAXIMUM_PLAYER_CONTRIB;
	public static long ALT_FESTIVAL_MANAGER_START;
	public static long ALT_FESTIVAL_LENGTH;
	public static long ALT_FESTIVAL_CYCLE_LENGTH;
	public static long ALT_FESTIVAL_FIRST_SPAWN;
	public static long ALT_FESTIVAL_FIRST_SWARM;
	public static long ALT_FESTIVAL_SECOND_SPAWN;
	public static long ALT_FESTIVAL_SECOND_SWARM;
	public static long ALT_FESTIVAL_CHEST_SPAWN;
	
	/** Four Sepulchers */
	public static int FS_TIME_ATTACK;
	public static int FS_TIME_ENTRY;
	public static int FS_TIME_WARMUP;
	public static int FS_PARTY_MEMBER_COUNT;
	
	/** dimensional rift */
	public static int RIFT_MIN_PARTY_SIZE;
	public static int RIFT_SPAWN_DELAY;
	public static int RIFT_MAX_JUMPS;
	public static int RIFT_AUTO_JUMPS_TIME_MIN;
	public static int RIFT_AUTO_JUMPS_TIME_MAX;
	public static int RIFT_ENTER_COST_RECRUIT;
	public static int RIFT_ENTER_COST_SOLDIER;
	public static int RIFT_ENTER_COST_OFFICER;
	public static int RIFT_ENTER_COST_CAPTAIN;
	public static int RIFT_ENTER_COST_COMMANDER;
	public static int RIFT_ENTER_COST_HERO;
	public static double RIFT_BOSS_ROOM_TIME_MUTIPLY;
	
	/** Wedding system */
	public static boolean ALLOW_WEDDING;
	public static int WEDDING_PRICE;
	public static boolean WEDDING_SAMESEX;
	public static boolean WEDDING_FORMALWEAR;
	
	/** Lottery */
	public static int ALT_LOTTERY_PRIZE;
	public static int ALT_LOTTERY_TICKET_PRICE;
	public static double ALT_LOTTERY_5_NUMBER_RATE;
	public static double ALT_LOTTERY_4_NUMBER_RATE;
	public static double ALT_LOTTERY_3_NUMBER_RATE;
	public static int ALT_LOTTERY_2_AND_1_NUMBER_PRIZE;
	
	/** Fishing tournament */
	public static boolean ALT_FISH_CHAMPIONSHIP_ENABLED;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_ITEM;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_1;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_2;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_3;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_4;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_5;
	
	// --------------------------------------------------
	// GeoEngine
	// --------------------------------------------------
	
	/** Geodata */
	public static String GEODATA_PATH;
	public static int COORD_SYNCHRONIZE;
	
	/** Path checking */
	public static int PART_OF_CHARACTER_HEIGHT;
	public static int MAX_OBSTACLE_HEIGHT;
	
	/** Path finding */
	public static boolean PATHFINDING;
	public static String PATHFIND_BUFFERS;
	public static int BASE_WEIGHT;
	public static int DIAGONAL_WEIGHT;
	public static int HEURISTIC_WEIGHT;
	public static int OBSTACLE_MULTIPLIER;
	public static int MAX_ITERATIONS;
	public static boolean DEBUG_PATH;
	public static boolean DEBUG_GEO_NODE;
	public static boolean ALLOW_VIP_NCOLOR;
	public static int VIP_NCOLOR;
	public static boolean ALLOW_VIP_TCOLOR;
	public static int VIP_TCOLOR;
	public static boolean ALLOW_AIO_NCOLOR;
	public static int AIO_NCOLOR;
	public static boolean ALLOW_AIO_TCOLOR;
	public static int AIO_TCOLOR;
	public static float VIP_XP_SP_RATE;
	public static float VIP_ADENA_RATE;
	public static float VIP_DROP_RATE;
	public static float VIP_SPOIL_RATE;
	// --------------------------------------------------
	// HexID
	// --------------------------------------------------
	
	public static int SERVER_ID;
	public static byte[] HEX_ID;
	
	// --------------------------------------------------
	// Loginserver
	// --------------------------------------------------
	
	public static String LOGIN_BIND_ADDRESS;
	public static int PORT_LOGIN;
	
	public static int LOGIN_TRY_BEFORE_BAN;
	public static int LOGIN_BLOCK_AFTER_BAN;
	public static boolean ACCEPT_NEW_GAMESERVER;
	
	public static boolean SHOW_LICENCE;
	
	public static boolean AUTO_CREATE_ACCOUNTS;
	
	public static boolean LOG_LOGIN_CONTROLLER;
	
	public static boolean FLOOD_PROTECTION;
	public static int FAST_CONNECTION_LIMIT;
	public static int NORMAL_CONNECTION_TIME;
	public static int FAST_CONNECTION_TIME;
	public static int MAX_CONNECTION_PER_IP;
	
	// --------------------------------------------------
	// NPCs / Monsters
	// --------------------------------------------------
	
	/** Champion Mod */
	public static int CHAMPION_FREQUENCY;
	public static int CHAMP_MIN_LVL;
	public static int CHAMP_MAX_LVL;
	public static int CHAMPION_HP;
	public static int CHAMPION_REWARDS;
	public static int CHAMPION_ADENAS_REWARDS;
	public static double CHAMPION_HP_REGEN;
	public static double CHAMPION_ATK;
	public static double CHAMPION_SPD_ATK;
	public static int CHAMPION_REWARD;
	public static int CHAMPION_REWARD_ID;
	public static int CHAMPION_REWARD_QTY;
	
	/** Buffer */
	public static String FIGHTER_SET;
	public static int[] FIGHTER_SET_LIST;
	public static String MAGE_SET;
	public static int[] MAGE_SET_LIST;
	public static int BUFFER_MAX_SCHEMES;
	public static int BUFFER_MAX_SKILLS;
	public static int BUFFER_STATIC_BUFF_COST;
	public static String BUFFER_BUFFS;
	public static Map<Integer, BuffSkillHolder> BUFFER_BUFFLIST;
	
	public static List<Integer> FIGHTER_SKILL_LIST;
	public static List<Integer> MAGE_SKILL_LIST;
	
	public static boolean RESTRICT_USE_BUFFER_ON_PVPFLAG;
	public static boolean RESTRICT_USE_BUFFER_IN_COMBAT;
	
	public static int VOTE_BUFF_ITEM_ID;
	public static int VOTE_BUFF_ITEM_COUNT;
	
	/** Misc */
	public static boolean ALLOW_CLASS_MASTERS;
	public static ClassMasterSettings CLASS_MASTER_SETTINGS;
	public static boolean ALLOW_ENTIRE_TREE;
	public static boolean ANNOUNCE_MAMMON_SPAWN;
	public static boolean ALT_MOB_AGRO_IN_PEACEZONE;
	public static boolean SHOW_NPC_LVL;
	public static boolean SHOW_NPC_CREST;
	public static boolean SHOW_SUMMON_CREST;
	
	/** Wyvern Manager */
	public static boolean WYVERN_ALLOW_UPGRADER;
	public static int WYVERN_REQUIRED_LEVEL;
	public static int WYVERN_REQUIRED_CRYSTALS;
	
	/** Raid Boss */
	public static double RAID_HP_REGEN_MULTIPLIER;
	public static double RAID_MP_REGEN_MULTIPLIER;
	public static double RAID_DEFENCE_MULTIPLIER;
	public static double RAID_MINION_RESPAWN_TIMER;
	
	public static boolean RAID_DISABLE_CURSE;
	public static int RAID_CHAOS_TIME;
	public static int GRAND_CHAOS_TIME;
	public static int MINION_CHAOS_TIME;
	
	/** Grand Boss */
	public static int SPAWN_INTERVAL_AQ;
	public static int RANDOM_SPAWN_TIME_AQ;
	public static String PARTY_FARMANNONCER;
	public static String PARTY_FARM_TITLE;
	public static boolean ENABLE_DROP_PARTYFARM;
	public static Map<Integer, List<Integer>> PARTY_DROP_LIST = new HashMap<>();
	public static int PARTY_FARM_REWARD_ID;
	public static int PARTY_FARM_REWARD_COUNT;
	public static int SPAWN_INTERVAL_ANTHARAS;
	public static int RANDOM_SPAWN_TIME_ANTHARAS;
	public static int WAIT_TIME_ANTHARAS;
	
	public static int SPAWN_INTERVAL_BAIUM;
	public static int RANDOM_SPAWN_TIME_BAIUM;
	
	public static int SPAWN_INTERVAL_CORE;
	public static int RANDOM_SPAWN_TIME_CORE;
	
	public static int SPAWN_INTERVAL_FRINTEZZA;
	public static int RANDOM_SPAWN_TIME_FRINTEZZA;
	public static int WAIT_TIME_FRINTEZZA;
	public static boolean BYPASS_FRINTEZZA_PARTIES_CHECK;
	public static int FRINTEZZA_MIN_PARTIES;
	public static int FRINTEZZA_MAX_PARTIES;
	public static int CLASS_ITEM_COUNT;
	public static int SPAWN_INTERVAL_ORFEN;
	public static int RANDOM_SPAWN_TIME_ORFEN;
	
	public static int SPAWN_INTERVAL_SAILREN;
	public static int RANDOM_SPAWN_TIME_SAILREN;
	public static int WAIT_TIME_SAILREN;
	
	public static int SPAWN_INTERVAL_VALAKAS;
	public static int RANDOM_SPAWN_TIME_VALAKAS;
	public static int WAIT_TIME_VALAKAS;
	public static boolean ANNOUNCE_BOSS_ALIVE;
	public static boolean ANNOUNCE_RAIDBOS_KILL;
	public static boolean ANNOUNCE_GRANDBOS_KILL;
	public static boolean ENABLE_RAIDBOSS_NOBLES;
	public static int BOSS_CUSTOM_NOBLES_ID;
	public static int DISTANCE_FOR_RENEWAL_NOBLES_PARTY;
	public static int SPAWN_INTERVAL_ZAKEN;
	public static int RANDOM_SPAWN_TIME_ZAKEN;
	public static int DONATE_ITEM;
	public static int NOBL_ITEM_COUNT;
	public static int SEX_ITEM_COUNT;
	public static int PK_ITEM_COUNT;
	public static int PK_CLEAN;
	public static int CLAN_ITEM_COUNT;
	public static int CLAN_REP_ITEM_COUNT;
	public static int CLAN_REPS;
	public static int AUGM_ITEM_COUNT;
	public static int CLAN_SKILL_ITEM_COUNT;
	public static int REC_ITEM_COUNT;
	public static int PASSWORD_ITEM_COUNT;
	public static int COLOR_ITEM_COUNT;
	public static int NAME_ITEM_COUNT;
	public static int ENCHANT_ITEM_COUNT;
	public static int ENCHANT_MAX_VALUE;
	/** AI */
	public static boolean GUARD_ATTACK_AGGRO_MOB;
	public static int MAX_DRIFT_RANGE;
	public static int MIN_NPC_ANIMATION;
	public static int MAX_NPC_ANIMATION;
	public static int MIN_MONSTER_ANIMATION;
	public static int MAX_MONSTER_ANIMATION;
	
	public static boolean ALT_GAME_VIEWNPC;
	
	// --------------------------------------------------
	// Players
	// --------------------------------------------------
	
	/** Misc */
	public static boolean EFFECT_CANCELING;
	public static double HP_REGEN_MULTIPLIER;
	public static double MP_REGEN_MULTIPLIER;
	public static double CP_REGEN_MULTIPLIER;
	public static int PLAYER_SPAWN_PROTECTION;
	public static int PLAYER_FAKEDEATH_UP_PROTECTION;
	public static double RESPAWN_RESTORE_HP;
	public static int MAX_PVTSTORE_SLOTS_DWARF;
	public static int MAX_PVTSTORE_SLOTS_OTHER;
	public static boolean DEEPBLUE_DROP_RULES;
	public static boolean ALT_GAME_DELEVEL;
	public static int DEATH_PENALTY_CHANCE;
	
	/** Inventory & WH */
	public static int INVENTORY_MAXIMUM_NO_DWARF;
	public static int INVENTORY_MAXIMUM_DWARF;
	public static int INVENTORY_MAXIMUM_QUEST_ITEMS;
	public static int INVENTORY_MAXIMUM_PET;
	public static int MAX_ITEM_IN_PACKET;
	public static double ALT_WEIGHT_LIMIT;
	public static int WAREHOUSE_SLOTS_NO_DWARF;
	public static int WAREHOUSE_SLOTS_DWARF;
	public static int WAREHOUSE_SLOTS_CLAN;
	public static int FREIGHT_SLOTS;
	public static boolean ALT_GAME_FREIGHTS;
	public static int ALT_GAME_FREIGHT_PRICE;
	
	/** Augmentations */
	public static int AUGMENTATION_NG_SKILL_CHANCE;
	public static int AUGMENTATION_NG_GLOW_CHANCE;
	public static int AUGMENTATION_MID_SKILL_CHANCE;
	public static int AUGMENTATION_MID_GLOW_CHANCE;
	public static int AUGMENTATION_HIGH_SKILL_CHANCE;
	public static int AUGMENTATION_HIGH_GLOW_CHANCE;
	public static int AUGMENTATION_TOP_SKILL_CHANCE;
	public static int AUGMENTATION_TOP_GLOW_CHANCE;
	public static int AUGMENTATION_BASESTAT_CHANCE;
	
	/** Karma & PvP */
	public static boolean KARMA_PLAYER_CAN_BE_KILLED_IN_PZ;
	public static boolean KARMA_PLAYER_CAN_SHOP;
	public static boolean KARMA_PLAYER_CAN_USE_GK;
	public static boolean KARMA_PLAYER_CAN_TELEPORT;
	public static boolean KARMA_PLAYER_CAN_TRADE;
	public static boolean KARMA_PLAYER_CAN_USE_WH;
	
	public static boolean KARMA_DROP_GM;
	public static boolean KARMA_AWARD_PK_KILL;
	public static int KARMA_PK_LIMIT;
	
	public static String KARMA_NONDROPPABLE_PET_ITEMS;
	public static String KARMA_NONDROPPABLE_ITEMS;
	public static int[] KARMA_LIST_NONDROPPABLE_PET_ITEMS;
	public static int[] KARMA_LIST_NONDROPPABLE_ITEMS;
	
	public static int PVP_NORMAL_TIME;
	public static int PVP_PVP_TIME;
	
	/** Party */
	public static String PARTY_XP_CUTOFF_METHOD;
	public static int PARTY_XP_CUTOFF_LEVEL;
	public static double PARTY_XP_CUTOFF_PERCENT;
	public static int PARTY_RANGE;
	
	/** GMs & Admin Stuff */
	public static int DEFAULT_ACCESS_LEVEL;
	public static boolean GM_HERO_AURA;
	public static boolean GM_STARTUP_INVULNERABLE;
	public static boolean GM_STARTUP_INVISIBLE;
	public static boolean GM_STARTUP_SILENCE;
	public static boolean GM_STARTUP_AUTO_LIST;
	
	/** petitions */
	public static boolean PETITIONING_ALLOWED;
	public static int MAX_PETITIONS_PER_PLAYER;
	public static int MAX_PETITIONS_PENDING;
	
	/** Crafting **/
	public static boolean IS_CRAFTING_ENABLED;
	public static int DWARF_RECIPE_LIMIT;
	public static int COMMON_RECIPE_LIMIT;
	public static boolean ALT_BLACKSMITH_USE_RECIPES;
	
	/** Skills & Classes **/
	public static boolean AUTO_LEARN_SKILLS;
	public static boolean ALT_GAME_MAGICFAILURES;
	public static boolean ALT_GAME_SHIELD_BLOCKS;
	public static int ALT_PERFECT_SHLD_BLOCK;
	public static boolean LIFE_CRYSTAL_NEEDED;
	public static boolean SP_BOOK_NEEDED;
	public static boolean ES_SP_BOOK_NEEDED;
	public static boolean DIVINE_SP_BOOK_NEEDED;
	public static boolean ALT_GAME_SUBCLASS_WITHOUT_QUESTS;
	
	/** Buffs */
	public static boolean STORE_SKILL_COOLTIME;
	public static int BUFFS_MAX_AMOUNT;
	
	/** Starting Adena */
	public static int STARTING_ADENA;
	
	// --------------------------------------------------
	// Sieges
	// --------------------------------------------------
	
	public static int SIEGE_LENGTH;
	public static int MINIMUM_CLAN_LEVEL;
	public static int MAX_ATTACKERS_NUMBER;
	public static int MAX_DEFENDERS_NUMBER;
	public static int ATTACKERS_RESPAWN_DELAY;
	
	// --------------------------------------------------
	// Server
	// --------------------------------------------------
	
	public static String GAMESERVER_HOSTNAME;
	public static int PORT_GAME;
	public static String HOSTNAME;
	public static int GAME_SERVER_LOGIN_PORT;
	public static String GAME_SERVER_LOGIN_HOST;
	public static int REQUEST_ID;
	public static boolean ACCEPT_ALTERNATE_ID;
	
	/** Access to database */
	public static String DATABASE_URL;
	public static String DATABASE_LOGIN;
	public static String DATABASE_PASSWORD;
	public static int DATABASE_MAX_CONNECTIONS;
	
	/** serverList & Test */
	public static boolean SERVER_LIST_BRACKET;
	public static boolean SERVER_LIST_CLOCK;
	public static int SERVER_LIST_AGE;
	public static boolean SERVER_LIST_TESTSERVER;
	public static boolean SERVER_LIST_PVPSERVER;
	public static boolean SERVER_GMONLY;
	
	/** clients related */
	public static int DELETE_DAYS;
	public static int MAXIMUM_ONLINE_USERS;
	public static int MIN_PROTOCOL_REVISION;
	public static int MAX_PROTOCOL_REVISION;
	
	/** Auto-loot */
	public static boolean AUTO_LOOT;
	public static boolean AUTO_LOOT_HERBS;
	public static boolean AUTO_LOOT_RAID;
	
	/** Items Management */
	public static boolean ALLOW_DISCARDITEM;
	public static boolean MULTIPLE_ITEM_DROP;
	public static int HERB_AUTO_DESTROY_TIME;
	public static int ITEM_AUTO_DESTROY_TIME;
	public static int EQUIPABLE_ITEM_AUTO_DESTROY_TIME;
	public static Map<Integer, Integer> SPECIAL_ITEM_DESTROY_TIME;
	public static int PLAYER_DROPPED_ITEM_MULTIPLIER;
	
	/** Rate control */
	public static double RATE_XP;
	public static double RATE_SP;
	public static double RATE_PARTY_XP;
	public static double RATE_PARTY_SP;
	public static double RATE_DROP_ADENA;
	public static double RATE_CONSUMABLE_COST;
	public static double RATE_DROP_ITEMS;
	public static double RATE_DROP_ITEMS_BY_RAID;
	public static double RATE_DROP_SPOIL;
	public static int RATE_DROP_MANOR;
	
	public static double RATE_QUEST_DROP;
	public static double RATE_QUEST_REWARD;
	public static double RATE_QUEST_REWARD_XP;
	public static double RATE_QUEST_REWARD_SP;
	public static double RATE_QUEST_REWARD_ADENA;
	
	public static double RATE_KARMA_EXP_LOST;
	public static double RATE_SIEGE_GUARDS_PRICE;
	
	public static int PLAYER_DROP_LIMIT;
	public static int PLAYER_RATE_DROP;
	public static int PLAYER_RATE_DROP_ITEM;
	public static int PLAYER_RATE_DROP_EQUIP;
	public static int PLAYER_RATE_DROP_EQUIP_WEAPON;
	
	public static int KARMA_DROP_LIMIT;
	public static int KARMA_RATE_DROP;
	public static int KARMA_RATE_DROP_ITEM;
	public static int KARMA_RATE_DROP_EQUIP;
	public static int KARMA_RATE_DROP_EQUIP_WEAPON;
	
	public static double PET_XP_RATE;
	public static int PET_FOOD_RATE;
	public static double SINEATER_XP_RATE;
	
	public static double RATE_DROP_COMMON_HERBS;
	public static double RATE_DROP_HP_HERBS;
	public static double RATE_DROP_MP_HERBS;
	public static double RATE_DROP_SPECIAL_HERBS;
	public static float RATE_DROP_SEAL_STONES;
	/** Allow types */
	public static boolean ALLOW_FREIGHT;
	public static boolean ALLOW_WAREHOUSE;
	public static boolean ALLOW_WEAR;
	public static int WEAR_DELAY;
	public static int WEAR_PRICE;
	public static boolean ALLOW_LOTTERY;
	public static boolean ALLOW_WATER;
	public static boolean ALLOW_BOAT;
	public static boolean ALLOW_CURSED_WEAPONS;
	public static boolean ALLOW_MANOR;
	public static boolean ENABLE_FALLING_DAMAGE;
	
	/** Debug & Dev */
	public static boolean ALT_DEV_NO_SPAWNS;
	public static boolean DEBUG;
	public static boolean DEVELOPER;
	public static boolean PACKET_HANDLER_DEBUG;
	
	/** Deadlock Detector */
	public static boolean DEADLOCK_DETECTOR;
	public static int DEADLOCK_CHECK_INTERVAL;
	public static boolean RESTART_ON_DEADLOCK;
	
	/** Logs */
	public static boolean LOG_CHAT;
	public static boolean LOG_ITEMS;
	public static boolean GMAUDIT;
	
	/** Community Board */
	public static boolean ENABLE_COMMUNITY_BOARD;
	public static String BBS_DEFAULT;
	
	/** Flood Protectors */
	public static int ROLL_DICE_TIME;
	public static int HERO_VOICE_TIME;
	public static int SUBCLASS_TIME;
	public static int DROP_ITEM_TIME;
	public static int SERVER_BYPASS_TIME;
	public static int MULTISELL_TIME;
	public static int MANUFACTURE_TIME;
	public static int MANOR_TIME;
	public static int SENDMAIL_TIME;
	public static int CHARACTER_SELECT_TIME;
	public static int GLOBAL_CHAT_TIME;
	public static int TRADE_CHAT_TIME;
	public static int SOCIAL_TIME;
	public static int USER_ITEM_TIME;
	
	/** ThreadPool */
	public static int SCHEDULED_THREAD_POOL_COUNT;
	public static int THREADS_PER_SCHEDULED_THREAD_POOL;
	public static int INSTANT_THREAD_POOL_COUNT;
	public static int THREADS_PER_INSTANT_THREAD_POOL;
	
	/** Misc */
	public static boolean L2WALKER_PROTECTION;
	public static boolean SERVER_NEWS;
	public static int ZONE_TOWN;
	public static boolean DISABLE_TUTORIAL;
	
	// --------------------------------------------------
	// Those "hidden" settings haven't configs to avoid admins to fuck their server
	// You still can experiment changing values here. But don't say I didn't warn you.
	// --------------------------------------------------
	
	/** Reserve Host on LoginServerThread */
	public static boolean RESERVE_HOST_ON_LOGIN = false; // default false
	
	/** MMO settings */
	public static int MMO_SELECTOR_SLEEP_TIME = 20; // default 20
	public static int MMO_MAX_SEND_PER_PASS = 80; // default 80
	public static int MMO_MAX_READ_PER_PASS = 80; // default 80
	public static int MMO_HELPER_BUFFER_COUNT = 20; // default 20
	public static boolean ESPECIAL_VIP_LOGIN;
	public static boolean ALLOW_BAKING_SYSTEM;
	/** Client Packets Queue settings */
	public static int CLIENT_PACKET_QUEUE_SIZE = 14; // default MMO_MAX_READ_PER_PASS + 2
	public static int CLIENT_PACKET_QUEUE_MAX_BURST_SIZE = 13; // default MMO_MAX_READ_PER_PASS + 1
	public static int CLIENT_PACKET_QUEUE_MAX_PACKETS_PER_SECOND = 160; // default 160
	public static int CLIENT_PACKET_QUEUE_MEASURE_INTERVAL = 5; // default 5
	public static int CLIENT_PACKET_QUEUE_MAX_AVERAGE_PACKETS_PER_SECOND = 80; // default 80
	public static int CLIENT_PACKET_QUEUE_MAX_FLOODS_PER_MIN = 2; // default 2
	public static int CLIENT_PACKET_QUEUE_MAX_OVERFLOWS_PER_MIN = 5; // default 1
	public static int CLIENT_PACKET_QUEUE_MAX_UNDERFLOWS_PER_MIN = 5; // default 1
	public static int CLIENT_PACKET_QUEUE_MAX_UNKNOWN_PER_MIN = 5; // default 5
	
	// --------------------------------------------------
	
	/**
	 * Initialize {@link ExProperties} from specified configuration file.
	 * @param filename : File name to be loaded.
	 * @return ExProperties : Initialized {@link ExProperties}.
	 */
	public static final ExProperties initProperties(String filename)
	{
		final ExProperties result = new ExProperties();
		
		try
		{
			result.load(new File(filename));
		}
		catch (IOException e)
		{
			_log.warning("Config: Error loading \"" + filename + "\" config.");
		}
		
		return result;
	}
	
	/**
	 * itemId1,itemNumber1;itemId2,itemNumber2... to the int[n][2] = [itemId1][itemNumber1],[itemId2][itemNumber2]...
	 * @param line
	 * @return an array consisting of parsed items.
	 */
	private static final int[][] parseItemsList(String line)
	{
		final String[] propertySplit = line.split(";");
		if (propertySplit.length == 0)
			return null;
		
		int i = 0;
		String[] valueSplit;
		final int[][] result = new int[propertySplit.length][];
		for (String value : propertySplit)
		{
			valueSplit = value.split(",");
			if (valueSplit.length != 2)
			{
				_log.warning("Config: Error parsing entry -> \"" + valueSplit[0] + "\", should be itemId,itemNumber");
				return null;
			}
			
			result[i] = new int[2];
			try
			{
				result[i][0] = Integer.parseInt(valueSplit[0]);
			}
			catch (NumberFormatException e)
			{
				_log.warning("Config: Error parsing item ID -> \"" + valueSplit[0] + "\"");
				return null;
			}
			
			try
			{
				result[i][1] = Integer.parseInt(valueSplit[1]);
			}
			catch (NumberFormatException e)
			{
				_log.warning("Config: Error parsing item amount -> \"" + valueSplit[1] + "\"");
				return null;
			}
			i++;
		}
		return result;
	}
	
	private static final void loadDonator()
	{
		final ExProperties donator = initProperties(DONATOR_FILE);
		/** VIP System */
		ALLOW_VIP_NCOLOR = Boolean.parseBoolean(donator.getProperty("AllowVipNameColor", "True"));
		VIP_NCOLOR = Integer.decode("0x" + donator.getProperty("VipNameColor", "88AA88"));
		ALLOW_VIP_TCOLOR = Boolean.parseBoolean(donator.getProperty("AllowVipTitleColor", "True"));
		VIP_TCOLOR = Integer.decode("0x" + donator.getProperty("VipTitleColor", "88AA88"));
		/** AIO System */
		ALLOW_AIO_NCOLOR = Boolean.parseBoolean(donator.getProperty("AllowAioNameColor", "True"));
		AIO_NCOLOR = Integer.decode("0x" + donator.getProperty("AioNameColor", "88AA88"));
		ALLOW_AIO_TCOLOR = Boolean.parseBoolean(donator.getProperty("AllowAioTitleColor", "True"));
		AIO_TCOLOR = Integer.decode("0x" + donator.getProperty("AioTitleColor", "88AA88"));
		VIP_XP_SP_RATE = Float.parseFloat(donator.getProperty("VIPXpSpRate", "1.5"));
		VIP_ADENA_RATE = Float.parseFloat(donator.getProperty("VIPAdenaRate", "1.5"));
		VIP_DROP_RATE = Float.parseFloat(donator.getProperty("VIPDropRate", "1.5"));
		VIP_SPOIL_RATE = Float.parseFloat(donator.getProperty("VIPSpoilRate", "1.5"));
	
		VIP_30_DAYS_PRICE = Integer.parseInt(donator.getProperty("Vip_30_Days_Price", "30"));
		VIP_60_DAYS_PRICE = Integer.parseInt(donator.getProperty("Vip_60_Days_Price", "60"));
		VIP_90_DAYS_PRICE = Integer.parseInt(donator.getProperty("Vip_90_Days_Price", "90"));
		VIP_ETERNAL_PRICE = Integer.parseInt(donator.getProperty("Vip_Eternal_Price", "120"));
		
		AIO_30_DAYS_PRICE = Integer.parseInt(donator.getProperty("Aio_30_Days_Price", "30"));
		AIO_60_DAYS_PRICE = Integer.parseInt(donator.getProperty("Aio_60_Days_Price", "60"));
		AIO_90_DAYS_PRICE = Integer.parseInt(donator.getProperty("Aio_90_Days_Price", "90"));
		AIO_ETERNAL_PRICE = Integer.parseInt(donator.getProperty("Aio_Eternal_Price", "120"));
		
		HERO_30_DAYS_PRICE = Integer.parseInt(donator.getProperty("Hero_30_Days_Price", "30"));
		HERO_60_DAYS_PRICE = Integer.parseInt(donator.getProperty("Hero_60_Days_Price", "60"));
		HERO_90_DAYS_PRICE = Integer.parseInt(donator.getProperty("Hero_90_Days_Price", "90"));
		HERO_ETERNAL_PRICE = Integer.parseInt(donator.getProperty("Hero_Eternal_Price", "120"));
		
		DONATE_CLASS_PRICE = Integer.parseInt(donator.getProperty("Change_Class_Price", "15"));
		DONATE_NAME_PRICE = Integer.parseInt(donator.getProperty("Change_Name_Price", "15"));
		DONATE_SEX_PRICE = Integer.parseInt(donator.getProperty("Change_Sex_Price", "15"));
		DONATESYSTEM = donator.getProperty("DonateSystem", false);
		SHOW_HTML = donator.getProperty("ShowHtmlOnEnter", false);
		DONATE_COIN_ID = Integer.parseInt(donator.getProperty("DonateCoin_Id", "9511"));
		AIO_ITEM_ID = donator.getProperty("RewardAioItemId", 10);
		AIO_SKILLS = new HashMap<>();
		for (String skillInfo : donator.getProperty("AioSkills").split(";"))
		{
			String[] info = skillInfo.split(",");
			AIO_SKILLS.put(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
		}
	}
	
	private static final void loadCustoms()
	{
		final ExProperties customs = initProperties(CUSTOMS_FILE);
		BOTS_PREVENTION = customs.getProperty("EnableBotsPrevention", false);
		KILLS_COUNTER = customs.getProperty("KillsCounter", 60);
		KILLS_COUNTER_RANDOMIZATION = customs.getProperty("KillsCounterRandomization", 50);
		VALIDATION_TIME = customs.getProperty("ValidationTime", 60);
		PUNISHMENT = customs.getProperty("Punishment", 0);
		PUNISHMENT_TIME = customs.getProperty("PunishmentTime", 60);
		PROTECTED_SKILLS = customs.getProperty("NotCanceledSkills");
		ALLOW_CUSTOM_CANCEL = new ArrayList<>();
		for (String id : PROTECTED_SKILLS.split(","))
		ALLOW_CUSTOM_CANCEL.add(Integer.parseInt(id));
		CUSTOM_CANCEL_SECONDS = Integer.parseInt(customs.getProperty("TimeReturnBuffs", "10"));
		ENABLE_GUI = Boolean.parseBoolean(customs.getProperty("EnableGui", "True"));
		ALLOW_AUTO_NOBLESS_FROM_BOSS = Boolean.valueOf(customs.getProperty("AllowAutoNoblessFromBoss", "True")).booleanValue();
		BOSS_ID = Integer.parseInt(customs.getProperty("BossId", "25325"));
		RADIUS_TO_RAID = Integer.parseInt(customs.getProperty("RadiusToRaid", "1000"));
		
		DISABLE_ATTACK_NPC_TYPE = customs.getProperty("DisableAttackToNpcs", false);
		ALLOWED_NPC_TYPES = customs.getProperty("AllowedNPCTypes");
		LIST_ALLOWED_NPC_TYPES = new ArrayList<>();
		for (String npc_type : ALLOWED_NPC_TYPES.split(","))
		{
			LIST_ALLOWED_NPC_TYPES.add(npc_type);
		}
		
		OLYMPIAD_END_ANNOUNE = customs.getProperty("OlyEndAnnounce", true);
		
		ANNOUNCE_CASTLE_LORDS = customs.getProperty("AnnounceCastleLordLogin", true);
		ANNOUNCE_HEROES = customs.getProperty("AnnounceHeroLogin", true);
		
		DISABLE_CAPSLOCK = Boolean.valueOf(customs.getProperty("DisableCapsLock", "false")).booleanValue();
		
		LEAVE_BUFFS_ON_DIE = Boolean.parseBoolean(customs.getProperty("LeaveBuffsOnDie", "True"));
		
		ENABLE_MODIFY_SKILL_DURATION = Boolean.parseBoolean(customs.getProperty("EnableModifySkillDuration", "false"));
		if (ENABLE_MODIFY_SKILL_DURATION)
		{
			SKILL_DURATION_LIST = new HashMap<>();
			
			String[] propert = customs.getProperty("SkillDurationList", "").split(";");
			
			String[] infos = propert;
			int str1 = infos.length;
			for (int id = 0; id < str1; id++)
			{
				String skill = infos[id];
				
				String[] skillSplit = skill.split(",");
				if (skillSplit.length != 2)
				{
					System.out.println("[SkillDurationList]: invalid config property -> SkillDurationList \"" + skill + "\"");
				}
				else
				{
					try
					{
						SKILL_DURATION_LIST.put(Integer.valueOf(Integer.parseInt(skillSplit[0])), Integer.valueOf(Integer.parseInt(skillSplit[1])));
					}
					catch (NumberFormatException nfe)
					{
						if (DEBUG)
						{
							nfe.printStackTrace();
						}
						if (!skill.equals(""))
						{
							System.out.println("[SkillDurationList]: invalid config property -> SkillList \"" + skillSplit[0] + "\"" + skillSplit[1]);
						}
					}
				}
			}
		}
		
		GRAND_BOSS = customs.getProperty("RaidBossList");
		GRAND_BOSS_LIST = new ArrayList<>();
		for (String id : GRAND_BOSS.trim().split(","))
		{
			GRAND_BOSS_LIST.add(Integer.parseInt(id.trim()));
		}
		
		BS_PVPZONE = Boolean.parseBoolean(customs.getProperty("DisableBsPvPZone", "False"));
		WYVERN_PVPZONE = Boolean.parseBoolean(customs.getProperty("WyverbPvPZone", "False"));
		
		MULTIBOX_PROTECTION_ENABLED = customs.getProperty("MultiboxProtectionEnabled", false);
		MULTIBOX_PROTECTION_CLIENTS_PER_PC = customs.getProperty("ClientsPerPc", 2);
		MULTIBOX_PROTECTION_PUNISH = customs.getProperty("MultiboxPunish", 2);
		TIME_MULTIBOX = customs.getProperty("Disconect_Multibox", 2);
		
		ANNOUNCE_PK_PVP = customs.getProperty("AnnouncePkPvP", false);
		ANNOUNCE_PK_PVP_NORMAL_MESSAGE = customs.getProperty("AnnouncePkPvPNormalMessage", true);
		ANNOUNCE_PK_MSG = customs.getProperty("AnnouncePkMsg", "Player $killer has slaughtered $target .");
		ANNOUNCE_PVP_MSG = customs.getProperty("AnnouncePvpMsg", "Player $killer has defeated $target .");
		
		OFFLINE_TRADE_ENABLE = customs.getProperty("OfflineTradeEnable", false);
		OFFLINE_CRAFT_ENABLE = customs.getProperty("OfflineCraftEnable", false);
		OFFLINE_MODE_IN_PEACE_ZONE = customs.getProperty("OfflineModeInPeaceZone", false);
		OFFLINE_MODE_NO_DAMAGE = customs.getProperty("OfflineModeNoDamage", false);
		OFFLINE_SET_NAME_COLOR = customs.getProperty("OfflineSetNameColor", false);
		OFFLINE_NAME_COLOR = Integer.decode("0x" + customs.getProperty("OfflineNameColor", 808080));
		OFFLINE_SET_SLEEP = customs.getProperty("OfflineSetSleepEffect", false);
		RESTORE_OFFLINERS = customs.getProperty("RestoreOffliners", false);
		OFFLINE_MAX_DAYS = customs.getProperty("OfflineMaxDays", 10);
		OFFLINE_DISCONNECT_FINISHED = customs.getProperty("OfflineDisconnectFinished", true);
		
		DISARM_WEP = customs.getProperty("DisarmWepOnLogin", false);
		NEW_CHAR_TITLE = customs.getProperty("NewCharTitle", "L2Surrender");
		SUBCLASS_LEVEL = customs.getProperty("SubclassLevel", 52);
		
		PVPS_COLORS = customs.getProperty("PvpsColorsName", "");
		PVPS_COLORS_LIST = new HashMap<>();
		
		String[] splitted_pvps_colors = PVPS_COLORS.split(";");
		
		for (String iii : splitted_pvps_colors)
		{
			
			String[] pvps_colors = iii.split(",");
			
			if (pvps_colors.length != 2)
			{
				System.out.println("Invalid properties.");
			}
			else
			{
				PVPS_COLORS_LIST.put(Integer.parseInt(pvps_colors[0]), Integer.decode("0x" + pvps_colors[1]));
			}
			
		}
		
		PKS_COLORS = customs.getProperty("PksColorsTitle", "");
		PKS_COLORS_LIST = new HashMap<>();
		
		String[] splitted_pks_colors = PKS_COLORS.split(";");
		
		for (String iii : splitted_pks_colors)
		{
			
			String[] pks_colors = iii.split(",");
			
			if (pks_colors.length != 2)
			{
				System.out.println("Invalid properties.");
			}
			else
			{
				PKS_COLORS_LIST.put(Integer.parseInt(pks_colors[0]), Integer.decode("0x" + pks_colors[1]));
			}
			
		}
		
		SHOW_HP_CP_PVP = customs.getProperty("ShowHpCpPvP", false);
		START_LEVEL = customs.getProperty("StartLevel", 1);
		
		CUSTOM_SPAWN_CHAR = customs.getProperty("CustomSpawn", false);
		RANDOM_SPAWN_CHAR = customs.getProperty("RandomAreasSpawn", 1);
		
		CUSTOM_SPAWN1 = customs.getProperty("custom_spawn1", new int[]
			{
				-47406,
				49423,
				59746
			});
		
		CUSTOM_SPAWN2 = customs.getProperty("custom_spawn2", new int[]
			{
				-47406,
				49423,
				59746
			});
		
		CUSTOM_SPAWN3 = customs.getProperty("custom_spawn3", new int[]
			{
				-47406,
				49423,
				59746
			});
		
		ALT_GAME_SUBCLASS_EVERYWHERE = customs.getProperty("SubclassEverywhere", false);
		
		/** PvpReward */
		PVP_REWARD_SYSTEM = customs.getProperty("PvpRewardSystem", false);
		String PVP_REWARD_VALUE = customs.getProperty("PvpReward", "57,100000000;");
		String[] pvp_reward_splitted_1 = PVP_REWARD_VALUE.split(";");
		for (String i : pvp_reward_splitted_1)
		{
			String[] pvp_reward_splitted_2 = i.split(",");
			PVP_REWARD.put(Integer.parseInt(pvp_reward_splitted_2[1]), Integer.parseInt(pvp_reward_splitted_2[0]));
		}
		
		/** PvpCount */
		PVP_COUNT_SYSTEM = customs.getProperty("PvpCountSystem", false);
		
		String killing_spree_values = customs.getProperty("QuakeSystemValues", "");
		String killing_spree_values_splitted_1[] = killing_spree_values.split(";");
		for (String s : killing_spree_values_splitted_1)
		{
			String killing_spree_values_splitted_2[] = s.split(",");
			QUAKE_VALUES.put(Integer.parseInt(killing_spree_values_splitted_2[0]), killing_spree_values_splitted_2[1]);
		}
		
		EMOTION_CHAT_SYSTEM = customs.getProperty("PvPsChatSystem", false);
		ALLOW_PVP_CHAT = customs.getProperty("AllowPvPChat", false);
		PVPS_TO_TALK_ON_SHOUT = customs.getProperty("PvPsToTalkOnShout", 40);
		PVPS_TO_TALK_ON_TRADE = customs.getProperty("PvPsToTalkOnTrade", 40);
		
		EMOTION_CHAT_SYSTEM = customs.getProperty("EmotionChatSystem", false);
		EMOTION_CHAT_LIST = new HashMap<>();
		for (String words : customs.getProperty("EmotionChat").split(";"))
		{
			final String[] infos = words.split(",");
			EMOTION_CHAT_LIST.put(infos[0], Integer.valueOf(infos[1]));
		}
		
		CHAT_RESTRICTION_WORDS = customs.getProperty("ChatRestrictionWords", false);
		CHAT_RESTRICTION_WORDS_LIST = new ArrayList<>();
		for (String words : customs.getProperty("ChatRestrictionWordsList").split(";"))
			CHAT_RESTRICTION_WORDS_LIST.add(words);
		
		/** Starting Items */
		STARTING_ITEMS_SYSTEM = customs.getProperty("StartingItemsSystem", false);
		pvp_reward_splitted_1 = customs.getProperty("StartingItems", "57,100000").split(";");
		
		for (String reward : pvp_reward_splitted_1)
		{
			String[] rewardSplit = reward.split(",");
			
			if (rewardSplit.length != 2)
				System.out.println("StartingItemsSystem[Config.load()]: invalid config property -> StartingItems \"" + reward + "\"");
			else
			{
				try
				{
					STARTING_ITEMS.add(new int[]
						{
							Integer.valueOf(rewardSplit[0]),
							Integer.valueOf(rewardSplit[1])
						});
				}
				catch (NumberFormatException nfe)
				{
					if (!reward.equals(""))
						System.out.println("StartingItemsSystem[Config.load()]: invalid config property -> StartingItems \"" + reward + "\"");
				}
			}
		}
		HERO_DAYS = Integer.parseInt(customs.getProperty("HeroDays", "5"));
		INFINITY_SS = customs.getProperty("InfinitySS", false);
		INFINITY_ARROWS = customs.getProperty("InfinityArrows", false);
		
		ENABLE_SKIPPING = customs.getProperty("EnableSkippingItems", false);
		
		WELCOME_EFFECT = customs.getProperty("NewCharEffect", true);
		
		PVP_PK_TITLE = Boolean.parseBoolean(customs.getProperty("PvpPkTitle", "True"));
		PVP_TITLE_PREFIX = customs.getProperty("PvPTitlePrefix", " ");
		PK_TITLE_PREFIX = customs.getProperty("PkTitlePrefix", " ");
		
		UNSTUCK_TIME = customs.getProperty("UnstuckTime", 30);
	}
	
	private static final void MultiNpcs()
	{
		final ExProperties multi = initProperties(MULTISHOP_FILE);
		DONATE_ITEM = multi.getProperty("DonateItemId", 9303);
		NOBL_ITEM_COUNT = multi.getProperty("NoblesseItemCount", 100);
		SEX_ITEM_COUNT = multi.getProperty("SexItemCount", 100);
		PK_ITEM_COUNT = multi.getProperty("PkItemCount", 100);
		PK_CLEAN = multi.getProperty("PkCleanValue", 50);
		CLAN_ITEM_COUNT = multi.getProperty("ClanItemCount", 100);
		CLAN_REP_ITEM_COUNT = multi.getProperty("ClanRepsCount", 100);
		CLAN_REPS = multi.getProperty("ClanReps", 20000);
		AUGM_ITEM_COUNT = multi.getProperty("AugmentionItemCount", 100);
		NOBL_ITEM_COUNT = multi.getProperty("ClanSkillsItemCount", 100);
		REC_ITEM_COUNT = multi.getProperty("RecItemCount", 100);
		PASSWORD_ITEM_COUNT = multi.getProperty("PasswordItemCount", 100);
		COLOR_ITEM_COUNT = multi.getProperty("ColorItemCount", 100);
		NAME_ITEM_COUNT = multi.getProperty("NameItemCount", 100);
		ENCHANT_ITEM_COUNT = multi.getProperty("EnchantItemCount", 100);
		ENCHANT_MAX_VALUE = multi.getProperty("MaxEnchantValue", 15);
		CLASS_ITEM_COUNT = multi.getProperty("ClassItemCount", 100);
	}
	
	private static final void loadFakes()
	{
		final ExProperties customs = initProperties(FAKE_PLAYER_FILE);
		FAKE_PLAYER_RETURNS_TO_FARM_ZONE = customs.getProperty("FakePlayerReturnFarmzone", true);
		SPAWN_FAKE_LOC1 = customs.getProperty("FakeSpawnLocation1", new int[]
			{
				148989,
				-168447,
				2008
			});
		SPAWN_FAKE_LOC2 = customs.getProperty("FakeSpawnLocation2", new int[]
			{
				147208,
				-171816,
				2248
			});
		FAKE_PLAYER_CAN_TARGET_REAL_PLAYER = customs.getProperty("FakePlayerTargetRealPlayer", true);
		FAKE_PLAYER_TITLE = customs.getProperty("FakePlayerTitle", "AdvancedGames");
	}
	
	private static final void loadRaidboss()
	{
		// Raid config
		final ExProperties npcs = initProperties(RAID_FILE);
		RAID_BOSS_INFO_PAGE_LIMIT = npcs.getProperty("RaidBossInfoPageLimit", 15);
		RAID_BOSS_DROP_PAGE_LIMIT = npcs.getProperty("RaidBossDropPageLimit", 15);
		RAID_BOSS_DATE_FORMAT = npcs.getProperty("RaidBossDateFormat", "MMM dd, HH:mm");
		RAID_BOSS_IDS = npcs.getProperty("RaidBossIds", "0,0");
		LIST_RAID_BOSS_IDS = new ArrayList<>();
		for (String val : RAID_BOSS_IDS.split(","))
		{
			int npcId = Integer.parseInt(val);
			LIST_RAID_BOSS_IDS.add(npcId);
		}
		FLAG_RB = npcs.getProperty("FlagRbZones", false);
		PLAYERS_CAN_HEAL_RB = npcs.getProperty("PlayersCanHealRb", false);
		ALLOW_DIRECT_TP_TO_BOSS_ROOM = npcs.getProperty("AllowGrandBossesTeleport", false);
		ANNOUNCE_RAIDBOS_KILL = Boolean.parseBoolean(npcs.getProperty("AnnounceRaidBossKill", "false"));
		ANNOUNCE_GRANDBOS_KILL = Boolean.parseBoolean(npcs.getProperty("AnnounceGranBossKill", "false"));
		ANNOUNCE_BOSS_ALIVE =  Boolean.parseBoolean(npcs.getProperty("AnnounceSpawnAllBoss", "false"));
		BOSS_CUSTOM_NOBLES_ID = Integer.parseInt(npcs.getProperty("BossLastHitID", "1"));
		DISTANCE_FOR_RENEWAL_NOBLES_PARTY = Integer.parseInt(npcs.getProperty("BossLastHitDistance", "1"));
		ENABLE_RAIDBOSS_NOBLES = Boolean.parseBoolean(npcs.getProperty("EnableBossEventLastHit", "true"));
		RAID_HP_REGEN_MULTIPLIER = npcs.getProperty("RaidHpRegenMultiplier", 1.);
		RAID_MP_REGEN_MULTIPLIER = npcs.getProperty("RaidMpRegenMultiplier", 1.);
		RAID_DEFENCE_MULTIPLIER = npcs.getProperty("RaidDefenceMultiplier", 1.);
		RAID_MINION_RESPAWN_TIMER = npcs.getProperty("RaidMinionRespawnTime", 300000);
		
		RAID_DISABLE_CURSE = npcs.getProperty("DisableRaidCurse", false);
		RAID_CHAOS_TIME = npcs.getProperty("RaidChaosTime", 30);
		GRAND_CHAOS_TIME = npcs.getProperty("GrandChaosTime", 30);
		MINION_CHAOS_TIME = npcs.getProperty("MinionChaosTime", 30);
		
		SPAWN_INTERVAL_AQ = npcs.getProperty("AntQueenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_AQ = npcs.getProperty("AntQueenRandomSpawn", 17);
		
		SPAWN_INTERVAL_ANTHARAS = npcs.getProperty("AntharasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_ANTHARAS = npcs.getProperty("AntharasRandomSpawn", 72);
		WAIT_TIME_ANTHARAS = npcs.getProperty("AntharasWaitTime", 30) * 60000;
		
		SPAWN_INTERVAL_BAIUM = npcs.getProperty("BaiumSpawnInterval", 168);
		RANDOM_SPAWN_TIME_BAIUM = npcs.getProperty("BaiumRandomSpawn", 48);
		
		SPAWN_INTERVAL_CORE = npcs.getProperty("CoreSpawnInterval", 60);
		RANDOM_SPAWN_TIME_CORE = npcs.getProperty("CoreRandomSpawn", 23);
		
		SPAWN_INTERVAL_FRINTEZZA = npcs.getProperty("FrintezzaSpawnInterval", 48);
		RANDOM_SPAWN_TIME_FRINTEZZA = npcs.getProperty("FrintezzaRandomSpawn", 8);
		WAIT_TIME_FRINTEZZA = npcs.getProperty("FrintezzaWaitTime", 10) * 60000;
		BYPASS_FRINTEZZA_PARTIES_CHECK = Boolean.valueOf(npcs.getProperty("BypassPartiesCheck", "true"));
		FRINTEZZA_MIN_PARTIES = npcs.getProperty("FrintezzaMinParties", 4);
		FRINTEZZA_MAX_PARTIES = npcs.getProperty("FrintezzaMaxParties", 5);
		ZAKEN_ENABLE_DOOR = npcs.getProperty("EnableZakenDoor", 0);
		SPAWN_INTERVAL_ORFEN = npcs.getProperty("OrfenSpawnInterval", 48);
		RANDOM_SPAWN_TIME_ORFEN = npcs.getProperty("OrfenRandomSpawn", 20);
		
		SPAWN_INTERVAL_SAILREN = npcs.getProperty("SailrenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_SAILREN = npcs.getProperty("SailrenRandomSpawn", 24);
		WAIT_TIME_SAILREN = npcs.getProperty("SailrenWaitTime", 5) * 60000;
		
		SPAWN_INTERVAL_VALAKAS = npcs.getProperty("ValakasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_VALAKAS = npcs.getProperty("ValakasRandomSpawn", 72);
		WAIT_TIME_VALAKAS = npcs.getProperty("ValakasWaitTime", 30) * 60000;
		
		SPAWN_INTERVAL_ZAKEN = npcs.getProperty("ZakenSpawnInterval", 60);
		RANDOM_SPAWN_TIME_ZAKEN = npcs.getProperty("ZakenRandomSpawn", 20);
	}
	
	/**
	 * Loads Character Killing Monuments settings.
	 */
	private static final void loadCharacterKillingMonuments()
	{
		final ExProperties ckm = initProperties(CKM_FILE);
		CKM_ENABLED = ckm.getProperty("CKMEnabled", false);
		CKM_CYCLE_LENGTH = ckm.getProperty("CKMCycleLength", 86400000);
		CKM_PVP_NPC_TITLE = ckm.getProperty("CKMPvPNpcTitle", "%kills% PvPs in the last 24h");
		CKM_PVP_NPC_TITLE_COLOR = Integer.decode("0x" + ckm.getProperty("CKMPvPNpcTitleColor", "00CCFF"));
		CKM_PVP_NPC_NAME_COLOR = Integer.decode("0x" + ckm.getProperty("CKMPvPNpcNameColor", "FFFFFF"));
		CKM_PK_NPC_TITLE = ckm.getProperty("CKMPKNpcTitle", "%kills% PKs in the last 24h");
		CKM_PK_NPC_TITLE_COLOR = Integer.decode("0x" + ckm.getProperty("CKMPKNpcTitleColor", "00CCFF"));
		CKM_PK_NPC_NAME_COLOR = Integer.decode("0x" + ckm.getProperty("CKMPKNpcNameColor", "FFFFFF"));
		CKM_PLAYER_REWARDS = parseItemsList(ckm.getProperty("CKMReward", "6651,50"));
	}
	
	/**
	 * Loads balance settings.
	 */
	private static final void loadBalance()
	{
		final ExProperties balance = initProperties(Config.BALANCE_FILE);
		
		ALLOW_LIGHT_USE_HEAVY = Boolean.parseBoolean(balance.getProperty("AllowLightUseHeavy", "False"));
		NOTALLOWCLASS = balance.getProperty("NotAllowedUseHeavy", "");
		NOTALLOWEDUSEHEAVY = new ArrayList<>();
		for (String classId : NOTALLOWCLASS.split(","))
		{
			
			NOTALLOWEDUSEHEAVY.add(Integer.valueOf(Integer.parseInt(classId)));
		}
		
		ALLOW_HEAVY_USE_LIGHT = Boolean.parseBoolean(balance.getProperty("AllowHeavyUseLight", "False"));
		NOTALLOWCLASSE = balance.getProperty("NotAllowedUseLight", "");
		NOTALLOWEDUSELIGHT = new ArrayList<>();
		for (String classId : NOTALLOWCLASSE.split(","))
		{
			
			NOTALLOWEDUSELIGHT.add(Integer.valueOf(Integer.parseInt(classId)));
		}
		
		ALT_DISABLE_BOW_CLASSES = Boolean.parseBoolean(balance.getProperty("AltDisableBow", "False"));
		DISABLE_BOW_CLASSES_STRING = balance.getProperty("DisableBowForClasses", "");
		DISABLE_BOW_CLASSES = new ArrayList<>();
		for (String class_id : DISABLE_BOW_CLASSES_STRING.split(","))
		{
			if (!class_id.equals(""))
				DISABLE_BOW_CLASSES.add(Integer.parseInt(class_id));
		}
		
		ALT_FIGHTER_TO_SUMMON = Float.parseFloat(balance.getProperty("OlympiadFighterToSummon", "1.00"));
		ALT_MAGE_TO_SUMMON = Float.parseFloat(balance.getProperty("OlympiadMageToSummon", "1.00"));
		ENABLE_CLASS_DAMAGES = balance.getProperty("EnableClassDamagesSettings", true);
		ENABLE_CLASS_DAMAGES_IN_OLY = balance.getProperty("EnableClassDamagesSettingsInOly", true);
		ENABLE_CLASS_DAMAGES_LOGGER = balance.getProperty("EnableClassDamagesLogger", true);
		
		ENABLE_CUSTOM_CRIT = Boolean.parseBoolean(balance.getProperty("Enable_Custom_CriticalChance", "true"));
		MAX_PCRIT_RATE = Integer.parseInt(balance.getProperty("MaxPCritRate", "500"));
		PCRIT_RATE_ArcherHuman = Integer.parseInt(balance.getProperty("PCritRate_ArcherHuman", "300"));
		PCRIT_RATE_ArcherElfo = Integer.parseInt(balance.getProperty("PCritRate_ArcherElfo", "300"));
		PCRIT_RATE_ArcherDarkElfo = Integer.parseInt(balance.getProperty("PCritRate_ArcherDarkElfo", "300"));
		
		MAX_MCRIT_RATE = Integer.parseInt(balance.getProperty("MaxMCritRate", "300"));
		MCRIT_RATE_Archmage = Integer.parseInt(balance.getProperty("MCritRate_Archmage", "300"));
		MCRIT_RATE_Soultaker = Integer.parseInt(balance.getProperty("MCritRate_Soultaker", "300"));
		MCRIT_RATE_Mystic_Muse = Integer.parseInt(balance.getProperty("MCritRate_Mystic_Muse", "300"));
		MCRIT_RATE_Storm_Screamer = Integer.parseInt(balance.getProperty("MCritRate_Storm_Screamer", "300"));
		MCRIT_RATE_Dominator = Integer.parseInt(balance.getProperty("MCritRate_Dominator", "300"));
		
		OLY_ENABLE_CUSTOM_CRIT = Boolean.parseBoolean(balance.getProperty("Enable_Oly_CriticalChance", "true"));
		OLY_MAX_PCRIT_RATE = Integer.parseInt(balance.getProperty("OlyMaxPCritRate", "500"));
		OLY_PCRIT_RATE_ArcherHuman = Integer.parseInt(balance.getProperty("OlyPCritRate_ArcherHuman", "300"));
		OLY_PCRIT_RATE_ArcherElfo = Integer.parseInt(balance.getProperty("OlyPCritRate_ArcherElfo", "300"));
		OLY_PCRIT_RATE_ArcherDarkElfo = Integer.parseInt(balance.getProperty("OlyPCritRate_ArcherDarkElfo", "300"));
		
		OLY_MAX_MCRIT_RATE = Integer.parseInt(balance.getProperty("OlyMaxMCritRate", "300"));
		OLY_MCRIT_RATE_Archmage = Integer.parseInt(balance.getProperty("OlyMCritRate_Archmage", "300"));
		OLY_MCRIT_RATE_Soultaker = Integer.parseInt(balance.getProperty("OlyMCritRate_Soultaker", "300"));
		OLY_MCRIT_RATE_Mystic_Muse = Integer.parseInt(balance.getProperty("OlyMCritRate_Mystic_Muse", "300"));
		OLY_MCRIT_RATE_Storm_Screamer = Integer.parseInt(balance.getProperty("OlyMCritRate_Storm_Screamer", "300"));
		OLY_MCRIT_RATE_Dominator = Integer.parseInt(balance.getProperty("OlyMCritRate_Dominator", "300"));
		
		MAX_MATK_SPEED = Integer.parseInt(balance.getProperty("MaxMAtkSpeed", "1999"));
		MAX_PATK_SPEED = Integer.parseInt(balance.getProperty("MaxPAtkSpeed", "1500"));
		MAX_PATK_SPEED_GHOST = Integer.parseInt(balance.getProperty("MaxPAtkSpeedGhost", "1500"));
		MAX_PATK_SPEED_MOONL = Integer.parseInt(balance.getProperty("MaxPAtkSpeedMoonl", "1500"));
		
		BLOW_ATTACK_FRONT = Integer.parseInt(balance.getProperty("BlowAttackFront", "50"));
		BLOW_ATTACK_SIDE = Integer.parseInt(balance.getProperty("BlowAttackSide", "60"));
		BLOW_ATTACK_BEHIND = Integer.parseInt(balance.getProperty("BlowAttackBehind", "70"));
		BACKSTAB_ATTACK_BEHIND = Integer.parseInt(balance.getProperty("BackstabAttackBehind", "70"));
		BACKSTAB_ATTACK_FRONT = Integer.parseInt(balance.getProperty("BackstabAttackFront", "0"));
		BACKSTAB_ATTACK_SIDE = Integer.parseInt(balance.getProperty("BackstabAttackSide", "0"));
		MAGIC_CRITICAL_POWER = Float.parseFloat(balance.getProperty("MagicCriticalPower", "3.0"));
		ANTI_SS_BUG_1 = Integer.parseInt(balance.getProperty("Delay", "2700"));
		ANTI_SS_BUG_2 = Integer.parseInt(balance.getProperty("DelayBow", "1500"));
		ANTI_SS_BUG_3 = Integer.parseInt(balance.getProperty("DelayNextAttack", "470000"));
		
	}
	
		private static final void loadPTFarmConfig()
		{
			final ExProperties BestFarm = initProperties(PARTYFARMEVENT);
			PART_ZONE_MONSTERS_EVENT = BestFarm.getProperty("PartyEventMonster");
			PART_ZONE_MONSTERS_EVENT_ID = new ArrayList<>();
			for (String id : PART_ZONE_MONSTERS_EVENT.split(","))
				PART_ZONE_MONSTERS_EVENT_ID.add(Integer.parseInt(id));
			PARTY_ZONE_REWARDS = parseReward(BestFarm, "PartyZoneReward");
			
			PARTY_FARM_MONSTER_DALAY = Integer.parseInt(BestFarm.getProperty("MonsterDelay", "10"));
			PARTY_FARM_BY_TIME_OF_DAY = Boolean.parseBoolean(BestFarm.getProperty("PartyFarmEventEnabled", "false"));
			START_PARTY = Boolean.parseBoolean(BestFarm.getProperty("StartSpawnPartyFarm", "false"));
			ENABLE_DUALBOX_PARTYFARM = Boolean.parseBoolean(BestFarm.getProperty("RenewalDualBoxPTFarm", "false"));
			EVENT_BEST_FARM_TIME = Integer.parseInt(BestFarm.getProperty("EventBestFarmTime", "1"));
			EVENT_BEST_FARM_INTERVAL_BY_TIME_OF_DAY = BestFarm.getProperty("BestFarmStartTime", "20:00").split(",");
			PARTY_MESSAGE_ENABLED = Boolean.parseBoolean(BestFarm.getProperty("ScreenPartyMessageEnable", "false"));
			PARTY_FARM_MESSAGE_TEXT = BestFarm.getProperty("ScreenPartyFarmMessageText", "Welcome to l2j server!");
			PARTY_FARM_MESSAGE_TIME = Integer.parseInt(BestFarm.getProperty("ScreenPartyFarmMessageTime", "10")) * 1000;
			
			String[] monsterLocs2 = BestFarm.getProperty("MonsterLoc", "").split(";");
			String[] locSplit3 = null;
			
			monsterId = Integer.parseInt(BestFarm.getProperty("MonsterId", "1"));
			
			MONSTER_LOCS_COUNT = monsterLocs2.length;
			MONSTER_LOCS = new int[MONSTER_LOCS_COUNT][3];
			int g;
			for (int e = 0; e < MONSTER_LOCS_COUNT; e++)
			{
				locSplit3 = monsterLocs2[e].split(",");
				for (g = 0; g < 3; g++)
				{
					MONSTER_LOCS[e][g] = Integer.parseInt(locSplit3[g].trim());
				}
			}
			
		}
		
		public static List<RewardHolder> parseReward(Properties propertie, String configName)
		{
			List<RewardHolder> auxReturn = new ArrayList<>();
			
			String aux = propertie.getProperty(configName).trim();
			for (String randomReward : aux.split(";"))
			{
				final String[] infos = randomReward.split(",");
				
				if (infos.length > 3)
					auxReturn.add(new RewardHolder(Integer.valueOf(infos[0]), Integer.valueOf(infos[1]), Integer.valueOf(infos[2]), Integer.valueOf(infos[3])));
				else
					auxReturn.add(new RewardHolder(Integer.valueOf(infos[0]), Integer.valueOf(infos[1]), Integer.valueOf(infos[2])));
			}
			return auxReturn;
		}
	
	//============================================================
	
	public static void loadPCBPointConfig()
	{
		final ExProperties PcBanG = initProperties(Config.PCBANGPOINT_FILE);
		
		PCB_ENABLE = Boolean.parseBoolean(PcBanG.getProperty("PcBangPointEnable", "true"));
		PCB_MIN_LEVEL = Integer.parseInt(PcBanG.getProperty("PcBangPointMinLevel", "20"));
		PCB_POINT_MIN = Integer.parseInt(PcBanG.getProperty("PcBangPointMinCount", "20"));
		PCB_POINT_MAX = Integer.parseInt(PcBanG.getProperty("PcBangPointMaxCount", "1000000"));
		PCB_COIN_ID = Integer.parseInt(PcBanG.getProperty("PCBCoinId", "0"));
		
		
		if(PCB_POINT_MAX < 1)
		{
			PCB_POINT_MAX = Integer.MAX_VALUE;
			
		}
		
		PCB_CHANCE_DUAL_POINT = Integer.parseInt(PcBanG.getProperty("PcBangPointDualChance", "20"));
		PCB_INTERVAL = Integer.parseInt(PcBanG.getProperty("PcBangPointTimeStamp", "900"));
		
	}
	
	
	
	//============================================================
	
	/**
	 * Loads enchant settings.
	 */
	private static final void loadEnchant()
	{
		final ExProperties enchant = initProperties(Config.ENCHANT_FILE);
		
		String[] propertySplit = enchant.getProperty("NormalWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					NORMAL_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("BlessWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					BLESS_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("CrystalWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					CRYSTAL_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("DonatorWeaponEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				System.out.println("invalid config property");
			}
			else
			{
				try
				{
					DONATOR_WEAPON_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						System.out.println("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("NormalArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					NORMAL_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("BlessArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					BLESS_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("CrystalArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					CRYSTAL_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("DonatorArmorEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				System.out.println("invalid config property");
			}
			else
			{
				try
				{
					DONATOR_ARMOR_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						System.out.println("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("NormalJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					NORMAL_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("BlessJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					BLESS_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("CrystalJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				_log.info("invalid config property");
			}
			else
			{
				try
				{
					CRYSTAL_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						_log.info("invalid config property");
					}
				}
			}
		}
		propertySplit = enchant.getProperty("DonatorJewelryEnchantLevel", "").split(";");
		for (String readData : propertySplit)
		{
			String[] writeData = readData.split(",");
			if (writeData.length != 2)
			{
				System.out.println("invalid config property");
			}
			else
			{
				try
				{
					DONATOR_JEWELRY_ENCHANT_LEVEL.put(Integer.valueOf(Integer.parseInt(writeData[0])), Integer.valueOf(Integer.parseInt(writeData[1])));
				}
				catch (NumberFormatException nfe)
				{
					if (DEBUG)
					{
						nfe.printStackTrace();
					}
					if (!readData.equals(""))
					{
						System.out.println("invalid config property");
					}
				}
			}
		}
		ENCHANT_HERO_WEAPON = Boolean.parseBoolean(enchant.getProperty("EnableEnchantHeroWeapons", "False"));
		
		GOLD_WEAPON = Integer.parseInt(enchant.getProperty("IdEnchantDonatorWeapon", "10010"));
		
		GOLD_ARMOR = Integer.parseInt(enchant.getProperty("IdEnchantDonatorArmor", "10011"));
		
		ENCHANT_SAFE_MAX = Integer.parseInt(enchant.getProperty("EnchantSafeMax", "3"));
		
		ENCHANT_SAFE_MAX_FULL = Integer.parseInt(enchant.getProperty("EnchantSafeMaxFull", "4"));
		
		SCROLL_STACKABLE = Boolean.parseBoolean(enchant.getProperty("ScrollStackable", "False"));
		
		ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("EnchantWeaponMax", "25"));
		ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("EnchantArmorMax", "25"));
		ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("EnchantJewelryMax", "25"));
		
		BLESSED_ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("BlessedEnchantWeaponMax", "25"));
		BLESSED_ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("BlessedEnchantArmorMax", "25"));
		BLESSED_ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("BlessedEnchantJewelryMax", "25"));
		
		BREAK_ENCHANT = Integer.valueOf(enchant.getProperty("BreakEnchant", "0")).intValue();
		
		CRYSTAL_ENCHANT_MIN = Integer.parseInt(enchant.getProperty("CrystalEnchantMin", "20"));
		CRYSTAL_ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("CrystalEnchantWeaponMax", "25"));
		CRYSTAL_ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("CrystalEnchantArmorMax", "25"));
		CRYSTAL_ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("CrystalEnchantJewelryMax", "25"));
		
		DONATOR_ENCHANT_MIN = Integer.parseInt(enchant.getProperty("DonatorEnchantMin", "20"));
		DONATOR_ENCHANT_WEAPON_MAX = Integer.parseInt(enchant.getProperty("DonatorEnchantWeaponMax", "25"));
		DONATOR_ENCHANT_ARMOR_MAX = Integer.parseInt(enchant.getProperty("DonatorEnchantArmorMax", "25"));
		DONATOR_ENCHANT_JEWELRY_MAX = Integer.parseInt(enchant.getProperty("DonatorEnchantJewelryMax", "25"));
		DONATOR_DECREASE_ENCHANT = Boolean.valueOf(enchant.getProperty("DonatorDecreaseEnchant", "false")).booleanValue();
		
		ENABLE_ENCHANT_ANNOUNCE = Boolean.parseBoolean(enchant.getProperty("EnableEnchantAnnounce", "False"));
		ENCHANT_ANNOUNCE_LEVEL = enchant.getProperty("EnchantAnnounceLevels", "6,10,16,20");
		LIST_ENCHANT_ANNOUNCE_LEVEL = new ArrayList<>();
		for (String id : ENCHANT_ANNOUNCE_LEVEL.split(","))
		{
			LIST_ENCHANT_ANNOUNCE_LEVEL.add(Integer.valueOf(Integer.parseInt(id)));
		}
		
	}
	
	/**
	 * Loads tournament settings.
	 */
	private static final void loadTour()
	{
		final ExProperties tournament = initProperties(Config.TOUR_FILE);
		TOURNAMENT_EVENT_START = tournament.getProperty("TournamentStartOn", false);
		TOURNAMENT_EVENT_TIME = tournament.getProperty("TournamentAutoEvent", false);
		TOURNAMENT_EVENT_SUMMON = tournament.getProperty("TournamentSummon", false);
		TOURNAMENT_EVENT_ANNOUNCE = tournament.getProperty("TournamenAnnounce", false);
		
		TOURNAMENT_EVENT_INTERVAL_BY_TIME_OF_DAY = tournament.getProperty("TournamentStartTime", "20:00").split(",");
		
		TOURNAMENT_TIME = Integer.parseInt(tournament.getProperty("TournamentEventTime", "1"));
		
		TITLE_COLOR_TEAM1 = tournament.getProperty("TitleColorTeam_1", "FFFFFF");
		TITLE_COLOR_TEAM2 = tournament.getProperty("TitleColorTeam_2", "FFFFFF");
		
		MSG_TEAM1 = tournament.getProperty("TitleTeam_1", "Team [1]");
		MSG_TEAM2 = tournament.getProperty("TitleTeam_2", "Team [2]");
		
		Allow_Same_HWID_On_Tournament = Boolean.parseBoolean(tournament.getProperty("Allow_Same_HWID_On_Tournament", "true"));
		
		ARENA_NPC = Integer.parseInt(tournament.getProperty("NPCRegister", "1"));
		
		NPC_locx = Integer.parseInt(tournament.getProperty("Locx", "1"));
		NPC_locy = Integer.parseInt(tournament.getProperty("Locy", "1"));
		NPC_locz = Integer.parseInt(tournament.getProperty("Locz", "1"));
		NPC_Heading = Integer.parseInt(tournament.getProperty("Heading", "1"));
		
		Tournament_locx = Integer.parseInt(tournament.getProperty("TournamentLocx", "1"));
		Tournament_locy = Integer.parseInt(tournament.getProperty("TournamentLocy", "1"));
		Tournament_locz = Integer.parseInt(tournament.getProperty("TournamentLocz", "1"));
		
		ALLOW_2X2_REGISTER = Boolean.parseBoolean(tournament.getProperty("Allow2x2Register", "true"));
		ALLOW_4X4_REGISTER = Boolean.parseBoolean(tournament.getProperty("Allow4x4Register", "true"));
		ALLOW_9X9_REGISTER = Boolean.parseBoolean(tournament.getProperty("Allow9x9Register", "true"));
		
		ALLOW_4X4_LOSTBUFF = Boolean.parseBoolean(tournament.getProperty("Allow4x4LostBuff", "false"));
		
		ARENA_MESSAGE_ENABLED = Boolean.parseBoolean(tournament.getProperty("ScreenArenaMessageEnable", "false"));
		ARENA_MESSAGE_TEXT = tournament.getProperty("ScreenArenaMessageText", "Welcome to L2J server!");
		ARENA_MESSAGE_TIME = Integer.parseInt(tournament.getProperty("ScreenArenaMessageTime", "10")) * 1000;
		
		String[] arenaLocs = tournament.getProperty("ArenasLoc", "").split(";");
		String[] locSplit = null;
		ARENA_EVENT_COUNT = arenaLocs.length;
		ARENA_EVENT_LOCS = new int[ARENA_EVENT_COUNT][3];
		for (int i = 0; i < ARENA_EVENT_COUNT; i++)
		{
			locSplit = arenaLocs[i].split(",");
			for (int j = 0; j < 3; j++)
			{
				ARENA_EVENT_LOCS[i][j] = Integer.parseInt(locSplit[j].trim());
			}
		}
		String[] arenaLocs4x4 = tournament.getProperty("Arenas4x4Loc", "").split(";");
		String[] locSplit4x4 = null;
		ARENA_EVENT_COUNT_4X4 = arenaLocs4x4.length;
		ARENA_EVENT_LOCS_4X4 = new int[ARENA_EVENT_COUNT_4X4][3];
		for (int i = 0; i < ARENA_EVENT_COUNT_4X4; i++)
		{
			locSplit4x4 = arenaLocs4x4[i].split(",");
			for (int j = 0; j < 3; j++)
			{
				ARENA_EVENT_LOCS_4X4[i][j] = Integer.parseInt(locSplit4x4[j].trim());
			}
		}
		String[] arenaLocs9x9 = tournament.getProperty("Arenas9x9Loc", "").split(";");
		String[] locSplit8x8 = null;
		ARENA_EVENT_COUNT_9X9 = arenaLocs9x9.length;
		ARENA_EVENT_LOCS_9X9 = new int[ARENA_EVENT_COUNT_9X9][3];
		int j;
		for (int i = 0; i < ARENA_EVENT_COUNT_9X9; i++)
		{
			locSplit8x8 = arenaLocs9x9[i].split(",");
			for (j = 0; j < 3; j++)
			{
				ARENA_EVENT_LOCS_9X9[i][j] = Integer.parseInt(locSplit8x8[j].trim());
			}
		}
		duelist_COUNT_4X4 = tournament.getProperty("duelist_amount_4x4", 1);
		dreadnought_COUNT_4X4 = tournament.getProperty("dreadnought_amount_4x4", 1);
		tanker_COUNT_4X4 = tournament.getProperty("tanker_amount_4x4", 1);
		dagger_COUNT_4X4 = tournament.getProperty("dagger_amount_4x4", 1);
		archer_COUNT_4X4 = tournament.getProperty("archer_amount_4x4", 1);
		bs_COUNT_4X4 = tournament.getProperty("bs_amount_4x4", 1);
		archmage_COUNT_4X4 = tournament.getProperty("archmage_amount_4x4", 1);
		soultaker_COUNT_4X4 = tournament.getProperty("soultaker_amount_4x4", 1);
		mysticMuse_COUNT_4X4 = tournament.getProperty("mysticMuse_amount_4x4", 1);
		stormScreamer_COUNT_4X4 = tournament.getProperty("stormScreamer_amount_4x4", 1);
		titan_COUNT_4X4 = tournament.getProperty("titan_amount_4x4", 1);
		dominator_COUNT_4X4 = tournament.getProperty("dominator_amount_4x4", 1);
		doomcryer_COUNT_4X4 = tournament.getProperty("doomcryer_amount_4x4", 1);
		
		duelist_COUNT_9X9 = tournament.getProperty("duelist_amount_9x9", 1);
		dreadnought_COUNT_9X9 = tournament.getProperty("dreadnought_amount_9x9", 1);
		tanker_COUNT_9X9 = tournament.getProperty("tanker_amount_9x9", 1);
		dagger_COUNT_9X9 = tournament.getProperty("dagger_amount_9x9", 1);
		archer_COUNT_9X9 = tournament.getProperty("archer_amount_9x9", 1);
		bs_COUNT_9X9 = tournament.getProperty("bs_amount_9x9", 1);
		archmage_COUNT_9X9 = tournament.getProperty("archmage_amount_9x9", 1);
		soultaker_COUNT_9X9 = tournament.getProperty("soultaker_amount_9x9", 1);
		mysticMuse_COUNT_9X9 = tournament.getProperty("mysticMuse_amount_9x9", 1);
		stormScreamer_COUNT_9X9 = tournament.getProperty("stormScreamer_amount_9x9", 1);
		titan_COUNT_9X9 = tournament.getProperty("titan_amount_9x9", 1);
		grandKhauatari_COUNT_9X9 = tournament.getProperty("grandKhauatari_amount_9x9", 1);
		dominator_COUNT_9X9 = tournament.getProperty("dominator_amount_9x9", 1);
		doomcryer_COUNT_9X9 = tournament.getProperty("doomcryer_amount_9x9", 1);
		
		ARENA_PVP_AMOUNT = tournament.getProperty("ArenaPvpJoin", 10);
		ARENA_REWARD_ID = tournament.getProperty("ArenaRewardId", 57);
		ARENA_WIN_REWARD_COUNT = tournament.getProperty("ArenaWinRewardCount", 1);
		ARENA_LOST_REWARD_COUNT = tournament.getProperty("ArenaLostRewardCount", 1);
		
		ARENA_WIN_REWARD_COUNT_4X4 = tournament.getProperty("ArenaWinRewardCount4x4", 1);
		ARENA_LOST_REWARD_COUNT_4X4 = tournament.getProperty("ArenaLostRewardCount4x4", 1);
		
		ARENA_WIN_REWARD_COUNT_9X9 = tournament.getProperty("ArenaWinRewardCount9x9", 1);
		ARENA_LOST_REWARD_COUNT_9X9 = tournament.getProperty("ArenaLostRewardCount9x9", 1);
		
		ARENA_CHECK_INTERVAL = tournament.getProperty("ArenaBattleCheckInterval", 15) * 1000;
		ARENA_CALL_INTERVAL = tournament.getProperty("ArenaBattleCallInterval", 60);
		
		ARENA_WAIT_INTERVAL = tournament.getProperty("ArenaBattleWaitInterval", 20);
		ARENA_WAIT_INTERVAL_4X4 = tournament.getProperty("ArenaBattleWaitInterval4x4", 45);
		ARENA_WAIT_INTERVAL_9X9 = tournament.getProperty("ArenaBattleWaitInterval9x9", 45);
		
		TOURNAMENT_ID_RESTRICT = tournament.getProperty("ItemsRestriction");
		
		TOURNAMENT_LISTID_RESTRICT = new ArrayList<>();
		for (String id : TOURNAMENT_ID_RESTRICT.split(","))
		{
			TOURNAMENT_LISTID_RESTRICT.add(Integer.valueOf(Integer.parseInt(id)));
		}
		ARENA_SKILL_PROTECT = Boolean.parseBoolean(tournament.getProperty("ArenaSkillProtect", "false"));
		for (String id : tournament.getProperty("ArenaDisableSkillList", "0").split(","))
		{
			ARENA_SKILL_LIST.add(Integer.valueOf(Integer.parseInt(id)));
		}
		for (String id : tournament.getProperty("DisableSkillList", "0").split(","))
		{
			ARENA_DISABLE_SKILL_LIST_PERM.add(Integer.valueOf(Integer.parseInt(id)));
		}
		for (String id : tournament.getProperty("ArenaDisableSkillList_noStart", "0").split(","))
		{
			ARENA_DISABLE_SKILL_LIST.add(Integer.valueOf(Integer.parseInt(id)));
		}
		for (String id : tournament.getProperty("ArenaStopSkillList", "0").split(","))
		{
			ARENA_STOP_SKILL_LIST.add(Integer.valueOf(Integer.parseInt(id)));
		}
	}
	
	/**
	 * Loads phantom settings.
	 */
	private static final void loadPhantom()
	{
		final ExProperties Phanton = initProperties(Config.PHANTOM_FILE);
		
		// ------------------------------------
		ALLOW_PHANTOM_PLAYERS = Boolean.parseBoolean(Phanton.getProperty("AllowPhantom", "False"));
		ALLOW_PHANTOM_STORE = Boolean.parseBoolean(Phanton.getProperty("AllowPhantomStore", "False"));
		
		PHANTOM_PRIVATE_STORE = Boolean.parseBoolean(Phanton.getProperty("PhantomPrivateStore", "True"));
		PHANTOM_PRIVATE_BUY_TITLE_MSG = Phanton.getProperty("PhantomPrivate_Buy_Title", "Lineage 2");
		PHANTOM_PRIVATE_BUY_TITLE = new ArrayList<>();
		for (final String type2 : PHANTOM_PRIVATE_BUY_TITLE_MSG.split(","))
		{
			PHANTOM_PRIVATE_BUY_TITLE.add(type2);
		}
		PHANTOM_PRIVATE_BUY_TITLE_MSG = null;
		PHANTOM_PRIVATE_BUY_CHANCE = Phanton.getProperty("PhantomPrivate_Buy_Chance", 50);
		PHANTOM_PRIVATE_BUY = Phanton.getProperty("PhantomPrivate_Buy", "");
		LIST_PRIVATE_BUY = new ArrayList<>();
		for (final String itemId : PHANTOM_PRIVATE_BUY.split(","))
		{
			Config.LIST_PRIVATE_BUY.add(Integer.parseInt(itemId));
		}
		PHANTOM_PRIVATE_SELL_TITLE_MSG = Phanton.getProperty("PhantomPrivate_Sell_Title", "Lineage 2");
		PHANTOM_PRIVATE_SELL_TITLE = new ArrayList<>();
		for (final String type2 : PHANTOM_PRIVATE_SELL_TITLE_MSG.split(","))
		{
			Config.PHANTOM_PRIVATE_SELL_TITLE.add(type2);
		}
		PHANTOM_PRIVATE_BUY_TITLE_MSG = null;
		PHANTOM_PRIVATE_SELL_CHANCE = Phanton.getProperty("PhantomPrivate_Sell_Chance", 50);
		PHANTOM_PRIVATE_SELL = Phanton.getProperty("PhantomPrivate_Sell", "");
		LIST_PRIVATE_SELL = new ArrayList<>();
		for (final String itemId : PHANTOM_PRIVATE_SELL.split(","))
		{
			LIST_PRIVATE_SELL.add(Integer.parseInt(itemId));
		}
		
		ALLOW_PHANTOM_FACE = Boolean.parseBoolean(Phanton.getProperty("PhantomFace", "True"));
		PHANTOM_FACE = Phanton.getProperty("PhantomFaceList", "");
		LIST_PHANTOM_FACE = new ArrayList<>();
		for (String itemId : PHANTOM_FACE.trim().split(","))
		{
			LIST_PHANTOM_FACE.add(Integer.valueOf(Integer.parseInt(itemId)));
		}
		
		ALLOW_PHANTOM_HAIR = Boolean.parseBoolean(Phanton.getProperty("PhantomHair", "True"));
		
		PHANTOM_HAIR = Phanton.getProperty("PhantomHairList", "0");
		LIST_PHANTOM_HAIR = new ArrayList<>();
		for (String itemId : PHANTOM_HAIR.trim().split(","))
		{
			LIST_PHANTOM_HAIR.add(Integer.valueOf(Integer.parseInt(itemId)));
		}
		
		PHANTOM_CHANCE_SIT = Integer.parseInt(Phanton.getProperty("Phantom_Chance_sitDown", "10"));
		PHANTOM_PLAYERS_SOULSHOT_ANIM = Boolean.parseBoolean(Phanton.getProperty("PhantomSoulshotAnimation", "True"));
		
		PHANTOM_PLAYERS_WALK = Phanton.getProperty("PhantomWalkChance", 50);
		
		PHANTOM_PLAYERS_ACC_1 = Phanton.getProperty("PhantomTownAccount", "l2jmega");
		
		PHANTOM_PLAYERS_ACC_2 = Phanton.getProperty("PhantomPrivateStoreAccount", "l2jmega2");
		
		PHANTOM_DELAY_SPAWN_FIRST = (int) TimeUnit.SECONDS.toMillis(Integer.parseInt(Phanton.getProperty("FirstDelaySpawn", "1")));
		DISCONNETC_DELAY = TimeUnit.MINUTES.toMillis(Integer.parseInt(Phanton.getProperty("DisconnectDelay", "15")));
		
		PHANTOM_PLAYERS_ARGUMENT_ANIM = Boolean.parseBoolean(Phanton.getProperty("PhantomArgumentAnimation", "True"));
		
		String[] arrayOfString1 = Phanton.getProperty("FakeEnchant", "0,14").split(",");
		PHANTOM_PLAYERS_ENCHANT_MIN = Integer.parseInt(arrayOfString1[0]);
		PHANTOM_PLAYERS_ENCHANT_MAX = Integer.parseInt(arrayOfString1[1]);
		
		PHANTOM_PLAYERS_TITLE_MSG = Phanton.getProperty("FakeTitleList", "Lineage 2");
		PHANTOM_PLAYERS_TITLE = new ArrayList<>();
		for (String type : PHANTOM_PLAYERS_TITLE_MSG.trim().split(","))
		{
			
			PHANTOM_PLAYERS_TITLE.add(type);
		}
		PHANTOM_PLAYERS_TITLE_MSG = null;
		
		PHANTOM_TITLE_PHANTOM = Boolean.parseBoolean(Phanton.getProperty("PhantomTitle", "false"));
		
		PHANTOM_TITLE_CONFIG = Boolean.parseBoolean(Phanton.getProperty("FakeTitleFixo", "false"));
		
		PHANTOM_TITLE_MSG = Phanton.getProperty("FakeTitle", "Lineage 2");
		PHANTOM_TITLE = new ArrayList<>();
		for (String type : PHANTOM_TITLE_MSG.trim().split(","))
		{
			
			PHANTOM_TITLE.add(type);
		}
		PHANTOM_TITLE_MSG = null;
		
		PHANTOM_CHANCE_MALARIA = Phanton.getProperty("Phantom_Chance_MalariaEffect", 60);
		PHANTOM_CHANCE_TITLE = Phanton.getProperty("Phantom_Chance_Title", 60);
	}
	
	private static final void loadManager()
	{
		// Bank config
		final ExProperties managers = initProperties(MANAGER_FILE);
		
		VOTE_SYSTEM_ENABLED = managers.getProperty("EnableVoteRewardSystem", true);
		VOTES_FOR_REWARD_TOP = managers.getProperty("VotesRequiredForRewardTop", 100);
		VOTES_SYSTEM_INITIAL_DELAY_TOP = managers.getProperty("VotesSystemInitialDelayTop", 18);
		VOTES_FOR_REWARD_HOP = managers.getProperty("VotesRequiredForRewardHop", 100);
		VOTES_SYSTEM_INITIAL_DELAY_HOP = managers.getProperty("VotesSystemInitialDelayHop", 18);
		VOTES_FOR_REWARD_NET = managers.getProperty("VotesRequiredForRewardNet", 100);
		VOTES_SYSTEM_INITIAL_DELAY_NET = managers.getProperty("VotesSystemInitialDelayNet", 18);
		VOTE_BOXES_ALLOWED = managers.getProperty("AutoVoteBoxesAllowed", 18);
		String REWARD_AUTO = managers.getProperty("AutoVoteSystemReward", "57,100000000;");
		String[] reward_splitted_1 = REWARD_AUTO.split(";");
		for (String i : reward_splitted_1)
		{
			String[] reward_splitted_2 = i.split(",");
			VOTES_REWARDS_LIST_AUTOVOTE.put(Integer.parseInt(reward_splitted_2[0]), Integer.parseInt(reward_splitted_2[1]));
		}
		VOTE_BOXES_ALLOWED = managers.getProperty("AutoVoteBoxesAllowed", 1);
		VOTES_SITE_TOPZONE_LINK_TOP = managers.getProperty("VotesSiteTopZoneLinkTop", "");
		VOTES_SITE_HOPZONE_LINK_HOP = managers.getProperty("VotesSiteHopZoneLinkHop", "");
		VOTES_SITE_L2NETWORK_LINK_NET = managers.getProperty("VotesSiteNetWorkLinkNet", "");
		SERVER_WEB_SITE = managers.getProperty("ServerWebSite", "");
		
		VOTE_MANAGER_ENABLED = managers.getProperty("EnableVoteManager", false);
		VOTES_SITE_HOPZONE_URL = managers.getProperty("HopzoneUrl", "");
		VOTES_SITE_TOPZONE_URL = managers.getProperty("TopzoneUrl", "");
		VOTES_SITE_L2NETWORK_URL = managers.getProperty("NetworkUrl", "");
		VOTE_REWARD_ID = managers.getProperty("VoteRewardId", 57);
		VOTE_REWARD_ID_COUNT = managers.getProperty("VoteRewardCount", 20);
		TIME_TO_VOTE = managers.getProperty("TimeToVote", 60);
		ALLOW_VOTE_REMINDER = managers.getProperty("VoteReminderEnable", true);
		VOTE_REMINDER_MINUTES = managers.getProperty("MinutesDelay", 1);
		
		/** Clan Manager */
		CLAN_ITEM_ID = managers.getProperty("ClanItemID", 57);
		CLAN_COUNT = managers.getProperty("ClanAmount", 100);
		
	}
	
	private static final void loadNewbies()
	{
		final ExProperties newbie = initProperties(NEWBIE_FILE);
		NEWBIE_DIST = Integer.parseInt(newbie.getProperty("Dist", "80"));
		NEWBIE_LADO = Integer.parseInt(newbie.getProperty("Yaw", "80"));
		NEWBIE_ALTURA = Integer.parseInt(newbie.getProperty("Pitch", "80"));
		
		ENABLE_STARTUP = newbie.getProperty("StartupEnabled", true);
		
		String[] TelepropertySplit = newbie.getProperty("TeleToLocation", "0,0,0").split(",");
		if (TelepropertySplit.length < 3)
		{
			System.out.println("NewbiesSystemEngine[Config.load()]: invalid config property -> TeleToLocation");
		}
		else
		{
			TELE_TO_LOCATION[0] = Integer.parseInt(TelepropertySplit[0]);
			TELE_TO_LOCATION[1] = Integer.parseInt(TelepropertySplit[1]);
			TELE_TO_LOCATION[2] = Integer.parseInt(TelepropertySplit[2]);
		}
		NEWBIE_ITEMS_ENCHANT = Integer.parseInt(newbie.getProperty("EnchantItens", "4"));
	}
	
	private static final void loadCommandList()
	{
		final ExProperties commands = initProperties(COMMAND_LIST);
		
		ALT_GAME_VIEWNPC = commands.getProperty("AltGameViewNpc", false);
		STATUS_CMD = Boolean.parseBoolean(commands.getProperty("AllowStatus", "true"));
		ENABLE_ONLINE = Boolean.parseBoolean(commands.getProperty("OnlineCmd", "true"));
		PLAYERS_ONLINE_TRICK = Integer.parseInt(commands.getProperty("OnlinePlayerAdd", "0"));
		EPIC_INFO = Boolean.parseBoolean(commands.getProperty("EpicInfoCmd", "true"));
		MENU_PANEL = Boolean.parseBoolean(commands.getProperty("MenuPanelCmd", "true"));
		SECURE_CMD = Boolean.parseBoolean(commands.getProperty("SecureCmd", "true"));
		TVT_CMD = Boolean.parseBoolean(commands.getProperty("TvTCmd", "true"));
		ESPECIAL_VIP_LOGIN = Boolean.parseBoolean(commands.getProperty("EspecialVipLogin", "False"));
		ALLOW_BAKING_SYSTEM = Boolean.parseBoolean(commands.getProperty("EnableCommandBaking", "False"));
		BANKING_SYSTEM_GOLDBARS = Integer.parseInt(commands.getProperty("BankingGoldbarCount", "1"));
		BANKING_SYSTEM_ADENA = Integer.parseInt(commands.getProperty("BankingAdenaCount", "500000000"));
	}
	
	/**
	 * Loads clan and clan hall settings.
	 */
	private static final void loadClans()
	{
		final ExProperties clans = initProperties(CLANS_FILE);
		ALT_CLAN_JOIN_DAYS = clans.getProperty("DaysBeforeJoinAClan", 5);
		ALT_CLAN_CREATE_DAYS = clans.getProperty("DaysBeforeCreateAClan", 10);
		ALT_MAX_NUM_OF_CLANS_IN_ALLY = clans.getProperty("AltMaxNumOfClansInAlly", 3);
		ALT_CLAN_MEMBERS_FOR_WAR = clans.getProperty("AltClanMembersForWar", 15);
		ALT_CLAN_WAR_PENALTY_WHEN_ENDED = clans.getProperty("AltClanWarPenaltyWhenEnded", 5);
		ALT_CLAN_DISSOLVE_DAYS = clans.getProperty("DaysToPassToDissolveAClan", 7);
		ALT_ALLY_JOIN_DAYS_WHEN_LEAVED = clans.getProperty("DaysBeforeJoinAllyWhenLeaved", 1);
		ALT_ALLY_JOIN_DAYS_WHEN_DISMISSED = clans.getProperty("DaysBeforeJoinAllyWhenDismissed", 1);
		ALT_ACCEPT_CLAN_DAYS_WHEN_DISMISSED = clans.getProperty("DaysBeforeAcceptNewClanWhenDismissed", 1);
		ALT_CREATE_ALLY_DAYS_WHEN_DISSOLVED = clans.getProperty("DaysBeforeCreateNewAllyWhenDissolved", 10);
		ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH = clans.getProperty("AltMembersCanWithdrawFromClanWH", false);
		REMOVE_CASTLE_CIRCLETS = clans.getProperty("RemoveCastleCirclets", true);
		
		ALT_MANOR_REFRESH_TIME = clans.getProperty("AltManorRefreshTime", 20);
		ALT_MANOR_REFRESH_MIN = clans.getProperty("AltManorRefreshMin", 0);
		ALT_MANOR_APPROVE_TIME = clans.getProperty("AltManorApproveTime", 6);
		ALT_MANOR_APPROVE_MIN = clans.getProperty("AltManorApproveMin", 0);
		ALT_MANOR_MAINTENANCE_MIN = clans.getProperty("AltManorMaintenanceMin", 6);
		ALT_MANOR_SAVE_PERIOD_RATE = clans.getProperty("AltManorSavePeriodRate", 2) * 3600000;
		
		CH_TELE_FEE_RATIO = clans.getProperty("ClanHallTeleportFunctionFeeRatio", 86400000);
		CH_TELE1_FEE = clans.getProperty("ClanHallTeleportFunctionFeeLvl1", 7000);
		CH_TELE2_FEE = clans.getProperty("ClanHallTeleportFunctionFeeLvl2", 14000);
		CH_SUPPORT_FEE_RATIO = clans.getProperty("ClanHallSupportFunctionFeeRatio", 86400000);
		CH_SUPPORT1_FEE = clans.getProperty("ClanHallSupportFeeLvl1", 17500);
		CH_SUPPORT2_FEE = clans.getProperty("ClanHallSupportFeeLvl2", 35000);
		CH_SUPPORT3_FEE = clans.getProperty("ClanHallSupportFeeLvl3", 49000);
		CH_SUPPORT4_FEE = clans.getProperty("ClanHallSupportFeeLvl4", 77000);
		CH_SUPPORT5_FEE = clans.getProperty("ClanHallSupportFeeLvl5", 147000);
		CH_SUPPORT6_FEE = clans.getProperty("ClanHallSupportFeeLvl6", 252000);
		CH_SUPPORT7_FEE = clans.getProperty("ClanHallSupportFeeLvl7", 259000);
		CH_SUPPORT8_FEE = clans.getProperty("ClanHallSupportFeeLvl8", 364000);
		CH_MPREG_FEE_RATIO = clans.getProperty("ClanHallMpRegenerationFunctionFeeRatio", 86400000);
		CH_MPREG1_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl1", 14000);
		CH_MPREG2_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl2", 26250);
		CH_MPREG3_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl3", 45500);
		CH_MPREG4_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl4", 96250);
		CH_MPREG5_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl5", 140000);
		CH_HPREG_FEE_RATIO = clans.getProperty("ClanHallHpRegenerationFunctionFeeRatio", 86400000);
		CH_HPREG1_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl1", 4900);
		CH_HPREG2_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl2", 5600);
		CH_HPREG3_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl3", 7000);
		CH_HPREG4_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl4", 8166);
		CH_HPREG5_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl5", 10500);
		CH_HPREG6_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl6", 12250);
		CH_HPREG7_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl7", 14000);
		CH_HPREG8_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl8", 15750);
		CH_HPREG9_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl9", 17500);
		CH_HPREG10_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl10", 22750);
		CH_HPREG11_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl11", 26250);
		CH_HPREG12_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl12", 29750);
		CH_HPREG13_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl13", 36166);
		CH_EXPREG_FEE_RATIO = clans.getProperty("ClanHallExpRegenerationFunctionFeeRatio", 86400000);
		CH_EXPREG1_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl1", 21000);
		CH_EXPREG2_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl2", 42000);
		CH_EXPREG3_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl3", 63000);
		CH_EXPREG4_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl4", 105000);
		CH_EXPREG5_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl5", 147000);
		CH_EXPREG6_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl6", 163331);
		CH_EXPREG7_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl7", 210000);
		CH_ITEM_FEE_RATIO = clans.getProperty("ClanHallItemCreationFunctionFeeRatio", 86400000);
		CH_ITEM1_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl1", 210000);
		CH_ITEM2_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl2", 490000);
		CH_ITEM3_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl3", 980000);
		CH_CURTAIN_FEE_RATIO = clans.getProperty("ClanHallCurtainFunctionFeeRatio", 86400000);
		CH_CURTAIN1_FEE = clans.getProperty("ClanHallCurtainFunctionFeeLvl1", 2002);
		CH_CURTAIN2_FEE = clans.getProperty("ClanHallCurtainFunctionFeeLvl2", 2625);
		CH_FRONT_FEE_RATIO = clans.getProperty("ClanHallFrontPlatformFunctionFeeRatio", 86400000);
		CH_FRONT1_FEE = clans.getProperty("ClanHallFrontPlatformFunctionFeeLvl1", 3031);
		CH_FRONT2_FEE = clans.getProperty("ClanHallFrontPlatformFunctionFeeLvl2", 9331);
	}
	
	/**
	 * Loads event settings.<br>
	 * Such as olympiad, seven signs festival, four sepulchures, dimensional rift, weddings, lottery, fishing championship.
	 */
	private static final void loadEvents()
	{
		final ExProperties events = initProperties(EVENTS_FILE);
		ALT_OLY_START_TIME = events.getProperty("AltOlyStartTime", 18);
		ALT_OLY_MIN = events.getProperty("AltOlyMin", 0);
		ALT_OLY_CPERIOD = events.getProperty("AltOlyCPeriod", 21600000);
		ALT_OLY_BATTLE = events.getProperty("AltOlyBattle", 180000);
		ALT_OLY_WPERIOD = events.getProperty("AltOlyWPeriod", 604800000);
		ALT_OLY_VPERIOD = events.getProperty("AltOlyVPeriod", 86400000);
		ALT_OLY_WAIT_TIME = events.getProperty("AltOlyWaitTime", 30);
		ALT_OLY_WAIT_BATTLE = events.getProperty("AltOlyWaitBattle", 60);
		ALT_OLY_WAIT_END = events.getProperty("AltOlyWaitEnd", 40);
		ALT_OLY_START_POINTS = events.getProperty("AltOlyStartPoints", 18);
		ALT_OLY_WEEKLY_POINTS = events.getProperty("AltOlyWeeklyPoints", 3);
		ALT_OLY_MIN_MATCHES = events.getProperty("AltOlyMinMatchesToBeClassed", 5);
		ALT_OLY_CLASSED = events.getProperty("AltOlyClassedParticipants", 5);
		ALT_OLY_NONCLASSED = events.getProperty("AltOlyNonClassedParticipants", 9);
		ALT_OLY_CLASSED_REWARD = parseItemsList(events.getProperty("AltOlyClassedReward", "6651,50"));
		ALT_OLY_NONCLASSED_REWARD = parseItemsList(events.getProperty("AltOlyNonClassedReward", "6651,30"));
		ALT_OLY_GP_PER_POINT = events.getProperty("AltOlyGPPerPoint", 1000);
		ALT_OLY_HERO_POINTS = events.getProperty("AltOlyHeroPoints", 300);
		ALT_OLY_RANK1_POINTS = events.getProperty("AltOlyRank1Points", 100);
		ALT_OLY_RANK2_POINTS = events.getProperty("AltOlyRank2Points", 75);
		ALT_OLY_RANK3_POINTS = events.getProperty("AltOlyRank3Points", 55);
		ALT_OLY_RANK4_POINTS = events.getProperty("AltOlyRank4Points", 40);
		ALT_OLY_RANK5_POINTS = events.getProperty("AltOlyRank5Points", 30);
		ALT_OLY_MAX_POINTS = events.getProperty("AltOlyMaxPoints", 10);
		ALT_OLY_DIVIDER_CLASSED = events.getProperty("AltOlyDividerClassed", 3);
		ALT_OLY_DIVIDER_NON_CLASSED = events.getProperty("AltOlyDividerNonClassed", 3);
		ALT_OLY_ANNOUNCE_GAMES = events.getProperty("AltOlyAnnounceGames", true);
		ALT_USE_CUSTOM_PERIOD = events.getProperty("AltUseCustomPeriod", true);
		ALT_CYSTOM_PERIOD = events.getProperty("AltCustomPeriod", new int[]
			{
				7,
				0,
				12,
				0,
				0
			});
		ALT_OLY_RECHARGE_SKILLS = events.getProperty("AltRechargeSkills", false);
		
		ALT_OLY_ALLOW_DUALBOX_OLY = events.getProperty("AltOlyAllowSameIPInOly", false);
		LIST_OLY_RESTRICTED_ITEMS = new ArrayList<>();
		for (String items : events.getProperty("AltOlyRestrictedItems", "").split(","))
			LIST_OLY_RESTRICTED_ITEMS.add(Integer.parseInt(items));
		
		ALT_OLY_GRADE_RESTRICTION_ITEMS = events.getProperty("AltOlyGradeRestrictionItems", false);
		ALT_OLY_LIST_OF_GRADE_RESTRICTION_ITEMS = new ArrayList<>();
		for (String items : events.getProperty("AltOlyGradeRestrictionItemsList", "").split(","))
			ALT_OLY_LIST_OF_GRADE_RESTRICTION_ITEMS.add(Integer.parseInt(items));
		ALT_OLY_RESTORE_IN_SECOND_COUNTDOWN = events.getProperty("AltOlyRestoreInSecondCountdown", 20);
		ALT_OLY_SHOW_MONTHLY_WINNERS = events.getProperty("AltShowMonthlyWinners", false);
		ALT_OLY_ALLOW_APPLY_AUGMENT_ON_CHAR = events.getProperty("AltOlyAllowApplyAugmentOnChar", false);
		
		ALT_OLY_SKILLS_ALLOW_APPLY_AUGMENT = new ArrayList<>();
		for (String skill : events.getProperty("AltOlySkillsAllowApplyAugment", "").split(","))
			ALT_OLY_SKILLS_ALLOW_APPLY_AUGMENT.add(Integer.parseInt(skill));
		
		PVP_LIMITS_OLY_ENABLE = events.getProperty("PvPKillsOlympiadLimits", true);
		PVP_LIMITS = events.getProperty("PvPKills", 10);
		
		ALT_GAME_CASTLE_DAWN = events.getProperty("AltCastleForDawn", true);
		ALT_GAME_CASTLE_DUSK = events.getProperty("AltCastleForDusk", true);
		ALT_FESTIVAL_MIN_PLAYER = MathUtil.limit(events.getProperty("AltFestivalMinPlayer", 5), 2, 9);
		ALT_MAXIMUM_PLAYER_CONTRIB = events.getProperty("AltMaxPlayerContrib", 1000000);
		ALT_FESTIVAL_MANAGER_START = events.getProperty("AltFestivalManagerStart", 120000);
		ALT_FESTIVAL_LENGTH = events.getProperty("AltFestivalLength", 1080000);
		ALT_FESTIVAL_CYCLE_LENGTH = events.getProperty("AltFestivalCycleLength", 2280000);
		ALT_FESTIVAL_FIRST_SPAWN = events.getProperty("AltFestivalFirstSpawn", 120000);
		ALT_FESTIVAL_FIRST_SWARM = events.getProperty("AltFestivalFirstSwarm", 300000);
		ALT_FESTIVAL_SECOND_SPAWN = events.getProperty("AltFestivalSecondSpawn", 540000);
		ALT_FESTIVAL_SECOND_SWARM = events.getProperty("AltFestivalSecondSwarm", 720000);
		ALT_FESTIVAL_CHEST_SPAWN = events.getProperty("AltFestivalChestSpawn", 900000);
		
		FS_TIME_ATTACK = events.getProperty("TimeOfAttack", 50);
		FS_TIME_ENTRY = events.getProperty("TimeOfEntry", 3);
		FS_TIME_WARMUP = events.getProperty("TimeOfWarmUp", 2);
		FS_PARTY_MEMBER_COUNT = MathUtil.limit(events.getProperty("NumberOfNecessaryPartyMembers", 4), 2, 9);
		
		RIFT_MIN_PARTY_SIZE = events.getProperty("RiftMinPartySize", 2);
		RIFT_MAX_JUMPS = events.getProperty("MaxRiftJumps", 4);
		RIFT_SPAWN_DELAY = events.getProperty("RiftSpawnDelay", 10000);
		RIFT_AUTO_JUMPS_TIME_MIN = events.getProperty("AutoJumpsDelayMin", 480);
		RIFT_AUTO_JUMPS_TIME_MAX = events.getProperty("AutoJumpsDelayMax", 600);
		RIFT_ENTER_COST_RECRUIT = events.getProperty("RecruitCost", 18);
		RIFT_ENTER_COST_SOLDIER = events.getProperty("SoldierCost", 21);
		RIFT_ENTER_COST_OFFICER = events.getProperty("OfficerCost", 24);
		RIFT_ENTER_COST_CAPTAIN = events.getProperty("CaptainCost", 27);
		RIFT_ENTER_COST_COMMANDER = events.getProperty("CommanderCost", 30);
		RIFT_ENTER_COST_HERO = events.getProperty("HeroCost", 33);
		RIFT_BOSS_ROOM_TIME_MUTIPLY = events.getProperty("BossRoomTimeMultiply", 1.);
		
		ALLOW_WEDDING = events.getProperty("AllowWedding", false);
		WEDDING_PRICE = events.getProperty("WeddingPrice", 1000000);
		WEDDING_SAMESEX = events.getProperty("WeddingAllowSameSex", false);
		WEDDING_FORMALWEAR = events.getProperty("WeddingFormalWear", true);
		
		ALT_LOTTERY_PRIZE = events.getProperty("AltLotteryPrize", 50000);
		ALT_LOTTERY_TICKET_PRICE = events.getProperty("AltLotteryTicketPrice", 2000);
		ALT_LOTTERY_5_NUMBER_RATE = events.getProperty("AltLottery5NumberRate", 0.6);
		ALT_LOTTERY_4_NUMBER_RATE = events.getProperty("AltLottery4NumberRate", 0.2);
		ALT_LOTTERY_3_NUMBER_RATE = events.getProperty("AltLottery3NumberRate", 0.2);
		ALT_LOTTERY_2_AND_1_NUMBER_PRIZE = events.getProperty("AltLottery2and1NumberPrize", 200);
		
		ALT_FISH_CHAMPIONSHIP_ENABLED = events.getProperty("AltFishChampionshipEnabled", true);
		ALT_FISH_CHAMPIONSHIP_REWARD_ITEM = events.getProperty("AltFishChampionshipRewardItemId", 57);
		ALT_FISH_CHAMPIONSHIP_REWARD_1 = events.getProperty("AltFishChampionshipReward1", 800000);
		ALT_FISH_CHAMPIONSHIP_REWARD_2 = events.getProperty("AltFishChampionshipReward2", 500000);
		ALT_FISH_CHAMPIONSHIP_REWARD_3 = events.getProperty("AltFishChampionshipReward3", 300000);
		ALT_FISH_CHAMPIONSHIP_REWARD_4 = events.getProperty("AltFishChampionshipReward4", 200000);
		ALT_FISH_CHAMPIONSHIP_REWARD_5 = events.getProperty("AltFishChampionshipReward5", 100000);
	}
	
	/**
	 * Loads geoengine settings.
	 */
	private static final void loadGeoengine()
	{
		final ExProperties geoengine = initProperties(GEOENGINE_FILE);
		GEODATA_PATH = geoengine.getProperty("GeoDataPath", "./data/geodata/");
		COORD_SYNCHRONIZE = geoengine.getProperty("CoordSynchronize", -1);
		
		PART_OF_CHARACTER_HEIGHT = geoengine.getProperty("PartOfCharacterHeight", 75);
		MAX_OBSTACLE_HEIGHT = geoengine.getProperty("MaxObstacleHeight", 32);
		
		PATHFINDING = geoengine.getProperty("PathFinding", true);
		PATHFIND_BUFFERS = geoengine.getProperty("PathFindBuffers", "100x6;128x6;192x6;256x4;320x4;384x4;500x2");
		BASE_WEIGHT = geoengine.getProperty("BaseWeight", 10);
		DIAGONAL_WEIGHT = geoengine.getProperty("DiagonalWeight", 14);
		OBSTACLE_MULTIPLIER = geoengine.getProperty("ObstacleMultiplier", 10);
		HEURISTIC_WEIGHT = geoengine.getProperty("HeuristicWeight", 20);
		MAX_ITERATIONS = geoengine.getProperty("MaxIterations", 3500);
		DEBUG_PATH = geoengine.getProperty("DebugPath", false);
		DEBUG_GEO_NODE = geoengine.getProperty("DebugGeoNode", false);
	}
	
	/**
	 * Loads hex ID settings.
	 */
	private static final void loadHexID()
	{
		final ExProperties hexid = initProperties(HEXID_FILE);
		SERVER_ID = Integer.parseInt(hexid.getProperty("ServerID"));
		HEX_ID = new BigInteger(hexid.getProperty("HexID"), 16).toByteArray();
	}
	
	/**
	 * Saves hex ID file.
	 * @param serverId : The ID of server.
	 * @param hexId : The hex ID of server.
	 */
	public static final void saveHexid(int serverId, String hexId)
	{
		saveHexid(serverId, hexId, HEXID_FILE);
	}
	
	/**
	 * Saves hexID file.
	 * @param serverId : The ID of server.
	 * @param hexId : The hexID of server.
	 * @param filename : The file name.
	 */
	public static final void saveHexid(int serverId, String hexId, String filename)
	{
		try
		{
			Properties hexSetting = new Properties();
			File file = new File(filename);
			file.createNewFile();
			
			OutputStream out = new FileOutputStream(file);
			hexSetting.setProperty("ServerID", String.valueOf(serverId));
			hexSetting.setProperty("HexID", hexId);
			hexSetting.store(out, "the hexID to auth into login");
			out.close();
		}
		catch (Exception e)
		{
			_log.warning("Config: Failed to save hex ID to \"" + filename + "\" file.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads NPC settings.<br>
	 * Such as champion monsters, NPC buffer, class master, wyvern, raid bosses and grand bosses, AI.
	 */
	private static final void loadNpcs()
	{
		final ExProperties npcs = initProperties(NPCS_FILE);
		CHAMPION_FREQUENCY = npcs.getProperty("ChampionFrequency", 0);
		CHAMP_MIN_LVL = npcs.getProperty("ChampionMinLevel", 20);
		CHAMP_MAX_LVL = npcs.getProperty("ChampionMaxLevel", 70);
		CHAMPION_HP = npcs.getProperty("ChampionHp", 8);
		CHAMPION_HP_REGEN = npcs.getProperty("ChampionHpRegen", 1.);
		CHAMPION_REWARDS = npcs.getProperty("ChampionRewards", 8);
		CHAMPION_ADENAS_REWARDS = npcs.getProperty("ChampionAdenasRewards", 1);
		CHAMPION_ATK = npcs.getProperty("ChampionAtk", 1.);
		CHAMPION_SPD_ATK = npcs.getProperty("ChampionSpdAtk", 1.);
		CHAMPION_REWARD = npcs.getProperty("ChampionRewardItem", 0);
		CHAMPION_REWARD_ID = npcs.getProperty("ChampionRewardItemID", 6393);
		CHAMPION_REWARD_QTY = npcs.getProperty("ChampionRewardItemQty", 1);
		CORE_RESP_MINION = Integer.parseInt(npcs.getProperty("CoreRespMinion", "60"));
		BUFFER_MAX_SCHEMES = npcs.getProperty("BufferMaxSchemesPerChar", 4);
		BUFFER_MAX_SKILLS = npcs.getProperty("BufferMaxSkillsPerScheme", 24);
		BUFFER_STATIC_BUFF_COST = npcs.getProperty("BufferStaticCostPerBuff", -1);
		BUFFER_BUFFS = npcs.getProperty("BufferBuffs");
		
		FIGHTER_SET = npcs.getProperty("FighterSet", "2375,3500,3501,3502,4422,4423,4424,4425,6648,6649,6650");
		MAGE_SET = npcs.getProperty("MageSet", "2375,3500,3501,3502,4422,4423,4424,4425,6648,6649,6650");
		
		String[] FighterList = FIGHTER_SET.split(",");
		FIGHTER_SET_LIST = new int[FighterList.length];
		for (int i = 0; i < FighterList.length; i++)
			FIGHTER_SET_LIST[i] = Integer.parseInt(FighterList[i]);
		
		String[] MageList = MAGE_SET.split(",");
		MAGE_SET_LIST = new int[MageList.length];
		for (int i = 0; i < MageList.length; i++)
			MAGE_SET_LIST[i] = Integer.parseInt(MageList[i]);
		
		BUFFER_BUFFLIST = new HashMap<>();
		for (String skillInfo : BUFFER_BUFFS.split(";"))
		{
			final String[] infos = skillInfo.split(",");
			BUFFER_BUFFLIST.put(Integer.valueOf(infos[0]), new BuffSkillHolder(Integer.valueOf(infos[0]), Integer.valueOf(infos[1]), infos[2], skillInfo));
		}
		
		RESTRICT_USE_BUFFER_ON_PVPFLAG = Boolean.valueOf(npcs.getProperty("RestrictUseBufferOnPvPFlag", "True"));
		RESTRICT_USE_BUFFER_IN_COMBAT = Boolean.valueOf(npcs.getProperty("RestrictUseBufferInCombat", "True"));
		
		VOTE_BUFF_ITEM_ID = Integer.parseInt(npcs.getProperty("VoteBuffItemId", "57"));
		VOTE_BUFF_ITEM_COUNT = Integer.parseInt(npcs.getProperty("VoteBuffItemCount", "1"));
		
		FIGHTER_SKILL_LIST = new ArrayList<>();
		for (String skill_id : npcs.getProperty("FighterSkillList", "").split(";"))
			FIGHTER_SKILL_LIST.add(Integer.parseInt(skill_id));
		
		MAGE_SKILL_LIST = new ArrayList<>();
		for (String skill_id : npcs.getProperty("MageSkillList", "").split(";"))
			MAGE_SKILL_LIST.add(Integer.parseInt(skill_id));
		
		ALLOW_CLASS_MASTERS = npcs.getProperty("AllowClassMasters", false);
		ALLOW_ENTIRE_TREE = npcs.getProperty("AllowEntireTree", false);
		if (ALLOW_CLASS_MASTERS)
			CLASS_MASTER_SETTINGS = new ClassMasterSettings(npcs.getProperty("ConfigClassMaster"));
		
		ANNOUNCE_MAMMON_SPAWN = npcs.getProperty("AnnounceMammonSpawn", true);
		ALT_MOB_AGRO_IN_PEACEZONE = npcs.getProperty("AltMobAgroInPeaceZone", true);
		SHOW_NPC_LVL = npcs.getProperty("ShowNpcLevel", false);
		SHOW_NPC_CREST = npcs.getProperty("ShowNpcCrest", false);
		SHOW_SUMMON_CREST = npcs.getProperty("ShowSummonCrest", false);
		
		WYVERN_ALLOW_UPGRADER = npcs.getProperty("AllowWyvernUpgrader", true);
		WYVERN_REQUIRED_LEVEL = npcs.getProperty("RequiredStriderLevel", 55);
		WYVERN_REQUIRED_CRYSTALS = npcs.getProperty("RequiredCrystalsNumber", 10);
		
		RAID_HP_REGEN_MULTIPLIER = npcs.getProperty("RaidHpRegenMultiplier", 1.);
		RAID_MP_REGEN_MULTIPLIER = npcs.getProperty("RaidMpRegenMultiplier", 1.);
		RAID_DEFENCE_MULTIPLIER = npcs.getProperty("RaidDefenceMultiplier", 1.);
		RAID_MINION_RESPAWN_TIMER = npcs.getProperty("RaidMinionRespawnTime", 300000);
		
		RAID_DISABLE_CURSE = npcs.getProperty("DisableRaidCurse", false);
		RAID_CHAOS_TIME = npcs.getProperty("RaidChaosTime", 30);
		GRAND_CHAOS_TIME = npcs.getProperty("GrandChaosTime", 30);
		MINION_CHAOS_TIME = npcs.getProperty("MinionChaosTime", 30);
		
		SPAWN_INTERVAL_AQ = npcs.getProperty("AntQueenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_AQ = npcs.getProperty("AntQueenRandomSpawn", 17);
		
		SPAWN_INTERVAL_ANTHARAS = npcs.getProperty("AntharasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_ANTHARAS = npcs.getProperty("AntharasRandomSpawn", 72);
		WAIT_TIME_ANTHARAS = npcs.getProperty("AntharasWaitTime", 30) * 60000;
		
		SPAWN_INTERVAL_BAIUM = npcs.getProperty("BaiumSpawnInterval", 168);
		RANDOM_SPAWN_TIME_BAIUM = npcs.getProperty("BaiumRandomSpawn", 48);
		
		SPAWN_INTERVAL_CORE = npcs.getProperty("CoreSpawnInterval", 60);
		RANDOM_SPAWN_TIME_CORE = npcs.getProperty("CoreRandomSpawn", 23);
		
		SPAWN_INTERVAL_FRINTEZZA = npcs.getProperty("FrintezzaSpawnInterval", 48);
		RANDOM_SPAWN_TIME_FRINTEZZA = npcs.getProperty("FrintezzaRandomSpawn", 8);
		WAIT_TIME_FRINTEZZA = npcs.getProperty("FrintezzaWaitTime", 10) * 60000;
		BYPASS_FRINTEZZA_PARTIES_CHECK = Boolean.valueOf(npcs.getProperty("BypassPartiesCheck", "true"));
		FRINTEZZA_MIN_PARTIES = npcs.getProperty("FrintezzaMinParties", 4);
		FRINTEZZA_MAX_PARTIES = npcs.getProperty("FrintezzaMaxParties", 5);
		
		SPAWN_INTERVAL_ORFEN = npcs.getProperty("OrfenSpawnInterval", 48);
		RANDOM_SPAWN_TIME_ORFEN = npcs.getProperty("OrfenRandomSpawn", 20);
		
		SPAWN_INTERVAL_SAILREN = npcs.getProperty("SailrenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_SAILREN = npcs.getProperty("SailrenRandomSpawn", 24);
		WAIT_TIME_SAILREN = npcs.getProperty("SailrenWaitTime", 5) * 60000;
		
		SPAWN_INTERVAL_VALAKAS = npcs.getProperty("ValakasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_VALAKAS = npcs.getProperty("ValakasRandomSpawn", 72);
		WAIT_TIME_VALAKAS = npcs.getProperty("ValakasWaitTime", 30) * 60000;
		
		SPAWN_INTERVAL_ZAKEN = npcs.getProperty("ZakenSpawnInterval", 60);
		RANDOM_SPAWN_TIME_ZAKEN = npcs.getProperty("ZakenRandomSpawn", 20);
		
		GUARD_ATTACK_AGGRO_MOB = npcs.getProperty("GuardAttackAggroMob", false);
		MAX_DRIFT_RANGE = npcs.getProperty("MaxDriftRange", 300);
		MIN_NPC_ANIMATION = npcs.getProperty("MinNPCAnimation", 20);
		MAX_NPC_ANIMATION = npcs.getProperty("MaxNPCAnimation", 40);
		MIN_MONSTER_ANIMATION = npcs.getProperty("MinMonsterAnimation", 10);
		MAX_MONSTER_ANIMATION = npcs.getProperty("MaxMonsterAnimation", 40);
	}
	
	/**
	 * Loads player settings.<br>
	 * Such as stats, inventory/warehouse, enchant, augmentation, karma, party, admin, petition, skill learn.
	 */
	private static final void loadPlayers()
	{
		final ExProperties players = initProperties(PLAYERS_FILE);
		EFFECT_CANCELING = players.getProperty("CancelLesserEffect", true);
		HP_REGEN_MULTIPLIER = players.getProperty("HpRegenMultiplier", 1.);
		MP_REGEN_MULTIPLIER = players.getProperty("MpRegenMultiplier", 1.);
		CP_REGEN_MULTIPLIER = players.getProperty("CpRegenMultiplier", 1.);
		PLAYER_SPAWN_PROTECTION = players.getProperty("PlayerSpawnProtection", 0);
		PLAYER_FAKEDEATH_UP_PROTECTION = players.getProperty("PlayerFakeDeathUpProtection", 0);
		RESPAWN_RESTORE_HP = players.getProperty("RespawnRestoreHP", 0.7);
		MAX_PVTSTORE_SLOTS_DWARF = players.getProperty("MaxPvtStoreSlotsDwarf", 5);
		MAX_PVTSTORE_SLOTS_OTHER = players.getProperty("MaxPvtStoreSlotsOther", 4);
		DEEPBLUE_DROP_RULES = players.getProperty("UseDeepBlueDropRules", true);
		ALT_GAME_DELEVEL = players.getProperty("Delevel", true);
		DEATH_PENALTY_CHANCE = players.getProperty("DeathPenaltyChance", 20);
		
		INVENTORY_MAXIMUM_NO_DWARF = players.getProperty("MaximumSlotsForNoDwarf", 80);
		INVENTORY_MAXIMUM_DWARF = players.getProperty("MaximumSlotsForDwarf", 100);
		INVENTORY_MAXIMUM_QUEST_ITEMS = players.getProperty("MaximumSlotsForQuestItems", 100);
		INVENTORY_MAXIMUM_PET = players.getProperty("MaximumSlotsForPet", 12);
		MAX_ITEM_IN_PACKET = Math.max(INVENTORY_MAXIMUM_NO_DWARF, INVENTORY_MAXIMUM_DWARF);
		ALT_WEIGHT_LIMIT = players.getProperty("AltWeightLimit", 1);
		WAREHOUSE_SLOTS_NO_DWARF = players.getProperty("MaximumWarehouseSlotsForNoDwarf", 100);
		WAREHOUSE_SLOTS_DWARF = players.getProperty("MaximumWarehouseSlotsForDwarf", 120);
		WAREHOUSE_SLOTS_CLAN = players.getProperty("MaximumWarehouseSlotsForClan", 150);
		FREIGHT_SLOTS = players.getProperty("MaximumFreightSlots", 20);
		ALT_GAME_FREIGHTS = players.getProperty("AltGameFreights", false);
		ALT_GAME_FREIGHT_PRICE = players.getProperty("AltGameFreightPrice", 1000);
		
		AUGMENTATION_NG_SKILL_CHANCE = players.getProperty("AugmentationNGSkillChance", 15);
		AUGMENTATION_NG_GLOW_CHANCE = players.getProperty("AugmentationNGGlowChance", 0);
		AUGMENTATION_MID_SKILL_CHANCE = players.getProperty("AugmentationMidSkillChance", 30);
		AUGMENTATION_MID_GLOW_CHANCE = players.getProperty("AugmentationMidGlowChance", 40);
		AUGMENTATION_HIGH_SKILL_CHANCE = players.getProperty("AugmentationHighSkillChance", 45);
		AUGMENTATION_HIGH_GLOW_CHANCE = players.getProperty("AugmentationHighGlowChance", 70);
		AUGMENTATION_TOP_SKILL_CHANCE = players.getProperty("AugmentationTopSkillChance", 60);
		AUGMENTATION_TOP_GLOW_CHANCE = players.getProperty("AugmentationTopGlowChance", 100);
		AUGMENTATION_BASESTAT_CHANCE = players.getProperty("AugmentationBaseStatChance", 1);
		
		KARMA_PLAYER_CAN_BE_KILLED_IN_PZ = players.getProperty("KarmaPlayerCanBeKilledInPeaceZone", false);
		KARMA_PLAYER_CAN_SHOP = players.getProperty("KarmaPlayerCanShop", false);
		KARMA_PLAYER_CAN_USE_GK = players.getProperty("KarmaPlayerCanUseGK", false);
		KARMA_PLAYER_CAN_TELEPORT = players.getProperty("KarmaPlayerCanTeleport", true);
		KARMA_PLAYER_CAN_TRADE = players.getProperty("KarmaPlayerCanTrade", true);
		KARMA_PLAYER_CAN_USE_WH = players.getProperty("KarmaPlayerCanUseWareHouse", true);
		KARMA_DROP_GM = players.getProperty("CanGMDropEquipment", false);
		KARMA_AWARD_PK_KILL = players.getProperty("AwardPKKillPVPPoint", true);
		KARMA_PK_LIMIT = players.getProperty("MinimumPKRequiredToDrop", 5);
		KARMA_NONDROPPABLE_PET_ITEMS = players.getProperty("ListOfPetItems", "2375,3500,3501,3502,4422,4423,4424,4425,6648,6649,6650");
		KARMA_NONDROPPABLE_ITEMS = players.getProperty("ListOfNonDroppableItemsForPK", "1147,425,1146,461,10,2368,7,6,2370,2369");
		
		String[] array = KARMA_NONDROPPABLE_PET_ITEMS.split(",");
		KARMA_LIST_NONDROPPABLE_PET_ITEMS = new int[array.length];
		
		for (int i = 0; i < array.length; i++)
			KARMA_LIST_NONDROPPABLE_PET_ITEMS[i] = Integer.parseInt(array[i]);
		
		array = KARMA_NONDROPPABLE_ITEMS.split(",");
		KARMA_LIST_NONDROPPABLE_ITEMS = new int[array.length];
		
		for (int i = 0; i < array.length; i++)
			KARMA_LIST_NONDROPPABLE_ITEMS[i] = Integer.parseInt(array[i]);
		
		// sorting so binarySearch can be used later
		Arrays.sort(KARMA_LIST_NONDROPPABLE_PET_ITEMS);
		Arrays.sort(KARMA_LIST_NONDROPPABLE_ITEMS);
		
		PVP_NORMAL_TIME = players.getProperty("PvPVsNormalTime", 15000);
		PVP_PVP_TIME = players.getProperty("PvPVsPvPTime", 30000);
		
		PARTY_XP_CUTOFF_METHOD = players.getProperty("PartyXpCutoffMethod", "level");
		PARTY_XP_CUTOFF_PERCENT = players.getProperty("PartyXpCutoffPercent", 3.);
		PARTY_XP_CUTOFF_LEVEL = players.getProperty("PartyXpCutoffLevel", 20);
		PARTY_RANGE = players.getProperty("PartyRange", 1500);
		
		DEFAULT_ACCESS_LEVEL = players.getProperty("DefaultAccessLevel", 0);
		GM_HERO_AURA = players.getProperty("GMHeroAura", false);
		GM_STARTUP_INVULNERABLE = players.getProperty("GMStartupInvulnerable", true);
		GM_STARTUP_INVISIBLE = players.getProperty("GMStartupInvisible", true);
		GM_STARTUP_SILENCE = players.getProperty("GMStartupSilence", true);
		GM_STARTUP_AUTO_LIST = players.getProperty("GMStartupAutoList", true);
		
		PETITIONING_ALLOWED = players.getProperty("PetitioningAllowed", true);
		MAX_PETITIONS_PER_PLAYER = players.getProperty("MaxPetitionsPerPlayer", 5);
		MAX_PETITIONS_PENDING = players.getProperty("MaxPetitionsPending", 25);
		
		IS_CRAFTING_ENABLED = players.getProperty("CraftingEnabled", true);
		DWARF_RECIPE_LIMIT = players.getProperty("DwarfRecipeLimit", 50);
		COMMON_RECIPE_LIMIT = players.getProperty("CommonRecipeLimit", 50);
		ALT_BLACKSMITH_USE_RECIPES = players.getProperty("AltBlacksmithUseRecipes", true);
		
		AUTO_LEARN_SKILLS = players.getProperty("AutoLearnSkills", false);
		ALT_GAME_MAGICFAILURES = players.getProperty("MagicFailures", true);
		ALT_GAME_SHIELD_BLOCKS = players.getProperty("AltShieldBlocks", false);
		ALT_PERFECT_SHLD_BLOCK = players.getProperty("AltPerfectShieldBlockRate", 10);
		LIFE_CRYSTAL_NEEDED = players.getProperty("LifeCrystalNeeded", true);
		SP_BOOK_NEEDED = players.getProperty("SpBookNeeded", true);
		ES_SP_BOOK_NEEDED = players.getProperty("EnchantSkillSpBookNeeded", true);
		DIVINE_SP_BOOK_NEEDED = players.getProperty("DivineInspirationSpBookNeeded", true);
		AUTO_LEARN_DIVINE_INSPIRATION = Boolean.parseBoolean(players.getProperty("AutoLearnDivineInspiration", "false"));
		ALT_GAME_SUBCLASS_WITHOUT_QUESTS = players.getProperty("AltSubClassWithoutQuests", false);
		
		BUFFS_MAX_AMOUNT = players.getProperty("MaxBuffsAmount", 20);
		STORE_SKILL_COOLTIME = players.getProperty("StoreSkillCooltime", true);
		
		STARTING_ADENA = players.getProperty("StartingAdena", 10000000);
		KARMA_PLAYER_CAN_USE_BUFFER = players.getProperty("KarmaFlagPlayerCanUseBuffer", false);
		EXPERTISE_PENALTY = players.getProperty("ExpertisePenalty", true);
	}
	
	/**
	 * Loads siege settings.
	 */
	private static final void loadSieges()
	{
		final ExProperties sieges = initProperties(Config.SIEGE_FILE);
		
		SIEGE_LENGTH = sieges.getProperty("SiegeLength", 120);
		MINIMUM_CLAN_LEVEL = sieges.getProperty("SiegeClanMinLevel", 4);
		MAX_ATTACKERS_NUMBER = sieges.getProperty("AttackerMaxClans", 10);
		MAX_DEFENDERS_NUMBER = sieges.getProperty("DefenderMaxClans", 10);
		ATTACKERS_RESPAWN_DELAY = sieges.getProperty("AttackerRespawn", 10000);
	}
	
	/**
	 * Loads gameserver settings.<br>
	 * IP addresses, database, rates, feature enabled/disabled, misc.
	 */
	private static final void loadServer()
	{
		final ExProperties server = initProperties(SERVER_FILE);
		
		GAMESERVER_HOSTNAME = server.getProperty("GameserverHostname");
		PORT_GAME = server.getProperty("GameserverPort", 7777);
		
		HOSTNAME = server.getProperty("Hostname", "*");
		
		GAME_SERVER_LOGIN_PORT = server.getProperty("LoginPort", 9014);
		GAME_SERVER_LOGIN_HOST = server.getProperty("LoginHost", "127.0.0.1");
		
		REQUEST_ID = server.getProperty("RequestServerID", 0);
		ACCEPT_ALTERNATE_ID = server.getProperty("AcceptAlternateID", true);
		
		DATABASE_URL = server.getProperty("URL", "jdbc:mysql://localhost/acis");
		DATABASE_LOGIN = server.getProperty("Login", "root");
		DATABASE_PASSWORD = server.getProperty("Password", "");
		DATABASE_MAX_CONNECTIONS = server.getProperty("MaximumDbConnections", 10);
		
		SERVER_LIST_BRACKET = server.getProperty("ServerListBrackets", false);
		SERVER_LIST_CLOCK = server.getProperty("ServerListClock", false);
		SERVER_GMONLY = server.getProperty("ServerGMOnly", false);
		SERVER_LIST_AGE = server.getProperty("ServerListAgeLimit", 0);
		SERVER_LIST_TESTSERVER = server.getProperty("TestServer", false);
		SERVER_LIST_PVPSERVER = server.getProperty("PvpServer", true);
		
		DELETE_DAYS = server.getProperty("DeleteCharAfterDays", 7);
		MAXIMUM_ONLINE_USERS = server.getProperty("MaximumOnlineUsers", 100);
		MIN_PROTOCOL_REVISION = server.getProperty("MinProtocolRevision", 730);
		MAX_PROTOCOL_REVISION = server.getProperty("MaxProtocolRevision", 746);
		if (MIN_PROTOCOL_REVISION > MAX_PROTOCOL_REVISION)
			throw new Error("MinProtocolRevision is bigger than MaxProtocolRevision in server.properties.");
		
		AUTO_LOOT = server.getProperty("AutoLoot", false);
		AUTO_LOOT_HERBS = server.getProperty("AutoLootHerbs", false);
		AUTO_LOOT_RAID = server.getProperty("AutoLootRaid", false);
		
		ALLOW_DISCARDITEM = server.getProperty("AllowDiscardItem", true);
		MULTIPLE_ITEM_DROP = server.getProperty("MultipleItemDrop", true);
		HERB_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyHerbTime", 15) * 1000;
		ITEM_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyItemTime", 600) * 1000;
		EQUIPABLE_ITEM_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyEquipableItemTime", 0) * 1000;
		SPECIAL_ITEM_DESTROY_TIME = new HashMap<>();
		String[] data = server.getProperty("AutoDestroySpecialItemTime", (String[]) null, ",");
		if (data != null)
		{
			for (String itemData : data)
			{
				String[] item = itemData.split("-");
				SPECIAL_ITEM_DESTROY_TIME.put(Integer.parseInt(item[0]), Integer.parseInt(item[1]) * 1000);
			}
		}
		PLAYER_DROPPED_ITEM_MULTIPLIER = server.getProperty("PlayerDroppedItemMultiplier", 1);
		
		RATE_XP = server.getProperty("RateXp", 1.);
		RATE_SP = server.getProperty("RateSp", 1.);
		RATE_PARTY_XP = server.getProperty("RatePartyXp", 1.);
		RATE_PARTY_SP = server.getProperty("RatePartySp", 1.);
		RATE_DROP_ADENA = server.getProperty("RateDropAdena", 1.);
		RATE_CONSUMABLE_COST = server.getProperty("RateConsumableCost", 1.);
		RATE_DROP_ITEMS = server.getProperty("RateDropItems", 1.);
		RATE_DROP_ITEMS_BY_RAID = server.getProperty("RateRaidDropItems", 1.);
		RATE_DROP_SPOIL = server.getProperty("RateDropSpoil", 1.);
		RATE_DROP_MANOR = server.getProperty("RateDropManor", 1);
		RATE_QUEST_DROP = server.getProperty("RateQuestDrop", 1.);
		RATE_QUEST_REWARD = server.getProperty("RateQuestReward", 1.);
		RATE_QUEST_REWARD_XP = server.getProperty("RateQuestRewardXP", 1.);
		RATE_QUEST_REWARD_SP = server.getProperty("RateQuestRewardSP", 1.);
		RATE_QUEST_REWARD_ADENA = server.getProperty("RateQuestRewardAdena", 1.);
		RATE_KARMA_EXP_LOST = server.getProperty("RateKarmaExpLost", 1.);
		RATE_SIEGE_GUARDS_PRICE = server.getProperty("RateSiegeGuardsPrice", 1.);
		RATE_DROP_COMMON_HERBS = server.getProperty("RateCommonHerbs", 1.);
		RATE_DROP_HP_HERBS = server.getProperty("RateHpHerbs", 1.);
		RATE_DROP_MP_HERBS = server.getProperty("RateMpHerbs", 1.);
		RATE_DROP_SEAL_STONES = Float.parseFloat(server.getProperty("RateDropSealStones", "1.00"));
		RATE_DROP_SPECIAL_HERBS = server.getProperty("RateSpecialHerbs", 1.);
		PLAYER_DROP_LIMIT = server.getProperty("PlayerDropLimit", 3);
		PLAYER_RATE_DROP = server.getProperty("PlayerRateDrop", 5);
		PLAYER_RATE_DROP_ITEM = server.getProperty("PlayerRateDropItem", 70);
		PLAYER_RATE_DROP_EQUIP = server.getProperty("PlayerRateDropEquip", 25);
		PLAYER_RATE_DROP_EQUIP_WEAPON = server.getProperty("PlayerRateDropEquipWeapon", 5);
		PET_XP_RATE = server.getProperty("PetXpRate", 1.);
		PET_FOOD_RATE = server.getProperty("PetFoodRate", 1);
		SINEATER_XP_RATE = server.getProperty("SinEaterXpRate", 1.);
		KARMA_DROP_LIMIT = server.getProperty("KarmaDropLimit", 10);
		KARMA_RATE_DROP = server.getProperty("KarmaRateDrop", 70);
		KARMA_RATE_DROP_ITEM = server.getProperty("KarmaRateDropItem", 50);
		KARMA_RATE_DROP_EQUIP = server.getProperty("KarmaRateDropEquip", 40);
		KARMA_RATE_DROP_EQUIP_WEAPON = server.getProperty("KarmaRateDropEquipWeapon", 10);
		
		ALLOW_FREIGHT = server.getProperty("AllowFreight", true);
		ALLOW_WAREHOUSE = server.getProperty("AllowWarehouse", true);
		ALLOW_WEAR = server.getProperty("AllowWear", true);
		WEAR_DELAY = server.getProperty("WearDelay", 5);
		WEAR_PRICE = server.getProperty("WearPrice", 10);
		ALLOW_LOTTERY = server.getProperty("AllowLottery", true);
		ALLOW_WATER = server.getProperty("AllowWater", true);
		ALLOW_MANOR = server.getProperty("AllowManor", true);
		ALLOW_BOAT = server.getProperty("AllowBoat", true);
		ALLOW_CURSED_WEAPONS = server.getProperty("AllowCursedWeapons", true);
		
		ENABLE_FALLING_DAMAGE = server.getProperty("EnableFallingDamage", true);
		
		ALT_DEV_NO_SPAWNS = server.getProperty("NoSpawns", false);
		DEBUG = server.getProperty("Debug", false);
		DEVELOPER = server.getProperty("Developer", false);
		PACKET_HANDLER_DEBUG = server.getProperty("PacketHandlerDebug", false);
		
		DEADLOCK_DETECTOR = server.getProperty("DeadLockDetector", false);
		DEADLOCK_CHECK_INTERVAL = server.getProperty("DeadLockCheckInterval", 20);
		RESTART_ON_DEADLOCK = server.getProperty("RestartOnDeadlock", false);
		
		LOG_CHAT = server.getProperty("LogChat", false);
		LOG_ITEMS = server.getProperty("LogItems", false);
		GMAUDIT = server.getProperty("GMAudit", false);
		
		ENABLE_COMMUNITY_BOARD = server.getProperty("EnableCommunityBoard", false);
		BBS_DEFAULT = server.getProperty("BBSDefault", "_bbshome");
		
		ROLL_DICE_TIME = server.getProperty("RollDiceTime", 4200);
		USER_ITEM_TIME = server.getProperty("UserItemTime", 4200);
		HERO_VOICE_TIME = server.getProperty("HeroVoiceTime", 10000);
		SUBCLASS_TIME = server.getProperty("SubclassTime", 2000);
		DROP_ITEM_TIME = server.getProperty("DropItemTime", 1000);
		SERVER_BYPASS_TIME = server.getProperty("ServerBypassTime", 500);
		MULTISELL_TIME = server.getProperty("MultisellTime", 100);
		MANUFACTURE_TIME = server.getProperty("ManufactureTime", 300);
		MANOR_TIME = server.getProperty("ManorTime", 3000);
		SENDMAIL_TIME = server.getProperty("SendMailTime", 10000);
		CHARACTER_SELECT_TIME = server.getProperty("CharacterSelectTime", 3000);
		GLOBAL_CHAT_TIME = server.getProperty("GlobalChatTime", 0);
		TRADE_CHAT_TIME = server.getProperty("TradeChatTime", 0);
		SOCIAL_TIME = server.getProperty("SocialTime", 2000);
		
		SCHEDULED_THREAD_POOL_COUNT = server.getProperty("ScheduledThreadPoolCount", -1);
		THREADS_PER_SCHEDULED_THREAD_POOL = server.getProperty("ThreadsPerScheduledThreadPool", 4);
		INSTANT_THREAD_POOL_COUNT = server.getProperty("InstantThreadPoolCount", -1);
		THREADS_PER_INSTANT_THREAD_POOL = server.getProperty("ThreadsPerInstantThreadPool", 2);
		L2WALKER_PROTECTION = server.getProperty("L2WalkerProtection", false);
		ZONE_TOWN = server.getProperty("ZoneTown", 0);
		SERVER_NEWS = server.getProperty("ShowServerNews", false);
		DISABLE_TUTORIAL = server.getProperty("DisableTutorial", false);
	}
	
	/**
	 * Loads loginserver settings.<br>
	 * IP addresses, database, account, misc.
	 */
	private static final void loadLogin()
	{
		final ExProperties server = initProperties(LOGIN_CONFIGURATION_FILE);
		HOSTNAME = server.getProperty("Hostname", "localhost");
		
		LOGIN_BIND_ADDRESS = server.getProperty("LoginserverHostname", "*");
		PORT_LOGIN = server.getProperty("LoginserverPort", 2106);
		
		GAME_SERVER_LOGIN_HOST = server.getProperty("LoginHostname", "*");
		GAME_SERVER_LOGIN_PORT = server.getProperty("LoginPort", 9014);
		
		LOGIN_TRY_BEFORE_BAN = server.getProperty("LoginTryBeforeBan", 3);
		LOGIN_BLOCK_AFTER_BAN = server.getProperty("LoginBlockAfterBan", 600);
		ACCEPT_NEW_GAMESERVER = server.getProperty("AcceptNewGameServer", false);
		
		SHOW_LICENCE = server.getProperty("ShowLicence", true);
		
		DATABASE_URL = server.getProperty("URL", "jdbc:mysql://localhost/acis");
		DATABASE_LOGIN = server.getProperty("Login", "root");
		DATABASE_PASSWORD = server.getProperty("Password", "");
		DATABASE_MAX_CONNECTIONS = server.getProperty("MaximumDbConnections", 10);
		
		AUTO_CREATE_ACCOUNTS = server.getProperty("AutoCreateAccounts", true);
		
		LOG_LOGIN_CONTROLLER = server.getProperty("LogLoginController", false);
		
		FLOOD_PROTECTION = server.getProperty("EnableFloodProtection", true);
		FAST_CONNECTION_LIMIT = server.getProperty("FastConnectionLimit", 15);
		NORMAL_CONNECTION_TIME = server.getProperty("NormalConnectionTime", 700);
		FAST_CONNECTION_TIME = server.getProperty("FastConnectionTime", 350);
		MAX_CONNECTION_PER_IP = server.getProperty("MaxConnectionPerIP", 50);
		
		DEBUG = server.getProperty("Debug", false);
		DEVELOPER = server.getProperty("Developer", false);
		PACKET_HANDLER_DEBUG = server.getProperty("PacketHandlerDebug", false);
	}
	
	public static final void loadGameServer()
	{
		_log.info("Loading l2jserver configuration files.");
		
		// extra settings
		loadDonator();
		
		// customs settings
		loadCustoms();
		
		// Bots settings
		loadCommandList();
		
		// Newbies settings
		loadNewbies();
		
		// Raidbosses settings
		loadRaidboss();
		
		// enchant settings
		loadEnchant();
		
		// balance settings
		loadBalance();
		
		// CKM settings
		loadCharacterKillingMonuments();
		
		// tournament settings
		loadTour();
		
		// phantom settings
		loadPhantom();
		
		// Npc Manager settings
		loadManager();
		
		// clans settings
		loadClans();
		
		// events settings
		loadEvents();
		
		// geoengine settings
		loadGeoengine();
		
		// hexID
		loadHexID();
		
		// NPCs/monsters settings
		loadNpcs();
		
		// players settings
		loadPlayers();
		
		// siege settings
		loadSieges();
		
		// server settings
		loadServer();
		_log.info("Loading L2JDev PhantomPlayers");
		// Phantom PlayerEngine
		loadFakes();
		MultiNpcs();
		
		//Pc Bnag point
		loadPCBPointConfig();
		loadPTFarmConfig();
	}
	
	public static final void loadLoginServer()
	{
		_log.info("Loading loginserver configuration files.");
		
		// login settings
		loadLogin();
	}
	
	public static final void loadAccountManager()
	{
		_log.info("Loading account manager configuration files.");
		
		// login settings
		loadLogin();
	}
	
	public static final void loadGameServerRegistration()
	{
		_log.info("Loading gameserver registration configuration files.");
		
		// login settings
		loadLogin();
	}
	
	public static final void loadGeodataConverter()
	{
		_log.info("Loading geodata converter configuration files.");
		
		// geoengine settings
		loadGeoengine();
	}
	
	public static final class ClassMasterSettings
	{
		private final Map<Integer, Boolean> _allowedClassChange;
		private final Map<Integer, List<IntIntHolder>> _claimItems;
		private final Map<Integer, List<IntIntHolder>> _rewardItems;
		
		public ClassMasterSettings(String configLine)
		{
			_allowedClassChange = new HashMap<>(3);
			_claimItems = new HashMap<>(3);
			_rewardItems = new HashMap<>(3);
			
			if (configLine != null)
				parseConfigLine(configLine.trim());
		}
		
		private void parseConfigLine(String configLine)
		{
			StringTokenizer st = new StringTokenizer(configLine, ";");
			while (st.hasMoreTokens())
			{
				// Get allowed class change.
				int job = Integer.parseInt(st.nextToken());
				
				_allowedClassChange.put(job, true);
				
				List<IntIntHolder> items = new ArrayList<>();
				
				// Parse items needed for class change.
				if (st.hasMoreTokens())
				{
					StringTokenizer st2 = new StringTokenizer(st.nextToken(), "[],");
					while (st2.hasMoreTokens())
					{
						StringTokenizer st3 = new StringTokenizer(st2.nextToken(), "()");
						items.add(new IntIntHolder(Integer.parseInt(st3.nextToken()), Integer.parseInt(st3.nextToken())));
					}
				}
				
				// Feed the map, and clean the list.
				_claimItems.put(job, items);
				items = new ArrayList<>();
				
				// Parse gifts after class change.
				if (st.hasMoreTokens())
				{
					StringTokenizer st2 = new StringTokenizer(st.nextToken(), "[],");
					while (st2.hasMoreTokens())
					{
						StringTokenizer st3 = new StringTokenizer(st2.nextToken(), "()");
						items.add(new IntIntHolder(Integer.parseInt(st3.nextToken()), Integer.parseInt(st3.nextToken())));
					}
				}
				
				_rewardItems.put(job, items);
			}
		}
		
		public boolean isAllowed(int job)
		{
			if (_allowedClassChange == null)
				return false;
			
			if (_allowedClassChange.containsKey(job))
				return _allowedClassChange.get(job);
			
			return false;
		}
		
		public List<IntIntHolder> getRewardItems(int job)
		{
			return _rewardItems.get(job);
		}
		
		public List<IntIntHolder> getRequiredItems(int job)
		{
			return _claimItems.get(job);
		}
	}
}