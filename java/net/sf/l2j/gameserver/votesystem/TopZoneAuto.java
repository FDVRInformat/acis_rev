package net.sf.l2j.gameserver.votesystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.Announcement;

/**
 * @author Reborn12
 */
public class TopZoneAuto extends AutoVoteMain
{

	public static int getTopZoneVotes()
	{
		int votes = -1;

		try
		{
			final URL obj = new URL(Config.VOTES_SITE_TOPZONE_LINK_TOP);
			final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.addRequestProperty("User-Agent", "L2TopZone");
			con.setConnectTimeout(5000);

			final int responseCode = con.getResponseCode();
			if (responseCode == 200)
			{
				try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())))
				{
					String inputLine;
					while ((inputLine = in.readLine()) != null)
					{
						votes = Integer.valueOf(inputLine);
						break;
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Server TOPZONE is offline Trying to Reconnect");
			Announcement.VoteAnnouncements("TOPZONE is offline...Trying to Reconnect");
		}
		return votes;
	}

	public static class CheckForRewardTop implements Runnable
	{
		@Override
		public void run()
		{
			if (_topzone)
			{
				int topzone_votes = getTopZoneVotes();
				if (topzone_votes != -1)
				{
					LOGGER.info("[TOPZONE] Votes: " + topzone_votes);
					Announcement.VoteAnnouncements("Topzone -  Votes are " + topzone_votes + "...");

					if (topzone_votes != 0 && topzone_votes >= getTopZoneVoteCount() + Config.VOTES_FOR_REWARD_TOP)
					{
						rewardPlayer();
						Announcement.VoteAnnouncements("Topzone - Thanks For Voting..Players Rewarded!");
					}
					setTopZoneVoteCount(topzone_votes);
				}
				Announcement.VoteAnnouncements("Topzone - Next Reward at " + (getTopZoneVoteCount() + Config.VOTES_FOR_REWARD_TOP) + " Votes!!");

				Announcement.VoteAnnouncements("Website: " + Config.SERVER_WEB_SITE);
			}
		}
	}
}