package com.facebook.react.bridge;

/* loaded from: classes.dex */
public final class CallbackImpl implements Callback {
    private final int mCallbackId;
    private boolean mInvoked = false;
    private final JSInstance mJSInstance;

    public CallbackImpl(JSInstance jSInstance, int i2) {
        this.mJSInstance = jSInstance;
        this.mCallbackId = i2;
    }

    @Override // com.facebook.react.bridge.Callback
    public void invoke(Object... objArr) {
        if (!this.mInvoked) {
            this.mJSInstance.invokeCallback(this.mCallbackId, Arguments.fromJavaArgs(objArr));
            this.mInvoked = true;
            return;
        }
        throw new RuntimeException("Illegal callback invocation from native module. This callback type only permits a single invocation from native code.");
    }
}
