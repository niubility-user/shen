package com.jingdong.common.web.xrender;

import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.base.util.StringUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.ui.JDWebView;
import com.tencent.smtt.sdk.ValueCallback;

/* loaded from: classes12.dex */
public class XRenderUtils {
    public static final String JS_DISPATCH_EVENT = "try{;(function(){var event = new CustomEvent('%s', {'detail': %s}); window.dispatchEvent(event);})();} catch (e) {console && console.error('ERROR in dispatchEvent:'+eventName, e);}";
    public static final String SWITCH_ENCODE_DATA = "xRenderH5DataEncode";

    public static /* synthetic */ void a(JDWebView jDWebView, String str, String str2) {
        if (jDWebView == null || jDWebView.getWebView() == null) {
            return;
        }
        jDWebView.getWebView().evaluateJavascript(str, (ValueCallback<String>) null);
        jDWebView.appendPerformanceData(str2, String.valueOf(System.currentTimeMillis()));
    }

    public static void dispatchJSEvent(final JDWebView jDWebView, final String str, JDJSONObject jDJSONObject) {
        try {
            Object obj = jDJSONObject;
            if (shouldEncodeXRenderJsonData()) {
                obj = StringUtils.string2JsStr(jDJSONObject.toJSONString());
            }
            String.format(JS_DISPATCH_EVENT, str, obj);
            Runnable runnable = new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0020: CONSTRUCTOR (r0v2 'runnable' java.lang.Runnable) = 
                  (r3v0 'jDWebView' com.jingdong.common.web.ui.JDWebView A[DONT_INLINE])
                  (r5 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r4v0 'str' java.lang.String A[DONT_INLINE])
                 A[Catch: Exception -> 0x003d, DECLARE_VAR, MD:(com.jingdong.common.web.ui.JDWebView, java.lang.String, java.lang.String):void (m)] (LINE:3) call: com.jingdong.common.web.xrender.h.<init>(com.jingdong.common.web.ui.JDWebView, java.lang.String, java.lang.String):void type: CONSTRUCTOR in method: com.jingdong.common.web.xrender.XRenderUtils.dispatchJSEvent(com.jingdong.common.web.ui.JDWebView, java.lang.String, com.jd.framework.json.JDJSONObject):void, file: classes12.dex
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
                boolean r0 = shouldEncodeXRenderJsonData()     // Catch: java.lang.Exception -> L3d
                if (r0 == 0) goto Le
                java.lang.String r5 = r5.toJSONString()     // Catch: java.lang.Exception -> L3d
                java.lang.String r5 = com.jd.libs.hybrid.base.util.StringUtils.string2JsStr(r5)     // Catch: java.lang.Exception -> L3d
            Le:
                java.lang.String r0 = "try{;(function(){var event = new CustomEvent('%s', {'detail': %s}); window.dispatchEvent(event);})();} catch (e) {console && console.error('ERROR in dispatchEvent:'+eventName, e);}"
                r1 = 2
                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L3d
                r2 = 0
                r1[r2] = r4     // Catch: java.lang.Exception -> L3d
                r2 = 1
                r1[r2] = r5     // Catch: java.lang.Exception -> L3d
                java.lang.String r5 = java.lang.String.format(r0, r1)     // Catch: java.lang.Exception -> L3d
                com.jingdong.common.web.xrender.h r0 = new com.jingdong.common.web.xrender.h     // Catch: java.lang.Exception -> L3d
                r0.<init>()     // Catch: java.lang.Exception -> L3d
                android.os.Looper r3 = android.os.Looper.myLooper()     // Catch: java.lang.Exception -> L3d
                android.os.Looper r4 = android.os.Looper.getMainLooper()     // Catch: java.lang.Exception -> L3d
                if (r3 != r4) goto L31
                r0.run()     // Catch: java.lang.Exception -> L3d
                goto L3d
            L31:
                android.os.Handler r3 = new android.os.Handler     // Catch: java.lang.Exception -> L3d
                android.os.Looper r4 = android.os.Looper.getMainLooper()     // Catch: java.lang.Exception -> L3d
                r3.<init>(r4)     // Catch: java.lang.Exception -> L3d
                r3.post(r0)     // Catch: java.lang.Exception -> L3d
            L3d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.xrender.XRenderUtils.dispatchJSEvent(com.jingdong.common.web.ui.JDWebView, java.lang.String, com.jd.framework.json.JDJSONObject):void");
        }

        public static boolean shouldEncodeXRenderJsonData() {
            return SwitchQueryFetcher.getSwitchBooleanValue(SWITCH_ENCODE_DATA, false);
        }
    }
