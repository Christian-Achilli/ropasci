package com.chris.interview.client.ropasci.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.chris.interview.client.ropasci.exceptions.MatchIsOver;
import com.chris.interview.client.ropasci.exceptions.MissingPlayers;
import com.chris.interview.client.ropasci.exceptions.TooManyPlayers;
import com.chris.interview.client.ropasci.services.GameEngine;


/**
 * @author chris
 * The match ends when 1 player wins at least 2 games out of 3
 */
public class BestOf3Games implements IMatch {

	private final Map<Player, GameScore> players = new HashMap<Player, GameScore>();
	private Player matchWinner = null;

	@Override
	public void addPlayer(Player player) throws TooManyPlayers {
		if(players.size() == 2) throw new TooManyPlayers();
		players.put(player, new GameScore());		
	}

	/**
	 * @return the winner of the game
	 * @throws MissingPlayers
	 * @throws MatchIsOver 
	 */
	@Override
	public Player playGame() throws MissingPlayers, MatchIsOver {
		checkIfCanPlay();
		Player winner = executeGame();
		updateMatchResults(winner);
		return winner;
	}

	private void updateMatchResults(Player winner) {
		if(null!=winner) {
			GameScore gs = players.get(winner);
			if(gs.hasWon()) {
				matchWinner = winner;
				matchWinner.win();
			}
		}
	}

	private Player executeGame() {
		Iterator<Player> it = players.keySet().iterator();
		Player winner = GameEngine.getWinner(it.next(), it.next());
		return winner;
	}

	private void checkIfCanPlay() throws MissingPlayers, MatchIsOver {
		if(players.size() < 2) throw new MissingPlayers();
		if(null != matchWinner) throw new MatchIsOver();
	}

	/**
	 * @return null if the match is not over yet
	 */
	@Override
	public Player getMatchWinner() {
		return matchWinner;
	}

	private class GameScore  {
		private int score = 0;
		boolean hasWon() {
			return ++score == 2;
		}
	}
}
