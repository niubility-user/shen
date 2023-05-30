package com.jingdong.app.mall.home.n.g.v;

import android.text.TextUtils;
import com.jingdong.app.mall.home.o.a.f;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class d extends JSONObject {
    private d() {
    }

    public static d b() {
        return c(null);
    }

    public static d c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new d(str);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return new d();
    }

    public JSONObject a(String str, Object obj) {
        return put(str, obj);
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

    private d(String str) throws JSONException {
        super(str);
    }
}
