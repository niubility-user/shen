package com.jd.framework.network.dialing;

import android.text.TextUtils;
import com.jd.framework.network.dialing.ConnectivityChangeObserver;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import com.jingdong.sdk.oklog.OKLog;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import okhttp3.Dns;

/* loaded from: classes13.dex */
public class LocalDNSDailer implements ConnectivityChangeObserver.Event {
    static Dns SYSTEM = new Dns() { // from class: com.jd.framework.network.dialing.LocalDNSDailer.1
        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str != null) {
                return Arrays.asList(InetAddress.getAllByName(str));
            }
            throw new UnknownHostException("hostname == null");
        }
    };
    static final String TAG = "LocalDNSDailer";
    private static LocalDNSDailer instance;
    private static boolean isFinished;
    private String cachedResult;

    private LocalDNSDailer() {
    }

    private synchronized void clear() {
        this.cachedResult = null;
    }

    public static synchronized LocalDNSDailer getInstance() {
        LocalDNSDailer localDNSDailer;
        synchronized (LocalDNSDailer.class) {
            if (instance == null) {
                instance = new LocalDNSDailer();
            }
            localDNSDailer = instance;
        }
        return localDNSDailer;
    }

    List<InetAddress> dnsLookup() {
        try {
            return (List) GlobalExecutorService.lightExecutorService().submit(new Callable<List<InetAddress>>() { // from class: com.jd.framework.network.dialing.LocalDNSDailer.2
                @Override // java.util.concurrent.Callable
                public List<InetAddress> call() {
                    try {
                        return LocalDNSDailer.SYSTEM.lookup("api.m.jd.com");
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return null;
                    }
                }
            }).get(250L, TimeUnit.MILLISECONDS);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    public String getBestLocalDnsIp(String str) {
        if (TextUtils.equals(str, "api.m.jd.com")) {
            try {
                return selectBestUniformIp();
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        }
        return null;
    }

    @Override // com.jd.framework.network.dialing.ConnectivityChangeObserver.Event
    public void onNetworkChange() {
        clear();
    }

    public synchronized String selectBestUniformIp() {
        if (isFinished) {
            return this.cachedResult;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "start local dns detect.");
        }
        long currentTimeMillis = System.currentTimeMillis();
        List<InetAddress> dnsLookup = dnsLookup();
        if (dnsLookup != null && dnsLookup.size() >= 2) {
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (OKLog.D) {
                OKLog.d(TAG, "dns look up cost : " + currentTimeMillis2 + " ms.");
            }
            ArrayList arrayList = new ArrayList();
            if (dnsLookup != null && dnsLookup.size() > 0) {
                for (InetAddress inetAddress : dnsLookup) {
                    IPEntity iPEntity = new IPEntity();
                    iPEntity.key = inetAddress.getHostAddress();
                    if (inetAddress instanceof Inet4Address) {
                        iPEntity.isV6 = false;
                    } else if (inetAddress instanceof Inet6Address) {
                        iPEntity.isV6 = true;
                    }
                    arrayList.add(iPEntity);
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, "entities : " + arrayList);
            }
            long currentTimeMillis3 = System.currentTimeMillis();
            IPEntity select = DialingExecutor.select(arrayList, 2000);
            if (select == null) {
                isFinished = true;
                this.cachedResult = null;
                return null;
            }
            String format = select.isV6 ? String.format("[%s]", select.key) : select.key;
            long currentTimeMillis4 = System.currentTimeMillis() - currentTimeMillis3;
            if (OKLog.D) {
                OKLog.d(TAG, "choose best result cost : " + currentTimeMillis4 + " ms.");
            }
            isFinished = true;
            this.cachedResult = format;
            if (OKLog.D) {
                OKLog.d(TAG, "choose best result : " + this.cachedResult);
            }
            return this.cachedResult;
        }
        isFinished = true;
        this.cachedResult = null;
        return null;
    }
}
