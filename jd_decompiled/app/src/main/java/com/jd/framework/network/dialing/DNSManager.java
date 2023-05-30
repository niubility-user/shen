package com.jd.framework.network.dialing;

import android.text.TextUtils;
import com.android.volley.VolleyLog;
import com.jd.framework.network.dialing.ConnectivityChangeObserver;
import com.jingdong.common.network.IpModel;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes13.dex */
public class DNSManager implements ConnectivityChangeObserver.Event {
    public static final int MAX_FAIL_THRESHOLD = 3;
    public static final String TAG = "DNSManager";
    private static DNSManager instance;
    private IBuildInIPBackUpConfig config = null;
    private IPEntity dailingHealthIP = null;
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> ipFailListMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> domainFailListMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, ArrayList<IPEntity>> buildInIPContainer = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, HashMap<String, ArrayList<IPEntity>>> httpDNSBackupIPContainer = new ConcurrentHashMap<>();

    /* loaded from: classes13.dex */
    public interface IBuildInIPBackUpConfig {
        boolean isFeatureEnable();
    }

    private DNSManager() {
        HashMap<String, String[]> buildInIPMap = IPConfiguration.buildInIPMap();
        for (String str : buildInIPMap.keySet()) {
            if (buildInIPMap.get(str) != null && buildInIPMap.get(str).length > 0) {
                ArrayList<IPEntity> arrayList = new ArrayList<>();
                for (String str2 : buildInIPMap.get(str)) {
                    IPEntity iPEntity = new IPEntity();
                    iPEntity.type = 0;
                    iPEntity.key = str2;
                    arrayList.add(iPEntity);
                }
                this.buildInIPContainer.put(str, arrayList);
            }
        }
    }

    public static synchronized DNSManager getInstance() {
        DNSManager dNSManager;
        synchronized (DNSManager.class) {
            if (instance == null) {
                instance = new DNSManager();
            }
            dNSManager = instance;
        }
        return dNSManager;
    }

    public void add2DomainFailList(String str, Exception exc) {
        synchronized (this.domainFailListMap) {
            if (!TextUtils.isEmpty(str) && exc != null && NetworkExceptionFilter.filter(exc)) {
                if (!this.domainFailListMap.containsKey(str)) {
                    if (VolleyLog.DEBUG) {
                        String str2 = "add " + str + " to fail list ";
                    }
                    this.domainFailListMap.put(str, 1);
                } else {
                    int intValue = this.domainFailListMap.get(str).intValue();
                    this.domainFailListMap.replace(str, Integer.valueOf(intValue), Integer.valueOf(intValue + 1));
                    if (VolleyLog.DEBUG) {
                        String str3 = "increase " + str + " occure count : " + this.domainFailListMap.get(str);
                    }
                }
            }
        }
    }

