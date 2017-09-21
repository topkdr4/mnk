jQuery(function(global) {
    "use strict";

    /*$.ajax({
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
    });*/

    $('.add-new-country').on('click', function (e) {
        $('body').css({
            'overflow': 'hidden'
        });


        setDefaultForm();
        $('.hover').show();
        $('.modal_form_country').show();
    });

    $('.hover').on('click', function(e) {
        $('.modal_form').fadeOut();
        $(this).hide();
    });

    //*********************************************************************

    $('.modal_form_country > *:not([class^="md-combo"])').on('click', function(e) {
        var child = $(this).find('.md-combobox-list:visible');
        if (child.length != 0)
            return;

        $('.md-combobox-list:visible').hide();
    });

    $('div.md-combobox').on('click', function(e) {
        $('.md-combobox-list').show();
    });

    $('.md-combobox-list').find('.md-combobox-item').each(function (item) {
        var attr = $(this).attr('data-image');
        $(this).css({
            'background' : 'url(' + attr + ') no-repeat center center',
            'background-size': 'contain'
        }).hover(function() {
            $(this).css({
                'background-color' : '#F5F5F5'
            });
        }, function() {
            $(this).css({
                'background-color' : '#FAFAFA'
            });
        }).on('click', function(e) {
            $('.md-combobox-list').fadeOut();
            $('div.md-combobox').attr({
                'data-image' : attr
            }).css({
                'background' : '#FAFAFA url(' + attr + ') no-repeat center center',
                'background-size' : 'contain'
            });
        });
    });

    $('.conuntry-modal-add').on('click', function(e) {
        console.log('send req');
    });

    $('.country-modal-close').on('click', function(e) {
        $('body').css({
            'overflow': 'auto'
        });

        $('.hover').hide();
        $('.modal_form').fadeOut();
    });


    function setDefaultForm() {
        $('.country-name').val("");
        $('.md-combobox').attr({
            'data-image': 'Wales.png'
        }).css({
            'background' : '#FAFAFA url(Wales.png) no-repeat center center',
            'background-size' : 'contain'
        });
    }


    //****************************************************************************
    $('.add-new-index').on('click', function() {
        $('.hover').show();
        $('.modal_form_index').show();
    });

    $('.index-abort').on('click', function() {
        $('.modal_form_index').fadeOut();
        $('.hover').hide();
    });

    //****************************************************************************
    $('.country-one-list').find('.country-one-item').each(function(item) {
        var current = $(this);
        var image   = current.attr('data-image');
        var uid     = current.attr('data-uid');
        current.css({
            'background' : '#FAFAFA url(' + image + ') no-repeat 2% center',
            'background-size': 'contain'
        }).hover(function() {
            $(this).css({
                'background-color' : '#F5F5F5'
            });
        }, function() {
            $(this).css({
                'background-color' : '#FAFAFA'
            });
        }).on('click', function(e) {
            $('.country-one').attr({
                'data-image' : image,
                'data-uid':         uid
            }).css({
                'background' : '#FAFAFA url(' + image + ') no-repeat 2% center',
                'background-size' : 'contain'
            }).text(current.text());

            $('.country-one-list').hide();
         });
    });

    $('.country-one').on('click', function(e) {
        var current = $(this);
        var width = current.outerWidth(true);
        var pos   = current.position();
        $('.country-one-list').width(width).css({
            left: pos.left,
            top : pos.top
        }).show();
    });

    //****************************************************************************
    $('.country-two-list').find('.country-two-item').each(function(item) {
            var current = $(this);
            var image   = current.attr('data-image');
            var uid     = current.attr('data-uid');
            current.css({
                'background' : '#FAFAFA url(' + image + ') no-repeat 2% center',
                'background-size': 'contain'
            }).hover(function() {
                $(this).css({
                    'background-color' : '#F5F5F5'
                });
            }, function() {
                $(this).css({
                    'background-color' : '#FAFAFA'
                });
            }).on('click', function(e) {
                $('.country-two').attr({
                    'data-image' : image,
                    'data-uid':         uid
                }).css({
                    'background' : '#FAFAFA url(' + image + ') no-repeat 2% center',
                    'background-size' : 'contain'
                }).text(current.text());

                $('.country-two-list').hide();
             });
        });

        $('.country-two').on('click', function(e) {
            var current = $(this);
            var width = current.outerWidth(true);
            var pos   = current.position();
            $('.country-two-list').width(width).css({
                left: pos.left,
                top : pos.top
            }).show();
        });

        $('.compare-button').on('click', function(e) {
            var first  = $('.country-one');
            var second = $('.country-two');
            var index  = $('.index:selected');

            var compare = {
                first:  first.attr('data-uid'),
                second: second.attr('data-uid'),
                index:  index.val()
            };

            //send query
        });
});
