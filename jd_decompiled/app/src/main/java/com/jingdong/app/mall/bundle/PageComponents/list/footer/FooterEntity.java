package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import com.jingdong.common.widget.custom.pageload.FloorType;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;

/* loaded from: classes19.dex */
public class FooterEntity<T> extends IFloorEntity {
    public States currentState = States.PAGING_LOADING;
    public T extendT;

    @Override // com.jingdong.common.widget.custom.pageload.entity.IFloorEntity
    public int getFloorType() {
        return FloorType.FOOTER;
    }
}
