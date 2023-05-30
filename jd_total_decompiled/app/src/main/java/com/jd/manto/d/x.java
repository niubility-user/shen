package com.jd.manto.d;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoProcessUtil;

/* loaded from: classes17.dex */
public class x {
    public static String a(Context context) {
        int lastIndexOf;
        String processName = MantoProcessUtil.getProcessName();
        if (TextUtils.isEmpty(processName) || (lastIndexOf = processName.lastIndexOf(":")) < 0 || lastIndexOf >= processName.length() - 1) {
            return "";
        }
        return context.getPackageName() + ".ACTION_ASSIST_" + processName.substring(processName.lastIndexOf(":") + 1).toUpperCase();
    }
}
