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

    function Controller () {}


    Controller.setContentCountryList = function() {
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
                }).attr({
                    'data-uid': country.id
                })).appendTo($('.wrap-countries'));
            });
        });
    };

    Controller.showCountryInfo = function(uid) {
        container.empty();
        $('<div/>', {
            class: 'country-detail'
        }).append($('<div/>', {
            class: 'tab-container'
        }).append($('<div/>', {
                class: 'tab graph tab-active',
                text:  'График'
            })).append($('<div/>', {
                class: 'tab table',
                text: 'Таблица'
            })).append($('<div/>', {
                class: 'tab'
            }).append($('<select/>', {
                name: 'indecies-list',
                class: 'common-combobox'
            }))).append($('<div/>', {
                class: 'tab remove-country',
                text: 'Удалить страну'
            }).on('click', function(e) {
                App.send("/webapi/country/remove", {
                    uid: uid
                }, function(data) {
                    $('#countries').click();
                });
            }))
        ).appendTo(container);

        App.send("/webapi/index/list", {}, function (data) {
            var select = $('select.common-combobox');
            data.result.forEach(function(index) {
                $('<option/>', {
                    value: index.uid,
                    text:  index.name,
                    class: 'common-option'
                }).attr({
                    'data-country-uid': uid
                }).appendTo(select);
            });

            if (data.result.length === 0)
                return;

            App.send("/webapi/value/analytic", {
                countryUid: uid,
                indexUid: $('.common-option').eq(0).val()
            }, function (data) {
                App.send("/webapi/value/list", {
                    countryUid: uid,
                    indexUid: $('.common-option').eq(0).val()
                }, function(values) {
                    Controller.setGraph(data, values);
                });
            });
        });
    };

    Controller.setGraph = function(regression, values) {
        var data = [];
        values.result.forEach(function(item) {
            data.push([item.valueX, item.valueY]);
        });
        var vals = [];
        regression.result.forEach(function(item) {
            vals.push([item.valueX, item.valueY]);
        });

        $('<div/>', {
            id: 'container',
            class: 'chart'
        }).appendTo($('.country-detail'));

        Highcharts.chart('container', {
            credits: {
                enabled: false
            },xAxis: {
                min: 0
            },
            yAxis: {
                title: {
                    text: ''
                },
                min: 0
            },
            title: {
                text: 'Модель линейной регрессии'
            },
            series: [{
                type: 'line',
                name: 'Линейная регрессия',
                data: vals,
                marker: {
                    enabled: true
                },
                states: {
                    hover: {
                        lineWidth: 0
                    }
                },
                enableMouseTracking: false
            }, {
                type: 'scatter',
                name: 'Observations',
                data: data,
                marker: {
                    radius: 4
                }
            }]
        });
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
        container.clear(Controller.setContentCountryList);
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

    window.Controller = Controller;
});
