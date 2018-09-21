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
		<h1> Welcome <% out.print(user.getUsername()); %>	</h1>
		<h2> What do you want to do today?</h2>
		<nav>
		  <ul>
		    <li>Lend
		      <ul class="drop-menu menu-1">
		        <li><a href="AddAsset" style="text-decoration: none;color : white">Add Asset</a></li>
		        <li><a href="ApproveAsset" style="text-decoration: none;color : white">Approve Asset</a></li>
		        <li><a href="ViewOwnedAsset" style="text-decoration: none;color : white">View Assets</a></li>
		      </ul>
		    </li>
		    <li>Borrow
		      <ul class="drop-menu menu-2">
		        <li><a href="BorrowAsset" style="text-decoration: none;color : white">Borrow Assets</a></li>
		        <li><a href="ViewBorrowedAsset" style="text-decoration: none;color : white">View Borrowed</a></li>
		      </ul>
		    </li>
		    <li><button>Notifications</button></li>
		  </ul>
		</nav>
		<br>
		<form action="Logout" method="post">
			<input type="submit" value="Logout" >
		</form>	
	</body>
</html>