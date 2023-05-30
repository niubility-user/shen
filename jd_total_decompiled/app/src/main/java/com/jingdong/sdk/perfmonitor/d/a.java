package com.jingdong.sdk.perfmonitor.d;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class a extends e {
    public a(@NonNull Context context) {
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(context.getApplicationContext(), "11", "1");
        if (stategyEntitiy == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("PerfMonitor", String.format("launch: %s, params: %s", stategyEntitiy.ret, stategyEntitiy.param));
        }
        try {
            if (TextUtils.isEmpty(stategyEntitiy.param) || !"1".equals(stategyEntitiy.ret)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(stategyEntitiy.param);
            this.a = "1".equals(jSONObject.getString("type"));
            if (jSONObject.has("module")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("module");
                if (jSONObject2.length() > 0) {
                    this.b = new ArrayMap();
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONObject jSONObject3 = jSONObject2.getJSONObject(next);
                        String string = jSONObject3.has("page") ? jSONObject3.getString("page") : next;
                        if ("1".equals(jSONObject3.getString("switch"))) {
                            this.b.put(next, string);
                        }
                    }
                }
            }
        } catch (JSONException e2) {
            OKLog.e("PerfMonitor", "JSON\u89e3\u6790\u9519\u8bef", e2);
        }
    }
}
