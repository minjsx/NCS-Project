<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Integer result = (Integer) request.getAttribute("checkResult");
	if (result == null) {
		result = 0;
	} else if (result == -1) {
		out.println("<script>alert('이미 사용중인 이메일입니다.');</script>");
	} else if (result == 1) {
		out.println("<script>alert('사용가능한 이메일입니다.');</script>");
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>광고주 회원가입폼</title>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/register.css" type="text/css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"></link>
<script
	src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.tab a').on('click', function(e) {
			e.preventDefault();
			$(this).parent().addClass('active');
			$(this).parent().siblings().removeClass('active');

			var href = $(this).attr('href');
			$('.forms > form').hide();
			$(href).fadeIn(500);
		});
	});
	var checked = false;
	var lastKeyword = '';
	var loopSendKeyword = false;

	var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var passCheck = /^[a-zA-Z0-9]{4,12}$/

	function checkEmail(emailCheck, what) {
		if (emailCheck.test(what)) {
			return true;
		}
		return false;
	}

	function checkPass(passCheck, what) {
		if (passCheck.test(what)) {
			return true;
		}
		return false;
	}

	function checkId(index) {
		var default_form = document.forms[index];
		var emailId = default_form.email.value;

		if (emailId == "") {
			document.getElementsByClassName('checkId')[index].style.color = "black";
			document.getElementsByClassName('checkId')[index].innerHTML = "※  이메일을 입력해주세요.</br></br>";
		} else if (checkEmail(emailCheck, emailId) == true) {
			document.getElementsByClassName('checkId')[index].style.color = "blue";
			document.getElementsByClassName('checkId')[index].innerHTML = "※ 유효한 이메일입니다.</br></br>";
		} else if (checkEmail(emailCheck, emailId) == false) {
			document.getElementsByClassName('checkId')[index].style.color = "red";
			document.getElementsByClassName('checkId')[index].innerHTML = "※ 유효하지 않은 이메일입니다.</br></br>";
		}
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

	function successRegister(index, resultCheck) {
		var default_form = document.forms[index];
		var emailId = default_form.email.value;
		var pass = default_form.password.value;
		var repass = default_form.repassword.value;
		var rname = default_form.name.value;
		var rphone = default_form.phone.value;
		
		if (emailId != "" && checkEmail(emailCheck, emailId) == true
				&& checkPass(passCheck, pass) == true && pass == repass
				&& checked == true && rname != "" && rphone != "" && resultCheck == 1) {		
			alert("회원가입에 성공하셨습니다.");
			default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=register-page";
			default_form.method = "post";
			default_form.submit();
			
		} else if (checked == false) {
			alert("약관동의에 체크해주세요.");
		} else if (emailId == "" || checkEmail(emailCheck, emailId) == false
				|| checkPass(passCheck, pass) == false || pass == repass
				|| checked == false || rname == "" || rphone == "" || resultCheck == -1) {
			alert("세부사항을 정확히 입력해주세요.");
		} else if (resultCheck == 0) {
			alert("이메일 중복체크를 해주세요.");
		} 	
	}

	function checkEmailId(index) {
		var default_form = document.forms[index];
		var emailIdCheck = default_form.email.value;
		if (emailIdCheck != "") {
			default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=emailCheck";
			default_form.method = "post";
			default_form.submit();
		} else if (emailIdCheck == "")
			alert("이메일을 입력해주세요.");
	}
</script>
</head>
<body>
	<div class="register-form-bg" style="background-image: url(<%=request.getContextPath()%>/images/register.jpg);">
		<div class="register-form-wrapper">
			<div class="register-img">
				<img
					src="<%=request.getContextPath()%>/images/main-logoicon_white.png"></img>
			</div>
			<div class="forms">
				<ul class="tab-group">
					<li class="tab active"><a href="#CreaterRegister">크리에이터 가입</a></li>
					<li class="tab"><a href="#AdvertiserRegister">기업회원 가입</a></li>
				</ul>
				<form id="CreaterRegister">
					<div class="input-field">
						<input type="text" name="email" placeholder="EMAIL ID"
							onkeyup="checkId(0)"></input>
						<div class="checkId"></div>
						<input type="button" onclick="checkEmailId(0)" value="중복체크"></input>
						<input type="password" name="password" placeholder="비밀번호"
							onkeyup="checkPwd(0)"></input>
						<div class="checkPwd"></div>
						<input type="password" placeholder="비밀번호 확인" name="repassword"
							onkeyup="checkPwd(0)"></input>
						<div class="recheckPwd"></div>
						<input type="text" name="name" placeholder="크리에이터 이름"></input> <input
							name="phone"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
							maxlength=11; placeholder="휴대폰 번호 입력"></input>
						<div class="form-agree-wrap">
							<label for="agree">크리에이터 약관에 동의</label><span
								class="form-checkbox" style="color: gray;"
								onclick="agreeChecked(this)"> <i
								class="far fa-check-circle"></i></span>
						</div>
						<input type="button" value="회원가입" class="button"
							onclick="successRegister(0, <%=result%>)"></input> <input
							type="hidden" name="type" value="1"></input>
					</div>
				</form>
				<form id="AdvertiserRegister">
					<div class="input-field">
						<input type="email" name="email" placeholder="EMAIL ID"
							onkeyup="checkId(1)"></input>
						<div class="checkId"></div>
						<input type="button" onclick="checkEmailId(1)" value="중복체크"></input>
						<input type="password" name="password" placeholder="비밀번호"
							onkeyup="checkPwd(1)"></input>
						<div class="checkPwd"></div>
						<input type="password" placeholder="비밀번호 확인" name="repassword"
							onkeyup="checkPwd(1)"></input>
						<div class="recheckPwd"></div>
						<input type="text" name="name" placeholder="기업 이름"></input> <input
							name="phone"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
							maxlength=11; placeholder="휴대폰 번호 입력"></input> <input type="text"
							placeholder="사업자등록번호 입력" name="adrno"></input>
						<div class="form-agree-wrap">
							<label for="agree">기업회원 약관에 동의</label><span class="form-checkbox"
								style="color: gray;" onclick="agreeChecked(this)"> <i
								class="far fa-check-circle"></i></span>
						</div>
						<input type="button" value="회원가입" class="button"
							onclick="successRegister(1, <%=result%>)"></input> <input
							type="hidden" name="type" value="2"></input>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>