<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ad.model.*" %>
<%@ page import="java.util.List" %>

<% 
	Creator cr = request.getAttribute("creator") instanceof Creator ? (Creator)request.getAttribute("creator") : null;
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="<%= request.getContextPath() %>/css/youtube_promotion_content.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/youtube_creator.js"></script>	
</head>
<body>

<div class="promotion-content-wrapper">
	<!-- img slider float 속성을 clear 해주기 위한 가상 div -->
	<div class="main-img-after"></div>

 		<!--  first contents -->
 	<div class="first-contents-wrapper">
		<div class="promotion-content-bg c-card-box">
			<div class="first-promotion-contents">
					<div class="card-left">
						<img class="card-img" src="<%= cr.getImg_src() %>">
					</div>		
					
					<div class="card-right card">
						<div class="card-title-wrapper"><h1 class="card-title"><%= cr.getC_chanel() %></h1></div>
						
						<div class="card-mid-wrapper">
						
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/유튜브아이콘.png">
									<p>구독자수</p>
								</div>
								<p><%= cr.getC_count() %>명</p>
							</div>
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/카테고리.png">
									<p>카테고리</p>
								</div>
								<p><%= cr.getCg_name() %></p>
							</div>
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/선호광고.png">
									<p>선호광고</p>
								</div>
								<p><%= cr.getCi_ad() %></p>
							</div>
							<div class="card-mid-content">
								<div class="card-mid-title">
									<img class="img_icon" src="<%= request.getContextPath() %>/images/단가2.png">
									<p>희망단가</p>
								</div>
								<p><%= cr.getCi_price() %>원</p>
							</div> 
						</div>		
					</div>			
			</div>
		</div>										
	</div>
	
	<!-- second contents -->
	  <div class="promotion-content-bg content-box">
	  	<h1 class="card-title">크리에이터 소개</h1>
	  	<div class="promotion-content-desc"><%= cr.getC_intro() %></div>
	  </div>	 
	  <!-- second contents end-->
	  
	  <!-- third contents -->
	  <div class="promotion-content-bg content-box">	   
	  	<h1 class="card-title">채널 컨텐츠 안내</h1>
	  	<div class="promotion-content-desc"><%= cr.getC_content() %></div>
	  </div>
	  <!-- third contents end--> 
</div>
</body>
</html>





