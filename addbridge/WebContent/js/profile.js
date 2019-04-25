function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}
	function handleFilePath(input) {
		// Initialize file variable 
		var file;

		// Check if a file is actually selected 
		if (input.files && (file = input.files[0])) {
			// Create a FileReader 
			var fileReader = new FileReader();

			// Listen to its onload event 
			fileReader.onload = function(e) {
				// Set the result to textarea 
				// DB연결시 주목!! 05_fileupload_class 참고할것.
				document.getElementById("uploadPreview").src = e.target.result;
				document.getElementById('txt').value = e.value;
			};

			// Then read the file 
			fileReader.readAsDataURL(file);
		}
	}
	function fn_change_hangul_money(txt_id)
    {  
		var num_value = txt_id.value;
		
        var num_length = num_value.length;
         	// 1 ~ 9 한글 표시
        var arrNumberWord = new Array("","일","이","삼","사","오","육","칠","팔","구");
            // 10, 100, 100 자리수 한글 표시
        var arrDigitWord = new  Array("","십","백","천");
            // 만단위 한글 표시
        var arrManWord = new  Array("","만","억", "조");
        if(isNaN(num_value) == true)
        	return;
        var han_value = "";
        var man_count = 0;      // 만단위 0이 아닌 금액 카운트.
     
        for(i=0; i < num_value.length; i++)
        {
       
        	
                  // 1단위의 문자로 표시.. (0은 제외)
        	var strTextWord = arrNumberWord[num_value.charAt(i)];
                  // 0이 아닌경우만, 십/백/천 표시
            if(strTextWord != "")
            {
            	
            	man_count++;
                strTextWord += arrDigitWord[(num_length - (i+1)) % 4];
            }
                  // 만단위마다 표시 (0인경우에도 만단위는 표시한다)
            if(man_count != 0 && (num_length - (i+1)) % 4 == 0)
            {  
                man_count = 0;
                strTextWord = strTextWord + arrManWord[(num_length - (i+1)) / 4];
            }
                han_value += strTextWord;
                
         
        }
        
    		var result = num_value.toString();
        
            if(num_value != 0)
            	 han_value = "금 : " + result.replace(/(\d)(?=(?:\d{3})+(?!\d))/g,'$1,') + " 원";
      
         
            document.all.han_money.innerText = han_value;
      
         
      
    }
	
	function fn_change_hangul_sub(txt_id)
    {  
		var num_value = txt_id.value;
		
        var num_length = num_value.length;
         	// 1 ~ 9 한글 표시
        var arrNumberWord = new Array("","일","이","삼","사","오","육","칠","팔","구");
            // 10, 100, 100 자리수 한글 표시
        var arrDigitWord = new  Array("","십","백","천");
            // 만단위 한글 표시
        var arrManWord = new  Array("","만","억", "조");
        if(isNaN(num_value) == true)
        	return;
        var han_value = "";
        var man_count = 0;      // 만단위 0이 아닌 금액 카운트.
     
        for(i=0; i < num_value.length; i++)
        {
       
        	
                  // 1단위의 문자로 표시.. (0은 제외)
        	var strTextWord = arrNumberWord[num_value.charAt(i)];
                  // 0이 아닌경우만, 십/백/천 표시
            if(strTextWord != "")
            {
            	
            	man_count++;
                strTextWord += arrDigitWord[(num_length - (i+1)) % 4];
            }
                  // 만단위마다 표시 (0인경우에도 만단위는 표시한다)
            if(man_count != 0 && (num_length - (i+1)) % 4 == 0)
            {  
                man_count = 0;
                strTextWord = strTextWord + arrManWord[(num_length - (i+1)) / 4];
            }
                han_value += strTextWord;
                
         
        }
        
    		var result = num_value.toString();
        
            if(num_value != 0)
            	 han_value = "구독자 : " + result.replace(/(\d)(?=(?:\d{3})+(?!\d))/g,'$1,') + " 명";
      
         
            document.all.sub_id.innerText = han_value;
  
    }

	
	