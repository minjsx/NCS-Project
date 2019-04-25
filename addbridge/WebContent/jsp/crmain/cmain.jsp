<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="ad.model.*"%>
<%@ page import="java.util.List"%>

<%
	List<YoutuberMain> proList = (List<YoutuberMain>) request.getAttribute("param");
	List<Category> cList = (List<Category>) request.getAttribute("cate");
	List<PromotionCategory> pList = (List<PromotionCategory>) request.getAttribute("pcate");
	int pages = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
	int totNum = request.getAttribute("pageCount") != null &&  request.getAttribute("pageCount") instanceof Integer ? (int)request.getAttribute("pageCount") : 1;
	
%>

<%
	Object user = session.getAttribute("user");

	int c_no = 0;
	//int p_no = 0;

	if (user instanceof Advertiser) {

	} else if (user instanceof Creator) {
		Creator creator = (Creator) user;
		//Promotion promotion = (Promotion) user;
		c_no = creator.getC_no();
		
		//p_no = promotion.getP_no();
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
<title>test</title>
<link href="<%=request.getContextPath()%>/css/crmain_content.css"
	rel="stylesheet" type="text/css" />
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/youtubemain.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/crmain_content.js"></script>
</head>
<body>
	<div class="content-border">
		<div class="ct">
			<img class="imgs" alt=""
				src="<%=request.getContextPath()%>/images/icon/logo-icon_green.png" />
			<p class="content-title">마케팅 프로모션 찾기</p>
		</div>
	</div>

	<div class="search-creator-border">
		<div id="js-combo-wrapper" class="promotion-combo-wrapper">
			<div class="promotion-combo">
				<select id="procategory" name="procategory">
					<option value="0" selected="selected">프로모션 구분</option>
					<%
						for (PromotionCategory p : pList) {
					%>
					<option value="<%=p.getPc_no()%>">#<%=p.getPc_name()%></option>
					<%
						}
					%>
				</select>
			</div>
			<div class="promotion-combo promotion-combo-middle">
				<select name="category" id="category">
					<option value="0" selected="selected">카테고리</option>
					<%
						for (Category c : cList) {
					%>
					<option value="<%=c.getCg_no()%>">#<%=c.getCg_name()%></option>
					<%
						}
					%>
				</select>
			</div>
			<div class="promotion-combo combo-last">
				<select name="price" id="price">
					<option value="0" selected="selected">희망단가</option>
					<option value="100~500">#희망단가 100 ~ 500</option>
					<option value="500~1000">#희망단가 500 ~ 1000</option>
					<option value="1000~1500">#희망단가 1000 ~ 1500</option>
					<option value="2000~5000">#희망단가 2000 ~ 5000</option>
				</select>
			</div>
		</div>

		<div class="txtarea" id="txtarea"></div>

		<div class="t_margin">
			<input class="txtinput" id="txtinput" type="text" placeholder="제품명이나 브랜드명으로도 검색해보세요!" />
			<input class="searchbox-search-btn" id="searchbox-searchbtn" type="button" value="검색" />
		</div>
		<form id="promo-search-form" action="<%=request.getContextPath()%>/MainControl?cmd=crmain&page=1" method="post">
			<input name="searchInfo" id="search-hidden-value" type="hidden"/>
		</form>
	</div>

	<div class="content-border">
		<div class="ct">
			<img class="imgs" alt=""
				src="<%=request.getContextPath()%>/images/icon/logo-icon_green.png" />
			<p class="content-title">
				지금 <span class="color_label"><%=proList.size()%></span> 건의 프로모션을
				만나보세요!
			</p>
		</div>
	</div>

	<div class="content-border">

			
			<div class="card-wrapper" id="card-wrapper">
				<%
					for (YoutuberMain ym : proList) {
				%>
		<form action="<%=request.getContextPath()%>/MainControl?cmd=list-page2"
			   id="frm2" method="post">
				<div class="card-border">
					<div class="card-img">
						<a href="" class="card-item"> <img
							src="<%= ym.getProImage() %>"
							class="gallery-img" alt="Image" />
						</a>
					</div>
					<div class="card-contents">
						<div class="activity-date">
							<b><%=ym.getStartDate()%> ~ <%=ym.getEndDate()%> 마감</b>
						</div>

						<div class="activity-name">
							<b><%=ym.getProName()%></b>
						</div>
						<div class="activity-price">
							<div><%=ym.getAdvertiser()%></div>
							<p><%=ym.getProPrice()%>원</p>
						</div>
						<div class="activity-company">
							<img src="<%= ym.getAdImage() %>" />
						</div>
						<div class="activity-btns">
							<div class="activity-btn-buy">
								<button type="submit">프로모션 지원</button>
								<input type="hidden" name="p_no" value="<%=ym.getP_no()%>" />
								
							</div>
						</div>
					</div>
				</div>
		</form>
				<%
					}
				%>

			</div>
	
	</div>
	
	<div class="page-wrapper">
   <%-- 페이지 번호 출력 --%>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=crmain&page=1">처음으로</a>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=crmain&page=<%= pages <= 1 ? 1 : pages-1 %>">◀ Prev</a>
	  	  <%    for(int i = 1; i <= totNum; i++) { %>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=crmain&page=<%=i%>"><span style="font-weight:600">[<%=i%>]</span></a>
	      <%  } %>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=crmain&page=<%= pages >= totNum ? totNum : pages + 1%>">Next ▶</a>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=crmain&page=<%=totNum%>">끝으로</a>
   </div>
	
</body>
</html>
