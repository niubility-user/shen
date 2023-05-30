package com.jingdong.common.XView2.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.listui.Observable;
import com.jingdong.common.listui.ReqStatus;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Map;

/* loaded from: classes5.dex */
public class HttpUtils {

    /* loaded from: classes5.dex */
    public interface OnNetFitchData<T> {
        void fitchData(T t);

        void onError(HttpError httpError);

        void onStart();
    }

    public static <T> void getData(Context context, final String str, Map<String, Object> map, final Class<T> cls, final OnNetFitchData<T> onNetFitchData) {
        if (!(context instanceof BaseActivity) || TextUtils.isEmpty(str) || cls == null) {
            return;
        }
        BaseActivity baseActivity = (BaseActivity) context;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(str);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                httpSetting.putJsonParam(entry.getKey(), entry.getValue());
            }
        }
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setOnTouchEvent(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.XView2.utils.HttpUtils.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse != null && httpResponse.getFastJsonObject() != null) {
                    try {
                        JDJSON.parseObject(httpResponse.getFastJsonObject().toString(), cls);
                        XView2Utils.runOnUiThread(new BaseRunnable
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001c: INVOKE 
                              (wrap: com.jingdong.common.XView2.utils.BaseRunnable : 0x0019: CONSTRUCTOR 
                              (r1v0 'this' com.jingdong.common.XView2.utils.HttpUtils$1 A[IMMUTABLE_TYPE, THIS])
                              (r2 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                             A[Catch: Exception -> 0x0020, MD:(com.jingdong.common.XView2.utils.HttpUtils$1, java.lang.Object):void (m), WRAPPED] (LINE:4) call: com.jingdong.common.XView2.utils.HttpUtils.1.2.<init>(com.jingdong.common.XView2.utils.HttpUtils$1, java.lang.Object):void type: CONSTRUCTOR)
                             type: STATIC call: com.jingdong.common.XView2.utils.XView2Utils.runOnUiThread(com.jingdong.common.XView2.utils.BaseRunnable):void A[Catch: Exception -> 0x0020, MD:(com.jingdong.common.XView2.utils.BaseRunnable):void (m), TRY_LEAVE] (LINE:4) in method: com.jingdong.common.XView2.utils.HttpUtils.1.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void, file: classes5.dex
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
                            if (r2 == 0) goto L29
                            com.jd.framework.json.JDJSONObject r0 = r2.getFastJsonObject()
                            if (r0 != 0) goto L9
                            goto L29
                        L9:
                            com.jd.framework.json.JDJSONObject r2 = r2.getFastJsonObject()     // Catch: java.lang.Exception -> L20
                            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L20
                            java.lang.Class r0 = r3     // Catch: java.lang.Exception -> L20
                            java.lang.Object r2 = com.jd.framework.json.JDJSON.parseObject(r2, r0)     // Catch: java.lang.Exception -> L20
                            com.jingdong.common.XView2.utils.HttpUtils$1$2 r0 = new com.jingdong.common.XView2.utils.HttpUtils$1$2     // Catch: java.lang.Exception -> L20
                            r0.<init>()     // Catch: java.lang.Exception -> L20
                            com.jingdong.common.XView2.utils.XView2Utils.runOnUiThread(r0)     // Catch: java.lang.Exception -> L20
                            goto L28
                        L20:
                            com.jingdong.common.XView2.utils.HttpUtils$1$3 r2 = new com.jingdong.common.XView2.utils.HttpUtils$1$3
                            r2.<init>()
                            com.jingdong.common.XView2.utils.XView2Utils.runOnUiThread(r2)
                        L28:
                            return
                        L29:
                            com.jingdong.common.XView2.utils.HttpUtils$1$1 r2 = new com.jingdong.common.XView2.utils.HttpUtils$1$1
                            r2.<init>()
                            com.jingdong.common.XView2.utils.XView2Utils.runOnUiThread(r2)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.utils.HttpUtils.AnonymousClass1.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void");
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(final HttpError httpError) {
                        XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.utils.HttpUtils.1.4
                            @Override // com.jingdong.common.XView2.utils.BaseRunnable
                            protected void safeRun() {
                                OnNetFitchData.this.onError(httpError);
                                XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data onError!");
                            }
                        });
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                    public void onProgress(int i2, int i3) {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                    public void onStart() {
                        XView2Utils.runOnUiThread(new BaseRunnable() { // from class: com.jingdong.common.XView2.utils.HttpUtils.1.5
                            @Override // com.jingdong.common.XView2.utils.BaseRunnable
                            protected void safeRun() {
                                OnNetFitchData.this.onStart();
                                XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data onStart!");
                            }
                        });
                    }
                });
                baseActivity.getHttpGroupaAsynPool().add(httpSetting);
            }

            public static void getXViewDataWithObservable(Context context, final String str, Map<String, Object> map, final Observable observable) {
                if (TextUtils.isEmpty(str) || observable == null) {
                    return;
                }
                XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data onStart!");
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setFunctionId(str);
                if (map != null && map.size() > 0) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        httpSetting.putJsonParam(entry.getKey(), entry.getValue());
                    }
                }
                httpSetting.setHost(Configuration.getPortalHost());
                httpSetting.setOnTouchEvent(true);
                httpSetting.setUseFastJsonParser(true);
                httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.XView2.utils.HttpUtils.2
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        XView2Utils.logD("HttpUtils", "functionId: " + str + httpResponse.getString());
                        if (httpResponse != null && httpResponse.getFastJsonObject() != null) {
                            try {
                                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                                if (fastJsonObject == null) {
                                    observable.postMainThread("error", "jdjsonObject\u6570\u636e\u4e3a\u7a7a");
                                    return;
                                }
                                JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
                                if (optJSONObject == null) {
                                    observable.postMainThread("error", "dataJSONObject\u6570\u636e\u4e3a\u7a7a");
                                    return;
                                }
                                XViewConfigEntity xViewConfigEntity = (XViewConfigEntity) JDJSON.parseObject(optJSONObject.toString(), XViewConfigEntity.class);
                                if (xViewConfigEntity != null) {
                                    observable.postMainThread("data", xViewConfigEntity);
                                    PreDownLoadManager.getManager().preDownLoadXViewCache(xViewConfigEntity);
                                    return;
                                }
                                observable.postMainThread("error", "\u6570\u636e\u4e3a\u7a7a");
                                XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data result is null!");
                                return;
                            } catch (Exception unused) {
                                observable.postMainThread("error", "\u6570\u636e\u5f02\u5e38");
                                XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data  exception!");
                                return;
                            }
                        }
                        observable.postMainThread("error", "\u6570\u636e\u4e3a\u7a7a");
                        XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data response is null!");
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        observable.postMainThread("error", "\u7f51\u7edc\u5931\u8d25");
                        XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data onError!" + httpError.toString());
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                    public void onProgress(int i2, int i3) {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                    public void onStart() {
                        observable.postMainThread("onStart", ReqStatus.INIT);
                        XView2Utils.logD("HttpUtils", "functionId: " + str + " fetch data onStart!");
                    }
                });
                HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
            }
        }
