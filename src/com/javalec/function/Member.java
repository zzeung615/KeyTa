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

import javax.swing.JOptionPane;

import com.javalec.setup.SH_Bean;
import com.javalec.setup.db_Share_Var;

public class Member {
	
	db_Share_Var ShareVar = new db_Share_Var();
	private final String url_mysql = ShareVar.DBNAME;
	private final String id_mysql = ShareVar.DBUSER;
	private final String pw_mysql = ShareVar.DBPASS;

	
	int code_no;		// 자기자신의 번호
	String id;			//         id
	String pw;			//         pw
	String name;		// 		   이름
	String resident;	//         주민번호
	ArrayList<String> bean = new ArrayList<String>();	
	
	public Member(ArrayList<String> bean) {
		super();
		this.bean = bean;
	}


	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	
	// 회원의 id, pw 찾는 메소드
	public Member(String id, String name, String resident) {
		super();
		this.id = id;
		this.name = name;
		this.resident = resident;
		
	}
	
	
	public Member(String id) {
		super();
		this.id = id;
	}



	public ArrayList<SH_Bean> memeber_search_id() {
		
		ArrayList<SH_Bean> BeanList = new ArrayList<SH_Bean>();
		
		
		String A =  "select ID, Name, Password, Resident from tycoon.member ";
		String B = "where Name = ? and Resident = ? ";
		
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, name);
			ps.setString(2, resident);
			
			
			ResultSet rs = ps.executeQuery();//
			
