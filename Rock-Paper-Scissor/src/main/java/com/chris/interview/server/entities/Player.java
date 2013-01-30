package com.chris.interview.server.entities;

public class Player {
    private final String name;
    private int score;
    @SuppressWarnings("unused")
    private Player(){
	this.name="not used";
    }
    public Player(String name) {
	this.name=name;
	this.score=0;
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
}
