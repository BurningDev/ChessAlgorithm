/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node parentNode;
	private List<Node> childrenNodes;
	private int score;
	private Chess chess;
	private int depth;
	private Fraction fraction;

	public Node(Node parentNode, Chess chess, int depth, Fraction fraction) {
		this.parentNode = parentNode;
		this.chess = chess;
		this.depth = depth;
		this.fraction = fraction;

		this.childrenNodes = new ArrayList<Node>();
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public List<Node> getChildNotes() {
		return childrenNodes;
	}

	public void setChildNotes(List<Node> childNotes) {
		this.childrenNodes = childNotes;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Chess getChess() {
		return chess;
	}

	public void setChess(Chess chess) {
		this.chess = chess;
	}

	public Fraction getFraction() {
		return this.fraction;
	}

	public int getDepth() {
		return this.depth;
	}

	public void calcScore(boolean player) {
		this.score = this.chess.getScore(this.fraction);

		if (player == true) {
			
			if (this.parentNode.getScore() > this.score) {
				this.parentNode.setScore(this.score);
			}
		}
	}

	public List<Chess> getPossibilities(Fraction fraction) {
		List<Chess> result = new ArrayList<Chess>();

		List<int[]> playerPositions = null;

		if (fraction == Fraction.PLAYER) {
			playerPositions = this.chess.getAllPlayerPositions();
		} else if (fraction == Fraction.COMPUTER) {
			playerPositions = this.chess.getAllComputerPositions();
		}

		for (int[] playerPosition : playerPositions) {
			if (this.chess.getAttackablePositions(playerPosition[0], playerPosition[1]) == null) {
				return result;
			}

			List<int[]> attackablePositions = this.chess.getAttackablePositions(playerPosition[0], playerPosition[1]);
			for (int[] attackablePosition : attackablePositions) {
				Chess tempChess = new Chess();
				tempChess.setSquares(ChessAlgorithm.cloneArray(this.chess.getSquares()));
				tempChess.move(playerPosition[0], playerPosition[1], attackablePosition[0], attackablePosition[1]);
				result.add(tempChess);
			}
		}

		return result;
	}

	public void expandTree() {
		if (this.depth >= 1) {

			List<Chess> possibilities = getPossibilities(fraction);

			for (Chess chess : possibilities) {
				Node node = new Node(this, chess, (depth - 1), getOtherFraction(fraction));
				this.childrenNodes.add(node);
				node.expandTree();
				node.calcScore(true);
			}
		}
	}

	public void expandTreePlayer() {
		List<int[]> playerPositions = this.chess.getAllPlayerPositions();

		for (int[] playerPosition : playerPositions) {

			List<int[]> attackablePositions = this.chess.getAttackablePositions(playerPosition[0], playerPosition[1]);

			for (int[] attackablePosition : attackablePositions) {
				Chess newChess = new Chess();
				newChess.setSquares(ChessAlgorithm.cloneArray(this.chess.getSquares()));
				newChess.move(playerPosition[0], playerPosition[1], attackablePosition[0], attackablePosition[1]);

				Node childNode = new Node(this, newChess, 1, Fraction.PLAYER);
				childNode.calcScore(true);
			}
		}
	}

	private Fraction getOtherFraction(Fraction fraction) {
		if (fraction == Fraction.COMPUTER) {
			return Fraction.PLAYER;
		} else {
			return Fraction.COMPUTER;
		}
	}
}
