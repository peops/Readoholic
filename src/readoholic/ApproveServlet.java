package readoholic;

import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ApproveServlet")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public ApproveServlet() {
        super();
        @SuppressWarnings("unused")
        DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
        if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			String sessionID = null;
			String username = null;
			Cookie[] cookies = request.getCookies();
			if(cookies !=null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("user")) username = cookie.getValue();
					if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
				}
			}
	        List<Asset> assets = DBMethod.read_requested_assets(conn,username);
			RequestDispatcher rd = request.getRequestDispatcher("Approve.jsp");
			request.setAttribute("assetlist", assets);
			rd.forward(request, response);
        }
        else{
        	RequestDispatcher rd =request.getRequestDispatcher("Login.jsp");
			request.setAttribute("msg","Log-In first!");
			rd.forward(request, response);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
        if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			String username = null;
			Cookie[] cookies = request.getCookies();
			if(cookies !=null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("user")) username = cookie.getValue();
				}
			}
			String assetId=request.getParameter("accept");
			Asset asset = DBMethod.read_asset(conn, assetId);
			DBMethod.edit_asset_status(conn, assetId, false, true);
			Transaction transaction = new Transaction(assetId, username, asset.getAssetBorrowerId(),20);
			DBMethod.add_transaction(conn, transaction);
			RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
			rd.forward(request, response);
        }
        else{
        	RequestDispatcher rd =request.getRequestDispatcher("Login.jsp");
			request.setAttribute("msg","Log-In first!");
			rd.forward(request, response);
        }
		
	}
}