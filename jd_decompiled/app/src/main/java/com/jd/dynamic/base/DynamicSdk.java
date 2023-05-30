package com.jd.dynamic.base;

import android.app.Application;
import android.app.Dialog;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.facebook.soloader.SoLoader;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.NewDynamicFetcher;
import com.jd.dynamic.base.interfaces.IABConfig;
import com.jd.dynamic.base.interfaces.IAppRouter;
import com.jd.dynamic.base.interfaces.ICustomView;
import com.jd.dynamic.base.interfaces.IDarkChangeListener;
import com.jd.dynamic.base.interfaces.IDynamicDark;
import com.jd.dynamic.base.interfaces.IDynamicMta;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.base.interfaces.IUniConfig;
import com.jd.dynamic.lib.error.DowngradeException;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

@Keep
/* loaded from: classes13.dex */
public class DynamicSdk {
    public static boolean hasLoadJsSo = false;
    private static String sDynamicStatus = "1";
    private static Engine sEngine;

    /* loaded from: classes13.dex */
    public static final class Engine {
        private Context a;

        /* renamed from: c */
        private String f1857c;
        private String[] d;

        /* renamed from: l */
        private ICustomHost f1865l;

        /* renamed from: m */
        private INetWorkRequest f1866m;

        /* renamed from: n */
        private IUniConfig f1867n;
        private IAppRouter o;
        private IImageLoader p;
        private IDynamicMta q;
        private Configuration r;
        private IDynamicDark s;
        private ICustomView t;
        private List<IDarkChangeListener> u;
        private IExceptionHandler v;
        private String w;
        private IDynamicDriver x;
        private IABConfig y;
        private String b = "dynamicSdk";

        /* renamed from: e */
        private boolean f1858e = false;

        /* renamed from: f */
        private boolean f1859f = false;

        /* renamed from: g */
        private boolean f1860g = false;

        /* renamed from: h */
        private boolean f1861h = false;

        /* renamed from: i */
        private boolean f1862i = true;

        /* renamed from: j */
        private int f1863j = 0;

        /* renamed from: k */
        private String f1864k = "";
        private final Object z = new Object();
        public HashMap<String, Object> globalCache = new HashMap<>(16);
        public ConcurrentHashMap<String, ArrayList<DynamicTemplateEngine>> templateEngineCache = new ConcurrentHashMap<>(16);

        /* loaded from: classes13.dex */
        public static final class Builder {
            private Context a;
            private String b;

            /* renamed from: c */
            private boolean f1874c;
            private boolean d;

            /* renamed from: e */
            private boolean f1875e;

            /* renamed from: f */
            private boolean f1876f;

            /* renamed from: g */
            private boolean f1877g;

            /* renamed from: h */
            private int f1878h;

            /* renamed from: i */
            private String f1879i;

            /* renamed from: j */
            private String[] f1880j;

            /* renamed from: k */
            private ICustomHost f1881k;

            /* renamed from: l */
            private INetWorkRequest f1882l;

            /* renamed from: m */
            private IUniConfig f1883m;

            /* renamed from: n */
            private IAppRouter f1884n;
            private IImageLoader o;
            private IDynamicMta p;
            private IDynamicDark q;
            private ICustomView r;
            private IExceptionHandler s;
            private String t;
            private IABConfig u;

            private Builder() {
                this.b = "dynamicSdk";
                this.f1874c = false;
                this.d = false;
                this.f1875e = false;
                this.f1876f = false;
                this.f1877g = true;
                this.f1878h = 0;
            }

            public Engine build() {
                Engine engine = new Engine();
                engine.a = this.a;
                engine.b = this.b;
                engine.f1858e = this.f1874c;
                engine.f1859f = this.d;
                engine.f1860g = this.f1875e;
                engine.f1861h = this.f1876f;
                engine.f1862i = this.f1877g;
                engine.f1857c = this.f1879i;
                engine.f1863j = this.f1878h;
                engine.d = this.f1880j;
                engine.f1865l = this.f1881k;
                engine.f1866m = this.f1882l;
                engine.f1867n = this.f1883m;
                engine.o = this.f1884n;
                engine.p = this.o;
                engine.q = this.p;
                engine.s = this.q;
                engine.t = this.r;
                engine.v = this.s;
                engine.w = this.t;
                DPIUtil.setDensity(BaseInfo.getDensity());
                engine.x = new DynamicDriver();
                engine.y = this.u;
                com.jd.dynamic.lib.expv2.b bVar = com.jd.dynamic.lib.expv2.b.f2233c;
                bVar.a();
                bVar.b(100);
                return engine;
            }

            public Builder pkgType(int i2) {
                this.f1878h = i2;
                return this;
            }

            public Builder setAbConfig(IABConfig iABConfig) {
                this.u = iABConfig;
                return this;
            }

            public Builder setAppRouter(IAppRouter iAppRouter) {
                this.f1884n = iAppRouter;
                return this;
            }

            public Builder setCustomHost(ICustomHost iCustomHost) {
                this.f1881k = iCustomHost;
                return this;
            }

            public Builder setCustomView(ICustomView iCustomView) {
                this.r = iCustomView;
                return this;
            }

            public Builder setDarkSwitch(IDynamicDark iDynamicDark) {
                this.q = iDynamicDark;
                return this;
            }

            public Builder setDynamicMta(IDynamicMta iDynamicMta) {
                this.p = iDynamicMta;
                return this;
            }

            public Builder setExceptionHandler(IExceptionHandler iExceptionHandler) {
                this.s = iExceptionHandler;
                return this;
            }

