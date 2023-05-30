package .;

import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u0000 \u001c2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u001cB\u001a\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001a\u0010\r\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0096\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0017\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00f8\u0001\u0000\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0019\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00f8\u0001\u0000\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "value", "", "contains-VKZWuLQ", "(J)Z", "contains", CartConstant.KEY_GLOBAL_IS_EMPTY, "()Z", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "getEndInclusive", "()Lkotlin/ULong;", "endInclusive", "getStart", "start", "<init>", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* renamed from: kotlin.ranges.ULongRange  reason: from toString */
/* loaded from: classes11.dex */
public final class  extends ULongProgression implements ClosedRange<ULong> {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    private static final  EMPTY = new (-1, 0, null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lkotlin/ranges/ULongRange$Companion;", "", "Lkotlin/ranges/ULongRange;", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlin.ranges.ULongRange$Companion  reason: from kotlin metadata */
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final  getEMPTY() {
            return .EMPTY;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private (long j2, long j3) {
        super(j2, j3, 1L, null);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(ULong uLong) {
        return m1123containsVKZWuLQ(uLong.getData());
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m1123containsVKZWuLQ(long value) {
        return UnsignedKt.ulongCompare(getFirst(), value) <= 0 && UnsignedKt.ulongCompare(value, getLast()) <= 0;
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean equals(@Nullable Object other) {
        if (other instanceof ) {
            if (!isEmpty() || !(() other).isEmpty()) {
                 Var = () other;
                if (getFirst() != Var.getFirst() || getLast() != Var.getLast()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.ULongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return ((int) ULong.m354constructorimpl(getLast() ^ ULong.m354constructorimpl(getLast() >>> 32))) + (((int) ULong.m354constructorimpl(getFirst() ^ ULong.m354constructorimpl(getFirst() >>> 32))) * 31);
    }

    @Override // kotlin.ranges.ULongProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return UnsignedKt.ulongCompare(getFirst(), getLast()) > 0;
    }

    @Override // kotlin.ranges.ULongProgression
    @NotNull
    public String toString() {
        return ULong.m391toStringimpl(getFirst()) + ".." + ULong.m391toStringimpl(getLast());
    }

    public /* synthetic */ (long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3);
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public ULong getEndInclusive() {
        return ULong.m348boximpl(getLast());
    }

    @Override // kotlin.ranges.ClosedRange
    @NotNull
    public ULong getStart() {
        return ULong.m348boximpl(getFirst());
    }
}
