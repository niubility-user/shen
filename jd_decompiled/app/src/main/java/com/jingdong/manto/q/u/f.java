package com.jingdong.manto.q.u;

import android.content.Context;
import com.jingdong.manto.q.u.a;
import java.util.Map;

/* loaded from: classes16.dex */
public class f extends a {
    public String b = "VideoPlayer";

    /* renamed from: c  reason: collision with root package name */
    public String f14126c = "JD-VIDEO-DIV";

    @Override // com.jingdong.manto.q.u.a
    public a.C0663a a(Context context, Map<String, String> map) {
        if (map == null || !map.containsKey("video-container-id")) {
            return null;
        }
        return a(context, Integer.valueOf(map.get("video-container-id")).intValue());
    }
}
