package com.tencent.mapsdk.internal;

import android.content.Context;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class xg {

    /* renamed from: e */
    private static final String f17458e = "[{\"id\":0,\"index\":0,\"order\":1},{\"id\":-1,\"index\":1,\"order\":-1},{\"id\":-1,\"index\":2,\"order\":-1},{\"id\":-1,\"index\":3,\"order\":-1},{\"id\":-1,\"index\":4,\"order\":-1},{\"id\":-1,\"index\":5,\"order\":-1},{\"id\":-1,\"index\":6,\"order\":-1},{\"id\":-1,\"index\":7,\"order\":-1},{\"id\":16,\"index\":8,\"order\":-1},{\"id\":9,\"index\":9,\"order\":-1},{\"id\":10,\"index\":10,\"order\":-1},{\"id\":5,\"index\":11,\"order\":-1},{\"id\":4,\"index\":12,\"order\":-1},{\"id\":6,\"index\":13,\"order\":-1},{\"id\":7,\"index\":14,\"order\":-1},{\"id\":8,\"index\":15,\"order\":-1}]";

    /* renamed from: f */
    public static final int f17459f = 1000;

    /* renamed from: g */
    public static final int f17460g = 11;
    private volatile List<wg> a;

    /* renamed from: c */
    private hc f17461c;
    private final Object b = new Object();
    private int d = -1;

    public xg(Context context, String str) {
        this.f17461c = str == null ? kc.a(context) : jc.a(context, str);
        b();
    }

    private List<wg> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                arrayList.add(new wg(jSONObject.getInt("index"), jSONObject.getInt("id"), jSONObject.getInt("order")));
            } catch (Exception unused) {
                return null;
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private void b() {
        this.a = new CopyOnWriteArrayList();
        String d = this.f17461c.d(l4.A);
        if (d == null) {
            d = f17458e;
        }
        try {
            JSONArray jSONArray = new JSONArray(d);
            synchronized (this.b) {
                this.a.addAll(a(jSONArray));
            }
        } catch (Exception unused) {
        }
    }

    public wg a(int i2) {
        int i3;
        synchronized (this.b) {
            if (this.a != null && this.a.size() != 0 && i2 >= 0 && i2 - 1000 < this.a.size()) {
                this.d = i2;
                if (i2 >= 1000) {
                    return this.a.get(i3);
                }
                if (i2 > 8 && i2 < 989) {
                    int i4 = i2 + 11;
                    if (i4 >= this.a.size()) {
                        return null;
                    }
                    return this.a.get(i4);
                }
                for (wg wgVar : this.a) {
                    if (wgVar.f17437c == i2) {
                        return wgVar;
                    }
                }
                return this.a.get(0);
            }
            return null;
        }
    }

    public String a() {
        int i2;
        if (this.a == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(128);
        for (wg wgVar : this.a) {
            if (wgVar.b != -1) {
                if (sb.length() != 0) {
                    sb.append(DYConstants.DY_REGEX_COMMA);
                }
                i2 = wgVar.b;
            } else if (sb.length() == 0) {
                i2 = 0;
            } else {
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.append(i2);
        }
        return sb.toString();
    }

    public void a(qc qcVar) {
        wg a;
        if (qcVar == null || (a = a(this.d)) == null) {
            return;
        }
        qcVar.h().d(a.a);
    }

    public int b(int i2) {
        synchronized (this.b) {
            if (this.a != null && this.a.size() != 0 && i2 >= 0) {
                if ((i2 >= 8 && i2 <= 19) || this.d >= 1000) {
                    return i2 + 1000;
                } else if (i2 > 19 && i2 < 1000) {
                    return i2 - 11;
                } else {
                    for (wg wgVar : this.a) {
                        if (i2 == wgVar.a) {
                            if (i2 == 0 && wgVar.b == 0 && this.d < 1) {
                                return 1000;
                            }
                            return wgVar.f17437c;
                        }
                    }
                    return i2;
                }
            }
            return i2;
        }
    }

    public void b(JSONArray jSONArray) {
        List<wg> a;
        String d = this.f17461c.d(l4.A);
        if (jSONArray == null || (a = a(jSONArray)) == null) {
            return;
        }
        synchronized (this.b) {
            this.a.clear();
            this.a.addAll(a);
        }
        if (jSONArray.toString().equals(d)) {
            return;
        }
        this.f17461c.b();
        this.f17461c.b(l4.A, jSONArray.toString());
    }
}
