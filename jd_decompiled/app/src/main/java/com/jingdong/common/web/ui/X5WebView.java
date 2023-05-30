package com.jingdong.common.web.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ValueCallback;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.jdmbridge.base.JDBridgeManager;
import com.jd.xbridge.XBridgeInstaller;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.CommonDialogController;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.web.BaseWebChromeClient;
import com.jingdong.common.web.BaseWebViewClient;
import com.jingdong.common.web.WebDebug;
import com.jingdong.common.web.WebViewPool;
import com.jingdong.common.web.javainterface.impl.MtaData;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebPerformance;
import com.jingdong.common.web.uilistener.WebViewScrollListener;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebBackForwardList;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class X5WebView extends WebView implements DownloadListener, View.OnLongClickListener, HybridWebView, IBridgeWebView {
    final String TAG;
    private boolean cachedWebView;
    private String extraUserAgent;
    private XBridgeInstaller installer;
    private Context mContext;
    private WebViewScrollListener mScrollListener;
    private BaseWebChromeClient mWebChromeClient;
    private List<WebViewScrollListener> mWebScrollListeners;
    private MtaData mtaData;
    private JDDialog openAppConfirmDialog;
    private String orgUserAgent;
    private WebSettings settings;
    private boolean useXBridge;

    public X5WebView(Context context) {
        super(context);
        this.TAG = X5WebView.class.getSimpleName();
        this.cachedWebView = false;
        this.openAppConfirmDialog = null;
        this.installer = null;
        this.mContext = context;
        config();
    }

    public static boolean isIntentAvailable(Intent intent) {
        List<ResolveInfo> queryIntentActivities = JdSdk.getInstance().getApplication().getPackageManager().queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    public static Intent newBrowserIntent(Uri uri, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (z) {
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }
        intent.setFlags(268435456);
        return intent;
    }

    private void reportFirstX5Performance(long j2, boolean z) {
        if (SwitchQueryFetcher.getSwitchBooleanValue("wvReport1stX5View", false)) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                WebPerformance webPerformance = new WebPerformance(System.currentTimeMillis());
                webPerformance.appendData(WebPerfManager.PAGE_TYPE, "firstX5WebView");
                Context context = this.mContext;
                webPerformance.appendData(WebPerfManager.PAGE_NAME, context != null ? context.getClass().getSimpleName() : null);
                webPerformance.appendData("initStart", String.valueOf(j2));
                webPerformance.appendData(WebPerfManager.INIT_FINISH, String.valueOf(currentTimeMillis));
                webPerformance.appendData(WebPerfManager.CORE_TYPE, getX5WebViewExtension() != null ? "x5" : "system");
                webPerformance.appendData(WebPerfManager.CORE_VER, String.valueOf(WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication())));
                if (z) {
                    try {
                        webPerformance.appendExtra("TBSWebViewCreated", 1);
                    } catch (Exception unused) {
                    }
                }
                WebPerfManager.getInstance().reportRecord(webPerformance, false);
            } catch (Throwable th) {
                if (Log.E) {
                    Log.e(this.TAG, th.getMessage(), th);
                }
            }
        }
    }

    public static void toBrowser(Uri uri) {
        Intent newBrowserIntent = newBrowserIntent(uri, true);
        try {
            if (isIntentAvailable(newBrowserIntent)) {
                if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
                    BaseFrameUtil.getInstance().getCurrentMyActivity().startActivityNoException(newBrowserIntent);
                }
            } else if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
                BaseFrameUtil.getInstance().getCurrentMyActivity().startActivityNoException(newBrowserIntent(uri, false));
            }
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
        }
    }

    public void addWebViewScrollListener(WebViewScrollListener webViewScrollListener) {
        if (this.mWebScrollListeners == null) {
            this.mWebScrollListeners = new ArrayList();
        }
        this.mWebScrollListeners.add(webViewScrollListener);
    }

    public void bindXBridge(boolean z) {
        this.useXBridge = z;
        if (z) {
            XBridgeInstaller xBridgeInstaller = new XBridgeInstaller();
            this.installer = xBridgeInstaller;
            xBridgeInstaller.install(this);
            return;
        }
        new JDBridgeManager(this);
    }

    @Override // com.tencent.smtt.sdk.WebView
    public boolean canGoBack() {
        if (super.canGoBack()) {
            WebBackForwardList copyBackForwardList = copyBackForwardList();
            return (copyBackForwardList != null && copyBackForwardList.getCurrentIndex() == 1 && WebViewPool.isRecycleUrl(copyBackForwardList.getItemAtIndex(0).getUrl())) ? false : true;
        }
        return false;
    }

    protected void config() {
        config(0L, false);
    }

    @Override // com.tencent.smtt.sdk.WebView, com.jd.xbridge.base.IBridgeWebView
    public void destroy() {
        super.destroy();
        XBridgeInstaller xBridgeInstaller = this.installer;
        if (xBridgeInstaller != null) {
            xBridgeInstaller.destroy();
        }
    }

    @Override // com.jd.libs.hybrid.base.HybridWebView, com.jd.jdcache.JDCacheWebView
    public void evaluateJavascript(String str, final ValueCallback<String> valueCallback) {
        evaluateJavascript(str, valueCallback != null ? new com.tencent.smtt.sdk.ValueCallback() { // from class: com.jingdong.common.web.ui.t
            @Override // com.tencent.smtt.sdk.ValueCallback, android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                valueCallback.onReceiveValue((String) obj);
            }
        } : null);
    }

    @Override // com.jd.xbridge.base.IBridgeWebView
    @Nullable
    public Map<String, com.jd.xbridge.base.d> getBridgeMap() {
        XBridgeInstaller xBridgeInstaller = this.installer;
        if (xBridgeInstaller != null) {
            return xBridgeInstaller.getBridgeMap();
        }
        return null;
    }

    public String getExtraUaString() {
        return this.extraUserAgent;
    }

    public String getMtaData() {
        MtaData mtaData = this.mtaData;
        if (mtaData != null) {
            return mtaData.getMtaData();
        }
        return null;
    }

    public int getMtaSequence() {
        MtaData mtaData = this.mtaData;
        if (mtaData != null) {
            return mtaData.getSequence();
        }
        return 0;
    }

    public String getOrgUserAgent() {
        return WebViewHelper.getOrgUserAgent(this.orgUserAgent);
    }

    public void injectMtaData() {
        if (this.mtaData != null && (getWebViewClient() instanceof BaseWebViewClient) && ((BaseWebViewClient) getWebViewClient()).isPageStartedOnce()) {
            this.mtaData.injectMtaData();
        }
    }

    public boolean isCachedWebView() {
        return this.cachedWebView;
    }

    public boolean isMtaDataInjected() {
        MtaData mtaData = this.mtaData;
        return mtaData != null && mtaData.isMtaDataInjected();
    }

    protected boolean needLongClick() {
        return true;
    }

    public void onActive() {
        if (this.installer != null && this.useXBridge && (getWebViewClient() instanceof BaseWebViewClient) && ((BaseWebViewClient) getWebViewClient()).isPageStartedOnce()) {
            this.installer.onResume();
        }
    }

    public void onDestroy() {
        try {
            JDDialog jDDialog = this.openAppConfirmDialog;
            if (jDDialog != null && jDDialog.isShowing()) {
                this.openAppConfirmDialog.dismiss();
            }
            this.openAppConfirmDialog = null;
            stopLoading();
            MtaData mtaData = this.mtaData;
            if (mtaData != null) {
                mtaData.onDestroy();
            }
            removeAllViews();
            ViewParent parent = getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this);
            }
            if (Log.D) {
                Log.d(this.TAG, "ondestroy");
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
        try {
            destroy();
        } catch (Exception e3) {
            if (Log.E) {
                e3.printStackTrace();
            }
        }
        BaseWebChromeClient baseWebChromeClient = this.mWebChromeClient;
        if (baseWebChromeClient != null) {
            baseWebChromeClient.destroyUploadMessage();
        }
        this.mWebChromeClient = null;
    }

    @Override // com.tencent.smtt.sdk.DownloadListener
    public void onDownloadStart(final String str, String str2, String str3, String str4, long j2) {
        showOpenThirdAppConfirm("\u5c06\u4f7f\u7528\u5916\u90e8\u6d4f\u89c8\u5668\u6253\u5f00", new View.OnClickListener() { // from class: com.jingdong.common.web.ui.X5WebView.3
            {
                X5WebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                X5WebView.toBrowser(Uri.parse(str));
            }
        }, null);
    }

    public void onInactive() {
        if (this.installer != null && this.useXBridge && (getWebViewClient() instanceof BaseWebViewClient) && ((BaseWebViewClient) getWebViewClient()).isPageStartedOnce()) {
            this.installer.onPause();
        }
    }

    @Override // com.tencent.smtt.sdk.WebView, android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (OKLog.D) {
            OKLog.d(this.TAG, "former view== " + view);
        }
        WebView.HitTestResult hitTestResult = getHitTestResult();
        int type = hitTestResult.getType();
        if (OKLog.D) {
            OKLog.d(this.TAG, "onLongClick  type:" + type);
        }
        if (type != 5 && type != 8) {
            if (WebDebug.report) {
                WebDebug.showDebugDialog(getContext());
                return false;
            }
            return false;
        }
        hitTestResult.getExtra();
        new CommonDialogController(getContext()).configData("\u662f\u5426\u5b58\u50a8\u56fe\u50cf?", new View.OnClickListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0066: INVOKE 
              (wrap: com.jingdong.common.utils.CommonDialogController : 0x005b: CONSTRUCTOR 
              (wrap: android.content.Context : 0x0057: INVOKE (r4v0 'this' com.jingdong.common.web.ui.X5WebView A[IMMUTABLE_TYPE, THIS]) type: VIRTUAL call: android.widget.FrameLayout.getContext():android.content.Context A[MD:():android.content.Context (s), WRAPPED])
             A[MD:(android.content.Context):void (m), WRAPPED] (LINE:10) call: com.jingdong.common.utils.CommonDialogController.<init>(android.content.Context):void type: CONSTRUCTOR)
              ("￦ﾘﾯ￥ﾐﾦ￥ﾭﾘ￥ﾂﾨ￥ﾛﾾ￥ﾃﾏ?")
              (wrap: android.view.View$OnClickListener : 0x0060: CONSTRUCTOR 
              (r4v0 'this' com.jingdong.common.web.ui.X5WebView A[IMMUTABLE_TYPE, THIS])
              (r5 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.web.ui.X5WebView, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.web.ui.X5WebView.6.<init>(com.jingdong.common.web.ui.X5WebView, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.utils.CommonDialogController.configData(java.lang.String, android.view.View$OnClickListener):void A[MD:(java.lang.String, android.view.View$OnClickListener):void (m)] (LINE:10) in method: com.jingdong.common.web.ui.X5WebView.onLongClick(android.view.View):boolean, file: classes12.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D
            if (r0 == 0) goto L1a
            java.lang.String r0 = r4.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "former view== "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.jingdong.sdk.oklog.OKLog.d(r0, r5)
        L1a:
            com.tencent.smtt.sdk.WebView$HitTestResult r5 = r4.getHitTestResult()
            int r0 = r5.getType()
            boolean r1 = com.jingdong.sdk.oklog.OKLog.D
            if (r1 == 0) goto L3c
            java.lang.String r1 = r4.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onLongClick  type:"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.jingdong.sdk.oklog.OKLog.d(r1, r2)
        L3c:
            r1 = 5
            if (r0 == r1) goto L51
            r1 = 8
            if (r0 != r1) goto L44
            goto L51
        L44:
            boolean r5 = com.jingdong.common.web.WebDebug.report
            if (r5 == 0) goto L4f
            android.content.Context r5 = r4.getContext()
            com.jingdong.common.web.WebDebug.showDebugDialog(r5)
        L4f:
            r5 = 0
            return r5
        L51:
            java.lang.String r5 = r5.getExtra()
            com.jingdong.common.utils.CommonDialogController r0 = new com.jingdong.common.utils.CommonDialogController
            android.content.Context r1 = r4.getContext()
            r0.<init>(r1)
            com.jingdong.common.web.ui.X5WebView$6 r1 = new com.jingdong.common.web.ui.X5WebView$6
            r1.<init>()
            java.lang.String r5 = "\u662f\u5426\u5b58\u50a8\u56fe\u50cf?"
            r0.configData(r5, r1)
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.ui.X5WebView.onLongClick(android.view.View):boolean");
    }

    @Override // com.tencent.smtt.sdk.WebView, com.jd.xbridge.base.IBridgeWebView
    public void onPause() {
        super.onPause();
        if (this.installer != null && this.useXBridge && (getWebViewClient() instanceof BaseWebViewClient) && ((BaseWebViewClient) getWebViewClient()).isPageStartedOnce()) {
            this.installer.onStop();
        }
    }

    @Override // com.tencent.smtt.sdk.WebView, com.jd.xbridge.base.IBridgeWebView
    public void onResume() {
        super.onResume();
        if (this.installer != null && this.useXBridge && (getWebViewClient() instanceof BaseWebViewClient) && ((BaseWebViewClient) getWebViewClient()).isPageStartedOnce()) {
            this.installer.onStart();
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        Log.d(this.TAG, "onScrollChanged:" + i2 + "  " + i3 + "  " + i4 + "  " + i5);
        if (useWebScrollListener()) {
            setWebScrollChanged(i2, i3, i4, i5);
        }
    }

    @Override // com.jd.xbridge.base.IBridgeWebView
    public void onStart() {
    }

    @Override // com.jd.xbridge.base.IBridgeWebView
    public void onStop() {
    }

    public void recordMediaBack(Intent intent, boolean z, int i2, int i3) {
        BaseWebChromeClient baseWebChromeClient = this.mWebChromeClient;
        if (baseWebChromeClient != null) {
            baseWebChromeClient.recordMediaBack(intent, z, i2, i3);
        }
    }

    public void removeWebViewScrollListener(WebViewScrollListener webViewScrollListener) {
        List<WebViewScrollListener> list = this.mWebScrollListeners;
        if (list == null || !list.contains(webViewScrollListener)) {
            return;
        }
        this.mWebScrollListeners.remove(webViewScrollListener);
    }

    public void selectFileBack(Intent intent, int i2, int i3, boolean z) {
        BaseWebChromeClient baseWebChromeClient = this.mWebChromeClient;
        if (baseWebChromeClient != null) {
            baseWebChromeClient.selectFileBack(intent, i2, i3, z);
        }
    }

    public void selectImageBack(Intent intent, int i2, int i3, boolean z) {
        BaseWebChromeClient baseWebChromeClient = this.mWebChromeClient;
        if (baseWebChromeClient != null) {
            baseWebChromeClient.selectImageBack(intent, i2, i3, z);
        }
    }

    public void selectVideoBack(Intent intent, int i2, int i3, boolean z) {
        BaseWebChromeClient baseWebChromeClient = this.mWebChromeClient;
        if (baseWebChromeClient != null) {
            baseWebChromeClient.selectVideoBack(intent, i2, i3, z);
        }
    }

    public void setCachedWebView(boolean z) {
        this.cachedWebView = z;
    }

    public void setCpsMtaData(Bundle bundle) {
        MtaData mtaData = this.mtaData;
        mtaData.setCpsMtaData(mtaData.parseAndGetCpsMtaData(bundle));
    }

    public void setExtraUaString(String str) {
        this.extraUserAgent = str;
    }

    public void setMtaDataInjected(boolean z) {
        MtaData mtaData = this.mtaData;
        if (mtaData != null) {
            mtaData.setMtaDataInjected(z);
        }
    }

    public void setUseCache(boolean z) {
        if (z) {
            WebViewHelper.enableWebViewCache(this, this.mContext);
            WebSettings webSettings = this.settings;
            if (webSettings != null) {
                webSettings.setCacheMode(-1);
                return;
            }
            return;
        }
        WebViewHelper.disableWebViewCache(this);
        WebSettings webSettings2 = this.settings;
        if (webSettings2 != null) {
            webSettings2.setCacheMode(2);
        }
    }

    @Override // com.tencent.smtt.sdk.WebView
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        super.setWebChromeClient(webChromeClient);
        if (webChromeClient instanceof BaseWebChromeClient) {
            this.mWebChromeClient = (BaseWebChromeClient) webChromeClient;
        }
    }

    public void setWebScrollChanged(int i2, int i3, int i4, int i5) {
        WebViewScrollListener webViewScrollListener = this.mScrollListener;
        if (webViewScrollListener != null) {
            webViewScrollListener.onScrollChanged(i2, i3, i4, i5);
        }
        List<WebViewScrollListener> list = this.mWebScrollListeners;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<WebViewScrollListener> it = this.mWebScrollListeners.iterator();
        while (it.hasNext()) {
            it.next().onScrollChanged(i2, i3, i4, i5);
        }
    }

    public void setWebViewScrollListener(WebViewScrollListener webViewScrollListener) {
        this.mScrollListener = webViewScrollListener;
    }

    public void showOpenThirdAppConfirm(String str, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        if (TextUtils.isEmpty(str)) {
            str = "\u5373\u5c06\u4f7f\u7528\u4eac\u4e1c\u5916\u7684\u5e94\u7528\u6253\u5f00";
        }
        JDDialog jDDialog = this.openAppConfirmDialog;
        if (jDDialog != null && jDDialog.isShowing()) {
            this.openAppConfirmDialog.dismiss();
        }
        if (this.openAppConfirmDialog == null) {
            this.openAppConfirmDialog = JDDialogFactory.getInstance().createJdDialogWithStyle2(getContext(), str, "\u53d6\u6d88", "\u540c\u610f");
        }
        this.openAppConfirmDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.ui.X5WebView.4
            {
                X5WebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View.OnClickListener onClickListener3 = onClickListener;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                X5WebView.this.openAppConfirmDialog.dismiss();
            }
        });
        this.openAppConfirmDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.ui.X5WebView.5
            {
                X5WebView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View.OnClickListener onClickListener3 = onClickListener2;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                X5WebView.this.openAppConfirmDialog.dismiss();
            }
        });
        if (!(getContext() instanceof Activity) || ((Activity) getContext()).isFinishing()) {
            return;
        }
        this.openAppConfirmDialog.show();
    }

    public void updateMtaData(String str) {
        MtaData mtaData = this.mtaData;
        if (mtaData != null) {
            mtaData.setMtaData(str);
        }
    }

    public boolean useWebScrollListener() {
        List<WebViewScrollListener> list;
        return this.mScrollListener != null || ((list = this.mWebScrollListeners) != null && list.size() > 0);
    }

    protected void config(long j2, boolean z) {
        requestFocus();
        setDownloadListener(this);
        WebSettings settings = getSettings();
        this.settings = settings;
        if (settings == null) {
            ToastUtils.shortToast(getContext(), R.string.error_open_m_page);
            ((Activity) this.mContext).finish();
            return;
        }
        setScrollBarStyle(33554432);
        try {
            this.settings.setJavaScriptEnabled(true);
            this.settings.setUseWideViewPort(true);
            this.settings.setLoadWithOverviewMode(true);
            this.settings.setAllowFileAccessFromFileURLs(false);
            this.settings.setAllowUniversalAccessFromFileURLs(false);
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(this.orgUserAgent)) {
            try {
                ExceptionReporter.reportWebViewCommonErrorNoIp("X5DuplicatedConfig", null, "context=" + (getContext() != null ? getContext().getClass().getName() : null), ExceptionReporter.getStackStringFromException(new Throwable("Get trace")));
            } catch (Throwable th2) {
                if (Log.E) {
                    th2.printStackTrace();
                }
            }
        }
        this.orgUserAgent = this.settings.getUserAgentString();
        WebViewHelper.initUA(this, false);
        MtaData mtaData = new MtaData(this);
        this.mtaData = mtaData;
        mtaData.setMtaData(WebViewHelper.getJdUaInfoEncryptUuid().toString());
        MtaData mtaData2 = this.mtaData;
        addJavascriptInterface(mtaData2, mtaData2.getName());
        this.settings.setSavePassword(false);
        WebViewHelper.clearBugJs(this);
        requestFocus();
        this.settings.setBuiltInZoomControls(false);
        this.settings.setGeolocationEnabled(true);
        this.settings.setPluginState(WebSettings.PluginState.ON);
        if (SDKUtils.getSDKVersion() >= 21) {
            this.settings.setMixedContentMode(0);
        }
        this.settings.setMediaPlaybackRequiresUserGesture(false);
        setWebChromeClient(new BaseWebChromeClient(this.mContext) { // from class: com.jingdong.common.web.ui.X5WebView.1
            {
                X5WebView.this = this;
            }
        });
        setWebViewClient(new BaseWebViewClient() { // from class: com.jingdong.common.web.ui.X5WebView.2
            {
                X5WebView.this = this;
            }

            @Override // com.tencent.smtt.sdk.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str != null && str.startsWith("openapp")) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.addCategory("android.intent.category.BROWSABLE");
                    if (X5WebView.this.mContext instanceof BaseActivity) {
                        ((BaseActivity) X5WebView.this.mContext).startActivityNoException(intent);
                        return true;
                    }
                    try {
                        X5WebView.this.mContext.startActivity(intent);
                        return true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return true;
                    }
                }
                webView.loadUrl(str);
                return true;
            }
        });
        X5InitUtil.clearImeiInfoFromSp(this.mContext);
        WebViewHelper.saveX5WebViewBasicInfo(this);
        if (needLongClick()) {
            setOnLongClickListener(this);
        }
        if (!WebViewHelper.firstX5WebViewCreateFinished && j2 > 0) {
            reportFirstX5Performance(j2, z);
        }
        WebViewHelper.firstX5WebViewCreateFinished = true;
    }

    public X5WebView(long j2, boolean z, Context context) {
        super(context);
        this.TAG = X5WebView.class.getSimpleName();
        this.cachedWebView = false;
        this.openAppConfirmDialog = null;
        this.installer = null;
        this.mContext = context;
        config(j2, z);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = X5WebView.class.getSimpleName();
        this.cachedWebView = false;
        this.openAppConfirmDialog = null;
        this.installer = null;
        this.mContext = context;
        config();
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = X5WebView.class.getSimpleName();
        this.cachedWebView = false;
        this.openAppConfirmDialog = null;
        this.installer = null;
        this.mContext = context;
        config();
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i2, boolean z) {
        super(context, attributeSet, i2, z);
        this.TAG = X5WebView.class.getSimpleName();
        this.cachedWebView = false;
        this.openAppConfirmDialog = null;
        this.installer = null;
        this.mContext = context;
        config();
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i2, Map<String, Object> map, boolean z) {
        super(context, attributeSet, i2, map, z);
        this.TAG = X5WebView.class.getSimpleName();
        this.cachedWebView = false;
        this.openAppConfirmDialog = null;
        this.installer = null;
        this.mContext = context;
        config();
    }
}
