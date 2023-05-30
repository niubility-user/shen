package com.jingdong.app.mall.home.floor.ctrl.t;

import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {
    JSONObject a;

    public a(Intent intent) {
        try {
            JSONObject jSONObject = new JSONObject();
            this.a = jSONObject;
            jSONObject.put("type", intent.getStringExtra("type"));
            this.a.put("subType", intent.getStringExtra("subType"));
            this.a.put("skuId", intent.getStringExtra("skuId"));
            this.a.put("pay", intent.getStringExtra("pay"));
            this.a.put("channel", intent.getStringExtra("channel"));
            this.a.put("source", intent.getStringExtra("source"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public com.jingdong.app.mall.home.r.c.b a() {
        if (this.a != null) {
            com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c("");
            String optString = this.a.optString("pay");
            if (TextUtils.isEmpty(optString)) {
                optString = "0";
            }
            c2.a("pay", optString);
            c2.a("opentype", "2");
            c2.a("source", this.a.optString("source"));
            return c2;
        }
        return null;
    }

    public void b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = this.a;
            if (jSONObject2 == null) {
                return;
            }
            jSONObject.put("channelInfo", jSONObject2.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = this.a;
            if (jSONObject2 == null) {
                return;
            }
            jSONObject.put(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID, jSONObject2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            this.a = jSONObject2;
            jSONObject2.put("type", jSONObject.optString("type"));
            this.a.put("subType", jSONObject.optString("subType"));
            this.a.put("skuId", jSONObject.optString("skuId"));
            this.a.put("pay", jSONObject.optString("pay"));
            this.a.put("channel", jSONObject.optString("channel"));
            this.a.put("source", jSONObject.optString("source"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
