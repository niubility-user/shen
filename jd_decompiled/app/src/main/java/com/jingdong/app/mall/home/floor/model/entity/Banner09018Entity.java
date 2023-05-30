package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class Banner09018Entity extends FloorEntity {
    public f leftElement;
    public f midElement;
    public f rightElement;

    public void addExpoJson() {
        f fVar = this.leftElement;
        if (fVar != null) {
            addExpoJson(fVar.l());
        }
        f fVar2 = this.midElement;
        if (fVar2 != null) {
            addExpoJson(fVar2.l());
        }
        f fVar3 = this.rightElement;
        if (fVar3 != null) {
            addExpoJson(fVar3.l());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (isValid()) {
            this.mExposData.clear();
            this.mExposData.add(this.leftElement.j());
            this.mExposData.add(this.midElement.j());
            this.mExposData.add(this.rightElement.j());
            return this.mExposData;
        }
        return super.getExpoData();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return (this.leftElement == null || this.midElement == null || this.rightElement == null) ? false : true;
    }

    public void resetData() {
        this.leftElement = null;
        this.midElement = null;
        this.rightElement = null;
    }

    private void addExpoJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonExposData.add(b.c(str));
    }
}
