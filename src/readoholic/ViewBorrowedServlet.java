package readoholic;

import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ViewBorrowedServlet")
public class ViewBorrowedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public ViewBorrowedServlet() {
        super();
        @SuppressWarnings("unused")
        DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
		User user=(User)hs.getAttribute("login_user");
		
		List<Asset> assets = DBMethod.read_borrowed_assets(conn, user.getUserName());

		RequestDispatcher rd = request.getRequestDispatcher("ViewBorrowed.jsp");
		request.setAttribute("assetlist",assets);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession hs=request.getSession(false);
		@SuppressWarnings("unused")
		User user=(User)hs.getAttribute("login_user");
		
		String id=request.getParameter("return");
		DBMethod.edit_asset_status(conn, id, false, false);
		DBMethod.edit_asset_borrower(conn, id, null);
		
		RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
		rd.forward(request, response);
	}
}