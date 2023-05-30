package kotlinx.coroutines.stream;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u00a2\u0006\u0004\b\u000b\u0010\fJ!\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0097@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/util/stream/Stream;", "stream", "Ljava/util/stream/Stream;", "<init>", "(Ljava/util/stream/Stream;)V", "kotlinx-coroutines-jdk8"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class StreamFlow<T> implements Flow<T> {
    private static final AtomicIntegerFieldUpdater consumed$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed");
    private volatile int consumed = 0;
    private final Stream<T> stream;

    public StreamFlow(@NotNull Stream<T> stream) {
        this.stream = stream;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e A[Catch: all -> 0x0038, TRY_LEAVE, TryCatch #0 {all -> 0x0038, blocks: (B:12:0x0033, B:22:0x0058, B:24:0x005e), top: B:36:0x0033 }] */
    @Override // kotlinx.coroutines.flow.Flow
    @InternalCoroutinesApi
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        StreamFlow$collect$1 streamFlow$collect$1;
        Object coroutine_suspended;
        int i2;
        StreamFlow<T> streamFlow;
        FlowCollector<? super T> flowCollector2;
        Iterator<T> it;
        if (continuation instanceof StreamFlow$collect$1) {
            streamFlow$collect$1 = (StreamFlow$collect$1) continuation;
            int i3 = streamFlow$collect$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                streamFlow$collect$1.label = i3 - Integer.MIN_VALUE;
                Object obj = streamFlow$collect$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = streamFlow$collect$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (consumed$FU.compareAndSet(this, 0, 1)) {
                        try {
                            streamFlow = this;
                            flowCollector2 = flowCollector;
                            it = this.stream.iterator();
                        } catch (Throwable th) {
                            th = th;
                            streamFlow = this;
                            streamFlow.stream.close();
                            throw th;
                        }
                    } else {
                        throw new IllegalStateException("Stream.consumeAsFlow can be collected only once".toString());
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    it = (Iterator) streamFlow$collect$1.L$3;
                    Object obj2 = streamFlow$collect$1.L$2;
                    FlowCollector<? super T> flowCollector3 = (FlowCollector) streamFlow$collect$1.L$1;
                    streamFlow = (StreamFlow) streamFlow$collect$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        flowCollector2 = flowCollector3;
                    } catch (Throwable th2) {
                        th = th2;
                        streamFlow.stream.close();
                        throw th;
                    }
                }
                while (it.hasNext()) {
                    Object obj3 = (T) it.next();
                    streamFlow$collect$1.L$0 = streamFlow;
                    streamFlow$collect$1.L$1 = flowCollector2;
                    streamFlow$collect$1.L$2 = obj3;
                    streamFlow$collect$1.L$3 = it;
                    streamFlow$collect$1.label = 1;
                    if (flowCollector2.emit(obj3, streamFlow$collect$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                streamFlow.stream.close();
                return Unit.INSTANCE;
            }
        }
        streamFlow$collect$1 = new StreamFlow$collect$1(this, continuation);
        Object obj4 = streamFlow$collect$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = streamFlow$collect$1.label;
        if (i2 != 0) {
        }
        while (it.hasNext()) {
        }
        streamFlow.stream.close();
        return Unit.INSTANCE;
    }
}
