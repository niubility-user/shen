package com.jingdong.manto.jsapi.camera;

import android.view.View;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.q.n;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends com.jingdong.manto.jsapi.base.b {
    @Override // com.jingdong.manto.jsapi.base.d
    public int a(JSONObject jSONObject) {
        return jSONObject.optInt("cameraId");
    }

    @Override // com.jingdong.manto.jsapi.base.b
    public boolean a(n nVar, int i2, View view, JSONObject jSONObject) {
        MantoCameraViewContainer mantoCameraViewContainer;
        if (!(view instanceof CoverViewContainer) || (mantoCameraViewContainer = (MantoCameraViewContainer) ((CoverViewContainer) view).convertTo(MantoCameraViewContainer.class)) == null) {
            return false;
        }
        nVar.b((n.g0) mantoCameraViewContainer);
        nVar.b((n.f0) mantoCameraViewContainer);
        nVar.b((n.c0) mantoCameraViewContainer);
        nVar.b((n.e0) mantoCameraViewContainer);
        nVar.b((n.d0) mantoCameraViewContainer);
        mantoCameraViewContainer.k();
        return true;
    }

    @Override // com.jingdong.manto.jsapi.base.d, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "removeCamera";
    }
}
