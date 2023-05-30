package com.huawei.hms.framework.common.hianalytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.utils.HMSBIInitializer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: classes12.dex */
public class HianalyticsHelper {
    private static final String DEAULT_HA_SERVICE_TAG = "_default_config_tag";
    private static final String HWID_HA_SERVICE_TAG = "hms_hwid";
    private static final String TAG = "HianalyticsHelper";
    private static final int TYPE_MAINTF = 1;
    private static final String USER_EXPERIENCE_INVOLVED = "user_experience_involved";
    private static final int USER_EXPERIENCE_ON = 1;
    @SuppressLint({"StaticFieldLeak"})
    private static volatile HianalyticsHelper instance;
    private boolean hasHMSBI;
    private boolean hasHianalytics;
    private String haTag = HWID_HA_SERVICE_TAG;
    private HiAnalyticsInstance hInstance = null;
    private HiAnalyticsInstance defaultInstance = null;
    private boolean isEnablePrivacyPolicy = false;
    private ExecutorService reportExecutor = ExecutorsUtils.newSingleThreadExecutor("report_ha");

    /* loaded from: classes12.dex */
    private static class HianalyticsRunnable implements Runnable {
        private final HianalyticsBaseData data;
        private final String event;

        HianalyticsRunnable(HianalyticsBaseData hianalyticsBaseData, String str) {
            this.data = hianalyticsBaseData;
            this.event = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            HianalyticsHelper.getInstance().onEvent(this.data.get(), this.event);
        }
    }

    private HianalyticsHelper() {
        try {
            HiAnalyticsManager.getInitFlag(DEAULT_HA_SERVICE_TAG);
            this.hasHianalytics = true;
        } catch (Throwable unused) {
            Logger.i(TAG, "Hianalytics sdk not found");
            this.hasHianalytics = false;
        }
        if (!this.hasHianalytics) {
            tryHMSBIInit(ContextHolder.getAppContext());
        }
        Logger.v(TAG, "this time the ha %s, mini %s", Boolean.valueOf(this.hasHianalytics), Boolean.valueOf(this.hasHMSBI));
    }

    public static HianalyticsHelper getInstance() {
        if (instance == null) {
            synchronized (HianalyticsHelper.class) {
                if (instance == null) {
                    instance = new HianalyticsHelper();
                }
            }
        }
        return instance;
    }

    private boolean isHianalyticsOk() {
        if (HiAnalyticsManager.getInitFlag(DEAULT_HA_SERVICE_TAG)) {
            if (this.defaultInstance == null) {
                this.defaultInstance = HiAnalyticsManager.getInstanceByTag(DEAULT_HA_SERVICE_TAG);
            }
            return this.defaultInstance != null;
        }
        if (this.hInstance == null) {
            this.hInstance = HiAnalyticsManager.getInstanceByTag(this.haTag);
        }
        return this.hInstance != null;
    }

    private void onNewEvent(Context context, String str, Map map, int i2) {
        if (context == null || map == null) {
            return;
        }
        Logger.v(TAG, "data = %s", map);
        try {
            HiAnalyticsUtils.getInstance().onNewEvent(context, str, map, i2);
        } catch (NoSuchMethodError unused) {
            Logger.w(TAG, "may be you need upgrade stats sdk");
        } catch (Throwable unused2) {
            Logger.i(TAG, "the stats has other error,pls check it");
        }
    }

    private void tryHMSBIInit(Context context) {
        if (context == null) {
            Logger.i(TAG, "the appContext hasn't init");
            return;
        }
        try {
            HMSBIInitializer.getInstance(context).initBI();
            this.hasHMSBI = true;
        } catch (NoClassDefFoundError unused) {
            Logger.w(TAG, "maybe you need add base sdk!");
        } catch (Throwable unused2) {
            Logger.w(TAG, "the hms base has other error!");
        }
    }

    public void enablePrivacyPolicy(boolean z) {
        this.isEnablePrivacyPolicy = z;
    }

    public void executeReportHa(HianalyticsBaseData hianalyticsBaseData, String str) {
        getReportExecutor().execute(new HianalyticsRunnable(hianalyticsBaseData, str));
    }

    public ExecutorService getReportExecutor() {
        return this.reportExecutor;
    }

    public boolean isEnableReport(Context context) {
        if (this.hasHMSBI) {
            return true;
        }
        if (this.hasHianalytics) {
            return isEnableReportNoSeed(context);
        }
        return false;
    }

    public boolean isEnableReportNoSeed(Context context) {
        if (this.hasHMSBI) {
            return true;
        }
        if (!this.hasHianalytics) {
            Logger.i(TAG, "Hianalytics sdk need to be initialized");
            return false;
        } else if (context == null) {
            Logger.i(TAG, "HianalyticsHelper context can't be null");
            return false;
        } else if (this.isEnablePrivacyPolicy) {
            return isHianalyticsOk();
        } else {
            try {
                if (Settings.Secure.getInt(context.getContentResolver(), USER_EXPERIENCE_INVOLVED, -1) == 1) {
                    return isHianalyticsOk();
                }
            } catch (IllegalStateException unused) {
                Logger.w(TAG, "the setting has illegalStateException");
            } catch (Throwable unused2) {
                Logger.w(TAG, "the setting has other error");
            }
            Logger.i(TAG, "user experience involved needs to be opened");
            return false;
        }
    }

    public void onEvent(LinkedHashMap<String, String> linkedHashMap, String str) {
        onEvent(linkedHashMap, str, 1);
    }

