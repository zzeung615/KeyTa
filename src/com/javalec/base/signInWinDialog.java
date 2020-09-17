package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.javalec.function.Member;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class signInWinDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lbID;
	private JTextField tfID;
	private JButton btDoubleCheck;
	private JLabel lbPassword;
	private JPasswordField pwfPassword;
	private JLabel lbPwCheck;
	private JPasswordField pwfCheck;
	private JLabel lbPwCheckSen;
	private JLabel lbName;
	private JTextField tfName;
	private JLabel lbResident;
	private JTextField tfResident;
	private JLabel lbImage;
	private JButton btAddImage;
	private String filepath;
	private JPanel btn1;
	private JPanel btn2;
	private JPanel btn3;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			signInWinDialog dialog = new signInWinDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public signInWinDialog() {
		setTitle("회원가입");
		setBounds(100, 100, 551, 276);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 244, 213));//배경색
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		contentPanel.add(getLbId());
		contentPanel.add(getTfID());
		contentPanel.add(getLbPassword());
		contentPanel.add(getPwfPassword());
		contentPanel.add(getLbPwCheck());
		contentPanel.add(getPwfCheck());
		contentPanel.add(getLbPwCheckSen());
		contentPanel.add(getLbName());
		contentPanel.add(getTfName());
		contentPanel.add(getLbResident());
		contentPanel.add(getTfResident());
		contentPanel.add(getBtAddImage());
		contentPanel.add(getLbImage());
		contentPanel.add(getBtn1());
		contentPanel.add(getBtn2());
		contentPanel.add(getBtn3());
	}
	
	private JPasswordField getPwfPassword() {
		if (pwfPassword == null) {
			pwfPassword = new JPasswordField();
			pwfPassword.setBackground(new Color(255,255,255));
			pwfPassword.setBorder(new LineBorder(Color.GRAY));
			pwfPassword.setEditable(false);
			pwfPassword.setBounds(130, 65, 140, 26);
		}
		return pwfPassword;
	}
	
	private JPasswordField getPwfCheck() {
		if (pwfCheck == null) {
			pwfCheck = new JPasswordField();
			pwfCheck.setBorder(new LineBorder(Color.GRAY));
			pwfCheck.setBackground(new Color(255,255,255));
			pwfCheck.setEditable(false);
			pwfCheck.setBounds(130, 96, 140, 26);
			
			pwfCheck.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					char[] pass = pwfPassword.getPassword();
					String passString = new String(pass).trim();
					boolean statm = false;
					
					if (passString.length() > 0 ) {
						lbPwCheck.setEnabled(true);
						pwfCheck.setEditable(true);
						lbPwCheckSen.setText("비밀번호 확인 후 엔터를 쳐주세요.");
						statm = true;
					}
				}
			});
			
			pwfCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pwfCheck.isEditable() && pwCheck() == 1) {
						tfName.setEditable(true);
						tfResident.setEditable(true);
						lbName.setEnabled(true);
						lbResident.setEnabled(true);
						lbImage.setEnabled(true);
						btAddImage.setEnabled(true);
					}
				}
			});
		}
		return pwfCheck;
	}
	
	private JLabel getLbName() {
		if (lbName == null) {
			lbName = new JLabel("이름");
			lbName.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
			lbName.setEnabled(false);
			lbName.setBounds(20, 160, 61, 16);
		}
		return lbName;
	}
	private JLabel getLbId() {
		if (lbID == null) {
			lbID = new JLabel("아이디");
			lbID.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
			lbID.setBounds(20, 24, 61, 16);
		}
		return lbID;
	}
	
	private JButton getBtdoubleCheck() {
		if (btDoubleCheck == null) {
			btDoubleCheck = new JButton("중복 확인");
			btDoubleCheck.setBorder(null);
			btDoubleCheck.setBackground(new Color(188, 143, 143));
			btDoubleCheck.setFont(new Font("Andale Mono", Font.BOLD, 13));
			btDoubleCheck.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					idCheck();
				}
				@Override
				public void mousePressed(MouseEvent e) {
					btDoubleCheck.setBackground(new Color(202,227,154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					btDoubleCheck.setBackground(new Color(255, 244, 213));
				}
			});

		}
		return btDoubleCheck;
	}
	
	private JLabel getLbResident() {
		if (lbResident == null) {
			lbResident = new JLabel("생년월일 (6자리) ");
			lbResident.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
			lbResident.setEnabled(false);
			lbResident.setBounds(20, 190, 117, 16);
		}
		return lbResident;
	}
	
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBorder(new LineBorder(Color.GRAY));
			tfName.setBackground(new Color(255,255,255));
			tfName.setEditable(false);
			tfName.setBounds(130, 155, 140, 26);
			tfName.setColumns(10);
		}
		return tfName;
	}
	
	private JTextField getTfID() {
		if (tfID== null) {
			tfID = new JTextField();
			tfID.setBackground(new Color(255,255,255));
			tfID.setBorder(new LineBorder(Color.GRAY));
			tfID.setBounds(130, 19, 140, 26);
			tfID.setColumns(10);
		}
		return tfID;
	}
	
	private JTextField getTfResident() {
		if (tfResident == null) {
			tfResident = new JTextField();
			tfResident.setBorder(new LineBorder(Color.GRAY));
			tfResident.setBackground(new Color(255,255,255));
			tfResident.setEditable(false);
			tfResident.setBounds(130, 186, 140, 26);
			tfResident.setColumns(10);
		}
		return tfResident;
	}
	
	
	private void idCheck() {
		String custId = tfID.getText().trim();
		
		int idCk = 0;
		
		if (custId.length() == 0) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
		} else {
			Member member = new Member(custId);
			idCk = member.idCheck();
			if (idCk == 0) {
				JOptionPane.showMessageDialog(null, "사용 가능합니다.");
				lbPassword.setEnabled(true);
				pwfPassword.setEnabled(true);
				pwfPassword.setEditable(true);
				lbPwCheckSen.setEnabled(true);
				lbPwCheckSen.setText("비밀번호를 입력해주세요.");
				
			} else {
				JOptionPane.showMessageDialog(null, "중복된 아이디가 있습니다.");
			}
		}
		
		
		
		

	} // idCheck
	
	
	private JLabel getLbPwCheckSen() {
		if (lbPwCheckSen == null) {
			lbPwCheckSen = new JLabel("");
			lbPwCheckSen.setForeground(new Color(0, 0, 0));
			lbPwCheckSen.setEnabled(false);
			lbPwCheckSen.setHorizontalAlignment(SwingConstants.CENTER);
			lbPwCheckSen.setBounds(12, 127, 346, 16);
		}
		return lbPwCheckSen;
	}
	
	
	private void Member_signUp(int result) {
		
		ArrayList<String> bean = new ArrayList<String>(5);
		char[] pass = pwfPassword.getPassword();
		String passSt = new String(pass);
		
		
		bean.add(tfID.getText().trim());
		bean.add(tfName.getText().trim());
		bean.add(passSt);
		bean.add(tfResident.getText().trim());
		bean.add(filepath);
		
		
		switch(result) {
		case 0 : 
			Member member = new Member(bean);
			member.member_signUp();
			break;
		case 1 :
			JOptionPane.showMessageDialog(null, "주민등록번호는 6자리 숫자로 입력해주세요.");
			break;
		case 2 :
			JOptionPane.showMessageDialog(null, "주민등록번호는 6자리 숫자로 입력해주세요.");
			break;
		case 3 :
			JOptionPane.showMessageDialog(null, "주민등록번호는 6자리 숫자로 입력해주세요.");
			break;
		case 4 :
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
			break;
		default :
			JOptionPane.showMessageDialog(null, "이름과 주민등록번호를 다시 확인해주세요.");
			break;
		}
		
		
		
	}
