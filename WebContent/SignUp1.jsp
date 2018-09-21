<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>AMS</title>
		<link rel="stylesheet" href="index.css">
	</head>
	<body>
	  <div class="form">
	    <form action="SignUp"  method="post">
	      <input type="text" name="rollno"placeholder="rollno" required/>
	      <input type="text" name="username"placeholder="username" required/>
	      <input type="password" name="password" placeholder="password" required/>
	      <input type="text" name="firstname"placeholder="firstname" required/>
	      <input type="text" name="lastname"placeholder="lastname" required/>
		  <input type="email" name="email"placeholder="email Id" required/>
		  <input type="text" name="roomno"placeholder="roomno" required/>
		  <input type="text" name="hall"placeholder="hall" required/>
		  <input type="text" name="phoneno"placeholder="phone number"/> 
	      <a href="Login"><button type="submit">Register</button></a>
	      <p class="message">Already a member:&emsp;<a href="Login">Sign In</a></p>
	    </form>
	  </div>
	</body>
</html>
