<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid">
		<h2>Twinone Management</h2>
	</div>
	
	<div id=main class="card">
<%-- 
		<div id="recordHeader"> 
		<a href= "/onetwinchild/${onetwinchild.id}/editrecord"><button class="btn btn-primary">Edit</button></a>
		</div>
--%>
		<div id=about class="card">
			
			<%-- <form:form action='/onetwinchild/${onetwinchild.id}/edit' method='post' modelAttribute='onetwinchild'> --%>
			<form:form action='/onetwinchild/edit' method='post' modelAttribute='onetwinchild'>
				<!-- JRF note: above is clean, replaced by hidden input below.  
				and below is now embacing the modelAttribute/path approach.  -->
				<form:input type="hidden"  path="id" />
				
				<%-- <form:input type="hidden" value="${onetwinchild.userMdl.id}" path="userMdl" /> --%>
				<%-- <form:hidden value="${onetwinchild.userMdl.id}" path="userMdl" /> --%>  
				<!-- JRF: WHACK FOREVER MORE THIS SILLY HIDDEN INPUT FROM ALL EDIT FORMS; CREATEDBY IS BEING MAINTAINED BY LINE IN THE CONTROLLER;  -->
		
				<div class="form-group">
					<form:label path="onetwinchildName" for="onetwinchildName">onetwinchildName</form:label>
					<form:input type="text" class="form-control" path="onetwinchildName"/>
					<p class="errorText"><form:errors path="onetwinchildName" />
					</p>
				</div>
				
 	
				<%-- <div class="form-group">
					<p>
						<form:label path="dojoMdl" for="dojoMdl">Dojo:</form:label>
					</p>
					<form:select path="dojoMdl" name="dojoMdl">
	 					<c:forEach var="record" items="${dojoList}">
							<c:choose>
								<c:when test="${publication.dojoMdl.id == record.id}">
									<option value="${record.id}" selected>
										<c:out value="${record.dojoName}"/>
									</Option>
								</c:when>
								<c:otherwise>
									<option value="${record.id}">
										<c:out value="${record.dojoName}" />
									</Option>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
					</form:select>
				</div> --%>
	 
				<div class="form-group">
					<form:label path="onetwinchildDesc" for="onetwinchildDesc">onetwinchildDesc</form:label>
					<form:textarea type="text" class="form-control" path="onetwinchildDesc" />
					<p class="errorText"><form:errors path="onetwinchildDesc" /></p>
				</div> 	
				
				<div class="form-group">
					<form:label path="onetwinchildDate" for="onetwinchildDate">onetwinchildDate</form:label>
					<form:input type="date" class="form-control" path="onetwinchildDate" />
					<p class="errorText"><form:errors path="onetwinchildDate" /></p>
				</div>
				
				<div class="form-group">
					<form:label path="onetwinchildFloat" for="onetwinchildFloat">onetwinchildFloat</form:label>
					<form:input type="number" class="form-control" path="onetwinchildFloat" min="0.00" step="0.01"/>
					<p class="errorText"><form:errors path="onetwinchildFloat" /></p>
				</div>
				
				<div class="form-group">
					<form:label path="onetwinchildInt" for="onetwinchildInt">onetwinchildInt</form:label>
					<form:input type="number" class="form-control" path="onetwinchildInt" min="0" step="1"/>
					<p class="errorText"><form:errors path="onetwinchildInt" /></p>
				</div>
				
				<div class="form-group">
					<form:label path="onetwinchildLookup" for="onetwinchildLookup">onetwinchildLookup</form:label>
					<form:select path="onetwinchildLookup">
						
						
						<form:option value="A" path="onetwinchildLookup">A</form:option>
						<form:option value="B" path="onetwinchildLookup">B</form:option>
						<form:option value="C" path="onetwinchildLookup">C</form:option>
					
					
					</form:select>
				</div>
				
				<div>
					<button type="submit" class="btn btn-primary">Update</button>
				</div>
			</form:form>
	
			<a href="/onetwinchild/${onetwinchild.id}"><button class="btn btn-secondary">Cancel</button></a>
			
		</div>
		
		<div id=list class="card">
			<h3>Twintwo List</h3>

			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">twintwoName</th>
						<th scope="col">actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${assignedCategories}">
						<tr>
							<td>${record.id}</td>
							<td><a href="/twintwo/${record.id}">${record.twintwoName}</a></td>
 								
							<td>
								<form action="/removeTwinoneTwintwoJoin" method="post">
								    
								    <input type="hidden" name="_method" value="delete">
								    <input type="hidden" name="twintwoId" value="${record.id}"/>
								    <input type="hidden" name="onetwinchildId" value="${onetwinchild.id}"/>
								    <!-- <input type="hidden" name="origin" value="1"/> -->
								    
								    <button class="btn btn-danger">Delete</button>
								</form>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<div id=form class="card">
			<h2>Add a Twintwo:</h2>
			<form action='/onetwinchild/${onetwinchild.id}/editTwintwoJoins' method='post' >
				<div class="form-group">
					<select name="twintwoId"> 
						<c:forEach var="record" items="${unassignedCategories}">
							<option value="${record.id}" >
								<c:out value="${record.twintwoName}" />
							</option>
						</c:forEach>
					</select>
				</div>
 				
 				<button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
	</div>
 	
 	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>