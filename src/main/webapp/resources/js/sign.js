/**
 * 회원가입 관련 자바스크립트 중복체크, 빈칸체크, 데이터넘겨주기 해야함
 */
$(document).ready(function() {
	var root=$("#root").val();
	
	//아이디 중복체크 확인
	$("#memberIDCheck").click(function() {
		var memberid=$("#memberID").val();		
		var memberIDCheckValue=$("#memberIDCheckValue").val();
		//alert(memberid+"\t"+memberIDCheckValue);
		
		$.ajax({
			url:root+"/memberidcheck.com?memberid="+memberid,
			type:"get",
			success:function(data){
//				alert(data);
				if(data>=1){
					alert("사용중인 아이디입니다.");
				}else{
					alert("사용가능한아이디 입니다.");
					$("#memberIDCheckValue").val("true");
				}
			}
		});
		
	});
	
	//이메일 가입 여부 확인
	$("#emailCheck").click(function(){
		var memberEmail=$("#memberEmail").val();
		
		$.ajax({
			url:root+"/emailCheck.com?memberEmail="+memberEmail,
			type:"get",
			success:function(data){
//				alert(data);
				if(data>=1){
					alert("사용중인 이메일입니다. 아이디/비밀번호찾기를 이용해주세요.");
				}else{
					alert("사용가능한 이메일 입니다.");
					$("#emailCheckValue").val("true");
				}
			}
		});
	});
	
	//가입하기 버튼 눌렀을때 빈칸확인, 비밀번호 같은지확인
	$("#signUp").click(function(){
		var memberID=$("#memberID").val();
		var memberIDCheckValue=$("#memberIDCheckValue").val();
		
		var memberPassword=$("#memberPassword").val();
		var memberPasswordCheck=$("#memberPasswordCheck").val();
		
		var memberEmail=$("#memberEmail").val();
		var emailCheckValue=$("#emailCheckValue").val();
		
		//빈칸확인, 길이확인
		if(memberIDCheckValue=='false'){
			alert("아이디 중복 확인을 해주세요");
			return false;
		}
		
		if(memberID.length < 4 || memberID.length > 20){
			alert("아이디는 4~20자로 써주세요");
			$("#memberIDCheckValue").val("false");
			return false;
		}
		
		if(emailCheckValue=='false'){
			alert("이메일 가입 여부 확인을 해주세요");
			return false;
		}
		
		if(memberEmail.length < 4 || memberEmail.length > 50){
			alert("이메일은 4~50자로 써주세요");
			$("#emailCheckValue").val("false");
			return false;
		}
		
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
		
		var sendData="memberID="+$("#memberID").val()+"&memberPassword="+memberPassword+"&memberEmail="+$("#memberEmail").val();
		
		$.ajax({
			type:"post",
			url: root+"/signupOk.com",
			dataType:"json",
			data: sendData,
			success:location.href=root+"/login.com"
		});
	});
});
