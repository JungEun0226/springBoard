<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
</head>
<body>
	<input type="hidden" value="${root}" id="root" />
	<c:set var="dto" value="${dto}"></c:set>
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row">
			<div class="w3-twothird w3-container col-3">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">작성일:</a>
				<!-- 날짜 형태 변환 -->
				<fmt:formatDate value="${dto.writedate }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
				<p style="font-size: 20px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${date}</p>
			</div>
			<div class="w3-twothird w3-container col-9">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">조회수:</a>
				<span style="font-size: 20px;">${dto.views }</span>
			</div>
		</div>
		<div class="w3-row">
			<div class="w3-twothird w3-container col-9">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">제목:</a>
				<p style="font-size: 20px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${dto.title }</p>
			</div>
		</div>
		<div class="w3-row">
			<div class="w3-twothird w3-container col-9">
				<div style="font-size: 20px;">${dto.content}</div>
			</div>
		</div>
		
		<c:if test="${!empty dto.filename}">
			<div class="w3-row">
				<div class="w3-twothird w3-container col-9">
					<p style="font-size: 20px;">첨부파일</p>
					<a href="#" id="file">
						<c:out value="${dto.filename}"></c:out>
					</a>
				</div>
			</div>
		</c:if>
		
		<div class="w3-row">
			<a href="#" class="w3-button w3-black" id="goList">목록으로</a>
			<c:if test="${membernumber==dto.membernumber}">
				<a href="#" class="w3-button w3-black" id="boardWriteUpdate">글 수정</a>
				<a href="#" class="w3-button w3-black" id="boardWriteDelete">글 삭제</a>
			</c:if>
		</div>
	</div>
</body>
</html>