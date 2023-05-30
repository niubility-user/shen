package com.jingdong.app.mall.home.n.g;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class s extends com.jingdong.app.mall.home.n.g.u.b {
    private final List<com.jingdong.app.mall.home.n.g.u.e> s;
    private int t;

    public s(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject, aVar);
        this.s = new CopyOnWriteArrayList();
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean B() {
        return false;
    }

    public final int G() {
        int d = (com.jingdong.app.mall.home.floor.common.d.f9279g - this.t) - com.jingdong.app.mall.home.floor.common.d.d(34);
        int size = this.s.size();
        if (size == 5 || d < 0) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.i(d / (size - 1));
    }

    @NotNull
    public final List<com.jingdong.app.mall.home.n.g.u.e> H() {
        return this.s;
    }

    public final void I(@NotNull CategoryEntity.CaItem caItem) {
        caItem.clearRequest();
        for (com.jingdong.app.mall.home.n.g.u.e eVar : this.s) {
            if (eVar instanceof com.jingdong.app.mall.home.n.g.x.n) {
                com.jingdong.app.mall.home.n.g.x.n nVar = (com.jingdong.app.mall.home.n.g.x.n) eVar;
                if (nVar.E().get()) {
                    caItem.setSort(nVar.B());
                    caItem.setSelf(nVar.A());
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.t("Category_Selected_SortRow_Expo", "\u4eac\u6311\u4f1a\u9009");
        StringBuilder sb = new StringBuilder();
        Iterator<com.jingdong.app.mall.home.n.g.u.e> it = this.s.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            com.jingdong.app.mall.home.n.g.u.e next = it.next();
            if (next instanceof com.jingdong.app.mall.home.n.g.x.n) {
                com.jingdong.app.mall.home.n.g.x.n nVar = (com.jingdong.app.mall.home.n.g.x.n) next;
                if (nVar.C()) {
                    if (sb.length() > 0) {
                        sb.append("#");
                    }
                    sb.append(nVar.x());
                }
            }
        }
        cVar.u("{}");
        cVar.D("filterOption", sb.length() > 0 ? sb.toString() : "-100");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
        int size;
        this.o = true;
        v(true);
        this.t = 0;
        JDJSONArray jDJSONArray = this.q;
        if (jDJSONArray != null && (size = jDJSONArray.size()) >= 3) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < size; i2++) {
                com.jingdong.app.mall.home.n.g.u.e itemModel = com.jingdong.app.mall.home.n.b.S_SELECT.getTypeModel(this.q.getJSONObject(i2), this, i2);
                Intrinsics.checkExpressionValueIsNotNull(itemModel, "itemModel");
                if (itemModel.m() && !arrayList.contains(itemModel.f())) {
                    String f2 = itemModel.f();
                    Intrinsics.checkExpressionValueIsNotNull(f2, "itemModel.key");
                    arrayList.add(f2);
                    this.t += itemModel.b();
                    this.s.add(itemModel);
                }
            }
        }
    }
}
