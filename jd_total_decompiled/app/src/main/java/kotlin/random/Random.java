package kotlin.random;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.DoubleCompanionObject;
import org.jetbrains.annotations.NotNull;

@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\r\b'\u0018\u0000 &2\u00020\u0001:\u0002'&B\u0007\u00a2\u0006\u0004\b$\u0010%J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u001f\u0010\u0006\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\u000eJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0016J\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ+\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001f\u0010!J\u0017\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u001f\u0010#\u00a8\u0006("}, d2 = {"Lkotlin/random/Random;", "", "", "bitCount", "nextBits", "(I)I", "nextInt", "()I", "until", "from", "(II)I", "", "nextLong", "()J", "(J)J", "(JJ)J", "", "nextBoolean", "()Z", "", "nextDouble", "()D", "(D)D", "(DD)D", "", "nextFloat", "()F", "", "array", "fromIndex", "toIndex", "nextBytes", "([BII)[B", "([B)[B", ApkDownloadTable.FIELD_SIZE, "(I)[B", "<init>", "()V", "Default", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class Random {

    /* renamed from: Default  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    @JvmField
    @NotNull
    public static final Companion Companion = Companion.INSTANCE;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Default companion object instead")
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00a8\u0006\b"}, d2 = {"Lkotlin/random/Random$Companion;", "Lkotlin/random/Random;", "", "bitCount", "nextBits", "(I)I", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion extends Random {
        public static final Companion INSTANCE = new Companion();

        private Companion() {
        }

        @Override // kotlin.random.Random
        public int nextBits(int bitCount) {
            return Random.INSTANCE.nextBits(bitCount);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b+\u0010(J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u001f\u0010\u0006\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\u000eJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0016J\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u001d\u0010 J'\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u001d\u0010#R\u001c\u0010%\u001a\u00020$8\u0006@\u0007X\u0087\u0004\u00a2\u0006\f\n\u0004\b%\u0010&\u0012\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006,"}, d2 = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "", "bitCount", "nextBits", "(I)I", "nextInt", "()I", "until", "from", "(II)I", "", "nextLong", "()J", "(J)J", "(JJ)J", "", "nextBoolean", "()Z", "", "nextDouble", "()D", "(D)D", "(DD)D", "", "nextFloat", "()F", "", "array", "nextBytes", "([B)[B", ApkDownloadTable.FIELD_SIZE, "(I)[B", "fromIndex", "toIndex", "([BII)[B", "Lkotlin/random/Random$Companion;", "Companion", "Lkotlin/random/Random$Companion;", "Companion$annotations", "()V", "defaultRandom", "Lkotlin/random/Random;", "<init>", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
    /* renamed from: kotlin.random.Random$Default  reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion extends Random {
        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Default companion object instead")
        public static /* synthetic */ void Companion$annotations() {
        }

        @Override // kotlin.random.Random
        public int nextBits(int bitCount) {
            return Random.defaultRandom.nextBits(bitCount);
        }

        @Override // kotlin.random.Random
        public boolean nextBoolean() {
            return Random.defaultRandom.nextBoolean();
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array) {
            return Random.defaultRandom.nextBytes(array);
        }

        @Override // kotlin.random.Random
        public double nextDouble() {
            return Random.defaultRandom.nextDouble();
        }

        @Override // kotlin.random.Random
        public float nextFloat() {
            return Random.defaultRandom.nextFloat();
        }

        @Override // kotlin.random.Random
        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        @Override // kotlin.random.Random
        public long nextLong() {
            return Random.defaultRandom.nextLong();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(int r2) {
            return Random.defaultRandom.nextBytes(r2);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double until) {
            return Random.defaultRandom.nextDouble(until);
        }

        @Override // kotlin.random.Random
        public int nextInt(int until) {
            return Random.defaultRandom.nextInt(until);
        }

        @Override // kotlin.random.Random
        public long nextLong(long until) {
            return Random.defaultRandom.nextLong(until);
        }

        @Override // kotlin.random.Random
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array, int fromIndex, int toIndex) {
            return Random.defaultRandom.nextBytes(array, fromIndex, toIndex);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double from, double until) {
            return Random.defaultRandom.nextDouble(from, until);
        }

        @Override // kotlin.random.Random
        public int nextInt(int from, int until) {
            return Random.defaultRandom.nextInt(from, until);
        }

        @Override // kotlin.random.Random
        public long nextLong(long from, long until) {
            return Random.defaultRandom.nextLong(from, until);
        }
    }

    public static /* synthetic */ byte[] nextBytes$default(Random random, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 2) != 0) {
                i2 = 0;
            }
            if ((i4 & 4) != 0) {
                i3 = bArr.length;
            }
            return random.nextBytes(bArr, i2, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
    }

    public abstract int nextBits(int bitCount);

    public boolean nextBoolean() {
        return nextBits(1) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x0015  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x007f  */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] nextBytes(@NotNull byte[] array, int fromIndex, int toIndex) {
        boolean z;
        int length = array.length;
        if (fromIndex >= 0 && length >= fromIndex) {
            int length2 = array.length;
            if (toIndex >= 0 && length2 >= toIndex) {
                z = true;
                if (z) {
                    throw new IllegalArgumentException(("fromIndex (" + fromIndex + ") or toIndex (" + toIndex + ") are out of range: 0.." + array.length + OrderISVUtil.MONEY_DECIMAL_CHAR).toString());
                }
                if (fromIndex <= toIndex) {
                    int i2 = (toIndex - fromIndex) / 4;
                    for (int i3 = 0; i3 < i2; i3++) {
                        int nextInt = nextInt();
                        array[fromIndex] = (byte) nextInt;
                        array[fromIndex + 1] = (byte) (nextInt >>> 8);
                        array[fromIndex + 2] = (byte) (nextInt >>> 16);
                        array[fromIndex + 3] = (byte) (nextInt >>> 24);
                        fromIndex += 4;
                    }
                    int i4 = toIndex - fromIndex;
                    int nextBits = nextBits(i4 * 8);
                    for (int i5 = 0; i5 < i4; i5++) {
                        array[fromIndex + i5] = (byte) (nextBits >>> (i5 * 8));
                    }
                    return array;
                }
                throw new IllegalArgumentException(("fromIndex (" + fromIndex + ") must be not greater than toIndex (" + toIndex + ").").toString());
            }
        }
        z = false;
        if (z) {
        }
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(nextBits(26), nextBits(27));
    }

    public float nextFloat() {
        return nextBits(24) / 16777216;
    }

    public int nextInt() {
        return nextBits(32);
    }

    public long nextLong() {
        return (nextInt() << 32) + nextInt();
    }

    public double nextDouble(double until) {
        return nextDouble(0.0d, until);
    }

    public int nextInt(int until) {
        return nextInt(0, until);
    }

    public long nextLong(long until) {
        return nextLong(0L, until);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public double nextDouble(double from, double until) {
        double nextDouble;
        RandomKt.checkRangeBounds(from, until);
        double d = until - from;
        if (Double.isInfinite(d)) {
            if ((Double.isInfinite(from) || Double.isNaN(from)) ? false : true) {
                if ((Double.isInfinite(until) || Double.isNaN(until)) ? false : true) {
                    double nextDouble2 = nextDouble();
                    double d2 = 2;
                    Double.isNaN(d2);
                    Double.isNaN(d2);
                    double d3 = nextDouble2 * ((until / d2) - (from / d2));
                    nextDouble = from + d3 + d3;
                    return nextDouble < until ? Math.nextAfter(until, DoubleCompanionObject.INSTANCE.getNEGATIVE_INFINITY()) : nextDouble;
                }
            }
        }
        nextDouble = from + (nextDouble() * d);
        if (nextDouble < until) {
        }
    }

    public int nextInt(int from, int until) {
        int nextInt;
        int i2;
        int i3;
        RandomKt.checkRangeBounds(from, until);
        int i4 = until - from;
        if (i4 > 0 || i4 == Integer.MIN_VALUE) {
            if (((-i4) & i4) == i4) {
                i3 = nextBits(RandomKt.fastLog2(i4));
                return from + i3;
            }
            do {
                nextInt = nextInt() >>> 1;
                i2 = nextInt % i4;
            } while ((nextInt - i2) + (i4 - 1) < 0);
            i3 = i2;
            return from + i3;
        }
        while (true) {
            int nextInt2 = nextInt();
            if (from <= nextInt2 && until > nextInt2) {
                return nextInt2;
            }
        }
    }

    public long nextLong(long from, long until) {
        long nextLong;
        long j2;
        long j3;
        int nextInt;
        RandomKt.checkRangeBounds(from, until);
        long j4 = until - from;
        if (j4 > 0) {
            if (((-j4) & j4) == j4) {
                int i2 = (int) j4;
                int i3 = (int) (j4 >>> 32);
                if (i2 != 0) {
                    nextInt = nextBits(RandomKt.fastLog2(i2));
                } else if (i3 == 1) {
                    nextInt = nextInt();
                } else {
                    j3 = (nextBits(RandomKt.fastLog2(i3)) << 32) + nextInt();
                    return from + j3;
                }
                j3 = nextInt & 4294967295L;
                return from + j3;
            }
            do {
                nextLong = nextLong() >>> 1;
                j2 = nextLong % j4;
            } while ((nextLong - j2) + (j4 - 1) < 0);
            j3 = j2;
            return from + j3;
        }
        while (true) {
            long nextLong2 = nextLong();
            if (from <= nextLong2 && until > nextLong2) {
                return nextLong2;
            }
        }
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] array) {
        return nextBytes(array, 0, array.length);
    }

    @NotNull
    public byte[] nextBytes(int r1) {
        return nextBytes(new byte[r1]);
    }
}
