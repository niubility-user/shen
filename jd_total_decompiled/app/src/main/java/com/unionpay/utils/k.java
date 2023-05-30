package com.unionpay.utils;

import java.util.Locale;

/* loaded from: classes11.dex */
public class k {

    /* renamed from: f  reason: collision with root package name */
    private static k f18232f;
    public String a = "";
    public String b = "";

    /* renamed from: c  reason: collision with root package name */
    public String f18233c = "";
    public String d = "";

    /* renamed from: e  reason: collision with root package name */
    public String f18234e = "";

    public static k a() {
        if (f18232f == null) {
            f18232f = Locale.getDefault().toString().startsWith("zh") ? new l() : new m();
        }
        return f18232f;
    }
}
