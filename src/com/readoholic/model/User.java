package com.readoholic.model;

public class User 
{
	private String userName;
	private String password;
	private String name;
	private String address;
	private String phoneNo;
	private String email;
	
	public User(String userName, String name, String address, String phoneNo, String email) {
		this.userName = userName;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	public User(String userName, String password, String name, String address, String phoneNo, String email) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof User)) {
	        return false;
	    }
	    User that = (User) other;
	    return (this.email == that.email) && (this.phoneNo == that.phoneNo);
	}
	
	@Override
	public String toString() { 
	    return	"Username: " + this.userName + 
	    		"\nName: " + this.name + 
	    		"\nAddress: " + this.address + 
	    		"\nPhone no.: " + this.phoneNo + 
	    		"\nEmail: " + this.email;
	}
}
