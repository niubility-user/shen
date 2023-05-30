package com.jd.dynamic.lib.viewparse;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.utils.t;

/* loaded from: classes13.dex */
public class d {
    public static float a(String str, Context context) {
        try {
            return DPIUtil.dip2pxF(context, Float.parseFloat(str));
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    public static int b(Context context, float f2) {
        return DPIUtil.dip2px(context, f2);
    }

    public static int c(DynamicTemplateEngine dynamicTemplateEngine, String str, Context context, String str2) {
        int identifier;
        if (TextUtils.isEmpty(str2)) {
            str2 = context.getPackageName();
        }
        String format = String.format("%s%s", str, str2);
        if (dynamicTemplateEngine == null || !dynamicTemplateEngine.getCachePool().imageResourceCacheContainsKey(format)) {
            try {
                identifier = context.getApplicationContext().getClassLoader().loadClass(str2 + ".R$drawable").getField(str).getInt(null);
            } catch (Exception unused) {
                t.e("ResourceUtils", "getImageResource error imageValue = " + str + ",packageName = " + str2);
                identifier = context.getResources().getIdentifier(str, "drawable", str2);
            }
            if (dynamicTemplateEngine != null) {
                dynamicTemplateEngine.getCachePool().putImageResourceCache(format, identifier);
            }
            return identifier;
        }
        return dynamicTemplateEngine.getCachePool().getImageResourceCache(format);
    }
}
