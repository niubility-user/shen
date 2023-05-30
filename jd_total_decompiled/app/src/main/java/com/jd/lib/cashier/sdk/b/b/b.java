package com.jd.lib.cashier.sdk.b.b;

import com.jd.dynamic.DYConstants;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes14.dex */
public class b extends ConcurrentHashMap<String, Object> {
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public synchronized Object put(String str, Object obj) {
        if (str == null) {
            str = DYConstants.DY_NULL_STR;
        }
        if (obj == null) {
            obj = DYConstants.DY_NULL_STR;
        }
        try {
        } catch (Throwable th) {
            throw th;
        }
        return super.put((b) str, (String) obj);
    }
}
