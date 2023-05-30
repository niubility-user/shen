package com.jingdong.manto.q.u;

import android.content.Context;
import com.jingdong.manto.q.u.a;
import java.util.Map;

/* loaded from: classes16.dex */
public class b extends a {
    public String b = "Canvas";

    /* renamed from: c  reason: collision with root package name */
    public String f14113c = "JD-CANVAS-DIV";
    private String d = "canvas-container-id";

    @Override // com.jingdong.manto.q.u.a
    public a.C0663a a(Context context, Map<String, String> map) {
        if (map == null || !map.containsKey(this.d)) {
            return null;
        }
        return a(context, Integer.valueOf(map.get(this.d)).intValue(), true);
    }
}
