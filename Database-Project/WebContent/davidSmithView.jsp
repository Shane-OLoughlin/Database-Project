<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith page</title>
</head>
<body>
<div align = "center">
	
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all quote requests</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Requests</h2></caption>
            <tr>
                <th>Quote Request ID</th>
                <th>Quote Note</th>
                <th>Quote Note</th>
            </tr>
            <c:forEach var="quoterequests" items="${listQuoteRequest}">
                <tr style="text-align:center">
                    <td><c:out value="${quoterequests.getQuoteRequestID()}" /></td>
                    <td><c:out value="${quoterequests.getQuoteNote()}" /></td>
                    <td><c:out value="${quoterequests.getQuoteNote()}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>
</body>
</html>
