package com.jingdong.app.mall.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.mall.update.UpdateInitialization;
import com.jingdong.app.mall.widget.WidgetUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.httpdns.DnsResolver;
import com.jingdong.common.network.NetworkEventObservable;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.jingdong.common.utils.BssidFetcher;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.utils.JDDnsUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class o {
    public static boolean b = true;
    private BroadcastReceiver a = new a();

    /* loaded from: classes4.dex */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            o.this.c(context);
            boolean isNetworkAvailable = NetUtils.isNetworkAvailable();
            boolean isWifi = NetUtils.isWifi();
            BssidFetcher.getInstance().updateBssid(isNetworkAvailable, isWifi);
            if (!isInitialStickyBroadcast()) {
                try {
                    NetworkEventObservable.getInstance().notifyObservers();
                    DnsResolver.getInstance().reset();
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    if (connectivityManager != null) {
                        JDHttpTookit.getEngine().getConnectivityChangeObserver().checkConnect(connectivityManager.getActiveNetworkInfo());
                        JdImageToolKit.getEngine().getConnectivityChangeObserver().checkConnect(connectivityManager.getActiveNetworkInfo());
                    }
                } catch (Exception unused) {
                }
            }
            if (Configuration.getBooleanProperty(Configuration.BEFORE_INIT_TIP).booleanValue() && !CommonBase.getJdSharedPreferences().getBoolean(Configuration.HAS_INIT_TIP, false)) {
                if (Log.D) {
                    Log.d("ListenNetState", "------\u672a\u540c\u610f\u8054\u7f51----");
                    return;
                }
                return;
            }
            if (Log.D) {
                Log.d("ListenNetState", "------\u5df2\u540c\u610f\u8054\u7f51----");
            }
            if (isNetworkAvailable && !o.b) {
                UpdateInitialization.getUpdateInitializationInstance().initNetwork();
            }
            try {
                if (CommonBase.getBooleanFromPreference(ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.FALSE).booleanValue() && isWifi) {
                    com.jingdong.app.mall.update.ApplicationUpgradeHelper.resumeUploadService();
                }
                if (!isWifi && isNetworkAvailable) {
                    com.jingdong.app.mall.update.ApplicationUpgradeHelper.stopUploadService();
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("ListenNetState", "resume or stop download apk->" + e2);
                }
            }
            if (isNetworkAvailable && !XView2Manager.mIsHasXViewData && !SwitchQueryFetcher.isXTime() && !o.b) {
                OKLog.i("ListenNetState", "XView2 \u76d1\u63a7\u7f51\u7edc\u72b6\u6001");
                XView2Manager.getInstance().requestXViewData();
            }
            if (OKLog.D) {
                OKLog.i("ListenNetState", "\u5373\u5c06\u9884\u52a0\u8f7dHttpDns\u914d\u7f6e\uff0c\u662f\u5426\u4e3a\u9996\u6b21\u521d\u59cb\u5316 : " + isInitialStickyBroadcast());
            }
            if (isNetworkAvailable && JDDnsUtil.getInstance().shouldOpenDnsControl()) {
                if (OKLog.D) {
                    OKLog.i("ListenNetState", "\u7f51\u7edc\u53ef\u7528\u4e14\u914d\u7f6e\u5f00\u5173\u5df2\u7ecf\u6253\u5f00\uff0c\u5f00\u59cb\u9884\u52a0\u8f7d");
                }
                JDDnsUtil.getInstance().preloadHttpDnsIp();
            }
            if (isWifi || "3G".equals(NetUtils.getNetworkType().toUpperCase()) || "4G".equals(NetUtils.getNetworkType().toUpperCase())) {
                jd.wjlogin_sdk.util.f.f19954c.equals(ProcessUtil.getProcessName(context));
            }
            o.b = false;
            if (NetUtils.isNetworkAvailable()) {
                WidgetUtils.q();
            }
        }
    }

    private void b() {
        BaseActivity a2 = com.jingdong.app.mall.e.b().a();
        if (a2 != null) {
            a2.checkNetwork();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        if (Log.D) {
            Log.d("ListenNetState", " -->> doNetwork()");
        }
        String networkType = NetUtils.getNetworkType();
        if (Log.D) {
            Log.d("ListenNetState", " -->> doNetwork() type " + networkType);
        }
        if (NetUtils.isNetworkAvailable()) {
            NetUtils.setOffNetwork(false);
            if (Log.D) {
                Log.d("ListenNetState", " -->> doNetwork() getCurrentType " + NetUtils.getCurrentType());
            }
            NetUtils.setCurrentType(networkType);
            b();
        } else if (NetUtils.isOffNetwork()) {
        } else {
            NetUtils.setOffNetwork(true);
            NetUtils.setCurrentType(networkType);
            b();
        }
    }

    public void d(Context context) {
        if (Log.D) {
            Log.d("ListenNetState", " -->> registerReceiver()");
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this.a, intentFilter);
    }

    public void e(Context context) {
        if (Log.D) {
            Log.d("ListenNetState", " -->> unRegisterReceiver()");
        }
        try {
            context.unregisterReceiver(this.a);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
