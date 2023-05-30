package com.jingdong.common.network;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.JdImageToolKit;
import com.jingdong.common.httpdns.DialingExecutor;
import com.jingdong.common.httpdns.GlobalExecutorService;
import com.jingdong.common.httpdns.NetworkExceptionFilter;
import com.jingdong.common.network.ConnectivityChangeObserver;
import com.jingdong.sdk.oklog.OKLog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import okhttp3.Dns;

/* loaded from: classes5.dex */
public class CDNRouteSelector implements ConnectivityChangeObserver.Event {
    public static final int ROUTE_TYPE_BK_DOMAIN = 2;
    public static final int ROUTE_TYPE_BK_HTTPDNS = 3;
    public static final int ROUTE_TYPE_LOCAL = 1;
    static Dns SYSTEM = new Dns() { // from class: com.jingdong.common.network.CDNRouteSelector.1
        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) {
            if (str != null) {
                return Arrays.asList(InetAddress.getAllByName(str));
            }
            throw new UnknownHostException("hostname == null");
        }
    };
    public static final String TAG = "CDNRouteSelectorV2";
    private final ConcurrentHashMap<String, DomainDialingTask> domainTaskMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, HttpDnsBackupDialingTask> httpDnsTaskMap = new ConcurrentHashMap<>();
    private Set<String> mFailingSet = new HashSet();
    private ConcurrentHashMap<String, Long> mAvailableIPSet = new ConcurrentHashMap<>();
    private List<String> mBackUpDomains = new ArrayList();

    /* loaded from: classes5.dex */
    public abstract class BaseDialingTask implements Callable<String> {
        public static final int RUNNING_STATUS_COMPLETED = 2;
        public static final int RUNNING_STATUS_INITIAL = 0;
        public static final int RUNNING_STATUS_RUNNING = 1;
        protected String mCachedAddress;
        protected final String mDomain;
        protected int mStatus = 0;

        public BaseDialingTask(String str) {
            this.mDomain = str;
        }

        @Override // java.util.concurrent.Callable
        public String call() {
            return dialing();
        }

        public synchronized void clear() {
            if (OKLog.D) {
                OKLog.d(CDNRouteSelector.TAG, "\u7f51\u7edc\u5207\u6362 \u6e05\u9664\u7f13\u5b58 " + this.mCachedAddress);
            }
            this.mCachedAddress = null;
            this.mStatus = 0;
        }

        protected abstract String dialing();

        public synchronized String getAddress() {
            int i2 = this.mStatus;
            if (i2 == 0) {
                this.mStatus = 1;
                GlobalExecutorService.newCachedThreadPool().submit(this);
            } else if (i2 != 1 && !TextUtils.isEmpty(this.mCachedAddress) && CDNRouteSelector.this.getFailingSet().contains(this.mCachedAddress)) {
                if (OKLog.D) {
                    OKLog.d(CDNRouteSelector.TAG, "DialingTask \u7f13\u5b58ip\u5730\u5740\u547d\u4e2d\u9ed1\u540d\u5355: " + this.mDomain + " -> " + this.mCachedAddress);
                }
                this.mCachedAddress = null;
            }
            return this.mCachedAddress;
        }

        public boolean isFinish() {
            return this.mStatus == 2;
        }
    }

    /* loaded from: classes5.dex */
    public class DomainDialingTask extends BaseDialingTask {
        public DomainDialingTask(String str) {
            super(str);
        }

        /* JADX WARN: Removed duplicated region for block: B:65:0x01b3 A[Catch: all -> 0x0374, TryCatch #0 {all -> 0x0374, blocks: (B:3:0x0004, B:6:0x000a, B:7:0x0025, B:9:0x003a, B:12:0x0061, B:15:0x0069, B:16:0x0077, B:18:0x007d, B:20:0x0087, B:24:0x0096, B:21:0x008d, B:23:0x0091, B:25:0x009a, B:28:0x00a3, B:30:0x00ac, B:32:0x00b2, B:34:0x00bb, B:36:0x00bf, B:39:0x00cb, B:40:0x00f0, B:42:0x00f6, B:43:0x011b, B:46:0x012b, B:49:0x0139, B:51:0x013d, B:52:0x015a, B:71:0x01f0, B:73:0x01f6, B:76:0x0204, B:78:0x0208, B:79:0x0225, B:104:0x02c9, B:105:0x02d4, B:111:0x02ee, B:113:0x02f5, B:115:0x0301, B:110:0x02e2, B:80:0x0237, B:82:0x023b, B:83:0x0258, B:85:0x0267, B:90:0x0278, B:92:0x027c, B:95:0x02a2, B:88:0x0273, B:53:0x016e, B:55:0x0172, B:56:0x018f, B:58:0x019e, B:63:0x01af, B:65:0x01b3, B:69:0x01de, B:61:0x01aa, B:118:0x0352, B:120:0x0356), top: B:127:0x0004, inners: #1, #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x01d6  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01de A[Catch: all -> 0x0374, TryCatch #0 {all -> 0x0374, blocks: (B:3:0x0004, B:6:0x000a, B:7:0x0025, B:9:0x003a, B:12:0x0061, B:15:0x0069, B:16:0x0077, B:18:0x007d, B:20:0x0087, B:24:0x0096, B:21:0x008d, B:23:0x0091, B:25:0x009a, B:28:0x00a3, B:30:0x00ac, B:32:0x00b2, B:34:0x00bb, B:36:0x00bf, B:39:0x00cb, B:40:0x00f0, B:42:0x00f6, B:43:0x011b, B:46:0x012b, B:49:0x0139, B:51:0x013d, B:52:0x015a, B:71:0x01f0, B:73:0x01f6, B:76:0x0204, B:78:0x0208, B:79:0x0225, B:104:0x02c9, B:105:0x02d4, B:111:0x02ee, B:113:0x02f5, B:115:0x0301, B:110:0x02e2, B:80:0x0237, B:82:0x023b, B:83:0x0258, B:85:0x0267, B:90:0x0278, B:92:0x027c, B:95:0x02a2, B:88:0x0273, B:53:0x016e, B:55:0x0172, B:56:0x018f, B:58:0x019e, B:63:0x01af, B:65:0x01b3, B:69:0x01de, B:61:0x01aa, B:118:0x0352, B:120:0x0356), top: B:127:0x0004, inners: #1, #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x027c A[Catch: all -> 0x0374, TryCatch #0 {all -> 0x0374, blocks: (B:3:0x0004, B:6:0x000a, B:7:0x0025, B:9:0x003a, B:12:0x0061, B:15:0x0069, B:16:0x0077, B:18:0x007d, B:20:0x0087, B:24:0x0096, B:21:0x008d, B:23:0x0091, B:25:0x009a, B:28:0x00a3, B:30:0x00ac, B:32:0x00b2, B:34:0x00bb, B:36:0x00bf, B:39:0x00cb, B:40:0x00f0, B:42:0x00f6, B:43:0x011b, B:46:0x012b, B:49:0x0139, B:51:0x013d, B:52:0x015a, B:71:0x01f0, B:73:0x01f6, B:76:0x0204, B:78:0x0208, B:79:0x0225, B:104:0x02c9, B:105:0x02d4, B:111:0x02ee, B:113:0x02f5, B:115:0x0301, B:110:0x02e2, B:80:0x0237, B:82:0x023b, B:83:0x0258, B:85:0x0267, B:90:0x0278, B:92:0x027c, B:95:0x02a2, B:88:0x0273, B:53:0x016e, B:55:0x0172, B:56:0x018f, B:58:0x019e, B:63:0x01af, B:65:0x01b3, B:69:0x01de, B:61:0x01aa, B:118:0x0352, B:120:0x0356), top: B:127:0x0004, inners: #1, #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:95:0x02a2 A[Catch: all -> 0x0374, TRY_LEAVE, TryCatch #0 {all -> 0x0374, blocks: (B:3:0x0004, B:6:0x000a, B:7:0x0025, B:9:0x003a, B:12:0x0061, B:15:0x0069, B:16:0x0077, B:18:0x007d, B:20:0x0087, B:24:0x0096, B:21:0x008d, B:23:0x0091, B:25:0x009a, B:28:0x00a3, B:30:0x00ac, B:32:0x00b2, B:34:0x00bb, B:36:0x00bf, B:39:0x00cb, B:40:0x00f0, B:42:0x00f6, B:43:0x011b, B:46:0x012b, B:49:0x0139, B:51:0x013d, B:52:0x015a, B:71:0x01f0, B:73:0x01f6, B:76:0x0204, B:78:0x0208, B:79:0x0225, B:104:0x02c9, B:105:0x02d4, B:111:0x02ee, B:113:0x02f5, B:115:0x0301, B:110:0x02e2, B:80:0x0237, B:82:0x023b, B:83:0x0258, B:85:0x0267, B:90:0x0278, B:92:0x027c, B:95:0x02a2, B:88:0x0273, B:53:0x016e, B:55:0x0172, B:56:0x018f, B:58:0x019e, B:63:0x01af, B:65:0x01b3, B:69:0x01de, B:61:0x01aa, B:118:0x0352, B:120:0x0356), top: B:127:0x0004, inners: #1, #2 }] */
        @Override // com.jingdong.common.network.CDNRouteSelector.BaseDialingTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected java.lang.String dialing() {
            /*
                Method dump skipped, instructions count: 891
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.network.CDNRouteSelector.DomainDialingTask.dialing():java.lang.String");
        }
    }

    /* loaded from: classes5.dex */
    public static class HealthVIP {
        public String domain;
        public int type;
        public String vip;

        public boolean isBackDomainHealthVip() {
            return this.type == 2;
        }

        public boolean isBackHttpDnsHealthVip() {
            return this.type == 3;
        }
    }

    /* loaded from: classes5.dex */
    public class HttpDnsBackupDialingTask extends BaseDialingTask {
        private final List<String> mBackupIpList;

        public HttpDnsBackupDialingTask(String str, List<String> list) {
            super(str);
            this.mBackupIpList = list;
        }

        @Override // com.jingdong.common.network.CDNRouteSelector.BaseDialingTask
        protected String dialing() {
            long currentTimeMillis;
            int size;
            Future[] futureArr;
            int i2;
            ArrayList arrayList;
            try {
                if (OKLog.D) {
                    OKLog.d(CDNRouteSelector.TAG, "DialingTask \u5f00\u59cbVIP\u63a2\u6d4b: " + this.mDomain + " list: " + this.mBackupIpList);
                }
                currentTimeMillis = System.currentTimeMillis();
                size = this.mBackupIpList.size();
                futureArr = new Future[size];
                i2 = 0;
                for (int i3 = 0; i3 < this.mBackupIpList.size(); i3++) {
                    this.mBackupIpList.get(i3);
                    futureArr[i3] = GlobalExecutorService.newCachedThreadPool().submit(new Callable<IPEntry>
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0052: APUT 
                          (r5v0 'futureArr' java.util.concurrent.Future[])
                          (r7v1 'i3' int)
                          (wrap: java.util.concurrent.Future : 0x004e: INVOKE 
                          (wrap: java.util.concurrent.ExecutorService : 0x0045: INVOKE  type: STATIC call: com.jingdong.common.httpdns.GlobalExecutorService.newCachedThreadPool():java.util.concurrent.ExecutorService A[Catch: all -> 0x00e4, MD:():java.util.concurrent.ExecutorService (m), WRAPPED])
                          (wrap: java.util.concurrent.Callable<com.jingdong.common.network.CDNRouteSelector$IPEntry> : 0x004b: CONSTRUCTOR 
                          (r14v0 'this' com.jingdong.common.network.CDNRouteSelector$HttpDnsBackupDialingTask A[IMMUTABLE_TYPE, THIS])
                          (r8 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[Catch: all -> 0x00e4, MD:(com.jingdong.common.network.CDNRouteSelector$HttpDnsBackupDialingTask, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.network.CDNRouteSelector.HttpDnsBackupDialingTask.1.<init>(com.jingdong.common.network.CDNRouteSelector$HttpDnsBackupDialingTask, java.lang.String):void type: CONSTRUCTOR)
                         type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.util.concurrent.Callable):java.util.concurrent.Future A[Catch: all -> 0x00e4, MD:<T>:(java.util.concurrent.Callable<T>):java.util.concurrent.Future<T> (c), WRAPPED])
                         A[Catch: all -> 0x00e4] in method: com.jingdong.common.network.CDNRouteSelector.HttpDnsBackupDialingTask.dialing():java.lang.String, file: classes5.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:195)
                        	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        Caused by: java.lang.NullPointerException
                        */
                    /*
                        this = this;
                        r0 = 2
                        boolean r1 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r2 = "CDNRouteSelectorV2"
                        if (r1 == 0) goto L27
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Le4
                        r1.<init>()     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r3 = "DialingTask \u5f00\u59cbVIP\u63a2\u6d4b: "
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r3 = r14.mDomain     // Catch: java.lang.Throwable -> Le4
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r3 = " list: "
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        java.util.List<java.lang.String> r3 = r14.mBackupIpList     // Catch: java.lang.Throwable -> Le4
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Le4
                        com.jingdong.sdk.oklog.OKLog.d(r2, r1)     // Catch: java.lang.Throwable -> Le4
                    L27:
                        long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Le4
                        java.util.List<java.lang.String> r1 = r14.mBackupIpList     // Catch: java.lang.Throwable -> Le4
                        int r1 = r1.size()     // Catch: java.lang.Throwable -> Le4
                        java.util.concurrent.Future[] r5 = new java.util.concurrent.Future[r1]     // Catch: java.lang.Throwable -> Le4
                        r6 = 0
                        r7 = 0
                    L35:
                        java.util.List<java.lang.String> r8 = r14.mBackupIpList     // Catch: java.lang.Throwable -> Le4
                        int r8 = r8.size()     // Catch: java.lang.Throwable -> Le4
                        if (r7 >= r8) goto L57
                        java.util.List<java.lang.String> r8 = r14.mBackupIpList     // Catch: java.lang.Throwable -> Le4
                        java.lang.Object r8 = r8.get(r7)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> Le4
                        java.util.concurrent.ExecutorService r9 = com.jingdong.common.httpdns.GlobalExecutorService.newCachedThreadPool()     // Catch: java.lang.Throwable -> Le4
                        com.jingdong.common.network.CDNRouteSelector$HttpDnsBackupDialingTask$1 r10 = new com.jingdong.common.network.CDNRouteSelector$HttpDnsBackupDialingTask$1     // Catch: java.lang.Throwable -> Le4
                        r10.<init>()     // Catch: java.lang.Throwable -> Le4
                        java.util.concurrent.Future r8 = r9.submit(r10)     // Catch: java.lang.Throwable -> Le4
                        r5[r7] = r8     // Catch: java.lang.Throwable -> Le4
                        int r7 = r7 + 1
                        goto L35
                    L57:
                        java.util.ArrayList r7 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Le4
                        r7.<init>()     // Catch: java.lang.Throwable -> Le4
                    L5c:
                        r8 = 0
                        if (r6 >= r1) goto L90
                        r9 = r5[r6]     // Catch: java.lang.Throwable -> Le4
                        java.lang.Object r9 = r9.get()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> Le4
                        com.jingdong.common.network.CDNRouteSelector$IPEntry r9 = (com.jingdong.common.network.CDNRouteSelector.IPEntry) r9     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> Le4
                        r8 = r9
                        goto L6d
                    L69:
                        r9 = move-exception
                        r9.printStackTrace()     // Catch: java.lang.Throwable -> Le4
                    L6d:
                        if (r8 == 0) goto L8d
                        long r9 = r8.time     // Catch: java.lang.Throwable -> Le4
                        r11 = 0
                        int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                        if (r13 <= 0) goto L8d
                        java.lang.String r9 = r8.ip     // Catch: java.lang.Throwable -> Le4
                        r7.add(r9)     // Catch: java.lang.Throwable -> Le4
                        com.jingdong.common.network.CDNRouteSelector r9 = com.jingdong.common.network.CDNRouteSelector.this     // Catch: java.lang.Throwable -> Le4
                        java.util.concurrent.ConcurrentHashMap r9 = com.jingdong.common.network.CDNRouteSelector.access$000(r9)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r10 = r8.ip     // Catch: java.lang.Throwable -> Le4
                        long r11 = r8.time     // Catch: java.lang.Throwable -> Le4
                        java.lang.Long r8 = java.lang.Long.valueOf(r11)     // Catch: java.lang.Throwable -> Le4
                        r9.put(r10, r8)     // Catch: java.lang.Throwable -> Le4
                    L8d:
                        int r6 = r6 + 1
                        goto L5c
                    L90:
                        int r1 = r7.size()     // Catch: java.lang.Throwable -> Le4
                        if (r1 <= 0) goto Laa
                        java.util.Random r1 = new java.util.Random     // Catch: java.lang.Throwable -> Le4
                        r1.<init>()     // Catch: java.lang.Throwable -> Le4
                        int r5 = r7.size()     // Catch: java.lang.Throwable -> Le4
                        int r1 = r1.nextInt(r5)     // Catch: java.lang.Throwable -> Le4
                        java.lang.Object r1 = r7.get(r1)     // Catch: java.lang.Throwable -> Le4
                        r8 = r1
                        java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> Le4
                    Laa:
                        boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> Le4
                        if (r1 != 0) goto Lb2
                        r14.mCachedAddress = r8     // Catch: java.lang.Throwable -> Le4
                    Lb2:
                        long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Le4
                        long r5 = r5 - r3
                        boolean r1 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> Le4
                        if (r1 == 0) goto Le1
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Le4
                        r1.<init>()     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r3 = "DialingTask HttpDns\u5907\u9009\u63a2\u6d4b\u7ed3\u679c : "
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r3 = r14.mDomain     // Catch: java.lang.Throwable -> Le4
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r3 = " \u6700\u7ec8\u7ed3\u679c : "
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        r1.append(r8)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r3 = "\uff0c\u8017\u65f6: "
                        r1.append(r3)     // Catch: java.lang.Throwable -> Le4
                        r1.append(r5)     // Catch: java.lang.Throwable -> Le4
                        java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Le4
                        com.jingdong.sdk.oklog.OKLog.d(r2, r1)     // Catch: java.lang.Throwable -> Le4
                    Le1:
                        r14.mStatus = r0
                        return r8
                    Le4:
                        r1 = move-exception
                        r14.mStatus = r0
                        goto Le9
                    Le8:
                        throw r1
                    Le9:
                        goto Le8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.network.CDNRouteSelector.HttpDnsBackupDialingTask.dialing():java.lang.String");
                }
            }

            /* loaded from: classes5.dex */
            public static class IPEntry {
                public String ip;
                public long time;
            }

            private List<String> getBackDomainList() {
                List<String> list;
                synchronized (this.mBackUpDomains) {
                    if (this.mBackUpDomains.isEmpty()) {
                        String config = JdImageToolKit.getEngine().getCDNDomainsResolver().getConfig();
                        if (OKLog.D) {
                            OKLog.d(TAG, "\u83b7\u53d6\u79fb\u52a8\u914d\u7f6e: " + config);
                        }
                        if (!TextUtils.isEmpty(config)) {
                            try {
                                JDJSONObject parseObject = JDJSON.parseObject(config);
                                if (parseObject != null) {
                                    JDJSONArray jSONArray = parseObject.getJSONArray("imageBackupDomain");
                                    for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                                        this.mBackUpDomains.add(jSONArray.get(i2).toString());
                                    }
                                    if (OKLog.D) {
                                        OKLog.d(TAG, "\u83b7\u53d6\u5907\u9009\u57df\u540d\u5217\u8868: " + this.mBackUpDomains);
                                    }
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    }
                    list = this.mBackUpDomains;
                }
                return list;
            }

            private String getHealthIp(String str) {
                try {
                    if (!this.domainTaskMap.containsKey(str)) {
                        this.domainTaskMap.put(str, new DomainDialingTask(str));
                    }
                    return this.domainTaskMap.get(str).getAddress();
                } catch (Throwable unused) {
                    return null;
                }
            }

            private String getHealthIpFromHttpDns(String str) {
                try {
                    com.jingdong.common.httpdns.IpModel ipModelByHost = getIpModelByHost(str, true);
                    if (ipModelByHost != null && ipModelByHost.getV4Backup() != null && ipModelByHost.getV4Backup().length > 0 && !this.httpDnsTaskMap.containsKey(str)) {
                        this.httpDnsTaskMap.put(str, new HttpDnsBackupDialingTask(str, Arrays.asList(ipModelByHost.getV4Backup())));
                    }
                    return this.httpDnsTaskMap.get(str).getAddress();
                } catch (Throwable unused) {
                    return null;
                }
            }

            public static boolean isCndAdvancedMode(String str) {
                return MobileConfigHelper.isCDNAdvancedModeEnable() && MobileConfigHelper.isSupportCDNAdvancedMode(str);
            }

            private boolean isLocalDnsDialingFinish(String str) {
                if (this.domainTaskMap.containsKey(str)) {
                    return this.domainTaskMap.get(str).isFinish();
                }
                return false;
            }

            public void addFailedIP(String str, Exception exc) {
                if (TextUtils.isEmpty(str) || exc == null) {
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "\u68c0\u6d4b\u5230ip\u5730\u5740 " + str + " \u53d1\u751f\u5f02\u5e38 " + exc);
                }
                if (!this.mFailingSet.contains(str) && NetworkExceptionFilter.filter(exc)) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "\u5c5e\u4e8e\u7279\u5b9a\u7684\u5f02\u5e38\u7c7b\u578b\uff0c\u5c06\u6545\u969cip " + str + " \u52a0\u5165\u5230\u9ed1\u540d\u5355\u4e2d");
                    }
                    this.mFailingSet.add(str);
                    this.mAvailableIPSet.remove(str);
                }
                if (this.mFailingSet.contains(str) || DialingExecutor.isHealthIP(str)) {
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "\u4e0d\u5c5e\u4e8e\u7279\u5b9a\u7684\u5f02\u5e38\u7c7b\u578b\uff0c\u5bf9 " + str + " \u8fdb\u884c\u63a2\u6d4b\u540e\u4e0d\u53ef\u7528\uff0c\u5c06\u5176\u52a0\u5165\u5230\u9ed1\u540d\u5355\u4e2d");
                }
                this.mFailingSet.add(str);
                this.mAvailableIPSet.remove(str);
            }

            List<InetAddress> dnsLookup(final String str) {
                try {
                    return (List) GlobalExecutorService.newCachedThreadPool().submit(new Callable<List<InetAddress>>() { // from class: com.jingdong.common.network.CDNRouteSelector.2
                        @Override // java.util.concurrent.Callable
                        public List<InetAddress> call() {
                            try {
                                return CDNRouteSelector.SYSTEM.lookup(str);
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

            public Set<String> getFailingSet() {
                return this.mFailingSet;
            }

            public HealthVIP getHealthAddress(String str) {
                long currentTimeMillis = System.currentTimeMillis();
                HealthVIP healthVIP = new HealthVIP();
                try {
                    String healthIp = getHealthIp(str);
                    if (!isLocalDnsDialingFinish(str)) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "getHealthAddress LocalDns\u89e3\u6790\u4efb\u52a1\u8fd8\u672a\u5b8c\u6210\uff0c\u4f7f\u7528\u539f\u59cb\u57df\u540d\u8fdb\u884c\u8bf7\u6c42");
                        }
                        return null;
                    } else if (!TextUtils.isEmpty(healthIp)) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "getHealthAddress \u9009\u53d6LocalDNS IP\u5730\u5740: " + str + " -> ip: " + healthIp);
                        }
                        healthVIP.vip = healthIp;
                        healthVIP.domain = str;
                        healthVIP.type = 1;
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        if (OKLog.D) {
                            OKLog.d(TAG, "getHealthAddress \u57df\u540d: " + str + "\u6700\u7ec8 VIP -> " + healthVIP.vip + ",\u8017\u65f6: " + currentTimeMillis2 + "\u6beb\u79d2");
                        }
                        return healthVIP;
                    } else {
                        List<String> backDomainList = getBackDomainList();
                        for (int i2 = 0; i2 < backDomainList.size(); i2++) {
                            String healthIp2 = getHealthIp(backDomainList.get(i2));
                            if (!TextUtils.isEmpty(healthIp2)) {
                                if (OKLog.D) {
                                    OKLog.d(TAG, "getHealthAddress \u57df\u540d: " + str + ",\u9009\u53d6\u5907\u9009\u57df\u540dIP\u5730\u5740: " + backDomainList.get(i2) + " -> ip: " + healthIp2);
                                }
                                healthVIP.vip = healthIp2;
                                healthVIP.domain = backDomainList.get(i2);
                                healthVIP.type = 2;
                                long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
                                if (OKLog.D) {
                                    OKLog.d(TAG, "getHealthAddress \u57df\u540d: " + str + "\u6700\u7ec8 VIP -> " + healthVIP.vip + ",\u8017\u65f6: " + currentTimeMillis3 + "\u6beb\u79d2");
                                }
                                return healthVIP;
                            }
                        }
                        String healthIpFromHttpDns = getHealthIpFromHttpDns(str);
                        if (TextUtils.isEmpty(healthIpFromHttpDns)) {
                            long currentTimeMillis4 = System.currentTimeMillis() - currentTimeMillis;
                            if (OKLog.D) {
                                OKLog.d(TAG, "getHealthAddress \u57df\u540d: " + str + "\u6700\u7ec8 VIP -> " + healthVIP.vip + ",\u8017\u65f6: " + currentTimeMillis4 + "\u6beb\u79d2");
                            }
                            return null;
                        }
                        if (OKLog.D) {
                            OKLog.d(TAG, "getHealthAddress \u57df\u540d: " + str + "\uff0c\u9009\u53d6HttpDns\u5907\u9009VIP -> " + healthIpFromHttpDns);
                        }
                        healthVIP.vip = healthIpFromHttpDns;
                        healthVIP.domain = str;
                        healthVIP.type = 3;
                        long currentTimeMillis5 = System.currentTimeMillis() - currentTimeMillis;
                        if (OKLog.D) {
                            OKLog.d(TAG, "getHealthAddress \u57df\u540d: " + str + "\u6700\u7ec8 VIP -> " + healthVIP.vip + ",\u8017\u65f6: " + currentTimeMillis5 + "\u6beb\u79d2");
                        }
                        return healthVIP;
                    }
                } finally {
                    long currentTimeMillis6 = System.currentTimeMillis() - currentTimeMillis;
                    if (OKLog.D) {
                        OKLog.d(TAG, "getHealthAddress \u57df\u540d: " + str + "\u6700\u7ec8 VIP -> " + healthVIP.vip + ",\u8017\u65f6: " + currentTimeMillis6 + "\u6beb\u79d2");
                    }
                }
            }

            com.jingdong.common.httpdns.IpModel getIpModelByHost(String str, boolean z) {
                return JdImageToolKit.getEngine().getHttpDnsDependency().getIpModelByHost(str, z);
            }

            @Override // com.jingdong.common.network.ConnectivityChangeObserver.Event
            public void onNetworkChange() {
                try {
                    Iterator<Map.Entry<String, DomainDialingTask>> it = this.domainTaskMap.entrySet().iterator();
                    while (it.hasNext()) {
                        it.next().getValue().clear();
                    }
                } catch (Throwable unused) {
                }
                try {
                    Iterator<Map.Entry<String, HttpDnsBackupDialingTask>> it2 = this.httpDnsTaskMap.entrySet().iterator();
                    while (it2.hasNext()) {
                        it2.next().getValue().clear();
                    }
                } catch (Throwable unused2) {
                }
                this.mAvailableIPSet.clear();
                this.mFailingSet.clear();
            }
        }
