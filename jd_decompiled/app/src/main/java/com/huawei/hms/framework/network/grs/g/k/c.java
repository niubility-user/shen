package com.huawei.hms.framework.network.grs.g.k;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.tencent.mapsdk.internal.i2;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    private final GrsBaseInfo a;
    private final Context b;

    /* renamed from: c  reason: collision with root package name */
    private final Set<String> f1343c = new HashSet();

    public c(GrsBaseInfo grsBaseInfo, Context context) {
        this.a = grsBaseInfo;
        this.b = context;
    }

    private String e() {
        Set<String> b = com.huawei.hms.framework.network.grs.f.b.a(this.b.getPackageName(), this.a).b();
        if (b.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        try {
            jSONObject.put(i2.d, jSONArray);
            Logger.i("GrsRequestInfo", "post service list is:%s", jSONObject.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    private String f() {
        Logger.v("GrsRequestInfo", "getGeoipService enter");
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = this.f1343c.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        try {
            jSONObject.put(i2.d, jSONArray);
            Logger.v("GrsRequestInfo", "post query service list is:%s", jSONObject.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public Context a() {
        return this.b;
    }

    public void a(String str) {
        this.f1343c.add(str);
    }

    public GrsBaseInfo b() {
        return this.a;
    }

    public String c() {
        return this.f1343c.size() == 0 ? e() : f();
    }

    public Set<String> d() {
        return this.f1343c;
    }
}
