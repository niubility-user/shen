package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.m9;

/* loaded from: classes9.dex */
public interface l9<D extends m9> {

    /* loaded from: classes9.dex */
    public interface a {
        public static final int a = -1;

        int a();
    }

    /* loaded from: classes9.dex */
    public interface b<T> {
        boolean a(T t);
    }

    long a();

    D a(String str, Class<D> cls);

    void a(String str, D d);

    boolean b(String str);

    void clear();

    long f();

    long getCount();
}
