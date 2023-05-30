package com.huawei.hms.hatool;

import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class f implements g {
    private byte[] a;
    private String b;

    /* renamed from: c */
    private String f1361c;
    private String d;

    /* renamed from: e */
    private String f1362e;

    /* renamed from: f */
    private List<b1> f1363f;

    public f(byte[] bArr, String str, String str2, String str3, String str4, List<b1> list) {
        this.a = (byte[]) bArr.clone();
        this.b = str;
        this.f1361c = str2;
        this.f1362e = str3;
        this.d = str4;
        this.f1363f = list;
    }

    private n0 a(Map<String, String> map) {
        return w.a(this.b, this.a, map);
    }

    private Map<String, String> a() {
        return k.b(this.f1361c, this.f1362e, this.d);
    }

    private void b() {
        b0.c().a(new d1(this.f1363f, this.f1361c, this.d, this.f1362e));
    }

    @Override // java.lang.Runnable
    public void run() {
        v.c("hmsSdk", "send data running");
        int b = a(a()).b();
        if (b != 200) {
            b();
            return;
        }
        v.b("hmsSdk", "events PostRequest sendevent TYPE : %s, TAG : %s, resultCode: %d ,reqID:" + this.d, this.f1362e, this.f1361c, Integer.valueOf(b));
    }
}
