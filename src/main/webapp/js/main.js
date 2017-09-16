jQuery(function(global) {
    "use strict";

    $.ajax({
        url: 'ebota.html',
        timeout: 10000,
        success: function(){
            console.log('succes');
            $('.hover').remove();
        },
        error: function() {
            setTimeout(function() {
                console.log('fail');
                $('.hover').remove();
                $('body').css({
                    'overflow': 'auto'
                });
            }, 100);
        }
    });
});