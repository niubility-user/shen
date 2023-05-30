package com.jingdong.jdpush_new.i;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.jdpush_new.connect.f;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.i;
import com.jingdong.jdpush_new.j.k;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b implements com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> {

    /* renamed from: c  reason: collision with root package name */
    public static final String f12848c = "b";
    private Context a;
    private f b;

    public b(Context context, f fVar) {
        this.a = context;
        this.b = fVar;
    }

    @Override // com.jingdong.jdpush_new.j.d
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void accept(com.jingdong.jdpush_new.g.b bVar) {
        String msgBody = bVar.getMsgBody();
        com.jingdong.jdpush_new.mta.b.b().l(103110);
        if (TextUtils.isEmpty(msgBody)) {
            com.jingdong.jdpush_new.mta.b.b().l(103112);
            return;
        }
        try {
            if (new JSONObject(msgBody).optInt("STATUS") == 0) {
                com.jingdong.jdpush_new.mta.b.b().l(103120);
                com.jingdong.jdpush_new.mta.a.b().g();
                g.d(f12848c, "doAfterLoginSuccess");
                this.b.c().i();
                c(this.a);
                b(this.a);
            } else {
                com.jingdong.jdpush_new.mta.b.b().l(103114);
            }
        } catch (JSONException e2) {
            g.g(e2);
        }
    }

    public void b(Context context) {
        try {
            List<com.jingdong.jdpush_new.g.c.d> i2 = com.jingdong.jdpush_new.f.f.m(context).i();
            if (i2 != null && i2.size() > 0) {
                for (com.jingdong.jdpush_new.g.c.d dVar : i2) {
                    if (TextUtils.equals(dVar.f(), "1")) {
                        k.a(context, Short.valueOf(dVar.b()).shortValue(), com.jingdong.jdpush_new.g.c.d.n(dVar), new JSONObject(dVar.d()).optInt("DEVMODLE"));
                    }
                }
            }
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    public void c(Context context) {
        com.jingdong.jdpush_new.g.c.a h2 = com.jingdong.jdpush_new.f.a.k(context).h(com.jingdong.jdpush_new.j.c.d(context));
        if (h2 == null) {
            return;
        }
        com.jingdong.jdpush_new.mta.b.b().c().longConnDT = com.jingdong.jdpush_new.j.c.i(context);
        com.jingdong.jdpush_new.mta.b.b().l(103200);
        if (!"1".equals(h2.g()) && !d(context)) {
            com.jingdong.jdpush_new.mta.b.b().l(103220);
            com.jingdong.jdpush_new.mta.b.b().g(context);
            return;
        }
        i.d().f(context, "jsp_openpush", com.jingdong.jdpush_new.j.f.b(context));
        com.jingdong.jdpush_new.j.c.u(context, 0, h2.c(), (short) 2104, Integer.valueOf(com.jingdong.jdpush_new.j.f.b(context)).intValue());
    }

    public boolean d(Context context) {
        return !i.d().a(context, "jsp_openpush", "").equals(com.jingdong.jdpush_new.j.f.b(context));
    }
}
