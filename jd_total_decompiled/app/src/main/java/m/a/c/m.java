package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class m implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    private e f20383g;

    public m(m.a.b.g gVar) throws IOException {
        this.f20383g = e.parse(gVar.e());
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        this.f20383g.encode(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public void b(String str) throws IOException {
        if (str.equalsIgnoreCase(j1.ALG_ID)) {
            this.f20383g = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateAlgorithmId.");
    }

    public Object c(String str) throws IOException {
        if (str.equalsIgnoreCase(j1.ALG_ID)) {
            return this.f20383g;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateAlgorithmId.");
    }

    public void d(String str, Object obj) throws IOException {
        if (obj instanceof e) {
            if (str.equalsIgnoreCase(j1.ALG_ID)) {
                this.f20383g = (e) obj;
                return;
            }
            throw new IOException("Attribute name not recognized by CertAttrSet:CertificateAlgorithmId.");
        }
        throw new IOException("Attribute must be of type AlgorithmId.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "algorithmID";
    }

    public String toString() {
        if (this.f20383g == null) {
            return "";
        }
        return this.f20383g.toString() + ", OID = " + this.f20383g.getOID().toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }
}
