package com.jingdong.app.mall.home.o.a;

import android.graphics.Bitmap;
import java.io.File;

/* loaded from: classes4.dex */
public class o {
    public static boolean a(Bitmap bitmap) {
        return (bitmap == null || bitmap.isRecycled()) ? false : true;
    }

    public static boolean b(File file) {
        return file != null && file.exists();
    }
}
