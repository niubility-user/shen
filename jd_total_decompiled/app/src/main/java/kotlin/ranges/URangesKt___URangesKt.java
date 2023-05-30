package kotlin.ranges;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.UIntProgression;
import kotlin.ranges.ULongProgression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u0005*\u00020\u0004H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0006\u001a\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\b\u001a\u001e\u0010\u0002\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\t\u001a\u0019\u0010\n\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u001a\u0019\u0010\n\u001a\u0004\u0018\u00010\u0005*\u00020\u0004H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\f\u001a \u0010\n\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\r\u001a \u0010\n\u001a\u0004\u0018\u00010\u0005*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000e\u001a!\u0010\u0013\u001a\u00020\u0010*\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a!\u0010\u0013\u001a\u00020\u0010*\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u001f\u0010\u0013\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a\u001f\u0010\u0013\u001a\u00020\u0010*\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u0013\u001a\u00020\u0010*\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a\u001f\u0010\u0013\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0005H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\u001f\u0010\u0013\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\u0017\u001a\u00020 H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"\u001a\u001f\u0010\u0013\u001a\u00020\u0010*\u00020\u00042\u0006\u0010\u0017\u001a\u00020 H\u0087\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a\u001f\u0010)\u001a\u00020&*\u00020\u00162\u0006\u0010%\u001a\u00020\u0016H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(\u001a\u001f\u0010)\u001a\u00020&*\u00020\u00012\u0006\u0010%\u001a\u00020\u0001H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010+\u001a\u001f\u0010)\u001a\u00020,*\u00020\u00052\u0006\u0010%\u001a\u00020\u0005H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.\u001a\u001f\u0010)\u001a\u00020&*\u00020 2\u0006\u0010%\u001a\u00020 H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b/\u00100\u001a\u0013\u00101\u001a\u00020&*\u00020&H\u0007\u00a2\u0006\u0004\b1\u00102\u001a\u0013\u00101\u001a\u00020,*\u00020,H\u0007\u00a2\u0006\u0004\b1\u00103\u001a\u001c\u00105\u001a\u00020&*\u00020&2\u0006\u00105\u001a\u000204H\u0087\u0004\u00a2\u0006\u0004\b5\u00106\u001a\u001c\u00105\u001a\u00020,*\u00020,2\u0006\u00105\u001a\u000207H\u0087\u0004\u00a2\u0006\u0004\b5\u00108\u001a\u001f\u0010;\u001a\u00020\u0000*\u00020\u00162\u0006\u0010%\u001a\u00020\u0016H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b9\u0010:\u001a\u001f\u0010;\u001a\u00020\u0000*\u00020\u00012\u0006\u0010%\u001a\u00020\u0001H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b<\u0010=\u001a\u001f\u0010;\u001a\u00020\u0004*\u00020\u00052\u0006\u0010%\u001a\u00020\u0005H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b>\u0010?\u001a\u001f\u0010;\u001a\u00020\u0000*\u00020 2\u0006\u0010%\u001a\u00020 H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b@\u0010A\u001a\u001e\u0010E\u001a\u00020\u0001*\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bC\u0010D\u001a\u001e\u0010E\u001a\u00020\u0005*\u00020\u00052\u0006\u0010B\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bF\u0010G\u001a\u001e\u0010E\u001a\u00020\u0016*\u00020\u00162\u0006\u0010B\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bH\u0010I\u001a\u001e\u0010E\u001a\u00020 *\u00020 2\u0006\u0010B\u001a\u00020 H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bJ\u0010K\u001a\u001e\u0010N\u001a\u00020\u0001*\u00020\u00012\u0006\u0010L\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bM\u0010D\u001a\u001e\u0010N\u001a\u00020\u0005*\u00020\u00052\u0006\u0010L\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bO\u0010G\u001a\u001e\u0010N\u001a\u00020\u0016*\u00020\u00162\u0006\u0010L\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bP\u0010I\u001a\u001e\u0010N\u001a\u00020 *\u00020 2\u0006\u0010L\u001a\u00020 H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bQ\u0010K\u001a&\u0010T\u001a\u00020\u0001*\u00020\u00012\u0006\u0010B\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bR\u0010S\u001a&\u0010T\u001a\u00020\u0005*\u00020\u00052\u0006\u0010B\u001a\u00020\u00052\u0006\u0010L\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bU\u0010V\u001a&\u0010T\u001a\u00020\u0016*\u00020\u00162\u0006\u0010B\u001a\u00020\u00162\u0006\u0010L\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bW\u0010X\u001a&\u0010T\u001a\u00020 *\u00020 2\u0006\u0010B\u001a\u00020 2\u0006\u0010L\u001a\u00020 H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\bY\u0010Z\u001a$\u0010T\u001a\u00020\u0001*\u00020\u00012\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00010[H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b]\u0010^\u001a$\u0010T\u001a\u00020\u0005*\u00020\u00052\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00050[H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b_\u0010`\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006a"}, d2 = {"Lkotlin/ranges/UIntRange;", "Lkotlin/UInt;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/ranges/ULongRange;", "Lkotlin/ULong;", "(Lkotlin/ranges/ULongRange;)J", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "randomOrNull", "(Lkotlin/ranges/UIntRange;)Lkotlin/UInt;", "(Lkotlin/ranges/ULongRange;)Lkotlin/ULong;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)Lkotlin/UInt;", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)Lkotlin/ULong;", "element", "", "contains-biwQdVI", "(Lkotlin/ranges/UIntRange;Lkotlin/UInt;)Z", "contains", "contains-GYNo2lE", "(Lkotlin/ranges/ULongRange;Lkotlin/ULong;)Z", "Lkotlin/UByte;", "value", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "Lkotlin/UShort;", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", RemoteMessageConst.TO, "Lkotlin/ranges/UIntProgression;", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "reversed", "(Lkotlin/ranges/UIntProgression;)Lkotlin/ranges/UIntProgression;", "(Lkotlin/ranges/ULongProgression;)Lkotlin/ranges/ULongProgression;", "", "step", "(Lkotlin/ranges/UIntProgression;I)Lkotlin/ranges/UIntProgression;", "", "(Lkotlin/ranges/ULongProgression;J)Lkotlin/ranges/ULongProgression;", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "minimumValue", "coerceAtLeast-J1ME1BU", "(II)I", "coerceAtLeast", "coerceAtLeast-eb3DHEI", "(JJ)J", "coerceAtLeast-Kr8caGY", "(BB)B", "coerceAtLeast-5PvTz6A", "(SS)S", "maximumValue", "coerceAtMost-J1ME1BU", "coerceAtMost", "coerceAtMost-eb3DHEI", "coerceAtMost-Kr8caGY", "coerceAtMost-5PvTz6A", "coerceIn-WZ9TVnA", "(III)I", "coerceIn", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-VKSA0NQ", "(SSS)S", "Lkotlin/ranges/ClosedRange;", "range", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/ranges/URangesKt")
/* loaded from: classes11.dex */
class URangesKt___URangesKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtLeast-5PvTz6A  reason: not valid java name */
    public static final short m1124coerceAtLeast5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & 65535, 65535 & s2) < 0 ? s2 : s;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtLeast-J1ME1BU  reason: not valid java name */
    public static final int m1125coerceAtLeastJ1ME1BU(int i2, int i3) {
        return UnsignedKt.uintCompare(i2, i3) < 0 ? i3 : i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtLeast-Kr8caGY  reason: not valid java name */
    public static final byte m1126coerceAtLeastKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) < 0 ? b2 : b;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtLeast-eb3DHEI  reason: not valid java name */
    public static final long m1127coerceAtLeasteb3DHEI(long j2, long j3) {
        return UnsignedKt.ulongCompare(j2, j3) < 0 ? j3 : j2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtMost-5PvTz6A  reason: not valid java name */
    public static final short m1128coerceAtMost5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & 65535, 65535 & s2) > 0 ? s2 : s;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtMost-J1ME1BU  reason: not valid java name */
    public static final int m1129coerceAtMostJ1ME1BU(int i2, int i3) {
        return UnsignedKt.uintCompare(i2, i3) > 0 ? i3 : i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtMost-Kr8caGY  reason: not valid java name */
    public static final byte m1130coerceAtMostKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255) > 0 ? b2 : b;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceAtMost-eb3DHEI  reason: not valid java name */
    public static final long m1131coerceAtMosteb3DHEI(long j2, long j3) {
        return UnsignedKt.ulongCompare(j2, j3) > 0 ? j3 : j2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceIn-JPwROB0  reason: not valid java name */
    public static final long m1132coerceInJPwROB0(long j2, @NotNull ClosedRange<ULong> closedRange) {
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((ULong) RangesKt___RangesKt.coerceIn(ULong.m348boximpl(j2), (ClosedFloatingPointRange<ULong>) closedRange)).getData();
        }
        if (!closedRange.isEmpty()) {
            return UnsignedKt.ulongCompare(j2, closedRange.getStart().getData()) < 0 ? closedRange.getStart().getData() : UnsignedKt.ulongCompare(j2, closedRange.getEndInclusive().getData()) > 0 ? closedRange.getEndInclusive().getData() : j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceIn-VKSA0NQ  reason: not valid java name */
    public static final short m1133coerceInVKSA0NQ(short s, short s2, short s3) {
        int i2 = s2 & 65535;
        int i3 = s3 & 65535;
        if (Intrinsics.compare(i2, i3) <= 0) {
            int i4 = 65535 & s;
            return Intrinsics.compare(i4, i2) < 0 ? s2 : Intrinsics.compare(i4, i3) > 0 ? s3 : s;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UShort.m486toStringimpl(s3) + " is less than minimum " + UShort.m486toStringimpl(s2) + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceIn-WZ9TVnA  reason: not valid java name */
    public static final int m1134coerceInWZ9TVnA(int i2, int i3, int i4) {
        if (UnsignedKt.uintCompare(i3, i4) <= 0) {
            return UnsignedKt.uintCompare(i2, i3) < 0 ? i3 : UnsignedKt.uintCompare(i2, i4) > 0 ? i4 : i2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UInt.m322toStringimpl(i4) + " is less than minimum " + UInt.m322toStringimpl(i3) + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceIn-b33U2AM  reason: not valid java name */
    public static final byte m1135coerceInb33U2AM(byte b, byte b2, byte b3) {
        int i2 = b2 & 255;
        int i3 = b3 & 255;
        if (Intrinsics.compare(i2, i3) <= 0) {
            int i4 = b & 255;
            return Intrinsics.compare(i4, i2) < 0 ? b2 : Intrinsics.compare(i4, i3) > 0 ? b3 : b;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + UByte.m253toStringimpl(b3) + " is less than minimum " + UByte.m253toStringimpl(b2) + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceIn-sambcqE  reason: not valid java name */
    public static final long m1136coerceInsambcqE(long j2, long j3, long j4) {
        if (UnsignedKt.ulongCompare(j3, j4) <= 0) {
            return UnsignedKt.ulongCompare(j2, j3) < 0 ? j3 : UnsignedKt.ulongCompare(j2, j4) > 0 ? j4 : j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + ULong.m391toStringimpl(j4) + " is less than minimum " + ULong.m391toStringimpl(j3) + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: coerceIn-wuiCnnA  reason: not valid java name */
    public static final int m1137coerceInwuiCnnA(int i2, @NotNull ClosedRange<UInt> closedRange) {
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((UInt) RangesKt___RangesKt.coerceIn(UInt.m279boximpl(i2), (ClosedFloatingPointRange<UInt>) closedRange)).getData();
        }
        if (!closedRange.isEmpty()) {
            return UnsignedKt.uintCompare(i2, closedRange.getStart().getData()) < 0 ? closedRange.getStart().getData() : UnsignedKt.uintCompare(i2, closedRange.getEndInclusive().getData()) > 0 ? closedRange.getEndInclusive().getData() : i2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: contains-68kG9v0  reason: not valid java name */
    public static final boolean m1138contains68kG9v0(@NotNull  Var, byte b) {
        return Var.m1121containsWZ4Q5Ns(UInt.m285constructorimpl(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: contains-GYNo2lE  reason: not valid java name */
    private static final boolean m1139containsGYNo2lE(@NotNull  Var, ULong uLong) {
        return uLong != null && Var.m1123containsVKZWuLQ(uLong.getData());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: contains-Gab390E  reason: not valid java name */
    public static final boolean m1140containsGab390E(@NotNull  Var, int i2) {
        return Var.m1123containsVKZWuLQ(ULong.m354constructorimpl(i2 & 4294967295L));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: contains-ULb-yJY  reason: not valid java name */
    public static final boolean m1141containsULbyJY(@NotNull  Var, byte b) {
        return Var.m1123containsVKZWuLQ(ULong.m354constructorimpl(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: contains-ZsK3CEQ  reason: not valid java name */
    public static final boolean m1142containsZsK3CEQ(@NotNull  Var, short s) {
        return Var.m1121containsWZ4Q5Ns(UInt.m285constructorimpl(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: contains-biwQdVI  reason: not valid java name */
    private static final boolean m1143containsbiwQdVI(@NotNull  Var, UInt uInt) {
        return uInt != null && Var.m1121containsWZ4Q5Ns(uInt.getData());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: contains-fz5IDCE  reason: not valid java name */
    public static final boolean m1144containsfz5IDCE(@NotNull  Var, long j2) {
        return ULong.m354constructorimpl(j2 >>> 32) == 0 && Var.m1121containsWZ4Q5Ns(UInt.m285constructorimpl((int) j2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: contains-uhHAxoY  reason: not valid java name */
    public static final boolean m1145containsuhHAxoY(@NotNull  Var, short s) {
        return Var.m1123containsVKZWuLQ(ULong.m354constructorimpl(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: downTo-5PvTz6A  reason: not valid java name */
    public static final UIntProgression m1146downTo5PvTz6A(short s, short s2) {
        return UIntProgression.INSTANCE.m1120fromClosedRangeNkh28Cs(UInt.m285constructorimpl(s & 65535), UInt.m285constructorimpl(s2 & 65535), -1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: downTo-J1ME1BU  reason: not valid java name */
    public static final UIntProgression m1147downToJ1ME1BU(int i2, int i3) {
        return UIntProgression.INSTANCE.m1120fromClosedRangeNkh28Cs(i2, i3, -1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: downTo-Kr8caGY  reason: not valid java name */
    public static final UIntProgression m1148downToKr8caGY(byte b, byte b2) {
        return UIntProgression.INSTANCE.m1120fromClosedRangeNkh28Cs(UInt.m285constructorimpl(b & 255), UInt.m285constructorimpl(b2 & 255), -1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: downTo-eb3DHEI  reason: not valid java name */
    public static final ULongProgression m1149downToeb3DHEI(long j2, long j3) {
        return ULongProgression.INSTANCE.m1122fromClosedRange7ftBX0g(j2, j3, -1L);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int random(@NotNull  Var) {
        return random(Var, Random.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final UInt randomOrNull(@NotNull  Var) {
        return randomOrNull(Var, Random.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntProgression reversed(@NotNull UIntProgression uIntProgression) {
        return UIntProgression.INSTANCE.m1120fromClosedRangeNkh28Cs(uIntProgression.getLast(), uIntProgression.getFirst(), -uIntProgression.getStep());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final UIntProgression step(@NotNull UIntProgression uIntProgression, int i2) {
        RangesKt__RangesKt.checkStepIsPositive(i2 > 0, Integer.valueOf(i2));
        UIntProgression.Companion companion = UIntProgression.INSTANCE;
        int first = uIntProgression.getFirst();
        int last = uIntProgression.getLast();
        if (uIntProgression.getStep() <= 0) {
            i2 = -i2;
        }
        return companion.m1120fromClosedRangeNkh28Cs(first, last, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: until-5PvTz6A  reason: not valid java name */
    public static final  m1150until5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s2 & 65535, 0) <= 0 ? .INSTANCE.getEMPTY() : new (UInt.m285constructorimpl(s & 65535), UInt.m285constructorimpl(UInt.m285constructorimpl(r3) - 1), null);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: until-J1ME1BU  reason: not valid java name */
    public static final  m1151untilJ1ME1BU(int i2, int i3) {
        return UnsignedKt.uintCompare(i3, 0) <= 0 ? .INSTANCE.getEMPTY() : new (i2, UInt.m285constructorimpl(i3 - 1), null);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: until-Kr8caGY  reason: not valid java name */
    public static final  m1152untilKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b2 & 255, 0) <= 0 ? .INSTANCE.getEMPTY() : new (UInt.m285constructorimpl(b & 255), UInt.m285constructorimpl(UInt.m285constructorimpl(r3) - 1), null);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: until-eb3DHEI  reason: not valid java name */
    public static final  m1153untileb3DHEI(long j2, long j3) {
        return UnsignedKt.ulongCompare(j3, 0L) <= 0 ? .INSTANCE.getEMPTY() : new (j2, ULong.m354constructorimpl(j3 - ULong.m354constructorimpl(1 & 4294967295L)), null);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long random(@NotNull  Var) {
        return random(Var, Random.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final ULong randomOrNull(@NotNull  Var) {
        return randomOrNull(Var, Random.INSTANCE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final ULongProgression reversed(@NotNull ULongProgression uLongProgression) {
        return ULongProgression.INSTANCE.m1122fromClosedRange7ftBX0g(uLongProgression.getLast(), uLongProgression.getFirst(), -uLongProgression.getStep());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int random(@NotNull  Var, @NotNull Random random) {
        try {
            return URandomKt.nextUInt(random, Var);
        } catch (IllegalArgumentException e2) {
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt randomOrNull(@NotNull  Var, @NotNull Random random) {
        if (Var.isEmpty()) {
            return null;
        }
        return UInt.m279boximpl(URandomKt.nextUInt(random, Var));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final ULongProgression step(@NotNull ULongProgression uLongProgression, long j2) {
        RangesKt__RangesKt.checkStepIsPositive(j2 > 0, Long.valueOf(j2));
        ULongProgression.Companion companion = ULongProgression.INSTANCE;
        long first = uLongProgression.getFirst();
        long last = uLongProgression.getLast();
        if (uLongProgression.getStep() <= 0) {
            j2 = -j2;
        }
        return companion.m1122fromClosedRange7ftBX0g(first, last, j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long random(@NotNull  Var, @NotNull Random random) {
        try {
            return URandomKt.nextULong(random, Var);
        } catch (IllegalArgumentException e2) {
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong randomOrNull(@NotNull  Var, @NotNull Random random) {
        if (Var.isEmpty()) {
            return null;
        }
        return ULong.m348boximpl(URandomKt.nextULong(random, Var));
    }
}
