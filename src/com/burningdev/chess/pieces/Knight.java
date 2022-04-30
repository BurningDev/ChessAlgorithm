/**
 * Author: BurningDev
 */
package com.burningdev.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.burningdev.chess.core.Chess;
import com.burningdev.chess.core.Figure;
import com.burningdev.chess.core.Fraction;

public class Knight extends ChessPiece {

	public Knight(Position position, Fraction fraction) {
		this.position = position;
		this.fraction = fraction;
		this.alive = true;
	}
	
	public Knight(Position position, Fraction fraction, boolean alive) {
		this.position = position;
		this.fraction = fraction;
		this.alive = alive;
	}
	
	@Override
	public List<Position> getReachableFields(Fraction fraction, Chess chess) {
		List<Position> result = new ArrayList<>();
		
		addPostionIfValid(position.x + 1, position.y + 2, result, chess);
		addPostionIfValid(position.x + 1, position.y - 2, result, chess);
		
		addPostionIfValid(position.x + 2, position.y + 1, result, chess);
		addPostionIfValid(position.x + 2, position.y - 1, result, chess);
		
		addPostionIfValid(position.x - 1, position.y + 2, result, chess);
		addPostionIfValid(position.x - 1, position.y - 2, result, chess);
		
		addPostionIfValid(position.x - 2, position.y + 1, result, chess);
		addPostionIfValid(position.x - 2, position.y - 1, result, chess);
		 
		return result;
	}

	@Override
	public Figure getFigure() {
		return Figure.KNIGHT;
	}
}
