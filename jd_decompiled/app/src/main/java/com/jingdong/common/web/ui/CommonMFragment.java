package com.jingdong.common.web.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridOfflineLoader;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.xconsole.XLog;
import com.jingdong.cleanmvp.presenter.BaseNavigator;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.MessageRedObserver;
import com.jingdong.common.messagecenter.view.NewMessagRedManager;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.R;
import com.jingdong.common.web.WebDebug;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.javainterface.WebPlugin;
import com.jingdong.common.web.javainterface.impl.AndroidSound;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.javainterface.impl.MobileLogin;
import com.jingdong.common.web.javainterface.impl.ScreenConfig;
import com.jingdong.common.web.managers.JSVoiceManager;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.GenTokenCallback;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uibinder.impl.CommonWebUiBinder;
import com.jingdong.common.web.uilistener.IActivityResult;
import com.jingdong.common.web.uilistener.TitleBackListener;
import com.jingdong.common.web.urlcheck.impl.PayCheckImpl;
import com.jingdong.common.web.util.BundleUtils;
import com.jingdong.common.web.util.ChannelPrivacyConfirmUtil;
import com.jingdong.common.web.util.JDWebViewInitRecord;
import com.jingdong.common.web.util.JdWebviewBlackListUtil;
import com.jingdong.common.web.util.MediaUtils;
import com.jingdong.common.web.util.MultiMedia;
import com.jingdong.common.web.util.SysBug5497Workaround;
import com.jingdong.common.web.util.UrlRedirectHelper;
import com.jingdong.common.web.util.WebLogHelper;
import com.jingdong.common.web.util.WebSwitchQueryFetcher;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.common.web.xrender.XRenderManager;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.perfmonitor.PerfMonitor;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebView;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class CommonMFragment extends JDTabFragment implements MessageRedObserver, IJdWebViewUi, IXFragment {
    private SysBug5497Workaround bug5497wa;
    private JDWebViewInitRecord initRecord;
    protected JDWebView mJdWebView;
    private WebEntity webEntity;
    private IWebUiBinder webUiBinder;
    private final String TAG = CommonMFragment.class.getSimpleName();
    private long currentInitStartTime = System.currentTimeMillis();
    private Boolean isHideProgress = Boolean.FALSE;
    private boolean isVisibleHintCalled = false;
    private boolean isDefaultVisibleCallbackDisabled = false;
    private boolean firstLoad = true;
    private boolean autoLoadWeb = true;
    private boolean enableHtmlPreload = true;
    private boolean autoSendReadyEvent = true;

    /* renamed from: com.jingdong.common.web.ui.CommonMFragment$8 */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$web$ui$GenTokenCallback$State;

        static {
            int[] iArr = new int[GenTokenCallback.State.values().length];
            $SwitchMap$com$jingdong$common$web$ui$GenTokenCallback$State = iArr;
            try {
                iArr[GenTokenCallback.State.GEN_TOKEN_READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$web$ui$GenTokenCallback$State[GenTokenCallback.State.GEN_TOKEN_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$web$ui$GenTokenCallback$State[GenTokenCallback.State.GEN_TOKEN_ING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$web$ui$GenTokenCallback$State[GenTokenCallback.State.GEN_TOKEN_FAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: a */
    public /* synthetic */ void b(String str) {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.loadLocalHtml(this.webEntity.url, str);
        }
    }

    /* renamed from: c */
    public /* synthetic */ void d(Runnable runnable, GenTokenCallback.State state) {
        int i2 = AnonymousClass8.$SwitchMap$com$jingdong$common$web$ui$GenTokenCallback$State[state.ordinal()];
        if (i2 == 1) {
            post(runnable);
            Log.xLogD("loadHtml", "\u65b0\u94fe\u8def gentoken ready");
        } else if (i2 == 2) {
            post(runnable);
            Log.xLogD("loadHtml", "\u65b0\u94fe\u8def gentoken success");
        } else if (i2 == 3) {
            Log.xLogD("loadHtml", "\u65b0\u94fe\u8def gentoken ing");
        } else if (i2 != 4) {
        } else {
            Log.xLogD("loadHtml", "\u65b0\u94fe\u8def gentoken fail");
            if (SwitchQueryFetcher.getSwitchBooleanValue("ttt_gentokenFail_loadUrl", false)) {
                this.mJdWebView.loadUrl(this.webEntity.url);
            }
        }
    }

    private void dealSavedInstanceState(Bundle bundle) {
        com.jingdong.corelib.utils.Log.d(this.TAG, "dealSavedInstanceState,bundle:" + bundle);
        if (bundle == null) {
            return;
        }
        this.webEntity.jsBridgeEntity.canJumpToPay = bundle.getBoolean("canJumpToPay", true);
        if (this.webEntity.jsBridgeEntity.canJumpToPay) {
            return;
        }
        post(new Runnable() { // from class: com.jingdong.common.web.ui.CommonMFragment.2
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                CommonMFragment.this.thisActivity.finish();
            }
        });
    }

    private void doRedirectUrl() {
        if (UrlRedirectHelper.isSwitchOn()) {
            try {
                this.webEntity.url = UrlRedirectHelper.getInstance().getRedirectUrl(this.webEntity.url);
                WebEntity webEntity = this.webEntity;
                String decode = URLDecoder.decode(webEntity.urlMap.get((Object) webEntity.action), "utf-8");
                WebEntity webEntity2 = this.webEntity;
                webEntity2.urlMap.put(webEntity2.action, UrlRedirectHelper.getInstance().getRedirectUrl(decode));
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: e */
    public /* synthetic */ void f() {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null && jDWebView.isPreRender() && this.firstLoad && this.autoLoadWeb) {
            this.firstLoad = false;
            return;
        }
        WebEntity webEntity = this.webEntity;
        if (webEntity.jumpOutSuc) {
            return;
        }
        if (!webEntity.isISVLoginObfuscator && webEntity.needGenToken) {
            gentoken();
            return;
        }
        if (WebDebug.report) {
            WebDebug.log("webview", "[genToken] Load url directly, no need to gentoken:" + this.webEntity.url, this);
        }
        if (com.jingdong.corelib.utils.Log.D || WebLogHelper.showXLog) {
            xLogD("[genToken] Load url directly, no need to gentoken, url: " + this.webEntity.url);
        }
        this.mJdWebView.loadUrl(this.webEntity.url);
        ExceptionReporter.reportWebViewCommonError("webViewNoNeedGenToken", this.webEntity.url, "webview loadUrl without requiring genToken! cause url: " + this.webEntity.url + "  check no need", "added V6.6.5 class CommonMFragment -->loadWeb");
    }

    /* renamed from: g */
    public /* synthetic */ void h() {
        this.thisActivity.finish();
    }

    private void getDataFromBundle(Bundle bundle) {
        WebEntity webEntity = new WebEntity();
        this.webEntity = webEntity;
        webEntity.init(bundle);
        if (WebDebug.report) {
            WebDebug.log("webview", "[getDataFromBundle]bundle:" + bundle.toString(), this);
        }
        URLParamMap uRLParamMap = this.webEntity.urlMap;
        String str = uRLParamMap != null ? uRLParamMap.get((Object) RemoteMessageConst.TO) : null;
        if (TextUtils.isEmpty(str)) {
            str = this.webEntity.url;
        }
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        WebEntity webEntity2 = this.webEntity;
        if (webEntity2.canUseDarkMode) {
            webEntity2.canUseDarkMode = WebUtils.canUseDarkMode(str);
        }
        this.isHideProgress = Boolean.valueOf(WebUtils.isHideProgress(str) || this.webEntity.jwebprog == 0);
        if (SwitchQueryFetcher.getSwitchBooleanValue("placeholderEnable", false)) {
            if (TextUtils.isEmpty(this.webEntity.loadingPlaceHolder)) {
                this.webEntity.loadingPlaceHolder = WebUtils.getLoadingPlaceHolder(str);
            }
        } else {
            this.webEntity.loadingPlaceHolder = null;
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue("hybridErrView", true)) {
            WebEntity webEntity3 = this.webEntity;
            webEntity3.showErrView = WebUtils.getErrViewSwitch(str, webEntity3.showErrView);
            return;
        }
        this.webEntity.showErrView = false;
    }

    /* renamed from: j */
    public /* synthetic */ void k(Runnable runnable, boolean z) {
        if (z) {
            post(runnable);
        } else {
            post(new Runnable() { // from class: com.jingdong.common.web.ui.e
                {
                    CommonMFragment.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    CommonMFragment.this.h();
                }
            });
        }
    }

    private void markPhaseEnd(String str) {
        if (this.initRecord == null) {
            this.initRecord = new JDWebViewInitRecord(this.currentInitStartTime);
        }
        this.initRecord.markPhaseEnd(str);
    }

    private void reportInitPerformance() {
        if (this.mJdWebView == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if ((iWebUiBinder instanceof CommonWebUiBinder) && ((CommonWebUiBinder) iWebUiBinder).getInitRecord() != null && ((CommonWebUiBinder) this.webUiBinder).getInitRecord().getTimeRecord() != null) {
            hashMap.put("UiBinderInit", ((CommonWebUiBinder) this.webUiBinder).getInitRecord().getTimeRecord());
        }
        JDWebViewInitRecord jDWebViewInitRecord = this.initRecord;
        if (jDWebViewInitRecord != null && jDWebViewInitRecord.getTimeRecord() != null) {
            hashMap.put("FragmentInit", this.initRecord.getTimeRecord());
        }
        if (!hashMap.isEmpty()) {
            this.mJdWebView.appendInitPerformanceData(hashMap);
        }
        JDWebView jDWebView = this.mJdWebView;
        JDWebViewInitRecord jDWebViewInitRecord2 = this.initRecord;
        jDWebView.reportInitPerformanceWithExtra(jDWebViewInitRecord2 != null ? jDWebViewInitRecord2.getTimeRecord() : null);
        this.initRecord = null;
    }

    private void xLogD(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        XLog.d(this.TAG, null, str, "webview");
    }

    private void xLogE(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        XLog.e(this.TAG, null, str, "webview");
        com.jd.libs.xdog.b.h(str + "\u6392\u67e5\u94fe\u63a5: https://cf.jd.com/pages/viewpage.action?pageId=307547104");
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment
    @Nullable
    protected BasePresenter createPresenter() {
        return null;
    }

    public void disableDefaultVisibleCallback(boolean z) {
        this.isDefaultVisibleCallbackDisabled = z;
    }

    public void gentoken() {
        OpenLinkTimeManager.getInstance().addExtraTiming("gentokenStart", System.currentTimeMillis());
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.addLoadEvent("gentokenStart");
        }
        WebEntity webEntity = this.webEntity;
        if (webEntity != null && WebSwitchQueryFetcher.newGentoken(webEntity.urlMap)) {
            WebUtils.newGentoken(this.webEntity, this.mJdWebView, this.thisActivity, this, true);
        } else {
            WebUtils.gentoken(this.webEntity, this.mJdWebView, this.thisActivity, this);
        }
    }

    public SysBug5497Workaround getBug5497WorkAround() {
        return this.bug5497wa;
    }

    @Override // androidx.fragment.app.Fragment, com.jingdong.common.web.ui.IJdWebViewUi
    public Context getContext() {
        BaseActivity baseActivity = this.thisActivity;
        return baseActivity == null ? super.getContext() : baseActivity;
    }

    public HybridOfflineLoader getHybridOfflineLoader() {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            return jDWebView.getHybridOfflineLoader();
        }
        return null;
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public JDWebView getJdWebView() {
        return this.mJdWebView;
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public NavigatorHolder getNaviHolder() {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            return jDWebView.getNavigatorHolder();
        }
        return null;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public String getPageParam() {
        return this.webEntity.url;
    }

    public String getUrl() {
        JDWebView jDWebView = this.mJdWebView;
        String finalUrl = jDWebView != null ? jDWebView.getFinalUrl() : null;
        if (TextUtils.isEmpty(finalUrl)) {
            WebEntity webEntity = this.webEntity;
            return webEntity != null ? webEntity.url : "";
        }
        return finalUrl;
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public WebEntity getWebEntity() {
        return this.webEntity;
    }

    @Override // com.jingdong.common.web.ui.IJdWebViewUi
    public IWebUiBinder getWebUiBinder() {
        if (this.webUiBinder == null) {
            this.webUiBinder = new CommonWebUiBinder();
        }
        return this.webUiBinder;
    }

    public boolean hasHybridAvailableFiles() {
        JDWebView jDWebView = this.mJdWebView;
        return jDWebView != null && jDWebView.hasHybridAvailableFiles();
    }

    public boolean hasHybridConfig() {
        JDWebView jDWebView = this.mJdWebView;
        return jDWebView != null && jDWebView.hasHybridOfflineConfig();
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.presenter.IBaseUI
    public void hideProgress() {
    }

    public void initJdWebView() {
        if (this.thisActivity instanceof IMainActivity) {
            this.mJdWebView.setIsMainFrameActivity(true);
            this.webEntity.isUseCloseBtn = false;
            this.mJdWebView.setTitleBackBtnVisible(false);
        } else if (!this.webEntity.isUseBackBtn) {
            this.mJdWebView.setTitleBackBtnVisible(false);
        }
        if (!TextUtils.isEmpty(this.webEntity.mTitle)) {
            this.mJdWebView.setFixedTitle(this.webEntity.mTitle);
        }
        this.mJdWebView.setSwitchImmersiveOpen(this.webEntity.switchImmersiveOpen);
        if (SwitchQueryFetcher.getSwitchBooleanValue("setTopBarPaddingSwitch", false) && this.webEntity.isTopBarGone) {
            if (com.jingdong.corelib.utils.Log.D || WebLogHelper.showXLog) {
                xLogD("setTopBarPaddingSwitch \u6253\u5f00 ");
            }
            this.mJdWebView.setTopBarGone(true);
        } else {
            this.mJdWebView.setTopBarGone(this.webEntity.isTopBarGone);
        }
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
        com.jingdong.corelib.utils.Log.d(this.TAG, "show right morebutton:" + this.webEntity.isShowMoreBtn);
        this.mJdWebView.setMoreBtnVisible(this.webEntity.isShowMoreBtn);
        this.mJdWebView.setStatusBarAlwaysTransparent(this.webEntity.statusBarAlwaysTransparent);
        if (TextUtils.equals(this.webEntity.mBundle.getString(MBaseKeyNames.KEY_ORIENTATION), MBaseKeyNames.VALUE_ORIENTATION) && this.thisActivity.getRequestedOrientation() != 0) {
            this.thisActivity.setRequestedOrientation(0);
            this.mJdWebView.setTopBarGone(true);
        }
        if (this.mJdWebView.isSystemCoreNotX5() && this.thisActivity.isStatusBarTintEnable() && this.thisActivity.statusBarTransparentEnable()) {
            this.bug5497wa = new SysBug5497Workaround(this.webUiBinder, this.thisActivity);
        }
        this.mJdWebView.setIsHideProgressForPrivacy(this.isHideProgress.booleanValue());
    }

    public void initWeb() {
        initJdWebView();
        markPhaseEnd("FragSetWeb");
        if (this.autoLoadWeb) {
            loadWeb();
        }
        markPhaseEnd("FragBeforeLoad");
    }

    protected JDWebView initWebView() {
        Pair<JDWebView, String> pair;
        JDWebView jDWebView;
        if (XRender.getInstance().isPreRender()) {
            pair = XRender.getInstance().getWebViewWithMta(getContext(), this.webEntity.url);
            jDWebView = pair != null ? (JDWebView) pair.first : null;
        } else {
            pair = null;
            jDWebView = null;
        }
        JDWebViewBuilder jDWebViewBuilder = new JDWebViewBuilder(getContext());
        if (TextUtils.isEmpty(this.webEntity.offlineId) && TextUtils.isEmpty(this.webEntity.preLoadId) && WebHybridUtils.hybridEnable && SwitchQueryFetcher.getSwitchBooleanValue("hybrid_view", true)) {
            if (jDWebView == null) {
                WebEntity webEntity = this.webEntity;
                webEntity.preLoadId = WebPreLoadHelper.preLoad(webEntity.url);
                WebEntity webEntity2 = this.webEntity;
                webEntity2.offlineId = WebOfflineLoaderManager.createOfflineLoader(webEntity2.url, this.enableHtmlPreload ? null : new IPreDownloadParamGetter.PreDownloadParamGetter() { // from class: com.jingdong.common.web.ui.CommonMFragment.1
                    {
                        CommonMFragment.this = this;
                    }

                    @Override // com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter.PreDownloadParamGetter, com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter
                    public String getDownloadUrl(OfflineFiles offlineFiles, String str) {
                        return null;
                    }
                });
            }
            this.webEntity.useHybrid = true;
        }
        doRedirectUrl();
        if (jDWebView == null) {
            JDWebViewBuilder errView = jDWebViewBuilder.setShouldReportInitRecord(false).setCanUseDarkMode(this.webEntity.canUseDarkMode).setEnableHybrid(this.webEntity.useHybrid).setHybridStarted((TextUtils.isEmpty(this.webEntity.offlineId) && TextUtils.isEmpty(this.webEntity.preLoadId)) ? false : true).setHybridUrl(this.webEntity.url).setOfflineId(this.webEntity.offlineId).setPreloadId(this.webEntity.preLoadId).setLoadingBg(this.webEntity.loadingPlaceHolder).setErrView(this.webEntity.showErrView);
            JsBridgeEntity jsBridgeEntity = this.webEntity.jsBridgeEntity;
            errView.setIsNeedShare(jsBridgeEntity != null && jsBridgeEntity.isNeedShare);
        } else {
            JDWebViewBuilder autoSendReadyEvent = jDWebViewBuilder.setShouldReportInitRecord(false).setCanUseDarkMode(this.webEntity.canUseDarkMode).setHybridUrl(this.webEntity.url).setHybridStarted(jDWebViewBuilder.hybridStarted || !TextUtils.isEmpty(this.webEntity.preLoadId)).setPreloadId(this.webEntity.preLoadId).setLoadingBg(this.webEntity.loadingPlaceHolder).setErrView(this.webEntity.showErrView).setAutoSendReadyEvent(this.autoSendReadyEvent);
            JsBridgeEntity jsBridgeEntity2 = this.webEntity.jsBridgeEntity;
            autoSendReadyEvent.setIsNeedShare(jsBridgeEntity2 != null && jsBridgeEntity2.isNeedShare);
        }
        Bundle bundle = this.webEntity.mBundle;
        if (bundle != null && bundle.getBoolean(OpenAppJumpController.KEY_OPEN_LINK, false)) {
            try {
                WebEntity webEntity3 = this.webEntity;
                if (webEntity3 != null && WebUtils.canPassGentoken(webEntity3.urlMap)) {
                    OpenLinkTimeManager.getInstance().addExtraTiming("passGentoken", System.currentTimeMillis());
                }
            } catch (Throwable unused) {
            }
            jDWebViewBuilder.addExtraSetting(OpenAppJumpController.KEY_OPEN_LINK, Boolean.TRUE);
        }
        JDWebView build = jDWebViewBuilder.build(jDWebView);
        if (pair != null && !TextUtils.isEmpty((CharSequence) pair.second)) {
            build.appendPerformanceData(WebPerfManager.BIZ_MSG, "xrender:" + ((String) pair.second));
        }
        return build;
    }

    public boolean isHybridPassGenToken() {
        JDWebView jDWebView = this.mJdWebView;
        return jDWebView != null && jDWebView.isHybridPassGenToken();
    }

    public boolean isLoginStateSyncing() {
        WebEntity webEntity = this.webEntity;
        return webEntity != null && webEntity.loginStateSync;
    }

    public void loadHtml(final String str) {
        JDWebView jDWebView;
        if (this.webEntity == null || (jDWebView = this.mJdWebView) == null || jDWebView.isPreRender()) {
            return;
        }
        final Runnable runnable = new Runnable() { // from class: com.jingdong.common.web.ui.a
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                CommonMFragment.this.b(str);
            }
        };
        if (this.mJdWebView.isHybridPassGenToken()) {
            this.webEntity.urlMap.put("webHybridHasOffConfig", "1");
        }
        if (WebUtils.canPassGentoken(this.webEntity.urlMap)) {
            post(runnable);
            Log.xLogD("loadHtml", "\u65b0\u94fe\u8def pass gentoken");
        } else {
            loadWeb();
            Log.xLogD("loadHtml", "\u65b0\u94fe\u8def need gentoken");
        }
        this.mJdWebView.setGenTokenCallback(new GenTokenCallback() { // from class: com.jingdong.common.web.ui.d
            {
                CommonMFragment.this = this;
            }

            @Override // com.jingdong.common.web.ui.GenTokenCallback
            public final void onCallback(GenTokenCallback.State state) {
                CommonMFragment.this.d(runnable, state);
            }
        });
    }

    public void loadWeb(String str) {
        resetWebUrl(str);
        loadWeb();
    }

    protected boolean needDestroyFragmentOnDestroy() {
        return true;
    }

    protected boolean needDestroyWebViewOnDestroy() {
        return true;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder instanceof IActivityResult) {
            ((IActivityResult) iWebUiBinder).onActivityResult(i2, i3, intent);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        IWebUiBinder iWebUiBinder;
        super.onConfigurationChanged(configuration);
        com.jingdong.corelib.utils.Log.d(this.TAG, "onConfigurationChanged, new config = " + configuration);
        if (!SwitchQueryFetcher.getSwitchBooleanValue(ScreenConfig.SWITCH_NAME, false) || (iWebUiBinder = this.webUiBinder) == null) {
            return;
        }
        IJavaInterface javaInterfaceObj = iWebUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JD_SCREEN_CONFIG);
        ScreenConfig screenConfig = javaInterfaceObj instanceof ScreenConfig ? (ScreenConfig) javaInterfaceObj : null;
        if (screenConfig != null) {
            screenConfig.onConfigurationChanged();
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.currentInitStartTime = System.currentTimeMillis();
        this.needRemoveviewOnStop = false;
        super.onCreate(bundle);
        setIsUseBasePV(false);
        com.jingdong.corelib.utils.Log.d(this.TAG, "onCreate,savedInstanceState:" + bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        com.jingdong.corelib.utils.Log.d(this.TAG, "onCreateViews,savedInstanceState:" + bundle);
        markPhaseEnd("FragCreate");
        getDataFromBundle(getArguments());
        markPhaseEnd("FragData");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mJdWebView = initWebView();
        PerfMonitor.getInstance().pageTrace(this, "webview_init", SystemClock.elapsedRealtime() - elapsedRealtime);
        markPhaseEnd("FragWebView");
        dealSavedInstanceState(bundle);
        BaseActivity baseActivity = this.thisActivity;
        this.mHttpGroupWithNPS = new HttpGroupWithNPS(baseActivity, baseActivity.getHttpGroupaAsynPool(), this.TAG, "", false);
        IWebUiBinder webUiBinder = getWebUiBinder();
        this.webUiBinder = webUiBinder;
        webUiBinder.bindUi(this);
        markPhaseEnd("FragBind");
        initWeb();
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.setCpsMtaData(getArguments());
            this.mJdWebView.appendPerformanceData("initStart", String.valueOf(this.currentInitStartTime));
            this.mJdWebView.appendPerformanceData(WebPerfManager.INIT_FINISH, String.valueOf(System.currentTimeMillis()));
            this.mJdWebView.appendWhiteScreenData("initStart", String.valueOf(this.currentInitStartTime));
            WebEntity webEntity = this.webEntity;
            if (webEntity != null) {
                this.mJdWebView.appendWhiteScreenData(WebWhiteScreenHolder.IS_FROM_M_INSIDE, String.valueOf(webEntity.isFromMInside));
                this.mJdWebView.appendWhiteScreenData("openAppActivityReferer", this.webEntity.openAppActivityReferer);
            }
            if (this.mJdWebView.isPreRender()) {
                long currentTimeMillis = (System.currentTimeMillis() - this.mJdWebView.getXRenderManager().getStartTime()) / 1000;
                getContext();
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("url", (Object) this.mJdWebView.getxRenderUrl());
                jDJSONObject.put("is_lag", this.mJdWebView.getXRenderManager().getXUiBinder().isEmptyCacheJsList() ? "0" : "1");
                jDJSONObject.put("is_loaded", this.mJdWebView.isPageLoaded() ? "1" : "0");
                jDJSONObject.put("loadtm", (Object) Long.valueOf(currentTimeMillis));
                XRender.sendExposure("xrender_add", jDJSONObject.toJSONString());
                this.mJdWebView.appendPerformanceData("renderExtraTime", String.valueOf(currentTimeMillis));
                this.mJdWebView.getXRenderManager().getXUiBinder().setWebUiBinder(this.webUiBinder);
                this.mJdWebView.appendWhiteScreenData(WebWhiteScreenHolder.X_RENDER, String.valueOf(System.currentTimeMillis()));
            }
            reportInitPerformance();
            this.mJdWebView.appendPerformanceData("from", BundleUtils.isFromDeepLink(getArguments()) ? "1" : "0");
        }
        return this.mJdWebView;
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        String str;
        Object obj;
        PayCheckImpl payCheckImpl;
        String str2;
        Uri parse;
        super.onDestroy();
        if (needDestroyFragmentOnDestroy()) {
            if (!SwitchQueryFetcher.getSwitchBooleanValue("webSetAct", false) && BaseFrameUtil.getInstance().getMainFrameActivity() == null && !"com.jd.lib.babel.view.activity.BabelActivity".equals(this.thisActivity.getClass().getName())) {
                BaseFrameUtil.getInstance().setCurrentMyActivity(null);
            }
            try {
                IWebUiBinder iWebUiBinder = this.webUiBinder;
                if (iWebUiBinder != null) {
                    AndroidSound androidSound = (AndroidSound) iWebUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.ANDROID_SOUND);
                    if (androidSound != null) {
                        androidSound.stopAndRelease();
                    }
                    JDAppUnite jDAppUnite = (JDAppUnite) this.webUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JDAPPUNITE);
                    if (jDAppUnite != null) {
                        jDAppUnite.onDestroy();
                        jDAppUnite.downLoadCancel();
                    }
                }
            } catch (Exception e2) {
                if (com.jingdong.corelib.utils.Log.E) {
                    e2.printStackTrace();
                }
            }
            WebEntity webEntity = this.webEntity;
            if (webEntity.isThirdPay && !webEntity.thirdPayStatus) {
                Uri parse2 = Uri.parse("jdpauth" + this.webEntity.thirdApp_key + "://?parameterKey={\"payStatus\":\"JDP_PAY_CANCEL\"}");
                Intent intent = new Intent();
                intent.addFlags(67108864);
                intent.setData(parse2);
                try {
                    startActivity(intent);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                this.webEntity.thirdPayStatus = true;
            }
            WebEntity webEntity2 = this.webEntity;
            if (webEntity2.isMetroPay) {
                JsBridgeEntity jsBridgeEntity = webEntity2.jsBridgeEntity;
                if (jsBridgeEntity.canReturnThirdApp) {
                    if (!TextUtils.isEmpty(jsBridgeEntity.metroPayData)) {
                        parse = Uri.parse("jdpauth" + this.webEntity.thirdApp_key + "://?parameterKey=" + this.webEntity.jsBridgeEntity.metroPayData);
                    } else {
                        parse = Uri.parse("jdpauth" + this.webEntity.thirdApp_key + "://?parameterKey={\"payStatus\":\"JDP_PAY_CANCEL\"}");
                    }
                    Intent intent2 = new Intent();
                    intent2.addFlags(67108864);
                    intent2.setData(parse);
                    try {
                        startActivity(intent2);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
            if (this.webEntity.pageFinished) {
                str = "";
                obj = RemoteMessageConst.TO;
            } else {
                PerformanceManager.getInstance().appendData("userAgent", getJdWebView() != null ? getJdWebView().getUaInfo() : DYConstants.DY_NULL_STR);
                PerformanceManager.getInstance().appendData(PerformanceManager.ERR_TYPE, "user interrupt");
                PerformanceManager.getInstance().appendData("errMsg", "\u9875\u9762\u672a\u52a0\u8f7d\u5b8c");
                PerformanceManager.getInstance().appendData("isError", "2");
                PerformanceManager.getInstance().appendData("mloadType", "webview");
                PerformanceManager performanceManager = PerformanceManager.getInstance();
                URLParamMap uRLParamMap = this.webEntity.urlMap;
                performanceManager.appendData(PerformanceManager.LOAD_URL, WebUtils.decodeUrl(uRLParamMap == null ? "" : uRLParamMap.get((Object) RemoteMessageConst.TO)));
                long currentTimeMillis = System.currentTimeMillis();
                PerformanceManager performanceManager2 = PerformanceManager.getInstance();
                WebEntity webEntity3 = this.webEntity;
                str = "";
                obj = RemoteMessageConst.TO;
                performanceManager2.appendData("mloadingTime", String.valueOf(currentTimeMillis - ((long) (webEntity3.webviewLoad_start * 1000.0d))));
                if (this.mJdWebView != null) {
                    PerformanceManager performanceManager3 = PerformanceManager.getInstance();
                    if (this.mJdWebView.isSystemCoreNotX5()) {
                        str2 = "system";
                    } else {
                        str2 = "x5-" + WebView.getTbsCoreVersion(JdSdk.getInstance().getApplication());
                    }
                    performanceManager3.appendData("kernelType", str2);
                }
                PerformanceManager.getInstance().report();
            }
            if (!this.webEntity.genTokenFinished) {
                PerformanceManager.getInstance().appendData(PerformanceManager.ERR_TYPE, "user interrupt");
                PerformanceManager.getInstance().appendData("errMsg", "gentoken\u672a\u52a0\u8f7d\u5b8c");
                PerformanceManager.getInstance().appendData("isError", "2");
                PerformanceManager.getInstance().appendData("mloadType", "gentoken");
                PerformanceManager performanceManager4 = PerformanceManager.getInstance();
                URLParamMap uRLParamMap2 = this.webEntity.urlMap;
                performanceManager4.appendData(PerformanceManager.LOAD_URL, WebUtils.decodeUrl(uRLParamMap2 == null ? str : uRLParamMap2.get(obj)));
                PerformanceManager.getInstance().appendData("mloadingTime", String.valueOf(System.currentTimeMillis() - ((long) (this.webEntity.genToken_start * 1000.0d))));
                PerformanceManager.getInstance().report();
            }
            IWebUiBinder iWebUiBinder2 = this.webUiBinder;
            if (iWebUiBinder2 != null && iWebUiBinder2.getWebViewUrlInterceptor() != null && (payCheckImpl = (PayCheckImpl) this.webUiBinder.getWebViewUrlInterceptor().getUrlCheck(WebUiConstans.UrlCheckKeys.CHECK_PAY)) != null) {
                payCheckImpl.unRegisterWXResultReceiver();
            }
            IWebUiBinder iWebUiBinder3 = this.webUiBinder;
            MobileLogin mobileLogin = iWebUiBinder3 != null ? (MobileLogin) iWebUiBinder3.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.MOBILE_LOGIN) : null;
            if (mobileLogin != null) {
                mobileLogin.onDestroy();
            }
            if (this.mJdWebView != null) {
                if (needDestroyWebViewOnDestroy()) {
                    this.mJdWebView.onDestory();
                }
                this.mJdWebView.reportPerformanceNow();
            }
            JDWebView jDWebView = this.mJdWebView;
            if (jDWebView != null && jDWebView.getNavigatorHolder() != null) {
                this.mJdWebView.getNavigatorHolder().unRegisterTitleThemeChangeListener();
            }
            MultiMedia.onCancel();
            SysBug5497Workaround sysBug5497Workaround = this.bug5497wa;
            if (sysBug5497Workaround != null) {
                sysBug5497Workaround.onDestroy();
            }
            IWebUiBinder iWebUiBinder4 = this.webUiBinder;
            if (iWebUiBinder4 != null) {
                iWebUiBinder4.unbindUi();
            }
        }
    }

    public void onJsMessage(String str, WebPlugin.CallBack callBack) {
        callBack.callback("This function is not supported in the current container !");
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.sdk.lib.compact.CompactBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            if (iWebUiBinder instanceof TitleBackListener) {
                return ((TitleBackListener) iWebUiBinder).keyBack();
            }
            return false;
        }
        return false;
    }

    @Override // com.jingdong.common.messagecenter.view.MessageRedObserver
    public void onMessageRedReceived(final Map<String, NewMessageRedInfo> map) {
        post(new Runnable() { // from class: com.jingdong.common.web.ui.CommonMFragment.7
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                NewMessageRedInfo newMessageRedInfo;
                Map map2 = map;
                if (map2 == null || !map2.containsKey("messageRedInfo") || (newMessageRedInfo = (NewMessageRedInfo) map.get("messageRedInfo")) == null) {
                    return;
                }
                if (!newMessageRedInfo.isShow9Number() && !newMessageRedInfo.isShow99Number()) {
                    CommonMFragment.this.mJdWebView.setRedPointVisibility(newMessageRedInfo.isShowRedDot());
                } else {
                    CommonMFragment.this.mJdWebView.setMsgRedPointNum(newMessageRedInfo.num, newMessageRedInfo.isShow9Number());
                }
            }
        });
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append(getContext() != null ? getContext().getClass().getSimpleName() : "");
        sb.append(" onPause");
        com.jingdong.corelib.utils.Log.d(str, sb.toString());
        WebEntity webEntity = this.webEntity;
        if (webEntity != null && webEntity.isNeedCookieRet) {
            CookieManager cookieManager = CookieManager.getInstance();
            if (!TextUtils.isEmpty(this.webEntity.cookieUrl)) {
                String cookie = cookieManager.getCookie(this.webEntity.cookieUrl);
                ArrayList arrayList = null;
                if (cookie != null) {
                    arrayList = new ArrayList();
                    String[] split = cookie.split(";");
                    for (String str2 : this.webEntity.cookieKeys) {
                        for (String str3 : split) {
                            if (str3.startsWith(str2 + ContainerUtils.KEY_VALUE_DELIMITER)) {
                                String substring = str3.substring(str2.length() + 1);
                                if (!TextUtils.isEmpty(substring)) {
                                    arrayList.add(substring);
                                }
                            }
                        }
                    }
                }
                String join = LangUtils.join("|", arrayList);
                if (com.jingdong.corelib.utils.Log.D) {
                    com.jingdong.corelib.utils.Log.d(this.TAG, "WebActivity cookie -->> " + join);
                }
                SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
                edit.putString(this.webEntity.cookieStoreName, join);
                edit.commit();
            }
        }
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.onPause();
        }
        this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.web.ui.CommonMFragment.6
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (CommonMFragment.this.getJdWebView() != null) {
                    CommonMFragment.this.mJdWebView.injectJs("javascript:window.MPing && MPing.EventSeries && MPing.EventSeries.androidSeries && MPing.EventSeries.androidSeries()");
                    CommonMFragment.this.mJdWebView.injectJs("javascript:window.getAndroidUnionSeries && getAndroidUnionSeries()");
                }
            }
        });
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.thisActivity.isFinishing() || this.webEntity == null) {
            return;
        }
        WebViewHelper.enablePlatformNotifications();
        this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.web.ui.CommonMFragment.4
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (CommonMFragment.this.isDefaultVisibleCallbackDisabled) {
                    return;
                }
                if (CommonMFragment.this.isVisibleHintCalled) {
                    if (CommonMFragment.this.getUserVisibleHint()) {
                        CommonMFragment.this.mJdWebView.onResume();
                        return;
                    }
                    return;
                }
                CommonMFragment.this.mJdWebView.onResume();
            }
        });
        WebEntity webEntity = this.webEntity;
        if (webEntity.authJumpLoginFlag) {
            webEntity.authJumpLoginFlag = false;
            if (!LoginUserBase.hasLogin()) {
                WebUtils.oneKeyLoginKelper(this.thisActivity);
            }
        }
        NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
        NewMessagRedManager.registPersonalMessageObserver(this);
        refreshCartCount();
        if (!LoginUserBase.hasLogin() || this.mHttpGroupWithNPS == null || WebViewHelper.isXTime()) {
            return;
        }
        NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
        NewMessagRedManager.requestMessage(this.mHttpGroupWithNPS.getHttpGroup());
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        com.jingdong.corelib.utils.Log.d(this.TAG, "onSaveInstanceState");
        if (bundle != null) {
            bundle.putBoolean("canJumpToPay", this.webEntity.jsBridgeEntity.canJumpToPay);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        WebViewHelper.disablePlatformNotifications();
        this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.web.ui.CommonMFragment.5
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (CommonMFragment.this.isDefaultVisibleCallbackDisabled) {
                    return;
                }
                try {
                    CommonMFragment.this.mJdWebView.onStop();
                } catch (Exception e2) {
                    if (com.jingdong.corelib.utils.Log.E) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.reportPerformanceNow();
        }
        WebEntity webEntity = this.webEntity;
        if (webEntity != null) {
            if (!TextUtils.isEmpty(webEntity.onekeylogin)) {
                this.webEntity.authJumpLoginFlag = true;
            }
            if (this.webEntity.isFromScan) {
                this.thisActivity.finish();
            }
        }
        NewMessagRedManager.getInstance(LoginUserBase.getLoginUserName());
        NewMessagRedManager.deregisterPersonalMessageObserver(this);
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            int i2 = R.id.root_layout;
            if (activity.findViewById(i2) != null) {
                MediaUtils.removeCameraPreView((ViewGroup) getActivity().findViewById(i2));
            }
        }
        JSVoiceManager.getInstance().cancel();
    }

    public void refreshCartCount() {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.refreshCartCount();
        }
    }

    public void resetWebUrl(String str) {
        if (this.webEntity == null && TextUtils.isEmpty(str)) {
            return;
        }
        String trim = str.trim();
        if (!trim.startsWith("https://") && !trim.startsWith("http://")) {
            ExceptionReporter.reportWebViewCommonError("url_error", trim, "unexpected url", trim);
        }
        WebEntity webEntity = this.webEntity;
        if (webEntity.urlMap != null) {
            if (TextUtils.isEmpty(webEntity.action)) {
                this.webEntity.action = RemoteMessageConst.TO;
            }
            WebEntity webEntity2 = this.webEntity;
            webEntity2.urlMap.put(webEntity2.action, trim);
        }
        if (!getArguments().getBoolean(WebEntity.ADD_CUSTOM_PARAMS_CHECKED, false)) {
            WebEntity webEntity3 = this.webEntity;
            trim = WebUtils.addCustomParams(trim, webEntity3.urlMap, webEntity3.action);
        }
        this.webEntity.url = trim;
    }

    public void sendReadyEvent(String str) {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView == null || !jDWebView.isPreRender() || this.mJdWebView.getXRenderManager() == null) {
            return;
        }
        XRenderManager xRenderManager = this.mJdWebView.getXRenderManager();
        WebEntity webEntity = this.webEntity;
        xRenderManager.sendReadyEvent(webEntity != null ? webEntity.url : "", str);
        this.mJdWebView.getXRenderManager().executeJsBridge();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
        if (bundle != null) {
            String url = WebUtils.getUrl(bundle);
            if (!bundle.getBoolean(WebEntity.ADD_CUSTOM_PARAMS_CHECKED, false)) {
                if (SwitchQueryFetcher.getSwitchBooleanValue("setArgumentsAddHeightSwitch", false)) {
                    SerializableContainer serializableContainer = (SerializableContainer) bundle.getSerializable("urlParamMap");
                    url = WebUtils.addHeightCustomParams(url, serializableContainer != null ? serializableContainer.getMap() : null, bundle.getString("urlAction"), "arguments");
                } else {
                    url = WebUtils.addCustomParams(url, bundle);
                }
            }
            if (bundle.getBoolean("isNeedRenderSendClick", true)) {
                XRender.Log("\u4e3b\u52a8\u5e2e\u52a9\u4e1a\u52a1\u8c03\u7528renderClick");
                XRender.getInstance().sendClickEvent(url);
            }
        }
    }

    public void setAutoLoadWeb(boolean z) {
        this.autoLoadWeb = z;
    }

    public void setAutoSendReadyEvent(boolean z) {
        this.autoSendReadyEvent = z;
    }

    public void setEnableHtmlPreload(boolean z) {
        this.enableHtmlPreload = z;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean z) {
        super.setUserVisibleHint(z);
        if (!this.isVisibleHintCalled) {
            this.isVisibleHintCalled = true;
        }
        BaseActivity baseActivity = this.thisActivity;
        if (baseActivity == null || baseActivity.isFinishing()) {
            return;
        }
        this.thisActivity.post(new Runnable() { // from class: com.jingdong.common.web.ui.CommonMFragment.3
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (CommonMFragment.this.isDefaultVisibleCallbackDisabled) {
                    return;
                }
                if (z) {
                    CommonMFragment.this.mJdWebView.onResume();
                } else {
                    CommonMFragment.this.mJdWebView.onStop();
                }
            }
        });
    }

    public void setWebViewVisibleStatus(boolean z) {
        JDWebView jDWebView;
        if (this.isDefaultVisibleCallbackDisabled && (jDWebView = this.mJdWebView) != null) {
            jDWebView.setVisibleStatus(z);
            this.mJdWebView.notifyWebViewVisible(z);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.presenter.IBaseUI
    public void showProgress() {
    }

    public void stopLoading() {
        this.mJdWebView.stopLoading();
    }

    public void xRenderSendClick(String str) {
        XRender.Log("\u4e1a\u52a1\u4e3b\u52a8\u8c03\u7528 sendClickEvent");
        XRender.getInstance().sendClickEvent(WebUtils.addStatusBarHeightParams(str));
    }

    public void loadWeb() {
        final Runnable runnable = new Runnable() { // from class: com.jingdong.common.web.ui.c
            {
                CommonMFragment.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                CommonMFragment.this.f();
            }
        };
        if (ActivityNumController.WebActivity.equals(this.thisActivity.getClass().getName())) {
            if (ChannelPrivacyConfirmUtil.checkIsNeedPrivacyConfirm(this.thisActivity, this.webEntity.url, new ChannelPrivacyConfirmUtil.ChannelPrivacyCallback() { // from class: com.jingdong.common.web.ui.b
                {
                    CommonMFragment.this = this;
                }

                @Override // com.jingdong.common.web.util.ChannelPrivacyConfirmUtil.ChannelPrivacyCallback
                public final void onAgree(boolean z) {
                    CommonMFragment.this.k(runnable, z);
                }
            })) {
                return;
            }
            post(runnable);
            return;
        }
        post(runnable);
    }
}
