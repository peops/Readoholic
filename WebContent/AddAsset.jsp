<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<title>AMS</title>
	<link rel="stylesheet" href="index.css">
	</head>
	<body>
		<div class="login-page">
		  <div class="form" align="left">
		  	<h2> ADD ASSET</h2>
		    <form action="AddAssetServlet" method="post">
			  <input type="text" name="assetname" placeholder="Asset name" required/>
		      <input type="text" name="class" placeholder="Asset Class" required/>
		      <input type="text" name="description" placeholder="Description(Fridge,Tv,etc)"/>
			  <input type="text" name="security" placeholder="Security Deposit" required/>	  
		      <button type="submit">submit</button>
			  <p class="message"><a href="Dashboard.jsp">Go Back</a></p>
		    </form>
		    <br>
			<form action="LogoutServlet" method="post">
				<input type="submit" value="Logout" >
			</form>
		  </div>
		</div>
	</body>
</html>
