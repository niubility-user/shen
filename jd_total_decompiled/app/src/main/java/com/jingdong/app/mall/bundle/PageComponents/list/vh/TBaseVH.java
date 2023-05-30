package com.jingdong.app.mall.bundle.PageComponents.list.vh;

import android.view.View;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.viewholder.BaseVH;

/* loaded from: classes19.dex */
public abstract class TBaseVH<E> extends BaseVH {
    public TBaseVH(View view) {
        super(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.common.widget.custom.pageload.viewholder.BaseVH
    public void bind(IFloorEntity iFloorEntity) {
        tBind(iFloorEntity);
    }

    public abstract void tBind(E e2);
}
