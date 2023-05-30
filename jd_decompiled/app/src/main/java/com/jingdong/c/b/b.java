package com.jingdong.c.b;

import android.content.Context;
import android.text.TextUtils;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.Arrays;

/* loaded from: classes10.dex */
public final class b {

    /* renamed from: f  reason: collision with root package name */
    private static long f12288f = -1;
    private final Context a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f12289c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private final c[] f12290e;

    /* renamed from: com.jingdong.c.b.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static class C0403b {
        Context a;
        boolean b;

        /* renamed from: c  reason: collision with root package name */
        boolean f12291c;
        boolean d;

        /* renamed from: f  reason: collision with root package name */
        boolean f12293f;

        /* renamed from: e  reason: collision with root package name */
        boolean f12292e = false;

        /* renamed from: g  reason: collision with root package name */
        boolean f12294g = false;

        /* renamed from: h  reason: collision with root package name */
        boolean f12295h = false;

        /* renamed from: i  reason: collision with root package name */
        boolean f12296i = false;

        /* renamed from: j  reason: collision with root package name */
        boolean f12297j = false;

        public b a() {
            return new b(this);
        }

        public C0403b b(Context context) {
            if (context != null) {
                this.a = context;
                return this;
            }
            throw new NullPointerException("context == null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class c implements Comparable<c> {

        /* renamed from: g  reason: collision with root package name */
        String f12298g;

        /* renamed from: h  reason: collision with root package name */
        String f12299h;

        public c() {
        }

        public c(String str, String str2) {
            this.f12298g = str;
            this.f12299h = str2;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private int a() {
            char c2;
            String str = this.f12298g;
            str.hashCode();
            switch (str.hashCode()) {
                case 107855:
                    if (str.equals(Constant.KEY_MAC)) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 3236040:
                    if (str.equals("imei")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 115652350:
                    if (str.equals("randomUUID")) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1131700202:
                    if (str.equals("androidId")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    return 3000;
                case 1:
                    return 4000;
                case 2:
                    return 1000;
                case 3:
                    return 2000;
                default:
                    return -1;
            }
        }

        @Override // java.lang.Comparable
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final int compareTo(c cVar) {
            return a() - cVar.a();
        }

        public final String toString() {
            return "Entity{key='" + this.f12298g + "', value='" + this.f12299h + "'}";
        }
    }

    private b(C0403b c0403b) {
        this.f12290e = new c[]{new c(), new c()};
        k.a(c0403b.a);
        this.a = c0403b.a;
        this.b = c0403b.f12294g ? c0403b.f12291c : k.c("wifi_mac_readable");
        this.f12289c = c0403b.f12295h ? c0403b.d : k.c("imei_readable");
        this.d = c0403b.f12296i ? c0403b.f12293f : k.c("file_cache_enabled");
        boolean z = c0403b.f12292e;
        h.c(c0403b.f12297j ? c0403b.b : k.c("loggable"));
    }

    public final void a(String str, String str2) {
        c cVar;
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        int i2 = 0;
        if (TextUtils.equals(str, "imei")) {
            c[] cVarArr = this.f12290e;
            cVarArr[0].f12299h = str2;
            cVar = cVarArr[0];
        } else if (TextUtils.equals(str, Constant.KEY_MAC)) {
            c[] cVarArr2 = this.f12290e;
            cVarArr2[1].f12299h = str2;
            cVar = cVarArr2[1];
        } else {
            int i3 = 0;
            while (true) {
                c[] cVarArr3 = this.f12290e;
                if (i3 >= cVarArr3.length) {
                    while (true) {
                        c[] cVarArr4 = this.f12290e;
                        if (i2 >= cVarArr4.length) {
                            return;
                        }
                        if (TextUtils.isEmpty(cVarArr4[i2].f12299h)) {
                            c[] cVarArr5 = this.f12290e;
                            cVarArr5[i2].f12299h = str2;
                            cVar = cVarArr5[i2];
                            break;
                        }
                        c cVar2 = new c(str, str2);
                        if (this.f12290e[i2].compareTo(cVar2) < 0) {
                            this.f12290e[i2] = cVar2;
                        }
                        i2++;
                    }
                } else if (TextUtils.equals(cVarArr3[i3].f12298g, str)) {
                    return;
                } else {
                    i3++;
                }
            }
        }
        cVar.f12298g = str;
    }

    public final Context b() {
        return this.a;
    }

    public final void c() {
        for (String str : i.b) {
            a(str, i.a().b(str));
        }
    }

    public final boolean d() {
        return this.d;
    }

    public final boolean e() {
        return this.f12289c;
    }

    public final boolean f() {
        return this.b;
    }

    public final boolean g() {
        return System.currentTimeMillis() - f12288f > 60000;
    }

    public final String toString() {
        return "Request: loggable=" + h.a + ", wifiMacReadable=" + this.b + ", imeiReadable=" + this.f12289c + ", fileCacheEnabled=" + this.d + ", idSlot=" + Arrays.toString(this.f12290e);
    }
}
