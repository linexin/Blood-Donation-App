package com.linex.google.blooddonnerapp.Model;

public class Users {
	public String name;
	public  String phoneNo;
	public String bloodGroup;
	public String address;
	
	public Users() {
	}
	
	public Users(String name, String phoneNo, String bloodGroup, String address) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.bloodGroup = bloodGroup;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}