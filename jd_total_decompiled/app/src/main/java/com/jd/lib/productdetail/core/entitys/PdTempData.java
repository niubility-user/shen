package com.jd.lib.productdetail.core.entitys;

import java.util.HashMap;

/* loaded from: classes15.dex */
public class PdTempData {
    public HashMap<String, Object> data = new HashMap<>();

    public void clear() {
        this.data.clear();
    }

    public String getString(String str) {
        if (this.data.containsKey(str)) {
            Object obj = this.data.get(str);
            if (obj instanceof String) {
                return (String) obj;
            }
            return null;
        }
        return null;
    }

    public void put(String str, Object obj) {
        this.data.put(str, obj);
    }
}
