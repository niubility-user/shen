package com.jingdong.common.web.xrender;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.util.StringUtils;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import de.greenrobot.event.EventBus;
import java.net.URLDecoder;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class XRenderManager {
    private static final String JS_DISPATCH_EVENT = "try{;(function(){var event = new CustomEvent('%s', {'detail': %s}); window.dispatchEvent(event);})();} catch (e) {console && console.error('ERROR in dispatchEvent:'+eventName, e);}";
    public static final String TAG = "XRenderManager";
    private String businessUrl;
    private String isClass;
    private boolean isConsumeXRender;
    private boolean isSendClickEvent;
    private HybridSDK.PreRenderModule module;
    private boolean performanceReported;
    private long reserveTime;
    private long startTime;
    private String url;
    private JDWebView webView;
    private XRenderWebUiBinder xUiBinder;
    private int versionCode = -1;
    private int shareVersionCode = -1;
    private boolean hasAttached = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b() {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null) {
            if (!jDWebView.isAttached()) {
                this.webView.onDestory();
                return;
            } else {
                XRender.Log("webview\u5df2\u4e0a\u5c4f\uff0c\u4e0d\u79fb\u9664", this.url);
                return;
            }
        }
        ExceptionReporter.reportWebViewCommonErrorNoIp("XRender", this.url, "", "JDWebView = null ");
    }

    public static String addQueryParams(String str, String str2) {
        Uri parse;
        Uri parse2;
        Uri.Builder buildUpon;
        Set<String> queryParameterNames;
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2) || (parse = Uri.parse(str)) == null || (parse2 = Uri.parse(str2)) == null || (buildUpon = parse2.buildUpon()) == null || (queryParameterNames = parse.getQueryParameterNames()) == null) {
            return "";
        }
        for (String str3 : queryParameterNames) {
            if (TextUtils.isEmpty(parse2.getQueryParameter(str3))) {
                buildUpon.appendQueryParameter(str3, parse.getQueryParameter(str3));
            }
        }
        return buildUpon.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(JDJSONObject jDJSONObject) {
        XRenderUtils.dispatchJSEvent(this.webView, WebPerfManager.XRENDER_READY, jDJSONObject);
    }

    private void checkImmersive(boolean z) {
        String finalUrl;
        String str;
        if (this.webView != null) {
            if (SwitchQueryFetcher.getSwitchBooleanValue("checkImmersiveUseBusinessUrl", false)) {
                XRender.Log("checkImmersiveUseBusinessUrl \u6253\u5f00\uff0c\u8bbe\u7f6e\u6c89\u6d78\u5f0f\u4ece\u4e1a\u52a1url\u91cc\u83b7\u53d6transparent\u53c2\u6570");
                finalUrl = this.businessUrl;
            } else {
                XRender.Log("checkImmersiveUseBusinessUrl \u5173\u95ed\uff0c\u8bbe\u7f6e\u6c89\u6d78\u5f0f\u4ece\u9884\u6e32\u67d3url\u91cc\u83b7\u53d6transparent\u53c2\u6570");
                finalUrl = this.webView.getFinalUrl();
            }
            Pattern compile = Pattern.compile("/cgi-bin/app/appjmp");
            try {
                str = URLDecoder.decode(finalUrl, "UTF-8");
            } catch (Exception e2) {
                e2.printStackTrace();
                str = "";
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                Uri parse = Uri.parse(str);
                if (compile.matcher(str).find()) {
                    return;
                }
                String queryParameter = parse.getQueryParameter("transparent");
                Log.d(TAG, "transparentStr:" + queryParameter + " isOncreate:" + z + "   url:" + str);
                if ("1".equals(queryParameter)) {
                    this.webView.setImmersive(true);
                } else if (!z) {
                    this.webView.setImmersive(false);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void clearData() {
        XRender.Log("\u9500\u6bc1\u6570\u636e");
        XRender.getInstance().removeCachedWebViewMap(this.url);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.web.xrender.g
            @Override // java.lang.Runnable
            public final void run() {
                XRenderManager.this.b();
            }
        });
    }

    public void executeJsBridge() {
        XRenderWebUiBinder xRenderWebUiBinder = this.xUiBinder;
        if (xRenderWebUiBinder != null) {
            xRenderWebUiBinder.executeCacheJsBridgeInterface();
        }
    }

    public String getBusinessUrl() {
        return this.businessUrl;
    }

    public String getIsClass() {
        return this.isClass;
    }

    public HybridSDK.PreRenderModule getModule() {
        return this.module;
    }

    public int getShareVersionCode() {
        return this.shareVersionCode;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public XRenderWebUiBinder getXUiBinder() {
        XRenderWebUiBinder xRenderWebUiBinder = this.xUiBinder;
        return xRenderWebUiBinder == null ? new XRenderWebUiBinder() : xRenderWebUiBinder;
    }

    public void init(JDWebView jDWebView) {
        this.webView = jDWebView;
        XRenderWebUiBinder xUiBinder = getXUiBinder();
        this.xUiBinder = xUiBinder;
        xUiBinder.addJs(jDWebView);
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    public boolean isConsumeXRender() {
        return this.isConsumeXRender;
    }

    public boolean isPerformanceReported() {
        return this.performanceReported;
    }

    public boolean isSendClickEvent() {
        return this.isSendClickEvent;
    }

    public boolean isTimeValid() {
        return (System.currentTimeMillis() - this.startTime) / 1000 <= this.reserveTime;
    }

    public void onAttach(boolean z) {
        if (this.hasAttached) {
            return;
        }
        if (!SwitchQueryFetcher.getSwitchBooleanValue("setTopBarPaddingSwitch", false)) {
            XRender.Log("setTopBarPaddingSwitch \u6253\u5f00");
            JDWebView jDWebView = this.webView;
            if (jDWebView != null && jDWebView.getShallCheckImmersiveOnInit()) {
                checkImmersive(false);
            }
        }
        this.hasAttached = true;
        if (z) {
            sendReadyEvent(this.businessUrl);
            XRender.Log("onAttach \u5f00\u59cb\u6267\u884c\u7f13\u5b58\u7684js\u6865");
            this.xUiBinder.executeCacheJsBridgeInterface();
        }
    }

    public void onDestroy() {
        this.xUiBinder = null;
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof LoginEvent) {
            if (baseEvent.getType() == LoginEvent.TYPE_LOGIN || baseEvent.getType() == LoginEvent.TYPE_LOGOUT) {
                onReceivedError("\u767b\u5f55\u72b6\u6001\u53d1\u751f\u53d8\u5316", "1");
            }
        }
    }

    public void onReceivedError(String str, String str2) {
        XRender.Log(str);
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("url", (Object) this.url);
        jDJSONObject.put("destroy_type", (Object) str2);
        XRender.sendExposure("xrender_destroy", jDJSONObject.toJSONString());
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && jDWebView.getPerformanceHolder() != null) {
            this.webView.getPerformanceHolder().appendExtraToCurrRecord("xrender_destroy", str2);
        }
        clearData();
        if ("1".equals(str2)) {
            ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", this.url, "1", "\u767b\u5f55\u6001\u53d8\u5316");
        } else {
            ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", this.url, "3", "\u9884\u6e32\u67d3\u5931\u8d25");
        }
    }

    public void onResume() {
    }

    public void recordTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void sendReadyEvent(String str) {
        sendReadyEvent(str, null);
    }

    public void setBusinessUrl(String str) {
        this.businessUrl = str;
    }

    public void setConsumeXRender(boolean z) {
        this.isConsumeXRender = z;
    }

    public void setIsClass(String str) {
        this.isClass = str;
    }

    public void setModule(HybridSDK.PreRenderModule preRenderModule) {
        this.module = preRenderModule;
    }

    public void setPerformanceReported(boolean z) {
        this.performanceReported = z;
    }

    public void setReserveTime(long j2) {
        this.reserveTime = j2;
    }

    public void setSendClickEvent(boolean z) {
        this.isSendClickEvent = z;
    }

    public void setShareVersionCode(int i2) {
        this.shareVersionCode = i2;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVersionCode(int i2) {
        this.versionCode = i2;
    }

    public void sendReadyEvent(String str, String str2) {
        if (this.webView != null) {
            XRender.Log("\u5f00\u59cb\u53d1\u9001xrenderReady \u4e8b\u4ef6\u53caJS\u6865\u5185\u5bb9\u8d4b\u503c");
            final JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("url", (Object) addQueryParams(this.webView.getFinalUrl(), str));
            XRender.Log("\u7ed9xRenderReady JS\u6865 url  = " + str);
            if (str2 != null) {
                jDJSONObject.put(WebPerfManager.H5_DATA, (Object) str2);
            }
            String string2JsStr = XRenderUtils.shouldEncodeXRenderJsonData() ? StringUtils.string2JsStr(jDJSONObject.toJSONString()) : jDJSONObject.toJSONString();
            XRender.Log("xrenderReady params = " + string2JsStr);
            XRenderUtils.dispatchJSEvent(this.webView, WebPerfManager.XRENDER_READY, jDJSONObject);
            if (this.xUiBinder.getxRenderJS() != null) {
                XRender.Log("\u7ed9xRenderReady JS\u6865 \u8fd4\u56de\u5185\u5bb9\u8d4b\u503c  = " + string2JsStr);
                this.xUiBinder.getxRenderJS().setxRenderReadyParams(string2JsStr);
            }
            if (SwitchQueryFetcher.getSwitchBooleanValue("isXrenderJsEventFix", false)) {
                XRender.Log("repeatSendReadyEvent\u5f00\u5173\uff1atrue\uff0c\u91cd\u590d\u53d1\u9001\u4e00\u6b21");
                this.webView.getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.web.xrender.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        XRenderManager.this.d(jDJSONObject);
                    }
                }, 50L);
            }
        }
    }
}