            public Builder setImageLoader(IImageLoader iImageLoader) {
                this.o = iImageLoader;
                return this;
            }

            public Builder setLocalResourcePath(String str) {
                this.t = str;
                return this;
            }

            public Builder setNetWorkRequest(INetWorkRequest iNetWorkRequest) {
                this.f1882l = iNetWorkRequest;
                return this;
            }

            public Builder setUniConfig(IUniConfig iUniConfig) {
                this.f1883m = iUniConfig;
                return this;
            }

            public Builder useDebug(boolean z) {
                this.f1876f = z;
                return this;
            }

            public Builder useFetchAtFirstRequest(boolean z) {
                this.d = z;
                return this;
            }

            public Builder useFetchAtInit(boolean z) {
                this.f1874c = z;
                return this;
            }

            public Builder useLog(boolean z) {
                this.f1875e = z;
                return this;
            }

            public Builder useMta(boolean z) {
                this.f1877g = z;
                return this;
            }

            public Builder withAppType(String str) {
                this.f1879i = str;
                return this;
            }

            public Builder withCacheDir(String str) {
                if (!TextUtils.isEmpty(str)) {
                    this.b = str;
                }
                return this;
            }

            public Builder withContext(Context context) {
                if (context instanceof Application) {
                    context = context.getApplicationContext();
                }
                this.a = context;
                return this;
            }

            public Builder withSystemCodes(String... strArr) {
                this.f1880j = strArr;
                return this;
            }
        }

        public static /* synthetic */ void D(Throwable th) {
        }

        public static /* synthetic */ Boolean p(String str) {
            com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str);
            return Boolean.valueOf((TextUtils.equals(g2.b, "1") || TextUtils.equals(g2.b, "2")) || (TextUtils.equals(g2.b, "0") && (com.jd.dynamic.lib.utils.m.I(g2.f1728c) || com.jd.dynamic.lib.utils.m.I(g2.f1729e))));
        }

        public void r() {
            com.jd.dynamic.lib.utils.t.b(this.f1860g);
            if (this.f1858e) {
                fetchTemplates();
            }
            if (SoLoader.isInitialized()) {
                com.jd.dynamic.lib.utils.n.a();
            } else {
                DynamicSdk.handException("YogaInit", "fastInitParser not work,soloader not init yet", null, "SDK1", null);
            }
            com.jd.dynamic.b.m.f.a().b();
        }

        public static /* synthetic */ void s(Throwable th) {
        }

        public static /* synthetic */ void t(boolean z, IDarkChangeListener iDarkChangeListener) {
            if (!(iDarkChangeListener instanceof DynamicTemplateEngine) || ((DynamicTemplateEngine) iDarkChangeListener).isAttached) {
                iDarkChangeListener.onDarkChange(z);
            }
        }

