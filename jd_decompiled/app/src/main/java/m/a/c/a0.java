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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public a0(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            r6.<init>()
            if (r7 == 0) goto L88
            int r0 = r7.length()
            if (r0 == 0) goto L88
            r0 = 32
            int r0 = r7.indexOf(r0)
            r1 = -1
            if (r0 != r1) goto L80
            r0 = 0
            char r1 = r7.charAt(r0)
            r2 = 46
            if (r1 == r2) goto L78
            int r1 = r7.length()
            r3 = 1
            int r1 = r1 - r3
            char r1 = r7.charAt(r1)
            if (r1 == r2) goto L78
        L29:
            int r1 = r7.length()
            if (r0 >= r1) goto L75
            int r1 = r7.indexOf(r2, r0)
            if (r1 >= 0) goto L39
            int r1 = r7.length()
        L39:
            int r4 = r1 - r0
            if (r4 < r3) goto L6d
            char r4 = r7.charAt(r0)
            java.lang.String r5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            int r4 = r5.indexOf(r4)
            if (r4 < 0) goto L65
        L49:
            int r0 = r0 + 1
            if (r0 >= r1) goto L62
            char r4 = r7.charAt(r0)
            java.lang.String r5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-"
            int r4 = r5.indexOf(r4)
            if (r4 < 0) goto L5a
            goto L49
        L5a:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "DNSName components must consist of letters, digits, and hyphens"
            r7.<init>(r0)
            throw r7
        L62:
            int r0 = r1 + 1
            goto L29
        L65:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "DNSName components must begin with a letter"
            r7.<init>(r0)
            throw r7
        L6d:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "DNSName SubjectAltNames with empty components are not permitted"
            r7.<init>(r0)
            throw r7
        L75:
            r6.f20300g = r7
            return
        L78:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "DNS names or NameConstraints may not begin or end with a ."
            r7.<init>(r0)
            throw r7
        L80:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "DNS names or NameConstraints with blank components are not permitted"
            r7.<init>(r0)
            throw r7
        L88:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "DNS name must not be null"
            r7.<init>(r0)
            goto L91
        L90:
            throw r7
        L91:
            goto L90
        */
        throw new UnsupportedOperationException("Method not decompiled: m.a.c.a0.<init>(java.lang.String):void");
    }
}
