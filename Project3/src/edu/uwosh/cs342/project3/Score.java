package edu.uwosh.cs342.project3;

public class Score {
	private static int score;

	public void increment() {
		score++;
	}

	public void reset() {
		score = 0;
	}

	public int get() {
		return score;
	}

}