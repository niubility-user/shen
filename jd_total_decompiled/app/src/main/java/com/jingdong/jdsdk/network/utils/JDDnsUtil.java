package com.jingdong.jdsdk.network.utils;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import com.jingdong.app.mall.utils.j;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class JDDnsUtil {
    public static final String CONFIG_DNS_VIP_V6 = "dnsvipV6";
    public static final String CONFIG_HTTPDNS = "httpdns";
    public static final String CONFIG_IMAGE_DNS = "imageDNS";
    public static final String CONFIG_IMAGE_V6 = "imageV6Flag";
    public static final String KEY_DNS_VIP = "dnsvip";
    public static final String KEY_DNS_VIP_V6 = "dnsvip_v6";
    public static final String KEY_HTTP_DNS = "httpdns";
    public static final String KEY_IMAGE_DNS = "imageDNS";
    public static final String KEY_IMAGE_V6 = "imageV6Flag";
    public static final String SCOPE_NAME = "JDHttpToolKit";
    private static final String TAG = "JDDnsUtil";
    private static final String[] defaultNetworkDomains = {"api.m.jd.com", Configuration.NEW_MSG_CENTER_HOST, Configuration.PERSONAL_CONFIG_HOST, Configuration.COMMUNITY_HOST};
    private static final String[] defaultNetworkDomainsDebug = {"api.m.jd.com", Configuration.NEW_MSG_CENTER_HOST, Configuration.PERSONAL_CONFIG_HOST, Configuration.COMMUNITY_HOST, "api.m.jd.care", "beta-api.m.jd.com"};
    private static volatile JDDnsUtil instance;
    private boolean isImageDnsControlEnable;
    private boolean isImageV6Enable;
    private boolean isNetworkDnsControlEnable;
    private JDMoblieConfigListener mJdMobileConfigListener = new JDMoblieConfigListener() { // from class: com.jingdong.jdsdk.network.utils.JDDnsUtil.1
        @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener
        public void onConfigUpdate() {
            if (OKLog.D) {
                OKLog.d(JDDnsUtil.TAG, "JDMobileConfig on loaded.");
            }
            if (JDDnsUtil.this.shouldOpenDnsControl()) {
                JDDnsUtil.this.preloadHttpDnsIp();
            } else {
                j.c().g(false);
            }
        }
    };

    private JDDnsUtil() {
        JDMobileConfig.getInstance().registerListener(this.mJdMobileConfigListener);
    }

    public static JDDnsUtil getInstance() {
        if (instance == null) {
            synchronized (JDDnsUtil.class) {
                if (instance == null) {
                    instance = new JDDnsUtil();
                }
            }
        }
        return instance;
    }

    public boolean isImageDnsControlEnable() {
        if (NetworkXConfig.isXTime()) {
            return false;
        }
        return this.isImageDnsControlEnable;
    }

    public boolean isImageV6Enable() {
        return this.isImageV6Enable;
    }

    public boolean isNeedUseIp(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : Configuration.isBeta() ? defaultNetworkDomainsDebug : defaultNetworkDomains) {
            if (TextUtils.equals(str, str2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNetworkDnsControlEnable() {
        if (NetworkXConfig.isXTime()) {
            return false;
        }
        return this.isNetworkDnsControlEnable;
    }

    public void preloadHttpDnsIp() {
        JDHttpDnsToolkit.getInstance().startDomainResolve(HttpDnsConfig.PREDOWNLOAD_PARAMS);
    }

    public void setDnsControl(boolean z) {
        this.isNetworkDnsControlEnable = z;
        this.isImageDnsControlEnable = z;
    }

    public boolean shouldOpenDnsControl() {
        String str;
        boolean z = false;
        if (NetworkXConfig.isXTime()) {
            return false;
        }
        this.isNetworkDnsControlEnable = TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "httpdns", "httpdns"));
        boolean equals = TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "imageDNS", "imageDNS"));
        this.isImageDnsControlEnable = equals;
        if (equals) {
            this.isImageV6Enable = TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "imageV6Flag", "imageV6Flag"));
            if (JDHttpDnsToolkit.getInstance() != null) {
                JDHttpDnsToolkit.getInstance().setCdnIpv6Enable(this.isImageV6Enable);
            }
        }
        boolean z2 = this.isImageDnsControlEnable || this.isNetworkDnsControlEnable;
        String str2 = "";
        if (z2) {
            str2 = JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "httpdns", KEY_DNS_VIP);
            str = JDMobileConfig.getInstance().getConfig("JDHttpToolKit", CONFIG_DNS_VIP_V6, KEY_DNS_VIP_V6);
            if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
                if (JDHttpDnsToolkit.getInstance() != null) {
                    JDHttpDnsToolkit.getInstance().setDnsVipV4(str2);
                    JDHttpDnsToolkit.getInstance().setDnsVipV6(str);
                }
                z = z2;
            }
        } else {
            z = z2;
            str = "";
        }
        if (OKLog.D) {
            OKLog.d(TAG, "\u3010\u79fb\u52a8\u914d\u7f6eHTTPDNS\u7ebf\u4e0a\u5f00\u5173\u914d\u7f6e\u3011\n --->  isNetworkDnsControlEnable : " + this.isNetworkDnsControlEnable + "\n --->  isImageDnsControlEnable : " + this.isImageDnsControlEnable + "\n --->  isImageV6Enable : " + this.isImageV6Enable + "\n --->  dnsvip : " + str2 + "\n --->  dnsvip_v6 : " + str);
        }
        return z;
    }
}
