<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* request.setCharacterEncoding("UTF-8");
	session.invalidate();
	out.println(
			"<script> alert('세션이 종료되었습니다.'); </script>"); */
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title></title>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/register.css" type="text/css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"></link>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script type="text/javascript">
	window.onload = function(){
	
		document.getElementById("creator_email").addEventListener("keypress", function onEvent(event) {
		    if (event.key === "Enter") {
		        LoginCheck(0);
		    }
		});
		
		document.getElementById("creator_pass").addEventListener("keypress", function onEvent(event) {
		    if (event.key === "Enter") {
		        LoginCheck(0);
		    }
		});
		
		document.getElementById("advertiser_email").addEventListener("keypress", function onEvent(event) {
		    if (event.key === "Enter") {
		        LoginCheck(1);
		    }
		});
		
		document.getElementById("advertiser_pass").addEventListener("keypress", function onEvent(event) {
		    if (event.key === "Enter") {
		        LoginCheck(1);
		    }
		});
	
	}

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

	function LoginCheck(index) {
		var default_form = document.forms[index];
		var emailId = default_form.email.value;
		var pass = default_form.password.value;
		if (emailId != "" && pass != "") {
			default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=login-page";
			default_form.method = "post";
			default_form.submit();
		} else if (emailId == "" || pass == "") {
			alert("아이디와 비밀번호를 모두 입력해주세요.");
		}

	}
	
</script>
</head>
<body>
	<div class="register-form-bg"
		style="background-image: url(<%=request.getContextPath()%>/images/register.jpg);">
		<div class="register-form-wrapper">
			<div class="register-img">
				<img
					src="<%=request.getContextPath()%>/images/main-logoicon_white.png"></img>
			</div>
			<div class="forms">
				<ul class="tab-group">
					<li class="tab active"><a href="#CreaterLogin">크리에이터 로그인</a></li>
					<li class="tab"><a href="#AdvertiserLogin">기업회원 로그인</a></li>
				</ul>
				<form id="CreaterLogin">
					<div class="input-field">
						<input id="creator_email" type="email" name="email" placeholder="EMAIL ID"></input> <input
							id="creator_pass" type="password" name="password" placeholder="비밀번호"></input> <input
							type="button" value="로그인" class="button" onclick="LoginCheck(0)"></input>
						<label id="label1" for="agree"><p class="text-p">
								<a href="<%=request.getContextPath()%>/MainControl?cmd=login">아이디와 비밀번호를 잊으셨나요?</a>
							</p></label> <label id="label2" for="agree"><p class="text-p">
								<a href="<%=request.getContextPath()%>/MainControl?cmd=signup">아직 ADDBRIDGE의 회원이 아니신가요?</a>
							</p></label>
					</div>
				</form>
				<form id="AdvertiserLogin">
					<div class="input-field">
						<input id="advertiser_email" type="email" name="email" placeholder="EMAIL ID"></input> <input
							id="advertiser_pass" type="password" name="password" placeholder="비밀번호"></input> <input
							type="button" value="로그인" class="button" onclick="LoginCheck(1)"></input>
						<label id="label1" for="agree"><p class="text-p">
								<a href="<%=request.getContextPath()%>/MainControl?cmd=login">아이디와 비밀번호를 잊으셨나요?</a>
							</p></label> <label id="label2" for="agree"><p class="text-p">
								<a href="<%=request.getContextPath()%>/MainControl?cmd=signup">아직 ADDBRIDGE의 회원이 아니신가요?</a>
							</p></label>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>