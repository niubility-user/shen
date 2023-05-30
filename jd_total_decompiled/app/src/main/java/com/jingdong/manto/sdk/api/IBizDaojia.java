package com.jingdong.manto.sdk.api;

import android.content.Context;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes16.dex */
public interface IBizDaojia extends IMantoSdkBase {
    void sendOrderData(Context context, JSONArray jSONArray, Map<String, String> map);
}
