package com.jd.framework.network.dialingv2;

import com.jd.framework.network.dialingv2.BaseDialingTask;
import com.jd.framework.network.dialingv2.DialingModel;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import com.jingdong.sdk.oklog.OKLog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import okhttp3.Dns;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes13.dex */
public class LocalDnsDialingTask extends BaseDialingTask {
    static Dns SYSTEM = new Dns() { // from class: com.jd.framework.network.dialingv2.LocalDnsDialingTask.1
        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str != null) {
                return Arrays.asList(InetAddress.getAllByName(str));
            }
            throw new UnknownHostException("hostname == null");
        }
    };

    static String[] convert(List<InetAddress> list) {
        if (list == null || list.isEmpty()) {
            return new String[0];
        }
        String[] strArr = new String[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            strArr[i2] = list.get(i2).getHostAddress();
        }
        return strArr;
    }

    public static LocalDnsDialingTask createTask() {
        return new LocalDnsDialingTask();
    }

    static List<InetAddress> dnsLookup(final String str) {
        try {
            return (List) GlobalExecutorService.lightExecutorService().submit(new Callable<List<InetAddress>>() { // from class: com.jd.framework.network.dialingv2.LocalDnsDialingTask.2
                @Override // java.util.concurrent.Callable
                public List<InetAddress> call() {
                    try {
                        return LocalDnsDialingTask.SYSTEM.lookup(str);
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return null;
                    }
                }
            }).get(250L, TimeUnit.MILLISECONDS);
        } catch (Exception e2) {
            OKLog.e("DialingTask", e2);
            return null;
        }
    }

    static String[] fetchLocalDnsIP(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String[] convert = convert(dnsLookup(str));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (OKLog.D) {
            OKLog.d("DialingTask", "LocalDnsIPDialingTask LocalDns\u67e5\u8be2\u8017\u65f6 : " + currentTimeMillis2 + " ms.");
        }
        return convert;
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    public void clear() {
        synchronized (LocalDnsDialingTask.class) {
            try {
                super.clear();
                this.data.clear();
            }
        }
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    protected List<DialingModel> filter() {
        ArrayList arrayList = new ArrayList();
        for (DialingModel dialingModel : this.data) {
            if (!DialingManager.getInstance().getDialingIpSet().contains(dialingModel.ipAddress)) {
                DialingManager.getInstance().getDialingIpSet().add(dialingModel.ipAddress);
                arrayList.add(dialingModel);
            }
        }
        return arrayList;
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    public DialingModel getCachedModel() {
        DialingModel dialingModel;
        synchronized (LocalDnsDialingTask.class) {
            if (this.available != null && DialingManager.getInstance().getFailingSet().contains(this.available.ipAddress)) {
                this.available = null;
            }
            dialingModel = this.available;
        }
        return dialingModel;
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    protected DialingModel selectModelWithStrategy(List<DialingModel> list) {
        return DialingMethodHelper.selectWithHappyEyeball(list, 150);
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    public void startDialing() {
        synchronized (LocalDnsDialingTask.class) {
            if (OKLog.D) {
                OKLog.d("DialingTask", "LocalDnsIPDialingTask LocalDns\u4efb\u52a1\u63a2\u6d4b\u5f00\u59cb");
            }
            if (this.status != BaseDialingTask.RunningStatus.INITIAL) {
                if (OKLog.D) {
                    OKLog.d("DialingTask", "LocalDns\u62e8\u6d4b\u5df2\u7ecf\u5f00\u59cb\uff0c\u65e0\u9700\u91cd\u590d\u63a2\u6d4b");
                }
                return;
            }
            setStatus(BaseDialingTask.RunningStatus.START);
            String[] fetchLocalDnsIP = fetchLocalDnsIP("api.m.jd.com");
            if (OKLog.D) {
                OKLog.d("DialingTask", "LocalDnsIPDialingTask \u57df\u540d api.m.jd.com");
                String str = "[]";
                if (fetchLocalDnsIP != null && fetchLocalDnsIP.length > 0) {
                    str = Arrays.toString(fetchLocalDnsIP);
                }
                OKLog.d("DialingTask", "DNS\u89e3\u6790\u7ed3\u679c\u4e3a : " + str);
            }
            if (fetchLocalDnsIP != null && fetchLocalDnsIP.length > 0) {
                for (String str2 : fetchLocalDnsIP) {
                    DialingModel newInstance = DialingModel.newInstance();
                    newInstance.from = DialingModel.Source.SOURCE_FROM_LOCAL_DNS;
                    newInstance.ipAddress = str2;
                    newInstance.isIPv6 = InetAddressUtils.isIPv6Address(str2);
                    this.data.add(newInstance);
                }
            }
            if (OKLog.D) {
                OKLog.d("DialingTask", "LocalDnsIPDialingTask \u5373\u5c06\u8fdb\u884c\u63a2\u6d4b\u7684IP\u5217\u8868 : " + this.data);
            }
            long currentTimeMillis = System.currentTimeMillis();
            List<DialingModel> dialing = DialingMethodHelper.dialing(filter(), 2000);
            if (dialing != null) {
                DialingManager.getInstance().add2AvailableList(dialing);
            }
            this.available = selectModelWithStrategy(dialing);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (OKLog.D) {
                OKLog.d("DialingTask", "LocalDnsIPDialingTask \u63a2\u6d4b\u5230\u6700\u4f73IP\u5730\u5740 : " + this.available + ", \u8017\u65f6 : " + currentTimeMillis2 + "\u6beb\u79d2");
            }
            setStatus(BaseDialingTask.RunningStatus.COMPLETED);
        }
    }
}
