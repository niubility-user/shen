package com.jingdong.sdk.perfmonitor.d;

import com.jingdong.sdk.perfmonitor.b.e;
import java.util.Map;

/* loaded from: classes12.dex */
public class e extends b {
    public e.p c(String str) {
        Map<String, String> map;
        if (this.a && ((map = this.b) == null || !map.containsValue(str))) {
            return e.p.AUTO;
        }
        if (b(str)) {
            return e.p.STARTUP;
        }
        return null;
    }
}
