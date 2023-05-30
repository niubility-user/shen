package com.jingdong.common.httpdns;

import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes5.dex */
public class DialingExecutor {
    public static final int DEFAULT_DAILING_PORT = 443;
    public static final int DEFAULT_DAILING_TIMEOUT = 2000;
    public static final int DEFAULT_HAPPY_EYEBALL_OFFSET = 150;
    public static final int LOCAL_DNS_DAILING_TIMEOUT = 250;
    public static final String TAG = "DialingExecutor";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
    public static long connect(String str, int i2, int i3) {
        long j2;
        if (i2 == 0) {
            i2 = 443;
        }
        Socket socket = 0;
        r0 = null;
        Socket socket2 = null;
        long j3 = 0;
        try {
            try {
                try {
                    j2 = System.currentTimeMillis();
                    try {
                        Socket socket3 = new Socket();
                        try {
                            socket3.connect(new InetSocketAddress(str, i2), i3);
                            socket3.sendUrgentData(255);
                            j3 = System.currentTimeMillis();
                            if (!socket3.isClosed()) {
                                socket3.shutdownInput();
                            }
                            socket3.shutdownOutput();
                            socket3.close();
                        } catch (IOException e2) {
                            e = e2;
                            socket2 = socket3;
                            e.printStackTrace();
                            if (socket2 != null && !socket2.isClosed()) {
                                socket2.shutdownInput();
                            }
                            socket2.shutdownOutput();
                            socket2.close();
                            long j4 = j3 - j2;
                            String str2 = TAG;
                            StringBuilder sb = new StringBuilder();
                            socket = "ip : ";
                            sb.append("ip : ");
                            sb.append(str);
                            sb.append(", rtt time : ");
                            sb.append(j4);
                            FLog.d(str2, sb.toString());
                            return j4;
                        } catch (Throwable th) {
                            th = th;
                            socket = socket3;
                            if (socket != 0) {
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
        String str22 = TAG;
        StringBuilder sb2 = new StringBuilder();
        socket = "ip : ";
        sb2.append("ip : ");
        sb2.append(str);
        sb2.append(", rtt time : ");
        sb2.append(j42);
        FLog.d(str22, sb2.toString());
        return j42;
    }

    public static long customIpTest(String str, int i2) {
        return connect(str, 443, i2);
    }

    public static IPEntity customIpTest(IPEntity iPEntity, int i2) {
        iPEntity.time = connect(iPEntity.key, 443, i2);
        iPEntity.updateTime = TimeUtils.getCurrentTime();
        return iPEntity;
    }

    public static List<String> domainDialing(List<String> list) {
        String str;
        if (list == null || list.size() <= 0) {
            return null;
        }
        FLog.d(TAG, "start domain connection test.");
        int size = list.size();
        Future[] futureArr = new Future[size];
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.get(i2);
            futureArr[i2] = GlobalExecutorService.lightExecutorService().submit(new Callable<String>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0032: APUT 
                  (r2v1 'futureArr' java.util.concurrent.Future[])
                  (r4v1 'i2' int)
                  (wrap: java.util.concurrent.Future : 0x002e: INVOKE 
                  (wrap: java.util.concurrent.ExecutorService : 0x0025: INVOKE  type: STATIC call: com.jingdong.common.httpdns.GlobalExecutorService.lightExecutorService():java.util.concurrent.ExecutorService A[MD:():java.util.concurrent.ExecutorService (m), WRAPPED])
                  (wrap: java.util.concurrent.Callable<java.lang.String> : 0x002b: CONSTRUCTOR (r5 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE]) A[MD:(java.lang.String):void (m), WRAPPED] call: com.jingdong.common.httpdns.DialingExecutor.4.<init>(java.lang.String):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.util.concurrent.Callable):java.util.concurrent.Future A[MD:<T>:(java.util.concurrent.Callable<T>):java.util.concurrent.Future<T> (c), WRAPPED])
                 in method: com.jingdong.common.httpdns.DialingExecutor.domainDialing(java.util.List<java.lang.String>):java.util.List<java.lang.String>, file: classes5.dex
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
                if (r8 == 0) goto L60
                int r1 = r8.size()
                if (r1 > 0) goto La
                goto L60
            La:
                java.lang.String r1 = com.jingdong.common.httpdns.DialingExecutor.TAG
                java.lang.String r2 = "start domain connection test."
                com.facebook.common.logging.FLog.d(r1, r2)
                int r1 = r8.size()
                java.util.concurrent.Future[] r2 = new java.util.concurrent.Future[r1]
                r3 = 0
                r4 = 0
            L19:
                int r5 = r8.size()
                if (r4 >= r5) goto L37
                java.lang.Object r5 = r8.get(r4)
                java.lang.String r5 = (java.lang.String) r5
                java.util.concurrent.ExecutorService r6 = com.jingdong.common.httpdns.GlobalExecutorService.lightExecutorService()
                com.jingdong.common.httpdns.DialingExecutor$4 r7 = new com.jingdong.common.httpdns.DialingExecutor$4
                r7.<init>()
                java.util.concurrent.Future r5 = r6.submit(r7)
                r2[r4] = r5
                int r4 = r4 + 1
                goto L19
            L37:
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
            L3c:
                if (r3 >= r1) goto L58
                r4 = r2[r3]
                java.lang.Object r4 = r4.get()     // Catch: java.lang.Exception -> L47
                java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Exception -> L47
                goto L4c
            L47:
                r4 = move-exception
                r4.printStackTrace()
                r4 = r0
            L4c:
                boolean r5 = android.text.TextUtils.isEmpty(r4)
                if (r5 != 0) goto L55
                r8.add(r4)
            L55:
                int r3 = r3 + 1
                goto L3c
            L58:
                java.lang.String r0 = com.jingdong.common.httpdns.DialingExecutor.TAG
                java.lang.String r1 = "domain connection test complete."
                com.facebook.common.logging.FLog.d(r0, r1)
                return r8
            L60:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.httpdns.DialingExecutor.domainDialing(java.util.List):java.util.List");
        }

        public static boolean isHealthDomain(String str) {
            return !TextUtils.isEmpty(str) && connect(str, 443, 2000) > 0;
        }

        public static boolean isHealthIP(String str) {
            return !TextUtils.isEmpty(str) && connect(str, 443, 2000) > 0;
        }

        public static IPEntity randomSelect(ArrayList<IPEntity> arrayList, final int i2) {
            IPEntity iPEntity;
            if (arrayList == null || arrayList.size() <= 0) {
                return null;
            }
            FLog.d(TAG, "Start IP connection test.");
            int size = arrayList.size();
            Future[] futureArr = new Future[size];
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList.get(i3);
                futureArr[i3] = GlobalExecutorService.lightExecutorService().submit(new Callable<IPEntity>
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0033: APUT 
                      (r2v1 'futureArr' java.util.concurrent.Future[])
                      (r4v1 'i3' int)
                      (wrap: java.util.concurrent.Future : 0x002f: INVOKE 
                      (wrap: java.util.concurrent.ExecutorService : 0x0026: INVOKE  type: STATIC call: com.jingdong.common.httpdns.GlobalExecutorService.lightExecutorService():java.util.concurrent.ExecutorService A[MD:():java.util.concurrent.ExecutorService (m), WRAPPED])
                      (wrap: java.util.concurrent.Callable<com.jingdong.common.httpdns.IPEntity> : 0x002c: CONSTRUCTOR (r5 I:com.jingdong.common.httpdns.IPEntity A[DONT_GENERATE, DONT_INLINE, REMOVE]), (r10v0 'i2' int A[DONT_INLINE]) A[MD:(com.jingdong.common.httpdns.IPEntity, int):void (m), WRAPPED] call: com.jingdong.common.httpdns.DialingExecutor.1.<init>(com.jingdong.common.httpdns.IPEntity, int):void type: CONSTRUCTOR)
                     type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.util.concurrent.Callable):java.util.concurrent.Future A[MD:<T>:(java.util.concurrent.Callable<T>):java.util.concurrent.Future<T> (c), WRAPPED])
                     in method: com.jingdong.common.httpdns.DialingExecutor.randomSelect(java.util.ArrayList<com.jingdong.common.httpdns.IPEntity>, int):com.jingdong.common.httpdns.IPEntity, file: classes5.dex
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
                    if (r9 == 0) goto L7e
                    int r1 = r9.size()
                    if (r1 > 0) goto Lb
                    goto L7e
                Lb:
                    java.lang.String r1 = com.jingdong.common.httpdns.DialingExecutor.TAG
                    java.lang.String r2 = "Start IP connection test."
                    com.facebook.common.logging.FLog.d(r1, r2)
                    int r1 = r9.size()
                    java.util.concurrent.Future[] r2 = new java.util.concurrent.Future[r1]
                    r3 = 0
                    r4 = 0
                L1a:
                    int r5 = r9.size()
                    if (r4 >= r5) goto L38
                    java.lang.Object r5 = r9.get(r4)
                    com.jingdong.common.httpdns.IPEntity r5 = (com.jingdong.common.httpdns.IPEntity) r5
                    java.util.concurrent.ExecutorService r6 = com.jingdong.common.httpdns.GlobalExecutorService.lightExecutorService()
                    com.jingdong.common.httpdns.DialingExecutor$1 r7 = new com.jingdong.common.httpdns.DialingExecutor$1
                    r7.<init>()
                    java.util.concurrent.Future r5 = r6.submit(r7)
                    r2[r4] = r5
                    int r4 = r4 + 1
                    goto L1a
                L38:
                    java.util.ArrayList r9 = new java.util.ArrayList
                    r9.<init>()
                L3d:
                    if (r3 >= r1) goto L5d
                    r10 = r2[r3]
                    java.lang.Object r10 = r10.get()     // Catch: java.lang.Exception -> L48
                    com.jingdong.common.httpdns.IPEntity r10 = (com.jingdong.common.httpdns.IPEntity) r10     // Catch: java.lang.Exception -> L48
                    goto L4d
                L48:
                    r10 = move-exception
                    r10.printStackTrace()
                    r10 = r0
                L4d:
                    if (r10 == 0) goto L5a
                    long r4 = r10.time
                    r6 = 0
                    int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                    if (r8 <= 0) goto L5a
                    r9.add(r10)
                L5a:
                    int r3 = r3 + 1
                    goto L3d
                L5d:
                    java.lang.String r10 = com.jingdong.common.httpdns.DialingExecutor.TAG
                    java.lang.String r1 = "IP connection test complete."
                    com.facebook.common.logging.FLog.d(r10, r1)
                    int r10 = r9.size()
                    if (r10 <= 0) goto L7e
                    java.util.Random r10 = new java.util.Random
                    r10.<init>()
                    int r0 = r9.size()
                    int r10 = r10.nextInt(r0)
                    java.lang.Object r9 = r9.get(r10)
                    r0 = r9
                    com.jingdong.common.httpdns.IPEntity r0 = (com.jingdong.common.httpdns.IPEntity) r0
                L7e:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.httpdns.DialingExecutor.randomSelect(java.util.ArrayList, int):com.jingdong.common.httpdns.IPEntity");
            }

            public static IPEntity select(final IPEntity iPEntity, final int i2) {
                IPEntity iPEntity2;
                FLog.d(TAG, "Start IP connection test.");
                try {
                    iPEntity2 = (IPEntity) GlobalExecutorService.lightExecutorService().submit(new Callable<IPEntity>() { // from class: com.jingdong.common.httpdns.DialingExecutor.3
                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.concurrent.Callable
                        public IPEntity call() {
                            return DialingExecutor.customIpTest(IPEntity.this, i2);
                        }
                    }).get();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    iPEntity2 = null;
                }
                if (iPEntity2 == null || iPEntity2.time <= 0) {
                    FLog.d(TAG, "IP connection test complete.");
                    return iPEntity2;
                }
                return iPEntity2;
            }

            public static IPEntity select(ArrayList<IPEntity> arrayList, final int i2) {
                IPEntity iPEntity;
                IPEntity iPEntity2 = null;
                if (arrayList != null && arrayList.size() > 0) {
                    FLog.d(TAG, "Start IP connection test.");
                    int size = arrayList.size();
                    Future[] futureArr = new Future[size];
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        arrayList.get(i3);
                        futureArr[i3] = GlobalExecutorService.lightExecutorService().submit(new Callable<IPEntity>
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0033: APUT 
                              (r2v1 'futureArr' java.util.concurrent.Future[])
                              (r4v1 'i3' int)
                              (wrap: java.util.concurrent.Future : 0x002f: INVOKE 
                              (wrap: java.util.concurrent.ExecutorService : 0x0026: INVOKE  type: STATIC call: com.jingdong.common.httpdns.GlobalExecutorService.lightExecutorService():java.util.concurrent.ExecutorService A[MD:():java.util.concurrent.ExecutorService (m), WRAPPED])
                              (wrap: java.util.concurrent.Callable<com.jingdong.common.httpdns.IPEntity> : 0x002c: CONSTRUCTOR (r5 I:com.jingdong.common.httpdns.IPEntity A[DONT_GENERATE, DONT_INLINE, REMOVE]), (r10v0 'i2' int A[DONT_INLINE]) A[MD:(com.jingdong.common.httpdns.IPEntity, int):void (m), WRAPPED] call: com.jingdong.common.httpdns.DialingExecutor.2.<init>(com.jingdong.common.httpdns.IPEntity, int):void type: CONSTRUCTOR)
                             type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.util.concurrent.Callable):java.util.concurrent.Future A[MD:<T>:(java.util.concurrent.Callable<T>):java.util.concurrent.Future<T> (c), WRAPPED])
                             in method: com.jingdong.common.httpdns.DialingExecutor.select(java.util.ArrayList<com.jingdong.common.httpdns.IPEntity>, int):com.jingdong.common.httpdns.IPEntity, file: classes5.dex
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
                            if (r9 == 0) goto L87
                            int r1 = r9.size()
                            if (r1 > 0) goto Lb
                            goto L87
                        Lb:
                            java.lang.String r1 = com.jingdong.common.httpdns.DialingExecutor.TAG
                            java.lang.String r2 = "Start IP connection test."
                            com.facebook.common.logging.FLog.d(r1, r2)
                            int r1 = r9.size()
                            java.util.concurrent.Future[] r2 = new java.util.concurrent.Future[r1]
                            r3 = 0
                            r4 = 0
                        L1a:
                            int r5 = r9.size()
                            if (r4 >= r5) goto L38
                            java.lang.Object r5 = r9.get(r4)
                            com.jingdong.common.httpdns.IPEntity r5 = (com.jingdong.common.httpdns.IPEntity) r5
                            java.util.concurrent.ExecutorService r6 = com.jingdong.common.httpdns.GlobalExecutorService.lightExecutorService()
                            com.jingdong.common.httpdns.DialingExecutor$2 r7 = new com.jingdong.common.httpdns.DialingExecutor$2
                            r7.<init>()
                            java.util.concurrent.Future r5 = r6.submit(r7)
                            r2[r4] = r5
                            int r4 = r4 + 1
                            goto L1a
                        L38:
                            java.util.ArrayList r9 = new java.util.ArrayList
                            r9.<init>()
                        L3d:
                            if (r3 >= r1) goto L5d
                            r10 = r2[r3]
                            java.lang.Object r10 = r10.get()     // Catch: java.lang.Exception -> L48
                            com.jingdong.common.httpdns.IPEntity r10 = (com.jingdong.common.httpdns.IPEntity) r10     // Catch: java.lang.Exception -> L48
                            goto L4d
                        L48:
                            r10 = move-exception
                            r10.printStackTrace()
                            r10 = r0
                        L4d:
                            if (r10 == 0) goto L5a
                            long r4 = r10.time
                            r6 = 0
                            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                            if (r8 <= 0) goto L5a
                            r9.add(r10)
                        L5a:
                            int r3 = r3 + 1
                            goto L3d
                        L5d:
                            java.lang.String r10 = com.jingdong.common.httpdns.DialingExecutor.TAG
                            java.lang.String r1 = "IP connection test complete."
                            com.facebook.common.logging.FLog.d(r10, r1)
                            int r10 = r9.size()
                            if (r10 <= 0) goto L87
                            java.util.Iterator r9 = r9.iterator()
                        L6e:
                            boolean r10 = r9.hasNext()
                            if (r10 == 0) goto L87
                            java.lang.Object r10 = r9.next()
                            com.jingdong.common.httpdns.IPEntity r10 = (com.jingdong.common.httpdns.IPEntity) r10
                            if (r0 != 0) goto L7d
                            goto L85
                        L7d:
                            long r1 = r0.time
                            long r3 = r10.time
                            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                            if (r5 <= 0) goto L6e
                        L85:
                            r0 = r10
                            goto L6e
                        L87:
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.httpdns.DialingExecutor.select(java.util.ArrayList, int):com.jingdong.common.httpdns.IPEntity");
                    }
                }
