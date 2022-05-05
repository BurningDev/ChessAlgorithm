/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

import java.util.ArrayList;
import java.util.List;

import com.burningdev.chess.pieces.Bishop;
import com.burningdev.chess.pieces.ChessPiece;
import com.burningdev.chess.pieces.King;
import com.burningdev.chess.pieces.Knight;
import com.burningdev.chess.pieces.Pawn;
import com.burningdev.chess.pieces.Position;
import com.burningdev.chess.pieces.Rook;

public class Chess {
	private List<ChessPiece> pieces;

	private Fraction winner;
	
	public Chess() {
		reset();
	}

	public List<Position> getAllPositions(Fraction fraction) {
		List<Position> result = new ArrayList<>();

		for (ChessPiece piece : getAllPieces(fraction)) {
			if(!piece.isAlive()) {
				continue;
			}
			
			result.add(piece.getPosition());
		}

		return result;
	}

	public List<ChessPiece> getAllPieces(Fraction fraction) {
		List<ChessPiece> result = new ArrayList<>();

		for (ChessPiece piece : this.pieces) {
			if(!piece.isAlive()) {
				continue;
			}
			
			if (fraction == null) {
				result.add(piece);
			} else {
				if (piece.getFraction() == fraction) {
					result.add(piece);
				}
			}
		}

		return result;
	}

	public List<Position> getAllComputerPositions() {
		return getAllPositions(Fraction.COMPUTER);
	}

	public List<Position> getAllPlayerPositions() {
		return getAllPositions(Fraction.PLAYER);
	}

	public List<Position> getAttackablePositions(int y, int x) {
		List<Position> reachablePositions = new ArrayList<>();
		ChessPiece piece = getChessPieceOnPosition(x, y);

		if (piece == null) {
			return reachablePositions;
		}

		reachablePositions = piece.getReachableFields(piece.getFraction(), this);

		return reachablePositions;
	}

	public boolean isPositionValid(int y, int x) {
		return ((y >= 0 && y <= 7) && (x >= 0 && x <= 7));
	}

	public void reset() {
		this.winner = null;

		this.pieces = new ArrayList<>();
		
		this.pieces.add(new Knight(new Position(1, 7), Fraction.COMPUTER));
		this.pieces.add(new Knight(new Position(6, 7), Fraction.COMPUTER));
		this.pieces.add(new Knight(new Position(1, 0), Fraction.PLAYER));
		this.pieces.add(new Knight(new Position(6, 0), Fraction.PLAYER));

		this.pieces.add(new Rook(new Position(0, 7), Fraction.COMPUTER));
		this.pieces.add(new Rook(new Position(7, 7), Fraction.COMPUTER));
		this.pieces.add(new Rook(new Position(0, 0), Fraction.PLAYER));
		this.pieces.add(new Rook(new Position(7, 0), Fraction.PLAYER));
		
		this.pieces.add(new Bishop(new Position(2, 7), Fraction.COMPUTER));
		this.pieces.add(new Bishop(new Position(5, 7), Fraction.COMPUTER));
		this.pieces.add(new Bishop(new Position(2, 0), Fraction.PLAYER));
		this.pieces.add(new Bishop(new Position(5, 0), Fraction.PLAYER));
		
		this.pieces.add(new King(new Position(3, 7), Fraction.COMPUTER));
		this.pieces.add(new King(new Position(4, 0), Fraction.PLAYER));

		for (int x = 0; x < 8; x++) {
			this.pieces.add(new Pawn(new Position(x, 6), Fraction.COMPUTER));
			this.pieces.add(new Pawn(new Position(x, 1), Fraction.PLAYER));
		}
	}

	public Fraction getWinner() {
		return this.winner;
	}
	
	public void setWinner(Fraction winner) {
		this.winner = winner;
	}

