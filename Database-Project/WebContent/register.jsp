<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title></head>
<body>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="register">
			<table border="1" cellpadding="5">
				<tr>
					<th>Username: </th>
					<td align="center" colspan="3">
						<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>First Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="firstName" size="45" value="FirstName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Last Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="lastName" size="45" value="LastName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Credit Card Info: </th>
					<td align="center" colspan="3">
						<input type="text" name="creditcardinfo" size="45" value="1234567812345678" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Phone Number: </th>
					<td align="center" colspan="3">
						<input type="text" name="phonenumber" size="45" value="1234567890" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Street Number: </th>
					<td align="center" colspan="3">
						<input type="text" name="adress_street_num" size="45" value="161" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Street: </th>
					<td align="center" colspan="3">
						<input type="text" name="adress_street" size="45" value="street" onfocus="this.value=''">
					</td>
				</tr>
								<tr>
					<th>City: </th>
					<td align="center" colspan="3">
						<input type="text" name="adress_city" size="45" value="Detroit" onfocus="this.value=''">
					</td>
				</tr>
											<tr>
					<th>State: </th>
					<td align="center" colspan="3">
						<input type="text" name="adress_state" size="45" value="MI" onfocus="this.value=''">
					</td>
				</tr>
											<tr>
					<th>Zip Code: </th>
					<td align="center" colspan="3">
						<input type="text" name="adress_zip_code" size="45" value="18782" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password: </th>
					<td align="center" colspan="3"> 
						<input type="password" name="password" size="45" value="password" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password Confirmation: </th>
					<td align="center" colspan="3">
						<input type="password" name="confirmation" size="45" value="password" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Register"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>