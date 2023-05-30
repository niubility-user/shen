package com.jd.libs.hybrid.preload.jsimp;

import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import androidx.annotation.Keep;
import com.jd.framework.json.JDJSON;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PerformanceUtils;
import com.jd.libs.hybrid.preload.DataProvider;
import com.jd.libs.xdog.b;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Keep
/* loaded from: classes16.dex */
public class HybridJSInterface {
    public static final String JS_OBJ_NAME = "JDHybrid";
    private static final String TAG = "HybridJSInterface";
    private String gprlend;
    private String gprlstart;
    private String mPreloadId;
    private HybridWebView mWebView;
    private PreloadDataCallback preloadCallback;

    /* loaded from: classes16.dex */
    public interface PreloadDataCallback {
        void onWebFetchData(int i2);
    }

    public HybridJSInterface(HybridWebView hybridWebView, String str) {
        this.mPreloadId = str;
        this.mWebView = hybridWebView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callPreloadCallback(int i2) {
        PreloadDataCallback preloadDataCallback = this.preloadCallback;
        if (preloadDataCallback != null) {
            preloadDataCallback.onWebFetchData(i2);
        }
        PerformanceUtils.onPreloadStatusChange(this.mWebView, String.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEnd() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.gprlend = valueOf;
        b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "gprlend", valueOf);
        try {
            Map<String, String> optPerformanceInfo = DataProvider.getInstance().optPerformanceInfo(this.mPreloadId);
            HashMap hashMap = new HashMap();
            if (optPerformanceInfo != null) {
                hashMap.putAll(optPerformanceInfo);
            }
            hashMap.put("gprlstart", this.gprlstart);
            hashMap.put("gprlend", this.gprlend);
            PerformanceUtils.onMonitor(this.mWebView, HttpDnsConfig.PREDOWNLOAD_PARAMS, hashMap);
        } catch (Throwable th) {
            if (Log.isDebug()) {
                Log.d(TAG, "preload monitor end exception", th);
            }
        }
    }

    private void onStart() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.gprlstart = valueOf;
        b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "gprlstart", valueOf);
    }

    static Object parseRemoteData(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (Log.isDebug()) {
                    Log.d(TAG, "preload parseObject jsonString --> " + str);
                }
                return JDJSON.parse(str);
            } catch (Throwable th) {
                Log.d(TAG, "preload parse data failed", th);
            }
        }
        Log.d(TAG, "preload data String --> " + str);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendJSONStr2M(final String str, final HybridWebView hybridWebView, final String str2, final int i2, String str3) {
        View view;
        if (Log.isDebug()) {
            Log.d(TAG, String.format(Locale.getDefault(), "Preload result: status=%d, data=%s", Integer.valueOf(i2), str3));
        }
        if (hybridWebView != null && (view = hybridWebView.getView()) != null) {
            parseRemoteData(str3);
            view.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003a: INVOKE 
                  (r0v1 'view' android.view.View)
                  (wrap: java.lang.Runnable : 0x0037: CONSTRUCTOR 
                  (r9v0 'this' com.jd.libs.hybrid.preload.jsimp.HybridJSInterface A[IMMUTABLE_TYPE, THIS])
                  (r12v0 'str2' java.lang.String A[DONT_INLINE])
                  (r11v0 'hybridWebView' com.jd.libs.hybrid.base.HybridWebView A[DONT_INLINE])
                  (r13v0 'i2' int A[DONT_INLINE])
                  (r7 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r10v0 'str' java.lang.String A[DONT_INLINE])
                 A[MD:(com.jd.libs.hybrid.preload.jsimp.HybridJSInterface, java.lang.String, com.jd.libs.hybrid.base.HybridWebView, int, java.lang.Object, java.lang.String):void (m), WRAPPED] (LINE:5) call: com.jd.libs.hybrid.preload.jsimp.HybridJSInterface.2.<init>(com.jd.libs.hybrid.preload.jsimp.HybridJSInterface, java.lang.String, com.jd.libs.hybrid.base.HybridWebView, int, java.lang.Object, java.lang.String):void type: CONSTRUCTOR)
                 type: VIRTUAL call: android.view.View.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:5) in method: com.jd.libs.hybrid.preload.jsimp.HybridJSInterface.sendJSONStr2M(java.lang.String, com.jd.libs.hybrid.base.HybridWebView, java.lang.String, int, java.lang.String):void, file: classes16.dex
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
                boolean r0 = com.jd.libs.hybrid.base.util.Log.isDebug()
                java.lang.String r1 = "HybridJSInterface"
                if (r0 == 0) goto L22
                java.util.Locale r0 = java.util.Locale.getDefault()
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
                r2[r3] = r4
                r3 = 1
                r2[r3] = r14
                java.lang.String r3 = "Preload result: status=%d, data=%s"
                java.lang.String r0 = java.lang.String.format(r0, r3, r2)
                com.jd.libs.hybrid.base.util.Log.d(r1, r0)
            L22:
                if (r11 == 0) goto L3e
                android.view.View r0 = r11.getView()
                if (r0 != 0) goto L2b
                goto L3e
            L2b:
                java.lang.Object r7 = parseRemoteData(r14)
                com.jd.libs.hybrid.preload.jsimp.HybridJSInterface$2 r14 = new com.jd.libs.hybrid.preload.jsimp.HybridJSInterface$2
                r2 = r14
                r3 = r9
                r4 = r12
                r5 = r11
                r6 = r13
                r8 = r10
                r2.<init>()
                r0.post(r14)
                return
            L3e:
                java.lang.String r10 = "webview is null in preload js callback."
                com.jd.libs.hybrid.base.util.Log.e(r1, r10)
                r9.onEnd()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.preload.jsimp.HybridJSInterface.sendJSONStr2M(java.lang.String, com.jd.libs.hybrid.base.HybridWebView, java.lang.String, int, java.lang.String):void");
        }

        @JavascriptInterface
        @Keep
        public void fetchPreloadData(final String str, final String str2) {
            onStart();
            if (TextUtils.isEmpty(str2)) {
                callPreloadCallback(0);
                Log.e(TAG, "callback name is empty.");
                onEnd();
            } else if (!HybridSettings.isInited()) {
                Log.e("Hybrid SDK is not initialized!");
                sendJSONStr2M(str, this.mWebView, str2, -2, null);
            } else if (str != null && this.mPreloadId != null) {
                DataProvider.getInstance().jsRequestData(str + CartConstant.KEY_YB_INFO_LINK + this.mPreloadId, new DataProvider.Callback() { // from class: com.jd.libs.hybrid.preload.jsimp.HybridJSInterface.1
                    @Override // com.jd.libs.hybrid.preload.DataProvider.Callback
                    public void onResult(int i2, String str3) {
                        if (HybridJSInterface.this.mWebView != null) {
                            HybridJSInterface hybridJSInterface = HybridJSInterface.this;
                            hybridJSInterface.sendJSONStr2M(str, hybridJSInterface.mWebView, str2, i2, str3);
                        }
                    }
                });
            } else {
                HybridWebView hybridWebView = this.mWebView;
                if (hybridWebView != null) {
                    sendJSONStr2M(str, hybridWebView, str2, -2, null);
                }
            }
        }

        public void setPreloadCallback(PreloadDataCallback preloadDataCallback) {
            this.preloadCallback = preloadDataCallback;
        }

        public void setPreloadId(String str) {
            this.mPreloadId = str;
        }
    }
