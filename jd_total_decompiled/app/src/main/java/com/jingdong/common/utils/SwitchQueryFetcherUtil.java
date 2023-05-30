package com.jingdong.common.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
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
    */
    public static JDJSONObject readGZipFile(String str) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        GZIPInputStream gZIPInputStream;
        InputStreamReader inputStreamReader2;
        ?? r4;
        StringBuilder sb = new StringBuilder();
        GZIPInputStream gZIPInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                gZIPInputStream = new GZIPInputStream(fileInputStream);
            } catch (Exception e2) {
                e = e2;
                gZIPInputStream = null;
                inputStreamReader = null;
            } catch (Throwable th) {
                th = th;
                inputStreamReader = null;
                inputStreamReader2 = inputStreamReader;
                close(gZIPInputStream2);
                close(fileInputStream);
                close(inputStreamReader);
                close(inputStreamReader2);
                throw th;
            }
            try {
                inputStreamReader = new InputStreamReader(gZIPInputStream, Charset.forName("UTF-8"));
            } catch (Exception e3) {
                e = e3;
                inputStreamReader = null;
                r4 = inputStreamReader;
                e.printStackTrace();
                close(gZIPInputStream);
                close(fileInputStream);
                close(inputStreamReader);
                close((Reader) r4);
                if (sb.length() == 0) {
                }
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader = null;
                r4 = 0;
            }
            try {
                r4 = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        try {
                            String readLine = r4.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        } catch (Throwable th3) {
                            th = th3;
                            gZIPInputStream2 = gZIPInputStream;
                            inputStreamReader2 = r4;
                            close(gZIPInputStream2);
                            close(fileInputStream);
                            close(inputStreamReader);
                            close(inputStreamReader2);
                            throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                        close(gZIPInputStream);
                        close(fileInputStream);
                        close(inputStreamReader);
                        close((Reader) r4);
                        if (sb.length() == 0) {
                        }
                    }
                }
            } catch (Exception e5) {
                e = e5;
                r4 = 0;
            } catch (Throwable th4) {
                th = th4;
                r4 = 0;
                gZIPInputStream2 = gZIPInputStream;
                inputStreamReader2 = r4;
                close(gZIPInputStream2);
                close(fileInputStream);
                close(inputStreamReader);
                close(inputStreamReader2);
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            gZIPInputStream = null;
            fileInputStream = null;
            inputStreamReader = null;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            inputStreamReader = null;
        }
        close(gZIPInputStream);
        close(fileInputStream);
        close(inputStreamReader);
        close((Reader) r4);
        try {
            if (sb.length() == 0) {
                return null;
            }
            return JDJSON.parseObject(sb.toString());
        } catch (Exception e7) {
            e7.printStackTrace();
            return null;
        }
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
