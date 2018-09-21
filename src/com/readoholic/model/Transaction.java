package com.readoholic.model;

import java.time.*;

import com.readoholic.utility.Hashcode;

public class Transaction{
	private String transactionId;
	private String bookId;	
	private String lenderId;
	private String borrowerId;
	private String lendDate;
	private String returnDate;
	private String roomno;
	private String hall;
	private String phoneno;
	
	//READ_DB
	public Transaction(String transactionId, String bookId, String lenderId, String borrowerId, String lendDate,
			String returnDate, String roomno, String hall, String phoneno) {
		super();
		this.transactionId = transactionId;
		this.bookId = bookId;
		this.lenderId = lenderId;
		this.borrowerId = borrowerId;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
		this.roomno = roomno;
		this.hall = hall;
		this.phoneno = phoneno;
	}
	
	//GET_USERIN
	public Transaction(String bookId, String lenderId, String borrowerId, int nDays, String roomno, String hall, String phoneno) {
		this.bookId = bookId;
		this.lenderId = lenderId;
		this.borrowerId = borrowerId;
		
		String[] id = new String[]{bookId, lenderId, borrowerId, Integer.toString(nDays)};
		this.transactionId = Hashcode.generate(id);
		
		Instant instantNow = Instant.now();
		this.lendDate = instantNow.toString();
		
		Instant InstantLater = instantNow.plus(Duration.ofHours(24*nDays));
		this.returnDate = InstantLater.toString();
		
		this.roomno = roomno;
		this.hall = hall;
		this.phoneno = phoneno;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getLenderId() {
		return lenderId;
	}

	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}

	public String getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getLendDate() {
		return lendDate;
	}

	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
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

	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Transaction)) {
	        return false;
	    }
	    Transaction that = (Transaction) other;
	    return (this.transactionId == that.transactionId);
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", bookId=" + bookId + ", lenderId=" + lenderId
				+ ", borrowerId=" + borrowerId + ", lendDate=" + lendDate + ", returnDate=" + returnDate + ", roomno="
				+ roomno + ", hall=" + hall + ", phoneno=" + phoneno + "]";
	}
	
}
