package kotlin.internal;

import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a*\u0010\u000f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u0005\u001a*\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lkotlin/UInt;", a.a, "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "differenceModulo", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "start", "end", "", "step", "getProgressionLastElement-Nkh28Cs", "getProgressionLastElement", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA */
    private static final int m1103differenceModuloWZ9TVnA(int i2, int i3, int i4) {
        int m512uintRemainderJ1ME1BU = UnsignedKt.m512uintRemainderJ1ME1BU(i2, i4);
        int m512uintRemainderJ1ME1BU2 = UnsignedKt.m512uintRemainderJ1ME1BU(i3, i4);
        int uintCompare = UnsignedKt.uintCompare(m512uintRemainderJ1ME1BU, m512uintRemainderJ1ME1BU2);
        int m285constructorimpl = UInt.m285constructorimpl(m512uintRemainderJ1ME1BU - m512uintRemainderJ1ME1BU2);
        return uintCompare >= 0 ? m285constructorimpl : UInt.m285constructorimpl(m285constructorimpl + i4);
    }

    /* renamed from: differenceModulo-sambcqE */
    private static final long m1104differenceModulosambcqE(long j2, long j3, long j4) {
        long m514ulongRemaindereb3DHEI = UnsignedKt.m514ulongRemaindereb3DHEI(j2, j4);
        long m514ulongRemaindereb3DHEI2 = UnsignedKt.m514ulongRemaindereb3DHEI(j3, j4);
        int ulongCompare = UnsignedKt.ulongCompare(m514ulongRemaindereb3DHEI, m514ulongRemaindereb3DHEI2);
        long m354constructorimpl = ULong.m354constructorimpl(m514ulongRemaindereb3DHEI - m514ulongRemaindereb3DHEI2);
        return ulongCompare >= 0 ? m354constructorimpl : ULong.m354constructorimpl(m354constructorimpl + j4);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-7ftBX0g */
    public static final long m1105getProgressionLastElement7ftBX0g(long j2, long j3, long j4) {
        if (j4 > 0) {
            return UnsignedKt.ulongCompare(j2, j3) >= 0 ? j3 : ULong.m354constructorimpl(j3 - m1104differenceModulosambcqE(j3, j2, ULong.m354constructorimpl(j4)));
        } else if (j4 < 0) {
            return UnsignedKt.ulongCompare(j2, j3) <= 0 ? j3 : ULong.m354constructorimpl(j3 + m1104differenceModulosambcqE(j2, j3, ULong.m354constructorimpl(-j4)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-Nkh28Cs */
    public static final int m1106getProgressionLastElementNkh28Cs(int i2, int i3, int i4) {
        if (i4 > 0) {
            return UnsignedKt.uintCompare(i2, i3) >= 0 ? i3 : UInt.m285constructorimpl(i3 - m1103differenceModuloWZ9TVnA(i3, i2, UInt.m285constructorimpl(i4)));
        } else if (i4 < 0) {
            return UnsignedKt.uintCompare(i2, i3) <= 0 ? i3 : UInt.m285constructorimpl(i3 + m1103differenceModuloWZ9TVnA(i2, i3, UInt.m285constructorimpl(-i4)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}
