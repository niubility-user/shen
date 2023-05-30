package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.li;
import java.io.File;

/* loaded from: classes9.dex */
public interface ji extends li.a {
    int a();

    Class a(String str);

    <T> Class<? extends T> a(String str, Class<T> cls);

    Object a(Class cls, String str, Class[] clsArr, Object[] objArr);

    <T> T a(Class<T> cls, Object... objArr);

    Object a(Object obj, String str, Class[] clsArr, Object[] objArr);

    Object a(Object obj, String str, Object... objArr);

    Object b(String str);

    ClassLoader d();

    File e();
}
