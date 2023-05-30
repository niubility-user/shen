package kotlin;

import ..;
import cn.com.union.fido.bean.uafclient.ErrorCode;
import com.jd.aips.verify.tracker.VerifyTracker;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087@\u0018\u0000 i2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001iB\u0014\b\u0001\u0012\u0006\u0010d\u001a\u00020<\u00f8\u0001\u0000\u00a2\u0006\u0004\bh\u0010+J\u001b\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0000H\u0097\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\nH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0005J\u001b\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\tJ\u001b\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\fJ\u001b\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0005J\u001b\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\tJ\u001b\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\fJ\u001b\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u0015J\u001b\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u0005J\u001b\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\tJ\u001b\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\fJ\u001b\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010\u0015J\u001b\u0010!\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0005J\u001b\u0010!\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010\tJ\u001b\u0010!\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010\fJ\u001b\u0010!\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010\u0015J\u001b\u0010&\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010\u0005J\u001b\u0010&\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010\tJ\u001b\u0010&\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010\fJ\u001b\u0010&\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010\u0015J\u0013\u0010,\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010+J\u0013\u0010.\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010+J\u001b\u00102\u001a\u00020/2\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u00101J\u001b\u00105\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u00104J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b6\u00104J\u001b\u00109\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b8\u00104J\u0013\u0010;\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010+J\u0010\u0010>\u001a\u00020<H\u0087\b\u00a2\u0006\u0004\b=\u0010+J\u0010\u0010B\u001a\u00020?H\u0087\b\u00a2\u0006\u0004\b@\u0010AJ\u0010\u0010E\u001a\u00020\u0003H\u0087\b\u00a2\u0006\u0004\bC\u0010DJ\u0010\u0010I\u001a\u00020FH\u0087\b\u00a2\u0006\u0004\bG\u0010HJ\u0013\u0010K\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bJ\u0010+J\u0013\u0010M\u001a\u00020\u0007H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bL\u0010AJ\u0013\u0010O\u001a\u00020\nH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bN\u0010DJ\u0013\u0010Q\u001a\u00020\rH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bP\u0010HJ\u0010\u0010U\u001a\u00020RH\u0087\b\u00a2\u0006\u0004\bS\u0010TJ\u0010\u0010Y\u001a\u00020VH\u0087\b\u00a2\u0006\u0004\bW\u0010XJ\u000f\u0010]\u001a\u00020ZH\u0016\u00a2\u0006\u0004\b[\u0010\\J\u0010\u0010^\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b^\u0010_J\u001a\u0010b\u001a\u00020a2\b\u0010\u0002\u001a\u0004\u0018\u00010`H\u00d6\u0003\u00a2\u0006\u0004\bb\u0010cR\u001c\u0010d\u001a\u00020<8\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\bd\u0010e\u0012\u0004\bf\u0010g\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006j"}, d2 = {"Lkotlin/UByte;", "", "other", "", "compareTo-7apg3OU", "(BB)I", "compareTo", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "plus-7apg3OU", "plus", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(BJ)J", "minus-7apg3OU", "minus", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times-7apg3OU", VerifyTracker.KEY_TIMES, "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div-7apg3OU", "div", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem-7apg3OU", "rem", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "inc-impl", "(B)B", "inc", "dec-impl", "dec", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeTo", "and-7apg3OU", "(BB)B", "and", "or-7apg3OU", "or", "xor-7apg3OU", "xor", "inv-impl", "inv", "", "toByte-impl", "toByte", "", "toShort-impl", "(B)S", "toShort", "toInt-impl", "(B)I", "toInt", "", "toLong-impl", "(B)J", "toLong", "toUByte-impl", "toUByte", "toUShort-impl", "toUShort", "toUInt-impl", "toUInt", "toULong-impl", "toULong", "", "toFloat-impl", "(B)F", "toFloat", "", "toDouble-impl", "(B)D", "toDouble", "", "toString-impl", "(B)Ljava/lang/String;", "toString", "hashCode", "()I", "", "", "equals", "(Ljava/lang/Object;)Z", "data", "B", "data$annotations", "()V", "constructor-impl", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* loaded from: classes11.dex */
public final class UByte implements Comparable<UByte> {
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    @PublishedApi
    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    @InlineOnly
    /* renamed from: and-7apg3OU */
    private static final byte m211and7apg3OU(byte b, byte b2) {
        return m218constructorimpl((byte) (b & b2));
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ UByte m212boximpl(byte b) {
        return new UByte(b);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private int m213compareTo7apg3OU(byte b) {
        return m214compareTo7apg3OU(this.data, b);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static final int m215compareToVKZWuLQ(byte b, long j2) {
        return UnsignedKt.ulongCompare(ULong.m354constructorimpl(b & 255), j2);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m216compareToWZ4Q5Ns(byte b, int i2) {
        return UnsignedKt.uintCompare(UInt.m285constructorimpl(b & 255), i2);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static final int m217compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & 255, s & 65535);
    }

    @PublishedApi
    /* renamed from: constructor-impl */
    public static byte m218constructorimpl(byte b) {
        return b;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    @InlineOnly
    /* renamed from: dec-impl */
    private static final byte m219decimpl(byte b) {
        return m218constructorimpl((byte) (b - 1));
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final int m220div7apg3OU(byte b, byte b2) {
        return UnsignedKt.m511uintDivideJ1ME1BU(UInt.m285constructorimpl(b & 255), UInt.m285constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m221divVKZWuLQ(byte b, long j2) {
        return UnsignedKt.m513ulongDivideeb3DHEI(ULong.m354constructorimpl(b & 255), j2);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final int m222divWZ4Q5Ns(byte b, int i2) {
        return UnsignedKt.m511uintDivideJ1ME1BU(UInt.m285constructorimpl(b & 255), i2);
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final int m223divxj2QHRw(byte b, short s) {
        return UnsignedKt.m511uintDivideJ1ME1BU(UInt.m285constructorimpl(b & 255), UInt.m285constructorimpl(s & 65535));
    }

    /* renamed from: equals-impl */
    public static boolean m224equalsimpl(byte b, @Nullable Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m225equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    /* renamed from: hashCode-impl */
    public static int m226hashCodeimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: inc-impl */
    private static final byte m227incimpl(byte b) {
        return m218constructorimpl((byte) (b + 1));
    }

    @InlineOnly
    /* renamed from: inv-impl */
    private static final byte m228invimpl(byte b) {
        return m218constructorimpl((byte) (b ^ (-1)));
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final int m229minus7apg3OU(byte b, byte b2) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) - UInt.m285constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m230minusVKZWuLQ(byte b, long j2) {
        return ULong.m354constructorimpl(ULong.m354constructorimpl(b & 255) - j2);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final int m231minusWZ4Q5Ns(byte b, int i2) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) - i2);
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final int m232minusxj2QHRw(byte b, short s) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) - UInt.m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: or-7apg3OU */
    private static final byte m233or7apg3OU(byte b, byte b2) {
        return m218constructorimpl((byte) (b | b2));
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final int m234plus7apg3OU(byte b, byte b2) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) + UInt.m285constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m235plusVKZWuLQ(byte b, long j2) {
        return ULong.m354constructorimpl(ULong.m354constructorimpl(b & 255) + j2);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final int m236plusWZ4Q5Ns(byte b, int i2) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) + i2);
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final int m237plusxj2QHRw(byte b, short s) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) + UInt.m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: rangeTo-7apg3OU */
    private static final  m238rangeTo7apg3OU(byte b, byte b2) {
        return new (UInt.m285constructorimpl(b & 255), UInt.m285constructorimpl(b2 & 255), null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final int m239rem7apg3OU(byte b, byte b2) {
        return UnsignedKt.m512uintRemainderJ1ME1BU(UInt.m285constructorimpl(b & 255), UInt.m285constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m240remVKZWuLQ(byte b, long j2) {
        return UnsignedKt.m514ulongRemaindereb3DHEI(ULong.m354constructorimpl(b & 255), j2);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final int m241remWZ4Q5Ns(byte b, int i2) {
        return UnsignedKt.m512uintRemainderJ1ME1BU(UInt.m285constructorimpl(b & 255), i2);
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final int m242remxj2QHRw(byte b, short s) {
        return UnsignedKt.m512uintRemainderJ1ME1BU(UInt.m285constructorimpl(b & 255), UInt.m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final int m243times7apg3OU(byte b, byte b2) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) * UInt.m285constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m244timesVKZWuLQ(byte b, long j2) {
        return ULong.m354constructorimpl(ULong.m354constructorimpl(b & 255) * j2);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final int m245timesWZ4Q5Ns(byte b, int i2) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) * i2);
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final int m246timesxj2QHRw(byte b, short s) {
        return UInt.m285constructorimpl(UInt.m285constructorimpl(b & 255) * UInt.m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m247toByteimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m248toDoubleimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m249toFloatimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m250toIntimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m251toLongimpl(byte b) {
        return b & 255;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m252toShortimpl(byte b) {
        return (short) (b & ErrorCode.UNKNOWN);
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m253toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }

    @InlineOnly
    /* renamed from: toUByte-impl */
    private static final byte m254toUByteimpl(byte b) {
        return b;
    }

    @InlineOnly
    /* renamed from: toUInt-impl */
    private static final int m255toUIntimpl(byte b) {
        return UInt.m285constructorimpl(b & 255);
    }

    @InlineOnly
    /* renamed from: toULong-impl */
    private static final long m256toULongimpl(byte b) {
        return ULong.m354constructorimpl(b & 255);
    }

    @InlineOnly
    /* renamed from: toUShort-impl */
    private static final short m257toUShortimpl(byte b) {
        return UShort.m451constructorimpl((short) (b & ErrorCode.UNKNOWN));
    }

    @InlineOnly
    /* renamed from: xor-7apg3OU */
    private static final byte m258xor7apg3OU(byte b, byte b2) {
        return m218constructorimpl((byte) (b ^ b2));
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return m213compareTo7apg3OU(uByte.getData());
    }

    public boolean equals(Object other) {
        return m224equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m226hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m253toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ byte getData() {
        return this.data;
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static int m214compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255);
    }
}
