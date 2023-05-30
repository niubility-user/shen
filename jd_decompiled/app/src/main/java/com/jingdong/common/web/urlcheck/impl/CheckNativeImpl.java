package com.jingdong.common.web.urlcheck.impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.cps.JDUnionUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.navutils.NavUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.util.M2NativeHelper;
import com.jingdong.common.web.util.WebSwitchQueryFetcher;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.manto.utils.MantoActivityUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebView;
import java.net.URLDecoder;

/* loaded from: classes12.dex */
public class CheckNativeImpl extends BaseWebComponent implements ICheckUrl {
    private static final int DELAYTIME = 500;
    private final String TAG;
    private final String ULINK_PREFIX_URL;
    private BaseActivity mActivity;

    public CheckNativeImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = CheckNativeImpl.class.getSimpleName();
        this.ULINK_PREFIX_URL = "linkst.m.jd.com/ul/ul.action";
        this.mActivity = iWebUiBinder.getBaseActivity();
    }

    private boolean isNeedCheckToNative(String str) {
        try {
            return true ^ TextUtils.equals("0", Uri.parse(str).getQueryParameter("has_native"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public boolean checkM2Native(String str, boolean z) {
        return checkM2Native(str, z, true);
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        boolean checkM2Native;
        if (SwitchQueryFetcher.getSwitchBooleanValue(WebSwitchQueryFetcher.FIX_CHECK_URL, false)) {
            checkM2Native = checkM2Native(str, false, !TextUtils.isEmpty(str) && (str.startsWith("https%") || str.startsWith("http%")));
        } else {
            checkM2Native = checkM2Native(str, false);
        }
        if (checkM2Native) {
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            if (iWebUiBinder != null && iWebUiBinder.getJdWebView() != null) {
                this.webUiBinder.getJdWebView().appendWhiteScreenData(WebWhiteScreenHolder.M2NATIVE, WebWhiteScreenHolder.M2NATIVE);
            }
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_NATIVE;
    }

    public boolean checkM2Native(String str, boolean z, boolean z2) {
        String replaceFirst;
        Log.d(this.TAG, "checkM2Native:" + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (z2) {
            try {
                str = URLDecoder.decode(str, "utf-8");
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null || NavUtils.isPageDegradeToH5(this.mActivity, str) || !isNeedCheckToNative(str)) {
            return false;
        }
        String urlHistory = this.webUiBinder.getJdWebView().getUrlHistory(-2);
        if (TextUtils.isEmpty(urlHistory)) {
            Bundle bundle = this.webUiBinder.getWebEntity().mBundle;
            urlHistory = bundle != null ? bundle.getString(MBaseKeyNames.KEY_REFERER) : null;
        }
        String str2 = urlHistory;
        Bundle bundleFromUrl = WebViewHelper.getBundleFromUrl(str);
        boolean z3 = !this.webUiBinder.getJdWebView().hasOnceShowPage || this.webUiBinder.getJdWebView().closeWhenNative || z;
        if (JDUnionUtils.isCpsUnionMatch(this.mActivity, str, str2, z3, this.mActivity.getIntent() == null ? null : this.mActivity.getIntent().getExtras(), bundleFromUrl, this.TAG)) {
            WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
            Log.d(this.TAG, "M2Native cps\u8054\u76df");
            if (z3) {
                this.mActivity.isDisposeableUnableExitAnim = true;
                this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CheckNativeImpl.this.mActivity.finish();
                    }
                }, 500);
            }
            return true;
        } else if (bundleFromUrl != null && bundleFromUrl.containsKey("jdreactkey")) {
            if (OKLog.D) {
                OKLog.d(this.TAG, "CheckNativeImpl [rn logic match] url: " + str);
            }
            Bundle bundle2 = new Bundle();
            if (z3) {
                this.mActivity.isDisposeableUnableExitAnim = true;
                bundle2.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
            }
            ReactActivityUtilsHelperBase.startJDReactCommonPage(this.webUiBinder.getBaseActivity(), str, bundle2);
            if (z3) {
                this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CheckNativeImpl.this.mActivity.finish();
                    }
                }, 500);
            }
            return true;
        } else {
            String str3 = "";
            if (str.startsWith("http://linkst.m.jd.com/ul/ul.action") || str.startsWith("https://linkst.m.jd.com/ul/ul.action")) {
                if (str.startsWith("https")) {
                    replaceFirst = str.replaceFirst("https://linkst.m.jd.com/ul/ul.action\\?", "");
                } else {
                    replaceFirst = str.startsWith("http") ? str.replaceFirst("http://linkst.m.jd.com/ul/ul.action\\?", "") : null;
                }
                if (replaceFirst != null && replaceFirst.startsWith("?")) {
                    replaceFirst = replaceFirst.substring(1);
                }
                try {
                    Uri parse = !TextUtils.isEmpty(replaceFirst) ? Uri.parse(replaceFirst) : null;
                    if (parse != null && JumpUtil.isJdScheme(parse.getScheme())) {
                        Intent data = new Intent().setData(parse);
                        Bundle bundle3 = this.webUiBinder.getWebEntity().mBundle;
                        if (bundle3 != null && z3) {
                            data.putExtras(bundle3);
                        }
                        data.putExtra(MBaseKeyNames.KEY_REFERER, str);
                        OpenAppJumpController.dispatchJumpRequest(this.webUiBinder.getBaseActivity(), data);
                        if (z3) {
                            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    CheckNativeImpl.this.mActivity.isDisposeableUnableExitAnim = true;
                                    CheckNativeImpl.this.mActivity.finish();
                                }
                            }, 500);
                        }
                        return true;
                    }
                } catch (Exception e3) {
                    ExceptionReporter.reportWebViewCommonError("CheckNativeImpl_ulink_error", str, e3.getMessage(), ExceptionReporter.getStackStringFromException(e3));
                    Log.e(this.TAG, e3.getMessage(), e3);
                }
            }
            if (!JumpUtil.VALUE_DES_MESSAGE_M.equals(this.webUiBinder.getWebEntity().mBundle.getString("des")) && M2NativeHelper.check2Babel(this.webUiBinder.getBaseActivity(), str, this.webUiBinder.getWebEntity().mBundle, z3, z)) {
                if (z3) {
                    this.webUiBinder.getJdWebView().onDestory();
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.4
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            }
            String skuId = WebViewHelper.getSkuId(str);
            if (!TextUtils.isEmpty(skuId)) {
                Bundle bundle4 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle4.putAll(bundleFromUrl);
                }
                bundle4.putString("skuId", skuId);
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle4.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes("productDetail", this.mActivity, bundle4);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u5546\u8be6");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.5
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            }
            String shopId = WebViewHelper.getShopId(str);
            if (!TextUtils.isEmpty(shopId)) {
                Bundle bundle5 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle5.putAll(bundleFromUrl);
                }
                bundle5.putString("shopId", shopId);
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle5.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes(JumpUtil.VAULE_DES_JSHOP, this.mActivity, bundle5);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u5e97\u94fa");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.6
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            }
            String faxianAuthorId = WebViewHelper.getFaxianAuthorId(str);
            if (!TextUtils.isEmpty(faxianAuthorId)) {
                Bundle bundle6 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle6.putAll(bundleFromUrl);
                }
                bundle6.putString("authorId", faxianAuthorId);
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle6.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_FAXIAN_AUTHOR, this.mActivity, bundle6);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u53d1\u73b0 \u4f5c\u8005\u9875");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.7
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            }
            String livePlayerRoomInfo = WebViewHelper.getLivePlayerRoomInfo(str);
            if (!TextUtils.isEmpty(livePlayerRoomInfo)) {
                Bundle bundle7 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle7.putAll(bundleFromUrl);
                }
                bundle7.putString("id", livePlayerRoomInfo);
                bundle7.putString("liveUrl", str);
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle7.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_FIND_LIVE_PRE, this.mActivity, bundle7);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u76f4\u64ad\u95f4");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.8
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            }
            String[] commentDetailInfo = WebViewHelper.getCommentDetailInfo(str);
            if (commentDetailInfo != null) {
                Bundle bundle8 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle8.putAll(bundleFromUrl);
                }
                bundle8.putString("productId", commentDetailInfo[0]);
                bundle8.putString(DeepLinkCommuneHelper.COMMENT_ID, commentDetailInfo[1]);
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle8.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_COMMENT_DETAIL, this.mActivity, bundle8);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u8bc4\u8bba\u8be6\u60c5");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.9
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            }
            String videoBuyInfo = WebViewHelper.getVideoBuyInfo(str);
            if (!TextUtils.isEmpty(videoBuyInfo)) {
                Bundle bundle9 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle9.putAll(bundleFromUrl);
                }
                bundle9.putString("id", videoBuyInfo);
                bundle9.putString("channel", "webview");
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle9.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_VIDEO_BUY, this.mActivity, bundle9);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u53d1\u73b0\u89c6\u9891\u8d2d");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.10
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            } else if (WebViewHelper.isMyJDMatch(str)) {
                Bundle bundle10 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle10.putAll(bundleFromUrl);
                }
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle10.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_MYJD, this.mActivity, bundle10);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u6211\u7684\u4eac\u4e1c");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.11
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            } else if (WebViewHelper.isMyCartMatch(str)) {
                Bundle bundle11 = new Bundle();
                if (bundleFromUrl != null) {
                    bundle11.putAll(bundleFromUrl);
                }
                if (z3) {
                    this.mActivity.isDisposeableUnableExitAnim = true;
                    bundle11.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                }
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_GO_CART, this.mActivity, bundle11);
                WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                Log.d(this.TAG, "M2Native \u8d2d\u7269\u8f66");
                if (z3) {
                    this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.12
                        @Override // java.lang.Runnable
                        public void run() {
                            CheckNativeImpl.this.mActivity.finish();
                        }
                    }, 500);
                }
                return true;
            } else {
                String searchResultInfo = WebViewHelper.getSearchResultInfo(str);
                if (!TextUtils.isEmpty(searchResultInfo)) {
                    Bundle bundle12 = new Bundle();
                    if (bundleFromUrl != null) {
                        bundle12.putAll(bundleFromUrl);
                        str3 = bundleFromUrl.getString("keyword");
                    }
                    bundle12.putString(JshopConst.JSHOP_SEARCH_KEYWORD, WebViewHelper.filterParam(str3, searchResultInfo));
                    bundle12.putString("from", "search");
                    if (z3) {
                        this.mActivity.isDisposeableUnableExitAnim = true;
                        bundle12.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                    }
                    JumpUtil.execJumpByDes(JumpUtil.VAULE_DES_PRODUCT_LIST, this.mActivity, bundle12);
                    WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                    Log.d(this.TAG, "M2Native \u641c\u7d22\u7ed3\u679c\u9875");
                    if (z3) {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.13
                            @Override // java.lang.Runnable
                            public void run() {
                                CheckNativeImpl.this.mActivity.finish();
                            }
                        }, 500);
                    }
                    return true;
                }
                if (this.webUiBinder != null) {
                    try {
                        Object invoke = Class.forName("com.jingdong.common.utils.CashierGenPayIdDomainCollector").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                        invoke.getClass().getMethod("preHandHistoryUrl", JDWebView.class, String.class).invoke(invoke, this.webUiBinder.getJdWebView(), str);
                    } catch (Throwable unused) {
                    }
                }
                if (WebViewHelper.isJdPayMatch(this.webUiBinder.getBaseActivity(), str)) {
                    WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                    Log.d(this.TAG, "M2Native jdPay");
                    if (z3) {
                        this.mActivity.isDisposeableUnableExitAnim = true;
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.14
                            @Override // java.lang.Runnable
                            public void run() {
                                CheckNativeImpl.this.mActivity.finish();
                            }
                        }, 500);
                    }
                    return true;
                } else if (WebViewHelper.isJDCouponMatch(str)) {
                    Bundle bundle13 = new Bundle();
                    if (bundleFromUrl != null) {
                        bundle13.putAll(bundleFromUrl);
                    }
                    if (z3) {
                        this.mActivity.isDisposeableUnableExitAnim = true;
                        bundle13.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                    }
                    JumpUtil.execJumpByDes(JumpUtil.VAULE_DES_COUPON_CENTER, this.webUiBinder.getBaseActivity(), bundle13);
                    WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                    Log.d(this.TAG, "M2Native \u9886\u5238\u4e2d\u5fc3");
                    if (z3) {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.15
                            @Override // java.lang.Runnable
                            public void run() {
                                CheckNativeImpl.this.mActivity.finish();
                            }
                        }, 500);
                    }
                    return true;
                } else if (WebViewHelper.isEnjoyBuyMatch(str)) {
                    Bundle bundle14 = new Bundle();
                    if (bundleFromUrl != null) {
                        bundle14.putAll(bundleFromUrl);
                    }
                    if (z3) {
                        this.mActivity.isDisposeableUnableExitAnim = true;
                        bundle14.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                    }
                    JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_ENJOYBUY, this.webUiBinder.getBaseActivity(), bundle14);
                    WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                    if (z3) {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.16
                            @Override // java.lang.Runnable
                            public void run() {
                                CheckNativeImpl.this.mActivity.finish();
                            }
                        }, 500);
                    }
                    return true;
                } else if (WebViewHelper.isBuyVideoProtrait(str)) {
                    Bundle bundle15 = new Bundle();
                    if (bundleFromUrl != null) {
                        bundle15.putAll(bundleFromUrl);
                    }
                    if (z3) {
                        this.mActivity.isDisposeableUnableExitAnim = true;
                        bundle15.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                    }
                    JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_VIDEO_IMMERSION, this.webUiBinder.getBaseActivity(), bundle15);
                    WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                    if (z3) {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.17
                            @Override // java.lang.Runnable
                            public void run() {
                                CheckNativeImpl.this.mActivity.finish();
                            }
                        }, 500);
                    }
                    return true;
                } else if (WebViewHelper.isOpenHomeUrl(str)) {
                    Bundle bundle16 = new Bundle();
                    if (bundleFromUrl != null) {
                        bundle16.putAll(bundleFromUrl);
                    }
                    if (z3) {
                        this.mActivity.isDisposeableUnableExitAnim = true;
                        bundle16.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                    }
                    JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_APPHOME, this.webUiBinder.getBaseActivity(), bundle16);
                    WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                    if (z3) {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.18
                            @Override // java.lang.Runnable
                            public void run() {
                                CheckNativeImpl.this.mActivity.finish();
                            }
                        }, 500);
                    }
                    return true;
                } else {
                    String pinGouId = WebViewHelper.getPinGouId(str);
                    if (!TextUtils.isEmpty(pinGouId)) {
                        Bundle bundle17 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle17.putAll(bundleFromUrl);
                        }
                        bundle17.putString("skuId", pinGouId);
                        if (z3) {
                            this.mActivity.isDisposeableUnableExitAnim = true;
                            bundle17.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                        }
                        JumpUtil.execJumpByDes("productDetail", this.mActivity, bundle17);
                        WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        if (z3) {
                            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.19
                                @Override // java.lang.Runnable
                                public void run() {
                                    CheckNativeImpl.this.mActivity.finish();
                                }
                            }, 500);
                        }
                        return true;
                    }
                    if (WebViewHelper.isMiniProgUrl(str)) {
                        Bundle bundle18 = new Bundle();
                        if (bundleFromUrl != null) {
                            bundle18.putAll(bundleFromUrl);
                        }
                        if (z3) {
                            this.mActivity.isDisposeableUnableExitAnim = true;
                            bundle18.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                        }
                        MantoActivityUtils.startManoPage(this.webUiBinder.getBaseActivity(), str, bundle18);
                        WebUnifiedMtaUtil.sendExposureMta(this.mActivity, this.TAG, "", str, WebUnifiedMtaUtil.MWEBVIEW_H5TONATIVE, "");
                        if (z3) {
                            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.CheckNativeImpl.20
                                @Override // java.lang.Runnable
                                public void run() {
                                    CheckNativeImpl.this.mActivity.finish();
                                }
                            }, 500);
                        }
                        return true;
                    }
                    return false;
                }
            }
        }
    }
}
