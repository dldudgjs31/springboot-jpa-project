<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
	<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="username">username:</label> <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="email">email:</label> <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
		</div>
		<c:if test="${empty principal.user.loginType}">
		<div class="form-group">
			<label for="password">password :</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		</c:if>
	</form>
		<button class="btn btn-primary" id="btn-update">회원정보 수정 완료</button>
</div>
<script src="${pageContext.request.contextPath}/js/user/user.js"></script>
<%@ include file="../layout/footer.jsp"%>