package com.jd.manto.d;

import android.content.Context;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.manto.sdk.api.IBizDaojia;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes17.dex */
public final class a implements IBizDaojia {
    @Override // com.jingdong.manto.sdk.api.IBizDaojia
    public void sendOrderData(Context context, JSONArray jSONArray, Map<String, String> map) {
        JDMtaUtils.getMaInitCommonInfo(context);
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String optString = jSONArray.optString(i2);
            JDMtaUtils.onSaveProductOrderPage(optString);
            OKLog.d("bizDaoj", "sku: " + optString);
        }
    }
}
