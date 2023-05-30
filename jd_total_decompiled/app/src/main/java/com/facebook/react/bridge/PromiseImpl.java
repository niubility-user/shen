package com.facebook.react.bridge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class PromiseImpl implements Promise {
    private static final String ERROR_DEFAULT_CODE = "EUNSPECIFIED";
    private static final String ERROR_DEFAULT_MESSAGE = "Error not specified.";
    private static final String ERROR_MAP_KEY_CODE = "code";
    private static final String ERROR_MAP_KEY_MESSAGE = "message";
    private static final String ERROR_MAP_KEY_NATIVE_STACK = "nativeStackAndroid";
    private static final String ERROR_MAP_KEY_USER_INFO = "userInfo";
    private static final int ERROR_STACK_FRAME_LIMIT = 10;
    private static final String STACK_FRAME_KEY_FILE = "file";
    private static final String STACK_FRAME_KEY_LINE_NUMBER = "lineNumber";
    private static final String STACK_FRAME_KEY_METHOD_NAME = "methodName";
    @Nullable
    private Callback mReject;
    @Nullable
    private Callback mResolve;

    public PromiseImpl(@Nullable Callback callback, @Nullable Callback callback2) {
        this.mResolve = callback;
        this.mReject = callback2;
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(String str, String str2) {
        reject(str, str2, null, null);
    }

    @Override // com.facebook.react.bridge.Promise
    public void resolve(Object obj) {
        Callback callback = this.mResolve;
        if (callback != null) {
            callback.invoke(obj);
            this.mResolve = null;
            this.mReject = null;
        }
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(String str, Throwable th) {
        reject(str, null, th, null);
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(String str, String str2, Throwable th) {
        reject(str, str2, th, null);
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(Throwable th) {
        reject(null, null, th, null);
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(Throwable th, WritableMap writableMap) {
        reject(null, null, th, writableMap);
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(String str, @Nonnull WritableMap writableMap) {
        reject(str, null, null, writableMap);
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(String str, Throwable th, WritableMap writableMap) {
        reject(str, null, th, writableMap);
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(String str, String str2, @Nonnull WritableMap writableMap) {
        reject(str, str2, null, writableMap);
    }

    @Override // com.facebook.react.bridge.Promise
    public void reject(@Nullable String str, @Nullable String str2, @Nullable Throwable th, @Nullable WritableMap writableMap) {
        if (this.mReject == null) {
            this.mResolve = null;
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (str == null) {
            writableNativeMap.putString("code", ERROR_DEFAULT_CODE);
        } else {
            writableNativeMap.putString("code", str);
        }
        if (str2 != null) {
            writableNativeMap.putString("message", str2);
        } else if (th != null) {
            writableNativeMap.putString("message", th.getMessage());
        } else {
            writableNativeMap.putString("message", ERROR_DEFAULT_MESSAGE);
        }
        if (writableMap != null) {
            writableNativeMap.putMap("userInfo", writableMap);
        } else {
            writableNativeMap.putNull("userInfo");
        }
        if (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            for (int i2 = 0; i2 < stackTrace.length && i2 < 10; i2++) {
                StackTraceElement stackTraceElement = stackTrace[i2];
                WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                writableNativeMap2.putString("file", stackTraceElement.getFileName());
                writableNativeMap2.putInt("lineNumber", stackTraceElement.getLineNumber());
                writableNativeMap2.putString("methodName", stackTraceElement.getMethodName());
                writableNativeArray.pushMap(writableNativeMap2);
            }
            writableNativeMap.putArray(ERROR_MAP_KEY_NATIVE_STACK, writableNativeArray);
        } else {
            writableNativeMap.putArray(ERROR_MAP_KEY_NATIVE_STACK, new WritableNativeArray());
        }
        this.mReject.invoke(writableNativeMap);
        this.mResolve = null;
        this.mReject = null;
    }

    @Override // com.facebook.react.bridge.Promise
    @Deprecated
    public void reject(String str) {
        reject(null, str, null, null);
    }
}
