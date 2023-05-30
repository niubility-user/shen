package com.jingdong.sdk.perfmonitor.d;

import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class c extends b {
    private Map<String, List<String>> d;

    public c(Context context) {
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
            if (jSONObject.has("tab_module")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("tab_module");
                if (jSONObject2.length() > 0) {
                    this.d = new HashMap();
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONObject jSONObject3 = jSONObject2.getJSONObject(next);
                        if ("1".equals(jSONObject3.getString("switch")) && jSONObject3.has("fragments")) {
                            JSONArray jSONArray = jSONObject3.getJSONArray("fragments");
                            if (jSONArray.length() > 0) {
                                LinkedList linkedList = new LinkedList();
                                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                    String optString = jSONArray.optString(i2);
                                    if (!TextUtils.isEmpty(optString)) {
                                        linkedList.add(optString);
                                    }
                                }
                                this.d.put(next, linkedList);
                            }
                        }
                    }
                }
            }
        } catch (JSONException e2) {
            OKLog.e("PerfMonitor", "JSON\u89e3\u6790\u9519\u8bef", e2);
        }
    }

    public boolean c(String str) {
        Map<String, List<String>> map = this.d;
        return map != null && map.containsKey(str);
    }

    public boolean d(Fragment fragment) {
        Map<String, List<String>> map;
        if (this.a && fragment != null && fragment.getActivity() != null && (map = this.d) != null && map.size() != 0) {
            String name = fragment.getActivity().getClass().getName();
            if (this.d.containsKey(name) && this.d.get(name).contains(fragment.getClass().getName())) {
                return true;
            }
        }
        return false;
    }
}
