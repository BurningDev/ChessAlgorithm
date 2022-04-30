/**
 * Author: BurningDev
 */package com.burningdev.chess.core;
public class ChessSquare {
	private Figure figure;
	private Fraction fraction;
	
	public ChessSquare(Figure figure, Fraction fraction) {
		this.figure = figure;
		this.fraction = fraction;
	}
	
	public ChessSquare() {

	}
	
	public Figure getFigure() {
		return figure;
	}
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	public Fraction getFraction() {
		return fraction;
	}
	public void setFraction(Fraction fraction) {
		this.fraction = fraction;
	}
}
