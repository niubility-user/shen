package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class l0 {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f1394c;
    private List<b1> d;

    /* renamed from: e  reason: collision with root package name */
    private String f1395e;

    public l0(String str, String str2, String str3, List<b1> list, String str4) {
        this.a = str;
        this.b = str2;
        this.f1394c = str3;
        this.d = list;
        this.f1395e = str4;
    }

    private String a(String str, String str2) {
        String str3;
        String f2 = a1.f(str, str2);
        if (TextUtils.isEmpty(f2)) {
            v.a("hmsSdk", "No report address,TAG : %s,TYPE: %s ", str, str2);
            return "";
        }
        if ("oper".equals(str2)) {
            str3 = "{url}/common/hmshioperqrt";
        } else if ("maint".equals(str2)) {
            str3 = "{url}/common/hmshimaintqrt";
        } else if (!"diffprivacy".equals(str2)) {
            return "";
        } else {
            str3 = "{url}/common/common2";
        }
        return str3.replace("{url}", f2);
    }

    private byte[] a(h1 h1Var) {
        String str;
        try {
            JSONObject a = h1Var.a();
            if (a == null) {
                v.e("hmsSdk", "uploadEvents is null");
                return new byte[0];
            }
            return k1.a(a.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            str = "sendData(): getBytes - Unsupported coding format!!";
            v.e("hmsSdk", str);
            return new byte[0];
        } catch (JSONException unused2) {
            str = "uploadEvents to json error";
            v.e("hmsSdk", str);
            return new byte[0];
        }
    }

    private void b() {
        if (c0.a(q0.i(), "backup_event", 5242880)) {
            v.d("hmsSdk", "backup file reach max limited size, discard new event ");
            return;
        }
        JSONArray c2 = c();
        String a = n1.a(this.a, this.b, this.f1395e);
        v.c("hmsSdk", "Update data cached into backup,spKey: " + a);
        d.b(q0.i(), "backup_event", a, c2.toString());
    }

    private JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        Iterator<b1> it = this.d.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(it.next().d());
            } catch (JSONException unused) {
                v.c("hmsSdk", "handleEvents: json error,Abandon this data");
            }
        }
        return jSONArray;
    }

    private h1 d() {
        return k.a(this.d, this.a, this.b, this.f1395e, this.f1394c);
    }

    public void a() {
        g d1Var;
        b0 c2;
        String str;
        String a = a(this.a, this.b);
        if (!TextUtils.isEmpty(a) || "preins".equals(this.b)) {
            if (!"_hms_config_tag".equals(this.a) && !"_openness_config_tag".equals(this.a)) {
                b();
            }
            h1 d = d();
            if (d != null) {
                byte[] a2 = a(d);
                if (a2.length == 0) {
                    str = "request body is empty";
                } else {
                    d1Var = new f(a2, a, this.a, this.b, this.f1395e, this.d);
                    c2 = b0.b();
                }
            } else {
                d1Var = new d1(this.d, this.a, this.f1395e, this.b);
                c2 = b0.c();
            }
            c2.a(d1Var);
            return;
        }
        str = "collectUrl is empty";
        v.e("hmsSdk", str);
    }
}
