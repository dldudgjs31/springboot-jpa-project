<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="username">username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="email">email:</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<div class="form-group">
			<label for="password">password :</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
	</form>
		<button class="btn btn-primary" id="btn-save">회원가입 완료</button>
</div>
<script src="${pageContext.request.contextPath}/js/user/user.js"></script>
<%@ include file="../layout/footer.jsp"%>