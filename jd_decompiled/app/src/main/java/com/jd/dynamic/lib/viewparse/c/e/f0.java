package com.jd.dynamic.lib.viewparse.c.e;

import android.text.TextUtils;
import android.view.View;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class f0 extends p0<View> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    public void a(HashMap<String, String> hashMap, View view) {
        if (!hashMap.containsKey("tag") || TextUtils.isEmpty(hashMap.get("tag"))) {
            return;
        }
        view.setTag(hashMap.get("tag"));
    }
}
