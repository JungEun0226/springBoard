<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@	taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${root}/css/header/header.css" />
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${root}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${root}/js/header.js"></script>
<script type="text/javascript" src="${root}/js/bodySignUp.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 머리 -->
	<div>
		<tiles:insertAttribute name="header" />
	</div>

 	<!-- 카테고리 -->
	<div>
		<tiles:insertAttribute name="category" />
	</div>
	
 	<!-- 몸통 -->
	<div>
		<tiles:insertAttribute name="body" />
	</div>
	
<%--  	<!-- 발 -->
	<div>
		<tiles:insertAttribute name="footer" />
	</div> --%>
</body>
</html>