			if(rs.next()) { //  rs.next() - 가져올거 있냐? 답 : True, False 로 나옴.. 읽어 올 데이터가 있다 = True = while문이 돈다. //한 줄씩 온다... 
				String mId = rs.getString(1);
				String mName = rs.getString(2);
				String mPassword = rs.getString(3);
				String mResident = rs.getString(4);
				
				SH_Bean bean = new SH_Bean(mId, mName, mPassword, mResident);
				BeanList.add(bean);
			} else {
				SH_Bean bean = new SH_Bean("", "", "", "");
				BeanList.add(bean);
			}
			conn_mysql.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return BeanList;
		
	}
	public ArrayList<SH_Bean> memeber_search_pw() {
		
		ArrayList<SH_Bean> BeanList = new ArrayList<SH_Bean>();
		
		
		String A =  "select ID, Name, Password, Resident from tycoon.member ";
		String B = "where Name = ? and Resident = ? and ID = ?";
		
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, name);
			ps.setString(2, resident);
			ps.setString(3, id);
			
			
			ResultSet rs = ps.executeQuery();//
			
			if(rs.next()) { //  rs.next() - 가져올거 있냐? 답 : True, False 로 나옴.. 읽어 올 데이터가 있다 = True = while문이 돈다. //한 줄씩 온다... 
				String mId = rs.getString(1);
				String mName = rs.getString(2);
				String mPassword = rs.getString(3);
				String mResident = rs.getString(4);
								
				SH_Bean bean = new SH_Bean(mId, mName, mPassword, mResident);
				BeanList.add(bean);
			} else {
				SH_Bean bean = new SH_Bean("", "", "", "");
				BeanList.add(bean);
			}
			conn_mysql.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return BeanList;
		
	}
	
	
	public int idCheck() {
		int result = 0;
		
		ResultSet rs;
		PreparedStatement ps = null; 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			String A = "select count(ID) as ID from tycoon.member ";
			String B = "where ID = ? ";  
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("ID");
				result = cnt;
			} else {
				result = 0;
			}
			
		conn_mysql.close();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "오류가 발생했습니다.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 회원가입  
	public void member_signUp() {
		
		PreparedStatement ps = null; // Java는 SQL명령어를 모른다...Prepare웅앵은 SQL을 JAVA가 알도록...
			
		try {
				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();  //전부 Connection을 달고 온다.
				
			String A = "insert into tycoon.member(ID, Name, Password, Resident, ProfImage) "; // insert into 지우고 쓰고싶은거 쓰면 다된다....
			String B = "values(?, ?, ?, ?, ?)";  //name 이라는 칼럼에 데이터를 하나 넣겟다.
			
			ps = conn_mysql.prepareStatement(A+B); //A,B 두 개 문장 합쳐서 자바가 잘 알도록 
								
			for(int i = 0; i < bean.size()-1; i++) {
				ps.setString(i+1, bean.get(i));
			}
				
				
			if (bean.get(4) == null) {
				File file = new File("images");
				FileInputStream inputfile = new FileInputStream(file.getAbsolutePath() + "/Default_member.jpeg");			// 기본 이미지 파일
				ps.setBinaryStream(5, inputfile);
			} else {
				FileInputStream fin = new FileInputStream(bean.get(4));
				ps.setBinaryStream(5, fin, fin.available());
			}
				
			ps.executeUpdate(); // 업데이트 해줘야된다.
					
			A = "insert into tycoon.playGame(exp, maxScore, member_ID) "; // insert into 지우고 쓰고싶은거 쓰면 다된다....
			B = "values(?, ?, ?)";  //name 이라는 칼럼에 데이터를 하나 넣겟다.
				
			ps = conn_mysql.prepareStatement(A+B); //A,B 두 개 문장 합쳐서 자바가 잘 알도록 
				
			ps.setInt(1, 0);
			ps.setInt(2, 0);
			ps.setString(3, bean.get(0));
				
			ps.executeUpdate(); // 업데이트 해줘야된다.
				
			conn_mysql.close(); // 데이터베이스는 항상 접근 가능한 명수가 정해져 있으므로,업데이트 하고 나면 꼭 클로즈 해주어야함.
				
			JOptionPane.showMessageDialog(null, "회원 가입이 완료되었습니다.");
				
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "오류가 발생했습니다.");
			e.printStackTrace();
				
		}
	}
		
		
	// 회원의 정보 수정
	public void member_update() {
		PreparedStatement ps = null; // Java는 SQL명령어를 모른다...Prepare웅앵은 SQL을 JAVA가 알도록...
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();  //전부 Connection을 달고 온다.
			
			String A = "update tycoon.member set Name = ?, Password = ?, Resident = ?"; //
			String B = " where ID = ?" ; //
			
			ps = conn_mysql.prepareStatement(A + B); //A,B 두 개 문장 합쳐서 자바가 잘 알도록 
			ps.setString(1, bean.get(1)); // 물음표 1개에 첫번째, 만약에 2개면 이 문장 두줄, 만약에 3개면 이 문장 3줄...
			ps.setString(2, bean.get(2));
			ps.setString(3, bean.get(3));
			ps.setString(4, bean.get(0));
			
			ps.executeUpdate(); // 업데이트 해줘야된다.
			
			conn_mysql.close(); // 데이터베이스는 항상 접근 가능한 명수가 정해져 있으므로,업데이트 하고 나면 꼭 클로즈 해주어야함.
			
			JOptionPane.showMessageDialog(null, bean.get(1) + "님의 정보가 수정되었습니다.");
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "오류가 발생했습니다.");
			e.printStackTrace();
	
		}		
	}
	
	// 상단패널 메인화면에서 사용할 회원 정보
	public ArrayList<SH_Bean> memeber_info() {
		ArrayList<SH_Bean> BeanList = new ArrayList<SH_Bean>();
		
		String A = "SELECT m.ID, m.Name, p.exp, p.maxScore, m.profImage FROM tycoon.member m LEFT JOIN tycoon.playGame p On m.ID = p.member_ID ";
		String B = "where m.ID = ?";
		
		String mId = "";
		String mName = "";
		int pExp;
		int pmaxScore;
		
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();//
			
			if(rs.next()) { //  rs.next() - 가져올거 있냐? 답 : True, False 로 나옴.. 읽어 올 데이터가 있다 = True = while문이 돈다. //한 줄씩 온다... 
				mId = rs.getString(1);
				mName = rs.getString(2);
				pExp = rs.getInt(3);
				pmaxScore = rs.getInt(4);
				
				SH_Bean bean = new SH_Bean(mId, mName, pExp, pmaxScore);
				BeanList.add(bean);
				
				db_Share_Var.filename = db_Share_Var.filename + 1;
				File file = new File(Integer.toString(db_Share_Var.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(5);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0) {
					output.write(buffer);
				}				
			} else {
				SH_Bean bean = new SH_Bean("", "", 0, 0);
				BeanList.add(bean);
			}
			conn_mysql.close();			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return BeanList;		
	}
	
	// 메뉴바에서 회원정보를 수정할때 사용하는 메소드
	public ArrayList<SH_Bean> updateMemberInfo() {
		ArrayList<SH_Bean> BeanList = new ArrayList<SH_Bean>();
		
		String A = "SELECT ID, Password, Name, Resident FROM tycoon.member ";
		String B = "where ID = ? ;";
				
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();//
			
			if(rs.next()) { //  rs.next() - 가져올거 있냐? 답 : True, False 로 나옴.. 읽어 올 데이터가 있다 = True = while문이 돈다. //한 줄씩 온다... 
				String Id = rs.getString(1);
				String Password = rs.getString(2);
				String name = rs.getString(3);
				String Resident = rs.getString(4);
				
				SH_Bean bean = new SH_Bean(Id, name, Password, Resident);
				BeanList.add(bean);				
			} else {
				SH_Bean bean = new SH_Bean("", "", 0, 0);
				BeanList.add(bean);
			}
			conn_mysql.close();			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return BeanList;		
	}
	
	//로그인시 ID 확인 용
	public String logIdCheck() {
		String pass = "";
		
		ResultSet rs;
		PreparedStatement ps = null; 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			String A = "select Password from tycoon.member ";
			String B = "where ID = ? ";  
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				pass = rs.getString("Password");
			}
			
		conn_mysql.close();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "오류가 발생했습니다.");
			e.printStackTrace();
		}

		return pass;
	}
	
	public ArrayList<SH_Bean> getExpRank() {
		ArrayList<SH_Bean> BeanList = new ArrayList<SH_Bean>();
		
		String A = "SELECT member_ID, exp, maxScore from tycoon.playGame order by exp Desc";
		
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(A); 
			
			for (int i = 0; i < 5; i++) {		
				if (rs.next()) {  //  rs.next() - 가져올거 있냐? 답 : True, False 로 나옴.. 읽어 올 데이터가 있다 = True = while문이 돈다. //한 줄씩 온다... 
				String mId = rs.getString(1);
				int pExp = rs.getInt(2);
				int pmaxScore = rs.getInt(3);
					
				SH_Bean bean = new SH_Bean(mId, pExp, pmaxScore);
				BeanList.add(bean);
					}
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return BeanList;
	
	}
	
	
	public ArrayList<SH_Bean> getMaxRank() {
		ArrayList<SH_Bean> BeanList = new ArrayList<SH_Bean>();
		
		String A = "SELECT member_ID, exp, maxScore from tycoon.playGame order by maxScore Desc";
		
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(A); 
			
			for (int i = 0; i < 5; i++) {		
					if(rs.next()) { //  rs.next() - 가져올거 있냐? 답 : True, False 로 나옴.. 읽어 올 데이터가 있다 = True = while문이 돈다. //한 줄씩 온다... 
					String mId = rs.getString(1);
					int pExp = rs.getInt(2);
					int pmaxScore = rs.getInt(3);
					
					SH_Bean bean = new SH_Bean(mId, pExp, pmaxScore);
					BeanList.add(bean);
					} 
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return BeanList;
	}
}
