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
		<h1>List all quote requests</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Responses</h2></caption>
            <tr>
                <th>Quote Response ID</th>
                <th>Initial Price</th>
                <th>Time Window</th>
            </tr>
            <c:forEach var="quoteresponses" items="${listQuoteResponse}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteresponses.getQuoteResponseID()}" /></td>
                    <td><c:out value="${quoteresponses.getInitialPrice()}" /></td>
                    <td><c:out value="${quoteresponses.getTimeWindow()}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>
	</body>
</html>