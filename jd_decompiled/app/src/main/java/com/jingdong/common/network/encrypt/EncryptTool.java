package com.jingdong.common.network.encrypt;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String encrypt(java.util.Map<java.lang.String, java.lang.String> r7) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1
            java.lang.String r3 = ""
            r4 = 0
            if (r7 == 0) goto L40
            java.lang.String r5 = r7.toString()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r6 = com.jingdong.common.network.encrypt.EncryptTool.encryptCache
            boolean r6 = r6.containsKey(r5)
            if (r6 == 0) goto L20
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r7 = com.jingdong.common.network.encrypt.EncryptTool.encryptCache
            java.lang.Object r7 = r7.get(r5)
            r3 = r7
            java.lang.String r3 = (java.lang.String) r3
            goto L41
        L20:
            com.jingdong.jdsdk.JdSdk r6 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L34
            android.content.Context r6 = r6.getApplicationContext()     // Catch: java.lang.Throwable -> L34
            com.jd.phc.e r2 = com.jd.phc.e.d(r6, r3, r2)     // Catch: java.lang.Throwable -> L34
            com.jd.phc.e$b r6 = com.jd.phc.e.b.MODIFIED_BASE64     // Catch: java.lang.Throwable -> L34
            java.lang.String r7 = r2.b(r7, r6)     // Catch: java.lang.Throwable -> L34
            r3 = r7
            goto L35
        L34:
        L35:
            boolean r7 = android.text.TextUtils.isEmpty(r3)
            if (r7 != 0) goto L40
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r7 = com.jingdong.common.network.encrypt.EncryptTool.encryptCache
            r7.put(r5, r3)
        L40:
            r2 = 0
        L41:
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r0
            boolean r7 = com.jingdong.sdk.oklog.OKLog.D
            if (r7 == 0) goto L68
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "encrypt()\u6267\u884c\u52a0\u5bc6\uff0c\u662f\u5426\u547d\u4e2d\u7f13\u5b58 : "
            r7.append(r0)
            r7.append(r2)
            java.lang.String r0 = " \uff0c\u8017\u65f6 : "
            r7.append(r0)
            r7.append(r4)
            java.lang.String r7 = r7.toString()
            java.lang.String r0 = "EncryptTool"
            com.jingdong.sdk.oklog.OKLog.d(r0, r7)
        L68:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.network.encrypt.EncryptTool.encrypt(java.util.Map):java.lang.String");
    }

    public static String encryptAndEncode(Map<String, String> map) {
        try {
            return URLEncoder.encode(encrypt(map), "UTF-8");
        } catch (Throwable unused) {
            return "";
        }
    }
}
