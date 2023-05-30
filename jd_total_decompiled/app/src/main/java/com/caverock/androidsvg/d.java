package com.caverock.androidsvg;

/* loaded from: classes.dex */
class d {
    private int a;
    private long b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(long j2, int i2) {
        this.b = j2;
        this.a = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d b(String str, int i2, int i3) {
        long j2;
        int i4;
        if (i2 >= i3) {
            return null;
        }
        long j3 = 0;
        int i5 = i2;
        while (i5 < i3) {
            char charAt = str.charAt(i5);
            if (charAt < '0' || charAt > '9') {
                if (charAt >= 'A' && charAt <= 'F') {
                    j2 = j3 * 16;
                    i4 = charAt - 'A';
                } else if (charAt < 'a' || charAt > 'f') {
                    break;
                } else {
                    j2 = j3 * 16;
                    i4 = charAt - 'a';
                }
                j3 = j2 + i4 + 10;
            } else {
                j3 = (j3 * 16) + ((long) (charAt - '0'));
            }
            if (j3 > 4294967295L) {
                return null;
            }
            i5++;
        }
        if (i5 == i2) {
            return null;
        }
        return new d(j3, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d c(String str, int i2, int i3, boolean z) {
        if (i2 >= i3) {
            return null;
        }
        if (z) {
            char charAt = str.charAt(i2);
            if (charAt != '+') {
                r1 = charAt == '-';
            }
            i2++;
        }
        long j2 = 0;
        int i4 = i2;
        while (i4 < i3) {
            char charAt2 = str.charAt(i4);
            if (charAt2 < '0' || charAt2 > '9') {
                break;
            }
            if (r1) {
                j2 = (j2 * 10) - ((long) (charAt2 - '0'));
                if (j2 < -2147483648L) {
                    return null;
                }
            } else {
                j2 = (j2 * 10) + ((long) (charAt2 - '0'));
                if (j2 > 2147483647L) {
                    return null;
                }
            }
            i4++;
        }
        if (i4 == i2) {
            return null;
        }
        return new d(j2, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.a;
    }

    public int d() {
        return (int) this.b;
    }
}
