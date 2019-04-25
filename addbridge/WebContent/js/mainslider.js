$(function (){
        $("#slider").responsiveSlides({
          auto: true,
          pager: false,
          timeout: 4000,
          nav: true,
          speed: 500,
          namespace: "callbacks",
          pauseControls : true,
          before: function () {
            $('.events').append("<li>before event fired.</li>");
          },
          after: function () {
            $('.events').append("<li>after event fired.</li>");
          }
        });           
        
        
        $("#noslider").responsiveSlides({
            auto: true,
            pager: false,
            timeout: 4000,
            nav: true,
            speed: 500,
            namespace: "callbacks",
            pauseControls : true,
            before: function () {
              $('.events').append("<li>before event fired.</li>");
            },
            after: function () {
              $('.events').append("<li>after event fired.</li>");
            }
          });
          
          $("#tslider").responsiveSlides({
              auto: true,
              pager: false,
              timeout: 4000,
              nav: true,
              speed: 500,
              namespace: "callbacks",
              pauseControls : true,
              before: function () {
                $('.events').append("<li>before event fired.</li>");
              },
              after: function () {
                $('.events').append("<li>after event fired.</li>");
              }
            });
          
          $("#admainslider").responsiveSlides({
              auto: true,
              pager: false,
              timeout: 4000,
              nav: true,
              speed: 500,
              namespace: "callbacks",
              pauseControls : true,
              before: function () {
                $('.events').append("<li>before event fired.</li>");
              },
              after: function () {
                $('.events').append("<li>after event fired.</li>");
              }
            });
});
 