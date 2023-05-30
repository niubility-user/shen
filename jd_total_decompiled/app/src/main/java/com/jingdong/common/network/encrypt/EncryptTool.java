package com.jingdong.common.network.encrypt;

import android.text.TextUtils;
import com.jd.phc.e;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class EncryptTool {
    public static final String TAG = "EncryptTool";
    private static ConcurrentHashMap<String, String> encryptCache = new ConcurrentHashMap<>();

    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String encrypt(Map<String, String> map) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = true;
        String str = "";
        if (map != null) {
            String obj = map.toString();
            if (encryptCache.containsKey(obj)) {
                str = encryptCache.get(obj);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (OKLog.D) {
                    OKLog.d(TAG, "encrypt()\u6267\u884c\u52a0\u5bc6\uff0c\u662f\u5426\u547d\u4e2d\u7f13\u5b58 : " + z + " \uff0c\u8017\u65f6 : " + currentTimeMillis2);
                }
                return str;
            }
            try {
                str = e.d(JdSdk.getInstance().getApplicationContext(), "", true).b(map, e.b.MODIFIED_BASE64);
            } catch (Throwable unused) {
            }
            if (!TextUtils.isEmpty(str)) {
                encryptCache.put(obj, str);
            }
        }
        z = false;
        long currentTimeMillis22 = System.currentTimeMillis() - currentTimeMillis;
        if (OKLog.D) {
        }
        return str;
    }

    public static String encryptAndEncode(Map<String, String> map) {
        try {
            return URLEncoder.encode(encrypt(map), "UTF-8");
        } catch (Throwable unused) {
            return "";
        }
    }
}
