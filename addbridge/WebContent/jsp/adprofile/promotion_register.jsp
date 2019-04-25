<%@page import="ad.model.Advertiser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로모션 등록</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cr_flex_register.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font.css" type="text/css">
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="<%=request.getContextPath()%>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/profile.js"></script>

<%
	Advertiser at = session.getAttribute("user") instanceof Advertiser ? (Advertiser)session.getAttribute("user") : null;
	int cash = 0;
	
	if(at == null){
		response.sendRedirect(request.getContextPath() + "/MainControl?cmd=index");
	}
	else{
		cash = at.getCash();
	}
	
%>

<script type="text/javascript">
var oEditors = [];
var oEditors2 = [];

$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1",
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "<%=request.getContextPath() %>/SE2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
          }, 
      });     
      
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors2,
          elPlaceHolder: "ir2",
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "<%=request.getContextPath() %>/SE2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
          }, 
      });     
      //저장버튼 클릭시 form 전송
      
      
      $("#savebtn").click(function(){
    	  oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
    	  oEditors2.getById["ir2"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
      });  
});
</script>

</head>
<body>

	<div class="promotion-register-title">
	<h1 class="h1_register first-title" >프로모션 등록</h1>
	<p class="sub_title">프로모션을 이곳에서 등록할 수 있습니다.</p>
	</div>

	<form class="promotion-register-form" id="frm" method="post" action="<%=request.getContextPath()%>/jsp/promotionManagement/promotionflex.jsp" enctype="multipart/form-data">
	<div class="promotion-register-content first-content">
	<div class="title_in_register">
	<b>제목</b>
	<input name="title" class="title_input_register" placeholder="프로모션의 제목을 입력해 주세요." required autocomplete="off" />
	</div>
	<div class="title_in_register">
		<b>프로모션 대표 이미지</b>
		<div class="img-margin">
			<span>
			<%-- if request 로 정보있으면 받고 없으면 받지말게 --%>
			<img id="uploadPreview" style="width: 150px; height: 150px;" />
			</span> 
			<span style="margin-left: 20px"> 
			<input type="file" name="profileImage" class="button" onchange="handleFilePath(this);" style="vertical-align: super" />
			</span>
		</div>
	</div>
	<div class="title_in_register">
	<b>프로모션 소개</b>
	<textarea name="promotion" required autocomplete="off" rows="10" cols="30" name="ir1" id="ir1" style="width:650px; height:350px; "></textarea>
	</div>
	<div class="title_in_register">
	<b>제작컨텐츠 안내</b>
	<textarea name="contentinfo" required autocomplete="off" rows="10" cols="30" name="ir2" id="ir2" style="width:650px; height:350px; "></textarea>
	</div>
	</div>
	<div class="promotion-register-content second-content">
	
	           <div class="title_in_register">
	               <h2 class="h2_register first-title">작업/기본 단가</h2>
	           </div>
	           <hr class="hr_register">
	
	           <div class="title_in_register">
	            <p class="div_site"><span class="red">*</span>자격요건</p>
	            <select class="select_register first-select" name="s1">
	         	<option value="자격요건">자격요건</option>
	         	<option value="구독자 수 10만 이상">구독자 수 10만 이상</option>
	        	<option value="구독자 수 50만 이상">구독자 수 50만 이상</option>
	        	<option value="구독자 수 100만 이상">구독자 수 100만 이상</option>
	         	<option value="주 시청연령 - 남성">주 시청연령 - 남성</option>
	        	<option value="주 시청연령 - 여성">주 시청연령 - 여성</option>
	            </select>
	        </div>
	
	        <div class="title_in_register">
	             <p class="div_site"><span class="red">*</span>프로모션 유형</p>
	            <select class="select_register second-select" name="s2">
	            <option value="프로모션 구분"> 프로모션 구분</option>
		    <option value="1">단순 제품 협찬</option>
	            <option value="2">유튜브 내 컨텐츠 제작</option>
	            <option value="3">제품 광고 제작</option>
	            <option value="4">유튜브 PPL</option>
	            </select>
	        </div>
	
	    	<div class="title_in_register">
		    	  <div class="div_site"><span class="red">*</span>카테고리</div>
			      <select class="select_register third-select" name="s3">
		        	 <option value="카테고리">카테고리</option>
		         	 <option value="1">게임</option>
			         <option value="2">IT기술</option>
			         <option value="3">푸드</option>
			         <option value="4">패션</option>
			         <option value="5">뷰티</option>
			         <option value="6">토이키즈</option>
			         <option value="7">영화</option>
			         <option value="8">교육</option>
			         <option value="9">운동</option>
			         <option value="10">엔터테인먼트</option>
		      	  </select>
	       </div>
	
	        <div class="title_in_register">
	             <p class="div_site"><span class="red">*</span>제작기간</p>
	            <input class="input_register" placeholder="프로모션 제작일을 정확히 입력해주세요(DD)" required autocomplete="off" name="period" />
	        </div>
	
	        <div class="title_in_register">
	            <p class="div_site"><span class="red">*</span>기본 단가(원)</p>
	            <input name="price" type="number" class="input_register" onkeyup="fn_change_hangul_money(this);" maxlength="12" required autocomplete="off" 
	            onkeypress="javascript:if((event.keyCode<48)||(event.keyCode>57))event.returnValue=false;" placeholder="프로모션 진행시 생각하시는 최소 단가를 입력" name="price" />
	            <span id="han_money" class="money">금액표시</span>
	        </div>
	
	        <div class="title_in_register">
	            <h2 class="h2_register first-title">결제하기 </h2>
	        </div>
	
	        <hr class="hr_register">
	
	        <div class="title_in_register promotion-cash">
	            <p>총 보유 캐시</p>
	            <b>
	            <span style="color: rgb(52, 152, 219);     font-weight: bold;"><%= cash %></span>
	            원</b>
	        </div>
	        <div class="title_in_register promotion-cash second-cash">
	            <p>총 결제 금액</p>
	            <b>
	            <span style="color: rgb(231, 76, 60);     font-weight: bold;">10000</span>
	            원</b>
	        </div>
	        <hr class="hr_register">
	        <div class="title_in_register promotion-cash">
	             <p>결제 후 잔액</p>
	             <b>
	             <span style="color: rgb(231, 76, 60);     font-weight: bold;"><%= cash - 10000 %></span>
	             원</b>
	        </div>
	        <div class="promotion-register-buttons">
	            <input type="button" class="button cacel_button" value="취 소">
	            <input id="savebtn" type="button" class="button check_button" value="등록하기">
	        </div>
	   </div>
	</form>
</body>
</html>