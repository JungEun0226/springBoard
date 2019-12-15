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
.form-control{
	font-size: 18px;
}
</style>
</head>
<body>
	<!-- 로그인시에만 글쓰기 가능 -->
	<!-- 아이디, 제목, 내용, 등록/취소버튼, 카테고리선택, 파일첨부 필요-->
	<!-- 제목,내용 빈칸확인, 카테고리확인, 등록,취소 알림 -->
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container" style="width: 88%;">
				<h2 class="text-center">글쓰기</h2>
				<form class="form-horizontal" action="/action_page.php" style="font-size: 20px;">
					<div class="form-group">
						<label class="control-label col-sm-2" for="categorySelect">카테고리선택:</label>
						<div class="col-sm-3">
							<select class="form-control" name="categorySelect">
								<option value="select">카테고리선택</option>
								<option value="java">자바</option>
								<option value="languageC">C언어</option>
								<option value="javascript">자바스크립트</option>
								<option value="html">HTML/CSS</option>
								<option value="python">파이썬</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">제목:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="content">내용:</label>
						<textarea class="form-control col-sm-10" rows="7" id="content" style="width: 79.5%; margin-left: 15px;"></textarea>
					</div>
					<div class="form-group" style="text-align: end;">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-primary" style="font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;">등록</button>
						</div>
					</div>
				</form>


			</div>
		</div>
	</div>

</body>
</html>