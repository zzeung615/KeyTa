package com.javalec.setup;

public class GameMenu_Bean {

	String md_name;
	String store_code;
	int md_price;
	
	int exp;
	int maxScore;
	
	// 	생성자 매개변수 유형 같으면 안됨.
	public GameMenu_Bean() {
		
	}
	
	public GameMenu_Bean(String md_name, String store_code, int md_price) {
		super();
		this.md_name = md_name;
		this.store_code = store_code;
		this.md_price = md_price;
	}
	
	public String getMd_name() {
		return md_name;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	public String getStore_code() {
		return store_code;
	}

	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}


	public int getMd_price() {
		return md_price;
	}

	public void setMd_price(int md_price) {
		this.md_price = md_price;
	}
	
	

}
