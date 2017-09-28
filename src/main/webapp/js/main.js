jQuery(function(global) {
    "use strict";

    $('.container').on('click', '.add-new-country', function (e) {
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


    $('.country-modal-add').on('click', function(e) {
        var data = {
            name: $('.country-name').val(),
            url: $('.md-combobox').attr('data-image')
        };

        App.send('/webapi/country/add', data, function() {
            $('.country-name').val('');
            $('.container').empty();
            Controller.setContentCountryList();
        });
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
    $('.container').on('click', '.add-new-index', function() {
        setDefaultIndexForm();
        $('.hover').show();
        $('.modal_form_index').show();
    });

    $('.index-abort').on('click', function() {
        $('.modal_form_index').fadeOut();
        $('.hover').hide();
    });

    function setDefaultIndexForm() {
        var field = $('.index-name');
        field.val('');
        field.removeClass('bad-value');
    }

    $('.add-index').on('click', function(e) {
        var field = $('.index-name');
        if (field.val().trim() == '') {
            field.addClass('bad-value');
            return;
        }

        App.send('/webapi/index/add', {
            name: field.val()
        }, function(data) {
            Controller.showIndeciesList();
            $('.index-abort').click();
        });
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
        var image = current.attr('data-image');
        var uid = current.attr('data-uid');
        current.css({
            'background': '#FAFAFA url(' + image + ') no-repeat 2% center',
            'background-size': 'contain'
        }).hover(function () {
            $(this).css({
                'background-color': '#F5F5F5'
            });
        }, function () {
            $(this).css({
                'background-color': '#FAFAFA'
            });
        }).on('click', function (e) {
            $('.country-two').attr({
                'data-image': image,
                'data-uid': uid
            }).css({
                'background': '#FAFAFA url(' + image + ') no-repeat 2% center',
                'background-size': 'contain'
            }).text(current.text());

            $('.country-two-list').hide();
        });
    });

    $('.country-two').on('click', function (e) {
        var current = $(this);
        var width = current.outerWidth(true);
        var pos = current.position();
        $('.country-two-list').width(width).css({
            left: pos.left,
            top: pos.top
        }).show();
    });

    $('.compare-button').on('click', function (e) {
        var first = $('.country-one');
        var second = $('.country-two');
        var index = $('.index:selected');

        var compare = {
            first: first.attr('data-uid'),
            second: second.attr('data-uid'),
            index: index.val()
        };

        //send query
    });

    $('.container').on('keyup', '#search_country', function(e) {
        var regexp = $(this).val();
        $('.wrap-countries').find('.card-image').each(function(item) {
            var parent = $(this).parent();
            if (regexp === '') {
                parent.show();
            } else {
                var reg = new RegExp(regexp);
                if (reg.test($(this).text()))
                     parent.show();
                else parent.hide();
            }
        });
    });

    $('.container').on('click', '.card-image', function(e) {
        Controller.showCountryInfo($(this).attr('data-uid'));
    });


    /* Добавление новго значение модалка */
    $('.container').on('click', '.show_modal_form_value', function(e) {
        restoreModalValue();
        $('body').css({
            'overflow': 'hidden'
        });
        $('.hover').show();
        $('.modal_form_value').show();
    });

    $('.value-modal-close').on('click', function(e) {
        $('.modal_form_value').fadeOut();
        $('.hover').hide();

        $('body').css({
            'overflow': 'auto'
        });
    });

    function restoreModalValue() {
        $('.modal-value').each(function() {
            var current = $(this);
            current.val('');
            current.removeClass('modal-bad-value');
        });
    }

    $('.value-modal-add').on('click', function(e) {
        var xField = $('.modal-x-value');
        var yField = $('.modal-y-value');

        var x = parseFloat(xField.val());
        var y = parseFloat(yField.val());

        var valid = true;

        if (Number.isNaN(x)) {
            valid = false;
            xField.addClass('modal-bad-value');
        } else {
            xField.val(x);
            xField.removeClass('modal-bad-value');
        }

        if (Number.isNaN(y)) {
            valid = false;
            yField.addClass('modal-bad-value');
        } else {
            yField.val(y);
            yField.removeClass('modal-bad-value');
        }

        if (!valid)
            return;

        var combobox = $('.common-combobox');
        var countryUid = combobox.find('option').eq(0).attr('data-country-uid');
        var indexUid   = combobox.val();

        App.send('/webapi/value/add', {
            countryUid: countryUid,
            indexUid: indexUid,
            valueX: x,
            valueY: y
        }, function() {
            $('.hover').click();
            Controller.showTable();
        });
    });

});
