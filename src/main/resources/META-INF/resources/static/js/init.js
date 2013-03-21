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

// Allow easy merging of configuration info before require.js is loaded
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

// Create a one-shot pseudo-`define()` function for use in Webbish's
// application-defined config.js script
var define=function(dependencies, moduleFactoryFn) {

	if (typeof dependencies === "function") {
		moduleFactoryFn=dependencies;
	}

	// Alias the merge function as config() for this one call in order to mimic
	// the typical way of configuring "naked" require.js. If config.js
	// calls require.config({ config: ... }), this function alias will be
	// automatically removed. Otherwise, we'll remove it ourselves afterward.
	require.config = require.merge;

	// Call the pseudo-module's factory function with our pseudo-requirejs
	// object, ignoring the declared dependencies (which are just for
	// formalism's sake)
	moduleFactoryFn(require);

	// Remove the config() alias if it wasn't overwritten by a config
	// property in config.js
	if (typeof require.config === "function") {
		delete require.config;
	}

	// Clean up and remove our custom merge() method
	delete require.merge;

	// Unset this define function once used to make room for require.js's
	// define() function
	define=undefined;

	console.log("Effective require.js configuration:",require);
}

// Define the base require.js configuration
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

// config.js will be called AFTER this point and call the pseudo-define()
// function above. Then require.js will be called and it will process the
// contents of `var require` and replace it with a function.