package com.jingdong.common.cart.clean;

import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MethodCallsLogger;

/* loaded from: classes5.dex */
public class CartGuideCleanPopupWindow_LifecycleAdapter implements GeneratedAdapter {
    final CartGuideCleanPopupWindow mReceiver;

    CartGuideCleanPopupWindow_LifecycleAdapter(CartGuideCleanPopupWindow cartGuideCleanPopupWindow) {
        this.mReceiver = cartGuideCleanPopupWindow;
    }

    @Override // androidx.lifecycle.GeneratedAdapter
    public void callMethods(LifecycleOwner lifecycleOwner, Lifecycle.Event event, boolean z, MethodCallsLogger methodCallsLogger) {
        boolean z2 = methodCallsLogger != null;
        if (!z && event == Lifecycle.Event.ON_STOP) {
            if (!z2 || methodCallsLogger.approveCall("dismissGuideToast", 1)) {
                this.mReceiver.dismissGuideToast();
            }
        }
    }
}
