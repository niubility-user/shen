package com.laser.utils.app;

import android.content.Context;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.laser.utils.common.LogUtil;

/* loaded from: classes13.dex */
public class PhoneUtil {
    private PhoneUtil() {
        throw new Error("Do not need instantiate!");
    }

    public static String getBasePhoneVersion() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
            LogUtil.d("basePhoneVersion: " + invoke.toString());
            return invoke.toString();
        } catch (Exception e2) {
            LogUtil.e("\u83b7\u53d6\u57fa\u5e26\u7248\u672c\u5931\u8d25\uff1a" + e2.getMessage());
            return "fail";
        }
    }

    public static String getMobileBrand(Context context) {
        try {
            return BaseInfo.getDeviceBrand();
        } catch (Exception unused) {
            return "\u672a\u77e5";
        }
    }

    public static String getMobileModel() {
        try {
            return BaseInfo.getDeviceModel();
        } catch (Exception unused) {
            return "\u672a\u77e5";
        }
    }
}
