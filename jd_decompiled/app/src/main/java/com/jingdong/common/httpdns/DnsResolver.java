package com.jingdong.common.httpdns;

import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.jingdong.JdImageToolKit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class DnsResolver {
    public static final int MAX_FAIL_THRESHOLD = 2;
    public static final String TAG = "ImageDnsResolver";
    private static DnsResolver instance;
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> ipFailListMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, HashMap<String, Object>> backupIpMap = new ConcurrentHashMap<>();

    private DnsResolver() {
    }

    public static synchronized DnsResolver getInstance() {
        DnsResolver dnsResolver;
        synchronized (DnsResolver.class) {
            if (instance == null) {
                instance = new DnsResolver();
            }
            dnsResolver = instance;
        }
        return dnsResolver;
    }

    public void add2IPFailList(String str, String str2, Exception exc) {
        String str3;
        String str4;
        synchronized (this.ipFailListMap) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && exc != null && NetworkExceptionFilter.filter(exc)) {
                if (this.ipFailListMap.containsKey(str)) {
                    ConcurrentHashMap<String, Integer> concurrentHashMap = this.ipFailListMap.get(str);
                    if (!concurrentHashMap.containsKey(str2)) {
                        concurrentHashMap.put(str2, 1);
                    }
                    int intValue = concurrentHashMap.get(str2).intValue();
                    concurrentHashMap.replace(str2, Integer.valueOf(intValue), Integer.valueOf(intValue + 1));
                    str3 = "increase host : " + str + ", with ip : " + str2 + " occure count : " + concurrentHashMap.get(str2);
                    str4 = TAG;
                } else {
                    ConcurrentHashMap<String, Integer> concurrentHashMap2 = new ConcurrentHashMap<>();
                    concurrentHashMap2.put(str2, 1);
                    this.ipFailListMap.put(str, concurrentHashMap2);
                    str4 = TAG;
                    str3 = "add host : " + str + ", with ip : " + str2 + " to fail list.";
                }
                FLog.d(str4, str3);
            }
        }
    }

    void fetchBackupIP(String str) {
        IpModel ipModelByHost = getIpModelByHost(str, true);
        if (ipModelByHost == null) {
            return;
        }
        int i2 = 2;
        if (!this.backupIpMap.containsKey(str)) {
            HashMap<String, Object> hashMap = new HashMap<>();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (ipModelByHost.getV4Backup() != null && ipModelByHost.getV4Backup().length > 0) {
                for (String str2 : ipModelByHost.getV4Backup()) {
                    IPEntity iPEntity = new IPEntity();
                    iPEntity.type = 2;
                    iPEntity.key = str2;
                    iPEntity.isV6 = false;
                    arrayList.add(iPEntity);
                }
            }
            if (ipModelByHost.getV6Backup() != null && ipModelByHost.getV6Backup().length > 0) {
                String[] v6Backup = ipModelByHost.getV6Backup();
                int length = v6Backup.length;
                int i3 = 0;
                while (i3 < length) {
                    String str3 = v6Backup[i3];
                    IPEntity iPEntity2 = new IPEntity();
                    iPEntity2.type = i2;
                    iPEntity2.key = String.format("[%s]", str3);
                    iPEntity2.isV6 = true;
                    arrayList2.add(iPEntity2);
                    i3++;
                    i2 = 2;
                }
            }
            hashMap.put("v4", arrayList);
            hashMap.put("v6", arrayList2);
            this.backupIpMap.put(str, hashMap);
            return;
        }
        HashMap<String, Object> hashMap2 = this.backupIpMap.get(str);
        ArrayList arrayList3 = (ArrayList) hashMap2.get("v6");
        ArrayList arrayList4 = (ArrayList) hashMap2.get("v4");
        if (ipModelByHost.getV6Backup() != null && ipModelByHost.getV6Backup().length > 0) {
            HashSet hashSet = new HashSet();
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                hashSet.add(((IPEntity) it.next()).key);
            }
            for (String str4 : ipModelByHost.getV6Backup()) {
                if (!hashSet.contains(str4)) {
                    IPEntity iPEntity3 = new IPEntity();
                    iPEntity3.type = 2;
                    iPEntity3.isV6 = true;
                    iPEntity3.key = String.format("[%s]", str4);
                    arrayList3.add(iPEntity3);
                }
            }
        }
        if (ipModelByHost.getV4Backup() == null || ipModelByHost.getV4Backup().length <= 0) {
            return;
        }
        HashSet hashSet2 = new HashSet();
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            hashSet2.add(((IPEntity) it2.next()).key);
        }
        for (String str5 : ipModelByHost.getV4Backup()) {
            if (!hashSet2.contains(str5)) {
                IPEntity iPEntity4 = new IPEntity();
                iPEntity4.type = 2;
                iPEntity4.isV6 = false;
                iPEntity4.key = str5;
                arrayList4.add(iPEntity4);
            }
        }
    }

    public IPEntity getHealthIp(String str) {
        StringBuilder sb;
        String str2;
        IpModel ipModelByHost = getIpModelByHost(str, false);
        if (ipModelByHost == null) {
            return null;
        }
        String master = ipModelByHost.getMaster();
        if (!TextUtils.isEmpty(master) && !hasFailedBefore(str, master)) {
            IPEntity iPEntity = new IPEntity();
            iPEntity.key = master;
            iPEntity.type = 1;
            return iPEntity;
        }
        fetchBackupIP(str);
        if (this.backupIpMap.containsKey(str)) {
            HashMap<String, Object> hashMap = this.backupIpMap.get(str);
            if (hashMap.containsKey("cache")) {
                return (IPEntity) hashMap.get("cache");
            }
            ArrayList arrayList = (ArrayList) hashMap.get("v6");
            IPEntity randomSelect = arrayList.size() > 0 ? DialingExecutor.randomSelect(arrayList, 2000) : null;
            if (randomSelect != null) {
                sb = new StringBuilder();
                str2 = "get dialing ip via ipv6 backup ip.";
            } else {
                ArrayList arrayList2 = (ArrayList) hashMap.get("v4");
                if (arrayList2.size() > 0) {
                    randomSelect = DialingExecutor.randomSelect(arrayList2, 2000);
                }
                if (randomSelect != null) {
                    sb = new StringBuilder();
                    str2 = "get dialing ip via ipv4 backup ip. ";
                }
            }
            sb.append(str2);
            sb.append(randomSelect);
            FLog.d(TAG, sb.toString());
            hashMap.put("cache", randomSelect);
            return randomSelect;
        }
        return null;
    }

    IpModel getIpModelByHost(String str, boolean z) {
        return JdImageToolKit.getEngine().getHttpDnsDependency().getIpModelByHost(str, z);
    }

    public boolean hasFailedBefore(String str, String str2) {
        boolean z;
        synchronized (this.ipFailListMap) {
            z = this.ipFailListMap.containsKey(str) && this.ipFailListMap.get(str) != null && this.ipFailListMap.get(str).containsKey(str2) && this.ipFailListMap.get(str).get(str2).intValue() >= 2;
        }
        return z;
    }

    public void removeFromFailList(String str, String str2) {
        synchronized (this.ipFailListMap) {
            if (this.ipFailListMap.containsKey(str)) {
                if (this.ipFailListMap.get(str) != null && this.ipFailListMap.get(str).containsKey(str2)) {
                    this.ipFailListMap.get(str).remove(str2);
                }
                FLog.d(TAG, "remove host : " + str + " with ip " + str2 + "from fail list.");
            }
        }
    }

    public void reset() {
        this.ipFailListMap.clear();
        this.backupIpMap.clear();
    }
}
