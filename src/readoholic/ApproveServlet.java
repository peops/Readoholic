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
	private Connection conn;
	String db_url = "jdbc:postgresql://localhost";
	String db_port = "5432";
	String db_name = "postgres";
	String db_user = "postgres";
	String db_password = "postgres";
	String url = "" + db_url + ":" + db_port + "/" + db_name + "";
    public ApproveServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        	Class.forName("org.postgresql.Driver");
        	java.sql.Connection connection = DriverManager.getConnection(url, db_user, db_password);
        	conn = connection;
        	
        	HttpSession hs=request.getSession(false);
        	User user=(User)hs.getAttribute("login_user");
        	
        	List<Asset> assets = DBMethod.read_requested_assets(conn,user.getUserName());
        	
        	RequestDispatcher rd = request.getRequestDispatcher("Approve.jsp");
        	request.setAttribute("assetlist", assets);
        	rd.forward(request, response);
        }
        catch (SQLException | ClassNotFoundException ex) {
        	System.out.println(ex.getMessage());
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
        	Class.forName("org.postgresql.Driver");
        	java.sql.Connection connection = DriverManager.getConnection(url, db_user, db_password);
        	conn = connection;
        	
        	HttpSession hs=request.getSession(false);
        	User user=(User)hs.getAttribute("login_user");
        	
        	String assetId=request.getParameter("accept");
        	Asset asset = DBMethod.read_asset(conn, assetId);
        	DBMethod.edit_asset_status(conn, assetId, false, true);
        	Transaction transaction = new Transaction(assetId, user.getUserName(), asset.getAssetBorrowerId(),20);
        	DBMethod.add_transaction(conn, transaction);
        	
        	RequestDispatcher rd = request.getRequestDispatcher("MainMenu.jsp");
        	rd.forward(request, response);	
        }
        catch (SQLException | ClassNotFoundException ex) {
	        System.out.println(ex.getMessage());
	    }
	}
}