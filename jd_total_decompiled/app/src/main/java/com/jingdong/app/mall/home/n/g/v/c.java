package com.jingdong.app.mall.home.n.g.v;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.widget.custom.livewidget.bean.VideoPerfEntity;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class c {
    private boolean a;
    private AtomicInteger b = new AtomicInteger(0);

    /* renamed from: c */
    private List<c> f10372c = new CopyOnWriteArrayList();
    private int d;

    /* renamed from: e */
    private d f10373e;

    /* renamed from: f */
    private d f10374f;

    /* renamed from: g */
    private String f10375g;

    /* renamed from: h */
    private String f10376h;

    /* renamed from: i */
    private String f10377i;

    /* renamed from: j */
    private String f10378j;

    @Nullable
    private d j() {
        return this.f10374f;
    }

    private d k() {
        d dVar = this.f10373e;
        return dVar == null ? d.b() : dVar;
    }

    private boolean o() {
        return this.d <= 0 || this.b.get() < this.d;
    }

    private void r(String str, int i2, String str2) {
        this.f10376h = str;
        this.d = i2;
        this.f10377i = str2;
    }

    public boolean A() {
        return this.d == 1;
    }

    public void B(int i2) {
        C(this.f10375g, i2);
    }

    public void C(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        b.c(str, e(j(), new String[0]));
    }

    public void D(String str, String str2) {
        d dVar = this.f10374f;
        if (dVar != null) {
            dVar.a(str, str2);
        }
    }

    public void E() {
        F(false);
    }

    public void F(boolean z) {
        if ((!z || this.f10372c.size() > 0) && o() && !TextUtils.isEmpty(this.f10376h)) {
            b.f(this);
            c();
            this.b.getAndIncrement();
        }
    }

    public void G(int i2) {
        d j2 = j();
        if (j2 == null) {
            return;
        }
        j2.a("clickPos", Integer.valueOf(i2));
    }

    public void a(c cVar) {
        if (cVar == null || cVar.j() == null || this.f10372c.contains(cVar)) {
            return;
        }
        this.f10372c.add(cVar);
    }

    public void b(List<c> list) {
        if (list == null) {
            return;
        }
        Iterator<c> it = list.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    public void c() {
        List<c> list = this.f10372c;
        if (list != null) {
            try {
                list.clear();
                a(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public d d(d dVar) {
        d b = d.b();
        b.put("pub", k());
        if (dVar != null) {
            b.put(VideoPerfEntity.FIELD_PRV, dVar);
        }
        return b;
    }

    public String e(d dVar, String... strArr) {
        d b = d.b();
        b.put("pub", this.f10373e);
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (dVar == null) {
            return b.toString();
        }
        if (strArr != null && strArr.length > 0) {
            for (int i2 = 0; i2 < strArr.length - 1; i2 += 2) {
                dVar.put(strArr[i2], strArr[i2 + 1]);
            }
        }
        b.put(VideoPerfEntity.FIELD_PRV, dVar);
        return b.toString();
    }

    public String f() {
        return this.f10377i;
    }

    public String g() {
        return this.f10376h;
    }

    public d h() {
        return d(null);
    }

    public String i() {
        d b = d.b();
        d k2 = k();
        b.put("pub", k2);
        int size = this.f10372c.size();
        if (size > 0) {
            JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
            int min = Math.min(size, 200);
            for (int i2 = 0; i2 < min; i2++) {
                d j2 = this.f10372c.get(i2).j();
                if (j2 != null) {
                    d.put(j2);
                }
            }
            if (this.a) {
                k2.a("pdCnt", String.valueOf(d.length()));
            }
            b.put(VideoPerfEntity.FIELD_PRV, d);
        } else {
            d dVar = this.f10374f;
            if (dVar != null) {
                b.put(VideoPerfEntity.FIELD_PRV, dVar);
            }
        }
        return b.toString();
    }

    public String l() {
        return this.f10378j;
    }

    public String m() {
        JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
        int size = this.f10372c.size();
        if (size > 0) {
            int min = Math.min(size, 200);
            for (int i2 = 0; i2 < min; i2++) {
                d j2 = this.f10372c.get(i2).j();
                if (j2 != null) {
                    d.put(j2);
                }
            }
        }
        return d.toString();
    }

    public List<c> n() {
        return this.f10372c;
    }

    public void p(String str) {
        this.f10375g = str;
    }

    public void q(String str, com.jingdong.app.mall.home.n.g.u.c cVar, String str2, int i2) {
        y(cVar, str2, i2);
        p(str);
    }

    public void s(String str, String str2) {
        r(str, -1, str2);
    }

    public void t(String str, String str2) {
        r(str, 1, str2);
    }

    public void u(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f10374f = d.c(str);
    }

    public void v(JDJSONObject jDJSONObject) {
        w(jDJSONObject, false);
    }

    public void w(JDJSONObject jDJSONObject, boolean z) {
        if (jDJSONObject == null) {
            return;
        }
        this.a = z;
        this.f10373e = d.c(com.jingdong.app.mall.home.r.e.b.getJsonString(jDJSONObject, "srvJson", "{}"));
    }

    public void x(String str, String str2) {
        this.f10378j = str;
    }

    public void y(com.jingdong.app.mall.home.n.g.u.c cVar, String str, int i2) {
        if (cVar == null) {
            return;
        }
        z(cVar.e().k(), str, i2);
    }

    public void z(d dVar, String str, int i2) {
        this.f10373e = dVar;
        u(str);
    }
}
