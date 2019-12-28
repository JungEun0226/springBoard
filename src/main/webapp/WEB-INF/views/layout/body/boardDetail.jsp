<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${root}/js/detail.js"></script>
</head>
<body>
	<input type="hidden" value="${root}" id="root" />
	<c:if test="${membernumber!=null}">
		<input type="hidden" value="${membernumber}" id="membernumber"/>	
	</c:if>
	
	<c:set var="boardDto" value="${boardDto}"/>
	<div class="w3-main" style="margin-left: 250px; display: block;" id="detailForm">
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
				<a onclick="update()" class="w3-button" style="border-style: double; border-color: darkgrey;" id="boardWriteUpdate">글수정</a>
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
				<div class="w3-row" style="margin: 20px 0px;">
					<div class="w3-twothird w3-container col-9" style="display: inline-flex;">
						<input type="text" class="form-control" id="reply" style="font-size: 20px; margin-right: 30px;">
						<input id="replyButton" type="button" value="댓글등록" class="btn btn-primary" style="position:relative; top:-3px; font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;"/>
					</div>
				</div>
				
				<!-- 댓글리스트 -->
				<div class="w3-row" id="newReply"></div>
				
				<!-- replyPagination -->
				<div class="w3-center w3-padding-32" style="display:block;" id="pagination"></div>

			</c:otherwise>
		</c:choose>
		
	</div>
	
	<!-- 글 수정용 폼 display:none 상태였다가 버튼누를시 화면 체인지 -->
	<div class="w3-main" style="margin-left: 250px; display: none;" id="updateDetailForm">
		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container" style="width: 88%;">
				<h2 class="text-center">글수정</h2>
				<form class="form-horizontal" action="${root}/boardWriteOk.com" method="post" style="font-size: 20px !important;" enctype="multipart/form-data">
					<input type="hidden" value="${boardDto.writenumber}" id="writenumber"/>
					<div class="form-group">
						<label class="control-label col-sm-2" for="categoryname">카테고리선택:</label>
						<div class="col-sm-3">
							<select class="form-control" name="categoryname" id="categoryname" style="font-size: 20px;" >
								<option value="select">카테고리선택</option>
								<option value="java">자바</option>
								<option value="languageC">C언어</option>
								<option value="javascript">자바스크립트</option>
								<option value="html">HTML/CSS</option>
								<option value="python">파이썬</option>
								<option value="qna">질문게시판</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">제목:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title" name="title" value="${boardDto.title }" style="font-size: 20px;">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">내용:</label>
						<textarea class="form-control col-sm-10" rows="7" id="content" name="content" style="width: 81%; margin-left: 15px; font-size: 20px;">${boardDto.content}</textarea>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="file">파일변경:</label>
						<c:if test="${!empty boardDto.filename}">
							<label class="control-label col-sm-3" for="file" style="text-align: left;">${boardDto.filename}</label>
						</c:if>
						<input type="file" name="file" style="padding-left: 15px;"/>
					</div>
					<div class="form-group" style="text-align: end;">
						<div class="col-sm-offset-2 col-sm-10">
							<input id="writeUpdateButton" type="submit" value="수정" class="btn btn-primary" style="font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;"/>
						</div>
					</div>
				</form>


			</div>
		</div>
	</div>
</body>
</html>