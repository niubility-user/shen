package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class i extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private boolean f20355j;

    /* renamed from: k  reason: collision with root package name */
    private int f20356k;

    private void f() throws IOException {
        if (!this.f20355j && this.f20356k < 0) {
            this.f20335i = null;
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        m.a.b.h hVar2 = new m.a.b.h();
        boolean z = this.f20355j;
        if (z) {
            hVar2.g(z);
        }
        int i2 = this.f20356k;
        if (i2 >= 0) {
            hVar2.k(i2);
        }
        hVar.y((byte) 48, hVar2);
        this.f20335i = hVar.toByteArray();
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.H;
            if (this.f20355j) {
                this.f20334h = true;
            } else {
                this.f20334h = false;
            }
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public Object g(String str) throws IOException {
        if (str.equalsIgnoreCase("is_ca")) {
            return Boolean.valueOf(this.f20355j);
        }
        if (str.equalsIgnoreCase("path_len")) {
            return Integer.valueOf(this.f20356k);
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:BasicConstraints.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "BasicConstraints";
    }

    @Override // m.a.c.e0
    public String toString() {
        String str;
        String str2 = super.toString() + "BasicConstraints:[\n";
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(this.f20355j ? "  CA:true" : "  CA:false");
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        String sb2 = sb.toString();
        if (this.f20356k >= 0) {
            str = sb2 + "  PathLen:" + this.f20356k + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        } else {
            str = sb2 + "  PathLen: undefined\n";
        }
        return str + "]\n";
    }
}
