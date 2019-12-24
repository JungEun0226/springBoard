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
		<div class="w3-row" style="margin-top: 100px;">
			<div class="w3-twothird w3-container col-3">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">작성일:</a>
				<!-- 날짜 형태 변환 -->
				<fmt:formatDate value="${dto.writedate }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
				<span style="font-size: 3rem;">${date}</span>
			
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none; margin-left: 15px;">조회수:</a>
				<span style="font-size: 3rem;">${dto.views }</span>
			</div>
		</div>
		<div class="w3-row" style="margin-bottom: 25px;">
			<div class="w3-twothird w3-container col-9">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">제목:</a>
				<span style="font-size: 3rem;">${dto.title }</span>
			</div>
		</div>
		<div class="w3-row">
			<div class="w3-twothird w3-container col-9">
				<div style="font-size: 20px; font-weight: lighter;">${dto.content}</div>
			</div>
		</div>
		
		<c:if test="${!empty dto.filename}">
			<div class="w3-row" style="margin-top: 30px;">
				<div class="w3-twothird w3-container col-9">
					<p style="font-size: 20px; display: inline; margin-right: 10px;">첨부파일:</p>
					<a href="#" id="file" style="font-size: 20px; color: darkcyan;">
						<c:out value="${dto.filename}"></c:out>
					</a>
				</div>
			</div>
		</c:if>
		
		<div class="w3-row" style="margin-top: 50px; margin-bottom: 30px; font-size: 20px; padding-left: 15px;">
			<a href="${root}/main.com?categorynumber=${dto.categorynumber}" class="w3-button" style="border-style: double; border-color: darkgrey;" id="goList">목록으로</a>
			<c:if test="${membernumber==dto.membernumber}">
				<a href="${root}/updateWrite.com?writenumber=${dto.writenumber}" class="w3-button" style="border-style: double; border-color: darkgrey;" id="boardWriteUpdate">글수정</a>
				<a href="${root}/deleteWrite.com?writenumber=${dto.writenumber}" class="w3-button" style="border-style: double; border-color: darkgrey;" id="boardWriteDelete">글삭제</a>
			</c:if>
		</div>
	</div>
</body>
</html>