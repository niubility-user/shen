package com.jingdong.common.utils.pre.utils;

import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.utils.pre.PersonalPreLoader;
import com.jingdong.common.utils.pre.task.ITask;
import com.jingdong.common.utils.pre.task.PreTask;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001d\u0010\u001eJ!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ+\u0010\f\u001a\u00020\u0006\"\u0004\b\u0000\u0010\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R+\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00178B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001f"}, d2 = {"Lcom/jingdong/common/utils/pre/utils/PersonalPreUtils;", "", "", "functionId", "Lcom/jingdong/common/utils/pre/task/ITask;", "task", "", "handlePreLoad", "(Ljava/lang/String;Lcom/jingdong/common/utils/pre/task/ITask;)V", "T", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;", "observable", "preLoad", "(Ljava/lang/String;Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;)V", "destroy", "(Ljava/lang/String;)V", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "addListener", "(Ljava/lang/String;Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;)V", "", "taskSuccess", "(Ljava/lang/String;)Z", "Ljava/util/concurrent/ConcurrentHashMap;", "taskMap$delegate", "Lkotlin/Lazy;", "getTaskMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "taskMap", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalPreUtils {

    /* renamed from: taskMap$delegate  reason: from kotlin metadata */
    private final Lazy taskMap = LazyKt.lazy(new Function0<ConcurrentHashMap<String, ITask>>() { // from class: com.jingdong.common.utils.pre.utils.PersonalPreUtils$taskMap$2
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final ConcurrentHashMap<String, ITask> invoke() {
            return new ConcurrentHashMap<>();
        }
    });

    private final ConcurrentHashMap<String, ITask> getTaskMap() {
        return (ConcurrentHashMap) this.taskMap.getValue();
    }

    private final void handlePreLoad(String functionId, ITask task) {
        if (getTaskMap().containsKey(functionId)) {
            return;
        }
        getTaskMap().put(functionId, task);
        if (task != null) {
            task.doTask();
        }
    }

    public final void addListener(@NotNull String functionId, @NotNull PersonalPreLoader.DataChangeListener r3) {
        ITask iTask;
        if (!getTaskMap().containsKey(functionId) || (iTask = getTaskMap().get(functionId)) == null) {
            return;
        }
        iTask.addTaskListener(r3);
    }

    public final void destroy(@NotNull String functionId) {
        ITask remove = getTaskMap().remove(functionId);
        if (remove != null) {
            remove.destroy();
        }
    }

    public final <T> void preLoad(@Nullable String functionId, @NotNull PersonalPreLoader.LoadDataObservable<T> observable) {
        if (functionId == null || functionId.length() == 0) {
            return;
        }
        handlePreLoad(functionId, new PreTask(observable));
    }

    public final boolean taskSuccess(@NotNull String functionId) {
        ITask iTask;
        if (!getTaskMap().containsKey(functionId) || (iTask = getTaskMap().get(functionId)) == null) {
            return false;
        }
        return iTask.isSuccess();
    }
}
