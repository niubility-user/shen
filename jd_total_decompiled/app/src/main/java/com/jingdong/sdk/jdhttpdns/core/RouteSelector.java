package com.jingdong.sdk.jdhttpdns.core;

import android.text.TextUtils;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import com.jingdong.sdk.jdhttpdns.utils.ThreadUtil;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes7.dex */
public final class RouteSelector {
    public static final String TAG = "httpdns";
    public String cachedTargetVip;
    private String ipv4 = HttpDnsConfig.DNS_VIP;
    private String ipv6;
    private boolean isCdnIpv6Enable;

    private String getTargetVipSync() {
        if (!TextUtils.isEmpty(this.ipv6) && !TextUtils.isEmpty(this.ipv4)) {
            Future submit = ThreadUtil.getExecutorService().submit(new Callable<Long>() { // from class: com.jingdong.sdk.jdhttpdns.core.RouteSelector.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Long call() throws Exception {
                    return Long.valueOf(DialingMethod.connect(String.format("[%s]", RouteSelector.this.ipv6), 80));
                }
            });
            Future submit2 = ThreadUtil.getExecutorService().submit(new Callable<Long>() { // from class: com.jingdong.sdk.jdhttpdns.core.RouteSelector.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Long call() throws Exception {
                    return Long.valueOf(DialingMethod.connect(RouteSelector.this.ipv4, 80));
                }
            });
            try {
                long longValue = ((Long) submit.get()).longValue();
                long longValue2 = ((Long) submit2.get()).longValue();
                if (DNSLog.D) {
                    DNSLog.d("httpdns", "vip connect test result ipv4 : " + longValue2 + " , ipv6 : " + longValue);
                }
                if (longValue > 0 && longValue2 > 0 && longValue - longValue2 < 250) {
                    return this.ipv6;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this.ipv4;
    }

    public String getIpv4() {
        return this.ipv4;
    }

    public String getIpv6() {
        return this.ipv6;
    }

    public synchronized String getTargetCdnVip() {
        if (this.isCdnIpv6Enable) {
            return getTargetVip();
        }
        return this.ipv4;
    }

    public synchronized String getTargetVip() {
        if (DNSLog.D) {
            DNSLog.d("httpdns", "get proper ip from ipv6 or ipv4");
        }
        if (this.cachedTargetVip == null) {
            this.cachedTargetVip = getTargetVipSync();
        }
        return this.cachedTargetVip;
    }

    public boolean isCdnIpv6Enable() {
        return this.isCdnIpv6Enable;
    }

    public synchronized void notifyDataHasChanged() {
        this.cachedTargetVip = null;
    }

    public void setCdnIpv6Enable(boolean z) {
        this.isCdnIpv6Enable = z;
    }

    public void setIpv4(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, this.ipv4)) {
            return;
        }
        this.ipv4 = str;
        notifyDataHasChanged();
    }

    public void setIpv6(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, this.ipv6)) {
            return;
        }
        this.ipv6 = str;
        notifyDataHasChanged();
    }
}
