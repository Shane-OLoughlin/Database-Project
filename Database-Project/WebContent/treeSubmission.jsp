<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tree Submission</title>
</head>
<body>
	 	<div align="center">
	 	<a href=logout>Log Out</a> |
	 	<a href=client>Return to Client Page</a>
		<form action="submittree">
		<input type="hidden" name="quoterequestid" value="${quoteRequest.getQuoteRequestID()}">
			<table border="1" cellpadding="5">
			<caption><h2>Tree Submission for Quote Request ${quoteRequest.getQuoteRequestID()}</h2></caption>
				<tr>
					<th>Size: </th>
					<td align="center" colspan="3">
						<input type="text" name="size" size="45" value = "1.0" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Height: </th>
					<td align="center" colspan="3">
						<input type="text" name="height" size="45" value = "1.0" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Location: </th>
					<td align="center" colspan="3">
						<input type="text" name="location" size="45" value = "Front Yard" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Proximity to House: </th>
					<td align="center" colspan="3">
						<input type="text" name="proximitytohouse" size="45" value = "1.0" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Picture 1: </th>
					<td align="center" colspan="3">
						<input type="text" name="picture1" size="45" value = "tree1.jpg" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Picture 2: </th>
					<td align="center" colspan="3">
						<input type="text" name="picture2" size="45" value = "tree2.jpg" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Picture 3: </th>
					<td align="center" colspan="3">
						<input type="text" name="picture3" size="45" value = "tree3.jpg" onfocus="this.value=''">
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