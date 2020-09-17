package com.javalec.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.managementStore;
import com.javalec.setup.md_Bean;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class managementStorePanel extends JPanel {

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
	private JLabel lblName_1;
	private JTextField txtmCode;
	private JTextField txtMDName;
	private JTextField txtPrice;
	private JButton btnMDUpdate;
	private JComboBox comboStroeName;
	private JButton btnMDInsert;
	private JPanel MDInsertPanel;
	private JPanel MDUpdatePanel;

	/**
	 * Create the panel.
	 */
	public managementStorePanel() {
		setLayout(null);
		setBackground(new Color(255, 224, 213));
		add(getScrollPane());		
		add(getLblMcode());
		add(getLblName());
		add(getLblPassword());
		add(getLblName_1());
		add(getTxtmCode());
		add(getTxtMDName());
		add(getTxtPrice());
		add(getComboStroeName());
		add(getMDInsertPanel());
		add(getMDUpdatePanel());
		
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
			lblMcode = new JLabel("mCode");
			lblMcode.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblMcode.setBounds(30, 297, 65, 30);
		}
		return lblMcode;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("store_name");
			lblName.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblName.setBounds(30, 327, 106, 30);
		}
		return lblName;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("md_name");
			lblPassword.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblPassword.setBounds(287, 322, 89, 30);
		}
		return lblPassword;
	}
	private JLabel getLblName_1() {
		if (lblName_1 == null) {
			lblName_1 = new JLabel("price");
			lblName_1.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblName_1.setBounds(30, 358, 44, 30);
		}
		return lblName_1;
	}
	private JTextField getTxtmCode() {
		if (txtmCode == null) {
			txtmCode = new JTextField();
			txtmCode.setEnabled(false);
			txtmCode.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtmCode.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtmCode.setBounds(142, 294, 728, 30);
			txtmCode.setColumns(10);
		}
		return txtmCode;
	}
	private JTextField getTxtMDName() {
		if (txtMDName == null) {
			txtMDName = new JTextField();
			txtMDName.setColumns(10);
			txtMDName.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtMDName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtMDName.setBounds(387, 324, 483, 30);
		}
		return txtMDName;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setColumns(10);
			txtPrice.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtPrice.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtPrice.setBounds(142, 355, 728, 30);
		}
		return txtPrice;
	}
	private JButton getBtnMDUpdate() {
		if (btnMDUpdate == null) {
			btnMDUpdate = new JButton("메뉴 수정");
			btnMDUpdate.setBounds(0, 0, 200, 50);
			btnMDUpdate.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					MDUpdatePanel.setBackground(new Color(202, 227, 154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					MDUpdatePanel.setBackground(new Color(252, 178, 85));
				}
			});
			btnMDUpdate.setBorder(new MatteBorder(1, 1, 1, 1, new Color(64, 64, 64)));
			btnMDUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(insertFieldCheck() == 0) {
						menu_update();
						TableInit();
						SearchAction();
						ClearColumn();
					}
				}
			});
		}
		return btnMDUpdate;
	}
	private JComboBox getComboStroeName() {
		if (comboStroeName == null) {
			comboStroeName = new JComboBox();
			comboStroeName.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			comboStroeName.setModel(new DefaultComboBoxModel(new String[] {"중국집", "카페", "분식집", "편의점"}));
			comboStroeName.setBounds(142, 321, 135, 40);
		}
		return comboStroeName;
	}
	private JButton getBtnMDInsert() {
		if (btnMDInsert == null) {
			btnMDInsert = new JButton("메뉴 추가");
			btnMDInsert.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					MDInsertPanel.setBackground(new Color(202, 227, 154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					MDInsertPanel.setBackground(new Color(252, 178, 85));
				}
			});
			btnMDInsert.setBounds(0, 0, 200, 50);
			btnMDInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(insertFieldCheck() == 0) {
						menu_Insert();
						TableInit();
						SearchAction();
						ClearColumn();
					}
				}
			});
			btnMDInsert.setBorder(new MatteBorder(1, 1, 1, 1, new Color(64, 64, 64)));
		}
		return btnMDInsert;
	}
	private JPanel getMDInsertPanel() {
		if (MDInsertPanel == null) {
			MDInsertPanel = new JPanel();
			MDInsertPanel.setBackground(new Color(252, 178, 85));
			MDInsertPanel.setBounds(196, 430, 200, 50);
			MDInsertPanel.setLayout(null);
			MDInsertPanel.add(getBtnMDInsert());
		}
		return MDInsertPanel;
	}
	private JPanel getMDUpdatePanel() {
		if (MDUpdatePanel == null) {
			MDUpdatePanel = new JPanel();
			MDUpdatePanel.setBackground(new Color(252, 178, 85));
			MDUpdatePanel.setBounds(514, 430, 200, 50);
			MDUpdatePanel.setLayout(null);
			MDUpdatePanel.add(getBtnMDUpdate());
		}
		return MDUpdatePanel;
	}
	
	// 테이블 설정 메소드
	private void TableInit() {		
		int i = Outer_table.getRowCount();	// 현재 테이블의 줄 수

		Outer_table.addColumn("mCode");
		Outer_table.addColumn("store_Name");
		Outer_table.addColumn("md_Name");
		Outer_table.addColumn("price");
		Outer_table.setColumnCount(4);
		
		for (int j = 0 ; j < i ; j++)
			Outer_table.removeRow(0);
		
		Inner_table.setAutoResizeMode(Inner_table.AUTO_RESIZE_OFF); 	// 내가 세팅한값 이상으로 사이즈를 못늘린다.
		Inner_table.setRowHeight(30);
		
		int colIndex = 0;
		TableColumn col = Inner_table.getColumnModel().getColumn(colIndex);
		int width = 140;
		col.setPreferredWidth(width);

		colIndex = 1;
		col = Inner_table.getColumnModel().getColumn(colIndex);
		width = 230;
		col.setPreferredWidth(width);
		
		colIndex = 2;
		col = Inner_table.getColumnModel().getColumn(colIndex);
		width = 230;
		col.setPreferredWidth(width);
		
		colIndex = 3;
		col = Inner_table.getColumnModel().getColumn(colIndex);
		width = 240;
		col.setPreferredWidth(width);
	}
	
	// 테이블에 값을 추가하는 메소드
	private void SearchAction() {
		managementStore dbAction = new managementStore();
		ArrayList<md_Bean> beanList = dbAction.SelectList();
		
		int listCount = beanList.size();
		
		for (int index = 0; index < listCount; index++) {
			String temp = Integer.toString(beanList.get(index).getM_Code());
			String[] qTxt = {temp, beanList.get(index).getStore_Name(), beanList.get(index).getMd_Name(), beanList.get(index).getPrice()};
			Outer_table.addRow(qTxt);
		}
	}
	
	// 테이블 클릭시 텍스트필드에 값을 넣는 메소드
	private void tableClick() {
        int i = Inner_table.getSelectedRow();
        String tkSequence = (String)Inner_table.getValueAt(i, 0);
        int wkSequence = Integer.parseInt(tkSequence);
        
        managementStore management = new managementStore(wkSequence);
        md_Bean bean = management.TableClick();
        
        txtmCode.setText(Integer.toString(bean.getM_Code()));
        comboStroeName.setSelectedItem(bean.getStore_Name());
        txtMDName.setText(bean.getMd_Name());
        txtPrice.setText(bean.getPrice());        
	}
	
	// 메뉴 수정 메소드
	public void menu_update() {
		int mCode = Integer.parseInt(txtmCode.getText());
		String store_name = comboStroeName.getSelectedItem().toString();
		String md_name = txtMDName.getText();
		String price = txtPrice.getText();		
		
		managementStore management = new managementStore(mCode, store_name, md_name, price);
		boolean aaa = management.manage_menu_update();
		if(aaa == true){
	          JOptionPane.showMessageDialog(null, txtMDName.getText() + " 의 메뉴가 수정 되었습니다.!");                    
		}else{
	          JOptionPane.showMessageDialog(null, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!");                    
		}
	}	
	
	// 메뉴 추가 메소드
	public void menu_Insert() {
		String store_name = comboStroeName.getSelectedItem().toString();
		String md_name = txtMDName.getText();
		String price = txtPrice.getText();		

		managementStore management = new managementStore(store_name, md_name, price);
		boolean aaa = management.manage_menu_Insert();
		if(aaa == true){
	          JOptionPane.showMessageDialog(null, txtMDName.getText() + " 의 메뉴가 추가 되었습니다.!");                    
		}else{
	          JOptionPane.showMessageDialog(null, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!");                    
		}
	}
	
	//	 입력폼 체크 메소드
	private int insertFieldCheck() {
		String num_pattern = "^[0-9]*$";					// Resident pattern
		String name_pattern = "^[0-9a-zA-zㄱ-ㅎㅏ-ㅣ가-힣]*$";	// name pattern
		
		// Patter.matches 를 사용하면 문자열을 비교한후 true false값으로 반환한다.
		boolean md_name = Pattern.matches(name_pattern, txtMDName.getText().trim());
		boolean price = Pattern.matches(num_pattern, txtPrice.getText().trim());
		
		int i = 0;		
		String msg = null;
		
		// name filed의 텍스트값의 길이를 가져와서 비교한다.
		if(txtMDName.getText().trim().length() == 0) {
			i++;
			msg = "메뉴이름을 입력하세요.";
			txtMDName.requestFocus();
		}else if(md_name == false) {
			i++;
			msg = "잘못된 형식의 메뉴이름 입니다.";
			txtMDName.requestFocus();
		} else if(md_name == false || price == false) {
			i++;
			msg = "특수기호는 들어갈 수 없습니다.";		
			
			if(md_name == false)
				txtMDName.requestFocus();
			else
				txtPrice.requestFocus();
		}
		
		if(i>0) 
			JOptionPane.showMessageDialog(null, msg);
		
		return i;
	}
	
	// DataField 정리
	private void ClearColumn() {
		txtmCode.setText("");
		txtMDName.setText("");
		txtPrice.setText("");		
	}
}
