package com.jingdong.common.web.uibinder.impl;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.BaseWebChromeClient;
import com.jingdong.common.web.javainterface.impl.AndroidNavi;
import com.jingdong.common.web.javainterface.impl.AndroidSound;
import com.jingdong.common.web.javainterface.impl.AndroidUploadImg;
import com.jingdong.common.web.javainterface.impl.BindGiftcard;
import com.jingdong.common.web.javainterface.impl.ConsoleInterface;
import com.jingdong.common.web.javainterface.impl.EventSeries;
import com.jingdong.common.web.javainterface.impl.IdCard;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.javainterface.impl.JDFunction;
import com.jingdong.common.web.javainterface.impl.JDPaySDK;
import com.jingdong.common.web.javainterface.impl.JSControlHelper;
import com.jingdong.common.web.javainterface.impl.JSLoginUserBase;
import com.jingdong.common.web.javainterface.impl.JavaScriptCallIntelligentAssistance;
import com.jingdong.common.web.javainterface.impl.MobileLogin;
import com.jingdong.common.web.javainterface.impl.MobileNavi;
import com.jingdong.common.web.javainterface.impl.MtaHelper;
import com.jingdong.common.web.javainterface.impl.Performance;
import com.jingdong.common.web.javainterface.impl.ScreenConfig;
import com.jingdong.common.web.javainterface.impl.SettleAccounts;
import com.jingdong.common.web.javainterface.impl.ShareHelper;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.javainterface.impl.XWidget;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.uibinder.BaseUiBinder;
import com.jingdong.common.web.uilistener.IActivityResult;
import com.jingdong.common.web.uilistener.IWebViewUrlInterceptor;
import com.jingdong.common.web.uilistener.TitleBackListener;
import com.jingdong.common.web.uilistener.impl.ActivityResultImpl;
import com.jingdong.common.web.uilistener.impl.CloseButtonListenerImpl;
import com.jingdong.common.web.uilistener.impl.TitleBackListenerImpl;
import com.jingdong.common.web.uilistener.impl.TitleChangeListenerImpl;
import com.jingdong.common.web.uilistener.impl.TitleRightTextViewClickListenerImpl;
import com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl;
import com.jingdong.common.web.uilistener.impl.WebViewNaviListenerImpl;
import com.jingdong.common.web.uilistener.impl.WebViewUrlInterceptorImpl;
import com.jingdong.common.web.urlcheck.impl.CheckNativeImpl;
import com.jingdong.common.web.urlcheck.impl.ImmersiveCheckImpl;
import com.jingdong.common.web.urlcheck.impl.JdAuthCheckImpl;
import com.jingdong.common.web.urlcheck.impl.LocCheckImpl;
import com.jingdong.common.web.urlcheck.impl.PayCheckImpl;
import com.jingdong.common.web.urlcheck.impl.SearchMyIdPlusImpl;
import com.jingdong.common.web.urlcheck.impl.SecondLevelPageCheckImpl;
import com.jingdong.common.web.urlcheck.impl.ShareGiftCheckImpl;
import com.jingdong.common.web.urlcheck.impl.SharePinCheckImpl;
import com.jingdong.common.web.urlcheck.impl.UrlCheckImpl;
import com.jingdong.common.web.util.JDWebViewInitRecord;
import com.jingdong.common.web.util.WebSwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.tencent.smtt.sdk.WebChromeClient;

/* loaded from: classes12.dex */
public class CommonWebUiBinder extends BaseUiBinder implements IActivityResult, TitleBackListener {
    private ActivityResultImpl activityResultImpl;
    private ImmersiveCheckImpl checkImmersiveImpl;
    private CheckNativeImpl checkNativeImpl;
    private TitleBackListenerImpl titleBackImpl;
    private UrlCheckImpl urlCheckImpl;
    private final String TAG = CommonWebUiBinder.class.getSimpleName();
    private JDWebViewInitRecord initRecord = new JDWebViewInitRecord(System.currentTimeMillis());

