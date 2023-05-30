package c.t.m.g;

import com.jd.dynamic.DYConstants;
import java.io.ByteArrayOutputStream;
import java.util.Random;

/* loaded from: classes.dex */
public class f6 {
    public static final Random a = new Random();
    public static final a b = new a();

    /* loaded from: classes.dex */
    public static class a {
        public x5 a = new x5();
        public String b = "";

        /* renamed from: c */
        public boolean f421c = false;

        public a() {
            a();
        }

        public void a() {
            c(k2.d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMi0gKCzapHg05OXTOlWf9sT20tJJ8C+h41HJZI+nGU2h4sdirRTUB7wdwRR1w604QZJmn55p4S9xBRVCZWIXX2kWmekr90vvvpQow55PYk1JyGXKz7a+yzQxmyEIsD4mtw+M7G76YQrgrjD42EcGH453xTUTdJGwjrn/eCJng6QIDAQAB"), "0000");
        }

        public void b(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, byte[] bArr2) {
            if (bArr2.length > 64) {
                throw new IllegalArgumentException("pwdAesBytes only support length [16/32/64].");
            }
            try {
                byteArrayOutputStream.write(this.b.getBytes("UTF-8"));
                this.a.c(byteArrayOutputStream, bArr2);
                byteArrayOutputStream.write(f5.g(bArr, bArr2, bArr2));
            } catch (Throwable unused) {
            }
        }

        public void c(byte[] bArr, String str) {
            StringBuilder sb = new StringBuilder("setPublicKey:");
            sb.append(bArr.length);
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(str);
            sb.append(", ignored:");
            sb.append(this.f421c);
            if (str.length() != 4 || t2.e(bArr)) {
                a();
                return;
            }
            this.b = str;
            this.a.e(bArr);
        }

        public byte[] d(byte[] bArr, byte[] bArr2, boolean z) {
            if (z) {
                bArr = f4.b(bArr);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            b(byteArrayOutputStream, bArr, bArr2);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static void a(byte[] bArr, String str) {
        b.c(bArr, str);
    }

    public static byte[] b(int i2) {
        byte[] bArr = new byte[i2];
        a.nextBytes(bArr);
        return bArr;
    }

    public static byte[] c(byte[] bArr, boolean z) {
        return d(bArr, b(16), z);
    }

    public static byte[] d(byte[] bArr, byte[] bArr2, boolean z) {
        return b.d(bArr, bArr2, z);
    }
}
