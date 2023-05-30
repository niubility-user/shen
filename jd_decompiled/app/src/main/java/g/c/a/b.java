package g.c.a;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;

/* loaded from: classes12.dex */
public final class b implements Comparable<b>, Serializable {
    public static final b MAX = fromString("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff");
    private final long highBits;
    private final long lowBits;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(long j2, long j3) {
        this.highBits = j2;
        this.lowBits = j3;
    }

    private int a(short[] sArr, int i2) {
        int i3 = 0;
        while (i2 < sArr.length && sArr[i2] == 0) {
            i3++;
            i2++;
        }
        return i3;
    }

    private String[] c() {
        short[] f2 = f();
        String[] strArr = new String[f2.length];
        for (int i2 = 0; i2 < f2.length; i2++) {
            strArr[i2] = String.format("%x", Short.valueOf(f2[i2]));
        }
        return strArr;
    }

    private String[] d() {
        short[] f2 = f();
        String[] strArr = new String[f2.length];
        for (int i2 = 0; i2 < f2.length; i2++) {
            strArr[i2] = String.format("%04x", Short.valueOf(f2[i2]));
        }
        return strArr;
    }

    private String e() {
        long j2 = this.lowBits;
        return "::ffff:" + ((int) ((4278190080L & j2) >> 24)) + OrderISVUtil.MONEY_DECIMAL + ((int) ((16711680 & j2) >> 16)) + OrderISVUtil.MONEY_DECIMAL + ((int) ((65280 & j2) >> 8)) + OrderISVUtil.MONEY_DECIMAL + ((int) (j2 & 255));
    }

    private short[] f() {
        short[] sArr = new short[8];
        for (int i2 = 0; i2 < 8; i2++) {
            if (c.d(i2)) {
                sArr[i2] = (short) (65535 & ((this.highBits << (i2 * 16)) >>> 112));
            } else {
                sArr[i2] = (short) (65535 & ((this.lowBits << (i2 * 16)) >>> 112));
            }
        }
        return sArr;
    }

