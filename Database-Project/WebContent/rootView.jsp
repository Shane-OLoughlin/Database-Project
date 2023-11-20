<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href=logout>Log Out</a> 
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Credit Card Info</th>
                <th>Phone Number</th>
                <th>ClientID</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditcardinfo}" /></td>
                    <td><c:out value="${users.phonenumber}"/></td>
                    <td><c:out value="${users.clientid}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Big Clients</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Credit Card Info</th>
                <th>Phone Number</th>
                <th>ClientID</th>
            </tr>
            <c:forEach var="users" items="${listBigClients}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditcardinfo}" /></td>
                    <td><c:out value="${users.phonenumber}"/></td>
                    <td><c:out value="${users.clientid}" /></td>
            </c:forEach>
        </table>
	</div>
		<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Easy Clients</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Credit Card Info</th>
                <th>Phone Number</th>
                <th>ClientID</th>
            </tr>
            <c:forEach var="users" items="${listEasyClients}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditcardinfo}" /></td>
                    <td><c:out value="${users.phonenumber}"/></td>
                    <td><c:out value="${users.clientid}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of One Tree Quotes</h2></caption>
            <tr>
                <th>Quote Request ID</th>
                <th>Quote Note</th>
                <th>Resubmitted for Negotiations?</th>
                <th>Email of Client</th>
            </tr>
            <c:forEach var="quoterequests" items="${listOneTreeQuotes}">
                <tr style="text-align:center">
                    <td><c:out value="${quoterequests.getQuoteRequestID()}" /></td>
                    <td><c:out value="${quoterequests.getQuoteNote()}" /></td>
                    <td><c:out value="${quoterequests.getNegotiations()}" /></td>
                    <td><c:out value="${quoterequests.getEmail()}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Prospective Clients</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Credit Card Info</th>
                <th>Phone Number</th>
                <th>ClientID</th>
            </tr>
            <c:forEach var="users" items="${listProspectiveClients}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditcardinfo}" /></td>
                    <td><c:out value="${users.phonenumber}"/></td>
                    <td><c:out value="${users.clientid}" /></td>
            </c:forEach>
        </table>
	</div>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Highest Trees</h2></caption>
            <tr>
                <th>Tree ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>Proximity to House</th>
                <th>Picture 1</th>
                <th>Picture 2</th>
                <th>Picture 3</th>
                <th>Date Cut</th>
                <th>Quote Request ID</th>
                <th>Email of Client</th>
            </tr>
            <c:forEach var="trees" items="${listHighestTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.getTreeID()}" /></td>
                    <td><c:out value="${trees.getSize()}" /></td>
                    <td><c:out value="${trees.getHeight()}" /></td>
                    <td><c:out value="${trees.getLocation()}" /></td>
                    <td><c:out value="${trees.getproximitytohouse()}" /></td>
                    <td><c:out value="${trees.getPicture1()}" /></td>
                    <td><c:out value="${trees.getPicture2()}" /></td>
                    <td><c:out value="${trees.getPicture3()}" /></td>
                    <td><c:out value="${trees.getDateCut()}" /></td>
                    <td><c:out value="${trees.getQuoteRequestID()}" /></td>
                    <td><c:out value="${trees.getEmail()}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Overdue Bills</h2></caption>
            <tr>
                <th>Bill Request ID</th>
                <th>Bill Note</th>
                <th>Bill Amount</th>
                <th>Time Generated</th>
                <th>Order of Work ID</th>
                <th>Email of Client</th>
            </tr>
            <c:forEach var="billrequests" items="${listOverdueBills}">
                <tr style="text-align:center">
                    <td><c:out value="${billrequests.getBillRequestID()}" /></td>
                    <td><c:out value="${billrequests.getBillNote()}" /></td>
                    <td><c:out value="${billrequests.getBillAmount()}" /></td>
                    <td><c:out value="${billrequests.getTimeGenerated()}" /></td>
                    <td><c:out value="${billrequests.getOrderOfWorkID()}" /></td>
                    <td><c:out value="${billrequests.getEmail()}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Bad Clients</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Credit Card Info</th>
                <th>Phone Number</th>
                <th>ClientID</th>
            </tr>
            <c:forEach var="users" items="${listBadClients}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditcardinfo}" /></td>
                    <td><c:out value="${users.phonenumber}"/></td>
                    <td><c:out value="${users.clientid}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Good Clients</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Credit Card Info</th>
                <th>Phone Number</th>
                <th>ClientID</th>
            </tr>
            <c:forEach var="users" items="${listGoodClients}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditcardinfo}" /></td>
                    <td><c:out value="${users.phonenumber}"/></td>
                    <td><c:out value="${users.clientid}" /></td>
            </c:forEach>
        </table>
	</div>
	    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>User Statistics</h2></caption>
            <tr>
                <th>Email</th>
                <th>Client ID</th>
                <th>Total Number of Trees</th>
                <th>Total Due Amount</th>
                <th>Total Paid Amount</th>
                <th>Dates When Work is Done</th>
            </tr>
            <c:forEach var="statistics" items="${listStatistics}">
                <tr style="text-align:center">
                    <td><c:out value="${statistics.getEmail()}" /></td>
                    <td><c:out value="${statistics.getClientID()}" /></td>
                    <td><c:out value="${statistics.getTotalTrees()}" /></td>
                    <td><c:out value="${statistics.getTotalDueAmount()}" /></td>
                    <td><c:out value="${statistics.getTotalPaidAmount()}" /></td>
                 <td>
                    <c:forEach var="date" items="${statistics.getDatesWorkDone()}">
                        <c:out value="${date}" /><br/>
                    </c:forEach>
                </td>
                </tr>
            </c:forEach>
        </table>
    </div>
	</div>
</body>
</html>