package com.jingdong.common.web.managers;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.xconsole.XLog;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.util.WebLogHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;
import jpbury.t;

/* loaded from: classes12.dex */
public class WebWhiteScreenHolder {
    public static final String BIZ_TYPE = "businessType";
    public static final String CAN_PASS_GENTOKEN = "canPassGentoken";
    public static final String ERR_CODE = "908";
    public static final String ERR_TYPE = "2";
    public static final String GENTOKEN_CANCEL = "gentokenCancel";
    public static final String GENTOKEN_ERROR = "gentokenErr";
    public static final String GENTOKEN_ERR_MSG = "gentokenErrMsg";
    public static final String GENTOKEN_FALSE = "gentokenFalse";
    public static final String GENTOKEN_START = "gentokenStart";
    public static final String GENTOKEN_SUCCESS = "gentokenSuccess";
    public static final String HTML_PRE_DOWNLOAD_STATUS = "htmlPreDownload";
    public static final String INIT_START = "initStart";
    public static final String INIT_START_URL = "initStartUrl";
    public static final String IS_FROM_M_INSIDE = "isFromMInside";
    public static final String LAST_PAGE = "lastPage";
    public static final String M2NATIVE = "M2Native";
    public static final String OPEN_APP_ACTIVITY_REFERER = "openAppActivityReferer";
    public static final String PAGE_COMMIT = "pageCommit";
    public static final String PAGE_DETECTED_BLANKSCREEN = "PageDetectedBlankScreen";
    public static final String PAGE_DWELL_TIME = "pageDwellTime";
    public static final String PAGE_FAIL = "pageFail";
    public static final String PAGE_FINISH = "pageFinish";
    public static final String PAGE_START = "pageStart";
    public static final int PAGE_START_COUNT = 5;
    public static final String PRELOAD_STATUS = "preloadStatus";
    private static final String TAG = "WebWhiteScreenHolder";
    public static String WEBVIEW_WHITESCREEN_ERROR = "WebView_WhiteScreen_Error";
    public static final String WEB_ERR = "webErr";
    public static final String WEB_ERR_MSG = "webErrMsg";
    public static final String WHITE_RENDER_RATIO_EXCEPTION = "whiteRenderRatioException";
    public static final String X_RENDER = "xrender";
    private JDJSONObject data;
    private boolean firstEvent;
    private String jsLog;
    private int pageStartCount;
    private StringBuilder overrideUrlChain = new StringBuilder();
    private StringBuilder loadEventChain = new StringBuilder();

