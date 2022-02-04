<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
		<c:forEach items="${boards.content}" var="dto">
	<div class="card m-5" style="width: 100%">
		<div class="card-body">
			<h4 class="card-title">${dto.title}</h4>
			<p class="card-text">${dto.content}</p>
			<a href="${pageContext.request.contextPath}/board/${dto.id}" class="btn btn-primary">상세보기</a>
		</div>
	</div>
		</c:forEach>

		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.number == 0 }">
					<li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}?page=${boards.number -1}">Previous</a></li>	
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}?page=${boards.number -1}">Previous</a></li>	
				</c:otherwise>
			</c:choose>
			<c:forEach var="page" begin="${boards.number+1}" end="${boards.number+3}">
				<c:if test="${boards.totalPages >= page}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}?page=${page -1}">${page}</a></li>
				</c:if>

			</c:forEach>
			
			
			<c:choose>
				<c:when test="${boards.number + 1 == boards.totalPages }">
					<li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}?page=${boards.number + 1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}?page=${boards.number + 1}">Next</a></li>
				</c:otherwise>
			</c:choose>
		  </ul>

</div>

<%@ include file="layout/footer.jsp"%>
