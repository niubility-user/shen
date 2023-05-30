package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.ChoreographerCompat;

/* loaded from: classes12.dex */
public abstract class GuardedFrameCallback extends ChoreographerCompat.FrameCallback {
    private final ReactContext mReactContext;

    /* JADX INFO: Access modifiers changed from: protected */
    public GuardedFrameCallback(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
    public final void doFrame(long j2) {
        try {
            doFrameGuarded(j2);
        } catch (RuntimeException e2) {
            this.mReactContext.handleException(e2);
        }
    }

    protected abstract void doFrameGuarded(long j2);
}
