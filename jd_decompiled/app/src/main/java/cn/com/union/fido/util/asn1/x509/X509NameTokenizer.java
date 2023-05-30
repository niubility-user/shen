package cn.com.union.fido.util.asn1.x509;

/* loaded from: classes.dex */
public class X509NameTokenizer {
    private StringBuffer buf;
    private int index;
    private char seperator;
    private String value;

    public X509NameTokenizer(String str) {
        this(str, ',');
    }

    public X509NameTokenizer(String str, char c2) {
        this.buf = new StringBuffer();
        this.value = str;
        this.index = -1;
        this.seperator = c2;
    }

    public boolean hasMoreTokens() {
        return this.index != this.value.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0056, code lost:
        if (r2.charAt(r2.length() - 1) == '=') goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0058, code lost:
        r8.buf.append('\\');
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0064, code lost:
        if (r8.seperator != '+') goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String nextToken() {
        /*
            r8 = this;
            int r0 = r8.index
            java.lang.String r1 = r8.value
            int r1 = r1.length()
            if (r0 != r1) goto Lc
            r0 = 0
            return r0
        Lc:
            int r0 = r8.index
            r1 = 1
            int r0 = r0 + r1
            java.lang.StringBuffer r2 = r8.buf
            r3 = 0
            r2.setLength(r3)
            r2 = 0
            r4 = 0
        L18:
            java.lang.String r5 = r8.value
            int r5 = r5.length()
            if (r0 == r5) goto L70
            java.lang.String r5 = r8.value
            char r5 = r5.charAt(r0)
            r6 = 34
            if (r5 != r6) goto L30
            if (r2 != 0) goto L67
            r4 = r4 ^ 1
        L2e:
            r2 = 0
            goto L6d
        L30:
            r6 = 92
            if (r2 != 0) goto L45
            if (r4 == 0) goto L37
            goto L45
        L37:
            if (r5 != r6) goto L3b
            r2 = 1
            goto L6d
        L3b:
            char r6 = r8.seperator
            if (r5 == r6) goto L70
            java.lang.StringBuffer r6 = r8.buf
            r6.append(r5)
            goto L6d
        L45:
            r2 = 35
            if (r5 != r2) goto L5e
            java.lang.StringBuffer r2 = r8.buf
            int r7 = r2.length()
            int r7 = r7 - r1
            char r2 = r2.charAt(r7)
            r7 = 61
            if (r2 != r7) goto L5e
        L58:
            java.lang.StringBuffer r2 = r8.buf
            r2.append(r6)
            goto L67
        L5e:
            r2 = 43
            if (r5 != r2) goto L67
            char r7 = r8.seperator
            if (r7 == r2) goto L67
            goto L58
        L67:
            java.lang.StringBuffer r2 = r8.buf
            r2.append(r5)
            goto L2e
        L6d:
            int r0 = r0 + 1
            goto L18
        L70:
            r8.index = r0
            java.lang.StringBuffer r0 = r8.buf
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.union.fido.util.asn1.x509.X509NameTokenizer.nextToken():java.lang.String");
    }
}
