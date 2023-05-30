package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import com.jingdong.app.mall.bundle.PageComponents.list.vh.TBaseVH;

/* loaded from: classes19.dex */
public class FooterVH extends TBaseVH<FooterEntity> {
    public <E extends Footer> FooterVH(E e2) {
        super(e2);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.vh.TBaseVH
    public void tBind(FooterEntity footerEntity) {
        ((Footer) this.itemView).bind(footerEntity);
    }
}
