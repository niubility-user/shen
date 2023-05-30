package com.jingdong.app.mall.bundle.jdrhsdk.b;

import android.text.TextUtils;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f8145c;

    public a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optString("code");
            jSONObject.optString(NotificationMessageSummary.ECHO);
            JSONObject optJSONObject = jSONObject.optJSONObject("disposal");
            if (optJSONObject != null) {
                this.a = optJSONObject.optString("rpId");
                JSONObject jSONObject2 = new JSONObject(optJSONObject.optString("evContent"));
                this.b = jSONObject2.optString("evType");
                jSONObject2.optString("evUrl");
                this.f8145c = jSONObject2.optString("evApi");
            }
        } catch (Exception unused) {
        }
    }

    public String a() {
        return TextUtils.isEmpty(this.f8145c) ? "" : this.f8145c;
    }

    public String b() {
        return TextUtils.isEmpty(this.b) ? "" : this.b;
    }

    public int c() {
        String b = b();
        b.hashCode();
        if (b.equals("2")) {
            return 101;
        }
        return !b.equals("3") ? -1 : 100;
    }

    public String d() {
        return TextUtils.isEmpty(this.a) ? "" : this.a;
    }
}
