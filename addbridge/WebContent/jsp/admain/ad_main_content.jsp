<%@page import="ad.model.Creator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	List<Creator> clist = request.getAttribute("clist") != null && request.getAttribute("clist") instanceof List ? (List<Creator>)request.getAttribute("clist") : null;
    int pages = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
	int totNum = request.getAttribute("pageCount") != null &&  request.getAttribute("pageCount") instanceof Integer ? (int)request.getAttribute("pageCount") : 1;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"/>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/admain_content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/admain_content.js"></script>
</head>
<body>
	<!-- search creator -->
	<div class="search-creator-title">
		<img class="green-icon" alt="" src="<%=request.getContextPath()%>/images/icon/logo-icon_green.png" />
		<p>크리에이터 찾기</p>
	</div>
	<div class="search-creator-border">
		
			<div class="searchbox-combo-wrapper" id="js-combo-wrapper">
					<div class="searchbox-combo">
						<select id="procategory" name="procategory">
							<option value="0" selected="selected">프로모션 카테고리</option>
							<option value="단순제품협찬">#단순제품 협찬</option>
							<option value="유튜브컨텐츠제작">#유튜브 컨텐츠 제작</option>
							<option value="제품광고제작">#제품광고 제작</option>
							<option value="유튜브PPL">#유튜브 PPL</option>
						</select>
					</div>
					<div class="searchbox-combo">
						<select name="category" id="category">
							<option value="0" selected="selected">카테고리</option>
							<option value="1">#게임</option>
							<option value="2">#IT기술</option>
							<option value="3">#푸드</option>
							<option value="4">#패션</option>
							<option value="5">#뷰티</option>
							<option value="6">#토이&키즈</option>
							<option value="7">#영화</option>
							<option value="8">#교육</option>
							<option value="9">#운동</option>
							<option value="10">#엔터테인먼트</option>
						</select>
					</div>
					<div class="searchbox-combo combo-last">
						<select name="price" id="price">
							<option value="0" selected="selected">희망 단가</option>
							<option value="5000000~10000000">#500~1000</option>
							<option value="10000000~15000000">#1000~2000</option>
							<option value="20000000~50000000">#2000~5000</option>
						</select>
					</div>		
			</div>
			<div class="searchbox-textarea" id="searchbox-textarea"></div>
			<div class="searchbox-bot-wrapper">
				<input class="searchbox-txtinput" id="searchbox-txtinput" type="text" placeholder="유튜브의 채널명으로도 검색해보세요." /> 
				<input class="searchbox-search-btn" id="searchbox-searchbtn" type="button" value="검색" />
			</div>
		<form id="creator-search-form" action="<%=request.getContextPath()%>/MainControl?cmd=admain&page=1" method="post">
			<input name="param" id="search-hidden-value" type="hidden"/>
		</form>
	</div>
	<!-- search creator end -->


	<!-- search creator result -->
	<div class="search-creator-result">
			<img class="green-icon" alt="" src="<%=request.getContextPath()%>/images/icon/logo-icon_green.png" />
			<p>지금<span style="color: #1abc9c;"><%= clist.size() %></span>명의 크리에이터를 만나보세요</p>
	</div>

	<div class="card-grid" id="card-wrapper">
	<!-- search creator result end-->	
	<% for(int i = 0 ; i < clist.size() ; i++){ %>
	<!-- card start-->
		<div class="creators card">
		<!-- TODO: background URL을 clist에서 가져와서 바꿔야함.. -->
		    <div class="wrapper" style="background: url('<%= clist.get(i).getImg_src() %>') 20% 1%/cover no-repeat;">
		      <div class="category">
		        <span><%= clist.get(i).getCg_name() %></span>
		      </div>
		      <div class="data">
		        <div class="card-content">
		          <span class="option">구독 <%= clist.get(i).getC_count() %>, 단가 <%= clist.get(i).getCi_price() %></span>
		          <h1 class="author"><a href="<%=request.getContextPath()%>/MainControl?cmd=cr-detail&c_no=<%=clist.get(i).getC_no()%>"><%= clist.get(i).getC_name() %></a></h1>
		          <p class="text"><%= clist.get(i).getC_intro() %></p>
		          <label for="show-menu" class="menu-button"><a href="#" class="far fa-heart"></a></label>
		        </div>
		        <input type="checkbox" id="show-menu" />
		      </div>
		    </div>
	  	</div>
  	<!-- card end -->
  	<% } %>
  </div>
  	
  	
  	<div class="page-wrapper">
   <%-- 페이지 번호 출력 --%>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=admain&page=1">처음으로</a>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=admain&page=<%= pages <= 1 ? 1 : pages-1 %>">◀ Prev</a>
	  	  <%    for(int i = 1; i <= totNum; i++) { %>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=admain&page=<%=i%>"><span style="font-weight:600">[<%=i%>]</span></a>
	      <%  } %>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=admain&page=<%= pages >= totNum ? totNum : pages + 1%>">Next ▶</a>
	      <a href="<%=request.getContextPath()%>/MainControl?cmd=admain&page=<%=totNum%>">끝으로</a>
   </div>
</body>
</html>
