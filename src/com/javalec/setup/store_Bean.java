package com.javalec.setup;

public class store_Bean {

	int m_Code;
	String Name;	
	
	public store_Bean(String name) {
		super();
		Name = name;
	}

	public store_Bean(int m_Code, String name) {
		super();
		this.m_Code = m_Code;
		Name = name;
	}

	public int getM_Code() {
		return m_Code;
	}

	public void setM_Code(int m_Code) {
		this.m_Code = m_Code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}	
}
