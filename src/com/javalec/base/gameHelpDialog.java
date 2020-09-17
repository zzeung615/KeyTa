package com.javalec.base;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class gameHelpDialog extends JDialog {
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			gameHelpDialog dialog = new gameHelpDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public gameHelpDialog() {
		getContentPane().setBackground(new Color(255, 248, 236));//배경색
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton cancelButton = new JButton("닫기");
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
			});
			cancelButton.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
			cancelButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
			cancelButton.setBounds(182, 240, 86, 29);
			getContentPane().add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		getContentPane().add(getTextArea());
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBorder(new EmptyBorder(0, 20, 0, 0));
			textArea.setTabSize(10);
			textArea.setEditable(false);
			textArea.setBackground(new Color(255, 248, 236));
			textArea.setText("\n\n이 게임은...... 뭘까요?");
			textArea.setBounds(6, 6, 438, 237);
		}
		return textArea;
	}
}
