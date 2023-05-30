package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.base.DynamicUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class v extends f<View> {

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f2446c;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean d(Map.Entry entry) {
        return Boolean.valueOf(DynamicUtils.isEL((String) entry.getValue()) || DynamicUtils.isKnownSymbol((String) entry.getValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(HashMap hashMap, Map.Entry entry) {
        hashMap.remove(entry.getKey());
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01da  */
    @Override // com.jd.dynamic.lib.viewparse.c.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View a(java.util.HashMap<java.lang.String, java.lang.String> r5, android.view.View r6) {
        /*
            Method dump skipped, instructions count: 484
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.viewparse.c.v.a(java.util.HashMap, android.view.View):android.view.View");
    }

    @Override // com.jd.dynamic.lib.viewparse.c.f
    public void c(JSONObject jSONObject) {
        this.f2446c = jSONObject;
    }
}
