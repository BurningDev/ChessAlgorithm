/**
 * Author: BurningDev
 */
package com.burningdev.chess.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import com.burningdev.chess.pieces.ChessPiece;
import com.burningdev.chess.utils.ImageUtil;

public class ChessboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<ChessPiece> pieces;

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

		for (ChessPiece piece : this.pieces) {
			if (piece.isAlive()) {
				graphics2d.drawImage(ImageUtil.getImageByFigure(piece.getFigure(), piece.getFraction()),
						(piece.getPosition().getX() * 80) + 8, (piece.getPosition().getY() * 80) + 5, 64, 64, null);
			}
		}
	}

	public void setPieces(List<ChessPiece> pieces) {
		this.pieces = pieces;
	}
}
