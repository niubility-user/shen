package com.jingdong.app.mall.home.r.e;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class d extends g {

    /* renamed from: n  reason: collision with root package name */
    public FloorEngine f10670n;
    public FloorEntity o;
    public ArrayList<d> p;
    public t q;
    private AtomicBoolean r;
    public Paint s;
    public int t;
    public int u;
    private boolean v;
    private boolean w;
    private int x;
    private int y;

    public d(h hVar, t tVar, boolean z) {
        super(hVar);
        this.q = t.UNKNOWN;
        this.r = new AtomicBoolean(false);
        this.u = -1;
        this.v = true;
        this.y = -1;
        this.isCacheData = z;
        this.mParentModel = hVar;
        s(tVar);
    }

    public int i() {
        int i2 = this.u;
        return i2 > 0 ? i2 : this.q.getFloorIntType();
    }

    public int j() {
        return this.x;
    }

    public void k(boolean z) {
    }

    public final void l() {
        if (this.mParentModel == null || this.r.getAndSet(true)) {
            return;
        }
        boolean z = this.mParentModel.l() && !this.mParentModel.b0;
        k(z);
        if (z) {
            this.s = new Paint(1);
            LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, r1.W, this.mParentModel.w, (float[]) null, Shader.TileMode.CLAMP);
            Matrix matrix = new Matrix();
            matrix.setTranslate(0.0f, -this.mTopParent);
            linearGradient.setLocalMatrix(matrix);
            this.s.setShader(linearGradient);
        }
    }

    public boolean m() {
        return this.x == 0;
    }

    public boolean n() {
        return this.x == this.y;
    }

    public boolean o() {
        return this.w;
    }

    public boolean p() {
        return this.v && t.UNKNOWN != this.q;
    }

    public boolean q() {
        ArrayList<d> arrayList;
        ArrayList<f> arrayList2 = this.f10682c;
        return (arrayList2 == null || arrayList2.size() == 0) && ((arrayList = this.p) == null || arrayList.size() == 0);
    }

    public d r() {
        ArrayList<d> arrayList = this.p;
        return (arrayList == null || arrayList.size() <= 0) ? this : this.p.get(0);
    }

    public void s(t tVar) {
        t(tVar, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void t(t tVar, Context context) {
        if (tVar == null) {
            return;
        }
        this.q = tVar;
        try {
            tVar.parseFloorInfo(this);
            this.q.checkAlignSkin(this);
            this.q.preInitFloorView(context, this);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    public void u(int i2) {
        this.x = i2;
        this.w = true;
    }

    public void v(int i2) {
        this.y = i2;
    }

    public void w(boolean z) {
        this.v = z;
    }

    public d(h hVar, t tVar, Context context, boolean z) {
        super(hVar);
        this.q = t.UNKNOWN;
        this.r = new AtomicBoolean(false);
        this.u = -1;
        this.v = true;
        this.y = -1;
        this.isCacheData = z;
        this.mParentModel = hVar;
        t(tVar, context);
    }

    public d(JDJSONObject jDJSONObject, boolean z) {
        super(jDJSONObject);
        this.q = t.UNKNOWN;
        this.r = new AtomicBoolean(false);
        this.u = -1;
        this.v = true;
        this.y = -1;
        this.isCacheData = z;
    }

    public d(t tVar, boolean z) {
        this.q = t.UNKNOWN;
        this.r = new AtomicBoolean(false);
        this.u = -1;
        this.v = true;
        this.y = -1;
        this.isCacheData = z;
        s(tVar);
    }
}
