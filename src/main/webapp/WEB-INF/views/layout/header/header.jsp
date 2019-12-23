<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
</head>
<body>

<div class="w3-top">
	<div class="w3-bar w3-theme w3-top w3-left-align w3-large">
		<a class="w3-bar-item w3-button w3-right w3-hide-large w3-hover-white w3-large w3-theme-l1" href="javascript:void(0)" onclick="w3_open()"><i class="fa fa-bars"></i></a>
		<a href="${root}/main.com" class="w3-bar-item w3-button w3-theme-l1" >프로그래밍 커뮤니티</a>
	</div>
</div>
</body>
</html>