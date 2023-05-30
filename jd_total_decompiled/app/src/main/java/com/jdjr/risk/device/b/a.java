package com.jdjr.risk.device.b;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public abstract class a {
    protected com.jdjr.risk.device.entity.a a;
    protected String b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f7323c;

    public abstract String a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> a(Context context, JSONObject jSONObject) {
        this.a.b();
        if (jSONObject != null) {
            b(context, jSONObject);
        }
        return this.a.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONObject jSONObject, String str) {
        this.f7323c = jSONObject;
        this.b = str;
    }

    protected abstract void b(Context context, JSONObject jSONObject);
}
