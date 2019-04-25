<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.Advertiser"%>
<%
	Object user = session.getAttribute("user");
	int a_no = 0;

	if (user instanceof Advertiser) {
		Advertiser advertiser = (Advertiser) user;
		a_no = advertiser.getA_no();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/ad_topbar_profile.css"
	rel="stylesheet" type="text/css" />
	
<script type="text/javascript">
function addcash(){
	  var winObj = window.open("Cash_Ex.jsp","캐시충전", "width=800, heigth=600");	  
}
</script>

</head>
<body>
	<div class="top-bar">
		<div class="top-bar-left">
			<div class="title">
				<a href="<%=request.getContextPath()%>/MainControl?cmd=index">ADDBRIDGE</a>
			</div>
		</div>		
		<div class="top-bar-right">
			<div><a href ="<%=request.getContextPath()%>/jsp/adprofile/ad_frame_addprofile.jsp"><i class="far fa-address-card icon"></i> <p>프로필 등록</p></a></div>
			<div><a onclick="addcash()"><i class="far fa-copyright icon"></i> <p>캐시충전 &nbsp;</p></a></div>
			<div><a href="<%=request.getContextPath()%>/jsp/adprofile/ad_frame_register.jsp"><i class="fab fa-adversal icon"></i> <p>프로모션 등록 &nbsp;</p></a></div>
			<div><a href="<%=request.getContextPath()%>/MainControl?cmd=status-check&a_no=<%=a_no%>"><i class="fas fa-business-time icon"></i> <p>프로모션 관리 &nbsp;</p></a></div>
			<div><a href="<%=request.getContextPath()%>/MainControl?cmd=status-support&a_no=<%=a_no%>"><i class="far fa-chart-bar icon"></i> <p>프로모션 지원현황 &nbsp;</p></a></div>
			
		</div>
	</div>
</body>
</html>
