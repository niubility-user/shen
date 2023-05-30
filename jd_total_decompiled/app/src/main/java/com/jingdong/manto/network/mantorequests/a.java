package com.jingdong.manto.network.mantorequests;

import android.text.TextUtils;

/* loaded from: classes16.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f13878c;

    public a(String str, String str2, String str3, boolean z) {
        this.a = str;
        this.b = str2;
        this.f13878c = str3;
        if (z) {
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str2)) {
                throw new IllegalArgumentException("savedPathDir or savedFileName can not be null when forceSaveStyle is true");
            }
        }
    }

    public String a() {
        return this.f13878c;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }
}
