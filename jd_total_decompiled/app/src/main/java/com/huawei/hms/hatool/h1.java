package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class h1 implements o1 {
    private List<b1> a;
    private k0 b;

    /* renamed from: c  reason: collision with root package name */
    private t0 f1377c;
    private o1 d;

    /* renamed from: e  reason: collision with root package name */
    private String f1378e = "";

    /* renamed from: f  reason: collision with root package name */
    private String f1379f;

    public h1(String str) {
        this.f1379f = str;
    }

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        String str;
        List<b1> list = this.a;
        if (list == null || list.size() == 0) {
            str = "Not have actionEvent to send";
        } else if (this.b == null || this.f1377c == null || this.d == null) {
            str = "model in wrong format";
        } else {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("header", this.b.a());
            JSONObject jSONObject2 = new JSONObject();
            JSONObject a = this.d.a();
            a.put("properties", this.f1377c.a());
            try {
                a.put("events_global_properties", new JSONObject(this.f1378e));
            } catch (JSONException unused) {
                a.put("events_global_properties", this.f1378e);
            }
            jSONObject2.put("events_common", a);
            JSONArray jSONArray = new JSONArray();
            Iterator<b1> it = this.a.iterator();
            while (it.hasNext()) {
                JSONObject a2 = it.next().a();
                if (a2 != null) {
                    jSONArray.put(a2);
                } else {
                    v.e("hmsSdk", "custom event is empty,delete this event");
                }
            }
            jSONObject2.put("events", jSONArray);
            try {
                String a3 = n.a(k1.a(jSONObject2.toString().getBytes("UTF-8")), this.f1379f);
                if (TextUtils.isEmpty(a3)) {
                    v.e("hmsSdk", "eventInfo encrypt failed,report over!");
                    return null;
                }
                jSONObject.put("event", a3);
                return jSONObject;
            } catch (UnsupportedEncodingException unused2) {
                str = "getBitZip(): Unsupported coding : utf-8";
            }
        }
        v.e("hmsSdk", str);
        return null;
    }

    public void a(k0 k0Var) {
        this.b = k0Var;
    }

    public void a(l lVar) {
        this.d = lVar;
    }

    public void a(t0 t0Var) {
        this.f1377c = t0Var;
    }

    public void a(String str) {
        if (str != null) {
            this.f1378e = str;
        }
    }

    public void a(List<b1> list) {
        this.a = list;
    }
}
