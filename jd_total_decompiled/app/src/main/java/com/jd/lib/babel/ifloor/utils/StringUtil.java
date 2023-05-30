package com.jd.lib.babel.ifloor.utils;

import android.content.Context;
import com.jd.lib.babel.ifloor.R;

/* loaded from: classes13.dex */
public class StringUtil {
    public static String getYangJiao(Context context) {
        return context.getResources().getString(R.string.yangjiao);
    }

    public static String getYangJiaoPrice(Context context, String str) {
        return context.getResources().getString(R.string.yangjiao) + str;
    }
}
