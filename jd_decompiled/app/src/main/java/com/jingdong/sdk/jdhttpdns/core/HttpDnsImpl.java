package com.jingdong.sdk.jdhttpdns.core;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.jdhttpdns.core.NetworkStateObserver;
import com.jingdong.sdk.jdhttpdns.listener.IResolveListener;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import com.jingdong.sdk.jdhttpdns.utils.NetUtils;
import com.jingdong.sdk.jdhttpdns.utils.ThreadUtil;
import com.jingdong.sdk.jdhttpdns.utils.Util;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes7.dex */
public class HttpDnsImpl implements HttpDns, NetworkStateObserver.NetChangeEvent {
    private static final String TAG = "HttpDns";
    public static Context applicationContext;
    private static boolean hasRegisterBroadCastReceive;
    private ExecutorService mExecutorService;
    private String mHosts;
    private IResolveListener mListener;
    private DNSMemoryCache memoryCache;
    private NetworkHandler networkHelper;
    private HashSet<String> requestSet;
    private JDHttpDnsToolkit toolkit;

    public HttpDnsImpl(JDHttpDnsToolkit jDHttpDnsToolkit) {
        this.toolkit = jDHttpDnsToolkit;
        applicationContext = jDHttpDnsToolkit.getApplication();
        this.memoryCache = new DNSMemoryCache();
        this.networkHelper = new NetworkHandler();
        this.mExecutorService = ThreadUtil.getExecutorService();
        this.requestSet = new HashSet<>();
        NetworkStateObserver.addEventListener(this);
        if (hasRegisterBroadCastReceive || !jDHttpDnsToolkit.isRefreshCacheAfterNetStateChanged()) {
            return;
        }
        NetworkStateObserver.registListenBroadCast(applicationContext);
        hasRegisterBroadCastReceive = true;
    }

    private void excuteResolveTask(String str, IResolveListener iResolveListener) {
        excuteResolveTask(str, iResolveListener, false);
    }

    @Override // com.jingdong.sdk.jdhttpdns.core.HttpDns
    public void clearCache(String str) {
        DNSLog.d("clearCache  host:" + str);
        if (TextUtils.isEmpty(str)) {
            this.memoryCache.clear();
        } else {
            this.memoryCache.clear(str);
        }
    }

    public synchronized boolean contains(String str) {
        return this.requestSet.contains(str);
    }

    @Override // com.jingdong.sdk.jdhttpdns.core.HttpDns
    public IpModel getIpModelByHost(String str, boolean z) {
        IpModel ipModelByHost = this.memoryCache.getIpModelByHost(str);
        if (ipModelByHost == null) {
            if (!TextUtils.isEmpty(str)) {
                DNSLog.w(TAG, "[Null ipModel] : " + str + " is not exist, then fetch again.");
                excuteResolveTask(str, null, z);
            }
            if (JDHttpDnsToolkit.getInstance().getRouteSelector().isCdnIpv6Enable() || !z || ipModelByHost == null || TextUtils.isEmpty(ipModelByHost.master) || InetAddressUtils.isIPv4Address(ipModelByHost.master)) {
                return ipModelByHost;
            }
            this.memoryCache.clear(str);
            if (!TextUtils.isEmpty(str)) {
                excuteResolveTask(str, null, z);
            }
            return null;
        } else if (Util.isExpire(ipModelByHost)) {
            if (!TextUtils.isEmpty(str)) {
                DNSLog.w(TAG, "[Expired ipModel] : " + str);
                excuteResolveTask(str, null, z);
            }
            return null;
        } else {
            return ipModelByHost;
        }
    }

    public DNSMemoryCache getMemoryCache() {
        return this.memoryCache;
    }

    public NetworkHandler getNetworkHelper() {
        return this.networkHelper;
    }

    @Override // com.jingdong.sdk.jdhttpdns.core.NetworkStateObserver.NetChangeEvent
    public void onNetworkChange() {
        if (NetUtils.isNetworkAvailable()) {
            JDHttpDnsToolkit.getInstance().getRouteSelector().notifyDataHasChanged();
            if (!JDHttpDnsToolkit.getInstance().isThirdPartApp()) {
                excuteResolveTask(HttpDnsConfig.PREDOWNLOAD_PARAMS, this.mListener);
            } else if (TextUtils.isEmpty(this.mHosts)) {
            } else {
                excuteResolveTask(this.mHosts, this.mListener);
            }
        }
    }

    public synchronized void put(String str) {
        this.requestSet.add(str);
    }

    public synchronized void remove(String str) {
        this.requestSet.remove(str);
    }

    @Override // com.jingdong.sdk.jdhttpdns.core.HttpDns
    public void startDomainResolve(IResolveListener iResolveListener, String... strArr) {
        DNSLog.d("startDomainResolve >>>>>");
        String concat = ParamHelper.concat(strArr);
        this.mHosts = concat;
        this.mListener = iResolveListener;
        excuteResolveTask(concat, iResolveListener);
    }

    public String uniqueId(String str) {
        return str;
    }

    private void excuteResolveTask(String str, IResolveListener iResolveListener, boolean z) {
        if (PrivacyController.isBackground()) {
            DNSLog.d("\u8fdb\u5165\u540e\u53f0\u62e6\u622a\u53d1\u9001\u8bf7\u6c42");
            return;
        }
        String uniqueId = uniqueId(str);
        if (contains(uniqueId)) {
            return;
        }
        put(uniqueId);
        this.mExecutorService.submit(new WorkRunnable(this, str, iResolveListener, z));
    }
}
