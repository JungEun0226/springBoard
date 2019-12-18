/**
 * 회원가입 관련 자바스크립트 중복체크, 빈칸체크, 데이터넘겨주기 해야함
 */
$(document).ready(function() {
	$("#memberIDCheck").click(function() {
		var memberid=$("#memberID").val();
		alert(memberid);
		
		/*$.get("/spring/memberidcheck.com?memberid=", memberid, function(data){
			alert(data);
		});*/
		
	});
});