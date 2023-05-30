package g.f.b.a;

import org.json.JSONObject;

/* loaded from: classes13.dex */
public class c {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f19543c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f19544e;

    /* renamed from: f  reason: collision with root package name */
    public String f19545f;

    public static JSONObject a(c cVar, boolean z) {
        JSONObject jSONObject = null;
        if (cVar == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("uid", cVar.a);
                if (z) {
                    jSONObject2.put("puf", cVar.b);
                }
                jSONObject2.put("authToken2", cVar.f19543c);
                jSONObject2.put("authKeyIndex", cVar.d);
                jSONObject2.put("authType", cVar.f19544e);
                jSONObject2.put("traceId", cVar.f19545f);
                return jSONObject2;
            } catch (Exception e2) {
                e = e2;
                jSONObject = jSONObject2;
                e.printStackTrace();
                return jSONObject;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
