package com.jd.stat.common.a;

import android.annotation.SuppressLint;
import com.jd.stat.common.l;

@SuppressLint({"MissingPermission", "HardwareIds"})
/* loaded from: classes18.dex */
public class i extends a {
    private String f() {
        try {
            if (!l.a(com.jd.stat.security.c.a)) {
            }
        } catch (Exception unused) {
        }
        return "";
    }

    @Override // com.jd.stat.common.a.a
    protected String a() {
        return f();
    }

    @Override // com.jd.stat.common.a.a
    protected String b() {
        return "";
    }

    @Override // com.jd.stat.common.a.a
    protected boolean c() {
        return com.jd.stat.security.d.a().m();
    }
}
