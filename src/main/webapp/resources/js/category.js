/**
 * 카테고리 관련 자바스크립트
 */

$(function() {
	var categorynumber=$("#categorynumber").val();
	var mainTitle;
	
	switch (categorynumber) {
	case 0:mainTitle="최신글";break;
	case 1:mainTitle="자바";break;
	case 2:mainTitle="C언어";break;
	case 3:mainTitle="자바스크립트";break;
	case 4:mainTitle="HTML/CSS";break;
	case 5:mainTitle="파이썬";break;
	case 6:mainTitle="질문게시판";break;
	default:mainTitle="최신글";break;
	}
	
	$("#mainTitle").text(mainTitle);
	
});

function pagination(categorynumber, pageNumber){
	//alert("작동됨"+categorynumber+"___"+pageNumber);
	//페이지이동 처리 필요.
	var root=$("#root").val();
	
	location.href=root+"/main.com?categorynumber="+categorynumber+"&pageNumber="+pageNumber;
};

function detailBoard(writenumber){
	//글 자세한 내용으로 이동. 뷰 구현해야함.
	alert(writenumber);
};