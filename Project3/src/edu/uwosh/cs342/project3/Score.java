package edu.uwosh.cs342.project3;

public class Score {
	private static int score;
	private static String userName;

	public void increment(int pts) {
		score += pts;
	}

	public void reset() {
		score = 0;
	}

	public int get() {
		return score;
	}

	public void setUsername(String userName) {
		Score.userName = userName;
	}
	public String getUsername(){
		return userName;
	}

}
