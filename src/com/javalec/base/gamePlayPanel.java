package com.javalec.base;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.javalec.function.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class gamePlayPanel extends JPanel {
	
	public JTextField tfInput;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblStore;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lbCustImg;
	private JLabel lblCntFail;
	private JLabel lblGameTime;
	private JLabel lblScore;
	private JLabel lblCntSucess;
	private JLabel lblCustNum;
	private JLabel lblOrder;
	private JLabel lblCustName;
	private JLabel label_5;
	private JLabel lblOrderTime;
	public JPanel pnResult;
	private JLabel label_1_1;
	private JLabel lblResultCustomerSucess;
	private JLabel label_2_1;
	private JLabel lblResultCustomerFail;
	private JLabel label_3_1;
	private JLabel lblResultScore;
	private JLabel label_3_1_1;
	private JLabel lblResultExp;
	public JLabel lblBackgrounImg;		// 백그라운드이미지
	private JLabel lblCount;	
		
	private JPanel pnCount;
	private JPanel panelPaint;
	private JPanel pnCustImg;
	
	private static game game = new game();
	//	게임 플레이 타임 
	private int game_time=0;
	//	고객 대기시간
	private int cust_time=0;
	// 	대기고객 수
	private int cust_num=0;
	//	성공, 실패 카운트
	private int count_fail=0;
	private int count_sucess=0;
	private String cust_name="";
	private String str="";
	private String[] claim = {"전 이런거 시킨적 없는데요?", "이게 뭐에요?", "빨리 주세요.","장난하세요?"};
	private int countdown = 3;
	private int score=0;	//	게임 수익금
	private int randStep=0;
	private int randStep2=0;
	
	//	level 1-3 	메뉴1개 	level 4-  메뉴2개까지
	private int gamelevel;
	private int userlevel=6;
	// 게임 타이머/ 고객 타이머
	Timer g_timer;
	Timer c_timer;
	TimerTask g_task;
	TimerTask c_task;
	Timer count_timer;
	TimerTask count_task;
	public static String store_name;
	private String[] temp = new String[2];

	//	게임 시작
	String order_str="";
	private JLabel lblFail;
	
	// 카운트타이머 추가 
	private void countTask() {
		count_task = new TimerTask() {
			@Override
			public void run() {
				lblCount.setText(Integer.toString(countdown));
				countdown--;
				if(countdown < 0) {
					lblCount.setText("시작!");
					all_timer();
					count_timer.cancel();
				}
			}
		};
	}
	
	//	고객시간 태스크
	private void newCTask() {
		c_task = new TimerTask() {
			

			@Override
			public void run() {
				
				//	아무것도 입력되지 않은채로 시간이 지나거나 답이 다를경우
				if(cust_time < 1) {
					System.out.println("다음손님");
					isEmptyField();
					randStep = (int)(Math.random()*11); //		랜덤 step
					randStep2 = (int)(Math.random()*11); //		랜덤 step
					gamelevel = (int)(Math.random()*userlevel); //		랜덤 step
					//	품목 같을경우 한개만
					if(randStep == randStep2)
						gamelevel = 1;
					//	메뉴를 여러개 담기위한 배열
					temp[0]=game.qTxt[0][randStep];
					temp[1]=game.qTxt[0][randStep2];
					chooseCustImg();
					cust_time = Integer.parseInt(game.custName[1][randStep]); //		다음 고객의 대기시간으로 세팅
					cust_name = game.custName[0][randStep];
					cust_num --;
					lblFail.setText("");
				}
				
				setTextField();
				cust_time--;
			}
		};
	}
	
	//	게임시간 태스크
	private void newGTask() {
		g_task = new TimerTask() {
			@Override
			public void run() {
				pnCount.setVisible(false);
				lblGameTime.setText(Integer.toString(game_time-1));
				game_time -= 1;
				if(game_time < 1) {
					stopGame();
				}	
			}
		};
	}
	private void chooseCustImg() {
		lbCustImg.setIcon(new ImageIcon("/Users/tj/Downloads/고객이미지/"+game.custName[0][randStep]+".png"));
	}
	public void all_timer() {
		game_timer();
		customer_timer();
	}
	//	게임 시작 타이머
	public void count_timer() {
		count_timer = new Timer();
		countTask();
		count_timer.schedule(count_task, 0, 1000);
	}
	
	// 총 게임시간 타이머
	public void game_timer() {
		game_time = 30; //	기본 30초 1라당 3초 증가 할것   ********************** 작업필요 ****************************

		g_timer = new Timer();
		newGTask();
		g_timer.schedule(g_task, 0, 1000);
	}
	
	//	고객 타이머
	public void customer_timer() {
		cust_time = Integer.parseInt(game.custName[1][0]);
		
//		cust_num = 3;
		lbCustImg.setIcon(new ImageIcon("/Users/tj/Downloads/고객이미지/"+ game.custName[0][randStep] +".png"));
		cust_name = game.custName[0][0];
		//	임시배열 초기값
		temp[0]=game.qTxt[0][0];
		temp[1]=game.qTxt[0][5];

		c_timer = new Timer();
		newCTask();
		c_timer.schedule(c_task, 0, 1000);
		
	}
	//	tfInput 가 비어있는가?
	private void isEmptyField() {
		if(str.equals("") == true)
			count_fail++;
	}
	private void addScore() {
		score += Integer.parseInt(game.qTxt[1][randStep]);
		lblScore.setText(Integer.toString(score));
	}
	public void stopAllTimer() {
		c_task.cancel();
		g_task.cancel();
		c_timer.cancel();
		g_timer.cancel();
	}
	private void stopGame() {
		stopAllTimer();
		clearTextField();
		tfInput.setEditable(false);
		lblResultCustomerSucess.setText(Integer.toString(count_sucess));
		lblResultCustomerFail.setText(Integer.toString(count_fail));
		lblResultScore.setText(Integer.toString(score));
		lblResultExp.setText(Integer.toString(score/100));
		//	결과창 보이기
		pnResult.setVisible(true);
	}
	private void setTextField() {
		lblOrderTime.setText(Integer.toString(cust_time));
//		lblCustNum.setText(Integer.toString(cust_num));
		lblCustName.setText(game.custName[0][randStep]);;
		lblCntFail.setText(Integer.toString(count_fail));
		lblCntSucess.setText(Integer.toString(count_sucess));
		
		if(gamelevel > 3)
			order_str=temp[0] + "/" + temp[1];
		else
			order_str=temp[0];
		
		lblOrder.setText(order_str);
		
		if(lblOrder.getText().trim().equals("/")) { 
			lblOrder.setText("");	
			count_sucess++;
			cust_time = 0;
		}

	}
	// 상단 정보창 정리
	public void clearTextField() {
		lblGameTime.setText("0");
		lblOrderTime.setText("0");
		lblCustName.setText("");
		lblOrder.setText("장사종료");
		lblCntSucess.setText("0");
		lblCntFail.setText("0");
		lblScore.setText("0");
	}
	private void showMsg(String str) {
		JOptionPane.showMessageDialog(null, str);
	}
	
	public gamePlayPanel() {
		
		setLayout(null);
		setBounds(100,100,900,744);
		add(getLblFail());
		add(getPnCount());
		add(getPnResult());
		add(getLabel());
		add(getLabel_2());
		add(getLabel_3());
		add(getLblStore());
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getLabel_1());
		add(getTfInput());
		add(getLblCntFail());
		add(getLblGameTime());
		add(getLblScore());
		add(getLblCntSucess());
		add(getLblCustNum());
		add(getLabel_5());
		add(getLblOrderTime());
		add(getLblCustName());
		add(getPanelPaint());
		add(getPnCustImg());
		add(getLblBackgrounImg());
		
	}
	private JTextField getTfInput() {
		if (tfInput == null) {
			tfInput = new JTextField();
			tfInput.setBorder(null);
			tfInput.setBackground(Color.WHITE);
			tfInput.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			tfInput.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					//	Enter 입력 
					//	현재 오답입력 이벤트 제외시킴
					if(e.getKeyCode() == 10 && tfInput.isEditable() == true) {
						int cnt=0;
						str = tfInput.getText().trim();
						if(gamelevel > 3) {
							for(int i=0; i<2; i++) {
								if(str.equals(temp[i]) == true) { 
									temp[i]="";
									addScore();
									cnt++;
								}
								if(str.equals(temp[i]) == true)  {
									if(temp[1]==temp[0] && cnt==1) {
										i--;
										continue;
									}
									temp[1]="";
									addScore();
								}
							}
							c_task.run();
							cust_time++;
						} else if((str.equals(lblOrder.getText().trim()) == false && str.equals("") == false )) {
							int randNum = (int)(Math.random()*claim.length);
							lblFail.setText(claim[randNum]);
//							c_task.run();
						} else if(str.equals(lblOrder.getText().trim()) == true) {   // level 3 이하
							lblOrder.setText("");
							count_sucess++;
							cust_time = 0;
							addScore();
							c_task.run();
							
						}
						tfInput.setText("");
					}
				}
			});
			tfInput.setBounds(61, 617, 778, 37);
			tfInput.setColumns(10);
		}
		return tfInput;
		
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("주문 고객");
			label.setForeground(Color.DARK_GRAY);
			label.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			label.setBounds(11, 17, 73, 16);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("서빙 성공");
			label_1.setForeground(Color.DARK_GRAY);
			label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			label_1.setBounds(11, 45, 73, 16);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("서빙 실패");
			label_2.setForeground(Color.DARK_GRAY);
			label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			label_2.setBounds(11, 73, 73, 16);
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("매출");
			label_3.setForeground(Color.DARK_GRAY);
			label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			label_3.setBounds(177, 45, 38, 16);
		}
		return label_3;
	}
	private JLabel getLblStore() {
		if (lblStore == null) {
			lblStore = new JLabel("");
			lblStore.setForeground(Color.DARK_GRAY);
			lblStore.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			lblStore.setHorizontalAlignment(SwingConstants.CENTER);
			lblStore.setBounds(419, 17, 61, 31);
		}
		return lblStore;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("대기 고객 ");
			lblNewLabel.setForeground(Color.DARK_GRAY);
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel.setBounds(442, 262, 62, 19);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("남은 시간");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			lblNewLabel_1.setForeground(Color.DARK_GRAY);
			lblNewLabel_1.setBounds(713, 162, 96, 28);
		}
		return lblNewLabel_1;
	}
	private JPanel getPanelPaint() {
		if (panelPaint == null) {
			panelPaint = new JPanel();
			panelPaint.setBackground(Color.WHITE);
			panelPaint.setBounds(267, 293, 466, 62);
			panelPaint.setLayout(null);
			panelPaint.add(getLblOrder());
			panelPaint.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
				}
			});
		}
		return panelPaint;
	}
	private JLabel getLbCustImg() {
		if (lbCustImg == null) {
			lbCustImg = new JLabel("");
			lbCustImg.setIcon(new ImageIcon("/Users/tj/Downloads/default.png"));
			lbCustImg.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbCustImg;
	}
	private JPanel getPnCustImg() {
		if (pnCustImg == null) {
			pnCustImg = new JPanel();
			pnCustImg.setBackground(Color.WHITE);
			pnCustImg.setBounds(139, 258, 118, 120);
			pnCustImg.setLayout(new BorderLayout(0, 0));
			pnCustImg.add(getLbCustImg());
		}
		return pnCustImg;
	}
	private JLabel getLblCntFail() {
		if (lblCntFail == null) {
			lblCntFail = new JLabel("0");
			lblCntFail.setForeground(Color.DARK_GRAY);
			lblCntFail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblCntFail.setBounds(101, 73, 78, 16);
		}
		return lblCntFail;
	}
	private JLabel getLblGameTime() {
		if (lblGameTime == null) {
			lblGameTime = new JLabel("0");
			lblGameTime.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
			lblGameTime.setForeground(Color.DARK_GRAY);
			lblGameTime.setBounds(821, 151, 50, 51);
		}
		return lblGameTime;
	}
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("0");
			lblScore.setForeground(Color.DARK_GRAY);
			lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblScore.setBounds(232, 45, 78, 16);
		}
		return lblScore;
	}
	private JLabel getLblCntSucess() {
		if (lblCntSucess == null) {
			lblCntSucess = new JLabel("0");
			lblCntSucess.setForeground(Color.DARK_GRAY);
			lblCntSucess.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblCntSucess.setBounds(101, 45, 71, 16);
		}
		return lblCntSucess;
	}
	private JLabel getLblCustNum() {
		if (lblCustNum == null) {
			lblCustNum = new JLabel("0");
			lblCustNum.setForeground(Color.DARK_GRAY);
			lblCustNum.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblCustNum.setBounds(515, 263, 61, 16);
		}
		return lblCustNum;
	}
	private JLabel getLblOrder() {
		if (lblOrder == null) {
			lblOrder = new JLabel("");
			lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
			lblOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			lblOrder.setBounds(43, 15, 379, 31);
		}
		return lblOrder;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("주문 처리 시간");
			label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			label_5.setForeground(Color.DARK_GRAY);
			label_5.setBounds(286, 262, 103, 19);
		}
		return label_5;
	}
	private JLabel getLblOrderTime() {
		if (lblOrderTime == null) {
			lblOrderTime = new JLabel("0");
			lblOrderTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblOrderTime.setForeground(Color.DARK_GRAY);
			lblOrderTime.setBounds(390, 263, 61, 16);
		}
		return lblOrderTime;
	}
	private JLabel getLblCustName() {
		if (lblCustName == null) {
			lblCustName = new JLabel("이름");
			lblCustName.setHorizontalAlignment(SwingConstants.CENTER);
			lblCustName.setForeground(Color.DARK_GRAY);
			lblCustName.setBounds(158, 215, 81, 31);
			lblCustName.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		}
		return lblCustName;
	}
	private JPanel getPnResult() {
		if (pnResult == null) {
			pnResult = new JPanel();
			pnResult.setBounds(316, 269, 267, 205);
			pnResult.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.DARK_GRAY));
			pnResult.setVisible(false);
			pnResult.setBackground(Color.WHITE);
			pnResult.setLayout(null);
			pnResult.add(getLabel_1_1());
			pnResult.add(getLblResultCustomerSucess());
			pnResult.add(getLabel_2_1());
			pnResult.add(getLblResultCustomerFail());
			pnResult.add(getLabel_3_1());
			pnResult.add(getLblResultScore());
			pnResult.add(getLabel_3_1_1());
			pnResult.add(getLblResultExp());
		}
		return pnResult;
	}
	private JLabel getLabel_1_1() {
		if (label_1_1 == null) {
			label_1_1 = new JLabel("완료 고객 수 : ");
			label_1_1.setForeground(Color.DARK_GRAY);
			label_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			label_1_1.setBounds(24, 23, 90, 19);
		}
		return label_1_1;
	}
	private JLabel getLblResultCustomerSucess() {
		if (lblResultCustomerSucess == null) {
			lblResultCustomerSucess = new JLabel("0");
			lblResultCustomerSucess.setForeground(Color.DARK_GRAY);
			lblResultCustomerSucess.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblResultCustomerSucess.setBounds(131, 24, 110, 16);
		}
		return lblResultCustomerSucess;
	}
	private JLabel getLabel_2_1() {
		if (label_2_1 == null) {
			label_2_1 = new JLabel("실패 고객 수 :");
			label_2_1.setForeground(Color.DARK_GRAY);
			label_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			label_2_1.setBounds(24, 51, 85, 19);
		}
		return label_2_1;
	}
	private JLabel getLblResultCustomerFail() {
		if (lblResultCustomerFail == null) {
			lblResultCustomerFail = new JLabel("0");
			lblResultCustomerFail.setForeground(Color.DARK_GRAY);
			lblResultCustomerFail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblResultCustomerFail.setBounds(131, 52, 110, 16);
		}
		return lblResultCustomerFail;
	}
	private JLabel getLabel_3_1() {
		if (label_3_1 == null) {
			label_3_1 = new JLabel("매출");
			label_3_1.setForeground(Color.DARK_GRAY);
			label_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			label_3_1.setBounds(73, 78, 26, 19);
		}
		return label_3_1;
	}
	private JLabel getLblResultScore() {
		if (lblResultScore == null) {
			lblResultScore = new JLabel("0");
			lblResultScore.setForeground(Color.DARK_GRAY);
			lblResultScore.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblResultScore.setBounds(131, 79, 110, 16);
		}
		return lblResultScore;
	}
	private JLabel getLabel_3_1_1() {
		if (label_3_1_1 == null) {
			label_3_1_1 = new JLabel("Exp : ");
			label_3_1_1.setForeground(Color.DARK_GRAY);
			label_3_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			label_3_1_1.setBounds(73, 102, 41, 19);
		}
		return label_3_1_1;
	}
	private JLabel getLblResultExp() {
		if (lblResultExp == null) {
			lblResultExp = new JLabel("0");
			lblResultExp.setForeground(Color.DARK_GRAY);
			lblResultExp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblResultExp.setBounds(131, 103, 110, 16);
		}
		return lblResultExp;
	}
	private JLabel getLblBackgrounImg() {
		if (lblBackgrounImg == null) {
			lblBackgrounImg = new JLabel("");
			lblBackgrounImg.setIcon(new ImageIcon("/Users/tj/Downloads/background_ver1.png"));
			lblBackgrounImg.setBounds(0, 0, 900, 744);
		}
		return lblBackgrounImg;
	}
	private JPanel getPnCount() {
		if (pnCount == null) {
			pnCount = new JPanel();
			pnCount.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.DARK_GRAY));
			pnCount.setBackground(Color.WHITE);
			pnCount.setBounds(356, 180, 188, 51);
			pnCount.setLayout(new BorderLayout(0, 0));
			pnCount.add(getLblCount(), BorderLayout.CENTER);
		}
		return pnCount;
	}
	private JLabel getLblCount() {
		if (lblCount == null) {
			lblCount = new JLabel("");
			lblCount.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCount;
	}
	private JLabel getLblFail() {
		if (lblFail == null) {
			lblFail = new JLabel();
			lblFail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblFail.setBounds(554, 362, 179, 16);
		}
		return lblFail;
	}
}
