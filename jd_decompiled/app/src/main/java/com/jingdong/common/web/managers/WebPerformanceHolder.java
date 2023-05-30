package com.jingdong.common.web.managers;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.util.URLUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
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

    /* JADX WARN: Removed duplicated region for block: B:42:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jingdong.common.web.managers.WebPerformance createRecord() {
        /*
            r4 = this;
            com.jingdong.common.web.managers.WebPerfManager r0 = com.jingdong.common.web.managers.WebPerfManager.getInstance()
            boolean r0 = r0.isEnable()
            if (r0 != 0) goto Lc
            r0 = 0
            return r0
        Lc:
            com.jingdong.common.web.managers.WebPerformance r0 = new com.jingdong.common.web.managers.WebPerformance
            long r1 = java.lang.System.currentTimeMillis()
            r0.<init>(r1)
            r4.currentRecord = r0
            java.lang.String r1 = r4.pageType
            java.lang.String r2 = "pageType"
            r0.appendData(r2, r1)
            com.jingdong.common.web.ui.JDWebView r1 = r4.jdWebView
            java.lang.String r2 = ""
            if (r1 == 0) goto L45
            android.content.Context r1 = r1.getContext()
            boolean r1 = r1 instanceof android.content.MutableContextWrapper
            if (r1 == 0) goto L45
            com.jingdong.common.web.ui.JDWebView r1 = r4.jdWebView
            android.content.Context r1 = r1.getContext()
            android.content.MutableContextWrapper r1 = (android.content.MutableContextWrapper) r1
            android.content.Context r1 = r1.getBaseContext()
            boolean r3 = r1 instanceof android.app.Activity
            if (r3 == 0) goto L45
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getSimpleName()
            goto L46
        L45:
            r1 = r2
        L46:
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L65
            com.jingdong.common.web.ui.JDWebView r1 = r4.jdWebView
            if (r1 == 0) goto L64
            android.content.Context r1 = r1.getContext()
            if (r1 == 0) goto L64
            com.jingdong.common.web.ui.JDWebView r1 = r4.jdWebView
            android.content.Context r1 = r1.getContext()
            java.lang.Class r1 = r1.getClass()
            java.lang.String r2 = r1.getSimpleName()
        L64:
            r1 = r2
        L65:
            java.lang.String r2 = "pageName"
            r0.appendData(r2, r1)
            java.lang.String r1 = "businessType"
            java.lang.String r2 = "0"
            r0.appendData(r1, r2)
            java.lang.String r1 = "businessBingo"
            r0.appendData(r1, r2)
            java.lang.String r1 = "combingo"
            r0.appendData(r1, r2)
            java.lang.String r1 = "preloadStatus"
            r0.appendData(r1, r2)
            java.lang.String r1 = "htmlPreDownload"
            r0.appendData(r1, r2)
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r1 = r4.performanceRecords
            r1.add(r0)
            boolean r1 = com.jingdong.sdk.oklog.OKLog.D
            if (r1 == 0) goto Lb9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "\u65b0\u589e\u4e00\u6761\u6027\u80fd\u6570\u636e\uff0c\u76ee\u524dsize = "
            r1.append(r2)
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r2 = r4.performanceRecords
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r2 = ", \u5f53\u524d\u6570\u636e = "
            r1.append(r2)
            com.jingdong.common.web.managers.WebPerformance r2 = r4.currentRecord
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "WebPerformanceHolder"
            com.jingdong.sdk.oklog.OKLog.d(r2, r1)
        Lb9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.managers.WebPerformanceHolder.createRecord():com.jingdong.common.web.managers.WebPerformance");
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

    /* JADX WARN: Removed duplicated region for block: B:103:0x0066 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0063 A[Catch: all -> 0x00a4, TryCatch #0 {, blocks: (B:66:0x0024, B:68:0x002c, B:70:0x002e, B:72:0x0037, B:76:0x0045, B:81:0x005e, B:83:0x0063, B:84:0x0066, B:79:0x0052, B:85:0x006a, B:87:0x0070, B:89:0x0079, B:90:0x00a2), top: B:96:0x0024 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void reportRecordBefore(com.jingdong.common.web.managers.WebPerformance r11, boolean r12, boolean r13) {
        /*
            r10 = this;
            com.jingdong.common.web.managers.WebPerfManager r0 = com.jingdong.common.web.managers.WebPerfManager.getInstance()
            boolean r0 = r0.isEnable()
            if (r0 != 0) goto Lb
            return
        Lb:
            if (r11 == 0) goto La7
            long r0 = r11.getCreateTime()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto La7
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r0 = r10.performanceRecords
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L21
            goto La7
        L21:
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r0 = r10.performanceRecords
            monitor-enter(r0)
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r1 = r10.performanceRecords     // Catch: java.lang.Throwable -> La4
            int r1 = r1.size()     // Catch: java.lang.Throwable -> La4
            if (r1 > 0) goto L2e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
            return
        L2e:
            r2 = 0
            java.util.LinkedList r3 = new java.util.LinkedList     // Catch: java.lang.Throwable -> La4
            r3.<init>()     // Catch: java.lang.Throwable -> La4
            r4 = 0
        L35:
            if (r4 >= r1) goto L6a
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r5 = r10.performanceRecords     // Catch: java.lang.Throwable -> La4
            java.lang.Object r5 = r5.get(r4)     // Catch: java.lang.Throwable -> La4
            com.jingdong.common.web.managers.WebPerformance r5 = (com.jingdong.common.web.managers.WebPerformance) r5     // Catch: java.lang.Throwable -> La4
            if (r5 == 0) goto L6a
            if (r2 == r5) goto L6a
            if (r12 == 0) goto L52
            long r6 = r5.getCreateTime()     // Catch: java.lang.Throwable -> La4
            long r8 = r11.getCreateTime()     // Catch: java.lang.Throwable -> La4
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 > 0) goto L6a
            goto L5e
        L52:
            long r6 = r5.getCreateTime()     // Catch: java.lang.Throwable -> La4
            long r8 = r11.getCreateTime()     // Catch: java.lang.Throwable -> La4
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 >= 0) goto L6a
        L5e:
            r10.reportRecord(r5, r13)     // Catch: java.lang.Throwable -> La4
            if (r13 != 0) goto L66
            r3.add(r5)     // Catch: java.lang.Throwable -> La4
        L66:
            int r4 = r4 + 1
            r2 = r5
            goto L35
        L6a:
            boolean r11 = r3.isEmpty()     // Catch: java.lang.Throwable -> La4
            if (r11 != 0) goto La2
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r11 = r10.performanceRecords     // Catch: java.lang.Throwable -> La4
            r11.removeAll(r3)     // Catch: java.lang.Throwable -> La4
            boolean r11 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> La4
            if (r11 == 0) goto La2
            java.lang.String r11 = "WebPerformanceHolder"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La4
            r12.<init>()     // Catch: java.lang.Throwable -> La4
            java.lang.String r13 = "[\u5220\u9664]"
            r12.append(r13)     // Catch: java.lang.Throwable -> La4
            int r13 = r3.size()     // Catch: java.lang.Throwable -> La4
            r12.append(r13)     // Catch: java.lang.Throwable -> La4
            java.lang.String r13 = "\u6761\u6027\u80fd\u6570\u636e\uff0c\u76ee\u524dsize = "
            r12.append(r13)     // Catch: java.lang.Throwable -> La4
            java.util.List<com.jingdong.common.web.managers.WebPerformance> r13 = r10.performanceRecords     // Catch: java.lang.Throwable -> La4
            int r13 = r13.size()     // Catch: java.lang.Throwable -> La4
            r12.append(r13)     // Catch: java.lang.Throwable -> La4
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> La4
            com.jingdong.sdk.oklog.OKLog.d(r11, r12)     // Catch: java.lang.Throwable -> La4
        La2:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
            return
        La4:
            r11 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La4
            throw r11
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.managers.WebPerformanceHolder.reportRecordBefore(com.jingdong.common.web.managers.WebPerformance, boolean, boolean):void");
    }
}
