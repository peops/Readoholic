package com.readoholic.dblayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.readoholic.model.Book;
import com.readoholic.model.Transaction;
import com.readoholic.model.User;

/** 
 * CREATE TABLE users (rollno varchar(10) PRIMARY KEY, username varchar(20) NOT NULL, pass_word varchar(20) NOT NULL, 
   						firstname varchar(50) NOT NULL, lastname varchar(50) NOT NULL, roomno varchar(5) NOT NULL, 
   						hall varchar(3) NOT NULL, email varchar(40) NOT NULL, 
   						phoneno varchar(10) NOT NULL);
 * @author Innovationchef
 * 
 */

/** 
 * CREATE TABLE books (bookid varchar(70) PRIMARY KEY, bookname varchar(70) NOT NULL, 
  						bookgenre varchar(30) NOT NULL, bookauthor varchar(50) NOT NULL, language varchar(70) NOT NULL, 
  						bookowner varchar(50) NOT NULL, bookdescription varchar(500) NOT NULL, 
  						booksecuritydeposit bigint NOT NULL, bookallocationstatus BOOLEAN NOT NULL);
 * @author Innovationchef
 *
 */

/** 
 * CREATE TABLE transactions (transactionid varchar(50) PRIMARY KEY, bookid varchar(50) NOT NULL, lenderid varchar(50) NOT NULL, 
  						borrowerid varchar(50) NOT NULL, lenddate varchar(50) NOT NULL,
  						returndate varchar(50) NOT NULL, phoneno char(10) NOT NULL, roomno varchar(5) NOT NULL, hall varchar(3) NOT NULL);
 * @author Innovationchef
 *
 */
