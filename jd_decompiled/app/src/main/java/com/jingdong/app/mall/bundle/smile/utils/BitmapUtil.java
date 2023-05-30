package com.jingdong.app.mall.bundle.smile.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* loaded from: classes3.dex */
public class BitmapUtil {
    public static Bitmap parseBitmap(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
