package com.jingdong.manto.m.p0.d;

import android.util.Pair;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        if (jSONObject == null) {
            str2 = "fail:data is null";
        } else {
            hVar.a();
            try {
                Pair<Boolean, String> a = a.a(hVar, jSONObject.optString("src"), jSONObject.optString("title"), jSONObject.optString("epname"), jSONObject.optString("singer"), jSONObject.optString("coverImgUrl"), jSONObject.optString("webUrl"), jSONObject.optString("protocol"), jSONObject.optInt("startTime", -1));
                if (((Boolean) a.first).booleanValue()) {
                    hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS));
                    return;
                }
                hVar.a(i2, putErrMsg("fail:" + ((String) a.second)));
                return;
            } catch (Throwable unused) {
                str2 = "fail: internal error";
            }
        }
        hVar.a(i2, putErrMsg(str2));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setBackgroundAudioState";
    }
}
