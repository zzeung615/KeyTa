package com.javalec.function;


public class login {
	
	int code_no; 		// 로그인하면 가지고올 회원 기본키
	String id;			// 로그인시 받을 id
	String pw;			// 로그인시 받을 password	
	
	public login(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}	

	// id pw 입력받아 회원이 맞으면 로그인 시켜주는 메소	
	public boolean login_check() {
		boolean ret = false;
			Member mb = new Member(id);
			String pass = mb.logIdCheck();
			
			if (pass.equals(pw) && pass.length() != 0) {
				ret = true;
			} else {
				ret = false;
			}
			
		return ret;
	}
	
	// 로그아웃
	public void logout() {
		
	}
}
