package com.javalec.base;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.javalec.function.Member;
import com.javalec.setup.SH_Bean;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;


public class searchIdDialog extends JPanel {
	private JTextField tfName;
	private JTextField tfResident;
	private JButton btFinId;
	private JButton btBack;
	private JLabel lbResult;
	int keyPress = 0;
	private JPanel pa1;
	private JPanel pa2;
	
	/**
	 * Create the panel.
	 */
	public searchIdDialog() {
		setBackground(new Color(255, 244, 213));
		setLayout(null);
		add(getTfName());
		add(getTfResident());
		add(getLbResult());
		add(getPa1());
		add(getPa2());
		
	}
	
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(keyPress == 0) {
						tfName.setText("");
						keyPress ++;
					}
				}
			});
			tfName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			tfName.setText("이름");
			tfName.setBackground(new Color(255,255,255));
			tfName.setBorder(new LineBorder(Color.GRAY));
			tfName.setColumns(10);
			tfName.setBounds(135, 65, 180, 26);
			tfName.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String text = tfName.getText().trim();
					if(text.length() == 0) {
						tfName.setText("이름");
					}
				keyPress = 0;
				}
				@Override
				public void focusGained(FocusEvent e) {
					if(tfName.getText().equals("이름")) {
					tfName.setCaretPosition(0);
					}
				}
			});
		}
		return tfName;
	}
	
	private JTextField getTfResident() {
		if (tfResident == null) {
			tfResident = new JTextField();
			tfResident.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(keyPress == 0) {
						tfResident.setText("");
						keyPress ++;
					}
				}
			});
			tfResident.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String text = tfResident.getText().trim();
					if(text.length() == 0) {
						tfResident.setText("생년월일 (6자리)");
					}
					keyPress = 0;
				}
				@Override
				public void focusGained(FocusEvent e) {
					if(tfResident.getText().equals("생년월일 (6자리)")) {
						tfResident.setCaretPosition(0);
					}
				}
			});
			tfResident.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			tfResident.setText("생년월일 (6자리)");
			tfResident.setBackground(new Color(255,255,255));
			tfResident.setBorder(new LineBorder(Color.GRAY));
			tfResident.setColumns(10);
			tfResident.setBounds(135, 95, 180, 26);
		}
		return tfResident;
	}
	
	private JButton getBtFinId() {
		if (btFinId == null) {
			btFinId = new JButton("아이디 찾기");
			btFinId.setBounds(21, 5, 58, 16);
			btFinId.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
			btFinId.setBackground(new Color(188, 143, 143));
			btFinId.setBorder(null);
			btFinId.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					boolean dataCk = member_dataCheck();
					if (dataCk) {
						member_search();
					} else {
						lbResult.setText("이름과 주민등록번호를 다시 확인해주세요.");
					}
					
				}
				@Override
				public void mousePressed(MouseEvent e) {
					pa1.setBackground(new Color(202,227,154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					pa1.setBackground(new Color(255, 244, 213));
				}
			});
		}
		return btFinId;
	}
	
	private JButton getBtBack() {
		if (btBack == null) {
			btBack = new JButton("뒤로가기");
			btBack.setBounds(20, 5, 44, 16);
			btBack.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
			btBack.setBackground(new Color(188, 143, 143));
			btBack.setBorder(null);
			btBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tfName.setText("이름");
					tfResident.setText("생년월일 (6자리)");
					setVisible(false);
					loginWinDialog.basePn.setVisible(true);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					pa2.setBackground(new Color(202,227,154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					pa2.setBackground(new Color(255, 244, 213));
				}
			});
		}
		return btBack;
	}
		
		
	private JLabel getLbResult() {
		if (lbResult == null) {
			lbResult = new JLabel("이름과 생년월일을 입력해주세요.");
			lbResult.setForeground(Color.BLACK);
			lbResult.setHorizontalAlignment(SwingConstants.CENTER);
			lbResult.setBounds(60, 130, 330, 16);
		}
		return lbResult;
	}
	
	
	private boolean member_dataCheck() {
		if (tfName.getText().length() > 0 && tfResident.getText().length() == 6) {
			return true;
		} else {
			return false;
		}
	}
	
	private void member_search() {
		String name = tfName.getText();
		String resd = tfResident.getText();
		Member memb = new Member("", name, resd);
		
		ArrayList<SH_Bean> beanList = memb.memeber_search_id();
		
		int listCount = beanList.size();
		String temp = beanList.get(0).getId();
		
		if (temp.length() == 0) {
			lbResult.setText("회원 정보가 존재하지 않습니다.");
		} else {
		lbResult.setText("당신의 아이디는 " + temp + "입니다.");
		}
	}
	
	private JPanel getPa1() {
		if (pa1 == null) {
			pa1 = new JPanel();
			pa1.setBackground(new Color(255, 255, 255));
			pa1.setBounds(175, 160, 100, 30);
			pa1.setLayout(null);
			pa1.add(getBtFinId());
		}
		return pa1;
	}
	private JPanel getPa2() {
		if (pa2 == null) {
			pa2 = new JPanel();
			pa2.setBackground(new Color(255, 255, 255));
			pa2.setBounds(360, 190, 84, 30);
			pa2.setLayout(null);
			pa2.add(getBtBack());
		}
		return pa2;
	}
}
