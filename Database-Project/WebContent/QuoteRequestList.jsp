<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All Quote Request list</title>
</head>
<body>
   <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Quote Requests</h2></caption>
            <tr>
				<th>Quote Request ID</th>
                <th>Quote Note</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${quoterequests.quoterequestid}" /></td>
                    <td><c:out value="${quoterequests.quotenote}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>