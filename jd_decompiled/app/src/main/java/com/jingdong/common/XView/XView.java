package com.jingdong.common.XView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.libs.hybrid.HybridGenTokenSupporter;
import com.jd.libs.hybrid.HybridOfflineLoader;
import com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.preload.IPreloadParamGetter;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import com.jd.libs.xconsole.XLog;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.WebLoginUtil;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.CashierGenPayIdDomainCollector;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.ui.IJdWebViewUi;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.WebViewClientListener;
import com.jingdong.common.web.uilistener.WebViewInterceptUrlAdapter;
import com.jingdong.common.web.util.WebLogHelper;
import com.jingdong.common.web.util.WebSwitchQueryFetcher;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.widget.CommonNavigator;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.tencent.smtt.sdk.WebView;
import java.text.DecimalFormat;
import jd.wjweblogin.common.listener.WJReqWebCookieCallBack;
import jd.wjweblogin.model.WJErrorResult;
import jd.wjweblogin.model.WJFailResult;

/* loaded from: classes5.dex */
public class XView extends FrameLayout implements IJdWebViewUi, IXView, IXViewUnite {
    private static final String LOGIN_PATH = "/user/login.action";
    public static final String MWEBVIEW1_START_EXPO = "XView1_Start_Expo";
    public static final String XVIEW1_START_EXPOSWITCH = "xview1StartExpoSwitch";
    private static final String XVIEW_SCHEME = "xview";
    private final String TAG;
    private boolean canChangeParentAccessibility;
    private final int closeBtnHeight;
    private final int closeBtnWidth;
    private int closeWay;
    DecimalFormat df;
    private boolean genTokenFinished;
    private double genToken_end;
    private double genToken_start;
    private long initTimeStart;
    private boolean isStart;
    private boolean isXViewReady;
    protected BaseActivity mActivity;
    private XViewCallBack mCallBack;
    private SimpleDraweeView mCloseBtn;
    private int mCloseBtnDelayTimeInSecond;
    private ICloseIntercept mCloseIntercept;
    private Handler mHandler;
    private boolean mIsNeedConfigCloseButton;
    protected JDWebView mJdWebView;
    private CommonNavigator.NaviClickAdaper mNaviListener;
    protected PAGESTATE mPageState;
    private ViewGroup mParentView;
    private float mPercentX;
    private float mPercentY;
    private String mReturnUrl;
    protected XViewEntity mXViewEntity;
    private boolean needDisplayXView;
    private boolean noTouch;
    private String offlineId;
    private boolean pageFinished;
    private boolean parentAccessibilityChanged;
    private String preloadId;
    private boolean shouldXViewDisplay;
    private WebEntity webEntity;
    private IWebUiBinder webUiBinder;
    private double webviewLoad_end;
    private double webviewLoad_start;

    /* loaded from: classes5.dex */
    public interface ICloseIntercept {
        boolean intercept();
    }

    /* loaded from: classes5.dex */
    public enum PAGESTATE {
        STOP,
        RESUME
    }

    /* loaded from: classes5.dex */
    public class XViewInterceptUrlListener extends WebViewInterceptUrlAdapter {
        XViewInterceptUrlListener() {
            XView.this = r1;
        }

        @Override // com.jingdong.common.web.uilistener.WebViewInterceptUrlAdapter, com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
        public boolean interceptOnPageStart(WebView webView, String str) {
            JDWebView jDWebView;
            if (XView.this.checkUrl(str) && (jDWebView = XView.this.mJdWebView) != null) {
                jDWebView.stopLoading();
            }
            return false;
        }

        @Override // com.jingdong.common.web.uilistener.WebViewInterceptUrlAdapter, com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
        public boolean interceptShouldOverrideLoading(WebView webView, String str) {
            if (XView.this.checkUrl(str)) {
                return true;
            }
            XView xView = XView.this;
            XViewEntity xViewEntity = xView.mXViewEntity;
            return xViewEntity != null && xViewEntity.subPageToWebActivity && xView.secondLevelPageCheck(str, webView.getHitTestResult());
        }
    }

    /* loaded from: classes5.dex */
    public class XViewWebClientListener implements WebViewClientListener {
        private boolean isError = false;

        XViewWebClientListener() {
            XView.this = r1;
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageFinished(WebView webView, String str) {
            Log.d(XView.this.TAG, "page load finished:" + str);
            if ("about:blank".equals(str) || webView.getProgress() < 100 || this.isError) {
                return;
            }
            XView xView = XView.this;
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            xView.webviewLoad_end = currentTimeMillis / 1000.0d;
            XView.this.pageFinished = true;
            XView.this.ready();
            if (PerformanceManager.getInstance().shouldReport()) {
                PerformanceManager.getInstance().appendData("mloadType", XView.XVIEW_SCHEME);
            }
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.d(XView.this.TAG, "page load start:" + str);
            if ("about:blank".equals(str)) {
                return;
            }
            XView xView = XView.this;
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            xView.webviewLoad_start = currentTimeMillis / 1000.0d;
            if (XView.this.mCallBack != null) {
                XView.this.mCallBack.onXViewLoadingUrl(str);
            }
            this.isError = false;
            XView.this.pageFinished = false;
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onReceivedError(WebView webView, final int i2, String str, String str2) {
            Log.d(XView.this.TAG, "page load error:" + i2);
            this.isError = true;
            XView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.XView.XView.XViewWebClientListener.1
                {
                    XViewWebClientListener.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (XView.this.mCallBack != null) {
                        XView.this.mCallBack.onError(i2);
                    }
                    if (XView.this.parentViewAutoAddOrRemove()) {
                        XView.this.closeXView();
                    }
                }
            });
            XView xView = XView.this;
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            xView.webviewLoad_end = currentTimeMillis / 1000.0d;
            XView.this.pageFinished = true;
            if (PerformanceManager.getInstance().shouldReport()) {
                PerformanceManager.getInstance().appendData("mloadType", XView.XVIEW_SCHEME);
            }
            ExceptionReporter.reportWebPageError("XView_Error", "xview page load error", str2, i2 + "");
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void shouldOverrideUrlLoading(WebView webView, String str) {
        }
    }

    public XView(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.webEntity = new WebEntity();
        this.mHandler = new Handler(Looper.getMainLooper());
        int dip2px = DPIUtil.dip2px(30.0f);
        this.closeBtnWidth = dip2px;
        this.closeBtnHeight = dip2px;
        this.isXViewReady = false;
        this.shouldXViewDisplay = false;
        this.needDisplayXView = false;
        this.noTouch = false;
        this.mPageState = PAGESTATE.RESUME;
        this.initTimeStart = System.currentTimeMillis();
        this.df = new DecimalFormat("#.###");
        this.pageFinished = true;
        this.genTokenFinished = true;
        this.closeWay = 0;
        this.mCloseBtnDelayTimeInSecond = 0;
        this.canChangeParentAccessibility = true;
        this.parentAccessibilityChanged = false;
        this.mIsNeedConfigCloseButton = true;
        this.offlineId = null;
        this.preloadId = null;
        init(context);
    }

