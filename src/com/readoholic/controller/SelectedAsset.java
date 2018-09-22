package com.readoholic.controller;

import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.readoholic.dblayer.DBConnection;
import com.readoholic.dblayer.DBMethod;
import com.readoholic.model.Book;
import com.readoholic.model.Quote;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SelectedAsset")
public class SelectedAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public SelectedAsset() {
        super();
        @SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
        conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId=request.getParameter("id");
		Book book = DBMethod.read_book(conn, bookId);
		Quote quote = DBMethod.read_quote(conn);
		RequestDispatcher rd = request.getRequestDispatcher("SelectedAsset.jsp");
		request.setAttribute("quote", quote);
		request.setAttribute("book", book);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	}
}