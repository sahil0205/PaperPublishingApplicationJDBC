package com.cg.ppa.entity;

import java.sql.Date;

public class Paper {
	private int paperId;
	private Date publishDate;
	private int editorId;
	private int price;

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public int getEditorId() {
		return editorId;
	}

	public void setEditorId(int editorId) {
		this.editorId = editorId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Paper(int paperId, Date publishDate, int editorId, int price) {
		super();
		this.paperId = paperId;
		this.publishDate = publishDate;
		this.editorId = editorId;
		this.price = price;
	}

	public Paper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", publishDate=" + publishDate + ", editorId=" + editorId + ", price="
				+ price + "]";
	}
}