    public WebWhiteScreenHolder() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        this.data = jDJSONObject;
        this.firstEvent = true;
        jDJSONObject.put("businessType", (Object) "0");
        this.data.put("preloadStatus", (Object) "0");
        this.data.put("htmlPreDownload", (Object) "0");
        this.data.put("lastPage", (Object) HybridSDK.getLastPageName());
    }

    private String checkErrMsg() {
        boolean isEmpty = TextUtils.isEmpty(this.data.getString(M2NATIVE));
        String str = WEB_ERR;
        if (!isEmpty) {
            str = "m2Native";
        } else if (!TextUtils.isEmpty(this.data.getString(GENTOKEN_ERR_MSG))) {
            str = GENTOKEN_ERROR;
        } else if (TextUtils.isEmpty(this.data.getString(WEB_ERR))) {
            if (this.pageStartCount > 5) {
                str = "loadErr";
            } else {
                str = !TextUtils.isEmpty(this.data.getString(this.jsLog)) ? "jsErr" : "unknown";
            }
        }
        xLog("errMsg \uff1a" + str);
        return str;
    }

    private String getDataFromPerformance(JDWebView jDWebView, String str) {
        WebPerformance currentRecord = (jDWebView == null || jDWebView.getPerformanceHolder() == null) ? null : jDWebView.getPerformanceHolder().getCurrentRecord();
        return currentRecord != null ? currentRecord.getData(str) : "";
    }

    private String getErrMsgInfo() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.data.optString(GENTOKEN_ERR_MSG))) {
            sb.append(this.data.optString(GENTOKEN_ERR_MSG));
        }
        if (!TextUtils.isEmpty(this.data.optString(WEB_ERR))) {
            if (sb.length() > 0) {
                sb.append("|||");
            }
            sb.append(this.data.optString(WEB_ERR));
        }
        if (!TextUtils.isEmpty(this.data.optString(WEB_ERR_MSG))) {
            if (sb.length() > 0) {
                sb.append("|||");
            }
            sb.append(this.data.optString(WEB_ERR_MSG));
        }
        if (!TextUtils.isEmpty(this.jsLog)) {
            if (sb.length() > 0) {
                sb.append("|||");
            }
            sb.append(this.jsLog);
        }
        if (TextUtils.isEmpty(this.data.optString("pageFinish"))) {
            if (sb.length() > 0) {
                sb.append("|||");
            }
            sb.append("pageFinishException");
        }
        return sb.toString();
    }

    private String getPageInitS() {
        String optString = this.data.optString("initStart");
        long parseLong = !TextUtils.isEmpty(optString) ? Long.parseLong(optString) : 0L;
        String optString2 = this.data.optString("pageFinish");
        return String.valueOf((TextUtils.isEmpty(optString2) ? 0L : Long.parseLong(optString2)) - parseLong);
    }

    public static void sendException(Throwable th, int i2) {
        if (th == null || TextUtils.isEmpty(th.getMessage())) {
            return;
        }
        xLog(th.getMessage());
        HashMap hashMap = new HashMap();
        hashMap.put("function", WHITE_RENDER_RATIO_EXCEPTION);
        hashMap.put("errCode", ERR_CODE);
        hashMap.put(PerformanceManager.ERR_TYPE, "2");
        hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
        hashMap.put("reserved1", th.getMessage());
        hashMap.put("reserved2", String.valueOf(i2));
        ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
    }

    public static void xLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (Log.D || WebLogHelper.showXLog) {
            XLog.d(TAG, null, str, TAG);
        }
    }

    public void addLoadEvent(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!this.firstEvent) {
            this.loadEventChain.append("-");
        }
        this.firstEvent = false;
        this.loadEventChain.append(str);
    }

    public void addOverrideUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.overrideUrlChain.append(HybridUrlUtils.excludeQuery(str));
                this.overrideUrlChain.append("->");
                return;
            } catch (Exception e2) {
                this.overrideUrlChain.append(e2.getMessage());
                return;
            }
        }
        this.overrideUrlChain.append(DYConstants.DY_NULL_STR);
    }

    public void addPageStartCount() {
        this.pageStartCount++;
    }

    public void appendData(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        this.data.put(str, (Object) str2);
    }

    public String getPageDwellTime() {
        String optString = this.data.optString("initStart");
        return String.valueOf(System.currentTimeMillis() - (!TextUtils.isEmpty(optString) ? Long.parseLong(optString) : 0L));
    }

    public void sendMta(JDWebView jDWebView) {
        if (jDWebView == null) {
            return;
        }
        try {
            String dataFromPerformance = getDataFromPerformance(jDWebView, WebPerfManager.FCP);
            String dataFromPerformance2 = getDataFromPerformance(jDWebView, WebPerfManager.LCP);
            String str = "0";
            this.data.put(WebPerfManager.FCP, TextUtils.isEmpty(dataFromPerformance) ? "0" : "1");
            JDJSONObject jDJSONObject = this.data;
            if (!TextUtils.isEmpty(dataFromPerformance2)) {
                str = "1";
            }
            jDJSONObject.put(WebPerfManager.LCP, (Object) str);
            this.data.put("pageFinishTime", (Object) getPageInitS());
            this.data.put("webLoadStatus", (Object) String.valueOf(jDWebView.isPageLoaded()));
            this.data.put("loadEventChain", (Object) this.loadEventChain.toString());
            this.data.remove("pageStart");
            this.data.remove("initStart");
            HashMap hashMap = new HashMap();
            hashMap.put("function", WEBVIEW_WHITESCREEN_ERROR);
            hashMap.put("errCode", ERR_CODE);
            Object[] objArr = new Object[1];
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            objArr[0] = Double.valueOf(currentTimeMillis / 1000.0d);
            hashMap.put("occurTime", String.format("%.6f", objArr));
            hashMap.put("url", jDWebView.getFinalUrl());
            hashMap.put("errMsg", checkErrMsg());
            hashMap.put(t.f20145j, getErrMsgInfo());
            this.data.remove("pageFinish");
            hashMap.put("reserved1", this.data.toJSONString());
            hashMap.put("reserved2", this.overrideUrlChain.toString());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
            xLog("whiteScreen data \uff1a" + hashMap);
        } catch (Throwable th) {
            xLog("whiteScreen Throwable e\uff1a" + th);
        }
    }

    public void setJsLog(String str) {
        this.jsLog = str;
    }
}
