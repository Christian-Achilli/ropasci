package com.chris.interview.client.ropasci.services;

import com.chris.interview.client.ropasci.entities.Player;
import com.chris.interview.client.ropasci.valueObjects.ChoiceCode;

public class GameEngine {
	public static Player getWinner(Player a, Player b) {
		if(null == a.getCurrentChoice() || null == b.getCurrentChoice()) 
			return null;
		ChoiceCode aChoice = a.getCurrentChoice().getCode();
		ChoiceCode bChoice = b.getCurrentChoice().getCode();
		if(aChoice == bChoice) 
			return null;
		Player winner = null;
		if(
				(ChoiceCode.R.equals(aChoice) && ChoiceCode.S.equals(bChoice)) ||
				(ChoiceCode.S.equals(aChoice) && ChoiceCode.P.equals(bChoice)) ||
				(ChoiceCode.P.equals(aChoice) && ChoiceCode.R.equals(bChoice)) 
				)
			winner = a;
		else
			winner = b;
		return winner;
	}
}
