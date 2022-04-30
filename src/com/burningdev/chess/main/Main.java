/**
 * Author: BurningDev
 */
package com.burningdev.chess.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Logger;

import com.burningdev.chess.view.ChessView;

public class Main {
	public static final String VERSION = "6.0";
	
	public static void main(String[] args) {
		Configurator.defaultConfig().formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{level}]: {message}").activate();
		
		Logger.info("Developed by BurningDev\nVersion: " + VERSION);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			Logger.error(e, e.getMessage());
		}
		
		ChessView chessView = new ChessView();
		chessView.open();
	}
}
