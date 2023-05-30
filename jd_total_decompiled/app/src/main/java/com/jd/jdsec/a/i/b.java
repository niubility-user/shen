package com.jd.jdsec.a.i;

import android.annotation.SuppressLint;

@SuppressLint({"MissingPermission"})
/* loaded from: classes13.dex */
public class b extends a {
    private String k() {
        try {
            return !com.jd.jdsec.a.a.a(com.jd.jdsec.c.g.a) ? "" : com.jingdong.jdsdk.a.a.a;
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jd.jdsec.a.i.a
    protected String b() {
        return com.jingdong.jdsdk.a.a.a;
    }

    @Override // com.jd.jdsec.a.i.a
    protected String e() {
        return k();
    }

    @Override // com.jd.jdsec.a.i.a
    protected boolean j() {
        return com.jd.jdsec.c.c.c().d();
    }
}
