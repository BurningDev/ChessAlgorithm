/**
 * Author: BurningDev
 */
package com.burningdev.chess.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	public void open() {
		try {
			AboutDialog dialog = new AboutDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AboutDialog() {
		setResizable(false);
		setTitle("About");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 444, 47);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblChess = new JLabel("Chess");
		lblChess.setBounds(10, 11, 85, 29);
		lblChess.setFont(new Font("Arial", Font.PLAIN, 25));
		lblChess.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblChess);
		
		JLabel lblDeveloper = new JLabel("Developer: BurningDev. All rights reserved.");
		lblDeveloper.setBounds(10, 213, 241, 14);
		contentPanel.add(lblDeveloper);
		
		JLabel lblDescription = new JLabel("Chess with graphical user interface and computer-controlled opponents.");
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setBounds(10, 58, 424, 40);
		contentPanel.add(lblDescription);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
