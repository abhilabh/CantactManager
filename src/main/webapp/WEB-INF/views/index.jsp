<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>contact manager home</title>
</head>
<body>
<div align="center">
<h1> Contact list</h1>
	<h3><a href="newContact">New contact</a></h3>
   <table border="1" cellpadding="5">
   		<tr>
   			<th>No</th>
   			<th>Name</th>
   			<th>Email</th>
   			<th>Address</th>
   			<th>Phone</th>
   			<th>Action</th>
   		</tr>
   		
   		<c:forEach items="${listContact}" var="contact" varStatus="status">
   		  <tr>
   			<td>${status.index+1}</td>
   			<td>${contact.name}</td>
   			<td>${contact.email}</td>
   			<td>${contact.address}</td>
   			<td>${contact.telephone}</td>
   			<td>
   				<a href="edit?id=${contact.id }">Edit</a>
   				<a href="delete?id=${contact.id }">Delete</a>
   			</td>
   		  </tr>
   		</c:forEach>
   </table>
</div>


</body>
</html>