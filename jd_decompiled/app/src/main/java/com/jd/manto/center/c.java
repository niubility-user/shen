package com.jd.manto.center;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.manto.launch.LaunchParam;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c {
    public static String a() {
        return Configuration.getSearchHost();
    }

    public static boolean b(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean c(List list) {
        return list != null && list.size() > 0;
    }

    public static void d(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        LaunchParam launchParam = new LaunchParam();
        launchParam.appId = str;
        if (!TextUtils.isEmpty(str2)) {
            launchParam.debugType = str2;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("source", DYConstants.DY_CENTER);
            launchParam.extrasJson = jSONObject.toString();
            launchParam.scene = str3;
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.jingdong.a.p(launchParam, context);
    }
}
