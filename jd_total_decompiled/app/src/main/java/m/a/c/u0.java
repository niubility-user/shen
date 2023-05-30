package m.a.c;

import java.io.IOException;
import java.security.cert.PolicyQualifierInfo;
import java.util.Set;

/* loaded from: classes11.dex */
public class u0 {
    private s a;
    private Set<PolicyQualifierInfo> b;

    public void a(m.a.b.h hVar) throws IOException {
        this.a.a(new m.a.b.h());
        throw null;
    }

    public s b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof u0) {
            this.a.equals(((u0) obj).b());
            throw null;
        }
        return false;
    }

    public int hashCode() {
        return ((this.a.hashCode() + 37) * 37) + this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("  [" + this.a.toString());
        sb.append(this.b + "  ]\n");
        return sb.toString();
    }
}