public interface DBMethod {
	static User user_resultset(ResultSet rs) throws SQLException{
		User user = null;
		String usern = rs.getString("username");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String phoneno = rs.getString("phoneno");
        String roomno = rs.getString("roomno");
        String hall = rs.getString("hall");
        String email = rs.getString("email");
        user = new User(usern, firstname, lastname, phoneno, roomno, hall,  email);
        return user;
	}
	static User login(Connection conn, String username, String password) throws SQLException{
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM users WHERE username = ? AND pass_word = ?";
	    User user = null;
	    preparedStatement = conn.prepareStatement(query);
    	preparedStatement.setString(1, username);
    	preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
        	user = user_resultset(rs);
        }
        return user;
	}
	static User read_user(Connection conn, String username) throws SQLException{
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM users WHERE username = ?";
	    User user = null;
	    preparedStatement = conn.prepareStatement(query);
    	preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
        	user = user_resultset(rs);
        }
        return user;
	}
	static void add_user(Connection conn, User user){
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO users(rollno, username, pass_word, firstname, lastname,roomno, hall,  email,  phoneno) VALUES(?,?,?,?,?,?,?,?,?)";
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, user.getRollno());
	    	preparedStatement.setString(2, user.getUsername());
	    	preparedStatement.setString(3, user.getPassword());
	    	preparedStatement.setString(4, user.getFirstName());
	    	preparedStatement.setString(5, user.getLastName());
	    	preparedStatement.setString(6, user.getRoomno());
	    	preparedStatement.setString(7, user.getHall());
	    	preparedStatement.setString(8, user.getEmail());
	    	preparedStatement.setString(9, user.getPhoneno());
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	    }
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
	
	static Book book_resultset(ResultSet rs) throws SQLException{
        String bookId = rs.getString("bookid");
        String bookName = rs.getString("bookname");
        String bookGenre = rs.getString("bookgenre");
        String bookAuthor = rs.getString("bookauthor");
        String language = rs.getString("language");
        String bookOwner = rs.getString("bookowner");
        String bookDescription = rs.getString("bookdescription");
        int bookSecurityDeposit = rs.getInt("booksecuritydeposit");
        boolean bookAllocationStatus = rs.getBoolean("bookallocationstatus");    
        Book book = new Book(bookId, bookName, bookGenre, bookAuthor, language, bookOwner, bookDescription,
        						bookSecurityDeposit, bookAllocationStatus);
        return book;
	}
	static void add_book(Connection conn, Book book){
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO books(bookid, bookname, bookgenre, bookauthor, language, bookowner, bookdescription, booksecuritydeposit, bookallocationstatus) VALUES(?,?,?,?,?,?,?,?,?)";	
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, book.getBookId());
	    	preparedStatement.setString(2, book.getBookName());
	    	preparedStatement.setString(3, book.getBookGenre());
	    	preparedStatement.setString(4, book.getBookAuthor());
	    	preparedStatement.setString(5, book.getLanguage());
	    	preparedStatement.setString(6, book.getBookOwner());
	    	preparedStatement.setString(7, book.getBookDescription());
	    	preparedStatement.setInt(8, book.getBookSecurityDeposit());
	    	preparedStatement.setBoolean(9, book.isBookAllocationStatus());
	        preparedStatement.executeUpdate();
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
	static List<Book> read_books(Connection conn){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM books WHERE bookallocationstatus = FALSE";
	    List<Book> books = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		Book book = book_resultset(rs);
	    		books.add(book);
	    	}
	        return books;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return books;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return books;
	        }   
	    }
	}
	static Book read_book(Connection conn, String id){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM books where bookid = ?";
	    Book book = null;
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, id);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		book = book_resultset(rs);
	    	}
	        return book;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return book;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return book;
	        }   
	    }
	}
	static List<Book> read_user_books(Connection conn, String username){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM books WHERE bookowner = ?";
	    
	    List<Book> books = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, username);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next()){
	    		Book book = book_resultset(rs);
	    		books.add(book);
	    	}
	        return books;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return books;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return books;
	        }   
	    }
	}	
	@SuppressWarnings("unused")
	static void delete_book(Connection conn, String bookId){
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM books WHERE bookid = ?";
		try{
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, bookId);
			int temp = preparedStatement.executeUpdate();
		}
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	@SuppressWarnings("unused")
	static void edit_book_status(Connection conn, String bookId, boolean allocation_flag){
		PreparedStatement preparedStatement = null;
		String query = "UPDATE books SET bookallocationstatus = ? WHERE bookid = ? ";
		try{
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setBoolean(1, allocation_flag);
			preparedStatement.setString(2, bookId); 
			int temp = preparedStatement.executeUpdate();
		}
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	static Transaction txn_resultset(ResultSet rs) throws SQLException{
		String transactionId = rs.getString("transactionid");
		String bookId = rs.getString("bookid");
        String lenderId = rs.getString("lenderid");
        String borrowerId = rs.getString("borrowerid");
        String lendDate = rs.getString("lenddate");
        String returnDate = rs.getString("returndate");
        String phoneno = rs.getString("phoneno");
        String roomno = rs.getString("roomno");
        String hall = rs.getString("hall");
        Transaction transaction = new Transaction(transactionId, bookId, lenderId, borrowerId, lendDate, returnDate, phoneno, roomno, hall);
        return transaction;
	}
	static List<Transaction> read_transactions(Connection conn){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM transactions";
	    List<Transaction> transactions = new ArrayList<>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	Transaction transaction = txn_resultset(rs);
	            transactions.add(transaction);
	        }
	        return transactions;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return transactions;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return transactions;
	        }
	    }
	}
	static Map<Book, String> read_borrowed_assets(Connection conn, String username){
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM transactions WHERE borrowerid = ?";
	    Map <Book, String> hm = new HashMap<Book, String>();
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, username);
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	Transaction transaction = txn_resultset(rs);
	            String bookId = transaction.getBookId();
	            Book book = read_book(conn, bookId);
	            hm.put(book, transaction.getTransactionId());
	        }
	        return hm;
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	        return hm;
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return hm;
	        }
	    }
	}
	@SuppressWarnings("unused")
	static void delete_transaction(Connection conn, String txnId){
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM transactions WHERE transactionId = ?";
		try{
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, txnId);
			int temp = preparedStatement.executeUpdate();
		}
		catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	static void add_transaction(Connection conn, Transaction transaction){
		PreparedStatement preparedStatement = null;
	    String query = "INSERT INTO transactions(transactionid , bookid, lenderid , borrowerid ,  lenddate , returndate, phoneno, roomno, hall) VALUES(?,?,?,?,?,?,?,?,?)";
	    try{
	    	preparedStatement = conn.prepareStatement(query);
	    	preparedStatement.setString(1, transaction.getTransactionId());
	    	preparedStatement.setString(2, transaction.getBookId());
	    	preparedStatement.setString(3, transaction.getLenderId());
	    	preparedStatement.setString(4, transaction.getBorrowerId());
	    	preparedStatement.setString(5, transaction.getLendDate());
	    	preparedStatement.setString(6, transaction.getReturnDate());
	    	preparedStatement.setString(7, transaction.getPhoneno());
	    	preparedStatement.setString(8, transaction.getRoomno());
	    	preparedStatement.setString(9, transaction.getHall());
	        preparedStatement.executeUpdate();
	    } 
	    catch(SQLException e ){
	        System.out.println(e.getMessage());
	    } 
	    finally{
	        if(preparedStatement != null){
	        	try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
}
