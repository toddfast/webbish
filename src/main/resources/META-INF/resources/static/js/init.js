// Avoid `console` errors in browsers that lack a console.
(function() {
	var method;
	var noop = function () {};
	var methods = [
		'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
		'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
		'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
		'timeStamp', 'trace', 'warn'];
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
var require = require || {};
require.merge = function(from) {

	function merge(to, from) {
		for (n in from) {
			if (typeof to[n] != "object") {
				to[n] = from[n];
			} else if (typeof from[n] == "object") {
				to[n] = merge(to[n], from[n]);
			}
		}

		return to;
	};

    return merge(this,from);
};

// Create a one-shot define() function for use in config.js
var define=function(dependencies, factoryFn) {
	// Call the factory, ignoring the dependencies
	factoryFn(require);

	// Unset this define function once used
	define=undefined;
}

// Merge the base require.js configuration
require.merge({
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

	deps: ["js-template"],

	callback: function initPage() {
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

		function findModuleName(attributeName, moduleName) {
			if (!moduleName) {
				eachReverse(document.getElementsByTagName("script"),
					function (script) {
						dataPage = script.getAttribute(attributeName);
						if (dataPage) {
							moduleName=dataPage;
							return true;
						}
					});
			}

			return moduleName;
		}

		var moduleName=findModuleName("data-page",window.pageModuleName)
			|| $("body:first").attr("id")
			|| $("html:first").attr("id");

		require(["js/page/"+moduleName]);
	}
});
