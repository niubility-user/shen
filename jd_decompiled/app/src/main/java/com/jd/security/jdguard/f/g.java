package com.jd.security.jdguard.f;

/* loaded from: classes17.dex */
public class g {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            a = iArr;
            try {
                iArr[b.debug.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.info.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[b.error.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[b.verbose.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[b.warning.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes17.dex */
    public enum b {
        info,
        debug,
        error,
        verbose,
        warning
    }

    public static void a(String str, String str2) {
        c(str, str2, b.debug);
    }

    public static void b(String str, String str2) {
        c(str, str2, b.error);
    }

    private static void c(String str, String str2, b bVar) {
        if (str2.length() > 4000) {
            int i2 = 0;
            while (i2 < str2.length()) {
                int i3 = i2 + 4000;
                if (i3 < str2.length()) {
                    d(str, str2.substring(i2, i3), bVar);
                } else {
                    d(str, str2.substring(i2, str2.length()), bVar);
                }
                i2 = i3;
            }
            return;
        }
        d(str, str2, bVar);
    }

    private static void d(String str, String str2, b bVar) {
        int i2 = a.a[bVar.ordinal()];
    }

    public static void e(String str, String str2) {
        c(str, str2, b.warning);
    }
}
