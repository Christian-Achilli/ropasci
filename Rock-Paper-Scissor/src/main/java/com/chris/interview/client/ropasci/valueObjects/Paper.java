package com.chris.interview.client.ropasci.valueObjects;

public class Paper extends PlayerChoice {
    @Override
    public String getDescription() {
	return "Paper";
    }

    @Override
    public ChoiceCode getCode() {
	return ChoiceCode.P;
    }
}
