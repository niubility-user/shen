package com.jingdong.sdk.perfmonitor.a;

import androidx.annotation.NonNull;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class e {
    private long a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private long f15302c;
    private JSONArray d;

    private static JSONArray a(Set<Thread> set) {
        JSONArray jSONArray = new JSONArray();
        if (set != null) {
            for (Thread thread : set) {
                if (thread != null && thread.getName() != null && !thread.getName().startsWith("Thread-")) {
                    jSONArray.put(thread.getName());
                }
            }
        }
        return jSONArray;
    }

    public void b(Set<Thread> set) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.a == 0) {
            this.a = currentTimeMillis;
        }
        int size = set.size();
        if (size >= this.b) {
            this.d = a(set);
            this.f15302c = currentTimeMillis;
            this.b = size;
        }
    }

    @NonNull
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ts", this.f15302c);
            jSONObject2.put("value", this.b);
            JSONArray jSONArray = this.d;
            if (jSONArray != null) {
                jSONObject2.put("threads", jSONArray);
            }
            jSONObject.put("max", jSONObject2);
            jSONObject.put("duration", System.currentTimeMillis() - this.a);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }
}
