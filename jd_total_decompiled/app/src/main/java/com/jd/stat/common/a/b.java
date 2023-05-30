package com.jd.stat.common.a;

import android.annotation.SuppressLint;
import com.jd.stat.common.l;

@SuppressLint({"MissingPermission"})
/* loaded from: classes18.dex */
public class b extends a {
    private String f() {
        try {
            return !l.a(com.jd.stat.security.c.a) ? "" : com.jingdong.jdsdk.a.a.a;
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jd.stat.common.a.a
    protected String a() {
        return f();
    }

    @Override // com.jd.stat.common.a.a
    protected String b() {
        return com.jingdong.jdsdk.a.a.a;
    }

    @Override // com.jd.stat.common.a.a
    protected boolean c() {
        return com.jd.stat.security.d.a().m();
    }
}
