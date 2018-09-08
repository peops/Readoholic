<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="readoholic.*" session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<title>AMS</title>
		<link rel="stylesheet" href="index1.css">
	</head>
	<body >
		<%
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
		%>
				<h1> Welcome <% out.print(username); %>	</h1>
				<h2> What do you want to do today?</h2>
				<nav>
				  <ul>
				    <li>Lend
				      <ul class="drop-menu menu-1">
				        <li><a href="AddAsset.jsp" style="text-decoration: none;color : white">Add Asset</a></li>
				        <li><a href="ApproveServlet" style="text-decoration: none;color : white">Approve Asset</a></li>
				        <li><a href="ViewAsset" style="text-decoration: none;color : white">View Assets</a></li>
				      </ul>
				    </li>
				    <li>Borrow
				      <ul class="drop-menu menu-2">
				        <li><a href="BorrowServlet" style="text-decoration: none;color : white">Borrow Assets</a></li>
				        <li><a href="ViewBorrowedServlet" style="text-decoration: none;color : white">View Borrowed</a></li>
				      </ul>
				    </li>
				    <li><button>Notifications</button></li>
				  </ul>
				</nav>
				<br>
				<form action="LogoutServlet" method="post">
					<input type="submit" value="Logout" >
				</form>
		<%	}
			else{
				RequestDispatcher rd =request.getRequestDispatcher("Login.jsp");
				request.setAttribute("msg","Log-In first!");
				rd.forward(request, response);
			}
		%>
	</body>
</html>