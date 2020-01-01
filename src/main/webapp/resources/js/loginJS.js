/**
 * 로그인 관련 js, 아이디/비밀번호 찾기도 여기 포함.
 */

$(function(){
	var root=$("#root").val();
	
	//로그인 버튼
	$("#login").click(function(){
		var id=$("#memberID").val();
		var pass=$("#memberPassword").val();
		
		if(id==''){
			alert("아이디를 입력해주세요");
			$("#memberID").focus();
			return false;
		}
		
		if(pass==''){
			alert("비밀번호를 입력해주세요");
			$("#memberPassword").focus();
			return false;
		}
		
		$.post(root+"/loginOk.com",
				{id:id, pass:pass},
				function(data){
					//alert(data);
					if(data>=1){
						//alert(data);
						location.href=root+"/main.com";
					}else{
						alert("아이디와 비밀번호를 확인해주세요");
						return false;
					}
				}
		);
	});
	
	//아이디 찾기
	$("#findIdButton").click(function(){
		var findId=$("#findId").val();
		
		if(findId==""){
			alert("이메일을 입력해주세요");
			return false;
		}
		
		var sendData="memberemail="+findId;
		
		$.ajax({
			url: root+"/findId.com",
			type:"post",
			data: sendData,
			success:makeIdForm
		});
	});
	
	//비밀번호 찾기
	$("#findPassButton").click(function(){
		var findPass=$("#findPass").val();
		
		if(findPass==""){
			alert("이메일을 입력해주세요");
			return false;
		}
		
		var sendData="memberemail="+findPass;
		
		$.ajax({
			url: root+"/findPass.com",
			type:"post",
			data: sendData,
			success:makePassForm
		});
	});
	
	
	//로그인 - 비밀번호 수정
	$("#passwordUpdate").click(function(){
		var memberPassword=$("#password").val();
		var memberPasswordCheck=$("#passwordCheck").val();
		var membernumber=$("#findPassMemberNum").val();
		
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
});

//   아이디/비밀번호 찾기 스타일 이벤트
function findIdJS(){
	$("#findPassStyleForm").css("display","none");
	$("#findIdResult").css("display","none");
	$("#findPassResult").css("display","none");
	$("#findIdStyleForm").css("display","block");
}

function findPasswordJS(){
	$("#findIdStyleForm").css("display","none");
	$("#findIdResult").css("display","none");
	$("#findPassResult").css("display","none");
	$("#findPassStyleForm").css("display","block");
}

function makeIdForm(data){
	//alert("findId:"+data);
	
	$("#findIdResult").css("display","block");
	
	var htmls="";
	var findIdResult=document.getElementById("findIdResult");
	findIdResult.innerHTML="";
	
	htmls+='<div class="form-group" style="width: 70%; text-align: center;">';
	if(data=="null"){
		htmls+='<span class="w3-bar-item" style="color:black; margin-right: 15px;">아이디가 존재하지 않습니다</span>';
	}else{
		htmls+='<span class="w3-bar-item" style="color:black; margin-right: 15px;">가입하신 아이디는 : '+data+' 입니다</span>';
	}
	htmls+='</div>';
	
	findIdResult.innerHTML=htmls;
}

function makePassForm(data){
	//멤버넘버받아와서 히든처리
	//alert("findPass:"+data);
	
	$("#findPassResult").css("display","block");
	
	var htmls="";
	var findPassResult=document.getElementById("findPassResult");
	findPassResult.innerHTML="";
	
	if(data!="null"){
		$("#findPassUpdateStyleForm").css("display","block");
		htmls+='<input type="hidden" value="'+data+'" id="findPassMemberNum"/>'
	}else{
		htmls+='<div class="form-group" style="width: 70%; text-align: center;">';
		htmls+='<span class="w3-bar-item" style="color:black; margin-right: 15px;">가입하신 이메일을 확인해주세요</span>';
		htmls+='</div>';
	}
	
	findPassResult.innerHTML=htmls;
}