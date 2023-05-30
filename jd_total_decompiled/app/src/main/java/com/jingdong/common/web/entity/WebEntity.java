package com.jingdong.common.web.entity;

import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.net.URLDecoder;
import java.text.DecimalFormat;

/* loaded from: classes6.dex */
public class WebEntity {
    public static final String ADD_CUSTOM_PARAMS_CHECKED = "webAddCustomParamsChecked";
    @Deprecated
    public static final String CAN_USE_DARK_MODE = "canUseDarkMode";
    public static final String DEFAULTBROWSER_SCHEME = "defaultblogin";
    public static final String IS_IGNORE_SHARE = "isIgnoreShare";
    public static final String IS_USE_CACHE = "isUseCache";
    public static final String KEY_DES = "des";
    public static final String KEY_ONEKEYLOGIN_KEPLER = "onekeylogin";
    public static final String KEY_RETURNURL = "authlogin_returnurl";
    public static final String PRE_CHECKED_NATIVE = "preCheckedNative";
    public static final String VALUE_ONEKEYLOGIN_ANDROIDRETURN = "androidReturn";
    public static final String VALUE_ONEKEYLOGIN_KEPLER = "kepler";
    public String action;
    public String browserlogin_fromurl;
    public String cookieStoreName;
    public String cookieUrl;
    public String des;
    public String fromActivity;
    public double genToken_end;
    public double genToken_start;
    public boolean isCashierDesk;
    public boolean isRegister;
    public boolean isShowMoreBtn;
    public boolean isTopBarGone;
    public boolean isUseCache;
    public boolean isUseCloseBtn;
    public String locUrl;
    public Bundle mBundle;
    public String mTitle;
    public String onekeylogin;
    public String payID;
    public String returnUrl;
    public Uri syncingUri;
    public String thirdApp_key;
    public String url;
    public String urlFromIntent;
    public URLParamMap urlMap;
    public double webviewLoad_end;
    public double webviewLoad_start;
    private final String TAG = WebEntity.class.getSimpleName();
    public JsBridgeEntity jsBridgeEntity = new JsBridgeEntity();
    public boolean isFromGame = false;
    public boolean isFromScan = false;
    public ShareInfo shareInfoInit = new ShareInfo();
    public boolean isFromAuthSdk = false;
    public int oautherror = 2;
    public String oauthCode = "-1";
    public String msgcode = "";
    public boolean isThirdPay = false;
    public boolean isMetroPay = false;
    public boolean thirdPayStatus = false;
    public boolean authJumpLoginFlag = false;
    public boolean isRegist = false;
    public String fromNewFillOrderActivity = "1";
    public boolean needGenToken = true;
    public boolean isISVLoginObfuscator = false;
    public boolean isNeedCookieRet = false;
    public String[] cookieKeys = null;
    public boolean isUseBackBtn = true;
    public boolean isIgnoreShare = false;
    public String idCardImgId = "";
    public boolean showSubPage = false;
    public boolean isNeedCheckToNative = true;
    public boolean statusBarAlwaysTransparent = false;
    public boolean switchImmersiveOpen = true;
    public boolean loginStateSync = false;
    public boolean faceLoginSpecialTag = false;
    public long gentokenEndTimeMillis = 9223372036854770807L;
    public boolean pageFinished = true;
    public boolean genTokenFinished = true;
    public DecimalFormat df = new DecimalFormat("#.###");
    public Tag yct_tag = null;
    public boolean jumpOutSuc = false;
    public String sourceValue = null;
    public boolean preCheckedNative = false;
    public boolean canUseDarkMode = false;
    public boolean useHybrid = false;
    public String offlineId = null;
    public String preLoadId = null;
    public String initReferer = null;
    public int jwebprog = -1;
    public String loadingPlaceHolder = null;
    public boolean showErrView = false;
    public boolean isFromMInside = true;
    public String openAppActivityReferer = "";

