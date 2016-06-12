package com.mxbt.beans;

public class AndWriteBean {
	 public int getAWid() {
		return AWid;
	}
	public void setAWid(int aWid) {
		AWid = aWid;
	}
	public int getAWcid() {
		return AWcid;
	}
	public void setAWcid(int aWcid) {
		AWcid = aWcid;
	}
	public int getAWtid() {
		return AWtid;
	}
	public void setAWtid(int aWtid) {
		AWtid = aWtid;
	}
	public int getAWauthor() {
		return AWauthor;
	}
	public void setAWauthor(int aWauthor) {
		AWauthor = aWauthor;
	}
	public String getAWtitle() {
		return AWtitle;
	}
	public void setAWtitle(String aWtitle) {
		AWtitle = aWtitle;
	}
	public int getAWvote() {
		return AWvote;
	}
	public void setAWvote(int aWvote) {
		AWvote = aWvote;
	}
	public String getAWcreatetime() {
		return AWcreatetime;
	}
	public void setAWcreatetime(String aWcreatetime) {
		AWcreatetime = aWcreatetime;
	}
	public String getAWisfinish() {
		return AWisfinish;
	}
	public void setAWisfinish(String aWisfinish) {
		AWisfinish = aWisfinish;
	}
	
	
	
	
	public AndWriteBean(int aWid, int aWcid, int aWtid, int aWauthor,
			String aWtitle, int aWvote, String aWcreatetime, String aWisfinish) {
		super();
		AWid = aWid;
		AWcid = aWcid;
		AWtid = aWtid;
		AWauthor = aWauthor;
		AWtitle = aWtitle;
		AWvote = aWvote;
		AWcreatetime = aWcreatetime;
		AWisfinish = aWisfinish;
	}




	@Override
	public String toString() {
		return "AndWriteBean [AWid=" + AWid + ", AWcid=" + AWcid + ", AWtid="
				+ AWtid + ", AWauthor=" + AWauthor + ", AWtitle=" + AWtitle
				+ ", AWvote=" + AWvote + ", AWcreatetime=" + AWcreatetime
				+ ", AWisfinish=" + AWisfinish + "]";
	}




	int AWid;
	 int AWcid;
	 int AWtid;
	 int AWauthor;
	 String AWtitle;
	 int AWvote;
	 String AWcreatetime;
	 String AWisfinish;
	 
}
