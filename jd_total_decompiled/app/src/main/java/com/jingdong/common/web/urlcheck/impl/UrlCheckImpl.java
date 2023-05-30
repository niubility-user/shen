package com.jingdong.common.web.urlcheck.impl;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkFaceloginLivenessHelper;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.login.ICancelLogin;
import com.jingdong.common.login.IRegist;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.ui.DialogController;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDEbookUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.WebViewPool;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.tencent.smtt.sdk.WebView;
import com.un.lib.popup.JDTopPopupWindowHelper;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class UrlCheckImpl extends BaseWebComponent implements ICheckUrl {
    private static final String LOGIN_FLAG = "m_login";
    public static final String LOGIN_PATH = "/user/login.action";
    private static final String REGISTER_FLAG = "m_register";
    public static final String REGIST_PATH = "/user/register.action";
    private final String TAG;
    private volatile boolean loginCBFlag;
    private DialogController loginSyncFailDialog;
    private boolean mHasCheckHttp;
    private volatile boolean registerCBFlag;
    private boolean requestLoginFlag;

    public UrlCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = UrlCheckImpl.class.getSimpleName();
        this.requestLoginFlag = false;
        this.mHasCheckHttp = false;
        this.loginCBFlag = false;
        this.registerCBFlag = false;
    }

    private boolean checkUrl1(final Uri uri) {
        r0 = false;
        r0 = false;
        r0 = false;
        final boolean z = false;
        if (uri != null && this.webUiBinder.getJdWebView() != null) {
            this.loginCBFlag = false;
            this.registerCBFlag = false;
            if (LOGIN_PATH.equals(uri.getPath())) {
                if (LoginUserBase.hasLogin()) {
                    WebUtils.reportCommonErr(this.webUiBinder, "H5TryLoginWhenLogined", "UrlCheckImpl.checkUrl1", "");
                    if (this.webUiBinder.getWebEntity().syncingUri == null || uri == null || !this.webUiBinder.getWebEntity().syncingUri.equals(uri)) {
                        loginStateSynchro(uri, true);
                        return true;
                    }
                    return true;
                } else if (this.requestLoginFlag) {
                    return true;
                } else {
                    int i2 = this.webUiBinder.getWebEntity().isRegist ? 67108864 : 0;
                    this.requestLoginFlag = true;
                    if (Log.D) {
                        Log.d(this.TAG, "startLoginActivity --->");
                    }
                    if (SwitchQueryFetcher.getSwitchBooleanValue("wvLoginClose", false)) {
                        String name = this.webUiBinder.getBaseActivity() != null ? this.webUiBinder.getBaseActivity().getClass().getName() : null;
                        JDWebView jdWebView = this.webUiBinder.getJdWebView();
                        if (Log.D) {
                            String str = this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("activity = ");
                            sb.append(name);
                            sb.append(", hasOnceShowPage = ");
                            sb.append(jdWebView != null ? Boolean.valueOf(jdWebView.hasOnceShowPage) : null);
                            sb.append(", closeWhenNative = ");
                            sb.append(jdWebView != null ? Boolean.valueOf(jdWebView.closeWhenNative) : null);
                            Log.d(str, sb.toString());
                        }
                        if (ActivityNumController.WebActivity.equals(name) && jdWebView != null && (!jdWebView.hasOnceShowPage || jdWebView.closeWhenNative)) {
                            z = true;
                        }
                    }
                    DeepLinkLoginHelper.startLoginActivity(this.webUiBinder.getBaseActivity(), null, new ICancelLogin() { // from class: com.jingdong.common.web.urlcheck.impl.UrlCheckImpl.1
                        @Override // com.jingdong.common.login.ICancelLogin
                        public void onCancel(String str2) {
                            if (Log.D) {
                                Log.d(UrlCheckImpl.this.TAG, "login callback: onCancel, tag = " + str2);
                            }
                            if (UrlCheckImpl.this.loginCBFlag) {
                                return;
                            }
                            UrlCheckImpl.this.loginCBFlag = true;
                            if (UrlCheckImpl.LOGIN_FLAG.equals(str2)) {
                                if (z) {
                                    if (Log.D) {
                                        Log.d(UrlCheckImpl.this.TAG, "login callback: onCancel, close web");
                                    }
                                    ((BaseWebComponent) UrlCheckImpl.this).webUiBinder.finishUi();
                                    return;
                                }
                                if (Log.D) {
                                    Log.d(UrlCheckImpl.this.TAG, "login callback: onCancel, loginCallback");
                                }
                                UrlCheckImpl.this.loginCallback(uri);
                            }
                        }

                        @Override // com.jingdong.common.login.ILogin
                        public void onSuccess(String str2) {
                            if (Log.D) {
                                Log.d(UrlCheckImpl.this.TAG, "login callback: onSuccess, tag = " + str2);
                            }
                            if (UrlCheckImpl.this.loginCBFlag) {
                                return;
                            }
                            UrlCheckImpl.this.loginCBFlag = true;
                            if (UrlCheckImpl.LOGIN_FLAG.equals(str2)) {
                                UrlCheckImpl.this.loginCallback(uri);
                            }
                        }
                    }, LOGIN_FLAG, i2);
                    return true;
                }
            } else if (REGIST_PATH.equals(uri.getPath())) {
                if (LoginUserBase.hasLogin()) {
                    if (this.webUiBinder.getWebEntity().syncingUri == null || uri == null || !this.webUiBinder.getWebEntity().syncingUri.equals(uri)) {
                        loginStateSynchro(uri, true);
                        return true;
                    }
                    return true;
                }
                int i3 = this.webUiBinder.getWebEntity().isRegist ? 67108864 : 0;
                if (Log.D) {
                    Log.d(this.TAG, "startRegisterActivity --->");
                }
                DeepLinkLoginHelper.startRegisterActivity(this.webUiBinder.getBaseActivity(), null, new IRegist() { // from class: com.jingdong.common.web.urlcheck.impl.UrlCheckImpl.2
                    @Override // com.jingdong.common.login.IRegist
                    public void onCancel(String str2) {
                        if (Log.D) {
                            Log.d(UrlCheckImpl.this.TAG, "register callback: IRegist onCancel, tag = " + str2);
                        }
                        if (UrlCheckImpl.this.registerCBFlag) {
                            return;
                        }
                        UrlCheckImpl.this.registerCBFlag = true;
                        if (UrlCheckImpl.REGISTER_FLAG.equals(str2)) {
                            UrlCheckImpl.this.registerCallback(uri);
                        }
                    }

                    @Override // com.jingdong.common.login.IRegist
                    public void onSuccess(String str2) {
                        if (Log.D) {
                            Log.d(UrlCheckImpl.this.TAG, "register callback: IRegist onSuccess, tag = " + str2);
                        }
                        if (UrlCheckImpl.this.registerCBFlag) {
                            return;
                        }
                        UrlCheckImpl.this.registerCBFlag = true;
                        if (UrlCheckImpl.REGISTER_FLAG.equals(str2)) {
                            UrlCheckImpl.this.registerCallback(uri);
                        }
                    }
                }, REGISTER_FLAG, i3);
                return true;
            } else if ("_blank".equals(new UrlQuerySanitizer(uri.toString()).getValue(TouchesHelper.TARGET_KEY))) {
                CommonBase.toBrowser(uri);
                return true;
            } else if (uri.toString().endsWith(".apk")) {
                CommonBase.toBrowser(uri);
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f9, code lost:
        if (r0.equals(com.jingdong.common.web.entity.WebEntity.VALUE_ONEKEYLOGIN_ANDROIDRETURN) != false) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0346  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean checkUrl2(Uri uri) {
        boolean z = false;
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if (Log.D) {
            Log.d(this.TAG, "checkUrl2 uri=" + uri);
            Log.d(this.TAG, "checkUrl2 scheme=" + scheme);
        }
        if (!TextUtils.isEmpty(this.webUiBinder.getWebEntity().browserlogin_fromurl) && !TextUtils.isEmpty(uri.getScheme())) {
            this.webUiBinder.getWebEntity();
            if (WebEntity.DEFAULTBROWSER_SCHEME.equals(uri.getScheme())) {
                String query = uri.getQuery();
                String[] split = query.split("browserlogin_returnurl=");
                if (split != null && split.length > 1) {
                    query = split[1];
                }
                Uri parse = Uri.parse(query);
                if (parse != null) {
                    String str = this.webUiBinder.getWebEntity().onekeylogin;
                    this.webUiBinder.getWebEntity();
                    if (TextUtils.equals(str, WebEntity.VALUE_ONEKEYLOGIN_ANDROIDRETURN)) {
                        WebUtils.oneKeyLoginKelper(this.webUiBinder.getBaseActivity());
                        return true;
                    }
                    CommonBase.toBrowser(parse);
                    this.webUiBinder.finishUi();
                    return true;
                }
                if (TextUtils.isEmpty(uri.getHost()) && uri.getHost().toLowerCase().contains("facelogin.realname")) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(LoginConstans.KEY_SPECIAL, this.webUiBinder.getWebEntity().faceLoginSpecialTag);
                    DeeplinkFaceloginLivenessHelper.jumpLivenessStart(this.webUiBinder.getBaseActivity(), bundle);
                    this.webUiBinder.finishUi();
                    return true;
                } else if (scheme != null || scheme.equals("http") || scheme.equals("https")) {
                    if (scheme != null) {
                        this.mHasCheckHttp = true;
                    }
                    return false;
                } else {
                    if (!uri.isOpaque()) {
                        uri.getQueryParameter("params");
                    }
                    if ("native".equals(scheme) && LoginConstans.FREGMENT_LOGIN_FLAG.equals(uri.getHost()) && "faceRecognition".equals(uri.getQueryParameter("type"))) {
                        DeeplinkFaceloginLivenessHelper.startLiveness(this.webUiBinder.getBaseActivity(), null);
                        return true;
                    }
                    if (JumpUtil.isJdScheme(scheme)) {
                        if (Log.D) {
                            Log.d(this.TAG, "checkUrl2() -->> is openapp url :" + uri.toString());
                        }
                        String queryParameter = uri.getQueryParameter("params");
                        if (!TextUtils.isEmpty(queryParameter) && queryParameter.indexOf("\"sourceValue\":\"scan\"") >= 0) {
                            this.webUiBinder.getWebEntity().isFromScan = true;
                        }
                        if (!TextUtils.isEmpty(queryParameter) && queryParameter.contains("\"category\":\"jump\"") && queryParameter.contains("\"des\":\"jd_native_im\"") && queryParameter.contains("\"orgId\":")) {
                            try {
                                int i2 = new JSONObject(queryParameter).getInt("orgId");
                                DDParameterBuilder generateWithPin = DDParameterBuilder.generateWithPin();
                                if (generateWithPin != null) {
                                    generateWithPin.addFrom(DDParameterBuilder.ACTION_BROADCAST_START_IM_PRIVATEORDERASK).addSkillID(String.valueOf(i2));
                                }
                                DeeplinkDongDongHelper.getInstance().startDongDong(this.webUiBinder.getBaseActivity(), generateWithPin.getBundle());
                                return true;
                            } catch (Exception unused) {
                            }
                        }
                        if (TextUtils.isEmpty(queryParameter)) {
                            queryParameter = uri.getQuery();
                        }
                        if (!TextUtils.isEmpty(queryParameter) && (queryParameter.contains("\"typelogin_in\":\"wjlogin\"") || "wjlogin".equals(uri.getQueryParameter("typelogin_in")))) {
                            z = true;
                        }
                        Intent intent = new Intent();
                        intent.setData(uri);
                        String urlHistory = this.webUiBinder.getJdWebView().getUrlHistory(-2);
                        if (TextUtils.isEmpty(urlHistory)) {
                            Bundle bundle2 = this.webUiBinder.getWebEntity().mBundle;
                            urlHistory = bundle2 != null ? bundle2.getString(MBaseKeyNames.KEY_REFERER) : null;
                        }
                        if (!TextUtils.isEmpty(urlHistory)) {
                            intent.putExtra(MBaseKeyNames.KEY_REFERER, urlHistory);
                        }
                        OpenAppJumpController.dispatchJumpRequest(this.webUiBinder.getBaseActivity(), intent);
                        if (z) {
                            this.webUiBinder.finishUi();
                        }
                        if (Log.D) {
                            Log.d(this.TAG, "checkUrl2() -->> start InterfaceActivity uri :" + uri.toString());
                        }
                    } else if (uri.toString().startsWith(JDTopPopupWindowHelper.CUSTOM_ROUTER_TYPE)) {
                        JDRouter.build(this.webUiBinder.getBaseActivity(), uri.toString()).open();
                        return true;
                    } else {
                        if (Log.D) {
                            Log.d(this.TAG, "checkUrl2() -->> is not openapp scheme :" + uri.toString());
                        }
                        if (WebViewPool.isRecycleUrl(uri.toString())) {
                            return false;
                        }
                        ExceptionReporter.reportWebViewCommonError("UrlCheck_Scheme_OutJump", uri.toString(), this.webUiBinder.getBaseActivity() != null ? this.webUiBinder.getBaseActivity().getClass().getSimpleName() : null, null);
                        Intent intent2 = new Intent("android.intent.action.VIEW", uri);
                        intent2.addCategory("android.intent.category.BROWSABLE");
                        startSchemeNoExceptionWithMta(intent2);
                    }
                    if (!this.webUiBinder.getJdWebView().hasOnceShowPage && !this.mHasCheckHttp) {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.UrlCheckImpl.3
                            @Override // java.lang.Runnable
                            public void run() {
                                if (((BaseWebComponent) UrlCheckImpl.this).webUiBinder != null) {
                                    ((BaseWebComponent) UrlCheckImpl.this).webUiBinder.finishUi();
                                }
                            }
                        }, 500);
                    }
                    return true;
                }
            }
        }
        if (!TextUtils.isEmpty(this.webUiBinder.getWebEntity().returnUrl) && this.webUiBinder.getWebEntity().returnUrl.toLowerCase().contains(scheme.toLowerCase())) {
            if (!TextUtils.isEmpty(this.webUiBinder.getWebEntity().onekeylogin)) {
                String str2 = this.webUiBinder.getWebEntity().onekeylogin;
                this.webUiBinder.getWebEntity();
                if (!str2.equals(WebEntity.VALUE_ONEKEYLOGIN_KEPLER)) {
                    String str3 = this.webUiBinder.getWebEntity().onekeylogin;
                    this.webUiBinder.getWebEntity();
                }
                WebUtils.oneKeyLoginKelper(this.webUiBinder.getBaseActivity());
                return true;
            }
            WebUtils.returnThirdApp(this.webUiBinder.getBaseActivity(), uri);
            return true;
        }
        if (TextUtils.isEmpty(uri.getHost())) {
        }
        if (scheme != null) {
        }
        if (scheme != null) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginCallback(Uri uri) {
        this.requestLoginFlag = false;
        if (!LoginUserBase.hasLogin()) {
            if (Log.D) {
                Log.d(this.TAG, "loginCallback() no login -->> ");
            }
            if (this.webUiBinder.getJdWebView() != null) {
                this.webUiBinder.getJdWebView().injectJs("javascript:window.userCancelLoginNotification&&userCancelLoginNotification()");
            }
            if (this.webUiBinder.getWebEntity().isFromAuthSdk) {
                this.webUiBinder.getWebEntity().oautherror = 2;
                this.webUiBinder.finishUi();
            }
            if (!TextUtils.isEmpty(uri.getPath()) && LOGIN_PATH.equals(uri.getPath()) && Uri.decode(uri.toString()).contains(LoginConstans.ENTERPRISE_RETURNURL)) {
                if (Log.D) {
                    Log.e(this.TAG + "resumeListener", uri.getPath());
                }
                this.webUiBinder.finishUi();
                return;
            }
            return;
        }
        if (Log.D) {
            Log.d(this.TAG, "loginCallback() has login -->> ");
        }
        if (!TextUtils.isEmpty(uri.getPath()) && LOGIN_PATH.equals(uri.getPath())) {
            if (Log.D) {
                Log.e(this.TAG + "resumeListener", uri.getPath());
            }
            if (uri.toString().equals(Uri.decode(LoginConstans.FROMREGIST))) {
                this.webUiBinder.finishUi();
            }
        }
        if (!TextUtils.isEmpty(uri.getPath()) && LOGIN_PATH.equals(uri.getPath())) {
            if (Uri.decode(uri.toString()).contains(LoginConstans.ENTERPRISE_RETURNURL)) {
                if (Log.D) {
                    Log.e(this.TAG + "resumeListener", uri.getPath());
                }
                this.webUiBinder.finishUi();
            }
        } else if (this.webUiBinder.getWebEntity().mBundle != null) {
            String string = this.webUiBinder.getWebEntity().mBundle.getString("from");
            if (!TextUtils.isEmpty(string) && JDEbookUtil.FROM_EBOOK.equals(string)) {
                loginStateSynchro(uri, "ebook_login");
                return;
            }
        }
        loginStateSynchro(uri, false);
    }

    private void loginStateSynchro(final Uri uri, boolean z) {
        WebUtils.loginStateSynchro(this.webUiBinder, uri, z, new CommonBase.BrowserAllUrlListener() { // from class: com.jingdong.common.web.urlcheck.impl.UrlCheckImpl.4
            @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
            public void onComplete(String str) {
            }

            @Override // com.jingdong.common.utils.CommonBase.BrowserNewUrlListener
            public void onError(HttpError httpError) {
                if (httpError != null && httpError.getJsonCode() == 3) {
                    UrlCheckImpl.this.onLoginGentokenErrorCode3(uri);
                }
            }

            @Override // com.jingdong.common.utils.CommonBase.BrowserAllUrlListener
            public void onReady() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoginGentokenErrorCode3(Uri uri) {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        X5WebView webView = (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) ? null : this.webUiBinder.getJdWebView().getWebView();
        final String uri2 = uri != null ? uri.toString() : null;
        if (webView == null || TextUtils.isEmpty(uri2) || this.webUiBinder.getJdWebView().getHandler() == null) {
            return;
        }
        this.webUiBinder.getJdWebView().getHandler().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.UrlCheckImpl.6
            @Override // java.lang.Runnable
            public void run() {
                UrlCheckImpl urlCheckImpl = UrlCheckImpl.this;
                urlCheckImpl.checkUrl(((BaseWebComponent) urlCheckImpl).webUiBinder.getJdWebView().getWebView(), uri2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerCallback(Uri uri) {
        if (Log.D) {
            Log.d(this.TAG, "registerCallback() -->> ");
        }
        if (LoginUserBase.hasLogin()) {
            if (Log.D) {
                Log.d(this.TAG, "registerCallback() has login -->> ");
            }
            loginStateSynchro(uri, false);
        }
    }

    private void setCashierDesk(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Uri parse = Uri.parse(str);
        if (WebUtils.isHttpOrHttps(parse.getScheme())) {
            this.webUiBinder.getWebEntity().isCashierDesk = JumpUtils.checkPayHost(parse.getHost());
        }
    }

    private void startSchemeNoExceptionWithMta(Intent intent) {
        BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
        if (intent == null || baseActivity == null) {
            return;
        }
        try {
            baseActivity.startActivityForResult(intent, -1);
        } catch (ActivityNotFoundException e2) {
            Uri data = intent.getData();
            if (Log.E) {
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error: try to start activity with uri: ");
                sb.append(data != null ? data.toString() : "");
                Log.e(str, sb.toString());
                Log.e(this.TAG, "startSchemeNoExceptionWithMta", e2);
            }
            String url = (this.webUiBinder.getJdWebView() == null || this.webUiBinder.getJdWebView().getWebView() == null) ? "" : this.webUiBinder.getJdWebView().getWebView().getUrl();
            if (TextUtils.isEmpty(url)) {
                url = (this.webUiBinder.getWebEntity() == null || this.webUiBinder.getWebEntity().urlMap == null) ? "" : this.webUiBinder.getWebEntity().urlMap.get((Object) RemoteMessageConst.TO);
            }
            ExceptionReporter.reportWebViewCommonError("SchemeNotFound", url, data != null ? data.toString() : "", "");
        } catch (Throwable th) {
            if (Log.E) {
                Log.e(this.TAG, "startSchemeNoExceptionWithMta", th);
            }
        }
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        Uri parse = Uri.parse(str);
        if (Log.D) {
            Log.d(this.TAG, "start checkUrl :" + parse);
        }
        setCashierDesk(str);
        if (checkUrl2(parse) || checkUrl1(parse)) {
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
                return true;
            }
            this.webUiBinder.getJdWebView().appendWhiteScreenData(WebWhiteScreenHolder.M2NATIVE, WebWhiteScreenHolder.M2NATIVE);
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_COMMON;
    }

    private void loginStateSynchro(final Uri uri, String str) {
        WebUtils.loginStateSynchro(this.webUiBinder, uri, str, new CommonBase.BrowserAllUrlListener() { // from class: com.jingdong.common.web.urlcheck.impl.UrlCheckImpl.5
            @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
            public void onComplete(String str2) {
            }

            @Override // com.jingdong.common.utils.CommonBase.BrowserNewUrlListener
            public void onError(HttpError httpError) {
                if (httpError != null && httpError.getJsonCode() == 3) {
                    UrlCheckImpl.this.onLoginGentokenErrorCode3(uri);
                }
            }

            @Override // com.jingdong.common.utils.CommonBase.BrowserAllUrlListener
            public void onReady() {
            }
        }, false);
    }
}
