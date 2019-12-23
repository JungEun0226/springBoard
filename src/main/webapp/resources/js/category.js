/**
 * 카테고리 관련 자바스크립트
 */

var root=document.getElementById("root");

function pagination(categorynumber, pageNumber){
	//alert("작동됨"+categorynumber+"___"+pageNumber);
	//페이지이동 처리 필요.
	location.href=root+"/main.com?categorynumber="+categorynumber+"&pageNumber="+pageNumber;
};

function detailBoard(writenumber){
	//글 자세한 내용으로 이동. 뷰 구현해야함.
	//alert(writenumber);
	location.href=root+"/boardDetail.com?writenumber="+writenumber;
};