package com.jingdong.common.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import jpbury.t;

/* loaded from: classes.dex */
public class SwitchQueryFetcherUtil {
    private static final String EXP_ERR_CODE = "965";
    private static final String SWITCH_QUERY_EVENT_ID = "switchquery_req_stat";
    public static final String SWITCH_QUERY_FAILED = "2";
    public static final String SWITCH_QUERY_HTTP_ERROR = "http_error";
    public static final String SWITCH_QUERY_HTTP_RESTRICT = "http_restrict_req_fail";
    public static final String SWITCH_QUERY_JSON_DIFF = "diff_invalid";
    public static final String SWITCH_QUERY_JSON_INVALID = "http_json_invalid";
    public static final String SWITCH_QUERY_JSON_PARSE = "parse_invalid";
    public static final String SWITCH_QUERY_SUCCESS = "1";
    private static final String TAG = "SwitchQueryFetcherUtil";

    static void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (Exception unused) {
        }
    }

    public static boolean leftLargerOrEqual(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        for (int i2 = 0; i2 < min; i2++) {
            try {
                int parseInt = Integer.parseInt(split[i2]);
                int parseInt2 = Integer.parseInt(split2[i2]);
                if (parseInt != parseInt2) {
                    return parseInt > parseInt2;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return split.length >= split2.length;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0060 A[Catch: Exception -> 0x0069, TRY_LEAVE, TryCatch #5 {Exception -> 0x0069, blocks: (B:33:0x0059, B:36:0x0060), top: B:48:0x0059 }] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jd.framework.json.JDJSONObject readGZipFile(java.lang.String r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L36
            java.lang.String r4 = "UTF-8"
            java.nio.charset.Charset r4 = java.nio.charset.Charset.forName(r4)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L36
            r3.<init>(r6, r4)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L36
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2f
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2f
        L20:
            java.lang.String r5 = r4.readLine()     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L6e
            if (r5 == 0) goto L4d
            r0.append(r5)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L6e
            goto L20
        L2a:
            r5 = move-exception
            goto L4a
        L2c:
            r0 = move-exception
            r4 = r1
            goto L6f
        L2f:
            r5 = move-exception
            r4 = r1
            goto L4a
        L32:
            r0 = move-exception
            r3 = r1
            r4 = r3
            goto L6f
        L36:
            r5 = move-exception
            r3 = r1
            goto L49
        L39:
            r0 = move-exception
            r3 = r1
            goto L43
        L3c:
            r5 = move-exception
            r6 = r1
            r3 = r6
            goto L49
        L40:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L43:
            r4 = r3
            goto L70
        L45:
            r5 = move-exception
            r6 = r1
            r2 = r6
            r3 = r2
        L49:
            r4 = r3
        L4a:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6e
        L4d:
            close(r6)
            close(r2)
            close(r3)
            close(r4)
            int r6 = r0.length()     // Catch: java.lang.Exception -> L69
            if (r6 != 0) goto L60
            return r1
        L60:
            java.lang.String r6 = r0.toString()     // Catch: java.lang.Exception -> L69
            com.jd.framework.json.JDJSONObject r1 = com.jd.framework.json.JDJSON.parseObject(r6)     // Catch: java.lang.Exception -> L69
            goto L6d
        L69:
            r6 = move-exception
            r6.printStackTrace()
        L6d:
            return r1
        L6e:
            r0 = move-exception
        L6f:
            r1 = r6
        L70:
            close(r1)
            close(r2)
            close(r3)
            close(r4)
            goto L7e
        L7d:
            throw r0
        L7e:
            goto L7d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.SwitchQueryFetcherUtil.readGZipFile(java.lang.String):com.jd.framework.json.JDJSONObject");
    }

    public static void reportSwitchQueryHttpExp(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", EXP_ERR_CODE);
            hashMap.put("function", str);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            hashMap.put("errMsg", str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            hashMap.put(t.f20145j, str3);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportSwitchQueryKeyExp(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", EXP_ERR_CODE);
            hashMap.put("function", str);
            hashMap.put("httpResp", str2);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void sendExpMta(Context context, String str, String str2, String str3, String str4) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            jDJSONObject.put("suc", (Object) str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            jDJSONObject.put("dv", (Object) str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            jDJSONObject.put("sc", (Object) str3);
            if (TextUtils.isEmpty(str4)) {
                str4 = "";
            }
            jDJSONObject.put("sc1", (Object) str4);
            JDMtaUtils.sendExposureDataWithExt(context, SWITCH_QUERY_EVENT_ID, "", "", "", "", jDJSONObject.toJSONString(), null);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    static void close(Reader reader) {
        try {
            reader.close();
        } catch (Exception unused) {
        }
    }
}
