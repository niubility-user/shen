package com.jingdong.common.utils.rx.internal.concurrent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.SOURCE)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0004\b\u0087\u0002\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0005"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/concurrent/IOTaskPriorityType;", "", "<init>", "()V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public @interface IOTaskPriorityType {
    public static final int CORE_TASK = 100;

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int DISCARD_TASK_VALUE = -10;
    public static final int LOW_PRIORITY_TASK = -50;
    public static final int NORMAL_TASK = 0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004\u00a8\u0006\n"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/concurrent/IOTaskPriorityType$Companion;", "", "", "DISCARD_TASK_VALUE", "I", "LOW_PRIORITY_TASK", "CORE_TASK", "NORMAL_TASK", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int CORE_TASK = 100;
        public static final int DISCARD_TASK_VALUE = -10;
        public static final int LOW_PRIORITY_TASK = -50;
        public static final int NORMAL_TASK = 0;

        private Companion() {
        }
    }
}
