package com.javalec.base;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;

import javax.swing.JTextField;

import com.javalec.function.Member;
import com.javalec.setup.SH_Bean;

import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class updateMemeberDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lbId;
	private JLabel lbPw;
	private JLabel lbName;
	private JLabel lbResident;
	private JTextField tfId;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfResident;
	String logId;
	private JPanel btnbg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			updateMemeberDialog dialog = new updateMemeberDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public updateMemeberDialog() {
		setTitle("회원정보");
		setBounds(100, 100, 310, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 244, 213));//배경색 변경
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton cancelButton = new JButton("닫기");
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
			});
			cancelButton.setBorder(null);
			cancelButton.setBounds(263, 205, 47, 29);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		contentPanel.add(getLbId());
		contentPanel.add(getLbPw());
		contentPanel.add(getLbName());
		contentPanel.add(getLbResident());
		contentPanel.add(getTfId());
		contentPanel.add(getTfPassword());
		contentPanel.add(getTfName());
		contentPanel.add(getTfResident());
		contentPanel.add(getBtnbg());
	}


	protected void UpdateMemberInfo() {
		// TODO Auto-generated method stub
		String mId = tfId.getText();
		String mPw = tfPassword.getText();
		String mName = tfName.getText();
		String mResident = tfResident.getText();
		
		ArrayList<String> bean = new ArrayList<String>();
		
		bean.add(mId);
		bean.add(mName);
		bean.add(mPw);
		bean.add(mResident);
		
		Member mb = new Member(bean);
		
		mb.member_update();
		
	}

	private JLabel getLbId() {
		if (lbId == null) {
			lbId = new JLabel("아이디");
			lbId.setBounds(35, 30, 61, 16);
		}
		return lbId;
	}
	private JLabel getLbPw() {
		if (lbPw == null) {
			lbPw = new JLabel("비밀번호");
			lbPw.setBounds(35, 68, 61, 16);
		}
		return lbPw;
	}
	private JLabel getLbName() {
		if (lbName == null) {
			lbName = new JLabel("이름");
			lbName.setBounds(35, 109, 61, 16);
		}
		return lbName;
	}
	private JLabel getLbResident() {
		if (lbResident == null) {
			lbResident = new JLabel("생년월일");
			lbResident.setBounds(35, 148, 61, 16);
		}
		return lbResident;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setDisabledTextColor(Color.DARK_GRAY);
			tfId.setEnabled(false);
			tfId.setEditable(false);
			tfId.setBackground(new Color(255,255,255));
			tfId.setBorder(new LineBorder(Color.GRAY));
			tfId.setBounds(99, 25, 130, 26);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JTextField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JTextField();
			tfPassword.setBackground(new Color(255,255,255));
			tfPassword.setBorder(new LineBorder(Color.GRAY));
			tfPassword.setColumns(10);
			tfPassword.setBounds(99, 63, 130, 26);
		}
		return tfPassword;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBackground(new Color(255,255,255));
			tfName.setBorder(new LineBorder(Color.GRAY));
			tfName.setColumns(10);
			tfName.setBounds(99, 104, 130, 26);
		}
		return tfName;
	}
	private JTextField getTfResident() {
		if (tfResident == null) {
			tfResident = new JTextField();
			tfResident.setBackground(new Color(255,255,255));
			tfResident.setBorder(new LineBorder(Color.GRAY));
			tfResident.setColumns(10);
			tfResident.setBounds(99, 143, 130, 26);
		}
		return tfResident;
	}

	public void getMembInfo(String logId) {
		// TODO Auto-generated method stub
		Member mb = new Member(logId);
		this.logId = logId;
		ArrayList<SH_Bean> bean = mb.updateMemberInfo();
		
		tfId.setText(bean.get(0).getId());
		tfName.setText(bean.get(0).getName());
		tfPassword.setText(bean.get(0).getPassword());
		tfResident.setText(bean.get(0).getResident());		
	}	
	private JPanel getBtnbg() {
		if (btnbg == null) {
			btnbg = new JPanel();
			btnbg.setBackground(new Color(255, 255, 255));
			btnbg.setBounds(105, 182, 100, 29);
			{
				JButton okButton = new JButton("회원정보수정");
				btnbg.add(okButton);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						UpdateMemberInfo();
					}
					@Override
					public void mousePressed(MouseEvent e) {//눌렀을때
						btnbg.setBackground(new Color(202,227,154));
					}
					@Override
					public void mouseReleased(MouseEvent e) {//뗐을때
						btnbg.setBackground(new Color(255, 244, 213));
					}
				});
				okButton.setBorder(null);
				okButton.setBackground(new Color(255, 244, 213));
				okButton.setActionCommand("수정");
				getRootPane().setDefaultButton(okButton);
			}
		}
		return btnbg;
	}
}
