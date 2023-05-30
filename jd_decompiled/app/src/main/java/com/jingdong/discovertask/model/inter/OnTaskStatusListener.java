package com.jingdong.discovertask.model.inter;

import com.jingdong.discovertask.model.entity.BaseTaskEntity;

/* loaded from: classes12.dex */
public interface OnTaskStatusListener<T extends BaseTaskEntity> {
    void onSignInfoDefeat(T t);

    void onSignInfoSuccess(T t);

    void onTaskClaimFailure(T t);

    void onTaskClaimSuccess(T t);

    void onTaskFinishFailure(T t);

    void onTaskFinishSuccess(T t);

    void onTaskListDefeat(T t);

    void onTaskListSuccess(T t);
}
