package com.jingdong.manto.m.h1;

import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        try {
            jSONObject.toString();
            ArrayList arrayList = new ArrayList();
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject == null) {
                return;
            }
            JSONArray optJSONArray = optJSONObject.optJSONArray("plugins");
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                String optString = optJSONArray.getJSONObject(i3).optString("provider");
                HashMap hashMap = new HashMap();
                hashMap.put("plugin_id", optString);
                hashMap.put("banning", "");
                hashMap.put("runnging_flag_info", "");
                hashMap.put("noncestr", "");
                hashMap.put(VerifyTracker.KEY_TIMESTAMP, "");
                hashMap.put("host_sign", "");
                hashMap.put("request_domain", "");
                hashMap.put("upload_domain", "");
                hashMap.put("download_domain", "");
                arrayList.add(hashMap);
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, arrayList);
            HashMap hashMap3 = new HashMap();
            hashMap3.put("data", hashMap2);
            hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, hashMap3, str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "verifyPlugin";
    }
}
