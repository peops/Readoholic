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
		    <form action="AddAsset" method="post">
			  <input type="text" name="assetname" placeholder="Asset name" required/>
		      <input type="text" name="class" placeholder="Asset Class" required/>
		      <input type="text" name="description" placeholder="Description(Fridge,Tv,etc)"/>
			  <input type="text" name="security" placeholder="Security Deposit" required/>	  
		      <button type="submit">submit</button>
		    </form>
		    <br>
			<form action="Logout" method="post">
				<input type="submit" value="Logout" >
			</form>
		  </div>
		</div>
	</body>
</html>
