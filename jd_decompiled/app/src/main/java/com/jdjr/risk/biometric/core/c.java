package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jdjr.risk.device.c.r;
import com.jdjr.risk.util.constant.RiskType;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import com.jdjr.risk.util.httputil.LorasHttpCallback;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jdjr.risk.biometric.core.c$3  reason: invalid class name */
    /* loaded from: classes18.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[RiskType.values().length];
            a = iArr;
            try {
                iArr[RiskType.SCREEN_MIRRORING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static void a(Context context, String str, RiskType riskType, String str2, LorasHttpCallback lorasHttpCallback) {
        String str3;
        try {
            if (!BaseInfo.isAgreedPrivacy()) {
                str3 = "0";
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                JSONObject a = d.a().a(context, riskType);
                if (a == null) {
                    b(context, str, riskType, str2, lorasHttpCallback);
                    return;
                }
                str3 = b(context, riskType, a);
            } else {
                str3 = b(context, riskType, b(context, str, riskType, str2));
            }
            lorasHttpCallback.onSuccess(str3);
        } catch (Throwable unused) {
            lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
        }
    }

    public static void a(Context context, String str, final LorasHttpCallback lorasHttpCallback) {
        try {
            String packageName = context.getPackageName();
            String c2 = BiometricManager.getInstance().a().c(context);
            JSONObject jSONObject = new JSONObject();
            if (packageName != null && !packageName.equals("")) {
                jSONObject.put("appId", packageName);
            }
            if (str != null && !str.equals("")) {
                jSONObject.put("bizId", str);
            }
            if (c2 != null && !c2.equals("")) {
                jSONObject.put("token", c2);
            }
            jSONObject.put("time", System.currentTimeMillis());
            com.jdjr.risk.util.httputil.b.a().a(jSONObject, com.jdjr.risk.util.httputil.a.a(), new LorasHttpCallback() { // from class: com.jdjr.risk.biometric.core.c.1
                @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
                public void onFailInCurentThread(int i2, String str2) {
                    LorasHttpCallback.this.onFailInCurentThread(i2, str2);
                }

                @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
                public void onFailInNetThread(int i2, String str2) {
                    LorasHttpCallback.this.onFailInNetThread(i2, str2);
                }

                @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
                public void onSuccess(String str2) {
                    String string;
                    try {
                        JSONObject jSONObject2 = new JSONObject(str2);
                        String string2 = jSONObject2.getString("code");
                        if (string2 == null || !"1".equals(string2) || (string = jSONObject2.getJSONObject("data").getString(DYConstants.RES_CODE)) == null || string.equals("")) {
                            LorasHttpCallback.this.onFailInCurentThread(903, HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR);
                        } else {
                            LorasHttpCallback.this.onSuccess(string);
                        }
                    } catch (Exception unused) {
                        LorasHttpCallback.this.onFailInCurentThread(903, HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR);
                    }
                }
            });
        } catch (Exception unused) {
            lorasHttpCallback.onFailInCurentThread(903, HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, RiskType riskType, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (AnonymousClass3.a[riskType.ordinal()] != 1) {
                    return "0";
                }
                String optString = jSONObject.optString("num", "");
                return !TextUtils.isEmpty(optString) ? r.j(context) >= Integer.valueOf(optString).intValue() ? "1" : "0" : "0";
            } catch (Throwable unused) {
                return "0";
            }
        }
        return "0";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject b(Context context, String str, RiskType riskType, String str2) {
        try {
            JSONObject a = d.a().a(context, riskType);
            if (a == null) {
                String a2 = com.jdjr.risk.biometric.c.e.a(context, str, riskType, str2);
                if (!a2.startsWith(HttpInfoConstants.HTTPT_RSULT_PRE)) {
                    a = new JSONObject(a2);
                }
            }
            if (a != null) {
                return a;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void b(final Context context, final String str, final RiskType riskType, final String str2, final LorasHttpCallback lorasHttpCallback) {
        try {
            new Thread(new Runnable() { // from class: com.jdjr.risk.biometric.core.c.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        c.b(context, riskType, c.b(context, str, riskType, str2));
                        new Handler(Looper.getMainLooper()).post(new Runnable
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0022: INVOKE 
                              (wrap: android.os.Handler : 0x001a: CONSTRUCTOR 
                              (wrap: android.os.Looper : 0x0016: INVOKE  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper A[Catch: all -> 0x0025, MD:():android.os.Looper (c), WRAPPED])
                             A[Catch: all -> 0x0025, MD:(android.os.Looper):void (c), WRAPPED] call: android.os.Handler.<init>(android.os.Looper):void type: CONSTRUCTOR)
                              (wrap: java.lang.Runnable : 0x001f: CONSTRUCTOR 
                              (r4v0 'this' com.jdjr.risk.biometric.core.c$2 A[IMMUTABLE_TYPE, THIS])
                              (r0 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                             A[Catch: all -> 0x0025, MD:(com.jdjr.risk.biometric.core.c$2, java.lang.String):void (m), WRAPPED] call: com.jdjr.risk.biometric.core.c.2.1.<init>(com.jdjr.risk.biometric.core.c$2, java.lang.String):void type: CONSTRUCTOR)
                             type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[Catch: all -> 0x0025, MD:(java.lang.Runnable):boolean (c), TRY_LEAVE] in method: com.jdjr.risk.biometric.core.c.2.run():void, file: classes18.dex
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
                            this = this;
                            android.content.Context r0 = r1     // Catch: java.lang.Throwable -> L25
                            java.lang.String r1 = r2     // Catch: java.lang.Throwable -> L25
                            com.jdjr.risk.util.constant.RiskType r2 = r3     // Catch: java.lang.Throwable -> L25
                            java.lang.String r3 = r4     // Catch: java.lang.Throwable -> L25
                            org.json.JSONObject r0 = com.jdjr.risk.biometric.core.c.a(r0, r1, r2, r3)     // Catch: java.lang.Throwable -> L25
                            android.content.Context r1 = r1     // Catch: java.lang.Throwable -> L25
                            com.jdjr.risk.util.constant.RiskType r2 = r3     // Catch: java.lang.Throwable -> L25
                            java.lang.String r0 = com.jdjr.risk.biometric.core.c.a(r1, r2, r0)     // Catch: java.lang.Throwable -> L25
                            android.os.Handler r1 = new android.os.Handler     // Catch: java.lang.Throwable -> L25
                            android.os.Looper r2 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L25
                            r1.<init>(r2)     // Catch: java.lang.Throwable -> L25
                            com.jdjr.risk.biometric.core.c$2$1 r2 = new com.jdjr.risk.biometric.core.c$2$1     // Catch: java.lang.Throwable -> L25
                            r2.<init>()     // Catch: java.lang.Throwable -> L25
                            r1.post(r2)     // Catch: java.lang.Throwable -> L25
                        L25:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.biometric.core.c.AnonymousClass2.run():void");
                    }
                }).start();
            } catch (Throwable unused) {
            }
        }
    }
