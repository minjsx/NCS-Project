$(document).ready(function() {
		$('.tab a').on('click', function(e) {
			e.preventDefault();

			$(this).parent().addClass('active');
			$(this).parent().siblings().removeClass('active');

			var href = $(this).attr('href');
			$('.forms > form').hide();
			$(href).fadeIn(500);
		});
	});

	function idFind(index) {
		var default_form = document.forms[index];
		var nameCheck = default_form.name.value;
		var phoneCheck = default_form.phone.value;
		if (nameCheck != "" && phoneCheck != "") {
			window.open("", "아이디 찾기",
					'width=380, height=160, left=760, top=380');
			default_form.action = "/Project/MainControl?cmd=idFind";
			default_form.target = "아이디 찾기";
			default_form.method = "post";
			default_form.submit();
		} else if (nameCheck == "" || phoneCheck == "")
			alert("성명과 연락처를 모두 입력해주세요.");
	}

	function passwordFind(index) {
		var default_form = document.forms[index];
		var emailIdCheck = default_form.email.value;
		var phoneCheck = default_form.phone.value;
		if (emailIdCheck != "" && phoneCheck != "") {
			window.open("", "비밀번호 찾기",
					'width=380, height=160, left=760, top=380');
			default_form.action = "/Project/MainControl?cmd=passwordFind";
			default_form.target = "비밀번호 찾기";
			default_form.method = "post";
			default_form.submit();
		} else if (emailIdCheck == "" || phoneCheck == "")
			alert("이메일과 연락처를 모두 입력해주세요.");
	}