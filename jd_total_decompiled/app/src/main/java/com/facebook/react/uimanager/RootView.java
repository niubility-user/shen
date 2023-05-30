package com.facebook.react.uimanager;

import android.view.MotionEvent;

/* loaded from: classes12.dex */
public interface RootView {
    void handleException(Throwable th);

    void onChildStartedNativeGesture(MotionEvent motionEvent);
}
