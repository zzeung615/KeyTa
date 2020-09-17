package com.javalec.setup;

public class customer_Bean {

	int cCode;
	String name;
	String waitTime;
		
	public customer_Bean(int cCode) {
		super();
		this.cCode = cCode;
	}	

	public customer_Bean(String name, String waitTime) {
		super();
		this.name = name;
		this.waitTime = waitTime;
	}
	
	public customer_Bean(int cCode, String name, String waitTime) {
		super();
		this.cCode = cCode;
		this.name = name;
		this.waitTime = waitTime;
	}
	
	public int getcCode() {
		return cCode;
	}
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}	
}
