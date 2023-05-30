package com.jingdong.common.utils.pre.task;

import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.utils.pre.PersonalPreLoader;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0019\u0010\b\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH&\u00a2\u0006\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/jingdong/common/utils/pre/task/ITask;", "", "", "doTask", "()V", "destroy", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "addTaskListener", "(Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;)V", "", "isSuccess", "()Z", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface ITask {
    void addTaskListener(@Nullable PersonalPreLoader.DataChangeListener listener);

    void destroy();

    void doTask();

    boolean isSuccess();
}
