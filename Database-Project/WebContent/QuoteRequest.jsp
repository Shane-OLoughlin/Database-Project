<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all quote requests</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Requests</h2></caption>
            <tr>
                <th>Quote request id</th>
                <th>Quote note</th>
            </tr>
            <c:forEach var="user" items="${get_quoterequest}">
                <tr style="text-align:center">
                    <td>"${quoterequest.quoterequestid}" </td>
                    <td>"${quoterequest.quotenote}"</td>
                 </tr>
            </c:forEach>
          </table>
	</div>
<body>

</body>
</html>