/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

import java.util.ArrayList;
import java.util.List;

public class Chess {
	private ChessSquare[][] squares;

	private Fraction winner;

	public Chess() {
		reset();
	}

	public List<int[]> getAllPositions(Fraction fraction) {
		List<int[]> result = new ArrayList<int[]>();

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				ChessSquare squares = this.squares[y][x];
				if (squares != null) {
					if (squares.getFraction() == fraction) {
						result.add(new int[] { y, x });
					}
				}
			}
		}

		return result;
	}

	private List<Figure> getAllFiguren(Fraction fraction) {
		List<Figure> result = new ArrayList<Figure>();

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				ChessSquare squares = this.squares[y][x];
				if (squares != null) {
					if (squares.getFraction() == fraction) {
						result.add(this.squares[y][x].getFigure());
					}
				}
			}
		}

		return result;
	}

	public List<int[]> getAllComputerPositions() {
		return getAllPositions(Fraction.COMPUTER);
	}

	public List<int[]> getAllPlayerPositions() {
		return getAllPositions(Fraction.PLAYER);
	}

	public List<int[]> getAttackablePositions(int y, int x) {
		ChessSquare squares = this.squares[y][x];

		List<int[]> result = new ArrayList<int[]>();

		if (this.squares[y][x] == null) {
			return null;
		}

		switch (squares.getFigure()) {
		case PAWN:
			int tempX;
			int tempY;

			if (squares.getFraction() == Fraction.COMPUTER) {
				tempY = y - 1;
				tempX = x + 1;
				if (isPositionValid(tempY, tempX)) {
					if (this.squares[tempY][tempX] != null) {
						if (this.squares[tempY][tempX].getFraction() != Fraction.COMPUTER) {
							result.add(new int[] { tempY, tempX });
						}
					}
				}
				tempY = y - 1;
				tempX = x - 1;
				if (isPositionValid(tempY, tempX)) {
					if (this.squares[tempY][tempX] != null) {
						if (this.squares[tempY][tempX].getFraction() != Fraction.COMPUTER) {
							result.add(new int[] { tempY, tempX });
						}
					}
				}

				tempY = y - 1;
				tempX = x;
				if (squares.getFraction() == Fraction.COMPUTER) {
					if (isPositionValid(tempY, tempX)) {
						if (this.squares[tempY][tempX] == null) {
							result.add(new int[] { tempY, tempX });
						}
					}
				}
			} else {
				tempY = y + 1;
				tempX = x + 1;
				if (isPositionValid(tempY, tempX)) {
					if (this.squares[tempY][tempX] != null) {
						if (this.squares[tempY][tempX].getFraction() != Fraction.PLAYER) {
							result.add(new int[] { tempY, tempX });
						}
					}
				}
				tempY = y + 1;
				tempX = x - 1;
				if (isPositionValid(tempY, tempX)) {
					if (this.squares[tempY][tempX] != null) {
						if (this.squares[tempY][tempX].getFraction() != Fraction.PLAYER) {
							result.add(new int[] { tempY, tempX });
						}
					}
				}

				tempY = y + 1;
				tempX = x;
				if (squares.getFraction() == Fraction.PLAYER) {
					if (isPositionValid(tempY, tempX)) {
						if (this.squares[tempY][tempX] == null) {
							result.add(new int[] { tempY, tempX });
						}
					}
				}
			}
			break;
		case KING:
			List<int[]> kingMovements = new ArrayList<int[]>();

			kingMovements.add(new int[] { y, x + 1 });
			kingMovements.add(new int[] { y, x - 1 });
			kingMovements.add(new int[] { y + 1, x });
			kingMovements.add(new int[] { y - 1, x });
			kingMovements.add(new int[] { y + 1, x + 1 });
			kingMovements.add(new int[] { y + 1, x - 1 });
			kingMovements.add(new int[] { y - 1, x + 1 });
			kingMovements.add(new int[] { y - 1, x - 1 });

			if (squares.getFraction() == Fraction.COMPUTER) {
				for (int[] kingMovement : kingMovements) {
					if (isPositionValid(kingMovement[0], kingMovement[1])) {
						if (this.squares[kingMovement[0]][kingMovement[1]] != null) {
							if (this.squares[kingMovement[0]][kingMovement[1]]
									.getFraction() != Fraction.COMPUTER) {
								result.add(new int[] { kingMovement[0], kingMovement[1] });
							}
						} else {
							result.add(new int[] { kingMovement[0], kingMovement[1] });
						}
					}
				}
			} else {
				for (int[] kingMovement : kingMovements) {
					if (isPositionValid(kingMovement[0], kingMovement[1])) {
						if (this.squares[kingMovement[0]][kingMovement[1]] != null) {
							if (this.squares[kingMovement[0]][kingMovement[1]]
									.getFraction() != Fraction.PLAYER) {
								result.add(new int[] { kingMovement[0], kingMovement[1] });
							}
						} else {
							result.add(new int[] { kingMovement[0], kingMovement[1] });
						}
					}
				}
			}
			break;
		case KNIGHT:
			List<int[]> knightMovements = new ArrayList<int[]>();

			knightMovements.add(new int[] { y + 2, x + 1 });
			knightMovements.add(new int[] { y + 2, x - 1 });
			knightMovements.add(new int[] { y - 2, x + 1 });
			knightMovements.add(new int[] { y - 2, x - 1 });
			knightMovements.add(new int[] { y + 1, x + 2 });
			knightMovements.add(new int[] { y - 1, x - 2 });
			knightMovements.add(new int[] { y + 1, x - 2 });
			knightMovements.add(new int[] { y - 1, x + 2 });

			for (int[] knightMovement : knightMovements) {
				if (isPositionValid(knightMovement[0], knightMovement[1])) {
					if (this.squares[knightMovement[0]][knightMovement[1]] != null) {
						if (this.squares[knightMovement[0]][knightMovement[1]].getFraction() != Fraction.COMPUTER) {
							result.add(new int[] { knightMovement[0], knightMovement[1] });
						}
					} else {
						result.add(new int[] { knightMovement[0], knightMovement[1] });
					}
				}
			}
			break;
		default:

		}

		return result;

	}

	public boolean isPositionValid(int y, int x) {
		if ((y >= 0 && y <= 7) && (x >= 0 && x <= 7)) {
			return true;
		}

		return false;
	}

	public ChessSquare[][] getSquares() {
		return this.squares;
	}

	public void setSquares(ChessSquare[][] squares) {
		this.squares = squares;
	}

	public void reset() {
		this.squares = new ChessSquare[8][8];
		this.winner = null;

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				this.squares[y][x] = null;
			}
		}

		this.squares[7][1] = new ChessSquare(Figure.KNIGHT, Fraction.COMPUTER);
		this.squares[7][6] = new ChessSquare(Figure.KNIGHT, Fraction.COMPUTER);
		this.squares[0][1] = new ChessSquare(Figure.KNIGHT, Fraction.PLAYER);
		this.squares[0][6] = new ChessSquare(Figure.KNIGHT, Fraction.PLAYER);

		this.squares[7][4] = new ChessSquare(Figure.KING, Fraction.COMPUTER);
		this.squares[0][4] = new ChessSquare(Figure.KING, Fraction.PLAYER);

		for (int x = 0; x < 8; x++) {
			this.squares[6][x] = new ChessSquare(Figure.PAWN, Fraction.COMPUTER);
		}

		for (int x = 0; x < 8; x++) {
			this.squares[1][x] = new ChessSquare(Figure.PAWN, Fraction.PLAYER);
		}
	}

	public Fraction getWinner() {
		return this.winner;
	}

	public int getScore(Fraction fraction) {
		int score = 0;

		List<Figure> figures = getAllFiguren(fraction);
		List<Figure> figuresEnemies = getAllFiguren(getOtherFraction(fraction));

		if (!figures.contains(Figure.KING)) {
			return -5;
		}

		for (Figure figure : figures) {
			switch (figure) {
			case PAWN:
				score += 1;
				break;
			case KNIGHT:
				score += 2;
				break;
			case BISHOP:
				score += 2;
				break;
			case ROOK:
				score += 2;
				break;
			case QUEEN:
				score += 4;
				break;
			case KING:
				score += 8;
				break;
			}
		}

		for (Figure figureEnemy : figuresEnemies) {
			switch (figureEnemy) {
			case PAWN:
				score -= 1;
				break;
			case KNIGHT:
				score -= 2;
				break;
			case BISHOP:
				score -= 2;
				break;
			case ROOK:
				score -= 2;
				break;
			case QUEEN:
				score -= 4;
				break;
			case KING:
				score -= 8;
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
		ChessSquare squares = this.squares[yBefore][xBefore];
		this.squares[yNow][xNow] = squares;
		this.squares[yBefore][xBefore] = null;
	}
}
