package com.jingdong.sdk.platform.lib.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;

/* loaded from: classes10.dex */
public class InflateUtil {
    private static final String TAG = "ImageUtil";

    private static LayoutInflater getLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public static View inflate(Context context, int i2, ViewGroup viewGroup) {
        try {
            return getLayoutInflater(context).inflate(i2, viewGroup);
        } catch (Throwable unused) {
            GlobalImageCache.getLruBitmapCache().clean();
            return getLayoutInflater(context).inflate(i2, viewGroup);
        }
    }

    public static View inflate(Context context, int i2, ViewGroup viewGroup, boolean z) {
        try {
            return getLayoutInflater(context).inflate(i2, viewGroup, z);
        } catch (Throwable unused) {
            GlobalImageCache.getLruBitmapCache().clean();
            return getLayoutInflater(context).inflate(i2, viewGroup, z);
        }
    }
}
