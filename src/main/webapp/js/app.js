(function() {
    "use strict";

    function App() {

    }

    function startLoader() {
        $('.preloader').show();
        $('body').css({'overflow': 'hidden'});
    }


    function stopLoader() {
        $('.preloader').hide();
        $('body').css({'overflow': 'auto'});
    }

    App.send = function(url, data, cb) {
        startLoader();
        $.ajax({
            url: url,
            data: data,
            type: 'POST',
            success: function(data) {
                cb(data);
                stopLoader();
            },
            error: function(data) {
                cb(data);
                stopLoader();
            }
        });
    };


    window.App = App;
})(this);
