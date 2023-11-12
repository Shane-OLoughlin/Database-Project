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

    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Requests</h2></caption>
            <tr>
                <th>Quote Request ID</th>
                <th>Quote Note</th>
                <th>Email of Client</th>
                <th>Reject</th>
                <th>Respond</th>
            </tr>
            <c:forEach var="quoterequests" items="${listQuoteRequest}">
                <tr style="text-align:center">
                    <td><c:out value="${quoterequests.getQuoteRequestID()}" /></td>
                    <td><c:out value="${quoterequests.getQuoteNote()}" /></td>
                    <td><c:out value="${quoterequests.getemailquoterequest()}" /></td>
                    <td><a href=reject?id="${quoterequests.getQuoteRequestID()}">Reject</a></td>
                    <td><a href=quoteResponseSubmission>Respond</a></td>
            </c:forEach>
        </table>
	</div>
    <div align="center">
        <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Trees</h2></caption>
            <tr>
                <th>Tree ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>Proximity to House</th>
                <th>Picture 1</th>
                <th>Picture 2</th>
                <th>Picture 3</th>
                <th>Quote Request ID</th>
                <th>Email of Client</th>
            </tr>
            <c:forEach var="trees" items="${listTree}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.getTreeID()}" /></td>
                    <td><c:out value="${trees.getSize()}" /></td>
                    <td><c:out value="${trees.getHeight()}" /></td>
                    <td><c:out value="${trees.getLocation()}" /></td>
                    <td><c:out value="${trees.getproximitytohouse()}" /></td>
                    <td><c:out value="${trees.getPicture1()}" /></td>
                    <td><c:out value="${trees.getPicture2()}" /></td>
                    <td><c:out value="${trees.getPicture3()}" /></td>
                    <td><c:out value="${trees.getQuoteRequestID()}" /></td>
                    <td><c:out value="${trees.getEmail()}" /></td>
            </c:forEach>
        </table>
	</div>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Responses</h2></caption>
            <tr>
                <th>Quote Response ID</th>
                <th>Initial Price</th>
                <th>Time Window</th>
                <th>Quote Request ID</th>
                <th>Email of Client</th>
            </tr>
            <c:forEach var="quoteresponses" items="${listQuoteResponse}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteresponses.getQuoteResponseID()}" /></td>
                    <td><c:out value="${quoteresponses.getInitialPrice()}" /></td>
                    <td><c:out value="${quoteresponses.getTimeWindow()}" /></td>
                    <td><c:out value="${quoteresponses.getQuoteRequestID()}" /></td>
                    <td><c:out value="${quoterequests.getEmail()}" /></td>
            </c:forEach>
        </table>
	</div>
	    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Rejects</h2></caption>
            <tr>
                <th>Quote Response ID</th>
                <th>Quote Reject Note</th>
                <th>Quote Request ID</th>
                <th>Email of Client</th>
            </tr>
            <c:forEach var="quoterejects" items="${listQuoteReject}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteresponses.getQuoteRejectID()}" /></td>
                    <td><c:out value="${quoterejections.getQuoteRejectNote()}" /></td>
                    <td><c:out value="${quoteresponses.getQuoteRequestID()}" /></td>
                    <td><c:out value="${quoterequests.getEmail()}" /></td>
            </c:forEach>
        </table>
	</div>
		    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Rejects</h2></caption>
            <tr>
                <th>Quote Response ID</th>
                <th>Quote Reject Note</th>
                <th>Quote Request ID</th>
                <th>Email of Client</th>
            </tr>
            <c:forEach var="quoterejects" items="${listQuoteReject}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteresponses.getQuoteRejectID()}" /></td>
                    <td><c:out value="${quoterejections.getQuoteRejectNote()}" /></td>
                    <td><c:out value="${quoteresponses.getQuoteRequestID()}" /></td>
                    <td><c:out value="${quoterequests.getEmail()}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>
</body>
</html>
