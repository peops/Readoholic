package com.readoholic.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Asset;
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
			List<Asset> assets = DBMethod.read_assets(conn);
			List<Asset> filtered_assets = new ArrayList<>();
			for(Asset a: assets){
				if(a.getAssetOwnerId().equals(user.getName())==false){
					filtered_assets.add(a);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("BorrowAsset.jsp");
			request.setAttribute("assetlist", filtered_assets);
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
			String selectedItem=request.getParameter("lend");
			System.out.println(selectedItem);
			DBMethod.edit_asset_status(conn, selectedItem, true, false);
			DBMethod.edit_asset_borrower(conn, selectedItem, user.getName());
			response.sendRedirect("Home");
        }
        else{
        	response.sendRedirect("Login");
        }
	}
}