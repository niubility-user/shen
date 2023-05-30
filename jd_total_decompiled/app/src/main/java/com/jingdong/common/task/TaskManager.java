package com.jingdong.common.task;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class TaskManager {
    private static final int CORE_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 20;
    private static final int MAX_POOL_SIZE = 10;
    private static final int WORK_QUEUE_SIZE = 30;
    private static TaskManager mTaskManager;
    private ThreadPoolExecutor mExecutor = null;
    private Handler mUICallbackHandler;

    private static synchronized TaskManager build() {
        TaskManager taskManager;
        synchronized (TaskManager.class) {
            taskManager = new TaskManager();
            taskManager.mExecutor = new ThreadPoolExecutor(5, 10, 20L, TimeUnit.SECONDS, new ArrayBlockingQueue(30));
            taskManager.mUICallbackHandler = new Handler(Looper.getMainLooper());
        }
        return taskManager;
    }

    public static <T> void executeTask(final Productor<T> productor, final Consumer<T> consumer) {
        if (mTaskManager == null) {
            mTaskManager = build();
        }
        mTaskManager.mExecutor.execute(new Runnable() { // from class: com.jingdong.common.task.TaskManager.1
            @Override // java.lang.Runnable
            public void run() {
                Productor.this.run();
                if (consumer != null) {
                    TaskManager.mTaskManager.mUICallbackHandler.post(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
                          (wrap: android.os.Handler : 0x000e: IGET 
                          (wrap: com.jingdong.common.task.TaskManager : 0x000a: SGET  A[MD:():com.jingdong.common.task.TaskManager (m), WRAPPED] (LINE:1) com.jingdong.common.task.TaskManager.mTaskManager com.jingdong.common.task.TaskManager)
                         A[MD:(com.jingdong.common.task.TaskManager):android.os.Handler (m), WRAPPED] (LINE:1) com.jingdong.common.task.TaskManager.mUICallbackHandler android.os.Handler)
                          (wrap: java.lang.Runnable : 0x0014: CONSTRUCTOR 
                          (r3v0 'this' com.jingdong.common.task.TaskManager$1 A[IMMUTABLE_TYPE, THIS])
                          (r0 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[MD:(com.jingdong.common.task.TaskManager$1, java.lang.Object):void (m), WRAPPED] call: com.jingdong.common.task.TaskManager.1.1.<init>(com.jingdong.common.task.TaskManager$1, java.lang.Object):void type: CONSTRUCTOR)
                         type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:1) in method: com.jingdong.common.task.TaskManager.1.run():void, file: classes6.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 23 more
                        */
                    /*
                        this = this;
                        com.jingdong.common.task.Productor r0 = com.jingdong.common.task.Productor.this
                        java.lang.Object r0 = r0.run()
                        com.jingdong.common.task.Consumer r1 = r2
                        if (r1 == 0) goto L1a
                        com.jingdong.common.task.TaskManager r1 = com.jingdong.common.task.TaskManager.access$000()
                        android.os.Handler r1 = com.jingdong.common.task.TaskManager.access$100(r1)
                        com.jingdong.common.task.TaskManager$1$1 r2 = new com.jingdong.common.task.TaskManager$1$1
                        r2.<init>()
                        r1.post(r2)
                    L1a:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.task.TaskManager.AnonymousClass1.run():void");
                }
            });
        }
    }
