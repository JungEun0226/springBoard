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
<style type="text/css">
.form-control{font-size: 18px;}
.control-label{padding-top: 2px !important;}
span button{
	height: 35px !important;
    padding-top: 4px !important;
    font-size: 20px !important;
    color: black !important;
    background-color: #f0f0f0 !important;
    border-color: lightgrey !important;
}
.col-sm-2{width: 20%;}
</style>
</head>
<body>
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container" style="width: 88%;">
				<h2 class="text-center">회원가입</h2>
				<form class="form-horizontal" action="/signUpOk.com" method="post" style="font-size: 20px;">
					<div class="form-group">
						<label class="control-label col-sm-2" for="memberID">아이디:</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="memberID">
						</div>
						<span>
							<button id="memberIDCheck" type="button" class="btn btn-primary" onclick="iDCheck()">아이디 중복 확인</button>
							<input id="memberIDCheckValue" type="hidden" value="false"><!-- 아이디중복확인 트루값일때 회원가입가능 -->
						</span>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="memberPassword">비밀번호:</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="memberPassword">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="memberPasswordCheck">비밀번호 재확인:</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="memberPasswordCheck">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="memberEmail">이메일:</label>
						<div class="col-sm-7">
							<input type="email" class="form-control" id="memberEmail">
						</div>
						<span><button id="emailCheck" type="button" class="btn btn-primary" >가입 여부 확인</button></span>
					</div>
					
					<div class="form-group" style="text-align: center;">
						<div class="col-sm-offset-1 col-sm-10">
							<button id="signUp" type="button" class="btn btn-primary" style="font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;">가입하기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>