package com.readoholic.model;

import com.readoholic.utility.Hashcode;

public class Book {
	private String bookId;
	private String bookName;
	private String bookGenre;
	private String bookAuthor;
	private String language;
	private String bookOwner;
	private String bookDescription;
	private int bookSecurityDeposit;
	private boolean bookAllocationStatus=false;
	
	public Book(String bookName, String bookGenre, String bookAuthor, String language, String bookOwner,
			String bookDescription, int bookSecurityDeposit, boolean bookAllocationStatus) {
		super();
		String[] id = new String[]{bookName, bookGenre, bookAuthor, bookOwner};
		this.bookId = Hashcode.generate(id);
		this.bookName = bookName;
		this.bookGenre = bookGenre;
		this.bookAuthor = bookAuthor;
		this.language = language;
		this.bookOwner = bookOwner;
		this.bookDescription = bookDescription;
		this.bookSecurityDeposit = bookSecurityDeposit;
		this.bookAllocationStatus = bookAllocationStatus;
	}
	public Book(String bookId, String bookName, String bookGenre, String bookAuthor, String language, String bookOwner,
			String bookDescription, int bookSecurityDeposit, boolean bookAllocationStatus) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookGenre = bookGenre;
		this.bookAuthor = bookAuthor;
		this.language = language;
		this.bookOwner = bookOwner;
		this.bookDescription = bookDescription;
		this.bookSecurityDeposit = bookSecurityDeposit;
		this.bookAllocationStatus = bookAllocationStatus;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookGenre() {
		return bookGenre;
	}
	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBookOwner() {
		return bookOwner;
	}
	public void setBookOwner(String bookOwner) {
		this.bookOwner = bookOwner;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	public int getBookSecurityDeposit() {
		return bookSecurityDeposit;
	}
	public void setBookSecurityDeposit(int bookSecurityDeposit) {
		this.bookSecurityDeposit = bookSecurityDeposit;
	}
	public boolean isBookAllocationStatus() {
		return bookAllocationStatus;
	}
	public void setBookAllocationStatus(boolean bookAllocationStatus) {
		this.bookAllocationStatus = bookAllocationStatus;
	}
	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Book)) {
	        return false;
	    }
	    Book that = (Book) other;
	    return (this.bookId == that.bookId) && (this.bookOwner == that.bookOwner);
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookGenre=" + bookGenre + ", bookAuthor="
				+ bookAuthor + ", language=" + language + ", bookOwner=" + bookOwner + ", bookDescription="
				+ bookDescription + ", bookSecurityDeposit=" + bookSecurityDeposit + ", bookAllocationStatus=" + bookAllocationStatus + "]";
	}	
}
