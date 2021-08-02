package com.csv.uploader.csvhelper;

public class AlertMessage {

	private String content;
	private String type;
	
	public AlertMessage(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}

	public AlertMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
