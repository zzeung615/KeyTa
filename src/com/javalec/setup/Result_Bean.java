package com.javalec.setup;

public class Result_Bean {
	
	int exp;
	int maxScore;
	int memberCode;
	String member_ID;
	
	public Result_Bean(int exp, int maxScore, int memberCode) {
		super();
		this.exp = exp;
		this.maxScore = maxScore;
		this.memberCode = memberCode;
	}	

	public Result_Bean(int exp, int maxScore, String member_ID) {
		super();
		this.exp = exp;
		this.maxScore = maxScore;
		this.member_ID = member_ID;
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

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public String getMember_ID() {
		return member_ID;
	}

	public void setMember_ID(String member_ID) {
		this.member_ID = member_ID;
	}
	
	

}
