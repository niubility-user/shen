package com.jingdong.app.mall.home.r.c;

import android.text.TextUtils;
import com.jingdong.app.mall.home.o.a.f;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b extends JSONObject {
    public b() {
    }

    public static b b() {
        return new b();
    }

    public static b c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new b(str);
            }
        } catch (Exception e2) {
            f.s0("FloorMaiDianJson", e2);
        }
        return new b();
    }

    public static JSONArray d() {
        return new JSONArray();
    }

    public JSONObject a(String str, Object obj) {
        return put(str, obj);
    }

    public JSONObject e(JSONObject jSONObject) {
        if (jSONObject == null) {
            return this;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                put(next, jSONObject.get(next));
            }
        } catch (Exception e2) {
            f.s0(this, e2);
        }
        return this;
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, Object obj) {
        try {
            return super.put(str, obj);
        } catch (Exception e2) {
            f.s0(this, e2);
            return this;
        }
    }

    private b(String str) throws JSONException {
        super(str);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, int i2) {
        try {
            return super.put(str, i2);
        } catch (Exception e2) {
            f.s0(this, e2);
            return this;
        }
    }
}
