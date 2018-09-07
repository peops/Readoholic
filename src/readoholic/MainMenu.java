package readoholic;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/MainMenu")
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MainMenu() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
