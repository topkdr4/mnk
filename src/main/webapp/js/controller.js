/**
 * menu controller
 * */
(function() {
    "use strict";

    var container = $('.container');
    container.clear = function(callback) {
        this.empty();
        startLoader();
        callback();
    }


    function startLoader() {
        alert('start loader');
    }


    $('#home').on('click', function() {
        container.clear();
    });


    $('#countries').on('click', function() {
        container.clear();
    });


    $('#indices').on('click', function() {
        container.clear();
    });


    $('#compare').on('click', function() {
        container.clear();
    });

})();