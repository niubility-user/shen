package com.jingdong.common.pool.bitmappool;

import android.graphics.Bitmap;
import com.jingdong.common.pool.bitmappool.internal.BitmapPool;
import com.jingdong.common.pool.bitmappool.internal.LruBitmapPool;
import java.util.Set;

/* loaded from: classes5.dex */
public class GlideBitmapPool {
    private static final int DEFAULT_MAX_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);
    private static GlideBitmapPool sInstance;
    private BitmapPool bitmapPool;

    private GlideBitmapPool(int i2) {
        this.bitmapPool = new LruBitmapPool(i2);
    }

    public static void clearMemory() {
        getInstance().bitmapPool.clearMemory();
    }

    public static Bitmap getBitmap(int i2, int i3, Bitmap.Config config) {
        return getInstance().bitmapPool.get(i2, i3, config);
    }

    public static Bitmap getDirtyBitmap(int i2, int i3, Bitmap.Config config) {
        return getInstance().bitmapPool.getDirty(i2, i3, config);
    }

    private static GlideBitmapPool getInstance() {
        GlideBitmapPool glideBitmapPool;
        GlideBitmapPool glideBitmapPool2 = sInstance;
        if (glideBitmapPool2 != null) {
            return glideBitmapPool2;
        }
        synchronized (GlideBitmapPool.class) {
            if (sInstance == null) {
                sInstance = new GlideBitmapPool(DEFAULT_MAX_SIZE);
            }
            glideBitmapPool = sInstance;
        }
        return glideBitmapPool;
    }

    public static void initialize(int i2) {
        sInstance = new GlideBitmapPool(i2);
    }

    public static void putBitmap(Bitmap bitmap) {
        getInstance().bitmapPool.put(bitmap);
    }

    public static void shutDown() {
        GlideBitmapPool glideBitmapPool = sInstance;
        if (glideBitmapPool != null) {
            glideBitmapPool.bitmapPool.clearMemory();
            sInstance = null;
        }
    }

    public static void trimMemory(int i2) {
        getInstance().bitmapPool.trimMemory(i2);
    }

    public static void initialize(int i2, Set<Bitmap.Config> set) {
        sInstance = new GlideBitmapPool(i2, set);
    }

    private GlideBitmapPool(int i2, Set<Bitmap.Config> set) {
        this.bitmapPool = new LruBitmapPool(i2, set);
    }
}
