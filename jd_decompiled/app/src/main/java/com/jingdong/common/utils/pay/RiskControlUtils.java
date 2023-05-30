package com.jingdong.common.utils.pay;

import android.app.Activity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;

/* loaded from: classes6.dex */
public class RiskControlUtils {
    private static final String TAG = "RiskControlUtils";
    private static boolean sIsAcceptPrivacy;

    public static String getLocalIPAddress() {
        try {
            List<String> netAddressesForIPv4 = BaseInfo.getNetAddressesForIPv4();
            return (netAddressesForIPv4 == null || netAddressesForIPv4.isEmpty()) ? "" : netAddressesForIPv4.get(netAddressesForIPv4.size() - 1);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getLocalMacAddress() {
        if (isAcceptPrivacy()) {
            try {
                String wifiMacAddress = BaseInfo.getWifiMacAddress();
                if (wifiMacAddress != null) {
                    wifiMacAddress = wifiMacAddress.replace(":", "-");
                }
                return wifiMacAddress;
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    public static boolean isAcceptPrivacy() {
        Activity activity = (Activity) BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (activity == null) {
            return false;
        }
        if (!sIsAcceptPrivacy) {
            sIsAcceptPrivacy = activity.getSharedPreferences("privacy", 0).getBoolean("privacy_has_show", false);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "user privacy result : " + sIsAcceptPrivacy);
        }
        return sIsAcceptPrivacy;
    }
}