    public void reportData(Context context, LinkedHashMap<String, String> linkedHashMap, String str, int i2) {
        if (isEnableReportNoSeed(context)) {
            onEvent(linkedHashMap, str, i2);
        }
    }

    public void reportException(final Throwable th, final String str) {
        if (getInstance().isEnableReport(ContextHolder.getAppContext())) {
            Thread.currentThread().getName();
            try {
                this.reportExecutor.submit(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0020: INVOKE 
                      (wrap: java.util.concurrent.ExecutorService : 0x0019: IGET (r4v0 'this' com.huawei.hms.framework.common.hianalytics.HianalyticsHelper A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x0024, RejectedExecutionException -> 0x0030, TRY_ENTER, WRAPPED] (LINE:3) com.huawei.hms.framework.common.hianalytics.HianalyticsHelper.reportExecutor java.util.concurrent.ExecutorService)
                      (wrap: java.lang.Runnable : 0x001d: CONSTRUCTOR 
                      (r4v0 'this' com.huawei.hms.framework.common.hianalytics.HianalyticsHelper A[IMMUTABLE_TYPE, THIS])
                      (r1 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v0 'th' java.lang.Throwable A[DONT_INLINE])
                      (r6v0 'str' java.lang.String A[DONT_INLINE])
                     A[Catch: Exception -> 0x0024, RejectedExecutionException -> 0x0030, MD:(com.huawei.hms.framework.common.hianalytics.HianalyticsHelper, java.lang.String, java.lang.Throwable, java.lang.String):void (m), WRAPPED] call: com.huawei.hms.framework.common.hianalytics.HianalyticsHelper.1.<init>(com.huawei.hms.framework.common.hianalytics.HianalyticsHelper, java.lang.String, java.lang.Throwable, java.lang.String):void type: CONSTRUCTOR)
                     type: INTERFACE call: java.util.concurrent.ExecutorService.submit(java.lang.Runnable):java.util.concurrent.Future A[Catch: Exception -> 0x0024, RejectedExecutionException -> 0x0030, MD:(java.lang.Runnable):java.util.concurrent.Future<?> (c), TRY_LEAVE] (LINE:3) in method: com.huawei.hms.framework.common.hianalytics.HianalyticsHelper.reportException(java.lang.Throwable, java.lang.String):void, file: classes12.dex
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
                    	... 29 more
                    */
                /*
                    this = this;
                    java.lang.String r0 = "HianalyticsHelper"
                    com.huawei.hms.framework.common.hianalytics.HianalyticsHelper r1 = getInstance()
                    android.content.Context r2 = com.huawei.hms.framework.common.ContextHolder.getAppContext()
                    boolean r1 = r1.isEnableReport(r2)
                    if (r1 != 0) goto L11
                    return
                L11:
                    java.lang.Thread r1 = java.lang.Thread.currentThread()
                    java.lang.String r1 = r1.getName()
                    java.util.concurrent.ExecutorService r2 = r4.reportExecutor     // Catch: java.lang.Exception -> L24 java.util.concurrent.RejectedExecutionException -> L30
                    com.huawei.hms.framework.common.hianalytics.HianalyticsHelper$1 r3 = new com.huawei.hms.framework.common.hianalytics.HianalyticsHelper$1     // Catch: java.lang.Exception -> L24 java.util.concurrent.RejectedExecutionException -> L30
                    r3.<init>()     // Catch: java.lang.Exception -> L24 java.util.concurrent.RejectedExecutionException -> L30
                    r2.submit(r3)     // Catch: java.lang.Exception -> L24 java.util.concurrent.RejectedExecutionException -> L30
                    goto L35
                L24:
                    r6 = 1
                    java.lang.Object[] r6 = new java.lang.Object[r6]
                    r1 = 0
                    r6[r1] = r5
                    java.lang.String r5 = "reportException error!"
                    com.huawei.hms.framework.common.Logger.i(r0, r5, r6)
                    goto L35
                L30:
                    java.lang.String r5 = "reportException error RejectedExecutionException"
                    com.huawei.hms.framework.common.Logger.i(r0, r5)
                L35:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.common.hianalytics.HianalyticsHelper.reportException(java.lang.Throwable, java.lang.String):void");
            }

            public void setHaTag(String str) {
                this.haTag = str;
            }

            public void onEvent(LinkedHashMap<String, String> linkedHashMap, String str, int i2) {
                HiAnalyticsInstance hiAnalyticsInstance;
                if (this.hasHMSBI) {
                    onNewEvent(ContextHolder.getAppContext(), str, linkedHashMap, i2);
                } else if (i2 == 0) {
                    Logger.v(TAG, "the base sdk isn't exsit, and reportType is %s", Integer.valueOf(i2));
                    return;
                }
                if (this.hasHianalytics && linkedHashMap != null) {
                    Logger.v(TAG, "data = %s", linkedHashMap);
                    if (HiAnalyticsManager.getInitFlag(DEAULT_HA_SERVICE_TAG) && (hiAnalyticsInstance = this.defaultInstance) != null) {
                        hiAnalyticsInstance.onEvent(1, str, linkedHashMap);
                        return;
                    }
                    HiAnalyticsInstance hiAnalyticsInstance2 = this.hInstance;
                    if (hiAnalyticsInstance2 != null) {
                        hiAnalyticsInstance2.onEvent(1, str, linkedHashMap);
                    } else {
                        Logger.e(TAG, "the ha has error,has init but is null!");
                    }
                }
            }

            public void onEvent(LinkedHashMap<String, String> linkedHashMap) {
                onEvent(linkedHashMap, HianalyticsBaseData.EVENT_ID);
            }
        }
