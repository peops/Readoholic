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
			  <input type="text" name="bookName" placeholder="bookName" required/>
		  	  <input type="text" name="bookGenre" placeholder="bookGenre" required/>
		  	  <input type="text" name="bookAuthor" placeholder="bookAuthor" required/>
		  	  <input type="text" name="language" placeholder="language" required/>
		  	  <input type="text" name="bookOwner" placeholder="bookOwner" required/>
		  	  <input type="text" name="bookDescription" placeholder="bookDescription" required/>
		  	  <input type="text" name="bookSecurityDeposit" placeholder="Security Deposit" required/>	  
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
