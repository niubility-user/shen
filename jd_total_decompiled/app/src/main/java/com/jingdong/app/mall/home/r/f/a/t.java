package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;
import com.jingdong.app.mall.home.floor.view.view.MallFloorMaiDian;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class t extends b<FloorEntity, FloorEngine, MallFloorMaiDian> {

    /* renamed from: h */
    private ArrayList<String> f10783h;

    public t(Class<FloorEntity> cls, Class<FloorEngine> cls2) {
        super(cls, cls2);
        this.f10783h = new ArrayList<>();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public ArrayList<String> g() {
        return this.f10783h;
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        this.f10783h.clear();
        if (hVar != null) {
            this.f10783h.add(hVar.C);
        }
    }
}
