package com.jingdong.jdma.common.utils;

import android.content.Context;
import java.io.File;

/* loaded from: classes12.dex */
public class j {
    public static File a(Context context) {
        return context.getExternalCacheDir();
    }

    public static boolean b(Context context) {
        File a;
        return (context == null || (a = a(context)) == null || !a.exists()) ? false : true;
    }
}
