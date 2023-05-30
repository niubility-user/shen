package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class j0 extends d0 {

    /* loaded from: classes15.dex */
    class a extends IMantoHttpListener {
        a(j0 j0Var) {
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            MantoLog.e("CustomReport", th.getMessage());
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            MantoLog.d("CustomReport", Integer.valueOf(jSONObject.optInt("code", -1)));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("actionData"));
            PkgDetailEntity pkgDetailEntity = hVar.h().f13016h;
            int intValue = Integer.valueOf(pkgDetailEntity.type).intValue();
            String str2 = pkgDetailEntity.appId;
            String optString = jSONObject2.optString("eventID");
            JSONObject optJSONObject = jSONObject2.optJSONObject("data");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(optJSONObject);
            MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.k(str2, optString, jSONArray, jSONObject2.optString("uid"), intValue, jSONObject2.optInt("type", 1), jSONObject2.optString("version")), new a(this));
        } catch (Throwable unused) {
        }
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.q.n nVar, JSONObject jSONObject, int i2, String str) {
        nVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "reportRealtimeAction";
    }
}
