package com.jingdong.discovertask.model.inter;

import com.jingdong.discovertask.model.entity.TaskEntity;
import com.jingdong.discovertask.model.mta.MtaTaskBtnClick;

/* loaded from: classes12.dex */
public interface OnInnerTaskClickListener {
    void onClick(TaskEntity.DiscBaseTaskEntity discBaseTaskEntity, MtaTaskBtnClick mtaTaskBtnClick, int i2);
}
