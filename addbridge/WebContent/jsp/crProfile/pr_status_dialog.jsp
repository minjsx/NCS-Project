<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
    System.out.println(ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=ctx%>/css/reset.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<%=ctx%>/css/font.css">
<link rel="stylesheet" href="<%=ctx%>/css/comu_dialog.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<script src = '<%=ctx%>/youtube/lib/jquery-3.1.1.js'></script>
<!-- <script src = 'http://code.jquery-1.9.1.min.js'></script> -->
<!-- <script src = 'http://ajax.googleapis.com/ajax/lib/jquery-1.11.1/jquery.min.js'></script> -->
<script language=javascript> 
moveTo(0,0)
resizeTo(400,310)
</script>
<script language="JavaScript">

top.window.moveTo(0,0);
if (document.all) {
top.window.resizeTo(screen.availWidth,screen.availHeight);
}
else if (document.layers||document.getElementById) {
if (top.window.outerHeight<screen.availHeight||top.window.outerWidth<screen.availWidth){
top.window.outerHeight = screen.availHeight;
top.window.outerWidth = screen.availWidth;
}
}

var checked = false;	// 체크여부
function agreeChecked(checkedElement) {
		if (checked == false) {
			checkedElement.style.color = "red";	// 체크 시 레드
			checked = true;
		} else {
			checkedElement.style.color = "gray";	// 체크해제 시 그레이
			checked = false;
		}
	}

function check(kiki){
	if( checked == false){
		alert('내용을 확인 하신 후, 체크를 해주세요.');
	} else{
		alert("확인되었습니다. 진행상황은 '진행중인 프로모션'에서 확인 하실 수 있습니다.");
		window.close(kiki);
	}
}


</script>

</head>
<body>
	<div class="all_section_comu">
	<form id="frm" name ="frm" class="form_span" >
		<br/>
		<div class="top_section_comu"><i class="far fa-bell" style="margin-right:right=10px"></i> 알림</div><br/>
		<div><br/><br/>
		<div><p> 지원자와 충분히 연락을 하신 후 신중히 수락해주세요.</p></div><br/>
		<div><p> 지원을 수락하시면 해당 프로모션 공고가 삭제됩니다.</p></div><br/><br/>
		<div class="read_proceeding_2"><label for="agree"> <span class="red">*</span> 해당 사실을 충분히 전달 받았습니다.</label> 
		<span class="form-checkbox" id="U_checkArg" style="color: gray;" onclick="agreeChecked(this)"> <i class="far fa-check-circle"></i></span></div><br/><br/>
		<input  type="button" class="complete_msg" value="수락하기" onclick="check(this)"/>&nbsp;<input type="button" class="cancel_msg" value="취소" onclick="javascript:window.close()"/><br/><br/>
		</div>
	</form>
	</div>
</body>
</html>