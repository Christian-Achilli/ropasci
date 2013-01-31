package com.chris.interview.client;

import com.chris.interview.client.ropasci.factories.ChoiceFactory;
import com.chris.interview.client.ropasci.useCases.IPlayAgainstComputer;
import com.chris.interview.client.ropasci.valueObjects.ChoiceCode;

public class GamePresenter implements IGameView.Presenter{

	private IGameView view;
	private IPlayAgainstComputer useCase;

	public GamePresenter(IGameView view, IPlayAgainstComputer useCase) {
		this.view = view;
		this.useCase = useCase;
		view.setAvailableChoices(ChoiceFactory.getAvailableChoices());
	}

	@Override
	public void startNewMatch() {
		useCase.startNewMatch();
	}

	@Override
	public void stopCurrentMatch() {
		view.matchIsOver();
	}

	@Override
	public void setPlayerName(String name) {
		useCase.enrollMe(name);
		view.setPlayerNames(name, useCase.getComputerName());
	}

	@Override
	public void play(String choiceCode) {
		Boolean amIWinner = getGameResult(choiceCode);
		updateGameResult(amIWinner);
		updateMatchStatus(amIWinner);

	}

	private Boolean getGameResult(String choiceCode) {
		Boolean amIWinner = useCase.playGame(ChoiceCode.fromString(choiceCode));
		view.setPlayerScores(""+useCase.getMyScore(), ""+useCase.getComputerScore());
		return amIWinner;
	}

	private void updateGameResult(Boolean amIWinner) {
		view.setLastGameChoices(useCase.getMyLastChoice(), useCase.getOpponentLastChoice());
		if(null == amIWinner)
			view.evenGame();
		else if(amIWinner)
			view.winGame();
		else 
			view.lostGame();
	}

	private void updateMatchStatus(Boolean amIWinner) {
		if(useCase.isMatchOver()) {
			view.matchIsOver();
		}
	}

}
