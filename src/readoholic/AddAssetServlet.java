package readoholic;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AddAssetServlet")
public class AddAssetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	public AddAssetServlet() {
        super();
        @SuppressWarnings("unused")
		DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assetname=request.getParameter("assetname");
		String assetclass=request.getParameter("class");
		String description=request.getParameter("description");
		String sec=request.getParameter("security");
		int security=Integer.parseInt(sec);
		
		HttpSession hs=request.getSession(false);
		User user=(User)hs.getAttribute("login_user");
		
		String ownerId=user.getUserName();
		Asset asset=new Asset(assetname,assetclass,ownerId,description,security);
		
		DBMethod.add_asset(conn, asset);
		
		RequestDispatcher rd=request.getRequestDispatcher("MainMenu.jsp");
		rd.forward(request, response);
	}
}