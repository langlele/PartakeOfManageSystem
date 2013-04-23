package net.lampbrother.java.share.domain;

import java.sql.Date;

/*
 CREATE TABLE share_record (
	`id` INT (11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	NAME VARCHAR (30),
	 preName VARCHAR (30,
	nextName VARCHAR (30),
	shareTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	remember INT (1) DEFAULT 0 ##没有记住
);
 */
public class ShareRecord {

	private Integer id;
	private String name;
	private String preName;
	private String nextName;
	private Date shareTime;
	private int remember;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
	public String getNextName() {
		return nextName;
	}
	public void setNextName(String nextName) {
		this.nextName = nextName;
	}
	public Date getShareTime() {
		return shareTime;
	}
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}
	public int getRemember() {
		return remember;
	}
	public void setRemember(int remember) {
		this.remember = remember;
	}
	@Override
	public String toString() {
		return "SelectedRecord [id=" + id + ", name=" + name + ", preName="
				+ preName + ", nextName=" + nextName + ", shareTime="
				+ shareTime + ", remember=" + remember + "]";
	}
}
