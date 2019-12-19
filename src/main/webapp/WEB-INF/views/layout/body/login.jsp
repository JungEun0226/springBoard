<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${root}/js/login.js"></script>
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
				<form class="form-horizontal" action="/signUpOk.com" method="post" style="font-size: 20px; width: 75%; float: right;">
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
					
					<div class="form-group" style="width: 70%; text-align: center;">
						<!-- 이메일로 아이디 비밀번호 찾기 -->
						<a class="w3-bar-item" href="${root}/signup.com" style="color:black; margin-right: 15px;">회원가입</a>
						<a class="w3-bar-item" href="#" style="color:black; margin-right: 15px;">아이디찾기</a>
						<a class="w3-bar-item" href="#" style="color:black;">비밀번호찾기</a>
						
					</div>
					
				</form>
					
			</div>
		</div>
	</div>
</body>
</html>