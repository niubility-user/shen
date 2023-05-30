package com.jingdong.common.login;

import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes.dex */
public class LoginReportUtil {
    public static final String CH_ID = "2";
    private static final String EXP_ERRCODE = "709";
    private static final String TAG = "LoginReportUtil";
    public static final String TYPE_ID = "8";

    private static String formatMillis(long j2) {
        double d = j2;
        Double.isNaN(d);
        return String.format("%.6f", Double.valueOf(d / 1000.0d));
    }

    public static String getCurrentMicrosecond() {
        return formatMillis(System.currentTimeMillis());
    }

    private static HashMap getReportData(String str, long j2, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("typeId", "8");
        hashMap.put("chId", "2");
        hashMap.put("itype", "oneButtonLogin");
        hashMap.put("function", str);
        hashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(j2));
        hashMap.put("occurTime", getCurrentMicrosecond());
        hashMap.put("responseCode", str2);
        hashMap.put("localSdkVersion", UserUtil.getWJLoginHelper().getWJLoginSDKVersion());
        hashMap.put("thirdSdkVersion", "9.0.6.1");
        if (OKLog.D) {
            OKLog.d(TAG, "reportPhoneLogin data= " + hashMap);
        }
        return hashMap;
    }

    public static void reportCode3(String str, String str2, String str3) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("specialFlag", "1");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("errCode", EXP_ERRCODE);
            hashMap.put("function", str2);
            hashMap.put("httpResp", str);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            hashMap.put("postData", SafetyManager.getCookies());
            hashMap.put("errMsg", str3);
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportPhoneLogin(String str, long j2, String str2) {
        try {
            if (!PerformanceReporter.getIsNeedReport(JdSdk.getInstance().getApplicationContext(), "8", "2")) {
                if (OKLog.D) {
                    OKLog.d(TAG, "not need report");
                    return;
                }
                return;
            }
            PerformanceReporter.reportData(getReportData(str, j2, str2));
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }
}
