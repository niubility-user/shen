package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes12.dex */
public abstract class e0 {
    private i a(int i2) {
        String str;
        if (i2 != 0) {
            str = f();
            if (!TextUtils.isEmpty(str)) {
                return new i(d0.UDID, str);
            }
        } else {
            str = "";
        }
        return new i(d0.EMPTY, str);
    }

    private i b(int i2) {
        String str;
        if ((i2 & 4) != 0) {
            str = f();
            if (!TextUtils.isEmpty(str)) {
                return new i(d0.UDID, str);
            }
        } else {
            str = "";
        }
        return new i(d0.EMPTY, str);
    }

    private boolean e() {
        g1 b = s.c().b();
        if (TextUtils.isEmpty(b.l())) {
            b.h(o.a());
        }
        return !TextUtils.isEmpty(b.l());
    }

    private String f() {
        g1 b = s.c().b();
        if (TextUtils.isEmpty(b.i())) {
            b.e(x0.c());
        }
        return b.i();
    }

    public i a(Context context) {
        String c2 = c();
        if (TextUtils.isEmpty(c2)) {
            String a = a();
            if (TextUtils.isEmpty(a)) {
                boolean e2 = e();
                String b = b();
                return !TextUtils.isEmpty(b) ? e2 ? new i(d0.SN, b) : new i(d0.UDID, a(b)) : e2 ? a(d()) : b(d());
            }
            return new i(d0.IMEI, a);
        }
        return new i(d0.UDID, c2);
    }

    public abstract String a();

    public abstract String a(String str);

    public abstract String b();

    public abstract String c();

    public abstract int d();
}
