package com.jd.framework.network.dialing;

import com.android.volley.VolleyLog;
import com.android.volley.utils.TimeUtils;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
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
    */
    public static long connect(String str, int i2, int i3) {
        long j2;
        if (i2 == 0) {
            i2 = 443;
        }
        Socket socket = null;
        r0 = null;
        socket = null;
        Socket socket2 = null;
        long j3 = 0;
        try {
            try {
                try {
                    j2 = System.currentTimeMillis();
                    try {
                        Socket socket3 = new Socket();
                        try {
                            SocketAddress inetSocketAddress = new InetSocketAddress(str, i2);
                            socket3.connect(inetSocketAddress, i3);
                            socket3.sendUrgentData(255);
                            j3 = System.currentTimeMillis();
                            if (!socket3.isClosed()) {
                                socket3.shutdownInput();
                            }
                            socket3.shutdownOutput();
                            socket3.close();
                            socket = inetSocketAddress;
                        } catch (IOException e2) {
                            e = e2;
                            socket2 = socket3;
                            e.printStackTrace();
                            if (socket2 != null && !socket2.isClosed()) {
                                socket2.shutdownInput();
                            }
                            socket2.shutdownOutput();
                            socket2.close();
                            socket = socket2;
                            long j4 = j3 - j2;
                            if (VolleyLog.DEBUG) {
                            }
                            return j4;
                        } catch (Throwable th) {
                            th = th;
                            socket = socket3;
                            if (socket != null) {
                                try {
                                    if (!socket.isClosed()) {
                                        socket.shutdownInput();
                                    }
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    throw th;
                                }
                            }
                            socket.shutdownOutput();
                            socket.close();
                            throw th;
                        }
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e5) {
                e = e5;
                j2 = 0;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
        long j42 = j3 - j2;
        if (VolleyLog.DEBUG) {
            String str2 = "ip : " + str + ", rtt time : " + j42;
        }
        return j42;
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
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:474)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 25 more
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
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:474)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 27 more
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
