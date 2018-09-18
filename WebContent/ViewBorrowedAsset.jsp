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
				List<Asset> assets=(List<Asset>)request.getAttribute("assetlist");
				if(assets.size()==0){
			%>
					<h2 align="center" style="padding:30px;">Nothing to show here.</h2>
			<%
				}
				else{
					for(Asset b : assets) {		
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
					       		<button type="submit" name="return" value="<%out.print(b.getAssetId());%>">Return</button>
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