<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ad.model.*"%>
<%@ page import="java.util.List"%>

<%
	request.setCharacterEncoding("UTF-8");

	int msg_sno = 0; //보내는 회원번호
	int c_no = Integer.parseInt(request.getParameter("c_no")); //받는 회원번호 c_no
	int proceeding_no = Integer.parseInt(request.getParameter("proceeding_no")); //진행중인 프로모션 번호
	int msg_rno = MessageDao.getInstance().getMemNo(c_no);
	/* 세션 */
	Object user = session.getAttribute("user");
	if (user instanceof Advertiser) {
		Advertiser advertiser = (Advertiser) user;
		
		msg_sno = advertiser.getMemNo(); //보내는 회원번호(광고주 회원번호)
		
	} else if (user instanceof Creator) {

	} else if (user == null) {
		//ContextPath 조심히 사용하자...
		out.print("<script> alert('세션이 만료되었습니다. 다시 로그인해주세요.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=main' </script>");

	} else {
		//TODO : (잘못된 경로로 접속하셨습니다.) 알림창을 띄우고, NOLOGIN 메인페이지로 
		out.println("<script> alert('잘못된 경로로 접속하셨습니다.'); document.location.href = '" + request.getContextPath() + "/MainControl?cmd=main' </script>");
	} 
	/* 세션end*/
	
	List<Member> member = (List<Member>)MessageDao.getInstance().partnerInfo(msg_rno); //파트너 정보 받기
	
	List<Message> msg = (List<Message>)MessageDao.getInstance().MessageList(msg_sno, msg_rno, proceeding_no);
	
	String msg_content = request.getParameter("msg_content"); //메세지내용
	
	if(msg_content == null || msg_content.length() == 0) {
		System.out.println("메세지내용 X");
	}
	else if(msg_content != null && msg_content.length() > 0) {
		System.out.println("메세지내용 :: " + msg_content);
		MessageDao.getInstance().SendMsg(proceeding_no, msg_sno, msg_rno, msg_content);
				
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>애드브릿지 ADDBRIDGE - 메세지함</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/comu_dialog.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" />
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/icon/mini-icon.png">
<script type="text/javascript">
	
	resizeTo(495, 540)
	if (document.all) {
		top.window.resizeTo(screen.availWidth, screen.availHeight);
	} else if (document.layers || document.getElementById) {
		if (top.window.outerHeight < screen.availHeight
				|| top.window.outerWidth < screen.availWidth) {
			top.window.outerHeight = screen.availHeight;
			top.window.outerWidth = screen.availWidth;
		}
	}
	
	window.onkeydown = function() {
		var kcode = event.keyCode;
		if(kcode == 116) event.returnValue = false;
	}
	

</script>

</head>
<body>

	<div class="all_section_comu">
		<form id="frm" name="frm" class="form_span" method="post" action="ad_comu_dialog.jsp" >
			<br />
			<div class="top_section_comu">
				<i class="far fa-comment-alt" style="margin-right: right=10px"></i>
				1:1 대화창
			</div>
			<br />
			<div>
				<div>
					파트너 &nbsp;
					<p class="partner_comu"><%=member.get(0).getName()%></p>
				</div>
				<br />
				<div class="div_all_comu">
					<div class="div_call_comu">
						연락처 &nbsp;
						<p class="phone_comu"><%=member.get(0).getPhone()%></p>
					</div>
					<br />
					<br />
					<div class="div_call_comu">
						이메일 &nbsp;
						<p class="email_comu"><%=member.get(0).getEmail()%></p>
					</div>
				</div>
				<div class="font_caution_comu" style="margin-top: 20px">※ 컨텐츠 계약시 파트너와 전화 통화를 하신 후 거래를 진행해 주시기 바랍니다.</div>
				<br />
				
				<div id="txtarea" name="txtarea" style="overflow: scroll; width: 444px; height: 189px; border: solid 3px gray">
					<% if(msg.size() == 0) { %>
		
				<div id="nomessage" style="text-align: center;">메세지가 없습니다.</div>
					<% } else {
					for(int i=0; i < msg.size(); i++) { %>
						<div style="display: flex;">
							<% if(msg_sno == msg.get(i).getMsg_sno()) {%>
								<p style="font-size: 18px; color: rgb(243, 156, 18);"><%=msg.get(i).getSendName() %><p/>
							<%} else {%>
								<p style="font-size: 18px; color :rgb(52, 152, 219);"><%=msg.get(i).getSendName() %><p/>
							<%} %>
							<P style="font-size: 10px;margin-left: 5px; margin-top: 7px;"><%=msg.get(i).getMsg_date() %><p/>
						</div>
						<br/>
						<div><%=msg.get(i).getMsg_content() %></div>
						<br/>
					<%}
				}%>
				</div>
				
				<br />
				<br /> <input class="content_msg_receive" name="msg_content"/> &nbsp;
					   <input type="hidden" name="c_no" value="<%=c_no%>"/>
					   <input type="hidden" name="proceeding_no" value="<%=proceeding_no%>"/>
					   <input id="send_msg" type="submit" class="send_msg" value="보내기"><br />
				<br />
				<div class="refresh_comu">* 본 대화창은 새로고침(F5)을 사용 할 수 없습니다.</div>
			</div>
		</form>
	</div>
</body>
</html>