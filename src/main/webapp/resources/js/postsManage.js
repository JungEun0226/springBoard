/**
 * 마이페이지 - 내 글/댓글 관리 자바스크립트
 */

function pagination(root, membernumber, key, pageNumber){
	if(key=='내 글 관리'){
		key="posts";
	}else{
		key="reply";
	}
	
	location.href=root+"/myPostsManage.com?membernumber="+membernumber+"&key="+key+"&pageNumber="+pageNumber;
}

function goDetail(root, writenumber){
	location.href=root+"/boardDetail.com?writenumber="+writenumber;
}

function checkAll(){
	if($("#selectAll").is(':checked')){
		$("input[name=check]").prop("checked", true);
	}else{
		$("input[name=check]").prop("checked", false);
	}
}

$(function(){
	var key=$("#key").val();
	var root=$("#root").val();
	
	if(key=="내 글 관리"){
		key="boardwrite";
		
	}else{
		key="reply";
	}
	
	//선택삭제
	$("#selectDelete").click(function(){
		var check="";
		
		$("input[name='check']:checked").each(function(){
			check+=$(this).val()+",";
		});
		
		check=check.substring(0,check.lastIndexOf(","));
		
		var sendData="check="+check+"&key="+key;
		
		if(check==''){
			alert("삭제할 글을 선택해주세요");
			return false;
		}
		
		$.ajax({
			url: root+"/myPageDelete.com",
			type: "post",
			data: sendData,
			success:function(){
				alert("삭제되었습니다");
				location.reload();
			}
		});
		
	});
	
	$("#allDelete").click(function(){
		var membernumber=$("#membernumber").val();

		if(confirm("정말로 작성한 모든것을 삭제하시겠습니까?")){
			var sendData="key="+key+"&membernumber="+membernumber;
			
			$.ajax({
				url: root+"/myPageDelete.com",
				type: "post",
				data: sendData,
				success:function(){
					alert("삭제되었습니다");
					location.reload();
				}
			});
			
		}else{
			return false;
		}
		
		
	});

})
