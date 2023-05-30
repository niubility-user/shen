package com.jingdong.common.web.util;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes12.dex */
public class WebSwitchQueryFetcher {
    public static final String FIX_CHECK_URL = "wvCheckUrlFix";
    private static final String NEWGENTOKEN = "newGentoken";
    private static final String NEWGENTOKEN_WHITELIST = "newGentokenWhiteList";
    private static final String XVIEW_NEWGENTOKEN = "xviewNewGentoken";

    public static boolean newGentoken() {
        return SwitchQueryFetcher.getSwitchBooleanValue(NEWGENTOKEN, false);
    }

    public static boolean xViewNewGentoken() {
        return SwitchQueryFetcher.getSwitchBooleanValue(XVIEW_NEWGENTOKEN, false);
    }

    public static boolean newGentoken(URLParamMap uRLParamMap) {
        if (uRLParamMap != null) {
            String str = uRLParamMap.get(RemoteMessageConst.TO);
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return newGentoken(str);
        }
        return false;
    }

    public static boolean newGentoken(String str) {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(NEWGENTOKEN_WHITELIST, "");
        if (TextUtils.isEmpty(switchStringValue) || TextUtils.isEmpty(str)) {
            return false;
        }
        String decode = Uri.decode(str);
        try {
            Uri parse = Uri.parse(decode);
            if (parse == null) {
                return false;
            }
            return WebUtils.hostListWithKeyWord(decode, parse.getHost(), switchStringValue.split(";"));
        } catch (Exception unused) {
            return false;
        }
    }
}
