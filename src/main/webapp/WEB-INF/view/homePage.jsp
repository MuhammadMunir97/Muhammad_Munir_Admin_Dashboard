<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
    <h1>Welcome Page <c:out value="${currentUser.firstName}"/> ${currentUser.lastName}</h1>
    <table>
    <thead>
        <tr>
            <th><h3>First Name</h3></th>
            <th><h3>Last Name</h3></th>
           	<th><h3>Email</h3></th>
        </tr>
    </thead>
    <tbody>
        <tr>
        	<td>${currentUser.firstName}</td>
        	<td>${currentUser.lastName}</td>
        	<td>${currentUser.email}</td>
        </tr>
    </tbody>
</table>
    
    
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
</body>
</html>