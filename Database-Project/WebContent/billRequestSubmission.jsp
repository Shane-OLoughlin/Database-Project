<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Request Submission</title>
</head>
<body>
	 	<div align="center">
	 	<a href=logout>Log Out</a>|
	 	<a href=davidsmith>Return to David Smith Page</a>
		<form action="submitbillrequest">
		<input type="hidden" name="orderofworkid" value="${orderOfWork.getOrderOfWorkID()}">
		<input type="hidden" name="email" value="${orderOfWork.getEmail()}">
			<table border="1" cellpadding="5">
				<caption><h2>Bill Request Submission for Order of Work ${orderOfWork.getOrderOfWorkID()} for User ${orderOfWork.getEmail()}</h2></caption>
				<tr>
					<th>Bill Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="billnote" size="45" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Bill Amount: </th>
					<td align="center" colspan="3">
						<input type="text" name="billamount" size="45" onfocus="this.value=''">
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
