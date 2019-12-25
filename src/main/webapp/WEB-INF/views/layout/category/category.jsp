<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그래밍</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
</head>
<body>
<input type="hidden" value="${root}" id="root"/> 
<!-- Sidebar -->
<nav class="w3-sidebar w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left" id="mySidebar">
	<a href="javascript:void(0)" onclick="w3_close()" class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu">
		<i class="fa fa-remove"></i>
	</a>
	<c:choose>
		<c:when test="${membernumber==null }">
			<a class="w3-bar-item w3-button w3-hover-black" href="${root}/login.com">로그인</a>
			<a class="w3-bar-item w3-button w3-hover-black" href="${root}/signup.com">회원가입</a>
		</c:when>
		<c:otherwise>
			<p class="w3-bar-item" style="margin-top:5px; ">${memberid}님 환영합니다!</p>
			<a class="w3-bar-item w3-button w3-hover-black" href="${root}/logout.com">로그아웃</a>
			<a class="w3-bar-item w3-button w3-hover-black" href="#">마이페이지</a>
			<a class="w3-bar-item w3-button w3-hover-black" href="${root}/boardWrite.com">글쓰기</a>
		</c:otherwise>
	</c:choose>
	<hr>
	<h4 class="w3-bar-item"><b>Category</b></h4>
	
	<input type="hidden" id="categorynumber" value="${categorynumber}"/>
	<a class="w3-bar-item w3-button w3-hover-black" href="${root}/main.com" id="listAll">전체글보기</a>
	<a class="w3-bar-item w3-button w3-hover-black" href="${root}/main.com?categorynumber=1" id="java">자바</a>
	<a class="w3-bar-item w3-button w3-hover-black" href="${root}/main.com?categorynumber=2" id="languageC">C언어</a>
	<a class="w3-bar-item w3-button w3-hover-black" href="${root}/main.com?categorynumber=3" id="javascript">자바스크립트</a>
	<a class="w3-bar-item w3-button w3-hover-black" href="${root}/main.com?categorynumber=4" id="html">HTML/CSS</a>
	<a class="w3-bar-item w3-button w3-hover-black" href="${root}/main.com?categorynumber=5" id="python">파이썬</a>
	<hr>
	<a class="w3-bar-item w3-button w3-hover-black" href="${root}/main.com?categorynumber=6" id="qna">질문게시판</a>
</nav>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "none";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}

</script>
</body>
</html>