    public boolean checkUrl(String str) {
        Uri parse = Uri.parse(str);
        if (Log.D) {
            Log.d(this.TAG, "start checkUrl :" + parse);
        }
        CashierGenPayIdDomainCollector.getInstance().preHandHistoryUrl(this.mJdWebView, str);
        if (WebViewHelper.isJdPayMatch(this.mActivity, str)) {
            return true;
        }
        String scheme = parse.getScheme();
        if (JumpUtil.isJdScheme(scheme)) {
            openAppJump(str, parse);
            return true;
        } else if ("/user/login.action".equals(parse.getPath())) {
            this.mReturnUrl = parse.getQueryParameter("returnurl");
            Log.d(this.TAG, "returnUrl:" + this.mReturnUrl);
            if (LoginUserBase.hasLogin()) {
                loginStateSynchro(this.mReturnUrl);
                return true;
            }
            loginCallback(this.mReturnUrl);
            return true;
        } else if (scheme.toLowerCase().startsWith(XVIEW_SCHEME)) {
            XViewRequest xViewRequest = new XViewRequest();
            xViewRequest.host = parse.getHost();
            xViewRequest.requestParams = parse.getQueryParameter("request");
            Log.d(this.TAG, "XView scheme\uff1a" + xViewRequest.toString());
            XViewCallBack xViewCallBack = this.mCallBack;
            if (xViewCallBack != null) {
                xViewCallBack.onXViewRequest(xViewRequest);
            }
            return true;
        } else {
            return false;
        }
    }

    private HybridOfflineLoader getHybridOfflineLoader() {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            return jDWebView.getHybridOfflineLoader();
        }
        return null;
    }

    private boolean hasHybridAvailableFiles() {
        HybridOfflineLoader hybridOfflineLoader;
        JDWebView jDWebView = this.mJdWebView;
        return (jDWebView == null || (hybridOfflineLoader = jDWebView.getHybridOfflineLoader()) == null || !hybridOfflineLoader.hasOfflineFiles()) ? false : true;
    }

