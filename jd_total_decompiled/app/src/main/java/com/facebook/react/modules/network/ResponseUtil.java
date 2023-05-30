package com.facebook.react.modules.network;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.net.SocketTimeoutException;

/* loaded from: classes12.dex */
public class ResponseUtil {
    public static void onDataReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, String str) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushString(str);
        rCTDeviceEventEmitter.emit("didReceiveNetworkData", createArray);
    }

    public static void onDataReceivedProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, long j2, long j3) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushInt((int) j2);
        createArray.pushInt((int) j3);
        rCTDeviceEventEmitter.emit("didReceiveNetworkDataProgress", createArray);
    }

    public static void onDataSend(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, long j2, long j3) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushInt((int) j2);
        createArray.pushInt((int) j3);
        rCTDeviceEventEmitter.emit("didSendNetworkData", createArray);
    }

    public static void onIncrementalDataReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, String str, long j2, long j3) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushString(str);
        createArray.pushInt((int) j2);
        createArray.pushInt((int) j3);
        rCTDeviceEventEmitter.emit("didReceiveNetworkIncrementalData", createArray);
    }

    public static void onRequestError(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, String str, Throwable th) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushString(str);
        if (th != null && th.getClass() == SocketTimeoutException.class) {
            createArray.pushBoolean(true);
        }
        rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
    }

    public static void onRequestSuccess(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushNull();
        rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", createArray);
    }

    public static void onResponseReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, int i3, WritableMap writableMap, String str) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushInt(i3);
        createArray.pushMap(writableMap);
        createArray.pushString(str);
        rCTDeviceEventEmitter.emit("didReceiveNetworkResponse", createArray);
    }

    public static void onDataReceived(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i2, WritableMap writableMap) {
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i2);
        createArray.pushMap(writableMap);
        rCTDeviceEventEmitter.emit("didReceiveNetworkData", createArray);
    }
}
