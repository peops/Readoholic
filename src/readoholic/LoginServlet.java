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
		HttpSession session=request.getSession(false);
        if(session!=null){ 
			User user=(User)session.getAttribute("login_user");
			String sessionID = null;
			String username = null;
			Cookie[] cookies = request.getCookies();
			if(cookies !=null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("user")) username = cookie.getValue();
					if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
				}
			}
			if(session != null){
	    		session.invalidate();
	    	}	        
        }
    	RequestDispatcher rd =request.getRequestDispatcher("Login.jsp");
    	rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        	String name=request.getParameter("name");
        	String pass=request.getParameter("pass");
        	User user = DBMethod.login(conn,name,pass);
        	
	    	if(user!=null){
				HttpSession hs=request.getSession();
			 	hs.setAttribute("login_user",user);
			 	hs.setMaxInactiveInterval(30*60);
				Cookie username = new Cookie("user", user.getUserName());
				username.setMaxAge(30*60);
				response.addCookie(username);
				response.sendRedirect("Dashboard");
	    	}
	    	else{
	    		throw new NullPointerException();
	    	}
    	}
        catch(SQLException | NullPointerException e){
    		response.sendRedirect("LoginServlet");
    	}
	}
}