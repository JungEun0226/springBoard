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
<script type="text/javascript" src="${root}/js/write.js"></script>
<style type="text/css">
.form-control{
	font-size: 18px;
}
</style>
</head>
<body>
	<!-- 글수정 페이지 -->
	
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container" style="width: 88%;">
				<h2 class="text-center">글쓰기</h2>
				<form class="form-horizontal" action="${root}/boardWriteOk.com" method="post" style="font-size: 20px;" enctype="multipart/form-data">
					<input type="hidden" value="${writenumber}" id="writenumber" name="writenumber"/>
					<div class="form-group">
						<label class="control-label col-sm-2" for="categoryname">카테고리선택:</label>
						<div class="col-sm-3">
							<select class="form-control" name="categoryname" id="categoryname">
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
							<input type="text" class="form-control" id="title" name="title">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">내용:</label>
						<textarea class="form-control col-sm-10" rows="7" id="content" name="content" style="width: 81%; margin-left: 15px;"></textarea>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="file">파일첨부:</label>
						<input type="file" name="file" style="padding-left: 15px;"/>
					</div>
					<div class="form-group" style="text-align: end;">
						<div class="col-sm-offset-2 col-sm-10">
							<input id="writeButton" type="submit" value="수정" class="btn btn-primary" style="font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;"/>
						</div>
					</div>
				</form>


			</div>
		</div>
	</div>

</body>
</html>