<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${root}/js/mypage.js"></script>
<style type="text/css">
input[type=button]{
font-size: 20px; 
color: black; 
background-color: #f0f0f0 !important; 
border-color: lightgrey;
margin-right: 20px;
}
</style>
</head>
<body>
	<input type="hidden" value="${membernumber}" id="membernumber"/>
	<input type="hidden" value="${root}" id="root" />
		
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row" style="margin-top: 100px;">
			<div class="w3-twothird w3-container col-3">
				<a class="w3-text-teal" style="font-size: 3rem; text-decoration: none;">비밀번호 수정</a>
				<div class="form-group" style="margin-top: 40px; display: flex;">
					<label class="control-label col-sm-3" style="font-size: 20px; position: relative; top: 5px;">비밀번호:</label>
					<div class="col-sm-7">
						<input type="password" class="form-control" id="password" placeholder="4~20글자로 써주세요">
					</div>
				</div>
				<div class="form-group" style="margin-top: 20px;">
					<label class="control-label col-sm-3" style="font-size: 20px; position: relative; top: 5px; margin-bottom: 20px;">비밀번호 재확인:</label>
					<div class="col-sm-7">
						<input type="password" class="form-control" id="passwordCheck" placeholder="4~20글자로 써주세요">
					</div>
					<input id="passwordUpdate" type="button" value="수정" class="btn btn-primary" style="position:relative; top:-5px; left:20px;"/>
				</div>
				
			</div>
		</div>
		<hr><hr><hr>
		<div class="w3-row">
			<div class="w3-twothird w3-container col-9">
				<input id="memberDelete" type="button" value="회원탈퇴" class="btn btn-primary"/>
				<input id="myPostsManage" type="button" value="내 글 관리" class="btn btn-primary"/>
				<input id="myReplyManage" type="button" value="내 댓글 관리" class="btn btn-primary"/>
			</div>
		</div>
	</div>
</body>
</html>