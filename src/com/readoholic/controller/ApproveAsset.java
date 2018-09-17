package com.readoholic.controller;

import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Asset;
import com.readoholic.model.Transaction;
import com.readoholic.model.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ApproveAsset")
public class ApproveAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public ApproveAsset() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
        conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
        if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			List<Asset> assets = DBMethod.read_requested_assets(conn,user.getName());
			RequestDispatcher rd = request.getRequestDispatcher("ApproveAsset.jsp");
			request.setAttribute("assetlist", assets);
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
			String assetId=request.getParameter("accept");
			Asset asset = DBMethod.read_asset(conn, assetId);
			DBMethod.edit_asset_status(conn, assetId, false, true);
			Transaction transaction = new Transaction(assetId, user.getName(), asset.getAssetBorrowerId(),20);
			DBMethod.add_transaction(conn, transaction);
			response.sendRedirect("Home");
        }
        else{
        	response.sendRedirect("Login");
        }
		
	}
}