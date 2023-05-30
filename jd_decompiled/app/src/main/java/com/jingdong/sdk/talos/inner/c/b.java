package com.jingdong.sdk.talos.inner.c;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.talos.inner.c.a;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public a a;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public Context f15510c;

    public b(Context context) {
        this.a = null;
        this.b = false;
        this.f15510c = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        String string = this.f15510c.getSharedPreferences("logx_strategyInfo", 0).getString("strategy", "");
        if (!TextUtils.isEmpty(string)) {
            a(string);
            return;
        }
        this.a = new a.C0747a().a();
        this.b = true;
    }

    public final void a(String str) {
        String str2;
        String str3;
        String str4;
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
            String optString = optJSONObject.optString("enable", "0");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("logReport");
            String str5 = "";
            if (optJSONObject2 != null) {
                str3 = optJSONObject2.optString("logDate", "");
                str4 = optJSONObject2.optString("reportNet", "");
                str2 = optJSONObject2.optString("logId", "");
            } else {
                str2 = "";
                str3 = str2;
                str4 = str3;
            }
            JSONObject optJSONObject3 = optJSONObject.optJSONObject("logConfig");
            int i2 = 3;
            int i3 = 500;
            int i4 = 50;
            int i5 = 2000;
            int i6 = 7;
            if (optJSONObject3 != null) {
                i6 = optJSONObject3.optInt("logMaxAge", 7);
                i5 = optJSONObject3.optInt("logMaxSize", 2000);
                i4 = optJSONObject3.optInt("minSdcardSize", 50);
                i3 = optJSONObject3.optInt("maxQueueSize", 500);
                i2 = optJSONObject3.optInt("level", 3);
                str5 = optJSONObject3.optString("reportUrl", "");
            }
            a.C0747a c0747a = new a.C0747a();
            c0747a.a = TextUtils.equals(optString, "1");
            c0747a.b = str3;
            c0747a.f15503c = str4;
            c0747a.d = str2;
            c0747a.f15504e = i6;
            c0747a.f15505f = i5;
            c0747a.f15506g = i4;
            c0747a.f15507h = i3;
            c0747a.f15508i = i2;
            c0747a.f15509j = str5;
            this.a = c0747a.b();
            this.b = false;
        } catch (Throwable unused) {
            this.a = new a.C0747a().a();
            this.b = true;
        }
    }
}
