package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes6.dex */
public class CalorieImageExpUtil {
    private static final String CALORIE_GET_IMG_EVENT = "calorie_get_img";
    private static final String SWITCH_KEY = "calorie_img_monitor";

    public static void calorieGetImgExp(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(SWITCH_KEY, "");
        if (TextUtils.isEmpty(switchStringValue)) {
            return;
        }
        String[] split = switchStringValue.split(DYConstants.DY_REGEX_COMMA);
        int length = split.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (TextUtils.equals(split[i2], str)) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("moduleid", (Object) str);
            jDJSONObject.put("imgid", (Object) str2);
            jDJSONObject.put("suc", (Object) str3);
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), CALORIE_GET_IMG_EVENT, "", "", "", "", jDJSONObject.toJSONString(), null);
        }
    }
}
