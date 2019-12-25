<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${root}/js/boardDetail.js"></script>
</head>
<body>
	<input type="hidden" value="${root}" id="root" />
	<c:if test="${membernumber!=null}">
		<input type="hidden" value="${membernumber}" id="membernumber"/>	
	</c:if>
	
	<c:set var="boardDto" value="${boardDto}"></c:set>
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row" style="margin-top: 100px;">
			<div class="w3-twothird w3-container col-3">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">작성일:</a>
				<!-- 날짜 형태 변환 -->
				<fmt:formatDate value="${boardDto.writedate }" var="writeDate" pattern="yyyy-MM-dd HH:mm:ss"/>
				<span style="font-size: 3rem;">${writeDate}</span>
			
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none; margin-left: 15px;">조회수:</a>
				<span style="font-size: 3rem;">${boardDto.views }</span>
			</div>
		</div>
		<div class="w3-row" style="margin-bottom: 25px;">
			<div class="w3-twothird w3-container col-9">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">제목:</a>
				<span style="font-size: 3rem;">${boardDto.title }</span>
			</div>
		</div>
		<div class="w3-row">
			<div class="w3-twothird w3-container col-9">
				<div style="font-size: 20px; font-weight: lighter;">${boardDto.content}</div>
			</div>
		</div>
		
		<c:if test="${!empty boardDto.filename}">
			<div class="w3-row" style="margin-top: 30px;">
				<div class="w3-twothird w3-container col-9">
					<p style="font-size: 20px; display: inline; margin-right: 10px;">첨부파일:</p>
					<a href="${root}/downloadFile.com?downFilePath=${downFilePath}" id="file" style="font-size: 20px; color: darkcyan;">
						<c:out value="${boardDto.filename}"></c:out>
					</a>
				</div>
			</div>
		</c:if>
		
		<div class="w3-row" style="margin-top: 50px; margin-bottom: 30px; font-size: 20px; padding-left: 15px;">
			<a href="${root}/main.com?categorynumber=${boardDto.categorynumber}" class="w3-button" style="border-style: double; border-color: darkgrey;" id="goList">목록으로</a>
			<c:if test="${membernumber==boardDto.membernumber}">
				<a href="${root}/updateWrite.com?writenumber=${boardDto.writenumber}" class="w3-button" style="border-style: double; border-color: darkgrey;" id="boardWriteUpdate">글수정</a>
				<a href="${root}/deleteWrite.com?writenumber=${boardDto.writenumber}" class="w3-button" style="border-style: double; border-color: darkgrey;" id="boardWriteDelete">글삭제</a>
			</c:if>
		</div>
		<hr>
		
		<!-- 댓글 로그인한 사람에게만 허용-->
		<c:choose>
			<c:when test="${membernumber==null}">
				<div class="w3-row">
					<div class="w3-twothird w3-container col-9">
						<div style="font-size: 20px; font-weight: lighter;">댓글을 달려면 로그인을 해주세요</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="w3-row">
					<div class="w3-twothird w3-container col-9" style="display: inline-flex;">
						<input type="text" class="form-control" id="reply" style="font-size: 20px; margin-right: 30px;">
						<input id="replyButton" type="button" value="댓글등록" class="btn btn-primary" style="position:relative; top:-3px; font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;"/>
					</div>
				</div>
				
				<div class="w3-row" id="newReply"></div>
				
				<c:forEach var="replyDto" items="${list}">
					<hr>
					<div class="w3-row">
						<div class="w3-twothird w3-container col-2" style="margin-bottom: 10px;">
							<span style="font-size: 20px; margin-right: 30px;">${replyDto.memberid }</span>
							<span style="font-size: 20px; margin-right: 15px;">작성일 : </span>
							<fmt:formatDate value="${replyDto.replydate }" var="replyDate" pattern="yyyy-MM-dd HH:mm:ss"/>
							<span style="font-size: 20px; margin-right: 15px;">${replyDate}</span>
							
							<c:if test="${membernumber==replyDto.membernumber}">
								<span style="font-size: 20px; margin-right: 15px;">|| 수정</span>
								<span style="font-size: 20px;">|| 삭제</span>
							</c:if>
						</div>
						<div class="w3-twothird w3-container col-9" style="margin-bottom: 10px;">
							<p style="font-size: 20px; margin-right: 30px;">${replyDto.replycontent }</p>
							<div style="display: inline-flex;">
								<input type="text" class="form-control" id="replyUpdate" style="font-size: 20px; margin-right: 30px; display: none;" value="${replyDto.replycontent}">
								<input id="replyButton" type="button" value="댓글수정" class="btn btn-primary" style="position:relative; top:-3px; font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey; display: none;"/>
							</div>
						</div>
					</div>
					<hr>
				</c:forEach>
				
				<!-- replyPagination -->
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
		
						<a class="w3-button w3-black" onclick="replyPagination(${boardDto.writenumber},${1},'${root}')">««</a>
						<c:if test="${startPage>pageBlock }">
							<a class="w3-button w3-black" onclick="replyPagination(${boardDto.writenumber},${startPage-pageBlock},'${root}')">«</a>
						</c:if>
		
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${pageNumber==i}">
									<a class="w3-button w3-black" style="background-color: gray; color: white;" onclick="replyPagination(${boardDto.writenumber},${i},'${root}')">${i}</a>
								</c:when>
								<c:otherwise>
									<a class="w3-button w3-black" onclick="replyPagination(${boardDto.writenumber},${i},'${root}')">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
		
						<c:if test="${endPage<pageCount}">
							<a class="w3-button w3-black" onclick="replyPagination(${boardDto.writenumber},${startPage+pageBlock},'${root}')">»</a>
						</c:if>
		
						<a class="w3-button w3-black" onclick="replyPagination(${boardDto.writenumber},${pageCount},'${root}')">»»</a>
					</div>
				</div>
				
				
			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>