package com.javalec.setup;

public class SH_Bean {
	
	//field
	
	int exp;
	int maxScore;
	String id;
	String name;
	String password;
	String resident;
	
	public SH_Bean() {
		// TODO Auto-generated constructor stub
	}

	
	
	public SH_Bean(String id, int exp, int maxScore) {
		super();
		this.id = id;
		this.exp = exp;
		this.maxScore = maxScore;
	}



	public SH_Bean(String id, String name, String password, String resident, int exp, int maxScore) {
		super();
		this.exp = exp;
		this.maxScore = maxScore;
		this.id = id;
		this.name = name;
		this.password = password;
		this.resident = resident;
	}
	
	

	public SH_Bean(String id, String name, int exp, int maxScore) {
		super();
		this.exp = exp;
		this.maxScore = maxScore;
		this.id = id;
		this.name = name;
	}	
	
	public SH_Bean(String id, String name, String password, String resident) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.resident = resident;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResident() {
		return resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	
	
	
	

}
