package com.unionpay.a;

import java.net.URL;
import java.util.HashMap;

/* loaded from: classes11.dex */
public final class d {
    private String b;

    /* renamed from: e  reason: collision with root package name */
    private String f18145e;
    private int a = 1;

    /* renamed from: c  reason: collision with root package name */
    private HashMap f18144c = null;
    private byte[] d = null;

    public d(String str) {
        this.b = str;
    }

    public final URL a() {
        try {
            return new URL(this.b);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void a(String str) {
        if (str != null) {
            this.d = str.getBytes();
            this.f18145e = str;
        }
    }

    public final String b() {
        return this.a == 1 ? "POST" : "GET";
    }

    public final String c() {
        return this.f18145e;
    }

    public final HashMap d() {
        return this.f18144c;
    }
}
