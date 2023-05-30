package com.jingdong.common.jdreactFramework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.facebook.react.ReactPackage;
import com.facebook.react.modules.clipboard.ClipboardSettingHelper;
import com.facebook.react.modules.network.BaseInfoHelper;
import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.global.PasteStateRouterImpl;
import com.jingdong.app.mall.utils.q;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdate;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateRequest;
import com.jingdong.common.jdreactFramework.fragment.RNMFragment;
import com.jingdong.common.jdreactFramework.helper.ReactPackageFactory;
import com.jingdong.common.jdreactFramework.lifecycle.RNLifeCycleObserver;
import com.jingdong.common.jdreactFramework.listener.JDRNDataReportListener;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSharedDataModule;
import com.jingdong.common.jdreactFramework.preload.JDReactCommonPreloadManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes5.dex */
public class JDReactManager {
    private static boolean isLoad;

    /* loaded from: classes5.dex */
    public static class CustomDns implements Dns {
        InetAddress ip2InetAddress(String str, String str2) {
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            try {
                if (InetAddressUtils.isIPv4Address(str2)) {
                    String[] split = str2.split("\\.");
                    byte[] bArr = new byte[4];
                    for (int i2 = 0; i2 < 4; i2++) {
                        bArr[i2] = (byte) (Integer.parseInt(split[i2]) & 255);
                    }
                    try {
                        return InetAddress.getByAddress(str, bArr);
                    } catch (UnknownHostException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
                byte[] bArr2 = new byte[0];
                try {
                    bArr2 = InetAddress.getByName(str2.substring(1, str2.length() - 1)).getAddress();
                } catch (UnknownHostException e3) {
                    e3.printStackTrace();
                }
                byte[] bArr3 = new byte[16];
                System.arraycopy(bArr2, 0, bArr3, 0, 16);
                try {
                    return Inet6Address.getByAddress(str, bArr3);
                } catch (UnknownHostException e4) {
                    e4.printStackTrace();
                    return null;
                }
            } catch (Exception e5) {
                e5.printStackTrace();
                return null;
            }
            e5.printStackTrace();
            return null;
        }

        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            InetAddress ip2InetAddress;
            IpModel ipModelByHost = JDHttpDnsToolkit.getInstance().getIpModelByHost(str);
            if (ipModelByHost != null && (ip2InetAddress = ip2InetAddress(str, ipModelByHost.getIp())) != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(ip2InetAddress);
                return arrayList;
            }
            return Dns.SYSTEM.lookup(str);
        }
    }

