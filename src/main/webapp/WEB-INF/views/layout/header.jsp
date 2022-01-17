<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<c:choose>
				
				<c:when test="${empty principal}">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}">메인 페이지</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/auth/loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/auth/joinForm">회원가입</a></li>
				</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}">메인 페이지</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/form">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/form">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
					</ul>
		
				</c:otherwise>
			</c:choose>




		</div>
	</nav>
	<br>