//		
	
	
	
	private int dataCheck() {
		
		char[] pass = pwfPassword.getPassword();
		String passSt = new String(pass);		
		
		//비밀번호 CaseCheck
		String resid = tfResident.getText().trim();
		
		int res = 0;
		
		if (resid.length() != 6) {
			res += 1;
			//JOptionPane.showMessageDialog(null, "주민등록번호 길이를 확인해주세요.");
		}
		
		for (int i = 0; i < resid.length(); i++) {
			char tmp = resid.charAt(i);
			if(Character.isDigit(tmp) == false) {
				res += 2;
				break;
			}
		}	
	
		if (tfName.getText().trim().length() == 0) {
			res +=4;
		}		
		
		return res;		
	}
	
	
	private int pwCheck() {
		
		char[] pass = pwfPassword.getPassword();
		char[] passck = pwfCheck.getPassword();
		String passString = new String(pass).trim();
		String passCk = new String(passck).trim();
		int res;
		
		if (passString.equals(passCk)) {
			lbPwCheckSen.setText("확인되었습니다.");
			res = 1;
		} else {
			lbPwCheckSen.setText("비밀번호를 다시 확인해주세요.");	
			res = 0;
		}	
		return res;
	}
	
	
	
	private JLabel getLbPassword() {
		if (lbPassword == null) {
			lbPassword = new JLabel("비밀번호");
			lbPassword.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
			lbPassword.setEnabled(false);
			lbPassword.setBounds(20, 70, 117, 16);
		}
		return lbPassword;
	}
	private JLabel getLbPwCheck() {
		if (lbPwCheck == null) {
			lbPwCheck = new JLabel("비밀번호 확인");
			lbPwCheck.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
			lbPwCheck.setEnabled(false);
			lbPwCheck.setBounds(20, 101, 117, 16);
		}
		return lbPwCheck;
	}
	
	
	private JLabel getLbImage() {
		if (lbImage == null) {
			lbImage = new JLabel("Image");
			lbImage.setBorder(new LineBorder(Color.DARK_GRAY));
			lbImage.setBounds(370, 19, 160, 160);
			lbImage.setEnabled(false);
			lbImage.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbImage;
	}
	private JButton getBtAddImage() {
		if (btAddImage == null) {
			btAddImage = new JButton("사진 추가");
			btAddImage.setFont(new Font("Andale Mono", Font.BOLD, 13));
			btAddImage.setBorder(null);
			btAddImage.setEnabled(false);
			btAddImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					lbImage.setText("");
					loadImage();
				}
			});
			btAddImage.setBounds(392, 182, 117, 26);
		}
		return btAddImage;
	}
	
	private void loadImage() {
		int imageWidth;
		int imageHeight;
		double ratio;
		
		Image image = null;		
		
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("이미지 불러오기");
		fc.setMultiSelectionEnabled(false);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.filepath = file.getPath();
			try {
				image = ImageIO.read(new File(filepath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			imageWidth = image.getWidth(null);
			imageHeight = image.getHeight(null);
			int w = 0;
			int h = 0;
			
			if (imageWidth < imageHeight) {
				ratio = 160/(double)imageHeight;
				w = (int)(imageWidth*ratio);
				h = (int)(imageHeight*ratio);
			} else {
				ratio = 160/(double)imageWidth;
				w = (int)(imageWidth*ratio);
				h = (int)(imageHeight*ratio);
			}
			
			try {
				Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_FAST);
				ImageIcon ic = new ImageIcon(resizeImage);
				lbImage.setIcon(ic);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	private JPanel getBtn1() {
		if (btn1 == null) {
			btn1 = new JPanel();
			btn1.setBackground(new Color(255, 255, 255));
			btn1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
				}
			});
			btn1.setBounds(282, 19, 75, 26);
			btn1.add(getBtdoubleCheck());
		}
		return btn1;
	}
	private JPanel getBtn2() {
		if (btn2 == null) {
			btn2 = new JPanel();
			btn2.setBackground(new Color(255, 255, 255));
			btn2.setBounds(221, 217, 108, 31);
			{
				JButton btSignIn = new JButton("가입");
				btn2.add(btSignIn);
				btSignIn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btSignIn.setBorder(null);
				btSignIn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int finData = dataCheck();
						Member_signUp(finData);	
					}
					@Override
					public void mousePressed(MouseEvent e) {
						btn2.setBackground(new Color(202,227,154));
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						btn2.setBackground(new Color(255, 244, 213));
					}
				});			
				btSignIn.setActionCommand("가입");
				getRootPane().setDefaultButton(btSignIn);
			}
		}
		return btn2;
	}
	private JPanel getBtn3() {
		if (btn3 == null) {
			btn3 = new JPanel();
			btn3.setBackground(new Color(255, 255, 255));
			btn3.setBounds(455, 217, 75, 31);
			{
				JButton btClose = new JButton("닫기");
				btClose.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						btn3.setBackground(new Color(202,227,154));
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						btn3.setBackground(new Color(255, 244, 213));
					}
				});
				btn3.add(btClose);
				btClose.setBorder(null);
				btClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btClose.setActionCommand("닫기");
			}
		}
		return btn3;
	}
}
