package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class s3 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f19000g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Context f19001h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ String f19002i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ String f19003j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ r3 f19004k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s3(r3 r3Var, String str, Context context, String str2, String str3) {
        this.f19004k = r3Var;
        this.f19000g = str;
        this.f19001h = context;
        this.f19002i = str2;
        this.f19003j = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        String str;
        String str2;
        Context context2;
        String str3;
        String str4;
        r3 r3Var;
        Context context3;
        r3 r3Var2;
        t3 t3Var;
        Context context4;
        if (TextUtils.isEmpty(this.f19000g)) {
            context = this.f19001h;
            str = DYConstants.DY_NULL_STR;
            str2 = "A receive a incorrect message with empty info";
        } else {
            try {
                n3.a(this.f19001h, this.f19000g, 1001, "get message");
                JSONObject jSONObject = new JSONObject(this.f19000g);
                String optString = jSONObject.optString("action");
                String optString2 = jSONObject.optString("awakened_app_packagename");
                String optString3 = jSONObject.optString("awake_app_packagename");
                String optString4 = jSONObject.optString("awake_app");
                String optString5 = jSONObject.optString("awake_type");
                int optInt = jSONObject.optInt("awake_foreground", 0);
                if (this.f19002i.equals(optString3) && this.f19003j.equals(optString4)) {
                    if (!TextUtils.isEmpty(optString5) && !TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString4) && !TextUtils.isEmpty(optString2)) {
                        this.f19004k.o(optString3);
                        this.f19004k.k(optString4);
                        q3 q3Var = new q3();
                        q3Var.f(optString);
                        q3Var.d(optString2);
                        q3Var.c(optInt);
                        q3Var.j(this.f19000g);
                        if ("service".equals(optString5)) {
                            if (TextUtils.isEmpty(optString)) {
                                q3Var.h("com.xiaomi.mipush.sdk.PushMessageHandler");
                                r3Var2 = this.f19004k;
                                t3Var = t3.SERVICE_COMPONENT;
                                context4 = this.f19001h;
                            } else {
                                r3Var2 = this.f19004k;
                                t3Var = t3.SERVICE_ACTION;
                                context4 = this.f19001h;
                            }
                            r3Var2.i(t3Var, context4, q3Var);
                            return;
                        }
                        t3 t3Var2 = t3.ACTIVITY;
                        if (t3Var2.f243a.equals(optString5)) {
                            r3Var = this.f19004k;
                            context3 = this.f19001h;
                        } else {
                            t3Var2 = t3.PROVIDER;
                            if (t3Var2.f243a.equals(optString5)) {
                                r3Var = this.f19004k;
                                context3 = this.f19001h;
                            } else {
                                context2 = this.f19001h;
                                str3 = this.f19000g;
                                str4 = "A receive a incorrect message with unknown type " + optString5;
                            }
                        }
                        r3Var.i(t3Var2, context3, q3Var);
                        return;
                    }
                    context2 = this.f19001h;
                    str3 = this.f19000g;
                    str4 = "A receive a incorrect message with empty type";
                    n3.a(context2, str3, 1008, str4);
                    return;
                }
                n3.a(this.f19001h, this.f19000g, 1008, "A receive a incorrect message with incorrect package info" + optString3);
                return;
            } catch (JSONException e2) {
                g.j.a.a.a.c.s(e2);
                context = this.f19001h;
                str = this.f19000g;
                str2 = "A meet a exception when receive the message";
            }
        }
        n3.a(context, str, 1008, str2);
    }
}
