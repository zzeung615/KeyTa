package com.javalec.base;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.javalec.function.login;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginWinDialog extends JDialog {
	
	private searchIdDialog scId = new searchIdDialog();
	private searchPWDialog scPw = new searchPWDialog();
	
	private JLabel lbID;
	private JLabel lbPW;
	private JTextField tfID;
	private JPasswordField pwPW;
	private JButton btnFdId;
	private JButton btnLogin;
	static JPanel basePn;
	private JPanel pnScId;
	private JPanel pnScPw;
	private Point location;
	public String loginId;
	private JPanel paid;
	private JPanel papw;
	private JButton btnFdId_1;
	private JPanel pajoin;
	private JButton btnFdId_2;
	private JPanel palog;
	
	

	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			loginWinDialog dialog = new loginWinDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("로그인");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Create the dialog.
	 */
	public loginWinDialog() {
		setTitle("로그인");
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(null);
		getContentPane().add(getBasePn());
	}
	
	
	private JLabel getLbID() {
		if (lbID == null) {
			lbID = new JLabel("아이디");
			lbID.setForeground(new Color(0, 0, 0));
			lbID.setFont(new Font("Andale Mono", Font.BOLD, 15));
			lbID.setBounds(115, 42, 80, 16);
		}
		return lbID;
	}
	
	private JLabel getLbPW() {
		if (lbPW == null) {
			lbPW = new JLabel("비밀번호");
			lbPW.setForeground(new Color(0, 0, 0));
			lbPW.setFont(new Font("Andale Mono", Font.BOLD, 15));
			lbPW.setBounds(115, 70, 80, 16);
		}
		return lbPW;
	}
	
	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.setBackground(new Color(255,255,255));
			tfID.setBounds(179, 38, 166, 25);
			tfID.setColumns(10);
			tfID.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return tfID;
	}
	
	private JPasswordField getPwPW() {
		if (pwPW == null) {
			pwPW = new JPasswordField();
			pwPW.setBackground(new Color(255,255,255));
			pwPW.setBorder(new LineBorder(new Color(0, 0, 0)));
			pwPW.setBounds(179, 66, 166, 25);
			pwPW.setColumns(10);
		}
		return pwPW;
	}
	
	private JButton getBtnFdId() {
		if (btnFdId == null) {
			btnFdId = new JButton("아이디 찾기");
			btnFdId.setBounds(8, 5, 58, 16);
			btnFdId.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
			btnFdId.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnFdId.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					getContentPane().add(getPnScId());
					pnScId.setVisible(true);
					basePnSet();
					setTitle("아이디 찾기");
				}
				@Override
				public void mousePressed(MouseEvent e) {
					paid.setBackground(new Color(202,227,154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					paid.setBackground(new Color(255, 244, 213));
				}
			});
		}
		return btnFdId;
	}
	
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("로그인");
			btnLogin.setBounds(95, 5, 47, 22);
			btnLogin.setFont(new Font("Andale Mono", Font.BOLD, 17));
			btnLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnLogin.setBackground(new Color(188, 143, 143));
			btnLogin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mbLogin();
					tfID.setText("");
					pwPW.setText("");
				}
				@Override
				public void mousePressed(MouseEvent e) {
					palog.setBackground(new Color(202,227,154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					palog.setBackground(new Color(255, 244, 213));
				}
			});
		}
		return btnLogin;
	}
	
	private JPanel getBasePn() {
		if (basePn == null) {
			basePn = new JPanel();
			basePn.setBackground(new Color(255, 244, 213));
			basePn.setFont(new Font("Lucida Grande", Font.BOLD, 17));
			basePn.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent e) {
					setTitle("로그인");
				}
			});
			basePn.setBounds(0, 0, 450, 230);
			basePn.setLayout(null);
			basePn.add(getLbPW());
			basePn.add(getTfID());
			basePn.add(getPwPW());
			basePn.add(getLbID());
			basePn.add(getPaid());
			basePn.add(getPapw());
			basePn.add(getPajoin());
			basePn.add(getPalog());
			}
		return basePn;
	}
	
	private JPanel getPnScId() {
		if (pnScId == null) {
			pnScId = scId;
			pnScId.setBounds(0, 0, 450, 230);
			pnScId.setVisible(false);
			
		}
		return pnScId;
	}
	private JPanel getPnScPw() {
		if (pnScPw == null) {
			pnScPw = scPw;
			pnScPw.setBounds(0, 0, 450, 230);
			pnScPw.setVisible(false);
		}
		return pnScPw;
	}
	
	private void mbLogin() {
		char[] pass = pwPW.getPassword();
		String passNew = new String(pass);
		
		boolean result = false;
		
		if (tfID.getText().trim() == "admin" && passNew == "admin") {
				
		} else {
			login lg = new login(tfID.getText().trim(), passNew);
			result = lg.login_check();
		}
		
		if (result == true) {
			this.loginId = tfID.getText().trim();
			setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 다시 확인해주세요.");
		}
	}
	
	private void basePnSet() {
		basePn.setVisible(false);
		tfID.setText("");
		pwPW.setText("");
	}
	
	
	private JPanel getPaid() {
		if (paid == null) {
			paid = new JPanel();
			paid.setBackground(new Color(255, 255, 255));
			paid.setBounds(108, 139, 75, 29);
			paid.setLayout(null);
			paid.add(getBtnFdId());
		}
		return paid;
	}
	private JPanel getPapw() {
		if (papw == null) {
			papw = new JPanel();
			papw.setBackground(new Color(255, 255, 255));
			papw.setBounds(190, 139, 75, 29);
			papw.setLayout(null);
			papw.add(getBtnFdId_1());
		}
		return papw;
	}
	private JButton getBtnFdId_1() {
		if (btnFdId_1 == null) {
			btnFdId_1 = new JButton("비밀번호 찾기");
			btnFdId_1.setBounds(0, 0, 75, 29);
			btnFdId_1.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					getContentPane().add(getPnScPw());
					pnScPw.setVisible(true);
					basePnSet();
					setTitle("비밀번호 찾기");
				}
				@Override
				public void mousePressed(MouseEvent e) {
					papw.setBackground(new Color(202,227,154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					papw.setBackground(new Color(255, 244, 213));
				}
			});
			btnFdId_1.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
			btnFdId_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		return btnFdId_1;
	}
	private JPanel getPajoin() {
		if (pajoin == null) {
			pajoin = new JPanel();
			pajoin.setBackground(new Color(255, 255, 255));
			pajoin.setBounds(270, 139, 75, 29);
			pajoin.setLayout(null);
			pajoin.add(getBtnFdId_2());
		}
		return pajoin;
	}
	private JButton getBtnFdId_2() {
		if (btnFdId_2 == null) {
			btnFdId_2 = new JButton("회원가입");
			btnFdId_2.setBounds(8, 5, 58, 16);
			btnFdId_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					signInWinDialog signwin = new signInWinDialog();
					location = basePn.getLocationOnScreen();
					int x = location.x;
					int y = location.y;
					signwin.setLocationRelativeTo(basePn);
					signwin.setVisible(true);
					tfID.setText("");
					pwPW.setText("");
				}
				@Override
				public void mousePressed(MouseEvent e) {
					pajoin.setBackground(new Color(202,227,154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					pajoin.setBackground(new Color(255, 244, 213));
				}
			});
			btnFdId_2.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
			btnFdId_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		return btnFdId_2;
	}
	private JPanel getPalog() {
		if (palog == null) {
			palog = new JPanel();
			palog.setBackground(new Color(255, 255, 255));
			palog.setBounds(108, 98, 237, 25);
			palog.setLayout(null);
			palog.add(getBtnLogin());
		}
		return palog;
	}
}
