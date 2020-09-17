package com.javalec.base;

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
import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import javax.swing.border.LineBorder;


public class searchPWDialog extends JPanel {
	private JTextField tfName;
	private JTextField tfResident;
	private JTextField tfId;
	private JButton btFinPw;
	private JButton btBack;
	private JLabel lbResult;
	int keyPress = 0;
	private JPanel pa1;
	private JPanel pa2;

	

	/**
	 * Create the panel.
	 */
	public searchPWDialog() {
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setBackground(new Color(255, 244, 213));//배경색
		setLayout(null);
		add(getTfName());
		add(getTfResident());
		add(getTfId());
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
					if (keyPress == 0) {
						tfName.setText("");
						keyPress ++;
					}
				}
			});
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
			tfName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			tfName.setText("이름");
			tfName.setBorder(new LineBorder(Color.GRAY));
			tfName.setBackground(new Color(255,255,255));
			tfName.setColumns(10);
			tfName.setBounds(135, 35, 180, 26);
		}
		return tfName;
	}
	
	
	private JTextField getTfResident() {
		if (tfResident == null) {
			tfResident = new JTextField();
			tfResident.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (keyPress == 0) {
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
					tfResident.setCaretPosition(0);
				}
			});
			tfResident.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			tfResident.setText("생년월일 (6자리)");
			tfResident.setBorder(new LineBorder(Color.GRAY));
			tfResident.setBackground(new Color(255,255,255));
			tfResident.setColumns(10);
			tfResident.setBounds(135, 65, 180, 26);
		}
		return tfResident;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (keyPress == 0) {
						tfId.setText("");
						keyPress ++;
					}
				}
			});
			tfId.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String text = tfId.getText().trim();
					if(text.length() == 0) {
						tfId.setText("아이디");
					}
					keyPress = 0;
				}
				@Override
				public void focusGained(FocusEvent e) {
					tfId.setCaretPosition(0);
				}
			});
			tfId.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			tfId.setText("아이디");
			tfId.setBorder(new LineBorder(Color.GRAY));
			tfId.setBackground(new Color(255,255,255));
			tfId.setColumns(10);
			tfId.setBounds(135, 95, 180, 26);
		}
		return tfId;
	}
	
	
	private JLabel getLbResult() {
		if (lbResult == null) {
			lbResult = new JLabel("이름, 생년월일, 아이디를 입력해주세요.");
			lbResult.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbResult.setForeground(Color.RED);
			lbResult.setHorizontalAlignment(SwingConstants.CENTER);
			lbResult.setBounds(60, 130, 330, 16);
		}
		return lbResult;
	}
	
	private JButton getBtFinPw() {
		if (btFinPw == null) {
			btFinPw = new JButton("비밀번호 찾기");
			btFinPw.setBounds(14, 5, 71, 18);
			btFinPw.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
			btFinPw.setBackground(new Color(188, 143, 143));
			btFinPw.setBorder(null);
			btFinPw.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					boolean dataCk = member_dataCheck();
					if (dataCk) {
						member_search();
					} else {
						lbResult.setText("이름, 주민등록번호, 아이디를 다시 확인해주세요.");
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
		return btFinPw;
	}
	
	private JButton getBtBack() {
		if (btBack == null) {
			btBack = new JButton("뒤로가기");
			btBack.setBounds(8, 5, 44, 16);
			btBack.setBorder(null);
			btBack.setBackground(new Color(188, 143, 143));
			btBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tfName.setText("이름");
					tfResident.setText("생년월일 (6자리)");
					tfId.setText("아이디");
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
	
		
	private boolean member_dataCheck() {
		
		if (tfName.getText().length() > 0 && tfResident.getText().length() == 6 && tfId.getText().length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void member_search() {
		String id = tfId.getText();
		String name = tfName.getText();
		String resd = tfResident.getText();
		
		Member memb = new Member(id, name, resd);
		
		ArrayList<SH_Bean> beanList = memb.memeber_search_pw();
		
		String temp = beanList.get(0).getPassword();
		
		if (temp.length() == 0) {
			lbResult.setText("회원 정보가 존재하지 않습니다.");
		} else {
		lbResult.setText("당신의 비밀번호는 " + temp + "입니다.");
		}
		
	}
	private JPanel getPa1() {
		if (pa1 == null) {
			pa1 = new JPanel();
			pa1.setBackground(new Color(255, 255, 255));
			pa1.setBounds(176, 161, 100, 29);
			pa1.setLayout(null);
			pa1.add(getBtFinPw());
		}
		return pa1;
	}
	private JPanel getPa2() {
		if (pa2 == null) {
			pa2 = new JPanel();
			pa2.setBackground(new Color(255, 255, 255));
			pa2.setBounds(371, 190, 61, 26);
			pa2.setLayout(null);
			pa2.add(getBtBack());
		}
		return pa2;
	}
}
