package com.jingdong.app.mall.home.p.b.e;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class b {
    private final ConcurrentHashMap<String, com.jingdong.app.mall.home.deploy.view.base.b> a = new ConcurrentHashMap<>();
    private h b;

    /* renamed from: c */
    private d f10540c;
    private f d;

    /* renamed from: e */
    private List<a> f10541e;

    /* renamed from: f */
    private boolean f10542f;

    public void a(String str, com.jingdong.app.mall.home.deploy.view.base.b bVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.a.put(str, bVar);
    }

    public ArrayList<f> b() {
        return this.f10540c.a();
    }

    public f c(int i2) {
        d dVar;
        f fVar = this.d;
        if (fVar != null || (dVar = this.f10540c) == null) {
            return fVar;
        }
        ArrayList<f> arrayList = dVar.f10682c;
        if (arrayList == null || arrayList.size() <= i2) {
            return null;
        }
        return arrayList.get(i2);
    }

    public h d() {
        return this.b;
    }

    public List<a> e() {
        return this.f10541e;
    }

    public boolean f() {
        return this.f10542f;
    }

    public void g(f fVar) {
        this.d = fVar;
    }

    public void h(d dVar) {
        this.f10540c = dVar;
    }

    public void i(h hVar) {
        this.b = hVar;
    }

    public void j(JDJSONObject jDJSONObject, List<a> list) {
        this.f10541e = list;
        this.f10542f = TextUtils.equals(jDJSONObject.optString("tempType"), "1");
    }
}
