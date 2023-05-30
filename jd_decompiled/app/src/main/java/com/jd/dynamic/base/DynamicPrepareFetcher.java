package com.jd.dynamic.base;

import android.text.TextUtils;
import com.jd.dynamic.base.NewDynamicFetcher;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class DynamicPrepareFetcher {
    public static final String KEY_PREPARE_INTERVAL = "interval";
    public static final String KEY_PREPARE_Last_FETCH_TIME = "lastFetchTime";
    public static final String KEY_PREPARE_MODE = "mode";
    public static final String KEY_PREPARE_MODEL_FOREGROUND = "foreground";
    public static final String KEY_PREPARE_MODEL_LAUNCH = "launch";
    public static final String KEY_PREPARE_MODULE = "module";
    public static final String KEY_PREPARE_MODULES = "modules";
    private static final AtomicInteger a = new AtomicInteger(0);
    private static long b = 0;

    public static void prepareFetch(String str) {
        String str2 = str;
        if (com.jd.dynamic.b.a.b.o().f() && a.get() <= 0) {
            int i2 = 1;
            char c2 = 0;
            com.jd.dynamic.lib.utils.t.e("prepareFetch : " + str2);
            long j2 = 3600;
            try {
                j2 = Long.parseLong(com.jd.dynamic.b.a.b.o().u("autoPrepareModules", "interval"));
            } catch (NumberFormatException unused) {
            }
            long j3 = j2 * 1000;
            final long currentTimeMillis = System.currentTimeMillis();
            com.jd.dynamic.lib.utils.t.g("prepareFetch intervalTime : " + j3);
            com.jd.dynamic.lib.utils.t.g("prepareFetch lastFetchTime : " + b);
            com.jd.dynamic.lib.utils.t.g("prepareFetch currentTimeMillis : " + currentTimeMillis);
            if (currentTimeMillis - b > j3) {
                String u = com.jd.dynamic.b.a.b.o().u("autoPrepareModules", KEY_PREPARE_MODULES);
                if (TextUtils.isEmpty(u)) {
                    return;
                }
                try {
                    JSONArray jSONArray = new JSONArray(u);
                    final int length = jSONArray.length();
                    if (length > 0) {
                        a.set(length);
                        new AtomicBoolean(false);
                        final AtomicInteger atomicInteger = new AtomicInteger();
                        int i3 = 0;
                        while (i3 < length) {
                            JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                            String optString = optJSONObject.optString("module");
                            String optString2 = optJSONObject.optString("mode");
                            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || !optString2.contains(str2)) {
                                a.decrementAndGet();
                                atomicInteger.incrementAndGet();
                            } else {
                                Object[] objArr = new Object[i2];
                                objArr[c2] = "module : " + optString + " , modeArray : " + optString2;
                                com.jd.dynamic.lib.utils.t.g(objArr);
                                NewDynamicFetcher.requestTemplateConfigs(DynamicSdk.getEngine().getAppType(), optString, true, new NewDynamicFetcher.GlobalConfigListener
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0125: INVOKE 
                                      (wrap: java.lang.String : 0x0113: INVOKE 
                                      (wrap: com.jd.dynamic.base.DynamicSdk$Engine : 0x010f: INVOKE  type: STATIC call: com.jd.dynamic.base.DynamicSdk.getEngine():com.jd.dynamic.base.DynamicSdk$Engine A[MD:():com.jd.dynamic.base.DynamicSdk$Engine (m), WRAPPED])
                                     type: VIRTUAL call: com.jd.dynamic.base.DynamicSdk.Engine.getAppType():java.lang.String A[MD:():java.lang.String (m), WRAPPED])
                                      (r11v0 'optString' java.lang.String)
                                      true
                                      (wrap: com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener : 0x0121: CONSTRUCTOR 
                                      (r6v0 'atomicInteger' java.util.concurrent.atomic.AtomicInteger A[DONT_INLINE])
                                      (r2v9 'length' int A[DONT_INLINE])
                                      (r5 I:java.util.concurrent.atomic.AtomicBoolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                      (r13v0 'currentTimeMillis' long A[DONT_INLINE])
                                     A[MD:(java.util.concurrent.atomic.AtomicInteger, int, java.util.concurrent.atomic.AtomicBoolean, long):void (m), WRAPPED] call: com.jd.dynamic.base.DynamicPrepareFetcher.1.<init>(java.util.concurrent.atomic.AtomicInteger, int, java.util.concurrent.atomic.AtomicBoolean, long):void type: CONSTRUCTOR)
                                     type: STATIC call: com.jd.dynamic.base.NewDynamicFetcher.requestTemplateConfigs(java.lang.String, java.lang.String, boolean, com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener):void A[MD:(java.lang.String, java.lang.String, boolean, com.jd.dynamic.base.NewDynamicFetcher$GlobalConfigListener):void (m)] in method: com.jd.dynamic.base.DynamicPrepareFetcher.prepareFetch(java.lang.String):void, file: classes13.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:226)
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
                                    Method dump skipped, instructions count: 318
                                    To view this dump change 'Code comments level' option to 'DEBUG'
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicPrepareFetcher.prepareFetch(java.lang.String):void");
                            }
                        }
