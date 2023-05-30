package com.jdjr.risk.device.a;

import android.content.Context;
import com.jdjr.risk.assist.info.BuildConfig;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.biometric.core.e;
import com.jdjr.risk.device.b.g;
import com.jdjr.risk.util.a.f;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b {
    private static volatile b a;
    private ExecutorService b = null;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, Object> f7320c;
    private ReentrantReadWriteLock d;

    private b() {
        b();
        this.f7320c = new HashMap();
        this.d = new ReentrantReadWriteLock();
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    private void a(Context context, String str, String str2, com.jdjr.risk.biometric.a.a aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis();
            LinkedHashMap<String, Object> a2 = new g().a(context, aVar);
            long currentTimeMillis2 = System.currentTimeMillis();
            JSONObject jSONObject2 = new JSONObject(a2);
            jSONObject2.put("isGuest", e.a());
            jSONObject.put("deviceInfo", jSONObject2);
            jSONObject.put("sdk_version", BuildConfig.BIOVersionName);
            jSONObject.put("integrityData", "");
            com.jdjr.risk.biometric.c.b.a(jSONObject, context.getPackageName(), str, String.valueOf(currentTimeMillis), String.valueOf(currentTimeMillis2), str2);
            String b = BiometricManager.getInstance().a().b(context);
            String e2 = BiometricManager.getInstance().a().e(context);
            jSONObject.put("token", b);
            jSONObject.put("cuid", e2);
            com.jdjr.risk.biometric.c.d.a(context, com.jdjr.risk.util.httputil.a.f(), jSONObject, str);
        } catch (JSONException unused) {
        }
    }

    private boolean a(Context context, String str) {
        boolean z;
        long j2 = com.jdjr.risk.util.a.d.a(context).getLong("isolateTime", 10000L);
        this.d.readLock().lock();
        try {
            r3 = this.f7320c.containsKey(str) ? ((Long) this.f7320c.get(str)).longValue() : 0L;
            z = this.f7320c.containsKey("agreedPrivacy") ? ((Boolean) this.f7320c.get("agreedPrivacy")).booleanValue() : true;
            this.d.readLock().unlock();
        } catch (Throwable unused) {
            this.d.readLock().unlock();
            z = true;
        }
        boolean z2 = e.a.get().getBoolean("agreedPrivacy");
        try {
        } catch (Throwable unused2) {
            this.d.writeLock().unlock();
        }
        if (!z2 || z) {
            if (System.currentTimeMillis() > r3 + j2) {
                this.d.writeLock().lock();
                this.f7320c.put(str, Long.valueOf(System.currentTimeMillis()));
                this.f7320c.put("agreedPrivacy", Boolean.valueOf(z2));
            }
            return false;
        }
        this.d.writeLock().lock();
        this.f7320c.put(str, Long.valueOf(System.currentTimeMillis()));
        this.f7320c.put("agreedPrivacy", Boolean.valueOf(z2));
        this.d.writeLock().unlock();
        return true;
    }

    private void b() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 3L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new f(), new ThreadPoolExecutor.DiscardPolicy());
        this.b = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str, String str2) {
        try {
            com.jdjr.risk.biometric.a.a a2 = com.jdjr.risk.biometric.c.c.a(context, str, str2);
            if (a2 == null || !a2.a()) {
                return;
            }
            a(context, str, str2, a2);
            if (a2.H()) {
                c(context, str, str2);
            }
            if (BaseInfo.isAgreedPrivacy() && a2.F()) {
                b(context, str, str2, a2);
            }
            if (BaseInfo.isAgreedPrivacy() && a2.b()) {
                c(context, str, str2, a2);
            }
        } catch (Throwable unused) {
        }
    }

    private void b(Context context, String str, String str2, com.jdjr.risk.biometric.a.a aVar) {
        a.a(context, str, str2, aVar);
    }

    private void c(Context context, String str, String str2) {
        c.a(context, str, str2);
    }

    private void c(Context context, String str, String str2, com.jdjr.risk.biometric.a.a aVar) {
        d.a(context, str, str2, aVar);
    }

    public void a(final Context context, final String str, final String str2) {
        if (context != null) {
            try {
                if (a(context, str)) {
                    e.a.get();
                    this.b.execute(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: INVOKE 
                          (wrap: java.util.concurrent.ExecutorService : 0x0011: IGET (r8v0 'this' com.jdjr.risk.device.a.b A[IMMUTABLE_TYPE, THIS]) A[Catch: all -> 0x0020, WRAPPED] com.jdjr.risk.device.a.b.b java.util.concurrent.ExecutorService)
                          (wrap: java.lang.Runnable : 0x001a: CONSTRUCTOR 
                          (r8v0 'this' com.jdjr.risk.device.a.b A[IMMUTABLE_TYPE, THIS])
                          (r3 I:android.os.Bundle A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r9v0 'context' android.content.Context A[DONT_INLINE])
                          (r10v0 'str' java.lang.String A[DONT_INLINE])
                          (r11v0 'str2' java.lang.String A[DONT_INLINE])
                         A[Catch: all -> 0x0020, MD:(com.jdjr.risk.device.a.b, android.os.Bundle, android.content.Context, java.lang.String, java.lang.String):void (m), WRAPPED] call: com.jdjr.risk.device.a.b.1.<init>(com.jdjr.risk.device.a.b, android.os.Bundle, android.content.Context, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
                         type: INTERFACE call: java.util.concurrent.ExecutorService.execute(java.lang.Runnable):void A[Catch: all -> 0x0020, MD:(java.lang.Runnable):void (c), TRY_LEAVE] in method: com.jdjr.risk.device.a.b.a(android.content.Context, java.lang.String, java.lang.String):void, file: classes18.dex
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
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
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
                        if (r9 == 0) goto L20
                        boolean r0 = r8.a(r9, r10)     // Catch: java.lang.Throwable -> L20
                        if (r0 == 0) goto L20
                        java.lang.ThreadLocal<android.os.Bundle> r0 = com.jdjr.risk.biometric.core.e.a     // Catch: java.lang.Throwable -> L20
                        java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L20
                        r3 = r0
                        android.os.Bundle r3 = (android.os.Bundle) r3     // Catch: java.lang.Throwable -> L20
                        java.util.concurrent.ExecutorService r0 = r8.b     // Catch: java.lang.Throwable -> L20
                        com.jdjr.risk.device.a.b$1 r7 = new com.jdjr.risk.device.a.b$1     // Catch: java.lang.Throwable -> L20
                        r1 = r7
                        r2 = r8
                        r4 = r9
                        r5 = r10
                        r6 = r11
                        r1.<init>()     // Catch: java.lang.Throwable -> L20
                        r0.execute(r7)     // Catch: java.lang.Throwable -> L20
                    L20:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.a.b.a(android.content.Context, java.lang.String, java.lang.String):void");
                }
            }
