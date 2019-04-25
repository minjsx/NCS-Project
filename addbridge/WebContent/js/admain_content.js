	window.onload = function(){
	   // TODO : Classname 바꿔야함   
	   var comboArr = document.getElementById("js-combo-wrapper").getElementsByClassName("searchbox-combo");
	   var optionArr = new Array();

	   var tagtext = "";
		   for(var i = 0; i < comboArr.length; i++){
			   
			   for(var j = 0 ; j < comboArr[i].getElementsByTagName("select").item(0).getElementsByTagName("option").length ; j++){
				   optionArr.push(comboArr[i].getElementsByTagName("select").item(0).getElementsByTagName("option").item(j));   
			   }	
			   
			   // 동적 EVENT 추가
	     		 comboArr[i].getElementsByTagName("select").item(0).addEventListener("change", function(){
				//this = select를 의미, select[select.selectedIndex]는 현재 선택된 속성을 반환함.				  
				  tagtext = this[this.selectedIndex].text;
				  this[this.selectedIndex].setAttribute("disabled", "disabled");  	
			
				  var tagButton = document.createElement("div");
					  tagButton.setAttribute("id", this.getAttribute("id") + "-" + this[this.selectedIndex].value);
					  tagButton.setAttribute("class", "tag teal");					  
					  tagButton.innerHTML = tagtext;
					  
			      var deleteButton = document.createElement("input");
				  	  deleteButton.setAttribute("type", "button");
				  	  deleteButton.setAttribute("id", "btn");
				  	  deleteButton.setAttribute("class", "delete-search-btn");
				  	  deleteButton.setAttribute("value", "X");
				  	  
				  	  //DELETE button EVENT 추가
				  	  deleteButton.addEventListener("click", function() {
				  		  //input 태그 앞부분의 text를 오려냄
				  		  var split = this.parentNode.innerHTML.split("<");
				  		  
				  		  for(var r = 0 ; r < optionArr.length ; r++){
				  			if(optionArr[r].innerHTML == split[0])
				  			{
				  				console.log(split[0]);
				  				optionArr[r].removeAttribute("disabled");	
				  			}
				  		  }
						  this.parentNode.remove();
				  	  });
				  	  
				  	  //tagbutton 안에 child 넣음
				  	  tagButton.appendChild(deleteButton);

				  	  //textarea 안에 child 넣음
				      document.getElementById("searchbox-textarea").appendChild(tagButton);
				   
				  	  // 0번째로 index 다시 초기화   
				      this.selectedIndex = 0;
			 });   
		   }
		   
		   
		   //검색 EVNET
		   document.getElementById("searchbox-searchbtn").addEventListener("click", function(){
			   var appendstr = "";
			   var textareaInner = document.getElementById("searchbox-textarea");
			   var innertags = textareaInner.getElementsByClassName("tag");
			   var splits = "";
			   for(var s = 0 ;  s < innertags.length ; s++){
				   appendstr += innertags[s].getAttribute("id") + "/";
				   splits = innertags[s].innerHTML.split("<");
				   for(var r = 0 ; r < optionArr.length ; r++){
			  			if(optionArr[r].innerHTML == splits[0])
			  			{
			  				optionArr[r].removeAttribute("disabled");	
			  			}
			  		  }
			   }
			   appendstr += "channel-" + document.getElementById("searchbox-txtinput").value;
			   document.getElementById("searchbox-txtinput").value = "";
			   document.getElementById("searchbox-textarea").innerHTML = "";
			   document.getElementById("search-hidden-value").value = appendstr;
			   document.getElementById("creator-search-form").submit();
		   });
		   
	}