package com.jingdong.common.jdreactFramework.track;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.listener.DataReportListener;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.common.jdreactFramework.utils.SPForDataReportUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class RenderDataReport {
    private static RenderDataReport sRenderDataReport;
    public static ArrayList<Map> sRenderReportList = new ArrayList<>();
    final String suffix = "FIRSTLOADOVER";
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    HashMap<String, HashMap<String, String>> dataMap = new HashMap<>();

    private RenderDataReport() {
    }

    public static RenderDataReport getInstance() {
        if (sRenderDataReport == null) {
            sRenderDataReport = new RenderDataReport();
        }
        return sRenderDataReport;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportData(HashMap<String, String> hashMap, String str) {
        DataReportListener dataReportListener = JDReactHelper.newInstance().getDataReportListener();
        if (dataReportListener != null) {
            hashMap.remove("title");
            hashMap.remove("loadStartTime");
            hashMap.remove("renderStartTime");
            hashMap.remove("jsRuntimeError");
            dataReportListener.onLoadDataReport(hashMap);
        }
        this.dataMap.remove(str);
    }

    public void attached(String str) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        hashMap.put("attached", System.currentTimeMillis() + "");
        JLog.d("RenderDataReport", "attached ==> " + str + "-->" + hashMap);
    }

    public void clearData() {
        JLog.d("RenderDataReport", "clearData ==> " + this.dataMap);
        this.dataMap.clear();
    }

    public HashMap getMap() {
        return this.dataMap;
    }

    public void hasDownload(String str, String str2) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        hashMap.put("hasdownload", str2.equals(DYConstants.DY_TRUE) ? "1" : "0");
        JLog.d("RenderDataReport", "hasdownload ==> " + str + "-->" + hashMap);
    }

    public void initData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        String valueForSession = SPForDataReportUtils.getValueForSession(str2);
        String str9 = "1";
        if (!TextUtils.isEmpty(valueForSession)) {
            if (!valueForSession.endsWith("FIRSTLOADOVER")) {
                SPForDataReportUtils.saveSession(str2, "FIRSTLOADOVER");
                str9 = "0";
            } else {
                valueForSession = SPForDataReportUtils.generateSessionId();
            }
        }
        if (TextUtils.isEmpty(valueForSession)) {
            valueForSession = SPForDataReportUtils.generateSessionId();
        }
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        hashMap.put("session", valueForSession);
        hashMap.put(JDReactConstant.IntentConstant.MODULE_NAME, str2);
        hashMap.put("moduleversion", str3);
        hashMap.put("bundletype", str4);
        hashMap.put("bundlesource", str5);
        hashMap.put("isfirstload", str9);
        hashMap.put("appversion", str6);
        hashMap.put("netstate", str7);
        hashMap.put("bundlesize", str8);
        JLog.d("RenderDataReport", "initData ==> " + str + "-->" + hashMap);
    }

    public void loadStart(String str) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        long currentTimeMillis = System.currentTimeMillis();
        hashMap.put("loadStartTime", currentTimeMillis + "");
        hashMap.put("loadstarttime", AresCommonUtils.DateFormatStr(currentTimeMillis, "yyyy-MM-dd HH:mm:ss SSS"));
        JLog.d("RenderDataReport", "loadStart ==> " + str + "-->" + hashMap);
    }

    public void putData(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || this.dataMap.get(str) == null) {
            return;
        }
        this.dataMap.get(str).put(str2, str3);
    }

    public void renderEnd(final String str, boolean z, String str2, String str3, boolean z2) {
        final HashMap<String, String> hashMap = this.dataMap.get(str);
        JLog.d("RenderDataReport", "renderEnd ==> " + str + "-->" + hashMap);
        if (hashMap == null) {
            return;
        }
        long parseLong = hashMap.get("loadStartTime") == null ? 0L : Long.parseLong(hashMap.get("loadStartTime"));
        long parseLong2 = hashMap.get("renderStartTime") == null ? 0L : Long.parseLong(hashMap.get("renderStartTime"));
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - parseLong;
        long j3 = currentTimeMillis - parseLong2;
        if (j3 < 0 || parseLong == 0 || parseLong2 == 0 || hashMap.get(JDReactConstant.IntentConstant.MODULE_NAME) == null) {
            return;
        }
        if (z) {
            hashMap.put("loadresult", "0");
        } else {
            hashMap.put("loadresult", "1");
            hashMap.put("loadfailcode", str2);
            hashMap.put("loadfailreason", str3);
        }
        hashMap.put("loadtime", j3 + "");
        hashMap.put("loadtotaltime", j2 + "");
        hashMap.put("engineversion", "0.59.9");
        hashMap.put("phonemodel", BaseInfo.getDeviceModel());
        hashMap.put("appid", NetConfig.sAppCode);
        if (z2) {
            hashMap.put("isPreload", "1");
        } else {
            hashMap.put("isPreload", "0");
        }
        if (!hashMap.containsKey("hasdownload")) {
            hashMap.put("hasdownload", "0");
        }
        if (!hashMap.containsKey("isbg")) {
            hashMap.put("isbg", "0");
        }
        hashMap.put("loadendtime", AresCommonUtils.DateFormatStr(currentTimeMillis, "yyyy-MM-dd HH:mm:ss SSS"));
        if (JDReactHelper.newInstance().isDebug()) {
            hashMap.put("title", AresCommonUtils.timestampToDateStr(currentTimeMillis));
            sRenderReportList.add(hashMap);
            JLog.d("RenderDataReport", "res = " + sRenderReportList.toString());
        }
        if (z) {
            this.mainHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.jdreactFramework.track.RenderDataReport.1
                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.equals((CharSequence) hashMap.get("jsRuntimeError"), DYConstants.DY_TRUE)) {
                        hashMap.put("loadresult", "1");
                        hashMap.put("loadfailcode", "11");
                        hashMap.put("loadfailreason", TrackConstant.getErrorMsg(11));
                    }
                    RenderDataReport.this.reportData(hashMap, str);
                }
            }, 1000L);
        } else {
            reportData(hashMap, str);
        }
    }

    public void renderStart(String str) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        hashMap.put("renderStartTime", System.currentTimeMillis() + "");
        JLog.d("RenderDataReport", "renderStart ==> " + str + "-->" + hashMap);
    }

    public void setBackgroundStatus(String str, int i2) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        hashMap.put("isbg", "" + i2);
        JLog.d("RenderDataReport", "isbg ==> " + str + "-->" + hashMap);
    }

    public void showRetry(String str) {
        HashMap<String, String> hashMap = this.dataMap.get(str);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.dataMap.put(str, hashMap);
        }
        hashMap.put("showRetry", "1");
        JLog.d("RenderDataReport", "showRetry ==> " + str + "-->" + hashMap);
    }
}
