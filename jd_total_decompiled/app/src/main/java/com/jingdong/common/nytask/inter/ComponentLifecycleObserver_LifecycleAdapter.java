package com.jingdong.common.nytask.inter;

import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MethodCallsLogger;

/* loaded from: classes5.dex */
public class ComponentLifecycleObserver_LifecycleAdapter implements GeneratedAdapter {
    final ComponentLifecycleObserver mReceiver;

    ComponentLifecycleObserver_LifecycleAdapter(ComponentLifecycleObserver componentLifecycleObserver) {
        this.mReceiver = componentLifecycleObserver;
    }

    @Override // androidx.lifecycle.GeneratedAdapter
    public void callMethods(LifecycleOwner lifecycleOwner, Lifecycle.Event event, boolean z, MethodCallsLogger methodCallsLogger) {
        boolean z2 = methodCallsLogger != null;
        if (z) {
            return;
        }
        if (event == Lifecycle.Event.ON_CREATE) {
            if (!z2 || methodCallsLogger.approveCall("onCreate", 1)) {
                this.mReceiver.onCreate();
            }
        } else if (event == Lifecycle.Event.ON_START) {
            if (!z2 || methodCallsLogger.approveCall("onStart", 1)) {
                this.mReceiver.onStart();
            }
        } else if (event == Lifecycle.Event.ON_RESUME) {
            if (!z2 || methodCallsLogger.approveCall("onResume", 1)) {
                this.mReceiver.onResume();
            }
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            if (!z2 || methodCallsLogger.approveCall("onPause", 1)) {
                this.mReceiver.onPause();
            }
        } else if (event == Lifecycle.Event.ON_STOP) {
            if (!z2 || methodCallsLogger.approveCall("onStop", 1)) {
                this.mReceiver.onStop();
            }
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            if (!z2 || methodCallsLogger.approveCall("onDestroy", 1)) {
                this.mReceiver.onDestroy();
            }
        }
    }
}
