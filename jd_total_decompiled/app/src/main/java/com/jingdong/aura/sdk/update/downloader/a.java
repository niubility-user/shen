package com.jingdong.aura.sdk.update.downloader;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.b.c;
import com.jingdong.aura.sdk.update.b.g;
import com.jingdong.aura.sdk.update.b.j;
import com.jingdong.aura.sdk.update.b.l;
import com.jingdong.aura.sdk.update.downloader.b;
import com.jingdong.aura.sdk.update.updater.IBundleInfoProvider;
import com.jingdong.aura.sdk.update.updater.IUpdateListener;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public final class a {
    Context a;
    PriorityBlockingQueue<AuraBundleResult> b;
    Handler d;

    /* renamed from: e */
    private List<AuraBundleResult> f12248e;

    /* renamed from: h */
    private IDownloader f12251h;

    /* renamed from: j */
    private IBundleInfoProvider f12253j;

    /* renamed from: f */
    private LinkedList<AuraBundleResult> f12249f = new LinkedList<>();

    /* renamed from: g */
    private AtomicInteger f12250g = new AtomicInteger(0);

    /* renamed from: c */
    ConcurrentLinkedQueue<AuraBundleResult> f12247c = new ConcurrentLinkedQueue<>();

    /* renamed from: i */
    private boolean f12252i = false;

    /* renamed from: k */
    private SharedPreferences.OnSharedPreferenceChangeListener f12254k = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.jingdong.aura.sdk.update.downloader.a.2
        {
            a.this = this;
        }

        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            c.a("DownloadManager", "onSharedPreferenceChanged");
            if ("bundle_provided_tip_button".equals(str)) {
                c.a("DownloadManager", "isWifiDownloadProvided changed");
                a.this.a();
            }
        }
    };

    public a(Context context, IDownloader iDownloader) {
        this.a = context;
        this.f12251h = iDownloader;
        IBundleInfoProvider iBundleInfoProvider = com.jingdong.aura.sdk.update.a.a().a.bundleInfoProvider;
        this.f12253j = iBundleInfoProvider;
        l.a(iBundleInfoProvider, "bundleInfoProvider is null");
        l.a(iDownloader, "downloader is null");
        HandlerThread handlerThread = new HandlerThread("AuraUpdate-DownloadManager");
        handlerThread.start();
        this.d = new Handler(handlerThread.getLooper());
    }

    private synchronized void a(final AuraBundleResult auraBundleResult) {
        if (auraBundleResult == null) {
            c.b("DownloadManager", "bundleResult is null");
            return;
        }
        com.jingdong.aura.sdk.update.a.a().a.bundleInfoProvider.getBundleNameFromUpdateID(auraBundleResult.updateId);
        this.f12247c.offer(auraBundleResult);
        auraBundleResult.downloadedSize = com.jingdong.aura.sdk.update.a.a().f12238h.getLong(auraBundleResult.updateId + CartConstant.KEY_YB_INFO_LINK + auraBundleResult.md5, 0L);
        c.a("DownloadManager", "download bundle:" + auraBundleResult.updateId + ", downloaded size:" + auraBundleResult.downloadedSize + " ," + auraBundleResult.downloadUrl);
        this.f12251h.download(auraBundleResult, new IDownloadCallback
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0075: INVOKE 
              (wrap: com.jingdong.aura.sdk.update.downloader.IDownloader : 0x006e: IGET (r5v0 'this' com.jingdong.aura.sdk.update.downloader.a A[IMMUTABLE_TYPE, THIS]) A[Catch: all -> 0x007a, WRAPPED] com.jingdong.aura.sdk.update.downloader.a.h com.jingdong.aura.sdk.update.downloader.IDownloader)
              (r6v0 'auraBundleResult' com.jingdong.aura.sdk.update.AuraBundleResult)
              (wrap: com.jingdong.aura.sdk.update.downloader.IDownloadCallback : 0x0072: CONSTRUCTOR 
              (r5v0 'this' com.jingdong.aura.sdk.update.downloader.a A[IMMUTABLE_TYPE, THIS])
              (r6v0 'auraBundleResult' com.jingdong.aura.sdk.update.AuraBundleResult A[DONT_INLINE])
              (r0 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[Catch: all -> 0x007a, MD:(com.jingdong.aura.sdk.update.downloader.a, com.jingdong.aura.sdk.update.AuraBundleResult, java.lang.String):void (m), WRAPPED] call: com.jingdong.aura.sdk.update.downloader.a.4.<init>(com.jingdong.aura.sdk.update.downloader.a, com.jingdong.aura.sdk.update.AuraBundleResult, java.lang.String):void type: CONSTRUCTOR)
             type: INTERFACE call: com.jingdong.aura.sdk.update.downloader.IDownloader.download(com.jingdong.aura.sdk.update.AuraBundleResult, com.jingdong.aura.sdk.update.downloader.IDownloadCallback):void A[Catch: all -> 0x007a, MD:(com.jingdong.aura.sdk.update.AuraBundleResult, com.jingdong.aura.sdk.update.downloader.IDownloadCallback):void (m), TRY_LEAVE] in method: com.jingdong.aura.sdk.update.downloader.a.a(com.jingdong.aura.sdk.update.AuraBundleResult):void, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
            monitor-enter(r5)
            if (r6 != 0) goto Lc
            java.lang.String r6 = "bundleResult is null"
            java.lang.String r0 = "DownloadManager"
            com.jingdong.aura.sdk.update.b.c.b(r0, r6)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r5)
            return
        Lc:
            com.jingdong.aura.sdk.update.a r0 = com.jingdong.aura.sdk.update.a.a()     // Catch: java.lang.Throwable -> L7a
            com.jingdong.aura.sdk.update.AuraUpdateConfig r0 = r0.a     // Catch: java.lang.Throwable -> L7a
            com.jingdong.aura.sdk.update.updater.IBundleInfoProvider r0 = r0.bundleInfoProvider     // Catch: java.lang.Throwable -> L7a
            java.lang.String r1 = r6.updateId     // Catch: java.lang.Throwable -> L7a
            java.lang.String r0 = r0.getBundleNameFromUpdateID(r1)     // Catch: java.lang.Throwable -> L7a
            java.util.concurrent.ConcurrentLinkedQueue<com.jingdong.aura.sdk.update.AuraBundleResult> r1 = r5.f12247c     // Catch: java.lang.Throwable -> L7a
            r1.offer(r6)     // Catch: java.lang.Throwable -> L7a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7a
            r1.<init>()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = r6.updateId     // Catch: java.lang.Throwable -> L7a
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = "_"
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = r6.md5     // Catch: java.lang.Throwable -> L7a
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7a
            com.jingdong.aura.sdk.update.a r2 = com.jingdong.aura.sdk.update.a.a()     // Catch: java.lang.Throwable -> L7a
            android.content.SharedPreferences r2 = r2.f12238h     // Catch: java.lang.Throwable -> L7a
            r3 = 0
            long r1 = r2.getLong(r1, r3)     // Catch: java.lang.Throwable -> L7a
            r6.downloadedSize = r1     // Catch: java.lang.Throwable -> L7a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = "download bundle:"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = r6.updateId     // Catch: java.lang.Throwable -> L7a
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = ", downloaded size:"
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            long r2 = r6.downloadedSize     // Catch: java.lang.Throwable -> L7a
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = " ,"
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = r6.downloadUrl     // Catch: java.lang.Throwable -> L7a
            r1.append(r2)     // Catch: java.lang.Throwable -> L7a
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = "DownloadManager"
            com.jingdong.aura.sdk.update.b.c.a(r2, r1)     // Catch: java.lang.Throwable -> L7a
            com.jingdong.aura.sdk.update.downloader.IDownloader r1 = r5.f12251h     // Catch: java.lang.Throwable -> L7a
            com.jingdong.aura.sdk.update.downloader.a$4 r2 = new com.jingdong.aura.sdk.update.downloader.a$4     // Catch: java.lang.Throwable -> L7a
            r2.<init>()     // Catch: java.lang.Throwable -> L7a
            r1.download(r6, r2)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r5)
            return
        L7a:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.sdk.update.downloader.a.a(com.jingdong.aura.sdk.update.AuraBundleResult):void");
    }

    static /* synthetic */ void a(AuraBundleResult auraBundleResult, long j2) {
        auraBundleResult.downloadedSize = j2;
        com.jingdong.aura.sdk.update.a.a().f12238h.edit().putLong(auraBundleResult.updateId + CartConstant.KEY_YB_INFO_LINK + auraBundleResult.md5, j2).apply();
    }

    public synchronized void a(String str, IUpdateListener iUpdateListener, boolean z, int i2, int i3) {
        c.a("DownloadManager", "downloadProvidedBundleInternal: updateId:" + str + " listener:" + iUpdateListener + " isForce:" + z + " type:" + i2 + " from");
        if (TextUtils.isEmpty(str)) {
            c.b("DownloadManager", "downloadProvidedBundle, updateId is empty");
            iUpdateListener.onDownloadFailure(new IllegalArgumentException("updateId is empty"));
            return;
        }
        if (iUpdateListener == null) {
            iUpdateListener = new com.jingdong.aura.sdk.update.updater.b();
        }
        AuraBundleResult auraBundleResult = null;
        List<AuraBundleResult> list = this.f12248e;
        if (list != null && list.size() > 0) {
            for (AuraBundleResult auraBundleResult2 : this.f12248e) {
                if (str.equals(auraBundleResult2.updateId)) {
                    c.a("DownloadManager", "downloadProvidedBundleInternal: find " + str + " in originBundleList:" + auraBundleResult2);
                    auraBundleResult2.downLoadFrom = i3;
                    auraBundleResult = auraBundleResult2;
                }
            }
        }
        if (auraBundleResult == null) {
            c.b("DownloadManager", "can not find bundle update info:".concat(String.valueOf(str)));
            iUpdateListener.onDownloadFailure(new IllegalArgumentException("can not find bundle update info:".concat(String.valueOf(str))));
            return;
        }
        AuraBundleResult auraBundleResult3 = new AuraBundleResult(auraBundleResult);
        auraBundleResult3.updateListener = iUpdateListener;
        auraBundleResult3.downloadType = z ? 2 : 1;
        if (g.a(auraBundleResult)) {
            c.a("DownloadManager", "downloadProvidedBundleInternal: completed:".concat(String.valueOf(auraBundleResult)));
            iUpdateListener.onDownloadFinish(auraBundleResult3);
            com.jingdong.aura.sdk.update.a.a().a(auraBundleResult3);
        } else if (i2 == 0) {
            c.a("DownloadManager", String.format("type: %s ,download now,%s", Integer.valueOf(i2), auraBundleResult3));
            if (this.f12247c.size() > 0) {
                this.f12251h.cancelAll(this.f12248e);
            }
            auraBundleResult3.isFromProvidedPage = true;
            a(auraBundleResult3);
        } else {
            if (this.b != null) {
                auraBundleResult3.downloadOrder = this.f12250g.incrementAndGet();
                this.b.add(auraBundleResult3);
            }
            this.f12249f.add(auraBundleResult3);
            c.a("DownloadManager", String.format("type: %s ,add to download queue", Integer.valueOf(i2)));
            b();
        }
    }

    private boolean b(AuraBundleResult auraBundleResult) {
        if (auraBundleResult.downloadType == 2) {
            return true;
        }
        if (auraBundleResult.bundleType == 1) {
            if (j.c(this.a) && (auraBundleResult.downloadType == 1 || com.jingdong.aura.sdk.update.a.a().f12239i.getBoolean("bundle_provided_tip_button", false) || auraBundleResult.bundleType == 2)) {
                return true;
            }
        } else if (j.c(this.a)) {
            return true;
        }
        return false;
    }

    final synchronized void a() {
        if (this.f12247c.size() <= 0) {
            List<AuraBundleResult> list = this.f12248e;
            if (list != null && list.size() > 0) {
                a(this.f12248e);
            }
            return;
        }
        AuraBundleResult peek = this.f12247c.peek();
        if (peek != null && !b(peek)) {
            c.a("DownloadManager", "stop download!");
            this.f12251h.cancelAll(this.f12248e);
        }
    }

    public final synchronized void a(final String str, final IUpdateListener iUpdateListener, final int i2) {
        this.d.post(new Runnable() { // from class: com.jingdong.aura.sdk.update.downloader.a.5
            {
                a.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.a(str, iUpdateListener, true, 0, i2);
            }
        });
    }

    public final synchronized void a(final String str, final IUpdateListener iUpdateListener, final boolean z) {
        this.d.post(new Runnable() { // from class: com.jingdong.aura.sdk.update.downloader.a.6
            final /* synthetic */ int d = 1;

            {
                a.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.a(str, iUpdateListener, z, this.d, 4);
            }
        });
    }

    public final synchronized void a(final List<AuraBundleResult> list) {
        this.d.post(new Runnable() { // from class: com.jingdong.aura.sdk.update.downloader.a.3
            {
                a.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                a.this.b(list);
            }
        });
    }

    final synchronized void b() {
        PriorityBlockingQueue<AuraBundleResult> priorityBlockingQueue = this.b;
        if (priorityBlockingQueue != null && priorityBlockingQueue.size() > 0) {
            if (this.f12247c.size() > 0) {
                c.a("DownloadManager", "runningDownloadQueue greater than threshold: size:" + this.f12247c.size());
                return;
            }
            AuraBundleResult peek = this.b.peek();
            if (peek == null) {
                c.a("DownloadManager", "bundleResult is null, nothing to download");
                return;
            }
            c.a("DownloadManager", "-----download bundle:" + peek.updateId);
            if (g.a(peek)) {
                this.b.remove(peek);
                c.a("DownloadManager", peek.updateId + " is alreadyDownloaded");
                peek.updateListener.onDownloadFinish(peek);
                b();
                return;
            }
            c.a("DownloadManager", "readyDownloadQueue size:" + this.b.size() + ", runningDownloadQueue size:" + this.f12247c.size());
            if (b(peek)) {
                this.b.remove(peek);
                a(peek);
                return;
            }
            c.b("DownloadManager", "should not download bundle:" + peek.updateId);
            return;
        }
        c.a("DownloadManager", "nothing to download");
    }

    final synchronized void b(List<AuraBundleResult> list) {
        int indexOf;
        c.a("DownloadManager", "\u542f\u52a8\u6216\u8005\u63a5\u53e3\u8fd4\u56de\u540e\u542f\u52a8\u4e0b\u8f7d...");
        if (!this.f12252i) {
            this.f12252i = true;
            b bVar = new b();
            bVar.b = new b.a() { // from class: com.jingdong.aura.sdk.update.downloader.a.1
                {
                    a.this = this;
                }

                @Override // com.jingdong.aura.sdk.update.downloader.b.a
                public final void a() {
                    a.this.a();
                }
            };
            Context context = com.jingdong.aura.sdk.update.a.a().a.context;
            c.a(b.a + " -->> registerReceiver()");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(bVar.d, intentFilter);
            com.jingdong.aura.sdk.update.a.a().f12239i.registerOnSharedPreferenceChangeListener(this.f12254k);
        }
        SharedPreferences sharedPreferences = com.jingdong.aura.sdk.update.a.a().f12239i;
        boolean z = sharedPreferences != null ? sharedPreferences.getBoolean("bundle_provided_tip_button", false) : false;
        c.a("DownloadManager", "isWifiDownloadProvided switch:".concat(String.valueOf(z)));
        this.f12248e = list;
        if (list != null && !list.isEmpty()) {
            LinkedList linkedList = new LinkedList();
            for (AuraBundleResult auraBundleResult : this.f12248e) {
                if (g.a(auraBundleResult)) {
                    c.a("DownloadManager", String.format("%s \u5df2\u7ecf\u4e0b\u8f7d\u5b8c\u6bd5", auraBundleResult.updateId));
                } else if (auraBundleResult.downloadType != 0 || z) {
                    IBundleInfoProvider iBundleInfoProvider = this.f12253j;
                    if (iBundleInfoProvider != null && iBundleInfoProvider.getBundleDownloadOrder() != null && (indexOf = this.f12253j.getBundleDownloadOrder().indexOf(auraBundleResult.updateId)) >= 0) {
                        auraBundleResult.downloadOrder = indexOf;
                    }
                    linkedList.offer(auraBundleResult);
                }
            }
            LinkedList linkedList2 = new LinkedList();
            linkedList2.addAll(linkedList);
            linkedList2.addAll(this.f12249f);
            if (com.jingdong.aura.sdk.update.a.a().a.enableLog) {
                Iterator it = linkedList2.iterator();
                while (it.hasNext()) {
                    AuraBundleResult auraBundleResult2 = (AuraBundleResult) it.next();
                    c.a("DownloadManager", "realStartDownload \u6700\u7ec8\u9700\u8981\u4e0b\u8f7d\u7684\u63d2\u4ef6\u5217\u8868 : " + auraBundleResult2.updateId + " ,\u4e0b\u8f7d\u7c7b\u578b : " + auraBundleResult2.getDownloadTypeStr());
                }
            }
            PriorityBlockingQueue<AuraBundleResult> priorityBlockingQueue = new PriorityBlockingQueue<>();
            this.b = priorityBlockingQueue;
            priorityBlockingQueue.addAll(linkedList2);
        }
        this.f12247c.clear();
        this.f12251h.cancelAll(this.f12248e);
        PriorityBlockingQueue<AuraBundleResult> priorityBlockingQueue2 = this.b;
        if (priorityBlockingQueue2 != null && priorityBlockingQueue2.size() > 0) {
            c.a("DownloadManager", "\u6700\u7ec8\u9700\u8981\u4e0b\u8f7d\u7684\u63d2\u4ef6\u4e2a\u6570 : " + this.b.size());
            b();
            return;
        }
        c.a("DownloadManager", "nothing to download");
    }

    public final synchronized void c() {
        this.f12251h.cancelAll(this.f12248e);
    }
}
