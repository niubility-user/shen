package com.jingdong.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.utils.MacAddressListener;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class TelephoneUtils {
    private static final String TAG = "TelephoneUtils";

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static String getDeviceId() {
        return "";
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static String getDeviceId(Context context) {
        return "";
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static String getIMSI() {
        return "";
    }

    public static synchronized void getLocalMacAddress(MacAddressListener macAddressListener) {
        synchronized (TelephoneUtils.class) {
            getLocalMacAddress(macAddressListener, JdSdk.getInstance().getApplication());
        }
    }

    public static String getMacAddressStr(Context context) {
        return BaseInfo.getWifiMacAddress();
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public static String getSimSerialNumber(Context context) {
        return "";
    }

    public static String getWifiMacAddressOver23() {
        try {
            return BaseInfo.getWifiMacAddress();
        } catch (Exception unused) {
            return "";
        }
    }

    public static synchronized void getLocalMacAddress(final MacAddressListener macAddressListener, final Context context) {
        synchronized (TelephoneUtils.class) {
            try {
                if (OKLog.D) {
                    OKLog.d(TAG, "getMacAddress() -->> ");
                }
                String macAddressStr = getMacAddressStr(context);
                if (OKLog.D) {
                    OKLog.d(TAG, "getMacAddress()--->" + macAddressStr);
                }
                if (Configuration.getBooleanProperty(Configuration.MUST_USE_WIFI_MAC).booleanValue()) {
                    macAddressListener.setMacAddress(macAddressStr);
                } else {
                    new Object();
                    new Thread
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0046: INVOKE 
                          (wrap: java.lang.Thread : 0x0043: CONSTRUCTOR 
                          (r6v0 'context' android.content.Context A[DONT_INLINE])
                          (r1 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r5v0 'macAddressListener' com.jingdong.jdsdk.utils.MacAddressListener A[DONT_INLINE])
                         A[Catch: all -> 0x004a, Exception -> 0x004c, MD:(android.content.Context, java.lang.Object, com.jingdong.jdsdk.utils.MacAddressListener):void (m), WRAPPED] (LINE:11) call: com.jingdong.common.utils.TelephoneUtils.1.<init>(android.content.Context, java.lang.Object, com.jingdong.jdsdk.utils.MacAddressListener):void type: CONSTRUCTOR)
                         type: VIRTUAL call: java.lang.Thread.start():void A[Catch: all -> 0x004a, Exception -> 0x004c, MD:():void (c), TRY_LEAVE] (LINE:12) in method: com.jingdong.common.utils.TelephoneUtils.getLocalMacAddress(com.jingdong.jdsdk.utils.MacAddressListener, android.content.Context):void, file: classes6.dex
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
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:96)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:840)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 35 more
                        */
                    /*
                        java.lang.Class<com.jingdong.common.utils.TelephoneUtils> r0 = com.jingdong.common.utils.TelephoneUtils.class
                        monitor-enter(r0)
                        boolean r1 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        if (r1 == 0) goto Le
                        java.lang.String r1 = com.jingdong.common.utils.TelephoneUtils.TAG     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        java.lang.String r2 = "getMacAddress() -->> "
                        com.jingdong.sdk.oklog.OKLog.d(r1, r2)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                    Le:
                        java.lang.String r1 = getMacAddressStr(r6)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        boolean r2 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        if (r2 == 0) goto L2c
                        java.lang.String r2 = com.jingdong.common.utils.TelephoneUtils.TAG     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        r3.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        java.lang.String r4 = "getMacAddress()--->"
                        r3.append(r4)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        r3.append(r1)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        com.jingdong.sdk.oklog.OKLog.d(r2, r3)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                    L2c:
                        java.lang.String r2 = "mustUseWifiMac"
                        java.lang.Boolean r2 = com.jingdong.jdsdk.config.Configuration.getBooleanProperty(r2)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        boolean r2 = r2.booleanValue()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        if (r2 == 0) goto L3c
                        r5.setMacAddress(r1)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        goto L56
                    L3c:
                        java.lang.Object r1 = new java.lang.Object     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        r1.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        com.jingdong.common.utils.TelephoneUtils$1 r2 = new com.jingdong.common.utils.TelephoneUtils$1     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        r2.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        r2.start()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
                        goto L56
                    L4a:
                        r5 = move-exception
                        goto L58
                    L4c:
                        r6 = move-exception
                        r1 = 0
                        r5.setMacAddress(r1)     // Catch: java.lang.Throwable -> L4a
                        java.lang.String r5 = com.jingdong.common.utils.TelephoneUtils.TAG     // Catch: java.lang.Throwable -> L4a
                        com.jingdong.sdk.oklog.OKLog.e(r5, r6)     // Catch: java.lang.Throwable -> L4a
                    L56:
                        monitor-exit(r0)
                        return
                    L58:
                        monitor-exit(r0)
                        throw r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.TelephoneUtils.getLocalMacAddress(com.jingdong.jdsdk.utils.MacAddressListener, android.content.Context):void");
                }
            }
