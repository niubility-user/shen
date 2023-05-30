package com.jingdong.common.utils.pre.task;

import com.jingdong.common.utils.pre.PersonalPreLoader;
import com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00018\u0000H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"com/jingdong/common/utils/pre/task/PreTask$run$1", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataCallBack;", "httpResponse", "", "onSuccess", "(Ljava/lang/Object;)V", "onError", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PreTask$run$1<T> implements PersonalPreLoader.DataCallBack<T> {
    final /* synthetic */ PreTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreTask$run$1(PreTask preTask) {
        this.this$0 = preTask;
    }

    @Override // com.jingdong.common.utils.pre.PersonalPreLoader.DataCallBack
    public void onError() {
        PreReadWriteLockUtil readWriteLockUtil;
        readWriteLockUtil = this.this$0.getReadWriteLockUtil();
        readWriteLockUtil.doRead(new PreReadWriteLockUtil.CallbackReadLock() { // from class: com.jingdong.common.utils.pre.task.PreTask$run$1$onError$1
            @Override // com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil.CallbackReadLock
            public void doRead() {
                PreTask$run$1.this.this$0.handleDataChange(null);
            }

            @Override // com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil.CallbackReadLock
            public void onException(@NotNull Throwable e2) {
                PreTask$run$1.this.this$0.handleDataChange(null);
            }
        });
    }

    @Override // com.jingdong.common.utils.pre.PersonalPreLoader.DataCallBack
    public void onSuccess(@Nullable final T httpResponse) {
        PreReadWriteLockUtil readWriteLockUtil;
        readWriteLockUtil = this.this$0.getReadWriteLockUtil();
        readWriteLockUtil.doRead(new PreReadWriteLockUtil.CallbackReadLock() { // from class: com.jingdong.common.utils.pre.task.PreTask$run$1$onSuccess$1
            @Override // com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil.CallbackReadLock
            public void doRead() {
                PreTask$run$1.this.this$0.handleDataChange(httpResponse);
            }

            @Override // com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil.CallbackReadLock
            public void onException(@NotNull Throwable e2) {
                PreTask$run$1.this.this$0.handleDataChange(httpResponse);
            }
        });
    }
}
