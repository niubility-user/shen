package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes11.dex */
public class b0 {

    /* renamed from: f  reason: collision with root package name */
    private static final String[] f20303f = {null, "key compromise", "CA compromise", "affiliation changed", "superseded", "cessation of operation", "certificate hold", "privilege withdrawn", "AA compromise"};
    private h0 a;
    private x0 b;

    /* renamed from: c  reason: collision with root package name */
    private boolean[] f20304c;
    private h0 d;

    /* renamed from: e  reason: collision with root package name */
    private volatile int f20305e;

    private static boolean b(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    private static String c(int i2) {
        if (i2 > 0) {
            String[] strArr = f20303f;
            if (i2 < strArr.length) {
                return strArr[i2];
            }
        }
        return "Unknown reason " + i2;
    }

    public void a(m.a.b.h hVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        if (this.a != null || this.b != null) {
            m.a.b.h hVar3 = new m.a.b.h();
            if (this.a != null) {
                m.a.b.h hVar4 = new m.a.b.h();
                this.a.b(hVar4);
                hVar3.A(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 0), hVar4);
            } else if (this.b != null) {
                m.a.b.h hVar5 = new m.a.b.h();
                this.b.a(hVar5);
                hVar3.A(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 1), hVar5);
            }
            hVar2.y(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 0), hVar3);
        }
        if (this.f20304c != null) {
            m.a.b.h hVar6 = new m.a.b.h();
            hVar6.v(new m.a.b.a(this.f20304c));
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 1), hVar6);
        }
        if (this.d != null) {
            m.a.b.h hVar7 = new m.a.b.h();
            this.d.b(hVar7);
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 2), hVar7);
        }
        hVar.y((byte) 48, hVar2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            b0 b0Var = (b0) obj;
            return b(this.a, b0Var.a) && b(this.b, b0Var.b) && b(this.d, b0Var.d) && Arrays.equals(this.f20304c, b0Var.f20304c);
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.f20305e;
        if (i2 == 0) {
            h0 h0Var = this.a;
            int hashCode = h0Var != null ? 1 + h0Var.hashCode() : 1;
            x0 x0Var = this.b;
            if (x0Var != null) {
                hashCode += x0Var.hashCode();
            }
            h0 h0Var2 = this.d;
            if (h0Var2 != null) {
                hashCode += h0Var2.hashCode();
            }
            if (this.f20304c != null) {
                int i3 = 0;
                while (true) {
                    boolean[] zArr = this.f20304c;
                    if (i3 >= zArr.length) {
                        break;
                    }
                    if (zArr[i3]) {
                        hashCode += i3;
                    }
                    i3++;
                }
            }
            int i4 = hashCode;
            this.f20305e = i4;
            return i4;
        }
        return i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.a != null) {
            sb.append("DistributionPoint:\n     " + this.a + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.b != null) {
            sb.append("DistributionPoint:\n     " + this.b + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.f20304c != null) {
            sb.append("   ReasonFlags:\n");
            int i2 = 0;
            while (true) {
                boolean[] zArr = this.f20304c;
                if (i2 >= zArr.length) {
                    break;
                }
                if (zArr[i2]) {
                    sb.append("    " + c(i2) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                i2++;
            }
        }
        if (this.d != null) {
            sb.append("   CRLIssuer:" + this.d + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }
}
