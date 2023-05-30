package com.jingdong.common.utils.personal;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.personal.PlatformOthers;
import com.jingdong.common.impl.UtilFactory;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.common.utils.FloatLayerADUtil;
import com.jingdong.common.utils.NpsTimeUtil;
import com.jingdong.common.utils.PersonalEnterUtils;
import com.jingdong.common.utils.PersonalNetDataManager;
import com.jingdong.common.utils.RefreshParamsUtils;
import com.jingdong.common.utils.TraceCustomUtil;
import com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper;
import com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class JDPersonalPlatformConfigUtils {
    private static final String DEFAULT_MENU_TIMESTAMP = "0";
    public static final String MENU_SOURCE_TYPE_DYNAMIC = "0";
    public static final String MENU_SOURCE_TYPE_STATIC = "1";
    private static final String TAG = "JDPersonalPlatformConfigUtils";
    private static String lastMenuTimeStamp = "0";

    /* JADX INFO: Access modifiers changed from: private */
    public static void addTraceTime(String str, String str2) {
        TraceCustomUtil.addTraceTime(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doRequest(boolean z, final String str, String str2, HashSet<String> hashSet, String str3, final PersonInfoBusinessCallback personInfoBusinessCallback) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.setFunctionId("personinfoBusiness");
        httpSetting.putJsonParam("menuStaticSource", str);
        httpSetting.putJsonParam("menuTimeStamp", str2);
        if (!NpsTimeUtil.judgeShowNps(NpsTimeUtil.FUNCTION_FLOAT_NPS)) {
            httpSetting.putJsonParam("callNPS", "0");
        } else {
            httpSetting.putJsonParam("callNPS", "1");
        }
        if (!NpsTimeUtil.judgeShowNps(NpsTimeUtil.FUNCTION_FLOAT_CJH)) {
            httpSetting.putJsonParam("callCJH", "0");
        } else {
            httpSetting.putJsonParam("callCJH", "1");
        }
        httpSetting.putJsonParam("closeJX", FloatLayerADUtil.getCloseJXSu());
        if (!FloatLayerADUtil.getAdFourTypeCloseSuList().isEmpty()) {
            httpSetting.putJsonParam("shieldFucIds", FloatLayerADUtil.getAdFourTypeCloseSuList());
        }
        if (!TextUtils.isEmpty(str3)) {
            httpSetting.putJsonParam("refreshEnable", str3);
        }
        if ("0".equals(str)) {
            httpSetting.putJsonParam("headTaskRefresh", RefreshParamsUtils.getCommonRefresh());
            if (LoginUserBase.hasLogin()) {
                httpSetting.putJsonParam("todayFirst", PersonalEnterUtils.isFirstEnterPersonal());
            }
        }
        handleLocationInfo(httpSetting);
        httpSetting.setEffect(0);
        httpSetting.setEnableGatewayQueue(!z);
        httpSetting.setTopPriority(!z);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                List<BaseTemplateEntity> list;
                JDPersonalPlatformConfigUtils.addTraceTime(TraceCustomUtil.TIME_NET_END, str);
                if (httpResponse != null && httpResponse.getCode() == 0) {
                    IJsonParse jsonParser = UtilFactory.getInstance().getJsonParser();
                    if (jsonParser != null) {
                        JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("others");
                        if (optJSONObject != null) {
                            PlatformOthers platformOthers = (PlatformOthers) jsonParser.parseJsonToObject(optJSONObject.toString(), PlatformOthers.class);
                            if (platformOthers != null) {
                                if (!TextUtils.isEmpty(platformOthers.menuTimeStamp) && !TextUtils.equals(platformOthers.menuTimeStamp, "0")) {
                                    String unused = JDPersonalPlatformConfigUtils.lastMenuTimeStamp = platformOthers.menuTimeStamp;
                                }
                                platformOthers.preProcess();
                            }
                            if (platformOthers != null && (list = platformOthers.floors) != null && !list.isEmpty()) {
                                PersonalStaticConfigCacheHelper.doSave(optJSONObject.toString());
                                PersonalNetDataManager.INSTANCE.setUpdate(true);
                            }
                        }
                        JDPersonalPlatformConfigUtils.addTraceTime(TraceCustomUtil.TIME_NET_END_CALLBACK, str);
                        JDPersonalPlatformConfigUtils.stopTraceTime(TraceCustomUtil.TIME_TOTAL, str);
                        PersonInfoBusinessCallback personInfoBusinessCallback2 = personInfoBusinessCallback;
                        if (personInfoBusinessCallback2 != null) {
                            personInfoBusinessCallback2.onSuccess(httpResponse);
                            return;
                        }
                        return;
                    }
                    PersonInfoBusinessCallback personInfoBusinessCallback3 = personInfoBusinessCallback;
                    if (personInfoBusinessCallback3 != null) {
                        Object[] objArr = new Object[1];
                        objArr[0] = Boolean.valueOf(jsonParser == null);
                        personInfoBusinessCallback3.onError(new IllegalArgumentException(String.format("onEnd get error jsonParse is null: %s", objArr)));
                        return;
                    }
                    return;
                }
                PersonInfoBusinessCallback personInfoBusinessCallback4 = personInfoBusinessCallback;
                if (personInfoBusinessCallback4 != null) {
                    Object[] objArr2 = new Object[2];
                    objArr2[0] = Boolean.valueOf(httpResponse == null);
                    objArr2[1] = Integer.valueOf(httpResponse != null ? httpResponse.getCode() : -1);
                    personInfoBusinessCallback4.onError(new IllegalArgumentException(String.format("onEnd get error httpResponse is null: %s, code: %s", objArr2)));
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                PersonInfoBusinessCallback personInfoBusinessCallback2 = personInfoBusinessCallback;
                if (personInfoBusinessCallback2 != null) {
                    personInfoBusinessCallback2.onError(new IllegalArgumentException(String.format("onError errorCode: %s, errorMessage: %s", httpError.getErrorCodeStr(), httpError.getMessage()), httpError));
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        addTraceTime(TraceCustomUtil.TIME_CREATE_HTTP_SETTING, str);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void fetchPersonInfoBusinessInfo(IPersonalPlatformHttp iPersonalPlatformHttp, final PersonInfoBusinessCallback personInfoBusinessCallback) {
        if (iPersonalPlatformHttp == null || iPersonalPlatformHttp.getHttpSetting() == null) {
            return;
        }
        HttpSetting httpSetting = iPersonalPlatformHttp.getHttpSetting();
        final String menuStaticSourceConfig = iPersonalPlatformHttp.getMenuStaticSourceConfig();
        httpSetting.putJsonParam("menuStaticSource", menuStaticSourceConfig);
        httpSetting.putJsonParam("menuTimeStamp", lastMenuTimeStamp);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                List<BaseTemplateEntity> list;
                JDPersonalPlatformConfigUtils.addTraceTime(TraceCustomUtil.TIME_NET_END, menuStaticSourceConfig);
                if (httpResponse != null && httpResponse.getCode() == 0) {
                    IJsonParse jsonParser = UtilFactory.getInstance().getJsonParser();
                    if (jsonParser != null) {
                        JDJSONObject optJSONObject = httpResponse.getFastJsonObject().optJSONObject("others");
                        if (optJSONObject != null) {
                            PlatformOthers platformOthers = (PlatformOthers) jsonParser.parseJsonToObject(optJSONObject.toString(), PlatformOthers.class);
                            if (platformOthers != null) {
                                if (!TextUtils.isEmpty(platformOthers.menuTimeStamp) && !TextUtils.equals(platformOthers.menuTimeStamp, "0")) {
                                    String unused = JDPersonalPlatformConfigUtils.lastMenuTimeStamp = platformOthers.menuTimeStamp;
                                }
                                platformOthers.preProcess();
                            }
                            if (platformOthers != null && (list = platformOthers.floors) != null && !list.isEmpty()) {
                                PersonalStaticConfigCacheHelper.doSave(optJSONObject.toString());
                                PersonalNetDataManager.INSTANCE.setUpdate(true);
                            }
                        }
                        JDPersonalPlatformConfigUtils.addTraceTime(TraceCustomUtil.TIME_NET_END_CALLBACK, menuStaticSourceConfig);
                        JDPersonalPlatformConfigUtils.stopTraceTime(TraceCustomUtil.TIME_TOTAL, menuStaticSourceConfig);
                        PersonInfoBusinessCallback personInfoBusinessCallback2 = personInfoBusinessCallback;
                        if (personInfoBusinessCallback2 != null) {
                            personInfoBusinessCallback2.onSuccess(httpResponse);
                            return;
                        }
                        return;
                    }
                    PersonInfoBusinessCallback personInfoBusinessCallback3 = personInfoBusinessCallback;
                    if (personInfoBusinessCallback3 != null) {
                        Object[] objArr = new Object[1];
                        objArr[0] = Boolean.valueOf(jsonParser == null);
                        personInfoBusinessCallback3.onError(new IllegalArgumentException(String.format("onEnd get error jsonParse is null: %s", objArr)));
                        return;
                    }
                    return;
                }
                PersonInfoBusinessCallback personInfoBusinessCallback4 = personInfoBusinessCallback;
                if (personInfoBusinessCallback4 != null) {
                    Object[] objArr2 = new Object[2];
                    objArr2[0] = Boolean.valueOf(httpResponse == null);
                    objArr2[1] = Integer.valueOf(httpResponse != null ? httpResponse.getCode() : -1);
                    personInfoBusinessCallback4.onError(new IllegalArgumentException(String.format("onEnd get error httpResponse is null: %s, code: %s", objArr2)));
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                PersonInfoBusinessCallback personInfoBusinessCallback2 = personInfoBusinessCallback;
                if (personInfoBusinessCallback2 != null) {
                    personInfoBusinessCallback2.onError(new IllegalArgumentException(String.format("onError errorCode: %s, errorMessage: %s", httpError.getErrorCodeStr(), httpError.getMessage()), httpError));
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        addTraceTime(TraceCustomUtil.TIME_CREATE_HTTP_SETTING, menuStaticSourceConfig);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void getPersonInfoBusinessInfo(String str) {
        getPersonInfoBusinessInfo(false, str, null, null);
    }

    private static void handleLocationInfo(HttpSetting httpSetting) {
        if (httpSetting == null) {
            return;
        }
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(JDPersonalStaticConfigUtils.LBS_ID);
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        String str = null;
        if (location != null) {
            if (!(location.getLng() == 0.0d && location.getLat() == 0.0d && location.getProvinceId() == 1 && location.getCityId() == 0 && location.getDistrictId() == 0 && location.getTownId() == 0)) {
                str = location.getProvinceId() + CartConstant.KEY_YB_INFO_LINK + location.getCityId() + CartConstant.KEY_YB_INFO_LINK + location.getDistrictId() + CartConstant.KEY_YB_INFO_LINK + location.getTownId();
            }
        }
        if (str == null) {
            str = "0_0_0_0";
        }
        httpSetting.putJsonParam("locationArea", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void stopTraceTime(String str, String str2) {
        TraceCustomUtil.stop(str, str2);
    }

    public static void getPersonInfoBusinessInfo(boolean z, String str) {
        getPersonInfoBusinessInfo(z, str, null, null);
    }

    public static void getPersonInfoBusinessInfo(HashSet<String> hashSet, PersonInfoBusinessCallback personInfoBusinessCallback) {
        getPersonInfoBusinessInfo(false, "0", hashSet, personInfoBusinessCallback);
    }

    public static void getPersonInfoBusinessInfo(boolean z, HashSet<String> hashSet, PersonInfoBusinessCallback personInfoBusinessCallback) {
        getPersonInfoBusinessInfo(z, "0", hashSet, personInfoBusinessCallback);
    }

    public static void getPersonInfoBusinessInfo(HashSet<String> hashSet, String str, PersonInfoBusinessCallback personInfoBusinessCallback) {
        getPersonInfoBusinessInfo(false, "0", hashSet, str, personInfoBusinessCallback);
    }

    public static void getPersonInfoBusinessInfo(boolean z, String str, HashSet<String> hashSet, PersonInfoBusinessCallback personInfoBusinessCallback) {
        getPersonInfoBusinessInfo(z, str, hashSet, null, personInfoBusinessCallback);
    }

    public static void getPersonInfoBusinessInfo(final boolean z, final String str, final HashSet<String> hashSet, final String str2, final PersonInfoBusinessCallback personInfoBusinessCallback) {
        TraceCustomUtil.init();
        if (TextUtils.equals(lastMenuTimeStamp, "0")) {
            PersonalStaticConfigCacheHelper.getCache(new PersonalStaticConfigCacheHelper.GetCacheCallback() { // from class: com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils.1
                @Override // com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper.GetCacheCallback
                public void onGetCache(@Nullable String str3, @Nonnull String str4) {
                    if (OKLog.D) {
                        OKLog.d(JDPersonalPlatformConfigUtils.TAG, String.format("onGetCache menuTimeStamp:%s \njsonString:%s", str4, str3));
                    }
                    JDPersonalPlatformConfigUtils.addTraceTime(TraceCustomUtil.TIME_STATIC_CONFIG_CACHE, str);
                    String unused = JDPersonalPlatformConfigUtils.lastMenuTimeStamp = str4;
                    JDPersonalPlatformConfigUtils.doRequest(z, str, str4, hashSet, str2, personInfoBusinessCallback);
                }
            });
        } else {
            doRequest(z, str, lastMenuTimeStamp, hashSet, str2, personInfoBusinessCallback);
        }
    }

    public static void getPersonInfoBusinessInfo(final IPersonalPlatformHttp iPersonalPlatformHttp, final PersonInfoBusinessCallback personInfoBusinessCallback) {
        if (iPersonalPlatformHttp == null) {
            return;
        }
        iPersonalPlatformHttp.getMenuStaticSourceConfig();
        TraceCustomUtil.init();
        if (TextUtils.equals(lastMenuTimeStamp, "0")) {
            PersonalStaticConfigCacheHelper.getCache(new PersonalStaticConfigCacheHelper.GetCacheCallback
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE 
                  (wrap: com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper$GetCacheCallback : 0x0016: CONSTRUCTOR 
                  (r0 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r3v0 'iPersonalPlatformHttp' com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp A[DONT_INLINE])
                  (r4v0 'personInfoBusinessCallback' com.jingdong.common.utils.personal.PersonInfoBusinessCallback A[DONT_INLINE])
                 A[MD:(java.lang.String, com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp, com.jingdong.common.utils.personal.PersonInfoBusinessCallback):void (m), WRAPPED] (LINE:14) call: com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils.3.<init>(java.lang.String, com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp, com.jingdong.common.utils.personal.PersonInfoBusinessCallback):void type: CONSTRUCTOR)
                 type: STATIC call: com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper.getCache(com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper$GetCacheCallback):void A[MD:(com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper$GetCacheCallback):void (m)] (LINE:14) in method: com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils.getPersonInfoBusinessInfo(com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp, com.jingdong.common.utils.personal.PersonInfoBusinessCallback):void, file: classes6.dex
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
                if (r3 != 0) goto L3
                return
            L3:
                java.lang.String r0 = r3.getMenuStaticSourceConfig()
                com.jingdong.common.utils.TraceCustomUtil.init()
                java.lang.String r1 = com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils.lastMenuTimeStamp
                java.lang.String r2 = "0"
                boolean r1 = android.text.TextUtils.equals(r1, r2)
                if (r1 == 0) goto L1d
                com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils$3 r1 = new com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils$3
                r1.<init>()
                com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper.getCache(r1)
                goto L20
            L1d:
                fetchPersonInfoBusinessInfo(r3, r4)
            L20:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils.getPersonInfoBusinessInfo(com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp, com.jingdong.common.utils.personal.PersonInfoBusinessCallback):void");
        }
    }
