package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.tencent.mapsdk.internal.mf;
import com.tencent.mapsdk.internal.nf;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class lf implements mf.d, nf.a {

    /* renamed from: j  reason: collision with root package name */
    public static final String f16840j = "AUTH_MARKER";
    private final l1 a;
    private final vh b;

    /* renamed from: c  reason: collision with root package name */
    private final xg f16841c;
    private final sf d;

    /* renamed from: e  reason: collision with root package name */
    private final qc f16842e;

    /* renamed from: f  reason: collision with root package name */
    private List<AsyncTask> f16843f;

    /* renamed from: g  reason: collision with root package name */
    private final qf f16844g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f16845h;

    /* renamed from: i  reason: collision with root package name */
    private final xi f16846i;

    /* loaded from: classes9.dex */
    public interface a {
        void a(Bitmap bitmap, int i2, int i3);
    }

    public lf(h1 h1Var) {
        String str;
        Context j2 = h1Var.j();
        this.f16846i = h1Var.l();
        this.b = h1Var.l().n0();
        l1 n2 = h1Var.n();
        this.a = n2;
        this.f16841c = h1Var.m();
        this.d = h1Var.k();
        this.f16842e = h1Var.l().A();
        this.f16843f = new ArrayList();
        String str2 = "";
        if (h1Var.l() == null || h1Var.l().l() == null) {
            str = "";
        } else {
            str2 = h1Var.l().l().getSubKey();
            str = h1Var.l().l().getSubId();
        }
        this.f16844g = new qf(j2, h1Var, str2);
        this.f16843f.add(new mf(n2.p, str2, str, this));
        this.f16843f.add(new nf(h1Var, this));
    }

    public void a() {
        if (this.f16843f != null) {
            for (int i2 = 0; i2 < this.f16843f.size(); i2++) {
                this.f16843f.get(i2).cancel(true);
            }
            this.f16843f.clear();
        }
        this.f16843f = null;
        this.f16845h = true;
    }

    public void a(a aVar, TencentMapOptions tencentMapOptions) {
        this.a.a(aVar, tencentMapOptions);
    }

    @Override // com.tencent.mapsdk.internal.mf.d
    public void a(mf.c cVar, JSONObject jSONObject) {
        r5 r5Var;
        if (this.f16845h) {
            return;
        }
        JSONArray jSONArray = null;
        if (cVar != null) {
            jSONArray = cVar.b;
            r5Var = cVar.a;
            rf rfVar = cVar.d;
            if (rfVar != null) {
                this.d.a(rfVar);
            }
        } else {
            r5Var = null;
        }
        a(jSONArray, r5Var);
        qc qcVar = this.f16842e;
        if (qcVar != null && cVar != null) {
            int i2 = cVar.f16882e;
            qcVar.v(i2 == gh.s || i2 == gh.t);
            y3 y3Var = (y3) this.f16842e.getMapComponent(y3.class);
            if (y3Var != null) {
                y3Var.a(cVar.f16881c);
            }
        }
        qa.i(pa.U);
    }

    public void a(JSONArray jSONArray, r5 r5Var) {
        xg xgVar = this.f16841c;
        if (xgVar == null) {
            return;
        }
        xgVar.b(jSONArray);
        b0 i2 = this.f16846i.i();
        if (i2 != null) {
            i2.a(r5Var);
        }
        this.f16844g.a(this.f16841c.a(), r5Var);
    }

    @Override // com.tencent.mapsdk.internal.nf.a
    public void a(boolean z, uh uhVar) {
        xi xiVar = this.f16846i;
        if (xiVar == null || uhVar == null) {
            return;
        }
        xiVar.a(z, uhVar.h());
        if (z) {
            this.b.h();
        }
        this.b.a(true);
    }

    public void b() {
        qa.h(pa.U);
        Iterator<AsyncTask> it = this.f16843f.iterator();
        while (it.hasNext()) {
            it.next().execute(new Object[0]);
        }
    }
}
