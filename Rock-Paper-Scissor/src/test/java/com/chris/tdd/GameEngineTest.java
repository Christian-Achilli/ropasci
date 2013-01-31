package com.chris.tdd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.chris.interview.client.ropasci.entities.Player;
import com.chris.interview.client.ropasci.exceptions.PlayerNameAlreadyInUse;
import com.chris.interview.client.ropasci.factories.ChoiceFactory;
import com.chris.interview.client.ropasci.factories.PlayerFactory;
import com.chris.interview.client.ropasci.services.GameEngine;
import com.chris.interview.client.ropasci.valueObjects.ChoiceCode;
import com.chris.interview.client.ropasci.valueObjects.PlayerChoice;

public class GameEngineTest {

	private PlayerFactory playerFactory;
	private ChoiceFactory choiceFactory;
	private Player playerA;
	private Player playerB;
	private PlayerChoice rock;
	private PlayerChoice paper;
	private PlayerChoice scissors;

	@Before
	public void setUp() {
		choiceFactory = new ChoiceFactory();
		rock = choiceFactory.createPlayerChoiceFromCode(ChoiceCode.R);
		scissors = choiceFactory.createPlayerChoiceFromCode(ChoiceCode.S);
		paper = choiceFactory.createPlayerChoiceFromCode(ChoiceCode.P);
		playerFactory = new PlayerFactory();
		try {
			playerA = playerFactory.createPlayer("A");
			playerB = playerFactory.createPlayer("B");
		} catch (PlayerNameAlreadyInUse e) {
			e.printStackTrace();
		}
	}

	@Test
	public void matchEven() {
		playerA.setCurrentChoice(rock);
		playerB.setCurrentChoice(rock);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(null, winner);
	}
	
	@Test
	public void matchVoid() {
		playerA.setCurrentChoice(rock);
		playerB.setCurrentChoice(null);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(null, winner);
	}

	@Test
	public void rockWinScissors() {
		playerA.setCurrentChoice(rock);
		playerB.setCurrentChoice(scissors);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(playerA, winner);
	}
	
	@Test
	public void scissorsWinPaper() {
		playerA.setCurrentChoice(scissors);
		playerB.setCurrentChoice(paper);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(playerA, winner);
	}
	
	@Test
	public void paperWinRock() {
		playerA.setCurrentChoice(paper);
		playerB.setCurrentChoice(rock);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(playerA, winner);
	}
	
	@Test
	public void scissorsLosesRock() {
		playerA.setCurrentChoice(scissors);
		playerB.setCurrentChoice(rock);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(playerB, winner);
	}
	
	@Test
	public void rockLosesPaper() {
		playerA.setCurrentChoice(rock);
		playerB.setCurrentChoice(paper);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(playerB, winner);
	}
	
	@Test
	public void paperLosesScissors() {
		playerA.setCurrentChoice(paper);
		playerB.setCurrentChoice(scissors);
		Player winner = GameEngine.getWinner(playerA, playerB);
		Assert.assertEquals(playerB, winner);
	}
	
}
