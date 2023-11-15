<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order of Work Submission</title>
</head>
<body>
	<div align="center">
	 	<a href=logout>Log Out</a> |
	 	<a href=client>Return to Client Page</a>
		<form action="submitorderofwork">
		<input type="hidden" name="quoteresponseid" value="${quoteResponse.getQuoteResponseID()}">
			<table border="1" cellpadding="5">
			<caption><h2>Order of Work Submission for Quote Response ${quoteResponse.getQuoteResponseID()}</h2></caption>
				<tr>
					<th>Order of Work Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="orderofworknote" size="45" onfocus="this.value=''">
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