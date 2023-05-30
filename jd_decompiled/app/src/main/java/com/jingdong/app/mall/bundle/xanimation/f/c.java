package com.jingdong.app.mall.bundle.xanimation.f;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;

/* loaded from: classes3.dex */
public class c {
    public static boolean a(String str) {
        return TextUtils.isEmpty(str) || str.trim().equals("") || DYConstants.DY_NULL_STR.equalsIgnoreCase(str);
    }
}
