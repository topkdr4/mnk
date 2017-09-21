/**
 * menu controller
 * */
$(function() {
    "use strict";

    var container = $('.container');
    container.clear = function(callback) {
        this.empty();
        callback();
    };


    $('#home').on('click', function() {

        container.clear(function () {
            $('<div/>', {
                class: 'card-count-container'
            }).append($('<div/>', {
                class: 'card-count-countries'
            }).append($('<div/>', {
                class: 'number country-count'
            })).append($('<div/>', {
                class: 'description',
                text:  'Стран'
            }))).append($('<div/>', {
                class: 'card-count-indecies'
            }).append($('<div/>', {
                class: 'number indecies-count'
            })).append($('<div/>', {
                class: 'description',
                text:  'Показателей'
            }))).appendTo(container);

            App.send("/webapi/country/list", {}, function (data) {
                $('.country-count').text(data.result.length);
            });

            App.send("/webapi/index/list", {}, function (data) {
                $('.indecies-count').text(data.result.length);
            });

        });
    });


    $('#countries').on('click', function() {
        container.clear(function () {

            $('<div/>', {
                class: 'country_search'
            }).append($('<input/>', {
                id: 'search_country',
                class: 'md-input-text',
                placeholder: 'Найти...'
            })).appendTo(container);

            $('<button/>', {
                class: 'add-new-country',
                text: '+'
            }).appendTo(container);

            $('<div/>', {
                class: 'countries-container'
            }).append($('<div/>', {
                class: 'wrap-countries'
            })).appendTo(container);

            App.send("/webapi/country/list", {}, function (data) {
                data.result.forEach(function(country) {
                    $('<div/>', {
                        class: 'md-card'
                    }).append($('<div/>', {
                        class: 'card-image',
                        text: country.name
                    }).css({
                        background: 'url(' + country.url + ') no-repeat left 40%'
                    })).appendTo($('.wrap-countries'));
                });
            });
        });
    });


    $('#indices').on('click', function() {
        container.clear(function () {
            
        });
    });


    $('#compare').on('click', function() {
        container.clear(function () {
            
        });
    });

    App.send("/image/list", {}, function(data) {
        var list = $('.md-combobox-list').empty();
        data.result.forEach(function (item) {
            var value = item.split('\\').join('/');

            $('<div/>', {
                class: 'md-combobox-item'
            }).attr({
                'data-image': value
            }).css({
                'background' : 'url(' + value + ') no-repeat center center',
                'background-size': 'contain'
            }).hover(function() {
                $(this).css({
                    'background-color' : '#F5F5F5'
                });
            }, function() {
                $(this).css({
                    'background-color' : '#FAFAFA'
                });
            }).on('click', function() {
                list.fadeOut();
                $('div.md-combobox').attr({
                    'data-image' : value
                }).css({
                    'background' : '#FAFAFA url(' + value + ') no-repeat center center',
                    'background-size' : 'contain'
                });
            }).appendTo(list);
        });
    });
});
