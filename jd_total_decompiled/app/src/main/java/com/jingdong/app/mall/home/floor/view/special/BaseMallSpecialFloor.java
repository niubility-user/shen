package com.jingdong.app.mall.home.floor.view.special;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorTop;
import com.jingdong.app.mall.home.r.e.a;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.widget.b;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public abstract class BaseMallSpecialFloor<M extends com.jingdong.app.mall.home.r.e.a> extends RelativeLayout implements b, IMallFloorTop {

    /* renamed from: g  reason: collision with root package name */
    private final String f9973g;

    /* renamed from: h  reason: collision with root package name */
    protected d f9974h;

    public BaseMallSpecialFloor(Context context) {
        super(context);
        this.f9973g = BaseMallSpecialFloor.class.getSimpleName();
    }

    private boolean a(d dVar) {
        return getHeight() != dVar.getFloorHeight();
    }

    protected abstract void b(M m2);

    @Override // com.jingdong.app.mall.home.widget.b
    public View getContentView() {
        return this;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorTop
    public int getLayoutMaxHeight() {
        return getHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorTop
    public final int getLayoutTop() {
        h hVar;
        d dVar = this.f9974h;
        if (dVar == null || (hVar = dVar.mParentModel) == null) {
            return 0;
        }
        return hVar.X + dVar.mTopParent;
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onPreInitView(d dVar, boolean z) {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onReleaseView() {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onUseView() {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public final void onViewBind(d dVar) {
        d dVar2 = this.f9974h;
        if (dVar2 != dVar || dVar2.isNeedRefresh || a(dVar)) {
            this.f9974h = dVar;
            try {
                b(dVar);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(this.f9973g, e2.getMessage());
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onViewRecycle() {
    }
}
