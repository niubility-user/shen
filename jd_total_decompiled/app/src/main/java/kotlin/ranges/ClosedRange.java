package kotlin.ranges;

import com.jingdong.jdsdk.constant.CartConstant;
import java.lang.Comparable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\bf\u0018\u0000*\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0003J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\f\u001a\u00028\u00008&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u000e\u001a\u00028\u00008&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000b\u00a8\u0006\u000f"}, d2 = {"Lkotlin/ranges/ClosedRange;", "", "T", "", "value", "", "contains", "(Ljava/lang/Comparable;)Z", CartConstant.KEY_GLOBAL_IS_EMPTY, "()Z", "getEndInclusive", "()Ljava/lang/Comparable;", "endInclusive", "getStart", "start", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface ClosedRange<T extends Comparable<? super T>> {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class DefaultImpls {
        public static <T extends Comparable<? super T>> boolean contains(ClosedRange<T> closedRange, @NotNull T t) {
            return t.compareTo(closedRange.getStart()) >= 0 && t.compareTo(closedRange.getEndInclusive()) <= 0;
        }

        public static <T extends Comparable<? super T>> boolean isEmpty(ClosedRange<T> closedRange) {
            return closedRange.getStart().compareTo(closedRange.getEndInclusive()) > 0;
        }
    }

    boolean contains(@NotNull T value);

    @NotNull
    T getEndInclusive();

    @NotNull
    T getStart();

    boolean isEmpty();
}
