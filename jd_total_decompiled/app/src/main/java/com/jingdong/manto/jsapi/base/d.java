package com.jingdong.manto.jsapi.base;

import com.jingdong.jdsdk.mta.ExposureRvUtils;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.d1.i;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class d extends d0 {
    i a;

    public d() {
    }

    public d(i iVar) {
        this.a = iVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int b(JSONObject jSONObject) {
        try {
            return jSONObject.optBoolean(ExposureRvUtils.HIDE) ? 4 : 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int c(JSONObject jSONObject) {
        try {
            return jSONObject.optInt("zIndex", 0);
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Boolean d(JSONObject jSONObject) {
        try {
            return Boolean.valueOf(jSONObject.optBoolean("fixed"));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float[] e(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("position"));
            return new float[]{MantoDensityUtils.convertToDeviceSize2(jSONObject2, "left", 0.0f), MantoDensityUtils.convertToDeviceSize2(jSONObject2, "top", 0.0f), MantoDensityUtils.convertToDeviceSize2(jSONObject2, "width", 0.0f), MantoDensityUtils.convertToDeviceSize2(jSONObject2, "height", 0.0f), jSONObject.optInt("zIndex", 0)};
        } catch (Exception unused) {
            return null;
        }
    }

    public int a(JSONObject jSONObject) {
        throw new JSONException("viewId do not exist, override the method getViewId(data).");
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        i iVar = this.a;
        if (iVar != null) {
            return iVar.a();
        }
        return null;
    }
}
