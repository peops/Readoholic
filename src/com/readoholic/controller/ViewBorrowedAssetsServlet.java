package com.readoholic.controller;

import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Asset;
import com.readoholic.model.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ViewBorrowedAssetsServlet")
public class ViewBorrowedAssetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public ViewBorrowedAssetsServlet() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
        conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
		if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			List<Asset> assets = DBMethod.read_borrowed_assets(conn, user.getName());
			RequestDispatcher rd = request.getRequestDispatcher("ViewBorrowedAssets.jsp");
			request.setAttribute("assetlist",assets);
			rd.forward(request, response);
        }
        else{
        	response.sendRedirect("LoginServlet");
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("return");
		DBMethod.edit_asset_status(conn, id, false, false);
		DBMethod.edit_asset_borrower(conn, id, null);
		response.sendRedirect("Dashboard");
	}
}