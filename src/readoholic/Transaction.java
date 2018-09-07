package readoholic;

import java.time.*;

public class Transaction{
	private String transactionId;
	private String assetId;	
	private String lenderId;
	private String borrowerId;
	private String lendDate;
	private String returnDate;
	
	public Transaction(String transactionId, String lenderId, String borrowerId, String assetId, String lendDate, String returnDate) {
		this.transactionId = transactionId;
		this.lenderId = lenderId;
		this.borrowerId = borrowerId;
		this.assetId = assetId;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
	}
	public Transaction(String assetId, String lenderId, String borrowerId, int nDays) {
		this.lenderId = lenderId;
		this.borrowerId = borrowerId;
		this.assetId = assetId;
		String[] id = new String[]{assetId, lenderId, borrowerId, Integer.toString(nDays)};
		this.transactionId = Hashcode.generate(id);
		Instant instantNow = Instant.now();
		this.lendDate = instantNow.toString();
		Instant InstantLater = instantNow.plus(Duration.ofHours(24*nDays));
		this.returnDate = InstantLater.toString();
	}
	
	public String gettransactionId() {
		return transactionId;
	}
	public void settransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
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
	
	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Transaction)) {
	        return false;
	    }
	    Transaction that = (Transaction) other;
	    return (this.transactionId == that.transactionId) && (this.transactionId == that.transactionId);
	}

	@Override
	public String toString() {
		return "Transaction:" + 
				"\nInvoice Id = " + transactionId +
				"\nAsset Id = " + assetId +
				"\nLender Id = " + lenderId +
				"\nBorrower Id = " + borrowerId +
				"\nLend Date = " + getLendDate() + 
				"\nReturn Date = " + getReturnDate();
	}
}
