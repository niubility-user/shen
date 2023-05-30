package com.xiaomi.push.service;

import com.xiaomi.push.i7;
import com.xiaomi.push.j7;

/* loaded from: classes11.dex */
/* synthetic */ class e0 {
    static final /* synthetic */ int[] a;
    static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[j7.values().length];
        b = iArr;
        try {
            iArr[j7.INT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            b[j7.LONG.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            b[j7.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            b[j7.BOOLEAN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr2 = new int[i7.values().length];
        a = iArr2;
        try {
            iArr2[i7.MISC_CONFIG.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[i7.PLUGIN_CONFIG.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
