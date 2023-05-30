package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* loaded from: classes11.dex */
public class l0 extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    h0 f20382j;

    public l0() {
        this.f20382j = null;
        this.f20333g = s0.G;
        this.f20334h = false;
        this.f20382j = new h0();
    }

    private void f() throws IOException {
        h0 h0Var = this.f20382j;
        if (h0Var != null && !h0Var.d()) {
            m.a.b.h hVar = new m.a.b.h();
            this.f20382j.b(hVar);
            this.f20335i = hVar.toByteArray();
            return;
        }
        this.f20335i = null;
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.G;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public Object g(String str) throws IOException {
        if (str.equalsIgnoreCase("issuer_name")) {
            return this.f20382j;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:IssuerAlternativeName.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "IssuerAlternativeName";
    }

    @Override // m.a.c.e0
    public String toString() {
        String str = super.toString() + "IssuerAlternativeName [\n";
        h0 h0Var = this.f20382j;
        if (h0Var == null) {
            str = str + "  null\n";
        } else {
            Iterator<f0> it = h0Var.e().iterator();
            while (it.hasNext()) {
                str = str + "  " + it.next() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        }
        return str + "]\n";
    }

    public l0(Boolean bool, Object obj) throws IOException {
        this.f20382j = null;
        this.f20333g = s0.G;
        this.f20334h = bool.booleanValue();
        byte[] bArr = (byte[]) obj;
        this.f20335i = bArr;
        m.a.b.i iVar = new m.a.b.i(bArr);
        if (iVar.f20295c == null) {
            this.f20382j = new h0();
        } else {
            this.f20382j = new h0(iVar);
        }
    }
}
