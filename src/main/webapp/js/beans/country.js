(function() {
    "use strict";

    function Country(uid, name, url) {
        this.__uid  = uid;
        this.__name = name;
        this.__url  = url;
    }

    Country.prototype.toElement = function(element) {
        var context = this;
        element.attr({
            'data-image': context.__url,
            'data-uid':   context.__uid
        }).text(context.__name);
    }


    window.Country = Country;

})(this);