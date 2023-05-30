package com.jingdong.common.widget.custom.pageload.entity;

import com.jingdong.common.widget.custom.CustomListFooterView;
import com.jingdong.common.widget.custom.pageload.FloorType;

/* loaded from: classes12.dex */
public class FooterEntity extends IFloorEntity {
    public CustomListFooterView.RetryListener retryListener;
    public short state = 1;

    @Override // com.jingdong.common.widget.custom.pageload.entity.IFloorEntity
    public int getFloorType() {
        return FloorType.FOOTER;
    }
}
