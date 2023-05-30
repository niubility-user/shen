package g.j.b.a;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class b extends d {

    /* renamed from: h  reason: collision with root package name */
    public String f19670h;

    /* renamed from: i  reason: collision with root package name */
    public int f19671i;

    /* renamed from: j  reason: collision with root package name */
    public long f19672j;

    /* renamed from: k  reason: collision with root package name */
    public String f19673k;

    @Override // g.j.b.a.d
    public JSONObject c() {
        try {
            JSONObject c2 = super.c();
            if (c2 == null) {
                return null;
            }
            c2.put("eventId", this.f19670h);
            c2.put("eventType", this.f19671i);
            c2.put("eventTime", this.f19672j);
            String str = this.f19673k;
            if (str == null) {
                str = "";
            }
            c2.put("eventContent", str);
            return c2;
        } catch (JSONException e2) {
            g.j.a.a.a.c.s(e2);
            return null;
        }
    }

    @Override // g.j.b.a.d
    public String d() {
        return super.d();
    }
}
