package com.javalec.base;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.function.managementMember;
import com.javalec.setup.db_Share_Var;
import com.javalec.setup.member_Bean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.MatteBorder;

public class managementMemberPanel extends JPanel {

	String filePath = "";
	int fileCount = 0;
	
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
	private JLabel lblResident;
	private JTextField txtmCode;
	private JTextField txtID;
	private JTextField txtPW;
	private JTextField txtName;
	private JTextField txtResident;
	private JButton btnMemberUpdate;
	private JLabel lbProfilImg;
	private JButton btnProfilImg;
	private JPanel memberUpdatePanel;
	private JPanel profillPanel;

	/**
	 * Create the panel.
	 */
	public managementMemberPanel() {
		setLayout(null);
		setBackground(new Color(255, 234, 213));
		add(getScrollPane());		
		add(getLblMcode());
		add(getLblName());
		add(getLblPassword());
		add(getLblName_1());
		add(getLblResident());
		add(getTxtmCode());
		add(getTxtID());
		add(getTxtPW());
		add(getTxtName());
		add(getTxtResident());
		add(getLbProfilImg());
		add(getMemberUpdatePanel());
		add(getProfillPanel());
		
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
			lblMcode.setBounds(31, 285, 65, 30);
		}
		return lblMcode;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("ID");
			lblName.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblName.setBounds(30, 315, 21, 30);
		}
		return lblName;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblPassword.setBounds(30, 345, 89, 30);
		}
		return lblPassword;
	}
	private JLabel getLblName_1() {
		if (lblName_1 == null) {
			lblName_1 = new JLabel("Name");
			lblName_1.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblName_1.setBounds(30, 375, 54, 30);
		}
		return lblName_1;
	}
	private JLabel getLblResident() {
		if (lblResident == null) {
			lblResident = new JLabel("Resident");
			lblResident.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
			lblResident.setBounds(30, 405, 80, 30);
		}
		return lblResident;
	}
	private JTextField getTxtmCode() {
		if (txtmCode == null) {
			txtmCode = new JTextField();
			txtmCode.setEnabled(false);
			txtmCode.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtmCode.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtmCode.setBounds(133, 282, 390, 30);
			txtmCode.setColumns(10);
		}
		return txtmCode;
	}
	private JTextField getTxtID() {
		if (txtID == null) {
			txtID = new JTextField();
			txtID.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtID.setEnabled(false);
			txtID.setColumns(10);
			txtID.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtID.setBounds(133, 312, 390, 30);
		}
		return txtID;
	}
	private JTextField getTxtPW() {
		if (txtPW == null) {
			txtPW = new JTextField();
			txtPW.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtPW.setColumns(10);
			txtPW.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtPW.setBounds(133, 342, 390, 30);
		}
		return txtPW;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtName.setColumns(10);
			txtName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtName.setBounds(133, 372, 390, 30);
		}
		return txtName;
	}
	private JTextField getTxtResident() {
		if (txtResident == null) {
			txtResident = new JTextField();
			txtResident.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
			txtResident.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					NumCheck(e);
				}
			});
			txtResident.setColumns(10);
			txtResident.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtResident.setBounds(133, 402, 390, 30);
		}
		return txtResident;
	}
	private JButton getBtnMemberUpdate() {
		if (btnMemberUpdate == null) {
			btnMemberUpdate = new JButton("회원 수정");
			btnMemberUpdate.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					memberUpdatePanel.setBackground(new Color(202, 227, 154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					memberUpdatePanel.setBackground(new Color(252, 178, 85));
				}
			});
			btnMemberUpdate.setBounds(0, 0, 170, 40);
			btnMemberUpdate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(64, 64, 64)));
			btnMemberUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(insertFieldCheck() == 0) {
						member_update();
						TableInit();
						SearchAction();
						ClearColumn();				
					}
				}
			});
		}
		return btnMemberUpdate;
	}
	private JLabel getLbProfilImg() {
		if (lbProfilImg == null) {
			lbProfilImg = new JLabel("");
			lbProfilImg.setBounds(554, 287, 160, 160);
		}
		return lbProfilImg;
	}
	private JButton getBtnProfilImg() {
		if (btnProfilImg == null) {
			btnProfilImg = new JButton("프로필 사진 바꾸기");
			btnProfilImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					profillPanel.setBackground(new Color(202, 227, 154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					profillPanel.setBackground(new Color(252, 178, 85));
				}
			});
			btnProfilImg.setBounds(0, 0, 117, 83);
			btnProfilImg.setBorder(new MatteBorder(1, 1, 1, 1, new Color(64, 64, 64)));
			btnProfilImg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					member_ImgSelect();
					TableInit();
					SearchAction();
					ClearColumn();
				}
			});
		}
		return btnProfilImg;
	}
	private JPanel getMemberUpdatePanel() {
		if (memberUpdatePanel == null) {
			memberUpdatePanel = new JPanel();
			memberUpdatePanel.setBackground(new Color(252, 178, 85));
			memberUpdatePanel.setBounds(364, 465, 170, 40);
			memberUpdatePanel.setLayout(null);
			memberUpdatePanel.add(getBtnMemberUpdate());
		}
		return memberUpdatePanel;
	}
	private JPanel getProfillPanel() {
		if (profillPanel == null) {
			profillPanel = new JPanel();
			profillPanel.setBackground(new Color(252, 178, 85));
			profillPanel.setBounds(738, 364, 117, 83);
			profillPanel.setLayout(null);
			profillPanel.add(getBtnProfilImg());
		}
		return profillPanel;
	}
	
	// 테이블 설정 메소드
	private void TableInit() {		
		int i = Outer_table.getRowCount();	// 현재 테이블의 줄 수
		
		Outer_table.addColumn("mCode");
		Outer_table.addColumn("ID");
		Outer_table.addColumn("Name");
		Outer_table.addColumn("Resident");
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
	
	private void SearchAction() {
		managementMember dbAction = new managementMember();
		ArrayList<member_Bean> beanList = dbAction.SelectList();
		
		int listCount = beanList.size();
		
		for (int index = 0; index < listCount; index++) {
			String temp = Integer.toString(beanList.get(index).getM_Code());
			String[] qTxt = {temp, beanList.get(index).getName(), beanList.get(index).getName(), beanList.get(index).getResident()};
			Outer_table.addRow(qTxt);
		}
	}
	
	private void tableClick() {
        int i = Inner_table.getSelectedRow();
        String tkSequence = (String)Inner_table.getValueAt(i, 0);
        int wkSequence = Integer.parseInt(tkSequence);
        
        managementMember management = new managementMember(wkSequence);
        member_Bean bean = management.TableClick();
        
        txtmCode.setText(Integer.toString(bean.getM_Code()));
        txtID.setText(bean.getID());
        txtName.setText(bean.getName());
        txtPW.setText(bean.getPassWord());
        txtResident.setText(bean.getResident());
        
        // Image처리
        // File name이 틀려야 즉각 보여주기가 가능하여   
        // ShareVar에서 int값으로 정의하여 계속 증가하게 하여 file name으로 사용후 삭제
        
		filePath = Integer.toString(db_Share_Var.filename);
		image_Resize();
		
		fileCount++;
	}
	
	// 이미지 선택하는 메소드
	private void member_ImgSelect() {
		// 파일 선택할수있는 창 인스턴스 생성
		JFileChooser chooser = new JFileChooser();
		// 고를 파일 선택자 이름 필터만들기
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, BMP", "jpeg", "jpg","png","bmp");
		chooser.setFileFilter(filter);
		
		// 파일선택창 띄우기
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		filePath = chooser.getSelectedFile().getPath();
		image_Resize();
	}
	
	public void image_Resize() {
		
		// 이미지 리사이즈
		try {
			Image image = ImageIO.read(new File(filePath));
			
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);

			int w = 0;
			int h = 0;
			
			if (imageWidth < imageHeight) {
				double ratio = 160/(double)imageHeight;
				w = (int)(imageWidth*ratio);
				h = (int)(imageHeight*ratio);
			} else {
				double ratio = 160/(double)imageWidth;
				w = (int)(imageWidth*ratio);
				h = (int)(imageHeight*ratio);
			}
			
			Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_FAST);
			ImageIcon ic = new ImageIcon(resizeImage);
			lbProfilImg.setIcon(ic);
			lbProfilImg.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	// 회원 정보수정 
	public void member_update() {
		int mCode = Integer.parseInt(txtmCode.getText());
		String id = txtName.getText();
		String name = txtName.getText();
		String password = txtPW.getText();
		String resident = txtResident.getText();
		
		// 수정버튼을 한번 누른후 다시 누를경우 이벤트 발생 X
		if(filePath.equals(""))
			return;
		
		// Image File
		FileInputStream input = null;
		File file = new File(filePath);
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		managementMember management = new managementMember(mCode, id, password, name, resident, input);
		boolean aaa = management.manage_memeber_update();
		if(aaa == true){
	        JOptionPane.showMessageDialog(null, txtName.getText() + " 님의 정보가 수정 되었습니다.!");   
		  	// 현재까지 만든 파일 삭제
		  	for (int i = 1 ; i <= fileCount ; i++) {
		  	file = new File(Integer.toString(i));
		  	file.delete();
		  	filePath = "";
		  	}                 
		}else{
	        JOptionPane.showMessageDialog(null, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!");                    
		}		
	}	
	
	// Resident 입력수 제한 및 숫자만 입력하게 가능
	private void NumCheck(KeyEvent e) {
		if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
			if(txtResident.getText().trim().length() >= 6) 
				txtResident.setText(txtResident.getText().substring(0,5));
		} else {
			e.consume();
		}	
	}
	
	//	 입력폼 체크 메소드
	private int insertFieldCheck() {
		String num_pattern = "^[0-9]*$";					// Resident pattern
		String pattern = "^[0-9a-zA-z]*$";					// id, password pattern
		String name_pattern = "^[0-9a-zA-zㄱ-ㅎㅏ-ㅣ가-힣]*$";	// name pattern
		
		// Patter.matches 를 사용하면 문자열을 비교한후 true false값으로 반환한다.
		boolean password = Pattern.matches(pattern, txtPW.getText().trim());
		boolean name = Pattern.matches(name_pattern, txtName.getText().trim());
		boolean resident = Pattern.matches(num_pattern, txtResident.getText().trim());
		
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
		} else if(!(txtResident.getText().trim().length() < 7) || resident == false) {
			i++;
			msg = "잘못된 형식의 Resident 입니다.";
			txtResident.requestFocus();
		} else if(txtID.getText().trim().length()==0) {
			i++;
			msg = "ID 를 입력하세요.";
			txtID.requestFocus();
		} else if(txtPW.getText().trim().length()==0) {
			i++;
			msg = "PW 을 입력하세요.";
			txtPW.requestFocus();
		} else if(name == false || password == false) {
			i++;
			msg = "특수기호는 들어갈 수 없습니다.";			
			if(name == false)
				txtName.requestFocus();
			else
				txtPW.requestFocus();
		}
		
		if(i>0) 
			JOptionPane.showMessageDialog(null, msg);
		
		return i;
	}
	
	// DataField 정리
	private void ClearColumn() {
		txtmCode.setText("");
		txtID.setText("");
		txtName.setText("");
		txtPW.setText("");
		txtResident.setText("");		
	}
}