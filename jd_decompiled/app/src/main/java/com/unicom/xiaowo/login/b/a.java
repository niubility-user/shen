package com.unicom.xiaowo.login.b;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import com.unicom.xiaowo.login.ResultListener;
import com.unicom.xiaowo.login.d.b;
import com.unicom.xiaowo.login.d.c;
import com.unicom.xiaowo.login.d.f;
import com.unicom.xiaowo.login.d.g;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private b f18109c;
    private String d;
    private ExecutorService b = Executors.newSingleThreadExecutor();
    private ScheduledExecutorService a = Executors.newScheduledThreadPool(1);

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> b() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        hashMap.put("system", Build.VERSION.RELEASE);
        hashMap.put("woodcock", f.i());
        return hashMap;
    }

    public void a(Context context, int i2, int i3, ResultListener resultListener) {
        b bVar = new b();
        this.f18109c = bVar;
        bVar.a(resultListener);
        try {
            this.a.schedule(new Runnable() { // from class: com.unicom.xiaowo.login.b.a.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (a.this) {
                        if (a.this.f18109c != null) {
                            a.this.f18109c.a(10000, "\u8bf7\u6c42\u8d85\u65f6");
                            a.this.f18109c = null;
                            a.this.a();
                        }
                    }
                }
            }, i2, TimeUnit.MILLISECONDS);
            a(context, i3);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            ScheduledExecutorService scheduledExecutorService = this.a;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
                this.a = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(Context context, int i2) {
        this.d = com.unicom.xiaowo.login.a.a.a();
        a(context, i2, new com.unicom.xiaowo.login.c.a() { // from class: com.unicom.xiaowo.login.b.a.2
            @Override // com.unicom.xiaowo.login.c.a
            public void a(int i3, String str) {
                synchronized (a.this) {
                    if (a.this.f18109c == null) {
                        return;
                    }
                    if (i3 != 1) {
                        a.this.f18109c.a(i3, str);
                    } else {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("code");
                            String optString = jSONObject.optString("msg");
                            String optString2 = jSONObject.optString("data");
                            if (optInt == 0) {
                                a.this.f18109c.a(optString, URLDecoder.decode(com.unicom.xiaowo.login.a.a.a(optString2, a.this.d), "UTF-8"));
                            } else {
                                a.this.f18109c.a(optInt, optString, optString2);
                            }
                        } catch (Exception e2) {
                            b bVar = a.this.f18109c;
                            StringBuilder sb = new StringBuilder();
                            sb.append("\u5f02\u5e38");
                            sb.append(e2.getMessage());
                            bVar.a(10002, sb.toString(), str);
                        }
                    }
                    a.this.f18109c = null;
                    a.this.a();
                }
            }
        });
    }

    private String a(Context context, int i2, String str) {
        try {
            String packageName = context.getPackageName();
            String b = g.b(context, context.getPackageName());
            if (packageName == null) {
                packageName = "";
            }
            if (b == null) {
                b = "";
            }
            String a = f.a();
            String str2 = i2 != 2 ? "1" : "";
            String str3 = "" + System.currentTimeMillis();
            String a2 = com.unicom.xiaowo.login.a.b.a(g.c(context).getBytes());
            String d = g.d(str);
            String a3 = g.a(str2 + a + "30100jsonp" + a2 + d + packageName + b + str3 + "5.1.1AR02B0825" + f.b());
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.PARAM_CLIENT_ID, a);
            jSONObject.put("client_type", "30100");
            jSONObject.put("format", com.unicom.xiaowo.login.a.b.a("jsonp"));
            jSONObject.put("version", com.unicom.xiaowo.login.a.b.a("5.1.1AR02B0825"));
            if (i2 != 2) {
                jSONObject.put("business_type", com.unicom.xiaowo.login.a.b.a(str2));
            }
            jSONObject.put("packname", com.unicom.xiaowo.login.a.b.a(packageName));
            jSONObject.put("packsign", com.unicom.xiaowo.login.a.b.a(b));
            jSONObject.put("timeStamp", com.unicom.xiaowo.login.a.b.a(str3));
            jSONObject.put("key", com.unicom.xiaowo.login.a.b.a(d));
            jSONObject.put(WebPerfManager.FP, com.unicom.xiaowo.login.a.b.a(a2));
            jSONObject.put("sign", com.unicom.xiaowo.login.a.b.a(a3));
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private void a(final Context context, final int i2, final com.unicom.xiaowo.login.c.a aVar) {
        try {
            int a = g.a(context.getApplicationContext());
            f.b(a);
            if (a == 1) {
                com.unicom.xiaowo.login.d.b.a().a(context, "https://opencloud.wostore.cn/openapi/netauth/precheck/wp?", new b.a() { // from class: com.unicom.xiaowo.login.b.a.3
                    @Override // com.unicom.xiaowo.login.d.b.a
                    public void a(boolean z, Network network) {
                        if (!z) {
                            aVar.a(10003, "\u65e0\u6cd5\u5207\u6362\u81f3\u6570\u636e\u7f51\u7edc");
                        } else {
                            a.this.a(context, i2, "https://opencloud.wostore.cn/openapi/netauth/precheck/wp?", network, aVar);
                        }
                    }
                });
            } else if (a == 0) {
                a(context, i2, "https://opencloud.wostore.cn/openapi/netauth/precheck/wp?", null, aVar);
            } else {
                aVar.a(10004, "\u6570\u636e\u7f51\u7edc\u672a\u5f00\u542f");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            aVar.a(10005, "\u7f51\u7edc\u5224\u65ad\u5f02\u5e38" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, int i2, String str, final Network network, final com.unicom.xiaowo.login.c.a aVar) {
        synchronized (this) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(c.a(a(context, i2, this.d), ContainerUtils.FIELD_DELIMITER));
                sb.toString();
                this.b.submit(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0023: INVOKE 
                      (wrap: java.util.concurrent.ExecutorService : 0x001c: IGET (r1v0 'this' com.unicom.xiaowo.login.b.a A[IMMUTABLE_TYPE, THIS]) A[Catch: all -> 0x0027, Exception -> 0x0029, WRAPPED] (LINE:47) com.unicom.xiaowo.login.b.a.b java.util.concurrent.ExecutorService)
                      (wrap: java.lang.Runnable : 0x0020: CONSTRUCTOR 
                      (r1v0 'this' com.unicom.xiaowo.login.b.a A[IMMUTABLE_TYPE, THIS])
                      (r2 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v0 'network' android.net.Network A[DONT_INLINE])
                      (r6v0 'aVar' com.unicom.xiaowo.login.c.a A[DONT_INLINE])
                     A[Catch: all -> 0x0027, Exception -> 0x0029, MD:(com.unicom.xiaowo.login.b.a, java.lang.String, android.net.Network, com.unicom.xiaowo.login.c.a):void (m), WRAPPED] call: com.unicom.xiaowo.login.b.a.4.<init>(com.unicom.xiaowo.login.b.a, java.lang.String, android.net.Network, com.unicom.xiaowo.login.c.a):void type: CONSTRUCTOR)
                     type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.lang.Runnable):java.util.concurrent.Future A[Catch: all -> 0x0027, Exception -> 0x0029, MD:(java.lang.Runnable):java.util.concurrent.Future<?> (c), TRY_LEAVE] (LINE:47) in method: com.unicom.xiaowo.login.b.a.a(android.content.Context, int, java.lang.String, android.net.Network, com.unicom.xiaowo.login.c.a):void, file: classes11.dex
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
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:240)
                    	at jadx.core.dex.regions.SynchronizedRegion.generate(SynchronizedRegion.java:44)
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
                    monitor-enter(r1)
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    r0.<init>()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    r0.append(r4)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    java.lang.String r4 = r1.d     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    java.lang.String r2 = r1.a(r2, r3, r4)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    java.lang.String r3 = "&"
                    java.lang.String r2 = com.unicom.xiaowo.login.d.c.a(r2, r3)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    r0.append(r2)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    java.lang.String r2 = r0.toString()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    java.util.concurrent.ExecutorService r3 = r1.b     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    com.unicom.xiaowo.login.b.a$4 r4 = new com.unicom.xiaowo.login.b.a$4     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    r4.<init>()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    r3.submit(r4)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
                    goto L44
                L27:
                    r2 = move-exception
                    goto L46
                L29:
                    r2 = move-exception
                    r3 = 10009(0x2719, float:1.4026E-41)
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L27
                    r4.<init>()     // Catch: java.lang.Throwable -> L27
                    java.lang.String r5 = "10009"
                    r4.append(r5)     // Catch: java.lang.Throwable -> L27
                    java.lang.String r2 = r2.getMessage()     // Catch: java.lang.Throwable -> L27
                    r4.append(r2)     // Catch: java.lang.Throwable -> L27
                    java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L27
                    r6.a(r3, r2)     // Catch: java.lang.Throwable -> L27
                L44:
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L27
                    return
                L46:
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L27
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.unicom.xiaowo.login.b.a.a(android.content.Context, int, java.lang.String, android.net.Network, com.unicom.xiaowo.login.c.a):void");
            }
        }
