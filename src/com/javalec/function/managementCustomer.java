package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.setup.customer_Bean;
import com.javalec.setup.db_Share_Var;

public class managementCustomer {

    private final String url_mysql = db_Share_Var.DBNAME;
    private final String id_mysql = db_Share_Var.DBUSER;
    private final String pw_mysql = db_Share_Var.DBPASS;
	
    int custoemr_cCode;
	String customer_name;		// 관리자가 수정할 손님 이름
	String customer_timer;		// 관리자가 수정할 손님의 대기시간	
	
	public managementCustomer() {
		super();
	}

	public managementCustomer(int custoemr_cCode) {
		super();
		this.custoemr_cCode = custoemr_cCode;
	}

	public managementCustomer(String customer_name, String customer_timer) {
		super();
		this.customer_name = customer_name;
		this.customer_timer = customer_timer;
	}
	
	public managementCustomer(int custoemr_cCode, String customer_name, String customer_timer) {
		super();
		this.custoemr_cCode = custoemr_cCode;
		this.customer_name = customer_name;
		this.customer_timer = customer_timer;
	}

	// 관리자가 사용할 손님의 데이터 수정 메소드
	public boolean manage_customer_update() {
		PreparedStatement ps = null;
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        @SuppressWarnings("unused")
	        Statement stmt_mysql = conn_mysql.createStatement();

		    String A = "update gameCust set Name = ?, waitTime = ? ";
		    String B = " where cCode = ? ";
	
	        ps = conn_mysql.prepareStatement(A+B);
	        ps.setString(1, customer_name);
	        ps.setString(2, customer_timer);
	        ps.setInt(3, custoemr_cCode);
	        ps.executeUpdate();
	
	        conn_mysql.close();
	    } catch (Exception e){
	        e.printStackTrace();
	        return false;
	    }
	    
	    return true;
	}	
	
	public boolean manage_customer_Insert() {
		PreparedStatement ps = null;
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        @SuppressWarnings("unused")
	        Statement stmt_mysql = conn_mysql.createStatement();

		    String A = "insert into gameCust( Name, waitTime) ";
		    String B = "values(?, ?);";
	
	        ps = conn_mysql.prepareStatement(A+B);
	        ps.setString(1, customer_name);
	        ps.setString(2, customer_timer);
	        ps.executeUpdate();
	
	        conn_mysql.close();
	    } catch (Exception e){
	        e.printStackTrace();
	        return false;
	    }
		
		return true;
	}
	
	// customer table 생성
	public ArrayList<customer_Bean> SelectList(){
		
		ArrayList<customer_Bean> BeanList = new ArrayList<customer_Bean>();
		
		String WhereDefault = "SELECT cCode, Name, waitTime FROM gameCust;";
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

            while(rs.next()){
            	
            	int cCode = rs.getInt(1);
            	String name = rs.getString(2);
            	String waitTime = rs.getString(3);
            	
            	customer_Bean bean = new customer_Bean(cCode, name, waitTime);
            	BeanList.add(bean);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return BeanList;
	}
	
	// customer table 클릭
	public customer_Bean TableClick() {
		customer_Bean bean = null;
		
		String query = "SELECT cCode, Name, waitTime FROM gameCust WHERE cCode = " + custoemr_cCode + ";";
		
		 try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(query);            

            if(rs.next()){
            	
            	int cCode = rs.getInt(1);
            	String name = rs.getString(2);
            	String waitTime = rs.getString(3);
            	
                bean = new customer_Bean(cCode, name, waitTime);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		
		return bean;
	}	
}
