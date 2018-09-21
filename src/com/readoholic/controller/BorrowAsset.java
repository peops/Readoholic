package com.readoholic.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Book;
import com.readoholic.model.Transaction;
import com.readoholic.model.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BorrowAsset")
public class BorrowAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public BorrowAsset() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
        conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
		if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			List<Book> books = DBMethod.read_books(conn);
			List<Book> filtered_books = new ArrayList<>();
			for(Book a: books){
				if(a.getBookOwner().equals(user.getUsername())==false){
					filtered_books.add(a);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("BorrowAsset.jsp");
			request.setAttribute("booklist", filtered_books);
			rd.forward(request, response);
        }
        else{
        	response.sendRedirect("Login");
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
        if(hs!=null){ 
        	User user=(User)hs.getAttribute("login_user");
			String bookId=request.getParameter("lend");
			Book book = DBMethod.read_book(conn, bookId);
			DBMethod.edit_book_status(conn, bookId, true);
			
			int ndays = 20;
			String roomno = "C210";
			String hall= "RP";
			String phoneno = "1234567890";
			Transaction transaction = new Transaction(bookId, book.getBookOwner(), user.getUsername(), ndays, roomno, hall, phoneno);
			DBMethod.add_transaction(conn, transaction);
			response.sendRedirect("Home");
        }
        else{
        	response.sendRedirect("Login");
        }
	}
}