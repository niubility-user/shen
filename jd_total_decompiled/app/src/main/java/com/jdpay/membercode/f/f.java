package com.jdpay.membercode.f;

import android.view.Window;

/* loaded from: classes18.dex */
public class f {
    public static void a(Window window) {
        if (window == null) {
            return;
        }
        window.addFlags(8192);
    }

    public static void b(Window window) {
        if (window == null) {
            return;
        }
        window.clearFlags(8192);
    }
}
