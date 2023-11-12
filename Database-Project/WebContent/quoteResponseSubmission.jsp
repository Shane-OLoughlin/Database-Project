<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 	<div align="center">
	 				<a href="davidSmithView.jsp" target="_self">Return to David Smith page</a>
		<form action="submitquoteresponse">
			<table border="1" cellpadding="5">
				<tr>
					<th>Initial Price: </th>
					<td align="center" colspan="3">
						<input type="text" name="initialprice" size="45" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Time Window: </th>
					<td align="center" colspan="3">
						<input type="text" name="timewindow" size="45" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Quote Request ID: </th>
					<td align="center" colspan="3">
						<input type="text" name="quoterequestid" size="45" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Email of Client: </th>
					<td align="center" colspan="3">
						<input type="text" name="email" size="45" onfocus="this.value=''">
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
