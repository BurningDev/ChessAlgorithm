/**
 * Author: BurningDev
 */
package com.burningdev.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.burningdev.chess.core.Chess;
import com.burningdev.chess.core.Figure;
import com.burningdev.chess.core.Fraction;

public class Pawn extends ChessPiece {

	public Pawn(Position position, Fraction fraction) {
		this.position = position;
		this.fraction = fraction;
		this.alive = true;
	}
	
	public Pawn(Position position, Fraction fraction, boolean alive) {
		this.position = position;
		this.fraction = fraction;
		this.alive = alive;
	}

	@Override
	public List<Position> getReachableFields(Fraction fraction, Chess chess) {
		List<Position> result = new ArrayList<>();

		if (fraction == Fraction.COMPUTER) {
			if (!chess.pieceExistsAndAlive(position.x, position.y - 1) ) {
				addPostionIfValid(position.x, position.y - 1, result, chess);
			}

			if (position.y == 6 && !chess.pieceExistsAndAlive(position.x, position.y - 2)) {
				addPostionIfValid(position.x, position.y - 2, result, chess);
			}
			
			if(chess.pieceExistsAndAlive(position.x - 1, position.y - 1) && chess.getChessPieceOnPosition(position.x - 1, position.y - 1).getFraction() == Fraction.PLAYER) {
				addPostionIfValid(position.x - 1, position.y - 1, result, chess);
			}
			
			if(chess.pieceExistsAndAlive(position.x + 1, position.y - 1) && chess.getChessPieceOnPosition(position.x + 1, position.y - 1).getFraction() == Fraction.PLAYER) {
				addPostionIfValid(position.x + 1, position.y - 1, result, chess);
			}
		} else {
			if (position.y == 1 && !chess.pieceExistsAndAlive(position.x, position.y + 2)) {
				addPostionIfValid(position.x, position.y + 2, result, chess);
			}

			if (!chess.pieceExistsAndAlive(position.x, position.y + 1)) {
				addPostionIfValid(position.x, position.y + 1, result, chess);
			}
			
			if(chess.pieceExistsAndAlive(position.x - 1, position.y + 1) && chess.getChessPieceOnPosition(position.x - 1, position.y + 1).getFraction() == Fraction.COMPUTER) {
				addPostionIfValid(position.x - 1, position.y + 1, result, chess);
			}
			
			if(chess.pieceExistsAndAlive(position.x + 1, position.y + 1) && chess.getChessPieceOnPosition(position.x + 1, position.y + 1).getFraction() == Fraction.COMPUTER) {
				addPostionIfValid(position.x + 1, position.y + 1, result, chess);
			}
		}

		return result;
	}

	@Override
	public Figure getFigure() {
		return Figure.PAWN;
	}
}
