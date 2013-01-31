package com.chris.interview.client.ropasci.entities;

import com.chris.interview.client.ropasci.valueObjects.PlayerChoice;

public class Player {
	private final String name;
	private int score; // how many matches he's won, not how many games
	private PlayerChoice currentChoice;

	@SuppressWarnings("unused")
	private Player() {
		this.name = "not used";
	}

	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	public String getName() {
		return name;
	}

	public void win() {
		score++;
	}

	public int getScore() {
		return score;
	}

	public PlayerChoice getCurrentChoice() {
		return currentChoice;
	}

	public void setCurrentChoice(PlayerChoice choice) {
		this.currentChoice = choice;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}


}
