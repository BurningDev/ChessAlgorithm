/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

import com.burningdev.chess.pieces.Position;

public class Movement {
	private Position from;
	private Position to;
	private int score;
	
	public Movement() {

	}
	
	public Movement(Position from, Position to, int score) {
		this.from = from;
		this.to = to;
		this.score = score;
	}
	
	public Position getFrom() {
		return from;
	}
	public void setFrom(Position from) {
		this.from = from;
	}
	public Position getTo() {
		return to;
	}
	public void setTo(Position to) {
		this.to = to;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
