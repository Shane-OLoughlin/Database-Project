<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quote Reject Submission</title>
</head>
<body>
	 	<div align="center">
	 	<a href=logout>Log Out</a>|
	 	<a href=davidsmith>Return to David Smith Page</a>
		<form action="submitquotereject">
			<input type="hidden" name="quoterequestid" value="${quoteRequest.getQuoteRequestID()}">
			<input type="hidden" name="email" value="${quoteRequest.getEmail()}">
			<table border="1" cellpadding="5">
				<caption><h2>Quote Reject Submission for Quote Request ${quoteRequest.getQuoteRequestID()} for User ${quoteRequest.getEmail()}</h2></caption>
				<tr>
					<th>Quote Reject Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="quoterejectnote" size="45" onfocus="this.value=''">
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
