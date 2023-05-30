package com.jingdong.app.mall.home.m;

import android.text.TextUtils;
import com.jingdong.app.mall.h.c.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.app.mall.home.o.a.o;
import java.io.File;

/* loaded from: classes4.dex */
public class a {
    public static String a(File file) {
        if (o.b(file) && file.length() > 0) {
            return "file://".concat(file.getAbsolutePath());
        }
        return null;
    }

    public static String b(String str) {
        return (TextUtils.isEmpty(str) || !l.e()) ? str : c(str);
    }

    public static String c(String str) {
        File d = d(str);
        if (o.b(d) && d.length() > 0) {
            f.r0(a.class, "\u547d\u4e2d\u9884\u4e0b\u8f7d\u7d20\u6750>>>" + str);
            return "file://".concat(d.getAbsolutePath());
        }
        return str;
    }

    private static File d(String str) {
        String a = c.a("home", str);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        File file = new File(a.replace("file://", ""));
        if (o.b(file)) {
            return file;
        }
        return null;
    }
}
