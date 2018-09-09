<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*" import="readoholic.*" session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="index2.css">
	</head>
	<body>
		<h1 align="center">Borrow Products</h1>
		<form action="BorrowServlet" method="post">
		<%
			HttpSession session=request.getSession();
			if(session.getAttribute("login_user") == null){
				RequestDispatcher rd =request.getRequestDispatcher("Login.jsp");
				request.setAttribute("msg","Log-In first!");
				rd.forward(request, response);
			}
			List<Asset> assets=(List<Asset>)request.getAttribute("assetlist");
			if(assets.size()==0){
		%>
		<h2 align="center" style="padding:30px;">Nothing to show here.</h2>
		<%
			}
			else{
				for(Asset b : assets) {
					if(b.isAssetRequestStatus()==false&&b.isAssetAllocationStatus()==false){
		%>
		
		<div class="row">
		  	<div class="column" style="background-color:#aaa;"> 
			  	<h2>Product Id: <%out.print(b.getAssetId()); %></h2>
			    <p>
					Product Name: 		<%out.print(b.getAssetName()); %> <br>
					Product Class: 		<%out.print(b.getAssetClass()); %> <br> 
					Product Description: <%out.print(b.getAssetDescription()); %>  <br>
					Security Deposit: 	<%out.print(b.getAssetSecurityDeposit()); %> <br>
					Owners Id:			<%out.print(b.getAssetOwnerId()); %><br>
			        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			        <button type="submit" name="lend" value="<%out.print(b.getAssetId());%>"> Rent Asset </button>
			    </p>
		    </div>		
		</div>
		<% 
					}
				}
			} 
		%>
		</form>
		<br>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="Logout" >
		</form>
	</body>
</html>
