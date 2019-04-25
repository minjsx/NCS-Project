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
Advertiser advertiser = (Advertiser) user;

int ad_no = 0;
String pass = "";
int cash = 0;

//int total = request.getParameter("cash_after");

if (user instanceof Advertiser) {
	ad_no = advertiser.getA_no();
	pass = advertiser.getPassword();
	cash = advertiser.getCash();
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>ADDBRIDGE-캐시충전</title>
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css">  -->
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath() %>/images/icon/mini-icon.png">
<style>
.ui-dialog-titlebar {
	background-color: rgb(26, 188, 156);
	color: white;
}

.ui-button {
	background-color: rgb(26, 188, 156);
	color: white;
}

.ui-button2 {
	margin-left: 87px;
}

.ui-dialog {
	border-style: solid;
	border-color: rgb(26, 188, 156);
	background-color: white;
}

.ui-dialog-content {
	background-color: white;
}

.ui-dialog-buttonset {
	background-color : white;
}

#pid {
	background-color: white;
}



</style>


<script type="text/javascript">
	resizeTo(500, 350)
	
	function close_cash(){
	  var winObj2 = window.close(this);	  
}

	$(document).ready(function() {
		$("#pw").click(function() {
			$("#pw").val("");
		});
	});

	$(function() {
		//$("#dialog").dialog();
		$("#charge_price").change(function() {
			var before_cash = $("#cash_before").val();
			var total = parseInt(this.value) + parseInt(before_cash);
			$("#cash_after").attr("value", total);
		});

		$("#dialog").dialog({
			autoOpen : true, //ìëì¼ë¡ ì´ë¦¬ì§ê²
			position : [ 5, 90 ], //x,y  ê°ì ì§ì 
			//"center", "left", "right", "top", "bottom"
			modal : true, //ëª¨ë¬ëíìì
			resizable : false, //í¬ê¸° ì¡°ì  ëª»íê²

			buttons : {		
				
				"취소" : function() {
					$(this).dialog("close");
					close_cash();
				},
				"충전" : function() {
					if ($("#pw").val() == '<%=pass%>') {	
					
						
						$("#cashForm").attr("action", "<%=request.getContextPath()%>/MainControl?cmd=cash-page");
						$("#cashForm").attr("method", "post");
						
					     var hiddenField1 = document.createElement("input");
				         hiddenField1.setAttribute("type", "hidden");
				         hiddenField1.setAttribute("name", "ad_no");
				         hiddenField1.setAttribute("value", <%=ad_no%>);
				         
				         var hiddenField2 = document.createElement("input");
				         hiddenField2.setAttribute("type", "hidden");
				         hiddenField2.setAttribute("name", "cash_after");
				         hiddenField2.setAttribute("value", $("#cash_after").attr("value"));
				         
				         var hiddenField3 = document.createElement("input");
				         hiddenField3.setAttribute("type", "hidden");
				         hiddenField3.setAttribute("name", "charge_price");
				         hiddenField3.setAttribute("value", $("#charge_price option:selected").attr("value"));
				         
				         $("#cashForm").append(hiddenField1);
				         $("#cashForm").append(hiddenField2);
				         $("#cashForm").append(hiddenField3);


						$("#cashForm").submit();
						
						//$("#dialog2").dialog("open");
						
						
					} else if($("#pw").val() == "" || $("#pw").val() != '<%=pass %>') {					
						$("#dialog3").dialog("open");
					}
				}
			}
		});     

		$("#dialog2").dialog({
			autoOpen : false, //ìëì¼ë¡ ì´ë¦¬ì§ìê²
			position : [ 450, 200 ], //x,y  ê°ì ì§ì 
			//"center", "left", "right", "top", "bottom"
			modal : true, //ëª¨ë¬ëíìì
			resizable : false, //í¬ê¸° ì¡°ì  ëª»íê²

			buttons : {
				"확인" : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#dialog3").dialog({
			autoOpen : false, //ìëì¼ë¡ ì´ë¦¬ì§ìê²
			position : [ 450, 200 ], //x,y  ê°ì ì§ì 
			//"center", "left", "right", "top", "bottom"ã´
			modal : true, //ëª¨ë¬ëíìì
			resizable : false, //í¬ê¸° ì¡°ì  ëª»íê²

			buttons : {
				"확인" : function() {
					$(this).dialog("close");
					//ì¸ë¹ì¨©ì´ í ê±°ì¼.
				}
			}
		});

	});
</script>

</head>
<body>
	<form id="cashForm">
		<!-- ì ëë¸ë¦¿ì§ ìºìì¶©ì  ë¤ì´ì¼ë¡ê·¸ -->
		<div id="dialog" title="▶애드브릿지 캐시충전">
			<p>
				충전금액<select id="charge_price" name="charge_price">
					<option value="">금액 선택</option>
					<option value="50000">50000</option>
					<option value="100000">100000</option>
					<option value="150000">150000</option>
					<option value="200000">200000</option>
				</select>
			</p>
			<p>
				비밀번호 <input type="password" id="pw" name="pw" />
			</p>
			<p>
				현재보유캐시 <input type="text" id="cash_before"
					name="cash_before" value="<%=cash%>" />
			</p>
			<p>
				충전후캐시 <input type="text" id="cash_after" name="cash_after">
			</p>
		</div>

		<!-- ìë¦¼1 ë¤ì´ì¼ë¡ê·¸ -->
		<div id="dialog2" title="알림">
			<p id="pid">캐시 충전이 되었습니다.</p>
		</div>


		<!-- ìë¦¼2 ë¤ì´ì¼ë¡ê·¸ -->
		<div id="dialog3" title="알림">
			<p id="pid">비밀번호를 제대로 입력해주세요.</p>
		</div>
	</form>

</body>
</html>