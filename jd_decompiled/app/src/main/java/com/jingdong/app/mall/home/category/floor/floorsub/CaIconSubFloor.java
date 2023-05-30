package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor;
import com.jingdong.app.mall.home.category.view.CaMoreLayout;
import com.jingdong.app.mall.home.n.g.l;
import com.jingdong.app.mall.home.n.g.u.c;
import com.jingdong.app.mall.home.n.g.v.b;
import com.jingdong.app.mall.home.n.g.w.d;
import com.jingdong.app.mall.home.n.g.x.h;
import com.jingdong.common.entity.JumpEntity;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaIconSubFloor extends BaseCaSkuTitleFloor<h> {
    public CaIconSubFloor(Context context) {
        super(context);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    protected boolean i() {
        if ("1".equals(((h) this.f8679m).c())) {
            c g2 = ((h) this.f8679m).g();
            if (g2 instanceof l) {
                com.jingdong.app.mall.home.n.g.v.c k2 = ((h) this.f8679m).k();
                b.c("Category_Main_Subcategory_More", k2 == null ? "" : k2.h().toString());
                JumpEntity d = ((h) this.f8679m).d();
                if (d != null && !TextUtils.isEmpty(d.des)) {
                    com.jingdong.app.mall.home.n.h.b.a(getContext(), d);
                    return true;
                }
                CaMoreLayout.g(getContext()).n((l) g2);
            }
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor
    protected d r() {
        return l.D;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor
    /* renamed from: t  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull h hVar) {
        super.e(hVar);
        if ("1".equals(((h) this.f8679m).c())) {
            CaMoreLayout.g(getContext());
        }
    }
}
