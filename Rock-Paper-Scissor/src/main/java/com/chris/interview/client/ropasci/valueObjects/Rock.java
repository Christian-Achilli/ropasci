package com.chris.interview.client.ropasci.valueObjects;

public class Rock extends PlayerChoice {
    @Override
    public String getDescription() {
	return "Rock";
    }

    @Override
    public ChoiceCode getCode() {
	return ChoiceCode.R;
    }
}
