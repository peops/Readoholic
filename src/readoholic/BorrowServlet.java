package readoholic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public BorrowServlet() {
        super();
        @SuppressWarnings("unused")
        DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Asset> assets = DBMethod.read_assets(conn);
		List<Asset> filtered_assets = new ArrayList<>();
		
		HttpSession hs=request.getSession(false);
		User user=(User)hs.getAttribute("login_user");
		
		for(Asset a: assets){
			if(a.getAssetOwnerId().equals(user.getUserName())==false){
				filtered_assets.add(a);
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Borrow.jsp");
		request.setAttribute("assetlist", filtered_assets);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
		User user=(User)hs.getAttribute("login_user");
		
		String selectedItem=request.getParameter("lend");
		System.out.println(selectedItem);
		DBMethod.edit_asset_status(conn, selectedItem, true, false);
		DBMethod.edit_asset_borrower(conn, selectedItem, user.getUserName());
		
		RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
		rd.forward(request, response);
	}
}