package com.jingdong.discovertask.model.inter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface TaskType {
    public static final int TYPE_TASK_FOLLOW = 4;
    public static final int TYPE_TASK_SCAN = 2;
    public static final int TYPE_TASK_SIGN = 1;
    public static final int TYPE_TASK_VISITED = 3;
}
