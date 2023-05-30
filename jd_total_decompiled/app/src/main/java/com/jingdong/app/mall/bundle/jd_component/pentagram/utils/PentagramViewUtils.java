package com.jingdong.app.mall.bundle.jd_component.pentagram.utils;

import android.text.TextUtils;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes2.dex */
public class PentagramViewUtils {
    public static boolean isDarkConfig() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 1;
    }

    public static int parseInt(String str, int i2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return Integer.parseInt(str);
            }
        } catch (NumberFormatException e2) {
            OKLog.e("ParseUtil", e2);
        }
        return i2;
    }
}
