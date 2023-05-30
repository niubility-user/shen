package com.jingdong.app.mall.utils;

import android.text.TextUtils;
import com.jd.mobile.image.config.IAVIFWhitePageEnable;
import com.jd.mobile.image.config.ICDNDomainResolver;
import com.jd.mobile.image.config.IExceptionReportHandler;
import com.jd.mobile.image.config.IGIFWhitePageEnable;
import com.jd.mobile.image.config.IHttpDnsDependency;
import com.jd.mobile.image.config.IImageController;
import com.jd.mobile.image.config.INetStatReporter;
import com.jd.mobile.image.config.INetworkParameter;
import com.jd.mobile.image.config.IOtherDependency;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.common.httpdns.IpModel;
import com.jingdong.common.network.JDNetworkDependencyFactory;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.NoImageUtils;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.depend.DependUtil;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.utils.JDDnsUtil;
import com.jingdong.jdsdk.network.utils.NetworkXConfig;
import com.jingdong.jdsdk.network.utils.StatSharePreferenceUtil;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class k {
    public static boolean a;

    /* loaded from: classes4.dex */
    public class a implements IExceptionReportHandler {
        a() {
        }

        @Override // com.jd.mobile.image.config.IExceptionReportHandler
        public void reportBitmapException(String str, JDFailReason jDFailReason, String str2, int i2) {
            Throwable cause = jDFailReason.getCause();
            if (cause == null || !(cause instanceof IllegalArgumentException) || str == null || !str.equals(JDImageUtils.FAKE_URI_EMPTY)) {
                ExceptionReporter.reportBitmapException(str, jDFailReason, str2, i2);
            }
        }

        @Override // com.jd.mobile.image.config.IExceptionReportHandler
        public void reportCDNDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4) {
            ExceptionReporter.reportCDNDowngradeData(str, str2, str3, z, i2, str4, "app_image", "\u539f\u751f\u56fe\u7247");
        }

        @Override // com.jd.mobile.image.config.IExceptionReportHandler
        public void reportClearTextRequest(String str) {
            ExceptionReporter.reportFrescoClearTextRequest(str);
        }

        @Override // com.jd.mobile.image.config.IExceptionReportHandler
        public void reportDpgPicMta(String str) {
            ExceptionReporter.reportDpgPicMta(str);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements INetworkParameter {
        b() {
        }

        @Override // com.jd.mobile.image.config.INetworkParameter
        public String getCustomUseAgent() {
            if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDRequestIdentifier", "switch", "imgUASwitch"), "1")) {
                return StatisticsReportUtil.UserAgentResolver.getSafeUserAgent();
            }
            return null;
        }

        @Override // com.jd.mobile.image.config.INetworkParameter
        public boolean isForce2HttpFlag() {
            return JDNetworkDependencyFactory.getExternalDebugConfigImpl().isForceHttpDownGrade();
        }

        @Override // com.jd.mobile.image.config.INetworkParameter
        public boolean isUseDomainFlag() {
            return !JDDnsUtil.getInstance().isImageDnsControlEnable();
        }

        @Override // com.jd.mobile.image.config.INetworkParameter
        public boolean isUseHttps() {
            if (NetworkXConfig.isXTime()) {
                return k.j();
            }
            return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "image", RuntimeConfigHelper.HTTPS_SWITCH, "1"), "1");
        }

        @Override // com.jd.mobile.image.config.INetworkParameter
        public boolean isUseOKHttp() {
            return DependUtil.getInstance().getDepend().isUseOkhttp();
        }

        @Override // com.jd.mobile.image.config.INetworkParameter
        public boolean needReferer() {
            return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDRequestIdentifier", "switch", "imgRefererSwitch"), "1");
        }
    }

    /* loaded from: classes4.dex */
    public class c implements IImageController {
        c() {
        }

        @Override // com.jd.mobile.image.config.IImageController
        public String getThisPageInfo() {
            return com.jingdong.jdsdk.b.a.c();
        }

        @Override // com.jd.mobile.image.config.IImageController
        public boolean needNoImage() {
            return NoImageUtils.needNoImage();
        }
    }

    /* loaded from: classes4.dex */
    public class d implements IOtherDependency {
        d() {
        }

        @Override // com.jd.mobile.image.config.IOtherDependency
        public void sentryMarkRequestInfo(String str, int i2, int i3, int i4, String str2, int i5) {
        }
    }

    /* loaded from: classes4.dex */
    public class e implements INetStatReporter {
        e() {
        }

        @Override // com.jd.mobile.image.config.INetStatReporter
        public void saveStatisticData(HashMap<String, Integer> hashMap) {
            StatSharePreferenceUtil.putIntMap(hashMap);
        }
    }

    /* loaded from: classes4.dex */
    public class f implements IHttpDnsDependency {
        f() {
        }

        @Override // com.jd.mobile.image.config.IHttpDnsDependency
        public IpModel getIpModelByHost(String str, boolean z) {
            com.jingdong.sdk.jdhttpdns.pojo.IpModel ipModelByHost;
            if (z) {
                ipModelByHost = JDHttpDnsToolkit.getInstance().getIpFromMemoryCache(str);
            } else {
                ipModelByHost = JDHttpDnsToolkit.getInstance().getIpModelByHost(str, true);
            }
            if (ipModelByHost == null) {
                return null;
            }
            return new IpModel(ipModelByHost.host, ipModelByHost.master, ipModelByHost.v4, ipModelByHost.v6);
        }
    }

    /* loaded from: classes4.dex */
    public class g implements ICDNDomainResolver {
        g() {
        }

        @Override // com.jd.mobile.image.config.ICDNDomainResolver
        public String getConfig() {
            return SwitchQueryFetcher.getSwitchStringValue("native-retry-config", "");
        }
    }

    /* loaded from: classes4.dex */
    public class h implements IAVIFWhitePageEnable {
        h() {
        }

        @Override // com.jd.mobile.image.config.IAVIFWhitePageEnable
        public boolean isAVIFWhitePageEnable() {
            return TextUtils.equals("1", SwitchQueryFetcher.getSwitchStringValue("AVIFWhitePageEnable", "0")) && k.a;
        }
    }

    /* loaded from: classes4.dex */
    public class i implements IGIFWhitePageEnable {
        i() {
        }

        @Override // com.jd.mobile.image.config.IGIFWhitePageEnable
        public boolean isGIFWhitePageEnable() {
            return TextUtils.equals("1", SwitchQueryFetcher.getSwitchStringValue("gif2webpEnable", "0")) && k.a;
        }
    }

    public static ICDNDomainResolver a() {
        return new g();
    }

    public static IExceptionReportHandler b() {
        return new a();
    }

    public static IHttpDnsDependency c() {
        return new f();
    }

    public static IAVIFWhitePageEnable d() {
        return new h();
    }

    public static IGIFWhitePageEnable e() {
        return new i();
    }

    public static IImageController f() {
        return new c();
    }

    public static INetStatReporter g() {
        return new e();
    }

    public static INetworkParameter h() {
        return new b();
    }

    public static IOtherDependency i() {
        return new d();
    }

    public static boolean j() {
        try {
            if (SwitchQueryFetcher.getFetcher().getFetchStatus() == -1) {
                return false;
            }
            return TextUtils.equals(SwitchQueryFetcher.getSwitchStringValue("isImgHttps", "1"), "1");
        } catch (Throwable unused) {
            return true;
        }
    }
}
