package com.mxbt.beans;

import java.io.Serializable;

import com.mxbt.servlet.Article_complete;

public class IndexBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int articleId;
	String headImg;
	String nickName;
	String dateTime;
	String kind;
	String backGround;
	String title;
	String content;
	int collectNum;// 收藏数量
	int recomendNum;// 推荐数量
	int commentNum;// 评论数量
	

	public IndexBean(){
	}

	public IndexBean(int articleId, String headImg, String nickName,
			String dateTime, String kind, String backGround, String title,
			String content, int collectNum, int recomendNum, int commentNum) {
		super();
		this.articleId = articleId;
		this.headImg = headImg;
		this.nickName = nickName;
		this.dateTime = dateTime;
		this.kind = kind;
		this.backGround = backGround;
		this.title = title;
		this.content = content;
		this.collectNum = collectNum;
		this.recomendNum = recomendNum;
		this.commentNum = commentNum;
	}


	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getBackGround() {
		return backGround;
	}

	public void setBackGround(String backGround) {
		this.backGround = backGround;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getRecomendNum() {
		return recomendNum;
	}

	public void setRecomendNum(int recomendNum) {
		this.recomendNum = recomendNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public String toString() {
		return "IndexBean [articleId=" + articleId + ", headImg=" + headImg
				+ ", nickName=" + nickName + ", dateTime=" + dateTime
				+ ", kind=" + kind + ", backGround=" + backGround + ", title="
				+ title + ", content=" + content + ", collectNum=" + collectNum
				+ ", recomendNum=" + recomendNum + ", commentNum=" + commentNum
				+ "]";
	}
	
	
}
