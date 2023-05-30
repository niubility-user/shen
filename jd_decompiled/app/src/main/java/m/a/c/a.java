package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes11.dex */
public class a implements m.a.b.d {

    /* renamed from: i  reason: collision with root package name */
    private static final m.a.b.c f20297i = m.a.b.c.c(j1.NAME, "\t[AVA]");

    /* renamed from: g  reason: collision with root package name */
    final m.a.b.j f20298g;

    /* renamed from: h  reason: collision with root package name */
    final m.a.b.i f20299h;

    static {
        ((Boolean) AccessController.doPrivileged(new m.a.a.a("com.sun.security.preserveOldDCEncoding"))).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(m.a.b.i iVar) throws IOException {
        if (iVar.a == 48) {
            this.f20298g = f1.i(iVar.f20295c.k());
            this.f20299h = iVar.f20295c.e();
            if (iVar.f20295c.a() == 0) {
                return;
            }
            throw new IOException("AVA, extra bytes = " + iVar.f20295c.a());
        }
        throw new IOException("AVA not a sequence");
    }

    private static boolean b(m.a.b.i iVar, boolean z) {
        if (z) {
            byte b = iVar.a;
            return b == 12 || b == 19;
        }
        byte b2 = iVar.a;
        return b2 == 12 || b2 == 22 || b2 == 27 || b2 == 30 || b2 == 19 || b2 == 20;
    }

    private String c(int i2, Map<String, String> map) {
        return c.a(this.f20298g, i2, map);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b6, code lost:
        if (r4 != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b8, code lost:
        if (r7 == ' ') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ba, code lost:
        if (r7 == '\n') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c0, code lost:
        if (",+=\n<>#;\\\"".indexOf(r7) < 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c2, code lost:
        r5 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String d(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m.a.c.a.d(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return c.b(this.f20298g, 3);
    }

    @Override // m.a.b.d
    public void derEncode(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        m.a.b.h hVar2 = new m.a.b.h();
        hVar.p(this.f20298g);
        this.f20299h.d(hVar);
        hVar2.y((byte) 48, hVar);
        outputStream.write(hVar2.toByteArray());
    }

    public String e() {
        StringBuilder sb = new StringBuilder(40);
        sb.append(c(3, Collections.emptyMap()));
        sb.append('=');
        if ((sb.charAt(0) >= '0' && sb.charAt(0) <= '9') || !b(this.f20299h, true)) {
            try {
                byte[] D = this.f20299h.D();
                sb.append('#');
                for (byte b : D) {
                    sb.append(Character.forDigit((b >>> 4) & 15, 16));
                    sb.append(Character.forDigit(b & 15, 16));
                }
            } catch (IOException unused) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        } else {
            try {
                String str = new String(this.f20299h.l(), "UTF8");
                StringBuilder sb2 = new StringBuilder();
                boolean z = false;
                for (int i2 = 0; i2 < str.length(); i2++) {
                    char charAt = str.charAt(i2);
                    if (!m.a.b.i.A(charAt) && ",+<>;\"\\".indexOf(charAt) < 0 && (i2 != 0 || charAt != '#')) {
                        if (f20297i != null && m.a.b.c.d("ava")) {
                            try {
                                byte[] bytes = Character.toString(charAt).getBytes("UTF8");
                                for (int i3 = 0; i3 < bytes.length; i3++) {
                                    sb2.append('\\');
                                    sb2.append(Character.forDigit((bytes[i3] >>> 4) & 15, 16));
                                    sb2.append(Character.forDigit(bytes[i3] & 15, 16));
                                }
                            } catch (IOException unused2) {
                                throw new IllegalArgumentException("DER Value conversion");
                            }
                        } else {
                            sb2.append(charAt);
                        }
                    } else {
                        if ((i2 == 0 && charAt == '#') || ",+<>;\"\\".indexOf(charAt) >= 0) {
                            sb2.append('\\');
                        }
                        if (Character.isWhitespace(charAt)) {
                            if (!z) {
                                sb2.append(charAt);
                                z = true;
                            }
                        } else {
                            sb2.append(charAt);
                        }
                    }
                    z = false;
                }
                sb.append(sb2.toString().trim());
            } catch (IOException unused3) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        }
        String sb3 = sb.toString();
        Locale locale = Locale.US;
        return sb3.toUpperCase(locale).toLowerCase(locale);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            return e().equals(((a) obj).e());
        }
        return false;
    }

    public String f(Map<String, String> map) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(c(3, map));
        sb.append('=');
        int i2 = 0;
        if ((sb.charAt(0) >= '0' && sb.charAt(0) <= '9') || !b(this.f20299h, false)) {
            try {
                byte[] D = this.f20299h.D();
                sb.append('#');
                while (i2 < D.length) {
                    byte b = D[i2];
                    sb.append(Character.forDigit((b >>> 4) & 15, 16));
                    sb.append(Character.forDigit(b & 15, 16));
                    i2++;
                }
            } catch (IOException unused) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        } else {
            try {
                String str = new String(this.f20299h.l(), "UTF8");
                StringBuilder sb2 = new StringBuilder();
                for (int i3 = 0; i3 < str.length(); i3++) {
                    char charAt = str.charAt(i3);
                    if (!m.a.b.i.A(charAt) && ",=+<>#;\"\\".indexOf(charAt) < 0) {
                        if (f20297i != null && m.a.b.c.d("ava")) {
                            try {
                                byte[] bytes = Character.toString(charAt).getBytes("UTF8");
                                for (int i4 = 0; i4 < bytes.length; i4++) {
                                    sb2.append('\\');
                                    sb2.append(Character.toUpperCase(Character.forDigit((bytes[i4] >>> 4) & 15, 16)));
                                    sb2.append(Character.toUpperCase(Character.forDigit(bytes[i4] & 15, 16)));
                                }
                            } catch (IOException unused2) {
                                throw new IllegalArgumentException("DER Value conversion");
                            }
                        } else {
                            sb2.append(charAt);
                        }
                    } else {
                        if (",=+<>#;\"\\".indexOf(charAt) >= 0) {
                            sb2.append('\\');
                        }
                        sb2.append(charAt);
                    }
                }
                char[] charArray = sb2.toString().toCharArray();
                StringBuilder sb3 = new StringBuilder();
                int i5 = 0;
                while (i5 < charArray.length && (charArray[i5] == ' ' || charArray[i5] == '\r')) {
                    i5++;
                }
                int length = charArray.length - 1;
                while (length >= 0 && (charArray[length] == ' ' || charArray[length] == '\r')) {
                    length--;
                }
                while (i2 < charArray.length) {
                    char c2 = charArray[i2];
                    if (i2 < i5 || i2 > length) {
                        sb3.append('\\');
                    }
                    sb3.append(c2);
                    i2++;
                }
                sb.append(sb3.toString());
            } catch (IOException unused3) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        }
        return sb.toString();
    }

    public int hashCode() {
        return e().hashCode();
    }

    public String toString() {
        return d(c(1, Collections.emptyMap()));
    }
}
