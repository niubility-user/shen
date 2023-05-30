package com.jd.stat.security;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;

/* loaded from: classes18.dex */
public class b {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f7041c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f7042e;

    /* renamed from: f  reason: collision with root package name */
    private int f7043f;

    /* renamed from: g  reason: collision with root package name */
    private Set<String> f7044g = new HashSet();

    public b(JSONArray jSONArray) {
        this.a = "";
        this.b = "{}";
        this.d = false;
        this.f7042e = false;
        this.f7043f = 0;
        try {
            this.a = jSONArray.optString(0, "");
            String optString = jSONArray.optString(1, "none");
            char c2 = '\uffff';
            switch (optString.hashCode()) {
                case -1410164923:
                    if (optString.equals("alter&fix")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1058189307:
                    if (optString.equals("fix&alter")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 101397:
                    if (optString.equals("fix")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 3387192:
                    if (optString.equals("none")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 92913686:
                    if (optString.equals("alter")) {
                        c2 = 4;
                        break;
                    }
                    break;
            }
            if (c2 == 0) {
                this.d = false;
                this.f7042e = false;
            } else if (c2 == 1 || c2 == 2) {
                this.d = true;
                this.f7042e = true;
            } else if (c2 == 3) {
                this.d = true;
                this.f7042e = false;
            } else if (c2 == 4) {
                this.d = false;
                this.f7042e = true;
            }
            this.b = jSONArray.optString(2, "{}");
            this.f7043f = Integer.parseInt(jSONArray.optString(3, "0"));
            this.f7041c = Integer.parseInt(jSONArray.optString(4, "0"));
            JSONArray optJSONArray = jSONArray.optJSONArray(5);
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    this.f7044g.add(optJSONArray.optString(i2));
                }
            }
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("CollectConfigRule", th);
        }
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.d;
    }

    public boolean c() {
        return this.f7042e;
    }

    public int d() {
        return this.f7043f;
    }

    public String e() {
        return this.b;
    }

    public int f() {
        return this.f7041c;
    }

    public boolean a(String str) {
        Set<String> set = this.f7044g;
        if (set == null) {
            return false;
        }
        return set.contains(str);
    }
}
