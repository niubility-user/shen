package com.xiaomi.push;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public abstract class j1 {

    /* loaded from: classes11.dex */
    public static class a extends i1 {
        public a() {
            super(1);
        }

        @Override // com.xiaomi.push.i1
        public String b(Context context, String str, List<i0> list) {
            URL url;
            if (list == null) {
                url = new URL(str);
            } else {
                Uri.Builder buildUpon = Uri.parse(str).buildUpon();
                for (i0 i0Var : list) {
                    buildUpon.appendQueryParameter(i0Var.a(), i0Var.b());
                }
                url = new URL(buildUpon.toString());
            }
            return j0.h(context, url);
        }
    }

    static int a(int i2, int i3) {
        return (((i3 + 243) / R2.attr.motionTarget) * 132) + R2.attr.internalMinHeight + i2 + i3;
    }

    static int b(int i2, int i3, int i4) {
        return (((i3 + 200) / R2.attr.motionTarget) * 132) + 1011 + i3 + i2 + i4;
    }

    private static int c(i1 i1Var, String str, List<i0> list, String str2) {
        if (i1Var.a() == 1) {
            return a(str.length(), d(str2));
        }
        if (i1Var.a() == 2) {
            return b(str.length(), e(list), d(str2));
        }
        return -1;
    }

    static int d(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    static int e(List<i0> list) {
        int i2 = 0;
        for (i0 i0Var : list) {
            if (!TextUtils.isEmpty(i0Var.a())) {
                i2 += i0Var.a().length();
            }
            if (!TextUtils.isEmpty(i0Var.b())) {
                i2 += i0Var.b().length();
            }
        }
        return i2 * 2;
    }

    public static String f(Context context, String str, List<i0> list) {
        return g(context, str, list, new a(), true);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00aa A[Catch: MalformedURLException -> 0x00c3, TRY_ENTER, TryCatch #4 {MalformedURLException -> 0x00c3, blocks: (B:4:0x000f, B:6:0x0016, B:8:0x0020, B:11:0x0027, B:13:0x002d, B:14:0x0030, B:15:0x0035, B:17:0x003b, B:19:0x0044, B:21:0x004c, B:49:0x00aa, B:50:0x00bc), top: B:64:0x000f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String g(Context context, String str, List<i0> list, i1 i1Var, boolean z) {
        b1 b1Var;
        IOException iOException;
        String str2;
        String str3;
        if (j0.p(context)) {
            try {
                ArrayList<String> arrayList = new ArrayList<>();
                if (z) {
                    b1 a2 = f1.c().a(str);
                    if (a2 != null) {
                        arrayList = a2.d(str);
                    }
                    b1Var = a2;
                } else {
                    b1Var = null;
                }
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
                Iterator<String> it = arrayList.iterator();
                String str4 = null;
                while (it.hasNext()) {
                    String next = it.next();
                    ArrayList arrayList2 = list != null ? new ArrayList(list) : null;
                    long currentTimeMillis = System.currentTimeMillis();
                    try {
                    } catch (IOException e2) {
                        iOException = e2;
                        str2 = str4;
                    }
                    if (!i1Var.c(context, next, arrayList2)) {
                        return str4;
                    }
                    String b = i1Var.b(context, next, arrayList2);
                    try {
                    } catch (IOException e3) {
                        e = e3;
                        str3 = b;
                    }
                    if (!TextUtils.isEmpty(b)) {
                        if (b1Var != null) {
                            try {
                                b1Var.l(next, System.currentTimeMillis() - currentTimeMillis, c(i1Var, next, arrayList2, b));
                            } catch (IOException e4) {
                                iOException = e4;
                                str2 = b;
                                if (b1Var != null) {
                                    b1Var.m(next, System.currentTimeMillis() - currentTimeMillis, c(i1Var, next, arrayList2, str2), iOException);
                                }
                                iOException.printStackTrace();
                                str4 = str2;
                            }
                        }
                        return b;
                    }
                    if (b1Var != null) {
                        str3 = b;
                        try {
                            b1Var.m(next, System.currentTimeMillis() - currentTimeMillis, c(i1Var, next, arrayList2, b), null);
                        } catch (IOException e5) {
                            e = e5;
                            String str5 = str3;
                            iOException = e;
                            str2 = str5;
                            if (b1Var != null) {
                            }
                            iOException.printStackTrace();
                            str4 = str2;
                        }
                    } else {
                        str3 = b;
                    }
                    str4 = str3;
                }
                return str4;
            } catch (MalformedURLException e6) {
                e6.printStackTrace();
            }
        }
        return null;
    }
}
