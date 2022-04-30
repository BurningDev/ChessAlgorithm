/**
 * Author: BurningDev
 */
package com.burningdev.chess.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.burningdev.chess.core.ChessSquare;
import com.burningdev.chess.utils.ImageUtil;

public class ChessboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ChessSquare[][] squares;

	/**
	 * Create the panel.
	 */
	public ChessboardPanel() {

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;

		boolean white = true;

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (white) {
					graphics2d.setColor(Color.BLACK);
					white = false;
				} else {
					graphics2d.setColor(Color.WHITE);
					white = true;
				}

				graphics2d.fillRect(x * 80, y * 80, 80, 80);
			}
			if (white) {
				white = false;
			} else {
				white = true;
			}
		}

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(this.squares[y][x] != null) {
					graphics2d.drawImage(ImageUtil.getImageByFigur(squares[y][x].getFigure(), squares[y][x].getFraction()), (x * 80) + 8, (y * 80) + 5, 64, 64, null);
				}
			}
		}
	}

	public void setSquares(ChessSquare[][] squares) {
		this.squares = squares;
	}
}
