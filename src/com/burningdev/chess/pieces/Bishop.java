/**
 * Author: BurningDev
 */
package com.burningdev.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.burningdev.chess.core.Chess;
import com.burningdev.chess.core.Figure;
import com.burningdev.chess.core.Fraction;

public class Bishop extends ChessPiece {

	public Bishop(Position position, Fraction fraction) {
		this.position = position;
		this.fraction = fraction;
		this.alive = true;
	}
	
	public Bishop(Position position, Fraction fraction, boolean alive) {
		this.position = position;
		this.fraction = fraction;
		this.alive = alive;
	}
	
	@Override
	public List<Position> getReachableFields(Fraction fraction, Chess chess) {
		List<Position> result = new ArrayList<>();
		
		for(int i1 = 1; i1 < 7; i1++) {
			ChessPiece piece = chess.getChessPieceOnPosition(position.x + i1, position.y + i1);
			if(addPostionIfValid(position.x + i1, position.y + i1, result, chess) == false || (piece != null && piece.getFraction() != fraction)) {
				break;
			}
		}
		
		for(int i1 = 1; i1 < 7; i1++) {
			ChessPiece piece = chess.getChessPieceOnPosition(position.x + i1, position.y - i1);
			if(addPostionIfValid(position.x + i1, position.y - i1, result, chess) == false || (piece != null && piece.getFraction() != fraction)) {
				break;
			}
		}
		
		for(int i1 = 1; i1 < 7; i1++) {
			ChessPiece piece = chess.getChessPieceOnPosition(position.x - i1, position.y + i1);
			if(addPostionIfValid(position.x - i1, position.y + i1, result, chess) == false || (piece != null && piece.getFraction() != fraction)) {
				break;
			}
		}
		
		for(int i1 = 1; i1 < 7; i1++) {
			ChessPiece piece = chess.getChessPieceOnPosition(position.x - i1, position.y - i1);
			if(addPostionIfValid(position.x - i1, position.y - i1, result, chess) == false || (piece != null && piece.getFraction() != fraction)) {
				break;
			}
		}
		
		return result;
	}

	@Override
	public Figure getFigure() {
		return Figure.BISHOP;
	}

}
