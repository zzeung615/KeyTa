package com.javalec.base;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class pleaseMacPCDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			pleaseMacPCDialog dialog = new pleaseMacPCDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public pleaseMacPCDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 244, 213));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea textArea = new JTextArea();
			textArea.setBorder(new EmptyBorder(0, 20, 0, 0));
			textArea.setBackground(new Color(255,255,255));
			textArea.setText("\n\n우리 조로 말할 것 같으면....");
			textArea.setBounds(6, 6, 438, 228);
			contentPanel.add(textArea);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(182, 240, 86, 32);
			contentPanel.add(panel);
			{
				JButton cancelButton = new JButton("닫기");
				panel.add(cancelButton);
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cancelButton.setBackground(new Color(202,227,154));
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						cancelButton.setBackground(new Color(255, 244, 213));
					}
				});
				cancelButton.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
				cancelButton.setBorder(new EmptyBorder(0, 0, 0, 0));
				cancelButton.setActionCommand("Cancel");
			}
		}
	}

}
