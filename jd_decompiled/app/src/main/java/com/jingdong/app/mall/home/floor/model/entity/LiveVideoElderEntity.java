package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.g;
import com.jingdong.app.mall.home.r.f.a.s;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class LiveVideoElderEntity extends FloorEntity {
    private f mData;

    public static boolean isValid(g gVar) {
        ArrayList<f> arrayList;
        return (gVar == null || (arrayList = gVar.f10682c) == null || arrayList.size() <= 0 || arrayList.get(0) == null || TextUtils.isEmpty(arrayList.get(0).u()) || TextUtils.isEmpty(arrayList.get(0).v())) ? false : true;
    }

    public int getBubbleAllTimes() {
        f fVar = this.mData;
        if (fVar == null) {
            return 0;
        }
        return fVar.S();
    }

    public int getBubbleTimes() {
        f fVar = this.mData;
        if (fVar == null) {
            return 0;
        }
        return fVar.M();
    }

    public f getData() {
        return this.mData;
    }

    public int getElderLiveAnimationIndex() {
        f fVar = this.mData;
        if (fVar == null) {
            return 0;
        }
        return fVar.getJsonInt("elderLiveAnimationIndex");
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    public void initData(f fVar) {
        this.mData = fVar;
        if (TextUtils.isEmpty(fVar.l())) {
            return;
        }
        b c2 = b.c(this.mData.l());
        s.V(c2, this.mData.W());
        this.mJsonExposData.add(c2);
    }

    public boolean isShowLiveBubble() {
        f fVar = this.mData;
        return fVar != null && 1 == fVar.getJsonInt("showBubble");
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return (TextUtils.isEmpty(this.mData.u()) || TextUtils.isEmpty(this.mData.v())) ? false : true;
    }
}
