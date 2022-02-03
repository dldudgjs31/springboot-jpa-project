<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<button class="btn btn-primary" onclick="history.back();">돌아가기</button>
	<c:if test="${principal.user.username==board.user.username}">
		<button id="btn-update" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/board/${board.id}/updateForm'">수정</button>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br>
		<h5>글번호 : <span id="id">${board.id}</span></h5>
		<h5>작성자 : <span>${board.user.username}</span></h5>		
	<br>
	<p> 글쓰기</p>
	<form>
		<div class="form-group">
			<h3>${board.title}</h3>
		</div>
		<hr>
		<div class="form-group">
			<div>
				${board.content}
			</div>
		</div>
		<hr>

	</form>
		<div class="card">
			<form>
			<input type="hidden" id="boardid" value="${board.id}"/>
			<div class="card-body"><textarea id="reply-content" class="form-control" rows="1"></textarea></div>
			<div class="card-footer"> <button type="button" id="btn-reply-save" class="btn btn-primary">등록 </button></div>
			</form>
		</div>
		<br>
		<div class="card">
			<div class="card-header">댓글리스트</div>
			<ul id="reply--box" class="list-group">
			<c:forEach var="reply" items="${board.reply}">
				<li id="reply--1"class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
						<button class="btn btn-danger">삭제</button>
					</div>
				</li>
			</c:forEach>


			  </ul>
		</div>
		<br>

</div>
<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="${pageContext.request.contextPath}/js/board/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
