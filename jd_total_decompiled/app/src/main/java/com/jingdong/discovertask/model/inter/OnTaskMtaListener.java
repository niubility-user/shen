package com.jingdong.discovertask.model.inter;

import com.jingdong.discovertask.model.entity.TaskEntity;

/* loaded from: classes12.dex */
public interface OnTaskMtaListener {
    void onDragFloatIconClick();

    void onDragFloatIconExposure();

    void onHomeFloatIconClick();

    void onHomeFloatIconExposure();

    void onHomeSignAcceptClick();

    void onTaskListBtnClick(TaskEntity.DiscBaseTaskEntity discBaseTaskEntity);

    void onTaskListCourseClick();

    void onTaskListDialogDismiss();

    void onTaskListDialogExposure();

    void onTaskListSignClick();

    void onTaskListSignInBtnExposure();

    void onTaskSignDialogExposure();

    void onTimeDownExposure();
}
