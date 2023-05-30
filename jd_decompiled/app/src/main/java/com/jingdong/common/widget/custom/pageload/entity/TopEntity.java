package com.jingdong.common.widget.custom.pageload.entity;

import com.jingdong.common.widget.custom.pageload.FloorType;
import java.util.List;

/* loaded from: classes12.dex */
public class TopEntity<T> extends IFloorEntity {
    public boolean isMove;
    public List<T> list;

    public TopEntity(List list) {
        this(list, false);
    }

    @Override // com.jingdong.common.widget.custom.pageload.entity.IFloorEntity
    public int getFloorType() {
        return FloorType.TOP;
    }

    public TopEntity(List list, boolean z) {
        this.list = list;
        this.isMove = z;
    }
}
