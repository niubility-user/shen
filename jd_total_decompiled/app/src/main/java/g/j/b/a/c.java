package g.j.b.a;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class c extends d {

    /* renamed from: h  reason: collision with root package name */
    public int f19674h;

    /* renamed from: i  reason: collision with root package name */
    public long f19675i = -1;

    /* renamed from: j  reason: collision with root package name */
    public long f19676j = -1;

    public static c e() {
        return new c();
    }

    @Override // g.j.b.a.d
    public JSONObject c() {
        try {
            JSONObject c2 = super.c();
            if (c2 == null) {
                return null;
            }
            c2.put("code", this.f19674h);
            c2.put("perfCounts", this.f19675i);
            c2.put("perfLatencies", this.f19676j);
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
