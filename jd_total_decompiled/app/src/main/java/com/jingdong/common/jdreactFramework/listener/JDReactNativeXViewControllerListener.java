package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.activities.OnXViewActionListener;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeXViewControllerListener implements NativeXViewControllerListener, JDFlutterCall {
    private static final String TAG = "JDReactNativeXViewControllerListener";
    public static final String XVIEWCONTROLLERCHANNEL = "com.jd.jdflutter/xViewController";

    @Override // com.jingdong.common.jdreactFramework.listener.NativeXViewControllerListener
    public void closeXView(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            final BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (baseActivity != null && (baseActivity instanceof OnXViewActionListener)) {
                baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ((OnXViewActionListener) baseActivity).closeXView();
                    }
                });
                if (jDCallback != null) {
                    jDCallback.invoke(new Object[0]);
                }
            } else if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeXViewControllerListener
    public void destroyXView(JDCallback jDCallback, JDCallback jDCallback2) {
        try {
            final BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (baseActivity != null && (baseActivity instanceof OnXViewActionListener)) {
                baseActivity.post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ((OnXViewActionListener) baseActivity).destroyXView();
                    }
                });
                if (jDCallback != null) {
                    jDCallback.invoke(new Object[0]);
                }
            } else if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("showXView")) {
            showXView(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("closeXView")) {
            closeXView(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("destroyXView")) {
            destroyXView(new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.8
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.9
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeXViewControllerListener
    public void showXView(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        try {
            final String str = (String) hashMap.get("url");
            Boolean.valueOf(!((Boolean) hashMap.get("passthrough")).booleanValue());
            final boolean containsKey = hashMap.containsKey("needAutoClose");
            final boolean booleanValue = containsKey ? ((Boolean) hashMap.get("needAutoClose")).booleanValue() : true;
            final int doubleValue = (int) ((Double) hashMap.get(VerifyTracker.KEY_TIMES)).doubleValue();
            ((Double) hashMap.get("showTimeDuration")).doubleValue();
            final BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (baseActivity != null && (baseActivity instanceof OnXViewActionListener)) {
                baseActivity.post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006e: INVOKE 
                      (r11v1 'baseActivity' com.jingdong.common.BaseActivity)
                      (wrap: java.lang.Runnable : 0x006b: CONSTRUCTOR 
                      (r16v0 'this' com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener A[IMMUTABLE_TYPE, THIS])
                      (r2v8 'containsKey' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r11v1 'baseActivity' com.jingdong.common.BaseActivity A[DONT_INLINE])
                      (r4v0 'doubleValue' int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v1 'str' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r6 I:java.lang.Boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r7 I:double A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r9v1 'booleanValue' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r18v0 'jDCallback' com.jingdong.common.jdreactFramework.JDCallback A[DONT_INLINE])
                      (r19v0 'jDCallback2' com.jingdong.common.jdreactFramework.JDCallback A[DONT_INLINE])
                     A[Catch: Exception -> 0x007e, MD:(com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener, boolean, com.jingdong.common.BaseActivity, int, java.lang.String, java.lang.Boolean, double, boolean, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void (m), WRAPPED] (LINE:9) call: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.1.<init>(com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener, boolean, com.jingdong.common.BaseActivity, int, java.lang.String, java.lang.Boolean, double, boolean, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[Catch: Exception -> 0x007e, MD:(java.lang.Runnable):void (m)] (LINE:9) in method: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.showXView(java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void, file: classes5.dex
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
                    this = this;
                    r0 = r17
                    r12 = r19
                    java.lang.String r1 = "needAutoClose"
                    java.lang.String r13 = "onError"
                    java.lang.String r2 = "url"
                    java.lang.Object r2 = r0.get(r2)     // Catch: java.lang.Exception -> L7e
                    r5 = r2
                    java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> L7e
                    java.lang.String r2 = "passthrough"
                    java.lang.Object r2 = r0.get(r2)     // Catch: java.lang.Exception -> L7e
                    java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Exception -> L7e
                    boolean r2 = r2.booleanValue()     // Catch: java.lang.Exception -> L7e
                    if (r2 != 0) goto L21
                    r2 = 1
                    goto L22
                L21:
                    r2 = 0
                L22:
                    java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Exception -> L7e
                    boolean r2 = r0.containsKey(r1)     // Catch: java.lang.Exception -> L7e
                    if (r2 == 0) goto L38
                    java.lang.Object r1 = r0.get(r1)     // Catch: java.lang.Exception -> L7e
                    java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Exception -> L7e
                    boolean r1 = r1.booleanValue()     // Catch: java.lang.Exception -> L7e
                    r9 = r1
                    goto L39
                L38:
                    r9 = 1
                L39:
                    java.lang.String r1 = "times"
                    java.lang.Object r1 = r0.get(r1)     // Catch: java.lang.Exception -> L7e
                    java.lang.Double r1 = (java.lang.Double) r1     // Catch: java.lang.Exception -> L7e
                    double r3 = r1.doubleValue()     // Catch: java.lang.Exception -> L7e
                    int r4 = (int) r3     // Catch: java.lang.Exception -> L7e
                    java.lang.String r1 = "showTimeDuration"
                    java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Exception -> L7e
                    java.lang.Double r0 = (java.lang.Double) r0     // Catch: java.lang.Exception -> L7e
                    double r7 = r0.doubleValue()     // Catch: java.lang.Exception -> L7e
                    android.app.Activity r0 = com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.getCurrentMyActivity()     // Catch: java.lang.Exception -> L7e
                    r11 = r0
                    com.jingdong.common.BaseActivity r11 = (com.jingdong.common.BaseActivity) r11     // Catch: java.lang.Exception -> L7e
                    if (r11 == 0) goto L72
                    boolean r0 = r11 instanceof com.jingdong.common.jdreactFramework.activities.OnXViewActionListener     // Catch: java.lang.Exception -> L7e
                    if (r0 == 0) goto L72
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener$1 r10 = new com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener$1     // Catch: java.lang.Exception -> L7e
                    r0 = r10
                    r1 = r16
                    r3 = r11
                    r14 = r10
                    r10 = r18
                    r15 = r11
                    r11 = r19
                    r0.<init>()     // Catch: java.lang.Exception -> L7e
                    r15.post(r14)     // Catch: java.lang.Exception -> L7e
                    goto L93
                L72:
                    if (r12 == 0) goto L93
                    r1 = 1
                    java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L7e
                    r1 = 0
                    r0[r1] = r13     // Catch: java.lang.Exception -> L7e
                    r12.invoke(r0)     // Catch: java.lang.Exception -> L7e
                    goto L93
                L7e:
                    r0 = move-exception
                    java.lang.String r1 = com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.TAG
                    java.lang.String r0 = r0.toString()
                    com.jingdong.common.jdreactFramework.utils.JLog.e(r1, r0)
                    if (r12 == 0) goto L93
                    r1 = 1
                    java.lang.Object[] r0 = new java.lang.Object[r1]
                    r1 = 0
                    r0[r1] = r13
                    r12.invoke(r0)
                L93:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeXViewControllerListener.showXView(java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void");
            }
        }
