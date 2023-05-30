(function(global) {
    var timerCallbacks = {};
    var timerCallbackId = 0;
    var _JDJSCore = global.JDJSCore

    var setTimeout = function(callback, tm){
        var callbackId = ++timerCallbackId
        timerCallbacks[callbackId] = callback;
        _JDJSCore.setTimerHandler(callback, callbackId, tm, false);
        return callbackId;
    };

    var setInterval = function(callback, tm){
        if(isCallbackRegistered(callback)) return -1;
        var callbackId = ++timerCallbackId
        timerCallbacks[callbackId] = callback;
        _JDJSCore.setTimerHandler(callback, callbackId, tm, true)
        return callbackId;
    };

    var isCallbackRegistered = function(callback){
        for(key in timerCallbacks)
        {
            var cb = timerCallbacks[key];
            if (callback == cb) {
                return true;
            }
        }
        return false;
    };

    var _onNativeTimer = function(callbackId, remove){
        var callback = timerCallbacks[callbackId];
        if (typeof callback == 'function') {
            callback();
        }
        if(remove==1)
        {
            delete timerCallbacks[callbackId];
        }
    };

    var clearTimer = function(callbackId){
        _JDJSCore.clearTimerHandler(callbackId);
        delete timerCallbacks[callbackId];
    };

    global.setTimeout = setTimeout;
    global.setInterval = setInterval;
    global.clearTimeout = clearTimer;
    global.clearInterval = clearTimer;

    global.JDTimerBridge = {
        onNativeTimer: _onNativeTimer
    }
}) (this);
