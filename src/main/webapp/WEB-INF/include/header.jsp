<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<div id=header class="container-fluid">
			<h1 id="siteTitle">java59motherchildparttwo</h1>
			<div class=header-top-vert>
				<p class="header-profile-text">${user.userName}</p>
				<p class="header-profile-text">Email: ${user.email}</p>
				<a href="/logout">Logout</a>
			</div>
	</div>
	
	<div id=navBar class="container-fluid">
		
					<a href= "/home">Home</a>
					
			
	</div>