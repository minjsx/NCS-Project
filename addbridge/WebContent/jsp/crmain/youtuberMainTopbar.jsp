<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="ad.model.Creator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	boolean sessCheck = true;
	Creator cr =  session.getAttribute("user") instanceof Creator ? (Creator)session.getAttribute("user") : null;
	if(cr == null){
		response.sendRedirect(request.getContextPath() + "/MainControl?cmd=index");
	}
	String name = cr.getC_name() != null ? cr.getC_name() + "님 환영합니다." : cr.getName() + "님 추가정보를 등록 해주세요!";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" />
	<link href="<%=request.getContextPath()%>/css/crmain_topbar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
function logout(check){
	check = false;
	if(check == false){
		document.location.href = "<%=request.getContextPath()%>/MainControl?cmd=killsession";
	}	
}
</script>
</head>
<body>
	<div class="top-bar">
			<div class="top-bar-left">
				<div class="title"><a href="<%=request.getContextPath()%>/MainControl?cmd=index"
					style="color: white; text-decoration: none;">ADDBRIDGE</a> </div>
				<div id="menu1" class="title">
					<ul class="main">
						<li id="cate-img"><i class="fas fa-th icon"></i>
					<div class="menu2">
						<ul class="sub">
							<li><a href="#">게임</a></li>
							<li><a href="#">푸드</a></li>
							<li><a href="#">뷰티</a></li>
							<li><a href="#">영화</a></li>
							<li><a href="#">운동</a></li>
						</ul>
						
						<ul class="sub">
							<li><a href="#">IT기술</a></li>
							<li><a href="#">패션</a></li>
							<li><a href="#">토이&키즈</a></li>
							<li><a href="#">교육</a></li>
							<li><a href="#">엔터테이먼트</a></li>
						</ul>
					</div>		
						</li>
					</ul>
				</div>
				<div class="label"><label><%= name %></label></div>			
			</div>
			<div class="top-bar-right">
					<div class="admain-top-bar-right">
				<div class="top-logout" onclick="logout(<%=sessCheck%>)">
					<i class="fas fa-sign-out-alt icon"></i>
					<p>로그아웃</p>
				</div>
				<div class="top-addprofile"><a href="<%=request.getContextPath()%>/jsp/crProfile/cr_profileflex.jsp"><i class="fas fa-user-cog"></i> 내 프로필 등록</a></div>
			</div>
		</div>
	</div>
</body>
</html>

 