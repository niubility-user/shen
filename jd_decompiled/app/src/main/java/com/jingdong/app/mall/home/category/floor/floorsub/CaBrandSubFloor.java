package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.widget.ImageView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor;
import com.jingdong.app.mall.home.n.g.w.d;
import com.jingdong.app.mall.home.n.g.x.b;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaBrandSubFloor extends BaseCaSkuTitleFloor<b> {
    public CaBrandSubFloor(Context context) {
        super(context);
        this.q.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.q.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor
    protected d r() {
        return com.jingdong.app.mall.home.n.g.b.A;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaSkuTitleFloor
    /* renamed from: t  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull b bVar) {
        super.e(bVar);
        if ("0".equals(bVar.u())) {
            this.r.setVisibility(4);
        } else {
            this.r.setVisibility(0);
        }
    }
}
