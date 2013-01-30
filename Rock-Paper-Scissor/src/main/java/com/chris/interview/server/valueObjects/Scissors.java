package com.chris.interview.server.valueObjects;

public class Scissors extends PlayerChoice {
    @Override
    public String getDescription() {
	return "Scissors";
    }
    @Override
    public String getCode() {
	return "S";
    }
}
