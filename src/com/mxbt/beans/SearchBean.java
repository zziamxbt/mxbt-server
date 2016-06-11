package com.mxbt.beans;

import java.io.Serializable;

public class SearchBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Uid;//主键
    private String Uhead;//头像
    private String Ubk;//背景图片
    private String Uname;//帐号
    private String Unickname;//昵称
    private String Usex;//性别
    private String Ucountry;//国家
    private String Usign;//个性签名
    private String Upassword;//密码
    private int Aid;//文章id
    private String Atitle;//文章标题
    
    public SearchBean(){
    	
    }

	public SearchBean(int uid, String uhead, String ubk, String uname,
			String unickname, String usex, String ucountry, String usign,
			String upassword, int aid, String atitle) {
		super();
		Uid = uid;
		Uhead = uhead;
		Ubk = ubk;
		Uname = uname;
		Unickname = unickname;
		Usex = usex;
		Ucountry = ucountry;
		Usign = usign;
		Upassword = upassword;
		Aid = aid;
		Atitle = atitle;
	}

	public int getUid() {
		return Uid;
	}

	public void setUid(int uid) {
		Uid = uid;
	}

	public String getUhead() {
		return Uhead;
	}

	public void setUhead(String uhead) {
		Uhead = uhead;
	}

	public String getUbk() {
		return Ubk;
	}

	public void setUbk(String ubk) {
		Ubk = ubk;
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

	public String getUsex() {
		return Usex;
	}

	public void setUsex(String usex) {
		Usex = usex;
	}

	public String getUcountry() {
		return Ucountry;
	}

	public void setUcountry(String ucountry) {
		Ucountry = ucountry;
	}

	public String getUsign() {
		return Usign;
	}

	public void setUsign(String usign) {
		Usign = usign;
	}

	public String getUpassword() {
		return Upassword;
	}

	public void setUpassword(String upassword) {
		Upassword = upassword;
	}

	public int getAid() {
		return Aid;
	}

	public void setAid(int aid) {
		Aid = aid;
	}

	public String getAtitle() {
		return Atitle;
	}

	public void setAtitle(String atitle) {
		Atitle = atitle;
	}

	@Override
	public String toString() {
		return "SearchBean [Uid=" + Uid + ", Uhead=" + Uhead + ", Ubk=" + Ubk
				+ ", Uname=" + Uname + ", Unickname=" + Unickname + ", Usex="
				+ Usex + ", Ucountry=" + Ucountry + ", Usign=" + Usign
				+ ", Upassword=" + Upassword + ", Aid=" + Aid + ", Atitle="
				+ Atitle + "]";
	}
    
    

}
