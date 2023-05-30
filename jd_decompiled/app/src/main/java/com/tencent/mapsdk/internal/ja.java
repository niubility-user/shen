package com.tencent.mapsdk.internal;

import android.content.Context;
import android.util.Log;

/* loaded from: classes9.dex */
public class ja {
    private static final String a = "LibraryLoader";
    public static final String[] b = {li.a, "txnavengine"};

    public static void a(Context context) {
        for (String str : b) {
            a(context, str);
        }
    }

    public static void a(Context context, String str) {
        try {
            System.loadLibrary(str);
            if (Log.isLoggable(a, 4)) {
                String str2 = "loadLibary:" + str + "  successful";
            }
        } catch (UnsatisfiedLinkError unused) {
            boolean b2 = ka.b(context, str);
            if (Log.isLoggable(a, 4)) {
                String str3 = "loadLibary:" + str + " result:" + b2;
            }
        }
    }
}
