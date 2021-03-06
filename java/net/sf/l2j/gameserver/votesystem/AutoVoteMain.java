package net.sf.l2j.gameserver.votesystem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.l2j.commons.concurrent.ThreadPool;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.votesystem.HopZoneAuto.CheckForRewardHop;
import net.sf.l2j.gameserver.votesystem.NetWorkAuto.CheckForRewardNet;
import net.sf.l2j.gameserver.votesystem.TopZoneAuto.CheckForRewardTop;

public class AutoVoteMain
{
	static final Logger LOGGER = Logger.getLogger(AutoVoteMain.class.getName());
	static Map<String, Integer> playerIps = new HashMap<>();
	private static int _topzoneVotesCount = 0;
	private static int _l2networkVotesCount = 0;
	private static int _hopzoneVotesCount = 0;
	static boolean _topzone = false;
	static boolean _l2network = false;
	static boolean _hopzone = false;

	AutoVoteMain()
	{
		LOGGER.info("VoteSystem Loaded.");
		if (_topzone)
		{
			int topzone_votes = TopZoneAuto.getTopZoneVotes();

			if (topzone_votes == -1)
				topzone_votes = 0;

			setTopZoneVoteCount(topzone_votes);
		}

		if (_l2network)
		{
			int l2network_votes = NetWorkAuto.getL2NetworkVotes();

			if (l2network_votes == -1)
				l2network_votes = 0;

			setL2NetworkVoteCount(l2network_votes);
		}

		if (_hopzone)
		{
			int hopzone_votes = HopZoneAuto.getHopZoneVotes();

			if (hopzone_votes == -1)
				hopzone_votes = 0;

			setHopZoneVoteCount(hopzone_votes);
		}
		startTimer();
	}

	private static void startTimer()
	{
		ThreadPool.scheduleAtFixedRate(new CheckForRewardNet(), Config.VOTES_SYSTEM_INITIAL_DELAY_NET * 60 * 1000, Config.VOTES_SYSTEM_INITIAL_DELAY_NET * 60 * 1000);
		ThreadPool.scheduleAtFixedRate(new CheckForRewardTop(), Config.VOTES_SYSTEM_INITIAL_DELAY_TOP * 60 * 1000, Config.VOTES_SYSTEM_INITIAL_DELAY_TOP * 60 * 1000);
		ThreadPool.scheduleAtFixedRate(new CheckForRewardHop(), Config.VOTES_SYSTEM_INITIAL_DELAY_HOP * 60 * 1000, Config.VOTES_SYSTEM_INITIAL_DELAY_HOP * 60 * 1000);
	}

	protected static void rewardPlayer()
	{
		Collection<Player> pls = World.getInstance().getPlayers();

		for (Player p : pls)
		{
			if (p.getClient() == null || p.getClient().isDetached()) // offline shops protection
				continue;
			if (p.isAfking())
				continue;

			boolean canReward = false;
			String pIp = p.getClient().getConnection().getInetAddress().getHostAddress();
			if (playerIps.containsKey(pIp))
			{
				int count = playerIps.get(pIp);
				if (count < Config.VOTE_BOXES_ALLOWED)
				{
					playerIps.remove(pIp);
					playerIps.put(pIp, count + 1);
					canReward = true;
				}
			}
			else
			{
				canReward = true;
				playerIps.put(pIp, 1);
			}
			if (canReward)
				for (int i : Config.VOTES_REWARDS_LIST_AUTOVOTE.keySet())
					p.addItem("Vote reward.", i, Config.VOTES_REWARDS_LIST_AUTOVOTE.get(i), p, true);
			else
				p.sendMessage("Already " + Config.VOTE_BOXES_ALLOWED + " character(s) of your ip have been rewarded, so this character won't be rewarded.");
		}
		playerIps.clear();
	}

	protected static void setTopZoneVoteCount(int voteCount)
	{
		_topzoneVotesCount = voteCount;
	}

	protected static int getTopZoneVoteCount()
	{
		return _topzoneVotesCount;

	}

	protected static void setL2NetworkVoteCount(int voteCount)
	{
		_l2networkVotesCount = voteCount;
	}

	protected static int getL2NetworkVoteCount()
	{
		return _l2networkVotesCount;
	}

	protected static void setHopZoneVoteCount(int voteCount)
	{
		_hopzoneVotesCount = voteCount;
	}

	protected static int getHopZoneVoteCount()
	{
		return _hopzoneVotesCount;
	}

	public static AutoVoteMain getInstance()
	{

		if (Config.VOTES_SITE_TOPZONE_LINK_TOP != null && !Config.VOTES_SITE_TOPZONE_LINK_TOP.equals(""))
		{
			_topzone = true;
		}
		if (Config.VOTES_SITE_L2NETWORK_LINK_NET != null && !Config.VOTES_SITE_L2NETWORK_LINK_NET.equals(""))
		{
			_l2network = true;
		}
		if (Config.VOTES_SITE_HOPZONE_LINK_HOP != null && !Config.VOTES_SITE_HOPZONE_LINK_HOP.equals(""))
		{
			_hopzone = true;
		}
		if (_topzone && _l2network && _hopzone)
		{
			return SingletonHolder._instance;
		}
		return null;
	}

	private static class SingletonHolder
	{
		protected static final AutoVoteMain _instance = new AutoVoteMain();
	}
}