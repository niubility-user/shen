package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class h extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private m0 f20347j;

    /* renamed from: k  reason: collision with root package name */
    private h0 f20348k;

    /* renamed from: l  reason: collision with root package name */
    private z0 f20349l;

    private void f() throws IOException {
        if (this.f20347j == null && this.f20348k == null && this.f20349l == null) {
            this.f20335i = null;
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        m.a.b.h hVar2 = new m.a.b.h();
        if (this.f20347j == null) {
            try {
                if (this.f20348k != null) {
                    m.a.b.h hVar3 = new m.a.b.h();
                    this.f20348k.b(hVar3);
                    hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 1), hVar3);
                }
                if (this.f20349l != null) {
                    m.a.b.h hVar4 = new m.a.b.h();
                    this.f20349l.b(hVar4);
                    hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 2), hVar4);
                }
                hVar.y((byte) 48, hVar2);
                this.f20335i = hVar.toByteArray();
                return;
            } catch (Exception e2) {
                throw new IOException(e2.toString());
            }
        }
        this.f20347j.a(new m.a.b.h());
        throw null;
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.z;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return "AuthorityKeyIdentifier";
    }

    @Override // m.a.c.e0
    public String toString() {
        String str = super.toString() + "AuthorityKeyIdentifier [\n";
        if (this.f20347j == null) {
            if (this.f20348k != null) {
                str = str + this.f20348k.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
            if (this.f20349l != null) {
                str = str + this.f20349l.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
            return str + "]\n";
        }
        new StringBuilder().append(str);
        this.f20347j.toString();
        throw null;
    }
}
