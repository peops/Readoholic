package com.readoholic.controller;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet implements DBMethod{
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	public SignUp() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
        if(hs!=null){ 
	        hs.invalidate();
        }
        RequestDispatcher rd = request.getRequestDispatcher("SignUp.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		
		User user=new User(username,password,name,address,phone,email);
		DBMethod.add_user(conn, user);
		response.sendRedirect("Login");   		
	}
}
