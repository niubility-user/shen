package kotlin.internal;

import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import kotlin.PublishedApi;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\n\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001f\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0006\u001a'\u0010\b\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\b\b\u0010\t\u001a'\u0010\b\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\b\u0010\n\u001a'\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0000H\u0001\u00a2\u0006\u0004\b\u000e\u0010\t\u001a'\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0001\u00a2\u0006\u0004\b\u000e\u0010\n\u00a8\u0006\u000f"}, d2 = {"", a.a, "b", "mod", "(II)I", "", "(JJ)J", "c", "differenceModulo", "(III)I", "(JJJ)J", "start", "end", "step", "getProgressionLastElement", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ProgressionUtilKt {
    private static final int differenceModulo(int i2, int i3, int i4) {
        return mod(mod(i2, i4) - mod(i3, i4), i4);
    }

    @PublishedApi
    public static final int getProgressionLastElement(int i2, int i3, int i4) {
        if (i4 > 0) {
            return i2 >= i3 ? i3 : i3 - differenceModulo(i3, i2, i4);
        } else if (i4 < 0) {
            return i2 <= i3 ? i3 : i3 + differenceModulo(i2, i3, -i4);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    private static final int mod(int i2, int i3) {
        int i4 = i2 % i3;
        return i4 >= 0 ? i4 : i4 + i3;
    }

    private static final long differenceModulo(long j2, long j3, long j4) {
        return mod(mod(j2, j4) - mod(j3, j4), j4);
    }

    private static final long mod(long j2, long j3) {
        long j4 = j2 % j3;
        return j4 >= 0 ? j4 : j4 + j3;
    }

    @PublishedApi
    public static final long getProgressionLastElement(long j2, long j3, long j4) {
        if (j4 > 0) {
            return j2 >= j3 ? j3 : j3 - differenceModulo(j3, j2, j4);
        } else if (j4 < 0) {
            return j2 <= j3 ? j3 : j3 + differenceModulo(j2, j3, -j4);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}
