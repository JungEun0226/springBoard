/**
 * 글 상세보기 관련 자바스크립트
 */

$(function(){
	$("#replyButton").click(function(){	
		showReplyList();
	});
});

function showReplyList(){
	var reply=$("#reply").val();
	var membernumber=$("#membernumber").val();
	var root=$("#root").val();
	var htmlText="";
	
	var replyData={"membernumber" : membernumber, "reply":reply}
	
	if(reply=="" || reply==null){
		alert("댓글을 입력해주세요");
		return false;
	}else{
		$.ajax({
			url: root+"/replyWrite.com",
			type: 'post',
			data: replyData,
			dataType: 'json',
			success: function(data){
				for(var i=0;i<data.length;i++){
					htmlText+='<hr>';
					htmlText+='<div class="w3-twothird w3-container col-2" style="margin-bottom: 10px;">';
					htmlText+='<span style="font-size: 20px; margin-right: 30px;">${replyDto.memberid }</span>';
					htmlText+='<span style="font-size: 20px; margin-right: 15px;">작성일 : </span>';
					htmlText+='<fmt:formatDate value="${replyDto.replydate }" var="replyDate" pattern="yyyy-MM-dd HH:mm:ss"/>';
					htmlText+='<span style="font-size: 20px; margin-right: 15px;">${replyDate}</span>';
					htmlText+='<c:if test="${membernumber==replyDto.membernumber}">';
					htmlText+='<span style="font-size: 20px; margin-right: 15px;">|| 수정</span>';
					htmlText+='<span style="font-size: 20px;">|| 삭제</span>';
					htmlText+='</c:if>';
					htmlText+='</div>';
					htmlText+='<div class="w3-twothird w3-container col-9" style="margin-bottom: 10px;">';
					htmlText+='<p style="font-size: 20px; margin-right: 30px;">${replyDto.replycontent }</p>';
					htmlText+='<div style="display: inline-flex;">';
					htmlText+='<input type="text" class="form-control" id="replyUpdate" style="font-size: 20px; margin-right: 30px; display: none;" value="${replyDto.replycontent}">';
					htmlText+='<input id="replyButton" type="button" value="댓글수정" class="btn btn-primary" style="position:relative; top:-3px; font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey; display: none;"/>';
					htmlText+='</div>';
					htmlText+='</div>';
					htmlText+='<hr>';
					
				}
				$("#newReply").html(htmlText);
			}

		});
	
	}
	
}

function replyPagination(writenumber, pageNumber, root){
	//alert("작동됨"+categorynumber+"___"+pageNumber);
	//페이지이동 처리 필요.
	location.href=root+"/boardDetail.com?writenumber="+writenumber+"&pageNumber="+pageNumber;
};