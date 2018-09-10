package readoholic;

import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ViewAvailableAssetsServlet")
public class ViewAvailableAssetsServlet extends HttpServlet implements DBMethod{
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	public ViewAvailableAssetsServlet() {
        super();
        @SuppressWarnings("unused")
        DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs=request.getSession(false);
        if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			String username = null;
			String sessionID = null;
			Cookie[] cookies = request.getCookies();
			if(cookies !=null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("user")) username = cookie.getValue();
					if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
				}
			}
			List<Asset> assets = DBMethod.read_user_assets(conn, username);
			if(assets!=null){
				RequestDispatcher rd = request.getRequestDispatcher("ViewAvailableAssets.jsp");
				request.setAttribute("assetlist", assets);
				rd.forward(request, response);
			}			
			else{
				response.sendRedirect("LoginServlet");
			}
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession(false);
        if(hs!=null){ 
			User user=(User)hs.getAttribute("login_user");
			String username = null;
			String sessionID = null;
			Cookie[] cookies = request.getCookies();
			if(cookies !=null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("user")) username = cookie.getValue();
					if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
				}
			}
			String id=request.getParameter("delete");
	    	System.out.println(id);
	    	DBMethod.delete_asset(conn, id);
	    	response.sendRedirect("Dashboard");
        }
	}
}