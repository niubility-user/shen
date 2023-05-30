package com.jingdong.manto.sdk;

import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class c<T> {
    private Object[] a;
    private int b;

    public c(int i2) {
        if (i2 <= 0) {
            MantoLog.e("MantoObjectPool", "The max pool size must be > 0");
        } else {
            this.a = new Object[i2];
        }
    }

    public T a() {
        int i2;
        Object[] objArr = this.a;
        if (objArr == null || (i2 = this.b) <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        T t = (T) objArr[i3];
        objArr[i3] = null;
        this.b = i3;
        return t;
    }

    public boolean a(T t) {
        if (this.a != null && this.b > 0) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.b) {
                    break;
                } else if (t.equals(this.a[i2])) {
                    while (i2 < this.b - 1) {
                        Object[] objArr = this.a;
                        int i3 = i2 + 1;
                        objArr[i2] = objArr[i3];
                        i2 = i3;
                    }
                } else {
                    i2++;
                }
            }
        }
        return true;
    }
}
