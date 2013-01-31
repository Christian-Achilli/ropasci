package com.chris.interview.client;

import com.chris.interview.client.ropasci.valueObjects.PlayerChoice;

public interface IGameView {

	public void setPlayerScores(String myScore, String opponentScore);
	public void setPlayerNames(String myName, String opponentName);
	public void matchIsOver();
	public void winGame();
	public void lostGame();
	public void evenGame();
	public void setLastGameChoices(PlayerChoice myChoice, PlayerChoice opponentChoice);
	public void setAvailableChoices(PlayerChoice... choices);
	
	interface Presenter {
		public void startNewMatch();
		public void stopCurrentMatch();
		public void setPlayerName(String name);
		public void play(String choiceCode);
	}

	
}
