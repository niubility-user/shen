package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JDReactJSLoadingModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeLoadingModule extends ReactContextBaseJavaModule {
    public JDReactNativeLoadingModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactJSLoaderModule";
    }

    @ReactMethod
    public void load(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        if (readableMap.hasKey("bundleName") && readableMap.hasKey("mouldeName")) {
            readableMap.getString("bundleName");
            readableMap.getString("mouldeName");
            boolean z = readableMap.hasKey("sync") && readableMap.getBoolean("sync");
            String string = readableMap.hasKey("md5") ? readableMap.getString("md5") : "";
            String string2 = readableMap.hasKey("version") ? readableMap.getString("version") : "";
            final CatalystInstance catalystInstance = getReactApplicationContext().getCatalystInstance();
            if (catalystInstance != null && (catalystInstance instanceof CatalystInstanceImpl)) {
                Activity currentMyActivity = getCurrentActivity() == null ? JDReactHelper.newInstance().getCurrentMyActivity() : getCurrentActivity();
                if (currentMyActivity != null) {
                    final HashMap hashMap = new HashMap();
                    hashMap.put("md5", string);
                    hashMap.put("version", string2);
                    final boolean z2 = z;
                    final String str = string2;
                    final String str2 = string;
                    currentMyActivity.runOnUiThread(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0091: INVOKE 
                          (r13v0 'currentMyActivity' android.app.Activity)
                          (wrap: java.lang.Runnable : 0x008e: CONSTRUCTOR 
                          (r16v0 'this' com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule A[IMMUTABLE_TYPE, THIS])
                          (r12v1 'catalystInstance' com.facebook.react.bridge.CatalystInstance A[DONT_INLINE])
                          (r7 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r6 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r5v1 'z2' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r14v0 'hashMap' java.util.HashMap A[DONT_INLINE])
                          (r18v0 'callback' com.facebook.react.bridge.Callback A[DONT_INLINE])
                          (r8v2 'str' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r9v2 'str2' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r19v0 'callback2' com.facebook.react.bridge.Callback A[DONT_INLINE])
                         A[MD:(com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule, com.facebook.react.bridge.CatalystInstance, java.lang.String, java.lang.String, boolean, java.util.HashMap, com.facebook.react.bridge.Callback, java.lang.String, java.lang.String, com.facebook.react.bridge.Callback):void (m), WRAPPED] (LINE:13) call: com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule.1.<init>(com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule, com.facebook.react.bridge.CatalystInstance, java.lang.String, java.lang.String, boolean, java.util.HashMap, com.facebook.react.bridge.Callback, java.lang.String, java.lang.String, com.facebook.react.bridge.Callback):void type: CONSTRUCTOR)
                         type: VIRTUAL call: android.app.Activity.runOnUiThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] (LINE:13) in method: com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule.load(com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Callback, com.facebook.react.bridge.Callback):void, file: classes5.dex
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
                        	... 39 more
                        */
                    /*
                        this = this;
                        r0 = r17
                        r10 = r19
                        java.lang.String r1 = "bundleName"
                        boolean r2 = r0.hasKey(r1)
                        java.lang.String r3 = "code"
                        r4 = 0
                        r5 = 1
                        if (r2 == 0) goto Lb4
                        java.lang.String r2 = "mouldeName"
                        boolean r6 = r0.hasKey(r2)
                        if (r6 == 0) goto Lb4
                        java.lang.String r6 = r0.getString(r1)
                        java.lang.String r7 = r0.getString(r2)
                        java.lang.String r1 = "sync"
                        boolean r2 = r0.hasKey(r1)
                        if (r2 == 0) goto L30
                        boolean r1 = r0.getBoolean(r1)
                        if (r1 == 0) goto L30
                        r8 = 1
                        goto L31
                    L30:
                        r8 = 0
                    L31:
                        java.lang.String r1 = "md5"
                        boolean r2 = r0.hasKey(r1)
                        java.lang.String r9 = ""
                        if (r2 == 0) goto L41
                        java.lang.String r2 = r0.getString(r1)
                        r11 = r2
                        goto L42
                    L41:
                        r11 = r9
                    L42:
                        java.lang.String r2 = "version"
                        boolean r12 = r0.hasKey(r2)
                        if (r12 == 0) goto L4f
                        java.lang.String r0 = r0.getString(r2)
                        r9 = r0
                    L4f:
                        com.facebook.react.bridge.ReactApplicationContext r0 = r16.getReactApplicationContext()
                        com.facebook.react.bridge.CatalystInstance r12 = r0.getCatalystInstance()
                        if (r12 == 0) goto La4
                        boolean r0 = r12 instanceof com.facebook.react.bridge.CatalystInstanceImpl
                        if (r0 == 0) goto La4
                        android.app.Activity r0 = r16.getCurrentActivity()
                        if (r0 != 0) goto L6c
                        com.jingdong.common.jdreactFramework.JDReactHelper r0 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
                        android.app.Activity r0 = r0.getCurrentMyActivity()
                        goto L70
                    L6c:
                        android.app.Activity r0 = r16.getCurrentActivity()
                    L70:
                        r13 = r0
                        if (r13 == 0) goto L95
                        java.util.HashMap r14 = new java.util.HashMap
                        r14.<init>()
                        r14.put(r1, r11)
                        r14.put(r2, r9)
                        com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule$1 r15 = new com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule$1
                        r0 = r15
                        r1 = r16
                        r2 = r12
                        r3 = r7
                        r4 = r6
                        r5 = r8
                        r6 = r14
                        r7 = r18
                        r8 = r9
                        r9 = r11
                        r10 = r19
                        r0.<init>()
                        r13.runOnUiThread(r15)
                        goto Lc3
                    L95:
                        com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()
                        r0.putInt(r3, r5)
                        java.lang.Object[] r1 = new java.lang.Object[r5]
                        r1[r4] = r0
                        r10.invoke(r1)
                        goto Lc3
                    La4:
                        com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()
                        r1 = 2
                        r0.putInt(r3, r1)
                        java.lang.Object[] r1 = new java.lang.Object[r5]
                        r1[r4] = r0
                        r10.invoke(r1)
                        goto Lc3
                    Lb4:
                        com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()
                        r1 = 3
                        r0.putInt(r3, r1)
                        java.lang.Object[] r1 = new java.lang.Object[r5]
                        r1[r4] = r0
                        r10.invoke(r1)
                    Lc3:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.modules.JDReactNativeLoadingModule.load(com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Callback, com.facebook.react.bridge.Callback):void");
                }

                @ReactMethod
                public void loadAsString(ReadableMap readableMap, Callback callback, Callback callback2) {
                    if (readableMap.hasKey("bundleName") && readableMap.hasKey("mouldeName")) {
                        String string = readableMap.getString("bundleName");
                        String string2 = readableMap.getString("mouldeName");
                        CatalystInstance catalystInstance = getReactApplicationContext().getCatalystInstance();
                        if (catalystInstance != null && (catalystInstance instanceof CatalystInstanceImpl)) {
                            String loadJSModuleAsString = JDReactJSLoadingModule.loadJSModuleAsString(getReactApplicationContext(), (CatalystInstanceImpl) catalystInstance, string2, string);
                            if (!TextUtils.isEmpty(loadJSModuleAsString)) {
                                callback.invoke(loadJSModuleAsString);
                                return;
                            } else {
                                callback2.invoke(new Object[0]);
                                return;
                            }
                        }
                        callback2.invoke(new Object[0]);
                        return;
                    }
                    callback2.invoke(new Object[0]);
                }
            }
