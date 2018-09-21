package com.readoholic.model;

public class User 
{
	private String rollno;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String roomno;
	private String hall;
	private String phoneno;
	private String email;

	//READ_DB
	public User(String username, String firstName, String lastName, String roomno, String hall, String phoneno,
			String email) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomno = roomno;
		this.hall = hall;
		this.phoneno = phoneno;
		this.email = email;
	}
	//GET_USERIN
	public User(String rollno, String username, String password, String firstName, String lastName, String roomno,
			String hall, String phoneno, String email) {
		super();
		this.rollno = rollno;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomno = roomno;
		this.hall = hall;
		this.phoneno = phoneno;
		this.email = email;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
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
	    return (this.rollno == that.rollno);
	}
	@Override
	public String toString() {
		return "User [rollno=" + rollno + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", roomno=" + roomno + ", hall=" + hall + ", phoneno="
				+ phoneno + ", email=" + email + "]";
	}
}
