package com.jingdong.manto.jsapi.container;

import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.utils.MantoDensityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b {
    public static void a(View view, JSONObject jSONObject) {
        boolean z;
        if (view == null || jSONObject == null) {
            return;
        }
        int parseColor = MantoDensityUtils.parseColor(jSONObject.optString(DYConstants.DY_BG_COLOR));
        int parseColor2 = MantoDensityUtils.parseColor(jSONObject.optString("borderColor"));
        float convertToDeviceSize2 = MantoDensityUtils.convertToDeviceSize2(jSONObject, "borderRadius", 0.0f);
        float convertToDeviceSize22 = MantoDensityUtils.convertToDeviceSize2(jSONObject, "borderWidth", 0.0f);
        if (view instanceof a) {
            a aVar = (a) view;
            aVar.setBackGroundColor(parseColor);
            aVar.setBorderColor(parseColor2);
            aVar.setBorderRadius(convertToDeviceSize2);
            aVar.setBorderWidth(convertToDeviceSize22);
            z = true;
        } else {
            z = false;
        }
        try {
            float f2 = (float) jSONObject.getDouble(ViewProps.OPACITY);
            if (f2 >= 0.0f && f2 <= 1.0f) {
                view.setAlpha(f2);
                z = true;
            }
        } catch (JSONException unused) {
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("padding");
        if (optJSONArray != null && optJSONArray.length() == 4) {
            view.setPadding(MantoDensityUtils.convertToDeviceSize2(optJSONArray, 3), MantoDensityUtils.convertToDeviceSize2(optJSONArray, 0), MantoDensityUtils.convertToDeviceSize2(optJSONArray, 1), MantoDensityUtils.convertToDeviceSize2(optJSONArray, 2));
        }
        if (z) {
            view.invalidate();
        }
    }
}
