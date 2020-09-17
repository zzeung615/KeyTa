package com.javalec.function;

import java.io.File;

public class rank {
	String id;		// 랭킹에 표시할 아이디
	int level;		//            레벨
	int score;		//            점수

	public String CrownGold = "";		// 왕관 골드
	public String CrownSilver = "";	// 왕관 실버
	public String CrownCooper = "";	// 왕관 브론즈	
	
	public rank() {
		super();
		rank_icon();
	}

	// 랭킹에 표시할 레벨
	public void rank_level() {
		
	}
	
	// 랭킹에 표시할 점수
	public void rank_score() {
		
	}
	
	public void rank_icon() {
		File file = new File("images");
		
		CrownGold = file.getAbsolutePath() + "/CrownGoldd.png";
		CrownSilver = file.getAbsoluteFile() + "/CrownSilver.png";
		CrownCooper = file.getAbsolutePath() + "/CrownCooper.png";				
	}
	
}
