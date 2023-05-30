package kotlin;

import ..;
import com.jd.aips.verify.tracker.VerifyTracker;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087@\u0018\u0000 m2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001mB\u0014\b\u0001\u0012\u0006\u0010h\u001a\u00020\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\bl\u0010+J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0000H\u0097\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0006J\u001b\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\nJ\u001b\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\fJ\u001b\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0006J\u001b\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\nJ\u001b\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\fJ\u001b\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u0015J\u001b\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u0006J\u001b\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\nJ\u001b\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\fJ\u001b\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010\u0015J\u001b\u0010!\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0006J\u001b\u0010!\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010\nJ\u001b\u0010!\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010\fJ\u001b\u0010!\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010\u0015J\u001b\u0010&\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010\u0006J\u001b\u0010&\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010\nJ\u001b\u0010&\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010\fJ\u001b\u0010&\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010\u0015J\u0013\u0010,\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010+J\u0013\u0010.\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010+J\u001b\u00102\u001a\u00020/2\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u00101J\u001b\u00105\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u0004H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b4\u0010\fJ\u001b\u00107\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u0004H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b6\u0010\fJ\u001b\u00109\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b8\u0010\fJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010\fJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b<\u0010\fJ\u0013\u0010?\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b>\u0010+J\u0010\u0010C\u001a\u00020@H\u0087\b\u00a2\u0006\u0004\bA\u0010BJ\u0010\u0010G\u001a\u00020DH\u0087\b\u00a2\u0006\u0004\bE\u0010FJ\u0010\u0010I\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\bH\u0010+J\u0010\u0010M\u001a\u00020JH\u0087\b\u00a2\u0006\u0004\bK\u0010LJ\u0013\u0010O\u001a\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bN\u0010BJ\u0013\u0010Q\u001a\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bP\u0010FJ\u0013\u0010S\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bR\u0010+J\u0013\u0010U\u001a\u00020\rH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bT\u0010LJ\u0010\u0010Y\u001a\u00020VH\u0087\b\u00a2\u0006\u0004\bW\u0010XJ\u0010\u0010]\u001a\u00020ZH\u0087\b\u00a2\u0006\u0004\b[\u0010\\J\u000f\u0010a\u001a\u00020^H\u0016\u00a2\u0006\u0004\b_\u0010`J\u0010\u0010b\u001a\u00020\u0004H\u00d6\u0001\u00a2\u0006\u0004\bb\u0010cJ\u001a\u0010f\u001a\u00020e2\b\u0010\u0003\u001a\u0004\u0018\u00010dH\u00d6\u0003\u00a2\u0006\u0004\bf\u0010gR\u001c\u0010h\u001a\u00020\u00048\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\bh\u0010i\u0012\u0004\bj\u0010k\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006n"}, d2 = {"Lkotlin/UInt;", "", "Lkotlin/UByte;", "other", "", "compareTo-7apg3OU", "(IB)I", "compareTo", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "compareTo-WZ4Q5Ns", "(II)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "plus-7apg3OU", "plus", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(IJ)J", "minus-7apg3OU", "minus", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times-7apg3OU", VerifyTracker.KEY_TIMES, "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div-7apg3OU", "div", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem-7apg3OU", "rem", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "inc-impl", "(I)I", "inc", "dec-impl", "dec", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeTo", "bitCount", "shl-impl", "shl", "shr-impl", "shr", "and-WZ4Q5Ns", "and", "or-WZ4Q5Ns", "or", "xor-WZ4Q5Ns", "xor", "inv-impl", "inv", "", "toByte-impl", "(I)B", "toByte", "", "toShort-impl", "(I)S", "toShort", "toInt-impl", "toInt", "", "toLong-impl", "(I)J", "toLong", "toUByte-impl", "toUByte", "toUShort-impl", "toUShort", "toUInt-impl", "toUInt", "toULong-impl", "toULong", "", "toFloat-impl", "(I)F", "toFloat", "", "toDouble-impl", "(I)D", "toDouble", "", "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode", "()I", "", "", "equals", "(Ljava/lang/Object;)Z", "data", "I", "data$annotations", "()V", "constructor-impl", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* loaded from: classes11.dex */
public final class UInt implements Comparable<UInt> {
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    @PublishedApi
    private /* synthetic */ UInt(int i2) {
        this.data = i2;
    }

    @InlineOnly
    /* renamed from: and-WZ4Q5Ns */
    private static final int m278andWZ4Q5Ns(int i2, int i3) {
        return m285constructorimpl(i2 & i3);
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ UInt m279boximpl(int i2) {
        return new UInt(i2);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static final int m280compareTo7apg3OU(int i2, byte b) {
        return UnsignedKt.uintCompare(i2, m285constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static final int m281compareToVKZWuLQ(int i2, long j2) {
        return UnsignedKt.ulongCompare(ULong.m354constructorimpl(i2 & 4294967295L), j2);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private int m282compareToWZ4Q5Ns(int i2) {
        return m283compareToWZ4Q5Ns(this.data, i2);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static final int m284compareToxj2QHRw(int i2, short s) {
        return UnsignedKt.uintCompare(i2, m285constructorimpl(s & 65535));
    }

    @PublishedApi
    /* renamed from: constructor-impl */
    public static int m285constructorimpl(int i2) {
        return i2;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    @InlineOnly
    /* renamed from: dec-impl */
    private static final int m286decimpl(int i2) {
        return m285constructorimpl(i2 - 1);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final int m287div7apg3OU(int i2, byte b) {
        return UnsignedKt.m511uintDivideJ1ME1BU(i2, m285constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m288divVKZWuLQ(int i2, long j2) {
        return UnsignedKt.m513ulongDivideeb3DHEI(ULong.m354constructorimpl(i2 & 4294967295L), j2);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final int m289divWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m511uintDivideJ1ME1BU(i2, i3);
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final int m290divxj2QHRw(int i2, short s) {
        return UnsignedKt.m511uintDivideJ1ME1BU(i2, m285constructorimpl(s & 65535));
    }

    /* renamed from: equals-impl */
    public static boolean m291equalsimpl(int i2, @Nullable Object obj) {
        return (obj instanceof UInt) && i2 == ((UInt) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m292equalsimpl0(int i2, int i3) {
        return i2 == i3;
    }

    /* renamed from: hashCode-impl */
    public static int m293hashCodeimpl(int i2) {
        return i2;
    }

    @InlineOnly
    /* renamed from: inc-impl */
    private static final int m294incimpl(int i2) {
        return m285constructorimpl(i2 + 1);
    }

    @InlineOnly
    /* renamed from: inv-impl */
    private static final int m295invimpl(int i2) {
        return m285constructorimpl(i2 ^ (-1));
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final int m296minus7apg3OU(int i2, byte b) {
        return m285constructorimpl(i2 - m285constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m297minusVKZWuLQ(int i2, long j2) {
        return ULong.m354constructorimpl(ULong.m354constructorimpl(i2 & 4294967295L) - j2);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final int m298minusWZ4Q5Ns(int i2, int i3) {
        return m285constructorimpl(i2 - i3);
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final int m299minusxj2QHRw(int i2, short s) {
        return m285constructorimpl(i2 - m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: or-WZ4Q5Ns */
    private static final int m300orWZ4Q5Ns(int i2, int i3) {
        return m285constructorimpl(i2 | i3);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final int m301plus7apg3OU(int i2, byte b) {
        return m285constructorimpl(i2 + m285constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m302plusVKZWuLQ(int i2, long j2) {
        return ULong.m354constructorimpl(ULong.m354constructorimpl(i2 & 4294967295L) + j2);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final int m303plusWZ4Q5Ns(int i2, int i3) {
        return m285constructorimpl(i2 + i3);
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final int m304plusxj2QHRw(int i2, short s) {
        return m285constructorimpl(i2 + m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns */
    private static final  m305rangeToWZ4Q5Ns(int i2, int i3) {
        return new (i2, i3, null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final int m306rem7apg3OU(int i2, byte b) {
        return UnsignedKt.m512uintRemainderJ1ME1BU(i2, m285constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m307remVKZWuLQ(int i2, long j2) {
        return UnsignedKt.m514ulongRemaindereb3DHEI(ULong.m354constructorimpl(i2 & 4294967295L), j2);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final int m308remWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m512uintRemainderJ1ME1BU(i2, i3);
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final int m309remxj2QHRw(int i2, short s) {
        return UnsignedKt.m512uintRemainderJ1ME1BU(i2, m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: shl-impl */
    private static final int m310shlimpl(int i2, int i3) {
        return m285constructorimpl(i2 << i3);
    }

    @InlineOnly
    /* renamed from: shr-impl */
    private static final int m311shrimpl(int i2, int i3) {
        return m285constructorimpl(i2 >>> i3);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final int m312times7apg3OU(int i2, byte b) {
        return m285constructorimpl(i2 * m285constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m313timesVKZWuLQ(int i2, long j2) {
        return ULong.m354constructorimpl(ULong.m354constructorimpl(i2 & 4294967295L) * j2);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final int m314timesWZ4Q5Ns(int i2, int i3) {
        return m285constructorimpl(i2 * i3);
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final int m315timesxj2QHRw(int i2, short s) {
        return m285constructorimpl(i2 * m285constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m316toByteimpl(int i2) {
        return (byte) i2;
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m317toDoubleimpl(int i2) {
        return UnsignedKt.uintToDouble(i2);
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m318toFloatimpl(int i2) {
        return (float) UnsignedKt.uintToDouble(i2);
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m319toIntimpl(int i2) {
        return i2;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m320toLongimpl(int i2) {
        return i2 & 4294967295L;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m321toShortimpl(int i2) {
        return (short) i2;
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m322toStringimpl(int i2) {
        return String.valueOf(i2 & 4294967295L);
    }

    @InlineOnly
    /* renamed from: toUByte-impl */
    private static final byte m323toUByteimpl(int i2) {
        return UByte.m218constructorimpl((byte) i2);
    }

    @InlineOnly
    /* renamed from: toUInt-impl */
    private static final int m324toUIntimpl(int i2) {
        return i2;
    }

    @InlineOnly
    /* renamed from: toULong-impl */
    private static final long m325toULongimpl(int i2) {
        return ULong.m354constructorimpl(i2 & 4294967295L);
    }

    @InlineOnly
    /* renamed from: toUShort-impl */
    private static final short m326toUShortimpl(int i2) {
        return UShort.m451constructorimpl((short) i2);
    }

    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns */
    private static final int m327xorWZ4Q5Ns(int i2, int i3) {
        return m285constructorimpl(i2 ^ i3);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return m282compareToWZ4Q5Ns(uInt.getData());
    }

    public boolean equals(Object other) {
        return m291equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m293hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m322toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ int getData() {
        return this.data;
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static int m283compareToWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.uintCompare(i2, i3);
    }
}
