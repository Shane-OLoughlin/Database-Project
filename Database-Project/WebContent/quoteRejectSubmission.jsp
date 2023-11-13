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
      <tr style="text-align:center">
         <td>Email: ${quoteRequest.getEmail()}</td>
      </tr>
      <tr>
         <td>Rejection Note: <input name="quoterejectnote" type = text/></td>
     </tr>
     <tr>
		<td><a href="rejected?id=${quoteRequest.getQuoteRequestID()}">Reject!</a></td>
     </tr>
     </table>
     </div>
</body>
</html>
