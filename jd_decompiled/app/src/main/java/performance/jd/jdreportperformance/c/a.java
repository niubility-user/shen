package performance.jd.jdreportperformance.c;

import javax.security.auth.x500.X500Principal;

/* loaded from: classes11.dex */
final class a {
    private final String a;
    private final int b;

    /* renamed from: c  reason: collision with root package name */
    private int f20430c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f20431e;

    /* renamed from: f  reason: collision with root package name */
    private int f20432f;

    /* renamed from: g  reason: collision with root package name */
    private char[] f20433g;

    public a(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.a = name;
        this.b = name.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a3, code lost:
        return new java.lang.String(r1, r2, r8.f20432f - r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a() {
        /*
            r8 = this;
            int r0 = r8.f20430c
            r8.d = r0
            r8.f20431e = r0
        L6:
            int r0 = r8.f20430c
            int r1 = r8.b
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f20433g
            int r2 = r8.d
            int r3 = r8.f20431e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r8.f20433g
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L60
            if (r2 == r5) goto L53
            r5 = 92
            if (r2 == r5) goto L40
            if (r2 == r4) goto L53
            if (r2 == r3) goto L53
            int r2 = r8.f20431e
            int r3 = r2 + 1
            r8.f20431e = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.f20430c = r0
            goto L6
        L40:
            int r0 = r8.f20431e
            int r2 = r0 + 1
            r8.f20431e = r2
            char r2 = r8.b()
            r1[r0] = r2
            int r0 = r8.f20430c
            int r0 = r0 + 1
            r8.f20430c = r0
            goto L6
        L53:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f20433g
            int r2 = r8.d
            int r3 = r8.f20431e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L60:
            int r2 = r8.f20431e
            r8.f20432f = r2
            int r0 = r0 + 1
            r8.f20430c = r0
            int r0 = r2 + 1
            r8.f20431e = r0
            r1[r2] = r6
        L6e:
            int r0 = r8.f20430c
            int r1 = r8.b
            if (r0 >= r1) goto L87
            char[] r2 = r8.f20433g
            char r7 = r2[r0]
            if (r7 != r6) goto L87
            int r1 = r8.f20431e
            int r7 = r1 + 1
            r8.f20431e = r7
            r2[r1] = r6
            int r0 = r0 + 1
            r8.f20430c = r0
            goto L6e
        L87:
            if (r0 == r1) goto L97
            char[] r1 = r8.f20433g
            char r2 = r1[r0]
            if (r2 == r3) goto L97
            char r2 = r1[r0]
            if (r2 == r4) goto L97
            char r0 = r1[r0]
            if (r0 != r5) goto L6
        L97:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f20433g
            int r2 = r8.d
            int r3 = r8.f20432f
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: performance.jd.jdreportperformance.c.a.a():java.lang.String");
    }

    private char b() {
        int i2 = this.f20430c + 1;
        this.f20430c = i2;
        if (i2 != this.b) {
            char[] cArr = this.f20433g;
            char c2 = cArr[i2];
            if (c2 != ' ' && c2 != '%' && c2 != '\\' && c2 != '_' && c2 != '\"' && c2 != '#') {
                switch (c2) {
                    case '*':
                    case '+':
                    case ',':
                        break;
                    default:
                        switch (c2) {
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                                break;
                            default:
                                return c();
                        }
                }
            }
            return cArr[i2];
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private char c() {
        int i2;
        int i3;
        int a = a(this.f20430c);
        this.f20430c++;
        if (a < 128) {
            return (char) a;
        }
        if (a < 192 || a > 247) {
            return '?';
        }
        if (a <= 223) {
            i3 = a & 31;
            i2 = 1;
        } else if (a <= 239) {
            i2 = 2;
            i3 = a & 15;
        } else {
            i2 = 3;
            i3 = a & 7;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = this.f20430c + 1;
            this.f20430c = i5;
            if (i5 == this.b || this.f20433g[i5] != '\\') {
                return '?';
            }
            int i6 = i5 + 1;
            this.f20430c = i6;
            int a2 = a(i6);
            this.f20430c++;
            if ((a2 & 192) != 128) {
                return '?';
            }
            i3 = (i3 << 6) + (a2 & 63);
        }
        return (char) i3;
    }

    private String d() {
        int i2;
        int i3 = this.f20430c;
        if (i3 + 4 < this.b) {
            this.d = i3;
            this.f20430c = i3 + 1;
            while (true) {
                i2 = this.f20430c;
                if (i2 == this.b) {
                    break;
                }
                char[] cArr = this.f20433g;
                if (cArr[i2] == '+' || cArr[i2] == ',' || cArr[i2] == ';') {
                    break;
                } else if (cArr[i2] == ' ') {
                    this.f20431e = i2;
                    this.f20430c = i2 + 1;
                    while (true) {
                        int i4 = this.f20430c;
                        if (i4 >= this.b || this.f20433g[i4] != ' ') {
                            break;
                        }
                        this.f20430c = i4 + 1;
                    }
                } else {
                    if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                        cArr[i2] = (char) (cArr[i2] + ' ');
                    }
                    this.f20430c = i2 + 1;
                }
            }
            this.f20431e = i2;
            int i5 = this.f20431e;
            int i6 = this.d;
            int i7 = i5 - i6;
            if (i7 >= 5 && (i7 & 1) != 0) {
                int i8 = i7 / 2;
                byte[] bArr = new byte[i8];
                int i9 = i6 + 1;
                for (int i10 = 0; i10 < i8; i10++) {
                    bArr[i10] = (byte) a(i9);
                    i9 += 2;
                }
                return new String(this.f20433g, this.d, i7);
            }
            throw new IllegalStateException("Unexpected end of DN: " + this.a);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private String e() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        while (true) {
            i2 = this.f20430c;
            i3 = this.b;
            if (i2 >= i3 || this.f20433g[i2] != ' ') {
                break;
            }
            this.f20430c = i2 + 1;
        }
        if (i2 == i3) {
            return null;
        }
        this.d = i2;
        this.f20430c = i2 + 1;
        while (true) {
            i4 = this.f20430c;
            i5 = this.b;
            if (i4 >= i5) {
                break;
            }
            char[] cArr = this.f20433g;
            if (cArr[i4] == '=' || cArr[i4] == ' ') {
                break;
            }
            this.f20430c = i4 + 1;
        }
        if (i4 < i5) {
            this.f20431e = i4;
            if (this.f20433g[i4] == ' ') {
                while (true) {
                    i6 = this.f20430c;
                    i7 = this.b;
                    if (i6 >= i7) {
                        break;
                    }
                    char[] cArr2 = this.f20433g;
                    if (cArr2[i6] == '=' || cArr2[i6] != ' ') {
                        break;
                    }
                    this.f20430c = i6 + 1;
                }
                if (this.f20433g[i6] != '=' || i6 == i7) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.a);
                }
            }
            this.f20430c++;
            while (true) {
                int i8 = this.f20430c;
                if (i8 >= this.b || this.f20433g[i8] != ' ') {
                    break;
                }
                this.f20430c = i8 + 1;
            }
            int i9 = this.f20431e;
            int i10 = this.d;
            if (i9 - i10 > 4) {
                char[] cArr3 = this.f20433g;
                if (cArr3[i10 + 3] == '.' && (cArr3[i10] == 'O' || cArr3[i10] == 'o')) {
                    int i11 = i10 + 1;
                    if (cArr3[i11] == 'I' || cArr3[i11] == 'i') {
                        int i12 = i10 + 2;
                        if (cArr3[i12] == 'D' || cArr3[i12] == 'd') {
                            this.d = i10 + 4;
                        }
                    }
                }
            }
            char[] cArr4 = this.f20433g;
            int i13 = this.d;
            return new String(cArr4, i13, this.f20431e - i13);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private String f() {
        int i2 = this.f20430c + 1;
        this.f20430c = i2;
        this.d = i2;
        this.f20431e = i2;
        while (true) {
            int i3 = this.f20430c;
            if (i3 != this.b) {
                char[] cArr = this.f20433g;
                if (cArr[i3] == '\"') {
                    this.f20430c = i3 + 1;
                    while (true) {
                        int i4 = this.f20430c;
                        if (i4 >= this.b || this.f20433g[i4] != ' ') {
                            break;
                        }
                        this.f20430c = i4 + 1;
                    }
                    char[] cArr2 = this.f20433g;
                    int i5 = this.d;
                    return new String(cArr2, i5, this.f20431e - i5);
                }
                if (cArr[i3] == '\\') {
                    cArr[this.f20431e] = b();
                } else {
                    cArr[this.f20431e] = cArr[i3];
                }
                this.f20430c++;
                this.f20431e++;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.a);
            }
        }
    }

    private int a(int i2) {
        int i3;
        int i4;
        int i5 = i2 + 1;
        if (i5 < this.b) {
            char[] cArr = this.f20433g;
            char c2 = cArr[i2];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.a);
            } else {
                i3 = c2 - '7';
            }
            char c3 = cArr[i5];
            if (c3 >= '0' && c3 <= '9') {
                i4 = c3 - '0';
            } else if (c3 >= 'a' && c3 <= 'f') {
                i4 = c3 - 'W';
            } else if (c3 < 'A' || c3 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.a);
            } else {
                i4 = c3 - '7';
            }
            return (i3 << 4) + i4;
        }
        throw new IllegalStateException("Malformed DN: " + this.a);
    }

    public String a(String str) {
        String f2;
        this.f20430c = 0;
        this.d = 0;
        this.f20431e = 0;
        this.f20432f = 0;
        this.f20433g = this.a.toCharArray();
        String e2 = e();
        if (e2 == null) {
            return null;
        }
        do {
            int i2 = this.f20430c;
            if (i2 == this.b) {
                return null;
            }
            char c2 = this.f20433g[i2];
            if (c2 == '\"') {
                f2 = f();
            } else if (c2 != '#') {
                f2 = (c2 == '+' || c2 == ',' || c2 == ';') ? "" : a();
            } else {
                f2 = d();
            }
            if (str.equalsIgnoreCase(e2)) {
                return f2;
            }
            int i3 = this.f20430c;
            if (i3 >= this.b) {
                return null;
            }
            char[] cArr = this.f20433g;
            if (cArr[i3] != ',' && cArr[i3] != ';' && cArr[i3] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.a);
            }
            this.f20430c = i3 + 1;
            e2 = e();
        } while (e2 != null);
        throw new IllegalStateException("Malformed DN: " + this.a);
    }
}
