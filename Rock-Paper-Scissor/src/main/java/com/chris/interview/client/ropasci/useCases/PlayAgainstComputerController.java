package com.chris.interview.client.ropasci.useCases;

import com.chris.interview.client.ropasci.entities.IMatch;
import com.chris.interview.client.ropasci.entities.Player;
import com.chris.interview.client.ropasci.exceptions.MatchIsOver;
import com.chris.interview.client.ropasci.exceptions.MissingPlayers;
import com.chris.interview.client.ropasci.exceptions.PlayerNameAlreadyInUse;
import com.chris.interview.client.ropasci.factories.ChoiceFactory;
import com.chris.interview.client.ropasci.factories.MatchFactory;
import com.chris.interview.client.ropasci.factories.PlayerFactory;
import com.chris.interview.client.ropasci.valueObjects.ChoiceCode;
import com.chris.interview.client.ropasci.valueObjects.PlayerChoice;

public class PlayAgainstComputerController implements IPlayAgainstComputer {

	private IMatch currentMatch;
	private final PlayerFactory playerFactory;
	private final Player computer;
	private Player user;
	private Player lastGameWinner;
	
	public PlayAgainstComputerController(PlayerFactory playerFactory) throws PlayerNameAlreadyInUse {
		this.playerFactory = playerFactory;
		computer = playerFactory.createPlayer("Jarvis");
	}
	
	@Override
	public void enrollMe(String name) {
		try {
			user = playerFactory.createPlayer(name);
		} catch (PlayerNameAlreadyInUse e) {
			e.printStackTrace();
		}

	}

	@Override
	public void startNewMatch() {
		currentMatch = MatchFactory.createBestOf3GamesMatch(user, computer);
	}

	@Override
	public Boolean playGame(ChoiceCode choiceCode) {
		user.setCurrentChoice(ChoiceFactory.createPlayerChoiceFromCode(choiceCode));
		computer.setCurrentChoice(ChoiceFactory.randomChoice());
		try {
			lastGameWinner = currentMatch.playGame();
		} catch (MissingPlayers e) {
			e.printStackTrace();
		} catch (MatchIsOver e) {
			e.printStackTrace();
		}
		return null == lastGameWinner ? null : new Boolean(lastGameWinner.equals(user));
	}

	@Override
	public boolean isMatchOver() {
		return currentMatch.getMatchWinner() != null;
	}

	@Override
	public int getMyScore() {
		return user.getScore();
	}

	@Override
	public String getComputerName() {
		return computer.getName();
	}

	@Override
	public int getComputerScore() {
		return computer.getScore();
	}

	@Override
	public PlayerChoice getOpponentLastChoice() {
		return computer.getCurrentChoice();
	}

	@Override
	public PlayerChoice getMyLastChoice() {
		return user.getCurrentChoice();
	}

}
