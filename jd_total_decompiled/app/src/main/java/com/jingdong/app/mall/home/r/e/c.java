package com.jingdong.app.mall.home.r.e;

import android.graphics.Paint;
import android.graphics.Path;
import com.jingdong.app.mall.home.floor.common.i.o;
import com.jingdong.app.mall.home.floor.common.i.t;
import java.util.List;

/* loaded from: classes4.dex */
public class c extends d {
    public Path A;
    public Paint B;
    public Path C;
    public Paint D;
    public Path E;
    private o F;
    public d G;
    public int z;

    public c(h hVar, o oVar) {
        super(hVar, t.FLOOR_DIVIDER, false);
        this.F = oVar;
    }

    @Override // com.jingdong.app.mall.home.r.e.a
    public int getFloorHeight() {
        d dVar = this.G;
        if (dVar == null || (dVar.mFloorHeight > 0 && dVar.isShowFloor())) {
            return this.mFloorHeight;
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.r.e.d
    public void k(boolean z) {
        super.k(z);
        if (z) {
            Path path = new Path();
            this.E = path;
            path.addRect(0.0f, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g, getFloorHeight(), Path.Direction.CW);
        }
    }

    @Override // com.jingdong.app.mall.home.r.e.d
    public boolean q() {
        return true;
    }

    public void x(List<? super d> list) {
        o oVar = this.F;
        if (oVar == null || !oVar.parseDividerHeight(list, this)) {
            return;
        }
        this.F.parseDividerInfo(this);
    }
}
