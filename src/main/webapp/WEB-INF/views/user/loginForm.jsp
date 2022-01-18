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
	</form>

</div>
<script src="${pageContext.request.contextPath}/js/user/user.js"></script>
<%@ include file="../layout/footer.jsp"%>