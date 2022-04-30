/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

import java.util.ArrayList;
import java.util.List;

import com.burningdev.chess.pieces.ChessPiece;
import com.burningdev.chess.pieces.Position;

public class Node {
	private Chess chess;
	private Fraction fraction;
	
	private Node parentNode;
	private List<Node> childrenNodes;
	private int depth;
	
	private int highestScore = -100;
	private int lowestScore = 100;
	private int score = 0;
	
	private boolean mainNode;
	
	public static int nodes = 0;

	public Node(Node parentNode, Chess chess, int depth, Fraction fraction, boolean mainNode) {
		Node.nodes++;
		
		this.parentNode = parentNode;
		this.chess = chess;
		this.depth = depth;
		this.fraction = fraction;
		this.mainNode = mainNode;

		this.childrenNodes = new ArrayList<>();
	}
	
	public void play() {
		List<ChessPiece> pieces = chess.getAllPieces(fraction);
		
		for(ChessPiece piece : pieces) {
			List<Position> positions = piece.getReachableFields(fraction, chess);
			
			for(Position position : positions) {
				ChessPiece otherPiece = chess.getChessPieceOnPosition(position.getX(), position.getY());
				
				Chess newChess = chess.clone();
				boolean moved = false;
				if(otherPiece == null || (otherPiece != null && otherPiece.getFraction() != fraction)) {
					moved = true;
					newChess.move(piece.getPosition().getY(), piece.getPosition().getX(), position.getY(), position.getX());
					
					int score = newChess.getScore(Fraction.COMPUTER);
					this.score = score;
					if(score > highestScore) {
						highestScore = score;
					}
					
					if(score < lowestScore) {
						lowestScore = score;
					}
				}
				
				if(depth > 0 && moved) {
					Node newNode = new Node(this, newChess, depth - 1, chess.getOtherFraction(fraction), false);
					newNode.play();
				}
			}
		}
		
		parentNode.addChildren(this);
	}
	
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	public int getHighestScore() {
		return this.highestScore;
	}
	
	public int getLowestScore() {
		return this.lowestScore;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public boolean isMainNode() {
		return this.mainNode;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	public void addChildren(Node node) {
		if(parentNode != null && mainNode == false) {
			parentNode.addChildren(node);
		} else if(mainNode == true) {
			this.childrenNodes.add(node);
		}
	}
	
	public List<Node> getChildren() {
		return childrenNodes;
	}
}