    public static b fromBigInteger(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.compareTo(BigInteger.ZERO) >= 0) {
                if (bigInteger.compareTo(MAX.toBigInteger()) <= 0) {
                    byte[] byteArray = bigInteger.toByteArray();
                    if (byteArray[0] == 0) {
                        return fromByteArray(c.h(Arrays.copyOfRange(byteArray, 1, byteArray.length), 16));
                    }
                    return fromByteArray(c.h(byteArray, 16));
                }
                throw new IllegalArgumentException("bigInteger represents a value bigger than 2^128 - 1");
            }
            throw new IllegalArgumentException("can not construct from negative value");
        }
        throw new IllegalArgumentException("can not construct from [null]");
    }

    public static b fromByteArray(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length == 16) {
                ByteBuffer allocate = ByteBuffer.allocate(16);
                for (byte b : bArr) {
                    allocate.put(b);
                }
                allocate.rewind();
                LongBuffer asLongBuffer = allocate.asLongBuffer();
                return new b(asLongBuffer.get(), asLongBuffer.get());
            }
            throw new IllegalArgumentException("the byte array to construct from should be 16 bytes long");
        }
        throw new IllegalArgumentException("can not construct from [null]");
    }

    public static b fromInetAddress(InetAddress inetAddress) {
        if (inetAddress != null) {
            return fromByteArray(inetAddress.getAddress());
        }
        throw new IllegalArgumentException("can not construct from [null]");
    }

    public static b fromLongs(long j2, long j3) {
        return new b(j2, j3);
    }

    public static b fromString(String str) {
        if (str != null) {
            long[] h2 = h(str, c.b(c.i(str)));
            c.j(h2);
            return c.f(h2);
        }
        throw new IllegalArgumentException("can not parse [null]");
    }

    private String g() {
        String[] c2 = c();
        StringBuilder sb = new StringBuilder();
        int[] b = b();
        int i2 = b[0];
        int i3 = b[1];
        boolean z = i3 > 1;
        for (int i4 = 0; i4 < c2.length; i4++) {
            if (z && i4 == i2) {
                if (i4 == 0) {
                    sb.append("::");
                } else {
                    sb.append(":");
                }
            } else if (i4 <= i2 || i4 >= i2 + i3) {
                sb.append(c2[i4]);
                if (i4 < 7) {
                    sb.append(":");
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    private static long[] h(String str, String str2) {
        try {
            return c.g(str2.split(":"));
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("can not parse [" + str + "]");
        }
    }

    public b add(int i2) {
        long j2 = this.lowBits;
        long j3 = i2 + j2;
        if (i2 >= 0) {
            if (c.e(j3, j2)) {
                return new b(this.highBits + 1, j3);
            }
            return new b(this.highBits, j3);
        } else if (c.e(j2, j3)) {
            return new b(this.highBits - 1, j3);
        } else {
            return new b(this.highBits, j3);
        }
    }

    int[] b() {
        short[] f2 = f();
        int i2 = -1;
        int i3 = 0;
        for (int i4 = 0; i4 < f2.length; i4++) {
            int a = a(f2, i4);
            if (a > i3) {
                i2 = i4;
                i3 = a;
            }
        }
        return new int[]{i2, i3};
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.highBits == bVar.highBits && this.lowBits == bVar.lowBits;
    }

    public long getHighBits() {
        return this.highBits;
    }

    public long getLowBits() {
        return this.lowBits;
    }

    public int hashCode() {
        long j2 = this.lowBits;
        long j3 = this.highBits;
        return (((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) (j3 ^ (j3 >>> 32)));
    }

    public boolean isIPv4Mapped() {
        if (this.highBits == 0) {
            long j2 = this.lowBits;
            if (((-281474976710656L) & j2) == 0 && (j2 & 281470681743360L) == 281470681743360L) {
                return true;
            }
        }
        return false;
    }

    public boolean isLinkLocal() {
        return e.LINK_LOCAL_NETWORK.contains(this);
    }

    public boolean isMulticast() {
        return e.MULTICAST_NETWORK.contains(this);
    }

    public boolean isSiteLocal() {
        return e.SITE_LOCAL_NETWORK.contains(this);
    }

    public b maskWithNetworkMask(g gVar) {
        if (gVar.asPrefixLength() == 128) {
            return this;
        }
        if (gVar.asPrefixLength() == 64) {
            return new b(this.highBits, 0L);
        }
        if (gVar.asPrefixLength() == 0) {
            return new b(0L, 0L);
        }
        if (gVar.asPrefixLength() > 64) {
            return new b(this.highBits, ((-1) << (64 - (gVar.asPrefixLength() - 64))) & this.lowBits);
        }
        return new b(((-1) << (64 - gVar.asPrefixLength())) & this.highBits, 0L);
    }

    public b maximumAddressWithNetworkMask(g gVar) {
        if (gVar.asPrefixLength() == 128) {
            return this;
        }
        if (gVar.asPrefixLength() == 64) {
            return new b(this.highBits, -1L);
        }
        if (gVar.asPrefixLength() > 64) {
            return new b(this.highBits, ((-1) >>> (gVar.asPrefixLength() - 64)) | this.lowBits);
        }
        return new b(this.highBits | ((-1) >>> gVar.asPrefixLength()), -1L);
    }

    public int numberOfLeadingOnes() {
        return new b(this.highBits ^ (-1), (-1) ^ this.lowBits).numberOfLeadingZeroes();
    }

    public int numberOfLeadingZeroes() {
        long j2 = this.highBits;
        if (j2 == 0) {
            return Long.numberOfLeadingZeros(this.lowBits) + 64;
        }
        return Long.numberOfLeadingZeros(j2);
    }

    public int numberOfTrailingOnes() {
        b add = add(1);
        if (add.getLowBits() == 0) {
            return Long.numberOfTrailingZeros(add.getHighBits()) + 64;
        }
        return Long.numberOfTrailingZeros(add.getLowBits());
    }

    public int numberOfTrailingZeroes() {
        long j2 = this.lowBits;
        if (j2 == 0) {
            return Long.numberOfTrailingZeros(this.highBits) + 64;
        }
        return Long.numberOfTrailingZeros(j2);
    }

    public b setBit(int i2) {
        if (i2 < 0 || i2 > 127) {
            throw new IllegalArgumentException("can only set bits in the interval [0, 127]");
        }
        if (i2 < 64) {
            return new b(this.highBits, (1 << i2) | this.lowBits);
        }
        return new b((1 << (i2 - 64)) | this.highBits, this.lowBits);
    }

    public b subtract(int i2) {
        long j2 = this.lowBits;
        long j3 = j2 - i2;
        if (i2 >= 0) {
            if (c.e(j2, j3)) {
                return new b(this.highBits - 1, j3);
            }
            return new b(this.highBits, j3);
        } else if (c.e(j3, j2)) {
            return new b(this.highBits + 1, j3);
        } else {
            return new b(this.highBits, j3);
        }
    }

    public BigInteger toBigInteger() {
        return new BigInteger(1, toByteArray());
    }

    public byte[] toByteArray() {
        return ByteBuffer.allocate(16).putLong(this.highBits).putLong(this.lowBits).array();
    }

    public InetAddress toInetAddress() throws UnknownHostException {
        return Inet6Address.getByName(toString());
    }

    public String toLongString() {
        String[] d = d();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < d.length - 1; i2++) {
            sb.append(d[i2]);
            sb.append(":");
        }
        sb.append(d[d.length - 1]);
        return sb.toString();
    }

    public String toString() {
        if (isIPv4Mapped()) {
            return e();
        }
        return g();
    }

    @Override // java.lang.Comparable
    public int compareTo(b bVar) {
        long j2 = this.highBits;
        long j3 = bVar.highBits;
        if (j2 != j3) {
            if (j2 == j3) {
                return 0;
            }
            return c.e(j2, j3) ? -1 : 1;
        }
        long j4 = this.lowBits;
        long j5 = bVar.lowBits;
        if (j4 == j5) {
            return 0;
        }
        return c.e(j4, j5) ? -1 : 1;
    }
}
