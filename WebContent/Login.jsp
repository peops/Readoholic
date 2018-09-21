<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
		<link rel="stylesheet" type="text/css" href="index.css">
	</head>
	<body>
		<form action="Login" method="post">
			<div class="form">
				<h1> Asset Management System</h1>
				<input type="text" name="username" placeholder="username" required/><br>
				<input type="password" name="password" placeholder="password" required/><br>
				<%
					String ms = (String)request.getAttribute("msg");
					if(ms!=null){
						out.print(ms);
					}
				%>
				<button>Submit</button>
				<p class="message">Not Registered? &emsp;<a href="SignUp">Sign Up</a></p>
			</div>
		</form>
	</body>
</html>
