function zipCheck(){
	//alert('zip');
	url = "zipcheck.jsp?check=y";
	window.open(url, "post", "toolbar=no, width=350, height=300, top=200, left=300, status=yes, scrollbars=yes,menubar=no");
}

function idCheck(){
	if(regForm.id.value === ""){
		alert('id입력');
		regForm.id.focus();
	}else{
		url = "idcheck.jsp?id=" + regForm.id.value;
		window.open(url, "id", "toolbar=no, width=300, height=150, top=200, left=300, status=yes, scrollbars=yes,menubar=no");		
	}
}

function inputCheck(){
	// 입력자료 검사 후 추가 처리
	if(regForm.id.value === ""){
		alert('아이디 입력');
		regForm.id.focus();
		return;
	}
	
	if(regForm.passwd.value === ""){
		alert('비밀번호 입력');
		regForm.passwd.focus();
		return;
	}
	
	if(regForm.passwd.value !== regForm.repasswd.value){
		alert('비밀번호 블일치');
		regForm.repasswd.focus();
		return;
	}

	if(regForm.name.value === ""){
		alert('이름 입력');
		regForm.name.focus();
		return;
	}
	
	if(regForm.email.value === "" ){
		alert('이메일 입력');
		regForm.email.focus();
		return;
	}
	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	if(!regForm.email.value.match(regExp)){
		alert('이메일 정확히 입력');
		regForm.email.focus();
		return;
	}	
	
	if(regForm.phone.value === "" ){
		alert('핸드폰 입력');
		regForm.phone.focus();
		return;
	}
	
	// 생략....
	
	regForm.submit();
	
}

// 로그인 후 개인정보 수정
function memberUpdate(){
	//입력자 검사 생략...
	document.updateForm.submit();
}
function memberUpdateCancel(){
	history.back();
}
function memberDelete(){
	alert('회원탈퇴');
}


// 관리자가 개인정보 수정
function memUpdate(id){
	document.updateForm.id.value = id;
	document.updateForm.submit();
}

function memberUpdateAdmin(){
	document.updateFormAdmin.submit();
}

function memberUpdateCancelAdmin(){
	location.href ="membermanager.jsp";
}


// 관리자가 상품 상세보기
function productDetail(no){
	document.detailForm.no.value = no;
	document.detailForm.submit();
}

function productUpdate(no){
	document.updateForm.no.value = no;
	document.updateForm.submit();
}

function productDelete(no){
	if(confirm('정말 삭제할까요?')){
	document.deleteForm.no.value = no;
	document.deleteForm.submit();
	}
}

// cart 처리를 위한 함수
function cartUpdate(form){
	form.flag.value = "update";
	form.submit();
	
}

function cartDelete(form){
	form.flag.value = "delete";
	form.submit();
}


// 관리자가 주문처리를 하는 곳
function orderDetail(no){
	document.detailForm.no.value = no;
	document.detailForm.submit();
}

function orderUpdate(form){
	document.detailForm.flag.value = "update";
	form.submit(); // form을 넘겼기 때문에 form.submit()이 가능
}

function orderDelete(form){
	document.detailForm.flag.value = "delete";
	document.detailForm.submit();
}

