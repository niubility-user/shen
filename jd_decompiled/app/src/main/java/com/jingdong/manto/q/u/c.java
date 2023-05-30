package com.jingdong.manto.q.u;

import android.content.Context;
import com.jingdong.manto.q.u.a;
import java.util.Map;

/* loaded from: classes16.dex */
public class c extends a {
    public String b = "LivePlayer";

    /* renamed from: c  reason: collision with root package name */
    public String f14114c = "JD-LIVE-DIV";
    private String d = "live-container-id";

    @Override // com.jingdong.manto.q.u.a
    public a.C0663a a(Context context, Map<String, String> map) {
        if (map == null || !map.containsKey(this.d)) {
            return null;
        }
        return a(context, Integer.valueOf(map.get(this.d)).intValue());
    }
}
