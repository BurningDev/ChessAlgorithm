/**
 * Author: BurningDev
 */
package com.burningdev.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.burningdev.chess.core.Chess;
import com.burningdev.chess.core.Figure;
import com.burningdev.chess.core.Fraction;

public class Rook extends ChessPiece {
	public Rook(Position position, Fraction fraction) {
		this.position = position;
		this.fraction = fraction;
		this.alive = true;
	}
	
	public Rook(Position position, Fraction fraction, boolean alive) {
		this.position = position;
		this.fraction = fraction;
		this.alive = alive;
	}
	
	@Override
	public List<Position> getReachableFields(Fraction fraction, Chess chess) {
		List<Position> result = new ArrayList<>();
		for(int x = -1; x >= -7; x--) {
			if(chess.getChessPieceOnPosition(position.x + x, position.y) != null && chess.getChessPieceOnPosition(position.x + x, position.y).getFraction() != fraction) {
				addPostionIfValid(position.x + x, position.y, result, chess);
				break;
			}
			
			if(chess.pieceExistsAndAlive(position.x + x, position.y)) {
				break;
			}
			
			addPostionIfValid(position.x + x, position.y, result, chess);
		}
		
		for(int x = 1; x <= 7; x++) {
			if(chess.getChessPieceOnPosition(position.x + x, position.y) != null && chess.getChessPieceOnPosition(position.x + x, position.y).getFraction() != fraction) {
				addPostionIfValid(position.x + x, position.y, result, chess);
				break;
			}
			
			if(chess.pieceExistsAndAlive(position.x + x, position.y)) {
				break;
			}
			
			addPostionIfValid(position.x + x, position.y, result, chess);
		}
		
		for(int y = -1; y >= -7; y--) {
			if(chess.getChessPieceOnPosition(position.x, position.y + y) != null && chess.getChessPieceOnPosition(position.x, position.y + y).getFraction() != fraction) {
				addPostionIfValid(position.x, position.y + y, result, chess);
				break;
			}
			
			if(chess.pieceExistsAndAlive(position.x, position.y + y)) {
				break;
			}
			
			addPostionIfValid(position.x, position.y + y, result, chess);
		}
		
		for(int y = 1; y <= 7; y++) {
			if(chess.getChessPieceOnPosition(position.x, position.y + y) != null && chess.getChessPieceOnPosition(position.x, position.y + y).getFraction() != fraction) {
				addPostionIfValid(position.x, position.y + y, result, chess);
				break;
			}
			
			if(chess.pieceExistsAndAlive(position.x, position.y + y)) {
				break;
			}
			
			addPostionIfValid(position.x, position.y + y, result, chess);
		}
		
		return result;
	}

	@Override
	public Figure getFigure() {
		return Figure.ROOK;
	}
}
