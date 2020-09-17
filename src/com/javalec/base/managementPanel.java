package com.javalec.base;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

public class managementPanel extends JPanel {
	
	private managementMemberPanel memberpanel = new managementMemberPanel();
	private managementStorePanel storepanel = new managementStorePanel();
	private managementCustomerPanel customerpanel = new managementCustomerPanel();
	
	private JLayeredPane layeredPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JPanel memberPanel;
	private JPanel storePanel;
	private JPanel customerPanel;
	private JPanel memberBtnPanel;
	private JPanel storeBtnPanel;
	private JPanel customerBtnPanel;
	

	/**
	 * Create the panel.
	 */
	public managementPanel() {
		setBackground(new Color(188, 143, 143));
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);
		add(getLayeredPane());
		add(getMemberBtnPanel());
		add(getStoreBtnPanel());
		add(getCustomerBtnPanel());
	}
	private JLayeredPane getLayeredPane() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setBounds(0, 100, 900, 600);
			layeredPane.setLayout(new CardLayout(0, 0));
			layeredPane.add(getMemberPanel(), "name_10198134630161");
			layeredPane.add(getStorePanel(), "name_10198143892232");
			layeredPane.add(getCustomerPanel(), "name_10198152468274");
		}
		return layeredPane;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("회원 멤버 수정");
			btnNewButton.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.removeAll();
					layeredPane.add(memberPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			});
			btnNewButton.setBorder(new EmptyBorder(30, 70, 30, 70));
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("가게 메뉴 수정");
			btnNewButton_1.setBorder(new EmptyBorder(30, 70, 30, 70));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.removeAll();
					layeredPane.add(storePanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			});
			btnNewButton_1.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("손님 난이도 수정");
			btnNewButton_2.setBorder(new EmptyBorder(30, 70, 30, 70));
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.removeAll();
					layeredPane.add(customerPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			});
			btnNewButton_2.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
		}
		return btnNewButton_2;
	}
	private JPanel getMemberPanel() {
		if (memberPanel == null) {
			memberPanel = memberpanel;
		}
		return memberPanel;
	}
	private JPanel getStorePanel() {
		if (storePanel == null) {
			storePanel = storepanel;
		}
		return storePanel;
	}
	private JPanel getCustomerPanel() {
		if (customerPanel == null) {
			customerPanel = customerpanel;
		}
		return customerPanel;
	}
	private JPanel getMemberBtnPanel() {
		if (memberBtnPanel == null) {
			memberBtnPanel = new JPanel();
			memberBtnPanel.setBounds(0, 0, 300, 100);
			memberBtnPanel.add(getBtnNewButton());
			memberBtnPanel.setBackground(new Color(255, 234, 213));
		}
		return memberBtnPanel;
	}
	private JPanel getStoreBtnPanel() {
		if (storeBtnPanel == null) {
			storeBtnPanel = new JPanel();
			storeBtnPanel.setBounds(300, 0, 300, 100);
			storeBtnPanel.add(getBtnNewButton_1());
			storeBtnPanel.setBackground(new Color(255, 224, 213));
		}
		return storeBtnPanel;
	}
	private JPanel getCustomerBtnPanel() {
		if (customerBtnPanel == null) {
			customerBtnPanel = new JPanel();
			customerBtnPanel.setBounds(600, 0, 300, 100);
			customerBtnPanel.add(getBtnNewButton_2());
			customerBtnPanel.setBackground(new Color(255, 244, 213));
		}
		return customerBtnPanel;
	}
}
