package com.jingdong.app.mall.home.r.e;

import android.graphics.Paint;
import android.graphics.Path;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;

/* loaded from: classes4.dex */
public class j extends d {
    public Path A;
    public d z;

    public j(h hVar) {
        super(hVar, t.TITLE_FLOOR, false);
        this.A = new Path();
        this.o = new FloorEntity();
        FloorEngine floorEngine = new FloorEngine();
        this.f10670n = floorEngine;
        floorEngine.e(hVar, this, this.o);
    }

    public static boolean x(h hVar) {
        return hVar != null && "1".equals(hVar.f10700l);
    }

    @Override // com.jingdong.app.mall.home.r.e.a
    public int getFloorHeight() {
        if (x(this.mParentModel)) {
            d dVar = this.z;
            if (dVar == null || (dVar.mFloorHeight > 0 && dVar.isShowFloor())) {
                return this.mFloorHeight;
            }
            return 0;
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.r.e.d
    public void k(boolean z) {
        super.k(z);
        this.A.addRect(0.0f, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g, getFloorHeight(), Path.Direction.CW);
        if (z) {
            return;
        }
        Paint paint = new Paint(1);
        this.s = paint;
        paint.setColor(this.o.getDividerColor());
    }

    @Override // com.jingdong.app.mall.home.r.e.d
    public boolean q() {
        return true;
    }
}
