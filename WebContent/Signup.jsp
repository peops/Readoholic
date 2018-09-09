<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>AMS</title>
		<link rel="stylesheet" href="index.css">
	</head>
	<body>
	  <div class="form">
	    <form action="SignInServlet"  method="post">
		  <input type="text" name="name"placeholder="name" required/>
	      <input type="text" name="username"placeholder="username" required/>
	      <input type="password" name="password" placeholder="password" required/>
		  <input type="email" name="email"placeholder="email Id" required/>
		  <input type="text" name="address"placeholder="address" required/>
		  <input type="text" name="phone"placeholder="phone number"/> 
	      <a href="Login.jsp"><button type="submit">Register</button></a>
	      <p class="message">Already a member:&emsp;<a href="Login.jsp">Sign In</a></p>
	    </form>
	  </div>
	</body>
</html>
