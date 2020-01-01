<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${root}/js/loginJS.js"></script>
<style type="text/css">
button{
	height: 35px !important;
    padding-top: 4px !important;
    font-size: 20px !important;
    color: black !important;
    background-color: #f0f0f0 !important;
    border-color: lightgrey !important;
}

.col-sm-4 {
	margin-top: 3px; 
}

.col-sm-4 input{
	font-size:20px;
}

</style>
</head>
<body>
	<input type="hidden" value="${root}" id="root"/>
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container" style="width: 88%;">
				<h2 class="text-center" style="padding-bottom: 25px;">로그인</h2>
				<form class="form-horizontal" style="font-size: 20px; width: 75%; float: right;">
					<div class="form-group">
						<label class="control-label col-sm-2" for="memberID">아이디:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="memberID">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="memberPassword">비밀번호:</label>
						<div class="col-sm-4">
							<input type="password" class="form-control" id="memberPassword">
						</div>
					</div>
					
					<div class="form-group" style="text-align: center;">
						<div class="col-sm-offset-1 col-sm-10" style="width:50%; padding-top: 20px; padding-bottom: 10px;">
							<button id="login" type="button" class="btn btn-primary">로그인</button>
						</div>
					</div>
					
					<!-- 이메일로 아이디 찾기 -->
					<div class="form-group" style="width: 70%; text-align: center;">
						<a class="w3-bar-item" href="${root}/signup.com" style="color:black; margin-right: 15px;">회원가입</a>
						<span class="w3-bar-item" style="color:black; margin-right: 15px;" onclick="findIdJS()">아이디찾기</span>
						<span class="w3-bar-item" style="color:black;" onclick="findPasswordJS()">비밀번호찾기</span>
					</div>
					
					<div class="form-group" style="width: 70%; text-align: center; display: none;" id="findIdStyleForm">
						<hr>
						<h2 class="text-center" style="padding-bottom: 25px;">아이디 찾기</h2>
						<label class="control-label col-sm-3" for="findId">이메일:</label>
						<div class="col-sm-8" style="display: flex;">
							<input type="text" class="form-control" id="findId">
							<button id="findIdButton" type="button" class="btn btn-primary" style="margin-left: 20px">찾기</button>
						</div>
					</div>
					<!-- 아이디찾기 결과값 -->
					<div id="findIdResult"></div>
					
					
					<!-- 이메일로 비밀번호찾기 -->
					<div class="form-group" style="width: 70%; text-align: center; display: none;" id="findPassStyleForm">
						<hr>
						<h2 class="text-center" style="padding-bottom: 15px;">비밀번호 찾기</h2>
						<label class="control-label col-sm-3">이메일:</label>
						<div class="col-sm-8" style="display: flex;">
							<input type="text" class="form-control" id="findPass">
							<button id="findPassButton" type="button" class="btn btn-primary" style="margin-left: 20px">찾기</button>
						</div>
					</div>
					
					<!-- 비밀번호찾기 결과값 -->
					<div id="findPassResult"></div>
					<div class="form-group" style="width: 70%; text-align: center; display: none;" id="findPassUpdateStyleForm">
						<hr>
						<h2 class="text-center" style="padding-bottom: 15px;">비밀번호 수정</h2>
						<label class="control-label col-sm-3">비밀번호:</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" placeholder="4~20글자로 써주세요">
						</div>
						
						<div class="form-group" style="margin-top: 60px;">
							<label class="control-label col-sm-4" style="margin-left: -48px;">비밀번호 확인:</label>
							<div class="col-sm-8" style="display: flex; width: 62%;">
								<input type="password" class="form-control" id="passwordCheck" placeholder="4~20글자로 써주세요">
								<button id="passwordUpdate" type="button" class="btn btn-primary" style="margin-left: 20px">수정</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>