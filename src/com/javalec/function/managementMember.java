package com.javalec.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.setup.db_Share_Var;
import com.javalec.setup.member_Bean;

public class managementMember {

    private final String url_mysql = db_Share_Var.DBNAME;
    private final String id_mysql = db_Share_Var.DBUSER;
    private final String pw_mysql = db_Share_Var.DBPASS;
    
	int member_code_no;			// 관리자가 수정할 회원의 번호
	String member_id;			// 관리자가 수정할 회원의 아이디
	String member_pw;			// 관리자가 수정할 회원의 비밀번호
	String member_name;			// 관리자가 수정할 회원의 이름
	String member_resident;		// 관리자가 수정할 회원의 주민번호
	FileInputStream file;			// 관리자가 수정할 회원의 이미지
	
	public managementMember() {
		
	}	
	
	public managementMember(int member_code_no) {
		super();
		this.member_code_no = member_code_no;
	}

	public managementMember(int member_code_no, String member_id, String member_name, String member_resident) {
		super();
		this.member_code_no = member_code_no;
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_resident = member_resident;
	}

	public managementMember(int member_code_no, String member_id, String member_pw, String member_name,
			String member_resident) {
		super();
		this.member_code_no = member_code_no;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_resident = member_resident;
	}

	public managementMember(int member_code_no, String member_id, String member_pw, String member_name,
			String member_resident, FileInputStream file) {
		super();
		this.member_code_no = member_code_no;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_resident = member_resident;
		this.file = file;
	}

	// 관리자가 사용하는 회원의 데이터 수정 메소드
	public boolean manage_memeber_update() {		
	    PreparedStatement ps = null;
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	        @SuppressWarnings("unused")
	        Statement stmt_mysql = conn_mysql.createStatement();

		    String A = "update tycoon.member set Name = ?, Password = ?, Resident = ?, ProfImage = ? ";
		    String B = " where mCode = ? ";
	
	        ps = conn_mysql.prepareStatement(A+B);
	        ps.setString(1, member_name);
	        ps.setString(2, member_pw);
	        ps.setString(3, member_resident);
	        ps.setBinaryStream(4, file);			// file로 받아서 binaryStream으로 넣는다.
	        ps.setInt(5, member_code_no);
	        ps.executeUpdate();
	
	        conn_mysql.close();
	    } catch (Exception e){
	        e.printStackTrace();
	        return false;
	    }
	    
	    return true;
	}
	
	// memeber table 생성
	public ArrayList<member_Bean> SelectList(){
		
		ArrayList<member_Bean> BeanList = new ArrayList<member_Bean>();
		
		String WhereDefault = "SELECT mCode, ID, Name, Resident FROM tycoon.member;";
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

            while(rs.next()){
            	
            	int mCode = rs.getInt(1);
            	String ID = rs.getString(2);
            	String Name = rs.getString(3);
            	String Resident = rs.getString(4);
            	
            	member_Bean bean = new member_Bean(mCode, ID, Name, Resident);
            	BeanList.add(bean);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return BeanList;
	}
	
	// member table 클릭
	public member_Bean TableClick() {
		member_Bean bean = null;
		
		String query = "SELECT mCode, ID, Name, Password, Resident, ProfImage FROM tycoon.member WHERE mCode = " + member_code_no + ";";
		
		 try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(query);            

            if(rs.next()){
            	
            	int mCode = rs.getInt(1);
            	String ID = rs.getString(2);
            	String Name = rs.getString(3);
            	String Password = rs.getString(4);
            	String Resident = rs.getString(5);
            	// File
            	db_Share_Var.filename = db_Share_Var.filename + 1;
            	File file = new File(Integer.toString(db_Share_Var.filename));
            	FileOutputStream output = new FileOutputStream(file);
            	InputStream input = rs.getBinaryStream(6);
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
            	
                bean = new member_Bean(mCode, ID, Name, Password, Resident);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		
		return bean;
	}	
}
