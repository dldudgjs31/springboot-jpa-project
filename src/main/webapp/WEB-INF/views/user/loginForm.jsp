<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="${pageContext.request.contextPath}/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">username:</label> <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">password :</label> <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=1bba6aafcad7c5f83d3e74b925ceb08a&redirect_uri=http://localhost:8000/blog/auth/kakao/callback&response_type=code"><img height="38px" src="${pageContext.request.contextPath}/image/kakao_login_button.png"> </a>
	</form>
<br>
</div>
<script src="${pageContext.request.contextPath}/js/user/user.js"></script>
<%@ include file="../layout/footer.jsp"%>