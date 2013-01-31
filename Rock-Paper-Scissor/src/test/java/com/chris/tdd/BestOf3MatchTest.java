package com.chris.tdd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.chris.interview.client.ropasci.entities.BestOf3Games;
import com.chris.interview.client.ropasci.entities.IMatch;
import com.chris.interview.client.ropasci.entities.Player;
import com.chris.interview.client.ropasci.exceptions.PlayerNameAlreadyInUse;
import com.chris.interview.client.ropasci.factories.ChoiceFactory;
import com.chris.interview.client.ropasci.factories.PlayerFactory;
import com.chris.interview.client.ropasci.valueObjects.ChoiceCode;

public class BestOf3MatchTest {

	private Player a;
	private Player b;
	
	@Before
	public void setUp() {
		PlayerFactory playerFactory = new PlayerFactory();
		try {
			a = playerFactory.createPlayer("A");
			b = playerFactory.createPlayer("B");
		} catch (PlayerNameAlreadyInUse e) {
			e.printStackTrace();
		}
	}
	
	// maybe I need a game controller to run the matches
	
	@Test
	public void playBestOf3Test() throws Exception {
		a.setCurrentChoice(ChoiceFactory.createPlayerChoiceFromCode(ChoiceCode.R));
		b.setCurrentChoice(ChoiceFactory.createPlayerChoiceFromCode(ChoiceCode.S));
		IMatch match = new BestOf3Games();
		match.addPlayer(a);
		match.addPlayer(a);
		match.addPlayer(b);
		Player gameWinner = match.playGame();
		Assert.assertEquals(a, gameWinner);
		Assert.assertEquals(null, match.getMatchWinner());
		gameWinner = match.playGame();
		Assert.assertEquals(a, gameWinner);
		Assert.assertEquals(a, match.getMatchWinner());
		Assert.assertEquals(1, a.getScore());
		Assert.assertEquals(0, b.getScore());
	}
	
}
