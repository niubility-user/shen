package com.jingdong.common;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.jd.dynamic.DYConstants;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebPerformance;
import com.jingdong.common.web.managers.WebPerformanceHolder;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebView;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ColorQueryBridge implements IBridgePlugin {
    private void appendRequestStatus(JDWebView jDWebView, String str, String str2) {
        synchronized (jDWebView) {
            String str3 = str + CartConstant.KEY_YB_INFO_LINK + str2;
            WebPerformanceHolder performanceHolder = jDWebView.getPerformanceHolder();
            WebPerformance currentRecord = performanceHolder != null ? performanceHolder.getCurrentRecord() : null;
            String data = currentRecord != null ? currentRecord.getData(WebPerfManager.NATIVE_REQUEST_STATUS) : null;
            if (!TextUtils.isEmpty(data)) {
                str3 = data + DYConstants.DY_REGEX_COMMA + str3;
            }
            jDWebView.appendPerformanceData(WebPerfManager.NATIVE_REQUEST_STATUS, str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void errorCallBack(IBridgeWebView iBridgeWebView, IBridgeCallback iBridgeCallback, String str, String str2) {
        iBridgeCallback.onError("\u8bf7\u6c42\u9519\u8bef");
        report(iBridgeWebView, false, str, "\u89e3\u6790\u9519\u8bef", "functionId=" + str + ", " + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hostEndWithListKeyWord(String str, String str2, String[] strArr) {
        if (TextUtils.isEmpty(str2) || !str2.endsWith("jd.com")) {
            for (String str3 : strArr) {
                String[] split = str3.split(DYConstants.DY_REGEX_COMMA);
                if (split.length == 1 && !TextUtils.isEmpty(split[0])) {
                    if (!TextUtils.isEmpty(str2) && str2.endsWith(split[0])) {
                        return true;
                    }
                } else if (split.length == 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(str2) && str2.endsWith(split[0]) && str.contains(split[1])) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void report(IBridgeWebView iBridgeWebView, boolean z, String str, String str2, String str3) {
        View view;
        if (iBridgeWebView == null || (view = iBridgeWebView.getView()) == null) {
            return;
        }
        JDWebView jDWebView = null;
        try {
            ViewParent parent = view.getParent();
            while (true) {
                if (parent == null) {
                    break;
                } else if (parent instanceof JDWebView) {
                    jDWebView = (JDWebView) parent;
                    break;
                } else {
                    parent = parent.getParent();
                }
            }
            if (jDWebView == null) {
                Log.d("ColorHttpBridge", "\u627e\u4e0d\u5230JDWebView, \u4e0d\u4e0a\u62a5\u57cb\u70b9");
            } else if (z) {
                appendRequestStatus(jDWebView, str, "0");
            } else {
                ExceptionReporter.reportWebViewCommonError("NativeNet_Error", jDWebView.getFinalUrl(), str2, str3);
                appendRequestStatus(jDWebView, str, "-1");
            }
        } catch (Exception e2) {
            Log.e("ColorHttpBridge", e2.getMessage(), e2);
        }
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(final IBridgeWebView iBridgeWebView, String str, final String str2, final IBridgeCallback iBridgeCallback) {
        str.hashCode();
        if (str.equals("colorRequest")) {
            SwitchQueryFetcher.getSwitchStringValue("WebNetList", "").split(";");
            if (iBridgeWebView != null) {
                iBridgeWebView.getView().post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: INVOKE 
                      (wrap: android.view.View : 0x001d: INVOKE (r8v0 'iBridgeWebView' com.jd.xbridge.base.IBridgeWebView) type: INTERFACE call: com.jd.xbridge.base.IBridgeWebView.getView():android.view.View A[MD:():android.view.View (m), WRAPPED] (LINE:3))
                      (wrap: java.lang.Runnable : 0x0028: CONSTRUCTOR 
                      (r7v0 'this' com.jingdong.common.ColorQueryBridge A[IMMUTABLE_TYPE, THIS])
                      (r8v0 'iBridgeWebView' com.jd.xbridge.base.IBridgeWebView A[DONT_INLINE])
                      (r4 I:java.lang.String[] A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r10v0 'str2' java.lang.String A[DONT_INLINE])
                      (r11v0 'iBridgeCallback' com.jd.xbridge.base.IBridgeCallback A[DONT_INLINE])
                     A[MD:(com.jingdong.common.ColorQueryBridge, com.jd.xbridge.base.IBridgeWebView, java.lang.String[], java.lang.String, com.jd.xbridge.base.IBridgeCallback):void (m), WRAPPED] call: com.jingdong.common.ColorQueryBridge.2.<init>(com.jingdong.common.ColorQueryBridge, com.jd.xbridge.base.IBridgeWebView, java.lang.String[], java.lang.String, com.jd.xbridge.base.IBridgeCallback):void type: CONSTRUCTOR)
                     type: VIRTUAL call: android.view.View.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:3) in method: com.jingdong.common.ColorQueryBridge.execute(com.jd.xbridge.base.IBridgeWebView, java.lang.String, java.lang.String, com.jd.xbridge.base.IBridgeCallback):boolean, file: classes5.dex
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
                    r9.hashCode()
                    java.lang.String r0 = "colorRequest"
                    boolean r9 = r9.equals(r0)
                    if (r9 != 0) goto Ld
                    r8 = 0
                    return r8
                Ld:
                    java.lang.String r9 = "WebNetList"
                    java.lang.String r0 = ""
                    java.lang.String r9 = com.jingdong.common.utils.SwitchQueryFetcher.getSwitchStringValue(r9, r0)
                    java.lang.String r0 = ";"
                    java.lang.String[] r4 = r9.split(r0)
                    if (r8 == 0) goto L2f
                    android.view.View r9 = r8.getView()
                    com.jingdong.common.ColorQueryBridge$2 r0 = new com.jingdong.common.ColorQueryBridge$2
                    r1 = r0
                    r2 = r7
                    r3 = r8
                    r5 = r10
                    r6 = r11
                    r1.<init>()
                    r9.post(r0)
                    goto L34
                L2f:
                    java.lang.String r8 = "\u8bf7\u6c42\u9519\u8bef"
                    r11.onError(r8)
                L34:
                    r8 = 1
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ColorQueryBridge.execute(com.jd.xbridge.base.IBridgeWebView, java.lang.String, java.lang.String, com.jd.xbridge.base.IBridgeCallback):boolean");
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:35:0x0084  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0088  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
            /* JADX WARN: Removed duplicated region for block: B:48:0x00b3  */
            /* JADX WARN: Removed duplicated region for block: B:58:0x00d8 A[Catch: Exception -> 0x0114, TryCatch #6 {Exception -> 0x0114, blocks: (B:56:0x00d0, B:58:0x00d8, B:60:0x00de, B:61:0x00e9, B:63:0x00fb, B:65:0x0101), top: B:85:0x00d0 }] */
            /* JADX WARN: Type inference failed for: r4v1 */
            /* JADX WARN: Type inference failed for: r4v2, types: [org.json.JSONObject] */
            /* JADX WARN: Type inference failed for: r4v6 */
            /* JADX WARN: Type inference failed for: r4v8 */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void fetch(final IBridgeWebView iBridgeWebView, String str, final IBridgeCallback iBridgeCallback) {
                String str2;
                String str3;
                final String str4;
                JSONObject jSONObject;
                Log.d("ColorHttpBridge", "colorRequestParams:" + str);
                JSONObject jSONObject2 = null;
                try {
                    JSONObject jSONObject3 = new JSONObject(str);
                    str2 = jSONObject3.has("url") ? jSONObject3.getString("url") : null;
                    try {
                        str4 = jSONObject3.has("functionId") ? jSONObject3.getString("functionId") : null;
                        try {
                            str3 = jSONObject3.has("headerType") ? jSONObject3.getString("headerType") : null;
                            try {
                                JSONObject jSONObject4 = jSONObject3.has("body") ? new JSONObject(jSONObject3.getString("body")) : null;
                                try {
                                    jSONObject = jSONObject4;
                                    if (jSONObject3.has("param")) {
                                        jSONObject2 = new JSONObject(jSONObject3.getString("param"));
                                        jSONObject = jSONObject4;
                                    }
                                } catch (Exception unused) {
                                    jSONObject = jSONObject4;
                                }
                            } catch (Exception unused2) {
                                jSONObject = 0;
                            }
                        } catch (Exception unused3) {
                            str3 = null;
                            jSONObject = 0;
                        }
                    } catch (Exception unused4) {
                        str3 = null;
                        str4 = str3;
                        jSONObject = str4;
                        HttpSetting httpSetting = new HttpSetting();
                        if (TextUtils.isEmpty(str2)) {
                        }
                        httpSetting.setFunctionId(str4);
                        if (jSONObject != 0) {
                        }
                        if (jSONObject2 != null) {
                        }
                        if ("1".equals(str3)) {
                        }
                        httpSetting.setUseFastJsonParser(true);
                        httpSetting.setNeedRetryOnBusinessLayer(false);
                        httpSetting.setNeedRetryOnNetworkLayer(false);
                        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.ColorQueryBridge.1
                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                            public void onEnd(HttpResponse httpResponse) {
                                try {
                                    iBridgeCallback.onSuccess(new JSONObject(httpResponse.getString()));
                                    ColorQueryBridge.this.report(iBridgeWebView, true, str4, null, "functionId=" + str4);
                                } catch (JSONException e2) {
                                    ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, e2.getMessage());
                                }
                            }

                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                            public void onError(HttpError httpError) {
                                if (httpError != null) {
                                    if (httpError.getJsonCode() == 0) {
                                        ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, httpError.toString());
                                        return;
                                    }
                                    HttpResponse httpResponse = httpError.getHttpResponse();
                                    if (httpResponse == null) {
                                        ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, httpError.toString());
                                        return;
                                    }
                                    try {
                                        iBridgeCallback.onSuccess(new JSONObject(httpResponse.getString()));
                                        ColorQueryBridge.this.report(iBridgeWebView, true, str4, null, "functionId=" + str4);
                                        return;
                                    } catch (JSONException e2) {
                                        ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, e2.getMessage());
                                        return;
                                    }
                                }
                                ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, "");
                            }

                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                            public void onProgress(int i2, int i3) {
                            }

                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                            public void onStart() {
                            }
                        });
                        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
                    }
                } catch (Exception unused5) {
                    str2 = null;
                    str3 = null;
                }
                HttpSetting httpSetting2 = new HttpSetting();
                if (TextUtils.isEmpty(str2)) {
                    httpSetting2.setUrl(str2);
                } else {
                    httpSetting2.setHost(Configuration.getPortalHost());
                }
                httpSetting2.setFunctionId(str4);
                if (jSONObject != 0) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        try {
                            httpSetting2.putJsonParam(next, jSONObject.get(next));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (jSONObject2 != null) {
                    Iterator<String> keys2 = jSONObject2.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        try {
                            httpSetting2.putQueryParam(next2, jSONObject2.getString(next2));
                        } catch (JSONException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                try {
                    if ("1".equals(str3)) {
                        String userAgentString = iBridgeWebView instanceof WebView ? ((WebView) iBridgeWebView).getSettings().getUserAgentString() : "";
                        String cookie = CookieManager.getInstance().getCookie(iBridgeWebView.getUrl());
                        if (!TextUtils.isEmpty(cookie) && !TextUtils.isEmpty(userAgentString)) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("User-Agent", userAgentString);
                            hashMap.put("Cookie", cookie);
                            httpSetting2.setHeaderMap(hashMap);
                        }
                    }
                } catch (Exception e4) {
                    if (Log.D) {
                        e4.printStackTrace();
                    }
                }
                httpSetting2.setUseFastJsonParser(true);
                httpSetting2.setNeedRetryOnBusinessLayer(false);
                httpSetting2.setNeedRetryOnNetworkLayer(false);
                httpSetting2.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.ColorQueryBridge.1
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        try {
                            iBridgeCallback.onSuccess(new JSONObject(httpResponse.getString()));
                            ColorQueryBridge.this.report(iBridgeWebView, true, str4, null, "functionId=" + str4);
                        } catch (JSONException e22) {
                            ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, e22.getMessage());
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        if (httpError != null) {
                            if (httpError.getJsonCode() == 0) {
                                ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, httpError.toString());
                                return;
                            }
                            HttpResponse httpResponse = httpError.getHttpResponse();
                            if (httpResponse == null) {
                                ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, httpError.toString());
                                return;
                            }
                            try {
                                iBridgeCallback.onSuccess(new JSONObject(httpResponse.getString()));
                                ColorQueryBridge.this.report(iBridgeWebView, true, str4, null, "functionId=" + str4);
                                return;
                            } catch (JSONException e22) {
                                ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, e22.getMessage());
                                return;
                            }
                        }
                        ColorQueryBridge.this.errorCallBack(iBridgeWebView, iBridgeCallback, str4, "");
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                    public void onProgress(int i2, int i3) {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                    public void onStart() {
                    }
                });
                HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting2);
            }
        }
