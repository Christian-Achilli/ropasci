package com.chris.interview.client.ropasci.factories;

import java.util.ArrayList;
import java.util.List;

import com.chris.interview.client.ropasci.entities.Player;
import com.chris.interview.client.ropasci.exceptions.PlayerNameAlreadyInUse;

public class PlayerFactory {
	private List<String> activePlayerNames;

	public PlayerFactory() {
		activePlayerNames = new ArrayList<String>();
	}

	public Player createPlayer(String playerName) throws PlayerNameAlreadyInUse {
		if (activePlayerNames.contains(playerName.toLowerCase()))
			throw new PlayerNameAlreadyInUse();
		return new Player(playerName);
	}
}
