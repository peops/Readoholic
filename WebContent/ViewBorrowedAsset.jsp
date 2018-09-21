<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="com.readoholic.model.*" session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="index2.css">
	</head>
	<body>
		<h1 align="center">Borrowed Products</h1>
		<form action="ViewBorrowedAsset" method="post">
			<%
				@SuppressWarnings("unchecked")	
				Map<Book, String> books=(Map<Book, String>)request.getAttribute("booklist");
				if(books.size()==0){
			%>
					<h2 align="center" style="padding:30px;">Nothing to show here.</h2>
			<%
				}
				else{
					for (Map.Entry<Book, String> entry : books.entrySet()){		
			%>
						<div class="row">
						  <div class="column" style="background-color:#aaa;"> 
							<h2>Product Id: <%out.print(entry.getKey().getBookId()); %></h2>
							<p>
								Product Name: 		<%out.print(entry.getKey().getBookName()); %> <br>
								&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					       		<button type="submit" name="return" value="<%out.print(entry.getKey().getBookId()+":"+entry.getValue());%>">Return</button>
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
