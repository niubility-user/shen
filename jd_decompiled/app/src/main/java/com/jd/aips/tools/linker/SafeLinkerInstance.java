package com.jd.aips.tools.linker;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.jd.aips.tools.linker.SafeLinker;
import com.jd.aips.tools.linker.elf.ElfParser;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes12.dex */
public class SafeLinkerInstance {
    protected final Set<String> a;
    protected final SafeLinker.LibraryLoader b;

    /* renamed from: c */
    protected final SafeLinker.LibraryInstaller f1636c;
    protected boolean d;

    /* renamed from: e */
    protected boolean f1637e;

    public SafeLinkerInstance() {
        this(new SystemLibraryLoader(), new ApkLibraryInstaller());
    }

    public void b(Context context, String str, String str2) {
        ElfParser elfParser;
        if (!this.a.contains(str) || this.d) {
            try {
                this.b.loadLibrary(str);
                this.a.add(str);
            } catch (UnsatisfiedLinkError e2) {
                Log.getStackTraceString(e2);
                File a = a(context, str, str2);
                if (!a.exists() || this.d) {
                    File a2 = a(context);
                    File a3 = a(context, str, str2);
                    this.b.mapLibraryName(str);
                    File[] listFiles = a2.listFiles(new FilenameFilter
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003d: INVOKE (r1v2 'listFiles' java.io.File[]) = 
                          (r1v1 'a2' java.io.File)
                          (wrap: java.io.FilenameFilter : 0x003a: CONSTRUCTOR 
                          (r7v0 'this' com.jd.aips.tools.linker.SafeLinkerInstance A[IMMUTABLE_TYPE, THIS])
                          (r2 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[MD:(com.jd.aips.tools.linker.SafeLinkerInstance, java.lang.String):void (m), WRAPPED] (LINE:10) call: com.jd.aips.tools.linker.SafeLinkerInstance.2.<init>(com.jd.aips.tools.linker.SafeLinkerInstance, java.lang.String):void type: CONSTRUCTOR)
                         type: VIRTUAL call: java.io.File.listFiles(java.io.FilenameFilter):java.io.File[] A[DECLARE_VAR, MD:(java.io.FilenameFilter):java.io.File[] (c)] (LINE:10) in method: com.jd.aips.tools.linker.SafeLinkerInstance.b(android.content.Context, java.lang.String, java.lang.String):void, file: classes12.dex
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
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeCatchBlock(RegionGen.java:365)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:313)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                        */
                    /*
                        this = this;
                        java.util.Set<java.lang.String> r0 = r7.a
                        boolean r0 = r0.contains(r9)
                        if (r0 == 0) goto Ld
                        boolean r0 = r7.d
                        if (r0 != 0) goto Ld
                        return
                    Ld:
                        com.jd.aips.tools.linker.SafeLinker$LibraryLoader r0 = r7.b     // Catch: java.lang.UnsatisfiedLinkError -> L18
                        r0.loadLibrary(r9)     // Catch: java.lang.UnsatisfiedLinkError -> L18
                        java.util.Set<java.lang.String> r0 = r7.a     // Catch: java.lang.UnsatisfiedLinkError -> L18
                        r0.add(r9)     // Catch: java.lang.UnsatisfiedLinkError -> L18
                        return
                    L18:
                        r0 = move-exception
                        android.util.Log.getStackTraceString(r0)
                        java.io.File r0 = r7.a(r8, r9, r10)
                        boolean r1 = r0.exists()
                        if (r1 == 0) goto L2a
                        boolean r1 = r7.d
                        if (r1 == 0) goto L76
                    L2a:
                        java.io.File r1 = r7.a(r8)
                        java.io.File r10 = r7.a(r8, r9, r10)
                        com.jd.aips.tools.linker.SafeLinker$LibraryLoader r2 = r7.b
                        java.lang.String r2 = r2.mapLibraryName(r9)
                        com.jd.aips.tools.linker.SafeLinkerInstance$2 r3 = new com.jd.aips.tools.linker.SafeLinkerInstance$2
                        r3.<init>(r7)
                        java.io.File[] r1 = r1.listFiles(r3)
                        if (r1 != 0) goto L44
                        goto L62
                    L44:
                        int r2 = r1.length
                        r3 = 0
                    L46:
                        if (r3 >= r2) goto L62
                        r4 = r1[r3]
                        boolean r5 = r7.d
                        if (r5 != 0) goto L5c
                        java.lang.String r5 = r4.getAbsolutePath()
                        java.lang.String r6 = r10.getAbsolutePath()
                        boolean r5 = r5.equals(r6)
                        if (r5 != 0) goto L5f
                    L5c:
                        r4.delete()
                    L5f:
                        int r3 = r3 + 1
                        goto L46
                    L62:
                        com.jd.aips.tools.linker.SafeLinker$LibraryInstaller r1 = r7.f1636c
                        com.jd.aips.tools.linker.SafeLinker$LibraryLoader r10 = r7.b
                        java.lang.String[] r3 = r10.supportedAbis()
                        com.jd.aips.tools.linker.SafeLinker$LibraryLoader r10 = r7.b
                        java.lang.String r4 = r10.mapLibraryName(r9)
                        r2 = r8
                        r5 = r0
                        r6 = r7
                        r1.installLibrary(r2, r3, r4, r5, r6)
                    L76:
                        boolean r10 = r7.f1637e     // Catch: java.io.IOException -> La9
                        if (r10 == 0) goto La9
                        r10 = 0
                        com.jd.aips.tools.linker.elf.ElfParser r1 = new com.jd.aips.tools.linker.elf.ElfParser     // Catch: java.lang.Throwable -> La4
                        r1.<init>(r0)     // Catch: java.lang.Throwable -> La4
                        java.util.List r10 = r1.parseNeededDependencies()     // Catch: java.lang.Throwable -> La1
                        r1.close()     // Catch: java.io.IOException -> La9
                        java.util.Iterator r10 = r10.iterator()     // Catch: java.io.IOException -> La9
                    L8b:
                        boolean r1 = r10.hasNext()     // Catch: java.io.IOException -> La9
                        if (r1 == 0) goto La9
                        java.lang.Object r1 = r10.next()     // Catch: java.io.IOException -> La9
                        java.lang.String r1 = (java.lang.String) r1     // Catch: java.io.IOException -> La9
                        com.jd.aips.tools.linker.SafeLinker$LibraryLoader r2 = r7.b     // Catch: java.io.IOException -> La9
                        java.lang.String r1 = r2.unmapLibraryName(r1)     // Catch: java.io.IOException -> La9
                        r7.loadLibrary(r8, r1)     // Catch: java.io.IOException -> La9
                        goto L8b
                    La1:
                        r8 = move-exception
                        r10 = r1
                        goto La5
                    La4:
                        r8 = move-exception
                    La5:
                        r10.close()     // Catch: java.io.IOException -> La9
                        throw r8     // Catch: java.io.IOException -> La9
                    La9:
                        com.jd.aips.tools.linker.SafeLinker$LibraryLoader r8 = r7.b
                        java.lang.String r10 = r0.getAbsolutePath()
                        r8.loadPath(r10)
                        java.util.Set<java.lang.String> r8 = r7.a
                        r8.add(r9)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.tools.linker.SafeLinkerInstance.b(android.content.Context, java.lang.String, java.lang.String):void");
                }

                public SafeLinkerInstance force() {
                    this.d = true;
                    return this;
                }

                public void loadLibrary(Context context, String str) {
                    loadLibrary(context, str, null, null);
                }

                public void log(String str, Object... objArr) {
                    log(String.format(Locale.getDefault(), str, objArr), new Object[0]);
                }

                public SafeLinkerInstance recursively() {
                    this.f1637e = true;
                    return this;
                }

                protected SafeLinkerInstance(SafeLinker.LibraryLoader libraryLoader, SafeLinker.LibraryInstaller libraryInstaller) {
                    this.a = new HashSet();
                    this.b = libraryLoader;
                    this.f1636c = libraryInstaller;
                }

                protected File a(Context context) {
                    return context.getDir("lib", 0);
                }

                public void loadLibrary(Context context, String str, String str2) {
                    loadLibrary(context, str, str2, null);
                }

                protected File a(Context context, String str, String str2) {
                    String mapLibraryName = this.b.mapLibraryName(str);
                    if (TextUtils.isEmpty(str2)) {
                        return new File(a(context), mapLibraryName);
                    }
                    return new File(a(context), mapLibraryName + OrderISVUtil.MONEY_DECIMAL + str2);
                }

                public void loadLibrary(Context context, String str, SafeLinker.LoadCallback loadCallback) {
                    loadLibrary(context, str, null, loadCallback);
                }

                public void loadLibrary(final Context context, final String str, final String str2, final SafeLinker.LoadCallback loadCallback) {
                    if (context != null) {
                        if (TextUtils.isEmpty(str)) {
                            throw new IllegalArgumentException("Given library is either null or empty");
                        }
                        if (loadCallback == null) {
                            b(context, str, str2);
                            return;
                        } else {
                            new Thread(new Runnable() { // from class: com.jd.aips.tools.linker.SafeLinkerInstance.1
                                {
                                    SafeLinkerInstance.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        SafeLinkerInstance.this.b(context, str, str2);
                                        loadCallback.success();
                                    } catch (MissingLibraryException e2) {
                                        loadCallback.failure(e2);
                                    } catch (UnsatisfiedLinkError e3) {
                                        loadCallback.failure(e3);
                                    }
                                }
                            }).start();
                            return;
                        }
                    }
                    throw new IllegalArgumentException("Given context is null");
                }
            }
