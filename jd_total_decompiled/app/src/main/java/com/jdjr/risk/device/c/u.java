package com.jdjr.risk.device.c;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes18.dex */
public class u {
    public static String a(Context context) {
        try {
            List<String> netAddressesForIPv4 = BaseInfo.getNetAddressesForIPv4();
            return (netAddressesForIPv4 == null || netAddressesForIPv4.size() <= 0) ? "" : netAddressesForIPv4.get(0);
        } catch (Exception unused) {
            return "";
        }
    }
}
