/**
 * Author: BurningDev
 */
package com.burningdev.chess.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.burningdev.chess.view.ChessView;

public class Main {
	public static void main(String[] args) {
		System.out.println("Developed by BurningDev\nVersion: 5.0");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		ChessView chessView = new ChessView();
		chessView.open();
	}
}
