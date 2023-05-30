package com.jingdong.moutaibuy.lib.g;

import android.app.Activity;

/* loaded from: classes16.dex */
public interface a {

    /* renamed from: com.jingdong.moutaibuy.lib.g.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public interface InterfaceC0707a {
        void onDenied();

        void onGranted();
    }

    void a(Activity activity, String str, InterfaceC0707a interfaceC0707a);

    boolean hasPermission(Activity activity, String str);

    void onRequestPermissionsResult(Activity activity, int i2, String[] strArr, int[] iArr);
}
