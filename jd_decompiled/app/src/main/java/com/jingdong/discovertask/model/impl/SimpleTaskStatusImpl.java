package com.jingdong.discovertask.model.impl;

import com.jingdong.discovertask.model.entity.BaseTaskEntity;
import com.jingdong.discovertask.model.inter.OnTaskStatusListener;

/* loaded from: classes12.dex */
public class SimpleTaskStatusImpl<T extends BaseTaskEntity> implements OnTaskStatusListener<T> {
    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onSignInfoDefeat(T t) {
    }

    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onSignInfoSuccess(T t) {
    }

    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onTaskClaimFailure(T t) {
    }

    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onTaskClaimSuccess(T t) {
    }

    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onTaskFinishFailure(T t) {
    }

    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onTaskFinishSuccess(T t) {
    }

    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onTaskListDefeat(T t) {
    }

    @Override // com.jingdong.discovertask.model.inter.OnTaskStatusListener
    public void onTaskListSuccess(T t) {
    }
}