    public void add2IPFailList(String str, String str2, Exception exc) {
        synchronized (this.ipFailListMap) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && exc != null && NetworkExceptionFilter.filter(exc)) {
                if (!this.ipFailListMap.containsKey(str)) {
                    ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
                    concurrentHashMap.put(str2, 1);
                    this.ipFailListMap.put(str, concurrentHashMap);
                    if (VolleyLog.DEBUG) {
                        String str3 = "add host : " + str + ", with ip : " + str2 + " to fail list.";
                    }
                } else {
                    ConcurrentHashMap<String, Integer> concurrentHashMap2 = this.ipFailListMap.get(str);
                    if (concurrentHashMap2.containsKey(str2)) {
                        int intValue = concurrentHashMap2.get(str2).intValue();
                        concurrentHashMap2.replace(str2, Integer.valueOf(intValue), Integer.valueOf(intValue + 1));
                        if (VolleyLog.DEBUG) {
                            String str4 = "increase host : " + str + ", with ip : " + str2 + " occure count : " + concurrentHashMap2.get(str2);
                        }
                    } else {
                        concurrentHashMap2.put(str2, 1);
                    }
                }
            }
        }
    }

    public void clearFailList() {
        synchronized (this.ipFailListMap) {
            this.ipFailListMap.clear();
        }
        synchronized (this.domainFailListMap) {
            this.domainFailListMap.clear();
        }
    }

    void fetchHttpdnsBackupIP(String str) {
        IpModel ipModelByHost = JDHttpTookit.getEngine().getHttpDnsControllerImpl().getIpModelByHost(str, true);
        if (ipModelByHost == null) {
            return;
        }
        int i2 = 2;
        if (this.httpDNSBackupIPContainer.containsKey(str)) {
            HashMap<String, ArrayList<IPEntity>> hashMap = this.httpDNSBackupIPContainer.get(str);
            ArrayList<IPEntity> arrayList = hashMap.get("v6");
            ArrayList<IPEntity> arrayList2 = hashMap.get("v4");
            if (ipModelByHost.getV6Backup() != null && ipModelByHost.getV6Backup().length > 0) {
                HashSet hashSet = new HashSet();
                Iterator<IPEntity> it = arrayList.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().key);
                }
                for (String str2 : ipModelByHost.getV6Backup()) {
                    if (!hashSet.contains(str2)) {
                        IPEntity iPEntity = new IPEntity();
                        iPEntity.type = 2;
                        iPEntity.isV6 = true;
                        iPEntity.key = String.format("[%s]", str2);
                        arrayList.add(iPEntity);
                    }
                }
            }
            if (ipModelByHost.getV4Backup() == null || ipModelByHost.getV4Backup().length <= 0) {
                return;
            }
            HashSet hashSet2 = new HashSet();
            Iterator<IPEntity> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                hashSet2.add(it2.next().key);
            }
            for (String str3 : ipModelByHost.getV4Backup()) {
                if (!hashSet2.contains(str3)) {
                    IPEntity iPEntity2 = new IPEntity();
                    iPEntity2.type = 2;
                    iPEntity2.isV6 = false;
                    iPEntity2.key = str3;
                    arrayList2.add(iPEntity2);
                }
            }
            return;
        }
        HashMap<String, ArrayList<IPEntity>> hashMap2 = new HashMap<>();
        ArrayList<IPEntity> arrayList3 = new ArrayList<>();
        ArrayList<IPEntity> arrayList4 = new ArrayList<>();
        if (ipModelByHost.getV4Backup() != null && ipModelByHost.getV4Backup().length > 0) {
            for (String str4 : ipModelByHost.getV4Backup()) {
                IPEntity iPEntity3 = new IPEntity();
                iPEntity3.type = 2;
                iPEntity3.key = str4;
                iPEntity3.isV6 = false;
                arrayList3.add(iPEntity3);
            }
        }
        if (ipModelByHost.getV6Backup() != null && ipModelByHost.getV6Backup().length > 0) {
            String[] v6Backup = ipModelByHost.getV6Backup();
            int length = v6Backup.length;
            int i3 = 0;
            while (i3 < length) {
                String str5 = v6Backup[i3];
                IPEntity iPEntity4 = new IPEntity();
                iPEntity4.type = i2;
                iPEntity4.key = String.format("[%s]", str5);
                iPEntity4.isV6 = true;
                arrayList4.add(iPEntity4);
                i3++;
                i2 = 2;
            }
        }
        hashMap2.put("v4", arrayList3);
        hashMap2.put("v6", arrayList4);
        this.httpDNSBackupIPContainer.put(str, hashMap2);
    }

    public synchronized IPEntity getDailingHealthIp(String str, String str2) {
        IBuildInIPBackUpConfig iBuildInIPBackUpConfig = this.config;
        IPEntity iPEntity = null;
        if (iBuildInIPBackUpConfig == null || iBuildInIPBackUpConfig.isFeatureEnable()) {
            if (!TextUtils.isEmpty(str2)) {
                if (!hasFailedBefore(str, str2)) {
                    return null;
                }
            } else if (!hasFailedBefore(str)) {
                return null;
            }
            if (this.dailingHealthIP != null) {
                OKLog.d(TAG, "get dialing ip via last cached health ip");
                return this.dailingHealthIP;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "get dialing ip via httpdns master ip.");
            }
            IpModel ipModelByHost = JDHttpTookit.getEngine().getHttpDnsControllerImpl().getIpModelByHost(str, true);
            if (ipModelByHost != null && !TextUtils.isEmpty(ipModelByHost.getMaster()) && !TextUtils.equals(ipModelByHost.getMaster(), str2)) {
                IPEntity iPEntity2 = new IPEntity();
                iPEntity2.type = 1;
                if (InetAddressUtils.isIPv6Address(ipModelByHost.getMaster())) {
                    iPEntity2.key = String.format("[%s]", ipModelByHost.getMaster());
                    iPEntity2.isV6 = true;
                } else {
                    iPEntity2.key = ipModelByHost.getMaster();
                }
                iPEntity = DialingExecutor.select(iPEntity2, 2000);
            }
            if (iPEntity != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "get dialing ip via httpdns ip." + iPEntity);
                }
                this.dailingHealthIP = iPEntity;
                return iPEntity;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "get dialing ip via httpdns backup ip.");
            }
            fetchHttpdnsBackupIP(str);
            if (this.httpDNSBackupIPContainer.containsKey(str)) {
                HashMap<String, ArrayList<IPEntity>> hashMap = this.httpDNSBackupIPContainer.get(str);
                ArrayList<IPEntity> arrayList = hashMap.get("v6");
                if (arrayList.size() > 0) {
                    iPEntity = DialingExecutor.randomSelect(arrayList, 2000);
                }
                if (iPEntity != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "get dialing ip via ipv6 backup ip." + iPEntity);
                    }
                    this.dailingHealthIP = iPEntity;
                    return iPEntity;
                }
                ArrayList<IPEntity> arrayList2 = hashMap.get("v4");
                if (arrayList2.size() > 0) {
                    iPEntity = DialingExecutor.randomSelect(arrayList2, 2000);
                }
                if (iPEntity != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "get dialing ip via ipv4 backup ip. " + iPEntity);
                    }
                    this.dailingHealthIP = iPEntity;
                    return iPEntity;
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, "get dialing ip via buildin ip.");
            }
            IPEntity randomSelect = DialingExecutor.randomSelect(this.buildInIPContainer.get(str), 2000);
            this.dailingHealthIP = randomSelect;
            return randomSelect;
        }
        return null;
    }

    public boolean hasFailedBefore(String str) {
        boolean z;
        synchronized (this.domainFailListMap) {
            z = this.domainFailListMap.containsKey(str) && this.domainFailListMap.get(str).intValue() >= 3;
        }
        return z;
    }

    @Override // com.jd.framework.network.dialing.ConnectivityChangeObserver.Event
    public void onNetworkChange() {
        if (VolleyLog.DEBUG) {
            VolleyLog.d(TAG, "network change");
        }
        clearFailList();
        resetDailingResult();
    }

    public void removeFromFailList(String str) {
        synchronized (this.domainFailListMap) {
            if (this.domainFailListMap.containsKey(str)) {
                if (VolleyLog.DEBUG) {
                    String str2 = "remove " + str + "from fail list";
                }
                this.domainFailListMap.remove(str);
            }
        }
    }

    public synchronized void resetDailingResult() {
        this.dailingHealthIP = null;
        ConcurrentHashMap<String, ArrayList<IPEntity>> concurrentHashMap = this.buildInIPContainer;
        if (concurrentHashMap != null) {
            Iterator<String> it = concurrentHashMap.keySet().iterator();
            while (it.hasNext()) {
                Iterator<IPEntity> it2 = this.buildInIPContainer.get(it.next()).iterator();
                while (it2.hasNext()) {
                    it2.next().time = 0L;
                }
            }
        }
        this.httpDNSBackupIPContainer.clear();
    }

    public void setBuildInIPBackUpConfig(IBuildInIPBackUpConfig iBuildInIPBackUpConfig) {
        this.config = iBuildInIPBackUpConfig;
    }

    public boolean hasFailedBefore(String str, String str2) {
        boolean z;
        synchronized (this.ipFailListMap) {
            z = this.ipFailListMap.containsKey(str) && this.ipFailListMap.get(str) != null && this.ipFailListMap.get(str).containsKey(str2) && this.ipFailListMap.get(str).get(str2).intValue() >= 3;
        }
        return z;
    }

    public void removeFromFailList(String str, String str2) {
        synchronized (this.ipFailListMap) {
            if (this.ipFailListMap.containsKey(str)) {
                if (this.ipFailListMap.get(str) != null && this.ipFailListMap.get(str).containsKey(str2)) {
                    this.ipFailListMap.get(str).remove(str2);
                }
                if (VolleyLog.DEBUG) {
                    String str3 = "remove host : " + str + " with ip " + str2 + "from fail list.";
                }
            }
        }
    }
}
