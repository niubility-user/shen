package com.bumptech.glide.load.engine.executor;

import android.os.Build;
import android.os.StrictMode;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class RuntimeCompat {
    private static final String CPU_LOCATION = "/sys/devices/system/cpu/";
    private static final String CPU_NAME_REGEX = "cpu[0-9]+";
    private static final String TAG = "GlideRuntimeCompat";

    private RuntimeCompat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int availableProcessors() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        return Build.VERSION.SDK_INT < 17 ? Math.max(getCoreCountPre17(), availableProcessors) : availableProcessors;
    }

    private static int getCoreCountPre17() {
        File[] fileArr;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                File file = new File(CPU_LOCATION);
                Pattern.compile(CPU_NAME_REGEX);
                fileArr = file.listFiles(new FilenameFilter
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: INVOKE (r1v9 'fileArr' java.io.File[]) = 
                      (r1v8 'file' java.io.File)
                      (wrap: java.io.FilenameFilter : 0x0013: CONSTRUCTOR (r2 I:java.util.regex.Pattern A[DONT_GENERATE, DONT_INLINE, REMOVE]) A[Catch: all -> 0x001e, MD:(java.util.regex.Pattern):void (m), WRAPPED] (LINE:4) call: com.bumptech.glide.load.engine.executor.RuntimeCompat.1.<init>(java.util.regex.Pattern):void type: CONSTRUCTOR)
                     type: VIRTUAL call: java.io.File.listFiles(java.io.FilenameFilter):java.io.File[] A[Catch: all -> 0x001e, MD:(java.io.FilenameFilter):java.io.File[] (c), TRY_LEAVE] (LINE:4) in method: com.bumptech.glide.load.engine.executor.RuntimeCompat.getCoreCountPre17():int, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                    	... 27 more
                    */
                /*
                    android.os.StrictMode$ThreadPolicy r0 = android.os.StrictMode.allowThreadDiskReads()
                    java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L1e
                    java.lang.String r2 = "/sys/devices/system/cpu/"
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L1e
                    java.lang.String r2 = "cpu[0-9]+"
                    java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)     // Catch: java.lang.Throwable -> L1e
                    com.bumptech.glide.load.engine.executor.RuntimeCompat$1 r3 = new com.bumptech.glide.load.engine.executor.RuntimeCompat$1     // Catch: java.lang.Throwable -> L1e
                    r3.<init>()     // Catch: java.lang.Throwable -> L1e
                    java.io.File[] r1 = r1.listFiles(r3)     // Catch: java.lang.Throwable -> L1e
                    android.os.StrictMode.setThreadPolicy(r0)
                    goto L29
                L1e:
                    java.lang.String r1 = "GlideRuntimeCompat"
                    r2 = 6
                    boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch: java.lang.Throwable -> L34
                    android.os.StrictMode.setThreadPolicy(r0)
                    r1 = 0
                L29:
                    r0 = 1
                    if (r1 == 0) goto L2e
                    int r1 = r1.length
                    goto L2f
                L2e:
                    r1 = 0
                L2f:
                    int r0 = java.lang.Math.max(r0, r1)
                    return r0
                L34:
                    r1 = move-exception
                    android.os.StrictMode.setThreadPolicy(r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.executor.RuntimeCompat.getCoreCountPre17():int");
            }
        }
