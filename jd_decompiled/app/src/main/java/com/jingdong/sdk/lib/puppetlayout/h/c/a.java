package com.jingdong.sdk.lib.puppetlayout.h.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;
import androidx.core.view.GravityCompat;
import com.jd.dynamic.DYConstants;

/* loaded from: classes8.dex */
public class a {
    private static LruCache<String, a> b = new LruCache<>(10);
    public int a;

    private a(String str) {
        this.a = 0;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.trim().split("\\s*\\|\\s*")) {
            this.a = c(str2) | this.a;
        }
    }

    public static a a(Context context, String str) {
        return b(str);
    }

    public static a b(String str) {
        a aVar = b.get(str);
        if (aVar == null) {
            a aVar2 = new a(str);
            b.put(str, aVar2);
            return aVar2;
        }
        return aVar;
    }

    private int c(String str) {
        if ("top".equals(str)) {
            return 48;
        }
        if ("bottom".equals(str)) {
            return 80;
        }
        if ("start".equals(str)) {
            return GravityCompat.START;
        }
        if ("end".equals(str)) {
            return GravityCompat.END;
        }
        if (DYConstants.DY_CENTER.equals(str)) {
            return 17;
        }
        if ("vcenter".equals(str)) {
            return 16;
        }
        if ("hcenter".equals(str)) {
            return 1;
        }
        if ("left".equals(str)) {
            return 19;
        }
        return "right".equals(str) ? 21 : 0;
    }
}
