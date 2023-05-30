package com.jingdong.manto.utils;

import androidx.core.app.ActivityCompat;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public final class i {
    private static Map<String, ActivityCompat.OnRequestPermissionsResultCallback> a = new HashMap();

    public static void a(String str, int i2, String[] strArr, int[] iArr) {
        if (a.containsKey(str)) {
            a.remove(str).onRequestPermissionsResult(i2, strArr, iArr);
        }
    }
}
