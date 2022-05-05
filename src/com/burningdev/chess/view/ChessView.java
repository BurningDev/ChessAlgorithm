/**
 * Author: BurningDev
 */
package com.burningdev.chess.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

import com.burningdev.chess.core.ChessAlgorithm;
import com.burningdev.chess.core.Fraction;

public class ChessView {

	private JFrame frame;
	private ChessAlgorithm chessAlgorithm;

	private ChessboardPanel squaresPanel;

	private int mode;
	private int[] firstPosition;
	private int[] finishPosition;

	public void open() {
		frame.setVisible(true);
	}

	public ChessView() {
		initialize();
	}

	private void initialize() {
		this.chessAlgorithm = new ChessAlgorithm();
		this.mode = 0;

		frame = new JFrame();
		frame.setBounds(100, 100, 646, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setTitle("Chess - by BurningDev");

		squaresPanel = new ChessboardPanel();
		squaresPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (chessAlgorithm.getWinner() != null) {
					return;
				}

				if (SwingUtilities.isRightMouseButton(e)) {
					mode = 0;
					return;
				}

				if (mode == 0 || mode == 1) {
					firstPosition = calcPosition(e.getY(), e.getX());
					mode = 2;
				} else if (mode == 2) {
					finishPosition = calcPosition(e.getY(), e.getX());

					mode = 1;

					int errorCode = chessAlgorithm.playerMove(firstPosition[0], firstPosition[1], finishPosition[0], finishPosition[1]);
					
					if(errorCode == 1) {
						mode = 0;
						JOptionPane.showMessageDialog(null, "You cannot move the pieces of the opponent.", "Warn",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					else if(errorCode == 2) {
						mode = 0;
						JOptionPane.showMessageDialog(null, "You must select another field..", "Warn",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					else if(errorCode == 3) {
						mode = 0;
						JOptionPane.showMessageDialog(null, "Your piece is on the field.", "Warn",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					else if(errorCode == 4) {
						mode = 0;
						JOptionPane.showMessageDialog(null, "The move is not valid.", "Warn",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (chessAlgorithm.getWinner() == null) {
						chessAlgorithm.computerMove();
					}
					
					squaresPanel.setPieces(chessAlgorithm.getPieces());
					squaresPanel.repaint();

					if (chessAlgorithm.getWinner() == Fraction.PLAYER) {
						JOptionPane.showMessageDialog(null, "You have defeated your opponent!\n\nGo to File -> Reset",
								"Victory", JOptionPane.INFORMATION_MESSAGE);
					} else if (chessAlgorithm.getWinner() == Fraction.COMPUTER) {
						JOptionPane.showMessageDialog(null, "The opponent has defeated you!\n\nGo to File -> Reset",
								"Defeat", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		frame.getContentPane().add(squaresPanel);

		squaresPanel.setPieces(this.chessAlgorithm.getPieces());

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenuItem mntmReset = new JMenuItem("Reset");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		mnFile.add(mntmReset);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mnAbout = new JMenuItem("About");
		mnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog aboutDialog = new AboutDialog();
				aboutDialog.open();
			}
		});
		mnHelp.add(mnAbout);
		squaresPanel.repaint();
	}

	/**
	 * Calculates the position on the board from the coordinates
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	private int[] calcPosition(int y, int x) {
		int[] result = new int[2];

		for (int tempY = 0; tempY < 8; tempY++) {
			if (y > (tempY * 80) && y < ((tempY * 80) + 80)) {
				result[0] = tempY + 1;
			}
		}

		for (int tempX = 0; tempX < 8; tempX++) {
			if (x > (tempX * 80) && x < ((tempX * 80) + 80)) {
				result[1] = tempX + 1;
			}
		}

		return result;
	}

	private void reset() {
		chessAlgorithm.reset();
		squaresPanel.setPieces(chessAlgorithm.getPieces());
		squaresPanel.repaint();
	}
}
