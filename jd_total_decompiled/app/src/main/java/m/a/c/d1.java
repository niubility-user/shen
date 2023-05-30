package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;

/* loaded from: classes11.dex */
public class d1 {
    private m.a.b.a a;

    public d1(m.a.b.i iVar) throws IOException {
        this.a = iVar.u(true);
    }

    public void a(m.a.b.h hVar, byte b) throws IOException {
        byte[] g2 = this.a.g();
        hVar.write(b);
        hVar.n(g2.length + 1);
        hVar.write((g2.length * 8) - this.a.b());
        hVar.write(g2);
    }

    public boolean[] b() {
        m.a.b.a aVar = this.a;
        if (aVar == null) {
            return null;
        }
        return aVar.f();
    }

    public String toString() {
        return "UniqueIdentity:" + this.a.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }
}