        public static /* synthetic */ Boolean w(String str) {
            com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str);
            return Boolean.valueOf((TextUtils.equals(g2.b, "1") || TextUtils.equals(g2.b, "2")) || (TextUtils.equals(g2.b, "0") && (com.jd.dynamic.lib.utils.m.I(g2.f1728c) || com.jd.dynamic.lib.utils.m.I(g2.f1729e))));
        }

        public static /* synthetic */ void z(Throwable th) {
        }

        public void addDarkChangeListener(IDarkChangeListener iDarkChangeListener) {
            List<IDarkChangeListener> list;
            synchronized (this.z) {
                if (this.u == null) {
                    this.u = new ArrayList();
                }
                if (iDarkChangeListener instanceof DynamicTemplateEngine) {
                    if (((DynamicTemplateEngine) iDarkChangeListener).isAttached && !this.u.contains(iDarkChangeListener)) {
                        list = this.u;
                        list.add(iDarkChangeListener);
                    }
                } else if (!this.u.contains(iDarkChangeListener)) {
                    list = this.u;
                    list.add(iDarkChangeListener);
                }
            }
        }

        public void clearCache() {
            com.jd.dynamic.b.e.a.a.g(true);
            DynamicFetcher.reset();
            com.jd.dynamic.b.e.a.b.f(true);
            NewDynamicFetcher.reset();
        }

        public void clearCache(boolean z) {
            com.jd.dynamic.b.e.a.a.g(z);
            DynamicFetcher.reset();
            com.jd.dynamic.b.e.a.b.f(z);
            NewDynamicFetcher.reset();
        }

        public void fetchTemplates() {
            fetchTemplates(null, true, this.d);
        }

        public void fetchTemplates(final DynamicFetcher.GlobalConfigListener globalConfigListener, boolean z, String... strArr) {
            Engine engine = this;
            String dynamicStatus = DynamicSdk.getDynamicStatus();
            int i2 = 0;
            if (TextUtils.equals(dynamicStatus, "0")) {
                if (globalConfigListener != null) {
                    globalConfigListener.onError(new DowngradeException("\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528"));
                }
                int length = strArr.length;
                while (i2 < length) {
                    DynamicMtaUtil.uploadDowngradeQueryMta(strArr[i2], dynamicStatus, null);
                    i2++;
                }
            } else if (TextUtils.isEmpty(engine.f1857c) || !com.jd.dynamic.lib.utils.m.L(strArr)) {
                if (globalConfigListener != null) {
                    globalConfigListener.onError(new DynamicException("appType\u4e3a\u7a7a\u6216\u8005systemCode\u4e3a\u7a7a"));
                }
            } else {
                ArrayList arrayList = new ArrayList();
                Observable.from(strArr).filter(new Func1() { // from class: com.jd.dynamic.base.e0
                    @Override // rx.functions.Func1
                    public final Object call(Object obj) {
                        Boolean p;
                        p = DynamicSdk.Engine.p((String) obj);
                        return p;
                    }
                }).forEach(new c1(arrayList), new Action1() { // from class: com.jd.dynamic.base.h0
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        DynamicSdk.Engine.s((Throwable) obj);
                    }
                });
                final int size = arrayList.size();
                if (size == 0) {
                    int length2 = strArr.length;
                    while (i2 < length2) {
                        DynamicMtaUtil.uploadDowngradeQueryMta(strArr[i2], dynamicStatus, null);
                        i2++;
                    }
                    if (globalConfigListener != null) {
                        globalConfigListener.onError(new DowngradeException(Arrays.toString(strArr) + "\u964d\u7ea7\u4e0d\u53ef\u7528"));
                        return;
                    }
                    return;
                }
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                new AtomicInteger(size);
                new AtomicInteger(0);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    final String str = (String) it.next();
                    final AtomicBoolean atomicBoolean2 = atomicBoolean;
                    DynamicFetcher.requestTemplateConfigs(engine.f1857c, str, z, new DynamicFetcher.GlobalConfigListener
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00bb: INVOKE 
                          (wrap: java.lang.String : 0x00a4: IGET (r8v1 'engine' com.jd.dynamic.base.DynamicSdk$Engine) A[WRAPPED] com.jd.dynamic.base.DynamicSdk.Engine.c java.lang.String)
                          (r15v1 'str' java.lang.String)
                          (r18v0 'z' boolean)
                          (wrap: com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener : 0x00b6: CONSTRUCTOR 
                          (r16v0 'this' com.jd.dynamic.base.DynamicSdk$Engine A[IMMUTABLE_TYPE, THIS])
                          (r15v1 'str' java.lang.String A[DONT_INLINE])
                          (r3v1 'atomicBoolean2' java.util.concurrent.atomic.AtomicBoolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r17v0 'globalConfigListener' com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener A[DONT_INLINE])
                          (r12 I:java.util.concurrent.atomic.AtomicInteger A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r13 I:java.util.concurrent.atomic.AtomicInteger A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r10v0 'size' int A[DONT_INLINE])
                         A[MD:(com.jd.dynamic.base.DynamicSdk$Engine, java.lang.String, java.util.concurrent.atomic.AtomicBoolean, com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener, java.util.concurrent.atomic.AtomicInteger, java.util.concurrent.atomic.AtomicInteger, int):void (m), WRAPPED] call: com.jd.dynamic.base.DynamicSdk.Engine.2.<init>(com.jd.dynamic.base.DynamicSdk$Engine, java.lang.String, java.util.concurrent.atomic.AtomicBoolean, com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener, java.util.concurrent.atomic.AtomicInteger, java.util.concurrent.atomic.AtomicInteger, int):void type: CONSTRUCTOR)
                         type: STATIC call: com.jd.dynamic.base.DynamicFetcher.requestTemplateConfigs(java.lang.String, java.lang.String, boolean, com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener):void A[MD:(java.lang.String, java.lang.String, boolean, com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener):void (m)] in method: com.jd.dynamic.base.DynamicSdk.Engine.fetchTemplates(com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener, boolean, java.lang.String[]):void, file: classes13.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:226)
                        	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                        	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:156)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:133)
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
                        this = this;
                        r8 = r16
                        r9 = r17
                        r0 = r19
                        java.lang.String r1 = com.jd.dynamic.base.DynamicSdk.getDynamicStatus()
                        java.lang.String r2 = "0"
                        boolean r2 = android.text.TextUtils.equals(r1, r2)
                        r3 = 0
                        r4 = 0
                        if (r2 == 0) goto L2c
                        if (r9 == 0) goto L20
                        com.jd.dynamic.lib.error.DowngradeException r2 = new com.jd.dynamic.lib.error.DowngradeException
                        java.lang.String r5 = "\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528"
                        r2.<init>(r5)
                        r9.onError(r2)
                    L20:
                        int r2 = r0.length
                    L21:
                        if (r4 >= r2) goto L2b
                        r5 = r0[r4]
                        com.jd.dynamic.base.DynamicMtaUtil.uploadDowngradeQueryMta(r5, r1, r3)
                        int r4 = r4 + 1
                        goto L21
                    L2b:
                        return
                    L2c:
                        java.lang.String r2 = r8.f1857c
                        boolean r2 = android.text.TextUtils.isEmpty(r2)
                        if (r2 != 0) goto Lc3
                        boolean r2 = com.jd.dynamic.lib.utils.m.L(r19)
                        if (r2 == 0) goto Lc3
                        java.util.ArrayList r2 = new java.util.ArrayList
                        r2.<init>()
                        rx.Observable r5 = rx.Observable.from(r19)
                        com.jd.dynamic.base.e0 r6 = new rx.functions.Func1() { // from class: com.jd.dynamic.base.e0
                            static {
                                /*
                                    com.jd.dynamic.base.e0 r0 = new com.jd.dynamic.base.e0
                                    r0.<init>()
                                    
                                    // error: 0x0005: SPUT (r0 I:com.jd.dynamic.base.e0) com.jd.dynamic.base.e0.g com.jd.dynamic.base.e0
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.e0.<clinit>():void");
                            }

                            {
                                /*
                                    r0 = this;
                                    r0.<init>()
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.e0.<init>():void");
                            }

                            @Override // rx.functions.Func1
                            public final java.lang.Object call(java.lang.Object r1) {
                                /*
                                    r0 = this;
                                    java.lang.String r1 = (java.lang.String) r1
                                    java.lang.Boolean r1 = com.jd.dynamic.base.DynamicSdk.Engine.H(r1)
                                    return r1
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.e0.call(java.lang.Object):java.lang.Object");
                            }
                        }
                        rx.Observable r5 = r5.filter(r6)
                        com.jd.dynamic.base.c1 r6 = new com.jd.dynamic.base.c1
                        r6.<init>(r2)
                        com.jd.dynamic.base.h0 r7 = new rx.functions.Action1() { // from class: com.jd.dynamic.base.h0
                            static {
                                /*
                                    com.jd.dynamic.base.h0 r0 = new com.jd.dynamic.base.h0
                                    r0.<init>()
                                    
                                    // error: 0x0005: SPUT (r0 I:com.jd.dynamic.base.h0) com.jd.dynamic.base.h0.g com.jd.dynamic.base.h0
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.h0.<clinit>():void");
                            }

                            {
                                /*
                                    r0 = this;
                                    r0.<init>()
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.h0.<init>():void");
                            }

                            @Override // rx.functions.Action1
                            public final void call(java.lang.Object r1) {
                                /*
                                    r0 = this;
                                    java.lang.Throwable r1 = (java.lang.Throwable) r1
                                    com.jd.dynamic.base.DynamicSdk.Engine.K(r1)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.h0.call(java.lang.Object):void");
                            }
                        }
                        r5.forEach(r6, r7)
                        int r10 = r2.size()
                        if (r10 != 0) goto L84
                        int r2 = r0.length
                    L5a:
                        if (r4 >= r2) goto L64
                        r5 = r0[r4]
                        com.jd.dynamic.base.DynamicMtaUtil.uploadDowngradeQueryMta(r5, r1, r3)
                        int r4 = r4 + 1
                        goto L5a
                    L64:
                        if (r9 == 0) goto L83
                        com.jd.dynamic.lib.error.DowngradeException r1 = new com.jd.dynamic.lib.error.DowngradeException
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        r2.<init>()
                        java.lang.String r0 = java.util.Arrays.toString(r19)
                        r2.append(r0)
                        java.lang.String r0 = "\u964d\u7ea7\u4e0d\u53ef\u7528"
                        r2.append(r0)
                        java.lang.String r0 = r2.toString()
                        r1.<init>(r0)
                        r9.onError(r1)
                    L83:
                        return
                    L84:
                        java.util.concurrent.atomic.AtomicBoolean r11 = new java.util.concurrent.atomic.AtomicBoolean
                        r11.<init>(r4)
                        java.util.concurrent.atomic.AtomicInteger r12 = new java.util.concurrent.atomic.AtomicInteger
                        r12.<init>(r10)
                        java.util.concurrent.atomic.AtomicInteger r13 = new java.util.concurrent.atomic.AtomicInteger
                        r13.<init>(r4)
                        java.util.Iterator r14 = r2.iterator()
                    L97:
                        boolean r0 = r14.hasNext()
                        if (r0 == 0) goto Lcf
                        java.lang.Object r0 = r14.next()
                        r15 = r0
                        java.lang.String r15 = (java.lang.String) r15
                        java.lang.String r7 = r8.f1857c
                        com.jd.dynamic.base.DynamicSdk$Engine$2 r6 = new com.jd.dynamic.base.DynamicSdk$Engine$2
                        r0 = r6
                        r1 = r16
                        r2 = r15
                        r3 = r11
                        r4 = r17
                        r5 = r12
                        r8 = r6
                        r6 = r13
                        r19 = r11
                        r11 = r7
                        r7 = r10
                        r0.<init>(r1)
                        r0 = r18
                        com.jd.dynamic.base.DynamicFetcher.requestTemplateConfigs(r11, r15, r0, r8)
                        r8 = r16
                        r11 = r19
                        goto L97
                    Lc3:
                        if (r9 == 0) goto Lcf
                        com.jd.dynamic.lib.error.DynamicException r0 = new com.jd.dynamic.lib.error.DynamicException
                        java.lang.String r1 = "appType\u4e3a\u7a7a\u6216\u8005systemCode\u4e3a\u7a7a"
                        r0.<init>(r1)
                        r9.onError(r0)
                    Lcf:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicSdk.Engine.fetchTemplates(com.jd.dynamic.base.DynamicFetcher$GlobalConfigListener, boolean, java.lang.String[]):void");
                }

                public IABConfig getAbConfig() {
                    return this.y;
                }

                public Configuration getAppConfiguration() {
                    return this.r;
                }

                public IAppRouter getAppRouter() {
                    return this.o;
                }

                public String getAppType() {
                    return this.f1857c;
                }

                public String getCacheDir() {
                    return this.b;
                }

                public Context getContext() {
                    return this.a;
                }

                public ICustomView getCustomViewFactory() {
                    return this.t;
                }

                public IDynamicDark getDynamicDark() {
                    return this.s;
                }

                public IDynamicMta getDynamicMta() {
                    return this.q;
                }

                public IExceptionHandler getExceptionHandler() {
                    return this.v;
                }

                public String getHost() {
                    ICustomHost iCustomHost = this.f1865l;
                    String host = iCustomHost != null ? iCustomHost.getHost() : "";
                    if (TextUtils.isEmpty(host)) {
                        host = this.f1861h ? "beta-api.m.jd.com" : "api.m.jd.com";
                    }
                    if (!TextUtils.isEmpty(this.f1864k) && !TextUtils.equals(this.f1864k, host)) {
                        clearCache();
                    }
                    this.f1864k = host;
                    return host;
                }

                public IImageLoader getImageLoader() {
                    return this.p;
                }

                public String getLocalResourcePath() {
                    return this.w;
                }

                public int getPkgType() {
                    return this.f1863j;
                }

                public INetWorkRequest getRequest() {
                    return this.f1866m;
                }

                public String[] getSystemCodes() {
                    return this.d;
                }

                public IUniConfig getUniConfig() {
                    return this.f1867n;
                }

                public boolean hasCachedTempFile(String str, String str2) {
                    String dynamicStatus = DynamicSdk.getDynamicStatus();
                    if (com.jd.dynamic.b.a.b.o().N() && com.jd.dynamic.b.i.a.e(str, str2)) {
                        return true;
                    }
                    if (TextUtils.equals(dynamicStatus, "0")) {
                        DynamicMtaUtil.uploadDowngradeCacheMta(str, str2, dynamicStatus, null, null);
                        return false;
                    }
                    boolean r = com.jd.dynamic.b.e.a.a.r(str, str2);
                    DynamicMtaUtil.displayTemplateMta(str, str2, r, false);
                    com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str);
                    boolean equals = TextUtils.equals(g2.b, "0");
                    boolean equals2 = TextUtils.equals(g2.b, "2");
                    if (equals) {
                        boolean z = com.jd.dynamic.lib.utils.m.I(g2.f1728c) && g2.f1728c.contains(str2);
                        r2 = com.jd.dynamic.lib.utils.m.I(g2.f1729e) && g2.f1729e.contains(str2);
                        if (!z && !r2) {
                            DynamicMtaUtil.uploadDowngradeCacheMta(str, str2, dynamicStatus, "0", "0");
                            return false;
                        }
                        DynamicMtaUtil.uploadDowngradeCacheMta(str, str2, dynamicStatus, "0", z ? "1" : "2");
                    } else if (equals2) {
                        boolean z2 = com.jd.dynamic.lib.utils.m.I(g2.f1728c) && g2.f1728c.contains(str2);
                        if (com.jd.dynamic.lib.utils.m.I(g2.d) && g2.d.contains(str2)) {
                            return false;
                        }
                        if (!z2) {
                            if (!r && !com.jd.dynamic.b.i.a.e(str, str2)) {
                                r2 = false;
                            }
                            return r2;
                        }
                    }
                    return r;
                }

                public boolean hasDarkListener(IDarkChangeListener iDarkChangeListener) {
                    List<IDarkChangeListener> list = this.u;
                    return (list == null || list.isEmpty() || !this.u.contains(iDarkChangeListener)) ? false : true;
                }

                public boolean isDebug() {
                    return this.f1861h;
                }

                public boolean isFetchAtFirstRequest() {
                    return this.f1859f;
                }

                public boolean isFetchAtInit() {
                    return this.f1858e;
                }

                public boolean isLog() {
                    return this.f1860g;
                }

                public void newFetchTemplates() {
                    newFetchTemplates(null, true, this.d);
                }

                public void newFetchTemplates(final NewDynamicFetcher.GlobalConfigListener globalConfigListener, boolean z, String... strArr) {
                    Engine engine = this;
                    String dynamicStatus = DynamicSdk.getDynamicStatus();
                    int i2 = 0;
                    if (TextUtils.equals(dynamicStatus, "0")) {
                        if (globalConfigListener != null) {
                            globalConfigListener.onError(new DowngradeException("\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528"));
                        }
                        int length = strArr.length;
                        while (i2 < length) {
                            DynamicMtaUtil.uploadDowngradeQueryMta(strArr[i2], dynamicStatus, null);
                            i2++;
                        }
                    } else if (TextUtils.isEmpty(engine.f1857c) || !com.jd.dynamic.lib.utils.m.L(strArr)) {
                        if (globalConfigListener != null) {
                            globalConfigListener.onError(new DynamicException("appType\u4e3a\u7a7a\u6216\u8005systemCode\u4e3a\u7a7a"));
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        Observable.from(strArr).filter(new Func1() { // from class: com.jd.dynamic.base.i0
                            @Override // rx.functions.Func1
                            public final Object call(Object obj) {
                                Boolean w;
                                w = DynamicSdk.Engine.w((String) obj);
                                return w;
                            }
                        }).forEach(new c1(arrayList), new Action1() { // from class: com.jd.dynamic.base.g0
                            @Override // rx.functions.Action1
                            public final void call(Object obj) {
                                DynamicSdk.Engine.z((Throwable) obj);
                            }
                        });
                        final int size = arrayList.size();
                        if (size == 0) {
                            int length2 = strArr.length;
                            while (i2 < length2) {
                                DynamicMtaUtil.uploadDowngradeQueryMta(strArr[i2], dynamicStatus, null);
                                i2++;
                            }
                            if (globalConfigListener != null) {
                                globalConfigListener.onError(new DowngradeException(Arrays.toString(strArr) + "\u964d\u7ea7\u4e0d\u53ef\u7528"));
                                return;
                            }
                            return;
                        }
                        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                        new AtomicInteger(size);
                        new AtomicInteger(0);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            final String str = (String) it.next();
                            final AtomicBoolean atomicBoolean2 = atomicBoolean;
                            NewDynamicFetcher.requestTemplateConfigs(engine.f1857c, str, z, new NewDynamicFetcher.GlobalConfigListener
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00bb: INVOKE 
                                  (wrap: java.lang.String : 0x00a4: IGET (r8v1 'engine' com.jd.dynamic.base.DynamicSdk$Engine) A[WRAPPED] com.jd.dynamic.base.DynamicSdk.Engine.c java.lang.String)
                                  (r15v1 'str' java.lang.String)
                                  (r18v0 'z' boolean)
                                  (wrap: com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener : 0x00b6: CONSTRUCTOR 
                                  (r16v0 'this' com.jd.dynamic.base.DynamicSdk$Engine A[IMMUTABLE_TYPE, THIS])
                                  (r15v1 'str' java.lang.String A[DONT_INLINE])
                                  (r3v1 'atomicBoolean2' java.util.concurrent.atomic.AtomicBoolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                  (r17v0 'globalConfigListener' com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener A[DONT_INLINE])
                                  (r12 I:java.util.concurrent.atomic.AtomicInteger A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                  (r13 I:java.util.concurrent.atomic.AtomicInteger A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                  (r10v0 'size' int A[DONT_INLINE])
                                 A[MD:(com.jd.dynamic.base.DynamicSdk$Engine, java.lang.String, java.util.concurrent.atomic.AtomicBoolean, com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener, java.util.concurrent.atomic.AtomicInteger, java.util.concurrent.atomic.AtomicInteger, int):void (m), WRAPPED] call: com.jd.dynamic.base.DynamicSdk.Engine.1.<init>(com.jd.dynamic.base.DynamicSdk$Engine, java.lang.String, java.util.concurrent.atomic.AtomicBoolean, com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener, java.util.concurrent.atomic.AtomicInteger, java.util.concurrent.atomic.AtomicInteger, int):void type: CONSTRUCTOR)
                                 type: STATIC call: com.jd.dynamic.base.NewDynamicFetcher.requestTemplateConfigs(java.lang.String, java.lang.String, boolean, com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener):void A[MD:(java.lang.String, java.lang.String, boolean, com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener):void (m)] in method: com.jd.dynamic.base.DynamicSdk.Engine.newFetchTemplates(com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener, boolean, java.lang.String[]):void, file: classes13.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:226)
                                	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:156)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:133)
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
                                this = this;
                                r8 = r16
                                r9 = r17
                                r0 = r19
                                java.lang.String r1 = com.jd.dynamic.base.DynamicSdk.getDynamicStatus()
                                java.lang.String r2 = "0"
                                boolean r2 = android.text.TextUtils.equals(r1, r2)
                                r3 = 0
                                r4 = 0
                                if (r2 == 0) goto L2c
                                if (r9 == 0) goto L20
                                com.jd.dynamic.lib.error.DowngradeException r2 = new com.jd.dynamic.lib.error.DowngradeException
                                java.lang.String r5 = "\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528"
                                r2.<init>(r5)
                                r9.onError(r2)
                            L20:
                                int r2 = r0.length
                            L21:
                                if (r4 >= r2) goto L2b
                                r5 = r0[r4]
                                com.jd.dynamic.base.DynamicMtaUtil.uploadDowngradeQueryMta(r5, r1, r3)
                                int r4 = r4 + 1
                                goto L21
                            L2b:
                                return
                            L2c:
                                java.lang.String r2 = r8.f1857c
                                boolean r2 = android.text.TextUtils.isEmpty(r2)
                                if (r2 != 0) goto Lc3
                                boolean r2 = com.jd.dynamic.lib.utils.m.L(r19)
                                if (r2 == 0) goto Lc3
                                java.util.ArrayList r2 = new java.util.ArrayList
                                r2.<init>()
                                rx.Observable r5 = rx.Observable.from(r19)
                                com.jd.dynamic.base.i0 r6 = new rx.functions.Func1() { // from class: com.jd.dynamic.base.i0
                                    static {
                                        /*
                                            com.jd.dynamic.base.i0 r0 = new com.jd.dynamic.base.i0
                                            r0.<init>()
                                            
                                            // error: 0x0005: SPUT (r0 I:com.jd.dynamic.base.i0) com.jd.dynamic.base.i0.g com.jd.dynamic.base.i0
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.i0.<clinit>():void");
                                    }

                                    {
                                        /*
                                            r0 = this;
                                            r0.<init>()
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.i0.<init>():void");
                                    }

                                    @Override // rx.functions.Func1
                                    public final java.lang.Object call(java.lang.Object r1) {
                                        /*
                                            r0 = this;
                                            java.lang.String r1 = (java.lang.String) r1
                                            java.lang.Boolean r1 = com.jd.dynamic.base.DynamicSdk.Engine.L(r1)
                                            return r1
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.i0.call(java.lang.Object):java.lang.Object");
                                    }
                                }
                                rx.Observable r5 = r5.filter(r6)
                                com.jd.dynamic.base.c1 r6 = new com.jd.dynamic.base.c1
                                r6.<init>(r2)
                                com.jd.dynamic.base.g0 r7 = new rx.functions.Action1() { // from class: com.jd.dynamic.base.g0
                                    static {
                                        /*
                                            com.jd.dynamic.base.g0 r0 = new com.jd.dynamic.base.g0
                                            r0.<init>()
                                            
                                            // error: 0x0005: SPUT (r0 I:com.jd.dynamic.base.g0) com.jd.dynamic.base.g0.g com.jd.dynamic.base.g0
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.g0.<clinit>():void");
                                    }

                                    {
                                        /*
                                            r0 = this;
                                            r0.<init>()
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.g0.<init>():void");
                                    }

                                    @Override // rx.functions.Action1
                                    public final void call(java.lang.Object r1) {
                                        /*
                                            r0 = this;
                                            java.lang.Throwable r1 = (java.lang.Throwable) r1
                                            com.jd.dynamic.base.DynamicSdk.Engine.J(r1)
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.g0.call(java.lang.Object):void");
                                    }
                                }
                                r5.forEach(r6, r7)
                                int r10 = r2.size()
                                if (r10 != 0) goto L84
                                int r2 = r0.length
                            L5a:
                                if (r4 >= r2) goto L64
                                r5 = r0[r4]
                                com.jd.dynamic.base.DynamicMtaUtil.uploadDowngradeQueryMta(r5, r1, r3)
                                int r4 = r4 + 1
                                goto L5a
                            L64:
                                if (r9 == 0) goto L83
                                com.jd.dynamic.lib.error.DowngradeException r1 = new com.jd.dynamic.lib.error.DowngradeException
                                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                                r2.<init>()
                                java.lang.String r0 = java.util.Arrays.toString(r19)
                                r2.append(r0)
                                java.lang.String r0 = "\u964d\u7ea7\u4e0d\u53ef\u7528"
                                r2.append(r0)
                                java.lang.String r0 = r2.toString()
                                r1.<init>(r0)
                                r9.onError(r1)
                            L83:
                                return
                            L84:
                                java.util.concurrent.atomic.AtomicBoolean r11 = new java.util.concurrent.atomic.AtomicBoolean
                                r11.<init>(r4)
                                java.util.concurrent.atomic.AtomicInteger r12 = new java.util.concurrent.atomic.AtomicInteger
                                r12.<init>(r10)
                                java.util.concurrent.atomic.AtomicInteger r13 = new java.util.concurrent.atomic.AtomicInteger
                                r13.<init>(r4)
                                java.util.Iterator r14 = r2.iterator()
                            L97:
                                boolean r0 = r14.hasNext()
                                if (r0 == 0) goto Lcf
                                java.lang.Object r0 = r14.next()
                                r15 = r0
                                java.lang.String r15 = (java.lang.String) r15
                                java.lang.String r7 = r8.f1857c
                                com.jd.dynamic.base.DynamicSdk$Engine$1 r6 = new com.jd.dynamic.base.DynamicSdk$Engine$1
                                r0 = r6
                                r1 = r16
                                r2 = r15
                                r3 = r11
                                r4 = r17
                                r5 = r12
                                r8 = r6
                                r6 = r13
                                r19 = r11
                                r11 = r7
                                r7 = r10
                                r0.<init>(r1)
                                r0 = r18
                                com.jd.dynamic.base.NewDynamicFetcher.requestTemplateConfigs(r11, r15, r0, r8)
                                r8 = r16
                                r11 = r19
                                goto L97
                            Lc3:
                                if (r9 == 0) goto Lcf
                                com.jd.dynamic.lib.error.DynamicException r0 = new com.jd.dynamic.lib.error.DynamicException
                                java.lang.String r1 = "appType\u4e3a\u7a7a\u6216\u8005systemCode\u4e3a\u7a7a"
                                r0.<init>(r1)
                                r9.onError(r0)
                            Lcf:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicSdk.Engine.newFetchTemplates(com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener, boolean, java.lang.String[]):void");
                        }

                        public boolean newHasCachedTempFile(String str, String str2) {
                            String dynamicStatus = DynamicSdk.getDynamicStatus();
                            if (com.jd.dynamic.b.a.b.o().N() && com.jd.dynamic.b.i.a.e(str, str2)) {
                                return true;
                            }
                            if (TextUtils.equals(dynamicStatus, "0")) {
                                DynamicMtaUtil.uploadDowngradeCacheMta(str, str2, dynamicStatus, null, null);
                                return false;
                            }
                            boolean t = com.jd.dynamic.b.e.a.b.t(str, str2);
                            DynamicMtaUtil.displayTemplateMta(str, str2, t, true);
                            com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str);
                            boolean equals = TextUtils.equals(g2.b, "0");
                            boolean equals2 = TextUtils.equals(g2.b, "2");
                            if (equals) {
                                boolean z = com.jd.dynamic.lib.utils.m.I(g2.f1728c) && g2.f1728c.contains(str2);
                                r2 = com.jd.dynamic.lib.utils.m.I(g2.f1729e) && g2.f1729e.contains(str2);
                                if (!z && !r2) {
                                    DynamicMtaUtil.uploadDowngradeCacheMta(str, str2, dynamicStatus, "0", "0");
                                    return false;
                                }
                                DynamicMtaUtil.uploadDowngradeCacheMta(str, str2, dynamicStatus, "0", z ? "1" : "2");
                            } else if (equals2) {
                                boolean z2 = com.jd.dynamic.lib.utils.m.I(g2.f1728c) && g2.f1728c.contains(str2);
                                if (com.jd.dynamic.lib.utils.m.I(g2.d) && g2.d.contains(str2)) {
                                    return false;
                                }
                                if (!z2) {
                                    if (!t && !com.jd.dynamic.b.i.a.e(str, str2)) {
                                        r2 = false;
                                    }
                                    return r2;
                                }
                            }
                            return t;
                        }

                        public synchronized void notifyDarkStatus(final boolean z) {
                            synchronized (this.z) {
                                List<IDarkChangeListener> list = this.u;
                                if (list != null && !list.isEmpty()) {
                                    Observable.from(this.u).forEach(new Action1() { // from class: com.jd.dynamic.base.f0
                                        @Override // rx.functions.Action1
                                        public final void call(Object obj) {
                                            DynamicSdk.Engine.t(z, (IDarkChangeListener) obj);
                                        }
                                    }, new Action1() { // from class: com.jd.dynamic.base.j0
                                        @Override // rx.functions.Action1
                                        public final void call(Object obj) {
                                            DynamicSdk.Engine.D((Throwable) obj);
                                        }
                                    });
                                }
                            }
                        }

                        public void releaseCache(String str) {
                            this.globalCache.remove(str);
                            releaseCacheEngine(str);
                        }

                        public void releaseCacheEngine(String str) {
                            ArrayList<DynamicTemplateEngine> remove = this.templateEngineCache.remove(str);
                            if (com.jd.dynamic.lib.utils.m.I(remove)) {
                                Iterator<DynamicTemplateEngine> it = remove.iterator();
                                while (it.hasNext()) {
                                    it.next().release();
                                }
                                remove.clear();
                            }
                        }

                        public void releaseCacheEngineWithPrefix(String str) {
                            if (com.jd.dynamic.lib.utils.m.J(this.templateEngineCache)) {
                                Iterator it = new ArrayList(this.templateEngineCache.keySet()).iterator();
                                while (it.hasNext()) {
                                    String str2 = (String) it.next();
                                    if (str2.contains(str)) {
                                        ArrayList<DynamicTemplateEngine> remove = this.templateEngineCache.remove(str2);
                                        if (com.jd.dynamic.lib.utils.m.I(remove)) {
                                            Iterator<DynamicTemplateEngine> it2 = remove.iterator();
                                            while (it2.hasNext()) {
                                                it2.next().release();
                                            }
                                            remove.clear();
                                        }
                                    }
                                }
                            }
                        }

                        public void releaseCacheWithPrefix(String str) {
                            if (com.jd.dynamic.lib.utils.m.J(this.globalCache)) {
                                Iterator it = new ArrayList(this.globalCache.keySet()).iterator();
                                while (it.hasNext()) {
                                    String str2 = (String) it.next();
                                    if (str2.contains(str)) {
                                        this.globalCache.remove(str2);
                                    }
                                }
                            }
                            releaseCacheEngineWithPrefix(str);
                        }

                        public void removeDarkChangeListener(IDarkChangeListener iDarkChangeListener) {
                            synchronized (this.z) {
                                List<IDarkChangeListener> list = this.u;
                                if (list != null) {
                                    list.remove(iDarkChangeListener);
                                }
                            }
                        }

                        public void setLocalResourcePath(String str) {
                            this.w = str;
                        }

                        public boolean useMta() {
                            return this.f1862i;
                        }
                    }

                    private DynamicSdk() {
                    }

                    public static IDynamicDriver getDriver() {
                        return getEngine().x;
                    }

                    public static String getDynamicStatus() {
                        String L = com.jd.dynamic.b.a.b.o().L();
                        sDynamicStatus = L;
                        return L;
                    }

                    public static Engine getEngine() {
                        return sEngine;
                    }

                    public static void handException(IExceptionHandler.DynamicExceptionData dynamicExceptionData) {
                        if (getEngine() == null || getEngine().v == null || dynamicExceptionData == null) {
                            return;
                        }
                        DynamicMtaUtil.addExceptionMta(dynamicExceptionData.systemCode, dynamicExceptionData.bizField, dynamicExceptionData.type, "" + dynamicExceptionData.httpRespCode);
                        dynamicExceptionData.systemCode = getEngine().getAppType() + "-" + dynamicExceptionData.systemCode;
                        getEngine().v.handException(dynamicExceptionData);
                    }

                    public static void handException(String str, String str2, String str3, String str4, int i2, Exception exc) {
                        handException(str, str2, str3, str4, i2, exc, null);
                    }

                    public static void handException(String str, String str2, String str3, String str4, int i2, Exception exc, JSONObject jSONObject) {
                        handException(new IExceptionHandler.DynamicExceptionData(str, str2, str3, str4, i2, exc, jSONObject));
                    }

                    public static void handException(String str, String str2, String str3, String str4, Exception exc) {
                        handException(str, str2, str3, str4, exc, (JSONObject) null);
                    }

                    public static void handException(String str, String str2, String str3, String str4, Exception exc, JSONObject jSONObject) {
                        handException(new IExceptionHandler.DynamicExceptionData(str, str2, str3, str4, exc, jSONObject));
                    }

                    public static void init(Engine engine) {
                        if (sEngine == null) {
                            sEngine = engine;
                            engine.r();
                        }
                        com.jd.dynamic.b.d.a.a();
                        ((Application) sEngine.getContext().getApplicationContext()).registerComponentCallbacks(new ComponentCallbacks() { // from class: com.jd.dynamic.base.DynamicSdk.1
                            @Override // android.content.ComponentCallbacks
                            public void onConfigurationChanged(@NonNull Configuration configuration) {
                                DynamicSdk.sEngine.r = configuration;
                            }

                            @Override // android.content.ComponentCallbacks
                            public void onLowMemory() {
                            }
                        });
                        com.jd.dynamic.b.k.a.b().d();
                    }

                    public static Engine.Builder newBuilder(Context context) {
                        return new Engine.Builder().withContext(context);
                    }

                    public static Dialog openDialog(Context context, IAppRouter.DialogConfig dialogConfig) {
                        IAppRouter appRouter = getEngine().getAppRouter();
                        if (appRouter != null) {
                            return appRouter.showDialog(context, dialogConfig);
                        }
                        return null;
                    }

                    public static Dialog openPopView(Context context, IAppRouter.PopViewConfig popViewConfig) {
                        IAppRouter appRouter = getEngine().getAppRouter();
                        if (appRouter != null) {
                            return appRouter.showPopView(context, popViewConfig);
                        }
                        return null;
                    }
                }
