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
		String rollno=request.getParameter("rollno");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		String roomno=request.getParameter("roomno");
		String hall=request.getParameter("hall");
		String phoneno=request.getParameter("phoneno");
		String email=request.getParameter("email");
		System.out.println("check0");
		User user=new User(rollno, username, password, firstName, lastName, roomno, hall, phoneno, email);
		DBMethod.add_user(conn, user);
		response.sendRedirect("Login");   		
	}
}
