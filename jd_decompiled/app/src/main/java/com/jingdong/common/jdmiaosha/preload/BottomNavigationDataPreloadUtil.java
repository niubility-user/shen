package com.jingdong.common.jdmiaosha.preload;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class BottomNavigationDataPreloadUtil {
    private static final String TAG = "BottomNavigation";

    public static Map<String, Object> generaValueMap(Bundle bundle) {
        String str;
        String str2;
        String str3;
        if (bundle != null) {
            str2 = bundle.getString(BottomNavigationConstant.KEY_SYSTEM_NAME);
            str3 = bundle.getString(BottomNavigationConstant.KEY_SCENE_NAME);
            str = bundle.getString(BottomNavigationConstant.KEY_FLOOR_NAME);
        } else {
            str = null;
            str2 = null;
            str3 = null;
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            return null;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put(BottomNavigationConstant.KEY_SYSTEM_NAME, str2);
        hashMap.put(BottomNavigationConstant.KEY_SCENE_NAME, str3);
        hashMap.put(BottomNavigationConstant.KEY_FLOOR_NAME, str);
        return hashMap;
    }

    public static void handlePreload(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        bundle.putLong(BottomNavigationConstant.KEY_PRELOAD_REQUEST_TIME, System.currentTimeMillis());
        Map<String, Object> generaValueMap = generaValueMap(bundle);
        int i2 = 0;
        if (generaValueMap == null) {
            i2 = 2;
        } else if (JumpNetDataProvider.getInstance().request(BottomNavigationConstant.FUNCTION_ID, generaValueMap, true)) {
            i2 = 1;
        }
        logD("start with preloadType: " + i2);
        bundle.putInt(BottomNavigationConstant.KEY_PRELOAD, i2);
    }

    public static void logD(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, str);
        }
    }
}
