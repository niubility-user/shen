package m.a.c;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class n0 extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private boolean[] f20389j;

    public n0(boolean[] zArr) throws IOException {
        this.f20389j = zArr;
        this.f20333g = s0.B;
        this.f20334h = true;
        f();
    }

    private void f() throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        hVar.v(new m.a.b.a(this.f20389j));
        this.f20335i = hVar.toByteArray();
    }

    private boolean i(int i2) {
        return this.f20389j[i2];
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.B;
            this.f20334h = true;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public Object g(String str) throws IOException {
        if (str.equalsIgnoreCase("digital_signature")) {
            return Boolean.valueOf(i(0));
        }
        if (str.equalsIgnoreCase("non_repudiation")) {
            return Boolean.valueOf(i(1));
        }
        if (str.equalsIgnoreCase("key_encipherment")) {
            return Boolean.valueOf(i(2));
        }
        if (str.equalsIgnoreCase("data_encipherment")) {
            return Boolean.valueOf(i(3));
        }
        if (str.equalsIgnoreCase("key_agreement")) {
            return Boolean.valueOf(i(4));
        }
        if (str.equalsIgnoreCase("key_certsign")) {
            return Boolean.valueOf(i(5));
        }
        if (str.equalsIgnoreCase("crl_sign")) {
            return Boolean.valueOf(i(6));
        }
        if (str.equalsIgnoreCase("encipher_only")) {
            return Boolean.valueOf(i(7));
        }
        if (str.equalsIgnoreCase("decipher_only")) {
            return Boolean.valueOf(i(8));
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:KeyUsage.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "KeyUsage";
    }

    public boolean[] h() {
        return (boolean[]) this.f20389j.clone();
    }

    @Override // m.a.c.e0
    public String toString() {
        String str = super.toString() + "KeyUsage [\n";
        try {
            if (i(0)) {
                str = str + "  DigitalSignature\n";
            }
            if (i(1)) {
                str = str + "  Non_repudiation\n";
            }
            if (i(2)) {
                str = str + "  Key_Encipherment\n";
            }
            if (i(3)) {
                str = str + "  Data_Encipherment\n";
            }
            if (i(4)) {
                str = str + "  Key_Agreement\n";
            }
            if (i(5)) {
                str = str + "  Key_CertSign\n";
            }
            if (i(6)) {
                str = str + "  Crl_Sign\n";
            }
            if (i(7)) {
                str = str + "  Encipher_Only\n";
            }
            if (i(8)) {
                str = str + "  Decipher_Only\n";
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
        return str + "]\n";
    }

    public n0() {
        this.f20333g = s0.B;
        this.f20334h = true;
        this.f20389j = new boolean[0];
    }
}
