package m.a.b;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.IOException;
import java.io.Serializable;

/* loaded from: classes11.dex */
public final class j implements Serializable {
    private static final long serialVersionUID = 8697030238860181294L;
    private int componentLen;
    private int[] components;

    /* renamed from: g  reason: collision with root package name */
    private volatile transient String f20296g;

    public j(String str) throws IOException {
        int i2 = 0;
        this.componentLen = 0;
        int i3 = 0;
        while (true) {
            int indexOf = str.indexOf(46, i3);
            if (indexOf == -1) {
                break;
            }
            i3 = indexOf + 1;
            this.componentLen++;
        }
        int i4 = this.componentLen + 1;
        this.componentLen = i4;
        this.components = new int[i4];
        int i5 = 0;
        while (true) {
            try {
                int indexOf2 = str.indexOf(46, i2);
                if (indexOf2 != -1) {
                    this.components[i5] = Integer.valueOf(str.substring(i2, indexOf2)).intValue();
                    i2 = indexOf2 + 1;
                    i5++;
                } else {
                    this.components[i5] = Integer.valueOf(str.substring(i2)).intValue();
                    a(this.components, this.componentLen);
                    this.f20296g = str;
                    return;
                }
            } catch (Exception e2) {
                throw new IOException("ObjectIdentifier() -- Invalid format: " + e2.toString());
            }
        }
    }

    private void a(int[] iArr, int i2) throws IOException {
        if (iArr != null && i2 >= 2) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (iArr[i3] < 0) {
                    throw new IOException("ObjectIdentifier() -- oid component #" + (i3 + 1) + " must be non-negative ");
                }
            }
            if (iArr[0] <= 2) {
                if (iArr[0] < 2 && iArr[1] > 39) {
                    throw new IOException("ObjectIdentifier() -- Second oid component is invalid ");
                }
                return;
            }
            throw new IOException("ObjectIdentifier() -- First oid component is invalid ");
        }
        throw new IOException("ObjectIdentifier() -- Must be at least two oid components ");
    }

    private static int c(g gVar) throws IOException {
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = i2 << 7;
            int c2 = gVar.c();
            if (i3 == 0 && c2 == 128) {
                throw new IOException("ObjectIdentifier() -- sub component starts with 0x80");
            }
            i2 = i4 | (c2 & 127);
            if ((c2 & 128) == 0) {
                return i2;
            }
        }
        throw new IOException("ObjectIdentifier() -- component value too big");
    }

    private void d(g gVar, int i2) throws IOException {
        this.components = new int[5];
        this.componentLen = 0;
        boolean z = true;
        while (gVar.a() > i2) {
            int c2 = c(gVar);
            if (c2 < 0) {
                throw new IOException("ObjectIdentifier() -- component values must be nonnegative");
            }
            if (z) {
                int i3 = c2 < 40 ? 0 : c2 < 80 ? 1 : 2;
                int[] iArr = this.components;
                iArr[0] = i3;
                iArr[1] = c2 - (i3 * 40);
                this.componentLen = 2;
                z = false;
            } else {
                int i4 = this.componentLen;
                int[] iArr2 = this.components;
                if (i4 >= iArr2.length) {
                    int[] iArr3 = new int[iArr2.length + 5];
                    System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
                    this.components = iArr3;
                }
                int[] iArr4 = this.components;
                int i5 = this.componentLen;
                this.componentLen = i5 + 1;
                iArr4[i5] = c2;
            }
        }
        a(this.components, this.componentLen);
        if (gVar.a() != i2) {
            throw new IOException("ObjectIdentifier() -- malformed input data");
        }
    }

    private static void e(h hVar, int i2) throws IOException {
        byte[] bArr = new byte[4];
        int i3 = 0;
        while (i3 < 4) {
            bArr[i3] = (byte) (i2 & 127);
            i2 >>>= 7;
            if (i2 == 0) {
                break;
            }
            i3++;
        }
        while (i3 > 0) {
            hVar.write(bArr[i3] | 128);
            i3--;
        }
        hVar.write(bArr[0]);
    }

    public static j newInternal(int[] iArr) {
        return new j(iArr, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(h hVar) throws IOException {
        h hVar2 = new h();
        int[] iArr = this.components;
        if (iArr[0] < 2) {
            hVar2.write((iArr[0] * 40) + iArr[1]);
        } else {
            e(hVar2, (iArr[0] * 40) + iArr[1]);
        }
        for (int i2 = 2; i2 < this.componentLen; i2++) {
            e(hVar2, this.components[i2]);
        }
        hVar.y((byte) 6, hVar2);
    }

    @Deprecated
    public boolean equals(j jVar) {
        return equals((Object) jVar);
    }

    public int hashCode() {
        int i2 = this.componentLen;
        for (int i3 = 0; i3 < this.componentLen; i3++) {
            i2 += this.components[i3] * 37;
        }
        return i2;
    }

    public boolean precedes(j jVar) {
        int i2;
        int i3;
        if (jVar != this && (i2 = this.componentLen) >= (i3 = jVar.componentLen)) {
            if (i3 < i2) {
                return true;
            }
            for (int i4 = 0; i4 < this.componentLen; i4++) {
                if (jVar.components[i4] < this.components[i4]) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        String str = this.f20296g;
        if (str == null) {
            StringBuffer stringBuffer = new StringBuffer(this.componentLen * 4);
            for (int i2 = 0; i2 < this.componentLen; i2++) {
                if (i2 != 0) {
                    stringBuffer.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
                }
                stringBuffer.append(this.components[i2]);
            }
            String stringBuffer2 = stringBuffer.toString();
            this.f20296g = stringBuffer2;
            return stringBuffer2;
        }
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (this.componentLen != jVar.componentLen) {
                return false;
            }
            for (int i2 = 0; i2 < this.componentLen; i2++) {
                if (this.components[i2] != jVar.components[i2]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public j(int[] iArr) throws IOException {
        a(iArr, iArr.length);
        this.components = (int[]) iArr.clone();
        this.componentLen = iArr.length;
    }

    public j(g gVar) throws IOException {
        byte c2 = (byte) gVar.c();
        if (c2 == 6) {
            int a = (gVar.a() - gVar.h()) - 1;
            if (a >= 0) {
                d(gVar, a);
                return;
            }
            throw new IOException("ObjectIdentifier() -- not enough data");
        }
        throw new IOException("ObjectIdentifier() -- data isn't an object ID (tag = " + ((int) c2) + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(f fVar) throws IOException {
        d(new g(fVar), 0);
    }

    private j(int[] iArr, boolean z) {
        this.components = iArr;
        this.componentLen = iArr.length;
    }
}
