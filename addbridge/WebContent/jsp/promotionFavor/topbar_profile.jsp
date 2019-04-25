<%@page import="ad.model.Creator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	boolean sessCheck = true;
%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<%
	int c_no = 0;
	Object user = session.getAttribute("user");
	if(user instanceof Creator){
		Creator cr = (Creator)user;
		c_no = cr.getC_no();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=ctx%>/css/reset.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link href="<%=ctx%>/css/cr_topbar_profile.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
function zoo()
{
	   var winObj = window.open("","newWin", "width=400, heigth=250");
       winObj.location.href = "Cash_Ex.html";     
}
function logout(check){
	check = false;
	if(check == false){
		document.location.href = "<%= request.getContextPath() %>/MainControl?cmd=killsession";
	}	
}
</script>
</head>
<body>
   <div class="top-bar">
      <div class="top-bar-left">
      	 <div> <img class="logo" src="<%=ctx%>/images/icon/logo-icon_white.png"></img> </div>
         <div class="title"> <a href="<%=ctx%>/MainControl?cmd=index"><p>ADDBRIDGE</p></a></div>
      </div>
      <div class="top-bar-right">
     	 <div class="top-logout" onclick="logout(<%=sessCheck%>)"><i class="fas fa-sign-out-alt icon"></i> <p>로그아웃  &nbsp;</p></div>	
       	 <div><a href="<%=ctx%>/jsp/crProfile/cr_profileflex.jsp"><i class="far fa-user-circle"></i> <p>나의 프로필 &nbsp;</p> </a></div>
       	 <div><a href="<%=ctx%>/MainControl?cmd=favor-page&c_no=<%=c_no%>"><i class="fas fa-heart"></i> <p>관심 프로모션 &nbsp;</p></a></div>
       	 <div><a href="<%=ctx%>/MainControl?cmd=pro-status&c_no=<%=c_no%>"><i class="fas fa-tasks"></i> <p>프로모션 상태 &nbsp;</p></a></div>
       	 <input type="hidden" name="c_no" value="<%=c_no %>" />
      </div>
   </div>
</body>
</html>
