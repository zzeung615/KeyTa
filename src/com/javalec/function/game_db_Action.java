package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javalec.setup.GameMenu_Bean;
import com.javalec.setup.Result_Bean;
import com.javalec.setup.customer_Bean;
import com.javalec.setup.db_Share_Var;

public class game_db_Action {
	
	private final String url_mysql =  db_Share_Var.DBNAME;
	private final String id_mysql = db_Share_Var.DBUSER;
	private final String pw_mysql = db_Share_Var.DBPASS;
	private int exp;
	private int maxScore;
	
	static Connection conn = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	
	
	public game_db_Action() {
		// TODO Auto-generated constructor stub
	}
	
	//	해당 가게의 메뉴, 메뉴가격, 가게코드 불러오기
	public ArrayList<GameMenu_Bean> selectMenu(String store_name) {
		ArrayList<GameMenu_Bean> BeanList = new ArrayList<GameMenu_Bean>();
		
		//	카페의 메뉴와 해당 가게의 코드 같이 가져옴
		String sql = "SELECT md_name, mCode, price FROM gameMD WHERE store_name = '" + store_name + "';";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String wkMdName = rs.getString(1);
				String wkStoreCode = rs.getString(2);
				int wkMdPrice = rs.getInt(3);
				GameMenu_Bean bean = new GameMenu_Bean(wkMdName, wkStoreCode, wkMdPrice);
				BeanList.add(bean);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return BeanList;
	}
	
	//	손님 , 기다리는시간 불러오기
	public ArrayList<customer_Bean> selectCustomer() {
		ArrayList<customer_Bean> BeanList = new ArrayList<customer_Bean>();
		
		String sql = "SELECT Name, waitTime \n" + 
					 "FROM gameCust";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String CustomerName = rs.getString(1);
				int CustWaitTime = rs.getInt(2);
				customer_Bean bean = new customer_Bean(CustomerName, Integer.toString(CustWaitTime));
				BeanList.add(bean);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return BeanList;
	}
	public void updatePlaygameTable(Result_Bean bean_result) {
		String member_id = bean_result.getMember_ID();
		String sql = "update playGame \n" + 
					 "set exp=? , maxScore=? \n" + 
					 "where member_ID = '" + member_id + "';";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, exp + bean_result.getExp());
			ps.setInt(2, maxScore + bean_result.getMaxScore());
			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void selectPlaygameTable(String id) {
		String sql = "SELECT exp, maxScore \n" + 
					 "FROM playGame \n" + 
					 "WHERE member_ID = '" + id + "';";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				exp = rs.getInt(1);
				maxScore = rs.getInt(2);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
