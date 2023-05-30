package com.jingdong.jdsdk.network.toolbox;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.imagepipeline.backends.okhttp3.ImgNetStatisticTool;
import com.google.common.net.HttpHeaders;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.JDNetworkResponse;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.toolbox.JDNetworkStatisticTool;
import com.jd.mobile.image.exception.JDImageNetworkException;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.WebDebug;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.jdexreport.JDExReportInterface;
import com.jingdong.jdexreport.broadcast.UserChangedListener;
import com.jingdong.jdexreport.einterface.InitCommonInfo;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.depend.DependUtil;
import com.jingdong.jdsdk.login.LoginUserHelper;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.jingdong.jdsdk.network.utils.StatSharePreferenceUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.utils.d;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.jdhttpdns.config.Constant;
import com.jingdong.sdk.jdhttpdns.utils.StatisticTool;
import com.jingdong.sdk.oklog.OKLog;
import com.novoda.imageloader.core.bitmap.BitmapUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import jpbury.t;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ExceptionReporter extends JDNetworkConstant {
    public static final String GATEWAY_DECRYPT_ERROR = "819";
    public static final long MAX_HOST_KEET_TIME = 60000;
    public static final String MIAOSHA_REMIND = "913";
    public static final String NORMAL_REMIND = "914";
    public static final String PHC_ERROR_CODE = "915";
    private static final String TAG = "ExceptionReporter";
    public static final String TLS_HANDSHAKE_DATA = "817";
    private static ExtraComponentInitializer extraComponentInitializer;
    private static InitCommonInfo initCommonInfo;
    private HttpSetting mHttpSetting;
    private static ExecutorService sExecutorService = Executors.newFixedThreadPool(2);
    private static HashMap<String, HostEntity> hostHashMap = new HashMap<>();
    private static ExecutorService tlsReportService = Executors.newFixedThreadPool(1);
    private static ConcurrentHashMap<String, LimitEntity> limits = new ConcurrentHashMap<>();

    /* loaded from: classes.dex */
    public interface ExtraComponentInitializer {
        InitCommonInfo init();
    }

    /* loaded from: classes14.dex */
    public static class HostEntity {
        String ip;
        long time;

        HostEntity() {
        }
    }

    /* loaded from: classes.dex */
    public static class LimitEntity {
        public long initTime;
        public int reportedNum;

        LimitEntity() {
        }
    }

    public ExceptionReporter() {
    }

    public static void acceptProtocol(boolean z) {
    }

    private static boolean checkCodeCanReport(Context context, HashMap<String, String> hashMap) {
        if (getLimitCnt(context) <= 0) {
            return false;
        }
        if (hashMap.containsKey("errCode")) {
            String str = hashMap.get("errCode");
            if (limits.containsKey(str)) {
                LimitEntity limitEntity = limits.get(str);
                if (getCurrentSeconds() - limitEntity.initTime >= getLimitInt(context)) {
                    limitEntity.initTime = getCurrentSeconds();
                    return true;
                } else if (limitEntity.reportedNum < getLimitCnt(context)) {
                    limitEntity.reportedNum++;
                    return true;
                } else {
                    if (OKLog.D) {
                        OKLog.d(TAG, String.format("errCode:%s report %d items,forbid report", str, Integer.valueOf(limitEntity.reportedNum)));
                    }
                    return false;
                }
            }
            LimitEntity limitEntity2 = new LimitEntity();
            limitEntity2.initTime = getCurrentSeconds();
            limitEntity2.reportedNum = 1;
            limits.put(str, limitEntity2);
            return true;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "not have errCode,forbid report");
        }
        return false;
    }

    public static String formatMillis(long j2) {
        double d = j2;
        Double.isNaN(d);
        return String.format("%.6f", Double.valueOf(d / 1000.0d));
    }

    public static String getCurrentMicrosecond() {
        return formatMillis(System.currentTimeMillis());
    }

    private static long getCurrentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getIpByHost(final String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return (String) sExecutorService.submit(new Callable<String>() { // from class: com.jingdong.jdsdk.network.toolbox.ExceptionReporter.4
                @Override // java.util.concurrent.Callable
                public String call() throws Exception {
                    try {
                        HostEntity hostEntity = (HostEntity) ExceptionReporter.hostHashMap.get(str);
                        if (hostEntity != null && System.currentTimeMillis() - hostEntity.time < 60000) {
                            return hostEntity.ip;
                        }
                        List asList = Arrays.asList(InetAddress.getAllByName(str));
                        if (asList == null || asList.size() <= 0) {
                            return "";
                        }
                        if (hostEntity == null) {
                            hostEntity = new HostEntity();
                            ExceptionReporter.hostHashMap.put(str, hostEntity);
                        }
                        hostEntity.ip = new JSONArray((Collection) asList).toString();
                        hostEntity.time = System.currentTimeMillis();
                        return hostEntity.ip;
                    } catch (Throwable unused) {
                        return "";
                    }
                }
            }).get(1000L, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            OKLog.e(TAG, th);
            return "";
        }
    }

    public static long getLimitCnt(Context context) {
        return JDExReportInterface.getLimitCnt(context);
    }

    public static long getLimitInt(Context context) {
        return JDExReportInterface.getLimitInt(context);
    }

    public static String getStackStringFromException(Throwable th) {
        PrintWriter printWriter;
        StringWriter stringWriter;
        if (th != null) {
            StringWriter stringWriter2 = null;
            try {
                stringWriter = new StringWriter();
                try {
                    printWriter = new PrintWriter((Writer) stringWriter, true);
                } catch (Exception unused) {
                    printWriter = null;
                } catch (Throwable th2) {
                    th = th2;
                    printWriter = null;
                }
            } catch (Exception unused2) {
                printWriter = null;
            } catch (Throwable th3) {
                th = th3;
                printWriter = null;
            }
            try {
                th.printStackTrace(printWriter);
                String stringBuffer = stringWriter.getBuffer().toString();
                try {
                    stringWriter.close();
                } catch (Exception unused3) {
                }
                try {
                    printWriter.close();
                } catch (Exception unused4) {
                }
                return stringBuffer;
            } catch (Exception unused5) {
                stringWriter2 = stringWriter;
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Exception unused6) {
                    }
                }
                if (printWriter != null) {
                    try {
                        printWriter.close();
                        return "null message";
                    } catch (Exception unused7) {
                        return "null message";
                    }
                }
                return "null message";
            } catch (Throwable th4) {
                th = th4;
                stringWriter2 = stringWriter;
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Exception unused8) {
                    }
                }
                if (printWriter != null) {
                    try {
                        printWriter.close();
                    } catch (Exception unused9) {
                    }
                }
                throw th;
            }
        }
        return "null message";
    }

    public static synchronized InitCommonInfo getinitCommonInfo() {
        InitCommonInfo initCommonInfo2;
        ExtraComponentInitializer extraComponentInitializer2;
        synchronized (ExceptionReporter.class) {
            if (initCommonInfo == null && (extraComponentInitializer2 = extraComponentInitializer) != null) {
                initCommonInfo = extraComponentInitializer2.init();
            }
            initCommonInfo2 = initCommonInfo;
        }
        return initCommonInfo2;
    }

    public static void init(Context context, ExtraComponentInitializer extraComponentInitializer2) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.EXCEPTION_REPORTER, true)) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            extraComponentInitializer = extraComponentInitializer2;
            OKLog.d(TAG, "ExcepitonReporter init");
            JDExReportInterface.init(context, getinitCommonInfo());
            JDExReportInterface.setUseChangedListener(new UserChangedListener() { // from class: com.jingdong.jdsdk.network.toolbox.ExceptionReporter.3
                @Override // com.jingdong.jdexreport.broadcast.UserChangedListener
                public String onUserChanged() {
                    return LoginUserHelper.getInstance().getLoginUser().getLoginUserName();
                }
            });
        }
    }

    public static void reportApplicationInstallEvent(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportApplicationInstallEvent: " + str);
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "");
            hashMap.put("url", str);
            hashMap.put("errCode", JDNetworkConstant.INSTALL_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("errMsg", str2);
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportApplicationUpgradeEvent(String str, String str2, String str3, String str4, String str5) {
        reportApplicationUpgradeEvent(str, str2, str3, str4, str5, "", "");
    }

    public static final void reportBitmapException(final String str, final JDFailReason jDFailReason, final String str2, final int i2) {
        sExecutorService.execute(new Runnable() { // from class: com.jingdong.jdsdk.network.toolbox.ExceptionReporter.2
            /* JADX WARN: Removed duplicated region for block: B:195:0x01b8 A[Catch: all -> 0x021b, TryCatch #1 {all -> 0x021b, blocks: (B:147:0x002e, B:148:0x0058, B:149:0x006a, B:151:0x006e, B:154:0x0076, B:157:0x0086, B:159:0x00b3, B:197:0x01eb, B:160:0x00d2, B:161:0x00f1, B:164:0x0107, B:166:0x010d, B:169:0x0117, B:171:0x011f, B:193:0x01aa, B:195:0x01b8, B:196:0x01d2, B:172:0x012c, B:174:0x0139, B:176:0x0143, B:178:0x014d, B:179:0x0156, B:182:0x0160, B:184:0x016a, B:185:0x0176, B:187:0x017e, B:189:0x0188, B:190:0x0195, B:192:0x019f, B:198:0x0203, B:199:0x0206), top: B:209:0x002e }] */
            /* JADX WARN: Removed duplicated region for block: B:196:0x01d2 A[Catch: all -> 0x021b, TryCatch #1 {all -> 0x021b, blocks: (B:147:0x002e, B:148:0x0058, B:149:0x006a, B:151:0x006e, B:154:0x0076, B:157:0x0086, B:159:0x00b3, B:197:0x01eb, B:160:0x00d2, B:161:0x00f1, B:164:0x0107, B:166:0x010d, B:169:0x0117, B:171:0x011f, B:193:0x01aa, B:195:0x01b8, B:196:0x01d2, B:172:0x012c, B:174:0x0139, B:176:0x0143, B:178:0x014d, B:179:0x0156, B:182:0x0160, B:184:0x016a, B:185:0x0176, B:187:0x017e, B:189:0x0188, B:190:0x0195, B:192:0x019f, B:198:0x0203, B:199:0x0206), top: B:209:0x002e }] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                String str3;
                String str4;
                if (OKLog.D) {
                    OKLog.d(ExceptionReporter.TAG, "reportBitmapException\uff1a" + str + ",failReason:" + jDFailReason);
                }
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("function", "picRes");
                    hashMap.put("url", str);
                    hashMap.put("postData", str2);
                    hashMap.put("httpResp", String.valueOf(i2));
                    hashMap.put("verifyCode", "");
                    try {
                        hashMap.put("reserved1", ExceptionReporter.getIpByHost(new URL(str).getHost()));
                    } catch (Exception unused) {
                    }
                    JDFailReason jDFailReason2 = jDFailReason;
                    if (jDFailReason2 != null) {
                        Throwable cause = jDFailReason2.getCause();
                        if (cause != null) {
                            if (cause instanceof JDImageNetworkException) {
                                hashMap.put("function", "imageDnsPicRes");
                                hashMap.put("reserved1", ((JDImageNetworkException) cause).dnsIp);
                                hashMap.put("errCode", "808");
                                hashMap.put(PerformanceManager.ERR_TYPE, "1");
                                str3 = cause.getCause().getClass().getName();
                                if (DependUtil.getInstance().getDepend().isUseOkhttp()) {
                                    str4 = "OkHttp-" + d.b(jDFailReason.getCause().getCause());
                                } else {
                                    str4 = "HttpUrlConnection-" + d.b(jDFailReason.getCause().getCause());
                                }
                            } else {
                                String localizedMessage = cause.getLocalizedMessage();
                                String name = cause.getClass().getName();
                                if (!name.equalsIgnoreCase("java.net.MalformedURLException") && (TextUtils.isEmpty(localizedMessage) || !localizedMessage.contains("Unsupported uri scheme"))) {
                                    if (name.contains(".net")) {
                                        hashMap.put("function", "netError");
                                        hashMap.put("errCode", "808");
                                        hashMap.put(PerformanceManager.ERR_TYPE, "1");
                                    } else {
                                        hashMap.put("function", "analysisError");
                                        if (!TextUtils.isEmpty(str) && (str.endsWith(".dpg") || str.endsWith(".dpg.webp"))) {
                                            hashMap.put("errCode", "911");
                                            hashMap.put(PerformanceManager.ERR_TYPE, "1");
                                        } else if (!TextUtils.isEmpty(str) && str.endsWith(".avif")) {
                                            hashMap.put("errCode", BitmapUtil.BITMAP_AVIF_ERRCODE);
                                            hashMap.put(PerformanceManager.ERR_TYPE, "1");
                                            hashMap.put("function", "avifAnalysisError");
                                        } else if (!TextUtils.isEmpty(str) && str.endsWith(".gif.webp")) {
                                            hashMap.put("errCode", BitmapUtil.BITMAP_AVIF_ERRCODE);
                                            hashMap.put(PerformanceManager.ERR_TYPE, "1");
                                            hashMap.put("function", "webpAnalysisError");
                                        } else {
                                            hashMap.put("errCode", "910");
                                            hashMap.put(PerformanceManager.ERR_TYPE, "2");
                                        }
                                    }
                                    str3 = name;
                                    if (!DependUtil.getInstance().getDepend().isUseOkhttp()) {
                                        str4 = "OkHttp-" + d.b(jDFailReason.getCause());
                                    } else {
                                        str4 = "HttpUrlConnection-" + d.b(jDFailReason.getCause());
                                    }
                                }
                                hashMap.put("errCode", "909");
                                hashMap.put(PerformanceManager.ERR_TYPE, "2");
                                str3 = "\u670d\u52a1\u7aef\u4e0b\u53d1\u7684url\u4e0d\u5408\u6cd5";
                                if (!DependUtil.getInstance().getDepend().isUseOkhttp()) {
                                }
                            }
                            hashMap.put("errMsg", "" + str3);
                            hashMap.put(t.f20145j, str4);
                        } else {
                            hashMap.put(t.f20145j, "");
                        }
                    }
                    hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
                    ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
                } catch (Throwable th) {
                    if (OKLog.E) {
                        OKLog.e(ExceptionReporter.TAG, th);
                    }
                }
            }
        });
    }

    public static void reportCDNDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4, String str5, String str6) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("success", str2);
            jSONObject.put(VerifyTracker.KEY_TIMES, i2);
            jSONObject.put("useDefault", z);
            jSONObject.put("hostname", str3);
            jSONObject.put(HttpHeaders.ReferrerPolicyValues.ORIGIN, str4);
            HashMap hashMap = new HashMap();
            hashMap.put("url", str);
            hashMap.put("errCode", "828");
            hashMap.put(t.f20145j, jSONObject.toString());
            hashMap.put("reserved1", str5);
            hashMap.put("reserved2", str6);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static final void reportDeviceUUIDParamMta() {
        try {
            String oaid = BaseInfo.getOAID();
            String str = BaseInfo.getOAIDStatus() ? "sup" : "unsup";
            if (SharedPreferencesUtil.getBoolean("base-info-oaid-reported-stat", false)) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", BitmapUtil.DPG_REPORT_ERRCODE);
            hashMap.put("function", "oaid-stat");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            hashMap.put("reserved1", oaid);
            hashMap.put("reserved2", str);
            hashMap.put("reserved3", BaseInfo.getDeviceManufacture());
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
            SharedPreferencesUtil.putBoolean("base-info-oaid-reported-stat", true);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static final void reportDpgPicMta(String str) {
    }

    public static final void reportDuplicatePicException(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportDuplicatePicException:" + str);
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "duplicatePic");
            hashMap.put("url", str);
            hashMap.put("postData", "");
            hashMap.put("httpResp", "0");
            hashMap.put("errCode", "801");
            hashMap.put("verifyCode", "");
            hashMap.put(t.f20145j, "");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "1");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportExceptionToBugly(Throwable th) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportException: ");
        }
        if (th != null) {
            JdCrashReport.postCaughtException(th);
        }
    }

    public static void reportFlowData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("specialFlag", "1");
            hashMap.put("httpResp", "0");
            hashMap.put("errCode", JDNetworkConstant.TRAFFIC_FLOW_ERRCODE);
            hashMap.put("errMsg", str);
            hashMap.put("verifyCode", "");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportFrescoClearTextRequest(String str) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "816");
            hashMap.put("url", str);
            hashMap.put("function", IStatInfoConfig.KEY_CLEARTEXT);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportGateWayDecryptError(String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", GATEWAY_DECRYPT_ERROR);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("function", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put("url", str2);
            }
            hashMap.put("occurTime", getCurrentMicrosecond());
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportGetQueryParameterException(Uri uri, String str) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "960");
            hashMap.put("function", "OpenappGetQueryParamsError");
            hashMap.put("errMsg", str);
            hashMap.put("url", uri.toString());
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void reportHttp2PingTimeoutException(String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("host", str);
            hashMap.put("protocol", str2);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("errCode", JDNetworkConstant.HTTP2_PING_TIMEOUT_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "1");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void reportHttpException(String str, HttpSetting httpSetting, HttpError httpError, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportHttpException:id\uff1a" + httpSetting.getId() + ",errorCode: " + str2 + ",exception:" + httpError.getException() + ",JsonCode:" + httpError.getJsonCode());
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", httpSetting.getMtaFunctionId());
            if (TextUtils.isEmpty(str)) {
                str = httpSetting.getUrl() == null ? "" : httpSetting.getUrl();
            }
            hashMap.put("url", str);
            hashMap.put("postData", httpSetting.getParamCacheKey());
            hashMap.put("httpResp", String.valueOf(httpError.getResponseCode()));
            hashMap.put("errCode", str2);
            hashMap.put("verifyCode", "" + httpError.getValidDataErrorCode());
            if (httpError != null) {
                if (httpError.getHttpResponse() != null) {
                    HttpResponse httpResponse = httpError.getHttpResponse();
                    if (!TextUtils.isEmpty(httpResponse.getString())) {
                        hashMap.put(t.f20145j, httpResponse.getString());
                    }
                    hashMap.put("errMsg", String.valueOf(httpError.getJsonCode()));
                } else if (httpError.getException() != null) {
                    hashMap.put("errMsg", httpError.getException().getCause() != null ? httpError.getException().getCause().getClass().getName() : "");
                }
                if (httpError.getException() != null && httpError.getException().getCause() != null && !TextUtils.isEmpty(httpError.getException().getCause().getMessage())) {
                    hashMap.put(t.f20145j, httpError.getException().getCause().getMessage());
                }
                if (httpError.getException() instanceof JDError) {
                    JDError jDError = (JDError) httpError.getException();
                    JDNetworkResponse jDNetworkResponse = jDError.networkResponse;
                    if (jDNetworkResponse != null && !TextUtils.isEmpty(jDNetworkResponse.responseStr)) {
                        hashMap.put(t.f20145j, jDError.networkResponse.responseStr);
                    }
                    hashMap.put("specialFlag", jDError.isDowngradeError() ? "0" : "1");
                }
            }
            hashMap.put("reserved2", d.b(httpError.getException()));
            hashMap.put("reserved3", String.valueOf(httpSetting.getId()));
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "1");
            try {
                hashMap.put("reserved1", getIpByHost(new URL(str).getHost()));
            } catch (Exception unused) {
            }
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportHttpsErrorToServer(String str, HttpSetting httpSetting, Throwable th) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportHttpException:id\uff1a" + httpSetting.getId() + DYConstants.DY_REGEX_COMMA + httpSetting.getFunctionId() + DYConstants.DY_REGEX_COMMA + th.toString());
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", httpSetting.getFunctionId());
            if (TextUtils.isEmpty(str)) {
                str = httpSetting.getUrl();
            }
            String str2 = "";
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            hashMap.put("url", str);
            hashMap.put("postData", httpSetting.getJsonParamsString());
            hashMap.put(t.f20145j, d.b(th));
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("errCode", JDNetworkConstant.HTTPS_REQUEST_EXP_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "1");
            if (th != null && th.getCause() != null) {
                str2 = th.getCause().getClass().getName();
            }
            hashMap.put("errMsg", str2);
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void reportJDGuardExceptionData(String str, String str2, String str3, String str4) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "826");
            hashMap.put("errMsg", str);
            hashMap.put("httpResp", str4);
            hashMap.put("function", str2);
            hashMap.put("reserved1", str3);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportKeyShareException(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "962");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("function", str);
            hashMap.put(t.f20145j, str2);
            hashMap.put("errMsg", str3);
            hashMap.put("httpResp", str4);
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportLibraryException(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "923");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("function", str);
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
        OKLog.d("reportLibraryException", str);
    }

    public static final void reportLive(String str, String str2, String str3) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", str);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("errMsg", str2);
            hashMap.put("occurTime", getCurrentMicrosecond());
            if (!TextUtils.isEmpty(str3)) {
                hashMap.put("specialFlag", str3);
            }
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportMiaoshaRemindErr(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("specialFlag", "1");
            hashMap.put("httpResp", "0");
            hashMap.put("errCode", MIAOSHA_REMIND);
            hashMap.put("errMsg", str);
            hashMap.put("verifyCode", "");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportNetworkStatisticData() {
        String str;
        String str2;
        String str3;
        SharedPreferences sharedPreferences = StatSharePreferenceUtil.getSharedPreferences();
        int i2 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_TOTAL_REQUEST_COUNT, 0);
        int i3 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_SUCCEED_REQUEST_COUNT, 0);
        int i4 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_DOMAIN2IP_DOWNGRADE_REQUEST_COUNT, 0);
        int i5 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_FINAL_DOWNGRADE_REQUEST_COUNT, 0);
        int i6 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_DOWNGRADE2BUILDIN_REQUEST_COUNT, 0);
        int i7 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_DOWNGRADE2HTTPDNS_REQUEST_COUNT, 0);
        int i8 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_DOWNGRADE2HTTPDNS_BACKUP_REQUEST_COUNT, 0);
        int i9 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_TOTAL_HTTPS_REQUEST_COUNT, 0);
        int i10 = sharedPreferences.getInt(Constant.KEY_TOTAL_HTTPDNS_REQUEST_COUNT, 0);
        int i11 = sharedPreferences.getInt(Constant.KEY_SUCCEED_HTTPDNS_REQUEST_COUNT, 0);
        int i12 = sharedPreferences.getInt(Constant.KEY_SUCCEED_HTTPDNS_DOMAIN_REQUEST_COUNT, 0);
        int i13 = sharedPreferences.getInt(ImgNetStatisticTool.KEY_TOTAL_IMG_REQUEST_COUNT, 0);
        int i14 = sharedPreferences.getInt(ImgNetStatisticTool.KEY_SUCCEED_IMG_REQUEST_COUNT, 0);
        int i15 = sharedPreferences.getInt(ImgNetStatisticTool.KEY_SUCCEED_IMG_DOMAIN_REQUEST_COUNT, 0);
        int i16 = sharedPreferences.getInt(ImgNetStatisticTool.KEY_SUCCEED_IMG_BAK_IP_REQUEST_COUNT, 0);
        int i17 = sharedPreferences.getInt(ImgNetStatisticTool.KEY_SUCCEED_IMG_BAK_DOMAIN_REQUEST_COUNT, 0);
        int i18 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_DOWNLOAD_TOTAL_REQUEST_COUNT, 0);
        int i19 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_DOWNLOAD_SUCCEED_REQUEST_COUNT, 0);
        int i20 = sharedPreferences.getInt(JDNetworkStatisticTool.KEY_DOWNLOAD_BACKUP_DOMAIN_COUNT, 0);
        int i21 = sharedPreferences.getInt("push_fgd", 0);
        int i22 = sharedPreferences.getInt("push_lvd", 0);
        int i23 = sharedPreferences.getInt("push_lctc", 0);
        int i24 = sharedPreferences.getInt("push_lcsc", 0);
        int i25 = sharedPreferences.getInt("push_cct", 0);
        boolean contains = sharedPreferences.contains("push_fcit");
        long j2 = sharedPreferences.getLong("push_fcit", 0L);
        if (OKLog.D) {
            str = "push_fcit";
            String str4 = "Network statistic data : Total Request : " + i2 + "\n Succeed : " + i3 + "\n Domain2Ip : " + i4 + "\n FinalDowngrade2Domain : " + i5 + "\n Down2BuildIn : " + i6 + "\n Down2HttpDns : " + i7 + "\n Down2Backup : " + i8 + "\n TotalHttpDnsCount : " + i10 + "\n SucceedHttpDnsCount : " + i11 + "\n totalHttpsRequestCount : " + i9 + "\n succeedHttpDNSDomainRequestCount : " + i12 + "\n totalImgRequestCount : " + i13 + "\n succImgRequestCount : " + i14 + "\n succImgDomainRequestCount : " + i15 + "\n succImgBakIpRequestCount : " + i16 + "\n succImgBakDomainReqCnt : " + i17 + "\n totalDownloadReqCnt : " + i18 + "\n downloadSuccessRequestCount : " + i19 + "\n downloadBackupDomainRequestCount : " + i20 + "\n foregroundDuration : " + i21 + "\n longConnValidDuration : " + i22 + "\n longConnTryCount : " + i23 + "\n longConnSuccessCount : " + i24 + "\n connectCostTime : " + i25 + "\n finishedConnectionInTime : " + j2;
            str2 = TAG;
            OKLog.d(str2, str4);
        } else {
            str = "push_fcit";
            str2 = TAG;
        }
        try {
            HashMap hashMap = new HashMap();
            String str5 = str2;
            try {
                hashMap.put("specialFlag", "1");
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("rc", i2).put("rsc", i3).put("isrc", i4).put("fdsrc", i5).put("bsrc", i6).put("hsrc", i7).put("bksrc", i8).put("hdnsrc", i10).put("hdnsrsc", i11).put("hdnsdsrc", i12).put("src", i9).put("img_rc", i13).put("img_rsc", i14).put("img_dsrc", i15).put("img_bkirc", i16).put("img_bkdrc", i17).put("dwn_rc", i18).put("dwn_rsc", i19).put("dwn_bkrc", i20).put("push_fgd", i21).put("push_lvd", i22).put("push_lctc", i23).put("push_lcsc", i24).put("push_cct", i25);
                if (contains) {
                    jSONObject.put(str, j2);
                }
                if (OKLog.D) {
                    str3 = str5;
                    try {
                        OKLog.d(str3, "statistic data String : " + jSONObject.toString());
                    } catch (Throwable th) {
                        th = th;
                        if (OKLog.E) {
                            OKLog.e(str3, th);
                        }
                        sharedPreferences.edit().clear().apply();
                        JDNetworkStatisticTool.getInstance().isSendLastResults = true;
                        StatisticTool.setIsSendLastResult(true);
                        ImgNetStatisticTool.setIsSendLastResult(true);
                    }
                } else {
                    str3 = str5;
                }
                hashMap.put("postData", jSONObject.toString());
                hashMap.put("errCode", JDNetworkConstant.STATISTIC_ERRCODE);
                hashMap.put(PerformanceManager.ERR_TYPE, "3");
                hashMap.put("occurTime", getCurrentMicrosecond());
                hashMap.put("reserved1", NetUtils.getNetworkType());
                hashMap.put("reserved2", LocalIPAddressResolver.getHostIPv6AddressJsonStr());
                hashMap.put("reserved3", DnsServerResolver.getServers(JdSdk.getInstance().getApplicationContext()));
                sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
            } catch (Throwable th2) {
                th = th2;
                str3 = str5;
            }
        } catch (Throwable th3) {
            th = th3;
            str3 = str2;
        }
        sharedPreferences.edit().clear().apply();
        JDNetworkStatisticTool.getInstance().isSendLastResults = true;
        StatisticTool.setIsSendLastResult(true);
        ImgNetStatisticTool.setIsSendLastResult(true);
    }

    public static void reportNormalRemindErr(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("specialFlag", "1");
            hashMap.put("httpResp", "0");
            hashMap.put("errCode", NORMAL_REMIND);
            hashMap.put("errMsg", str);
            hashMap.put("verifyCode", "");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportOpenAppJumpException(String str, String str2, String str3) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "960");
            hashMap.put("function", str);
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put("errMsg", "url: " + str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                hashMap.put(t.f20145j, str3);
            }
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        if (WebDebug.report) {
            WebDebug.log("openapp", "function:" + str + "--url:" + str2 + "--err:" + str3);
        }
    }

    public static void reportPHCException(String str, String str2, String str3, Throwable th, String str4) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", PHC_ERROR_CODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("function", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put("url", str2);
            }
            if (th != null) {
                hashMap.put(t.f20145j, d.b(th));
            }
            if (!TextUtils.isEmpty(str4)) {
                hashMap.put(t.f20145j, str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                hashMap.put("errMsg", str3);
            }
            hashMap.put("occurTime", getCurrentMicrosecond());
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th2) {
            if (OKLog.E) {
                OKLog.e(TAG, th2);
            }
        }
    }

    public static final void reportRichtextException(String str, String str2, String str3, String str4, String str5, String str6) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportRichtextException: functionId = " + str + ", errorCode = 920, authorId = " + str2 + ", articleId = " + str3 + ", channel = " + str4 + ", richtextUrl = " + str5);
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", str);
            hashMap.put("errCode", "920");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("errMsg", String.format("{\"AuthorId\":\"%s\", \"ArticleId\":\"%s\", \"channel\":\"%s\", \"richtexturl\":\"%s\", \"H5ErrorCode\":\"%s\"}", str2, str3, str4, str5, str6));
            hashMap.put("occurTime", getCurrentMicrosecond());
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportSettlementException(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "961");
            hashMap.put("function", "calcFunc");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("errMsg", str2);
            hashMap.put(t.f20145j, str);
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportStartUpData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("specialFlag", "1");
            hashMap.put("httpResp", "0");
            hashMap.put("errCode", JDNetworkConstant.START_UP_TIME_ERRCODE);
            hashMap.put("errMsg", str);
            hashMap.put("verifyCode", "");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void reportTlsHandshakeData(final JDNetworkStatisticTool.TlsStatEntry tlsStatEntry) {
        tlsReportService.submit(new Runnable() { // from class: com.jingdong.jdsdk.network.toolbox.ExceptionReporter.1
            /* JADX WARN: Removed duplicated region for block: B:119:0x0067 A[Catch: all -> 0x00da, TRY_ENTER, TryCatch #2 {all -> 0x00ec, blocks: (B:103:0x0006, B:105:0x0013, B:107:0x0026, B:133:0x00e4, B:119:0x0067, B:121:0x0084, B:122:0x008e, B:124:0x0098, B:125:0x00a1, B:126:0x00ae, B:127:0x00c5), top: B:143:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:147:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                try {
                    String stringFromPreference = ConfigUtil.getStringFromPreference("tlsReport");
                    if (TextUtils.isEmpty(stringFromPreference)) {
                        return;
                    }
                    JDJSONObject parseObject = JDJSON.parseObject(stringFromPreference);
                    if (!TextUtils.equals("1", parseObject.getString("reportSwitch"))) {
                        return;
                    }
                    int parseInt = Integer.parseInt(parseObject.getString("limit"));
                    int parseInt2 = Integer.parseInt(parseObject.getString("interval"));
                    long j2 = SharedPreferencesUtil.getLong("tlsReportTimeMillis", 0L);
                    boolean z = false;
                    int i2 = SharedPreferencesUtil.getInt("tlsReportCount", 0);
                    long currentTimeMillis = System.currentTimeMillis();
                    if (j2 > 0) {
                        long j3 = currentTimeMillis - j2;
                        if (j3 <= 86400000) {
                            if (j3 >= parseInt2 * 1000 && i2 < parseInt) {
                                z = true;
                            }
                            if (z) {
                                return;
                            }
                            HashMap hashMap = new HashMap();
                            hashMap.put("errCode", ExceptionReporter.TLS_HANDSHAKE_DATA);
                            hashMap.put(PerformanceManager.ERR_TYPE, "2");
                            if (!TextUtils.isEmpty(tlsStatEntry.url)) {
                                hashMap.put("url", tlsStatEntry.url);
                            }
                            if (!TextUtils.isEmpty(tlsStatEntry.tlsVersion)) {
                                hashMap.put(t.f20145j, tlsStatEntry.tlsVersion);
                            }
                            hashMap.put("errNum", String.valueOf(tlsStatEntry.timeCost));
                            try {
                                hashMap.put("reserved1", ExceptionReporter.getIpByHost(new URL(tlsStatEntry.url).getHost()));
                            } catch (Exception unused) {
                            }
                            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
                            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
                            SharedPreferencesUtil.putLong("tlsReportTimeMillis", currentTimeMillis);
                            SharedPreferencesUtil.putInt("tlsReportCount", i2 + 1);
                            return;
                        }
                    }
                    z = true;
                    i2 = 0;
                    if (z) {
                    }
                } catch (Throwable th) {
                    if (OKLog.D) {
                        th.printStackTrace();
                    }
                }
            }
        });
    }

    public static final void reportWebPageError(String str, String str2, String str3, String str4) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", str);
            hashMap.put("errCode", WebWhiteScreenHolder.ERR_CODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put(t.f20145j, str2);
            hashMap.put("url", str3);
            hashMap.put("errMsg", str4);
            hashMap.put("occurTime", getCurrentMicrosecond());
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static final void reportWebViewCommonError(String str, String str2, String str3, String str4) {
        reportWebViewCommonError(str, str2, str3, str4, true, null);
    }

    public static final void reportWebViewCommonErrorNoIp(String str, String str2, String str3, String str4) {
        reportWebViewCommonError(str, str2, str3, str4, false, null);
    }

    public static final void reportWebViewErrorUrl(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportWebViewErrorUrl:" + str);
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "WebView_Error_Host");
            hashMap.put("url", str);
            hashMap.put("postData", str2);
            hashMap.put("httpResp", "0");
            hashMap.put("errCode", JDNetworkConstant.WEBVIEW_ERROR_HOST_ERRCODE);
            hashMap.put("verifyCode", "");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static final void reportWebViewSslError(String str, String str2, String str3) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportWebViewSslError:" + str);
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", JDNetworkConstant.HTTPS_CERTIFICATE_INVALID_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "1");
            hashMap.put("url", str);
            hashMap.put("function", "WebView_SSL_Error");
            hashMap.put("errMsg", str2);
            hashMap.put(t.f20145j, str3);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("reserved1", getIpByHost(new URL(str).getHost()));
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static void sendExceptionData(Context context, HashMap<String, String> hashMap) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.EXCEPTION_REPORTER, true) && hashMap != null) {
            try {
                if (checkCodeCanReport(context, hashMap)) {
                    hashMap.put("accountId", JDExReportInterface.getEncryptLoginUserName(LoginUserHelper.getInstance().getLoginUser().getLoginUserName()));
                    hashMap.put("userModel", JDBModeUtils.getCurrentMode());
                    JDExReportInterface.sendData(context, getinitCommonInfo(), hashMap);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void attachHttpSetting(HttpSetting httpSetting) {
        this.mHttpSetting = httpSetting;
    }

    public void reportHttpBusinessException(HttpResponse httpResponse) {
        if (this.mHttpSetting == null) {
            return;
        }
        String string = httpResponse == null ? "" : httpResponse.getString();
        HttpSetting httpSetting = this.mHttpSetting;
        String url = httpSetting.getUrl();
        String jsonParamsString = httpSetting.getJsonParamsString();
        if (OKLog.D) {
            OKLog.d(TAG, "reportHttpBusinessException:id\uff1a" + httpSetting.getId() + DYConstants.DY_REGEX_COMMA + httpSetting.getMtaFunctionId());
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", httpSetting.getMtaFunctionId());
            hashMap.put("url", url);
            hashMap.put("postData", jsonParamsString);
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(httpResponse == null ? "" : Integer.valueOf(httpResponse.getCode()));
            hashMap.put("httpResp", sb.toString());
            hashMap.put("errCode", JDNetworkConstant.HTTP_BUSNISS_ERRCODE);
            hashMap.put("errMsg", string);
            hashMap.put("verifyCode", "");
            hashMap.put(t.f20145j, "");
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public ExceptionReporter(HttpSetting httpSetting) {
        attachHttpSetting(httpSetting);
    }

    public static void reportApplicationUpgradeEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportApplicationUpgradeEvent: " + str);
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "");
            hashMap.put("url", str);
            hashMap.put("upClickTime", str2);
            hashMap.put("upCancleTime", str3);
            hashMap.put("upDownSize", str4);
            hashMap.put("upInstallTime", str5);
            hashMap.put("errCode", JDNetworkConstant.UPGRADE_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            hashMap.put("occurTime", getCurrentMicrosecond());
            if (!TextUtils.isEmpty(str6)) {
                hashMap.put("errMsg", str6);
            }
            if (!TextUtils.isEmpty(str7)) {
                hashMap.put(t.f20145j, str7);
            }
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static final void reportBitmapException(String str, JDFailReason jDFailReason, String str2) {
        reportBitmapException(str, jDFailReason, str2, -1);
    }

    public static final void reportWebViewCommonError(String str, String str2, String str3, String str4, boolean z, String str5) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportWebViewCommonError:");
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", JDNetworkConstant.START_UP_TIME_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            hashMap.put("function", str);
            hashMap.put("errMsg", str3);
            hashMap.put(t.f20145j, str4);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("url", str2 == null ? "" : str2);
            if (z && !TextUtils.isEmpty(str2) && str2.startsWith("http")) {
                try {
                    hashMap.put("reserved1", getIpByHost(new URL(str2).getHost()));
                } catch (Exception unused) {
                }
            }
            String str6 = WebHybridUtils.HDB_SESSION;
            if (!TextUtils.isEmpty(str6)) {
                hashMap.put("reserved2", str6);
            }
            if (!TextUtils.isEmpty(str5)) {
                hashMap.put("reserved3", str5);
            }
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static final void reportWebViewCommonErrorNoIp(String str, String str2, String str3, String str4, String str5) {
        reportWebViewCommonError(str, str2, str3, str4, false, str5);
    }

    public static final void reportWebPageError(String str, String str2, String str3, String str4, String str5) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", str);
            hashMap.put("errCode", WebWhiteScreenHolder.ERR_CODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put(t.f20145j, str2);
            hashMap.put("url", str3);
            hashMap.put("errMsg", str4);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("reserved1", str5);
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public static final void reportWebPageError(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", str);
            hashMap.put("errCode", WebWhiteScreenHolder.ERR_CODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put(t.f20145j, str2);
            hashMap.put("url", str3);
            hashMap.put("errMsg", str4);
            hashMap.put("occurTime", getCurrentMicrosecond());
            hashMap.put("reserved1", str5);
            hashMap.put("reserved2", str6);
            hashMap.put("reserved3", str7);
            sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }
}
