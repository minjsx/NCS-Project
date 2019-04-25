<%@page import="ad.model.Proceeding"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Proceeding> pList = (List<Proceeding>)request.getAttribute("Proceeding_cr");	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>진행중인 프로모션</title>
<link href="<%=request.getContextPath() %>/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/css/font.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/pro_proceeding.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script language="JavaScript">
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
		alert('확인되었습니다. 메인으로 이동합니다.');		
		window.location.href="<%=request.getContextPath()%>/MainControl?cmd=index";
		//석현아 여기야 도와줘!
	}
}

 function sendMsg() {
	
	var winObj = window.open("","newWin", "width=495,heigth=540");
	winObj.location.href = "<%=request.getContextPath()%>/jsp/crProfile/comu_dialog.jsp?proceeding_no=<%=pList.get(0).getProceeding_no()%>";  
} 

</script>

</head>
<body>
<form id="msg" name="msg" method="post" >
<br/>
   <br/><br/><br/>
   <div><h1 class="title_proceeding">진행중인 프로모션</h1></div><br/><br/>
   <div class="site_proceeding"><h2 class="h2_register">프로모션명</h2></div>
   <hr class="hr_proceeding"><br/>
   <div class="site_promotion">
   <div class="div_font_pr"></div>
   <div class="font_promotion"><%=pList.get(0).getP_name() %></div>
   <div class="div_font_pr"></div>
   </div><br/><br/>
   
  
   <h2 class="h2_simple_register">기본정보</h2>
   <input type="hidden" name ="proceeding_no" value="<%=pList.get(0).getProceeding_no()%>"/>
   <input type="hidden" name ="c_no" value="<%=pList.get(0).getC_no()%>"/>
   <input type="button" value="메세지함" class="message_register_btn" onclick="sendMsg()"/><br/>
</form>
   <hr class="hr_proceeding"><br/>
   <div class="site_all_proceeding" >
   <table class="site_table_proceeding">
   		<tr>
   			<td class="site_table_proceeding_1" > 진행일시 </td>
   			<td class="site_table_proceeding_2"> <%=pList.get(0).getPp_startDate() %></td>
   		</tr>
   		<tr>
   			<td class="site_table_proceeding_1"> 콘텐츠 단가 </td>
   			<td class="site_table_proceeding_2"> <%=pList.get(0).getP_price() %></td>
   		</tr>
   		<tr>
   			<td class="site_table_proceeding_1"> 제작 기간 </td>
   			<td class="site_table_proceeding_2"> <%=pList.get(0).getP_period() %></td>
   		</tr>
   		<tr>
   			<td class="site_table_proceeding_1"> 연락처 </td>
   			<td class="site_table_proceeding_2"> <%=pList.get(0).getM_tel() %></td>
   		</tr>
   		<tr>
   			<td class="site_table_proceeding_1"> 이메일 </td>
   			<td class="site_table_proceeding_2"> <%=pList.get(0).getM_email() %></td>
   		</tr>
   		<tr>
   			<td class="site_table_proceeding_1"> 선호미팅장소 </td>
   			<td class="site_table_proceeding_2"> <%=pList.get(0).getCi_region() %></td>
   		</tr>
   		<tr>
   			<td class="site_table_proceeding_1"> 파트너명 </td>
   			<td class="site_table_proceeding_2"> <%=pList.get(0).getC_name() %></td>
   		</tr>
   </table>
   </div><br/><br/>
   <form>
   
   <div  class="site_proceeding"><h2 class="h2_register"> 제작 정보</h2></div>
   <hr class="hr_proceeding"><br/><br/><br/>
   <div  class="site_proceeding">
   		<div class="textarea_color"> 프로모션 정보</div>
   		<div> <textarea class="textarea_site" required autocomplete="off" style="resize: none; overflow: scroll;">  <%=pList.get(0).getP_info().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "") %></textarea></div>
   </div><br/><br/>
   
   <div class="site_proceeding">
   		<div class="textarea_color"> 제작 컨텐츠 정보</div>
   		<div> <textarea class="textarea_site" required autocomplete="off" style="resize: none; overflow: scroll;"> <%=pList.get(0).getP_content().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "") %> </textarea></div>
   </div><br/><br/><br/><br/><br/>
   <div class="site_proceeding">
   <label for="agree"> <span class="red">*</span> 프로모션을 완료 하시면 해당 프로모션은 종료됩니다. 해당 사실을 충분히 전달 받았습니다.</label> 
   <span class="form-checkbox" id="U_checkArg" style="color: gray;" onclick="agreeChecked(this)"> <i class="far fa-check-circle"></i>
   </span></div><br/><br/><br/>

   	<span style="margin-left: 370px; margin-right: 20px">
   	<input type="button" class="cacel_button" value="프로모션 취소"></span>
	<input type="button" class="check_button" value="프로모션 완료" onclick="check(this)"><br/><br/>
	<!-- 이따 합시다 -->
	</form>
</body>
</html>