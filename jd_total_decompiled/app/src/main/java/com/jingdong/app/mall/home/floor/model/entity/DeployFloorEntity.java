package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jingdong.app.mall.home.p.b.e.b;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class DeployFloorEntity extends FloorEntity {
    private b mNodeModel;

    public void addExpoInfo(ArrayList<com.jingdong.app.mall.home.r.c.b> arrayList, ArrayList<String> arrayList2) {
        if (!arrayList2.isEmpty()) {
            Iterator<String> it = arrayList2.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!TextUtils.isEmpty(next)) {
                    this.mExposData.add(next);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.mJsonExposData.addAll(arrayList);
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        return this.mExposData;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<com.jingdong.app.mall.home.r.c.b> getExpoJson() {
        return this.mJsonExposData;
    }

    public b getNodeModel() {
        return this.mNodeModel;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return this.mNodeModel != null;
    }

    public void setNodeModel(b bVar) {
        this.mNodeModel = bVar;
    }
}
