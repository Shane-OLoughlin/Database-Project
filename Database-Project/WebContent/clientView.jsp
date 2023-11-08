<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>
<body>
<center><h1>Welcome! You have been successfully logged in</h1> </center>
	 	<div align="center">
	 				<a href="login.jsp" target="_self">Return to Login Page</a>
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="submitquoterequest">
			<table border="1" cellpadding="5">
				<tr>
					<th>Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="quotenote" size="45" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Submit"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>