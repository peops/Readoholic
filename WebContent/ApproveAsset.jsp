<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1" import="java.util.*" import="readoholic.*" session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="index2.css">
	</head>
	<body>
		<h1 align="center">Approve Products</h1>
		<form action="ApproveAssetServlet" method="post">
		<%
			HttpSession session=request.getSession();
			if(session.getAttribute("login_user") == null){
				RequestDispatcher rd =request.getRequestDispatcher("Login.jsp");
				request.setAttribute("msg","Log-In first!");
				rd.forward(request, response);
			}
			if(session!=null){ 
				User user=(User)session.getAttribute("login_user");
				String username = null;
				String sessionID = null;
				Cookie[] cookies = request.getCookies();
				if(cookies !=null){
					for(Cookie cookie : cookies){
						if(cookie.getName().equals("user")) username = cookie.getValue();
						if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
					}
				}
				List<Asset> assets=(List<Asset>)request.getAttribute("assetlist");
				if(assets.size()==0){
					%><h2 align="center" style="padding:30px;">Nothing to show here.</h2><%
				}
				else{
					for(Asset b:assets) 
					{
		%>
			
					<div class="row">
					  <div class="column" style="background-color:#aaa;"> 
					  	<h2>Product Id: <%out.print(b.getAssetId()); %></h2>
					    <p>
					       Product Name: 		<%out.print(b.getAssetName()); %> <br>
					   	   Product Class: 		<%out.print(b.getAssetClass()); %> <br> 
					       Product Description: <%out.print(b.getAssetDescription()); %>  <br>
					       Security Deposit: 	<%out.print(b.getAssetSecurityDeposit()); %> <br>
					       Borrower Id:			<%out.print(b.getAssetBorrowerId()); %><br>
					       &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					       <button type="submit" name="accept" value="<%out.print(b.getAssetId());%>">Approve</button>
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
