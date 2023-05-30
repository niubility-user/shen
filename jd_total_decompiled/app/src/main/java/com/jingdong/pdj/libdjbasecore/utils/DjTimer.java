package com.jingdong.pdj.libdjbasecore.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 $2\u00020\u0001:\u0001$B\u0015\b\u0007\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0004J\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u0004J\u001d\u0010\u000e\u001a\u00020\r2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J\u000f\u0010\u0011\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0004J\u000f\u0010\u0012\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0004R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R&\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0016j\b\u0012\u0004\u0012\u00020\u0005`\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006%"}, d2 = {"Lcom/jingdong/pdj/libdjbasecore/utils/DjTimer;", "Landroidx/lifecycle/LifecycleObserver;", "", "countDownItem", "()V", "Lcom/jingdong/pdj/libdjbasecore/utils/DownData;", "data", "addTask", "(Lcom/jingdong/pdj/libdjbasecore/utils/DownData;)V", "startTimer", "stopTimer", "Lkotlin/Function0;", "onEach", "Lkotlinx/coroutines/Job;", "createCountDownJob", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/Job;", "onStart", "onStop", "onDestroy", "", "count", "I", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "allDownData", "Ljava/util/ArrayList;", "", "mTag", "Ljava/lang/String;", "timerJob", "Lkotlinx/coroutines/Job;", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "<init>", "(Landroidx/lifecycle/Lifecycle;)V", "Companion", "libDJBaseCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class DjTimer implements LifecycleObserver {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static HashMap<String, DjTimer> timerMap = new HashMap<>();
    private ArrayList<DownData> allDownData;
    private int count;
    private Lifecycle lifecycle;
    private String mTag;
    private Job timerJob;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J#\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR2\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\fj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006`\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/pdj/libdjbasecore/utils/DjTimer$Companion;", "", "", "tag", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "Lcom/jingdong/pdj/libdjbasecore/utils/DjTimer;", "getTimerByTag", "(Ljava/lang/String;Landroidx/lifecycle/Lifecycle;)Lcom/jingdong/pdj/libdjbasecore/utils/DjTimer;", "", "deleteTimerByTag", "(Ljava/lang/String;)V", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "timerMap", "Ljava/util/HashMap;", "<init>", "()V", "libDJBaseCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ DjTimer getTimerByTag$default(Companion companion, String str, Lifecycle lifecycle, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                lifecycle = null;
            }
            return companion.getTimerByTag(str, lifecycle);
        }

        public final void deleteTimerByTag(@NotNull String tag) {
            DjTimer.timerMap.remove(tag);
        }

        @JvmOverloads
        @NotNull
        public final DjTimer getTimerByTag(@NotNull String str) {
            return getTimerByTag$default(this, str, null, 2, null);
        }

        @JvmOverloads
        @NotNull
        public final DjTimer getTimerByTag(@NotNull String tag, @Nullable Lifecycle lifecycle) {
            DjTimer djTimer = (DjTimer) DjTimer.timerMap.get(tag);
            if (djTimer != null) {
                if (lifecycle != null) {
                    lifecycle.addObserver(djTimer);
                }
                if (djTimer != null) {
                    return djTimer;
                }
            }
            DjTimer djTimer2 = new DjTimer(lifecycle);
            DjTimer.timerMap.put(tag, djTimer2);
            djTimer2.mTag = tag;
            return djTimer2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.lifecycle.Lifecycle, kotlin.jvm.internal.DefaultConstructorMarker] */
    @JvmOverloads
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DjTimer() {
        this(r0, 1, r0);
        ?? r0 = 0;
    }

    @JvmOverloads
    public DjTimer(@Nullable Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        this.mTag = "" + hashCode();
        this.allDownData = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void countDownItem() {
        for (int size = this.allDownData.size() - 1; size >= 0; size--) {
            DownData downData = this.allDownData.get(size);
            Intrinsics.checkExpressionValueIsNotNull(downData, "allDownData[i]");
            DownData downData2 = downData;
            if (!downData2.getEnableState()) {
                this.allDownData.remove(downData2);
            } else if (downData2.getRemainTime() > 0) {
                downData2.getOnTick().invoke(Long.valueOf(downData2.getRemainTime()));
            } else {
                downData2.getOnFinish().invoke();
                this.allDownData.remove(downData2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Job createCountDownJob$default(DjTimer djTimer, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.jingdong.pdj.libdjbasecore.utils.DjTimer$createCountDownJob$1
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
        return djTimer.createCountDownJob(function0);
    }

    public final void addTask(@Nullable DownData data) {
        if (data != null) {
            if (!this.allDownData.contains(data)) {
                this.allDownData.add(data);
            }
            startTimer();
        }
    }

    @NotNull
    public final Job createCountDownJob(@NotNull Function0<Unit> onEach) {
        return FlowKt.launchIn(FlowKt.flowOn(FlowKt.onEach(FlowKt.flowOn(FlowKt.flow(new DjTimer$createCountDownJob$2(this, null)), Dispatchers.getDefault()), new DjTimer$createCountDownJob$3(onEach, null)), Dispatchers.getMain()), GlobalScope.INSTANCE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy() {
        this.allDownData.clear();
        stopTimer();
        this.timerJob = null;
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
        timerMap.remove(this.mTag);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void onStart() {
        startTimer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onStop() {
        stopTimer();
    }

    public final void startTimer() {
        Job job = this.timerJob;
        if (job == null || !(job == null || job.isActive() || this.allDownData.size() <= 0)) {
            this.timerJob = createCountDownJob(new Function0<Unit>() { // from class: com.jingdong.pdj.libdjbasecore.utils.DjTimer$startTimer$1
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
                    arrayList = DjTimer.this.allDownData;
                    if (arrayList.size() != 0) {
                        DjTimer.this.countDownItem();
                    } else {
                        DjTimer.this.stopTimer();
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

    public /* synthetic */ DjTimer(Lifecycle lifecycle, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : lifecycle);
    }
}
