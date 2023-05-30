package com.jingdong.common.web.managers;

import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.util.URLUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebView;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes12.dex */
public class WebPerformanceHolder {
    private static final String TAG = "WebPerformanceHolder";
    public static final String xHost = "sfgala.jd.com";
    private volatile WebPerformance currentRecord;
    private JDWebView jdWebView;
    private final List<WebPerformance> performanceRecords = Collections.synchronizedList(new LinkedList());
    private String pageType = "webview";
    private String firstInterruptMsg = null;

    public WebPerformanceHolder(JDWebView jDWebView) {
        WebPerfManager.getInstance();
        this.jdWebView = jDWebView;
        createRecord();
    }

    public void addHttpErrToCurrRecord(JDJSONObject jDJSONObject) {
        if (this.currentRecord != null) {
            this.currentRecord.addHttpErr(jDJSONObject);
        }
    }

    public void addSslErrToCurrRecord(JDJSONObject jDJSONObject) {
        if (this.currentRecord != null) {
            this.currentRecord.addSslErr(jDJSONObject);
        }
    }

    public void appendDataToCurrRecord(String str, String str2) {
        if (this.currentRecord != null) {
            this.currentRecord.appendData(str, str2);
        }
    }

    public void appendExtraJsonObjToCurr(String str, String str2, Object obj) {
        if (this.currentRecord != null) {
            this.currentRecord.appendToExtraJsonObj(str, str2, obj);
        }
    }

    public void appendExtraToCurrRecord(String str, Object obj) {
        if (this.currentRecord != null) {
            this.currentRecord.appendExtra(str, obj);
        }
    }