    private boolean isHttpOrHttps(String str) {
        return "http".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str);
    }

    private void loadXCache() {
        if (this.offlineId == null || this.preloadId == null) {
            Log.d(this.TAG, "createXView: try to start hybrid loading for url: " + this.mXViewEntity.url);
            this.offlineId = WebOfflineLoaderManager.createOfflineLoader(this.mXViewEntity.url, new IPreDownloadParamGetter.PreDownloadParamGetter() { // from class: com.jingdong.common.XView.XView.5
                {
                    XView.this = this;
                }

                @Override // com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter.PreDownloadParamGetter, com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter
                public String getDownloadUrl(OfflineFiles offlineFiles, String str) {
                    if (offlineFiles == null) {
                        return null;
                    }
                    return str;
                }
            });
            this.preloadId = WebPreLoadHelper.preLoad(this.mXViewEntity.url, new IPreloadParamGetter.PreloadParamGetter() { // from class: com.jingdong.common.XView.XView.6
                {
                    XView.this = this;
                }

                @Override // com.jd.libs.hybrid.preload.IPreloadParamGetter.PreloadParamGetter, com.jd.libs.hybrid.preload.IPreloadParamGetter
                public boolean allowPreloadData(PreloadInfoEntity preloadInfoEntity) {
                    return preloadInfoEntity != null;
                }
            });
            if (TextUtils.isEmpty(this.offlineId) && TextUtils.isEmpty(this.preloadId)) {
                return;
            }
            startHybrid(this.offlineId, this.preloadId);
        }
    }

    private void loginCallback(final String str) {
        if (Log.D) {
            Log.d(this.TAG, "loginCallback() returnUrl-->> " + str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DeepLinkLoginHelper.startLoginActivity(this.mActivity, null, new ILogin() { // from class: com.jingdong.common.XView.XView.11
            {
                XView.this = this;
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str2) {
                if ("xviewLogin".equals(str2)) {
                    XView.this.loginStateSynchro(str);
                }
            }
        }, "xviewLogin");
    }

    public void loginStateSynchro(String str) {
        if (WebSwitchQueryFetcher.newGentoken() && WebSwitchQueryFetcher.xViewNewGentoken()) {
            newGentoken(str, false);
        } else {
            gentokenAndLoadUrl(str, false);
        }
    }

    private void newGentoken(final String str, final boolean z) {
        JDWebView jDWebView;
        if (this.mJdWebView == null) {
            return;
        }
        if (Log.D || WebLogHelper.showXLog) {
            XLog.d(this.TAG, null, "[newGentoken][xview]  start...", "webview");
            XLog.d(this.TAG, null, "[newGentoken][xview]  checkCanPassGentoken = " + z, "webview");
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        if (z && (jDWebView = this.mJdWebView) != null && jDWebView.isHybridPassGenToken()) {
            uRLParamMap.put("webHybridHasOffConfig", "1");
        }
        if (z && this.mJdWebView != null && WebUtils.canPassGentoken(uRLParamMap)) {
            String loadUrlIgnoreGentoken = WebUtils.getLoadUrlIgnoreGentoken(uRLParamMap);
            if (Log.D || WebLogHelper.showXLog) {
                XLog.d(this.TAG, null, "[newGentoken][xview] Can pass gentoken, url: " + loadUrlIgnoreGentoken, "webview");
            }
            this.mJdWebView.loadUrl(loadUrlIgnoreGentoken);
            return;
        }
        WebLoginUtil.getWJLoginHelper().reqWebCookie(false, str, new WJReqWebCookieCallBack() { // from class: com.jingdong.common.XView.XView.8
            {
                XView.this = this;
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onFail(WJFailResult wJFailResult) {
                JDWebView jDWebView2 = XView.this.mJdWebView;
                if (jDWebView2 != null) {
                    if (Log.D || WebLogHelper.showXLog) {
                        jDWebView2.xLogD("[newGentoken] -> reqWebLogin onFail code = " + wJFailResult.getErrorCode());
                    }
                    XView.this.gentokenAndLoadUrl(str, z);
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onHttpTaskError(HttpError httpError) {
                JDWebView jDWebView2 = XView.this.mJdWebView;
                if (jDWebView2 != null) {
                    if (Log.D || WebLogHelper.showXLog) {
                        jDWebView2.xLogD("[newGentoken] -> reqWebLogin onHttpTaskError code = " + httpError.getErrorCode());
                    }
                    XView.this.gentokenAndLoadUrl(str, z);
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onLocalError(WJErrorResult wJErrorResult) {
                JDWebView jDWebView2 = XView.this.mJdWebView;
                if (jDWebView2 != null) {
                    if (Log.D || WebLogHelper.showXLog) {
                        jDWebView2.xLogD("[newGentoken] -> reqWebLogin onLocalError code = " + wJErrorResult.getErrorCode());
                    }
                    XView.this.gentokenAndLoadUrl(str, z);
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onSuccess(String str2) {
                JDWebView jDWebView2 = XView.this.mJdWebView;
                if (jDWebView2 != null) {
                    if (Log.D || WebLogHelper.showXLog) {
                        jDWebView2.xLogD("[newGentoken][xview] -> reqWebLogin onSuccess url=" + str);
                    }
                    XView.this.mJdWebView.loadUrl(str);
                }
            }

            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
            public void onWithinTheValidity(String str2) {
                JDWebView jDWebView2 = XView.this.mJdWebView;
                if (jDWebView2 != null) {
                    if (Log.D || WebLogHelper.showXLog) {
                        jDWebView2.xLogD("[newGentoken][xview] -> reqWebLogin onWithinTheValidity url=" + str);
                    }
                    XView.this.mJdWebView.loadUrl(str);
                }
            }
        });
    }

    public void ready() {
        if (!this.isStart || this.mXViewEntity == null) {
            return;
        }
        Log.d(this.TAG, "XView ready,gentoken cost-->" + (this.genToken_end - this.genToken_start) + ",page load cost-->" + (this.webviewLoad_end - this.webviewLoad_start));
        this.isXViewReady = true;
        XViewCallBack xViewCallBack = this.mCallBack;
        if (xViewCallBack != null) {
            xViewCallBack.onXViewReady();
        }
        if (parentViewAutoAddOrRemove()) {
            if (this.mXViewEntity.needAutoDisplay) {
                PAGESTATE pagestate = this.mPageState;
                if (pagestate == PAGESTATE.RESUME) {
                    displayXView();
                    return;
                } else if (pagestate == PAGESTATE.STOP) {
                    this.needDisplayXView = true;
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        displayXView();
    }

    public boolean secondLevelPageCheck(String str, WebView.HitTestResult hitTestResult) {
        if (!this.isXViewReady || hitTestResult == null || TextUtils.isEmpty(hitTestResult.getExtra())) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        DeepLinkMHelper.startWebActivity(this.mActivity, bundle);
        return true;
    }

    @Override // com.jingdong.common.XView.IXView
    @Deprecated
    public void autoShowXView() {
        startXView();
    }

    @Override // com.jingdong.common.XView.IXView
    public void closeXView() {
        String str;
        String str2;
        if (this.isStart) {
            Log.d(this.TAG, "closeXView, way = " + this.closeWay);
            setVisibility(8);
            this.isXViewReady = false;
            this.shouldXViewDisplay = false;
            this.needDisplayXView = false;
            this.isStart = false;
            ViewGroup viewGroup = this.mParentView;
            if (viewGroup != null) {
                viewGroup.removeView(this);
                if (this.parentAccessibilityChanged && BaseInfo.getAndroidSDKVersion() >= 16) {
                    this.parentAccessibilityChanged = false;
                    for (int i2 = 0; i2 < this.mParentView.getChildCount(); i2++) {
                        if (this.mParentView.getChildAt(i2) != null && this.mParentView.getChildAt(i2) != this) {
                            this.mParentView.getChildAt(i2).setImportantForAccessibility(0);
                        }
                    }
                }
            }
            try {
                if (this.pageFinished) {
                    str = "";
                    str2 = PerformanceManager.LOAD_URL;
                } else {
                    PerformanceManager.getInstance().appendData(PerformanceManager.ERR_TYPE, "user interrupt");
                    PerformanceManager.getInstance().appendData("errMsg", "\u9875\u9762\u672a\u52a0\u8f7d\u5b8c");
                    PerformanceManager.getInstance().appendData("isError", "2");
                    PerformanceManager.getInstance().appendData("mloadType", XVIEW_SCHEME);
                    PerformanceManager performanceManager = PerformanceManager.getInstance();
                    XViewEntity xViewEntity = this.mXViewEntity;
                    performanceManager.appendData(PerformanceManager.LOAD_URL, xViewEntity != null ? xViewEntity.url : "");
                    long currentTimeMillis = System.currentTimeMillis();
                    PerformanceManager performanceManager2 = PerformanceManager.getInstance();
                    str = "";
                    str2 = PerformanceManager.LOAD_URL;
                    performanceManager2.appendData("mloadingTime", String.valueOf(currentTimeMillis - ((long) (this.webviewLoad_start * 1000.0d))));
                    if (this.mJdWebView != null) {
                        PerformanceManager.getInstance().appendData("kernelType", this.mJdWebView.isSystemCoreNotX5() ? "system" : "x5-" + WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication()));
                    }
                    PerformanceManager.getInstance().report();
                }
                if (!this.genTokenFinished) {
                    PerformanceManager.getInstance().appendData(PerformanceManager.ERR_TYPE, "user interrupt");
                    PerformanceManager.getInstance().appendData("errMsg", "gentoken\u672a\u52a0\u8f7d\u5b8c");
                    PerformanceManager.getInstance().appendData("isError", "2");
                    PerformanceManager.getInstance().appendData("mloadType", "x_gentoken");
                    PerformanceManager performanceManager3 = PerformanceManager.getInstance();
                    XViewEntity xViewEntity2 = this.mXViewEntity;
                    performanceManager3.appendData(str2, xViewEntity2 != null ? xViewEntity2.url : str);
                    PerformanceManager.getInstance().appendData("mloadingTime", String.valueOf(System.currentTimeMillis() - ((long) (this.genToken_start * 1000.0d))));
                    PerformanceManager.getInstance().report();
                }
                JDWebView jDWebView = this.mJdWebView;
                if (jDWebView != null) {
                    jDWebView.stopLoading();
                    if (this.mJdWebView.getWebView() != null) {
                        this.mJdWebView.getWebView().loadUrl("about:blank");
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            XViewCallBack xViewCallBack = this.mCallBack;
            if (xViewCallBack != null) {
                xViewCallBack.onXVivewClosed();
            }
        }
    }

    public void closeXViewFromJs() {
        this.closeWay = 1;
        closeXView();
    }

    @Override // com.jingdong.common.XView.IXView
    public void configCloseButton(final String str, final float f2, final float f3) {
        Log.d(this.TAG, "configCloseButton:" + str + "  " + f2 + "  " + f3);
        if (this.mIsNeedConfigCloseButton || !SwitchQueryFetcher.getSwitchBooleanValue("config_close_button", false)) {
            this.mHandler.post(new Runnable() { // from class: com.jingdong.common.XView.XView.7
                {
                    XView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (!TextUtils.isEmpty(str)) {
                        JDImageUtils.loadImage(str, new JDImageLoadingListener() { // from class: com.jingdong.common.XView.XView.7.1
                            {
                                AnonymousClass7.this = this;
                            }

                            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                            public void onLoadingCancelled(String str2, View view) {
                                if (XView.this.mCloseBtn != null) {
                                    XView.this.mCloseBtn.setVisibility(0);
                                }
                            }

                            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                                if (XView.this.mCloseBtn != null) {
                                    XView.this.mCloseBtn.setVisibility(0);
                                    XView.this.mCloseBtn.setImageBitmap(bitmap);
                                }
                            }

                            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                                if (XView.this.mCloseBtn != null) {
                                    XView.this.mCloseBtn.setVisibility(0);
                                    XView.this.mCloseBtn.setImageResource(R.drawable.layer_close);
                                }
                            }

                            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                            public void onLoadingStarted(String str2, View view) {
                                if (XView.this.mCloseBtn != null) {
                                    XView.this.mCloseBtn.setVisibility(4);
                                }
                            }
                        });
                    }
                    float f4 = f2;
                    if (f4 >= 0.0f) {
                        float f5 = f3;
                        if (f5 < 0.0f || f4 > 1.0f || f5 > 1.0f) {
                            return;
                        }
                        XView.this.mPercentX = f4;
                        XView.this.mPercentY = f3;
                        int measuredWidth = XView.this.getMeasuredWidth();
                        int measuredHeight = XView.this.getMeasuredHeight();
                        Log.d(XView.this.TAG, "xview  w:" + measuredWidth + "   h:" + measuredHeight + "   closeBtn w:" + XView.this.closeBtnWidth + "   h:" + XView.this.closeBtnHeight);
                        int i2 = (int) (f2 * ((float) (measuredWidth - XView.this.closeBtnWidth)));
                        int i3 = (int) (f3 * ((float) (measuredHeight - XView.this.closeBtnHeight)));
                        if (XView.this.mCloseBtn != null) {
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(XView.this.closeBtnWidth, XView.this.closeBtnHeight);
                            layoutParams.leftMargin = i2;
                            layoutParams.topMargin = i3;
                            XView.this.mCloseBtn.setLayoutParams(layoutParams);
                            Log.d(XView.this.TAG, "set closeButton postion success:" + i2 + "  " + i3);
                        }
                    }
                }
            });
        }
    }

    public void configXView(ViewGroup viewGroup, XViewEntity xViewEntity, XViewCallBack xViewCallBack) {
        this.mParentView = viewGroup;
        this.mXViewEntity = xViewEntity;
        this.mCallBack = xViewCallBack;
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            if (xViewEntity != null && xViewEntity.needNavi) {
                jDWebView.setTopBarGone(false);
                this.mJdWebView.setMoreBtnVisible(false);
                this.mJdWebView.setTitleBackBtnVisible(false);
                this.mNaviListener = new CommonNavigator.NaviClickAdaper() { // from class: com.jingdong.common.XView.XView.4
                    {
                        XView.this = this;
                    }

                    @Override // com.jingdong.common.widget.CommonNavigator.NaviClickAdaper, com.jingdong.common.widget.NavigatorHolder.NaviListener
                    public void onClickShare() {
                        super.onClickShare();
                        XView.this.showShareDialog();
                    }
                };
                JDWebView jDWebView2 = this.mJdWebView;
                if (jDWebView2 != null && jDWebView2.getNavigatorHolder() != null) {
                    this.mJdWebView.getNavigatorHolder().setNaviListener(this.mNaviListener);
                }
            } else {
                jDWebView.setTopBarGone(true);
            }
        }
        if (this.mCloseBtn != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.closeBtnWidth, this.closeBtnHeight);
            layoutParams.rightMargin = DPIUtil.dip2px(10.0f);
            layoutParams.topMargin = DPIUtil.dip2px(10.0f);
            layoutParams.addRule(11);
            this.mPercentX = 0.0f;
            this.mPercentY = 0.0f;
            this.mCloseBtn.setLayoutParams(layoutParams);
            this.mCloseBtn.setImageResource(com.jingdong.common.R.drawable.xview_close);
        }
        XViewEntity xViewEntity2 = this.mXViewEntity;
        if (xViewEntity2 != null) {
            if (xViewEntity2.isIntercepted && xViewEntity2.needCloseButton) {
                setCloseButtonVisible(0);
            } else {
                setCloseButtonVisible(8);
            }
        }
        loadXCache();
    }

    @Deprecated
    protected boolean dealTransparentBackground() {
        return false;
    }

    public void destroyXView() {
        Log.d(this.TAG, "destroyXView");
        closeXView();
        try {
            JDAppUnite jDAppUnite = (JDAppUnite) this.webUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JDAPPUNITE);
            if (jDAppUnite != null) {
                jDAppUnite.onDestroy();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.setVisibility(8);
            removeView(this.mJdWebView);
            this.mJdWebView.onDestory();
            this.mJdWebView = null;
        }
        this.mParentView = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.noTouch) {
            return true;
        }
        if (!parentViewAutoAddOrRemove()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        XViewEntity xViewEntity = this.mXViewEntity;
        if (xViewEntity != null && xViewEntity.isIntercepted) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (xViewEntity == null || xViewEntity.isIntercepted) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean displayXView() {
        SimpleDraweeView simpleDraweeView;
        XViewEntity xViewEntity;
        Log.d(this.TAG, "displayXView,isXViewReady:" + this.isXViewReady);
        if (this.mXViewEntity != null && this.isXViewReady) {
            this.needDisplayXView = false;
            try {
                WebUnifiedMtaUtil.sendExposureMta(getContext(), "com.jingdong.common.XView.XView.class", "", this.mXViewEntity.url, WebUnifiedMtaUtil.MWEBVIEW_XVIEWEXPO, "");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.canChangeParentAccessibility && (xViewEntity = this.mXViewEntity) != null && xViewEntity.isIntercepted && this.mParentView != null && BaseInfo.getAndroidSDKVersion() >= 19) {
                for (int i2 = 0; i2 < this.mParentView.getChildCount(); i2++) {
                    if (this.mParentView.getChildAt(i2) != null && this.mParentView.getChildAt(i2) != this) {
                        this.mParentView.getChildAt(i2).setImportantForAccessibility(4);
                        this.parentAccessibilityChanged = true;
                    }
                }
            }
            if (this.mCloseBtnDelayTimeInSecond > 0 && (simpleDraweeView = this.mCloseBtn) != null && this.mHandler != null && simpleDraweeView.getVisibility() == 0) {
                this.mCloseBtn.setVisibility(8);
                this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.XView.XView.2
                    {
                        XView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        XView.this.setCloseButtonVisible(0);
                    }
                }, this.mCloseBtnDelayTimeInSecond * 1000);
            }
            setVisibility(0);
            this.shouldXViewDisplay = true;
            execJs("window.XViewShowCallback && window.XViewShowCallback();");
            XViewEntity xViewEntity2 = this.mXViewEntity;
            if (xViewEntity2 != null && xViewEntity2.autoRemoveDelayTime > 0) {
                this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.XView.XView.3
                    {
                        XView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        XView.this.closeWay = 3;
                        XView.this.closeXView();
                    }
                }, this.mXViewEntity.autoRemoveDelayTime);
            }
            XViewCallBack xViewCallBack = this.mCallBack;
            if (xViewCallBack != null) {
                xViewCallBack.onXViewDisplayed();
            }
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.XView.IXView
    public void execJs(String str) {
        try {
            this.mJdWebView.injectJs("javascript:" + str);
        } catch (Exception e2) {
            Log.d(this.TAG, e2.getMessage() + "--->" + this.mJdWebView);
        }
    }

    public void gentokenAndLoadUrl(String str) {
        if (WebSwitchQueryFetcher.newGentoken() && WebSwitchQueryFetcher.xViewNewGentoken()) {
            newGentoken(str, true);
        } else {
            gentokenAndLoadUrl(str, true);
        }
    }

    public int getCloseWay() {
        return this.closeWay;
    }

    protected int getInflateLayoutRes() {
        return R.layout.home_layer;
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public JDWebView getJdWebView() {
        return this.mJdWebView;
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public NavigatorHolder getNaviHolder() {
        return this.mJdWebView.getNavigatorHolder();
    }

    @Override // com.jingdong.common.XView.IXViewUnite
    public void getOpenDoorVideoPlayerState(String str) {
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public WebEntity getWebEntity() {
        return this.webEntity;
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public IWebUiBinder getWebUiBinder() {
        if (this.webUiBinder == null) {
            this.webUiBinder = new XViewUiBinder();
        }
        return this.webUiBinder;
    }

    protected void init(Context context) {
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.mActivity = baseActivity;
            try {
                LayoutInflater.from(baseActivity).inflate(getInflateLayoutRes(), this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mJdWebView = (JDWebView) findViewById(R.id.webview);
            this.mCloseBtn = (SimpleDraweeView) findViewById(R.id.close);
            JDWebView jDWebView = this.mJdWebView;
            if (jDWebView != null) {
                jDWebView.setUseCache(true);
                this.mJdWebView.setTopBarGone(true);
                this.mJdWebView.setNeedShowProgress(false);
                if (!dealTransparentBackground() && this.mJdWebView.getWebView() != null) {
                    this.mJdWebView.getWebView().setBackgroundColor(0);
                    this.mJdWebView.getWebView().setVerticalScrollBarEnabled(false);
                    if (this.mJdWebView.getWebView().getX5WebViewExtension() != null) {
                        this.mJdWebView.getWebView().getX5WebViewExtension().setScrollBarFadingEnabled(false);
                    }
                }
                this.mJdWebView.setOverScrollMode(2);
                IWebUiBinder webUiBinder = getWebUiBinder();
                this.webUiBinder = webUiBinder;
                webUiBinder.bindUi(this);
                this.mJdWebView.setWebViewClientListener(new XViewWebClientListener());
                this.mJdWebView.setWebViewInterceptUrlListener(new XViewInterceptUrlListener());
                SimpleDraweeView simpleDraweeView = this.mCloseBtn;
                if (simpleDraweeView != null) {
                    simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView.XView.1
                        {
                            XView.this = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (XView.this.mCloseIntercept == null || !XView.this.mCloseIntercept.intercept()) {
                                XView.this.closeWay = 2;
                                XView.this.closeXView();
                            }
                            if (XView.this.mCallBack != null) {
                                XView.this.mCallBack.onCloseButtonClicked();
                            }
                            if (XView.this.webUiBinder == null || XView.this.webUiBinder.getBaseActivity() == null) {
                                return;
                            }
                            BaseActivity baseActivity2 = XView.this.webUiBinder.getBaseActivity();
                            XViewEntity xViewEntity = XView.this.mXViewEntity;
                            JDMtaUtils.onClick(baseActivity2, "MWebview_XViewClose", "", xViewEntity == null ? "" : xViewEntity.url);
                        }
                    });
                }
                if (parentViewAutoAddOrRemove()) {
                    setVisibility(4);
                }
                this.mJdWebView.setPageType(XVIEW_SCHEME);
                this.mJdWebView.appendPerformanceData("initStart", String.valueOf(this.initTimeStart));
                this.mJdWebView.appendPerformanceData(WebPerfManager.INIT_FINISH, String.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public boolean isChangeParentAccessibility() {
        return this.canChangeParentAccessibility;
    }

    public boolean isXViewReady() {
        return this.isXViewReady;
    }

    @Override // com.jingdong.common.XView.IXView
    public boolean isXViewShow() {
        return this.shouldXViewDisplay;
    }

    public void notifyXViewVisibility(boolean z) {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.notifyWebViewVisible(z);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            try {
                if (this.mPercentX == 0.0f || this.mPercentY == 0.0f) {
                    return;
                }
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                if (measuredWidth <= 0 || measuredHeight <= 0) {
                    return;
                }
                Log.d(this.TAG, "xview layout change  w:" + measuredWidth + "   h:" + measuredHeight + "   closeBtn w:" + this.closeBtnWidth + "   h:" + this.closeBtnHeight);
                int i6 = (int) (this.mPercentX * ((float) (measuredWidth - this.closeBtnWidth)));
                int i7 = (int) (this.mPercentY * ((float) (measuredHeight - this.closeBtnHeight)));
                if (this.mCloseBtn != null) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.closeBtnWidth, this.closeBtnHeight);
                    layoutParams.leftMargin = i6;
                    layoutParams.topMargin = i7;
                    this.mCloseBtn.setLayoutParams(layoutParams);
                    Log.d(this.TAG, "set closeButton postion success:" + i6 + "  " + i7);
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jingdong.common.XView.IXView
    public void onResume() {
        Log.d(this.TAG, "onResume");
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView == null) {
            return;
        }
        this.mPageState = PAGESTATE.RESUME;
        if (this.needDisplayXView) {
            displayXView();
        } else if (this.shouldXViewDisplay) {
            setVisibility(0);
            this.mJdWebView.onResume();
        } else {
            jDWebView.onResume();
        }
    }

    public void onStop() {
        Log.d(this.TAG, "onStop");
        this.mPageState = PAGESTATE.STOP;
        if (this.mXViewEntity != null && getVisibility() == 0) {
            if (!this.mXViewEntity.isIntercepted) {
                closeXView();
                return;
            }
            setVisibility(4);
            this.mJdWebView.onStop();
        }
    }

    public void openAppJump(String str, Uri uri) {
        Intent intent = new Intent();
        intent.setData(uri);
        try {
            OpenAppJumpController.dispatchJumpRequest(this.mActivity, intent);
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            if (iWebUiBinder != null && iWebUiBinder.getBaseActivity() != null) {
                JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_XViewClick", "", str);
            }
        } catch (Exception e2) {
            Log.e(this.TAG, e2.getMessage());
        }
        XViewEntity xViewEntity = this.mXViewEntity;
        if (xViewEntity == null || !xViewEntity.needAutoClose) {
            return;
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.XView.XView.10
            {
                XView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                XView.this.closeXView();
            }
        }, 1000L);
    }

    protected boolean parentViewAutoAddOrRemove() {
        return true;
    }

    @Override // com.jingdong.common.XView.IXView
    @Deprecated
    public void preloadXView() {
        startXView();
    }

    public void setChangeParentAccessibility(boolean z) {
        this.canChangeParentAccessibility = z;
    }

    public void setCloseBtnDelayTime(int i2) {
        this.mCloseBtnDelayTimeInSecond = i2;
    }

    public void setCloseButtonVisible(int i2) {
        Log.d(this.TAG, "setCloseButtonVisible:" + i2);
        SimpleDraweeView simpleDraweeView = this.mCloseBtn;
        if (simpleDraweeView != null) {
            if (i2 != 0) {
                simpleDraweeView.setVisibility(i2);
            } else {
                simpleDraweeView.post(new Runnable() { // from class: com.jingdong.common.XView.XView.12
                    {
                        XView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        int[] iArr = new int[2];
                        XView.this.getLocationInWindow(iArr);
                        int statusBarHeight = UnStatusBarTintUtil.getStatusBarHeight((Activity) XView.this.mActivity);
                        Log.d(XView.this.TAG, "xview getLocationInWindow:" + iArr[0] + "  " + iArr[1]);
                        int[] iArr2 = new int[2];
                        XView.this.getLocationOnScreen(iArr2);
                        Log.d(XView.this.TAG, "xview getLocationOnScreen:" + iArr2[0] + "  " + iArr2[1]);
                        String str = XView.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("statusBarHeight:");
                        sb.append(statusBarHeight);
                        Log.d(str, sb.toString());
                        if (iArr[1] < statusBarHeight && (XView.this.mCloseBtn.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) XView.this.mCloseBtn.getLayoutParams();
                            marginLayoutParams.topMargin += statusBarHeight - iArr[1];
                            XView.this.mCloseBtn.setLayoutParams(marginLayoutParams);
                        }
                        XView.this.mCloseBtn.setVisibility(0);
                    }
                });
            }
        }
    }

    public void setCloseIntercept(ICloseIntercept iCloseIntercept) {
        this.mCloseIntercept = iCloseIntercept;
    }

    public void setIsNeedConfigCloseButton(boolean z) {
        this.mIsNeedConfigCloseButton = z;
    }

    public void setNoTouch(boolean z) {
        this.noTouch = z;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        Log.d(this.TAG, "setVisibility:" + i2);
        super.setVisibility(i2);
        notifyXViewVisibility(i2 == 0);
        XViewCallBack xViewCallBack = this.mCallBack;
        if (xViewCallBack != null) {
            xViewCallBack.onXViewVisibleChanged(i2 == 0);
        }
    }

    public boolean setXViewShareInfo(ShareInfo shareInfo) {
        return false;
    }

    public void showShareDialog() {
        if (this.webUiBinder.getWebEntity().jsBridgeEntity.shareInfo != null) {
            ShareUtil.showShareDialog(this.mActivity, this.webUiBinder.getWebEntity().jsBridgeEntity.shareInfo, this.webUiBinder.getWebEntity().jsBridgeEntity.shareCallbackListener, this.webUiBinder.getWebEntity().jsBridgeEntity.shareClickCallbackListener);
        } else {
            ToastUtils.shortToast(getContext(), "share info is empty");
        }
    }

    @Override // com.jingdong.common.XView.IXViewUnite
    public void sourceIsReady(String str) {
    }

    public void startHybrid(String str, String str2) {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.enableHybrid(str, str2);
        }
    }

    @Override // com.jingdong.common.XView.IXView
    public void startXView() {
        ViewGroup viewGroup;
        if (parentViewAutoAddOrRemove() && (this.mParentView == null || this.mXViewEntity == null)) {
            Log.e(this.TAG, "startXView error ");
            return;
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(XVIEW1_START_EXPOSWITCH, false)) {
            WebUnifiedMtaUtil.sendExposureMta(getContext(), "com.jingdong.common.XView.XView.class", "", this.mXViewEntity.url, MWEBVIEW1_START_EXPO, "");
        }
        Log.d(this.TAG, "startXView");
        this.isXViewReady = false;
        this.shouldXViewDisplay = false;
        this.needDisplayXView = false;
        this.closeWay = 0;
        this.isStart = true;
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.stopLoading();
        }
        if (parentViewAutoAddOrRemove()) {
            setVisibility(4);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            XViewEntity xViewEntity = this.mXViewEntity;
            int i2 = xViewEntity.height;
            if (i2 > 0) {
                int i3 = xViewEntity.width;
                if (i3 > 0) {
                    layoutParams.width = DPIUtil.dip2px(i3);
                    layoutParams.height = DPIUtil.dip2px(this.mXViewEntity.height);
                } else {
                    layoutParams.width = -1;
                    layoutParams.height = DPIUtil.dip2px(i2);
                }
            }
            if (getParent() != null && (getParent() instanceof ViewGroup)) {
                ((ViewGroup) getParent()).removeView(this);
            }
            ViewGroup viewGroup2 = this.mParentView;
            if (viewGroup2 != null && viewGroup2.indexOfChild(this) == -1) {
                this.mParentView.addView(this, layoutParams);
            }
        }
        XViewCallBack xViewCallBack = this.mCallBack;
        if (xViewCallBack != null) {
            xViewCallBack.onStart();
        }
        gentokenAndLoadUrl(WebUtils.addCustomParams(this.mXViewEntity.url, null));
        if (!parentViewAutoAddOrRemove() || !this.mXViewEntity.isFragment || (viewGroup = this.mParentView) == null || viewGroup.getWindowVisibility() == 0) {
            return;
        }
        onStop();
    }

    public void gentokenAndLoadUrl(final String str, boolean z) {
        JDWebView jDWebView;
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        if (z && (jDWebView = this.mJdWebView) != null && jDWebView.isHybridPassGenToken()) {
            uRLParamMap.put("webHybridHasOffConfig", "1");
        }
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("isHybridPassGenToken:");
        JDWebView jDWebView2 = this.mJdWebView;
        sb.append(jDWebView2 != null && jDWebView2.isHybridPassGenToken());
        Log.d(str2, sb.toString());
        if (z && this.mJdWebView != null && WebUtils.canPassGentoken(uRLParamMap)) {
            String loadUrlIgnoreGentoken = WebUtils.getLoadUrlIgnoreGentoken(uRLParamMap);
            if (Log.D || WebLogHelper.showXLog) {
                XLog.d(this.TAG, null, "[genToken] Can pass gentoken, url: " + loadUrlIgnoreGentoken, "webview");
            }
            this.mJdWebView.loadUrl(loadUrlIgnoreGentoken);
            return;
        }
        CommonBase.queryBrowserUrlSupportNoLbs(RemoteMessageConst.TO, uRLParamMap, new CommonBase.GenTokenStatusListener() { // from class: com.jingdong.common.XView.XView.9
            {
                XView.this = this;
            }

            @Override // com.jingdong.common.utils.CommonBase.GenTokenStatusListener
            public void gentokenStatus(String str3, String str4, String str5) {
                if (TextUtils.isEmpty(str3) || "0".equals(str3)) {
                    return;
                }
                Log.d(XView.this.TAG, "gentoken error,status:" + str3 + " msg:" + str4 + "  token:" + str5);
                XView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.XView.XView.9.1
                    {
                        AnonymousClass9.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (XView.this.mCallBack != null) {
                            XView.this.mCallBack.onError(-100);
                        }
                        if (XView.this.parentViewAutoAddOrRemove()) {
                            XView.this.closeXView();
                        }
                    }
                });
                ExceptionReporter.reportWebPageError("Gentoken_Error", "gentoken request error", str, "-100");
            }

            @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
            public void onComplete(final String str3) {
                Log.d(XView.this.TAG, "gentoken complete");
                long currentTimeMillis = System.currentTimeMillis();
                XView xView = XView.this;
                double d = currentTimeMillis;
                Double.isNaN(d);
                xView.genToken_end = d / 1000.0d;
                XView.this.genTokenFinished = true;
                JDWebView jDWebView3 = XView.this.mJdWebView;
                if (jDWebView3 != null) {
                    jDWebView3.setLoadInterrupted(null);
                }
                if (SwitchQueryFetcher.getSwitchBooleanValue("hybridGentoken", false)) {
                    Log.d(XView.this.TAG, "[genToken][xview] use hybrid's http to sync cookie");
                    System.currentTimeMillis();
                    JDWebView jDWebView4 = XView.this.mJdWebView;
                    HybridGenTokenSupporter.loadGenTokenUrl(str3, str, jDWebView4 != null ? jDWebView4.getHybridOfflineLoader() : null, Looper.getMainLooper(), new HybridGenTokenSupporter.GenTokenCallback
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x005b: INVOKE 
                          (r11v0 'str3' java.lang.String)
                          (wrap: java.lang.String : 0x0050: IGET (r10v0 'this' com.jingdong.common.XView.XView$9 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:11) com.jingdong.common.XView.XView.9.val$url java.lang.String)
                          (wrap: com.jd.libs.hybrid.HybridOfflineLoader : ?: TERNARY null = ((r2v14 'jDWebView4' com.jingdong.common.web.ui.JDWebView) != (null com.jingdong.common.web.ui.JDWebView)) ? (wrap: com.jd.libs.hybrid.HybridOfflineLoader : 0x004c: INVOKE (r2v14 'jDWebView4' com.jingdong.common.web.ui.JDWebView) type: VIRTUAL call: com.jingdong.common.web.ui.JDWebView.getHybridOfflineLoader():com.jd.libs.hybrid.HybridOfflineLoader A[MD:():com.jd.libs.hybrid.HybridOfflineLoader (m), WRAPPED]) : (null com.jd.libs.hybrid.HybridOfflineLoader))
                          (wrap: android.os.Looper : 0x0052: INVOKE  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper A[MD:():android.os.Looper (c), WRAPPED])
                          (wrap: com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback : 0x0058: CONSTRUCTOR 
                          (r10v0 'this' com.jingdong.common.XView.XView$9 A[IMMUTABLE_TYPE, THIS])
                          (r7 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r11v0 'str3' java.lang.String A[DONT_INLINE])
                         A[MD:(com.jingdong.common.XView.XView$9, long, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.XView.XView.9.2.<init>(com.jingdong.common.XView.XView$9, long, java.lang.String):void type: CONSTRUCTOR)
                         type: STATIC call: com.jd.libs.hybrid.HybridGenTokenSupporter.loadGenTokenUrl(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void A[MD:(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void (m)] (LINE:11) in method: com.jingdong.common.XView.XView.9.onComplete(java.lang.String):void, file: classes5.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        Caused by: java.lang.NullPointerException
                        */
                    /*
                        this = this;
                        com.jingdong.common.XView.XView r0 = com.jingdong.common.XView.XView.this
                        java.lang.String r0 = com.jingdong.common.XView.XView.access$700(r0)
                        java.lang.String r1 = "gentoken complete"
                        com.jingdong.corelib.utils.Log.d(r0, r1)
                        long r0 = java.lang.System.currentTimeMillis()
                        com.jingdong.common.XView.XView r2 = com.jingdong.common.XView.XView.this
                        double r3 = (double) r0
                        r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
                        java.lang.Double.isNaN(r3)
                        double r3 = r3 / r5
                        com.jingdong.common.XView.XView.access$1202(r2, r3)
                        com.jingdong.common.XView.XView r2 = com.jingdong.common.XView.XView.this
                        r3 = 1
                        com.jingdong.common.XView.XView.access$1302(r2, r3)
                        com.jingdong.common.XView.XView r2 = com.jingdong.common.XView.XView.this
                        com.jingdong.common.web.ui.JDWebView r2 = r2.mJdWebView
                        r3 = 0
                        if (r2 == 0) goto L2e
                        r2.setLoadInterrupted(r3)
                    L2e:
                        r2 = 0
                        java.lang.String r4 = "hybridGentoken"
                        boolean r2 = com.jingdong.common.utils.SwitchQueryFetcher.getSwitchBooleanValue(r4, r2)
                        if (r2 == 0) goto L5f
                        com.jingdong.common.XView.XView r2 = com.jingdong.common.XView.XView.this
                        java.lang.String r2 = com.jingdong.common.XView.XView.access$700(r2)
                        java.lang.String r4 = "[genToken][xview] use hybrid's http to sync cookie"
                        com.jingdong.corelib.utils.Log.d(r2, r4)
                        long r7 = java.lang.System.currentTimeMillis()
                        com.jingdong.common.XView.XView r2 = com.jingdong.common.XView.XView.this
                        com.jingdong.common.web.ui.JDWebView r2 = r2.mJdWebView
                        if (r2 == 0) goto L50
                        com.jd.libs.hybrid.HybridOfflineLoader r3 = r2.getHybridOfflineLoader()
                    L50:
                        java.lang.String r2 = r2
                        android.os.Looper r4 = android.os.Looper.getMainLooper()
                        com.jingdong.common.XView.XView$9$2 r9 = new com.jingdong.common.XView.XView$9$2
                        r9.<init>()
                        com.jd.libs.hybrid.HybridGenTokenSupporter.loadGenTokenUrl(r11, r2, r3, r4, r9)
                        goto L6d
                    L5f:
                        com.jingdong.common.XView.XView r2 = com.jingdong.common.XView.XView.this
                        android.os.Handler r2 = com.jingdong.common.XView.XView.access$1100(r2)
                        com.jingdong.common.XView.XView$9$3 r3 = new com.jingdong.common.XView.XView$9$3
                        r3.<init>()
                        r2.post(r3)
                    L6d:
                        com.jingdong.common.web.managers.PerformanceManager r11 = com.jingdong.common.web.managers.PerformanceManager.getInstance()     // Catch: java.lang.Exception -> Lb4
                        java.lang.String r2 = "mloadType"
                        java.lang.String r3 = "x_gentoken"
                        r11.appendData(r2, r3)     // Catch: java.lang.Exception -> Lb4
                        com.jingdong.common.web.managers.PerformanceManager r11 = com.jingdong.common.web.managers.PerformanceManager.getInstance()     // Catch: java.lang.Exception -> Lb4
                        java.lang.String r2 = "mloadUrl"
                        com.jingdong.common.XView.XView r3 = com.jingdong.common.XView.XView.this     // Catch: java.lang.Exception -> Lb4
                        com.jingdong.common.XView.XViewEntity r3 = r3.mXViewEntity     // Catch: java.lang.Exception -> Lb4
                        if (r3 == 0) goto L87
                        java.lang.String r3 = r3.url     // Catch: java.lang.Exception -> Lb4
                        goto L89
                    L87:
                        java.lang.String r3 = ""
                    L89:
                        r11.appendData(r2, r3)     // Catch: java.lang.Exception -> Lb4
                        com.jingdong.common.web.managers.PerformanceManager r11 = com.jingdong.common.web.managers.PerformanceManager.getInstance()     // Catch: java.lang.Exception -> Lb4
                        java.lang.String r2 = "mloadingTime"
                        com.jingdong.common.XView.XView r3 = com.jingdong.common.XView.XView.this     // Catch: java.lang.Exception -> Lb4
                        double r3 = com.jingdong.common.XView.XView.access$1200(r3)     // Catch: java.lang.Exception -> Lb4
                        com.jingdong.common.XView.XView r7 = com.jingdong.common.XView.XView.this     // Catch: java.lang.Exception -> Lb4
                        double r7 = com.jingdong.common.XView.XView.access$1400(r7)     // Catch: java.lang.Exception -> Lb4
                        double r3 = r3 - r7
                        double r3 = r3 * r5
                        long r3 = java.lang.Math.round(r3)     // Catch: java.lang.Exception -> Lb4
                        java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Exception -> Lb4
                        r11.appendData(r2, r3)     // Catch: java.lang.Exception -> Lb4
                        com.jingdong.common.web.managers.PerformanceManager r11 = com.jingdong.common.web.managers.PerformanceManager.getInstance()     // Catch: java.lang.Exception -> Lb4
                        r11.report()     // Catch: java.lang.Exception -> Lb4
                        goto Lb8
                    Lb4:
                        r11 = move-exception
                        r11.printStackTrace()
                    Lb8:
                        com.jingdong.common.XView.XView r11 = com.jingdong.common.XView.XView.this
                        com.jingdong.common.web.ui.JDWebView r11 = r11.mJdWebView
                        if (r11 == 0) goto Lc7
                        java.lang.String r0 = java.lang.String.valueOf(r0)
                        java.lang.String r1 = "gentokenFinish"
                        r11.appendPerformanceData(r1, r0)
                    Lc7:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView.XView.AnonymousClass9.onComplete(java.lang.String):void");
                }

                @Override // com.jingdong.common.utils.CommonBase.BrowserNewUrlListener
                public void onError(HttpError httpError) {
                    String str3;
                    Log.d(XView.this.TAG, "gentoken onerror");
                    XView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.XView.XView.9.4
                        {
                            AnonymousClass9.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (XView.this.mCallBack != null) {
                                XView.this.mCallBack.onError(-100);
                            }
                            if (XView.this.parentViewAutoAddOrRemove()) {
                                XView.this.closeXView();
                            }
                        }
                    });
                    XView xView = XView.this;
                    double currentTimeMillis = System.currentTimeMillis();
                    Double.isNaN(currentTimeMillis);
                    xView.genToken_end = currentTimeMillis / 1000.0d;
                    XView.this.genTokenFinished = true;
                    JDWebView jDWebView3 = XView.this.mJdWebView;
                    if (jDWebView3 != null) {
                        jDWebView3.setLoadInterrupted(null);
                    }
                    if (httpError == null) {
                        str3 = "unknown";
                    } else {
                        str3 = httpError.getErrorCode() + "";
                    }
                    ExceptionReporter.reportWebPageError("Gentoken_Error", "xView gentoken request error", str, str3);
                    PerformanceManager.getInstance().appendData(PerformanceManager.MERROR_CODE, str3);
                    PerformanceManager.getInstance().appendData("mloadType", "x_gentoken");
                    PerformanceManager performanceManager = PerformanceManager.getInstance();
                    XViewEntity xViewEntity = XView.this.mXViewEntity;
                    performanceManager.appendData(PerformanceManager.LOAD_URL, xViewEntity != null ? xViewEntity.url : "");
                    PerformanceManager.getInstance().appendData("mloadingTime", String.valueOf(Math.round((XView.this.genToken_end - XView.this.genToken_start) * 1000.0d)));
                    PerformanceManager.getInstance().appendData("isError", "1");
                    PerformanceManager.getInstance().appendData("errMsg", "genToken\u5931\u8d25");
                    PerformanceManager.getInstance().report();
                }

                @Override // com.jingdong.common.utils.CommonBase.BrowserAllUrlListener
                public void onReady() {
                    Log.d(XView.this.TAG, "gentoken onReady");
                    long currentTimeMillis = System.currentTimeMillis();
                    XView xView = XView.this;
                    double d = currentTimeMillis;
                    Double.isNaN(d);
                    xView.genToken_start = d / 1000.0d;
                    XView.this.genTokenFinished = false;
                    JDWebView jDWebView3 = XView.this.mJdWebView;
                    if (jDWebView3 != null) {
                        jDWebView3.setLoadInterrupted("gentoken");
                        XView.this.mJdWebView.appendPerformanceData("gentokenStart", String.valueOf(currentTimeMillis));
                    }
                }
            });
        }

        public XView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public XView(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.TAG = getClass().getSimpleName();
            this.webEntity = new WebEntity();
            this.mHandler = new Handler(Looper.getMainLooper());
            int dip2px = DPIUtil.dip2px(30.0f);
            this.closeBtnWidth = dip2px;
            this.closeBtnHeight = dip2px;
            this.isXViewReady = false;
            this.shouldXViewDisplay = false;
            this.needDisplayXView = false;
            this.noTouch = false;
            this.mPageState = PAGESTATE.RESUME;
            this.initTimeStart = System.currentTimeMillis();
            this.df = new DecimalFormat("#.###");
            this.pageFinished = true;
            this.genTokenFinished = true;
            this.closeWay = 0;
            this.mCloseBtnDelayTimeInSecond = 0;
            this.canChangeParentAccessibility = true;
            this.parentAccessibilityChanged = false;
            this.mIsNeedConfigCloseButton = true;
            this.offlineId = null;
            this.preloadId = null;
            init(context);
        }
    }
