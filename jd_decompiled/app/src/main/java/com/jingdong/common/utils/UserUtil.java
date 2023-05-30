package com.jingdong.common.utils;

import android.content.Intent;
import android.text.TextUtils;
import com.jd.security.jdguard.a.c;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.login.LoginLocationUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.WebReqCookieUtil;
import com.jingdong.common.web.WebLoginHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.ProxyConfig;
import com.wjlogin.onekey.sdk.common.OneKeyLoginHelper;
import com.wjlogin.onekey.sdk.model.OneKeyInfo;
import de.greenrobot.event.EventBus;
import java.net.URI;
import java.util.Map;
import jd.wjlogin_sdk.common.WJDGuardProxy;
import jd.wjlogin_sdk.common.WJLoginClientInfoProxy;
import jd.wjlogin_sdk.common.WJLoginElderProxy;
import jd.wjlogin_sdk.common.WJLoginExtendProxy;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.WJLoginPrivacyProxy;
import jd.wjlogin_sdk.common.WjLoginHttpDnsProxy;
import jd.wjlogin_sdk.model.ClientInfo;
import jd.wjlogin_sdk.model.FLDeviceInfo;
import jd.wjlogin_sdk.model.IpModel;

/* loaded from: classes.dex */
public class UserUtil {
    private static final short JD_LOGIN_APP_ID = 100;
    private static final String TAG = "LoginSDK.UserUtil";
    private static ClientInfo clientInfo;
    private static WJLoginHelper helper;
    private static OneKeyLoginHelper oneKeyLoginHelper;
    private static WJLoginExtendProxy mLoginParamProxy = new WJLoginExtendProxy() { // from class: com.jingdong.common.utils.UserUtil.2
        @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
        public String getArea() {
            try {
                JDLocation cacheLocation = LoginLocationUtil.getCacheLocation();
                return cacheLocation.getLng() + CartConstant.KEY_YB_INFO_LINK + cacheLocation.getLat();
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }

        @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
        public String getDeviceFinger() {
            return LoginUserBase.getDeviceJson();
        }

        @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
        public String getJMAFinger() {
            return JMA.getSoftFingerprint(JdSdk.getInstance().getApplication());
        }

        @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
        public String getUuid() {
            try {
                String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
                return TextUtils.isEmpty(readDeviceUUID) ? "" : readDeviceUUID;
            } catch (Throwable unused) {
                return "";
            }
        }
    };
    private static WjLoginHttpDnsProxy mWjLoginHttpDnsProxy = new WjLoginHttpDnsProxy() { // from class: com.jingdong.common.utils.UserUtil.3
        @Override // jd.wjlogin_sdk.common.WjLoginHttpDnsProxy
        public IpModel getIpModel(String str) {
            if (str != null && !"".equals(str)) {
                try {
                    com.jingdong.sdk.jdhttpdns.pojo.IpModel ipModelByHost = JDHttpDnsToolkit.getInstance().getIpModelByHost(str);
                    if (ipModelByHost == null) {
                        return null;
                    }
                    IpModel ipModel = new IpModel();
                    ipModel.master = ipModelByHost.master;
                    ipModel.host = ipModelByHost.host;
                    ipModel.updateTime = ipModelByHost.updateTime;
                    ipModel.ttl = ipModelByHost.ttl;
                    ipModel.v4 = ipModelByHost.v4;
                    ipModel.v6 = ipModelByHost.v6;
                    return ipModel;
                } catch (Exception unused) {
                }
            }
            return null;
        }
    };
    private static WJLoginElderProxy elderProxy = new WJLoginElderProxy() { // from class: com.jingdong.common.utils.UserUtil.4
        @Override // jd.wjlogin_sdk.common.WJLoginElderProxy
        public String getElderUemps() {
            return !JDElderModeUtils.isElderMode() ? "0" : "1";
        }
    };
    private static WJLoginPrivacyProxy privacyProxy = new WJLoginPrivacyProxy() { // from class: com.jingdong.common.utils.UserUtil.5
        @Override // jd.wjlogin_sdk.common.WJLoginPrivacyProxy
        public boolean isWJAgreePrivacy() {
            return JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
        }
    };
    private static WJLoginClientInfoProxy clientInfoProxy = new WJLoginClientInfoProxy() { // from class: com.jingdong.common.utils.UserUtil.6
        @Override // jd.wjlogin_sdk.common.WJLoginClientInfoProxy
        public String getJDDeviceBrand() {
            return BaseInfo.getDeviceBrand();
        }

        @Override // jd.wjlogin_sdk.common.WJLoginClientInfoProxy
        public String getJDDeviceModel() {
            return BaseInfo.getDeviceModel();
        }

        @Override // jd.wjlogin_sdk.common.WJLoginClientInfoProxy
        public String getJDDeviceName() {
            return BaseInfo.getDeviceName();
        }

        @Override // jd.wjlogin_sdk.common.WJLoginClientInfoProxy
        public String getJDOsVer() {
            return BaseInfo.getAndroidVersion();
        }

        @Override // jd.wjlogin_sdk.common.WJLoginClientInfoProxy
        public String getJDScreen() {
            return BaseInfo.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + BaseInfo.getScreenWidth();
        }
    };
    private static WJDGuardProxy guardProxy = new WJDGuardProxy() { // from class: com.jingdong.common.utils.UserUtil.7
        @Override // jd.wjlogin_sdk.common.WJDGuardProxy
        public Map<String, String> getJDGuardSign(URI uri, byte[] bArr, String str, String str2, boolean z) {
            return c.b(uri, bArr, str, str2, z);
        }
    };

    public static void cleanData(IMyActivity iMyActivity) {
        clearWebViewCookie(iMyActivity);
        CommonBase.getJdSharedPreferences().edit().remove("redDotParams").commit();
        EventBus.getDefault().post(new LoginEvent(LoginEvent.TYPE_LOGOUT));
        Intent intent = new Intent("com.jingdong.action.user.login.out");
        intent.setPackage(JdSdk.getInstance().getApplicationContext().getPackageName());
        JdSdk.getInstance().getApplication().sendBroadcast(intent);
        WebLoginHelper.onUserLoginChange(false, 1);
    }

    public static void clearWebViewCookie(final IMyActivity iMyActivity) {
        new Thread(new Runnable() { // from class: com.jingdong.common.utils.UserUtil.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    IMyActivity iMyActivity2 = iMyActivity;
                    if (iMyActivity2 != null && iMyActivity2.getThisActivity() != null) {
                        CookieSyncManager.createInstance(iMyActivity.getThisActivity());
                        CookieManager.getInstance().removeAllCookie();
                        CookieSyncManager.getInstance().sync();
                    }
                } catch (Throwable unused) {
                }
            }
        }).start();
    }

    public static synchronized ClientInfo getClientInfo() {
        ClientInfo clientInfo2;
        synchronized (UserUtil.class) {
            if (clientInfo == null) {
                ClientInfo clientInfo3 = new ClientInfo();
                clientInfo = clientInfo3;
                clientInfo3.setDwAppID(getJdLoginAppId());
                clientInfo.setAppName(CashierSdkGlobalConfig.CASHIER_SDK_SOURCE);
                clientInfo.setPartner(Configuration.getProperty(Configuration.PARTNER));
                clientInfo.setUnionId(Configuration.getProperty(Configuration.UNION_ID));
                clientInfo.setSubunionId(Configuration.getProperty(Configuration.SUB_UNION_ID));
                clientInfo.setWJAgreePrivacy(JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()));
                clientInfo.setDeviceBrand(BaseInfo.getDeviceBrand());
                clientInfo.setDeviceModel(BaseInfo.getDeviceModel());
                clientInfo.setDeviceName(BaseInfo.getDeviceName());
                clientInfo.setOsVer(BaseInfo.getAndroidVersion());
                clientInfo.setScreen(BaseInfo.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + BaseInfo.getScreenWidth());
                clientInfo.setFristInstallTime(BaseInfo.getAppFirstInstallTime());
                clientInfo.setLastUpdateTime(BaseInfo.getAppLastUpdateTime());
            }
            clientInfo2 = clientInfo;
        }
        return clientInfo2;
    }

    public static FLDeviceInfo getFLDeviceInfo() {
        return new FLDeviceInfo();
    }

    public static short getJdLoginAppId() {
        return JD_LOGIN_APP_ID;
    }

    private static OneKeyInfo getOneKeyInfo() {
        OneKeyInfo oneKeyInfo = new OneKeyInfo();
        oneKeyInfo.setCmAppId(LoginConstans.CHINA_MOBILE_APP_ID);
        oneKeyInfo.setCmAppKey(LoginConstans.CHINA_MOBILE_APP_KEY);
        oneKeyInfo.setCuClientId(LoginConstans.CHINA_UNICOM_APPID);
        oneKeyInfo.setCuClientSecret(LoginConstans.CHINA_UNICOM_APPSECRET);
        oneKeyInfo.setCtAppId(LoginConstans.CT_APPID);
        oneKeyInfo.setCtAppSecret(LoginConstans.CT_APPSECRET);
        return oneKeyInfo;
    }

    public static synchronized OneKeyLoginHelper getOneKeyLoginHelper() {
        OneKeyLoginHelper oneKeyLoginHelper2;
        synchronized (UserUtil.class) {
            if (oneKeyLoginHelper == null) {
                if (OKLog.I && JdSdk.getInstance().getApplication().getApplicationContext() == null) {
                    OKLog.i(TAG, "getOneKeyLoginHelper JdSdk.getInstance().getApplication().getApplicationContext() is null!");
                }
                oneKeyLoginHelper = OneKeyLoginHelper.createInstance(JdSdk.getInstance().getApplication(), getOneKeyInfo(), false);
                if (OKLog.I) {
                    OKLog.i(TAG, "create getOneKeyLoginHelper in UserUtil");
                }
            }
            oneKeyLoginHelper2 = oneKeyLoginHelper;
        }
        return oneKeyLoginHelper2;
    }

    public static synchronized WJLoginHelper getWJLoginHelper() {
        WJLoginHelper wJLoginHelper;
        synchronized (UserUtil.class) {
            if (helper == null) {
                if (OKLog.I && JdSdk.getInstance().getApplication().getApplicationContext() == null) {
                    OKLog.i(TAG, "getWJLoginHelper JdSdk.getInstance().getApplication().getApplicationContext() is null!");
                }
                WJLoginHelper createInstance = WJLoginHelper.createInstance(JdSdk.getInstance().getApplication(), getClientInfo(), false);
                helper = createInstance;
                createInstance.setWJLoginExtendProxy(mLoginParamProxy);
                helper.setWJLoginHttpDnsProxy(mWjLoginHttpDnsProxy);
                helper.setElderProxy(elderProxy);
                helper.setPrivacyProxy(privacyProxy);
                helper.setClientInfoProxy(clientInfoProxy);
                helper.setWJdGuardProxy(guardProxy);
                if (OKLog.I) {
                    OKLog.i(TAG, "create WJLoginHelper in UserUtil");
                }
            }
            wJLoginHelper = helper;
        }
        return wJLoginHelper;
    }

    public static void onLogout(IMyActivity iMyActivity) {
        if (OKLog.D) {
            OKLog.d(TAG, " onLogout --> ");
        }
        AddressUtil.updateAddressByAll(AddressUtil.getAddressGlobal());
        LoginUserBase.setUserStateOff(true);
        ShoppingBaseController.clearLocalCart();
        Constants.clearOrderInfo();
        JDMtaUtils.clearMtaContent();
        JMAUtils.getBlog(true);
        EventBus.getDefault().post(new LoginEvent(LoginEvent.TYPE_READY_LOGOUT));
        getWJLoginHelper().exitLogin();
        getWJLoginHelper().getLoginConfig();
        WebReqCookieUtil.clearWebCookie(1);
        cleanData(iMyActivity);
    }

    private static String spilitSubString(String str, int i2) {
        if (str != null) {
            try {
                if (str.length() > i2) {
                    str = str.substring(0, i2);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        return str == null ? "" : str;
    }
}