    private static void checkUpdate() {
        q.c().d(new Runnable() { // from class: com.jingdong.common.jdreactFramework.JDReactManager.6
            @Override // java.lang.Runnable
            public void run() {
                ReactNativeUpdate.getInstance().reactUnzipSo();
                ReactNativeUpdate.getInstance().checkUpdate();
            }
        });
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static JDReactAuraHelper.CommonInvokeInterface createCommonInvokeInterface() {
        return new JDReactAuraHelper.CommonInvokeInterface() { // from class: com.jingdong.common.jdreactFramework.JDReactManager.1
            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public String getAvailabilityPaysuccessConstant() {
                return JDReactConstant.AVAILABILITY_PAYSUCCESS;
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public boolean getModuleAvailability(String str) {
                return ReactModuleAvailabilityUtils.getModuleAvailability(str);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public String getModuleBackupUrl() {
                return ReactModuleAvailabilityUtils.getModuleBackupUrl(JDReactConstant.AVAILABILITY_PAYSUCCESS);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public String getReactModulePluginPath(String str) {
                PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JdSdk.getInstance().getApplication(), str);
                if (pluginDir != null) {
                    return pluginDir.pluginDir;
                }
                return null;
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public String getReactModuleUpdateState(String str) {
                return ReactSharedPreferenceUtils.getDownLoadingStatus(str).status;
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public boolean isReactModuleForceUpate(String str) {
                return ReactNativeUpdateManager.getInstance().isItForceUpdate(str);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchCardPwdBuyMain(Context context, Bundle bundle) {
                ReactActivityUtils.startJDReactCardPwdBuyMain(context, bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchCardPwdDetail(Context context, Bundle bundle) {
                ReactActivityUtils.startJDReactCardPwdDetail(context, bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchGamePropBuyActivity(Context context, Intent intent) {
                ReactActivityUtils.startJDReactNativeGamePropBuyActivity(context, intent);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchJDReactCommonActivity(Context context, Bundle bundle) {
                ReactActivityUtils.startJDReactCommonActivity(context, bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchJshopMineActivity(Context context, Intent intent) {
                ReactActivityUtils.startJDReactJShopMineActivity(context, intent);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchLivingPayActivity(Context context, Bundle bundle) {
                ReactActivityUtils.startJDReactLivingPayActivity(context, bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchLivingPayOrderDetailActivity(Context context, Bundle bundle) {
                ReactActivityUtils.startJDReactLivingPayOrderDetailActivity(context, bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchLivingPayRecordsActivity(Context context, Bundle bundle) {
                ReactActivityUtils.startJDReactLivingPayRecordsActivity(context, bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchMovieHomeActivity(Context context, Intent intent) {
                ReactActivityUtils.starJDReactMovieHomeActivity(context, intent);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchNewProductActivity(Context context, Intent intent) {
                ReactActivityUtils.startJDReactNewProductActivity(context, intent);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchPaySuccessActivity(Context context, Intent intent) {
                ReactActivityUtils.startJDReactPaySuccessActivity(context, intent);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void launchRankListActivity(Context context, Bundle bundle) {
                ReactActivityUtils.startJDReactRankListActivity(context, bundle);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void putData(String str, String str2) {
                JDReactNativeSharedDataModule.putData(str, str2);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void sendPhoneChargeCoupon(String str, HashMap hashMap) {
                ReactActivityUtils.sendPhoneChargeCoupon(str, hashMap);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public void setPackageManger() {
                AbstractJDReactInitialHelper.setPackageManger(new JDReactExtendPackage());
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public int weUsePreloadData(String str) {
                return ReactVersionUtils.weUsePreloadData(JdSdk.getInstance().getApplication(), str);
            }

            @Override // com.jingdong.common.jdreactFramework.JDReactAuraHelper.CommonInvokeInterface
            public BaseFragment createMFragment() {
                return new RNMFragment();
            }
        };
    }

    public static ReactPackageFactory createReactPackageFactory() {
        return new ReactPackageFactory() { // from class: com.jingdong.common.jdreactFramework.JDReactManager.2
            @Override // com.jingdong.common.jdreactFramework.helper.ReactPackageFactory
            public ReactPackage newReactPackage() {
                return new JDReactExtendPackage();
            }
        };
    }

    public static String getLocalData(Context context) {
        InputStreamReader inputStreamReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open("rn_localdata.json"), "UTF-8");
            try {
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        try {
                            th.printStackTrace();
                            closeQuietly(bufferedReader);
                            closeQuietly(inputStreamReader);
                            return sb.toString();
                        } catch (Throwable th2) {
                            closeQuietly(bufferedReader);
                            closeQuietly(inputStreamReader);
                            throw th2;
                        }
                    }
                }
                closeQuietly(bufferedReader2);
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
        }
        closeQuietly(inputStreamReader);
        return sb.toString();
    }

    public static void init() {
        isLoad = false;
        JDReactHelper.newInstance().setDataReportListener(new JDRNDataReportListener());
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new RNLifeCycleObserver());
        OkHttpClientProvider.setOkHttpClientFactory(new OkHttpClientFactory() { // from class: com.jingdong.common.jdreactFramework.JDReactManager.3
            @Override // com.facebook.react.modules.network.OkHttpClientFactory
            public OkHttpClient createNewNetworkModuleClient() {
                if (TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDReact", "JDReactDNS", "switch", "0"))) {
                    return OkHttpClientProvider.createClientBuilder().dns(new CustomDns()).build();
                }
                return OkHttpClientProvider.createClientBuilder().build();
            }
        });
        JLog.setLogger(new OkLogger());
        checkUpdate();
        try {
            ClipboardSettingHelper.newInstance().setClipboardSettingListener(new ClipboardSettingHelper.ClipboardSettingListener() { // from class: com.jingdong.common.jdreactFramework.JDReactManager.4
                @Override // com.facebook.react.modules.clipboard.ClipboardSettingHelper.ClipboardSettingListener
                public boolean isSupportClipboard() {
                    return PasteStateRouterImpl.getClipPasteStatusValue();
                }
            });
            BaseInfoHelper.newInstance().setBaseInfo(new BaseInfoHelper.BaseInfoInterface() { // from class: com.jingdong.common.jdreactFramework.JDReactManager.5
                @Override // com.facebook.react.modules.network.BaseInfoHelper.BaseInfoInterface
                public String getUserAgent() {
                    return BaseInfo.getUserAgent() + " network/" + BaseInfo.getNetworkType();
                }
            });
        } catch (Exception unused) {
        }
    }

    public static boolean isXTime() {
        try {
            return SwitchQueryFetcher.isXTime();
        } catch (Exception unused) {
            return false;
        }
    }

    public static synchronized void newCheckUpdate(Context context) {
        JDJSONObject optJSONObject;
        synchronized (JDReactManager.class) {
            try {
                if (isXTime()) {
                    if (!isLoad) {
                        JDJSONObject parseObject = JDJSON.parseObject(getLocalData(context));
                        if (parseObject.optJSONObject("result") != null && (optJSONObject = parseObject.optJSONObject("result")) != null) {
                            JDJSONArray optJSONArray = optJSONObject.optJSONArray("upgradeInfo");
                            JDJSONArray optJSONArray2 = optJSONObject.optJSONArray("degradeInfo");
                            if (optJSONArray != null) {
                                ReactNativeUpdateRequest.getInstance(context).parseUpgradeInfo(optJSONArray);
                            }
                            if (optJSONArray2 != null) {
                                ReactNativeUpdateRequest.getInstance(context).parseH5Url(optJSONArray2);
                            }
                            isLoad = true;
                        }
                    }
                } else {
                    ReactNativeUpdate.getInstance().checkUpdate();
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void updateConfig() {
        JDReactCommonPreloadManager.getInstance().setEnable("1".equals(JDMobileConfig.getInstance().getConfig("JDReact", "JDReactCommonPreload", "switch", "0")));
        JDReactCommonPreloadManager.getInstance().setExceptConfigStr(JDMobileConfig.getInstance().getConfig("JDReact", "JDReactCommonPreload", "except", "[]"));
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "network", "enable_download_suspend", "1"), "1")) {
            try {
                JDReactHelper.newInstance().setNetCountDownMills((int) (Double.parseDouble(JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "network", "download_suspend_window", "1")) * 60.0d * 1000.0d));
                return;
            } catch (Exception unused) {
                JDReactHelper.newInstance().setNetCountDownMills(60000);
                return;
            }
        }
        JDReactHelper.newInstance().setNetCountDownMills(0);
    }
}
