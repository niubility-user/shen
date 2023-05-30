var JDJSBridge = function(e) {
	// console.log('JDJSBridge e',e);
	"function" == typeof logxx && logxx("jsbridge start");
	var t = "undefined" != typeof __devtoolssubcontext && __devtoolssubcontext;
	if (e.navigator && e.navigator.userAgent) {
		var n = e.navigator.userAgent; (n.indexOf("appservice") > -1 || n.indexOf("jddevtools") > -1) && (t = !0)
	}
	if (t) {
		var o = e.JDJSBridge;
		//delete e.JDJSBridge;
		return o
	}
	var r = e.__jdConfig || {},
	i = e.hasOwnProperty("document"),
	a = r.isWK,
	u = {},
	c = 0,
	s = {},
	l = {};
	if (i) {
		var n = e.navigator.userAgent; ! ( - 1 != n.indexOf("Android"))
	}
	var f = e.webkit,
  d = e.JDJSCore?e.JDJSCore:e.oldJDJSCore;
  //由于安卓端存在页面重定向 二次注入 JDJSCore 丢失的问题 增加原有变量保存
	if(d){
		e.oldJDJSCore = d;
	}
	// console.log('JDJSCore',d);
	// console.log('JDJSCore document',i);
	f = f || {
		messageHandlers: {
			invokeHandler: {
				postMessage: function() {}
			}
		}
	};
	f.messageHandlers = f.messageHandlers || {
		invokeHandler: {
			postMessage: function() {}
		}
	};
	f.messageHandlers.invokeHandler = f.messageHandlers.invokeHandler || {
		postMessage: function() {}
	};

	"object" == typeof d && "function" != typeof d.publishHandler && (d.publishHandler = function() {}),
	delete e.webkit,
	delete e.JDJSCore;
	var p = JSON.parse,
	h = JSON.stringify,
	v = function(e, t) {
		if (void 0 !== e && "function" == typeof u[t] && "" !== e && null !== e) {
			try {
				e = p(e),
				e = JDNativeBuffer.unpack(e)
			} catch(t) {
				e = {}
			}
			u[t](e),
			delete u[t]
		}
	},
	g = function(e, t, n) {
		if (d) {
			var o = d.invokeHandler(e, t, n);
			v(o, n)
		} else {
			var r = {
				event: e,
				paramsString: t,
				callbackId: n
			};
			if (a) {
				var o = prompt("webgame_invoke", h(r));
				v(o, n)
			} else f.messageHandlers.invokeHandler.postMessage(r)
		}
	},
	_ = function(e, t, n) {
		d ? d.publishHandler(e, t, n) : f.messageHandlers.publishHandler.postMessage({
			event: e,
			paramsString: t,
			webviewIds: n
		})
	},
	y = function(e, t, n) {
		try {
			t = JDNativeBuffer.pack(t);
		} catch(e) {

}
		var o = h(t || {}),
		r = ++c;
		u[r] = n,
		g(e, o, r)
	},
	b = function(e, t) {
		try {
			t = JDNativeBuffer.unpack(t);
		} catch(e) {

}
		var n = u[e];
		"function" == typeof n && n(t),
		delete u[e]
	},
	m = function(e, t) {
		s[e] = t
	},
	w = function(e, t, n) {
		n = n || [],
		n = h(n);
		var o = "custom_event_" + e,
		r = h(t);
		_(o, r, n)
	},
	S = function(e, t) {
		l["custom_event_" + e] = t
	},
	k = function(e, t, n, o) {
		try {
			t = JDNativeBuffer.unpack(t);
		} catch(e) {

}
		var r;
		"function" == typeof(r = -1 != e.indexOf("custom_event_") ? l[e] : s[e]) && r(t, n, o)
	};
	return e.JDJSBridge = {
		get invokeCallbackHandler() {
			return b
		},
		get subscribeHandler() {
			return k
		}
	},
	r && r.clientDebug && (e.JDJSBridge = {
		on: m,
		publish: w,
		invoke: y,
		subscribe: S,
		get invokeCallbackHandler() {
			return b
		},
		get subscribeHandler() {
			return k
		}
	}),
	{
		on: m,
		publish: w,
		invoke: y,
		subscribe: S,
		get invokeCallbackHandler() {
			return b
		},
		get subscribeHandler() {
			return k
		}
	}
} (this)