package com.jingdong.common.jdreactFramework.views.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.libs.hybrid.HybridGenTokenSupporter;
import com.jd.libs.xconsole.XLog;
import com.jingdong.app.mall.R;
import com.jingdong.common.jdreactFramework.listener.JDCallbackShouldOverriderUrlLoading;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.WebDebug;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.IJdWebViewUi;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.JDWebViewBuilder;
import com.jingdong.common.web.uibinder.impl.CommonWebUiBinder;
import com.jingdong.common.web.uilistener.WebViewClientListener;
import com.jingdong.common.web.util.JdWebviewBlackListUtil;
import com.jingdong.common.web.util.WebLogHelper;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.tencent.smtt.sdk.WebView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CommonMView extends FrameLayout implements IJdWebViewUi, JDCallbackShouldOverriderUrlLoading {
    private static final String BRIDGE_NAME = "_COMMON_M_VIEW_BRIDGE";
    private final String TAG;
    private boolean isGentokenFinished;
    private boolean isWebViewInited;
    private String jsCode;
    private String loadUrl;
    private JDReactWebView mJdWebView;
    private OnMessageListener mMessageListener;
    private WebViewClientListener mWebViewClientListener;
    private boolean messagingEnabled;
    private Activity thisActivity;
    private WebEntity webEntity;
    private CommonWebUiBinder webUiBinder;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class MessageBridge {
        CommonMView commonMView;

        MessageBridge(CommonMView commonMView) {
            this.commonMView = commonMView;
        }

        @JavascriptInterface
        public void postMessage(String str) {
            this.commonMView.onMessage(str);
        }
    }

    /* loaded from: classes5.dex */
    public interface OnMessageListener {
        void onMessage(String str);
    }

    public CommonMView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = CommonMView.class.getSimpleName();
        this.isWebViewInited = false;
        this.isGentokenFinished = false;
        this.messagingEnabled = false;
    }

    private void gentoken() {
        final String str;
        WebEntity webEntity = this.webEntity;
        if (webEntity == null) {
            return;
        }
        if (WebUtils.canPassGentoken(webEntity.urlMap)) {
            this.mJdWebView.loadUrl(WebUtils.getLoadUrlIgnoreGentoken(this.webEntity.urlMap));
            return;
        }
        WebEntity webEntity2 = this.webEntity;
        if (webEntity2.urlMap != null) {
            if (TextUtils.isEmpty(webEntity2.action)) {
                str = this.webEntity.urlMap.get((Object) RemoteMessageConst.TO);
            } else {
                WebEntity webEntity3 = this.webEntity;
                str = webEntity3.urlMap.get((Object) webEntity3.action);
            }
            if (!TextUtils.isEmpty(str)) {
                try {
                    str = Uri.decode(str);
                } catch (Exception unused) {
                }
            }
        } else {
            str = null;
        }
        if (WebDebug.report) {
            WebDebug.log("webview", "[genToken] Ready to gentoken: " + str, this);
        }
        if (Log.D || WebLogHelper.showXLog) {
            XLog.d(this.TAG, null, "[genToken] Ready to gentoken, url: " + str, "rn-webview");
        }
        WebEntity webEntity4 = this.webEntity;
        CommonBase.queryBrowserUrlSupportNoLbs(webEntity4.action, webEntity4.urlMap, new CommonBase.BrowserAllUrlListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.CommonMView.2
            @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
            public void onComplete(final String str2) {
                if (!SwitchQueryFetcher.getSwitchBooleanValue("hybridGentoken", false)) {
                    CommonMView.this.thisActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.webview.CommonMView.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Log.D || WebLogHelper.showXLog) {
                                XLog.d(CommonMView.this.TAG, null, "[genToken]\u76f4\u63a5\u4f7f\u7528webview\u52a0\u8f7dgentokenUrl\uff0c\u539f\u56e0\uff1aSwitchQuery\u5f00\u5173\u5173\u95ed", "rn-webview");
                            }
                            CommonMView.this.mJdWebView.loadUrl(str2);
                            CommonMView.this.mJdWebView.addLoadEvent(WebWhiteScreenHolder.GENTOKEN_FALSE);
                        }
                    });
                } else {
                    if (WebDebug.report) {
                        WebDebug.log("webview", "[genToken] use hybrid's http to sync cookie", CommonMView.this);
                    }
                    System.currentTimeMillis();
                    HybridGenTokenSupporter.loadGenTokenUrl(str2, str, CommonMView.this.mJdWebView.getHybridOfflineLoader(), Looper.getMainLooper(), new HybridGenTokenSupporter.GenTokenCallback
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002f: INVOKE 
                          (r13v0 'str2' java.lang.String)
                          (wrap: java.lang.String : 0x001a: IGET (r12v0 'this' com.jingdong.common.jdreactFramework.views.webview.CommonMView$2 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:5) com.jingdong.common.jdreactFramework.views.webview.CommonMView.2.val$finalDecodedUrl java.lang.String)
                          (wrap: com.jd.libs.hybrid.HybridOfflineLoader : 0x0022: INVOKE 
                          (wrap: com.jingdong.common.jdreactFramework.views.webview.JDReactWebView : 0x001e: IGET 
                          (wrap: com.jingdong.common.jdreactFramework.views.webview.CommonMView : 0x001c: IGET (r12v0 'this' com.jingdong.common.jdreactFramework.views.webview.CommonMView$2 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] com.jingdong.common.jdreactFramework.views.webview.CommonMView.2.this$0 com.jingdong.common.jdreactFramework.views.webview.CommonMView)
                         A[MD:(com.jingdong.common.jdreactFramework.views.webview.CommonMView):com.jingdong.common.jdreactFramework.views.webview.JDReactWebView (m), WRAPPED] (LINE:1) com.jingdong.common.jdreactFramework.views.webview.CommonMView.mJdWebView com.jingdong.common.jdreactFramework.views.webview.JDReactWebView)
                         type: VIRTUAL call: com.jingdong.common.web.ui.JDWebView.getHybridOfflineLoader():com.jd.libs.hybrid.HybridOfflineLoader A[MD:():com.jd.libs.hybrid.HybridOfflineLoader (m), WRAPPED] (LINE:1))
                          (wrap: android.os.Looper : 0x0026: INVOKE  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper A[MD:():android.os.Looper (c), WRAPPED])
                          (wrap: com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback : 0x002c: CONSTRUCTOR 
                          (r12v0 'this' com.jingdong.common.jdreactFramework.views.webview.CommonMView$2 A[IMMUTABLE_TYPE, THIS])
                          (r0 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r13v0 'str2' java.lang.String A[DONT_INLINE])
                         A[MD:(com.jingdong.common.jdreactFramework.views.webview.CommonMView$2, long, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.jdreactFramework.views.webview.CommonMView.2.1.<init>(com.jingdong.common.jdreactFramework.views.webview.CommonMView$2, long, java.lang.String):void type: CONSTRUCTOR)
                         type: STATIC call: com.jd.libs.hybrid.HybridGenTokenSupporter.loadGenTokenUrl(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void A[MD:(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void (m)] (LINE:1) in method: com.jingdong.common.jdreactFramework.views.webview.CommonMView.2.onComplete(java.lang.String):void, file: classes5.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
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
                        Method dump skipped, instructions count: 289
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.views.webview.CommonMView.AnonymousClass2.onComplete(java.lang.String):void");
                }

                @Override // com.jingdong.common.utils.CommonBase.BrowserNewUrlListener
                public void onError(HttpError httpError) {
                    String str2;
                    if (CommonMView.this.thisActivity != null && !CommonMView.this.thisActivity.isFinishing()) {
                        ToastUtils.shortToast(CommonMView.this.thisActivity.getString(R.string.hb) + "(gentoken)");
                    }
                    WebEntity webEntity5 = CommonMView.this.webEntity;
                    double currentTimeMillis = System.currentTimeMillis();
                    Double.isNaN(currentTimeMillis);
                    webEntity5.genToken_end = currentTimeMillis / 1000.0d;
                    CommonMView.this.webEntity.genTokenFinished = true;
                    JDMtaUtils.sendWebviewLoadData(CommonMView.this.thisActivity, CommonMView.this.TAG, "", "gentoken", CommonMView.this.webEntity.urlMap == null ? "" : CommonMView.this.webEntity.urlMap.get((Object) RemoteMessageConst.TO), CommonMView.this.webEntity.df.format(CommonMView.this.webEntity.genToken_start), CommonMView.this.webEntity.df.format(CommonMView.this.webEntity.genToken_end), "fail");
                    if (httpError == null) {
                        str2 = "unknown";
                    } else {
                        str2 = httpError.getErrorCode() + "";
                    }
                    ExceptionReporter.reportWebPageError("Gentoken_Error", "gentoken request error", CommonMView.this.webEntity.urlMap == null ? "" : CommonMView.this.webEntity.urlMap.get((Object) RemoteMessageConst.TO), str2);
                    PerformanceManager.getInstance().appendData(PerformanceManager.MERROR_CODE, str2);
                    PerformanceManager.getInstance().appendData("mloadType", "gentoken");
                    PerformanceManager.getInstance().appendData(PerformanceManager.LOAD_URL, WebUtils.decodeUrl(CommonMView.this.webEntity.urlMap != null ? CommonMView.this.webEntity.urlMap.get((Object) RemoteMessageConst.TO) : ""));
                    PerformanceManager.getInstance().appendData("mloadingTime", String.valueOf(Math.round((CommonMView.this.webEntity.genToken_end - CommonMView.this.webEntity.genToken_start) * 1000.0d)));
                    PerformanceManager.getInstance().appendData("isError", "1");
                    PerformanceManager.getInstance().appendData("errMsg", "genToken\u5931\u8d25");
                    PerformanceManager.getInstance().report();
                    CommonMView.this.mJdWebView.appendWhiteScreenData(WebWhiteScreenHolder.GENTOKEN_ERR_MSG, "gentoken- errorCode= " + str2);
                }

                @Override // com.jingdong.common.utils.CommonBase.BrowserAllUrlListener
                public void onReady() {
                    WebEntity webEntity5 = CommonMView.this.webEntity;
                    double currentTimeMillis = System.currentTimeMillis();
                    Double.isNaN(currentTimeMillis);
                    webEntity5.genToken_start = currentTimeMillis / 1000.0d;
                    CommonMView.this.webEntity.genTokenFinished = false;
                }
            }, true);
        }

        private void getDataFromBundle(Bundle bundle) {
            WebEntity webEntity = new WebEntity();
            this.webEntity = webEntity;
            webEntity.init(bundle);
            if (WebDebug.report) {
                WebDebug.log("webview", "[getDataFromBundle]bundle:" + bundle.toString(), this);
            }
        }

        private void initJdWebView() {
            if (!TextUtils.isEmpty(this.webEntity.mTitle)) {
                this.mJdWebView.setFixedTitle(this.webEntity.mTitle);
            }
            this.mJdWebView.setSwitchImmersiveOpen(this.webEntity.switchImmersiveOpen);
            this.mJdWebView.setTopBarGone(this.webEntity.isTopBarGone);
            this.mJdWebView.setUseCloseBtn(this.webEntity.isUseCloseBtn);
            if ("fromNotice".equals(this.webEntity.sourceValue)) {
                this.mJdWebView.setCloseBtnVisible(true);
            }
            this.mJdWebView.setShareBtnState(this.webEntity.jsBridgeEntity.isNeedShare);
            if (!TextUtils.isEmpty(this.webEntity.urlFromIntent) && JdWebviewBlackListUtil.shouldDisableWebViewCache(this.webEntity.urlFromIntent)) {
                this.webEntity.isUseCache = false;
            }
            this.mJdWebView.setUseCache(this.webEntity.isUseCache);
            if (!TextUtils.isEmpty(this.webEntity.url) && JdWebviewBlackListUtil.needHideRightPopButton(this.webEntity.url)) {
                this.webEntity.isShowMoreBtn = false;
            }
            if (!TextUtils.isEmpty(this.webEntity.urlFromIntent) && JdWebviewBlackListUtil.needHideRightPopButton(this.webEntity.urlFromIntent)) {
                this.webEntity.isShowMoreBtn = false;
            }
            Log.d(this.TAG, "show right morebutton:" + this.webEntity.isShowMoreBtn);
            this.mJdWebView.setMoreBtnVisible(this.webEntity.isShowMoreBtn);
            this.mJdWebView.setStatusBarAlwaysTransparent(this.webEntity.statusBarAlwaysTransparent);
            this.mJdWebView.setWebViewClientListener(new WebViewClientListener() { // from class: com.jingdong.common.jdreactFramework.views.webview.CommonMView.1
                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void onPageFinished(WebView webView, String str) {
                    CommonMView.this.linkMessageBridge();
                    if (!TextUtils.isEmpty(CommonMView.this.jsCode)) {
                        CommonMView commonMView = CommonMView.this;
                        commonMView.injectJS(commonMView.jsCode);
                    }
                    if (CommonMView.this.mWebViewClientListener != null) {
                        CommonMView.this.mWebViewClientListener.onPageFinished(webView, str);
                    }
                }

                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                    if (CommonMView.this.mWebViewClientListener != null) {
                        CommonMView.this.mWebViewClientListener.onPageStarted(webView, str, bitmap);
                    }
                }

                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void onReceivedError(WebView webView, int i2, String str, String str2) {
                    if (CommonMView.this.mWebViewClientListener != null) {
                        CommonMView.this.mWebViewClientListener.onReceivedError(webView, i2, str, str2);
                    }
                }

                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void shouldOverrideUrlLoading(WebView webView, String str) {
                    if (CommonMView.this.mWebViewClientListener != null) {
                        CommonMView.this.mWebViewClientListener.shouldOverrideUrlLoading(webView, str);
                    }
                }
            });
        }

        private void initWeb() {
            initJdWebView();
            if (TextUtils.isEmpty(this.webEntity.url)) {
                return;
            }
            loadWeb();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void linkMessageBridge() {
            if (this.messagingEnabled) {
                getRealWebView().loadUrl("javascript:(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {_COMMON_M_VIEW_BRIDGE.postMessage(String(data));})");
            }
        }

        private void loadWeb() {
            WebEntity webEntity = this.webEntity;
            if (webEntity.jumpOutSuc) {
                return;
            }
            if (!webEntity.isISVLoginObfuscator && webEntity.needGenToken) {
                gentoken();
                return;
            }
            this.mJdWebView.loadUrl(webEntity.url);
            ExceptionReporter.reportWebViewCommonError("webViewNoNeedGenToken", this.webEntity.url, "webview loadUrl without requiring genToken! cause url: " + this.webEntity.url + "  check no need", "added V6.6.5 class CommonMFragment -->loadWeb");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onMessage(String str) {
            OnMessageListener onMessageListener = this.mMessageListener;
            if (onMessageListener != null) {
                onMessageListener.onMessage(str);
            }
        }

        @Override // com.jingdong.common.jdreactFramework.listener.JDCallbackShouldOverriderUrlLoading
        public void OnCallbackShouldOverrideUrlLoading(WebView webView, String str) {
            WebViewClientListener webViewClientListener = this.mWebViewClientListener;
            if (webViewClientListener != null) {
                webViewClientListener.shouldOverrideUrlLoading(webView, str);
            }
        }

        public boolean canGoBack() {
            return getRealWebView().canGoBack();
        }

        public boolean canGoForward() {
            return getRealWebView().canGoForward();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void cleanupCallbacksAndDestroy() {
            getJdWebView().onDestory();
        }

        @Override // com.jingdong.common.web.ui.IJdWebViewUi
        public JDWebView getJdWebView() {
            return this.mJdWebView;
        }

        @Override // com.jingdong.common.web.ui.IJdWebViewUi
        public NavigatorHolder getNaviHolder() {
            JDReactWebView jDReactWebView = this.mJdWebView;
            if (jDReactWebView != null) {
                return jDReactWebView.getNavigatorHolder();
            }
            return null;
        }

        public int getProgress() {
            return getRealWebView().getProgress();
        }

        public WebView getRealWebView() {
            return getJdWebView().getWebView();
        }

        public String getTitle() {
            return getRealWebView().getTitle();
        }

        @Override // com.jingdong.common.web.ui.IJdWebViewUi
        public WebEntity getWebEntity() {
            return this.webEntity;
        }

        protected JDReactWebView initWebView() {
            JDWebViewBuilder jDWebViewBuilder = new JDWebViewBuilder(getContext());
            boolean z = true;
            if (TextUtils.isEmpty(this.webEntity.offlineId) && TextUtils.isEmpty(this.webEntity.preLoadId) && WebHybridUtils.hybridEnable && SwitchQueryFetcher.getSwitchBooleanValue("hybrid_view", true)) {
                WebEntity webEntity = this.webEntity;
                webEntity.offlineId = WebOfflineLoaderManager.createOfflineLoader(webEntity.url);
                WebEntity webEntity2 = this.webEntity;
                webEntity2.preLoadId = WebPreLoadHelper.preLoad(webEntity2.url);
                this.webEntity.useHybrid = true;
            }
            JDWebViewBuilder enableHybrid = jDWebViewBuilder.setEnableHybrid(this.webEntity.useHybrid);
            if (TextUtils.isEmpty(this.webEntity.offlineId) && TextUtils.isEmpty(this.webEntity.preLoadId)) {
                z = false;
            }
            enableHybrid.setHybridStarted(z).setHybridUrl(this.webEntity.url).setOfflineId(this.webEntity.offlineId).setPreloadId(this.webEntity.preLoadId);
            return new JDReactWebView(getContext(), jDWebViewBuilder);
        }

        public void injectJS(String str) {
            this.jsCode = str;
            getJdWebView().injectJs(str);
        }

        public boolean isMessagingEnabled() {
            return this.messagingEnabled;
        }

        public void onActivityResult(int i2, int i3, Intent intent) {
            this.webUiBinder.onActivityResult(i2, i3, intent);
        }

        public void postMessage(String str) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("data", str);
                getRealWebView().loadUrl("javascript:(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }

        public void setDomStorageEnabled(boolean z) {
            getRealWebView().getSettings().setDomStorageEnabled(z);
        }

        public void setJavaScriptEnabled(boolean z) {
            getRealWebView().getSettings().setJavaScriptEnabled(z);
        }

        public void setMediaPlaybackRequiresUserAction(boolean z) {
            getRealWebView().getSettings().setMediaPlaybackRequiresUserGesture(z);
        }

        public void setMessagingEnabled(boolean z) {
            if (this.messagingEnabled == z) {
                return;
            }
            this.messagingEnabled = z;
            if (z) {
                getRealWebView().addJavascriptInterface(new MessageBridge(this), BRIDGE_NAME);
                linkMessageBridge();
                return;
            }
            getRealWebView().removeJavascriptInterface(BRIDGE_NAME);
        }

        public void setOnMessageListener(OnMessageListener onMessageListener) {
            this.mMessageListener = onMessageListener;
        }

        public void setScalesPageToFit(boolean z) {
            getRealWebView().getSettings().setUseWideViewPort(!z);
        }

        public void setTopBarGone(boolean z) {
            this.mJdWebView.setTopBarGone(z);
        }

        public void setUserAgent(String str) {
            if (str != null) {
                getRealWebView().getSettings().setUserAgentString(str);
            }
        }

        public void setWebViewClientListener(WebViewClientListener webViewClientListener) {
            this.mWebViewClientListener = webViewClientListener;
        }

        public void updateUrl(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            getDataFromBundle(bundle);
            loadWeb();
        }

        @Override // com.jingdong.common.web.ui.IJdWebViewUi
        public CommonWebUiBinder getWebUiBinder() {
            if (this.webUiBinder == null) {
                this.webUiBinder = new CommonWebUiBinder() { // from class: com.jingdong.common.jdreactFramework.views.webview.CommonMView.4
                    @Override // com.jingdong.common.web.uibinder.impl.CommonWebUiBinder
                    public void bindUrlIntercept() {
                        getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new SchemeCheckImpl(this, CommonMView.this));
                        super.bindUrlIntercept();
                    }
                };
            }
            return this.webUiBinder;
        }

        public CommonMView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.TAG = CommonMView.class.getSimpleName();
            this.isWebViewInited = false;
            this.isGentokenFinished = false;
            this.messagingEnabled = false;
        }

        public CommonMView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
            super(context, attributeSet, i2, i3);
            this.TAG = CommonMView.class.getSimpleName();
            this.isWebViewInited = false;
            this.isGentokenFinished = false;
            this.messagingEnabled = false;
        }

        public CommonMView(@NonNull Activity activity) {
            super(activity);
            this.TAG = CommonMView.class.getSimpleName();
            this.isWebViewInited = false;
            this.isGentokenFinished = false;
            this.messagingEnabled = false;
            this.thisActivity = activity;
            getDataFromBundle(new Bundle());
            this.mJdWebView = initWebView();
            CommonWebUiBinder webUiBinder = getWebUiBinder();
            this.webUiBinder = webUiBinder;
            webUiBinder.bindUi(this);
            initWeb();
            addView(this.mJdWebView);
        }
    }
