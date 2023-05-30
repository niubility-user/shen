package com.jingdong.common.pool.bitmappool.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public class LruBitmapPool implements BitmapPool {
    private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    private static final String TAG = "LruBitmapPool";
    private final Set<Bitmap.Config> allowedConfigs;
    private int currentSize;
    private int evictions;
    private int hits;
    private final int initialMaxSize;
    private int maxSize;
    private int misses;
    private int puts;
    private final LruPoolStrategy strategy;
    private final BitmapTracker tracker;

    /* loaded from: classes5.dex */
    public interface BitmapTracker {
        void add(Bitmap bitmap);

        void remove(Bitmap bitmap);
    }

    /* loaded from: classes5.dex */
    public static class NullBitmapTracker implements BitmapTracker {
        private NullBitmapTracker() {
        }

        @Override // com.jingdong.common.pool.bitmappool.internal.LruBitmapPool.BitmapTracker
        public void add(Bitmap bitmap) {
        }

        @Override // com.jingdong.common.pool.bitmappool.internal.LruBitmapPool.BitmapTracker
        public void remove(Bitmap bitmap) {
        }
    }

    /* loaded from: classes5.dex */
    private static class ThrowingBitmapTracker implements BitmapTracker {
        private final Set<Bitmap> bitmaps = Collections.synchronizedSet(new HashSet());

        private ThrowingBitmapTracker() {
        }

        @Override // com.jingdong.common.pool.bitmappool.internal.LruBitmapPool.BitmapTracker
        public void add(Bitmap bitmap) {
            if (!this.bitmaps.contains(bitmap)) {
                this.bitmaps.add(bitmap);
                return;
            }
            throw new IllegalStateException("Can't add already added bitmap: " + bitmap + " [" + bitmap.getWidth() + JshopConst.JSHOP_PROMOTIO_X + bitmap.getHeight() + "]");
        }

        @Override // com.jingdong.common.pool.bitmappool.internal.LruBitmapPool.BitmapTracker
        public void remove(Bitmap bitmap) {
            if (this.bitmaps.contains(bitmap)) {
                this.bitmaps.remove(bitmap);
                return;
            }
            throw new IllegalStateException("Cannot remove bitmap not in tracker");
        }
    }

    private LruBitmapPool(int i2, LruPoolStrategy lruPoolStrategy, Set<Bitmap.Config> set) {
        this.initialMaxSize = i2;
        this.maxSize = i2;
        this.strategy = lruPoolStrategy;
        this.allowedConfigs = set;
        this.tracker = new NullBitmapTracker();
    }

    private void dump() {
        if (Log.isLoggable(TAG, 2)) {
            dumpUnchecked();
        }
    }

    private void dumpUnchecked() {
        String str = "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + "\nStrategy=" + this.strategy;
    }

    private void evict() {
        trimToSize(this.maxSize);
    }

    private static Set<Bitmap.Config> getDefaultAllowedConfigs() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(Bitmap.Config.values()));
        if (Build.VERSION.SDK_INT >= 19) {
            hashSet.add(null);
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private static LruPoolStrategy getDefaultStrategy() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new SizeConfigStrategy();
        }
        return new AttributeStrategy();
    }

    private synchronized Bitmap getDirtyOrNull(int i2, int i3, Bitmap.Config config) {
        Bitmap bitmap;
        bitmap = this.strategy.get(i2, i3, config != null ? config : DEFAULT_CONFIG);
        if (bitmap == null) {
            if (Log.isLoggable(TAG, 3)) {
                String str = "Missing bitmap=" + this.strategy.logBitmap(i2, i3, config);
            }
            this.misses++;
        } else {
            this.hits++;
            this.currentSize -= this.strategy.getSize(bitmap);
            this.tracker.remove(bitmap);
            normalize(bitmap);
        }
        if (Log.isLoggable(TAG, 2)) {
            String str2 = "Get bitmap=" + this.strategy.logBitmap(i2, i3, config);
        }
        dump();
        return bitmap;
    }

    @TargetApi(12)
    private static void maybeSetAlpha(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }

    @TargetApi(19)
    private static void maybeSetPreMultiplied(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            bitmap.setPremultiplied(true);
        }
    }

    private static void normalize(Bitmap bitmap) {
        maybeSetAlpha(bitmap);
        maybeSetPreMultiplied(bitmap);
    }

    private synchronized void trimToSize(int i2) {
        while (this.currentSize > i2) {
            Bitmap removeLast = this.strategy.removeLast();
            if (removeLast == null) {
                if (Log.isLoggable(TAG, 5)) {
                    dumpUnchecked();
                }
                this.currentSize = 0;
                return;
            }
            this.tracker.remove(removeLast);
            this.currentSize -= this.strategy.getSize(removeLast);
            this.evictions++;
            if (Log.isLoggable(TAG, 3)) {
                String str = "Evicting bitmap=" + this.strategy.logBitmap(removeLast);
            }
            dump();
            removeLast.recycle();
        }
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.BitmapPool
    public void clearMemory() {
        Log.isLoggable(TAG, 3);
        trimToSize(0);
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.BitmapPool
    public Bitmap get(int i2, int i3, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i2, i3, config);
        if (dirtyOrNull != null && !dirtyOrNull.isRecycled()) {
            dirtyOrNull.eraseColor(0);
            return dirtyOrNull;
        }
        return Bitmap.createBitmap(i2, i3, config);
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.BitmapPool
    public Bitmap getDirty(int i2, int i3, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i2, i3, config);
        return (dirtyOrNull == null || dirtyOrNull.isRecycled()) ? Bitmap.createBitmap(i2, i3, config) : dirtyOrNull;
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.BitmapPool
    public int getMaxSize() {
        return this.maxSize;
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.BitmapPool
    public synchronized void put(Bitmap bitmap) {
        try {
            if (bitmap != null) {
                if (!bitmap.isRecycled()) {
                    if (bitmap.isMutable() && this.strategy.getSize(bitmap) <= this.maxSize && this.allowedConfigs.contains(bitmap.getConfig())) {
                        int size = this.strategy.getSize(bitmap);
                        this.strategy.put(bitmap);
                        this.tracker.add(bitmap);
                        this.puts++;
                        this.currentSize += size;
                        if (Log.isLoggable(TAG, 2)) {
                            String str = "Put bitmap in pool=" + this.strategy.logBitmap(bitmap);
                        }
                        dump();
                        evict();
                        return;
                    }
                    if (Log.isLoggable(TAG, 2)) {
                        String str2 = "Reject bitmap from pool, bitmap: " + this.strategy.logBitmap(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.allowedConfigs.contains(bitmap.getConfig());
                    }
                    bitmap.recycle();
                    return;
                }
                throw new IllegalStateException("Cannot pool recycled bitmap");
            }
            throw new NullPointerException("Bitmap must not be null");
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.BitmapPool
    public synchronized void setSizeMultiplier(float f2) {
        this.maxSize = Math.round(this.initialMaxSize * f2);
        evict();
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.BitmapPool
    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i2) {
        if (Log.isLoggable(TAG, 3)) {
            String str = "trimMemory, level=" + i2;
        }
        if (i2 >= 40) {
            clearMemory();
        } else if (i2 >= 20) {
            trimToSize(this.maxSize / 2);
        }
    }

    public LruBitmapPool(int i2) {
        this(i2, getDefaultStrategy(), getDefaultAllowedConfigs());
    }

    public LruBitmapPool(int i2, Set<Bitmap.Config> set) {
        this(i2, getDefaultStrategy(), set);
    }
}
