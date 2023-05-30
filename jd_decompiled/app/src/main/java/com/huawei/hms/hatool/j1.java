package com.huawei.hms.hatool;

/* loaded from: classes12.dex */
public class j1 implements g {
    private String a;
    private String b;

    public j1(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        w0.a(this.a, this.b);
    }
}
