<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid">
		<h2>Twinone Management</h2>
	</div>

	<div id=main class="container-fluid">
		<div id=form class="container-fluid">
			<h3>Add a Twinone</h3>
			<%-- <form:form action='/store/twinone/new' method='post' modelAttribute='twinone'> --%>
			<form:form action='/twinone/new' method='post' modelAttribute='twinone'>
			
				<!-- begin: JRF trying to tie user to twinone -->
				<%-- <form:hidden value="${user.id}" path="userMdl" /> --%>
				<!-- JRF NOTE: just put line above into the controller too, so that it's set using the session there, and elim another goofy unnecessary hidden form line -->
				<!-- end: JRF trying to tie user to twinone -->
				
				<div class="form-group">
					<form:label path="twinoneName" for="twinoneName">twinoneName</form:label>
					<form:input type="text" class="form-control" path="twinoneName"/>
					<p class="errorText"><form:errors path="twinoneName"/>
					</p>
				</div>

				<div class="form-group">
					<form:label path="twinoneDesc" for="twinoneDesc">twinoneDesc</form:label>
					<form:textarea type="text" class="form-control" path="twinoneDesc" />
					<p class="errorText"><form:errors path="twinoneDesc" /></p>
				</div> 				

				<div class="form-group">
					<form:label path="twinoneDate" for="twinoneDate">twinoneDate</form:label>
					<form:input type="date" class="form-control" path="twinoneDate" />
					<p class="errorText"><form:errors path="twinoneDate" /></p>
				</div>
				
				<div class="form-group">
					<form:label path="twinoneFloat" for="twinoneFloat">twinoneFloat</form:label>
					<form:input type="number" class="form-control" path="twinoneFloat" min="0.00" step="0.01" value="0.00"/>
					<p class="errorText"><form:errors path="twinoneFloat" /></p>
				</div>
				
				<div class="form-group">
					<form:label path="twinoneInt" for="twinoneInt">twinoneInt</form:label>
					<form:input type="number" class="form-control" path="twinoneInt" min="0" step="1" value="0"/>
					<p class="errorText"><form:errors path="twinoneInt" /></p>
				</div>
				
				<div class="form-group">
					<form:label path="twinoneLookup" for="twinoneLookup">twinoneLookup:</form:label>
					<form:select path="twinoneLookup">
						<form:option value="A" path="twinoneLookup">A</form:option>
						<form:option value="B" path="twinoneLookup">B</form:option>
						<form:option value="C" path="twinoneLookup">C</form:option>
					</form:select>
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