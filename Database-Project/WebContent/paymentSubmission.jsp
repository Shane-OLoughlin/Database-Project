<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Submission</title>
</head>
<body>
	 	<div align="center">
	 	<a href=logout>Log Out</a> |
	 	<a href=client>Return to Client Page</a>
		<form action="submitpayment">
		<input type="hidden" name="billrequestid" value="${billRequest.getBillRequestID()}">
		<input type="hidden" name="paymentamount" value="${billRequest.getBillAmount()}">
			<table border="1" cellpadding="5">
				<caption><h2>Payment Submission for Bill Request ${billRequest.getBillRequestID()}</h2></caption>
				<tr>
					<th>Payment Amount: </th>
					<td align="center" colspan="3">$${billRequest.getBillAmount()}</td>
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