    private void checkISVLogin(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if ("1".equals(Uri.parse(str).getQueryParameter("requestIsvToken"))) {
                this.isISVLoginObfuscator = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void init(Bundle bundle) {
        Log.d(this.TAG, "getDataFromBundle:" + bundle.toString());
        this.mBundle = bundle;
        this.initReferer = bundle.getString(MBaseKeyNames.KEY_REFERER);
        String string = bundle.getString("url");
        this.url = string;
        if (string != null) {
            this.url = string.trim();
        }
        if (!TextUtils.isEmpty(this.url) && !this.url.startsWith("https://") && !this.url.startsWith("http://")) {
            ExceptionReporter.reportWebViewCommonError("url_error", this.url, "unexpected url", bundle.toString());
        }
        this.isFromMInside = bundle.getBoolean(WebWhiteScreenHolder.IS_FROM_M_INSIDE, true);
        this.openAppActivityReferer = bundle.getString("openAppActivityReferer", "");
        if (this.needGenToken) {
            this.action = bundle.getString("urlAction");
            try {
                this.urlMap = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap();
            } catch (Exception unused) {
                this.urlMap = null;
            }
            if (TextUtils.isEmpty(this.action)) {
                this.action = RemoteMessageConst.TO;
            }
            if (!TextUtils.isEmpty(this.url)) {
                URLParamMap uRLParamMap = this.urlMap;
                if (uRLParamMap == null || !uRLParamMap.containsKey(this.action)) {
                    URLParamMap uRLParamMap2 = new URLParamMap();
                    this.urlMap = uRLParamMap2;
                    uRLParamMap2.put(this.action, this.url);
                }
            } else {
                URLParamMap uRLParamMap3 = this.urlMap;
                if (uRLParamMap3 != null) {
                    try {
                        this.url = URLDecoder.decode(uRLParamMap3.get((Object) this.action), "utf-8");
                    } catch (Exception unused2) {
                    }
                }
            }
            Log.d(this.TAG, "urlmap:" + this.urlMap);
        }
        if (!bundle.getBoolean(ADD_CUSTOM_PARAMS_CHECKED, false)) {
            this.url = WebUtils.addCustomParams(this.url, this.urlMap, this.action);
        }
        URLParamMap uRLParamMap4 = this.urlMap;
        if (uRLParamMap4 != null) {
            this.urlFromIntent = uRLParamMap4.get((Object) this.action);
        }
        this.locUrl = bundle.getString("locUrl");
        this.isFromGame = bundle.getBoolean(MBaseKeyNames.KEy_FROM_GAME, false);
        this.fromActivity = bundle.getString("fromActivity");
        this.isNeedCookieRet = bundle.getBoolean("isNeedCookieRet", false);
        this.cookieKeys = bundle.getStringArray("cookieKeys");
        this.cookieStoreName = bundle.getString("cookieStoreName");
        this.cookieUrl = bundle.getString("cookieUrl");
        this.isTopBarGone = bundle.getBoolean("isTopBarGone", false);
        this.isUseCloseBtn = bundle.getBoolean(MBaseKeyNames.IS_USE_RIGHT_BUTTON, true);
        this.isUseBackBtn = bundle.getBoolean(MKeyNames.IS_USE_BACK_BUTTON, true);
        this.isUseCache = bundle.getBoolean(IS_USE_CACHE, true);
        this.isIgnoreShare = bundle.getBoolean(IS_IGNORE_SHARE, false);
        this.isFromAuthSdk = bundle.getBoolean(MBaseKeyNames.IS_FROM_AUTHSDK, false);
        this.mTitle = bundle.getString("title");
        this.isShowMoreBtn = bundle.getBoolean(MBaseKeyNames.IS_SHOW_MORE_BTN, true);
        this.showSubPage = bundle.getBoolean("showSubPage", false);
        this.isRegist = bundle.getBoolean("isRegist", false);
        this.isNeedCheckToNative = bundle.getBoolean(MKeyNames.NEED_CHECK_NATIVE, true);
        this.returnUrl = bundle.getString(KEY_RETURNURL);
        this.des = bundle.getString("des");
        this.onekeylogin = bundle.getString(KEY_ONEKEYLOGIN_KEPLER);
        this.browserlogin_fromurl = bundle.getString("browserlogin_fromurl");
        this.jsBridgeEntity.isNeedShare = bundle.getBoolean(MBaseKeyNames.IS_NEED_SHARE, false);
        this.thirdApp_key = bundle.getString("thirdAppKey", "");
        this.isThirdPay = bundle.getBoolean(MBaseKeyNames.KEY_FROM_THIRD_PAY, false);
        this.isMetroPay = bundle.getBoolean(MBaseKeyNames.KEY_FROM_METRO_PAY, false);
        this.statusBarAlwaysTransparent = bundle.getBoolean(MBaseKeyNames.KEY_ALWAYS_TRANSPARENT_STATUSBAR, false);
        this.switchImmersiveOpen = bundle.getBoolean(MBaseKeyNames.KEY_SWITCH_IMMERSIVE_OPEN, true);
        this.yct_tag = (Tag) bundle.getParcelable("yct_tag");
        this.sourceValue = bundle.getString("sourceValue");
        this.preCheckedNative = bundle.getBoolean(PRE_CHECKED_NATIVE, false);
        this.canUseDarkMode = bundle.getBoolean("canUseDarkMode", false);
        this.useHybrid = bundle.getBoolean(MBaseKeyNames.KEY_USE_HYBRID, false);
        this.offlineId = bundle.getString(MBaseKeyNames.KEY_OFFLINE_ID);
        this.preLoadId = bundle.getString(MBaseKeyNames.KEY_PRELOAD_ID);
        this.jwebprog = bundle.getInt(MBaseKeyNames.KEY_JWEBPROG, -1);
        this.loadingPlaceHolder = bundle.getString("placeholder", null);
        this.showErrView = bundle.getBoolean(MKeyNames.SHOW_ERROR_VIEW, false);
        if (this.jsBridgeEntity.isNeedShare) {
            if (bundle.containsKey("shareInfo")) {
                this.shareInfoInit = (ShareInfo) bundle.getParcelable("shareInfo");
            } else {
                this.shareInfoInit = new ShareInfo();
            }
            if (bundle.containsKey("shareUrl")) {
                this.shareInfoInit.setUrl(bundle.getString("shareUrl"));
            }
            if (bundle.containsKey(MBaseKeyNames.SHARE_TITLE)) {
                this.shareInfoInit.setTitle(bundle.getString(MBaseKeyNames.SHARE_TITLE));
            }
            if (bundle.containsKey(MBaseKeyNames.SHARE_ICONURL)) {
                this.shareInfoInit.setIconUrl(bundle.getString(MBaseKeyNames.SHARE_ICONURL));
            }
            if (bundle.containsKey(MBaseKeyNames.SHARE_EVENTFROM)) {
                this.shareInfoInit.setEventFrom(bundle.getString(MBaseKeyNames.SHARE_EVENTFROM));
            }
            if (bundle.containsKey(MBaseKeyNames.SHARE_WXCONTENT)) {
                this.shareInfoInit.setWxcontent(bundle.getString(MBaseKeyNames.SHARE_WXCONTENT));
                this.shareInfoInit.setSummary(bundle.getString(MBaseKeyNames.SHARE_WXCONTENT));
            }
            if (bundle.containsKey(MBaseKeyNames.SHARE_WXMOMENTSCONTENT)) {
                this.shareInfoInit.setWxMomentsContent(bundle.getString(MBaseKeyNames.SHARE_WXMOMENTSCONTENT));
            }
            if (bundle.containsKey(MBaseKeyNames.SHARE_CANCEL_EVENT_ID)) {
                this.shareInfoInit.setCancelEventId(bundle.getString(MBaseKeyNames.SHARE_CANCEL_EVENT_ID));
            }
        }
        this.faceLoginSpecialTag = bundle.getBoolean(LoginConstans.KEY_SPECIAL);
        checkISVLogin(this.urlFromIntent);
    }
}
