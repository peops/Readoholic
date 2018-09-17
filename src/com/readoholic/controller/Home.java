package com.readoholic.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Home() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
        if(hs!=null){ 
	        RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
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
