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
</head>
<body>
	<div class="w3-main" style="margin-left: 250px">
		<div class="w3-row" style="margin-top: 100px;">
			<div class="w3-twothird w3-container col-3">
				<h2>${key}</h2>
				
				<table class="table" style="font-size: 20px;">
					<thead>
						<tr>
							<th><input type="checkbox" name="selectAll" value="selectAll"></th>
							<th>글번호</th>
							<c:if test="${key eq '내 글 관리'}">
								<th>글제목</th>
								<th>작성일</th>
								<th>글내용</th>
							</c:if>
							
							<c:if test="${key eq '내 댓글 관리'}">
								<th>댓글번호</th>
								<th>작성일</th>
								<th>댓글내용</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						
						<c:if test="${key eq '내 글 관리'}">
							<c:forEach var="listDto" items="${list}">
								<tr>
								<td><input type="checkbox"></td>	
								<td>${listDto.writenumber }</td>
								<td>${listDto.title }</td>
								<fmt:formatDate value="${listDto.writedate }" var="writedate" pattern="yyyy-MM-dd HH:mm:ss"/>
								<td>${writedate}</td>
								<td>${listDto.content }</td>
								</tr>
							</c:forEach>
						</c:if>
						
						<c:if test="${key eq '내 댓글 관리'}">
							<c:forEach var="listDto" items="${list}">
								<tr>
								<td><input type="checkbox"></td>	
								<td>${listDto.writenumber }</td>
								<td>${listDto.replynumber }</td>
								<fmt:formatDate value="${listDto.replydate }" var="replyDate" pattern="yyyy-MM-dd HH:mm:ss"/>
								<td>${replyDate}</td>
								<td>${listDto.replycontent }</td>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
				
				<p><button type="button" style="float: right; margin: 25px 0px;">전체 삭제</button></p>
				<p><button type="button" style="float: right; margin: 25px 20px;">선택 삭제</button></p>
				
				<!-- 페이지기능 -->
				<div class="w3-center w3-padding-32">
					<div class="w3-bar">
						<c:if test="${count>0}">
							<fmt:parseNumber var="pageCount" value="${count/boardSize+(count%boardSize==0? 0:1)}" integerOnly="true" />
							<c:set var="pageBlock" value="${10}" />
		
							<fmt:parseNumber var="rs" value="${(pageNumber-1)/pageBlock}" integerOnly="true" />
							<c:set var="startPage" value="${rs*pageBlock+1}" />
							<c:set var="endPage" value="${startPage+pageBlock-1 }" />
		
							<c:if test="${endPage>pageCount }">
								<c:set var="endPage" value="${pageCount}" />
							</c:if>
						</c:if>
		
						<a class="w3-button w3-black" onclick="pagination('${root}',${categorynumber},${1})">««</a>
						<c:if test="${startPage>pageBlock }">
							<a class="w3-button w3-black" onclick="pagination('${root}',${categorynumber},${startPage-pageBlock})">«</a>
						</c:if>
		
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${pageNumber==i}">
									<a class="w3-button w3-black" style="background-color: gray; color: white;" onclick="pagination('${root}',${categorynumber},${i})">${i}</a>
								</c:when>
								<c:otherwise>
									<a class="w3-button w3-black" onclick="pagination('${root}',${categorynumber},${i})">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
		
						<c:if test="${endPage<pageCount }">
							<a class="w3-button w3-black" onclick="pagination('${root}',${categorynumber},${startPage+pageBlock})">»</a>
						</c:if>
		
						<a class="w3-button w3-black" onclick="pagination('${root}',${categorynumber},${pageCount})">»»</a>
		
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>