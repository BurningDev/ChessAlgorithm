/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.burningdev.chess.pieces.ChessPiece;
import com.burningdev.chess.pieces.Position;

public class ChessAlgorithm {
	private Chess chess;

	public ChessAlgorithm() {
		this.chess = new Chess();
	}

	public int playerMove(int beforeY, int beforeX, int nowY, int nowX) {
		beforeY = beforeY - 1;
		beforeX = beforeX - 1;
		nowY = nowY - 1;
		nowX = nowX - 1;

		ChessPiece pieceToMove = this.chess.getChessPieceOnPosition(beforeX, beforeY);
		
		if(pieceToMove == null) {
			return 0;
		}
		
		if (pieceToMove != null
				&& pieceToMove.getFraction() == Fraction.COMPUTER) {
			return 1;
		}
		
		if (beforeX == nowX && beforeY == nowY) {
			return 2;
		}
		
		if (this.chess.getChessPieceOnPosition(nowX, nowY) != null
				&& this.chess.getChessPieceOnPosition(nowX, nowY).getFraction() == Fraction.PLAYER) {
			return 3;
		}
		
		boolean moveInvalid = true;
		for(Position position : pieceToMove.getReachableFields(Fraction.PLAYER, chess)) {
			if(position.getX() == nowX && position.getY() == nowY) {
				moveInvalid = false;
			}
		}
		
		if(moveInvalid) {
			return 4;
		}

		this.chess.move(beforeY, beforeX, nowY, nowX);

		if (!this.chess.pieceExistsAndAlive(Figure.KING, Fraction.COMPUTER)) {
			this.chess.setWinner(Fraction.PLAYER);
		}

		return -1;
	}

	public void computerMove() {
		if (!this.chess.pieceExistsAndAlive(Figure.KING, Fraction.PLAYER)) {
			this.chess.setWinner(Fraction.COMPUTER);
			return;
		}

		List<Position> computerPositions = this.chess.getAllComputerPositions();
		List<Movement> movements = new ArrayList<>();

		Node.nodes = 0;

		for (Position computerPosition : computerPositions) {

			List<Position> attackablePositions = this.chess.getAttackablePositions(computerPosition.getY(),
					computerPosition.getX());

			for (Position attackablePosition : attackablePositions) {
				// A copy of chess is created
				Chess newChess = this.chess.clone();

				newChess.move(computerPosition.getY(), computerPosition.getX(), attackablePosition.getY(),
						attackablePosition.getX());

				Node parentNode = new Node(null, newChess, 2, Fraction.PLAYER, true);
				parentNode.setParentNode(parentNode);
				parentNode.play();

				int score = 0;
				for (Node node : parentNode.getChildren()) {
					score += node.getScore();
				}

				movements.add(new Movement(computerPosition, attackablePosition, score));
			}
		}

		Collections.sort(movements, new Comparator<Movement>() {
			@Override
			public int compare(Movement m1, Movement m2) {
				int score1 = m1.getScore();
				int score2 = m2.getScore();

				if (score1 < score2) {
					return -1;
				}

				if (score1 > score2) {
					return 1;
				}

				return 0;
			}
		});

		Movement nextMove = movements.get(movements.size() - 1);
		this.chess.move(nextMove.getFrom().getY(), nextMove.getFrom().getX(), nextMove.getTo().getY(),
				nextMove.getTo().getX());
		
		if (!this.chess.pieceExistsAndAlive(Figure.KING, Fraction.PLAYER)) {
			this.chess.setWinner(Fraction.COMPUTER);
		}
	}

	public List<ChessPiece> getPieces() {
		return this.chess.getAllPieces(null);
	}

	public void reset() {
		this.chess.reset();
	}

	public Fraction getWinner() {
		return this.chess.getWinner();
	}
}
