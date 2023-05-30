package com.jingdong.jdpush_new.e;

import android.content.Context;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.jdpush_new.f.d;
import com.jingdong.jdpush_new.f.f;
import com.jingdong.jdpush_new.j.i;
import com.jingdong.sdk.platform.business.personal.R2;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    private static c a;

    public static c f() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public void a(Context context, int i2, String str, String str2) {
        try {
            if (new JSONObject(str).optInt("STATUS") == 0) {
                com.jingdong.jdpush_new.g.c.b i3 = d.k(context).i(str2 + i2);
                if (i3 != null) {
                    i3.q("0");
                    d.k(context).n(i3);
                }
            }
        } catch (JSONException unused) {
        }
    }

    public void b(Context context, int i2, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("STATUS") == 0) {
                com.jingdong.jdpush_new.g.c.d k2 = f.m(context).k(jSONObject.optString(MIPushMsg.MSG_SEQ));
                if (k2 != null) {
                    k2.m("0");
                    f.m(context).q(k2);
                }
            }
        } catch (JSONException unused) {
        }
    }

    public void c(Context context, int i2, String str, String str2) {
        int i3 = i2 * 1000;
        com.jingdong.jdpush_new.mta.b.b().l(i3 + R2.attr.actionbar_icon_dark_back);
        try {
            if (new JSONObject(str).optInt("STATUS") == 0) {
                com.jingdong.jdpush_new.g.c.a h2 = com.jingdong.jdpush_new.f.a.k(context).h(str2);
                if (h2 != null && i2 == 0) {
                    h2.q("0");
                    com.jingdong.jdpush_new.f.a.k(context).m(h2);
                }
                com.jingdong.jdpush_new.g.c.b i4 = d.k(context).i(str2 + i2 + R2.attr.title_back_button);
                if (i4 != null) {
                    i4.q("0");
                    d.k(context).n(i4);
                }
                if (i2 == 0) {
                    com.jingdong.jdpush_new.mta.b.b().c().longConnDT = com.jingdong.jdpush_new.j.c.i(context);
                } else {
                    com.jingdong.jdpush_new.mta.b.b().c().romDT = i.c(context, i2);
                }
                com.jingdong.jdpush_new.mta.b.b().c().dtSrc = i2;
                com.jingdong.jdpush_new.mta.b.b().l(i3 + 400);
                com.jingdong.jdpush_new.mta.b.b().g(context);
                return;
            }
            com.jingdong.jdpush_new.mta.b.b().l(i3 + R2.attr.additionBottom);
        } catch (JSONException unused) {
        }
    }

    public void d(Context context, int i2, String str, String str2) {
        try {
            if (new JSONObject(str).optInt("STATUS") == 0) {
                com.jingdong.jdpush_new.g.c.b i3 = d.k(context).i(str2 + i2);
                if (i3 != null) {
                    i3.q("0");
                    d.k(context).n(i3);
                }
            }
        } catch (JSONException unused) {
        }
    }

    public void e(String str) {
        try {
            new JSONObject(str).optInt("STATUS");
        } catch (JSONException unused) {
        }
    }
}