    public void clearAll() {
        if (this.performanceRecords.isEmpty()) {
            return;
        }
        synchronized (this.performanceRecords) {
            this.performanceRecords.clear();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public WebPerformance createRecord() {
        String str;
        if (WebPerfManager.getInstance().isEnable()) {
            WebPerformance webPerformance = new WebPerformance(System.currentTimeMillis());
            this.currentRecord = webPerformance;
            webPerformance.appendData(WebPerfManager.PAGE_TYPE, this.pageType);
            JDWebView jDWebView = this.jdWebView;
            String str2 = "";
            if (jDWebView != null && (jDWebView.getContext() instanceof MutableContextWrapper)) {
                Context baseContext = ((MutableContextWrapper) this.jdWebView.getContext()).getBaseContext();
                if (baseContext instanceof Activity) {
                    str = baseContext.getClass().getSimpleName();
                    if (TextUtils.isEmpty(str)) {
                        JDWebView jDWebView2 = this.jdWebView;
                        if (jDWebView2 != null && jDWebView2.getContext() != null) {
                            str2 = this.jdWebView.getContext().getClass().getSimpleName();
                        }
                        str = str2;
                    }
                    webPerformance.appendData(WebPerfManager.PAGE_NAME, str);
                    webPerformance.appendData("businessType", "0");
                    webPerformance.appendData(WebPerfManager.BIZ_OFFLINE_BINGO, "0");
                    webPerformance.appendData(WebPerfManager.SHARED_OFFLINE_BINGO, "0");
                    webPerformance.appendData("preloadStatus", "0");
                    webPerformance.appendData("htmlPreDownload", "0");
                    this.performanceRecords.add(webPerformance);
                    if (OKLog.D) {
                        OKLog.d(TAG, "\u65b0\u589e\u4e00\u6761\u6027\u80fd\u6570\u636e\uff0c\u76ee\u524dsize = " + this.performanceRecords.size() + ", \u5f53\u524d\u6570\u636e = " + this.currentRecord.toString());
                    }
                    return webPerformance;
                }
            }
            str = "";
            if (TextUtils.isEmpty(str)) {
            }
            webPerformance.appendData(WebPerfManager.PAGE_NAME, str);
            webPerformance.appendData("businessType", "0");
            webPerformance.appendData(WebPerfManager.BIZ_OFFLINE_BINGO, "0");
            webPerformance.appendData(WebPerfManager.SHARED_OFFLINE_BINGO, "0");
            webPerformance.appendData("preloadStatus", "0");
            webPerformance.appendData("htmlPreDownload", "0");
            this.performanceRecords.add(webPerformance);
            if (OKLog.D) {
            }
            return webPerformance;
        }
        return null;
    }

    public WebPerformance getCurrentRecord() {
        return this.currentRecord;
    }

    public String getFirstInterruptMsg() {
        return this.firstInterruptMsg;
    }

    public WebPerformance getRecord(long j2) {
        if (!WebPerfManager.getInstance().isEnable() || j2 == 0 || this.performanceRecords.isEmpty()) {
            return null;
        }
        synchronized (this.performanceRecords) {
            int size = this.performanceRecords.size();
            if (size == 0) {
                return null;
            }
            for (int i2 = size - 1; i2 >= 0; i2--) {
                WebPerformance webPerformance = this.performanceRecords.get(i2);
                if (webPerformance != null && webPerformance.getCreateTime() == j2) {
                    return webPerformance;
                }
            }
            return null;
        }
    }

    public WebPerformance getRecordNotContainKeyFromUrl(String str, String str2) {
        if (!WebPerfManager.getInstance().isEnable() || TextUtils.isEmpty(str) || this.performanceRecords.isEmpty()) {
            return null;
        }
        synchronized (this.performanceRecords) {
            if (this.performanceRecords.isEmpty()) {
                return null;
            }
            for (WebPerformance webPerformance : this.performanceRecords) {
                if (webPerformance != null && str.equalsIgnoreCase(webPerformance.getUrl()) && !webPerformance.containKeyExact(str2)) {
                    return webPerformance;
                }
            }
            return null;
        }
    }

    public WebPerformance getRecordWithoutFinish100FromUrl(String str) {
        return getRecordNotContainKeyFromUrl(str, WebPerfManager.PAGE_FINISH100);
    }

    public WebPerformance getRecordWithoutFinishFromUrl(String str) {
        return getRecordNotContainKeyFromUrl(str, "pageFinish");
    }

    public boolean isCombineWithNext() {
        return this.currentRecord != null && this.currentRecord.isCombineWithNext();
    }

    public boolean isFirstInterrupted() {
        return !TextUtils.isEmpty(this.firstInterruptMsg);
    }

    public void reportAll() {
        if (WebPerfManager.getInstance().isEnable() && !this.performanceRecords.isEmpty()) {
            reportRecordBefore(this.currentRecord, true);
        }
    }

    public void reportRecord(WebPerformance webPerformance, boolean z) {
        if (!WebPerfManager.getInstance().isEnable() || webPerformance == null || webPerformance.isReported()) {
            return;
        }
        if (webPerformance.isDirectReport()) {
            WebPerfManager.getInstance().reportRecord(webPerformance, z);
            return;
        }
        if (z) {
            webPerformance.setDirectReport(true);
        }
        if (webPerformance.containKeyExact(WebPerfManager.PAGE_FINISH100)) {
            webPerformance.appendExtra(WebPerfManager.PAGE_FINISH100, webPerformance.getData(WebPerfManager.PAGE_FINISH100));
        }
        if (isFirstInterrupted() && webPerformance.containKeyExact("initStart")) {
            webPerformance.appendData(WebPerfManager.INTERRUPT, String.valueOf(System.currentTimeMillis()));
            webPerformance.appendExtra("firstInterruptMsg", getFirstInterruptMsg());
        }
        if (webPerformance.isInterrupted()) {
            webPerformance.appendToExtraJsonObj("singleInterrupt", "time", String.valueOf(System.currentTimeMillis()));
            webPerformance.appendToExtraJsonObj("singleInterrupt", "msg", webPerformance.getInterruptedMsg());
        }
        if (webPerformance.containKeyExact(WebPerfManager.COMBINED_PAGE_START) || webPerformance.containKeyExact(WebPerfManager.COMBINED_PAGE_FINISH)) {
            webPerformance.appendToExtraJsonObj(WebPerfManager.COMBINE_DATA, WebPerfManager.COMBINED_PAGE_START, webPerformance.getData(WebPerfManager.COMBINED_PAGE_START));
            webPerformance.appendToExtraJsonObj(WebPerfManager.COMBINE_DATA, WebPerfManager.COMBINED_PAGE_FINISH, webPerformance.getData(WebPerfManager.COMBINED_PAGE_FINISH));
        }
        if (webPerformance.isNetError()) {
            JDWebView jDWebView = this.jdWebView;
            webPerformance.appendData(WebPerfManager.UA, jDWebView != null ? jDWebView.getUaInfo() : "");
            String url = webPerformance.getUrl();
            if (!TextUtils.isEmpty(url)) {
                HashMap hashMap = new HashMap();
                hashMap.put(BabelLoginCallback.KEY_COOKIES, CookieManager.getInstance().getCookie(url));
                webPerformance.appendData("header", hashMap);
            }
        }
        JDWebView jDWebView2 = this.jdWebView;
        webPerformance.appendData(WebPerfManager.CORE_TYPE, jDWebView2 != null ? !jDWebView2.isSystemCoreNotX5() ? "x5" : "system" : "");
        webPerformance.appendData(WebPerfManager.CORE_VER, String.valueOf(WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication())));
        webPerformance.preReport();
        reportXData(webPerformance);
        WebPerfManager.getInstance().reportRecord(webPerformance, z);
    }

    public void reportRecordBefore(WebPerformance webPerformance, boolean z) {
        reportRecordBefore(webPerformance, z, false);
    }

    public void reportXData(WebPerformance webPerformance) {
        String str;
        long j2;
        long j3;
        long j4;
        try {
            String data = webPerformance.containKeyExact("url") ? webPerformance.getData("url") : "";
            String data2 = webPerformance.containKeyExact(WebPerfManager.BIZ_OFFLINE_BINGO) ? webPerformance.getData(WebPerfManager.BIZ_OFFLINE_BINGO) : "0";
            String data3 = webPerformance.containKeyExact("initStart") ? webPerformance.getData("initStart") : "";
            String data4 = webPerformance.containKeyExact("pageStart") ? webPerformance.getData("pageStart") : "";
            String data5 = webPerformance.containKeyExact("pageFinish") ? webPerformance.getData("pageFinish") : "";
            String data6 = webPerformance.containKeyExact(WebPerfManager.X_B_TYPE) ? webPerformance.getData(WebPerfManager.X_B_TYPE) : "0";
            String data7 = webPerformance.containKeyExact("errorCode") ? webPerformance.getData("errorCode") : "";
            str = "";
            String str2 = "1";
            if (webPerformance.isInterrupted()) {
                data7 = "1";
            } else if (TextUtils.isEmpty(data7)) {
                data7 = "0";
            }
            String data8 = webPerformance.containKeyExact(WebPerfManager.HYBRID_CONFIG_VERSION) ? webPerformance.getData(WebPerfManager.HYBRID_CONFIG_VERSION) : str;
            String data9 = webPerformance.containKeyExact(WebPerfManager.HYBRID_FILE_VERSION) ? webPerformance.getData(WebPerfManager.HYBRID_FILE_VERSION) : "";
            long j5 = 0;
            try {
                j2 = Long.parseLong(data3);
            } catch (Exception unused) {
                j2 = 0;
            }
            try {
                j3 = Long.parseLong(data4);
            } catch (Exception unused2) {
                j3 = 0;
            }
            try {
                j4 = Long.parseLong(data5);
            } catch (Exception unused3) {
                j4 = 0;
            }
            long j6 = j4 - j2;
            long j7 = j4 - j3;
            String str3 = webPerformance.containKeyExact("initStart") ? "1" : "0";
            if ("1".equals(str3)) {
                if (j2 != 0 && j4 != 0) {
                    j5 = j6;
                }
            } else if (j3 != 0 && j4 != 0) {
                j5 = j7;
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("url", (Object) data);
            jDJSONObject.put(WebPerfManager.X_B_TYPE, (Object) data6);
            if (!"1".equals(data2)) {
                str2 = "0";
            }
            jDJSONObject.put("bingo", (Object) str2);
            jDJSONObject.put("loadtime", (Object) String.valueOf(j5));
            jDJSONObject.put("code", (Object) data7);
            jDJSONObject.put("onelevel", str3);
            jDJSONObject.put(WebPerfManager.HYBRID_CONFIG_VERSION, (Object) data8);
            jDJSONObject.put(WebPerfManager.HYBRID_FILE_VERSION, (Object) data9);
            try {
                JDMtaUtils.sendExposureDataWithExt(this.jdWebView.getContext(), "hybrid_info", "", "", "", "", jDJSONObject.toJSONString(), null);
            } catch (Exception unused4) {
            }
        } catch (Exception unused5) {
        }
    }

    public void setCombineWithNext(boolean z) {
        if (this.currentRecord != null) {
            this.currentRecord.setCombineWithNext(z);
        }
    }

    public void setErrorToCurrRecord(boolean z) {
        if (this.currentRecord != null) {
            this.currentRecord.setError(z);
        }
    }

    public void setFailUrlToCurrRecord(String str) {
        if (this.currentRecord != null) {
            this.currentRecord.setFailingUrl(str);
        }
    }

    public void setFirstInterruptMsg(String str) {
        this.firstInterruptMsg = str;
    }

    public void setNetErrorToCurrRecord(boolean z) {
        if (this.currentRecord != null) {
            this.currentRecord.setNetError(z);
        }
    }

    public void setPageType(String str) {
        if (WebPerfManager.getInstance().isEnable() && !TextUtils.isEmpty(str)) {
            this.pageType = str;
            if (this.currentRecord != null) {
                this.currentRecord.appendData(WebPerfManager.PAGE_TYPE, str);
            }
        }
    }

    public void setPathToCurrRecord(List<String> list) {
        if (this.currentRecord != null) {
            this.currentRecord.setPath(list);
        }
    }

    public boolean shouldCombineRecordForUrl(String str) {
        return URLUtils.isGentokenUrl(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0063 A[Catch: all -> 0x00a4, TryCatch #0 {, blocks: (B:119:0x0024, B:121:0x002c, B:123:0x002e, B:125:0x0037, B:129:0x0045, B:134:0x005e, B:136:0x0063, B:137:0x0066, B:132:0x0052, B:138:0x006a, B:140:0x0070, B:142:0x0079, B:143:0x00a2), top: B:149:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0066 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void reportRecordBefore(WebPerformance webPerformance, boolean z, boolean z2) {
        if (!WebPerfManager.getInstance().isEnable() || webPerformance == null || webPerformance.getCreateTime() == 0 || this.performanceRecords.isEmpty()) {
            return;
        }
        synchronized (this.performanceRecords) {
            int size = this.performanceRecords.size();
            if (size <= 0) {
                return;
            }
            WebPerformance webPerformance2 = null;
            LinkedList linkedList = new LinkedList();
            int i2 = 0;
            while (i2 < size) {
                WebPerformance webPerformance3 = this.performanceRecords.get(i2);
                if (webPerformance3 == null || webPerformance2 == webPerformance3) {
                    break;
                } else if (z) {
                    if (webPerformance3.getCreateTime() > webPerformance.getCreateTime()) {
                        break;
                    }
                    reportRecord(webPerformance3, z2);
                    if (z2) {
                        linkedList.add(webPerformance3);
                    }
                    i2++;
                    webPerformance2 = webPerformance3;
                } else {
                    if (webPerformance3.getCreateTime() >= webPerformance.getCreateTime()) {
                        break;
                    }
                    reportRecord(webPerformance3, z2);
                    if (z2) {
                    }
                    i2++;
                    webPerformance2 = webPerformance3;
                }
            }
            if (!linkedList.isEmpty()) {
                this.performanceRecords.removeAll(linkedList);
                if (OKLog.D) {
                    OKLog.d(TAG, "[\u5220\u9664]" + linkedList.size() + "\u6761\u6027\u80fd\u6570\u636e\uff0c\u76ee\u524dsize = " + this.performanceRecords.size());
                }
            }
        }
    }
}
