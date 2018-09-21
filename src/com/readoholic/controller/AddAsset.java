package com.readoholic.controller;

import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Book;
import com.readoholic.model.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AddAsset")
public class AddAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	public AddAsset() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
        conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
        if(hs!=null){ 
	        RequestDispatcher rd = request.getRequestDispatcher("AddAsset.jsp");
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
			String bookName=request.getParameter("bookName");
			String bookGenre=request.getParameter("bookGenre");
			String bookAuthor=request.getParameter("bookAuthor");
			String language=request.getParameter("language");
			String bookDescription=request.getParameter("bookDescription");
			String bookSecurityDeposit=request.getParameter("bookSecurityDeposit");
			int security=Integer.parseInt(bookSecurityDeposit);
			
			Book book=new Book(bookName, bookGenre, bookAuthor, language, user.getUsername(), bookDescription, security, false);
			DBMethod.add_book(conn, book);
			response.sendRedirect("Home");
        }
        else{
        	response.sendRedirect("Login");
        }
	}
}