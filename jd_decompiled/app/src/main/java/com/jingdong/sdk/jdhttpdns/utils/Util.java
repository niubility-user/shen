package com.jingdong.sdk.jdhttpdns.utils;

import android.text.TextUtils;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes7.dex */
public class Util {
    public static boolean isExpire(IpModel ipModel) {
        if (ipModel == null) {
            DNSLog.d("ipModel is null, It isn't expire.");
            return false;
        }
        long distanceMins = TimeUtils.getDistanceMins(ipModel.getUpdateTime(), TimeUtils.getCurrentTime());
        if (distanceMins < 0 || TextUtils.isEmpty(ipModel.ttl) || ((float) distanceMins) <= Integer.parseInt(ipModel.ttl) * 0.75f) {
            return false;
        }
        DNSLog.d("cache is expire:" + ipModel.host + ":" + ipModel.master);
        return true;
    }

    public static boolean validIPAddress(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return InetAddressUtils.isIPv4Address(str) || InetAddressUtils.isIPv6Address(str);
    }
}
