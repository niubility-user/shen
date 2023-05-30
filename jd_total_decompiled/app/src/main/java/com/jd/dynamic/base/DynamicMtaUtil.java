package com.jd.dynamic.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.interfaces.IDynamicMta;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.Template;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.common.web.managers.WebPerfManager;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes13.dex */
public class DynamicMtaUtil {
    public static final int ACTION_LONG_PRESS = 2;
    public static final int ACTION_SINGLE_CLICK = 1;
    public static final int ACTION_TOUCH = 0;
    public static final String DOWNGRADE_STAGE_1 = "1";
    public static final String DOWNGRADE_STAGE_2 = "2";
    public static final String DOWNGRADE_STAGE_3 = "3";
    public static final String DOWNGRADE_TYPE_0 = "0";
    public static final String DOWNGRADE_TYPE_1 = "1";
    public static final String DOWNGRADE_TYPE_2 = "2";
    private static final String DYN_FILE_GRAY = "isGray";
    private static final String DYN_FILE_NAME = "fileName";
    private static final String DYN_IS_HTTPS = "isHttps";
    public static final String DYN_PERFORMANCE_QUERY_DATA = "dynPerformanceQueryData";
    private static final String EVENT_ID_ACTION = "dynBasicDataAction";
    private static final String EVENT_ID_AFTER_RENDER = "dynAfterRender";
    private static final String EVENT_ID_API = "dynApi";
    private static final String EVENT_ID_DISPLAY_TEMPLATE = "dynDisplayTemplate";
    private static final String EVENT_ID_DOWNGRADE = "dynDowngradeData";
    private static final String EVENT_ID_DYN_DISPLAY = "dynDisplay";
    private static final String EVENT_ID_DYN_GET_TEMPLATE = "dynGetTemplate";
    private static final String EVENT_ID_DYN_RENDER = "dynRender";
    private static final String EVENT_ID_EXCEPTION = "dynException";
    private static final String EVENT_ID_IS_MAIN_THREAD = "isMainThread";
    private static final String EVENT_ID_OLD_API = "dynOldApi";
    private static final String EVENT_ID_PERFORMANCE = "dynPerformanceTime";
    private static final String EVENT_ID_PRE_RENDER = "dynPreRender";
    private static final String EVENT_ID_RENDER_A_STATUS = "dynRenderActivity";
    private static final String EVENT_ID_RENDER_ENTER_POST = "dynEnterPost";
    private static final String EVENT_ID_RENDER_RESULT = "dynRenderResult";
    private static final String EVENT_ID_START_LOAD = "dynLoadTemplate";
    private static final String EVENT_KEY_DOWNLOAD_TEMPLATE = "downloadTemplate";
    private static final String HIT_CACHE = "hitCache";
    private static final String TAG = "DynamicMtaUtil";
    public static final int TEMP_DISK = 2;
    public static final int TEMP_LIST_DISK = 2;
    public static final int TEMP_LIST_MEM = 1;
    public static final int TEMP_LIST_NET = 3;
    public static final int TEMP_LIST_NO = 0;
    public static final int TEMP_LOCAL = 4;
    public static final int TEMP_MEM = 1;
    public static final int TEMP_NET = 3;
    public static final int TEMP_NO = 0;
    private static ConcurrentHashMap<String, JsonObject> sMtaMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Template> sMtaTemplateMap = new ConcurrentHashMap<>();
    private static volatile AtomicInteger sRequestIndex = new AtomicInteger();

