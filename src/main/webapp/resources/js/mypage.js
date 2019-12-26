/**
 * 마이페이지 관련 자바스크립트
 */

$(function(){
	var membernumber=$("#membernumber").val();
	var root=$("#root").val();
	
	
	//비밀번호수정
	$("#passwordUpdate").click(function(){
		var memberPassword=$("#password").val();
		var memberPasswordCheck=$("#passwordCheck").val();
		
		//alert(memberPassword,+","+membernumber);
		
		if(memberPassword==''){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		if(memberPasswordCheck==''){
			alert("비밀번호 확인을 입력해주세요");
			return false;
		}
		
		if(memberPassword!=memberPasswordCheck){
			alert("비밀번호와 비밀번호 확인값이 다릅니다.다시 써주세요");
			return false;
		}
		
		if(memberPasswordCheck.length < 4 || memberPasswordCheck.length > 20){
			alert("비밀번호는 4~20자로 써주세요");
			return false;
		}
		
		var sendData="membernumber="+membernumber+"&memberPassword="+memberPassword;
		
		$.ajax({
			type:"post",
			url: root+"/passwordUpdate.com",
			data: sendData,
			success:alert("수정되었습니다")
		});
	});
	
	
	//회원탈퇴 기능
	$("#memberDelete").click(function(){
		var con=confirm("정말로 탈퇴하시겠습니까?");
		if(con){
			location.href=root+"/deleteMember.com";			
		}
	});
});