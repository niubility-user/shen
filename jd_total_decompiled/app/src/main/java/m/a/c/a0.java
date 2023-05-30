package m.a.c;

import java.io.IOException;

/* loaded from: classes11.dex */
public class a0 implements g0 {

    /* renamed from: g  reason: collision with root package name */
    private String f20300g;

    public a0(m.a.b.i iVar) throws IOException {
        this.f20300g = iVar.n();
    }

    @Override // m.a.c.g0
    public int a(g0 g0Var) throws UnsupportedOperationException {
        if (g0Var != null && g0Var.getType() == 2) {
            String lowerCase = ((a0) g0Var).c().toLowerCase();
            String lowerCase2 = this.f20300g.toLowerCase();
            if (lowerCase.equals(lowerCase2)) {
                return 0;
            }
            if (lowerCase2.endsWith(lowerCase)) {
                if (lowerCase2.charAt(lowerCase2.lastIndexOf(lowerCase) - 1) == '.') {
                    return 2;
                }
            } else if (lowerCase.endsWith(lowerCase2) && lowerCase.charAt(lowerCase.lastIndexOf(lowerCase2) - 1) == '.') {
                return 1;
            }
            return 3;
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        hVar.j(this.f20300g);
    }

    public String c() {
        return this.f20300g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a0) {
            return this.f20300g.equalsIgnoreCase(((a0) obj).f20300g);
        }
        return false;
    }

    @Override // m.a.c.g0
    public int getType() {
        return 2;
    }

    public int hashCode() {
        return this.f20300g.toUpperCase().hashCode();
    }

    public String toString() {
        return "DNSName: " + this.f20300g;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:
        r0 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public a0(String str) throws IOException {
        if (str != null && str.length() != 0) {
            if (str.indexOf(32) == -1) {
                int i2 = 0;
                if (str.charAt(0) != '.' && str.charAt(str.length() - 1) != '.') {
                    while (i2 < str.length()) {
                        int indexOf = str.indexOf(46, i2);
                        indexOf = indexOf < 0 ? str.length() : indexOf;
                        if (indexOf - i2 >= 1) {
                            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(str.charAt(i2)) < 0) {
                                throw new IOException("DNSName components must begin with a letter");
                            }
                            do {
                                i2++;
                                if (i2 < indexOf) {
                                }
                            } while ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-".indexOf(str.charAt(i2)) >= 0);
                            throw new IOException("DNSName components must consist of letters, digits, and hyphens");
                        }
                        throw new IOException("DNSName SubjectAltNames with empty components are not permitted");
                    }
                    this.f20300g = str;
                    return;
                }
                throw new IOException("DNS names or NameConstraints may not begin or end with a .");
            }
            throw new IOException("DNS names or NameConstraints with blank components are not permitted");
        }
        throw new IOException("DNS name must not be null");
    }
}
