package com.chris.interview.server.factories;

import com.chris.interview.server.entities.Player;

public class PlayerFactory {
    public Player createPlayer(String playerName) {
	return new Player(playerName);
    }
}
