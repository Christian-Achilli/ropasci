package com.chris.interview.client.ropasci.useCases;

import com.chris.interview.client.ropasci.valueObjects.ChoiceCode;
import com.chris.interview.client.ropasci.valueObjects.PlayerChoice;

public interface IPlayAgainstComputer {

	public void enrollMe(String name);
	
	public void startNewMatch();
	
	/**
	 * @param choiceCode
	 * @return null is even, true if I win, false if the computer win
	 */
	public Boolean playGame(ChoiceCode choiceCode); 
	
	public boolean isMatchOver();
	
	public int getMyScore();

	public String getComputerName();

	public int getComputerScore();

	public PlayerChoice getOpponentLastChoice();
	
	public PlayerChoice getMyLastChoice();
	
}
