<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="com.readoholic.model.*" session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<title>AMS</title>
	<link rel="stylesheet" href="test.css">
	</head>
	<body>
		<h1 align="center">Assets owned by you</h1>
		<form action="ViewOwnedAsset" method="post">
		<%
			@SuppressWarnings("unchecked")
			List<Book> books=(List<Book>)request.getAttribute("booklist");
			if(books.size()==0){
		%>
			<h2 align="center" style="padding:30px;">Nothing to show here.</h2>
		<%
			}
			else{
				for(Book b : books){
		%>
					<div class="row">
						<div class="column" style="background-color:#aaa;"> 
						  	<h2>Product Id: <%out.print(b.getBookId()); %></h2>
						    <p>
						    Product Name: 		<%out.print(b.getBookName()); %> <br>
						    Product Status:		
						    					<% if(b.isBookAllocationStatus()==true){
						    	  							 out.print("Allocated");
						    	   				%>			&emsp;&emsp;<button type="submit" name="delete" value= <%out.print(b.getBookId());%>>Delete Asset</button><br>
						    	   				<% 
						    	   					}
													else{
													       out.print("Available");
												%>		   &emsp;&emsp;<button type="submit" name="delete" value= <%out.print(b.getBookId());%>>Delete Asset</button>
												<% 
													}
												%> <br>
							</p>
						</div>
					</div>
		<% 
				} 
			}
		%>
		</form>
		<br>
		<form action="Logout" method="post">
			<input type="submit" value="Logout" >
		</form>
	</body>
</html>
