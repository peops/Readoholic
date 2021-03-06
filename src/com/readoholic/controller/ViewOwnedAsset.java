package com.readoholic.controller;

import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Book;
import com.readoholic.model.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ViewOwnedAsset")
public class ViewOwnedAsset extends HttpServlet implements DBMethod{
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	public ViewOwnedAsset() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
        if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			List<Book> assets = DBMethod.read_user_books(conn, user.getUsername());
			if(assets!=null){
				RequestDispatcher rd = request.getRequestDispatcher("ViewOwnedAsset.jsp");
				request.setAttribute("booklist", assets);
				rd.forward(request, response);
			}			
			else{
				response.sendRedirect("LoginServlet");
			}
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
        if(hs!=null){ 
			String id=request.getParameter("delete");
	    	DBMethod.delete_book(conn, id);
	    	response.sendRedirect("Home");
        }
	}
}