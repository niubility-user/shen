package com.jingdong.app.mall.home.p.b.f;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.n.h.c;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class a {
    public static boolean a(String str) {
        return TextUtils.equals(str, DYConstants.DY_TRUE);
    }

    public static int b(String str) {
        String[] e2 = e(str);
        if (e2 == null || e2.length <= 0) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.view.b.h.a.d(e2[0], 0);
    }

    public static int c(String str, int i2) {
        return c.h(str, i2);
    }

    @Nullable
    public static int[] d(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = TextUtils.split(str, DYConstants.DY_REGEX_COMMA);
        int length = split.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = c.h(split[i3], i2);
        }
        return iArr;
    }

    @Nullable
    public static String[] e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return TextUtils.split(str, DYConstants.DY_REGEX_COMMA);
    }
}
