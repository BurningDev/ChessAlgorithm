/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

public class Movement {
	private int[] from;
	private int[] to;
	private int score;
	
	public Movement() {

	}
	
	public Movement(int[] from, int[] to, int score) {
		this.from = from;
		this.to = to;
		this.score = score;
	}
	
	public int[] getFrom() {
		return from;
	}
	public void setFrom(int[] from) {
		this.from = from;
	}
	public int[] getTo() {
		return to;
	}
	public void setTo(int[] to) {
		this.to = to;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
