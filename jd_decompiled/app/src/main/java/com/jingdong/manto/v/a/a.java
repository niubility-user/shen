package com.jingdong.manto.v.a;

import com.jd.dynamic.DYConstants;

/* loaded from: classes16.dex */
public class a {
    public Object[] a;

    public static <T> b<T> a(T t) {
        b<T> bVar = new b<>();
        bVar.a = new Object[]{t};
        return bVar;
    }

    public static <T1, T2> d<T1, T2> a(T1 t1, T2 t2) {
        d<T1, T2> dVar = new d<>();
        dVar.a = new Object[]{t1, t2};
        return dVar;
    }

    public final int a() {
        Object[] objArr = this.a;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public final <T> T a(int i2) {
        Object[] objArr = this.a;
        if (objArr.length <= i2) {
            return null;
        }
        return (T) objArr[i2];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Integer num = 1;
        for (Object obj : this.a) {
            if (num != null) {
                num = null;
            } else {
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.append(obj);
        }
        return sb.toString();
    }
}
