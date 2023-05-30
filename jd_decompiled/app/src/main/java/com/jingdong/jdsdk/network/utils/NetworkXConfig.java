package com.jingdong.jdsdk.network.utils;

import android.text.TextUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;

/* loaded from: classes14.dex */
public class NetworkXConfig {
    public static boolean isHttpsSwitch() {
        try {
            if (SwitchQueryFetcher.getFetcher().getFetchStatus() == -1) {
                return false;
            }
            return TextUtils.equals(SwitchQueryFetcher.getSwitchStringValue(RuntimeConfigHelper.HTTPS_SWITCH, "1"), "1");
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isXTime() {
        try {
            return SwitchQueryFetcher.isXTime();
        } catch (Throwable unused) {
            return false;
        }
    }
}
