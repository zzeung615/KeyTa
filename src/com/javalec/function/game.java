package com.javalec.function;

import java.io.File;
import java.util.ArrayList;

import com.javalec.base.gamePlayPanel;
import com.javalec.setup.GameMenu_Bean;
import com.javalec.setup.customer_Bean;

public class game {

	int sucess_customer;		// 받은 손님 수
	int fail_customer;			// 받지 못한 손님 수
	int income;					// 벌어들인 돈
	
	int stand_by_customer;		// 대기 고객
				// 총 게임 시간
	
	public static String store_name;				// 가게 선정
	int count=0;
	
	public String backImg = ""; 		// 배경 이미지
	
	public int[] randIndex; 
	public int[] data; 
	public static String[][] custName;
	public static String[][] qTxt;
	
	static gamePlayPanel pnGame = new gamePlayPanel();
	static game_db_Action dbAction = new game_db_Action();
	static ArrayList<customer_Bean> bean_cust = dbAction.selectCustomer();
	static ArrayList<GameMenu_Bean> bean_menu;
	
	//	고객정보 랜덤 세팅
	public void game_cust_setup() {
		randIndex(bean_cust.size());
		putRandCustInfo(bean_cust);
		
	}
	//	해당 가게 메뉴 랜덤 세팅
	public void game_menu_setup() {
		bean_menu = dbAction.selectMenu(store_name);
		putRandMD(bean_cust.size(),bean_menu);
	}
	// 상단 정보패널 업데이트
	public void game_state_update() {
		
	}
	
	// 메뉴 랜덤으로 받을거 선택하는 메소드
	public void game_menu_select() {
		
	}
	
	// 메뉴 받은거 처리하는 메소드
	public void game_menu_clear() {
		
	}
	
	// 메뉴 받을 손님 & 대기시간 
	public void game_customer_waiting() {
		
	}
	
	// 게임 끝낫을때 나오는 정보창
	public void game_clear_info() {
		
	}
	
	
	//	중복되지않는 랜덤한 숫자 배열 생성
	private void randIndex(int bean_size) {
		
		randIndex = new int[bean_size];
		data = new int[bean_size];
//		중복되지않는 랜덤의수 randIndex[] 배열에 저장
		for(int i=0; i<bean_size; i++) {
			randIndex[i] = (int)(Math.random()*bean_size);
			data[i] =randIndex[i];
			if(i>0) {
				for(int j=0; j<i; j++)
					if(randIndex[i] == data[j])
						i--;
			}
		}
	}
	
	//	randIndex[] 의 요소를 첨자로 사용해 고객정보 순서 랜덤하게 배열에저장
	private void putRandCustInfo(ArrayList<customer_Bean> bean_cust) {
//		이름을과 각 웨이팅시간을 랜덤하게 저장할 배열
		custName = new String[2][bean_cust.size()];
//		randIndex[] 배열의 각 요소의 값을 custName[] 배열의 요소로 사용 custName[] 배열에 랜덤하게 이름 저장하기
		for(int i=0; i<bean_cust.size(); i++) {
			custName[0][randIndex[i]] = bean_cust.get(i).getName();
			custName[1][randIndex[i]] = bean_cust.get(i).getWaitTime();
		}
	}
	public void putRandMD(int cust_num, ArrayList<GameMenu_Bean> bean_menu) {
		qTxt = new String[2][cust_num];
		for(int i=0; i<cust_num; i++) {
			int j = (int)(Math.random()*bean_menu.size());
			qTxt[0][randIndex[i]] = bean_menu.get(j).getMd_name();
			qTxt[1][randIndex[i]] = Integer.toString(bean_menu.get(j).getMd_price());
			
		}
	}
	
	public String backImg() {
		File file = new File("images");
		
		backImg = file.getAbsolutePath() + "/background_ver1.png";
		return backImg;
	}
}
