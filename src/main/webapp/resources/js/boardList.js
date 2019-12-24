/**
 * 게시판 목록 관련 자바스크립트
 */

$(document).ready(function(){
	var categorynumber=$("#categorynumber").val();
	var title;
	//alert(categorynumber);
	
	if(categorynumber==0){
		title="최신글";
	}else if(categorynumber==1){
		title='자바';
	}else if(categorynumber==2){
		title="C언어";
	}else if(categorynumber==3){
		title="자바스크립트";
	}else if(categorynumber==4){
		title="HTML/CSS";
	}else if(categorynumber==5){
		title="파이썬";
	}else if(categorynumber==6){
		title="질문게시판";
	}
	
	$("#mainTitle").text(title);
});

function pagination(root, categorynumber, pageNumber){
	//alert("작동됨"+categorynumber+"___"+pageNumber);
	//페이지이동 처리 필요.
	location.href=root+"/main.com?categorynumber="+categorynumber+"&pageNumber="+pageNumber;
};

function detailBoard(root, writenumber){
	//글 자세한 내용으로 이동. 뷰 구현해야함.
	//alert(writenumber);
	location.href=root+"/boardDetail.com?writenumber="+writenumber;
};
