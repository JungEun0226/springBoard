/**
 * 글쓰기 관련 자바스크립트
 */
$(function(){
	$("#writeButton").click(function(){
		var categorySelect=$("#categoryname").val();
		var title=$("#title").val();
		var content=$("#content").val();
		
		if(categorySelect=="select"){
			alert("카테고리를 선택해주세요");
			return false;
		}
		
		if(title=="" || content==""){
			alert("제목과 내용을 입력해주세요");
			return false;
		}
	});
	
});