package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* loaded from: classes12.dex */
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 4611686018427387904L;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static final class AsciiDigits {
        private static final byte[] asciiDigits;

        static {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int i2 = 0; i2 <= 9; i2++) {
                bArr[i2 + 48] = (byte) i2;
            }
            for (int i3 = 0; i3 <= 26; i3++) {
                byte b = (byte) (i3 + 10);
                bArr[i3 + 65] = b;
                bArr[i3 + 97] = b;
            }
            asciiDigits = bArr;
        }

        private AsciiDigits() {
        }

        static int digit(char c2) {
            if (c2 < '\u0080') {
                return asciiDigits[c2];
            }
            return -1;
        }
    }

    /* loaded from: classes12.dex */
    private enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Longs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int i2 = 0; i2 < min; i2++) {
                int compare = Longs.compare(jArr[i2], jArr2[i2]);
                if (compare != 0) {
                    return compare;
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    @GwtCompatible
    /* loaded from: classes12.dex */
    private static class LongArrayAsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final long[] array;
        final int end;
        final int start;

        LongArrayAsList(long[] jArr) {
            this(jArr, 0, jArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Long) && Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof LongArrayAsList) {
                LongArrayAsList longArrayAsList = (LongArrayAsList) obj;
                int size = size();
                if (longArrayAsList.size() != size) {
                    return false;
                }
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.array[this.start + i2] != longArrayAsList.array[longArrayAsList.start + i2]) {
                        return false;
                    }
                }
                return true;
            }
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i2 = 1;
            for (int i3 = this.start; i3 < this.end; i3++) {
                i2 = (i2 * 31) + Longs.hashCode(this.array[i3]);
            }
            return i2;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int indexOf;
            if (!(obj instanceof Long) || (indexOf = Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return indexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Long) || (lastIndexOf = Longs.lastIndexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return lastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Long> subList(int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, size());
            if (i2 == i3) {
                return Collections.emptyList();
            }
            long[] jArr = this.array;
            int i4 = this.start;
            return new LongArrayAsList(jArr, i2 + i4, i4 + i3);
        }

        long[] toLongArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 10);
            sb.append('[');
            sb.append(this.array[this.start]);
            int i2 = this.start;
            while (true) {
                i2++;
                if (i2 < this.end) {
                    sb.append(", ");
                    sb.append(this.array[i2]);
                } else {
                    sb.append(']');
                    return sb.toString();
                }
            }
        }

        LongArrayAsList(long[] jArr, int i2, int i3) {
            this.array = jArr;
            this.start = i2;
            this.end = i3;
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int i2) {
            Preconditions.checkElementIndex(i2, size());
            return Long.valueOf(this.array[this.start + i2]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Long set(int i2, Long l2) {
            Preconditions.checkElementIndex(i2, size());
            long[] jArr = this.array;
            int i3 = this.start;
            long j2 = jArr[i3 + i2];
            jArr[i3 + i2] = ((Long) Preconditions.checkNotNull(l2)).longValue();
            return Long.valueOf(j2);
        }
    }

    /* loaded from: classes12.dex */
    private static final class LongConverter extends Converter<String, Long> implements Serializable {
        static final LongConverter INSTANCE = new LongConverter();
        private static final long serialVersionUID = 1;

        private LongConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Longs.stringConverter()";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public String doBackward(Long l2) {
            return l2.toString();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public Long doForward(String str) {
            return Long.decode(str);
        }
    }

    private Longs() {
    }

    public static List<Long> asList(long... jArr) {
        if (jArr.length == 0) {
            return Collections.emptyList();
        }
        return new LongArrayAsList(jArr);
    }

    public static int compare(long j2, long j3) {
        if (j2 < j3) {
            return -1;
        }
        return j2 > j3 ? 1 : 0;
    }

    public static long[] concat(long[]... jArr) {
        int i2 = 0;
        for (long[] jArr2 : jArr) {
            i2 += jArr2.length;
        }
        long[] jArr3 = new long[i2];
        int i3 = 0;
        for (long[] jArr4 : jArr) {
            System.arraycopy(jArr4, 0, jArr3, i3, jArr4.length);
            i3 += jArr4.length;
        }
        return jArr3;
    }

    @Beta
    public static long constrainToRange(long j2, long j3, long j4) {
        Preconditions.checkArgument(j3 <= j4, "min (%s) must be less than or equal to max (%s)", j3, j4);
        return Math.min(Math.max(j2, j3), j4);
    }

    public static boolean contains(long[] jArr, long j2) {
        for (long j3 : jArr) {
            if (j3 == j2) {
                return true;
            }
        }
        return false;
    }

    public static long[] ensureCapacity(long[] jArr, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0, "Invalid minLength: %s", i2);
        Preconditions.checkArgument(i3 >= 0, "Invalid padding: %s", i3);
        return jArr.length < i2 ? Arrays.copyOf(jArr, i2 + i3) : jArr;
    }

    public static long fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 8, "array too small: %s < %s", bArr.length, 8);
        return fromBytes(bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]);
    }

    public static long fromBytes(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((b2 & 255) << 48) | ((b & 255) << 56) | ((b3 & 255) << 40) | ((b4 & 255) << 32) | ((b5 & 255) << 24) | ((b6 & 255) << 16) | ((b7 & 255) << 8) | (b8 & 255);
    }

    public static int hashCode(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    public static int indexOf(long[] jArr, long j2) {
        return indexOf(jArr, j2, 0, jArr.length);
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 10);
        sb.append(jArr[0]);
        for (int i2 = 1; i2 < jArr.length; i2++) {
            sb.append(str);
            sb.append(jArr[i2]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(long[] jArr, long j2) {
        return lastIndexOf(jArr, j2, 0, jArr.length);
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j2 = jArr[0];
        for (int i2 = 1; i2 < jArr.length; i2++) {
            if (jArr[i2] > j2) {
                j2 = jArr[i2];
            }
        }
        return j2;
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j2 = jArr[0];
        for (int i2 = 1; i2 < jArr.length; i2++) {
            if (jArr[i2] < j2) {
                j2 = jArr[i2];
            }
        }
        return j2;
    }

    public static void reverse(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        reverse(jArr, 0, jArr.length);
    }

    public static void sortDescending(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sortDescending(jArr, 0, jArr.length);
    }

    @Beta
    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList) collection).toLongArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = ((Number) Preconditions.checkNotNull(array[i2])).longValue();
        }
        return jArr;
    }

    public static byte[] toByteArray(long j2) {
        byte[] bArr = new byte[8];
        for (int i2 = 7; i2 >= 0; i2--) {
            bArr[i2] = (byte) (255 & j2);
            j2 >>= 8;
        }
        return bArr;
    }

    @Beta
    @NullableDecl
    public static Long tryParse(String str) {
        return tryParse(str, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(long[] jArr, long j2, int i2, int i3) {
        while (i2 < i3) {
            if (jArr[i2] == j2) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(long[] jArr, long j2, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            if (jArr[i4] == j2) {
                return i4;
            }
        }
        return -1;
    }

    @Beta
    @NullableDecl
    public static Long tryParse(String str, int i2) {
        if (((String) Preconditions.checkNotNull(str)).isEmpty()) {
            return null;
        }
        if (i2 >= 2 && i2 <= 36) {
            int i3 = str.charAt(0) == '-' ? 1 : 0;
            if (i3 == str.length()) {
                return null;
            }
            int i4 = i3 + 1;
            int digit = AsciiDigits.digit(str.charAt(i3));
            if (digit < 0 || digit >= i2) {
                return null;
            }
            long j2 = -digit;
            long j3 = i2;
            long j4 = Long.MIN_VALUE / j3;
            while (i4 < str.length()) {
                int i5 = i4 + 1;
                int digit2 = AsciiDigits.digit(str.charAt(i4));
                if (digit2 < 0 || digit2 >= i2 || j2 < j4) {
                    return null;
                }
                long j5 = j2 * j3;
                long j6 = digit2;
                if (j5 < j6 - Long.MIN_VALUE) {
                    return null;
                }
                j2 = j5 - j6;
                i4 = i5;
            }
            if (i3 != 0) {
                return Long.valueOf(j2);
            }
            if (j2 == Long.MIN_VALUE) {
                return null;
            }
            return Long.valueOf(-j2);
        }
        throw new IllegalArgumentException("radix must be between MIN_RADIX and MAX_RADIX but was " + i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(long[] r8, long[] r9) {
        /*
            java.lang.String r0 = "array"
            com.google.common.base.Preconditions.checkNotNull(r8, r0)
            java.lang.String r0 = "target"
            com.google.common.base.Preconditions.checkNotNull(r9, r0)
            int r0 = r9.length
            r1 = 0
            if (r0 != 0) goto Lf
            return r1
        Lf:
            r0 = 0
        L10:
            int r2 = r8.length
            int r3 = r9.length
            int r2 = r2 - r3
            int r2 = r2 + 1
            if (r0 >= r2) goto L2c
            r2 = 0
        L18:
            int r3 = r9.length
            if (r2 >= r3) goto L2b
            int r3 = r0 + r2
            r3 = r8[r3]
            r5 = r9[r2]
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L28
            int r0 = r0 + 1
            goto L10
        L28:
            int r2 = r2 + 1
            goto L18
        L2b:
            return r0
        L2c:
            r8 = -1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Longs.indexOf(long[], long[]):int");
    }

    public static void reverse(long[] jArr, int i2, int i3) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i2, i3, jArr.length);
        for (int i4 = i3 - 1; i2 < i4; i4--) {
            long j2 = jArr[i2];
            jArr[i2] = jArr[i4];
            jArr[i4] = j2;
            i2++;
        }
    }

    public static void sortDescending(long[] jArr, int i2, int i3) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i2, i3, jArr.length);
        Arrays.sort(jArr, i2, i3);
        reverse(jArr, i2, i3);
    }
}
