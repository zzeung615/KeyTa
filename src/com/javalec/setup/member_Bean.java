package com.javalec.setup;

public class member_Bean {

	int m_Code;
	String ID;
	String Name;
	String PassWord;
	String Resident;
	
	public member_Bean(int m_Code, String iD, String name, String resident) {
		this.m_Code = m_Code;
		ID = iD;
		Name = name;
		Resident = resident;
	}

	public member_Bean(int m_Code, String iD, String name, String passWord, String resident) {
		this.m_Code = m_Code;
		ID = iD;
		Name = name;
		PassWord = passWord;
		Resident = resident;
	}

	public int getM_Code() {
		return m_Code;
	}

	public void setM_Code(int m_Code) {
		this.m_Code = m_Code;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getResident() {
		return Resident;
	}

	public void setResident(String resident) {
		Resident = resident;
	}
	
	
	
}
