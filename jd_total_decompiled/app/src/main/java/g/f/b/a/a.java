package g.f.b.a;

import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f19535c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public String f19536e;

    /* renamed from: f  reason: collision with root package name */
    public int f19537f;

    /* renamed from: g  reason: collision with root package name */
    public String f19538g;

    /* renamed from: h  reason: collision with root package name */
    public String f19539h;

    public static JSONObject a(a aVar, boolean z, boolean z2) {
        JSONObject jSONObject = null;
        if (aVar == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("uid", aVar.b);
                if (!z) {
                    jSONObject2.put("authType", aVar.a);
                    if (z2) {
                        jSONObject2.put("puf", aVar.f19535c);
                    }
                    jSONObject2.put("authRandom", aVar.f19536e);
                    jSONObject2.put("traceId", aVar.f19539h);
                } else {
                    jSONObject2.put("extendedInfo", aVar.f19538g);
                    jSONObject2.put("encryptVersion", 2);
                }
                jSONObject2.put("authKeyIndex", aVar.d);
                jSONObject2.put("isPre", aVar.f19537f);
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
