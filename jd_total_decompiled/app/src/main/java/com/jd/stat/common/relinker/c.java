package com.jd.stat.common.relinker;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.jd.stat.common.relinker.a.f;
import com.jd.stat.common.relinker.b;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes18.dex */
public class c {
    protected final Set<String> a;
    protected final b.InterfaceC0220b b;

    /* renamed from: c */
    protected final b.a f7023c;
    protected boolean d;

    /* renamed from: e */
    protected boolean f7024e;

    /* renamed from: f */
    protected b.d f7025f;

    public c() {
        this(new d(), new a());
    }

    public void c(Context context, String str, String str2) {
        if (this.a.contains(str) && !this.d) {
            a("%s already loaded previously!", str);
            return;
        }
        try {
            this.b.a(str);
            this.a.add(str);
            a("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e2) {
            a("Loading the library normally failed: %s", Log.getStackTraceString(e2));
            a("%s (%s) was not loaded normally, re-linking...", str, str2);
            File a = a(context, str, str2);
            if (!a.exists() || this.d) {
                if (this.d) {
                    a("Forcing a re-link of %s (%s)...", str, str2);
                }
                b(context, str, str2);
                this.f7023c.a(context, this.b.a(), this.b.c(str), a, this);
            }
            try {
                if (this.f7024e) {
                    Iterator<String> it = new f(a).b().iterator();
                    while (it.hasNext()) {
                        a(context, this.b.d(it.next()));
                    }
                }
            } catch (IOException unused) {
            }
            this.b.b(a.getAbsolutePath());
            this.a.add(str);
            a("%s (%s) was re-linked!", str, str2);
        }
    }

    protected void b(Context context, String str, String str2) {
        File a = a(context);
        File a2 = a(context, str, str2);
        this.b.c(str);
        File[] listFiles = a.listFiles(new FilenameFilter
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: INVOKE (r6v2 'listFiles' java.io.File[]) = 
              (r0v0 'a' java.io.File)
              (wrap: java.io.FilenameFilter : 0x0010: CONSTRUCTOR 
              (r4v0 'this' com.jd.stat.common.relinker.c A[IMMUTABLE_TYPE, THIS])
              (r6 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jd.stat.common.relinker.c, java.lang.String):void (m), WRAPPED] (LINE:4) call: com.jd.stat.common.relinker.c.2.<init>(com.jd.stat.common.relinker.c, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: java.io.File.listFiles(java.io.FilenameFilter):java.io.File[] A[DECLARE_VAR, MD:(java.io.FilenameFilter):java.io.File[] (c)] (LINE:4) in method: com.jd.stat.common.relinker.c.b(android.content.Context, java.lang.String, java.lang.String):void, file: classes18.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	... 15 more
            */
        /*
            this = this;
            java.io.File r0 = r4.a(r5)
            java.io.File r5 = r4.a(r5, r6, r7)
            com.jd.stat.common.relinker.b$b r7 = r4.b
            java.lang.String r6 = r7.c(r6)
            com.jd.stat.common.relinker.c$2 r7 = new com.jd.stat.common.relinker.c$2
            r7.<init>()
            java.io.File[] r6 = r0.listFiles(r7)
            if (r6 != 0) goto L1a
            return
        L1a:
            int r7 = r6.length
            r0 = 0
        L1c:
            if (r0 >= r7) goto L38
            r1 = r6[r0]
            boolean r2 = r4.d
            if (r2 != 0) goto L32
            java.lang.String r2 = r1.getAbsolutePath()
            java.lang.String r3 = r5.getAbsolutePath()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L35
        L32:
            r1.delete()
        L35:
            int r0 = r0 + 1
            goto L1c
        L38:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.relinker.c.b(android.content.Context, java.lang.String, java.lang.String):void");
    }

    protected c(b.InterfaceC0220b interfaceC0220b, b.a aVar) {
        this.a = new HashSet();
        if (interfaceC0220b == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (aVar != null) {
            this.b = interfaceC0220b;
            this.f7023c = aVar;
            return;
        }
        throw new IllegalArgumentException("Cannot pass null library installer");
    }

    public void a(Context context, String str) {
        a(context, str, (String) null, (b.c) null);
    }

    public void a(final Context context, final String str, final String str2, final b.c cVar) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                a("Beginning load of %s...", str);
                if (cVar == null) {
                    c(context, str, str2);
                    return;
                } else {
                    new Thread(new Runnable() { // from class: com.jd.stat.common.relinker.c.1
                        {
                            c.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                c.this.c(context, str, str2);
                                cVar.a();
                            } catch (MissingLibraryException e2) {
                                cVar.a(e2);
                            } catch (UnsatisfiedLinkError e3) {
                                cVar.a(e3);
                            }
                        }
                    }).start();
                    return;
                }
            }
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        throw new IllegalArgumentException("Given context is null");
    }

    protected File a(Context context) {
        return context.getDir("lib", 0);
    }

    protected File a(Context context, String str, String str2) {
        String c2 = this.b.c(str);
        if (TextUtils.isEmpty(str2)) {
            return new File(a(context), c2);
        }
        return new File(a(context), c2 + OrderISVUtil.MONEY_DECIMAL + str2);
    }

    public void a(String str, Object... objArr) {
        a(String.format(Locale.US, str, objArr));
    }

    public void a(String str) {
        b.d dVar = this.f7025f;
        if (dVar != null) {
            dVar.a(str);
        }
    }
}
