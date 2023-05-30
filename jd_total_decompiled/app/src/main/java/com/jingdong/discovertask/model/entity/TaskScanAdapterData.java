package com.jingdong.discovertask.model.entity;

import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.discovertask.model.entity.TaskEntity;

/* loaded from: classes12.dex */
public class TaskScanAdapterData extends IFloorEntity {
    public TaskEntity.DiscBaseTaskEntity mDiscBaseTaskEntity;
    public int mtaTaskPosition = -1;

    @Override // com.jingdong.common.widget.custom.pageload.entity.IFloorEntity
    public int getFloorType() {
        return 2;
    }
}
