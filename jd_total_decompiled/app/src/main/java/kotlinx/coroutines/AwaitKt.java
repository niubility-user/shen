package kotlinx.coroutines;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a?\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001\"\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\b\u001a'\u0010\f\u001a\u00020\u000b2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0001\"\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a\u001d\u0010\f\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\t0\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"T", "", "Lkotlinx/coroutines/Deferred;", "deferreds", "", "awaitAll", "([Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/Job;", "jobs", "", "joinAll", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class AwaitKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object awaitAll(@NotNull Deferred<? extends T>[] deferredArr, @NotNull Continuation<? super List<? extends T>> continuation) {
        AwaitKt$awaitAll$1 awaitKt$awaitAll$1;
        Object coroutine_suspended;
        int i2;
        List emptyList;
        if (continuation instanceof AwaitKt$awaitAll$1) {
            awaitKt$awaitAll$1 = (AwaitKt$awaitAll$1) continuation;
            int i3 = awaitKt$awaitAll$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                awaitKt$awaitAll$1.label = i3 - Integer.MIN_VALUE;
                Object obj = awaitKt$awaitAll$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = awaitKt$awaitAll$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (deferredArr.length == 0) {
                        emptyList = CollectionsKt__CollectionsKt.emptyList();
                        return emptyList;
                    }
                    AwaitAll awaitAll = new AwaitAll(deferredArr);
                    awaitKt$awaitAll$1.L$0 = deferredArr;
                    awaitKt$awaitAll$1.label = 1;
                    obj = awaitAll.await(awaitKt$awaitAll$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Deferred[] deferredArr2 = (Deferred[]) awaitKt$awaitAll$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return (List) obj;
            }
        }
        awaitKt$awaitAll$1 = new AwaitKt$awaitAll$1(continuation);
        Object obj2 = awaitKt$awaitAll$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = awaitKt$awaitAll$1.label;
        if (i2 != 0) {
        }
        return (List) obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0078  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0073 -> B:19:0x0076). Please submit an issue!!! */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object joinAll(@NotNull Job[] jobArr, @NotNull Continuation<? super Unit> continuation) {
        AwaitKt$joinAll$1 awaitKt$joinAll$1;
        Object coroutine_suspended;
        int i2;
        int length;
        AwaitKt$joinAll$1 awaitKt$joinAll$12;
        Object obj;
        Job[] jobArr2;
        Job[] jobArr3;
        Job[] jobArr4;
        int i3;
        if (continuation instanceof AwaitKt$joinAll$1) {
            awaitKt$joinAll$1 = (AwaitKt$joinAll$1) continuation;
            int i4 = awaitKt$joinAll$1.label;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                awaitKt$joinAll$1.label = i4 - Integer.MIN_VALUE;
                Object obj2 = awaitKt$joinAll$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = awaitKt$joinAll$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj2);
                    length = jobArr.length;
                    awaitKt$joinAll$12 = awaitKt$joinAll$1;
                    obj = coroutine_suspended;
                    jobArr2 = jobArr;
                    jobArr3 = jobArr2;
                    jobArr4 = jobArr3;
                    i3 = 0;
                    if (i3 < length) {
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Job job = (Job) awaitKt$joinAll$1.L$4;
                    Job job2 = (Job) awaitKt$joinAll$1.L$3;
                    i3 = awaitKt$joinAll$1.I$1;
                    int i5 = awaitKt$joinAll$1.I$0;
                    ResultKt.throwOnFailure(obj2);
                    jobArr2 = (Job[]) awaitKt$joinAll$1.L$2;
                    obj = coroutine_suspended;
                    jobArr4 = (Job[]) awaitKt$joinAll$1.L$1;
                    length = i5;
                    awaitKt$joinAll$12 = awaitKt$joinAll$1;
                    jobArr3 = (Job[]) awaitKt$joinAll$1.L$0;
                    i3++;
                    if (i3 < length) {
                        Job job3 = jobArr2[i3];
                        awaitKt$joinAll$12.L$0 = jobArr3;
                        awaitKt$joinAll$12.L$1 = jobArr4;
                        awaitKt$joinAll$12.L$2 = jobArr2;
                        awaitKt$joinAll$12.I$0 = length;
                        awaitKt$joinAll$12.I$1 = i3;
                        awaitKt$joinAll$12.L$3 = job3;
                        awaitKt$joinAll$12.L$4 = job3;
                        awaitKt$joinAll$12.label = 1;
                        if (job3.join(awaitKt$joinAll$12) == obj) {
                            return obj;
                        }
                        i3++;
                        if (i3 < length) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        awaitKt$joinAll$1 = new AwaitKt$joinAll$1(continuation);
        Object obj22 = awaitKt$joinAll$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = awaitKt$joinAll$1.label;
        if (i2 != 0) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object awaitAll(@NotNull Collection<? extends Deferred<? extends T>> collection, @NotNull Continuation<? super List<? extends T>> continuation) {
        AwaitKt$awaitAll$2 awaitKt$awaitAll$2;
        Object coroutine_suspended;
        int i2;
        List emptyList;
        if (continuation instanceof AwaitKt$awaitAll$2) {
            awaitKt$awaitAll$2 = (AwaitKt$awaitAll$2) continuation;
            int i3 = awaitKt$awaitAll$2.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                awaitKt$awaitAll$2.label = i3 - Integer.MIN_VALUE;
                Object obj = awaitKt$awaitAll$2.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = awaitKt$awaitAll$2.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (collection.isEmpty()) {
                        emptyList = CollectionsKt__CollectionsKt.emptyList();
                        return emptyList;
                    }
                    Object[] array = collection.toArray(new Deferred[0]);
                    if (array != null) {
                        AwaitAll awaitAll = new AwaitAll((Deferred[]) array);
                        awaitKt$awaitAll$2.L$0 = collection;
                        awaitKt$awaitAll$2.label = 1;
                        obj = awaitAll.await(awaitKt$awaitAll$2);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Collection collection2 = (Collection) awaitKt$awaitAll$2.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return (List) obj;
            }
        }
        awaitKt$awaitAll$2 = new AwaitKt$awaitAll$2(continuation);
        Object obj2 = awaitKt$awaitAll$2.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = awaitKt$awaitAll$2.label;
        if (i2 != 0) {
        }
        return (List) obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005a  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object joinAll(@NotNull Collection<? extends Job> collection, @NotNull Continuation<? super Unit> continuation) {
        AwaitKt$joinAll$3 awaitKt$joinAll$3;
        Object coroutine_suspended;
        int i2;
        Object obj;
        AwaitKt$joinAll$3 awaitKt$joinAll$32;
        Object obj2;
        Iterator it;
        Object obj3;
        if (continuation instanceof AwaitKt$joinAll$3) {
            awaitKt$joinAll$3 = (AwaitKt$joinAll$3) continuation;
            int i3 = awaitKt$joinAll$3.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                awaitKt$joinAll$3.label = i3 - Integer.MIN_VALUE;
                Object obj4 = awaitKt$joinAll$3.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = awaitKt$joinAll$3.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj4);
                    obj = coroutine_suspended;
                    awaitKt$joinAll$32 = awaitKt$joinAll$3;
                    obj2 = collection;
                    it = collection.iterator();
                    obj3 = obj2;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Job job = (Job) awaitKt$joinAll$3.L$4;
                    Object obj5 = awaitKt$joinAll$3.L$3;
                    it = (Iterator) awaitKt$joinAll$3.L$2;
                    ResultKt.throwOnFailure(obj4);
                    obj3 = (Collection) awaitKt$joinAll$3.L$0;
                    awaitKt$joinAll$32 = awaitKt$joinAll$3;
                    obj2 = (Iterable) awaitKt$joinAll$3.L$1;
                    obj = coroutine_suspended;
                }
                while (it.hasNext()) {
                    Object next = it.next();
                    Job job2 = (Job) next;
                    awaitKt$joinAll$32.L$0 = obj3;
                    awaitKt$joinAll$32.L$1 = obj2;
                    awaitKt$joinAll$32.L$2 = it;
                    awaitKt$joinAll$32.L$3 = next;
                    awaitKt$joinAll$32.L$4 = job2;
                    awaitKt$joinAll$32.label = 1;
                    if (job2.join(awaitKt$joinAll$32) == obj) {
                        return obj;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        awaitKt$joinAll$3 = new AwaitKt$joinAll$3(continuation);
        Object obj42 = awaitKt$joinAll$3.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = awaitKt$joinAll$3.label;
        if (i2 != 0) {
        }
        while (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }
}
