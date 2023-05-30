package com.jingdong.sdk.platform.business.personal.common;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.widget.PersonalLottieView;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes10.dex */
public class PersonalCommonUtil {
    public static final int INVALID_TIMESTAMP = -1;
    private static final String TAG = "PersonalCommonUtil";

    private PersonalCommonUtil() {
    }

    public static long convertTimestamp(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return -1L;
            }
            return Long.parseLong(str);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, String.format("parse updateTimeStamp exception:%s", e2));
                return -1L;
            }
            return -1L;
        }
    }

    @NonNull
    public static String getSafetyString(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static int getShowPxValue(int i2) {
        return DPIUtil.dip2px(i2 / 2);
    }

    public static boolean isShowLottie(View view) {
        return ABTestUtils.isLottieEnable() && Build.VERSION.SDK_INT >= 16 && (view instanceof PersonalLottieView);
    }

    public static void setNumberTextFont(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        FontsUtil.changeTextFont(textView, 4099);
        textView.setText(str);
    }
}
