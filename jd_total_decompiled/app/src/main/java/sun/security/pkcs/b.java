package sun.security.pkcs;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import m.a.b.i;
import m.a.c.h0;
import m.a.c.z0;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
class b {
    private static volatile HexDumpEncoder d;
    private byte[] a;
    private h0 b;

    /* renamed from: c  reason: collision with root package name */
    private z0 f20482c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(i iVar) throws IOException {
        this.a = iVar.f20295c.e().D();
        if (iVar.f20295c.a() > 0) {
            i e2 = iVar.f20295c.e();
            this.b = new h0(e2.f20295c.e());
            this.f20482c = new z0(e2.f20295c.e());
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[\n\tCertificate hash (SHA-1):\n");
        if (d == null) {
            d = new HexDumpEncoder();
        }
        stringBuffer.append(d.encode(this.a));
        if (this.b != null && this.f20482c != null) {
            stringBuffer.append("\n\tIssuer: " + this.b + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            StringBuilder sb = new StringBuilder();
            sb.append("\t");
            sb.append(this.f20482c);
            stringBuffer.append(sb.toString());
        }
        stringBuffer.append("\n]");
        return stringBuffer.toString();
    }
}
