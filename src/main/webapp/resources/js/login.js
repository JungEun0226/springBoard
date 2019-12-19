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
		
		//alert(id+" 그리고 "+pass);
		
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
});

