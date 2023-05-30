package com.jingdong.manto.jsapi.camera;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.mta.ExposureRvUtils;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class e extends com.jingdong.manto.jsapi.base.c {
    @Override // com.jingdong.manto.jsapi.base.d
    public int a(JSONObject jSONObject) {
        return jSONObject.optInt("cameraId");
    }

    @Override // com.jingdong.manto.jsapi.base.c
    public boolean a(n nVar, int i2, View view, JSONObject jSONObject, String str) {
        if (view instanceof CoverViewContainer) {
            Context context = nVar.f14071i;
            MantoCameraViewContainer mantoCameraViewContainer = (MantoCameraViewContainer) ((CoverViewContainer) view).convertTo(MantoCameraViewContainer.class);
            if (mantoCameraViewContainer == null) {
                return false;
            }
            jSONObject.optInt("cameraId");
            String optString = jSONObject.optString("devicePosition", ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID);
            String optString2 = jSONObject.optString("flash", "auto");
            boolean optBoolean = jSONObject.optBoolean("needOutput", false);
            mantoCameraViewContainer.setAppUniqueId(nVar.c());
            if (jSONObject.optString("devicePosition").length() > 0) {
                mantoCameraViewContainer.a(optString, false);
            }
            if (jSONObject.optString("flash").length() > 0) {
                mantoCameraViewContainer.setFlash(optString2);
            }
            mantoCameraViewContainer.setNeedOutput(optBoolean);
            JSONObject optJSONObject = jSONObject.optJSONObject("position");
            int a = b.a(context, optJSONObject, "width", 0);
            int a2 = b.a(context, optJSONObject, "height", 0);
            boolean a3 = (a == 0 || a2 == 0) ? false : mantoCameraViewContainer.a(a, a2);
            String optString3 = jSONObject.optString("mode");
            if (!TextUtils.isEmpty(optString3)) {
                mantoCameraViewContainer.setMode(optString3);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("scanArea");
            if (optJSONArray != null && optJSONArray.length() == 4) {
                int density = (int) MantoDensityUtils.getDensity(context);
                mantoCameraViewContainer.a(optJSONArray.optInt(0) * density, optJSONArray.optInt(1) * density, optJSONArray.optInt(2) * density, density * optJSONArray.optInt(3));
            }
            mantoCameraViewContainer.setScanFreq(jSONObject.optInt("scanFreq"));
            if (a3) {
                mantoCameraViewContainer.k();
                mantoCameraViewContainer.g();
            } else {
                mantoCameraViewContainer.q();
            }
            mantoCameraViewContainer.setFrontIsHide(jSONObject.optBoolean(ExposureRvUtils.HIDE, false));
            return true;
        }
        return false;
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "updateCamera";
    }
}
