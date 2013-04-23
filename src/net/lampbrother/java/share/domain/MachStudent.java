package net.lampbrother.java.share.domain;

public class MachStudent {
	
	private String name;
	private String groupname;
	private String address;
	
	public MachStudent() {
		super();
	}
	public MachStudent(String name, String groupname, String address) {
		super();
		this.name = name;
		this.groupname = groupname;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
