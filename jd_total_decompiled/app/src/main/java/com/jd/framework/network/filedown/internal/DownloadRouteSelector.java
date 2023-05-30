package com.jd.framework.network.filedown.internal;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.dialing.ConnectivityChangeObserver;
import com.jd.framework.network.dialing.NetworkExceptionFilter;
import com.jd.framework.network.dialingv2.DialingMethodHelper;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes13.dex */
public class DownloadRouteSelector implements ConnectivityChangeObserver.Event {
    public static final String TAG = "DownloadRouteSelector";
    private final ConcurrentHashMap<String, DialingTask> domainTaskMap = new ConcurrentHashMap<>();
    private String lastDomainListStr = "";

    /* loaded from: classes13.dex */
    public class DialingTask implements Callable<String> {
        private String cachedHealthDomain;
        private String mDomain;
        private List<String> mBackups = new ArrayList();
        private List<String> failList = new ArrayList();

        public DialingTask(String str, List<String> list) {
            DownloadRouteSelector.this = r1;
            this.mDomain = str;
            this.mBackups.addAll(list);
        }

        public synchronized void clear() {
            if (OKLog.D) {
                OKLog.d(DownloadRouteSelector.TAG, "\u7f51\u7edc\u5207\u6362 \u6e05\u9664\u7f13\u5b58 " + this.cachedHealthDomain);
            }
            this.cachedHealthDomain = null;
            this.failList.clear();
        }

        public synchronized void updateFailList(String str, Exception exc) {
            if (OKLog.D) {
                OKLog.d(DownloadRouteSelector.TAG, "\u57df\u540d\u53d1\u751f\u5f02\u5e38 -> " + str + " \u82e5\u5df2\u7f13\u5b58\u5c06\u6e05\u9664");
            }
            if (TextUtils.equals(str, this.cachedHealthDomain)) {
                this.cachedHealthDomain = null;
            }
            if (NetworkExceptionFilter.filter(exc)) {
                if (OKLog.D) {
                    OKLog.d(DownloadRouteSelector.TAG, "\u57df\u540d\u53d1\u751f\u5f02\u5e38 -> " + str + " \u65e0\u6cd5\u4f7f\u7528\uff0c\u52a0\u5165\u5230\u5931\u8d25\u540d\u5355\u4e2d");
                }
                this.failList.add(str);
            }
        }

        @Override // java.util.concurrent.Callable
        public String call() throws Exception {
            synchronized (this) {
                if (!TextUtils.isEmpty(this.cachedHealthDomain)) {
                    if (OKLog.D) {
                        OKLog.d(DownloadRouteSelector.TAG, "startDialingTask \u9009\u53d6\u7f13\u5b58\u7ed3\u679c -> " + this.cachedHealthDomain);
                    }
                    return this.cachedHealthDomain;
                } else if (!this.failList.contains(this.mDomain) && DialingMethodHelper.isHealthDomain(this.mDomain)) {
                    this.cachedHealthDomain = this.mDomain;
                    if (OKLog.D) {
                        OKLog.d(DownloadRouteSelector.TAG, "startDialingTask \u63a2\u6d4b\u5230\u4e3b\u57df\u540d\u53ef\u7528 -> " + this.cachedHealthDomain);
                    }
                    return this.cachedHealthDomain;
                } else {
                    List<String> domainDialing = DialingMethodHelper.domainDialing(this.mBackups);
                    if (!domainDialing.isEmpty()) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= domainDialing.size()) {
                                break;
                            } else if (!this.failList.contains(domainDialing.get(i2))) {
                                this.cachedHealthDomain = domainDialing.get(i2);
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (OKLog.D) {
                            OKLog.d(DownloadRouteSelector.TAG, "startDialingTask \u9009\u53d6\u5907\u9009\u57df\u540d -> " + this.cachedHealthDomain);
                        }
                        return this.cachedHealthDomain;
                    }
                    if (OKLog.D) {
                        OKLog.d(DownloadRouteSelector.TAG, "startDialingTask \u5747\u65e0\u53ef\u7528\u4f7f\u7528\u9ed8\u8ba4\u57df\u540d -> " + this.mDomain);
                    }
                    return this.mDomain;
                }
            }
        }
    }

    public String getHealthDomain(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (shouldDialing(str)) {
                String str2 = (String) GlobalExecutorService.lightExecutorService().submit(this.domainTaskMap.get(str)).get();
                try {
                    if (OKLog.D) {
                        OKLog.d(TAG, "getHealthDomain \u9009\u53d6\u5065\u5eb7\u7684\u57df\u540d -> " + str2 + " cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                    }
                } catch (Throwable unused) {
                }
                return str2;
            }
            return str;
        } catch (Throwable unused2) {
            return str;
        }
    }

    @Override // com.jd.framework.network.dialing.ConnectivityChangeObserver.Event
    public void onNetworkChange() {
        try {
            Iterator<Map.Entry<String, DialingTask>> it = this.domainTaskMap.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().clear();
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void setupRoute() {
        if (RuntimeConfigHelper.enableDownloadAdvancedMode()) {
            try {
                String config = JDHttpTookit.getEngine().getDownloadDomainsResolver().getConfig();
                if (!TextUtils.isEmpty(config) && !TextUtils.equals(this.lastDomainListStr, config)) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "setupRoute \u83b7\u53d6\u7ebf\u4e0a\u914d\u7f6e\u57df\u540d\u5217\u8868 -> " + config + " \u5f00\u59cb\u521b\u5efa\u4efb\u52a1\u5217\u8868");
                    }
                    this.lastDomainListStr = config;
                    this.domainTaskMap.clear();
                    JDJSONObject parseObject = JDJSON.parseObject(config);
                    if (parseObject != null) {
                        for (Map.Entry<String, Object> entry : parseObject.entrySet()) {
                            if (!TextUtils.isEmpty(entry.getKey())) {
                                ArrayList arrayList = new ArrayList();
                                if (entry.getValue() instanceof List) {
                                    arrayList.addAll((List) entry.getValue());
                                }
                                if (!arrayList.isEmpty()) {
                                    this.domainTaskMap.put(entry.getKey(), new DialingTask(entry.getKey(), arrayList));
                                }
                            }
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public boolean shouldDialing(String str) {
        return RuntimeConfigHelper.enableDownloadAdvancedMode() && RuntimeConfigHelper.isSupportDownloadAdvancedMode(str) && this.domainTaskMap.containsKey(str);
    }

    public void updateDomainFailList(String str, String str2, Exception exc) {
        if (this.domainTaskMap.containsKey(str)) {
            this.domainTaskMap.get(str).updateFailList(str2, exc);
        }
    }
}
