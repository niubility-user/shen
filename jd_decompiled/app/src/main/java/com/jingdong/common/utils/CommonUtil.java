package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.entity.GraduallyPayEntity;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.pay.AndroidPayConstants;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.UPPayAssistEx;

/* loaded from: classes6.dex */
public abstract class CommonUtil extends CommonBase {
    private static final String TAG = "CommonUtil";
    protected static JDLocationCacheOption mLbsCacheOption = new JDLocationCacheOption();
    protected static String seType;
    protected static String tn;
    private long lastTounionAndWeiXinPayTimeMillis;
    protected String reAppId;
    protected String reExtendMap;
    protected String reJDPayChannel;
    protected String reSdkParam;

    public static void androidPay(final Activity activity, final String str, final String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.utils.CommonUtil.2
            @Override // java.lang.Runnable
            public void run() {
                Activity activity2 = activity;
                if (activity2 != null) {
                    UPPayAssistEx.startSEPay(activity2, null, null, str, "00", str2);
                }
            }
        });
    }

    public static void doPayFinishForward(String str, final CommonBase.BrowserCashierUrlListener browserCashierUrlListener) {
        queryGetSuccessurl(str, new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.CommonUtil.5
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                try {
                    String string = fastJsonObject.getString("code");
                    Bundle bundle = new Bundle();
                    if (OKLog.I) {
                        OKLog.i(CommonUtil.TAG, "doPayFinishForward.code=" + string);
                    }
                    if ("0".equals(string)) {
                        String string2 = fastJsonObject.getString("data");
                        bundle.putString("businessMapInfo", fastJsonObject.optString("businessMapInfo"));
                        if (OKLog.I) {
                            OKLog.i(CommonUtil.TAG, "doPayFinishForward.url=" + string2);
                        }
                        if (fastJsonObject.containsKey("graduallyPay")) {
                            bundle.putSerializable(AndroidPayConstants.INTENT_EXTAS_GRADUALLY_PAY_ENTITY_KEY, new GraduallyPayEntity(fastJsonObject));
                        }
                        String string3 = fastJsonObject.getString("closeWebView");
                        if (!TextUtils.isEmpty(string3)) {
                            bundle.putString("closeWebView", string3);
                        }
                        if (browserCashierUrlListener != null) {
                            if (!TextUtils.isEmpty(string2)) {
                                browserCashierUrlListener.onComplete(string2, bundle);
                                return;
                            } else {
                                browserCashierUrlListener.onError(null);
                                return;
                            }
                        }
                        return;
                    }
                    CommonBase.BrowserCashierUrlListener browserCashierUrlListener2 = browserCashierUrlListener;
                    if (browserCashierUrlListener2 != null) {
                        browserCashierUrlListener2.onError(null);
                    }
                } catch (Exception e2) {
                    OKLog.e(CommonUtil.TAG, e2);
                    CommonBase.BrowserCashierUrlListener browserCashierUrlListener3 = browserCashierUrlListener;
                    if (browserCashierUrlListener3 != null) {
                        browserCashierUrlListener3.onError(null);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                CommonBase.BrowserCashierUrlListener browserCashierUrlListener2 = browserCashierUrlListener;
                if (browserCashierUrlListener2 != null) {
                    browserCashierUrlListener2.onError(null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                CommonBase.BrowserCashierUrlListener browserCashierUrlListener2 = browserCashierUrlListener;
                if (browserCashierUrlListener2 == null || !(browserCashierUrlListener2 instanceof CommonBase.BrowserAllUrlListener)) {
                    return;
                }
                ((CommonBase.BrowserAllUrlListener) browserCashierUrlListener2).onReady();
            }
        });
    }

    public static String getSeType() {
        return seType;
    }

    public static String getUnpayTN() {
        return tn;
    }

    public static void pay(final Activity activity, final String str) {
        if (TextUtils.isEmpty(tn) || activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.utils.CommonUtil.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    UPPayAssistEx.startPay(activity, null, null, str, "00");
                } catch (Exception e2) {
                    if (Log.D) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    private static void queryGetSuccessurl(String str, HttpGroup.OnCommonListener onCommonListener) {
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setNotifyUser(true);
        httpSetting.setUrl(Configuration.getPayUrl());
        httpSetting.setEffect(1);
        String payAppID = CommonBase.getPayAppID();
        httpSetting.putJsonParam("payId", str);
        httpSetting.putJsonParam("appId", payAppID);
        httpSetting.putJsonParam("version9Flag", ABTestUtils.is900UIStyle() ? "1" : "0");
        httpSetting.setFunctionId(JumpUtils.FUNCTION_ID_GETSUCCESSURL);
        try {
            mLbsCacheOption.setBusinessId("3ec559ecab741969546695d4c1f725ed");
            String valueOf = String.valueOf(JDLocationCache.getInstance().getLocation(mLbsCacheOption).getLng());
            String valueOf2 = String.valueOf(JDLocationCache.getInstance().getLocation(mLbsCacheOption).getLat());
            httpSetting.putJsonParam(PdLVBody.LONGITUDE, DesCommonUtils.encryptThreeDESECB(valueOf, "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8"));
            httpSetting.putJsonParam(PdLVBody.LATITUDE, DesCommonUtils.encryptThreeDESECB(valueOf2, "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8"));
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "fk_Exception-->" + e2.getMessage());
            }
        }
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(onCommonListener);
        httpGroup.add(httpSetting);
    }

    public static void queryPayResult(JDJSONObject jDJSONObject, CommonBase.BrowserCashierNewUrlListener browserCashierNewUrlListener) {
        queryPayResult(jDJSONObject, browserCashierNewUrlListener, 1);
    }

    public void resetCommonValue() {
        this.reAppId = "";
        this.reSdkParam = "";
        this.reExtendMap = "";
    }

    public void doJDPay(String str, String str2, String str3, String str4, Activity activity) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("APP_ID", str);
        bundle.putString("PAY_PARAM", str3);
        bundle.putBoolean("JDPAY_TOAST_PRINT", false);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("JDPAY_CHANNEL_TYPE", str2);
            bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER_V4");
        } else {
            bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER");
        }
        if (!TextUtils.isEmpty(str4)) {
            bundle.putString("JDPAY_EXTEND_INFO", str4);
        }
        try {
            bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
        } catch (Exception e2) {
            OKLog.e(TAG, "sessionKey.Exception=", e2);
        }
        DeeplinkJDpaySdkHelper.startJDPayActivityForResult(activity, bundle, 10);
    }

    @Override // com.jingdong.common.utils.ICommon
    public void doPay(Activity activity, String str) {
        pay(activity, str);
    }

    public void reDoJDPay(Activity activity) {
        doJDPay(this.reAppId, this.reJDPayChannel, this.reSdkParam, this.reExtendMap, activity);
    }

    public void showNoticeDialogStyle1(final String str) {
        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.utils.CommonUtil.4
            {
                CommonUtil.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                final JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1((Context) BaseFrameUtil.getInstance().getCurrentMyActivity(), str, JdSdk.getInstance().getApplication().getString(R.string.ok));
                createJdDialogWithStyle1.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.CommonUtil.4.1
                    {
                        AnonymousClass4.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        createJdDialogWithStyle1.cancel();
                    }
                });
                createJdDialogWithStyle1.show();
            }
        });
    }

    public void unionAndWeiXinPay(Bundle bundle) {
        unionAndWeiXinPay((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity(), bundle, null);
    }

    public static void doPayFinishForward(String str, final CommonBase.BrowserNewUrlListener browserNewUrlListener) {
        queryGetSuccessurl(str, new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.CommonUtil.6
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                try {
                    String string = fastJsonObject.getString("code");
                    Bundle bundle = new Bundle();
                    if (OKLog.I) {
                        OKLog.i(CommonUtil.TAG, "doPayFinishForward.code=" + string);
                    }
                    if ("0".equals(string)) {
                        String string2 = fastJsonObject.getString("data");
                        String string3 = fastJsonObject.getString("closeWebView");
                        bundle.putString("businessMapInfo", fastJsonObject.optString("businessMapInfo"));
                        if (!TextUtils.isEmpty(string3)) {
                            bundle.putString("closeWebView", string3);
                        }
                        if (OKLog.I) {
                            OKLog.i(CommonUtil.TAG, "doPayFinishForward.url=" + string2);
                        }
                        if (browserNewUrlListener != null) {
                            if (!TextUtils.isEmpty(string2)) {
                                browserNewUrlListener.onComplete(string2);
                                return;
                            } else {
                                browserNewUrlListener.onError(null);
                                return;
                            }
                        }
                        return;
                    }
                    CommonBase.BrowserNewUrlListener browserNewUrlListener2 = browserNewUrlListener;
                    if (browserNewUrlListener2 != null) {
                        browserNewUrlListener2.onError(null);
                    }
                } catch (Exception e2) {
                    OKLog.e(CommonUtil.TAG, e2);
                    CommonBase.BrowserNewUrlListener browserNewUrlListener3 = browserNewUrlListener;
                    if (browserNewUrlListener3 != null) {
                        browserNewUrlListener3.onError(null);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                CommonBase.BrowserNewUrlListener browserNewUrlListener2 = browserNewUrlListener;
                if (browserNewUrlListener2 != null) {
                    browserNewUrlListener2.onError(null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                CommonBase.BrowserNewUrlListener browserNewUrlListener2 = browserNewUrlListener;
                if (browserNewUrlListener2 == null || !(browserNewUrlListener2 instanceof CommonBase.BrowserAllUrlListener)) {
                    return;
                }
                ((CommonBase.BrowserAllUrlListener) browserNewUrlListener2).onReady();
            }
        });
    }

    public static void queryPayResult(JDJSONObject jDJSONObject, final CommonBase.BrowserCashierNewUrlListener browserCashierNewUrlListener, final int i2) {
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        jDJSONObject.optString("octopusPay");
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setNotifyUser(true);
        httpSetting.setUrl(Configuration.getPayUrl());
        httpSetting.setEffect(1);
        jDJSONObject.put("appId", (Object) CommonBase.getPayAppID());
        jDJSONObject.put("version9Flag", (Object) (ABTestUtils.is900UIStyle() ? "1" : "0"));
        httpSetting.setJsonParams(jDJSONObject);
        httpSetting.setFunctionId(JumpUtils.FUNCTION_ID_GETSUCCESSURL);
        httpSetting.setUseFastJsonParser(true);
        try {
            mLbsCacheOption.setBusinessId("3ec559ecab741969546695d4c1f725ed");
            String valueOf = String.valueOf(JDLocationCache.getInstance().getLocation(mLbsCacheOption).getLng());
            String valueOf2 = String.valueOf(JDLocationCache.getInstance().getLocation(mLbsCacheOption).getLat());
            httpSetting.putJsonParam(PdLVBody.LONGITUDE, DesCommonUtils.encryptThreeDESECB(valueOf, "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8"));
            httpSetting.putJsonParam(PdLVBody.LATITUDE, DesCommonUtils.encryptThreeDESECB(valueOf2, "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8"));
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "fk_Exception-->" + e2.getMessage());
            }
        }
        httpSetting.setListener(new HttpGroup.OnCommonListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00bb: INVOKE 
              (r3v0 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
              (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnCommonListener : 0x00b8: CONSTRUCTOR 
              (r9v0 'i2' int A[DONT_INLINE])
              (r8v0 'browserCashierNewUrlListener' com.jingdong.common.utils.CommonBase$BrowserCashierNewUrlListener A[DONT_INLINE])
              (r2 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(int, com.jingdong.common.utils.CommonBase$BrowserCashierNewUrlListener, java.lang.String):void (m), WRAPPED] (LINE:23) call: com.jingdong.common.utils.CommonUtil.7.<init>(int, com.jingdong.common.utils.CommonBase$BrowserCashierNewUrlListener, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:23) in method: com.jingdong.common.utils.CommonUtil.queryPayResult(com.jd.framework.json.JDJSONObject, com.jingdong.common.utils.CommonBase$BrowserCashierNewUrlListener, int):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            java.lang.String r0 = "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8"
            com.jingdong.jdsdk.network.toolbox.HttpGroupSetting r1 = com.jingdong.common.network.HttpGroupUtils.createNewSettings()
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.setType(r2)
            com.jingdong.common.BaseFrameUtil r2 = com.jingdong.common.BaseFrameUtil.getInstance()
            com.jingdong.common.frame.IMyActivity r2 = r2.getCurrentMyActivity()
            android.app.Activity r2 = (android.app.Activity) r2
            r1.setMyActivity(r2)
            com.jingdong.jdsdk.network.toolbox.HttpGroup r1 = com.jingdong.jdsdk.network.toolbox.HttpGroup.getHttpGroup(r1)
            java.lang.String r2 = "octopusPay"
            java.lang.String r2 = r7.optString(r2)
            com.jingdong.jdsdk.network.toolbox.HttpSetting r3 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r3.<init>()
            r4 = 1
            r3.setNotifyUser(r4)
            java.lang.String r5 = com.jingdong.jdsdk.config.Configuration.getPayUrl()
            r3.setUrl(r5)
            r3.setEffect(r4)
            java.lang.String r5 = com.jingdong.common.utils.CommonBase.getPayAppID()
            java.lang.String r6 = "appId"
            r7.put(r6, r5)
            boolean r5 = com.jingdong.common.utils.ABTestUtils.is900UIStyle()
            if (r5 == 0) goto L47
            java.lang.String r5 = "1"
            goto L49
        L47:
            java.lang.String r5 = "0"
        L49:
            java.lang.String r6 = "version9Flag"
            r7.put(r6, r5)
            r3.setJsonParams(r7)
            java.lang.String r7 = "getSuccessUrl"
            r3.setFunctionId(r7)
            r3.setUseFastJsonParser(r4)
            com.jingdong.common.lbs.jdlocation.JDLocationCacheOption r7 = com.jingdong.common.utils.CommonUtil.mLbsCacheOption     // Catch: java.lang.Exception -> L97
            java.lang.String r4 = "3ec559ecab741969546695d4c1f725ed"
            r7.setBusinessId(r4)     // Catch: java.lang.Exception -> L97
            com.jingdong.common.lbs.jdlocation.JDLocationCache r7 = com.jingdong.common.lbs.jdlocation.JDLocationCache.getInstance()     // Catch: java.lang.Exception -> L97
            com.jingdong.common.lbs.jdlocation.JDLocationCacheOption r4 = com.jingdong.common.utils.CommonUtil.mLbsCacheOption     // Catch: java.lang.Exception -> L97
            com.jingdong.common.lbs.jdlocation.JDLocation r7 = r7.getLocation(r4)     // Catch: java.lang.Exception -> L97
            double r4 = r7.getLng()     // Catch: java.lang.Exception -> L97
            java.lang.String r7 = java.lang.String.valueOf(r4)     // Catch: java.lang.Exception -> L97
            com.jingdong.common.lbs.jdlocation.JDLocationCache r4 = com.jingdong.common.lbs.jdlocation.JDLocationCache.getInstance()     // Catch: java.lang.Exception -> L97
            com.jingdong.common.lbs.jdlocation.JDLocationCacheOption r5 = com.jingdong.common.utils.CommonUtil.mLbsCacheOption     // Catch: java.lang.Exception -> L97
            com.jingdong.common.lbs.jdlocation.JDLocation r4 = r4.getLocation(r5)     // Catch: java.lang.Exception -> L97
            double r4 = r4.getLat()     // Catch: java.lang.Exception -> L97
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Exception -> L97
            java.lang.String r5 = "longitude"
            java.lang.String r7 = com.jingdong.common.entity.DesCommonUtils.encryptThreeDESECB(r7, r0)     // Catch: java.lang.Exception -> L97
            r3.putJsonParam(r5, r7)     // Catch: java.lang.Exception -> L97
            java.lang.String r7 = "latitude"
            java.lang.String r0 = com.jingdong.common.entity.DesCommonUtils.encryptThreeDESECB(r4, r0)     // Catch: java.lang.Exception -> L97
            r3.putJsonParam(r7, r0)     // Catch: java.lang.Exception -> L97
            goto Lb6
        L97:
            r7 = move-exception
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D
            if (r0 == 0) goto Lb6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "fk_Exception-->"
            r0.append(r4)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            java.lang.String r0 = "CommonUtil"
            com.jingdong.sdk.oklog.OKLog.d(r0, r7)
        Lb6:
            com.jingdong.common.utils.CommonUtil$7 r7 = new com.jingdong.common.utils.CommonUtil$7
            r7.<init>()
            r3.setListener(r7)
            r1.add(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.CommonUtil.queryPayResult(com.jd.framework.json.JDJSONObject, com.jingdong.common.utils.CommonBase$BrowserCashierNewUrlListener, int):void");
    }

    public void unionAndWeiXinPay(final Activity activity, Bundle bundle, final CommonBase.ProgresslListener progresslListener) {
        if (System.currentTimeMillis() - this.lastTounionAndWeiXinPayTimeMillis < 2000) {
            return;
        }
        this.lastTounionAndWeiXinPayTimeMillis = System.currentTimeMillis();
        if (bundle == null) {
            return;
        }
        final String string = bundle.getString("payId");
        final String string2 = bundle.getString("type");
        bundle.getString("jdPayChannel");
        String string3 = bundle.getString("appId");
        if (TextUtils.isEmpty(string3)) {
            string3 = CommonBase.getPayAppID();
        }
        if (TextUtils.isEmpty(string3) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string)) {
            return;
        }
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        createNewSettings.setMyActivity((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.setNotifyUser(true);
        httpSetting.setUrl(Configuration.getPayUrl());
        if ("4".equals(string2) || "5".equals(string2)) {
            if (!checkSDKForPay()) {
                return;
            }
            httpSetting.setFunctionId(JumpUtils.FUNCTION_ID_UNIONPAYV2);
            httpSetting.putJsonParam("type", string2);
        }
        if ("10".equals(string2)) {
            httpSetting.setFunctionId(JumpUtils.FUNCTION_ID_WEIXINPAY);
        }
        if ("12".equals(string2)) {
            httpSetting.setFunctionId(JumpUtils.FUNCTION_ID_JDPAYV2);
        }
        if ("13".equals(string2)) {
            httpSetting.setFunctionId("qqWalletPay");
        }
        if ("6".equals(string2)) {
            httpSetting.setFunctionId("octopusPay");
        }
        httpSetting.setEffect(1);
        httpSetting.putJsonParam("payId", string);
        httpSetting.putJsonParam("appId", string3);
        httpSetting.setUseFastJsonParser(true);
        new ExceptionReporter(httpSetting);
        httpSetting.setListener(new HttpGroup.OnCommonListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00f1: INVOKE 
              (r10v0 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
              (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnCommonListener : 0x00ee: CONSTRUCTOR 
              (r11v0 'this' com.jingdong.common.utils.CommonUtil A[IMMUTABLE_TYPE, THIS])
              (r14v0 'progresslListener' com.jingdong.common.utils.CommonBase$ProgresslListener A[DONT_INLINE])
              (r4v1 'string2' java.lang.String A[DONT_INLINE])
              (r5v0 'string' java.lang.String A[DONT_INLINE])
              (r12v0 'activity' android.app.Activity A[DONT_INLINE])
              (r7 I:com.jingdong.jdsdk.network.toolbox.ExceptionReporter A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r8 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.utils.CommonUtil, com.jingdong.common.utils.CommonBase$ProgresslListener, java.lang.String, java.lang.String, android.app.Activity, com.jingdong.jdsdk.network.toolbox.ExceptionReporter, java.lang.String):void (m), WRAPPED] (LINE:37) call: com.jingdong.common.utils.CommonUtil.3.<init>(com.jingdong.common.utils.CommonUtil, com.jingdong.common.utils.CommonBase$ProgresslListener, java.lang.String, java.lang.String, android.app.Activity, com.jingdong.jdsdk.network.toolbox.ExceptionReporter, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:37) in method: com.jingdong.common.utils.CommonUtil.unionAndWeiXinPay(android.app.Activity, android.os.Bundle, com.jingdong.common.utils.CommonBase$ProgresslListener):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
            */
        /*
            this = this;
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r11.lastTounionAndWeiXinPayTimeMillis
            long r0 = r0 - r2
            r2 = 2000(0x7d0, double:9.88E-321)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto Le
            return
        Le:
            long r0 = java.lang.System.currentTimeMillis()
            r11.lastTounionAndWeiXinPayTimeMillis = r0
            if (r13 != 0) goto L17
            return
        L17:
            java.lang.String r0 = "payId"
            java.lang.String r5 = r13.getString(r0)
            java.lang.String r1 = "type"
            java.lang.String r4 = r13.getString(r1)
            java.lang.String r2 = "jdPayChannel"
            java.lang.String r8 = r13.getString(r2)
            java.lang.String r2 = "appId"
            java.lang.String r13 = r13.getString(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r13)
            if (r3 == 0) goto L39
            java.lang.String r13 = com.jingdong.common.utils.CommonBase.getPayAppID()
        L39:
            boolean r3 = android.text.TextUtils.isEmpty(r13)
            if (r3 != 0) goto Lf7
            boolean r3 = android.text.TextUtils.isEmpty(r4)
            if (r3 != 0) goto Lf7
            boolean r3 = android.text.TextUtils.isEmpty(r5)
            if (r3 == 0) goto L4d
            goto Lf7
        L4d:
            com.jingdong.jdsdk.network.toolbox.HttpGroupSetting r3 = com.jingdong.common.network.HttpGroupUtils.createNewSettings()
            r6 = 1000(0x3e8, float:1.401E-42)
            r3.setType(r6)
            com.jingdong.common.BaseFrameUtil r6 = com.jingdong.common.BaseFrameUtil.getInstance()
            com.jingdong.common.frame.IMyActivity r6 = r6.getCurrentMyActivity()
            android.app.Activity r6 = (android.app.Activity) r6
            r3.setMyActivity(r6)
            com.jingdong.jdsdk.network.toolbox.HttpGroup r9 = com.jingdong.jdsdk.network.toolbox.HttpGroup.getHttpGroup(r3)
            com.jingdong.jdsdk.network.toolbox.HttpSetting r10 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r10.<init>()
            com.jingdong.jdsdk.config.HostConfig r3 = com.jingdong.jdsdk.config.HostConfig.getInstance()
            java.lang.String r6 = "personal_host"
            java.lang.String r3 = r3.getHost(r6)
            r10.setHost(r3)
            r3 = 1
            r10.setNotifyUser(r3)
            java.lang.String r6 = com.jingdong.jdsdk.config.Configuration.getPayUrl()
            r10.setUrl(r6)
            java.lang.String r6 = "4"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L94
            java.lang.String r6 = "5"
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto La3
        L94:
            boolean r6 = r11.checkSDKForPay()
            if (r6 != 0) goto L9b
            return
        L9b:
            java.lang.String r6 = "unionPayV2"
            r10.setFunctionId(r6)
            r10.putJsonParam(r1, r4)
        La3:
            java.lang.String r1 = "10"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto Lb0
            java.lang.String r1 = "weixinPay"
            r10.setFunctionId(r1)
        Lb0:
            java.lang.String r1 = "12"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto Lbd
            java.lang.String r1 = "jdPayV2"
            r10.setFunctionId(r1)
        Lbd:
            java.lang.String r1 = "13"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto Lca
            java.lang.String r1 = "qqWalletPay"
            r10.setFunctionId(r1)
        Lca:
            java.lang.String r1 = "6"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto Ld7
            java.lang.String r1 = "octopusPay"
            r10.setFunctionId(r1)
        Ld7:
            r10.setEffect(r3)
            r10.putJsonParam(r0, r5)
            r10.putJsonParam(r2, r13)
            r10.setUseFastJsonParser(r3)
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter r7 = new com.jingdong.jdsdk.network.toolbox.ExceptionReporter
            r7.<init>(r10)
            com.jingdong.common.utils.CommonUtil$3 r13 = new com.jingdong.common.utils.CommonUtil$3
            r1 = r13
            r2 = r11
            r3 = r14
            r6 = r12
            r1.<init>()
            r10.setListener(r13)
            r9.add(r10)
        Lf7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.CommonUtil.unionAndWeiXinPay(android.app.Activity, android.os.Bundle, com.jingdong.common.utils.CommonBase$ProgresslListener):void");
    }
}
