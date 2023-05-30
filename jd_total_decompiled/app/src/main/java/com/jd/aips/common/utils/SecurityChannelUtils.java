package com.jd.aips.common.utils;

import android.content.Context;
import android.text.TextUtils;
import com.wangyin.platform.CryptoUtils;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class SecurityChannelUtils {
    public static byte[] decodeDataFromServer(Context context, String str) {
        try {
            byte[] decodeDataFromServer = CryptoUtils.newInstance(context).decodeDataFromServer(str);
            String str2 = new String(Arrays.copyOfRange(decodeDataFromServer, 0, 5));
            if (!TextUtils.isEmpty(str2) && str2.equals("00000")) {
                return Arrays.copyOfRange(decodeDataFromServer, 5, decodeDataFromServer.length);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static byte[] encodeDataToServer(Context context, String str) {
        try {
            byte[] encodeDataToServer = CryptoUtils.newInstance(context).encodeDataToServer(str, System.currentTimeMillis());
            String str2 = new String(Arrays.copyOfRange(encodeDataToServer, 0, 5));
            if (!TextUtils.isEmpty(str2) && str2.equals("00000")) {
                return Arrays.copyOfRange(encodeDataToServer, 5, encodeDataToServer.length);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static void startSecurityChannel(Context context) {
        CryptoUtils.newInstance(context).startAutoHandshake();
    }
}
