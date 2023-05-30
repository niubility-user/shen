package com.jd.dynamic.a.a.a;

import android.app.Activity;
import com.jd.dynamic.lib.utils.DPIUtil;

/* loaded from: classes13.dex */
public class e implements h {
    private Activity a;

    public e(Activity activity) {
        this.a = activity;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        if ("getScreenWidth".equals(str)) {
            return Integer.valueOf(DPIUtil.getWidthWithDp(this.a));
        }
        return null;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "Device";
    }
}
