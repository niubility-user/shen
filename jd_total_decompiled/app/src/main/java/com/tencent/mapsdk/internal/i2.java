package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public interface i2 {
    public static final String d = "services";

    /* loaded from: classes9.dex */
    public interface a {
        void setAllow(boolean z);

        void setUseHttps(boolean z);

        void setUseTest(boolean z);
    }

    a a(String str);

    <T extends p> void a(Class<T> cls);

    <T extends a> void a(String str, Class<T> cls);

    h2 c();
}
