package com.readoholic.controller;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Book;
import com.readoholic.model.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ViewBorrowedAsset")
public class ViewBorrowedAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public ViewBorrowedAsset() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
        conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
		if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			Map <Book, String> books = DBMethod.read_borrowed_assets(conn, user.getUsername());
			RequestDispatcher rd = request.getRequestDispatcher("ViewBorrowedAsset.jsp");
			request.setAttribute("booklist",books);
			rd.forward(request, response);
        }
        else{
        	response.sendRedirect("Login");
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookid=request.getParameter("return");
		List<String> ids = Arrays.asList(bookid.split(":"));
		DBMethod.edit_book_status(conn, ids.get(0), false);
		DBMethod.delete_transaction(conn, ids.get(1));
		response.sendRedirect("Home");
	}
}