package com.chris.interview.client.ropasci.factories;

import com.chris.interview.client.ropasci.entities.BestOf3Games;
import com.chris.interview.client.ropasci.entities.IMatch;
import com.chris.interview.client.ropasci.entities.Player;
import com.chris.interview.client.ropasci.exceptions.TooManyPlayers;

public class MatchFactory {
	public static IMatch createBestOf3GamesMatch(Player a, Player b) {
		IMatch ret = new BestOf3Games();
		try {
			ret.addPlayer(a);
			ret.addPlayer(b);
		} catch (TooManyPlayers e) {
			e.printStackTrace();
		}
		return ret;
	}
}