    private boolean onBindCheckM2NativeUrl() {
        if (Log.D) {
            if (getWebEntity().isNeedCheckToNative) {
                if (getWebEntity().preCheckedNative) {
                    Log.d("PreCheckNative", "Native checked before opening WebView(CommonMFragment), skip first native checking on bind.");
                } else {
                    Log.d("PreCheckNative", "Did NOT check native before opening WebView(CommonMFragment), will check it on bind.");
                }
            } else {
                Log.d("PreCheckNative", "No need to check native.");
            }
        }
        if (!getWebEntity().preCheckedNative && getWebEntity().isNeedCheckToNative) {
            if (this.checkNativeImpl == null) {
                this.checkNativeImpl = new CheckNativeImpl(this);
            }
            if (SwitchQueryFetcher.getSwitchBooleanValue(WebSwitchQueryFetcher.FIX_CHECK_URL, false)) {
                String str = null;
                String str2 = getWebEntity().urlMap != null ? getWebEntity().urlMap.get((Object) RemoteMessageConst.TO) : null;
                if (!TextUtils.isEmpty(str2)) {
                    str = str2;
                } else if (!TextUtils.isEmpty(getWebEntity().url)) {
                    str = getWebEntity().url;
                }
                if (!TextUtils.isEmpty(str)) {
                    String trim = str.trim();
                    return this.checkNativeImpl.checkM2Native(trim, true, trim.startsWith("https%") || trim.startsWith("http%"));
                }
            } else if (getWebEntity().urlMap != null && !TextUtils.isEmpty(getWebEntity().urlMap.get((Object) RemoteMessageConst.TO))) {
                return this.checkNativeImpl.checkM2Native(getWebEntity().urlMap.get((Object) RemoteMessageConst.TO), true);
            } else {
                if (!TextUtils.isEmpty(getWebEntity().url)) {
                    return this.checkNativeImpl.checkM2Native(getWebEntity().url, true);
                }
            }
        }
        return false;
    }

