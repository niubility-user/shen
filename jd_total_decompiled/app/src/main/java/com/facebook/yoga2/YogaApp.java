package com.facebook.yoga2;

import android.app.Application;
import android.content.Context;
import com.getkeepsafe.relinker.c;

/* loaded from: classes12.dex */
public class YogaApp {
    public static Application application;

    /* JADX WARN: Removed duplicated region for block: B:18:0x0017 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean loadLib(Context context) {
        boolean z;
        if (context != null) {
            try {
                c.a(context, "c++_shared");
                c.a(context, "ylayout");
                z = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (!z) {
                try {
                    System.loadLibrary("c++_shared");
                    System.loadLibrary("ylayout");
                    return true;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            return z;
        }
        z = false;
        if (!z) {
        }
        return z;
    }

    public static boolean loadLib() {
        return loadLib(application);
    }
}
