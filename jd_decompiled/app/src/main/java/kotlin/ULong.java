package kotlin;

import ..;
import com.jd.aips.verify.tracker.VerifyTracker;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087@\u0018\u0000 p2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001pB\u0014\b\u0001\u0012\u0006\u0010k\u001a\u00020N\u00f8\u0001\u0000\u00a2\u0006\u0004\bo\u0010.J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0000H\u0097\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u0011J\u001b\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u0014J\u001b\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u0016J\u001b\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u0018J\u001b\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u0011J\u001b\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0014J\u001b\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\u0016J\u001b\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010\u0018J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010\u0011J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010\u0014J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010\u0016J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010\u0018J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010\u0011J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010\u0014J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010\u0016J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010\u0018J\u0013\u0010/\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.J\u0013\u00101\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u0010.J\u001b\u00105\u001a\u0002022\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\n\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u00104J\u001b\u00108\u001a\u00020\u00002\u0006\u00106\u001a\u00020\u0004H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b7\u0010\u0016J\u001b\u0010:\u001a\u00020\u00002\u0006\u00106\u001a\u00020\u0004H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b9\u0010\u0016J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b;\u0010\u0018J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b=\u0010\u0018J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0087\f\u00f8\u0001\u0000\u00a2\u0006\u0004\b?\u0010\u0018J\u0013\u0010B\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010.J\u0010\u0010F\u001a\u00020CH\u0087\b\u00a2\u0006\u0004\bD\u0010EJ\u0010\u0010J\u001a\u00020GH\u0087\b\u00a2\u0006\u0004\bH\u0010IJ\u0010\u0010M\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\bK\u0010LJ\u0010\u0010P\u001a\u00020NH\u0087\b\u00a2\u0006\u0004\bO\u0010.J\u0013\u0010R\u001a\u00020\u0002H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bQ\u0010EJ\u0013\u0010T\u001a\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bS\u0010IJ\u0013\u0010V\u001a\u00020\u000bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bU\u0010LJ\u0013\u0010X\u001a\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\bW\u0010.J\u0010\u0010\\\u001a\u00020YH\u0087\b\u00a2\u0006\u0004\bZ\u0010[J\u0010\u0010`\u001a\u00020]H\u0087\b\u00a2\u0006\u0004\b^\u0010_J\u000f\u0010d\u001a\u00020aH\u0016\u00a2\u0006\u0004\bb\u0010cJ\u0010\u0010e\u001a\u00020\u0004H\u00d6\u0001\u00a2\u0006\u0004\be\u0010fJ\u001a\u0010i\u001a\u00020h2\b\u0010\u0003\u001a\u0004\u0018\u00010gH\u00d6\u0003\u00a2\u0006\u0004\bi\u0010jR\u001c\u0010k\u001a\u00020N8\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\bk\u0010l\u0012\u0004\bm\u0010n\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006q"}, d2 = {"Lkotlin/ULong;", "", "Lkotlin/UByte;", "other", "", "compareTo-7apg3OU", "(JB)I", "compareTo", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "plus-7apg3OU", "(JB)J", "plus", "plus-xj2QHRw", "(JS)J", "plus-WZ4Q5Ns", "(JI)J", "plus-VKZWuLQ", "(JJ)J", "minus-7apg3OU", "minus", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times-7apg3OU", VerifyTracker.KEY_TIMES, "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div-7apg3OU", "div", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem-7apg3OU", "rem", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "inc-impl", "(J)J", "inc", "dec-impl", "dec", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeTo", "bitCount", "shl-impl", "shl", "shr-impl", "shr", "and-VKZWuLQ", "and", "or-VKZWuLQ", "or", "xor-VKZWuLQ", "xor", "inv-impl", "inv", "", "toByte-impl", "(J)B", "toByte", "", "toShort-impl", "(J)S", "toShort", "toInt-impl", "(J)I", "toInt", "", "toLong-impl", "toLong", "toUByte-impl", "toUByte", "toUShort-impl", "toUShort", "toUInt-impl", "toUInt", "toULong-impl", "toULong", "", "toFloat-impl", "(J)F", "toFloat", "", "toDouble-impl", "(J)D", "toDouble", "", "toString-impl", "(J)Ljava/lang/String;", "toString", "hashCode", "()I", "", "", "equals", "(Ljava/lang/Object;)Z", "data", "J", "data$annotations", "()V", "constructor-impl", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
@ExperimentalUnsignedTypes
/* loaded from: classes11.dex */
public final class ULong implements Comparable<ULong> {
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    @PublishedApi
    private /* synthetic */ ULong(long j2) {
        this.data = j2;
    }

    @InlineOnly
    /* renamed from: and-VKZWuLQ */
    private static final long m347andVKZWuLQ(long j2, long j3) {
        return m354constructorimpl(j2 & j3);
    }

    @NotNull
    /* renamed from: box-impl */
    public static final /* synthetic */ ULong m348boximpl(long j2) {
        return new ULong(j2);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU */
    private static final int m349compareTo7apg3OU(long j2, byte b) {
        return UnsignedKt.ulongCompare(j2, m354constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private int m350compareToVKZWuLQ(long j2) {
        return m351compareToVKZWuLQ(this.data, j2);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m352compareToWZ4Q5Ns(long j2, int i2) {
        return UnsignedKt.ulongCompare(j2, m354constructorimpl(i2 & 4294967295L));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw */
    private static final int m353compareToxj2QHRw(long j2, short s) {
        return UnsignedKt.ulongCompare(j2, m354constructorimpl(s & 65535));
    }

    @PublishedApi
    /* renamed from: constructor-impl */
    public static long m354constructorimpl(long j2) {
        return j2;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    @InlineOnly
    /* renamed from: dec-impl */
    private static final long m355decimpl(long j2) {
        return m354constructorimpl(j2 - 1);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU */
    private static final long m356div7apg3OU(long j2, byte b) {
        return UnsignedKt.m513ulongDivideeb3DHEI(j2, m354constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ */
    private static final long m357divVKZWuLQ(long j2, long j3) {
        return UnsignedKt.m513ulongDivideeb3DHEI(j2, j3);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns */
    private static final long m358divWZ4Q5Ns(long j2, int i2) {
        return UnsignedKt.m513ulongDivideeb3DHEI(j2, m354constructorimpl(i2 & 4294967295L));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw */
    private static final long m359divxj2QHRw(long j2, short s) {
        return UnsignedKt.m513ulongDivideeb3DHEI(j2, m354constructorimpl(s & 65535));
    }

    /* renamed from: equals-impl */
    public static boolean m360equalsimpl(long j2, @Nullable Object obj) {
        return (obj instanceof ULong) && j2 == ((ULong) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m361equalsimpl0(long j2, long j3) {
        return j2 == j3;
    }

    /* renamed from: hashCode-impl */
    public static int m362hashCodeimpl(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    @InlineOnly
    /* renamed from: inc-impl */
    private static final long m363incimpl(long j2) {
        return m354constructorimpl(j2 + 1);
    }

    @InlineOnly
    /* renamed from: inv-impl */
    private static final long m364invimpl(long j2) {
        return m354constructorimpl(j2 ^ (-1));
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU */
    private static final long m365minus7apg3OU(long j2, byte b) {
        return m354constructorimpl(j2 - m354constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ */
    private static final long m366minusVKZWuLQ(long j2, long j3) {
        return m354constructorimpl(j2 - j3);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns */
    private static final long m367minusWZ4Q5Ns(long j2, int i2) {
        return m354constructorimpl(j2 - m354constructorimpl(i2 & 4294967295L));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw */
    private static final long m368minusxj2QHRw(long j2, short s) {
        return m354constructorimpl(j2 - m354constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: or-VKZWuLQ */
    private static final long m369orVKZWuLQ(long j2, long j3) {
        return m354constructorimpl(j2 | j3);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU */
    private static final long m370plus7apg3OU(long j2, byte b) {
        return m354constructorimpl(j2 + m354constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ */
    private static final long m371plusVKZWuLQ(long j2, long j3) {
        return m354constructorimpl(j2 + j3);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns */
    private static final long m372plusWZ4Q5Ns(long j2, int i2) {
        return m354constructorimpl(j2 + m354constructorimpl(i2 & 4294967295L));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw */
    private static final long m373plusxj2QHRw(long j2, short s) {
        return m354constructorimpl(j2 + m354constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: rangeTo-VKZWuLQ */
    private static final  m374rangeToVKZWuLQ(long j2, long j3) {
        return new (j2, j3, null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU */
    private static final long m375rem7apg3OU(long j2, byte b) {
        return UnsignedKt.m514ulongRemaindereb3DHEI(j2, m354constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ */
    private static final long m376remVKZWuLQ(long j2, long j3) {
        return UnsignedKt.m514ulongRemaindereb3DHEI(j2, j3);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns */
    private static final long m377remWZ4Q5Ns(long j2, int i2) {
        return UnsignedKt.m514ulongRemaindereb3DHEI(j2, m354constructorimpl(i2 & 4294967295L));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw */
    private static final long m378remxj2QHRw(long j2, short s) {
        return UnsignedKt.m514ulongRemaindereb3DHEI(j2, m354constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: shl-impl */
    private static final long m379shlimpl(long j2, int i2) {
        return m354constructorimpl(j2 << i2);
    }

    @InlineOnly
    /* renamed from: shr-impl */
    private static final long m380shrimpl(long j2, int i2) {
        return m354constructorimpl(j2 >>> i2);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU */
    private static final long m381times7apg3OU(long j2, byte b) {
        return m354constructorimpl(j2 * m354constructorimpl(b & 255));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ */
    private static final long m382timesVKZWuLQ(long j2, long j3) {
        return m354constructorimpl(j2 * j3);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns */
    private static final long m383timesWZ4Q5Ns(long j2, int i2) {
        return m354constructorimpl(j2 * m354constructorimpl(i2 & 4294967295L));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw */
    private static final long m384timesxj2QHRw(long j2, short s) {
        return m354constructorimpl(j2 * m354constructorimpl(s & 65535));
    }

    @InlineOnly
    /* renamed from: toByte-impl */
    private static final byte m385toByteimpl(long j2) {
        return (byte) j2;
    }

    @InlineOnly
    /* renamed from: toDouble-impl */
    private static final double m386toDoubleimpl(long j2) {
        return UnsignedKt.ulongToDouble(j2);
    }

    @InlineOnly
    /* renamed from: toFloat-impl */
    private static final float m387toFloatimpl(long j2) {
        return (float) UnsignedKt.ulongToDouble(j2);
    }

    @InlineOnly
    /* renamed from: toInt-impl */
    private static final int m388toIntimpl(long j2) {
        return (int) j2;
    }

    @InlineOnly
    /* renamed from: toLong-impl */
    private static final long m389toLongimpl(long j2) {
        return j2;
    }

    @InlineOnly
    /* renamed from: toShort-impl */
    private static final short m390toShortimpl(long j2) {
        return (short) j2;
    }

    @NotNull
    /* renamed from: toString-impl */
    public static String m391toStringimpl(long j2) {
        return UnsignedKt.ulongToString(j2);
    }

    @InlineOnly
    /* renamed from: toUByte-impl */
    private static final byte m392toUByteimpl(long j2) {
        return UByte.m218constructorimpl((byte) j2);
    }

    @InlineOnly
    /* renamed from: toUInt-impl */
    private static final int m393toUIntimpl(long j2) {
        return UInt.m285constructorimpl((int) j2);
    }

    @InlineOnly
    /* renamed from: toULong-impl */
    private static final long m394toULongimpl(long j2) {
        return j2;
    }

    @InlineOnly
    /* renamed from: toUShort-impl */
    private static final short m395toUShortimpl(long j2) {
        return UShort.m451constructorimpl((short) j2);
    }

    @InlineOnly
    /* renamed from: xor-VKZWuLQ */
    private static final long m396xorVKZWuLQ(long j2, long j3) {
        return m354constructorimpl(j2 ^ j3);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return m350compareToVKZWuLQ(uLong.getData());
    }

    public boolean equals(Object other) {
        return m360equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m362hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m391toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: from getter */
    public final /* synthetic */ long getData() {
        return this.data;
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ */
    private static int m351compareToVKZWuLQ(long j2, long j3) {
        return UnsignedKt.ulongCompare(j2, j3);
    }
}
