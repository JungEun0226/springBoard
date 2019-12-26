/**
 * 글 상세보기 관련 자바스크립트
 */

var root=$("#root").val();

$(function(){
	$("#replyButton").click(function(){	
		writeReply();
	});
});

function writeReply(){
	var reply=$("#reply").val();
	var membernumber=$("#membernumber").val();
	var pageNumber=$("#pageNumber").val();
	var writenumber=$("#writenumber").val();
	
	if(reply=="" || reply==null){
		alert("댓글을 입력해주세요");
		return false;
	}
	
	var sendData="reply="+reply+"&membernumber="+membernumber+"&pageNumber="+pageNumber+"&writenumber="+writenumber;
	
	$.ajax({
		url: root+"/replyWrite.com",
		type: 'post',
		data: sendData,
		dataType:"json",
		success: getReplyList,
		error:function(jqXHR, textStatus, errorThrown ) {	// 이곳의 ajax에서 에러가 나면 얼럿창으로 에러 메시지 출력
			alert(jqXHR);
	    	alert(textStatus);
	    	alert(errorThrown);
	    }
	});
	
	
	
}

function replyPagination(writenumber, pageNumber, root){
	//alert("작동됨"+categorynumber+"___"+pageNumber);
	//페이지이동 처리 필요.
	location.href=root+"/boardDetail.com?writenumber="+writenumber+"&pageNumber="+pageNumber;
};

function getReplyList(json){
	//alert(json);
	
	
	//makeList(json);
	//makePage(json);
}

function makeList(json){
	//alert(json.count);  newReply
	var htmlText="";
	
	$(json.list).each(function(i,e){
		htmlText+='<hr>';
		htmlText+='<div class="w3-twothird w3-container col-2" style="margin-bottom: 10px;">';
		htmlText+='<span style="font-size: 20px; margin-right: 30px;">'+ json.list[i].memberid+'</span>';
		htmlText+='<span style="font-size: 20px; margin-right: 15px;">작성일 : </span>';
		htmlText+='<fmt:formatDate value="'+ json.list[i].replydate +'" var="replyDate" pattern="yyyy-MM-dd HH:mm:ss"/>';
		htmlText+='<span style="font-size: 20px; margin-right: 15px;">${replyDate}</span>';
		htmlText+='<c:if test="${membernumber=='+ json.list[i].membernumber+'}">';
		htmlText+='<span style="font-size: 20px; margin-right: 15px;">|| 수정</span>';
		htmlText+='<span style="font-size: 20px;">|| 삭제</span>';
		htmlText+='</c:if>';
		htmlText+='</div>';
		htmlText+='<div class="w3-twothird w3-container col-9" style="margin-bottom: 10px;">';
		htmlText+='<p style="font-size: 20px; margin-right: 30px;">'+ json.list[i].replycontent+'</p>';
		htmlText+='<div style="display: inline-flex;">';
		htmlText+='<input type="text" class="form-control" id="replyUpdate" style="font-size: 20px; margin-right: 30px; display: none;" value="'+ json.list[i].replycontent+'">';
		htmlText+='<input id="replyButton" type="button" value="댓글수정" class="btn btn-primary" style="position:relative; top:-3px; font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey; display: none;"/>';
		htmlText+='</div>';
		htmlText+='</div>';
		htmlText+='<hr>';

		var newReply=document.getElementById("newReply");
		newReply.innerHTML=htmlText;
	});
}

function makePage(json){
	$("#paginationBefore").css("display","none");
	
	var writenumber=$("#writenumber").val();
	var pageNumber=json.pageNumber;
	var boardSize=json.boardSize;
	var count=json.count;
	var paginationAfter=document.getElementById("paginationAfter");
	
	if(count>0){
		var pageCount=parseInt(count/boardSize+(count%boardSize==0? 0:1));
		var pageBlock=10;
		
		var startPage=(parseInt((pageNumber-1)/pageBlock))*pageBlock+1;
		var endPage=startPage+pageBlock-1;
//		alert(startPage+", "+endPage);
		if(endPage>pageCount)	endPage=pageCount;
		
		paginationAfter.innerHTML+='<a class="w3-button w3-black" onclick="replyPagination('+writenumber+','+1+','+root+')">««</a>"';
		
		if(startPage>pageBlock){
			paginationAfter.innerHTML+='<a class="w3-button w3-black" onclick="replyPagination('+writenumber+','+(startPage-pageBlock)+','+root+')">«</a>';
		}
		
		for(var a=startPage;a<=endPage;a++){						
			if(pageNumber==a){
				paginationAfter.innerHTML+='<a class="w3-button w3-black" style="background-color: gray; color: white;" onclick="replyPagination('+writenumber+','+i+','+root+')">${i}</a>';
			}else{
				paginationAfter.innerHTML+='<a class="w3-button w3-black" onclick="replyPagination('+writenumber+','+i+','+root+')">${i}</a>';
			}
		}
		
		if(endPage<pageCount){
			paginationAfter.innerHTML+='<a class="w3-button w3-black" onclick="replyPagination('+writenumber+','+(startPage+pageBlock)+','+root+')">»</a>';
		}
		
		paginationAfter.innerHTML+='<a class="w3-button w3-black" onclick="replyPagination('+writenumber+','+pageCount+','+root+')">»»</a>';
	}
}