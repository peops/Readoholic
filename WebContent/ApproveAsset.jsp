<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1" import="java.util.*" import="com.readoholic.model.*" session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="index2.css">
	</head>
	<body>
		<h1 align="center">Approve Products</h1>
		<form action="ApproveAsset" method="post">
		<%
				@SuppressWarnings("unchecked")
				List<Book> books=(List<Book>)request.getAttribute("booklist");
				if(books.size()==0){
					%><h2 align="center" style="padding:30px;">Nothing to show here.</h2><%
				}
				else{
					for(Book b:books) 
					{
		%>
			
					<div class="row">
					  <div class="column" style="background-color:#aaa;"> 
					  	<h2>Product Id: <%out.print(b.getBookId()); %></h2>
					    <p>
					       Product Name: 		<%out.print(b.getBookName()); %> <br>
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
