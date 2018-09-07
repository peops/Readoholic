package readoholic;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public LoginServlet() {
    	super();
    	@SuppressWarnings("unused")
    	DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        	String name=request.getParameter("name");
        	String pass=request.getParameter("pass");
        	User user = DBMethod.login(conn,name,pass);
        	
	    	if(user==null)
	    		throw new NullPointerException();
			HttpSession hs=request.getSession();
		 	hs.setAttribute("login_user",user);
			RequestDispatcher rd=request.getRequestDispatcher("MainMenu.jsp");
			rd.forward(request, response);
    	}
        catch(SQLException | NullPointerException e){
    		RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
    		request.setAttribute("msg", "Incorrect Id or Password!! Please re-login");
			rd.forward(request,response);
    	}
	}
}