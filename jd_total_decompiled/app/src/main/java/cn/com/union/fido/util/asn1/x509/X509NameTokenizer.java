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
    */
    public String nextToken() {
        if (this.index == this.value.length()) {
            return null;
        }
        int i2 = this.index + 1;
        this.buf.setLength(0);
        boolean z = false;
        boolean z2 = false;
        while (i2 != this.value.length()) {
            char charAt = this.value.charAt(i2);
            if (charAt == '\"') {
                if (!z) {
                    z2 = !z2;
                    z = false;
                    i2++;
                }
            } else if (z || z2) {
                if (charAt == '#') {
                    StringBuffer stringBuffer = this.buf;
                }
                if (charAt == '+') {
                }
            } else {
                if (charAt != '\\') {
                    if (charAt == this.seperator) {
                        break;
                    }
                    this.buf.append(charAt);
                } else {
                    z = true;
                }
                i2++;
            }
            this.buf.append(charAt);
            z = false;
            i2++;
        }
        this.index = i2;
        return this.buf.toString().trim();
    }
}
