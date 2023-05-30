package g.f.b.a;

import org.json.JSONObject;

/* loaded from: classes13.dex */
public class d {
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f19546c;

    public static d a(JSONObject jSONObject) {
        d dVar = new d();
        if (jSONObject != null) {
            try {
                dVar.a = jSONObject.getInt("error");
                jSONObject.getString("errorDesc");
                dVar.b = jSONObject.getString("errorUrl");
                if (dVar.a == 0) {
                    jSONObject.getString("uid");
                    jSONObject.getString("syBrand");
                    jSONObject.getString("manu");
                    jSONObject.getString("syid");
                    jSONObject.getString("accessToken");
                    dVar.f19546c = jSONObject.getString("successUrl");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return dVar;
    }
}
