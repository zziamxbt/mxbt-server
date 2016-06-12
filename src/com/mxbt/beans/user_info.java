package com.mxbt.beans;

import java.io.Serializable;

public class user_info implements Serializable{
	String Uname;
	String Unickname;
	String Uhead;
	public user_info(){}
	public user_info(String uname, String unickname, String uhead) {
		super();
		Uname = uname;
		Unickname = unickname;
		Uhead = uhead;
	}
	public String getUname() {
		return Uname;
	}
	public void setUname(String uname) {
		Uname = uname;
	}
	public String getUnickname() {
		return Unickname;
	}
	public void setUnickname(String unickname) {
		Unickname = unickname;
	}
	public String getUhead() {
		return Uhead;
	}
	public void setUhead(String uhead) {
		Uhead = uhead;
	}
	
}
