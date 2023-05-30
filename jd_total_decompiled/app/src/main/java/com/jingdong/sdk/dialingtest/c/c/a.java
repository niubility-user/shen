package com.jingdong.sdk.dialingtest.c.c;

import javax.security.auth.x500.X500Principal;

/* loaded from: classes7.dex */
final class a {
    private final String a;
    private final int b;

    /* renamed from: c  reason: collision with root package name */
    private int f14725c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f14726e;

    /* renamed from: f  reason: collision with root package name */
    private int f14727f;

    /* renamed from: g  reason: collision with root package name */
    private char[] f14728g;

    public a(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.a = name;
        this.b = name.length();
    }

    private int a(int i2) {
        int i3;
        int i4;
        int i5 = i2 + 1;
        if (i5 < this.b) {
            char[] cArr = this.f14728g;
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

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a3, code lost:
        return new java.lang.String(r1, r2, r8.f14727f - r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String b() {
        int i2;
        int i3;
        int i4 = this.f14725c;
        this.d = i4;
        this.f14726e = i4;
        while (true) {
            int i5 = this.f14725c;
            if (i5 >= this.b) {
                char[] cArr = this.f14728g;
                int i6 = this.d;
                return new String(cArr, i6, this.f14726e - i6);
            }
            char[] cArr2 = this.f14728g;
            char c2 = cArr2[i5];
            if (c2 == ' ') {
                int i7 = this.f14726e;
                this.f14727f = i7;
                this.f14725c = i5 + 1;
                this.f14726e = i7 + 1;
                cArr2[i7] = ' ';
                while (true) {
                    i2 = this.f14725c;
                    i3 = this.b;
                    if (i2 >= i3) {
                        break;
                    }
                    char[] cArr3 = this.f14728g;
                    if (cArr3[i2] != ' ') {
                        break;
                    }
                    int i8 = this.f14726e;
                    this.f14726e = i8 + 1;
                    cArr3[i8] = ' ';
                    this.f14725c = i2 + 1;
                }
                if (i2 == i3) {
                    break;
                }
                char[] cArr4 = this.f14728g;
                if (cArr4[i2] == ',' || cArr4[i2] == '+' || cArr4[i2] == ';') {
                    break;
                }
            } else if (c2 == ';') {
                break;
            } else if (c2 == '\\') {
                int i9 = this.f14726e;
                this.f14726e = i9 + 1;
                cArr2[i9] = d();
                this.f14725c++;
            } else if (c2 == '+' || c2 == ',') {
                break;
            } else {
                int i10 = this.f14726e;
                this.f14726e = i10 + 1;
                cArr2[i10] = cArr2[i5];
                this.f14725c = i5 + 1;
            }
        }
        char[] cArr5 = this.f14728g;
        int i11 = this.d;
        return new String(cArr5, i11, this.f14726e - i11);
    }

    private char d() {
        int i2 = this.f14725c + 1;
        this.f14725c = i2;
        if (i2 != this.b) {
            char[] cArr = this.f14728g;
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
                                return e();
                        }
                }
            }
            return cArr[i2];
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private char e() {
        int i2;
        int i3;
        int a = a(this.f14725c);
        this.f14725c++;
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
            int i5 = this.f14725c + 1;
            this.f14725c = i5;
            if (i5 == this.b || this.f14728g[i5] != '\\') {
                return '?';
            }
            int i6 = i5 + 1;
            this.f14725c = i6;
            int a2 = a(i6);
            this.f14725c++;
            if ((a2 & 192) != 128) {
                return '?';
            }
            i3 = (i3 << 6) + (a2 & 63);
        }
        return (char) i3;
    }

    private String f() {
        int i2;
        int i3 = this.f14725c;
        if (i3 + 4 < this.b) {
            this.d = i3;
            this.f14725c = i3 + 1;
            while (true) {
                i2 = this.f14725c;
                if (i2 == this.b) {
                    break;
                }
                char[] cArr = this.f14728g;
                if (cArr[i2] == '+' || cArr[i2] == ',' || cArr[i2] == ';') {
                    break;
                } else if (cArr[i2] == ' ') {
                    this.f14726e = i2;
                    this.f14725c = i2 + 1;
                    while (true) {
                        int i4 = this.f14725c;
                        if (i4 >= this.b || this.f14728g[i4] != ' ') {
                            break;
                        }
                        this.f14725c = i4 + 1;
                    }
                } else {
                    if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                        cArr[i2] = (char) (cArr[i2] + ' ');
                    }
                    this.f14725c = i2 + 1;
                }
            }
            this.f14726e = i2;
            int i5 = this.f14726e;
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
                return new String(this.f14728g, this.d, i7);
            }
            throw new IllegalStateException("Unexpected end of DN: " + this.a);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private String g() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        while (true) {
            i2 = this.f14725c;
            i3 = this.b;
            if (i2 >= i3 || this.f14728g[i2] != ' ') {
                break;
            }
            this.f14725c = i2 + 1;
        }
        if (i2 == i3) {
            return null;
        }
        this.d = i2;
        this.f14725c = i2 + 1;
        while (true) {
            i4 = this.f14725c;
            i5 = this.b;
            if (i4 >= i5) {
                break;
            }
            char[] cArr = this.f14728g;
            if (cArr[i4] == '=' || cArr[i4] == ' ') {
                break;
            }
            this.f14725c = i4 + 1;
        }
        if (i4 < i5) {
            this.f14726e = i4;
            if (this.f14728g[i4] == ' ') {
                while (true) {
                    i6 = this.f14725c;
                    i7 = this.b;
                    if (i6 >= i7) {
                        break;
                    }
                    char[] cArr2 = this.f14728g;
                    if (cArr2[i6] == '=' || cArr2[i6] != ' ') {
                        break;
                    }
                    this.f14725c = i6 + 1;
                }
                if (this.f14728g[i6] != '=' || i6 == i7) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.a);
                }
            }
            this.f14725c++;
            while (true) {
                int i8 = this.f14725c;
                if (i8 >= this.b || this.f14728g[i8] != ' ') {
                    break;
                }
                this.f14725c = i8 + 1;
            }
            int i9 = this.f14726e;
            int i10 = this.d;
            if (i9 - i10 > 4) {
                char[] cArr3 = this.f14728g;
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
            char[] cArr4 = this.f14728g;
            int i13 = this.d;
            return new String(cArr4, i13, this.f14726e - i13);
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private String h() {
        int i2 = this.f14725c + 1;
        this.f14725c = i2;
        this.d = i2;
        this.f14726e = i2;
        while (true) {
            int i3 = this.f14725c;
            if (i3 != this.b) {
                char[] cArr = this.f14728g;
                if (cArr[i3] == '\"') {
                    this.f14725c = i3 + 1;
                    while (true) {
                        int i4 = this.f14725c;
                        if (i4 >= this.b || this.f14728g[i4] != ' ') {
                            break;
                        }
                        this.f14725c = i4 + 1;
                    }
                    char[] cArr2 = this.f14728g;
                    int i5 = this.d;
                    return new String(cArr2, i5, this.f14726e - i5);
                }
                if (cArr[i3] == '\\') {
                    cArr[this.f14726e] = d();
                } else {
                    cArr[this.f14726e] = cArr[i3];
                }
                this.f14725c++;
                this.f14726e++;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.a);
            }
        }
    }

    public String c(String str) {
        String h2;
        this.f14725c = 0;
        this.d = 0;
        this.f14726e = 0;
        this.f14727f = 0;
        this.f14728g = this.a.toCharArray();
        String g2 = g();
        if (g2 == null) {
            return null;
        }
        do {
            int i2 = this.f14725c;
            if (i2 == this.b) {
                return null;
            }
            char c2 = this.f14728g[i2];
            if (c2 == '\"') {
                h2 = h();
            } else if (c2 != '#') {
                h2 = (c2 == '+' || c2 == ',' || c2 == ';') ? "" : b();
            } else {
                h2 = f();
            }
            if (str.equalsIgnoreCase(g2)) {
                return h2;
            }
            int i3 = this.f14725c;
            if (i3 >= this.b) {
                return null;
            }
            char[] cArr = this.f14728g;
            if (cArr[i3] != ',' && cArr[i3] != ';' && cArr[i3] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.a);
            }
            this.f14725c = i3 + 1;
            g2 = g();
        } while (g2 != null);
        throw new IllegalStateException("Malformed DN: " + this.a);
    }
}