	public int getScore(Fraction fraction) {
		int score = 0;

		List<ChessPiece> pieces = getAllPieces(fraction);
		List<ChessPiece> piecesEnemies = getAllPieces(getOtherFraction(fraction));

		boolean kingAlive = false;
		for (ChessPiece piece : pieces) {
			if (piece.getFigure() == Figure.KING && piece.isAlive() && piece.getFraction() == fraction) {
				kingAlive = true;
			}
		}

		if (!kingAlive) {			
			return -70;
		}

		for (ChessPiece piece : pieces) {
			if(!piece.isAlive()) {
				continue;
			}
			
			switch (piece.getFigure()) {
			case PAWN:
				score += 2;
				break;
			case KNIGHT:
				score += 4;
				break;
			case BISHOP:
				score += 4;
				break;
			case ROOK:
				score += 4;
				break;
			case QUEEN:
				score += 8;
				break;
			case KING:
				score += 16;
				break;
			}
		}

		for (ChessPiece pieceEnemy : piecesEnemies) {
			if(!pieceEnemy.isAlive()) {
				continue;
			}
			
			switch (pieceEnemy.getFigure()) {
			case PAWN:
				score -= 2;
				break;
			case KNIGHT:
				score -= 4;
				break;
			case BISHOP:
				score -= 4;
				break;
			case ROOK:
				score -= 4;
				break;
			case QUEEN:
				score -= 8;
				break;
			case KING:
				score -= 16;
				break;
			}
		}

		return score;
	}

	public Fraction getOtherFraction(Fraction fraction) {
		if (fraction == Fraction.COMPUTER) {
			return Fraction.PLAYER;
		} else {
			return Fraction.COMPUTER;
		}
	}

	public void move(int yBefore, int xBefore, int yNow, int xNow) {
		ChessPiece pieceNow = getChessPieceOnPosition(xNow, yNow);
		if(pieceNow != null && pieceNow.isAlive()) {
			pieceNow.kill();
		}
		
		ChessPiece piece = getChessPieceOnPosition(xBefore, yBefore);
		if (piece != null) {
			piece.setPosition(new Position(xNow, yNow));
		}
	}

	public ChessPiece getChessPieceOnPosition(int x, int y) {
		for (ChessPiece piece : this.pieces) {
			if (piece.getPosition().getX() == x && piece.getPosition().getY() == y && piece.isAlive()) {
				return piece;
			}
		}

		return null;
	}
	
	public void addChessPiece(ChessPiece piece) {
		this.pieces.add(piece);
	}
	
	public boolean pieceExistsAndAlive(int x, int y) {
		ChessPiece piece = getChessPieceOnPosition(x, y);
		
		if(piece == null) {
			return false;
		}
		
		if(piece.isAlive()) {
			return true;
		}
		
		return false;
	}
	
	public boolean pieceExistsAndAlive(Figure figure, Fraction fraction) {
		for(ChessPiece piece : this.pieces) {
			if(piece.getFigure() == figure && piece.isAlive() && piece.getFraction() == fraction) {
				return true;
			}
		}
		
		return false;
	}
	
	public Chess clone() {
		Chess newChess = new Chess();
		newChess.pieces.clear();
		for(ChessPiece piece : this.getAllPieces(null)) {
			if(piece instanceof Pawn) {
				newChess.addChessPiece(new Pawn(piece.getPosition(), piece.getFraction(), piece.isAlive()));
			}
			
			if(piece instanceof Knight) {
				newChess.addChessPiece(new Knight(piece.getPosition(), piece.getFraction(), piece.isAlive()));
			}
			
			if(piece instanceof Rook) {
				newChess.addChessPiece(new Rook(piece.getPosition(), piece.getFraction(), piece.isAlive()));
			}
			
			if(piece instanceof Bishop) {
				newChess.addChessPiece(new Bishop(piece.getPosition(), piece.getFraction(), piece.isAlive()));
			}
			
			if(piece instanceof King) {
				newChess.addChessPiece(new King(piece.getPosition(), piece.getFraction(), piece.isAlive()));
			}
		}
		
		return newChess;
	}
}
