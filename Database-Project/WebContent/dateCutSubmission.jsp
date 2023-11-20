<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Date Cut Submission</title>
</head>
<body>
	 	<div align="center">
	 	<a href=logout>Log Out</a>|
	 	<a href=davidsmith>Return to David Smith Page</a>
		<form action="submitdatecut">
		<input type="hidden" name="treeid" value="${tree.getTreeID()}">
			<table border="1" cellpadding="5">
				<caption><h2>Date Cut Submission for Tree ${tree.getTreeID()} for User ${tree.getEmail()}</h2></caption>
				<tr>
					<th>Date Cut: </th>
					<td align="center" colspan="3">
						<input type="text" name="datecut" size="45" onfocus="this.value=''">
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
