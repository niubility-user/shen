package com.jingdong.app.mall.home.r.f.a;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.model.entity.LiveVideoElderEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LiveVideoElderEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder;

/* loaded from: classes4.dex */
public class s extends b<LiveVideoElderEntity, LiveVideoElderEngine, MallFloorLiveVideoElder> {
    public static void V(com.jingdong.app.mall.home.r.c.b bVar, String str) {
        if (bVar != null) {
            bVar.put("videoid", "-100");
            bVar.put("isvideo", "0");
            bVar.put("islabelfrash", "0");
            if (TextUtils.isEmpty(str)) {
                bVar.put("tabstyle", "0");
            } else if (str.equals(com.jingdong.app.mall.home.o.a.f.j(6, str))) {
                bVar.put("tabstyle", "1");
            } else {
                bVar.put("tabstyle", "2");
            }
        }
    }

    public int P() {
        return ((LiveVideoElderEntity) this.d).getBubbleAllTimes();
    }

    public int Q() {
        return ((LiveVideoElderEntity) this.d).getBubbleTimes();
    }

    public com.jingdong.app.mall.home.r.e.f R() {
        return ((LiveVideoElderEntity) this.d).getData();
    }

    public int S() {
        return ((LiveVideoElderEntity) this.d).getElderLiveAnimationIndex();
    }

    public boolean T(int i2) {
        if (S() != 0) {
            if (S() == 1 && i2 == 0) {
                return true;
            }
            return S() == 2 && i2 == 1;
        }
        return true;
    }

    public boolean U() {
        return ((LiveVideoElderEntity) this.d).isShowLiveBubble();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((LiveVideoElderEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
