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
                })).hover(function(e) {
                    var current = $(this);
                    var removeButton = $('<input/>', {
                        type: 'button',
                        class: 'select-for-delete',
                        val:   'x'
                    });

                    removeButton.hover(function(e) {
                        current.css({
                            'border': '2px solid #b71c1c'
                        });
                    }, function(e) {
                        current.css({
                            'border': '2px solid #FAFAFA'
                        });
                    });

                    removeButton.on('click', function(e) {
                    App.send("/webapi/country/remove", { uid: country.id }, function(data) {
                            $('#countries').click();
                        });
                    });

                    current.append(removeButton);
                }, function(e) {
                    $('.select-for-delete').off().remove();
                }).appendTo($('.wrap-countries'));
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
                class: 'tab combo-tab'
            }).append($('<select/>', {
                name: 'indecies-list',
                class: 'common-combobox'
            })))
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


    $('.container').on('click', '.tab', function(e) {
        var that = $(this);
        if (that.hasClass("tab-active") || that.hasClass("combo-tab"))
            return;

        $('.tab-active').removeClass("tab-active");
        that.addClass("tab-active");

        if (that.hasClass('graph')) {
            showGraph();
        }

        if (that.hasClass('table')) {
            showTable();
        }
    });

    Controller.showTable = function() {
        showTable();
    }

    function showTable() {
        $('#container').remove();
        $('.country-table').remove();
        $('.show_modal_form_value').remove();
        var combobox = $('.common-combobox');
        var countryUid = combobox.find('option').eq(0).attr('data-country-uid');
        var indexUid   = combobox.val();
        showTable0(countryUid, indexUid);
    }

    function showTable0(countryUid, indexUid) {
        App.send("/webapi/value/list", {
            countryUid: countryUid,
            indexUid:indexUid
        }, function(values) {
            var root = $('<div/>', {
                class: 'country-table md-table'
            });

            var table = $('<table/>');
            appendHeaderLine(table);
            values.result.forEach(function(item) {
                appendLine(table, item);
            });

            root.append(table);
            $('.country-detail').append(root);
            $('.country-detail').append($('<button/>', {
                class: 'show_modal_form_value',
                text: '+'
            }));
        });
    }

    function appendHeaderLine(table) {
        var tr = $('<tr/>');
        for (var i = 0; i < 5; i++) {
            var th = $('<th/>');
            switch (i) {
                case 1:
                    th.text('Значение по Х');
                    break;
                case 2:
                    th.text('Значение по Y');
                    break;
            }
            tr.append(th);
        }

        table.append(tr);
    }


    function appendLine(table, data) {
        table.append($('<tr/>').append($('<td/>', {
            class: 'checkbox no_check'
        })).append($('<td/>', {
            class: 'x-value',
            text:  data.valueX
        })).append($('<td/>', {
            class: 'y-value',
            text:  data.valueY
        })).append($('<td/>', {
            class: 'save-value'
        }).attr({
            'data-uid': data.uid
        })).append($('<td/>', {
            class: 'remove-value'
        }).attr({
            'data-uid': data.uid
        })));
    }

    function showGraph() {
        $('#container').remove();
        $('.country-table').remove();
        var combobox = $('.common-combobox');
        var countryUid = combobox.find('option').eq(0).attr('data-country-uid');
        var indexUid   = combobox.val();
        showGraph0(countryUid, indexUid);
    }

    function showGraph0(countryUid, indexUid) {
        App.send("/webapi/value/analytic", {
            countryUid: countryUid,
            indexUid: indexUid
        }, function (data) {
            App.send("/webapi/value/list", {
                countryUid: countryUid,
                indexUid:indexUid
            }, function(values) {
                Controller.setGraph(data, values);
            });
        });
    }


    $('.container').on('change', '.common-combobox', function(e) {
        var active = $('.tab-active');

        if (active.hasClass('graph')) {
            $('#container').remove();
            $('.country-table').remove();
            showGraph();
        }

        if (active.hasClass('table')) {
            $('#container').remove();
            $('.country-table').remove();
            showTable();
        }
    });

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
                name: 'Исходные данные',
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


    function createIndex(item) {
        return $('<div/>', {
            class: 'index-card'
        }).append($('<div/>', {
            text: item.name
        }));
    }


    Controller.showIndeciesList = function() {
        container.clear(function () {
            var indeciesContainer = $('<div/>', { class: 'indecies-container' });
            var wrap = $('<div/>', { class: 'wrap-indecies' });

            App.send('/webapi/index/list', {}, function(data) {
                data.result.forEach(function(item) {
                    wrap.append(createIndex(item));
                });
            });

            indeciesContainer.append(wrap).append($('<button/>', {
                class: 'add-new-index',
                text:  '+'
            }));
            container.append(indeciesContainer);
        });
    }


    $('#indices').on('click', function() {
        Controller.showIndeciesList();
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

    $('.container').on('click', '.checkbox', function(e) {
        $(this).toggleClass('no_check');
        $(this).toggleClass('check');
    });

    $('container').contextmenu(function(e) {
        e.preventDefault();
    });

    var setValue = function() {
        var parent = $(this).parent();
        var check  = parent.find('.checkbox');
        if (!check.hasClass('check'))
            return;

        var result = prompt('Новое значение', '');
        $(this).text(parseFloat(result));
    };

    $('.container').on('dblclick', '.x-value', setValue);
    $('.container').on('dblclick', '.y-value', setValue);

    $('.container').on('click', '.save-value', function (e) {
        var uid = $(this).attr('data-uid');
        var parent = $(this).parent();
        var x = parent.find('.x-value');
        var y = parent.find('.y-value');
        App.send('/webapi/value/update', {uid: uid, x: x.text(), y: y.text()}, function() {});
    });

    $('.container').on('click', '.remove-value', function (e) {
        var uid = $(this).attr('data-uid');
        App.send('/webapi/value/remove', {uid: uid}, showTable);
    });

    window.Controller = Controller;
});
