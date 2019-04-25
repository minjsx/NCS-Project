<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.Advertiser"%>
<%!
	Object user;
	String email = "";
	String eno = "";
	String ename = "";
	String imgsrc = "";
	Advertiser advertiser;
%>
<%
	
	user = session.getAttribute("user");
	if (user instanceof Advertiser) {
		advertiser = (Advertiser) user;
		email = advertiser.getEmail();
		eno = advertiser.getA_eno();
		ename = advertiser.getA_name();
		if(advertiser.getImg_src() != null){
			imgsrc = advertiser.getImg_src();
		}else{
			imgsrc = request.getContextPath() + "/images/프로필.png";
		}
	}
 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로필 입력하기</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="<%=request.getContextPath()%>/js/profile.js"></script>
<script type="text/javascript">

	window.onload = function(){
		var imgfrm = document.getElementById("uploadPreview");
			imgfrm.setAttribute("src", "<%=imgsrc%>");
	}


	var checkFirst = false;
	var checked = false;
	var lastKeyword = '';
	var loopSendKeyword = false;

	var passCheck = /^[a-zA-Z0-9]{4,12}$/

	function checkPass(passCheck, what) {
		if (passCheck.test(what)) {
			return true;
		}
		return false;
	}

	function checkPwd(index) {
		var default_form = document.forms[index];
		var pass = default_form.password.value;
		var repass = default_form.repassword.value;

		if (pass == "") {
			document.getElementsByClassName('checkPwd')[index].style.color = "black";
			document.getElementsByClassName('checkPwd')[index].innerHTML = "※  비밀번호를 입력해주세요.</br></br>";
		} else if (checkPass(passCheck, pass) == false && pass == repass) {
			document.getElementsByClassName('checkPwd')[index].style.color = "red";
			document.getElementsByClassName('checkPwd')[index].innerHTML = "※ 부적절한 비밀번호입니다.</br></br>";
			document.getElementsByClassName('recheckPwd')[index].style.color = "blue";
			document.getElementsByClassName('recheckPwd')[index].innerHTML = "※ 비밀번호가 일치합니다.</br></br>";
		} else if (checkPass(passCheck, pass) == true && pass == repass) {
			document.getElementsByClassName('checkPwd')[index].style.color = "blue";
			document.getElementsByClassName('checkPwd')[index].innerHTML = "※ 사용가능한 비밀번호입니다.</br></br>";
			document.getElementsByClassName('recheckPwd')[index].style.color = "blue";
			document.getElementsByClassName('recheckPwd')[index].innerHTML = "※ 비밀번호가 일치합니다.</br></br>";
		} else if (checkPass(passCheck, pass) == true && pass != repass) {
			document.getElementsByClassName('checkPwd')[index].style.color = "blue";
			document.getElementsByClassName('checkPwd')[index].innerHTML = "※ 사용가능한 비밀번호입니다.</br></br>";
			document.getElementsByClassName('recheckPwd')[index].style.color = "red";
			document.getElementsByClassName('recheckPwd')[index].innerHTML = "※  비밀번호가 일치하지 않습니다.</br></br>";
		} else if (checkPass(passCheck, pass) == false && pass != repass) {
			document.getElementsByClassName('checkPwd')[index].style.color = "red";
			document.getElementsByClassName('checkPwd')[index].innerHTML = "※ 부적절한 비밀번호입니다.</br></br>";
			document.getElementsByClassName('recheckPwd')[index].style.color = "red";
			document.getElementsByClassName('recheckPwd')[index].innerHTML = "※  비밀번호가 일치하지 않습니다.</br></br>";
		}
	}

	function agreeChecked(checkedElement) {
		if (checked == false) {
			checkedElement.style.color = "red";
			checked = true;
		} else {
			checkedElement.style.color = "gray";
			checked = false;
		}
	}
</script>
</head>
<body>
	<br />
	<br />
	<h1 style="font-weight: bold; font-size: 36px;">
		<strong>기본정보</strong>
	</h1>
	<br />
	<br />
	<br />
	<p>
		<span class="red">*</span> <strong> 필수정보를 입력하셔야, 정상적인 서비스를
			이용하실 수 있습니다. </strong>
	</p>
	<br />
	<br />

	
		<span class="red">*</span> <span style="margin-right: 72px">EMAIL
			ID</span> <input type="text" class="input6_3" value="<%=email%>" disabled />
		<div class="checkId div-ajax"></div>
		<br /> <span class="red">*</span> <span style="margin-right: 30px">
			사업자 등록번호</span> <input type="text" class="input6_3" value="<%=eno%>" disabled /> <br />
		<br /> <span class="red">*</span> <span style="margin-right: 78px">
			비밀번호</span> <input name="password" type="password" class="input6_3"
			placeholder="비밀번호를 입력해주세요." onkeyup="checkPwd(0)" />
		<div class="checkPwd div-ajax"></div>
		<br /> <span class="red">*</span> <span style="margin-right: 45px">
			비밀번호 확인</span> <input name="repassword" type="password" class="input6_3"
			onkeyup="checkPwd(0)" />
		<div class="recheckPwd div-ajax"></div>

	<br />
	<span class="red">*</span>
	<span style="margin-right: 92px"> 기업명</span>
	<input type="text" class="input6_3" value="<%=ename%>" disabled />
	<br />
	<br />
	<br />
	<!--  form start -->
	<form method="post" action="<%=request.getContextPath()%>/MainControl?cmd=ad-profile" enctype="multipart/form-data">
	<div style="display: flex">
		<div>
			<span class="red">*</span> <span style="margin-right: 80px">
				기업로고</span>
		</div>
		<div>
			<span>
			<img id="uploadPreview" style="width: 150px; height: 150px;" src="<%= imgsrc %>"/>
			</span> 
			<span style="margin-left: 20px"> 
			<input type="file" name="profileImage" class="button" onchange="handleFilePath(this);" style="vertical-align: super" />
			</span>
		</div>
	</div>
	<br />
	<br />
		<span style="margin-left: 240px; margin-right: 20px">
			<input type="button" class="cacel_button" value="취 소" onclick="cancel(0)">
		</span>
			<input type="submit" class="check_button" value="저장하기">
	</form>
	<!--  form end -->
	<br />
	<br />
	<br />
	<br />

</body>
</html>
