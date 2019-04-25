<%@page import="ad.model.Advertiser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!
    String comName = ""; // companeyName
	String addInfo = ""; // 추가정보 입력 요청
	boolean sessCheck = true;%>
<%
	Object user = session.getAttribute("user");
	if (user instanceof Advertiser) {
		Advertiser ad = (Advertiser) user;
		if (ad.getA_name() == null) {
			addInfo = "추가 정보를 입력해 주세요!";
		} else {
			comName = ad.getA_name();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AD-TOPBAR</title>
<link href="<%=request.getContextPath()%>/css/reset.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/admain_topbar.css"
	rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/admain_topcategory.js"></script>
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
	<div class="admain-top-bar">
		<div class="admain-top-bar-left">
			<div class="title">
				<a href="<%=request.getContextPath()%>/MainControl?cmd=index"
					style="color: white; text-decoration: none;"><p>ADDBRIDGE</p></a> <a
					class="toggleMenu" href="#">CATEGORYMENU..</a>
				<ul class="top-category-nav">
					<li class="top-category-navicon"><a href="#"><i
							class="fas fa-th icon"></i></a>

				</ul>
			</div>
			<div class="company-name topbar-name-font">
				<p><%=comName%></p>
			</div>
			<div class="welcome">
				<p>
					대표님 환영합니다.
					<%=addInfo%></p>
			</div>
		</div>
		<div class="admain-top-bar-right">
			<div class="top-logout" onclick="logout(<%=sessCheck%>)">
				<i class="fas fa-sign-out-alt icon"></i>
				<p>로그아웃</p>
			</div>
			<div class="top-promo">
				<i class="fas fa-business-time icon"></i>
				<a href = "<%=request.getContextPath()%>/jsp/adprofile/ad_frame_addprofile.jsp">내 정보 관리</a>
			</div>
		</div>
	</div>
</body>
</html>
