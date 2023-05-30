package com.jingdong.common.openlinktime;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class OpenLinkTimeManager {
    private static final String BABEL_ON_RENDER = "babelOnRender";
    private static final String ENTER_BABEL = "enterBabel";
    private static final String ENTER_M = "enterM";
    private static final String ENTER_UNION = "enterUnion";
    private static final String INTERFACE_CREATE = "InterfaceCreate";
    private static final String LAUNCH_START = "launchStart";
    private static final String OPEN_APP_START = "openappStart";
    private static String TAG = "OpenLinkTimeManager";
    private static final String WB_LOAD_URL = "wbLoadUrl";
    private static final String WB_ON_RENDER = "wbOnRender";
    private int lastSequence;
    private int mLatestSequence;
    private static final AtomicBoolean sCold = new AtomicBoolean(false);
    private static final AtomicBoolean jdsdkInited = new AtomicBoolean(false);
    private static final AtomicBoolean sNeedReport = new AtomicBoolean(false);
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private OpenLinkTimeEntity mEntity = new OpenLinkTimeEntity();
    private JDJSONObject mTimeMap = new JDJSONObject();
    private JDJSONObject mExtraTimingMap = new JDJSONObject();
    private JDJSONObject mJsonParam = new JDJSONObject();
    private SparseArray<JDJSONObject> mOpenJsonParam = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Instance {
        static OpenLinkTimeManager sInstance = new OpenLinkTimeManager();

        Instance() {
        }
    }

    private synchronized void addTime(String str) {
        addTime(str, false);
    }

    public static OpenLinkTimeManager getInstance() {
        return Instance.sInstance;
    }

    private void reportData() {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.LINK_TRACK_ENABLE, false) && PerformanceReporter.getIsNeedReport(JdSdk.getInstance().getApplicationContext(), "8", "5")) {
            DecimalFormat decimalFormat = new DecimalFormat("0.000000");
            HashMap<String, String> reportMap = this.mEntity.getReportMap();
            reportMap.put("occurTime", decimalFormat.format(System.currentTimeMillis() / 1000));
            reportMap.put("typeId", "8");
            reportMap.put("chId", "5");
            JDJSONObject jDJSONObject = this.mTimeMap;
            if (jDJSONObject != null) {
                reportMap.put("timing", jDJSONObject.toString());
            }
            JDJSONObject jDJSONObject2 = this.mExtraTimingMap;
            if (jDJSONObject2 != null && jDJSONObject2.size() > 0) {
                reportMap.put("extraTiming", this.mExtraTimingMap.toString());
            }
            JDJSONObject jDJSONObject3 = this.mJsonParam;
            if (jDJSONObject3 != null && jDJSONObject3.size() > 0) {
                reportMap.put("jsonParam", this.mJsonParam.toString());
            }
            PerformanceReporter.reportData(reportMap);
        }
    }

    public void addExtraTiming(final String str, final long j2) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.mExtraTimingMap.put(str, (Object) Long.valueOf(j2));
            } else {
                sHandler.post(new Runnable() { // from class: com.jingdong.common.openlinktime.OpenLinkTimeManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        OpenLinkTimeManager.this.mExtraTimingMap.put(str, (Object) Long.valueOf(j2));
                    }
                });
            }
        } catch (Throwable th) {
            th.getMessage();
        }
    }

    public void addJsonParam(final String str, final String str2) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.mJsonParam.put(str, (Object) str2);
            } else {
                sHandler.post(new Runnable() { // from class: com.jingdong.common.openlinktime.OpenLinkTimeManager.3
                    @Override // java.lang.Runnable
                    public void run() {
                        OpenLinkTimeManager.this.mJsonParam.put(str, (Object) str2);
                    }
                });
            }
        } catch (Throwable th) {
            th.getMessage();
        }
    }

    public void babelNative(String str, int i2) {
        if (sNeedReport.get() && i2 == this.mLatestSequence) {
            this.mEntity.setJumpUrl(str);
            this.mEntity.babelNative();
            addTime(BABEL_ON_RENDER, true);
        }
    }

    public void enterBabel(int i2) {
        if (sNeedReport.get() && i2 == this.mLatestSequence) {
            addTime(ENTER_BABEL);
            this.mEntity.setBusinessType("babel");
        }
    }

    public void enterM(int i2) {
        if (sNeedReport.get() && i2 == this.mLatestSequence) {
            addTime(ENTER_M);
            this.mEntity.setBusinessType("webview");
        }
    }

    public void enterUnion(int i2) {
        if (i2 != this.mLatestSequence) {
            return;
        }
        addTime(ENTER_UNION);
    }

    public void finish() {
        this.mEntity.isFinish(false);
        reportTimeData();
    }

    public int getLastSequence() {
        return this.lastSequence;
    }

    public int getLatestSequence() {
        return this.mLatestSequence;
    }

    public JDJSONObject getOpenJsonParam(int i2) {
        return this.mOpenJsonParam.get(i2);
    }

    public void interfaceActivityCreate() {
        if (!sCold.get()) {
            finish();
        } else if (this.mTimeMap.size() > 1) {
            reset();
        }
        addTime(INTERFACE_CREATE);
    }

    public boolean isCold() {
        return sCold.get();
    }

    public void isOpenLink(String str) {
        sNeedReport.set(true);
        this.mEntity.setUrl(str);
    }

    public void isShowPrivacyDialog() {
        this.mEntity.isPrivacy(true);
    }

    public void jdSdkInited() {
        jdsdkInited.set(true);
    }

    public void onForeToBackground() {
        this.mEntity.setEvent(OpenLinkTimeEntity.EVENT_BACK);
        finish();
    }

    public void reportTimeData() {
        try {
            if (sNeedReport.getAndSet(false)) {
                reportData();
            }
            reset();
        } catch (Throwable th) {
            th.getMessage();
        }
    }

    public void reset() {
        sCold.set(false);
        sNeedReport.set(false);
        this.mEntity = new OpenLinkTimeEntity();
        this.mTimeMap = new JDJSONObject();
        this.mExtraTimingMap = new JDJSONObject();
        this.mJsonParam = new JDJSONObject();
        this.mLatestSequence = 0;
        if (this.mOpenJsonParam.size() > 3) {
            this.mOpenJsonParam.removeAt(0);
        }
    }

    public synchronized int setOpenJsonParam(JDJSONObject jDJSONObject) {
        int i2;
        i2 = this.lastSequence + 1;
        this.lastSequence = i2;
        this.mOpenJsonParam.put(i2, jDJSONObject);
        this.mLatestSequence = i2;
        return i2;
    }

    public void start() {
        try {
            if (!jdsdkInited.get() || ProcessUtil.isMainProcess()) {
                reset();
                sCold.set(true);
                this.mEntity.isCold(true);
                addTime(LAUNCH_START);
            }
        } catch (Throwable th) {
            th.getMessage();
        }
    }

    public void startOpenApp() {
        addTime(OPEN_APP_START);
    }

    public void webviewLoadUrl(int i2) {
        if (sNeedReport.get() && i2 == this.mLatestSequence) {
            addTime(WB_LOAD_URL);
        }
    }

    public void webviewRender(String str, int i2) {
        if (sNeedReport.get() && i2 == this.mLatestSequence) {
            this.mEntity.setJumpUrl(str);
            if (this.mEntity.isBabelType()) {
                this.mEntity.babel();
            } else {
                this.mEntity.webview();
            }
            addTime(WB_ON_RENDER, true);
        }
    }

    private synchronized void addTime(final String str, final boolean z) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.mTimeMap.put(str, (Object) Long.valueOf(System.currentTimeMillis()));
                if (z) {
                    reportTimeData();
                }
            } else {
                System.currentTimeMillis();
                sHandler.post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002d: INVOKE 
                      (wrap: android.os.Handler : 0x0022: SGET  A[Catch: all -> 0x0031, WRAPPED] (LINE:7) com.jingdong.common.openlinktime.OpenLinkTimeManager.sHandler android.os.Handler)
                      (wrap: java.lang.Runnable : 0x002a: CONSTRUCTOR 
                      (r8v0 'this' com.jingdong.common.openlinktime.OpenLinkTimeManager A[IMMUTABLE_TYPE, THIS])
                      (r9v0 'str' java.lang.String A[DONT_INLINE])
                      (r3 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r10v0 'z' boolean A[DONT_INLINE])
                     A[Catch: all -> 0x0031, MD:(com.jingdong.common.openlinktime.OpenLinkTimeManager, java.lang.String, long, boolean):void (m), WRAPPED] call: com.jingdong.common.openlinktime.OpenLinkTimeManager.1.<init>(com.jingdong.common.openlinktime.OpenLinkTimeManager, java.lang.String, long, boolean):void type: CONSTRUCTOR)
                     type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[Catch: all -> 0x0031, MD:(java.lang.Runnable):boolean (c), TRY_LEAVE] (LINE:7) in method: com.jingdong.common.openlinktime.OpenLinkTimeManager.addTime(java.lang.String, boolean):void, file: classes.dex
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
                    	... 31 more
                    */
                /*
                    this = this;
                    monitor-enter(r8)
                    android.os.Looper r0 = android.os.Looper.myLooper()     // Catch: java.lang.Throwable -> L31
                    android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L31
                    if (r0 != r1) goto L1e
                    com.jd.framework.json.JDJSONObject r0 = r8.mTimeMap     // Catch: java.lang.Throwable -> L31
                    long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L31
                    java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch: java.lang.Throwable -> L31
                    r0.put(r9, r1)     // Catch: java.lang.Throwable -> L31
                    if (r10 == 0) goto L35
                    r8.reportTimeData()     // Catch: java.lang.Throwable -> L31
                    goto L35
                L1e:
                    long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L31
                    android.os.Handler r6 = com.jingdong.common.openlinktime.OpenLinkTimeManager.sHandler     // Catch: java.lang.Throwable -> L31
                    com.jingdong.common.openlinktime.OpenLinkTimeManager$1 r7 = new com.jingdong.common.openlinktime.OpenLinkTimeManager$1     // Catch: java.lang.Throwable -> L31
                    r0 = r7
                    r1 = r8
                    r2 = r9
                    r5 = r10
                    r0.<init>()     // Catch: java.lang.Throwable -> L31
                    r6.post(r7)     // Catch: java.lang.Throwable -> L31
                    goto L35
                L31:
                    r9 = move-exception
                    r9.getMessage()     // Catch: java.lang.Throwable -> L37
                L35:
                    monitor-exit(r8)
                    return
                L37:
                    r9 = move-exception
                    monitor-exit(r8)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.openlinktime.OpenLinkTimeManager.addTime(java.lang.String, boolean):void");
            }
        }
