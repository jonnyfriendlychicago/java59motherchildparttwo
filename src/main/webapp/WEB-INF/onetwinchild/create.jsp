<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	
	<div id=pageHeader class="container-fluid">
		<h2>Store Management</h2>
	</div>

	<div id=main class="container-fluid">
		<div id=form class="container-fluid">
			<h3>Add a Onetwinchild</h3>
			<%-- <form:form action='/store/onetwinchild/new' method='post' modelAttribute='onetwinchild'> --%>
			<form:form action='/onetwinchild/new' method='post' modelAttribute='onetwinchild'>
			
				<!-- begin: JRF trying to tie user to onetwinchild -->
				<%-- <form:hidden value="${user.id}" path="userMdl" /> --%>
				<!-- JRF NOTE: just put line above into the controller too, so that it's set using the session there, and elim another goofy unnecessary hidden form line -->
				<!-- end: JRF trying to tie user to onetwinchild -->
				
				<div class="form-group">
					<form:label path="onetwinchildName" for="onetwinchildName">onetwinchildName</form:label>
					<form:input type="text" class="form-control" path="onetwinchildName"/>
					<p class="errorText"><form:errors path="onetwinchildName"/>
					</p>
				</div>

				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
<%-- 			
			<c:choose>
				<c:when test="${onErrorPath == 'yep' }">
					<a href= "/publication"><button class="btn btn-secondary">Cancel</button></a>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose> 			
 --%>		
 </div>

	
	</div>
	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
	<jsp:include page="/WEB-INF/include/footer.jsp"/>
	
</body>
</html>