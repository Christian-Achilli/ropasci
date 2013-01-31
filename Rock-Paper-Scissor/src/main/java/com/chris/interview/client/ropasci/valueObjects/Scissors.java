package com.chris.interview.client.ropasci.valueObjects;

public class Scissors extends PlayerChoice {
    @Override
    public String getDescription() {
	return "Scissors";
    }

    @Override
    public ChoiceCode getCode() {
	return ChoiceCode.S;
    }
}
