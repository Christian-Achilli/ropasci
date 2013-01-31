package com.chris.interview.client.ropasci.valueObjects;

public enum ChoiceCode {
    S, P, R;

	public static ChoiceCode fromString(String choiceCode) {
		for(ChoiceCode choice: values()) 
			if(choice.name().equals(choiceCode))
				return choice;
		return null;
	}
}
