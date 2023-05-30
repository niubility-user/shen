package com.jingdong.app.mall.home.floor.view.special;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.r.e.c;

/* loaded from: classes4.dex */
public class MallFloorDivider extends BaseMallSpecialFloor<c> {

    /* renamed from: i  reason: collision with root package name */
    private c f9975i;

    public MallFloorDivider(Context context) {
        super(context);
    }

    public void c() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.special.BaseMallSpecialFloor
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public void b(c cVar) {
        this.f9975i = cVar;
        cVar.l();
        c();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Paint paint;
        Paint paint2;
        Paint paint3;
        if (this.f9975i == null || com.jingdong.app.mall.home.state.dark.a.h()) {
            return;
        }
        c cVar = this.f9975i;
        Path path = cVar.E;
        if (path != null && (paint3 = cVar.s) != null) {
            canvas.drawPath(path, paint3);
        }
        c cVar2 = this.f9975i;
        Path path2 = cVar2.C;
        if (path2 != null && (paint2 = cVar2.D) != null) {
            canvas.drawPath(path2, paint2);
        }
        c cVar3 = this.f9975i;
        Path path3 = cVar3.A;
        if (path3 == null || (paint = cVar3.B) == null) {
            return;
        }
        canvas.drawPath(path3, paint);
    }

    @Override // com.jingdong.app.mall.home.floor.view.special.BaseMallSpecialFloor, com.jingdong.app.mall.home.widget.b
    public void onViewRecycle() {
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        c cVar = this.f9975i;
        if (cVar != null) {
            int i2 = cVar.z;
            setPadding(i2, 0, i2, 0);
            layoutParams.height = getVisibility() == 0 ? this.f9975i.getFloorHeight() : 0;
        }
        super.setLayoutParams(layoutParams);
    }
}
