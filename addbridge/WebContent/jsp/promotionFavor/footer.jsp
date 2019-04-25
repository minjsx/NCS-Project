<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
    System.out.println(ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
   <link href="<%=ctx %>/css/reset.css" rel="stylesheet" type="text/css"/>
   <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
   <link href="<%=ctx %>/css/cr_footer.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="div_all">
<br/><br/><br/><br/>
<div class="div_left">
	<h1 class="bigName"><span style="margin-left:400px">ADDBRIDGE</span></h1><br/><br/><br/>
	<p><span style="margin-left:400px"><i class="fas fa-phone-volume"></i> &nbsp; 042-630-4949</span></p><br/>
	<p><span style="margin-left:397px"><i class="far fa-envelope"></i>  &nbsp; help@addbridge.com </span></p><br/>
	<p><span style="margin-left:400px"><i class="fas fa-map-marker-alt"></i>  &nbsp; 대전광역시 동구 백룡로 11번길 63 </span></p><br/>
	<p><span style="margin-left:422px"> (우송 IT 센터)1층 </span></p><br/>
	<p><span style="margin-left:400px"><i class="far fa-building"></i> &nbsp; 182-43-00974 </span></p><br/>
</div>
<div class="div_center_1"><br/>
	<h2 class="midiumName"><span>서비스</span></h2><br/><br/><br/>
	<p><a href="">광고주 등록</a></p><br/>
	<p><a href="profile.jsp">크리에이터 등록</a></p><br/>
	<p><span>마케팅 사례</span></p><br/>
</div>
<div class="div_center_2"><br/>
	<h2 class="midiumName"><span>이용안내</span></h2><br/><br/><br/>
	<p><span>서비스 소개</span></p><br/>
	<p><span>결제 안내</span></p><br/>
	<p><span>자주 묻는 질문</span></p><br/>
</div>
<div class="div_right"><br/>
	<h2 class="midiumName"><span>About us</span></h2><br/><br/><br/>
	<p><span>회사 소개</span></p><br/>
	<p><span>애드브릿지 소식</span></p><br/>
	<p><span>이용 약관</span></p><br/>
</div>
<hr width="70%">
<div class="bar-right">
	<span><a href="https://twitter.com/?lang=ko"><img src="<%=ctx %>/images/icon/1.png"/></a></span>
	<span><a href="https://ko-kr.facebook.com/"><img  src="<%=ctx %>/images/icon/2.png"/></a></span>
	<span><a href="https://www.youtube.com/?gl=KR&hl=ko"><img src="<%=ctx %>/images/icon/3.png"/></a></span> 
	<span><a href="https://www.instagram.com/?hl=ko"><img src="<%=ctx %>/images/icon/4.png"/></a></span>
</div>
</div>
</body>
</html>