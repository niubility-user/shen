package g.f.b.a;

import org.json.JSONObject;

/* loaded from: classes13.dex */
public class b {
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f19540c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public int f19541e;

    /* renamed from: f  reason: collision with root package name */
    public String f19542f;

    public static b a(JSONObject jSONObject) {
        b bVar = new b();
        if (jSONObject != null) {
            try {
                bVar.a = jSONObject.getInt("error");
                bVar.b = jSONObject.getString("errorDesc");
                bVar.f19540c = jSONObject.getString("errorUrl");
                if (bVar.a == 0) {
                    jSONObject.getString("uid");
                    bVar.d = jSONObject.getString("authToken");
                    bVar.f19541e = jSONObject.getInt("authType");
                    bVar.f19542f = jSONObject.getString("traceId");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bVar;
    }
}
