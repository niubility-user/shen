package com.jingdong.common.pool.bitmappool.internal;

import android.graphics.Bitmap;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes5.dex */
public class AttributeStrategy implements LruPoolStrategy {
    private final KeyPool keyPool = new KeyPool();
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class Key implements Poolable {
        private Bitmap.Config config;
        private int height;
        private final KeyPool pool;
        private int width;

        public Key(KeyPool keyPool) {
            this.pool = keyPool;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                Key key = (Key) obj;
                return this.width == key.width && this.height == key.height && this.config == key.config;
            }
            return false;
        }

        public int hashCode() {
            int i2 = ((this.width * 31) + this.height) * 31;
            Bitmap.Config config = this.config;
            return i2 + (config != null ? config.hashCode() : 0);
        }

        public void init(int i2, int i3, Bitmap.Config config) {
            this.width = i2;
            this.height = i3;
            this.config = config;
        }

        @Override // com.jingdong.common.pool.bitmappool.internal.Poolable
        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            return AttributeStrategy.getBitmapString(this.width, this.height, this.config);
        }
    }

    /* loaded from: classes5.dex */
    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        public Key get(int i2, int i3, Bitmap.Config config) {
            Key key = get();
            key.init(i2, i3, config);
            return key;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jingdong.common.pool.bitmappool.internal.BaseKeyPool
        public Key create() {
            return new Key(this);
        }
    }

    private static String getBitmapString(Bitmap bitmap) {
        return getBitmapString(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.LruPoolStrategy
    public Bitmap get(int i2, int i3, Bitmap.Config config) {
        return this.groupedMap.get(this.keyPool.get(i2, i3, config));
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.LruPoolStrategy
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.LruPoolStrategy
    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.LruPoolStrategy
    public void put(Bitmap bitmap) {
        this.groupedMap.put(this.keyPool.get(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.LruPoolStrategy
    public Bitmap removeLast() {
        return this.groupedMap.removeLast();
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.groupedMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBitmapString(int i2, int i3, Bitmap.Config config) {
        return "[" + i2 + JshopConst.JSHOP_PROMOTIO_X + i3 + "], " + config;
    }

    @Override // com.jingdong.common.pool.bitmappool.internal.LruPoolStrategy
    public String logBitmap(int i2, int i3, Bitmap.Config config) {
        return getBitmapString(i2, i3, config);
    }
}
