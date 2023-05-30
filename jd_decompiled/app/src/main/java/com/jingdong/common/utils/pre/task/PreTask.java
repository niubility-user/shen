package com.jingdong.common.utils.pre.task;

import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.utils.pre.PersonalPreLoader;
import com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 (*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u0003:\u0001(B\u0015\u0012\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\n\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00018\u0000H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0019\u0010\u000f\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u000f\u0010\bJ\u000f\u0010\u0010\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0010\u0010\rJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u0004\u0018\u00018\u00008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001d\u0010\u001d\u001a\u00020\u00188B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR#\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u001e8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010!R\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%\u00a8\u0006)"}, d2 = {"Lcom/jingdong/common/utils/pre/task/PreTask;", "T", "Lcom/jingdong/common/utils/pre/task/ITask;", "Ljava/lang/Runnable;", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "", "handleAddTaskListener", "(Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;)V", "httpResponse", "handleDataChange", "(Ljava/lang/Object;)V", "doTask", "()V", "destroy", "addTaskListener", "run", "", "isSuccess", "()Z", "response", "Ljava/lang/Object;", "isNetFinish", "Z", "Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil;", "readWriteLockUtil$delegate", "Lkotlin/Lazy;", "getReadWriteLockUtil", "()Lcom/jingdong/common/utils/pre/utils/PreReadWriteLockUtil;", "readWriteLockUtil", "Ljava/util/concurrent/CopyOnWriteArrayList;", "listeners$delegate", "getListeners", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "listeners", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;", "observable", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;", "<init>", "(Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;)V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PreTask<T> implements ITask, Runnable {
    @NotNull
    public static final String TAG = "PreTask";
    private boolean isNetFinish;

    /* renamed from: listeners$delegate  reason: from kotlin metadata */
    private final Lazy listeners;
    private final PersonalPreLoader.LoadDataObservable<T> observable;

    /* renamed from: readWriteLockUtil$delegate  reason: from kotlin metadata */
    private final Lazy readWriteLockUtil;
    private T response;

    public PreTask(@NotNull PersonalPreLoader.LoadDataObservable<T> loadDataObservable) {
        Lazy lazy;
        Lazy lazy2;
        this.observable = loadDataObservable;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<CopyOnWriteArrayList<PersonalPreLoader.DataChangeListener>>() { // from class: com.jingdong.common.utils.pre.task.PreTask$listeners$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CopyOnWriteArrayList<PersonalPreLoader.DataChangeListener> invoke() {
                return new CopyOnWriteArrayList<>();
            }
        });
        this.listeners = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<PreReadWriteLockUtil>() { // from class: com.jingdong.common.utils.pre.task.PreTask$readWriteLockUtil$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PreReadWriteLockUtil invoke() {
                return new PreReadWriteLockUtil();
            }
        });
        this.readWriteLockUtil = lazy2;
    }

    private final CopyOnWriteArrayList<PersonalPreLoader.DataChangeListener> getListeners() {
        return (CopyOnWriteArrayList) this.listeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PreReadWriteLockUtil getReadWriteLockUtil() {
        return (PreReadWriteLockUtil) this.readWriteLockUtil.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleAddTaskListener(PersonalPreLoader.DataChangeListener listener) {
        if (!getListeners().contains(listener)) {
            getListeners().add(listener);
        }
        if (this.isNetFinish) {
            listener.change(this.response);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDataChange(T httpResponse) {
        this.response = httpResponse;
        this.isNetFinish = true;
        Iterator<T> it = getListeners().iterator();
        while (it.hasNext()) {
            ((PersonalPreLoader.DataChangeListener) it.next()).change(this.response);
        }
    }

    @Override // com.jingdong.common.utils.pre.task.ITask
    public void addTaskListener(@Nullable final PersonalPreLoader.DataChangeListener listener) {
        if (listener != null) {
            getReadWriteLockUtil().doWrite(new PreReadWriteLockUtil.CallbackWriteLock() { // from class: com.jingdong.common.utils.pre.task.PreTask$addTaskListener$$inlined$let$lambda$1
                @Override // com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil.CallbackWriteLock
                public void doWrite() {
                    this.handleAddTaskListener(PersonalPreLoader.DataChangeListener.this);
                }

                @Override // com.jingdong.common.utils.pre.utils.PreReadWriteLockUtil.CallbackWriteLock
                public void onException(@NotNull Throwable th) {
                    this.handleAddTaskListener(PersonalPreLoader.DataChangeListener.this);
                }
            });
        }
    }

    @Override // com.jingdong.common.utils.pre.task.ITask
    public void destroy() {
        this.isNetFinish = false;
        this.response = null;
        getListeners().clear();
    }

    @Override // com.jingdong.common.utils.pre.task.ITask
    public void doTask() {
        run();
    }

    @Override // com.jingdong.common.utils.pre.task.ITask
    public boolean isSuccess() {
        return this.isNetFinish && this.response != null;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.observable.load(new PreTask$run$1(this));
    }
}
