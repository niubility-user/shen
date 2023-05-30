package com.jd.libs.hybrid.offlineload.utils;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.MtaUtils;
import com.jd.libs.hybrid.base.util.PerfMtaUtils;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jingdong.common.web.managers.WebPerfManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes16.dex */
public class OfflineMtaUtils {
    public static final int CONFIG_ERR = -1;
    public static final int CONFIG_OFFLINE_FIRST_DOWNLOAD = 1;
    public static final int CONFIG_OFFLINE_NO_DATA = 3;
    public static final int CONFIG_OFFLINE_NO_UPDATE = 0;
    public static final int CONFIG_OFFLINE_UPDATE = 2;
    public static final int TYPE_BIZ = 1;
    public static final int TYPE_COMMON = 2;
    public static final String UNPACK_ERR = "-1";
    public static final String UNPACK_INVALID = "-2";
    public static final String UNPACK_MERGE_ERR = "-3";
    public static final String UNPACK_SUCCESS = "0";

    private static List<String> parsePreRenderData(String str) {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("prerender");
            JSONArray optJSONArray = optJSONObject != null ? optJSONObject.optJSONArray(DynamicPrepareFetcher.KEY_PREPARE_MODULES) : null;
            if (optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList(optJSONArray.length());
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject jSONObject = optJSONArray.getJSONObject(i2);
                    if (jSONObject != null) {
                        String optString = jSONObject.optString("appid");
                        if (!TextUtils.isEmpty(optString)) {
                            arrayList.add(optString);
                        }
                    }
                }
                return arrayList;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    private static void sendConfigMta(int i2, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", String.valueOf(i2));
            if (jSONArray != null) {
                jSONObject.put("detail", jSONArray);
            }
        } catch (JSONException e2) {
            Log.e("OfflineMtaUtils", e2);
        }
        MtaUtils.sendExposureData(HybridSettings.getAppContext(), "hybrid_config", "", "", "", "", jSONObject.toString(), null);
        HashMap hashMap = new HashMap();
        hashMap.put("status", String.valueOf(i2));
        if (jSONArray != null) {
            hashMap.put("detail", jSONArray.toString());
        }
        PerfMtaUtils.onConfigGot(hashMap);
    }

    public static void sendFetchConfigMta(int i2) {
        sendConfigMta(i2, null);
    }

    public static void sendFetchConfigMta(int i2, List<OfflineEntity> list, String str) {
        List<String> parsePreRenderData;
        JSONArray jSONArray;
        JSONArray jSONArray2 = null;
        if (list != null) {
            try {
                parsePreRenderData = parsePreRenderData(str);
                jSONArray = new JSONArray();
            } catch (JSONException e2) {
                e = e2;
            }
            try {
                for (OfflineEntity offlineEntity : list) {
                    if (offlineEntity != null) {
                        JSONObject jSONObject = new JSONObject();
                        String appid = offlineEntity.getAppid();
                        jSONObject.put(WebPerfManager.HYBRID_ID, appid);
                        jSONObject.put(WebPerfManager.HYBRID_FILE_VERSION, offlineEntity.getFileInfo() == null ? "" : String.valueOf(offlineEntity.getFileInfo().getVersionCode()));
                        jSONObject.put(WebPerfManager.HYBRID_CONFIG_VERSION, String.valueOf(offlineEntity.getModuleCode()));
                        if (parsePreRenderData != null && !parsePreRenderData.isEmpty() && !TextUtils.isEmpty(appid)) {
                            jSONObject.put("preRender", parsePreRenderData.contains(appid) ? "1" : "0");
                        }
                        jSONArray.put(jSONObject);
                    }
                }
                jSONArray2 = jSONArray;
            } catch (JSONException e3) {
                e = e3;
                jSONArray2 = jSONArray;
                e.printStackTrace();
                sendConfigMta(i2, jSONArray2);
            }
        }
        sendConfigMta(i2, jSONArray2);
    }

    public static void sendFetchConfigMta(int i2, Map<String, OfflineModule> map, String str) {
        List<String> parsePreRenderData;
        JSONArray jSONArray;
        JSONArray jSONArray2 = null;
        if (map != null) {
            try {
                parsePreRenderData = parsePreRenderData(str);
                jSONArray = new JSONArray();
            } catch (JSONException e2) {
                e = e2;
            }
            try {
                for (OfflineModule offlineModule : map.values()) {
                    if (offlineModule != null) {
                        JSONObject jSONObject = new JSONObject();
                        String appid = offlineModule.getAppid();
                        jSONObject.put(WebPerfManager.HYBRID_ID, appid);
                        jSONObject.put(WebPerfManager.HYBRID_FILE_VERSION, offlineModule.getFileInfo() == null ? "" : String.valueOf(offlineModule.getFileInfo().getVersionCode()));
                        jSONObject.put(WebPerfManager.HYBRID_CONFIG_VERSION, String.valueOf(offlineModule.getModuleCode()));
                        if (parsePreRenderData != null && !parsePreRenderData.isEmpty() && !TextUtils.isEmpty(appid)) {
                            jSONObject.put("preRender", parsePreRenderData.contains(appid) ? "1" : "0");
                        }
                        jSONArray.put(jSONObject);
                    }
                }
                jSONArray2 = jSONArray;
            } catch (JSONException e3) {
                e = e3;
                jSONArray2 = jSONArray;
                e.printStackTrace();
                sendConfigMta(i2, jSONArray2);
            }
        }
        sendConfigMta(i2, jSONArray2);
    }
}
