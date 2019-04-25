
      //유튜브 구독자수 구하기

		 $(function(){
	    	 
	    		$.post('<%= request.getContextPath() %>/jsp/youtube_count.jsp', function(data){
	    			var gudok = $('.primary-header-actions > span', data);    		
	    			$("#result").empty();
	    			
	    			gudok.each(function() {
	    				var rank = $(".yt-subscription-button-subscriber-count-branded-horizontal", this).text();
	    				console.log(rank);
	    				//$("#h3").append(rank);
	    			});
	    			
	    		}); 
	    	 
	      }); 
	
     