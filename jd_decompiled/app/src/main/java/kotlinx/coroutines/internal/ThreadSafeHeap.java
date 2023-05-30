package kotlinx.coroutines.internal;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.a.a;
import com.jingdong.jdsdk.constant.CartConstant;
import java.lang.Comparable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalCoroutinesApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0017\u0018\u0000*\u0012\b\u0000\u0010\u0003*\u00020\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0004j\u0002`\u0005B\u0007\u00a2\u0006\u0004\b2\u0010\u0013J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0082\u0010\u00a2\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0082\u0010\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\fH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\b\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0015J&\u0010\u001a\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00180\u0017H\u0086\b\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00028\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001eJ.\u0010 \u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00028\u00002\u0014\u0010\u001f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\u00180\u0017H\u0086\b\u00a2\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00028\u0000\u00a2\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u0004\u0018\u00018\u0000H\u0001\u00a2\u0006\u0004\b$\u0010\u0015J\u0017\u0010&\u001a\u00028\u00002\u0006\u0010%\u001a\u00020\u0006H\u0001\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00028\u0000H\u0001\u00a2\u0006\u0004\b(\u0010\u001eR \u0010)\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0013\u0010+\u001a\u00020\u00188F@\u0006\u00a2\u0006\u0006\u001a\u0004\b+\u0010,R$\u00101\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00068F@BX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b.\u0010/\"\u0004\b0\u0010\n\u00a8\u00063"}, d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "T", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "", "i", "", "siftUpFrom", "(I)V", "siftDownFrom", "", "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "j", "swap", "(II)V", "clear", "()V", "peek", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "Lkotlin/Function1;", "", "predicate", "removeFirstIf", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "node", "addLast", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "cond", "addLastIf", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function1;)Z", "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "firstImpl", "index", "removeAtImpl", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "addImpl", a.a, "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", CartConstant.KEY_GLOBAL_IS_EMPTY, "()Z", "value", "getSize", "()I", "setSize", ApkDownloadTable.FIELD_SIZE, "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {
    private volatile int _size = 0;
    private T[] a;

    private final T[] realloc() {
        T[] tArr = this.a;
        if (tArr == null) {
            T[] tArr2 = (T[]) new ThreadSafeHeapNode[4];
            this.a = tArr2;
            return tArr2;
        } else if (get_size() >= tArr.length) {
            Object[] copyOf = Arrays.copyOf(tArr, get_size() * 2);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            T[] tArr3 = (T[]) ((ThreadSafeHeapNode[]) copyOf);
            this.a = tArr3;
            return tArr3;
        } else {
            return tArr;
        }
    }

    private final void setSize(int i2) {
        this._size = i2;
    }

    private final void siftDownFrom(int i2) {
        while (true) {
            int i3 = (i2 * 2) + 1;
            if (i3 >= get_size()) {
                return;
            }
            T[] tArr = this.a;
            if (tArr == null) {
                Intrinsics.throwNpe();
            }
            int i4 = i3 + 1;
            if (i4 < get_size()) {
                T t = tArr[i4];
                if (t == null) {
                    Intrinsics.throwNpe();
                }
                Comparable comparable = (Comparable) t;
                T t2 = tArr[i3];
                if (t2 == null) {
                    Intrinsics.throwNpe();
                }
                if (comparable.compareTo(t2) < 0) {
                    i3 = i4;
                }
            }
            T t3 = tArr[i2];
            if (t3 == null) {
                Intrinsics.throwNpe();
            }
            Comparable comparable2 = (Comparable) t3;
            T t4 = tArr[i3];
            if (t4 == null) {
                Intrinsics.throwNpe();
            }
            if (comparable2.compareTo(t4) <= 0) {
                return;
            }
            swap(i2, i3);
            i2 = i3;
        }
    }

    private final void siftUpFrom(int i2) {
        while (i2 > 0) {
            T[] tArr = this.a;
            if (tArr == null) {
                Intrinsics.throwNpe();
            }
            int i3 = (i2 - 1) / 2;
            T t = tArr[i3];
            if (t == null) {
                Intrinsics.throwNpe();
            }
            Comparable comparable = (Comparable) t;
            T t2 = tArr[i2];
            if (t2 == null) {
                Intrinsics.throwNpe();
            }
            if (comparable.compareTo(t2) <= 0) {
                return;
            }
            swap(i2, i3);
            i2 = i3;
        }
    }

    private final void swap(int i2, int j2) {
        T[] tArr = this.a;
        if (tArr == null) {
            Intrinsics.throwNpe();
        }
        T t = tArr[j2];
        if (t == null) {
            Intrinsics.throwNpe();
        }
        T t2 = tArr[i2];
        if (t2 == null) {
            Intrinsics.throwNpe();
        }
        tArr[i2] = t;
        tArr[j2] = t2;
        t.setIndex(i2);
        t2.setIndex(j2);
    }

    @PublishedApi
    public final void addImpl(@NotNull T node) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(node.getHeap() == null)) {
                throw new AssertionError();
            }
        }
        node.setHeap(this);
        T[] realloc = realloc();
        int i2 = get_size();
        setSize(i2 + 1);
        realloc[i2] = node;
        node.setIndex(i2);
        siftUpFrom(i2);
    }

    public final void addLast(@NotNull T node) {
        synchronized (this) {
            addImpl(node);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean addLastIf(@NotNull T node, @NotNull Function1<? super T, Boolean> cond) {
        boolean z;
        synchronized (this) {
            try {
                if (cond.invoke(firstImpl()).booleanValue()) {
                    addImpl(node);
                    z = true;
                } else {
                    z = false;
                }
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return z;
    }

    public final void clear() {
        synchronized (this) {
            T[] tArr = this.a;
            if (tArr != null) {
                ArraysKt___ArraysJvmKt.fill$default(tArr, (Object) null, 0, 0, 6, (Object) null);
            }
            this._size = 0;
            Unit unit = Unit.INSTANCE;
        }
    }

    @PublishedApi
    @Nullable
    public final T firstImpl() {
        T[] tArr = this.a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    /* renamed from: getSize  reason: from getter */
    public final int get_size() {
        return this._size;
    }

    public final boolean isEmpty() {
        return get_size() == 0;
    }

    @Nullable
    public final T peek() {
        T firstImpl;
        synchronized (this) {
            firstImpl = firstImpl();
        }
        return firstImpl;
    }

    public final boolean remove(@NotNull T node) {
        boolean z;
        synchronized (this) {
            z = true;
            if (node.getHeap() == null) {
                z = false;
            } else {
                int index = node.getIndex();
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(index >= 0)) {
                        throw new AssertionError();
                    }
                }
                removeAtImpl(index);
            }
        }
        return z;
    }

    @PublishedApi
    @NotNull
    public final T removeAtImpl(int index) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(get_size() > 0)) {
                throw new AssertionError();
            }
        }
        T[] tArr = this.a;
        if (tArr == null) {
            Intrinsics.throwNpe();
        }
        setSize(get_size() - 1);
        if (index < get_size()) {
            swap(index, get_size());
            int i2 = (index - 1) / 2;
            if (index > 0) {
                T t = tArr[index];
                if (t == null) {
                    Intrinsics.throwNpe();
                }
                Comparable comparable = (Comparable) t;
                T t2 = tArr[i2];
                if (t2 == null) {
                    Intrinsics.throwNpe();
                }
                if (comparable.compareTo(t2) < 0) {
                    swap(index, i2);
                    siftUpFrom(i2);
                }
            }
            siftDownFrom(index);
        }
        T t3 = tArr[get_size()];
        if (t3 == null) {
            Intrinsics.throwNpe();
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(t3.getHeap() == this)) {
                throw new AssertionError();
            }
        }
        t3.setHeap(null);
        t3.setIndex(-1);
        tArr[get_size()] = null;
        return t3;
    }

    @Nullable
    public final T removeFirstIf(@NotNull Function1<? super T, Boolean> predicate) {
        synchronized (this) {
            try {
                T firstImpl = firstImpl();
                if (firstImpl != null) {
                    T removeAtImpl = predicate.invoke(firstImpl).booleanValue() ? removeAtImpl(0) : null;
                    InlineMarker.finallyStart(1);
                    InlineMarker.finallyEnd(1);
                    return removeAtImpl;
                }
                InlineMarker.finallyStart(2);
                InlineMarker.finallyEnd(2);
                return null;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
    }

    @Nullable
    public final T removeFirstOrNull() {
        T removeAtImpl;
        synchronized (this) {
            removeAtImpl = get_size() > 0 ? removeAtImpl(0) : null;
        }
        return removeAtImpl;
    }
}
