$(function() {
	
	//프로모션구분 선택
	$('#protyoe').click(function() {
		var str = "";
		var index = $("#protyoe option").index($("#protyoe option:selected"));
		
		var pro = $('#protyoe');
	
		if($("#protyoe option:selected").val() == 0) {
			str ="";
		} 
		else {
			var str = $("#protyoe option:selected").text();	
		}
		
		if(str != "") {
			$('#protyoe option:eq(0)').prop("selected", true);
			$('#txtarea').append("<div id='tag_button' class='tag teal'>" + str + "<input type='button' class='btn' id='btn' value='X' />" + "</div>&nbsp");
			$('#protyoe option[value="'+ index + '"]').attr('disabled','disabled');	
			$('#hintdiv').remove();
		}
		
	});	
	
	//카테고리 선택
	$('#category').click(function() {
		var str = "";
		var index = $("#category option").index($("#category option:selected"));
		
		if($("#category option:selected").val() == 0) {
			str ="";
		} 
		else {
			var str = $("#category option:selected").text();	
		}
		
		if(str != "") {
			$('#category option:eq(0)').prop("selected", true);
			$('#txtarea').append("<div id='tag_button' class='tag teal'>" + str + "<input type='button' class='btn' id='btn' value='X' />" + "</div>&nbsp");
			$('#category option[value="'+ index + '"]').attr('disabled','disabled');
			$('#hintdiv').remove();
		}
	});	
	
	//희망단가 선택
	$('#price').click(function() {
		var str = "";
		var index = $("#price option").index($("#price option:selected"));
	
		if($("#price option:selected").val() == 0) {
			str ="";
		} 
		else {
			var str = $("#price option:selected").text();	
		}
		
		if(str != "") {
			$('#price option:eq(0)').prop("selected", true);
			$('#txtarea').append("<div id='tag_button' class='tag teal'>" + str + "<input type='button' class='btn' id='btn' value='X' />" + "</div>&nbsp");
			$('#price option[value="'+ index + '"]').attr('disabled','disabled');	
			$('#hintdiv').remove();
		}
	});	
	
	//x버튼
	$(document).on('click', '#btn', function() {
		var txt = $(this).closest("div").text();
		
		$(this).closest("div").remove();
		
		if(txt != null) {
			$("select[name='protyoe'] option:contains('"+ txt +"')").removeAttr('disabled','disabled');
			$("select[name='category'] option:contains('"+ txt +"')").removeAttr('disabled','disabled');
			$("select[name='price'] option:contains('"+ txt +"')").removeAttr('disabled','disabled');
		}	
	});
	
	//텍스트클리어
	$('#txtinput').click(function() {
		$('#txtinput').val("");
	}); 
	
	//검색버튼
/*	$('#search').click(function() {
		var temp = $('#txtarea').text().split('#');
		
		 $.each(temp, function(index, item) {
			 console.log($.trim(item));
         });
		 
	});*/
	
	//엔터키 이벤트
	$('#txtinput').keydown(function(key) {
		if(key.keyCode == 13) {
			var str = $('#txtinput').val();
			$('#hintdiv').remove();
			$('#txtarea').append("<div id='tag_button' class='tag teal'>" + str + "<input type='button' class='btn' id='btn' value='X' />" + "</div>&nbsp");
			$('#txtinput').val("제품명이나 브랜드명으로도 검색해보세요!");
			$('#txtinput').blur();
		}
	});
	
	//esc키 이벤트
	$('#txtinput').keyup(function(key) {
		if(key.keyCode == 27) {
			$('#txtinput').val("");
		}
	});
	
	//자동완성기능
	$(function() {
	    var auto = ["라이엇 게임즈","리그 오브 레전드","게임","AOS","lol","League of Legends"];
	    $("#txtinput").autocomplete({
	        source: auto,
	        select: function(event, ui) {
	        },
	        focus: function(event, ui) {
	            return false;
	        }
	    });
	});
});

