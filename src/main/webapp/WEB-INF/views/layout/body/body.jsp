<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${root}/js/category.js"></script>
<style type="text/css">
.w3-twothird {
	width: 100%;
}
</style>
</head>
<body>
	<input type="hidden" value="${categorynumber}" id="categorynumber" />
	<input type="hidden" value="${root}" id="root" />
	<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row w3-padding-64" style="padding-bottom: 35px !important;">
			<div class="w3-twothird w3-container">
				<h1 class="w3-text-teal" id="mainTitle"></h1>
			</div>
		</div>
		
		<c:if test="${empty list }">
			<div class="w3-row">
				<div class="w3-twothird w3-container">
					<p style="font-size: 20px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">작성된 글이 없습니다. 글을 등록해주세요.</p>
				</div>
			</div>
		</c:if>
		<c:forEach var="listDto" items="${list}">
			<div class="w3-row">
				<div class="w3-twothird w3-container">
					<a class="w3-text-teal" onclick="detailBoard(${listDto.writenumber})" style="font-size: 3rem; text-decoration: none;">${listDto.title}</a>
					<p style="font-size: 20px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${listDto.content}</p>
					<input type="hidden" value="${listDto.writenumber}" id="writenumber" />
				</div>
			</div>
		</c:forEach>

		<!-- Pagination -->
		<div class="w3-center w3-padding-32">
			<div class="w3-bar">
				<c:if test="${count>0}">
					<fmt:parseNumber var="pageCount" value="${count/boardSize+(count%boardSize==0? 0:1)}" integerOnly="true" />
					<c:set var="pageBlock" value="${10}" />

					<fmt:parseNumber var="rs" value="${(pageNumber-1)/pageBlock}" integerOnly="true" />
					<c:set var="startPage" value="${rs*pageBlock+1}" />
					<c:set var="endPage" value="${startPage+pageBlock-1 }" />

					<c:if test="${endPage>pageCount }">
						<c:set var="endPage" value="${pageCount}" />
					</c:if>
				</c:if>

				<a class="w3-button w3-black" onclick="pagination(${categorynumber},${1})">««</a>
				<c:if test="${startPage>pageBlock }">
					<a class="w3-button w3-black" onclick="pagination(${categorynumber},${startPage-pageBlock})">«</a>
				</c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:choose>
						<c:when test="${pageNumber==i}">
							<a class="w3-button w3-black" style="background-color: gray; color: white;" onclick="pagination(${categorynumber},${i})">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="w3-button w3-black" onclick="pagination(${categorynumber},${i})">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${endPage<pageCount }">
					<a class="w3-button w3-black" onclick="pagination(${categorynumber},${startPage+pageBlock})">»</a>
				</c:if>

				<a class="w3-button w3-black" onclick="pagination(${categorynumber},${pageCount})">»»</a>

			</div>
		</div>
	</div>
</body>
</html>





