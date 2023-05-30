package c.t.m.g;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class o3 {
    public static final int[] d = {31, 113, 239, R2.attr.arrowStartPosition, R2.attr.banner_support_touch_interrupt, R2.attr.chatfrom_bg, R2.attr.clearsTag, R2.attr.dotColor, R2.attr.exTabSelectedTextSize, R2.attr.haveBottomDiv};
    public byte[] a = new byte[0];
    public a[] b;

    /* renamed from: c  reason: collision with root package name */
    public byte[] f571c;

    /* loaded from: classes.dex */
    public static class a {
        public int a;
        public int b;

        public a(int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }

        public int a(String str) {
            int length = str.length();
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                i2 = str.charAt(i3) + (this.b * i2);
            }
            return (this.a - 1) & i2;
        }
    }

    public o3(int i2, int i3) {
        int i4 = 0;
        this.b = new a[Math.min(Math.max(1, i3), d.length)];
        while (true) {
            a[] aVarArr = this.b;
            if (i4 >= aVarArr.length) {
                this.f571c = new byte[i2];
                return;
            } else {
                aVarArr[i4] = new a(i2 * 8, d[i4]);
                i4++;
            }
        }
    }

    public void a(String str) {
        synchronized (this.a) {
            for (a aVar : this.b) {
                s2.a(this.f571c, aVar.a(str), true);
            }
        }
    }

    public void b(byte[] bArr) {
        synchronized (this.a) {
            if (bArr != null) {
                System.arraycopy(bArr, 0, this.f571c, 0, Math.min(bArr.length, this.f571c.length));
            }
        }
    }

    public byte[] c() {
        byte[] bArr;
        synchronized (this.a) {
            bArr = this.f571c;
        }
        return bArr;
    }

    public boolean d(String str) {
        synchronized (this.a) {
            if (str == null) {
                return false;
            }
            for (a aVar : this.b) {
                if (!s2.b(this.f571c, aVar.a(str))) {
                    return false;
                }
            }
            return true;
        }
    }
}
