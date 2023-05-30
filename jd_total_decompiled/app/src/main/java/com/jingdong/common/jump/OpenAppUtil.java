package com.jingdong.common.jump;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.utils.base.BaseArchUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class OpenAppUtil {
    public static void addRefererToIntent(Activity activity, Intent intent) {
        if (activity == null || intent == null) {
            return;
        }
        try {
            String referer = BaseArchUtil.getReferer(activity);
            String appPackageName = BaseInfo.getAppPackageName();
            if (!TextUtils.isEmpty(referer) && !TextUtils.equals(appPackageName, referer)) {
                intent.putExtra("openAppActivityReferer", referer);
            }
        } catch (Throwable unused) {
            if (OKLog.D) {
                OKLog.d("OpenAppUtil", "addRefererToIntent err");
            }
        }
    }

    public static <T> String appendUrlParams(String str, String str2, T t) {
        String str3 = str2 + ContainerUtils.KEY_VALUE_DELIMITER + t;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (!str.contains("?")) {
            return str + "?" + str3;
        }
        return str + ContainerUtils.FIELD_DELIMITER + str3;
    }

    public static String getQueryParameter(Uri uri, String str) {
        if (uri.isOpaque()) {
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        }
        if (str != null) {
            String encodedQuery = uri.getEncodedQuery();
            if (encodedQuery == null) {
                return null;
            }
            String encode = Uri.encode(str, null);
            int length = encodedQuery.length();
            int i2 = 0;
            while (true) {
                int indexOf = encodedQuery.indexOf(38, i2);
                int i3 = indexOf != -1 ? indexOf : length;
                int indexOf2 = encodedQuery.indexOf(61, i2);
                if (indexOf2 > i3 || indexOf2 == -1) {
                    indexOf2 = i3;
                }
                if (indexOf2 - i2 == encode.length() && encodedQuery.regionMatches(i2, encode, 0, encode.length())) {
                    return indexOf2 == i3 ? "" : encodedQuery.substring(indexOf2 + 1, i3);
                } else if (indexOf == -1) {
                    return null;
                } else {
                    i2 = indexOf + 1;
                }
            }
        } else {
            throw new NullPointerException("key");
        }
    }

    public static String getReferer(Context context) {
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity != null) {
            try {
                return BaseArchUtil.getReferer(activity);
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    public static boolean isJDReferer(Context context) {
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity != null) {
            try {
                String referer = BaseArchUtil.getReferer(activity);
                String appPackageName = BaseInfo.getAppPackageName();
                if (TextUtils.isEmpty(referer)) {
                    return false;
                }
                return TextUtils.equals(appPackageName, referer);
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    public static String justifyScheme(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str) && (TextUtils.equals(str, OpenAppConstant.SCHEME_OPENAPP_1) || TextUtils.equals(str, "openapp.jdmobile") || TextUtils.equals(str, "openjd") || TextUtils.equals(str, "jdpay") || TextUtils.equals(str, "jdpayopen"))) {
            z = true;
        }
        return !z ? OpenAppConstant.SCHEME_OPENAPP_1 : str;
    }
}
