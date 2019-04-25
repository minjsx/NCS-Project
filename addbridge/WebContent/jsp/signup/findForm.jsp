<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ad.model.*"%>
<%@ page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css" type="text/css"></link>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"></link>
<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/findform.js"></script>
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
					<li class="tab active"><a href="#idFind">아이디 찾기</a></li>
					<li class="tab"><a href="#passwordFind">비밀번호 찾기</a></li>
				</ul>
				<form id="idFind">
					<div class="input-field">
						<input type="text" name="name"
							placeholder="크리에이터 이름 혹은 대표자명을 입력하세요."></input> <input
							type="text" name="phone"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
							maxlength=11; placeholder="연락처를 입력하세요."></input> <input
							type="button" value="아이디 찾기" class="button" onclick="idFind(0)"></input>
					</div>
				</form>
				<form id="passwordFind">
					<div class="input-field">
						<input type="text" name="email" placeholder="EMAIL ID"></input> <input
							type="text" name="phone"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
							maxlength=11; placeholder="연락처를 입력하세요."></input> <input
							type="button" value="비밀번호 찾기" class="button"
							onclick="passwordFind(1)"></input>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>