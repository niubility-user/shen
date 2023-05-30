package com.jingdong.common.cart;

import android.content.Context;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.oklog.OKLog;
import java.io.FileOutputStream;

/* loaded from: classes5.dex */
public class CartBaseTool {
    private static final String TAG = "CartBaseTool";

    public static String getAddressId() {
        try {
            return AddressUtil.getAddressGlobal() != null ? String.valueOf(AddressUtil.getAddressGlobal().getId()) : "";
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "getAddressId----->", e2);
                return "";
            }
            return "";
        }
    }

    public static boolean isSpecial(long j2, int i2) {
        return ((j2 >> i2) & 1) == 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0060, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0062, code lost:
        com.jingdong.sdk.oklog.OKLog.e(com.jingdong.common.cart.CartBaseTool.TAG, " readCartConfigInfo --->  : ", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0085, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L54;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0094 A[Catch: Exception -> 0x0090, TRY_LEAVE, TryCatch #0 {Exception -> 0x0090, blocks: (B:57:0x008c, B:61:0x0094), top: B:69:0x008c }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jd.framework.json.JDJSONObject readCartConfigInfo(android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.String r0 = " readCartConfigInfo --->  : "
            java.lang.String r1 = "CartBaseTool"
            boolean r2 = com.jingdong.sdk.oklog.OKLog.D
            if (r2 == 0) goto Lf
            java.lang.String r2 = "CartConfigState"
            java.lang.String r3 = " readCartConfigInfo"
            com.jingdong.sdk.oklog.OKLog.d(r2, r3)
        Lf:
            r2 = 0
            java.io.FileInputStream r7 = r7.openFileInput(r8)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6b
            if (r7 == 0) goto L57
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L54
            r8.<init>()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L54
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L89
        L1f:
            int r4 = r7.read(r3)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L89
            r5 = -1
            if (r4 == r5) goto L2b
            r5 = 0
            r8.write(r3, r5, r4)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L89
            goto L1f
        L2b:
            r8.close()     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L89
            r7.close()     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L89
            java.lang.String r3 = "UTF-8"
            java.lang.String r3 = r8.toString(r3)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L89
            com.jd.framework.json.JDJSONObject r2 = com.jd.framework.json.JDJSON.parseObject(r3)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L89
            r8.close()     // Catch: java.lang.Exception -> L44
            if (r7 == 0) goto L4c
            r7.close()     // Catch: java.lang.Exception -> L44
            goto L4c
        L44:
            r7 = move-exception
            boolean r8 = com.jingdong.sdk.oklog.OKLog.E
            if (r8 == 0) goto L4c
            com.jingdong.sdk.oklog.OKLog.e(r1, r0, r7)
        L4c:
            return r2
        L4d:
            r3 = move-exception
            goto L6e
        L4f:
            r8 = move-exception
            r6 = r2
            r2 = r8
            r8 = r6
            goto L8a
        L54:
            r3 = move-exception
            r8 = r2
            goto L6e
        L57:
            if (r7 == 0) goto L88
            r7.close()     // Catch: java.lang.Exception -> L5d
            goto L88
        L5d:
            r7 = move-exception
            boolean r8 = com.jingdong.sdk.oklog.OKLog.E
            if (r8 == 0) goto L88
        L62:
            com.jingdong.sdk.oklog.OKLog.e(r1, r0, r7)
            goto L88
        L66:
            r7 = move-exception
            r8 = r2
            r2 = r7
            r7 = r8
            goto L8a
        L6b:
            r3 = move-exception
            r7 = r2
            r8 = r7
        L6e:
            boolean r4 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L89
            if (r4 == 0) goto L75
            com.jingdong.sdk.oklog.OKLog.e(r1, r0, r3)     // Catch: java.lang.Throwable -> L89
        L75:
            if (r8 == 0) goto L7d
            r8.close()     // Catch: java.lang.Exception -> L7b
            goto L7d
        L7b:
            r7 = move-exception
            goto L83
        L7d:
            if (r7 == 0) goto L88
            r7.close()     // Catch: java.lang.Exception -> L7b
            goto L88
        L83:
            boolean r8 = com.jingdong.sdk.oklog.OKLog.E
            if (r8 == 0) goto L88
            goto L62
        L88:
            return r2
        L89:
            r2 = move-exception
        L8a:
            if (r8 == 0) goto L92
            r8.close()     // Catch: java.lang.Exception -> L90
            goto L92
        L90:
            r7 = move-exception
            goto L98
        L92:
            if (r7 == 0) goto L9f
            r7.close()     // Catch: java.lang.Exception -> L90
            goto L9f
        L98:
            boolean r8 = com.jingdong.sdk.oklog.OKLog.E
            if (r8 == 0) goto L9f
            com.jingdong.sdk.oklog.OKLog.e(r1, r0, r7)
        L9f:
            goto La1
        La0:
            throw r2
        La1:
            goto La0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.cart.CartBaseTool.readCartConfigInfo(android.content.Context, java.lang.String):com.jd.framework.json.JDJSONObject");
    }

    public static boolean saveCartConfigInfo(Context context, String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, " saveCartConfigInfo ---> json : " + str2);
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = context.openFileOutput(str, 0);
                fileOutputStream.write(str2.getBytes("UTF-8"));
                fileOutputStream.close();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, " saveCartConfigInfo --->  : ", e2);
                        }
                    }
                }
                return true;
            } catch (Throwable th) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                        if (OKLog.E) {
                            OKLog.e(TAG, " saveCartConfigInfo --->  : ", e3);
                        }
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            if (OKLog.E) {
                OKLog.e(TAG, " saveCartConfigInfo --->  : ", e4);
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e5) {
                    if (OKLog.E) {
                        OKLog.e(TAG, " saveCartConfigInfo --->  : ", e5);
                    }
                }
            }
            return false;
        }
    }

    public static int setSpecialBit(int i2, int i3) {
        if (i2 < 0 || i3 >= 32) {
            return -1;
        }
        return i2 | ((int) Math.pow(2.0d, i3));
    }
}
