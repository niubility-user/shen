package com.jingdong.common.login;

import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import jd.wjweblogin.common.WJWebLoginExtendProxy;
import jd.wjweblogin.common.WJWebLoginHelper;
import jd.wjweblogin.model.WJClientParams;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class WebLoginUtil {
    private static final int APPID = 100;
    private static final String NETAPPID = "h5LoginSDK";
    private static final String SECRETKEY = "7128037d4383450e8e1125b037f6616d";
    private static final String TAG = "WJWebLogin.WebLoginUtil";
    private static WJWebLoginHelper helper;
    public static boolean isRefreshWebCookieed;
    private static WJWebLoginExtendProxy wjWebLoginExtendProxy = new WJWebLoginExtendProxy() { // from class: com.jingdong.common.login.WebLoginUtil.1
        @Override // jd.wjweblogin.common.WJWebLoginExtendProxy
        public String getKoWhiteList() {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("jwl_koHosts_str", "");
            if (OKLog.D) {
                OKLog.d(WebLoginUtil.TAG, "getKoWhiteList koWhiteList =" + switchStringValue);
            }
            return switchStringValue;
        }

        @Override // jd.wjweblogin.common.WJWebLoginExtendProxy
        public String getPin() {
            return UserUtil.getWJLoginHelper().getPin();
        }

        @Override // jd.wjweblogin.common.WJWebLoginExtendProxy
        public String getWhiteList() {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("jwl_whiteHosts_str", "");
            if (OKLog.D) {
                OKLog.d(WebLoginUtil.TAG, "getWhiteList whiteList =" + switchStringValue);
            }
            return switchStringValue;
        }

        @Override // jd.wjweblogin.common.WJWebLoginExtendProxy
        public String getWsKey() {
            return UserUtil.getWJLoginHelper().getA2();
        }

        @Override // jd.wjweblogin.common.WJWebLoginExtendProxy
        public boolean isOpenNewReqWebCookie() {
            boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("jwl_sdkOpen_flag", false);
            if (OKLog.D) {
                OKLog.d(WebLoginUtil.TAG, "isOpenWebCookieSwitch  =" + switchBooleanValue);
            }
            return switchBooleanValue;
        }

        @Override // jd.wjweblogin.common.WJWebLoginExtendProxy
        public void jmaReport(String str, String str2, JSONObject jSONObject) {
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication(), str2, "", str, "", "", jSONObject.toString(), null);
        }

        @Override // jd.wjweblogin.common.WJWebLoginExtendProxy
        public int requestTimeout() {
            int switchIntValue = SwitchQueryFetcher.getSwitchIntValue("jwl_ptLoginTimeout", 5);
            if (OKLog.D) {
                OKLog.d(WebLoginUtil.TAG, "jwl_ptLoginTimeout =" + switchIntValue);
            }
            return 5;
        }
    };

    private static WJClientParams getClientInfo() {
        WJClientParams wJClientParams = new WJClientParams();
        wJClientParams.setAppId(100);
        return wJClientParams;
    }

    public static synchronized WJWebLoginHelper getWJLoginHelper() {
        WJWebLoginHelper wJWebLoginHelper;
        synchronized (WebLoginUtil.class) {
            if (helper == null) {
                WJWebLoginHelper createInstance = WJWebLoginHelper.createInstance(JdSdk.getInstance().getApplication(), getClientInfo(), false);
                helper = createInstance;
                createInstance.setExtendProxy(wjWebLoginExtendProxy);
            }
            wJWebLoginHelper = helper;
        }
        return wJWebLoginHelper;
    }
}
