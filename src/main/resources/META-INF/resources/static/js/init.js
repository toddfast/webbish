// Avoid `console` errors in browsers that lack a console.
(function() {
    var method;
    var noop = function () {};
    var methods = [
        'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
        'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
        'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
        'timeStamp', 'trace', 'warn'
    ];
    var length = methods.length;
    var console = (window.console = window.console || {});

    while (length--) {
        method = methods[length];

        // Only stub undefined methods.
        if (!console[method]) {
            console[method] = noop;
        }
    }
}());

// Global require.js configuration (defined before loading require.js)
var require = {
	baseUrl: "static/",
	paths: {
		"js-template": "js/lib/js-template/js-template",
		"modernizr": "js/lib/modernizr/modernizr-2.6.2",
		"bootstrap": "js/lib/bootstrap/bootstrap"
	},

	shim: {
		"modernizr": {
			exports: "window.Modernizr"
		},
		"bootstrap": {
			exports: "$.fn.alert"
		}
	},

	map: {
		"*": {
		  "css": "js/lib/require-css/css"
		}
	},

	deps: [],

	callback: function initPage() {

		var moduleName=window.moduleName;
		if (!moduleName) {
			function eachReverse(ary, func) {
				if (ary) {
					var i;
					for (i = ary.length - 1; i > -1; i -= 1) {
						if (ary[i] && func(ary[i], i, ary)) {
							break;
						}
					}
				}
			}

			eachReverse(document.getElementsByTagName("script"),
				function (script) {
					dataPage = script.getAttribute("data-page");
					if (dataPage) {
						moduleName=dataPage;
						return true;
					}
				});
		}

		var moduleName=moduleName
			|| $("body:first").attr("id")
			|| $("html:first").attr("id");

		require(["js/page/"+moduleName]);
	}
};
