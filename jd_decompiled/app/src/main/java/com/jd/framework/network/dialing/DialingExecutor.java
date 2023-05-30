package com.jd.framework.network.dialing;

import com.android.volley.VolleyLog;
import com.android.volley.utils.TimeUtils;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes13.dex */
public class DialingExecutor {
    public static final int DEFAULT_DAILING_TIMEOUT = 2000;
    public static final int LOCAL_DNS_DAILING_TIMEOUT = 250;
    public static final String TAG = "DialingExecutor";

    /* JADX WARN: Removed duplicated region for block: B:33:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long connect(java.lang.String r6, int r7, int r8) {
        /*
            if (r7 != 0) goto L4
            r7 = 443(0x1bb, float:6.21E-43)
        L4:
            r0 = 0
            r1 = 0
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L40
            java.net.Socket r5 = new java.net.Socket     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L3e
            r5.<init>()     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L3e
            java.net.InetSocketAddress r0 = new java.net.InetSocketAddress     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L39
            r0.<init>(r6, r7)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L39
            r5.connect(r0, r8)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L39
            r7 = 255(0xff, float:3.57E-43)
            r5.sendUrgentData(r7)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L39
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L39
            boolean r7 = r5.isClosed()     // Catch: java.io.IOException -> L31
            if (r7 != 0) goto L2a
            r5.shutdownInput()     // Catch: java.io.IOException -> L31
        L2a:
            r5.shutdownOutput()     // Catch: java.io.IOException -> L31
            r5.close()     // Catch: java.io.IOException -> L31
            goto L56
        L31:
            r7 = move-exception
            r7.printStackTrace()
            goto L56
        L36:
            r6 = move-exception
            r0 = r5
            goto L74
        L39:
            r7 = move-exception
            r0 = r5
            goto L42
        L3c:
            r7 = move-exception
            goto L42
        L3e:
            r6 = move-exception
            goto L74
        L40:
            r7 = move-exception
            r3 = r1
        L42:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            if (r0 == 0) goto L50
            boolean r7 = r0.isClosed()     // Catch: java.io.IOException -> L31
            if (r7 != 0) goto L50
            r0.shutdownInput()     // Catch: java.io.IOException -> L31
        L50:
            r0.shutdownOutput()     // Catch: java.io.IOException -> L31
            r0.close()     // Catch: java.io.IOException -> L31
        L56:
            long r1 = r1 - r3
            boolean r7 = com.android.volley.VolleyLog.DEBUG
            if (r7 == 0) goto L73
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "ip : "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r6 = ", rtt time : "
            r7.append(r6)
            r7.append(r1)
            r7.toString()
        L73:
            return r1
        L74:
            if (r0 == 0) goto L7f
            boolean r7 = r0.isClosed()     // Catch: java.io.IOException -> L86
            if (r7 != 0) goto L7f
            r0.shutdownInput()     // Catch: java.io.IOException -> L86
        L7f:
            r0.shutdownOutput()     // Catch: java.io.IOException -> L86
            r0.close()     // Catch: java.io.IOException -> L86
            goto L8a
        L86:
            r7 = move-exception
            r7.printStackTrace()
        L8a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.framework.network.dialing.DialingExecutor.connect(java.lang.String, int, int):long");
    }

    public static IPEntity customIpTest(IPEntity iPEntity, int i2) {
        iPEntity.time = connect(iPEntity.key, 443, i2);
        iPEntity.updateTime = TimeUtils.getCurrentTime();
        return iPEntity;
    }

    public static IPEntity randomSelect(ArrayList<IPEntity> arrayList, final int i2) {
        IPEntity iPEntity;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        boolean z = VolleyLog.DEBUG;
        int size = arrayList.size();
        Future[] futureArr = new Future[size];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList.get(i3);
            futureArr[i3] = GlobalExecutorService.lightExecutorService().submit(new Callable<IPEntity>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002d: APUT 
                  (r2v0 'futureArr' java.util.concurrent.Future[])
                  (r4v1 'i3' int)
                  (wrap: java.util.concurrent.Future : 0x0029: INVOKE 
                  (wrap: java.util.concurrent.ExecutorService : 0x0020: INVOKE  type: STATIC call: com.jingdong.jdsdk.network.toolbox.GlobalExecutorService.lightExecutorService():java.util.concurrent.ExecutorService A[MD:():java.util.concurrent.ExecutorService (m), WRAPPED] (LINE:6))
                  (wrap: java.util.concurrent.Callable<com.jd.framework.network.dialing.IPEntity> : 0x0026: CONSTRUCTOR (r5 I:com.jd.framework.network.dialing.IPEntity A[DONT_GENERATE, DONT_INLINE, REMOVE]), (r10v0 'i2' int A[DONT_INLINE]) A[MD:(com.jd.framework.network.dialing.IPEntity, int):void (m), WRAPPED] call: com.jd.framework.network.dialing.DialingExecutor.1.<init>(com.jd.framework.network.dialing.IPEntity, int):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.util.concurrent.Callable):java.util.concurrent.Future A[MD:<T>:(java.util.concurrent.Callable<T>):java.util.concurrent.Future<T> (c), WRAPPED] (LINE:6))
                 (LINE:6) in method: com.jd.framework.network.dialing.DialingExecutor.randomSelect(java.util.ArrayList<com.jd.framework.network.dialing.IPEntity>, int):com.jd.framework.network.dialing.IPEntity, file: classes13.dex
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
                r0 = 0
                if (r9 == 0) goto L73
                int r1 = r9.size()
                if (r1 > 0) goto La
                goto L73
            La:
                boolean r1 = com.android.volley.VolleyLog.DEBUG
                int r1 = r9.size()
                java.util.concurrent.Future[] r2 = new java.util.concurrent.Future[r1]
                r3 = 0
                r4 = 0
            L14:
                int r5 = r9.size()
                if (r4 >= r5) goto L32
                java.lang.Object r5 = r9.get(r4)
                com.jd.framework.network.dialing.IPEntity r5 = (com.jd.framework.network.dialing.IPEntity) r5
                java.util.concurrent.ExecutorService r6 = com.jingdong.jdsdk.network.toolbox.GlobalExecutorService.lightExecutorService()
                com.jd.framework.network.dialing.DialingExecutor$1 r7 = new com.jd.framework.network.dialing.DialingExecutor$1
                r7.<init>()
                java.util.concurrent.Future r5 = r6.submit(r7)
                r2[r4] = r5
                int r4 = r4 + 1
                goto L14
            L32:
                java.util.ArrayList r9 = new java.util.ArrayList
                r9.<init>()
            L37:
                if (r3 >= r1) goto L57
                r10 = r2[r3]
                java.lang.Object r10 = r10.get()     // Catch: java.lang.Exception -> L42
                com.jd.framework.network.dialing.IPEntity r10 = (com.jd.framework.network.dialing.IPEntity) r10     // Catch: java.lang.Exception -> L42
                goto L47
            L42:
                r10 = move-exception
                r10.printStackTrace()
                r10 = r0
            L47:
                if (r10 == 0) goto L54
                long r4 = r10.time
                r6 = 0
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 <= 0) goto L54
                r9.add(r10)
            L54:
                int r3 = r3 + 1
                goto L37
            L57:
                boolean r10 = com.android.volley.VolleyLog.DEBUG
                int r10 = r9.size()
                if (r10 <= 0) goto L73
                java.util.Random r10 = new java.util.Random
                r10.<init>()
                int r0 = r9.size()
                int r10 = r10.nextInt(r0)
                java.lang.Object r9 = r9.get(r10)
                r0 = r9
                com.jd.framework.network.dialing.IPEntity r0 = (com.jd.framework.network.dialing.IPEntity) r0
            L73:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.framework.network.dialing.DialingExecutor.randomSelect(java.util.ArrayList, int):com.jd.framework.network.dialing.IPEntity");
        }

        public static IPEntity select(ArrayList<IPEntity> arrayList, final int i2) {
            IPEntity iPEntity;
            IPEntity iPEntity2 = null;
            if (arrayList != null && arrayList.size() > 0) {
                boolean z = VolleyLog.DEBUG;
                int size = arrayList.size();
                Future[] futureArr = new Future[size];
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    arrayList.get(i3);
                    futureArr[i3] = GlobalExecutorService.lightExecutorService().submit(new Callable<IPEntity>
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002e: APUT 
                          (r2v0 'futureArr' java.util.concurrent.Future[])
                          (r4v1 'i3' int)
                          (wrap: java.util.concurrent.Future : 0x002a: INVOKE 
                          (wrap: java.util.concurrent.ExecutorService : 0x0021: INVOKE  type: STATIC call: com.jingdong.jdsdk.network.toolbox.GlobalExecutorService.lightExecutorService():java.util.concurrent.ExecutorService A[MD:():java.util.concurrent.ExecutorService (m), WRAPPED] (LINE:6))
                          (wrap: java.util.concurrent.Callable<com.jd.framework.network.dialing.IPEntity> : 0x0027: CONSTRUCTOR (r5 I:com.jd.framework.network.dialing.IPEntity A[DONT_GENERATE, DONT_INLINE, REMOVE]), (r10v0 'i2' int A[DONT_INLINE]) A[MD:(com.jd.framework.network.dialing.IPEntity, int):void (m), WRAPPED] call: com.jd.framework.network.dialing.DialingExecutor.2.<init>(com.jd.framework.network.dialing.IPEntity, int):void type: CONSTRUCTOR)
                         type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.util.concurrent.Callable):java.util.concurrent.Future A[MD:<T>:(java.util.concurrent.Callable<T>):java.util.concurrent.Future<T> (c), WRAPPED] (LINE:6))
                         (LINE:6) in method: com.jd.framework.network.dialing.DialingExecutor.select(java.util.ArrayList<com.jd.framework.network.dialing.IPEntity>, int):com.jd.framework.network.dialing.IPEntity, file: classes13.dex
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
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                        r0 = 0
                        if (r9 == 0) goto L7d
                        int r1 = r9.size()
                        if (r1 > 0) goto Lb
                        goto L7d
                    Lb:
                        boolean r1 = com.android.volley.VolleyLog.DEBUG
                        int r1 = r9.size()
                        java.util.concurrent.Future[] r2 = new java.util.concurrent.Future[r1]
                        r3 = 0
                        r4 = 0
                    L15:
                        int r5 = r9.size()
                        if (r4 >= r5) goto L33
                        java.lang.Object r5 = r9.get(r4)
                        com.jd.framework.network.dialing.IPEntity r5 = (com.jd.framework.network.dialing.IPEntity) r5
                        java.util.concurrent.ExecutorService r6 = com.jingdong.jdsdk.network.toolbox.GlobalExecutorService.lightExecutorService()
                        com.jd.framework.network.dialing.DialingExecutor$2 r7 = new com.jd.framework.network.dialing.DialingExecutor$2
                        r7.<init>()
                        java.util.concurrent.Future r5 = r6.submit(r7)
                        r2[r4] = r5
                        int r4 = r4 + 1
                        goto L15
                    L33:
                        java.util.ArrayList r9 = new java.util.ArrayList
                        r9.<init>()
                    L38:
                        if (r3 >= r1) goto L58
                        r10 = r2[r3]
                        java.lang.Object r10 = r10.get()     // Catch: java.lang.Exception -> L43
                        com.jd.framework.network.dialing.IPEntity r10 = (com.jd.framework.network.dialing.IPEntity) r10     // Catch: java.lang.Exception -> L43
                        goto L48
                    L43:
                        r10 = move-exception
                        r10.printStackTrace()
                        r10 = r0
                    L48:
                        if (r10 == 0) goto L55
                        long r4 = r10.time
                        r6 = 0
                        int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                        if (r8 <= 0) goto L55
                        r9.add(r10)
                    L55:
                        int r3 = r3 + 1
                        goto L38
                    L58:
                        boolean r10 = com.android.volley.VolleyLog.DEBUG
                        int r10 = r9.size()
                        if (r10 <= 0) goto L7d
                        java.util.Iterator r9 = r9.iterator()
                    L64:
                        boolean r10 = r9.hasNext()
                        if (r10 == 0) goto L7d
                        java.lang.Object r10 = r9.next()
                        com.jd.framework.network.dialing.IPEntity r10 = (com.jd.framework.network.dialing.IPEntity) r10
                        if (r0 != 0) goto L73
                        goto L7b
                    L73:
                        long r1 = r0.time
                        long r3 = r10.time
                        int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                        if (r5 <= 0) goto L64
                    L7b:
                        r0 = r10
                        goto L64
                    L7d:
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.framework.network.dialing.DialingExecutor.select(java.util.ArrayList, int):com.jd.framework.network.dialing.IPEntity");
                }

                public static IPEntity select(final IPEntity iPEntity, final int i2) {
                    IPEntity iPEntity2;
                    boolean z = VolleyLog.DEBUG;
                    try {
                        iPEntity2 = (IPEntity) GlobalExecutorService.lightExecutorService().submit(new Callable<IPEntity>() { // from class: com.jd.framework.network.dialing.DialingExecutor.3
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // java.util.concurrent.Callable
                            public IPEntity call() throws Exception {
                                return DialingExecutor.customIpTest(IPEntity.this, i2);
                            }
                        }).get();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        iPEntity2 = null;
                    }
                    if (iPEntity2 == null || iPEntity2.time <= 0) {
                        boolean z2 = VolleyLog.DEBUG;
                        return null;
                    }
                    return iPEntity2;
                }
            }
