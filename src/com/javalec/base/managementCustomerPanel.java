package com.javalec.base;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.managementCustomer;
import com.javalec.setup.customer_Bean;
import java.awt.event.KeyAdapter;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

public class managementCustomerPanel extends JPanel {

	private final DefaultTableModel Outer_table = new DefaultTableModel(){
		// Table cell 변경 불가능 설정
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	
	private JScrollPane scrollPane;
	private JTable Inner_table;
	private JLabel lblMcode;
	private JLabel lblName;
	private JLabel lblPassword;
	private JTextField txtcCode;
	private JTextField txtName;
	private JTextField txtWaitTime;
	private JButton btnCustomerUpdate;
	private JButton btnCustomerInsert;
	private JPanel updateBtnPanel;
	private JPanel insertBtnPanel;

	/**
	 * Create the panel.
	 */
	public managementCustomerPanel() {
		setLayout(null);
		setBackground(new Color(255, 244, 213));
		add(getScrollPane());		
		add(getLblMcode());
		add(getLblName());
		add(getLblPassword());
		add(getTxtcCode());
		add(getTxtName());
		add(getTxtWaitTime());
		add(getUpdateBtnPanel());
		add(getInsertBtnPanel());
		
		TableInit();
		SearchAction();
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(30, 10, 840, 260);
			scrollPane.setViewportView(getInner_table());
		}
		return scrollPane;
	}
	private JTable getInner_table() {
		if (Inner_table == null) {
			Inner_table = new JTable();
			Inner_table.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			Inner_table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == 1) {
						tableClick();						
					}
				}
			});
			Inner_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			// -------------------------------
			Inner_table.setModel(Outer_table); 	// <--- ********* 중요함
			// -------------------------------
		}
		return Inner_table;
	}
	private JLabel getLblMcode() {
		if (lblMcode == null) {
			lblMcode = new JLabel("cCode");
			lblMcode.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblMcode.setBounds(30, 303, 58, 30);
		}
		return lblMcode;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("name");
			lblName.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblName.setBounds(30, 353, 50, 30);
		}
		return lblName;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("waitTime");
			lblPassword.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblPassword.setBounds(30, 403, 83, 30);
		}
		return lblPassword;
	}
	private JTextField getTxtcCode() {
		if (txtcCode == null) {
			txtcCode = new JTextField();
			txtcCode.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtcCode.setBounds(177, 293, 693, 50);
			txtcCode.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			txtcCode.setEnabled(false);
			txtcCode.setColumns(10);
		}
		return txtcCode;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtName.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			txtName.setColumns(10);
			txtName.setBounds(177, 343, 693, 50);
		}
		return txtName;
	}
	private JTextField getTxtWaitTime() {
		if (txtWaitTime == null) {
			txtWaitTime = new JTextField();
			txtWaitTime.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtWaitTime.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			txtWaitTime.setColumns(10);
			txtWaitTime.setBounds(177, 393, 693, 50);
		}
		return txtWaitTime;
	}
	private JPanel getUpdateBtnPanel() {
		if (updateBtnPanel == null) {
			updateBtnPanel = new JPanel();
			updateBtnPanel.setBounds(468, 470, 207, 58);
			updateBtnPanel.setLayout(null);
			updateBtnPanel.add(getBtnCustomerUpdate());
			updateBtnPanel.setBackground(new Color(252, 178, 85));
		}
		return updateBtnPanel;
	}
	private JButton getBtnCustomerUpdate() {
		if (btnCustomerUpdate == null) {
			btnCustomerUpdate = new JButton("손님 수정");
			btnCustomerUpdate.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					updateBtnPanel.setBackground(new Color(202, 227, 154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					updateBtnPanel.setBackground(new Color(252, 178, 85));
				}
			});
			btnCustomerUpdate.setBounds(0, 0, 207, 58);
			btnCustomerUpdate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(64, 64, 64)));
			btnCustomerUpdate.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
			btnCustomerUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(insertFieldCheck() == 0) {
						customer_update();
						TableInit();
						SearchAction();
						ClearColumn();
					}
				}
			});
		}
		return btnCustomerUpdate;
	}
	private JPanel getInsertBtnPanel() {
		if (insertBtnPanel == null) {
			insertBtnPanel = new JPanel();
			insertBtnPanel.setBounds(187, 470, 207, 58);
			insertBtnPanel.setLayout(null);
			insertBtnPanel.add(getBtnCustomerInsert());
			insertBtnPanel.setBackground(new Color(252, 178, 85));
		}
		return insertBtnPanel;
	}
	private JButton getBtnCustomerInsert() {
		if (btnCustomerInsert == null) {
			btnCustomerInsert = new JButton("손님 추가");
			btnCustomerInsert.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					insertBtnPanel.setBackground(new Color(202, 227, 154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					insertBtnPanel.setBackground(new Color(252, 178, 85));
				}
			});
			btnCustomerInsert.setBounds(0, 0, 207, 58);
			btnCustomerInsert.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			btnCustomerInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(insertFieldCheck() == 0) {
						customer_Insert();
						TableInit();
						SearchAction();
						ClearColumn();	
					}				
				}
			});
			btnCustomerInsert.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
		}
		return btnCustomerInsert;
	}
	
	// 테이블 설정 메소드
	private void TableInit() {		
		int i = Outer_table.getRowCount();	// 현재 테이블의 줄 수

		Outer_table.addColumn("cCode");
		Outer_table.addColumn("Name");
		Outer_table.addColumn("waitTime");
		Outer_table.setColumnCount(3);
		
		for (int j = 0 ; j < i ; j++)
			Outer_table.removeRow(0);
		
		Inner_table.setAutoResizeMode(Inner_table.AUTO_RESIZE_OFF); 	// 내가 세팅한값 이상으로 사이즈를 못늘린다.
		Inner_table.setRowHeight(30);
		
		int colIndex = 0;
		TableColumn col = Inner_table.getColumnModel().getColumn(colIndex);
		int width = 180;
		col.setPreferredWidth(width);		

		colIndex = 1;
		col = Inner_table.getColumnModel().getColumn(colIndex);
		width = 330;
		col.setPreferredWidth(width);
		
		colIndex = 2;
		col = Inner_table.getColumnModel().getColumn(colIndex);
		width = 330;
		col.setPreferredWidth(width);
	}
	
	private void SearchAction() {
		managementCustomer dbAction = new managementCustomer();
		ArrayList<customer_Bean> beanList = dbAction.SelectList();
		
		int listCount = beanList.size();
		
		for (int index = 0; index < listCount; index++) {
			String temp = Integer.toString(beanList.get(index).getcCode());
			String[] qTxt = {temp, beanList.get(index).getName(), beanList.get(index).getWaitTime()};
			Outer_table.addRow(qTxt);
		}
	}
	
	private void tableClick() {
        int i = Inner_table.getSelectedRow();
        String tkSequence = (String)Inner_table.getValueAt(i, 0);
        int wkSequence = Integer.parseInt(tkSequence);
        
        managementCustomer management = new managementCustomer(wkSequence);
        customer_Bean bean = management.TableClick();
        
        txtcCode.setText(Integer.toString(bean.getcCode()));
        txtName.setText(bean.getName());
        txtWaitTime.setText(bean.getWaitTime());     
	}
	
	public void customer_update() {
		int cCode = Integer.parseInt(txtcCode.getText());
		String name = txtName.getText();
		String waitTime = txtWaitTime.getText();	
		
		managementCustomer management = new managementCustomer(cCode, name, waitTime);
		boolean aaa = management.manage_customer_update();
		if(aaa == true){
	          JOptionPane.showMessageDialog(null, txtWaitTime.getText() + " 의 손님이 수정 되었습니다.!");                    
		}else{
	          JOptionPane.showMessageDialog(null, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!");                    
		}
	}
	
	public void customer_Insert() {
		String name = txtName.getText();
		String waitTime = txtWaitTime.getText();
		
		managementCustomer management = new managementCustomer(name, waitTime);
		boolean aaa = management.manage_customer_Insert();
		if(aaa) {
	          JOptionPane.showMessageDialog(null, txtWaitTime.getText() + " 의 손님이 추가 되었습니다.!");   			
		} else {
	          JOptionPane.showMessageDialog(null, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!");    			
		}
	}
	
	//	 입력폼 체크 메소드
	private int insertFieldCheck() {
		String num_pattern = "^[0-9]*$";					// Resident pattern
		String name_pattern = "^[0-9a-zA-zㄱ-ㅎㅏ-ㅣ가-힣]*$";	// name pattern
		
		// Patter.matches 를 사용하면 문자열을 비교한후 true false값으로 반환한다.
		boolean name = Pattern.matches(name_pattern, txtName.getText().trim());
		boolean waitTime = Pattern.matches(num_pattern, txtWaitTime.getText().trim());
		
		int i = 0;		
		String msg = null;
		
		// name filed의 텍스트값의 길이를 가져와서 비교한다.
		if(txtName.getText().trim().length() == 0) {
			i++;
			msg = "이름을 입력하세요.";
			txtName.requestFocus();
		} else if(name == false) {		// name이라는 패턴에서 나온 true false 값으로 확인한다.
			i++;
			msg = "잘못된 형식의 이름 입니다.";
			txtName.requestFocus();
		} else if(waitTime == false) {
			i++;
			msg = "잘못된 형식의 WaitTime 입니다.";
			txtWaitTime.requestFocus();
		} else if(name == false) {
			i++;
			msg = "특수기호는 들어갈 수 없습니다.";	
			txtName.requestFocus();
		}
		
		if(i>0) 
			JOptionPane.showMessageDialog(null, msg);
		
		return i;
	}
	
	// DataField 정리
	private void ClearColumn() {
		txtcCode.setText("");
		txtName.setText("");
		txtWaitTime.setText("");
	}
}
