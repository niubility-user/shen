package com.jingdong.manto.card;

import android.text.TextUtils;
import com.jingdong.a;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.manto.preload.b;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class MantoCardHelper {
    public static final String CARD_CLICK_CLICK_CARD = "J_SmartCard_Module";
    public static final String CARD_EXPOSURE_LOAD_CARD = "J_SmartCard_ModuleLoadExpo";
    private static Map<CardRequestParameter, String> activityUUidCache = new ConcurrentHashMap();
    private static int allowCardsCount = Integer.MAX_VALUE;
    private static boolean useCachedCardJdaInfo;

    public static void addUUidCache(CardRequestParameter cardRequestParameter, String str) {
        activityUUidCache.put(cardRequestParameter, str);
    }

    public static int getAllowCardsCount() {
        return allowCardsCount;
    }

    public static String getUUidFromCache(CardRequestParameter cardRequestParameter) {
        return activityUUidCache.get(cardRequestParameter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject makeClickETModel(CardRequestParameter cardRequestParameter) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(EtModelMaker.KEY_P_ID, "J_SmartCard");
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(EtModelMaker.KEY_A_ID, cardRequestParameter.getCardID());
            jSONObject3.put(EtModelMaker.KEY_A_P, "0");
            jSONArray.put(jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put(EtModelMaker.KEY_A_ID, cardRequestParameter.getScene());
            jSONObject4.put(EtModelMaker.KEY_A_P, "0");
            jSONArray.put(jSONObject4);
            jSONObject2.put("area", jSONArray);
            jSONObject2.put(EtModelMaker.KEY_LA_ID, "");
            jSONObject2.put(EtModelMaker.KEY_LA_P, "0");
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("src", "");
            jSONObject5.put("abtest", new JSONArray());
            jSONObject5.put(EtModelMaker.KEY_CONT, new JSONArray());
            jSONObject2.put(EtModelMaker.KEY_STM, jSONObject5);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("project_id", cardRequestParameter.getProject_id());
            jSONObject6.put("floorId", cardRequestParameter.getFloorId());
            jSONObject6.put("vapp_type", cardRequestParameter.getDebugType());
            jSONObject6.put("host_id", a.b);
            String vendorId = cardRequestParameter.getVendorId();
            String str = "-100";
            if (TextUtils.isEmpty(vendorId)) {
                vendorId = "-100";
            }
            jSONObject6.put("venderId", vendorId);
            String uUidFromCache = getUUidFromCache(cardRequestParameter);
            if (!TextUtils.isEmpty(uUidFromCache)) {
                str = uUidFromCache;
            }
            jSONObject6.put("activityUuid", str);
            jSONObject2.put("ext", jSONObject6);
            jSONObject.put(EtModelMaker.KEY_LSTM, jSONObject2);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject makeExpoETModel(CardRequestParameter cardRequestParameter) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(EtModelMaker.KEY_P_ID, "J_SmartCard");
            JSONArray jSONArray2 = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(EtModelMaker.KEY_A_ID, cardRequestParameter.getCardID());
            jSONObject3.put(EtModelMaker.KEY_A_P, "0");
            jSONArray2.put(jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put(EtModelMaker.KEY_A_ID, cardRequestParameter.getScene());
            jSONObject4.put(EtModelMaker.KEY_A_P, "0");
            jSONArray2.put(jSONObject4);
            jSONObject2.put("area", jSONArray2);
            jSONObject2.put(EtModelMaker.KEY_LA_ID, "");
            jSONObject2.put(EtModelMaker.KEY_LA_P, "0");
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("src", "");
            jSONObject5.put("abtest", new JSONArray());
            jSONObject5.put(EtModelMaker.KEY_CONT, new JSONArray());
            jSONObject2.put(EtModelMaker.KEY_STM, jSONObject5);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("project_id", cardRequestParameter.getProject_id());
            jSONObject6.put("floorId", cardRequestParameter.getFloorId());
            jSONObject6.put("vapp_type", cardRequestParameter.getDebugType());
            jSONObject6.put("host_id", a.b);
            String vendorId = cardRequestParameter.getVendorId();
            String str = "-100";
            if (TextUtils.isEmpty(vendorId)) {
                vendorId = "-100";
            }
            jSONObject6.put("venderId", vendorId);
            String uUidFromCache = getUUidFromCache(cardRequestParameter);
            if (!TextUtils.isEmpty(uUidFromCache)) {
                str = uUidFromCache;
            }
            jSONObject6.put("activityUuid", str);
            jSONObject2.put("ext", jSONObject6);
            jSONArray.put(jSONObject2);
            jSONObject.put(EtModelMaker.KEY_LSTMS, jSONArray);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void preloadInMain() {
        try {
            if (MantoProcessUtil.isMainProcess()) {
                b.a(null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void setAllowCardsCount(int i2) {
        allowCardsCount = i2;
    }

    public static void setUseCachedCardJdaInfo(boolean z) {
        useCachedCardJdaInfo = z;
    }

    public static boolean useCachedCardJdaInfo() {
        return useCachedCardJdaInfo;
    }
}
