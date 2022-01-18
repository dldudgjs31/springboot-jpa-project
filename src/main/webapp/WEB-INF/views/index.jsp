<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>

<div class="container">
	<div class="card m-5" style="width: 100%">
		<c:forEach items="${boards.content}" var="dto">
		<div class="card-body">
			<h4 class="card-title">${dto.title}</h4>
			<p class="card-text">${dto.content}</p>
			<a href="${pageContext.request.contextPath}/board/${dto.id}" class="btn btn-primary">상세보기</a>
		</div>
		</c:forEach>
	</div>

		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}?page=${boards.number -1}">Previous</a></li>
			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}?page=${boards.number + 1}">Next</a></li>
		  </ul>

</div>

<%@ include file="layout/footer.jsp"%>
