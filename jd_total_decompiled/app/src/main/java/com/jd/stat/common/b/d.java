package com.jd.stat.common.b;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class d {
    private JSONObject a;

    public d a() {
        this.a = new JSONObject();
        return this;
    }

    public JSONObject b() {
        return this.a;
    }

    public d a(String str, String str2) {
        try {
            this.a.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return this;
    }

    public d a(String str, JSONArray jSONArray) {
        try {
            this.a.put(str, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return this;
    }
}
