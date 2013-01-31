package com.chris.interview.client;

import com.chris.interview.client.ropasci.factories.PlayerFactory;
import com.chris.interview.client.ropasci.useCases.PlayAgainstComputerController;
import com.chris.interview.client.ropasci.valueObjects.PlayerChoice;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class RopasciGame implements EntryPoint, IGameView {

	private final VerticalPanel mainPanel = new VerticalPanel();
	private final GameScoreTable gameScoreTable = new GameScoreTable();
	private final ScoreTable myScoreTable = new ScoreTable();
	private final ScoreTable computerScoreTable = new ScoreTable();
	private final Button startMatch = new Button("Start New Match!");
	private final HorizontalPanel informationPanel = new HorizontalPanel();
	private final HorizontalPanel controlsPanel = new HorizontalPanel();
	private final HorizontalPanel choiceButtons = new HorizontalPanel();
	private final TextBox inputPlayerName = new TextBox();
	
	
	private IGameView.Presenter presenter;

	public void onModuleLoad() {
		try {
			presenter = new GamePresenter(this, new PlayAgainstComputerController(new PlayerFactory()) );
		} catch (Exception e) {
			e.printStackTrace();
			Window.alert("Initialization error. Sorry you cannot play at this time.");
			return;
		}
		informationPanel.add(myScoreTable);
		informationPanel.add(new HTML("<div style=\'margin-left:50px; margin-right:50px; margin-top:16px; margin-bottom: 40px\'>VS</div>"));
		informationPanel.add(computerScoreTable);
		controlsPanel.add(startMatch);
		mainPanel.add(informationPanel);
		mainPanel.add(controlsPanel);
		mainPanel.add(choiceButtons);
		mainPanel.add(gameScoreTable);
		RootPanel.get().add(mainPanel);
		gameScoreTable.setVisible(false);
		choiceButtons.setVisible(false);
		informationPanel.setVisible(false);
		mainPanel.setVisible(false);
		final HorizontalPanel enrollPanel = new HorizontalPanel();
		final DialogBox dialogBox = new DialogBox();
		final HTML errorMessage = new HTML("<span style=\'color:red\'>Please, tell me your name</span>");
		dialogBox.setText("Let's play Rock-Paper-Scissors against Computer! State your nick:");
		dialogBox.add(inputPlayerName);
		dialogBox.setAnimationEnabled(true);
		final Button enrollButton = new Button("Go and Have Fun!");
		dialogBox.show();
		RootPanel.get().add(dialogBox);
		enrollPanel.add(enrollButton);
		enrollPanel.add(errorMessage);
		errorMessage.setVisible(false);
		RootPanel.get().add(enrollPanel);
		enrollButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if("".equals(inputPlayerName.getText())) {
					errorMessage.setVisible(true);
					return;
				}
				RootPanel.get().remove(enrollPanel);
				RootPanel.get().remove(dialogBox);
				enrollButton.setVisible(false);
				presenter.setPlayerName(inputPlayerName.getText());
				mainPanel.setVisible(true);
			}
		});
		startMatch.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.startNewMatch();
				startMatch.setVisible(false);
				informationPanel.setVisible(true);
				choiceButtons.setVisible(true);
				gameScoreTable.setVisible(true);
				gameScoreTable.reset();
			}
			
		});
	}

	@Override
	public void setPlayerScores(String myScoreVal, String opponentScoreVal) {
		myScoreTable.setScore(myScoreVal);
		computerScoreTable.setScore(opponentScoreVal);
	}

	@Override
	public void setPlayerNames(String myName, String opponentNm) {
		myScoreTable.setPlayerName(myName.toUpperCase());
		computerScoreTable.setPlayerName(opponentNm.toUpperCase());
	}

	@Override
	public void matchIsOver() {
		startMatch.setVisible(true);
		choiceButtons.setVisible(false);
	}

	@Override
	public void winGame() {
		choiceButtons.setVisible(true);
		gameScoreTable.win();
	}

	@Override
	public void lostGame() {
		choiceButtons.setVisible(true);
		gameScoreTable.lose();
	}

	@Override
	public void evenGame() {
		choiceButtons.setVisible(true);
		gameScoreTable.even();
	}

	@Override
	public void setLastGameChoices(PlayerChoice myChoice, PlayerChoice opponentChoice) {
		myScoreTable.setChoice(myChoice.getDescription().toUpperCase());
		computerScoreTable.setChoice(opponentChoice.getDescription().toUpperCase());	
	}

	@Override
	public void setAvailableChoices(PlayerChoice... choices) {
		for(PlayerChoice choice: choices) {
			choiceButtons.add(new ChoiceButton(choice));
		}
	}
	
	private class ChoiceButton extends Button {
		private final PlayerChoice choice;
		private ChoiceButton(final PlayerChoice choice) {
			super(choice.getDescription());
			this.choice = choice;
			this.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					choiceButtons.setVisible(false);
					presenter.play(choice.getCode().name());
					
				}
			});
		}
	}
	
	private class ScoreTable extends VerticalPanel {
		private final Label playerName = new Label("");
		private final Label playerScore = new Label("0");
		private final Label playerChoice = new Label("-");
		private ScoreTable() {
			add(playerName);
			add(playerScore);
			add(playerChoice);
		}
		void setPlayerName(String n) {
			playerName.setText(n);
		}
		void setScore(String s) {
			playerScore.setText(s);
		}
		void setChoice(String c) {
			playerChoice.setText(c);
		}
	}
	
	private class GameScoreTable extends VerticalPanel {
		private int winCounter = 0;
		private int loseCounter = 0;
		private int evenCounter = 0;
		private HorizontalPanel winHP = new HorizontalPanel();
		private HorizontalPanel loseHP = new HorizontalPanel();
		private HorizontalPanel evenHP = new HorizontalPanel();
		private final HTML youWin = new HTML("<div style=\'color:green; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom:5px\'>You Win:</div>");
		private final HTML youLose = new HTML("<div style=\'color:red; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>You Lose:</div>");
		private final HTML even = new HTML("<div style=\'color:grey; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>Even:</div>");
		private final HTML youWinS = new HTML("<div style=\'color:green; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px; te\'>"+winCounter+"</div>");
		private final HTML youLoseS = new HTML("<div style=\'color:red; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+loseCounter+"</div>");
		private final HTML evenS = new HTML("<div style=\'color:grey; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+evenCounter+"</div>");
		private GameScoreTable() {
			add(winHP);
			add(loseHP);
			add(evenHP);
			winHP.add(youWin);
			winHP.add(youWinS);
			loseHP.add(youLose);
			loseHP.add(youLoseS);
			evenHP.add(even);
			evenHP.add(evenS);
		}
		
		void win() {
			winCounter++;
			youWinS.setHTML("<div style=\'color:green; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+winCounter+"</div>");
		}
		
		void lose() {
			loseCounter++;
			youLoseS.setHTML("<div style=\'color:red; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+loseCounter+"</div>");
		}
		
		void even() {
			evenCounter++;
			evenS.setHTML("<div style=\'color:grey; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+evenCounter+"</div>");
		}
		
		void reset() {
			winCounter = 0;
			loseCounter = 0;
			evenCounter = 0;
			youWinS.setHTML("<div style=\'color:green; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+winCounter+"</div>");
			youLoseS.setHTML("<div style=\'color:red; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+loseCounter+"</div>");
			evenS.setHTML("<div style=\'color:grey; margin-left:5px; margin-right:5px; margin-top:5px; margin-bottom: 5px\'>"+evenCounter+"</div>");
		}
	}

}