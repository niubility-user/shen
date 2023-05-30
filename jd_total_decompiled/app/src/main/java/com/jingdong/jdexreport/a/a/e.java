package com.jingdong.jdexreport.a.a;

import android.content.Context;
import java.io.File;

/* loaded from: classes.dex */
public class e {
    public static File a(Context context) {
        return context.getExternalCacheDir();
    }

    public static boolean b(Context context) {
        return a(context) != null && a(context).exists();
    }
}
