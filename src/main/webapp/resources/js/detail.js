/**
 * 글 상세화면 관련 임시 자바스크립트
 */

var root=$("#root").val();

$(function(){
	var writenumber=$("#writenumber").val();
	var pageNumber="";
	//alert(writenumber);
	
	//댓글 리스트 받아오기
	replyList(writenumber,pageNumber);
	
	//댓글 등록 버튼
	$("#replyButton").click(function(){	
		writeReply(writenumber);
	});
	
	//글 수정
	$("#writeUpdateButton").click(function(){
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

//글 수정
function update(){
	$("#detailForm").css("display","none");
	$("#updateDetailForm").css("display","block");
}

//댓글목록 받아오기
function replyList(writenumber, pageNumber){

	var sendData="writenumber="+writenumber+"&pageNumber="+pageNumber;
	//alert(sendData);
	
	$.ajax({
		url: root+"/replyList.com",
		type:"post",
		data:sendData,
		success:makeList,
		error:function(jqXHR, textStatus, errorThrown ) {
			alert(jqXHR);
	    	alert(textStatus);
	    	alert(errorThrown);
	    }
	});
}

//댓글등록
function writeReply(writenumber){
	var reply=$("#reply").val();
	var membernumber=$("#membernumber").val();
	
	if(reply=="" || reply==null){
		alert("댓글을 입력해주세요");
		return false;
	}
	
	var sendData="reply="+reply+"&membernumber="+membernumber+"&writenumber="+writenumber;
	
	$.ajax({
		url: root+"/replyWrite.com",
		type: 'post',
		data: sendData,
		success: replyList(writenumber),
		error:function(jqXHR, textStatus, errorThrown ) {
			alert(jqXHR);
	    	alert(textStatus);
	    	alert(errorThrown);
	    }
	});
}

function makeList(json){
	//alert(json.count);  newReply
	var htmlText="";
	
	//댓글리스트 보여주기
	$(json.list).each(function(i,e){
		htmlText+='<div class="w3-twothird w3-container col-2" style="margin-bottom: 10px;">';
		htmlText+='<span style="font-size: 20px; margin-right: 30px;">'+ json.list[i].memberid+'</span>';
		htmlText+='<span style="font-size: 20px; margin-right: 15px;">작성일 : </span>';
		htmlText+='<span style="font-size: 20px; margin-right: 15px;">'+json.list[i].replydate+'</span>';
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

		var newReply=document.getElementById("newReply");
		newReply.innerHTML=htmlText;
	});
	
	//페이징기능
	var writenumber=$("#writenumber").val();
	var pageNumber=json.pageNumber;
	var boardSize=json.boardSize;
	var count=json.count;
	var pagination=document.getElementById("pagination");
	pagination.innerHTML="";
	
	if(count>0){
		var pageCount=parseInt(count/boardSize+(count%boardSize==0? 0:1));
		var pageBlock=10;
		
		var startPage=parseInt(((pageNumber-1)/pageBlock))*pageBlock+1;
		var endPage=startPage+pageBlock-1;
		//alert(startPage+", "+endPage+", pageCount"+pageCount);
		
		if(endPage>pageCount)	endPage=pageCount;
		
		pagination.innerHTML+='<a class="w3-button w3-black" onclick="replyList('+writenumber+','+1+')">««</a>';
		
		if(startPage>pageBlock){
			pagination.innerHTML+='<a class="w3-button w3-black" onclick="replyList('+writenumber+','+(startPage-pageBlock)+')">«</a>';
		}
		
		for(i=startPage;i<=endPage;i++){						
			pagination.innerHTML+='<a class="w3-button w3-black" onclick="replyList('+writenumber+','+i+')">'+i+'</a>';
		}
		
		if(endPage<pageCount){
			pagination.innerHTML+='<a class="w3-button w3-black" onclick="replyList('+writenumber+','+(startPage+pageBlock)+')">»</a>';
		}
		
		pagination.innerHTML+='<a class="w3-button w3-black" onclick="replyList('+writenumber+','+pageCount+')">»»</a>';
	}
}
