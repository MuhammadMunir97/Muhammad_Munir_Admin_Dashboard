<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
</head>
<body>
    <h1>Welcome to the Admin Page <c:out value="${currentUser.firstName}" /> ${currentUser.lastName}</h1>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    
    <table>
	    <thead>
	        <tr>
	            <th><h3>First Name</h3></th>
	            <th><h3>Last Name</h3></th>
	            <th><h3>Email</h3></th>
	            <th><h3>Action</h3></th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${users}" var="user">
	        <tr>
	        	<td>${user.firstName}</td>
	        	<td>${user.lastName}</td>
	        	<td>${user.email}</td>
	        	<td>
		        	<c:choose>
					  <c:when test="${fn:length(user.roles) gt 1}">
					    <c:out value="admin" />
					  </c:when>
					  <c:otherwise>
					  <c:forEach items="${user.roles}" var="role">
						  <c:choose>
						  <c:when test="${role.name == 'ROLE_ADMIN'}">
						    <c:out value="Admin" />
						  </c:when>
							<c:otherwise>
							    <form action="/admin/delete" method="post">
							    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								    <input type="hidden" name="_method" value="delete">
								    <input type="hidden" name="user_Id" value="${user.id}">
								    <input type="submit" value="Delete">
								</form>
								<form action="/admin/promote" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								    <input type="hidden" name="_method" value="put">
								    <input type="hidden" name="user_Id" value="${user.id}">
								    <input type="submit" value="Make an Admin">
								</form>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					  </c:otherwise>
					</c:choose>
				</td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>
</body>
</html>