    public static /* synthetic */ void a(String str, String str2, String str3, String str4) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            jsonObject.addProperty("exceptionType", str3);
            jsonObject.addProperty("errorCode", str4);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_EXCEPTION, "", "", "", "", "", jsonObject.toString());
            }
        } catch (Exception unused) {
        }
    }

    public static void addExceptionMta(final String str, final String str2, final String str3, final String str4) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.o
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.a(str, str2, str3, str4);
            }
        });
    }

    public static void afterRender(final String str, final String str2, final long j2) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.s
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.b(str, str2, j2);
            }
        });
    }

    public static void appendBindDataMtaStat(String str, MtaTimePair mtaTimePair) {
        JsonObject jsonObject;
        if (useMta()) {
            try {
                if (TextUtils.isEmpty(str) || !mtaTimePair.isValid() || (jsonObject = sMtaMap.get(str)) == null) {
                    return;
                }
                jsonObject.addProperty("bindData", mtaTimePair.useTime());
            } catch (Exception unused) {
            }
        }
    }

    public static void appendCreateModelMtaStat(String str, MtaTimePair mtaTimePair) {
        if (useMta()) {
            try {
                JsonObject jsonObject = sMtaMap.get(str);
                if (jsonObject != null) {
                    jsonObject.addProperty("createModel", mtaTimePair.useTime());
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void appendDownloadTypeMtaStat(String str, int i2) {
        if (useMta()) {
            try {
                JsonObject jsonObject = sMtaMap.get(str);
                if (jsonObject != null) {
                    String str2 = "";
                    if (i2 == 0) {
                        str2 = "0";
                    } else if (i2 == 1) {
                        str2 = "1";
                    } else if (i2 == 2) {
                        str2 = "2";
                    } else if (i2 == 3) {
                        str2 = "3";
                    } else if (i2 == 4) {
                        str2 = "4";
                    }
                    jsonObject.addProperty("downloadTemplateType", str2);
                }
            } catch (Exception unused) {
            }
        }
    }

    @Deprecated
    public static void appendFirstPhaseMtaStat(String str, String str2, String str3, String str4, MtaTimePair mtaTimePair) {
        if (useMta()) {
            try {
                JsonObject jsonObject = sMtaMap.get(str);
                if (jsonObject != null) {
                    jsonObject.addProperty("businessName", str2);
                    jsonObject.addProperty("businessVersion", str3);
                    jsonObject.addProperty("templateId", str4);
                    if (mtaTimePair.isValid()) {
                        jsonObject.addProperty("queryTemplateDuration", mtaTimePair.useTime());
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void appendGetTemplateEnd(String str) {
        if (useMta()) {
            try {
                JsonObject jsonObject = sMtaMap.get(str);
                if (jsonObject != null) {
                    jsonObject.addProperty("getTemplate", getCurMicroseconds(System.nanoTime() - jsonObject.get("dynamicStartTime").getAsLong()));
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void appendLoadJsEnd(String str, MtaTimePair mtaTimePair) {
        if (useMta()) {
            try {
                JsonObject jsonObject = sMtaMap.get(str);
                if (jsonObject != null) {
                    jsonObject.addProperty("loadJS", mtaTimePair.useTime());
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [int] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    public static void appendRenderMtaStat(String str, MtaTimePair mtaTimePair) {
        JsonObject jsonObject;
        DYConstants.DYLog("use time is: " + mtaTimePair.useTime());
        if (useMta()) {
            try {
                if (TextUtils.isEmpty(str) || !mtaTimePair.isValid() || (jsonObject = sMtaMap.get(str)) == null) {
                    return;
                }
                if (com.jd.dynamic.b.a.b.o().C()) {
                    ?? r0 = com.jd.dynamic.b.a.b.o().D();
                    if (com.jd.dynamic.b.a.b.o().E()) {
                        r0 = 2;
                        jsonObject.addProperty("fastTem", com.jd.dynamic.b.a.b.o().F().toString());
                        jsonObject.addProperty("fastTimes", Integer.valueOf(com.jd.dynamic.b.a.b.o().G()));
                    }
                    jsonObject.addProperty("preheat", Integer.valueOf((int) r0));
                }
                jsonObject.addProperty(WebPerfManager.RENDER, mtaTimePair.useTime());
            } catch (Exception unused) {
            }
        }
    }

    public static /* synthetic */ void b(String str, String str2, long j2) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            jsonObject.addProperty("delayTime", Long.valueOf(j2));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_AFTER_RENDER, "", "", "", "", "", jsonObject.toString());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "afterRender Exception" + e2.getMessage(), str2, str, 1011, e2);
        }
    }

    public static /* synthetic */ void c(String str, String str2, boolean z, boolean z2) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
            String str3 = "1";
            jsonObject.addProperty(HIT_CACHE, z ? "1" : "0");
            if (!z2) {
                str3 = "0";
            }
            jsonObject.addProperty("newApi", str3);
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_DISPLAY_TEMPLATE, "", "", "", "", "", jsonObject.toString());
            }
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void d(String str, String str2, String str3, long j2) {
        Template tempByMtaId = getTempByMtaId(str);
        try {
            JsonObject baseJson = getBaseJson(tempByMtaId, str2, str3);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            baseJson.addProperty("delayTime", Long.valueOf(j2));
            if (tempByMtaId != null) {
                baseJson.addProperty(DYN_FILE_NAME, tempByMtaId.fileObjectKey);
                baseJson.addProperty(DYN_FILE_GRAY, tempByMtaId.isGray);
            }
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_DYN_DISPLAY, "", "", "", "", "", baseJson.toString());
            }
            realTimeReport(str2, EVENT_ID_DYN_DISPLAY, baseJson, tempByMtaId);
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, e2.getMessage(), str3, str2, 1011, e2, com.jd.dynamic.lib.utils.m.n(tempByMtaId, null));
        }
    }

    public static void displayTemplateMta(final String str, final String str2, final boolean z, final boolean z2) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.t
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.c(str, str2, z, z2);
            }
        });
    }

    public static /* synthetic */ void e(String str, String str2) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_PRE_RENDER, "", "", "", "", "", jsonObject.toString());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, "preRender Exception" + e2.getMessage(), str2, str, 1011, e2);
        }
    }

    public static void endLoadTemplate(final String str, final String str2, final String str3, final long j2) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.v
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.d(str3, str, str2, j2);
            }
        });
    }

    public static /* synthetic */ void f(String str, String str2, String str3) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
            jsonObject.addProperty("source", str3);
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_API, "", "", "", "", "", jsonObject.toString());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(EVENT_ID_API, EVENT_ID_RENDER_ENTER_POST + e2.getMessage(), str2, str, 1011, e2);
        }
    }

    public static /* synthetic */ void g(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appType", DynamicSdk.getEngine().getAppType());
            jSONObject.put(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jSONObject.put("bizField", str2);
            jSONObject.put("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            jSONObject.put("type", EVENT_ID_DYN_GET_TEMPLATE);
            jSONObject.put("source", str3);
            jSONObject.put(EVENT_ID_PERFORMANCE, str4);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_API, "", "", "", "", "", jSONObject.toString());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(EVENT_ID_API, EVENT_ID_RENDER_ENTER_POST + e2.getMessage(), str2, str, 1011, e2);
        }
    }

    private static JsonObject getBaseJson(Template template, String str, String str2) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
        jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
        if (template != null) {
            if (!TextUtils.isEmpty(template.systemCode)) {
                str = template.systemCode;
            }
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            if (!TextUtils.isEmpty(template.bizField)) {
                str2 = template.bizField;
            }
            jsonObject.addProperty("bizField", str2);
            jsonObject.addProperty("version", template.version);
            jsonObject.addProperty("pckVersion", template.pckVersion);
            jsonObject.addProperty("businessName", template.businessName);
            jsonObject.addProperty("templateType", template.getTemplateType());
            jsonObject.addProperty("newApi", template.isNewApi ? "1" : "0");
        } else {
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
        }
        return jsonObject;
    }

    public static String getCurMicroseconds(long j2) {
        try {
            return new BigDecimal(j2).divide(new BigDecimal(1000000L)).setScale(3, 4).toString();
        } catch (Exception unused) {
            return "0";
        }
    }

    public static String getDowngradeTemplateType(com.jd.dynamic.b.d.a aVar, String str) {
        String str2;
        try {
            if (aVar.d(str)) {
                str2 = "0";
            } else if (aVar.e(str)) {
                str2 = "2";
            } else if (!aVar.b(str)) {
                return null;
            } else {
                str2 = "1";
            }
            return str2;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Template getTempByMtaId(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return sMtaTemplateMap.get(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static /* synthetic */ void h(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appType", DynamicSdk.getEngine().getAppType());
            jSONObject.put(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jSONObject.put("bizField", str2);
            jSONObject.put("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            jSONObject.put(EVENT_ID_IS_MAIN_THREAD, com.jd.dynamic.lib.utils.m.D() ? "1" : "0");
            jSONObject.put("type", EVENT_ID_DYN_RENDER);
            jSONObject.put("source", str3);
            jSONObject.put(EVENT_ID_PERFORMANCE, str4);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_API, "", "", "", "", "", jSONObject.toString());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(EVENT_ID_API, EVENT_ID_RENDER_ENTER_POST + e2.getMessage(), str2, str, 1011, e2);
        }
    }

    private static void handleTemplateType(String str, JsonObject jsonObject) {
        Template tempByMtaId = getTempByMtaId(str);
        if (tempByMtaId != null) {
            jsonObject.addProperty("newApi", tempByMtaId.isNewApi ? "1" : "0");
            jsonObject.addProperty("templateType", tempByMtaId.getTemplateType());
        }
    }

    public static /* synthetic */ void i(Template template, JSONObject jSONObject) {
        try {
            if (!com.jd.dynamic.b.a.b.o().P() || template == null || jSONObject == null) {
                return;
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty("templateId", template.templateId);
            jsonObject.addProperty("bizField", template.bizField);
            jsonObject.addProperty("businessName", template.businessName);
            jsonObject.addProperty("version", template.version);
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, template.systemCode);
            jsonObject.addProperty("businessData", jSONObject.toString());
            DynamicSdk.getEngine().getRequest().requestWithHost("storeMCubeBusinessData", jsonObject.toString(), DynamicSdk.getEngine().getHost(), null);
        } catch (Exception unused) {
        }
    }

    @Deprecated
    private static boolean isNative(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("isNative");
        if (jsonElement != null) {
            try {
                return jsonElement.getAsBoolean();
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static /* synthetic */ void j(Activity activity, long j2, String str, String str2) {
        try {
            JsonObject jsonObject = new JsonObject();
            String str3 = "1";
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= 17) {
                    jsonObject.addProperty("activityDestroy", activity.isDestroyed() ? "1" : "0");
                }
                jsonObject.addProperty("activityFinish", activity.isFinishing() ? "1" : "0");
            }
            jsonObject.addProperty("activityNull", activity == null ? "1" : "0");
            if (!com.jd.dynamic.lib.utils.m.D()) {
                str3 = "0";
            }
            jsonObject.addProperty(EVENT_ID_IS_MAIN_THREAD, str3);
            jsonObject.addProperty("delayTime", Long.valueOf(j2));
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_RENDER_A_STATUS, "", "", "", "", "", jsonObject.toString());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, EVENT_ID_RENDER_A_STATUS + e2.getMessage(), str2, str, 1011, e2);
        }
    }

    public static /* synthetic */ void k(String str, String str2, long j2) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jsonObject.addProperty("bizField", str2);
            jsonObject.addProperty("delayTime", Long.valueOf(j2));
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_RENDER_ENTER_POST, "", "", "", "", "", jsonObject.toString());
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_RENDER, EVENT_ID_RENDER_ENTER_POST + e2.getMessage(), str2, str, 1011, e2);
        }
    }

    public static /* synthetic */ void l(String str, String str2, String str3) {
        try {
            Template tempByMtaId = getTempByMtaId(str);
            JsonObject baseJson = getBaseJson(tempByMtaId, str2, str3);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_DYN_RENDER, "", "", "", "", "", baseJson.toString());
            }
            realTimeReport(str2, EVENT_ID_DYN_RENDER, baseJson, tempByMtaId);
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void m(String str, String str2, String str3) {
        try {
            Template tempByMtaId = getTempByMtaId(str);
            if (tempByMtaId != null) {
                tempByMtaId.setEnterTime(System.currentTimeMillis());
                tempByMtaId.setEnterUUID(UUID.randomUUID().toString());
            }
            JsonObject baseJson = getBaseJson(tempByMtaId, str2, str3);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_DYN_GET_TEMPLATE, "", "", "", "", "", baseJson.toString());
            }
            realTimeReport(str2, EVENT_ID_DYN_GET_TEMPLATE, baseJson, tempByMtaId);
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void n(String str, String str2, String str3) {
        try {
            Template tempByMtaId = getTempByMtaId(str);
            JsonObject baseJson = getBaseJson(tempByMtaId, str2, str3);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_START_LOAD, "", "", "", "", "", baseJson.toString());
            }
            realTimeReport(str2, EVENT_ID_START_LOAD, baseJson, tempByMtaId);
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void o(String str, String str2, String str3) {
        try {
            Template tempByMtaId = getTempByMtaId(str);
            JsonObject baseJson = getBaseJson(tempByMtaId, str2, str3);
            baseJson.addProperty("newApi", "1");
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_START_LOAD, "", "", "", "", "", baseJson.toString());
            }
            realTimeReport(str2, EVENT_ID_START_LOAD, baseJson, tempByMtaId);
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void p(Template template, int i2) {
        String str = "";
        try {
            JsonObject baseJson = getBaseJson(template, "", "");
            if (i2 == 0) {
                str = "touch";
            } else if (i2 == 1) {
                str = "click";
            } else if (i2 == 2) {
                str = "longPress";
            }
            baseJson.addProperty("type", str);
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_ACTION, "", "", "", "", "", baseJson.toString());
            }
        } catch (Exception unused) {
        }
    }

    public static void preRender(final String str, final String str2) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.b0
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.e(str, str2);
            }
        });
    }

    public static /* synthetic */ void q(Template template, String str, String str2, String str3, String str4) {
        try {
            JsonObject baseJson = getBaseJson(template, "", "");
            if (!TextUtils.isEmpty(str)) {
                baseJson.addProperty("stage", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                baseJson.addProperty("dynamic", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                baseJson.addProperty(DynamicPrepareFetcher.KEY_PREPARE_MODULES, str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                baseJson.addProperty(MobileCertConstants.TEMPLATE, str4);
            }
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_DOWNGRADE, "", "", "", "", "", baseJson.toString());
            }
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void r(Template template, String str, boolean z, boolean z2, long j2) {
        JsonObject baseJson = getBaseJson(template, str, "");
        baseJson.addProperty("functionId", EVENT_KEY_DOWNLOAD_TEMPLATE);
        baseJson.addProperty("success", z ? "1" : "0");
        if (z2) {
            baseJson.addProperty("retry", "1");
        }
        if (template != null) {
            baseJson.addProperty(DYN_IS_HTTPS, template.fullFileUrl.startsWith("https") ? "1" : "0");
        }
        baseJson.addProperty("duration", getCurMicroseconds(j2));
        IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
        if (dynamicMta != null) {
            dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), DYN_PERFORMANCE_QUERY_DATA, "", "", "", "", "", baseJson.toString());
        }
        realTimeReport(str, DYN_PERFORMANCE_QUERY_DATA, baseJson, null);
    }

    private static void realTimeReport(String str, String str2, JsonObject jsonObject, Template template) {
        if (com.jd.dynamic.b.a.b.o().H()) {
            if (template != null) {
                com.jd.dynamic.b.k.a.b().c().a("dynamic").c(DynamicSdk.getEngine().getContext(), DynamicSdk.getEngine().getAppType(), str2, str, jsonObject.toString(), template.getEnterTime(), template.getEnterUUID(), null);
            } else {
                com.jd.dynamic.b.k.a.b().c().a("dynamic").b(DynamicSdk.getEngine().getContext(), DynamicSdk.getEngine().getAppType(), str2, str, jsonObject.toString(), null);
            }
        }
    }

    public static void removeMta(String str) {
        if (useMta()) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                sMtaMap.remove(str);
            } catch (Exception unused) {
            }
        }
    }

    public static Template removeTempByMtaId(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return sMtaTemplateMap.remove(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void reportAPIEnter(final String str, final String str2, final String str3) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.i
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.f(str, str2, str3);
            }
        });
    }

    public static void reportAPILoad(final String str, final String str2, final String str3, final String str4) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.m
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.g(str, str2, str3, str4);
            }
        });
    }

    public static void reportAPIRender(final String str, final String str2, final String str3, final String str4) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.l
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.h(str, str2, str3, str4);
            }
        });
    }

    public static void reportBusinessData(final Template template, final JSONObject jSONObject) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.a0
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.i(template, jSONObject);
            }
        });
    }

    public static void reportOldApiInfo(Context context, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DYConstants.DYN_PRV_SYSCODE_KEY, str);
            jSONObject.put("bizField", str2);
            jSONObject.put("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(context, EVENT_ID_OLD_API, "", "", "", "", "", jSONObject.toString());
            }
        } catch (JSONException unused) {
        }
    }

    public static void reportRenderActivityStatus(final Activity activity, final String str, final String str2, final long j2) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.d0
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.j(activity, j2, str, str2);
            }
        });
    }

    public static void reportRenderEnterPost(final String str, final String str2, final long j2) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.j
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.k(str, str2, j2);
            }
        });
    }

    public static void reportRenderResult(String str, String str2, int i2) {
        try {
            JsonObject baseJson = getBaseJson(null, str, str2);
            baseJson.addProperty("renderResult", Integer.valueOf(i2));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), EVENT_ID_RENDER_RESULT, "", "", "", "", "", baseJson.toString());
            }
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void s(long j2, Template template, boolean z, String str, long j3) {
        try {
            if (j2 < 0) {
                String str2 = "unknow";
                String str3 = template == null ? "unknow" : template.bizField;
                if (template != null) {
                    str2 = template.systemCode;
                }
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_MTA, "uploadDownloadTempMta duration \u5c0f\u4e8e0 duration = " + j2 + "\uff0csuccess = " + z, str3, str2, new RuntimeException("uploadDownloadTempMta duration \u5c0f\u4e8e0\uff0c"), com.jd.dynamic.lib.utils.m.n(template, null));
                return;
            }
            JsonObject baseJson = getBaseJson(template, str, "");
            baseJson.addProperty("functionId", EVENT_KEY_DOWNLOAD_TEMPLATE);
            String str4 = "1";
            baseJson.addProperty("success", z ? "1" : "0");
            if (template != null) {
                if (!template.fullFileUrl.startsWith("https")) {
                    str4 = "0";
                }
                baseJson.addProperty(DYN_IS_HTTPS, str4);
            }
            baseJson.addProperty("duration", getCurMicroseconds(j2));
            baseJson.addProperty("startDuration", getCurMicroseconds(j3));
            IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
            if (dynamicMta != null) {
                dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), DYN_PERFORMANCE_QUERY_DATA, "", "", "", "", "", baseJson.toString());
            }
            realTimeReport(str, DYN_PERFORMANCE_QUERY_DATA, baseJson, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void startDynRender(final String str, final String str2, final String str3) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.k
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.l(str3, str, str2);
            }
        });
    }

    public static void startGetTemplate(final String str, final String str2, final String str3) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.r
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.m(str3, str, str2);
            }
        });
    }

    public static void startLoadTemplate(final String str, final String str2, final String str3) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.q
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.n(str3, str, str2);
            }
        });
    }

    public static void startLoadTemplateObj(final String str, final String str2, final String str3) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.x
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.o(str3, str, str2);
            }
        });
    }

    @Deprecated
    public static String startMtaStat(String str, String str2, String str3) {
        return startMtaStat(str, str2, str3, false);
    }

    public static String startMtaStat(String str, String str2, String str3, String str4, boolean z) {
        if (useMta()) {
            String str5 = str + "-" + sRequestIndex.getAndIncrement();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("dynamicStartTime", Long.valueOf(System.nanoTime()));
            jsonObject.addProperty("appType", str);
            jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str2);
            jsonObject.addProperty("bizField", str4);
            jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
            sMtaMap.put(str5, jsonObject);
            Template template = new Template();
            template.systemCode = str2;
            template.businessCode = str3;
            template.bizField = str4;
            sMtaTemplateMap.put(str5, template);
            return str5;
        }
        return "";
    }

    public static String startMtaStat(String str, String str2, String str3, boolean z) {
        return startMtaStat(str, str2, str3, "", z);
    }

    public static /* synthetic */ void t(String str, Context context, String str2) {
        try {
            JsonObject jsonObject = sMtaMap.get(str);
            if (jsonObject != null) {
                jsonObject.addProperty("totalWithDelay", getCurMicroseconds(System.nanoTime() - jsonObject.get("dynamicStartTime").getAsLong()));
                jsonObject.remove("dynamicStartTime");
                handleTemplateType(str, jsonObject);
                com.jd.dynamic.lib.utils.t.c(String.format("getTemplate:%s----createModel:%s----bindData:%s----render:%s----totalWithDelay:%s", jsonObject.get("getTemplate"), jsonObject.get("createModel"), jsonObject.get("bindData"), jsonObject.get(WebPerfManager.RENDER), jsonObject.get("totalWithDelay")));
                IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
                if (dynamicMta != null) {
                    if (str2 == null) {
                        str2 = "";
                    }
                    dynamicMta.expoMtaEvent(context, EVENT_ID_PERFORMANCE, "", str2, "", "", "", jsonObject.toString());
                }
            }
            sMtaMap.remove(str);
            removeTempByMtaId(str);
        } catch (Exception unused) {
        }
    }

    public static /* synthetic */ void u(String str, boolean z, long j2, boolean z2) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("functionId", IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES);
        jsonObject.addProperty("appType", DynamicSdk.getEngine().getAppType());
        jsonObject.addProperty(DYConstants.DYN_PRV_SYSCODE_KEY, str);
        jsonObject.addProperty("success", z ? "1" : "0");
        jsonObject.addProperty("duration", getCurMicroseconds(j2));
        jsonObject.addProperty("newApi", z2 ? "1" : "0");
        jsonObject.addProperty("productionEnv", String.valueOf(DynamicSdk.getEngine().getPkgType()));
        IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
        if (dynamicMta != null) {
            dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), DYN_PERFORMANCE_QUERY_DATA, "", "", "", "", "", jsonObject.toString());
        }
    }

    public static void updateTempListSource(String str, int i2) {
        if (useMta()) {
            try {
                JsonObject jsonObject = sMtaMap.get(str);
                if (jsonObject != null) {
                    String str2 = "";
                    if (i2 == 0) {
                        str2 = "0";
                    } else if (i2 == 1) {
                        str2 = "1";
                    } else if (i2 == 2) {
                        str2 = "2";
                    } else if (i2 == 3) {
                        str2 = "3";
                    }
                    jsonObject.addProperty("queryTemplatesType", str2);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void updateTemplate(String str, Template template) {
        if (useMta() && template != null) {
            try {
                if (sMtaTemplateMap.containsKey(str)) {
                    Template template2 = sMtaTemplateMap.get(str);
                    if (template2 != null) {
                        template.setEnterTime(template2.getEnterTime());
                        template.setEnterUUID(template2.getEnterUUID());
                    }
                    sMtaTemplateMap.put(str, template);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void updateTemplateType2JS(String str) {
        Template tempByMtaId = getTempByMtaId(str);
        if (tempByMtaId != null) {
            tempByMtaId.isUseJs = true;
            tempByMtaId.isUseZip = true;
        }
    }

    public static void updateTemplateType2Zip(String str) {
        Template tempByMtaId = getTempByMtaId(str);
        if (tempByMtaId != null) {
            tempByMtaId.isUseZip = true;
        }
    }

    public static void updateTemplateZipVersion(String str, String str2) {
        Template tempByMtaId = getTempByMtaId(str);
        if (tempByMtaId != null) {
            tempByMtaId.pckVersion = str2;
        }
    }

    public static void uploadActionMta(final Template template, final int i2) {
        if (useMta()) {
            if (template == null || !template.isValid() || i2 < 0 || i2 > 2) {
                com.jd.dynamic.lib.utils.t.c(TAG, "uploadActionMta()  template not valid return!!!");
                return;
            }
            com.jd.dynamic.lib.utils.t.c(TAG, "uploadActionMta()  upload action:" + i2);
            com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.u
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicMtaUtil.p(template, i2);
                }
            });
        }
    }

    public static void uploadDowngradeCacheMta(String str, String str2, String str3, String str4, String str5) {
        Template template = new Template();
        template.systemCode = str;
        template.bizField = str2;
        uploadDowngradeMta(template, "1", str3, str4, str5);
    }

    public static void uploadDowngradeMta(final Template template, final String str, final String str2, final String str3, final String str4) {
        com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.z
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMtaUtil.q(template, str, str2, str3, str4);
            }
        });
    }

    public static void uploadDowngradeQueryMta(String str, String str2, String str3) {
        Template template = new Template();
        template.systemCode = str;
        uploadDowngradeMta(template, "2", str2, str3, null);
    }

    public static void uploadDowngradeTemplateMta(String str, String str2, String str3, String str4, String str5, String str6) {
        Template template = new Template();
        template.systemCode = str;
        template.bizField = str2;
        template.businessCode = str3;
        uploadDowngradeMta(template, "3", str4, str5, str6);
    }

    public static void uploadDownloadTempMta(String str, Template template, long j2, boolean z) {
        uploadDownloadTempMta(str, template, j2, z, false);
    }

    public static void uploadDownloadTempMta(final String str, final Template template, final long j2, final boolean z, final boolean z2) {
        if (useMta()) {
            try {
                if (j2 >= 0) {
                    com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.c0
                        @Override // java.lang.Runnable
                        public final void run() {
                            DynamicMtaUtil.r(template, str, z, z2, j2);
                        }
                    });
                    return;
                }
                String str2 = "unknow";
                String str3 = template == null ? "unknow" : template.bizField;
                if (template != null) {
                    str2 = template.systemCode;
                }
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_MTA, "uploadDownloadTempMta duration \u5c0f\u4e8e0 duration = " + j2 + "\uff0csuccess = " + z, str3, str2, new RuntimeException("uploadDownloadTempMta duration \u5c0f\u4e8e0\uff0c"), com.jd.dynamic.lib.utils.m.n(template, null));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void uploadDownloadTempMtaWithStart(final String str, final Template template, final long j2, final long j3, final boolean z) {
        if (useMta()) {
            com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.y
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicMtaUtil.s(j2, template, z, str, j3);
                }
            });
        }
    }

    @Deprecated
    public static void uploadMta(Context context, String str) {
        uploadMta(context, str, "");
    }

    public static void uploadMta(final Context context, final String str, final String str2) {
        if (useMta()) {
            if (TextUtils.isEmpty(str) || context == null) {
                removeMta(str);
            } else {
                com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.n
                    @Override // java.lang.Runnable
                    public final void run() {
                        DynamicMtaUtil.t(str, context, str2);
                    }
                });
            }
        }
    }

    public static void uploadQueryTempsMta(final String str, final long j2, final boolean z, final boolean z2) {
        if (useMta()) {
            try {
                if (j2 >= 0) {
                    com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.w
                        @Override // java.lang.Runnable
                        public final void run() {
                            DynamicMtaUtil.u(str, z, j2, z2);
                        }
                    });
                    return;
                }
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_MTA, "uploadQueryTempsMta duration \u5c0f\u4e8e0 duration = " + j2 + "\uff0csuccess = " + z, null, str, new RuntimeException("uploadQueryTempsMta duration \u5c0f\u4e8e0\uff0c"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void uploadUnZipTempMta(final String str, final Template template, final long j2, final boolean z, final boolean z2) {
        if (useMta()) {
            try {
                if (j2 >= 0) {
                    com.jd.dynamic.lib.utils.h.a(new Runnable() { // from class: com.jd.dynamic.base.p
                        @Override // java.lang.Runnable
                        public final void run() {
                            DynamicMtaUtil.v(template, str, z, j2, z2);
                        }
                    });
                    return;
                }
                String str2 = "unknow";
                String str3 = template == null ? "unknow" : template.bizField;
                if (template != null) {
                    str2 = template.systemCode;
                }
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_MTA, "uploadUnZipTempMta duration \u5c0f\u4e8e0 duration = " + j2 + "\uff0csuccess = " + z, str3, str2, new RuntimeException("uploadUnZipTempMta duration \u5c0f\u4e8e0\uff0c"), com.jd.dynamic.lib.utils.m.n(template, null));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static boolean useMta() {
        return DynamicSdk.getEngine().useMta();
    }

    public static /* synthetic */ void v(Template template, String str, boolean z, long j2, boolean z2) {
        JsonObject baseJson = getBaseJson(template, str, "");
        baseJson.addProperty("functionId", IExceptionHandler.DynamicExceptionData.TYPE_UNZIP);
        baseJson.addProperty("success", z ? "1" : "0");
        baseJson.addProperty("duration", getCurMicroseconds(j2));
        baseJson.addProperty("unzipType", z2 ? "2" : "1");
        IDynamicMta dynamicMta = DynamicSdk.getEngine().getDynamicMta();
        if (dynamicMta != null) {
            dynamicMta.expoMtaEvent(DynamicSdk.getEngine().getContext(), DYN_PERFORMANCE_QUERY_DATA, "", "", "", "", "", baseJson.toString());
        }
    }
}
