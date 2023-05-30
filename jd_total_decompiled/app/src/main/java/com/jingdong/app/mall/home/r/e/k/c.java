package com.jingdong.app.mall.home.r.e.k;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class c extends d {
    private static Map<String, Boolean> u = new ConcurrentHashMap();

    /* renamed from: l */
    public String f10725l;

    /* renamed from: m */
    public String f10726m;

    /* renamed from: n */
    public String f10727n;
    public com.jingdong.app.mall.home.r.c.b o;
    public boolean p;
    private String q;
    private JumpEntity r;
    public boolean s;
    private int t;

    c(JDJSONObject jDJSONObject, boolean z, boolean z2, IconFloorEntity iconFloorEntity) {
        super(jDJSONObject);
        this.s = z2;
        this.t = iconFloorEntity.getScrollType();
        this.f10725l = getJsonString("id");
        getJsonString("appCode");
        this.f10726m = getJsonString("name");
        this.f10727n = getJsonString("icon");
        com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "param", "");
        this.q = com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "isSub", "0");
        this.p = !"0".equals(getJsonString("redControl", "1"));
        this.r = (JumpEntity) getObject("jump", JumpEntity.class);
        if (!z2) {
            o(z, iconFloorEntity.isSupportLabel());
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(this.r.getSrvJson());
        this.o = c2;
        c2.a("labelword", this.a);
    }

    private void o(boolean z, boolean z2) {
        Boolean bool = u.get(this.b);
        if (bool == null || !bool.booleanValue()) {
            g(z, z2);
        }
    }

    public static List<c> s(JDJSONArray jDJSONArray, boolean z, boolean z2, IconFloorEntity iconFloorEntity) {
        ArrayList arrayList = new ArrayList();
        if (jDJSONArray != null && jDJSONArray.size() != 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    arrayList.add(new c(jSONObject, z, z2, iconFloorEntity));
                }
            }
        }
        return arrayList;
    }

    @Override // com.jingdong.app.mall.home.r.e.k.d
    public String b() {
        return this.f10725l.concat(d()).concat(e()).concat(this.t + "");
    }

    public JumpEntity getJump() {
        return this.r;
    }

    public void l() {
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        this.o.a("labelword", "");
        u.put(this.b, Boolean.TRUE);
    }

    public void m(boolean z) {
        if (z && i() && f.Y(this.f10731c) > 2.0f) {
            this.f10731c = "";
            this.o.a("labelword", "");
        }
    }

    public String n() {
        return this.q;
    }

    public boolean p() {
        return this.s;
    }

    public void q() {
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        d.a(this.b);
        Boolean bool = u.get(this.b);
        if (bool == null || !bool.booleanValue()) {
            new com.jingdong.app.mall.home.q.a("\u767e\u5b9d\u7bb1Icon\u70b9\u51fb", this.f10733f).b();
        }
    }

    public void r() {
        if (h()) {
            String str = "shake_lottie_info" + this.b;
            String str2 = this.s + " \u662f\u5426\u5fae\u7f13\u5b58\u6570\u636e";
            d.k(d.c(this.b), this.b);
        }
        if (TextUtils.isEmpty(this.f10732e) || !h()) {
            return;
        }
        new com.jingdong.app.mall.home.q.a("\u767e\u5b9d\u7bb1Icon\u66dd\u5149", true, this.f10732e).b();
    }
}
