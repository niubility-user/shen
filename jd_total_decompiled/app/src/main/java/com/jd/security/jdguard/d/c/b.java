package com.jd.security.jdguard.d.c;

import com.jd.security.jdguard.core.Bridge;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes17.dex */
public class b implements d {
    private static b a;

    private <T> T j(Object... objArr) {
        return (T) Bridge.main(102, objArr)[0];
    }

    public static b k() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String a() {
        return (String) j("8");
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String b() {
        return (String) j("6");
    }

    @Override // com.jd.security.jdguard.d.c.d
    public int c() {
        int intValue = ((Integer) j("0")).intValue();
        if (intValue < 0) {
            return 0;
        }
        return intValue;
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String d(int i2) {
        return (String) j("7", String.valueOf(i2));
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String e(String str) {
        return (String) j("1", str);
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String f() {
        return (String) j("2");
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String g() {
        return (String) j("5");
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String h() {
        return (String) j("3", String.valueOf(BaseInfo.getAndroidSDKVersion()));
    }

    @Override // com.jd.security.jdguard.d.c.d
    public String i() {
        return (String) j("4");
    }
}
