package com.airbnb.lottie;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.os.TraceCompat;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static LottieNetworkCacheProvider cacheProvider;
    private static int depthPastMaxDepth;
    private static LottieNetworkFetcher fetcher;
    private static volatile NetworkCache networkCache;
    private static volatile NetworkFetcher networkFetcher;
    private static String[] sections;
    private static long[] startTimeNs;
    private static int traceDepth;
    private static boolean traceEnabled;

    private L() {
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            int i2 = traceDepth;
            if (i2 == 20) {
                depthPastMaxDepth++;
                return;
            }
            sections[i2] = str;
            startTimeNs[i2] = System.nanoTime();
            TraceCompat.beginSection(str);
            traceDepth++;
        }
    }

    public static float endSection(String str) {
        int i2 = depthPastMaxDepth;
        if (i2 > 0) {
            depthPastMaxDepth = i2 - 1;
            return 0.0f;
        } else if (traceEnabled) {
            int i3 = traceDepth - 1;
            traceDepth = i3;
            if (i3 != -1) {
                if (str.equals(sections[i3])) {
                    TraceCompat.endSection();
                    return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
                }
                throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + sections[traceDepth] + OrderISVUtil.MONEY_DECIMAL);
            }
            throw new IllegalStateException("Can't end trace section. There are none.");
        } else {
            return 0.0f;
        }
    }

    @NonNull
    public static NetworkCache networkCache(@NonNull Context context) {
        context.getApplicationContext();
        NetworkCache networkCache2 = networkCache;
        if (networkCache2 == null) {
            synchronized (NetworkCache.class) {
                networkCache2 = networkCache;
                if (networkCache2 == null) {
                    LottieNetworkCacheProvider lottieNetworkCacheProvider = cacheProvider;
                    if (lottieNetworkCacheProvider == null) {
                        lottieNetworkCacheProvider = new LottieNetworkCacheProvider
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0018: CONSTRUCTOR (r2v1 'lottieNetworkCacheProvider' com.airbnb.lottie.network.LottieNetworkCacheProvider) = (r3 I:android.content.Context A[DONT_GENERATE, DONT_INLINE, REMOVE]) A[Catch: all -> 0x0022, MD:(android.content.Context):void (m)] call: com.airbnb.lottie.L.1.<init>(android.content.Context):void type: CONSTRUCTOR in method: com.airbnb.lottie.L.networkCache(android.content.Context):com.airbnb.lottie.network.NetworkCache, file: classes.dex
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
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
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
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                            	... 41 more
                            */
                        /*
                            android.content.Context r3 = r3.getApplicationContext()
                            com.airbnb.lottie.network.NetworkCache r0 = com.airbnb.lottie.L.networkCache
                            if (r0 != 0) goto L25
                            java.lang.Class<com.airbnb.lottie.network.NetworkCache> r1 = com.airbnb.lottie.network.NetworkCache.class
                            monitor-enter(r1)
                            com.airbnb.lottie.network.NetworkCache r0 = com.airbnb.lottie.L.networkCache     // Catch: java.lang.Throwable -> L22
                            if (r0 != 0) goto L20
                            com.airbnb.lottie.network.NetworkCache r0 = new com.airbnb.lottie.network.NetworkCache     // Catch: java.lang.Throwable -> L22
                            com.airbnb.lottie.network.LottieNetworkCacheProvider r2 = com.airbnb.lottie.L.cacheProvider     // Catch: java.lang.Throwable -> L22
                            if (r2 == 0) goto L16
                            goto L1b
                        L16:
                            com.airbnb.lottie.L$1 r2 = new com.airbnb.lottie.L$1     // Catch: java.lang.Throwable -> L22
                            r2.<init>()     // Catch: java.lang.Throwable -> L22
                        L1b:
                            r0.<init>(r2)     // Catch: java.lang.Throwable -> L22
                            com.airbnb.lottie.L.networkCache = r0     // Catch: java.lang.Throwable -> L22
                        L20:
                            monitor-exit(r1)     // Catch: java.lang.Throwable -> L22
                            goto L25
                        L22:
                            r3 = move-exception
                            monitor-exit(r1)     // Catch: java.lang.Throwable -> L22
                            throw r3
                        L25:
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.L.networkCache(android.content.Context):com.airbnb.lottie.network.NetworkCache");
                    }

                    @NonNull
                    public static NetworkFetcher networkFetcher(@NonNull Context context) {
                        NetworkFetcher networkFetcher2 = networkFetcher;
                        if (networkFetcher2 == null) {
                            synchronized (NetworkFetcher.class) {
                                networkFetcher2 = networkFetcher;
                                if (networkFetcher2 == null) {
                                    NetworkCache networkCache2 = networkCache(context);
                                    LottieNetworkFetcher lottieNetworkFetcher = fetcher;
                                    if (lottieNetworkFetcher == null) {
                                        lottieNetworkFetcher = new DefaultLottieNetworkFetcher();
                                    }
                                    networkFetcher2 = new NetworkFetcher(networkCache2, lottieNetworkFetcher);
                                    networkFetcher = networkFetcher2;
                                }
                            }
                        }
                        return networkFetcher2;
                    }

                    public static void setCacheProvider(LottieNetworkCacheProvider lottieNetworkCacheProvider) {
                        cacheProvider = lottieNetworkCacheProvider;
                    }

                    public static void setFetcher(LottieNetworkFetcher lottieNetworkFetcher) {
                        fetcher = lottieNetworkFetcher;
                    }

                    public static void setTraceEnabled(boolean z) {
                        if (traceEnabled == z) {
                            return;
                        }
                        traceEnabled = z;
                        if (z) {
                            sections = new String[20];
                            startTimeNs = new long[20];
                        }
                    }
                }
