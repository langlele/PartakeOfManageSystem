package net.lampbrother.java.share.domain;

/*
 *CREATE TABLE `student` (
 `id` INT (11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
 `NAME` VARCHAR (30),
 `phone` VARCHAR (45),
 `address` VARCHAR (90),
 `email` VARCHAR (90),
 `groupname` VARCHAR (45),
 `qq` VARCHAR (30),
 state INT (1) DEFAULT 0  ## 0:未中奖过 1:中奖了但还没有分享, 2: 分享了
 ); 
 */
public class Student {

	private Integer id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String groupname;
	private String qq;
	private int state;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
