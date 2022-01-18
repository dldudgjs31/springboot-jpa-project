<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	글 수정
	<form>
		<input type="hidden" id="id" value="${board.id}"/>
		<div class="form-group">
			<label for="username">title:</label> 
			<input type="text" value="${board.title}" name="title" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<label for="content">content:</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>

	</form>
		<button id="update" class="btn btn-primary">글 수정 완료</button>
</div>
<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="${pageContext.request.contextPath}/js/board/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
