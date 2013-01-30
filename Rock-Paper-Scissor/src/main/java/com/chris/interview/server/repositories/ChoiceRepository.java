package com.chris.interview.server.repositories;

import com.chris.interview.server.valueObjects.Paper;
import com.chris.interview.server.valueObjects.PlayerChoice;
import com.chris.interview.server.valueObjects.Rock;
import com.chris.interview.server.valueObjects.Scissors;

public class ChoiceRepository {
    public PlayerChoice[] getAvailableChoices() {
	PlayerChoice[] choices = new PlayerChoice[3];
	choices[0] = new Scissors();
	choices[1] = new Rock();
	choices[2] = new Paper();
	return choices;
    }
}
