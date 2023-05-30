package com.novoda.imageloader.core.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.jingdong.jdsdk.image.JDFrescoUtils;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.novoda.imageloader.core.cache.util.LruCache;

/* loaded from: classes14.dex */
public class LruBitmapCache {
    public static final int DEFAULT_MEMORY_CACHE_PERCENTAGE = 25;
    private static final int DEFAULT_MEMORY_CAPACITY_FOR_DEVICES_OLDER_THAN_API_LEVEL_4 = 12;
    private static final long ONE_M_BYTES = 1048576;
    private LruCache<GlobalImageCache.BitmapDigest, Bitmap> cache;
    private long capacity;

    public LruBitmapCache(Context context) {
        this(context, 25);
    }

    public LruBitmapCache(Context context, int i2) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int memoryClass = activityManager != null ? activityManager.getMemoryClass() : 0;
        FLog.d(LruBitmapCache.class.getName(), "LruBitmapCache() memClass1 -->> " + memoryClass);
        memoryClass = memoryClass == 0 ? 12 : memoryClass;
        FLog.d(LruBitmapCache.class.getName(), "LruBitmapCache() memClass2 -->> " + memoryClass);
        this.capacity = (memoryClass * ((i2 < 0 ? 0 : i2) > 81 ? 80 : r7)) / 100;
        FLog.d(LruBitmapCache.class.getName(), "LruBitmapCache() capacity1 -->> " + this.capacity);
        if (this.capacity <= 0) {
            this.capacity = 4L;
        }
        FLog.d(LruBitmapCache.class.getName(), "LruBitmapCache() capacity2 -->> " + this.capacity);
        this.capacity = this.capacity * ONE_M_BYTES;
        reset();
    }

    private void recycleMemery() {
        System.gc();
    }

    private void reset() {
        LruCache<GlobalImageCache.BitmapDigest, Bitmap> lruCache = this.cache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
        this.cache = new LruCache<GlobalImageCache.BitmapDigest, Bitmap>(this.capacity) { // from class: com.novoda.imageloader.core.cache.LruBitmapCache.1
            {
                LruBitmapCache.this = this;
            }

            @Override // com.novoda.imageloader.core.cache.util.LruCache
            public void entryRemoved(boolean z, GlobalImageCache.BitmapDigest bitmapDigest, Bitmap bitmap, Bitmap bitmap2) {
                FLog.d(LruBitmapCache.class.getName(), "entryRemoved() bitmapDigest -->> " + bitmapDigest);
                FLog.d(LruBitmapCache.class.getName(), "entryRemoved() bitmap -->> " + bitmap);
                if (bitmapDigest.isAllowRecycle()) {
                    LruBitmapCache.this.cache.remove(bitmapDigest);
                }
                if (z) {
                    GlobalImageCache.remove(bitmapDigest);
                }
            }

            @Override // com.novoda.imageloader.core.cache.util.LruCache
            public long sizeOf(GlobalImageCache.BitmapDigest bitmapDigest, Bitmap bitmap) {
                FLog.d(LruBitmapCache.class.getName(), "sizeOf() bitmapRowBytes=" + bitmap.getRowBytes() + " bitmapWidth=" + bitmap.getWidth() + " bitmapHeight=" + bitmap.getHeight() + " size=" + (bitmap.getWidth() * bitmap.getHeight() * 4) + " -->> ");
                return bitmap.getWidth() * bitmap.getHeight() * 4;
            }
        };
    }

    public void clean() {
        cleanMost();
    }

    public void cleanMost() {
        JDFrescoUtils.clearMemoryCache();
        recycleMemery();
        double d = this.capacity;
        Double.isNaN(d);
        long round = Math.round(d * 0.5d);
        FLog.d(LruBitmapCache.class.getName(), "cleanMost() maxSize -->> " + round);
        this.cache.evict(round);
    }

    public Bitmap get(GlobalImageCache.BitmapDigest bitmapDigest) {
        FLog.d(LruBitmapCache.class.getName(), "get() bitmapDigest -->> " + bitmapDigest);
        return this.cache.get(bitmapDigest);
    }

    public void put(GlobalImageCache.BitmapDigest bitmapDigest, Bitmap bitmap) {
        FLog.d(LruBitmapCache.class.getName(), "put() bitmapDigest -->> " + bitmapDigest);
        this.cache.put(bitmapDigest, bitmap);
    }

    public void remove(GlobalImageCache.BitmapDigest bitmapDigest) {
        FLog.d(LruBitmapCache.class.getName(), "remove() bitmapDigest -->> " + bitmapDigest);
        this.cache.remove(bitmapDigest);
    }
}
