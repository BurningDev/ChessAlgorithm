/**
 * Author: BurningDev
 */
package com.burningdev.chess.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChessAlgorithm {
	private Chess chess;
	
	public ChessAlgorithm() {
		this.chess = new Chess();
	}
	
	public void playerMove(int beforeY, int beforeX, int nowY, int nowX) {
		beforeY = beforeY - 1;
		beforeX = beforeX - 1;
		nowY = nowY - 1;
		nowX = nowX - 1;
		
		this.chess.move(beforeY, beforeX, nowY, nowX);
		
		computerMove();
	}

	private void computerMove() {
		List<int[]> computerPositions = this.chess.getAllComputerPositions();
		List<Movement> movements = new ArrayList<Movement>();
		
		for(int[] computerPosition : computerPositions) {
			
			List<int[]> attackablePositions = this.chess.getAttackablePositions(computerPosition[0], computerPosition[1]);
			
			for(int[] attackablePosition : attackablePositions) {
				Chess newChess = new Chess();
				newChess.setSquares(cloneArray(this.chess.getSquares()));
				newChess.move(computerPosition[0], computerPosition[1], attackablePosition[0], attackablePosition[1]);
				
				Node parentNode = new Node(null, newChess, 2, Fraction.COMPUTER);
				parentNode.calcScore(false);
				parentNode.expandTreePlayer();
				
				System.out.println(parentNode.getScore());
				
				movements.add(new Movement(new int[] {computerPosition[0], computerPosition[1]}, new int[] {attackablePosition[0], attackablePosition[1]}, parentNode.getScore()));
			}
		}
		
		System.out.println();
		
		Collections.sort(movements, new Comparator<Movement>() {
			@Override
			public int compare(Movement b1, Movement b2) {
				if (b1.getScore() < b2.getScore()) {
					return -1;
				}

				if (b1.getScore() > b2.getScore()) {
					return 1;
				}

				return 0;
			}
		});
		
		Movement nextMove = movements.get(movements.size() - 1);
		this.chess.move(nextMove.getFrom()[0], nextMove.getFrom()[1], nextMove.getTo()[0], nextMove.getTo()[1]);
	}
	
	public ChessSquare[][] getSquares() {
		return this.chess.getSquares();
	}
	
	public void reset() {
		this.chess.reset();
	}
	
	public static ChessSquare[][] cloneArray(ChessSquare[][] src) {
	    int length = src.length;
	    ChessSquare[][] target = new ChessSquare[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
}