    public void bindJavaScriptInterface() {
        if (getJdWebView().isPreRender()) {
            return;
        }
        addJavascriptInterface(new WebJavaScript(this));
        addJavascriptInterface(new BindGiftcard(this));
        SettleAccounts settleAccounts = new SettleAccounts(this);
        settleAccounts.getDataFromBundle(getWebEntity().mBundle);
        addJavascriptInterface(settleAccounts);
        addJavascriptInterface(new JDPaySDK(this));
        addJavascriptInterface(new AndroidSound(this));
        addJavascriptInterface(new AndroidUploadImg(this));
        addJavascriptInterface(new MtaHelper(this));
        addJavascriptInterface(new AndroidNavi(this));
        addJavascriptInterface(new IdCard(this));
        addJavascriptInterface(new JSLoginUserBase(this));
        addJavascriptInterface(new JSControlHelper(this));
        try {
            addJavascriptInterface(new JavaScriptCallIntelligentAssistance(this));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        addJavascriptInterface(new JDFunction(getBaseActivity(), this));
        addJavascriptInterface(new MobileLogin(this));
        addJavascriptInterface(new ShareHelper(this, getWebEntity().isIgnoreShare, this.mHandler));
        addJavascriptInterface(new EventSeries(this));
        addJavascriptInterface(new MobileNavi(this));
        addJavascriptInterface(new JDAppUnite(this));
        if (WebPerfManager.getInstance().isEnable()) {
            Performance performance2 = new Performance(this);
            addJavascriptInterface(performance2);
            if (getJdWebView() != null) {
                getJdWebView().setTimingReportEnable(true, performance2);
            }
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue(ScreenConfig.SWITCH_NAME, false)) {
            addJavascriptInterface(new ScreenConfig(this));
        }
        addJavascriptInterface(new ConsoleInterface());
        addJavascriptInterface(new XWidget(this));
    }

    public void bindUiLintener() {
        getJdWebView().setWebViewClientListener(new WebViewClientListenerImpl(this));
        if (getWebEntity().isUseCloseBtn) {
            getJdWebView().setCloseButtonListener(new CloseButtonListenerImpl(this));
        }
        getJdWebView().setWebViewNaviListener(new WebViewNaviListenerImpl(this));
        if (this.titleBackImpl == null) {
            this.titleBackImpl = new TitleBackListenerImpl(this);
        }
        getJdWebView().setTitleBackListener(this.titleBackImpl);
        getJdWebView().setTitleChangeListener(new TitleChangeListenerImpl(this));
        getJdWebView().setTitleRightTextViewClickListener(new TitleRightTextViewClickListenerImpl(this));
        getJdWebView().setupShadowTrigger();
    }

    public void bindUrlIntercept() {
        if (this.urlCheckImpl == null) {
            this.urlCheckImpl = new UrlCheckImpl(this);
        }
        getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new PayCheckImpl(this));
        getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new JdAuthCheckImpl(this));
        getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new LocCheckImpl(this));
        getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new SearchMyIdPlusImpl(this));
        getWebViewUrlInterceptor().addUrlShouldOverrideLoading(this.urlCheckImpl);
        if (getWebEntity().isNeedCheckToNative) {
            if (this.checkNativeImpl == null) {
                this.checkNativeImpl = new CheckNativeImpl(this);
            }
            getWebViewUrlInterceptor().addUrlShouldOverrideLoading(this.checkNativeImpl);
        }
        if ((getBaseActivity() instanceof IMainActivity) || getWebEntity().showSubPage) {
            getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new SecondLevelPageCheckImpl(this));
        }
        getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new ShareGiftCheckImpl(this));
        if (!TextUtils.isEmpty(LoginUserBase.getUserPin())) {
            getWebViewUrlInterceptor().addUrlShouldOverrideLoading(new SharePinCheckImpl(this));
        }
        getWebViewUrlInterceptor().addUrlCheckOnPageStart(this.urlCheckImpl);
        if (this.checkImmersiveImpl == null) {
            this.checkImmersiveImpl = new ImmersiveCheckImpl(this);
        }
        getWebViewUrlInterceptor().addUrlCheckOnPageStartAfterDefaultNavi(this.checkImmersiveImpl);
        getJdWebView().setWebViewInterceptUrlListener(getWebViewUrlInterceptor());
    }

    public JDWebViewInitRecord getInitRecord() {
        return this.initRecord;
    }

    @Override // com.jingdong.common.web.uilistener.TitleBackListener
    public boolean keyBack() {
        try {
            WebChromeClient webChromeClient = getJdWebView().getWebView().getWebChromeClient();
            if (webChromeClient instanceof BaseWebChromeClient) {
                if (((BaseWebChromeClient) webChromeClient).onBack()) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        if (this.titleBackImpl == null) {
            this.titleBackImpl = new TitleBackListenerImpl(this);
        }
        return this.titleBackImpl.keyBack();
    }

    @Override // com.jingdong.common.web.uibinder.BaseUiBinder
    protected IWebViewUrlInterceptor newUrlInterceptor() {
        return new WebViewUrlInterceptorImpl(this);
    }

    @Override // com.jingdong.common.web.uilistener.IActivityResult
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (this.activityResultImpl == null) {
            this.activityResultImpl = new ActivityResultImpl(this);
        }
        this.activityResultImpl.onActivityResult(i2, i3, intent);
    }

    @Override // com.jingdong.common.web.uibinder.BaseUiBinder
    public void onBindUi() {
        if (this.jdWebViewUi != null) {
            if (!onBindCheckM2NativeUrl()) {
                JDWebViewInitRecord.RecordHelper.markPhaseEnd(this.initRecord, "bindM2Native");
                if (Log.D) {
                    Log.d(this.TAG, "CommonWebUiBinder->onBindUi.onBindCheckM2NativeUrl=false");
                }
                bindJavaScriptInterface();
                JDWebViewInitRecord.RecordHelper.markPhaseEnd(this.initRecord, "bindJs");
                bindUiLintener();
                JDWebViewInitRecord.RecordHelper.markPhaseEnd(this.initRecord, "bindListener");
                bindUrlIntercept();
                JDWebViewInitRecord.RecordHelper.markPhaseEnd(this.initRecord, "bindIntercept");
                if (TextUtils.isEmpty(getWebEntity().urlFromIntent)) {
                    return;
                }
                if (this.checkImmersiveImpl == null) {
                    this.checkImmersiveImpl = new ImmersiveCheckImpl(this);
                }
                this.checkImmersiveImpl.checkImmersive(getWebEntity().urlFromIntent, true);
                JDWebViewInitRecord.RecordHelper.markPhaseEnd(this.initRecord, "bindImmersive");
                return;
            }
            JDWebViewInitRecord.RecordHelper.markPhaseEnd(this.initRecord, "bindM2Native");
            if (Log.D) {
                Log.d(this.TAG, "CommonWebUiBinder->onBindUi.onBindCheckM2NativeUrl=true");
            }
            getWebEntity().jumpOutSuc = true;
        }
    }

    public void onClickEvent(String str, String str2) {
        if (Log.D) {
            Log.d(this.TAG, "onClickEvent clickId-->> " + str);
        }
        JDMtaUtils.onClick(getBaseActivity(), str, "CommonMFragment", str2);
    }

    @Override // com.jingdong.common.web.uilistener.TitleBackListener
    public boolean titleBack() {
        if (this.titleBackImpl == null) {
            this.titleBackImpl = new TitleBackListenerImpl(this);
        }
        return this.titleBackImpl.titleBack();
    }

    @Override // com.jingdong.common.web.uibinder.BaseUiBinder, com.jingdong.common.web.uibinder.IWebUiBinder
    public void unbindUi() {
        super.unbindUi();
    }
}
