package com.jingdong.common.pool.bitmappool.internal;

import android.graphics.Bitmap;

/* loaded from: classes5.dex */
public interface BitmapPool {
    void clearMemory();

    Bitmap get(int i2, int i3, Bitmap.Config config);

    Bitmap getDirty(int i2, int i3, Bitmap.Config config);

    int getMaxSize();

    void put(Bitmap bitmap);

    void setSizeMultiplier(float f2);

    void trimMemory(int i2);
}
