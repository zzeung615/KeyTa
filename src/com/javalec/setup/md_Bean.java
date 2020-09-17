package com.javalec.setup;

public class md_Bean {

	int m_Code;
	String store_Name;
	String md_Name;
	String price;
	
	public md_Bean(String store_Name, String md_Name, String price) {
		super();
		this.store_Name = store_Name;
		this.md_Name = md_Name;
		this.price = price;
	}	

	public md_Bean(int m_Code, String store_Name, String md_Name, String price) {
		super();
		this.m_Code = m_Code;
		this.store_Name = store_Name;
		this.md_Name = md_Name;
		this.price = price;
	}

	public int getM_Code() {
		return m_Code;
	}

	public void setM_Code(int m_Code) {
		this.m_Code = m_Code;
	}

	public String getStore_Name() {
		return store_Name;
	}

	public void setStore_Name(String store_Name) {
		this.store_Name = store_Name;
	}

	public String getMd_Name() {
		return md_Name;
	}

	public void setMd_Name(String md_Name) {
		this.md_Name = md_Name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}	
}
