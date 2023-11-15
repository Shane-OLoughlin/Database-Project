<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client View</title>
</head>
<body>
<center><h1>Welcome! You have been successfully logged in</h1> </center>
	<div align="center">
	 	<a href=logout>Log Out</a>
		<form action="submitquoterequest">
			<table border="1" cellpadding="5">
			<caption><h2>Quote Request Submission (Add trees after Submitting, in List of Quote Requests)</h2></caption>
				<tr>
					<th>Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="quotenote" size="45" value = "note" onfocus="this.value=''">
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
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Requests</h2></caption>
            <tr>
                <th>Quote Request ID</th>
                <th>Quote Note</th>
                <th>Add Tree</th>
            </tr>
            <c:forEach var="quoterequests" items="${listQuoteRequest}">
                <tr style="text-align:center">
                    <td><c:out value="${quoterequests.getQuoteRequestID()}" /></td>
                    <td><c:out value="${quoterequests.getQuoteNote()}" /></td>
                    <td><a href=addtree?id="${quoterequests.getQuoteRequestID()}">Add Tree</a></td>
            </c:forEach>
        </table>
	</div>
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
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quote Rejects</h2></caption>
            <tr>
                <th>Quote Reject ID</th>
                <th>Quote Reject Note</th>
                <th>Quote Request ID</th>
            </tr>
            <c:forEach var="quoterejects" items="${listQuoteReject}">
                <tr style="text-align:center">
                    <td><c:out value="${quoterejects.getQuoteRejectID()}" /></td>
                    <td><c:out value="${quoterejects.getQuoteRejectNote()}" /></td>
                    <td><c:out value="${quoterejects.getQuoteRequestID()}" /></td>
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
                <th>Accept</th>
            </tr>
            <c:forEach var="quoteresponses" items="${listQuoteResponse}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteresponses.getQuoteResponseID()}" /></td>
                    <td><c:out value="${quoteresponses.getInitialPrice()}" /></td>
                    <td><c:out value="${quoteresponses.getTimeWindow()}" /></td>
                    <td><c:out value="${quoteresponses.getQuoteRequestID()}" /></td>
                    <td><a href=accept?id="${quoteresponses.getQuoteResponseID()}">Accept</a></td>
            </c:forEach>
        </table>
	</div>
  	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Order of Works</h2></caption>
            <tr>
                <th>Order of Work ID</th>
                <th>Order of Work Note</th>
                <th>Quote Response ID</th>
            </tr>
            <c:forEach var="orderofworks" items="${listOrderOfWork}">
                <tr style="text-align:center">
                    <td><c:out value="${orderofworks.getOrderOfWorkID()}" /></td>
                    <td><c:out value="${orderofworks.getOrderOfWorkNote()}" /></td>
                    <td><c:out value="${orderofworks.getQuoteResponseID()}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Bill Requests</h2></caption>
            <tr>
                <th>Bill Request ID</th>
                <th>Bill Note</th>
                <th>Bill Amount</th>
                <th>Order of Work ID</th>
                <th>Reject</th>
                <th>Pay</th>
            </tr>
            <c:forEach var="billrequests" items="${listBillRequest}">
                <tr style="text-align:center">
                    <td><c:out value="${billrequests.getBillRequestID()}" /></td>
                    <td><c:out value="${billrequests.getBillNote()}" /></td>
                    <td><c:out value="${billrequests.getBillAmount()}" /></td>
                    <td><c:out value="${billrequests.getOrderOfWorkID()}" /></td>
                    <td><a href=rejectbill?id="${billrequests.getBillRequestID()}">Reject</a></td>
                    <td><a href=pay?id="${billrequests.getBillRequestID()}">Pay</a></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Bill Rejects</h2></caption>
            <tr>
                <th>Bill Reject ID</th>
                <th>Bill Reject Note</th>
                <th>Bill Request ID</th>
            </tr>
            <c:forEach var="billrejects" items="${listBillReject}">
                <tr style="text-align:center">
                    <td><c:out value="${billrejects.getBillRejectID()}" /></td>
                    <td><c:out value="${billrejects.getBillRejectNote()}" /></td>
                    <td><c:out value="${billrejects.getBillRequestID()}" /></td>
            </c:forEach>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Payments</h2></caption>
            <tr>
                <th>Payment ID</th>
                <th>Payment Amount</th>
                <th>Bill Request ID</th>
            </tr>
            <c:forEach var="reportofrevenues" items="${listReportOfRevenue}">
                <tr style="text-align:center">
                    <td><c:out value="${reportofrevenues.getReportOfRevenueID()}" /></td>
                    <td><c:out value="${reportofrevenues.getPaymentAmount()}" /></td>
                    <td><c:out value="${reportofrevenues.getBillRequestID()}" /></td>
            </c:forEach>
        </table>
	</div>
	</body>
</html>