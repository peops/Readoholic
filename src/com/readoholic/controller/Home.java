package com.readoholic.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Book;
import com.readoholic.model.Quote;
import com.readoholic.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public Home() {
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
			Quote quote = DBMethod.read_quote(conn);
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			request.setAttribute("quote", quote);
			request.setAttribute("booklist", filtered_books);
			rd.forward(request, response);
        }
        else{
        	response.sendRedirect("Login");
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
