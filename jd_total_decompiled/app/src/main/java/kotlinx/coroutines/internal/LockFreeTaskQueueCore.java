package kotlinx.coroutines.internal;

import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0011\b\u0000\u0018\u0000 /*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002/0B\u0017\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010$\u001a\u00020\u0014\u00a2\u0006\u0004\b-\u0010.J3\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0000H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ3\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00062\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u00062\u0006\u0010\u0010\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u00062\u0006\u0010\u0010\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0012J\r\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001cJ-\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010 \"\u0004\b\u0001\u0010\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001e\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0014\u00a2\u0006\u0004\b#\u0010\u0016R\u0016\u0010$\u001a\u00020\u00148\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0013\u0010&\u001a\u00020\u00148F@\u0006\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u0016R\u0016\u0010'\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0013\u0010,\u001a\u00020\u00038F@\u0006\u00a2\u0006\u0006\u001a\u0004\b*\u0010+\u00a8\u00061"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "", "E", "", "index", "element", "Lkotlinx/coroutines/internal/Core;", "fillPlaceholder", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "oldHead", "newHead", "removeSlowPath", "(II)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "", "markFrozen", "()J", XView2Constants.STATE, "allocateOrGetNextCopy", "(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "allocateNextCopy", "", "close", "()Z", "addLast", "(Ljava/lang/Object;)I", "removeFirstOrNull", "()Ljava/lang/Object;", "next", "()Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "R", "Lkotlin/Function1;", "transform", "", "map", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "isClosed", "singleConsumer", "Z", CartConstant.KEY_GLOBAL_IS_EMPTY, "mask", "I", "capacity", "getSize", "()I", ApkDownloadTable.FIELD_SIZE, "<init>", "(IZ)V", "Companion", "Placeholder", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class LockFreeTaskQueueCore<E> {
    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    public static final int CAPACITY_BITS = 30;
    public static final long CLOSED_MASK = 2305843009213693952L;
    public static final int CLOSED_SHIFT = 61;
    public static final long FROZEN_MASK = 1152921504606846976L;
    public static final int FROZEN_SHIFT = 60;
    public static final long HEAD_MASK = 1073741823;
    public static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    public static final int MAX_CAPACITY_MASK = 1073741823;
    public static final int MIN_ADD_SPIN_CAPACITY = 1024;
    public static final long TAIL_MASK = 1152921503533105152L;
    public static final int TAIL_SHIFT = 30;
    private volatile Object _next = null;
    private volatile long _state = 0;
    private AtomicReferenceArray array;
    private final int capacity;
    private final int mask;
    private final boolean singleConsumer;

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @JvmField
    @NotNull
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");
    private static final AtomicLongFieldUpdater _state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b+\u0010,J\u001c\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0086\u0004\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000b\u0010\tJR\u0010\u0013\u001a\u00028\u0001\"\u0004\b\u0001\u0010\f*\u00020\u000226\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00028\u00010\rH\u0086\b\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0006*\u00020\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u0016\u0010\u001b\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0018R\u0016\u0010\u001c\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0018R\u0016\u0010\u001f\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0016\u0010 \u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b \u0010\u0018R\u0016\u0010!\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001dR\u0016\u0010\"\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0018R\u0016\u0010#\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b#\u0010\u0018R\u0016\u0010$\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010\u0018R\u0016\u0010%\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010\u0018R\u0016\u0010'\u001a\u00020&8\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b)\u0010\u001dR\u0016\u0010*\u001a\u00020\u00068\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b*\u0010\u0018\u00a8\u0006-"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;", "", "", "other", "wo", "(JJ)J", "", "newHead", "updateHead", "(JI)J", "newTail", "updateTail", "T", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", DataCompassUtils.MODULE_TYPE_HEAD, "tail", JDReactConstant.BLOCK, "withState", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "addFailReason", "(J)I", "ADD_CLOSED", "I", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "J", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "MIN_ADD_SPIN_CAPACITY", "Lkotlinx/coroutines/internal/Symbol;", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "TAIL_MASK", "TAIL_SHIFT", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public final int addFailReason(long j2) {
            return (j2 & LockFreeTaskQueueCore.CLOSED_MASK) != 0 ? 2 : 1;
        }

        public final long updateHead(long j2, int i2) {
            return wo(j2, LockFreeTaskQueueCore.HEAD_MASK) | (i2 << 0);
        }

        public final long updateTail(long j2, int i2) {
            return wo(j2, LockFreeTaskQueueCore.TAIL_MASK) | (i2 << 30);
        }

        public final <T> T withState(long j2, @NotNull Function2<? super Integer, ? super Integer, ? extends T> function2) {
            return function2.invoke(Integer.valueOf((int) ((LockFreeTaskQueueCore.HEAD_MASK & j2) >> 0)), Integer.valueOf((int) ((j2 & LockFreeTaskQueueCore.TAIL_MASK) >> 30)));
        }

        public final long wo(long j2, long j3) {
            return j2 & (j3 ^ (-1));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;", "", "", "index", "I", "<init>", "(I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Placeholder {
        @JvmField
        public final int index;

        public Placeholder(int i2) {
            this.index = i2;
        }
    }

    public LockFreeTaskQueueCore(int i2, boolean z) {
        this.capacity = i2;
        this.singleConsumer = z;
        int i3 = i2 - 1;
        this.mask = i3;
        this.array = new AtomicReferenceArray(i2);
        if (!(i3 <= 1073741823)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!((i2 & i3) == 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final LockFreeTaskQueueCore<E> allocateNextCopy(long r7) {
        LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = new LockFreeTaskQueueCore<>(this.capacity * 2, this.singleConsumer);
        int i2 = (int) ((HEAD_MASK & r7) >> 0);
        int i3 = (int) ((TAIL_MASK & r7) >> 30);
        while (true) {
            int i4 = this.mask;
            if ((i2 & i4) != (i3 & i4)) {
                Object obj = this.array.get(i4 & i2);
                if (obj == null) {
                    obj = new Placeholder(i2);
                }
                lockFreeTaskQueueCore.array.set(lockFreeTaskQueueCore.mask & i2, obj);
                i2++;
            } else {
                lockFreeTaskQueueCore._state = INSTANCE.wo(r7, 1152921504606846976L);
                return lockFreeTaskQueueCore;
            }
        }
    }

    private final LockFreeTaskQueueCore<E> allocateOrGetNextCopy(long r4) {
        while (true) {
            LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._next;
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            _next$FU.compareAndSet(this, null, allocateNextCopy(r4));
        }
    }

    private final LockFreeTaskQueueCore<E> fillPlaceholder(int index, E element) {
        Object obj = this.array.get(this.mask & index);
        if ((obj instanceof Placeholder) && ((Placeholder) obj).index == index) {
            this.array.set(index & this.mask, element);
            return this;
        }
        return null;
    }

    private final long markFrozen() {
        long j2;
        long j3;
        do {
            j2 = this._state;
            if ((j2 & 1152921504606846976L) != 0) {
                return j2;
            }
            j3 = j2 | 1152921504606846976L;
        } while (!_state$FU.compareAndSet(this, j2, j3));
        return j3;
    }

    private final LockFreeTaskQueueCore<E> removeSlowPath(int oldHead, int newHead) {
        long j2;
        Companion companion;
        int i2;
        do {
            j2 = this._state;
            companion = INSTANCE;
            i2 = (int) ((HEAD_MASK & j2) >> 0);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(i2 == oldHead)) {
                    throw new AssertionError();
                }
            }
            if ((1152921504606846976L & j2) != 0) {
                return next();
            }
        } while (!_state$FU.compareAndSet(this, j2, companion.updateHead(j2, newHead)));
        this.array.set(this.mask & i2, null);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x004e, code lost:
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int addLast(@NotNull E element) {
        while (true) {
            long j2 = this._state;
            if ((3458764513820540928L & j2) != 0) {
                return INSTANCE.addFailReason(j2);
            }
            Companion companion = INSTANCE;
            int i2 = (int) ((HEAD_MASK & j2) >> 0);
            int i3 = (int) ((TAIL_MASK & j2) >> 30);
            int i4 = this.mask;
            if (((i3 + 2) & i4) == (i2 & i4)) {
                return 1;
            }
            if (!this.singleConsumer && this.array.get(i3 & i4) != null) {
                int i5 = this.capacity;
                if (i5 < 1024 || ((i3 - i2) & MAX_CAPACITY_MASK) > (i5 >> 1)) {
                    break;
                }
            } else if (_state$FU.compareAndSet(this, j2, companion.updateTail(j2, (i3 + 1) & MAX_CAPACITY_MASK))) {
                this.array.set(i3 & i4, element);
                LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = this;
                while ((lockFreeTaskQueueCore._state & 1152921504606846976L) != 0 && (lockFreeTaskQueueCore = lockFreeTaskQueueCore.next().fillPlaceholder(i3, element)) != null) {
                }
                return 0;
            }
        }
    }

    public final boolean close() {
        long j2;
        do {
            j2 = this._state;
            if ((j2 & CLOSED_MASK) != 0) {
                return true;
            }
            if ((1152921504606846976L & j2) != 0) {
                return false;
            }
        } while (!_state$FU.compareAndSet(this, j2, j2 | CLOSED_MASK));
        return true;
    }

    public final int getSize() {
        long j2 = this._state;
        return 1073741823 & (((int) ((j2 & TAIL_MASK) >> 30)) - ((int) ((HEAD_MASK & j2) >> 0)));
    }

    public final boolean isClosed() {
        return (this._state & CLOSED_MASK) != 0;
    }

    public final boolean isEmpty() {
        long j2 = this._state;
        return ((int) ((HEAD_MASK & j2) >> 0)) == ((int) ((j2 & TAIL_MASK) >> 30));
    }

    @NotNull
    public final <R> List<R> map(@NotNull Function1<? super E, ? extends R> transform) {
        ArrayList arrayList = new ArrayList(this.capacity);
        long j2 = this._state;
        int i2 = (int) ((HEAD_MASK & j2) >> 0);
        int i3 = (int) ((j2 & TAIL_MASK) >> 30);
        while (true) {
            int i4 = this.mask;
            if ((i2 & i4) == (i3 & i4)) {
                return arrayList;
            }
            Object obj = (Object) this.array.get(i4 & i2);
            if (obj != 0 && !(obj instanceof Placeholder)) {
                arrayList.add(transform.invoke(obj));
            }
            i2++;
        }
    }

    @NotNull
    public final LockFreeTaskQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    @Nullable
    public final Object removeFirstOrNull() {
        while (true) {
            long j2 = this._state;
            if ((1152921504606846976L & j2) != 0) {
                return REMOVE_FROZEN;
            }
            Companion companion = INSTANCE;
            int i2 = (int) ((HEAD_MASK & j2) >> 0);
            int i3 = (int) ((TAIL_MASK & j2) >> 30);
            int i4 = this.mask;
            if ((i3 & i4) == (i2 & i4)) {
                return null;
            }
            Object obj = this.array.get(i4 & i2);
            if (obj == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else if (obj instanceof Placeholder) {
                return null;
            } else {
                int i5 = (i2 + 1) & MAX_CAPACITY_MASK;
                if (_state$FU.compareAndSet(this, j2, companion.updateHead(j2, i5))) {
                    this.array.set(this.mask & i2, null);
                    return obj;
                } else if (this.singleConsumer) {
                    LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = this;
                    do {
                        lockFreeTaskQueueCore = lockFreeTaskQueueCore.removeSlowPath(i2, i5);
                    } while (lockFreeTaskQueueCore != null);
                    return obj;
                }
            }
        }
    }
}
