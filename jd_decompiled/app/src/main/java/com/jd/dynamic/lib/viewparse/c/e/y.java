package com.jd.dynamic.lib.viewparse.c.e;

import android.view.View;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class y extends p0<View> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    public void a(HashMap<String, String> hashMap, View view) {
        if (hashMap.containsKey("contentDescription")) {
            view.setContentDescription(hashMap.get("contentDescription"));
        }
    }
}
