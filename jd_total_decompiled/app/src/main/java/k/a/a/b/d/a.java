package k.a.a.b.d;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
public class a implements d {
    private Mac a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private String f20190c;

    public a(String str) {
        this.f20190c = str;
        try {
            Mac mac = Mac.getInstance(str);
            this.a = mac;
            this.b = mac.getMacLength();
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // k.a.a.b.d.d
    public int a() {
        return this.b;
    }

    @Override // k.a.a.b.d.d
    public void b(byte[] bArr) {
        try {
            this.a.init(new SecretKeySpec(bArr, this.f20190c));
        } catch (InvalidKeyException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // k.a.a.b.d.d
    public byte[] c(byte[] bArr) {
        return this.a.doFinal(bArr);
    }

    public byte[] d() {
        return this.a.doFinal();
    }

    public void e(byte[] bArr, int i2, int i3) {
        try {
            this.a.update(bArr, i2, i3);
        } catch (IllegalStateException e2) {
            throw new RuntimeException(e2);
        }
    }
}
