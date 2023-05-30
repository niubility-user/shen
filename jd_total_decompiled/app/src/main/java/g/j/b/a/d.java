package g.j.b.a;

import com.tencent.mapsdk.internal.l4;
import com.vivo.push.PushClientConstants;
import com.xiaomi.push.a8;
import com.xiaomi.push.t0;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class d {
    public int a;
    public String b;

    /* renamed from: c */
    public int f19677c;
    private String d = t0.a();

    /* renamed from: e */
    private String f19678e = a8.d();

    /* renamed from: f */
    private String f19679f;

    /* renamed from: g */
    private String f19680g;

    public void a(String str) {
        this.f19679f = str;
    }

    public void b(String str) {
        this.f19680g = str;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("production", this.a);
            jSONObject.put("reportType", this.f19677c);
            jSONObject.put("clientInterfaceId", this.b);
            jSONObject.put("os", this.d);
            jSONObject.put("miuiVersion", this.f19678e);
            jSONObject.put(PushClientConstants.TAG_PKG_NAME, this.f19679f);
            jSONObject.put(l4.f16791e, this.f19680g);
            return jSONObject;
        } catch (JSONException e2) {
            g.j.a.a.a.c.s(e2);
            return null;
        }
    }

    public String d() {
        JSONObject c2 = c();
        return c2 == null ? "" : c2.toString();
    }
}
