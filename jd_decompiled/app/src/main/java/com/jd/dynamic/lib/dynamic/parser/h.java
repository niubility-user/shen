package com.jd.dynamic.lib.dynamic.parser;

import android.text.TextUtils;
import com.jd.dynamic.base.DynamicTemplateEngine;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class h {
    public static int a(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        Integer num;
        if (TextUtils.isEmpty(str) || dynamicTemplateEngine == null || dynamicTemplateEngine.getViewIdTable() == null || (num = dynamicTemplateEngine.getViewIdTable().get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public static int b(AtomicInteger atomicInteger, List<Integer> list) {
        int andIncrement = atomicInteger.getAndIncrement();
        return list.contains(Integer.valueOf(andIncrement)) ? b(atomicInteger, list) : andIncrement;
    }

    public static void c(DynamicTemplateEngine dynamicTemplateEngine, String str, int i2) {
        if (dynamicTemplateEngine == null || dynamicTemplateEngine.getViewIdTable() == null || TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (h.class) {
            dynamicTemplateEngine.getViewIdTable().put(str, Integer.valueOf(i2));
        }
    }
}
