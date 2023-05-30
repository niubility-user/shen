package com.jingdong.sdk.lib.puppetlayout.f.b;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class b {
    static {
        new HashMap();
    }

    public static c a(String str, com.jingdong.sdk.lib.puppetlayout.f.c.b bVar) throws Exception {
        if (str.equals("YLayout")) {
            return new a(str, bVar);
        }
        String str2 = bVar.a.get("customClass");
        if (!TextUtils.isEmpty(str2)) {
            return new c(str, str2, bVar);
        }
        return new c(str, bVar);
    }
}
