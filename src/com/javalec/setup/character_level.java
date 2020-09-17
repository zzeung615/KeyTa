package com.javalec.setup;

public class character_level {

	int exp;	// 케릭터 경험치	
	
	public character_level(int exp) {
		super();
		this.exp = exp;
	}
	
	// 케릭터 경험치를 비교해서 나타내야할 레벨 	
	// ex) ch1 = 30000; level1 -> 3000 level2 -> 15000 level3 -> 30000 이 케릭터의 레벨은 3
	public int character_level() {
		int level = 1;
		
		if (exp > 1000)
			level = 2;
		else if (exp > 3000)
			level = 3;
		else if (exp > 6000)
			level = 4;
		else if (exp > 10000)
			level = 5;
		else if (exp > 15000)
			level = 6;
		else if (exp > 21000)
			level = 7;
		else if (exp > 28000)
			level = 8;
		else if (exp > 36000)
			level = 9;
		else if (exp > 45000)
			level = 10;
		
		return level;		
	}
	
}
