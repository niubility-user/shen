package com.jingdong.common.pool.bitmappool.internal;

import android.graphics.Bitmap;

/* loaded from: classes5.dex */
interface LruPoolStrategy {
    Bitmap get(int i2, int i3, Bitmap.Config config);

    int getSize(Bitmap bitmap);

    String logBitmap(int i2, int i3, Bitmap.Config config);

    String logBitmap(Bitmap bitmap);

    void put(Bitmap bitmap);

    Bitmap removeLast();
}
