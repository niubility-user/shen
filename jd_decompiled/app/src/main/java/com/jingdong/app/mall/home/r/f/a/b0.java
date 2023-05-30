package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;
import com.jingdong.app.mall.home.floor.view.view.MallFloorWithSubFloor;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class b0 extends b<FloorEntity, FloorEngine, MallFloorWithSubFloor> {
    public String P(com.jingdong.app.mall.home.r.e.d dVar) {
        StringBuilder sb = new StringBuilder();
        ArrayList<com.jingdong.app.mall.home.r.e.f> a = dVar.a();
        if (a != null) {
            for (int i2 = 0; i2 < a.size(); i2++) {
                sb.append(a.get(i2).s());
            }
        }
        return Md5Encrypt.md5(dVar.a + sb.toString());
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void t(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.t(hVar, dVar);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        MallFloorWithSubFloor mallFloorWithSubFloor = (MallFloorWithSubFloor) c();
        if (mallFloorWithSubFloor == null || dVar == null) {
            return;
        }
        mallFloorWithSubFloor.cleanUI();
        ArrayList<com.jingdong.app.mall.home.r.e.d> arrayList = dVar.p;
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size();
            int i2 = R.id.mallfloor_item1;
            int i3 = size;
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                com.jingdong.app.mall.home.r.e.d dVar2 = arrayList.get(i5);
                if (dVar2 == null) {
                    i3--;
                    mallFloorWithSubFloor.cleanUI();
                } else {
                    mallFloorWithSubFloor.initSubFloorView(hVar, dVar, dVar2, i4, i2);
                    i4 = i2;
                    i2++;
                }
            }
            if (i4 == 0 || i3 == 0) {
                mallFloorWithSubFloor.cleanUI();
                mallFloorWithSubFloor.onSetVisible(false);
                return;
            }
            return;
        }
        mallFloorWithSubFloor.onSetVisible(false);
    }
}
