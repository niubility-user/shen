package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor;
import com.jingdong.app.mall.home.category.view.CaMoreLayout;
import com.jingdong.app.mall.home.n.g.o;
import com.jingdong.app.mall.home.n.g.w.d;
import com.jingdong.app.mall.home.n.g.x.j;

/* loaded from: classes4.dex */
public class CaMoreIconSubFloor extends BaseCaSkuTitleFloor<j> {
    public CaMoreIconSubFloor(Context context) {
        super(context);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    protected boolean i() {
        CaMoreLayout j2 = CaMoreLayout.j();
        if (j2 != null) {
            j2.t();
            return false;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor
    protected d r() {
        return o.B;
    }
}
