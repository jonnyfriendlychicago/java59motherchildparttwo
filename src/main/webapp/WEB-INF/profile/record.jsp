<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid">
		<h2>Twinone Management</h2>
	</div>
	
	<!-- <div id=main class="card"> -->
	<div id=main class="container-fluid">
		<div id="recordHeader"> 
			<h3><c:out value="${userProfile.userName}"></c:out></h3>

<%-- 
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id }">
--%>			
			<%-- <a href= "/twinone/${twinone.id}/edit"><button class="btn btn-primary">Edit</button></a> --%>
<%-- 
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
--%>
		
<%-- 
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id }">
--%>
					
					<%-- <form action="/twinone/${twinone.id}" method="post">
					    <input type="hidden" name="_method" value="delete">
					    <button class="btn btn-danger">Delete this Twinone</button>
					</form> --%>
					
<%-- 
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
--%>

		</div>
		<div id=about class="card">
			<%-- <h4>id: <c:out value="${publication.id}"></c:out></h4> --%>
			
			<p>Email:<c:out value="${userProfile.email}"></c:out></p>
			<p>userID:<c:out value="${userProfile.id}"></c:out></p>
			

		</div>
		
		
		


	</div>
 
 	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>