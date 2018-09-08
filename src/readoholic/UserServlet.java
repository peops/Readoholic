package readoholic;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet implements DBMethod{
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	public UserServlet() {
        super();
        @SuppressWarnings("unused")
        DBConnection dbcon = DBConnection.getInstance();
    	conn = DBConnection.getStatement();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		
		User user=new User(username,password,name,address,phone,email);
		DBMethod.add_user(conn, user);
		
		RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
		request.setAttribute("msg","You are now registered, Please log in to access!");
		rd.forward(request, response);    		
	}
}
