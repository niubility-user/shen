package com.jingdong.common.network;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.dialingv2.DialingManager;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.toolbox.JDNetworkStatisticTool;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.verify.VerifyPrivacyInfoProxy;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleData;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleListener;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleOption;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.h.a.a;
import com.jingdong.app.mall.mocker.MockerUtils;
import com.jingdong.app.mall.utils.j;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.login.DeviceFinger;
import com.jingdong.common.login.LoginLocationUtil;
import com.jingdong.common.login.LoginReportUtil;
import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.network.AbsDialogController;
import com.jingdong.common.network.encrypt.EncryptBodyController;
import com.jingdong.common.network.encrypt.EncryptHeaderController;
import com.jingdong.common.network.encrypt.EncryptStatParamController;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.ui.JdDialogParam;
import com.jingdong.common.ui.LottieLoadingView;
import com.jingdong.common.utils.BitmapkitUtils;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.login.LoginUserHelper;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.db.entry.CacheFileTable;
import com.jingdong.jdsdk.network.dependency.IAppProxy;
import com.jingdong.jdsdk.network.dependency.IBusinessJsonCodeEventListener;
import com.jingdong.jdsdk.network.dependency.ICustomUIComponent;
import com.jingdong.jdsdk.network.dependency.IDownloadDomainsResolver;
import com.jingdong.jdsdk.network.dependency.IExceptionReportHandler;
import com.jingdong.jdsdk.network.dependency.IExternalDebugConfig;
import com.jingdong.jdsdk.network.dependency.IGatewayRespHeaderListener;
import com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin;
import com.jingdong.jdsdk.network.dependency.IHardGuardVerifyPlugin;
import com.jingdong.jdsdk.network.dependency.IHttpDnsController;
import com.jingdong.jdsdk.network.dependency.ILoginUserController;
import com.jingdong.jdsdk.network.dependency.INetworkController;
import com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin;
import com.jingdong.jdsdk.network.dependency.IRuntimeConfig;
import com.jingdong.jdsdk.network.dependency.ISignatureHandler;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.network.utils.HttpUtils;
import com.jingdong.jdsdk.network.utils.JDDnsUtil;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.jingdong.jdsdk.network.utils.NetworkXConfig;
import com.jingdong.jdsdk.network.utils.StatSharePreferenceUtil;
import com.jingdong.jdsdk.utils.JsonEncryptUtil;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.lib.netdiagnosis.NetDiagnosisController;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.listener.IFailureController;
import com.jingdong.sdk.jdhttpdns.listener.IKeyParamProvider;
import com.jingdong.sdk.jdhttpdns.listener.IReporter;
import com.jingdong.sdk.jdhttpdns.listener.IRestrictController;
import com.jingdong.sdk.jdhttpdns.listener.IStatReporter;
import com.jingdong.sdk.jdhttpdns.pojo.FailEvent;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.ProxyConfig;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class JDNetworkDependencyFactory {
    public static IAppProxy getAppProxy() {
        return new IAppProxy() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.10
            @Override // com.jingdong.jdsdk.network.dependency.IAppProxy
            public void clearCacheFiles() {
                CacheFileTable.clearCacheFiles();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IAppProxy
            public void exitApp() {
                BaseFrameUtil.exitAll();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IAppProxy
            public Activity getCurrentMyActivity() {
                return ActivityManagerUtil.getScreenManager().currentActivity();
            }
        };
    }

    public static IBusinessJsonCodeEventListener getBusinessJsonCodeEventListener() {
        return new IBusinessJsonCodeEventListener() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.21
            @Override // com.jingdong.jdsdk.network.dependency.IBusinessJsonCodeEventListener
            public void onJsonCodeReceive(String str, HttpSetting httpSetting, HttpResponse httpResponse) {
                Map<String, String> header;
                if (httpSetting == null || httpResponse == null) {
                    return;
                }
                try {
                    String functionId = httpSetting.getFunctionId();
                    if (!TextUtils.isEmpty(functionId) && !TextUtils.isEmpty(str)) {
                        String config = JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "jsonCode", "functionId_wl", "[]");
                        String config2 = JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "jsonCode", "jsonCode_wl", "[]");
                        List parseArray = JDJSON.parseArray(config, String.class);
                        List parseArray2 = JDJSON.parseArray(config2, String.class);
                        if (parseArray == null || parseArray.isEmpty() || parseArray2 == null || parseArray2.isEmpty() || (header = httpResponse.getHeader()) == null || header.isEmpty() || !parseArray.contains(functionId) || !parseArray2.contains(str)) {
                            return;
                        }
                        String headerFieldIgnoreCase = HttpUtils.getHeaderFieldIgnoreCase(header, "X-API-Request-Id");
                        if (OKLog.D) {
                            OKLog.d("onJsonCodeReceive", "\u547d\u4e2d\u62e6\u622a code: " + str + ", functionId: " + functionId + ", requestId: " + headerFieldIgnoreCase);
                        }
                        if (TextUtils.isEmpty(headerFieldIgnoreCase)) {
                            return;
                        }
                        String jsonParamsString = httpSetting.getJsonParamsString();
                        String string = httpResponse.getString();
                        if (OKLog.D) {
                            OKLog.d("onJsonCodeReceive", "\u89e6\u53d1\u57cb\u70b9\u4e0a\u62a5 bodyParam: " + jsonParamsString + "\n functionId:  " + functionId + "\n requestId:   " + headerFieldIgnoreCase + "\n responseStr:  " + string);
                        }
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("requestId", (Object) headerFieldIgnoreCase);
                        jDJSONObject.put("functionId", (Object) functionId);
                        jDJSONObject.put("responseStr", (Object) string);
                        if (TextUtils.isEmpty(jsonParamsString)) {
                            jsonParamsString = "";
                        }
                        jDJSONObject.put("bodyParam", (Object) jsonParamsString);
                        jDJSONObject.put("code", (Object) str);
                        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "network_component_error_code_expo", "", "", "", "", jDJSONObject.toString(), null);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
    }

    public static ICustomUIComponent getCustomUIComponentDependency() {
        return new ICustomUIComponent() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.9
            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public Dialog createJdDialogNewStyle(Context context, JDGetWayQueueTools.JdDialogParam jdDialogParam, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener) {
                JdDialogParam jdDialogParam2 = new JdDialogParam();
                jdDialogParam2.message = jdDialogParam.message;
                jdDialogParam2.countDownTime = jdDialogParam.countDownTime;
                JDGetWayQueueTools.JdDialogParam.ButtonParam buttonParam = jdDialogParam.left;
                jdDialogParam2.left = new JdDialogParam.ButtonParam(buttonParam.title, buttonParam.jumpUrl);
                JDGetWayQueueTools.JdDialogParam.ButtonParam buttonParam2 = jdDialogParam.right;
                jdDialogParam2.right = new JdDialogParam.ButtonParam(buttonParam2.title, buttonParam2.jumpUrl);
                JDDialog createJdDialog601 = JDDialogFactory.getInstance().createJdDialog601(context, jdDialogParam2);
                createJdDialog601.setCanceledOnTouchOutside(false);
                createJdDialog601.setOnLeftButtonClickListener(onClickListener);
                createJdDialog601.setOnRightButtonClickListener(onClickListener2);
                createJdDialog601.setOnCancelListener(onCancelListener);
                return createJdDialog601;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public Dialog createJdDialogWithStyleTimer(Context context, String str, String str2, int i2, View.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener) {
                JDDialog createJdDialogWithStyleTimer = JDDialogFactory.getInstance().createJdDialogWithStyleTimer(context, str, str2, i2);
                createJdDialogWithStyleTimer.setCanceledOnTouchOutside(false);
                createJdDialogWithStyleTimer.setOnLeftButtonClickListener(onClickListener);
                createJdDialogWithStyleTimer.setOnCancelListener(onCancelListener);
                return createJdDialogWithStyleTimer;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public View createProgressBar() {
                return BaseApplication.getLottieLoadingView();
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void releaseResource(View view) {
                LottieLoadingView.freeLottieMemory(view);
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void startTimeCountNew(Dialog dialog) {
                if (dialog == null || !(dialog instanceof JDDialog)) {
                    return;
                }
                ((JDDialog) dialog).startTimeCountNew();
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void updateCountDown(Dialog dialog, int i2) {
                if (dialog instanceof JDDialog) {
                    ((JDDialog) dialog).setCountdown(i2);
                }
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public void updateTick(Dialog dialog, long j2) {
                if (dialog instanceof JDDialog) {
                    ((JDDialog) dialog).updateTick(j2);
                }
            }

            @Override // com.jingdong.jdsdk.network.dependency.ICustomUIComponent
            public Dialog createJdDialogWithStyleTimer(Context context, String str, String str2, String str3, int i2, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener) {
                JDDialog createJdDialogWithStyleTimer = JDDialogFactory.getInstance().createJdDialogWithStyleTimer(context, str, str2, str3, i2, str4);
                createJdDialogWithStyleTimer.setCanceledOnTouchOutside(false);
                createJdDialogWithStyleTimer.setOnLeftButtonClickListener(onClickListener);
                createJdDialogWithStyleTimer.setOnRightButtonClickListener(onClickListener2);
                createJdDialogWithStyleTimer.setOnCancelListener(onCancelListener);
                return createJdDialogWithStyleTimer;
            }
        };
    }

    public static IDownloadDomainsResolver getDownloadDomainResolver() {
        return new IDownloadDomainsResolver() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.20
            @Override // com.jingdong.jdsdk.network.dependency.IDownloadDomainsResolver
            public String getConfig() {
                return SwitchQueryFetcher.getSwitchStringValue(HybridSDK.SWITCH_XCACHE_RETRYDOMAIN, "");
            }
        };
    }

    public static IExceptionReportHandler getExceptionReportDelegate() {
        return new IExceptionReportHandler() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.6
            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportDownloadDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4) {
                ExceptionReporter.reportCDNDowngradeData(str, str2, str3, z, i2, str4, "app_download", "\u539f\u751f\u4e0b\u8f7d");
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportDuplicatePicException(String str) {
                ExceptionReporter.reportDuplicatePicException(str);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttp2PingTimeoutException(String str, String str2) {
                ExceptionReporter.reportHttp2PingTimeoutException(str, str2);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttpBusinessException(HttpSetting httpSetting, HttpResponse httpResponse) {
                new ExceptionReporter(httpSetting).reportHttpBusinessException(httpResponse);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttpException(String str, HttpSetting httpSetting, HttpError httpError, String str2) {
                ExceptionReporter.reportHttpException(str, httpSetting, httpError, str2);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void reportHttpsErrorToServer(String str, HttpSetting httpSetting, Throwable th) {
                ExceptionReporter.reportHttpsErrorToServer(str, httpSetting, th);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void sendMtaCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
                JDMtaUtils.sendCommonData(context, str, str2, str3, obj, str4, str5, str6, str7);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExceptionReportHandler
            public void sendPropertyData(Context context, String str, String str2, String str3, String str4) {
                JDMtaUtils.sendPropertyData(context, str, str2, str3, str4);
            }
        };
    }

    public static IExternalDebugConfig getExternalDebugConfigImpl() {
        return new IExternalDebugConfig() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.8
            @Override // com.jingdong.jdsdk.network.dependency.IExternalDebugConfig
            public void addMockerIdName(HttpSetting httpSetting) {
                if (OKLog.D) {
                    try {
                        URL mockerUrl = MockerUtils.getInstance().getMockerUrl(new URL(httpSetting.getUrl()));
                        if (mockerUrl != null) {
                            httpSetting.setUrl(mockerUrl.toString());
                        } else if (httpSetting.getHost().equals(Configuration.UNIFORM_HOST_TEST)) {
                            URL url = new URL(httpSetting.getUrl());
                            if ("https".equals(url.getProtocol())) {
                                httpSetting.setUrl(url.toString().replaceFirst("(?i)https", "http"));
                            }
                            String url2 = httpSetting.getUrl();
                            String string = SharedPreferencesUtil.getSharedPreferences().getString("host_mocker_id_name", "");
                            List parseArray = JDJSON.parseArray(SharedPreferencesUtil.getSharedPreferences().getString("host_mocker_functionId_name", ""), String.class);
                            if (parseArray != null && parseArray.size() > 0 && httpSetting.getFunctionId() != null && parseArray.contains(httpSetting.getFunctionId())) {
                                httpSetting.setUrl(url2 + "&mockerUserId=" + string);
                                return;
                            }
                            httpSetting.setUrl(url2.replaceFirst("(?i)android.m.jd.care/service", HostConfig.getInstance().getServiceTestModel().getReleaseHost()));
                        }
                    } catch (MalformedURLException e2) {
                        e2.printStackTrace();
                        OKLog.e("addMockerIdName", e2);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.dependency.IExternalDebugConfig
            public boolean isForceHttpDownGrade() {
                SharedPreferences sharedPreferences;
                if (Configuration.isBeta() && (sharedPreferences = SharedPreferencesUtil.getSharedPreferences()) != null) {
                    return sharedPreferences.getBoolean(PersonalConstants.SP_NETWORK_SWITCH_DEVELOP, false);
                }
                return false;
            }
        };
    }

    public static IGatewayRespHeaderListener getGatewayRespHeaderListenerImpl() {
        System.currentTimeMillis();
        return new IGatewayRespHeaderListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0009: RETURN 
              (wrap: com.jingdong.jdsdk.network.dependency.IGatewayRespHeaderListener : 0x0006: CONSTRUCTOR (r0 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE]) A[MD:(long):void (m), WRAPPED] (LINE:2) call: com.jingdong.common.network.JDNetworkDependencyFactory.16.<init>(long):void type: CONSTRUCTOR)
             (LINE:2) in method: com.jingdong.common.network.JDNetworkDependencyFactory.getGatewayRespHeaderListenerImpl():com.jingdong.jdsdk.network.dependency.IGatewayRespHeaderListener, file: classes.dex
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            long r0 = java.lang.System.currentTimeMillis()
            com.jingdong.common.network.JDNetworkDependencyFactory$16 r2 = new com.jingdong.common.network.JDNetworkDependencyFactory$16
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.network.JDNetworkDependencyFactory.getGatewayRespHeaderListenerImpl():com.jingdong.jdsdk.network.dependency.IGatewayRespHeaderListener");
    }

    public static IGuardVerifyPlugin getGuardVerifyPlugin() {
        return new a(new a.b() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.19
            public static final String online_key_enable = "enable";
            public static final String online_key_expires = "expires_time";

            @Override // com.jingdong.app.mall.h.a.a.b
            public String getEid() {
                return DeviceFinger.getFinger(JdSdk.getInstance().getApplicationContext());
            }

            @Override // com.jingdong.app.mall.h.a.a.b
            public long getExpiresTime() {
                try {
                    return Long.parseLong(JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "guardVerify", "expires_time", "0"));
                } catch (Throwable unused) {
                    return 0L;
                }
            }

            @Override // com.jingdong.app.mall.h.a.a.b
            public String getUUID() {
                return StatisticsReportUtil.readDeviceUUID();
            }

            @Override // com.jingdong.app.mall.h.a.a.b
            public String getUserName() {
                return UserUtil.getWJLoginHelper().getPin();
            }

            @Override // com.jingdong.app.mall.h.a.a.b
            public VerifyPrivacyInfoProxy getVerifyPrivacyInfoProxy() {
                return new VerifyPrivacyInfoProxy() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.19.1
                    @Override // com.jd.verify.VerifyPrivacyInfoProxy
                    public String getPrivacyAndroidId() {
                        return BaseInfo.getAndroidId();
                    }

                    @Override // com.jd.verify.VerifyPrivacyInfoProxy
                    public String getPrivacyDeviceBrand() {
                        return BaseInfo.getDeviceBrand();
                    }

                    @Override // com.jd.verify.VerifyPrivacyInfoProxy
                    public String getPrivacyDeviceModel() {
                        return BaseInfo.getDeviceModel();
                    }

                    @Override // com.jd.verify.VerifyPrivacyInfoProxy
                    public String getPrivacyLatitude() {
                        try {
                            return LoginLocationUtil.getCacheLocation().getLat() + "";
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return "";
                        }
                    }

                    @Override // com.jd.verify.VerifyPrivacyInfoProxy
                    public String getPrivacyLongitude() {
                        try {
                            return LoginLocationUtil.getCacheLocation().getLng() + "";
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return "";
                        }
                    }

                    @Override // com.jd.verify.VerifyPrivacyInfoProxy
                    public String getPrivacyScreen() {
                        return BaseInfo.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + BaseInfo.getScreenWidth();
                    }

                    @Override // com.jd.verify.VerifyPrivacyInfoProxy
                    public String getPrivateOSRelease() {
                        return BaseInfo.getAndroidVersion();
                    }
                };
            }

            @Override // com.jingdong.app.mall.h.a.a.b
            public boolean isDebug() {
                return Configuration.isBeta();
            }

            @Override // com.jingdong.app.mall.h.a.a.b
            public boolean isOnlineSwitchOpen() {
                return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "guardVerify", "enable", "0"));
            }
        });
    }

    public static IHardGuardVerifyPlugin getHardGuardVerifyPlugin() {
        return new IHardGuardVerifyPlugin() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.22
            @Override // com.jingdong.jdsdk.network.dependency.IHardGuardVerifyPlugin
            public void triggerGuardVerifyCheck(String str, final IHardGuardVerifyPlugin.ICheckListener iCheckListener) {
                try {
                    if (OKLog.D) {
                        OKLog.d("RiskHandle", "onHandle jumpToRiskHandle response=" + str);
                    }
                    JDRiskHandleOption jDRiskHandleOption = new JDRiskHandleOption();
                    jDRiskHandleOption.setResponse(str);
                    JDRiskHandleManager.getInstance().jumpToRiskHandle(ActivityManagerUtil.getScreenManager().currentActivity(), jDRiskHandleOption, new JDRiskHandleListener() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.22.1
                        @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleListener
                        public void onHandleFail(JDRiskHandleError jDRiskHandleError) {
                            if (OKLog.D) {
                                OKLog.d("RiskHandle", "onHandleFail");
                            }
                            IHardGuardVerifyPlugin.ICheckListener iCheckListener2 = iCheckListener;
                            if (iCheckListener2 == null || jDRiskHandleError == null) {
                                return;
                            }
                            iCheckListener2.onCheckFinished(jDRiskHandleError.getJsonStr());
                            if (OKLog.D) {
                                OKLog.d("RiskHandle", "onHandleFail onCheckFinished:" + jDRiskHandleError.getJsonStr());
                            }
                        }

                        @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleListener
                        public void onHandleSuccess(JDRiskHandleData jDRiskHandleData) {
                            if (OKLog.D) {
                                OKLog.d("RiskHandle", "onHandleSuccess");
                            }
                            IHardGuardVerifyPlugin.ICheckListener iCheckListener2 = iCheckListener;
                            if (iCheckListener2 == null || jDRiskHandleData == null) {
                                return;
                            }
                            iCheckListener2.onCheckFinished(jDRiskHandleData.getJsonStr());
                            if (OKLog.D) {
                                OKLog.d("RiskHandle", "onHandleSuccess onCheckFinished:" + jDRiskHandleData.getJsonStr());
                            }
                        }
                    });
                } catch (Exception e2) {
                    OKLog.e("RiskHandle", "Exception:" + e2.getMessage());
                }
            }
        };
    }

    public static IHttpDnsController getHttpDnsControllerImpl() {
        return new IHttpDnsController() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.15
            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public boolean canUseHttpDns(String str) {
                return JDDnsUtil.getInstance().isNeedUseIp(str);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public IpModel getIpModelByHost(String str, boolean z) {
                com.jingdong.sdk.jdhttpdns.pojo.IpModel ipModelByHost;
                if (z) {
                    ipModelByHost = JDHttpDnsToolkit.getInstance().getIpFromMemoryCache(str);
                } else {
                    ipModelByHost = JDHttpDnsToolkit.getInstance().getIpModelByHost(str);
                }
                if (ipModelByHost == null) {
                    return null;
                }
                return new IpModel(ipModelByHost.host, ipModelByHost.master, ipModelByHost.v4, ipModelByHost.v6, ipModelByHost.updateTime);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public boolean isOpenDnsControl() {
                return JDDnsUtil.getInstance().isNetworkDnsControlEnable();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IHttpDnsController
            public void onHttpDnsReceived(IpModel ipModel) {
                DialingManager.getInstance().onHttpDnsReceived(ipModel);
            }
        };
    }

    public static IFailureController getHttpDnsFailureController() {
        return new IFailureController() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.12
            @Override // com.jingdong.sdk.jdhttpdns.listener.IFailureController
            public int getFailureCountLimit() {
                return 3;
            }

            @Override // com.jingdong.sdk.jdhttpdns.listener.IFailureController
            public void onHttpDnsFailure(Throwable th) {
                if (OKLog.D) {
                    OKLog.d("httpDns", "httpDns\u63a5\u53e3\u8bf7\u6c42\u5931\u8d25-> " + th);
                }
                j.c().g(false);
            }

            @Override // com.jingdong.sdk.jdhttpdns.listener.IFailureController
            public void reachFailureLimit() {
                JDDnsUtil.getInstance().setDnsControl(false);
            }
        };
    }

    public static IReporter getHttpDnsReporter() {
        return new IReporter() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.11
            @Override // com.jingdong.sdk.jdhttpdns.listener.IReporter
            public void httpDnsMta(FailEvent failEvent) {
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setFunctionId("");
                httpSetting.setUrl(failEvent.getUrl());
                HttpError httpError = new HttpError(new JDError(failEvent.getException()));
                if (OKLog.D) {
                    OKLog.d("HttpDns", "mta:" + failEvent.getUrl() + DYConstants.DY_REGEX_COMMA + httpError.getException().toString());
                }
                new ExceptionReporter();
                ExceptionReporter.reportHttpException(failEvent.getUrl(), httpSetting, httpError, JDNetworkConstant.HTTPDNS_EXP_ERRCODE);
            }
        };
    }

    public static IStatReporter getHttpDnsStatReporter() {
        return new IStatReporter() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.13
            @Override // com.jingdong.sdk.jdhttpdns.listener.IStatReporter
            public void saveNetworkStatistic(HashMap<String, Integer> hashMap) {
                StatSharePreferenceUtil.putIntMap(hashMap);
            }
        };
    }

    public static AbsDialogController.IDialog getHttpErrorAlertDialog() {
        return new AbsDialogController.IDialog() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.5
            JDDialog mDialog = null;

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void createDialog(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, CharSequence charSequence5) {
                String charSequence6;
                if (charSequence3 != null) {
                    try {
                        charSequence6 = charSequence3.toString();
                    } catch (Throwable unused) {
                        return;
                    }
                } else {
                    charSequence6 = "\u91cd\u8bd5";
                }
                this.mDialog = JDDialogFactory.getInstance().createJdDialogWithStyle2(context, charSequence2, charSequence6, charSequence5 != null ? charSequence5.toString() : "\u53d6\u6d88");
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void dismiss() {
                try {
                    JDDialog jDDialog = this.mDialog;
                    if (jDDialog == null || !jDDialog.isShowing()) {
                        return;
                    }
                    this.mDialog.dismiss();
                    this.mDialog = null;
                } catch (Throwable unused) {
                }
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setMessage(CharSequence charSequence) {
                try {
                    JDDialog jDDialog = this.mDialog;
                    if (jDDialog != null) {
                        jDDialog.setMessage(charSequence);
                    }
                } catch (Throwable unused) {
                }
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setNegativeButton(CharSequence charSequence) {
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setNeutralButton(CharSequence charSequence) {
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setOnNegativeClickListener(final DialogInterface.OnClickListener onClickListener) {
                JDDialog jDDialog = this.mDialog;
                if (jDDialog != null) {
                    jDDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.5.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            DialogInterface.OnClickListener onClickListener2 = onClickListener;
                            if (onClickListener2 != null) {
                                onClickListener2.onClick(null, 0);
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setOnPositiveClickListener(final DialogInterface.OnClickListener onClickListener) {
                JDDialog jDDialog = this.mDialog;
                if (jDDialog != null) {
                    jDDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.5.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            DialogInterface.OnClickListener onClickListener2 = onClickListener;
                            if (onClickListener2 != null) {
                                onClickListener2.onClick(null, 0);
                            }
                        }
                    });
                }
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setPositiveButton(CharSequence charSequence) {
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setTitle(CharSequence charSequence) {
                try {
                    JDDialog jDDialog = this.mDialog;
                    if (jDDialog != null) {
                        jDDialog.setTitle(charSequence);
                    }
                } catch (Throwable unused) {
                }
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void setView(View view) {
            }

            @Override // com.jingdong.common.network.AbsDialogController.IDialog
            public void show() {
                try {
                    JDDialog jDDialog = this.mDialog;
                    if (jDDialog != null) {
                        jDDialog.show();
                    }
                } catch (Throwable unused) {
                }
            }
        };
    }

    public static IKeyParamProvider getKeyParamProvider() {
        return new IKeyParamProvider() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.17
            @Override // com.jingdong.sdk.jdhttpdns.listener.IKeyParamProvider
            public String readDeviceUUID() {
                return StatisticsReportUtil.readAndroidId();
            }
        };
    }

    public static ILoginUserController getLoginUserControllerImpl() {
        return new ILoginUserController() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.4
            @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController
            public String getCookie() {
                return SafetyManager.getCookies();
            }

            @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController
            public void logoutOnlineInfo(ILoginUserController.ILoginStateChecker iLoginStateChecker) {
                LoginUserHelper.getInstance().getLoginUser().logoutOnCode3(iLoginStateChecker);
            }

            @Override // com.jingdong.jdsdk.network.dependency.ILoginUserController
            public void reportCode3(String str) {
                LoginReportUtil.reportCode3("3", str, "respCode3");
            }
        };
    }

    public static INetworkController getNetworkControllerImpl() {
        return new INetworkController() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.7
            @Override // com.jingdong.jdsdk.network.dependency.INetworkController
            public void autoNetDiagnose() {
                NetDiagnosisController.getController().autoNetDiagnose();
            }

            @Override // com.jingdong.jdsdk.network.dependency.INetworkController
            public boolean isAllowNetworkConnection() {
                return true;
            }
        };
    }

    public static IPHCEncryptionPlugin getPhcEncryptionPlugin() {
        return new IPHCEncryptionPlugin() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.14
            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public String getEncryptBodyParamStr(HttpSetting httpSetting, JDJSONObject jDJSONObject) {
                return JsonEncryptUtil.getEncryptBodyStr(httpSetting, jDJSONObject);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportDecryptError(Throwable th) {
                ExceptionReporter.reportPHCException("PCHDecryptError", "", null, th, null);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportEncryptError(String str, Throwable th) {
                ExceptionReporter.reportPHCException("PCHEncryptError", str, null, th, null);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportGateWayDecryptError(String str, String str2) {
                ExceptionReporter.reportGateWayDecryptError(str, str2);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void reportInitError(String str, String str2) {
                ExceptionReporter.reportPHCException("initPCHError", "", str, null, str2);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void resendEncryptError(String str) {
                ExceptionReporter.reportPHCException("resentEncryptError", str, null, null, null);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin
            public void resendServer731Error(String str, String str2) {
                ExceptionReporter.reportPHCException("resentServer731Error", str, null, null, str2);
            }
        };
    }

    public static IRestrictController getRestrictController() {
        return new IRestrictController() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.18
            @Override // com.jingdong.sdk.jdhttpdns.listener.IRestrictController
            public boolean isXTime() {
                return NetworkXConfig.isXTime();
            }
        };
    }

    public static IRuntimeConfig getRuntimeConfigImpl() {
        return new IRuntimeConfig() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.1
            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public boolean getBoolean(String str, boolean z) {
                return SharedPreferencesUtil.getSharedPreferences().getBoolean(str, z);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public String getDataFromMobileConfig(String str, String str2) {
                return JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "network", str, str2);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public String getDataFromSwitchQuery(String str, String str2) {
                return SwitchQueryFetcher.getSwitchStringValue(str, str2);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public String getStringFromPreference(String str) {
                return ConfigUtil.getStringFromPreference(str);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public boolean isUseHttpsDuringX() {
                return NetworkXConfig.isHttpsSwitch();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IRuntimeConfig
            public boolean isXTime() {
                return NetworkXConfig.isXTime();
            }
        };
    }

    public static ISignatureHandler getSignatureHandler() {
        return new ISignatureHandler() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.3
            @Override // com.jingdong.jdsdk.network.dependency.ISignatureHandler
            public byte[] decodeFromJni(byte[] bArr) {
                if (BitmapkitUtils.isFuncAvailable()) {
                    return BitmapkitUtils.encodeJni(bArr, true);
                }
                return null;
            }

            @Override // com.jingdong.jdsdk.network.dependency.ISignatureHandler
            public void networkSettingsPreSignature() {
                NetworkSetting.networkSetting();
                BitmapkitUtils.loadBMP();
                if (BitmapkitUtils.isFuncAvailable()) {
                    return;
                }
                SignatureAlertController.alertSignatureFailedDialog(ActivityManagerUtil.getScreenManager().currentActivity());
            }

            @Override // com.jingdong.jdsdk.network.dependency.ISignatureHandler
            public String signature(Context context, String str, String str2, String str3, String str4, String str5) {
                return BitmapkitUtils.getSignFromJni(context, str, str2, str3, str4, str5);
            }
        };
    }

    public static IStatInfoConfig getStatInfoConfigImpl() {
        return new IStatInfoConfig() { // from class: com.jingdong.common.network.JDNetworkDependencyFactory.2
            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public boolean canUseReferer() {
                return TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDRequestIdentifier", "switch", "netRefererSwitch"), "1");
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String encryptBody(String str) {
                return EncryptBodyController.encryptBody(str);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public Map<String, String> getColorStatParamStr(boolean z, boolean z2, boolean z3, Map<String, String> map, String str) {
                return EncryptStatParamController.getColorQueryParams(z, z2, z3, map, str);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getDeviceUUID(boolean z) {
                String readDeviceUUID = z ? StatisticsReportUtil.readDeviceUUID() : DeviceInfoHelper.getAid();
                return TextUtils.isEmpty(readDeviceUUID) ? StatisticsReportUtil.readInstallationId() : readDeviceUUID;
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getJdv() {
                String jdv = JDMtaUtils.getJdv();
                if (TextUtils.isEmpty(jdv)) {
                    return "";
                }
                return "__jdv=" + jdv + ";";
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getStatisticReportString(String str, boolean z, boolean z2, boolean z3, Map<String, String> map, String str2) {
                if (z3) {
                    return EncryptStatParamController.getQueryParamsStr(z, z2, map, str2);
                }
                return StatisticsReportUtil.getReportStringWithEncryptUUID(str, z, z2, str2);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public Map<String, String> getUniformHeaderField(boolean z, boolean z2) {
                if (z2) {
                    return EncryptHeaderController.getEncryptHeaderField(z);
                }
                return StatisticsReportUtil.getUniformHeaderField(z);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getVersionName() {
                return PackageInfoUtil.getVersionName();
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public void reportTlsHandshakeStatData(JDNetworkStatisticTool.TlsStatEntry tlsStatEntry) {
                ExceptionReporter.reportTlsHandshakeData(tlsStatEntry);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public void saveNetworkStatistic(HashMap<String, Integer> hashMap) {
                StatSharePreferenceUtil.putIntMap(hashMap);
            }

            @Override // com.jingdong.jdsdk.network.dependency.IStatInfoConfig
            public String getDeviceUUID(String str, boolean z) {
                return getDeviceUUID(z);
            }
        };
    }
}
