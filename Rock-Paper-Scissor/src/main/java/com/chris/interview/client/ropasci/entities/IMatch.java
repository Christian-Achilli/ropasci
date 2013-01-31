package com.chris.interview.client.ropasci.entities;

import com.chris.interview.client.ropasci.exceptions.MatchIsOver;
import com.chris.interview.client.ropasci.exceptions.MissingPlayers;
import com.chris.interview.client.ropasci.exceptions.TooManyPlayers;

public interface IMatch {

	public void addPlayer(Player player) throws TooManyPlayers;

	/**
	 * @return the winner of the game
	 * @throws MissingPlayers
	 * @throws MatchIsOver 
	 */
	public Player playGame() throws MissingPlayers, MatchIsOver;

	/**
	 * @return null if the match is not over yet
	 */
	public Player getMatchWinner();

}