(function() {
    "use strict";

    function Index(uid, name) {
        this.__uid  = uid;
        this.__name = name;
    }


    Index.prototype.toElement = function(element) {
        var context = this;
        element.attr({
            'data-uid': context.__uid
        }).text(context.__name);
    }

    window.Index = Index;
})(this);