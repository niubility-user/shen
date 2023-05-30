package com.jingdong.jdexreport.e;

import android.content.Context;

/* loaded from: classes.dex */
public class a extends c {

    /* renamed from: e  reason: collision with root package name */
    public static a f12595e;

    public a(Context context) {
        super(context, "cache.info", "cacheinfo");
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (f12595e == null) {
                f12595e = new a(context);
            }
            aVar = f12595e;
        }
        return aVar;
    }
}
