package com.jingdong.app.mall.bundle.xanimation.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface XAnimationType {
    public static final int TYPE_ASSIGN_LAYER = 2;
    public static final int TYPE_ASSIGN_VIEW = 0;
    public static final int TYPE_ASSIGN_VIEW_GROUP = 1;
    public static final int TYPE_MONITOR_PROGRESS = 3;
}
