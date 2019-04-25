<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ad.model.*" %>
<%@ page import="java.util.List" %>   

<%
	List<Promotion> mList3 = (List <Promotion>)request.getAttribute("param3");

	Object user = session.getAttribute("user");
	int c_no = 0;
	int p_no = request.getParameter("p_no") == null ? 0 : Integer.parseInt(request.getParameter("p_no"));
	
	if (user instanceof Advertiser) {

	} else if (user instanceof Creator) {
		Creator creator = (Creator) user;
		c_no = creator.getC_no();
	} else if (user == null) {
		//ContextPath 조심히 사용하자...
		out.print("<script> alert('세션이 만료되었습니다. 다시 로그인해주세요.'); document.location.href = '"
				+ request.getContextPath() + "/MainControl?cmd=main' </script>");

	} else {
		//TODO : (잘못된 경로로 접속하셨습니다.) 알림창을 띄우고, NOLOGIN 메인페이지로 
		out.println("<script> alert('잘못된 경로로 접속하셨습니다.'); document.location.href = '" + request.getContextPath()
				+ "/MainControl?cmd=main' </script>");
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/youtube_promotion.js"></script>	
</head>
<script type="text/javascript">
function addPromotion(index) {
	var default_form = document.forms[index];

	default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=add-promotion";
	default_form.method = "post";
	default_form.submit();
}

function support(index) {
	var default_form = document.forms[index];

	default_form.action = "<%=request.getContextPath()%>/MainControl?cmd=support-promotion";
	default_form.method = "post";
	default_form.submit();
}

</script>
<body>

	<div class="promotion-content-wrapper">
	<!-- img slider float 속성을 clear 해주기 위한 가상 div -->
	<div class="main-img-after"></div>
 		<!--  first contents -->
 		
 	<div class="first-contents-wrapper">
		<div class="promotion-content-bg card-box">
			<div class="first-promotion-contents">
					<% for(Promotion p : mList3) { %> 	
					<div class="card-left">
						<img class="card-img" src="<%= p.getAimg_path() %>">
					</div>		
							
					<div class="card-right card">
						<div class="card-title-wrapper"><h1 class="card-title"><%= p.getP_name() %></h1></div>
						
						<div class = "card-mid-wrapper">
						
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/지원자격.png">
									<p>지원자격</p>
								</div>
								<p><%= p.getP_qualification() %></p>
							</div>
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/단가2.png">
									<p>기본단가</p>
								</div>
								<p><%= p.getP_price() %>원</p>
							</div>
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/제작기간.png">
									<p>제작기간</p>
								</div>
								<p><%= p.getP_period() %>일</p>
							</div>
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/마감일자.png">
									<p>마감일자</p>
								</div>
								<p><%= p.getP_deadline() %></p>
							</div> 
						</div>
						<% } %> 
					</div>			
			</div>
		</div>
		<!--  first contents end -->
		
		
		<!--  btns nav -->
		<div class="promotion-btn-nav">
				<form class="promotion-btn-nav-form">
					<button type="button" class="promo-btn btn-success" onclick="support(0)">
						<i class="fas fa-plus-circle"></i>
						<b class="promo-btn-text">협업 신청하기</b>
					</button>
					<button type="button" class="promo-btn btn-danger" onclick="addPromotion(0)">
						<i class="fas fa-heart"></i>
						<b class="promo-btn-text">관심 프로모션 추가</b>
					</button>
					<input type="hidden" name="p_no" value="<%=p_no%>" />
		 			<input type="hidden" name="c_no" value="<%=c_no%>" />
		 		</form>
		</div>
		<!-- btns nav end -->

	</div>
	  <!-- second contents -->
	  <div class="promotion-content-bg content-box">
	  	<h1 class="card-title">프로모션 소개</h1>
	  	 <% for(Promotion p : mList3) { %> 
	  	<div class="promotion-content-desc"><%= p.getP_info() %></div>
		 <% } %> 
	  </div>	 
	  <!-- second contents end-->
	  
	  <!-- third contents -->
	  <div class="promotion-content-bg content-box">	   
	  	<h1 class="card-title">제작 컨텐츠 안내</h1>
	  	 <% for(Promotion p : mList3) { %> 
	  	<div class="promotion-content-desc"> <%= p.getP_content() %> </div>
 		<% } %> 
	  </div>
	  <!-- third contents end-->
	  
	</div>

</body>
</html>

