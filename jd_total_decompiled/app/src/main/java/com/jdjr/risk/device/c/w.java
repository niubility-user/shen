package com.jdjr.risk.device.c;

import android.os.Debug;

/* loaded from: classes18.dex */
public class w {
    public static boolean a(int i2) {
        try {
            return Debug.getNativeHeapFreeSize() > ((long) ((i2 * 1024) * 1024));
        } catch (Throwable unused) {
            return true;
        }
    }
}
