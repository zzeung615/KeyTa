package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.setup.db_Share_Var;
import com.javalec.setup.md_Bean;

public class managementStore {

    private final String url_mysql = db_Share_Var.DBNAME;
    private final String id_mysql = db_Share_Var.DBUSER;
    private final String pw_mysql = db_Share_Var.DBPASS;
    
    int store_mcode;
	String store_name;			// 관리자가 수정할 가게 이름
	String store_menu;			// 관리자가 수정할 메뉴
	String store_price;			// 관리자가 수정할 메뉴의 가격
	
	public managementStore() {
		super();
	}		

	public managementStore(int store_mcode) {
		super();
		this.store_mcode = store_mcode;
	}

	public managementStore(String store_name, String store_menu, String store_price) {
		super();
		this.store_name = store_name;
		this.store_menu = store_menu;
		this.store_price = store_price;
	}		
	
	public managementStore(int store_mcode, String store_name, String store_menu, String store_price) {
		super();
		this.store_mcode = store_mcode;
		this.store_name = store_name;
		this.store_menu = store_menu;
		this.store_price = store_price;
	}

	// 관리자가 사용하는 메뉴의 데이터 수정 메소드
	public boolean manage_menu_update() {
	    PreparedStatement ps = null;
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        @SuppressWarnings("unused")
	        Statement stmt_mysql = conn_mysql.createStatement();

		    String A = "update gameMD set store_name = ?, md_name = ?, price = ? ";
		    String B = " where mCode = ? ";
	
	        ps = conn_mysql.prepareStatement(A+B);
	        ps.setString(1, store_name);
	        ps.setString(2, store_menu);
	        ps.setString(3, store_price);
	        ps.setInt(4, store_mcode);
	        ps.executeUpdate();
	
	        conn_mysql.close();
	    } catch (Exception e){
	        e.printStackTrace();
	        return false;
	    }
	    
	    return true;
	}
	
	public boolean manage_menu_Insert() {
		PreparedStatement ps = null;
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        @SuppressWarnings("unused")
	        Statement stmt_mysql = conn_mysql.createStatement();

		    String A = "insert into gameMD (md_name, price, store_name) ";
		    String B = "values(?, ?, ?);";
	
	        ps = conn_mysql.prepareStatement(A+B);
	        ps.setString(1, store_menu);
	        ps.setString(2, store_price);
	        ps.setString(3, store_name);
	        ps.executeUpdate();
	
	        conn_mysql.close();
	    } catch (Exception e){
	        e.printStackTrace();
	        return false;
	    }
		
		return true;
	}
	
	// Store table 생성
	public ArrayList<md_Bean> SelectList(){
		
		ArrayList<md_Bean> BeanList = new ArrayList<md_Bean>();
		
		String WhereDefault = "SELECT mCode, store_name, md_name, price FROM gameMD;";
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

            while(rs.next()){
            	
            	int mCode = rs.getInt(1);
            	String store_Name = rs.getString(2);
            	String md_Name = rs.getString(3);
            	String price = rs.getString(4);
            	
            	md_Bean bean = new md_Bean(mCode, store_Name, md_Name, price);
            	BeanList.add(bean);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return BeanList;
	}
	
	// Store table 클릭
	public md_Bean TableClick() {
		md_Bean bean = null;
		
		String query = "SELECT mCode, store_name, md_name, price FROM gameMD WHERE mCode = " + store_mcode + ";";
		
		 try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(query);            

            if(rs.next()){
            	
            	int mCode = rs.getInt(1);
            	String store_Name = rs.getString(2);
            	String md_Name = rs.getString(3);
            	String price = rs.getString(4);
            	
                bean = new md_Bean(mCode, store_Name, md_Name, price);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		
		return bean;
	}
	
}
