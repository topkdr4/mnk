(function() {
    "use strict";

    function App() {

    }

    App.send = function(url, data, cb) {
        $('body').css({'overflow': 'hidden'});
        $('.hover').show();
        $.ajax({
            url: url,
            data: data,
            success: function(data) {
                cb(data);
                $('body').css({'overflow': 'auto'});
                $('.hover').remove();
            },
            error: function() {
                $('body').css({'overflow': 'auto'});
                $('.hover').remove();
            }
        });
    }


    windows.App = App;
})(this);