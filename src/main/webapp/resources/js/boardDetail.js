/**
 * 글 상세화면 관련 임시 자바스크립트
 */

var root=$("#root").val();

$(function(){
	//댓글 리스트 받아오기
	var writenumber=$("#writenumber").val();
	
	replyList(writenumber,1);
	
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
	
	//댓글 등록 버튼
	$("#replyButton").click(function(){	
		var reply=$("#reply").val();
		var membernumber=$("#membernumber").val();
		
		if(reply==""){
			alert("댓글을 입력해주세요");
			return false;
		}
		
		var sendData="reply="+reply+"&membernumber="+membernumber+"&writenumber="+writenumber;
		
		$.ajax({
			url: root+"/replyWrite.com",
			type: 'post',
			data: sendData,
			success:replyList(writenumber,1)
		});
	});
	
});

//글 수정 화면 이벤트
function update(){
	$("#detailForm").css("display","none");
	$("#updateDetailForm").css("display","block");
}

//댓글 수정 버튼 화면 이벤트
function updateDisplay(replynumber) {
	$("#updateForm"+replynumber).css("display","flex");
}

//댓글 수정 
function updateReply(replynumber){
	var writenumber=$("#writenumber").val();
	var reply=$("#replyUpdateContent"+replynumber+"").val();
	var sendData="replynumber="+replynumber+"&reply="+reply;
	
	$.ajax({
		url: root+"/replyUpdate.com",
		type: "post",
		data: sendData,
		success:replyList(writenumber, 1)
	});
}

//댓글 삭제 
function deleteReply(replynumber){
	var writenumber=$("#writenumber").val();
	var sendData="replynumber="+replynumber;
	
	var yes=confirm("댓글을 삭제하시겠습니까?");
	
	if(yes==true){
		$.ajax({
			url: root+"/replyDelete.com",
			type: "post",
			data: sendData,
			success:replyList(writenumber, 1)
		});		
	}else{
		return false;
	}
}

//댓글목록 받아오기
function replyList(writenumber, pageNumber){
	var sendData="writenumber="+writenumber+"&pageNumber="+pageNumber;
	alert(sendData);
	
	$.ajax({
		url: root+"/replyList.com",
		type:"post",
		data:sendData,
		success:makeList
	});
}

//댓글목록
function makeList(json){
	alert(json.count); 
	var htmlText="";
	$("#reply").val("");
	
	var membernumber=$("#membernumber").val();
	var newReply=document.getElementById("newReply");
	
	newReply.innerHTML="";
	
	//댓글리스트 보여주기
	$(json.list).each(function(i,e){
		htmlText+='<div class="w3-twothird w3-container col-2" style="margin-bottom: 10px;">';
		htmlText+='<span style="font-size: 20px; margin-right: 30px;">'+ json.list[i].memberid+'</span>';
		htmlText+='<span style="font-size: 20px; margin-right: 15px;">작성일 : </span>';
		htmlText+='<span style="font-size: 20px; margin-right: 15px;">'+json.list[i].replydate+'</span>';
		if(membernumber==json.list[i].membernumber){
			htmlText+='<button style="font-size: 20px; margin-right: 15px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;" onclick="updateDisplay('+json.list[i].replynumber+')">수정</button>';
			htmlText+='<button style="font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;" onclick="deleteReply('+json.list[i].replynumber+')">삭제</button>';
		}
		htmlText+='</div>';
		htmlText+='<div class="w3-twothird w3-container col-9" style="margin-bottom: 10px;">';
		htmlText+='<p style="font-size: 20px; margin-right: 30px;" id="replyContent'+json.list[i].replynumber+'">'+ json.list[i].replycontent+'</p>';
		htmlText+='<div style="display: none;" id="updateForm'+json.list[i].replynumber+'">';
		htmlText+='<input type="text" class="form-control" id="replyUpdateContent'+json.list[i].replynumber+'" style="font-size: 20px; margin-right: 30px;" value="'+ json.list[i].replycontent+'">';
		htmlText+='<input onclick="updateReply('+json.list[i].replynumber+')" type="button" value="댓글수정" class="btn btn-primary" style="position:relative; top:-3px; font-size: 20px; color: black; background-color: #f0f0f0 !important; border-color: lightgrey;"/>';
		htmlText+='</div>';
		htmlText+='</div>';

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
