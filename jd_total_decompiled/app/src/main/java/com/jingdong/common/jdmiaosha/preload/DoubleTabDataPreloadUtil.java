package com.jingdong.common.jdmiaosha.preload;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DoubleTabDataPreloadUtil {
    public static final String FUNCTION_ID = "qryCompositeMaterials";
    public static final String KEY_ACTIVITY_ID = "activityId";
    public static final String KEY_AD_GROUP_ID = "adGroupId";
    public static final String KEY_APPLY_KEY = "applyKey";
    public static final String KEY_TAB_DETAIL = "tabList";
    public static final String KEY_TAB_LINK = "tabLink";
    public static final String KEY_TAB_PARAMS = "tabParams";
    public static final String KEY_TAB_PARAMS_DECODE = "tabParamsDecode";
    public static final String PRELOAD = "preload_double_tab";
    public static final int PRELOAD_DEFAULT = 3;
    public static final int PRELOAD_LOCAL = 1;
    public static final int PRELOAD_LOCAL_DEFAULT = 5;
    public static final int PRELOAD_LOCAL_NET = 4;
    public static final int PRELOAD_LOCAL_NO = 6;
    public static final int PRELOAD_NET = 2;
    public static final int PRELOAD_NO = 0;
    private static final String TAG = "DeepLinkWebContainer";
    private static final int VALID_SIZE = 2;

    public static Map<String, Object> checkRequestValid(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        String string = jDJSONObject.getString("activityId");
        String string2 = jDJSONObject.getString(KEY_APPLY_KEY);
        String string3 = jDJSONObject.getString(KEY_AD_GROUP_ID);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return null;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put(KEY_APPLY_KEY, string2);
        hashMap.put("activityId", string);
        hashMap.put("pageId", "");
        JDJSONArray jDJSONArray = new JDJSONArray();
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("mapTo", (Object) "dataInfo");
        jDJSONObject2.put("type", (Object) "advertGroup");
        jDJSONObject2.put("id", (Object) string3);
        jDJSONObject2.put("next", (Object) new JDJSONArray());
        jDJSONArray.add(jDJSONObject2);
        hashMap.put("qryParam", jDJSONArray.toJSONString());
        JDJSONObject jDJSONObject3 = new JDJSONObject();
        jDJSONObject3.put(HybridSDK.LNG, (Object) String.valueOf(JDLocationCache.getInstance().getLocation().getLng()));
        jDJSONObject3.put("lat", (Object) String.valueOf(JDLocationCache.getInstance().getLocation().getLat()));
        hashMap.put("geo", jDJSONObject3.toJSONString());
        return hashMap;
    }

    private static String decode(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (Exception unused) {
            return null;
        }
    }

    private static String decodeTabParams(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString(KEY_TAB_PARAMS);
            if (TextUtils.isEmpty(string)) {
                return genTabParams(bundle).toJSONString();
            }
            return decode(string);
        }
        return null;
    }

    private static JDJSONObject genTabParams(Bundle bundle) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("tabIndex", (Object) bundle.getString("tabIndex"));
        jDJSONObject.put("activityId", (Object) bundle.getString("activityId"));
        jDJSONObject.put(KEY_APPLY_KEY, (Object) bundle.getString(KEY_APPLY_KEY));
        jDJSONObject.put(KEY_AD_GROUP_ID, (Object) bundle.getString(KEY_AD_GROUP_ID));
        jDJSONObject.put("tabStyle", (Object) bundle.getString("tabStyle"));
        jDJSONObject.put("isExtend", (Object) bundle.getString("isExtend"));
        return jDJSONObject;
    }

    private static int handleNewPreload(JDJSONObject jDJSONObject) {
        Map<String, Object> checkRequestValid = checkRequestValid(jDJSONObject);
        if (checkRequestValid != null) {
            return preloadRequest(checkRequestValid) ? 4 : 6;
        }
        logD("params is invalid, return false");
        return 5;
    }

    public static void handlePreload(Context context, Bundle bundle, boolean z) {
        if (context == null || bundle == null) {
            return;
        }
        bundle.putLong(BottomNavigationConstant.KEY_PRELOAD_REQUEST_TIME, System.currentTimeMillis());
        String string = bundle.getString(KEY_TAB_LINK);
        if (z && !TextUtils.isEmpty(string)) {
            JDJSONObject genTabParams = genTabParams(bundle);
            if (genTabParams != null) {
                bundle.putString(KEY_TAB_PARAMS_DECODE, genTabParams.toJSONString());
            }
            int handleNewPreload = handleNewPreload(genTabParams);
            logD("start with preloadType: " + handleNewPreload);
            bundle.putInt(PRELOAD, handleNewPreload);
            return;
        }
        int i2 = 0;
        int i3 = 1;
        logD("isSwitchOpen: ", Boolean.valueOf(z), "--tabLink: ", string);
        String decodeTabParams = decodeTabParams(bundle);
        bundle.putString(KEY_TAB_PARAMS_DECODE, decodeTabParams);
        JDJSONObject parseObject = parseObject(decodeTabParams);
        if (parseObject == null) {
            logD("start with preloadType: 3");
            bundle.putInt(PRELOAD, 3);
            return;
        }
        JDJSONArray jSONArray = parseObject.getJSONArray(KEY_TAB_DETAIL);
        if (jSONArray == null || jSONArray.size() < 2) {
            logD("tabList is null,start net request");
            Map<String, Object> checkRequestValid = checkRequestValid(parseObject);
            if (checkRequestValid == null) {
                logD("params is invalid, return false");
                i2 = 3;
            } else if (preloadRequest(checkRequestValid)) {
                i2 = 2;
            }
            i3 = i2;
        }
        logD("start with preloadType: " + i3);
        bundle.putInt(PRELOAD, i3);
    }

    public static void logD(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, str);
        }
    }

    private static JDJSONObject parseObject(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return JDJSON.parseObject(str);
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean preloadRequest(Map<String, Object> map) {
        return JumpNetDataProvider.getInstance().request(FUNCTION_ID, map, new boolean[0]);
    }

    public static void logD(Object... objArr) {
        if (OKLog.D) {
            OKLog.d(TAG, objArr);
        }
    }
}
