package com.jingdong.pdj.libdjbasecore.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002BN\b\u0007\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\n\u0010/\u001a\u0006\u0012\u0002\b\u00030.\u0012+\b\u0002\u0010:\u001a%\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u0003\u0018\u000105j\u0004\u0018\u0001`9\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\n\u0010\tJ\u0015\u0010\n\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\n\u0010\rJ\r\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000e\u0010\u0005J\r\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0005J\u001d\u0010\u0013\u001a\u00020\u00122\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0005J\u000f\u0010\u0016\u001a\u00020\u0003H\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0005J\u000f\u0010\u0017\u001a\u00020\u0003H\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0005J\u000f\u0010\u0018\u001a\u00020\u0003H\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0005J\u000f\u0010\u0019\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u0005J\u001f\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ)\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0016\u00a2\u0006\u0004\b\u001c\u0010 J\u001f\u0010!\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b!\u0010\u001dJ\u001f\u0010\"\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\"\u0010\u001dJ'\u0010%\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b%\u0010&R\u0018\u0010(\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R&\u0010,\u001a\u0012\u0012\u0004\u0012\u00020\u00060*j\b\u0012\u0004\u0012\u00020\u0006`+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u001a\u0010/\u001a\u0006\u0012\u0002\b\u00030.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104R9\u0010:\u001a%\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u0003\u0018\u000105j\u0004\u0018\u0001`98\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;\u00a8\u0006>"}, d2 = {"Lcom/jingdong/pdj/libdjbasecore/utils/DjRVTimer;", "Landroidx/lifecycle/LifecycleObserver;", "Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;", "", "countDownItem", "()V", "Lcom/jingdong/pdj/libdjbasecore/utils/RvPos;", "data", "addCountDownPos", "(Lcom/jingdong/pdj/libdjbasecore/utils/RvPos;)V", "removeCountDownPos", "", "pos", "(I)V", "startTimer", "stopTimer", "Lkotlin/Function0;", "onEach", "Lkotlinx/coroutines/Job;", "createCountDownJob", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/Job;", "removeAllPosition", "onStart", "onStop", "onDestroy", "onChanged", "positionStart", "itemCount", "onItemRangeChanged", "(II)V", "", "payload", "(IILjava/lang/Object;)V", "onItemRangeInserted", "onItemRangeRemoved", "fromPosition", "toPosition", "onItemRangeMoved", "(III)V", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "countDownPositions", "Ljava/util/ArrayList;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "rvAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "count", "I", "timerJob", "Lkotlinx/coroutines/Job;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "position", "Lcom/jingdong/pdj/libdjbasecore/utils/OnTimeDownListener;", "timeDownListener", "Lkotlin/jvm/functions/Function1;", "<init>", "(Landroidx/lifecycle/Lifecycle;Landroidx/recyclerview/widget/RecyclerView$Adapter;Lkotlin/jvm/functions/Function1;)V", "libDJBaseCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class DjRVTimer extends RecyclerView.AdapterDataObserver implements LifecycleObserver {
    private int count;
    private ArrayList<RvPos> countDownPositions;
    private Lifecycle lifecycle;
    private RecyclerView.Adapter<?> rvAdapter;
    private Function1<? super Integer, Unit> timeDownListener;
    private Job timerJob;

    @JvmOverloads
    public DjRVTimer(@Nullable Lifecycle lifecycle, @NotNull RecyclerView.Adapter<?> adapter) {
        this(lifecycle, adapter, null, 4, null);
    }

    public /* synthetic */ DjRVTimer(Lifecycle lifecycle, RecyclerView.Adapter adapter, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : lifecycle, adapter, (i2 & 4) != 0 ? null : function1);
    }

    @JvmOverloads
    public DjRVTimer(@NotNull RecyclerView.Adapter<?> adapter) {
        this(null, adapter, null, 5, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void countDownItem() {
        for (int size = this.countDownPositions.size() - 1; size >= 0; size--) {
            RvPos rvPos = this.countDownPositions.get(size);
            Intrinsics.checkExpressionValueIsNotNull(rvPos, "countDownPositions[i]");
            RvPos rvPos2 = rvPos;
            if (!rvPos2.getEnableState()) {
                this.countDownPositions.remove(rvPos2);
            } else {
                Function1<? super Integer, Unit> function1 = this.timeDownListener;
                if (function1 == null) {
                    this.rvAdapter.notifyItemChanged(rvPos2.getPosition());
                } else if (function1 != null) {
                    function1.invoke(Integer.valueOf(rvPos2.getPosition()));
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Job createCountDownJob$default(DjRVTimer djRVTimer, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.jingdong.pdj.libdjbasecore.utils.DjRVTimer$createCountDownJob$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        return djRVTimer.createCountDownJob(function0);
    }

    public final void addCountDownPos(@NotNull RvPos data) {
        if (this.countDownPositions.contains(data)) {
            return;
        }
        this.countDownPositions.add(data);
        startTimer();
    }

    @NotNull
    public final Job createCountDownJob(@NotNull Function0<Unit> onEach) {
        return FlowKt.launchIn(FlowKt.flowOn(FlowKt.onEach(FlowKt.flowOn(FlowKt.flow(new DjRVTimer$createCountDownJob$2(this, null)), Dispatchers.getDefault()), new DjRVTimer$createCountDownJob$3(onEach, null)), Dispatchers.getMain()), GlobalScope.INSTANCE);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onChanged() {
        removeAllPosition();
        super.onChanged();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy() {
        this.countDownPositions.clear();
        stopTimer();
        this.timerJob = null;
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeInserted(int positionStart, int itemCount) {
        Iterator<RvPos> it = this.countDownPositions.iterator();
        while (it.hasNext()) {
            RvPos next = it.next();
            if (next.getPosition() >= positionStart) {
                next.setPosition(next.getPosition() + itemCount);
            }
        }
        super.onItemRangeInserted(positionStart, itemCount);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        Iterator<RvPos> it = this.countDownPositions.iterator();
        while (it.hasNext()) {
            RvPos next = it.next();
            if (next.getPosition() == fromPosition) {
                next.setPosition(toPosition);
            } else if (next.getPosition() == toPosition) {
                next.setPosition(fromPosition);
            }
        }
        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        int size = this.countDownPositions.size();
        while (true) {
            size--;
            if (size >= 0) {
                RvPos rvPos = this.countDownPositions.get(size);
                Intrinsics.checkExpressionValueIsNotNull(rvPos, "countDownPositions[i]");
                RvPos rvPos2 = rvPos;
                if (rvPos2.getPosition() >= positionStart + itemCount) {
                    rvPos2.setPosition(rvPos2.getPosition() - itemCount);
                } else if (rvPos2.getPosition() >= positionStart) {
                    removeCountDownPos(rvPos2.getPosition());
                }
            } else {
                super.onItemRangeRemoved(positionStart, itemCount);
                return;
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void onStart() {
        startTimer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onStop() {
        stopTimer();
    }

    public final void removeAllPosition() {
        this.countDownPositions.clear();
    }

    public final void removeCountDownPos(@NotNull RvPos data) {
        this.countDownPositions.remove(data);
    }

    public final void startTimer() {
        Job job = this.timerJob;
        if (job == null || !(job == null || job.isActive() || this.countDownPositions.size() <= 0)) {
            this.timerJob = createCountDownJob(new Function0<Unit>() { // from class: com.jingdong.pdj.libdjbasecore.utils.DjRVTimer$startTimer$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ArrayList arrayList;
                    arrayList = DjRVTimer.this.countDownPositions;
                    if (arrayList.size() != 0) {
                        DjRVTimer.this.countDownItem();
                    } else {
                        DjRVTimer.this.stopTimer();
                    }
                }
            });
        }
    }

    public final void stopTimer() {
        Job job;
        Job job2 = this.timerJob;
        if (job2 == null || !job2.isActive() || (job = this.timerJob) == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }

    @JvmOverloads
    public DjRVTimer(@Nullable Lifecycle lifecycle, @NotNull RecyclerView.Adapter<?> adapter, @Nullable Function1<? super Integer, Unit> function1) {
        this.lifecycle = lifecycle;
        this.rvAdapter = adapter;
        this.timeDownListener = function1;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        this.rvAdapter.registerAdapterDataObserver(this);
        this.countDownPositions = new ArrayList<>();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
        super.onItemRangeChanged(positionStart, itemCount, payload);
    }

    public final void removeCountDownPos(int pos) {
        this.countDownPositions.remove(new RvPos(pos, false, 2, null));
    }
}
