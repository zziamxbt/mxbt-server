package com.mxbt.beans;

public class SignBean {
	private boolean SignFlag;
	
	public SignBean(){}

	public SignBean(boolean signFlag) {
		super();
		SignFlag = signFlag;
	}

	public boolean isSignFlag() {
		return SignFlag;
	}

	public void setSignFlag(boolean signFlag) {
		SignFlag = signFlag;
	}
	

}
