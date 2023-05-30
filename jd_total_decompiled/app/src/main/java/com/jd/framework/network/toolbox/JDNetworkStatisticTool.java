package com.jd.framework.network.toolbox;

import com.jingdong.jdsdk.network.JDHttpTookit;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class JDNetworkStatisticTool {
    public static final String KEY_DOMAIN2IP_DOWNGRADE_REQUEST_COUNT = "domain2IpDowngradRequestCount";
    public static final String KEY_DOWNGRADE2BUILDIN_REQUEST_COUNT = "downgrade2BuildInIpRequestCount";
    public static final String KEY_DOWNGRADE2HTTPDNS_BACKUP_REQUEST_COUNT = "downgrade2HttpDnsBackupIpRequestCount";
    public static final String KEY_DOWNGRADE2HTTPDNS_REQUEST_COUNT = "downgrade2HttpDnsIpRequestCount";
    public static final String KEY_DOWNLOAD_BACKUP_DOMAIN_COUNT = "downloadBackupDomainCount";
    public static final String KEY_DOWNLOAD_SUCCEED_REQUEST_COUNT = "downloadSucceedRequestCount";
    public static final String KEY_DOWNLOAD_TOTAL_REQUEST_COUNT = "downloadTotalRequestCount";
    public static final String KEY_FINAL_DOWNGRADE_REQUEST_COUNT = "finalDowngradRequestCount";
    public static final String KEY_SUCCEED_REQUEST_COUNT = "succeedRequestCount";
    public static final String KEY_TOTAL_HTTPS_REQUEST_COUNT = "totalHttpsRequestCount";
    public static final String KEY_TOTAL_REQUEST_COUNT = "totalRequestCount";
    public static final String TAG = "JDNetworkStatisticTool";
    private static JDNetworkStatisticTool instance;
    public boolean isSendLastResults = false;
    private AtomicInteger totalRequestCount = new AtomicInteger(0);
    private AtomicInteger succeedRequestCount = new AtomicInteger(0);
    private AtomicInteger finalDowngradRequestCount = new AtomicInteger(0);
    private AtomicInteger domain2IpDowngradRequestCount = new AtomicInteger(0);
    private AtomicInteger downgrade2BuildInIpRequestCount = new AtomicInteger(0);
    private AtomicInteger downgrade2HttpDnsIpRequestCount = new AtomicInteger(0);
    private AtomicInteger downgrade2HttpDnsBackupIpRequestCount = new AtomicInteger(0);
    private AtomicInteger httpsDowngrade2HttpRequestCount = new AtomicInteger(0);
    private AtomicInteger totalHttpsRequestCount = new AtomicInteger(0);
    private AtomicInteger downloadRequestCount = new AtomicInteger(0);
    private AtomicInteger downloadSuccessRequestCount = new AtomicInteger(0);
    private AtomicInteger downloadBackupDomainRequestCount = new AtomicInteger(0);

    /* loaded from: classes.dex */
    public static class TlsStatEntry {
        public long timeCost;
        public String tlsVersion;
        public String url;

        public String toString() {
            return "TlsEntry: [ url : " + this.url + ", tlsVersion : " + this.tlsVersion + ", timeCost   : " + this.timeCost;
        }
    }

    private JDNetworkStatisticTool() {
    }

    public static JDNetworkStatisticTool getInstance() {
        if (instance == null) {
            synchronized (JDNetworkStatisticTool.class) {
                if (instance == null) {
                    instance = new JDNetworkStatisticTool();
                }
            }
        }
        return instance;
    }

    public int getDomain2IpDowngradRequestCount() {
        return this.domain2IpDowngradRequestCount.get();
    }

    public int getDowngrade2BackupIpRequestCount() {
        return this.downgrade2HttpDnsBackupIpRequestCount.get();
    }

    public int getDowngrade2BuildInIpRequestCount() {
        return this.downgrade2BuildInIpRequestCount.get();
    }

    public int getDowngrade2HttpDnsIpRequestCount() {
        return this.downgrade2HttpDnsIpRequestCount.get();
    }

    public int getDownloadBakDomainReqCnt() {
        return this.downloadBackupDomainRequestCount.get();
    }

    public int getDownloadSuccessReqCnt() {
        return this.downloadSuccessRequestCount.get();
    }

    public int getDownloadTotalReqCnt() {
        return this.downloadRequestCount.get();
    }

    public int getFinalDowngradRequestCount() {
        return this.finalDowngradRequestCount.get();
    }

    public int getHttpsDowngrade2HttpRequestCount() {
        return this.httpsDowngrade2HttpRequestCount.get();
    }

    public int getSucceedRequestCount() {
        return this.succeedRequestCount.get();
    }

    public int getTotalHttpsRequestCount() {
        return this.totalHttpsRequestCount.get();
    }

    public int getTotalRequestCount() {
        return this.totalRequestCount.get();
    }

    public int incrDomain2IpDowngradRequestCount() {
        return this.domain2IpDowngradRequestCount.incrementAndGet();
    }

    public int incrDowngrade2BackupIpRequestCount() {
        return this.downgrade2HttpDnsBackupIpRequestCount.incrementAndGet();
    }

    public int incrDowngrade2BuildInIpRequestCount() {
        return this.downgrade2BuildInIpRequestCount.incrementAndGet();
    }

    public int incrDowngrade2HttpDnsIpRequestCount() {
        return this.downgrade2HttpDnsIpRequestCount.incrementAndGet();
    }

    public int incrDownloadBakDomainReqCnt() {
        return this.downloadBackupDomainRequestCount.incrementAndGet();
    }

    public int incrDownloadSuccessReqCnt() {
        return this.downloadSuccessRequestCount.incrementAndGet();
    }

    public int incrDownloadTotalReqCnt() {
        return this.downloadRequestCount.incrementAndGet();
    }

    public int incrFinalDowngradeRequestCount() {
        return this.finalDowngradRequestCount.incrementAndGet();
    }

    public int incrHttpsDowngrade2HttpRequestCount() {
        return this.httpsDowngrade2HttpRequestCount.incrementAndGet();
    }

    public int incrSucceedRequestCount() {
        return this.succeedRequestCount.incrementAndGet();
    }

    public synchronized int incrTotalHttpsRequestCount() {
        return this.totalHttpsRequestCount.incrementAndGet();
    }

    public int incrTotalRequestCount() {
        return this.totalRequestCount.incrementAndGet();
    }

    public void reportTlsStatEntry(TlsStatEntry tlsStatEntry) {
        JDHttpTookit.getEngine().getStatInfoConfigImpl().reportTlsHandshakeStatData(tlsStatEntry);
    }

    public void saveDownloadStatResult() {
        if (getInstance().isSendLastResults) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put(KEY_DOWNLOAD_TOTAL_REQUEST_COUNT, Integer.valueOf(getDownloadTotalReqCnt()));
            hashMap.put(KEY_DOWNLOAD_SUCCEED_REQUEST_COUNT, Integer.valueOf(getDownloadSuccessReqCnt()));
            hashMap.put(KEY_DOWNLOAD_BACKUP_DOMAIN_COUNT, Integer.valueOf(getDownloadBakDomainReqCnt()));
            JDHttpTookit.getEngine().getStatInfoConfigImpl().saveNetworkStatistic(hashMap);
        }
    }

    public void saveNetworkStatisticResult() {
        if (getInstance().isSendLastResults) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put(KEY_TOTAL_REQUEST_COUNT, Integer.valueOf(getTotalRequestCount()));
            hashMap.put(KEY_SUCCEED_REQUEST_COUNT, Integer.valueOf(getSucceedRequestCount()));
            hashMap.put(KEY_DOMAIN2IP_DOWNGRADE_REQUEST_COUNT, Integer.valueOf(getDomain2IpDowngradRequestCount()));
            hashMap.put(KEY_FINAL_DOWNGRADE_REQUEST_COUNT, Integer.valueOf(getFinalDowngradRequestCount()));
            hashMap.put(KEY_DOWNGRADE2BUILDIN_REQUEST_COUNT, Integer.valueOf(getDowngrade2BuildInIpRequestCount()));
            hashMap.put(KEY_DOWNGRADE2HTTPDNS_REQUEST_COUNT, Integer.valueOf(getDowngrade2HttpDnsIpRequestCount()));
            hashMap.put(KEY_DOWNGRADE2HTTPDNS_BACKUP_REQUEST_COUNT, Integer.valueOf(getDowngrade2BackupIpRequestCount()));
            hashMap.put(KEY_TOTAL_HTTPS_REQUEST_COUNT, Integer.valueOf(getTotalHttpsRequestCount()));
            JDHttpTookit.getEngine().getStatInfoConfigImpl().saveNetworkStatistic(hashMap);
        }
    }
}
