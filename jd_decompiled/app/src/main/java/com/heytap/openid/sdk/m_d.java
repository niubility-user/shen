package com.heytap.openid.sdk;

import android.content.Context;
import android.os.Looper;

/* loaded from: classes12.dex */
public class m_d {
    public static boolean m_a;
    public static boolean m_b;

    public static Context m_a(Context context) {
        return (context == null || context.getApplicationContext() == null) ? context : context.getApplicationContext();
    }

    public static boolean m_a() {
        return m_a && m_b && Looper.myLooper() != Looper.getMainLooper();
    }
}
