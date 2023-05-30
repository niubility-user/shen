package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.TongLanSchoolEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.TongLanSchoolEngine;
import com.jingdong.app.mall.home.floor.view.view.MallFloorTongLanSchool;

/* loaded from: classes4.dex */
public class y extends b<TongLanSchoolEntity, TongLanSchoolEngine, MallFloorTongLanSchool> {
    public y(Class<TongLanSchoolEntity> cls, Class<TongLanSchoolEngine> cls2) {
        super(cls, cls2);
    }

    public com.jingdong.app.mall.home.r.e.f P() {
        return ((TongLanSchoolEntity) this.d).element;
    }

    public String Q() {
        return ((TongLanSchoolEntity) this.d).iconImg;
    }

    public int R() {
        return ((TongLanSchoolEntity) this.d).imageType;
    }

    public String S() {
        return ((TongLanSchoolEntity) this.d).img;
    }

    public String T() {
        return ((TongLanSchoolEntity) this.d).subtitle;
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        MallFloorTongLanSchool mallFloorTongLanSchool = (MallFloorTongLanSchool) c();
        if (mallFloorTongLanSchool == null) {
            return;
        }
        mallFloorTongLanSchool.initViewData();
    }
}
