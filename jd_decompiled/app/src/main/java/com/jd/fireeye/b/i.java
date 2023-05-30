package com.jd.fireeye.b;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class i {
    public static String a(Context context) {
        try {
            return BaseInfo.getNetworkOperatorName(context);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        return "unknown";
    }
}
