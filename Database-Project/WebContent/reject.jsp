<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Quote Request Details</h1>
    <div align="center">
     <table>
     <tr style="text-align:center">
         <td>RequestId: ${quoteRequest.getQuoteRequestID()}</td>
      </tr>
      <tr>
         <td>QuoteNote: ${quoteRequest.getQuoteNote()}</td>
      </tr>
      <tr>
         <td>Rejection Note: <input name="rejectionNote" type = text/></td>
     </tr>
     <tr>
		<td><a href="rejected?id=${quoteRequest.getQuoteRequestID()}">Reject!</a></td>
     </tr>
     </table>
     </div>
</body>
</html>
