package com.jingdong.app.mall.home.floor.presenter.engine;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.layout.year.YearSkuItem;
import com.jingdong.app.mall.home.floor.model.entity.DeployFloorEntity;
import com.jingdong.app.mall.home.p.b.e.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class DeployFloorEngine extends FloorEngine<DeployFloorEntity> {
    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, DeployFloorEntity deployFloorEntity) {
        super.e(hVar, dVar, deployFloorEntity);
        if (hVar == null || dVar == null || deployFloorEntity == null) {
            return;
        }
        ArrayList<f> a = dVar.a();
        JDJSONObject jDJSONObject = dVar.f10687i;
        if (jDJSONObject == null || jDJSONObject.size() <= 0 || a == null || a.size() <= 0) {
            return;
        }
        List<a> j2 = a.j(jDJSONObject);
        if (j2.size() > dVar.f10682c.size()) {
            return;
        }
        Iterator<f> it = a.iterator();
        while (it.hasNext()) {
            f next = it.next();
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<b> arrayList2 = new ArrayList<>();
            arrayList.add(next.j());
            arrayList2.add(next.k());
            ArrayList<YearSkuItem> i2 = YearSkuItem.i(next.getJsonArr(CartConstant.KEY_ITEMS));
            if (i2 != null && i2.size() > 0) {
                Iterator<YearSkuItem> it2 = i2.iterator();
                while (it2.hasNext()) {
                    YearSkuItem next2 = it2.next();
                    arrayList.add(next2.a());
                    arrayList2.add(b.c(next2.b()));
                }
            }
            JDJSONObject jsonObject = next.getJsonObject("banner");
            if (jsonObject != null) {
                arrayList.add(jsonObject.getString("expo"));
                arrayList2.add(b.c(jsonObject.getString("expoJson")));
            }
            deployFloorEntity.addExpoInfo(arrayList2, arrayList);
        }
        com.jingdong.app.mall.home.p.b.e.b bVar = new com.jingdong.app.mall.home.p.b.e.b();
        bVar.i(hVar);
        bVar.h(dVar);
        bVar.j(jDJSONObject, j2);
        deployFloorEntity.setNodeModel(bVar);
    }
}
