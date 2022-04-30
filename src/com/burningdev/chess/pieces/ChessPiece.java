/**
 * Author: BurningDev
 */
package com.burningdev.chess.pieces;

import java.util.List;

import com.burningdev.chess.core.Chess;
import com.burningdev.chess.core.Figure;
import com.burningdev.chess.core.Fraction;

public abstract class ChessPiece {
	protected Position position;
	protected Figure figure;
	protected Fraction fraction;
	protected boolean alive;
	
	public abstract List<Position> getReachableFields(Fraction fraction, Chess chess);

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public abstract Figure getFigure();

	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
	public Fraction getFraction() {
		return fraction;
	}

	public void setFraction(Fraction fraction) {
		this.fraction = fraction;
	}
	
	protected boolean addPostionIfValid(int x, int y, List<Position> positions, Chess chess) {
		if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			ChessPiece piece = chess.getChessPieceOnPosition(x, y);
			if(piece != null && piece.getFraction() == fraction) {
				return false;
			}
			
			positions.add(new Position(x, y));
			return true;
		} else {
			return false;
		}
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		this.alive = false;
	}
}