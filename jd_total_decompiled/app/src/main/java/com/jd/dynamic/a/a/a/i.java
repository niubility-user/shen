package com.jd.dynamic.a.a.a;

/* loaded from: classes13.dex */
public class i {
    public static Object a(int i2, Object... objArr) {
        if (i2 < 0 || objArr == null || objArr.length <= 0 || i2 >= objArr.length) {
            return null;
        }
        return objArr[i2];
    }
}
