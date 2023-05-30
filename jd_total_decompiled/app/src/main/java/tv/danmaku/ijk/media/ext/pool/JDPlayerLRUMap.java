package tv.danmaku.ijk.media.ext.pool;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class JDPlayerLRUMap<K, V> extends LinkedHashMap<K, V> {
    private MapCallback mapCallback;
    private final int maxSize;

    /* loaded from: classes11.dex */
    interface MapCallback {
        void onRemoved(Map.Entry entry);
    }

    public JDPlayerLRUMap(int i2, MapCallback mapCallback) {
        this.maxSize = i2;
        this.mapCallback = mapCallback;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        MapCallback mapCallback;
        boolean z = size() > this.maxSize;
        if (z && (mapCallback = this.mapCallback) != null) {
            mapCallback.onRemoved(entry);
        }
        return z;
    }
}
