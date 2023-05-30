package com.jingdong.sdk.platform.floor.isv;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.apis.DYTemplateStatus;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jingdong.sdk.platform.PlatformHelper;
import com.jingdong.sdk.platform.floor.FloorManager;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.isv.BaseFunction;
import java.util.HashMap;

/* loaded from: classes10.dex */
public class ISVConst {
    static final String EVENT_ADD_DATA = "addData";
    public static final String EVENT_CLOSE_POP_WINDOW = "closePopview";
    public static final String EVENT_MTA_CLICK = "mtaClick";
    public static final String EVENT_MTA_EXPLORE = "mtaExplore";
    public static final String EVENT_OPENAPP = "openApp";
    static final String EVENT_OPEN_H5 = "openH5";
    public static final String EVENT_OPEN_POP_WINDOW = "openPopview";
    public static final String EVENT_PARAM_EXTDATA = "extData";
    public static final String EVENT_REFRESH = "refresh";
    public static final String EVENT_SHOW_FLOOR = "visibleFloor";
    static final String EVENT_TOAST = "toast";
    public static final String EVENT_WILL_REFRESH = "willRefresh";
    public static final String ISV_DYN_COMMON_EVENT_CLASS = "pdCommonEvent";
    public static final String ISV_DYN_COMMON_FLOOR = "bpDynamicFloor";
    public static boolean IS_DEBUG = false;
    public static final String MTA_EVENT_ID = "eventId";
    public static final String MTA_EVENT_PARAM = "data";
    public static final String MTA_EXPLORE = "mtaExplore";
    static HashMap<String, HashMap<String, Class<? extends BaseFunction>>> sBaseFactions;
    static HashMap<String, String> sReportType = new HashMap<>();
    static HashMap<String, String> sSysCodemap;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        sSysCodemap = hashMap;
        hashMap.put("productDetail", "productDetail");
        sReportType.put("productDetail", "jdos_warecoresoa");
    }

    public static void ISVReportBussiness(String str, BaseTemplateEntity baseTemplateEntity) {
        HashMap hashMap = new HashMap();
        hashMap.put("app", "app");
        hashMap.put("m", getReportType(str));
        if (baseTemplateEntity != null) {
            hashMap.put("mid", baseTemplateEntity.mId);
            hashMap.put("bid", baseTemplateEntity.bId);
        }
        hashMap.put("t", 1);
        hashMap.put("st", 13);
        com.jingdong.sdk.lib.isvmonitor.a.a.a(hashMap);
    }

    public static void ISVReportException(String str, Exception exc, BaseTemplateEntity baseTemplateEntity) {
        HashMap hashMap = new HashMap();
        hashMap.put("app", "app");
        hashMap.put("m", getReportType(str));
        if (baseTemplateEntity != null) {
            hashMap.put("mid", baseTemplateEntity.mId);
            hashMap.put("bid", baseTemplateEntity.bId);
        }
        hashMap.put("t", 1);
        hashMap.put("st", 11);
        com.jingdong.sdk.lib.isvmonitor.a.a.b(exc, "ISVPDDetail", hashMap);
    }

    public static <K extends BaseFloorData> CommFunction getFunction(String str, BaseDynFloor baseDynFloor, Context context, String str2, BaseFunction.DataSupport dataSupport) {
        HashMap<String, Class<? extends BaseFunction>> hashMap;
        HashMap<String, HashMap<String, Class<? extends BaseFunction>>> hashMap2 = sBaseFactions;
        BaseFunction baseFunction = null;
        if (hashMap2 != null && hashMap2.containsKey(str) && (hashMap = sBaseFactions.get(str)) != null && hashMap.containsKey(str2)) {
            try {
                baseFunction = hashMap.get(str2).newInstance();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            }
            if (baseFunction != null) {
                baseFunction.initData(baseDynFloor, context, dataSupport);
            }
        }
        return baseFunction;
    }

    public static String getModelType(String str) {
        if (TextUtils.equals(str, "productDetail")) {
            return "jdos_warecoresoa";
        }
        HashMap<String, String> hashMap = sReportType;
        if (hashMap == null || !hashMap.containsKey(str)) {
            return "";
        }
        sReportType.get(str);
        return "";
    }

    public static String getReportType(String str) {
        HashMap<String, String> hashMap;
        return (TextUtils.isEmpty(str) || (hashMap = sReportType) == null) ? "" : hashMap.get(str);
    }

    public static String getSystemCode(String str) {
        return TextUtils.isEmpty(str) ? "" : sSysCodemap.get(str);
    }

    public static void init(String str, String str2, String str3, Class<? extends BaseDynFloor> cls, Class<? extends BaseFunction> cls2) {
        init(str, str2, str3, cls);
        registFunction(str, ISV_DYN_COMMON_EVENT_CLASS, cls2);
    }

    public static boolean isTemplateDownloaded(String str, String str2) {
        if (IS_DEBUG) {
            return true;
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        DYTemplateStatus templateStatus = DynamicSdk.getDriver().getTemplateStatus(str, str2);
        return templateStatus.getB() || templateStatus.getA();
    }

    public static void registFunction(String str, String str2, Class<? extends BaseFunction> cls) {
        HashMap<String, Class<? extends BaseFunction>> hashMap;
        if (TextUtils.isEmpty(str2) || cls == null) {
            return;
        }
        if (sBaseFactions == null) {
            sBaseFactions = new HashMap<>();
        }
        if (sBaseFactions.containsKey(str2)) {
            hashMap = sBaseFactions.get(str2);
        } else {
            HashMap<String, Class<? extends BaseFunction>> hashMap2 = new HashMap<>();
            sBaseFactions.put(str, hashMap2);
            hashMap = hashMap2;
        }
        if (hashMap == null || hashMap.containsKey(str2)) {
            return;
        }
        hashMap.put(str2, cls);
    }

    public static void removeFunction(String str) {
        HashMap<String, HashMap<String, Class<? extends BaseFunction>>> hashMap;
        if (TextUtils.isEmpty(str) || (hashMap = sBaseFactions) == null || !hashMap.containsKey(str)) {
            return;
        }
        sBaseFactions.remove(str);
    }

    public static void init(String str, String str2, String str3, Class<? extends BaseDynFloor> cls) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        DynamicSdk.getDriver().prepare(str2, "");
        sSysCodemap.put(str, str2);
        sReportType.put(str, str3);
        FloorManager floorManager = PlatformHelper.getFloorManager(str);
        if (floorManager.getClassById("bpDynamicFloor") == null) {
            floorManager.registerFloor("bpDynamicFloor", cls, false, true);
        }
    }
}
