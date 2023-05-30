package m.a.c;

import com.jdcn.biz.client.BankCardConstants;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class o extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private h0 f20390j;

    private void f() throws IOException {
        h0 h0Var = this.f20390j;
        if (h0Var != null && !h0Var.d()) {
            m.a.b.h hVar = new m.a.b.h();
            this.f20390j.b(hVar);
            this.f20335i = hVar.toByteArray();
            return;
        }
        this.f20335i = null;
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.R;
            this.f20334h = true;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public Object g(String str) throws IOException {
        if (str.equalsIgnoreCase(BankCardConstants.KEY_ISSUER)) {
            return this.f20390j;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateIssuer");
    }

    @Override // m.a.c.l
    public String getName() {
        return "CertificateIssuer";
    }

    @Override // m.a.c.e0
    public String toString() {
        return super.toString() + "Certificate Issuer [\n" + String.valueOf(this.f20390j) + "]\n";
    }
}
