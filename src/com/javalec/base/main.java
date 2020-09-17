package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLayeredPane;
import javax.swing.JMenu;

import java.awt.CardLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import com.javalec.function.game;
import com.javalec.function.rank;

import java.awt.Font;
import java.awt.Point;

import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class main {
	
	private mainPanel mainpanel = new mainPanel();
	private managementPanel managementpanel = new managementPanel();
	private gameSelectPanel gameSelectPanel = new gameSelectPanel();
	private gamePlayPanel playGamePanel = new gamePlayPanel();
	private updateMemeberDialog upMemb = new updateMemeberDialog();
	private gameHelpDialog gmhelp = new gameHelpDialog();
	private pleaseMacPCDialog plmac = new pleaseMacPCDialog();
	
	rank rank = new rank();

	private Point location;
	int windAct = 0;
	public static String logId = "";
	loginWinDialog lgwin = new loginWinDialog();
	String[] start;
	String gameSelect = ""; 		// 게임 선택 구분 문자
	
	private JFrame frame;
	private JMenuBar mainMenu;
	private JMenu mnKeyta;
	private JMenu mnMemInfo;
	private JMenu mnHelp;
	private JMenuItem mnLogout;
	private JMenuItem mnGameClose;
	private JMenuItem mnUdMem;
	private JMenuItem mnGameHelp;
	private JMenuItem muUsInfo;
	private JLayeredPane layeredPane;
	private JPanel panel;
	private JButton btnCafe;
	private JButton btnBunsik;
	private JButton btnConvenience;
	private JButton btnChinese;
	private JPanel manamgeMentPanel;
	private JPanel mainPanel;
	private JMenuItem mntmAdminItem;
	private JMenuItem mntmMainMenuItem;
	private JButton btStart;
	private JButton btnGameStart;
	private JButton btnGoBack;
	private JPanel PlayGamePanel;
	private JPanel gameBtnPanel;
	private JButton btnGamePlayQuit;
	private JLabel playGamePanelBackground;
	private JPanel pnChinese;
	private JPanel pnConvenience;
	private JPanel pnBunsik;
	private JPanel pnCafe;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
		
		// 랭크에서 보여줄 이미지 교체 작업
		setIconImage();
		
		// login Dialog 창 띄우기
		frame.addWindowListener(new WindowAdapter() {
			@Override  // 윈도우 오픈드 사라짐  꼭 변수 windAct 사용할 것.
			public void windowActivated(WindowEvent e) {
				switch(windAct) {
				case 0 :
					location = frame.getLocationOnScreen();
					lgwin.setLocationRelativeTo(frame);
					lgwin.setVisible(true);
					windAct ++;
					break;
				case 1 :
					if(!lgwin.isVisible()) {
						if(lgwin.loginId.equals("admin"))
							mntmAdminItem.setVisible(true);
						mainpanel.getMaininfo(lgwin.loginId);
						mainpanel.getRanking();
						windAct ++;			
						frame.setEnabled(true);					
					}
					break;
				default :
					break;
				}
			}
		});
	}

	/**
	 * 
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setEnabled(false);
		frame.setBounds(100, 100, 900, 744);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLayeredPane());
		frame.setJMenuBar(getMainMenu());
	}
	
	private JMenuBar getMainMenu() {
		if (mainMenu == null) {
			mainMenu = new JMenuBar();
			mainMenu.add(getMnKeyta());
			mainMenu.add(getMnMemInfo());
			mainMenu.add(getMnHelp());
			mainMenu.add(getMntmMainMenuItem());
			mainMenu.add(getMntmAdminItem());
		}
		return mainMenu;
	}
	private JMenu getMnKeyta() {
		if (mnKeyta == null) {
			mnKeyta = new JMenu("키보드타이쿤");
			mnKeyta.add(getMnLogout());
			mnKeyta.add(getMnGameClose());
		}
		return mnKeyta;
	}
	private JMenu getMnMemInfo() {
		if (mnMemInfo == null) {
			mnMemInfo = new JMenu("회원 정보");
			mnMemInfo.add(getMnUdMem());
		}
		return mnMemInfo;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("도움말");
			mnHelp.add(getMnGameHelp());
			mnHelp.add(getMuUsInfo());
		}
		return mnHelp;
	}
	private JMenuItem getMnLogout() {
		if (mnLogout == null) {
			mnLogout = new JMenuItem("로그아웃");
			mnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
				mainpanel.tfName.setText("");
				mainpanel.tfId.setText("");
				mainpanel.tfLevel.setText("");
				mainpanel.tfMaxSco.setText("");
				mainpanel.lbImage.setText("Image");
				mainpanel.lbImage.setIcon(null);
				
				location = frame.getLocationOnScreen();
				lgwin.setLocationRelativeTo(frame);
				lgwin.setVisible(true);
				windAct = 1;
				}
			});
		}
		return mnLogout;
	}
	private JMenuItem getMnGameClose() {
		if (mnGameClose == null) {
			mnGameClose = new JMenuItem("게임종료");
			mnGameClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);;
				}
			});
		}
		return mnGameClose;
	}
	private JMenuItem getMnUdMem() {
		if (mnUdMem == null) {
			mnUdMem = new JMenuItem("회원 정보 수정");
			mnUdMem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!upMemb.isVisible()) {
						location = frame.getLocationOnScreen();
						upMemb.setLocationRelativeTo(frame);
						upMemb.setVisible(true);
						upMemb.getMembInfo(lgwin.loginId);
					}
				}
			});
		}
		return mnUdMem;
	}
	private JMenuItem getMnGameHelp() {
		if (mnGameHelp == null) {
			mnGameHelp = new JMenuItem("게임 도움말");
			mnGameHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!gmhelp.isVisible()) {
						location = frame.getLocationOnScreen();
						gmhelp.setLocationRelativeTo(frame);
						gmhelp.setVisible(true);
					}
				}
			});
		}
		return mnGameHelp;
	}
	private JMenuItem getMuUsInfo() {
		if (muUsInfo == null) {
			muUsInfo = new JMenuItem("맥피씨사조");
			muUsInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!plmac.isVisible()) {
						location = frame.getLocationOnScreen();
						plmac.setLocationRelativeTo(frame);
						plmac.setVisible(true);
					}
				}
			});
		}
		return muUsInfo;
	}
	private JMenuItem getMntmAdminItem() {
		if (mntmAdminItem == null) {
			mntmAdminItem = new JMenuItem("관리자");
			mntmAdminItem.setVisible(false);
			mntmAdminItem.setMaximumSize(new Dimension(80, 32767));
			mntmAdminItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.removeAll();
					layeredPane.add(manamgeMentPanel);
					layeredPane.repaint();
					layeredPane.revalidate();		
				}
			});
		}
		return mntmAdminItem;
	}
	private JMenuItem getMntmMainMenuItem() {
		if (mntmMainMenuItem == null) {
			mntmMainMenuItem = new JMenuItem("메인메뉴");
			mntmMainMenuItem.setMaximumSize(new Dimension(80, 100));
			mntmMainMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.removeAll();
					layeredPane.add(mainPanel);
					layeredPane.repaint();
					layeredPane.revalidate();	
				}
			});
		}
		return mntmMainMenuItem;
	}
	private JLayeredPane getLayeredPane() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setBounds(0, 0, 900, 700);
			layeredPane.setLayout(new CardLayout(0, 0));
			layeredPane.add(getMainPanel(), "name_23656712730558");
			mainpanel.add(getBtStart());
			layeredPane.add(getPanel(), "name_19546935962481");
			gameSelectPanel.add(getBtnCafe());
			gameSelectPanel.add(getBtnBunsik());
			gameSelectPanel.add(getBtnConvenience());
			gameSelectPanel.add(getBtnChinese());
			gameSelectPanel.add(getBtnGameStart());
			gameSelectPanel.add(getBtnGoBack());
			gameSelectPanel.add(getPnChinese());
			gameSelectPanel.add(getPnConvenience());
			gameSelectPanel.add(getPnBunsik());
			gameSelectPanel.add(getPnCafe());
			layeredPane.add(getManamgeMentPanel(), "name_21388213954312");
			layeredPane.add(getPlayGamePanel(), "name_28145266015879");
			playGamePanel.add(getGameBtnPanel());
			playGamePanel.add(getPlayGamePanelBackground());
		}
		return layeredPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = gameSelectPanel;
		}
		return panel;
	}
	private JButton getBtnCafe() {
		if (btnCafe == null) {
			btnCafe = new JButton("카페");
			btnCafe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameSelectPanel.textChange("카페");
					gameSelect = "카페";
					
//					pnCafe.setBackground(new Color(252, 224, 151));
//					pnChinese.setBackground(new Color(255, 244, 213));
//					pnConvenience.setBackground(new Color(255, 244, 213));
//					pnBunsik.setBackground(new Color(255, 244, 213));
				}
			});
			btnCafe.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
			btnCafe.setBorder(new MatteBorder(3, 3, 3, 3, new Color(252, 224, 151)));
			btnCafe.setBounds(81, 172, 200, 50);
		}
		return btnCafe;
	}
	private JButton getBtnBunsik() {
		if (btnBunsik == null) {
			btnBunsik = new JButton("분식집");
			btnBunsik.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameSelectPanel.textChange("분식집");
					gameSelect = "분식집";
					
//					pnBunsik.setBackground(new Color(252, 224, 151));
//					pnChinese.setBackground(new Color(255, 244, 213));
//					pnConvenience.setBackground(new Color(255, 244, 213));
//					pnCafe.setBackground(new Color(255, 244, 213));
				}
			});
			btnBunsik.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
			btnBunsik.setBorder(new MatteBorder(3, 3, 3, 3, new Color(252, 224, 151)));
			btnBunsik.setBounds(81, 283, 200, 50);
		}
		return btnBunsik;
	}
	private JButton getBtnConvenience() {
		if (btnConvenience == null) {
			btnConvenience = new JButton("편의점");
			btnConvenience.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameSelectPanel.textChange("편의점");
					gameSelect = "편의점";
					
//					pnConvenience.setBackground(new Color(252, 224, 151));
//					pnChinese.setBackground(new Color(255, 244, 213));
//					pnBunsik.setBackground(new Color(255, 244, 213));
//					pnCafe.setBackground(new Color(255, 244, 213));
				}
			});
			btnConvenience.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
			btnConvenience.setBorder(new MatteBorder(3, 3, 3, 3, new Color(252, 224, 151)));
			btnConvenience.setBounds(81, 383, 200, 50);
		}
		return btnConvenience;
	}
	private JButton getBtnChinese() {
		if (btnChinese == null) {
			btnChinese = new JButton("중국집");
			btnChinese.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameSelectPanel.textChange("중국집");
					gameSelect = "중국집";
					
//					pnChinese.setBackground(new Color(252, 224, 151));
//					pnConvenience.setBackground(new Color(255, 244, 213));
//					pnBunsik.setBackground(new Color(255, 244, 213));
//					pnCafe.setBackground(new Color(255, 244, 213));
				}
			});
			btnChinese.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
			btnChinese.setBorder(new MatteBorder(3, 3, 3, 3, new Color(252, 224, 151)));
			btnChinese.setBounds(81, 492, 200, 50);
		}
		return btnChinese;
	}
	private JPanel getPnChinese() {
		if(pnChinese == null) {
			pnChinese = new JPanel();
			pnChinese.setBackground(new Color(255, 244, 213));
			pnChinese.setBounds(81, 492, 200, 50);
		}
		return pnChinese;
	}
	private JPanel getPnConvenience() {
		if(pnConvenience == null) {
			pnConvenience = new JPanel();
			pnConvenience.setBackground(new Color(255, 244, 213));
			pnConvenience.setBounds(81, 383, 200, 50);
		}
		return pnConvenience;
	}
	
	private JPanel getPnBunsik() {
		if(pnBunsik == null) {
			pnBunsik = new JPanel();
			pnBunsik.setBackground(new Color(255, 244, 213));
			pnBunsik.setBounds(81, 283, 200, 50);
		}
		return pnBunsik;
	}
	private JPanel getPnCafe() {
		if(pnCafe == null) {
			pnCafe = new JPanel();
			pnCafe.setBackground(new Color(255, 244, 213));
			pnCafe.setBounds(81, 172, 200, 50);
		}
		return pnCafe;
	}
	
	private JButton getBtnGameStart() {
		if (btnGameStart == null) {
			btnGameStart = new JButton("게임 시작");
			btnGameStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//	게임 모드 설명창 빈칸인지 확인하기
					if(gameSelectPanel.textIsEmpty() == false) {
						layeredPane.removeAll();
						layeredPane.add(playGamePanel);
						layeredPane.repaint();
						layeredPane.revalidate();
						// 게임 실행 
						game game = new game();
						playGamePanelBackground.setIcon(new ImageIcon(game.backImg()));
						game.store_name = gameSelect;
						game.game_cust_setup();
						game.game_menu_setup();
						playGamePanel.count_timer();
					} else
						JOptionPane.showMessageDialog(null, "게임모드를 선택하세요");
				}
			});
			btnGameStart.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
			btnGameStart.setBorder(new MatteBorder(1, 1, 1, 1, new Color(64, 64, 64)));
			btnGameStart.setBounds(226, 582, 200, 50);
		}
		return btnGameStart;
	}
	private JButton getBtnGoBack() {
		if (btnGoBack == null) {
			btnGoBack = new JButton("돌아가기");
			btnGoBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.removeAll();
					layeredPane.add(mainPanel);
					layeredPane.repaint();
					layeredPane.revalidate();	
					mainMenu.setEnabled(true);
				}
			});
			btnGoBack.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
			btnGoBack.setBorder(new MatteBorder(1, 1, 1, 1, new Color(64, 64, 64)));
			btnGoBack.setBounds(516, 582, 200, 50);
		}
		return btnGoBack;
	}
	private JPanel getManamgeMentPanel() {
		if (manamgeMentPanel == null) {
			manamgeMentPanel = managementpanel;
		}
		return manamgeMentPanel;
	}
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = mainpanel;
		}
		return mainPanel;
	}
	private JButton getBtStart() {
		if (btStart == null) {
			btStart = new JButton("게임선택");
			btStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layeredPane.removeAll();
					layeredPane.add(gameSelectPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
					mainMenu.setEnabled(false);
					frame.setBounds(frame.getLocation().x, frame.getLocation().y, 900, 700);
				}
			});
			btStart.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
			btStart.setBorder(new LineBorder(Color.DARK_GRAY));
			btStart.setBackground(new Color(204, 102, 102));
			btStart.setBounds(374, 551, 152, 37);
		}
		return btStart;
	}	
	
	// 랭크 이미지 변경하기
	private void setIconImage() {
		mainpanel.lbExpNum1.setIcon(new ImageIcon(rank.CrownGold));
		mainpanel.lbExpNum2.setIcon(new ImageIcon(rank.CrownSilver));
		mainpanel.lbExpNum3.setIcon(new ImageIcon(rank.CrownCooper));
		
		mainpanel.lbMaxNum1.setIcon(new ImageIcon(rank.CrownGold));
		mainpanel.lbMaxNum2.setIcon(new ImageIcon(rank.CrownSilver));
		mainpanel.lbMaxNum3.setIcon(new ImageIcon(rank.CrownCooper));		
	}
	
	private JPanel getPlayGamePanel() {
		if (PlayGamePanel == null) {
			PlayGamePanel = playGamePanel;
		}
		return PlayGamePanel;
	}
	private JPanel getGameBtnPanel() {
		if (gameBtnPanel == null) {
			gameBtnPanel = new JPanel();
			gameBtnPanel.setBackground(Color.WHITE);
			gameBtnPanel.setBackground(new Color(252, 178, 85));
			gameBtnPanel.setBounds(775, 0, 125, 55);
			gameBtnPanel.setLayout(null);
			gameBtnPanel.add(getBtnGamePlayQuit());
		}
		return gameBtnPanel;
	}
	private JButton getBtnGamePlayQuit() {
		if (btnGamePlayQuit == null) {
			btnGamePlayQuit = new JButton("종료");
			btnGamePlayQuit.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					gameBtnPanel.setBackground(new Color(202, 227, 154));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					gameBtnPanel.setBackground(new Color(252, 178, 85));
				}
			});
			btnGamePlayQuit.setBackground(Color.WHITE);
			btnGamePlayQuit.setBorder(null);
			btnGamePlayQuit.setBounds(0, 0, 125, 55);
			btnGamePlayQuit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playGamePanel.stopAllTimer();					
					playGamePanel.pnResult.setVisible(false);
					playGamePanel.tfInput.setEditable(true);
					playGamePanel.clearTextField();
					
					layeredPane.removeAll();
					layeredPane.add(mainPanel);
					layeredPane.repaint();
					layeredPane.revalidate();	
					mainMenu.setEnabled(true);				
				}
			});
		}
		return btnGamePlayQuit;
	}
	private JLabel getPlayGamePanelBackground() {
		if (playGamePanelBackground == null) {
			playGamePanelBackground = new JLabel("");
			playGamePanelBackground.setBounds(0, 0, 900, 740);
		}
		return playGamePanelBackground;
	}
}
