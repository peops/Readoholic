<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="com.readoholic.model.*" session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<title>AMS</title>
		<link rel="stylesheet" href="index1.css">
	</head>
	<body >
		<%
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("login_user");
		%>
		<h1> Welcome <% out.print(user.getName()); %>	</h1>
		<h2> What do you want to do today?</h2>
		<nav>
		  <ul>
		    <li>Lend
		      <ul class="drop-menu menu-1">
		        <li><a href="AddAssetServlet" style="text-decoration: none;color : white">Add Asset</a></li>
		        <li><a href="ApproveAssetServlet" style="text-decoration: none;color : white">Approve Asset</a></li>
		        <li><a href="ViewAvailableAssetsServlet" style="text-decoration: none;color : white">View Assets</a></li>
		      </ul>
		    </li>
		    <li>Borrow
		      <ul class="drop-menu menu-2">
		        <li><a href="BorrowAssetServlet" style="text-decoration: none;color : white">Borrow Assets</a></li>
		        <li><a href="ViewBorrowedAssetsServlet" style="text-decoration: none;color : white">View Borrowed</a></li>
		      </ul>
		    </li>
		    <li><button>Notifications</button></li>
		  </ul>
		</nav>
		<br>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="Logout" >
		</form>	
	</body>
</html>