package com.jingdong.common.unification.uniconfig;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class UnIconCache {
    private static UnIconCache cache;
    public LinkedHashMap<String, Map<String, IconConfigModel>> iconCache = new LRULinkedHashMap(30);

    /* loaded from: classes6.dex */
    static class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        private static final long serialVersionUID = 1;
        private int capacity;

        LRULinkedHashMap(int i2) {
            super(i2, 0.75f, true);
            this.capacity = i2;
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry<K, V> entry) {
            return size() > this.capacity;
        }
    }

    private UnIconCache() {
    }

    public static UnIconCache getInstance() {
        UnIconCache unIconCache;
        UnIconCache unIconCache2 = cache;
        if (unIconCache2 != null) {
            return unIconCache2;
        }
        synchronized (UnIconCache.class) {
            if (cache == null) {
                cache = new UnIconCache();
            }
            unIconCache = cache;
        }
        return unIconCache;
    }

    public void clear() {
        LinkedHashMap<String, Map<String, IconConfigModel>> linkedHashMap = this.iconCache;
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            return;
        }
        this.iconCache.clear();
    }

    public IconConfigModel get(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        Map<String, IconConfigModel> map = this.iconCache.get(str);
        if (map == null || map.isEmpty()) {
            return null;
        }
        IconConfigModel iconConfigModel = map.get(str2);
        return iconConfigModel == null ? (str2.endsWith(UnIconConfigConstants.UN_ICON_ID_B) || str2.endsWith(UnIconConfigConstants.UN_ICON_ID_B_S)) ? map.get(str) : iconConfigModel : iconConfigModel;
    }

    public void putCache(String str, Map<String, IconConfigModel> map) {
        this.iconCache.put(str, map);
    }

    public void update(String str, String str2, IconConfigModel iconConfigModel) {
        Map<String, IconConfigModel> map = this.iconCache.get(str);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(str2, iconConfigModel);
        this.iconCache.put(str, map);
    }
}
