<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="readoholic.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<title>AMS</title>
	<link rel="stylesheet" href="test.css">
	</head>
	<body>
		<h1 align="center">Assets owned by you</h1>
		<form action="ViewAsset" method="post">
		<%
			HttpSession hs = request.getSession(false);
			List<Asset> assets=(List<Asset>)request.getAttribute("assetlist");
			if(assets.size()==0){
		%>
			<h2 align="center" style="padding:30px;">Nothing to show here.</h2>
		<%
			}
			else{
				for(Asset b : assets){
		%>
					<div class="row">
						<div class="column" style="background-color:#aaa;"> 
						  	<h2>Product Id: <%out.print(b.getAssetId()); %></h2>
						    <p>
						    Product Name: 		<%out.print(b.getAssetName()); %> <br>
						   	Product Class: 		<%out.print(b.getAssetClass()); %> <br>
						    Product Description: <%out.print(b.getAssetDescription()); %> <br>
						    Security Deposit: 	<%out.print(b.getAssetSecurityDeposit()); %> <br>
						    Product Status:		
						    					<% if(b.isAssetAllocationStatus()==true){
						    	  							 out.print("Allocated");
						    	  				%>
						    	  							 <br>
						    	  							 Borrower Id:
						    	  				<% 			
						    	  							 out.println(b.getAssetBorrowerId());
													}
						      						else if(b.isAssetRequestStatus()==true){
						    	   							out.print("Requested");
						    	   				%>			&emsp;&emsp;<button type="submit" name="delete" value= <%out.print(b.getAssetId());%>>Delete Asset</button><br>
						    	   							Requested by:
						    	   				<% 			out.println(b.getAssetBorrowerId());
						       						}
													else{
													       out.print("Available");
												%>		   &emsp;&emsp;<button type="submit" name="delete" value= <%out.print(b.getAssetId());%>>Delete Asset</button>
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
	</body>
</html>
