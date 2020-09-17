package com.javalec.base;

import java.awt.Color;

import javax.swing.JPanel;

import com.javalec.function.gameSelect;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class gameSelectPanel extends JPanel {
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel Panel;
	private JLabel lblexplain;

	/**
	 * Create the panel.
	 */
	public gameSelectPanel() {
		setBackground(new Color(255, 244, 213));
		setLayout(null);
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getPanel());

	}


	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("KeyBorad Tycoon");
			lblNewLabel.setFont(new Font("Apple Color Emoji", Font.PLAIN, 37));
			lblNewLabel.setBounds(278, 40, 345, 49);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("경영하고싶은 가게를 선택하세요");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(81, 138, 200, 19);
		}
		return lblNewLabel_1;
	}
	private JPanel getPanel() {
		if (Panel == null) {
			Panel = new JPanel();
			Panel.setBounds(336, 171, 500, 370);
			Panel.setLayout(null);
//			Panel.setBackground(new Color(208, 163, 163));
			Panel.setBackground(new Color(255, 233, 171));
			Panel.add(getLblexplain());
		}
		return Panel;
	}
	private JLabel getLblexplain() {
		if (lblexplain == null) {
			lblexplain = new JLabel("");
			lblexplain.setBounds(10, 10, 480, 350);
			lblexplain.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblexplain.setVerticalAlignment(SwingConstants.TOP);
		}
		return lblexplain;
	}
	
	public void textChange(String check) {
		gameSelect gameselect = new gameSelect();
		String str = "";
		
		if (check.equals("카페")) {
			str = gameselect.store_menu_String("카페");
			lblexplain.setText(str + " 메뉴를 팔수있습니다. <html>");			
		} else if (check.equals("분식집")) {
			str = gameselect.store_menu_String("분식집");
			lblexplain.setText(str + " 메뉴를 팔수있습니다. <html>");				
		} else if (check.equals("편의점")) {
			str = gameselect.store_menu_String("편의점");
			lblexplain.setText(str + " 메뉴를 팔수있습니다. <html>");			
		} else {
			str = gameselect.store_menu_String("중국집");
			lblexplain.setText(str + " 메뉴를 팔수있습니다. <html>");				
		}
	}
	public boolean textIsEmpty() {
		boolean isEmpty = false;
		if(lblexplain.getText().equals("") == true)
			isEmpty = true;
		return isEmpty;
	}
}
