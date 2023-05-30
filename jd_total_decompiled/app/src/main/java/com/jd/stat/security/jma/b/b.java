package com.jd.stat.security.jma.b;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b extends a implements f {
    private ArrayList<JSONObject> a = new ArrayList<>();

    public void a(List<JSONObject> list) {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.b()) { // from class: com.jd.stat.security.jma.b.b.2
            @Override // com.jd.stat.network.d
            protected String e() {
                try {
                    return "content=" + URLEncoder.encode(b.this.a(arrayList).getString("content"), "UTF-8");
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        };
        dVar.a((Object) ("BusinessEventTrackSender." + System.currentTimeMillis()));
        dVar.h();
    }

    @Override // com.jd.stat.security.jma.b.f
    public void c(JSONObject jSONObject) {
        this.a.add(jSONObject);
        if (this.a.size() >= 1) {
            final ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.a);
            this.a.clear();
            com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.b()) { // from class: com.jd.stat.security.jma.b.b.1
                @Override // com.jd.stat.network.d
                protected String e() {
                    try {
                        return "content=" + URLEncoder.encode(b.this.a(arrayList).getString("content"), "UTF-8");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                }
            };
            dVar.a((Object) ("BusinessEventTrackSender." + System.currentTimeMillis()));
            dVar.h();
        }
    }
}
