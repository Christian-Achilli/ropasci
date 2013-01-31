package com.chris.interview.client.ropasci.repositories;

import com.chris.interview.client.ropasci.valueObjects.Paper;
import com.chris.interview.client.ropasci.valueObjects.PlayerChoice;
import com.chris.interview.client.ropasci.valueObjects.Rock;
import com.chris.interview.client.ropasci.valueObjects.Scissors;

public class ChoiceRepository {
    public PlayerChoice[] getAvailableChoices() {
	PlayerChoice[] choices = new PlayerChoice[3];
	choices[0] = new Scissors();
	choices[1] = new Rock();
	choices[2] = new Paper();
	return choices;
    }
}
