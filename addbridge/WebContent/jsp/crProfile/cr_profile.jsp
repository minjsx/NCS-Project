<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.Member"%>
<%@page import="ad.model.Creator"%>
<%@page import="ad.model.Advertiser"%>
<%
	String ctx = request.getContextPath();
%>
<%
	// 0. 넘겨받는 한글 깨지지 않도록 지정
	request.setCharacterEncoding("UTF-8");
%>
<%
	Object user = session.getAttribute("user");
	Creator creator = (Creator) user;

	int mem_no = 0;
	if (user instanceof Creator) {
		mem_no = creator.getMem_no();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로필 입력하기</title>
<link rel="stylesheet" href="<%=ctx%>/css/font.css">
<link href="<%=ctx%>/css/reset.css" rel="stylesheet" type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="<%=ctx%>/js/profile.js"></script>
<style>
.navbar-header {
	background_color: Yellow;
}

/<
span class ="red ">* </span>Remove the navbar 's default margin-bottom and rounded borders 
    <span class ="red ">* </span> / .navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/<
span class ="red ">* </span>Set height of the grid so .sidenav can be 
    100% (adjust as needed ) <span class ="red ">* </span> / .row.content
	{
	height: 450px
}

/<
span class ="red ">* </span>Set gray background color and 100% height <span class
    ="red ">* </span> / .sidenav {
	padding-top: 20px;
	background-color: white;
	height: 100%;
}

/<
span class ="red ">* </span>Set black background color, white text and some padding 
    <span class ="red ">* </span> / footer {
	background-color: #555;
	color: white;
	padding: 15px;
}
/<
span
class
="
red
"
>
*
</
span
>
Onsmallscreens
,
setheightto
'
auto
'
forsidenavandgrid
<
spanclass
="red"
>
*
</
span
>
/
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>

<script type="text/javascript">
   function checkFields(index)
   {
      var frmObj = document.forms[index];
      
      frmObj.action = "<%=ctx%>/MainControl?cmd=cr-profile";
      frmObj.method = "post";
      frmObj.submit();
   }
   
   function updateFields(index)
   {
      var frmObj = document.forms[index];
   
      frmObj.action = "<%=ctx%>/MainControl?cmd=cr-ud-profile";
      frmObj.method = "post";
      frmObj.submit();
   }
   
   function cancel(index) 
   {
	   var frmObj = document.forms[index];
	   
	   frmObj.action = "<%=ctx%>/MainControl?cmd=crmain";
	   frmObj.method = "post";
	   frmObj.submit();
   }
   
   
   $(function(){
      $("#ci_ad").val("<%=creator.getCi_ad()%>").attr("selected","selected");      
      $("#cg_name").val("<%=creator.getCg_no()%>").attr("selected","selected");
	});
</script>
</head>
<body>

	<%
		if (creator.getC_name() == null) {
	%>
	<br />
	<br />
	<h1 style="font-weight: bold; font-size: 36px;">
		<strong>기본정보</strong>
	</h1>
	<br />
	<br />
	<br />
	<p>
		<span class="red">*</span> <strong> 필수정보를 입력하셔야, 정상적인 서비스를
			이용하실 수 있습니다. </strong>
	</p>
	<br />
	<br />

	<form name="frm" enctype="multipart/form-data">
		<p>
			<input type="hidden" name="mem_no" value="<%=mem_no%>" /> <span
				class="red">*</span> <span style="margin-right: 43px">크리에이터 명</span>
			<input name="c_name" class="input235_3"
				placeholder="본인의 크리에이터 명을 입력해 주세요." required autocomplete="off" />
			&nbsp; <span class="red">*</span> <span style="margin-right: 28px">구독자
				수</span> <input name="c_count" class="input235_3"
				placeholder="본인의 구독자 수를 입력." required autocomplete="off"
				onkeyup="fn_change_hangul_sub(this);" maxlength="12" /> <span
				id="sub_id" class="money"> 숫자만 입력. </span> <br /> <br /> <span
				class="red">*</span> <span style="margin-right: 72px">채널 이름</span> <input
				name="c_chanel" class="input6_3" placeholder="본인의 채널 이름을 입력해 주세요."
				required autocomplete="off" />
		</p>
		<p>
			<span class="red">*</span> 프로필 사진
		</p>
		<p>
			<span style="margin-left: 160px"><img id="uploadPreview"
				style="width: 150px; height: 150px;" /> <span
				style="margin-left: 20px"> 
				<input type="file" name="profileImage" class="load_picture_button" onchange="handleFilePath(this);" required autocomplete="off" /></span></span>
		</p>
		<p>
			<span class="red">*</span>본인 소개
		</p>
		<p>
			<span style="margin-left: 160px"> <textarea id="c_intro"
					name="c_intro" style="width: 600px; height: 300px" required
					autocomplete="off"></textarea>
			</span>
		</p>
		<br /> <br />
		<p>
			<span class="red">*</span>컨텐츠 소개
		</p>
		<p>
			<span style="margin-left: 160px"> <textarea id="c_content"
					name="c_content" style="width: 600px; height: 300px" required
					autocomplete="off"></textarea>
			</span>
		</p>
		<br /> <br />
		<p>
			<span class="red">*</span> <span style="margin-right: 43px">미팅
				가능지역</span> <input name="ci_region" class="input6_3"
				placeholder="미팅 가능 지역을 입력해 주세요." required autocomplete="off" />
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 26px">시제품
				배송 주소 </span> <span style="margin-right: 20px"> <input
				name="sample6_postcode" style="width: 110px; height: 35px"
				id="sample6_postcode" placeholder="우편번호" required autocomplete="off" /></span>
			<input name="sample6_address" style="width: 364px; height: 35px"
				id="sample6_address" placeholder="주소" /> <input
				style="width: 95px; height: 40px" type="button"
				onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
		</p>
		<br />
		<p>
			<span style="margin-left: 162px"> <input
				name="sample6_extraAddress" type="text"
				style="width: 300px; height: 35px" id="sample6_extraAddress"
				placeholder="상세주소1"></span><span style="margin-left: 18px">
				<input name="sample6_detailAddres"
				style="width: 275px; height: 35px" id="sample6_detailAddress"
				placeholder="상세주소2" />
			</span>
		</p>
		<br /> <br /> <br /> <br /> <br />
		<h3>크리에이터 프로필</h3>
		<br /> <br />
		<p>
			<span class="red">*</span> 필수정보를 입력하셔야, 정상적인 서비스를 이용하실 수 있습니다.
		</p>
		<p>내 프로필 페이지 https://localhost/detail/idxNO</p>
		<p>프로필페이지는 자신의 PR 페이지로서, 기업회원들에게 공개됩니다.</p>
		<br /> <br />
		<h2>내 채널 정보</h2>
		<br />
		<hr>
		<p>
			<span class="red">*</span> <span style="margin-right: 32px">유튜브
				카테고리</span> <select name="category" style="width: 275px; height: 35px">
				<option value="1">게임</option>
				<option value="2">IT기술</option>
				<option value="3">푸드</option>
				<option value="4">패션</option>
				<option value="5">뷰티</option>
				<option value="6">토이&키즈</option>
				<option value="7">영화</option>
				<option value="8">교육</option>
				<option value="9">운동</option>
				<option value="10">엔터테인먼트</option>
			</select>
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 28px">유튜브
				채널 URL</span> <input name="c_url" class="input6_3"
				placeholder="https://www.youtube.com/channel/UCjGMEHhtVyhCJGntCw2hb4w"
				required autocomplete="off" />
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 28px">유튜브
				영상 URL </span> <input name="c_rurl" class="input6_3"
				placeholder="본인을 홍보할 수 있는 대표적인 유튜브 영상의 url을 올려주세요." required
				autocomplete="off" />
		</p>
		<br /> <br /> <br /> <br /> <br />
		<h2>작업/기본단가</h2>
		<br />
		<hr>
		<p>
			<span class="red">*</span> <span style="margin-right: 51px">기본
				단가(원)</span> <input type="number" class="input35_3_won" required
				autocomplete="off" placeholder="프로모션 진행시 생각하시는 최소 단가를 입력해 주세요."
				name="ci_price" id="c_price" onkeyup="fn_change_hangul_money(this);"
				maxlength="12" /> <span id="han_money" class="money">금액표시</span>
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 37px">평균영상작업일</span>
			<input name="ci_term" class="workDay" required autocomplete="off"
				onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
				placeholder="영상 의뢰에서 제작까지의 평균 작업일을 입력하세요." name="workDay" /> <span
				class="red">숫자만 입력</span>
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 15px">선호
				프로모션 유형 </span> <select name="ci_ad" style="width: 275px; height: 35px">
				<option value="0">프로모션 구분</option>
				<option value="유튜브 PPL">유튜브 PPL</option>
				<option value="단순 제품 협찬">단순 제품 협찬</option>
				<option value="유튜브 내 컨텐츠 제작">유튜브 내 컨텐츠 제작</option>
				<option value="제품 광고 제작">제품 광고 제작</option>
			</select>
		</p>
		<br /> <br /> <br /> <span
			style="margin-left: 370px; margin-right: 20px"><input
			type="button" class="cacel_button" value="취소" onclick="cancel(0)"></span>
		<input type="button" class="check_button" value="저장하기"
			onclick="checkFields(0)" /> <br /> <br /> <br /> <br />
	</form>

	<%
		} else {
	%>
	<%
		String[] sp = creator.getCi_addr().split("#");
	%>
	<br />
	<br />
	<h1 style="font-weight: bold; font-size: 36px;">
		<strong>기본정보</strong>
	</h1>
	<br />
	<br />
	<br />
	<p>
		<span class="red">*</span> <strong> 필수정보를 입력하셔야, 정상적인 서비스를
			이용하실 수 있습니다. </strong>
	</p>
	<br />
	<br />

	<form name="frm_1">
		<p>
			<input type="hidden" name="mem_no" value="<%=mem_no%>" /> <span
				class="red">*</span> <span style="margin-right: 43px">크리에이터 명</span>
			<input name="c_name" class="input235_3"
				placeholder="본인의 크리에이터 명을 입력해 주세요." required autocomplete="off"
				value="<%=creator.getC_name()%>" /> &nbsp; <span class="red">*</span>
			<span style="margin-right: 28px">구독자 수</span> <input name="c_count"
				class="input235_3" placeholder="본인의 구독자 수를 입력." required
				autocomplete="off" value="<%=creator.getC_count()%>"
				onkeyup="fn_change_hangul_sub(this);" maxlength="12" /> <span
				id="sub_id" class="money"></span> <br /> <br /> <span class="red">*</span>
			<span style="margin-right: 72px">채널 이름</span> <input name="c_chanel"
				class="input6_3" placeholder="본인의 채널 이름을 입력해 주세요." required
				autocomplete="off" value="<%=creator.getC_chanel()%>" />
		</p>
		<p>
			<span class="red">*</span> 프로필 사진
		</p>
		<p>
			<span style="margin-left: 160px"><img id="uploadPreview"
				style="width: 150px; height: 150px;" src="<%= creator.getImg_src() %>"/> <span
				style="margin-left: 20px"> <input type="file" name="profileImage"
					class="load_picture_button" onchange="handleFilePath(this);"
					required autocomplete="off" /></span></span>
		</p>
		<p>
			<span class="red">*</span>본인 소개
		</p>
		<p>
			<span style="margin-left: 160px"> <textarea name="c_intro"
					style="width: 600px; height: 300px" required autocomplete="off"><%=creator.getC_intro()%></textarea></span>
		</p>
		<br /> <br />
		<p>
			<span class="red">*</span>컨텐츠 소개
		</p>
		<p>
			<span style="margin-left: 160px"> <textarea name="c_content"
					style="width: 600px; height: 300px" required autocomplete="off"><%=creator.getC_content()%></textarea>
			</span>
		</p>
		<br /> <br />
		<p>
			<span class="red">*</span> <span style="margin-right: 43px">미팅
				가능지역</span> <input name="ci_region" class="input6_3"
				value="<%=creator.getCi_region()%>" placeholder="미팅 가능 지역을 입력해 주세요."
				required autocomplete="off" />
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 26px">시제품
				배송 주소 </span> <span style="margin-right: 20px"> <input
				name="sample6_postcode" value="<%=sp[0]%>"
				style="width: 110px; height: 35px" id="sample6_postcode"
				placeholder="우편번호" required autocomplete="off" /></span> <input
				name="sample6_address" value="<%=sp[1]%>"
				style="width: 364px; height: 35px" id="sample6_address"
				placeholder="주소" /> <input style="width: 95px; height: 40px"
				type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
		</p>
		<br />
		<p>


			<span style="margin-left: 162px"> <input
				name="sample6_extraAddress" type="text"
				style="width: 300px; height: 35px" id="sample6_extraAddress"
				placeholder="상세주소1" value="<%=sp[2]%>">
			</span> <span style="margin-left: 18px"> <input
				name="sample6_detailAddres" style="width: 275px; height: 35px"
				id="sample6_detailAddress" placeholder="상세주소2" value="<%=sp[3]%>" />
			</span>
		</p>
		<br /> <br /> <br /> <br /> <br />
		<h3>크리에이터 프로필</h3>
		<br /> <br />
		<p>
			<span class="red">*</span> 필수정보를 입력하셔야, 정상적인 서비스를 이용하실 수 있습니다.
		</p>
		<p>내 프로필 페이지 https://localhost/detail/idxNO</p>
		<p>프로필페이지는 자신의 PR 페이지로서, 기업회원들에게 공개됩니다.</p>
		<br /> <br />
		<h2>내 채널 정보</h2>
		<br />
		<hr>
		<p>
			<span class="red">*</span> <span style="margin-right: 32px">유튜브
				카테고리</span> <select name="category" id="cg_name"
				style="width: 275px; height: 35px">
				<option value="1">게임</option>
				<option value="2">IT기술</option>
				<option value="3">푸드</option>
				<option value="4">패션</option>
				<option value="5">뷰티</option>
				<option value="6">토이&키즈</option>
				<option value="7">영화</option>
				<option value="8">교육</option>
				<option value="9">운동</option>
				<option value="10">엔터테인먼트</option>
			</select>
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 28px">유튜브
				채널 URL</span> <input name="c_url" class="input6_3"
				value="<%=creator.getC_url()%>" required autocomplete="off" />
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 28px">유튜브
				영상 URL </span> <input name="c_rurl" class="input6_3"
				value="<%=creator.getC_rurl()%>" required autocomplete="off" />
		</p>
		<br /> <br /> <br /> <br /> <br />
		<h2>작업/기본단가</h2>
		<br />
		<hr>
		<p>
			<span class="red">*</span> <span style="margin-right: 51px">기본
				단가(원)</span> <input type="number" class="input35_3_won"
				value="<%=creator.getCi_price()%>" name="ci_price" id="c_price"
				onkeyup="fn_change_hangul_money(this);" maxlength="12" /> <span
				id="han_money" class="money">금액표시</span>
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 37px">평균영상작업일</span>
			<input name="ci_term" class="workDay"
				onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
				value="<%=creator.getCi_term()%>" name="workDay" /> <span
				class="red">숫자만 입력</span>
		</p>
		<br />
		<p>
			<span class="red">*</span> <span style="margin-right: 15px">선호
				프로모션 유형 </span> <select name="ci_ad" id="ci_ad"
				style="width: 275px; height: 35px">
				<option value="0">프로모션 구분</option>
				<option value="유튜브 PPL">유튜브 PPL</option>
				<option value="단순 제품 협찬">단순 제품 협찬</option>
				<option value="유튜브 내 컨텐츠 제작">유튜브 내 컨텐츠 제작</option>
				<option value="제품 광고 제작">제품 광고 제작</option>
			</select>
		</p>
		<br /> <br /> <br /> <span
			style="margin-left: 370px; margin-right: 20px"><input
			type="button" class="cacel_button" value="취 소" onclick="cancel(0)"></span>
		<input type="button" class="check_button" value="수정하기" onclick="updateFields(0)" /> 
		<br /> 
		<br /> 
		<br /> 
		<br />
	</form>

	<% } %>

</body>
</html>