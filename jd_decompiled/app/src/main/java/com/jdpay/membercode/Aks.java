package com.jdpay.membercode;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jdpay.util.JPMCMonitor;
import com.wangyin.platform.CryptoUtils;
import java.nio.charset.Charset;

/* loaded from: classes18.dex */
public class Aks {
    public static final int ALGORITHM_INTERNATION = 1;
    public static final int ALGORITHM_SM = 0;
    public static final String SUCCESS_CODE = "00000";
    public static volatile int algorithm;
    private static CryptoUtils crypto;

    public static String decode(@NonNull String str) {
        return getResult(algorithm == 1 ? crypto.decodeDataFromServer(str) : crypto.decodeDataFromServer_gm(str));
    }

    public static String encode(@NonNull String str) {
        return getResult(algorithm == 1 ? crypto.encodeDataToServer(str, System.currentTimeMillis()) : crypto.encodeDataToServer_gm(str, System.currentTimeMillis()));
    }

    public static String getResult(@NonNull byte[] bArr) {
        String str = new String(bArr, Charset.forName("UTF-8"));
        if (str.startsWith("00000")) {
            return str.substring(5);
        }
        JPMCMonitor.e("MC Aks decode failed:" + str);
        return null;
    }

    public static void initialize(@NonNull Context context) {
        crypto = CryptoUtils.newInstance(context.getApplicationContext());
        if (algorithm == 1) {
            crypto.startAutoHandshake();
        } else {
            crypto.startAutoHandshake_gm();
        }
    }
}
