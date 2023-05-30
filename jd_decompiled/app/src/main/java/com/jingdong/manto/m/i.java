package com.jingdong.manto.m;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i extends d0 {
    private static float a(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness") / 255.0f;
        } catch (Settings.SettingNotFoundException unused) {
            return 0.0f;
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        MantoCore core = getCore(hVar);
        if (core == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        Activity activity = core.getActivity();
        if (activity == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            MantoLog.e("JsApiGetScreenBrightnes", "context is null, invoke fail!");
            return;
        }
        float f2 = activity.getWindow().getAttributes().screenBrightness;
        if (f2 < 0.0f) {
            f2 = a(activity);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("value", Float.valueOf(f2));
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, hashMap, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getScreenBrightness";
    }
}
